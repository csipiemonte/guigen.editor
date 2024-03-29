/**
 * <copyright>
 * (C) Copyright 2011 CSI-PIEMONTE;

 * Concesso in licenza a norma dell'EUPL, esclusivamente versione 1.1;
 * Non e' possibile utilizzare l'opera salvo nel rispetto della Licenza.
 * E' possibile ottenere una copia della Licenza al seguente indirizzo:
 *
 * http://www.eupl.it/opensource/eupl-1-1
 *
 * Salvo diversamente indicato dalla legge applicabile o concordato per 
 * iscritto, il software distribuito secondo i termini della Licenza e' 
 * distribuito "TAL QUALE", SENZA GARANZIE O CONDIZIONI DI ALCUN TIPO,
 * esplicite o implicite.
 * Si veda la Licenza per la lingua specifica che disciplina le autorizzazioni
 * e le limitazioni secondo i termini della Licenza.
 * </copyright>
 *
 * $Id$
 */
package it.csi.mddtools.guigen.presentation.GUIModelNormalizerWizard;

import it.csi.mddtools.guigen.ActivationParam;
import it.csi.mddtools.guigen.AppDataBinding;
import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.ApplicationData;
import it.csi.mddtools.guigen.ApplicationDataDefs;
import it.csi.mddtools.guigen.ComplexType;
import it.csi.mddtools.guigen.ContentPanel;
import it.csi.mddtools.guigen.DataWidget;
import it.csi.mddtools.guigen.EventHandler;
import it.csi.mddtools.guigen.ExecCommand;
import it.csi.mddtools.guigen.Field;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.MultiDataWidget;
import it.csi.mddtools.guigen.SecurityModel;
import it.csi.mddtools.guigen.SimpleType;
import it.csi.mddtools.guigen.Type;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.TypedArray;
import it.csi.mddtools.guigen.Typedefs;
import it.csi.mddtools.guigen.Widget;
import it.csi.mddtools.guigen.genutils.EditUtils;
import it.csi.mddtools.guigen.genutils.GenUtils;
import it.csi.mddtools.guigen.genutils.GenUtilsChecks;
import it.csi.mddtools.guigen.presentation.GuigenModelWizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class GUIModelNormalizer {

	
	String modelFileName;
	String commonFilesFolderName;
	IWorkspaceRoot wkspcRoot;
	IProgressMonitor monitor;
	
	// risorse
	ResourceSet resourceSet;
	Resource guiModelRes;
	Resource commonTNSRes;
	Resource commonAppdataRes;
	
	ArrayList<Resource> resourcesToSave = new ArrayList<Resource>();
	
	// frammenti di modello
	GUIModel guimodel;
	TypeNamespace commonTNS;
	AppDataGroup commonAppdata;
	
	Map<Object, Object> options = new HashMap<Object, Object>();
	
	
	
	public GUIModelNormalizer(String modelFileName,
			String commonFilesFolderName, IWorkspaceRoot wkspcRoot,
			IProgressMonitor monitor) {
		super();
		this.modelFileName = modelFileName;
		this.commonFilesFolderName = commonFilesFolderName;
		this.wkspcRoot = wkspcRoot;
		this.monitor = monitor;
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
	}
	
	public void normalize() throws CoreException{
		initExistingResources();
		includeStdLib();
		externalizeSecurityModel();
		externalizeTypes();
		externalizeAppdatas();
		externalizeAppModules();
		saveAll();
	}

	/**
	 * Salva tutte le nuove risorse pi� la risorsa del modello (che � sicuramente stata modificata)
	 */
	private void saveAll()  throws CoreException{
		for (Iterator<Resource> iterator = resourcesToSave.iterator(); iterator.hasNext();) {
			Resource res = iterator.next();
			try {
				res.save(options);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void externalizeAppModules()  throws CoreException{
		monitor.setTaskName("esternalizzazione degli application module (nella cartella 'module')");
		ArrayList<AppModule> oldmods = new ArrayList<AppModule>();
		oldmods.addAll(guimodel.getStructure().getAppWindow().getAppArea().getModules());
		Iterator<AppModule> it_mod = oldmods.iterator();
		while (it_mod.hasNext()) {
			AppModule mod = it_mod.next();
			Resource modResource = createNewResource(""+mod.getName()+"Module.guigen", "module");
			modResource.getContents().add(mod);
			if (!guimodel.getStructure().getAppWindow().getAppArea().getExtModules().contains(mod)){
				guimodel.getStructure().getAppWindow().getAppArea().getModules().remove(mod);
				guimodel.getStructure().getAppWindow().getAppArea().getExtModules().add(mod);
			}
		}
		monitor.worked(1);
	}

	private void externalizeAppdatas()  throws CoreException{
		monitor.setTaskName("esternalizzazione degli application data group (nella cartella 'appdata')");
		ArrayList<AppDataGroup> oldadgs = new ArrayList<AppDataGroup>();
		oldadgs.addAll(guimodel.getAppDataDefs().getGroups());
		Iterator<AppDataGroup> it_adg = oldadgs.iterator();
		while (it_adg.hasNext()) {
			AppDataGroup adg = it_adg.next();
			Resource adgResource = createNewResource(""+adg.getName()+"Appdata.guigen", "appdata");
			adgResource.getContents().add(adg);
			if (!guimodel.getAppDataDefs().getExtGroups().contains(adg)){
				guimodel.getAppDataDefs().getGroups().remove(adg);
				guimodel.getAppDataDefs().getExtGroups().add(adg);
			}
		}
		monitor.worked(1);
	}

	/**
	 * Per ogni TypeNamespace esternalizza la risorsa, se gi� non esternalizzata
	 * @throws CoreException
	 */
	private void externalizeTypes()  throws CoreException{
		monitor.setTaskName("esternalizzazione dei type namespace (nella cartella 'tns'");
		ArrayList<TypeNamespace> oldTNSs = new ArrayList<TypeNamespace>();
		oldTNSs.addAll(guimodel.getTypedefs().getNamespaces());
		Iterator<TypeNamespace> it_tns = oldTNSs.iterator();
		while (it_tns.hasNext()) {
			TypeNamespace tns = it_tns.next();
			Resource tnsResource = createNewResource(""+tns.getName()+"TNS.guigen", "tns");
			tnsResource.getContents().add(tns);
			if (!guimodel.getTypedefs().getExtNamespaces().contains(tns)){
				guimodel.getTypedefs().getNamespaces().remove(tns);
				guimodel.getTypedefs().getExtNamespaces().add(tns);
			}
		}
		monitor.worked(1);
	}

	/**
	 * Esternalizza il security model se gi� non esternalizzato
	 * @throws CoreException
	 */
	private void externalizeSecurityModel()  throws CoreException{
		SecurityModel secMod = guimodel.getSecurityModel();
		if (secMod!=null){
			monitor.setTaskName("esternalizzazione del security model nel file 'securityModel.guigen'");
			guimodel.setSecurityModel(null);
			guimodel.setExtSecurityModel(secMod);
			Resource secModRes = createNewResource("securityModel.guigen", null);
			secModRes.getContents().add(secMod);
			monitor.worked(1);
		}
	}

	private Resource createNewResource(String name, String optSubfolderName) {
		URI uri = null;
		if (optSubfolderName!=null && optSubfolderName.length()>0){
			uri = guiModelRes.getURI().trimSegments(1).appendSegment(optSubfolderName).appendSegment(name);
		}
		else{
			uri = guiModelRes.getURI().trimSegments(1).appendSegment(name);
		}
		Resource res = resourceSet.createResource(uri);
		resourcesToSave.add(res);
		return res;
	}

	/**
	 * [a] inclusione delle standard library (tipi e appdata). 
	 * 1-inclusione e referenziazione (se gi� non referenziato) del TypeNamespace [common] (dal file commonTNS.guigen) 
	 * 2-inclusione e referenziazione (se gi� non referenziato) dell' AppDataGroup [common] (dal file commonTNS.guigen) 
	 * 3-ripuntamento di tutti i tipi semplici e array di tipi semplici e UserInfo e TreeNode dal type namespace di base (old style) ai corrispondenti tipi del TypeNamespace [common]. In Widget, complexTypes definiti da utente 
	 * 4-ripuntamento di tutti i content panel che referenziavano l'appdata [currentUser] in modo che puntino al [common.currentUser] 
	 * 5-ripuntamento di eventuali appdatabinding che puntavano all'appdata [currentUser] in modo che puntino a [common.currentuser] 
	 * 6-ripuntamento di eventuali postExecData che puntavano a [currentuser] in modo che puntino a [common.currentUser] 
	 * 7-eliminazione dei tipi gi� presenti in commonTNS.guigen dal modello principale 
	 * 8-eliminazione dell'appdata [currentUser] dall'appdata group di default del modello principale 
	 * @throws CoreException
	 */
	private void includeStdLib()  throws CoreException{
		monitor.setTaskName("inclusione liberie standard di tipi e appdata");
		// a.1
		guimodel.getTypedefs().getExtNamespaces().add(commonTNS);
		// a.2
		guimodel.getAppDataDefs().getExtGroups().add(commonAppdata);
		// a.3
		moveBaseTypeRefs();
		// a.4, a.5, a.6
		moveCurrentUserRefs();
		// a.7
		dropObsoleteBaseTypes();
		// a.8
		dropObsoleteAppData();
		monitor.worked(1);
	}

	/**
	 * cancella dall'appdatagroup di default tutti gli appdata
	 */
	private void dropObsoleteAppData() {
		guimodel.getAppDataDefs().getAppData().clear();
	}

	/**
	 * cancella dal namespace di default i tipi contenuti anche in commonTNS
	 */
	private void dropObsoleteBaseTypes() {
		guimodel.getTypedefs().getTypes().clear();
	}

	private void moveCurrentUserRefs() {
		monitor.setTaskName("aggiornamento dei puntamenti all'application data 'currentUser' nei pannelli");
		moveCurrentUserRefsFromUI();
		monitor.worked(1);
		monitor.setTaskName("aggiornamento dei puntamenti all'application data 'currentUser' negli ExecCommand");
		moveCurrentUserRefsFromExecCommands();
		monitor.worked(1);
	}


	/**
	 * aggiorna i puntamenti all'appdata 'currentUser' nei postExecData degli ExecCommand
	 */
	private void moveCurrentUserRefsFromExecCommands() {
		List<ContentPanel> allCP = GenUtils.getAllContentPanels(guimodel);
		Iterator<ContentPanel> it_cp = allCP.iterator();
		while (it_cp.hasNext()) {
			ContentPanel cp = it_cp.next();
			List<Widget> allW = GenUtils.findAllWidgetsInContentPanel(cp);
			Iterator<Widget> it_w = allW.iterator();
			while (it_w.hasNext()) {
				Widget widget = it_w.next();
				Iterator<EventHandler> it_evh = widget.getEventHandlers().iterator();
				while (it_evh.hasNext()) {
					EventHandler evh = it_evh.next();
					List<ExecCommand> allExec = GenUtils.getAllExecActionsForEventHandler(evh);
					Iterator<ExecCommand> it_exec = allExec.iterator();
					while (it_exec.hasNext()) {
						ExecCommand exec = it_exec.next();
						replaceAppDataRef("curentUser", exec, getNewCurrentUserAppdata());
					}
					
				}
			}
		}
	}

	private void replaceAppDataRef(String appdataName, ExecCommand exec,
			ApplicationData newAppdata) {
		Iterator<ApplicationData> it_appd = exec.getPostExecData().iterator();
		while (it_appd.hasNext()) {
			ApplicationData appd = it_appd.next();
			if (appd.getName().equals(appdataName)){
				exec.getPostExecData().remove(appd);
				exec.getPostExecData().add(newAppdata);
				break;
			}
		}
		
	}

	private ApplicationData getNewCurrentUserAppdata() {
		Iterator<ApplicationData> it_appd = commonAppdata.getAppData().iterator();
		while (it_appd.hasNext()) {
			ApplicationData appd = it_appd.next();
			if (appd.getName().equals("currentUser")){
				return appd;
			}
		}
		return null;
	}
	
	private void replaceAppDataRef(String appdataName, Widget widget,
			ApplicationData newAppdata) {
		if (widget instanceof DataWidget){
			AppDataBinding db = ((DataWidget)widget).getDatabinding();
			if (db!=null && db.getAppData().getName().equals(appdataName))
				db.setAppData(getNewCurrentUserAppdata());
		}
		if (widget instanceof MultiDataWidget){
			AppDataBinding mdb = ((MultiDataWidget)widget).getMultiDataBinding();
			if (mdb!=null && mdb.getAppData().getName().equals(appdataName))
				mdb.setAppData(newAppdata);
		}
		
	}

	private void replaceAppDataRef(String appdataName, ContentPanel cp,
			ApplicationData newAppdata) {
		Iterator<ApplicationData> appd_it = cp.getAppData().iterator();
		while (appd_it.hasNext()) {
			ApplicationData appd = appd_it.next();
			if (appd.getName().equals(appdataName)){
				cp.getAppData().remove(appd);
				cp.getAppData().add(newAppdata);
				break;
			}
			
		}
		
	}

	/**
	 * Aggiorna i puntamenti all'appdata 'currentUser' nei content panel e nei data binding.
	 */
	private void moveCurrentUserRefsFromUI() {
		List<ContentPanel> allCP = GenUtils.getAllContentPanels(guimodel);
		Iterator<ContentPanel> it_cp = allCP.iterator();
		while (it_cp.hasNext()) {
			ContentPanel cp = it_cp.next();
			replaceAppDataRef("currentUser", cp, getNewCurrentUserAppdata());
			List<Widget> allW = GenUtils.findAllWidgetsInContentPanel(cp);
			Iterator<Widget> it_w = allW.iterator();
			while (it_w.hasNext()) {
				Widget widget = it_w.next();
				replaceAppDataRef("currentUser", widget, getNewCurrentUserAppdata());
			}
		}
	}

	

	// a.3
	private void moveBaseTypeRefs() {
		// sposta tutti i tipi semplici dagli user defined data
		monitor.setTaskName("aggiornamento dei tipi base referenziati negli user defined Types");
		moveBaseTypeRefsFromUserTypes();
		monitor.worked(1);
		// sposta tutti i tipi semplici dai widget
		monitor.setTaskName("aggiornamento dei tipi base referenziati dai widget");
		moveBaseTypeRefsFromWidgets();
		monitor.worked(1);
		// sposta i tipi dei parametri di init.
		monitor.setTaskName("aggiornamento dei tipi base referenziati dai parametri di inizializzazione");
		moveBaseTypeRefsFromInitParams();
		monitor.worked(1);
		// sposta tutti i tipi semplici dagli appdata binding
		monitor.setTaskName("aggiornamento dei tipi base referenziati negli application data");
		moveBaseTypeRefsFromAppData();
		monitor.worked(1);
	}

	private void moveBaseTypeRefsFromInitParams() {
		if (guimodel.getActivationModel()!=null && guimodel.getActivationModel().getActivationParams()!=null){
			Iterator<ActivationParam> it_ap = guimodel.getActivationModel().getActivationParams().iterator();
			while (it_ap.hasNext()) {
				ActivationParam ap = it_ap.next();
				if(ap.getType() instanceof SimpleType)
					ap.setType((SimpleType)getRemappedBaseType(ap.getType()));
			}
		}
	}

	//////////////////////////////////////////////////////
	/// moveBaseTypeRefsFromUserTypes
	//////////////////////////////////////////////////////
	
	private void moveBaseTypeRefsFromAppData() {
		ApplicationDataDefs appdatadefs = guimodel.getAppDataDefs();
		moveBaseTypeRefsFromAppdatas(appdatadefs.getAppData());
		Iterator<AppDataGroup> it_adg = appdatadefs.getGroups().iterator();
		while (it_adg.hasNext()) {
			AppDataGroup group = it_adg.next();
			moveBaseTypeRefsFromAppdatas(group.getAppData());
		}
		
	}

	private void moveBaseTypeRefsFromAppdatas(EList<ApplicationData> appDatas) {
		Iterator<ApplicationData> it_ad = appDatas.iterator();
		while (it_ad.hasNext()) {
			ApplicationData ad = it_ad.next();
			if (ad.getType() instanceof SimpleType)
				ad.setType(getRemappedBaseType(ad.getType()));
			else if (ad.getType() instanceof TypedArray && (((TypedArray)ad.getType()).getComponentType() instanceof SimpleType)){
				ad.setType(getRemappedBaseType(ad.getType()));
			}
			else if (ad.getType() instanceof ComplexType && ad.getType().getName().equals("UserInfo")){
				ad.setType(getRemappedBaseType(ad.getType()));
			}
		}
	}

	//////////////////////////////////////////////////////
	
//////////////////////////////////////////////////////
/// moveBaseTypeRefsFromUserTypes
//////////////////////////////////////////////////////
	
	/**
	 * Occorre solo effettuare il ripuntamento di:
	 * tipi dei field di complex types per i complex types del tns rtoot e di tutti i tns nested se il
	 * tipo del field � un Simple o un TypedArray con component type Simple. 
	 * Non genera risorse extra da salvare 
	 */
	private void moveBaseTypeRefsFromUserTypes() {
		Typedefs typedefs = guimodel.getTypedefs();
		moveBaseTypeRefsFromTypes(typedefs.getTypes());
		Iterator<TypeNamespace> it_ns = typedefs.getNamespaces().iterator();
		while (it_ns.hasNext()) {
			TypeNamespace typeNamespace = it_ns.next();
			moveBaseTypeRefsFromTypes(typeNamespace.getTypes());
		}
		
	}

	
	private void moveBaseTypeRefsFromTypes(EList<Type> types) {
		Iterator<Type> it = types.iterator();
		while (it.hasNext()) {
			moveBaseTypeRefsFromType(it.next(), types); 
		}
	}
	
	private void moveBaseTypeRefsFromType(Type type, EList<Type> tlist) {
		if (type instanceof SimpleType)
			moveBaseTypeRefsFromType((SimpleType)type, tlist);
		else if(type instanceof TypedArray)
			moveBaseTypeRefsFromType((TypedArray)type, tlist);
		else if(type instanceof ComplexType)
			moveBaseTypeRefsFromType((ComplexType)type, tlist);
	}
	
	private void moveBaseTypeRefsFromType(SimpleType type, EList<Type> tlist) {
		// NOP
	}
	
	private void moveBaseTypeRefsFromType(TypedArray type, EList<Type> tlist) {
		// NOP
	}
	
	private void moveBaseTypeRefsFromType(ComplexType type, EList<Type> tlist) {
		Iterator<Field> f_it = type.getFields().iterator();
		while (f_it.hasNext()) {
			Field field = f_it.next();
			if (field.getType() instanceof SimpleType
					|| (field.getType() instanceof TypedArray && ((TypedArray) field
							.getType()).getComponentType() instanceof SimpleType)
					|| (field.getType() instanceof ComplexType && field
							.getType().getName().equals("UserInfo"))
				) {
				field.setType(getRemappedBaseType(field.getType()));
			}
		}
	}
	
	


////////////////////////////
	
	
	//////////////////////////////////////////////////////
	/// moveBaseTypeRefsFromWidgets
	//////////////////////////////////////////////////////
	
	private void moveBaseTypeRefsFromWidgets() {
		Iterator<ContentPanel> it_cp = GenUtils.getAllContentPanels(guimodel.getStructure().getAppWindow().getAppArea()).iterator();
		while (it_cp.hasNext()) {
			ContentPanel cp = it_cp.next();
			ArrayList<Widget> allW = GenUtils.findAllWidgetsInContentPanel(cp);
			Iterator<Widget> it_w = allW.iterator();
			while (it_w.hasNext()) {
				Widget widget = it_w.next();
				if(widget instanceof DataWidget){
					Type oldT = ((DataWidget) widget).getDataType();
					((DataWidget) widget).setDataType(getRemappedBaseType(oldT));
				}
			}	
		}
		
	}

/////////////////////////////
	
	/**
	 * Carica le risorse iniziali (model, common tns, common appdata)
	 */
	private void initExistingResources() throws CoreException{
		monitor.setTaskName("caricamento risorse");
		try{
		resourceSet = new ResourceSetImpl();
		URI modelFileURI = URI.createPlatformResourceURI(modelFileName, true);
		guiModelRes = resourceSet.createResource(modelFileURI);
		guiModelRes.load(options);
		resourcesToSave.add(guiModelRes);
		guimodel = (GUIModel)(guiModelRes.getContents().get(0));
		
		URI commonTNSFileURI = URI.createPlatformResourceURI(commonFilesFolderName+"/commonTNS.guigen", true);
		commonTNSRes = resourceSet.createResource(commonTNSFileURI);
		commonTNSRes.load(options);
		commonTNS = (TypeNamespace)(commonTNSRes.getContents().get(0));
		
		URI commonAppdataFileURI = URI.createPlatformResourceURI(commonFilesFolderName+"/commonAppdata.guigen", true);
		commonAppdataRes = resourceSet.createResource(commonAppdataFileURI);
		commonAppdataRes.load(options);
		commonAppdata = (AppDataGroup)(commonAppdataRes.getContents().get(0));
		}
		catch (Exception e) {
			//throw new CoreException(status);
			e.printStackTrace();
		}
		finally{
			monitor.worked(1);
		}
	}
	
	
	/////
	private Type getRemappedBaseType(Type oldType){
		EList<Type> commonTNSTypes = commonTNS.getTypes();
		Iterator<Type> type_it = commonTNSTypes.iterator();
		while (type_it.hasNext()) {
			Type currType = type_it.next();
			if (GenUtilsChecks.typeWeakEquals(oldType, currType))
				return currType;
		}
		return oldType;
	}

	private Type findTypeInNamespace(String name, Class typeClass,
			TypeNamespace tns) {
		
		Iterator<Type> it = tns.getTypes().iterator();
		while (it.hasNext()) {
			Type type = it.next();
			if (type.getClass()==typeClass && type.getName().equals(name)){
				return type;
			}
		}
		return null;
	}

}
