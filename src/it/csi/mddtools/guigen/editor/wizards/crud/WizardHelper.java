package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.ActivateMultiPanelItemCommand;
import it.csi.mddtools.guigen.AppDataBinding;
import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.ApplicationArea;
import it.csi.mddtools.guigen.ApplicationData;
import it.csi.mddtools.guigen.Button;
import it.csi.mddtools.guigen.CPCommand;
import it.csi.mddtools.guigen.CPCommandExecutionTypes;
import it.csi.mddtools.guigen.CPCommands;
import it.csi.mddtools.guigen.Calendar;
import it.csi.mddtools.guigen.CheckBox;
import it.csi.mddtools.guigen.ClearAppdataCommand;
import it.csi.mddtools.guigen.Column;
import it.csi.mddtools.guigen.ColumnModel;
import it.csi.mddtools.guigen.CommandFunctions;
import it.csi.mddtools.guigen.CommandOutcome;
import it.csi.mddtools.guigen.CommandPanel;
import it.csi.mddtools.guigen.ComplexType;
import it.csi.mddtools.guigen.ContentPanel;
import it.csi.mddtools.guigen.DataLifetimeType;
import it.csi.mddtools.guigen.EventHandler;
import it.csi.mddtools.guigen.EventTypes;
import it.csi.mddtools.guigen.ExecCommand;
import it.csi.mddtools.guigen.Field;
import it.csi.mddtools.guigen.FormPanel;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.HorizontalFlowPanelLayout;
import it.csi.mddtools.guigen.JumpCommand;
import it.csi.mddtools.guigen.MsgBoxPanel;
import it.csi.mddtools.guigen.MultiDataWidget;
import it.csi.mddtools.guigen.MultiPanel;
import it.csi.mddtools.guigen.NOPCommand;
import it.csi.mddtools.guigen.RefreshViewCommand;
import it.csi.mddtools.guigen.SequenceCommand;
import it.csi.mddtools.guigen.SimpleType;
import it.csi.mddtools.guigen.SimpleTypeCodes;
import it.csi.mddtools.guigen.StdMessagePanel;
import it.csi.mddtools.guigen.Table;
import it.csi.mddtools.guigen.TextField;
import it.csi.mddtools.guigen.Type;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.TypedArray;
import it.csi.mddtools.guigen.Typedefs;
import it.csi.mddtools.guigen.UDLRCPanelLayout;
import it.csi.mddtools.guigen.UDLRCSpecConstants;
import it.csi.mddtools.guigen.UDLRCWidgetLayoutSpec;
import it.csi.mddtools.guigen.VerticalFlowPanelLayout;
import it.csi.mddtools.guigen.WidgetLayoutSpecifier;
import it.csi.mddtools.guigen.WidgetsPanel;
import it.csi.mddtools.guigen.genutils.GenUtils;
import it.csi.mddtools.guigen.impl.WidgetLayoutSpecifierImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.Options;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;



public class WizardHelper {
	
	private static final String FOLDER_NAME_MOD_SEC = "modules";
	
	private static final String EXT_FILE = ".guigen";
	
	private static final String APPDATA = "Appdata";
	
	private static final String CP_RICERCA = "cpRicerca";
	
	private static final String FP_RICERCA = "fpRicerca";
	
	private static final String BT_RICERCA ="btRicerca";
	
	private static final String ELENCO = "elenco";
	
	private static final String FILTRO = "filtro";
	
	private static final String ID = "id";
	
	private static final String SELEZIONATO = "Selezionato";
	
	private static final String DETTAGLIO = "dettaglio";
	
	private static final String PARENTESI_QUADRE = "[]";
	
	private static final String RICERCA = "Ricerca";
	
	private static final String NUOVA = "nuova";
	
	private static final String BLANK = " ";
	
	private static final String RESULT_CODE = "OK";
	
	private static final String RESULT_CODE_KO = "KO";
	
	private static final String MULTI_PANEL = "mp";
	
	private static final String TAB = "tab";
	
	private static final String FORM_PANEL = "fp";
	
	private static final String WP = "wp";
	
	private static final String BT = "bt";
	
	private static final String TEXT_FIELD = "tf";
	
	private static final String CALENDAR = "cal";
	
	private static final String ELIMINA = "Elimina";
	
	private static final String INSERISCI = "Inserisci";
	
	private static final String SALVA = "Salva";

	private static final String SHOW_DETAIL = "SHOW_DETAIL";

	private static final String NO_ITEM_SELECTED ="NO_ITEM_SELECTED";
	
	private static final String STANDARD_MSG = "stdMsg";

	private static final String CHECK_BOX = "chk";
	
	
	private static ApplicationData appDataFiltro = null;
	
	private static AppModule appModule = null;
	
	
	public static AppModule getAppModule() {
		return appModule;
	}

	public static void setAppModule(AppModule appModule) {
		WizardHelper.appModule = appModule;
	}

	public static ApplicationData getAppDataFiltro() {
		return appDataFiltro;
	}

	public static void setAppDataFiltro(ApplicationData appDataFiltro) {
		WizardHelper.appDataFiltro = appDataFiltro;
	}
	
	private static ApplicationData appDataPK = null;
	
	public static ApplicationData getAppDataPK() {
		return appDataPK;
	}

	public static void setAppDataPK(ApplicationData appDataPK) {
		WizardHelper.appDataPK = appDataPK;
	}
	
	private static ApplicationData appDataDett = null;
	
	public static ApplicationData getAppDataDett() {
		return appDataDett;
	}

	public static void setAppDataDett(ApplicationData appDataDett) {
		WizardHelper.appDataDett = appDataDett;
	}
	
	private static ApplicationData appDataElencoEntita = null;
	

	public static ApplicationData getAppDataElencoEntita() {
		return appDataElencoEntita;
	}

	public static void setAppDataElencoEntita(ApplicationData appDataElencoEntita) {
		WizardHelper.appDataElencoEntita = appDataElencoEntita;
	}

	public static ResourceSet resourceSet = null;
	
	public static ResourceSet creaResultSet() {
		resourceSet = new ResourceSetImpl();
		return resourceSet;
	}
	
	public static ResourceSet getResourceSet() {
		return resourceSet;
	}

	public static void setResourceSet(ResourceSet resourceSet) {
		WizardHelper.resourceSet = resourceSet;
	}
	
	public static Resource modPrincResource = null;
	
	


	public static Resource getModPrincResource() {
		return modPrincResource;
	}

	public static void setModPrincResource(Resource modPrincResource) {
		WizardHelper.modPrincResource = modPrincResource;
	}

	private static Resource resourceAppMod = null;
	
	public static Resource getResourceAppMod() {
		return resourceAppMod;
	}

	public static void setResourceAppMod(Resource resourceAppMod) {
		WizardHelper.resourceAppMod = resourceAppMod;
	}

	private static GUIModel guiModel;//8-10-2012
	
	
	 public static GUIModel getGuiModel() {
		return guiModel;
	}

	public static void setGuiModel(GUIModel guiModel) {
		WizardHelper.guiModel = guiModel;
	}


	static File  file;  
	


	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		WizardHelper.file = file;
	}

	private static Resource risorsaAppdDataGroup = null;
	
	
	

	public static Resource getRisorsaAppdDataGroup() {
		return risorsaAppdDataGroup;
	}

	public static void setRisorsaAppdDataGroup(Resource risorsaAppdDataGroup) {
		WizardHelper.risorsaAppdDataGroup = risorsaAppdDataGroup;
	}

	/**
	 * This caches an instance of the model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GuigenPackage guigenPackage = GuigenPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GuigenFactory guigenFactory = guigenPackage.getGuigenFactory();
	
	
	private static  Set<String> listaUnica = new HashSet<String>();
	
	private static  Set<String> listaUnicaTab = new HashSet<String>();
	
	

	public static Set<String> getListaUnica() {
		return listaUnica;
	}
	
	public static  String[] getListaFiltri() {
		  return (String[])listaUnica.toArray(new String[listaUnica.size()]);
	}
	
	public static  String[] getListaCampi() {
			return (String[] )listaUnicaTab.toArray(new String[listaUnicaTab.size()]);
	}

	public static void setListaUnica(Set<String> listaUnica) {
		WizardHelper.listaUnica = listaUnica;
	}
	
	

	public static Set<String> getListaUnicaTab() {
		return listaUnicaTab;
	}

	public static void setListaUnicaTab(Set<String> listaUnicaTab) {
		WizardHelper.listaUnicaTab = listaUnicaTab;
	}
	

//	private static EObject getResourceGen(String filename) throws IOException {
//		
//			EObject emfMod = null;
//			String modPrincFilePath = filename;
//			URI modPrincFileURI = URI.createPlatformResourceURI(modPrincFilePath, true);
//			
//			Resource modPrincResource = resourceSet.createResource(modPrincFileURI);
//			Map<Object, Object> options = new HashMap<Object, Object>();
//			
//			modPrincResource.load(options);
//			EList<EObject> emfModPrincContent = (EList)modPrincResource.getContents();
//			if(emfModPrincContent != null) {
//				emfMod = emfModPrincContent.get(0);
//			}
//			
//		   return emfMod;
//	}
	
	public static Resource getResourceGen(String filename) throws IOException {
		
		Resource resource = null;
		
		String modPrincFilePath = filename;
		URI modPrincFileURI = URI.createPlatformResourceURI(modPrincFilePath, true);
		
		if(resourceSet != null) {
		
		 resource = resourceSet.createResource(modPrincFileURI);
		 Map<Object, Object> options = new HashMap<Object, Object>();
		
			resource.load(options);
		}
		
	   return resource;
}
	
	public static boolean caricaModPrinc(String nomeFile) throws IOException {
		EObject emfMod = null;
		boolean res = false;
		
		creaResultSet();
		
		Resource resource = getResourceGen(nomeFile);
		if(resource != null) {
			
			EList<EObject> resourceContent = resource.getContents();
			
			if(resourceContent != null && resourceContent.size()> 0) {
				emfMod = resourceContent.get(0);
			
				if(isModPrinc(emfMod)) {
					setGuiModel((GUIModel)emfMod);
					setModPrincResource(resource);
					res = true;
				 }else {
					resourceSet = null;
				}
			}
		}
		return res;
	}
	

	
	public static boolean isModPrinc(EObject modello) throws IOException {
		boolean res = false;
			if (modello instanceof GUIModel) {
				res = true;
			}
			return res;
	}
	
//	public static boolean caricaModPrinc(String nomeFile) throws IOException {
//		boolean res = false;
//		
//		creaResultSet();
//		
//		EObject emfMod = getResourceGen(nomeFile);
//		
//		if(isModPrinc(emfMod)) {
//			setGuiModel((GUIModel)emfMod);
//			res = true;
//		}else {
//			resourceSet = null;
//		}
//		return res;
//	}
	
	public static EList<TypeNamespace>  getAllNamespace(GUIModel guiModel) throws IOException {
		
		Typedefs tipi = guiModel.getTypedefs();
		
		EList<TypeNamespace> listaNameSpace = tipi.getExtNamespaces();
		
			return listaNameSpace;
		}
	
	
	
		
		public static List<String>  getNameComplexTypeByNamespace(EList<TypeNamespace> listaNamespace) throws IOException {
			// listaTipoComplex =(EList<Type>) new ArrayList <Type>();
			EList<Type> listaTipi;
			List<String> listaNomeTipiComplex = new ArrayList<String>();
			for (TypeNamespace typeNamespace : listaNamespace) {
			  	listaTipi = typeNamespace.getTypes();
			  	for(Type tipo : listaTipi) {
			  		if(tipo instanceof ComplexType) {
			  			listaNomeTipiComplex.add(tipo.getName());
			  		}
			  	}
			}
			Collections.sort(listaNomeTipiComplex);
			return listaNomeTipiComplex;
		}
		
		
		public static List<String> getNameComplexTypeByModPrinc(GUIModel guiModel)  {
			
			Typedefs tipi = guiModel.getTypedefs();
			
			EList<Type> listaTipi;
			EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
			
			List<String> listaNomeTipiComplex = new ArrayList<String>();
			for (TypeNamespace typeNamespace : listaNamespace) {
			  	listaTipi = typeNamespace.getTypes();
			  	for(Type tipo : listaTipi) {
			  		if(tipo instanceof ComplexType) {
			  			listaNomeTipiComplex.add(tipo.getName());
			  		}
			  	}
			}
			Collections.sort(listaNomeTipiComplex);
			return listaNomeTipiComplex;
		}
		
	//nuovo 	
public static  Map<String, Type> getNameComplexTypeByModPrincMapp(GUIModel guiModel)  {
			
			Typedefs tipi = guiModel.getTypedefs();
			
			EList<Type> listaTipi;
			EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
			
			Map <String, Type> mapListaAttrEntita = new HashMap<String, Type>();
			for (TypeNamespace typeNamespace : listaNamespace) {
			  	listaTipi = typeNamespace.getTypes();
			  	for(Type tipo : listaTipi) {
			  		if(tipo instanceof ComplexType) {
			  			mapListaAttrEntita.put(tipo.getName(), tipo);
			  		}
			  	}
			}
			
			return mapListaAttrEntita;
		}
		
//nuovvvvvvv
public static List<String> getNameComplexTypeByModPrincFull(GUIModel guiModel, WizardInfo info)  {

	
	Typedefs tipi = guiModel.getTypedefs();
	
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	
	List<String> listaNomeTipiComplex = new ArrayList<String>();
	
	Map<String, Type> mapNomeTipo =  new HashMap<String, Type>();
	
	for (TypeNamespace typeNamespace : listaNamespace) {
	  	listaTipi = typeNamespace.getTypes();
	  	for(Type tipo : listaTipi) {
	  		if(tipo instanceof ComplexType) {
	  			listaNomeTipiComplex.add(tipo.getName());
	  			mapNomeTipo.put(tipo.getName(), tipo);
	  		}
	  	}
	}
	info.setMapNomeTipo(mapNomeTipo);
	
	Collections.sort(listaNomeTipiComplex);
	
	return listaNomeTipiComplex;
}
		public static String[] getVettoreByList(List<String> lista) {
			
			
			//ci ho pensato dopo questa devo poi sostituirla  lista.toArray(new String[lista.size()]);
			
			int dim = lista.size();
			String[] items= new String[dim];
			for (int i = 0; i < lista.size(); i++) {
				items[i] = (String)lista.get(i);
			}
			return items;
		}
		
//		public static String[] getNameComplexType(GUIModel guiModel) {
//	
//			List<String> lista = WizardHelper.getNameComplexTypeByModPrinc(guiModel);
//			
//				return WizardHelper.getVettoreByList(lista);
//		
//		}
		public static String[] getNameComplexType(GUIModel guiModel, WizardInfo info) {
			
					List<String> lista = WizardHelper.getNameComplexTypeByModPrincFull(guiModel, info); 
					
						return WizardHelper.getVettoreByList(lista);
				
				}
		
		
		public static Type getTipoByNameEntita(String nomeEntita, WizardInfo info) {
			
			Type tipoEntita = (Type)info.getMapNomeTipo().get(nomeEntita);
			
				return tipoEntita;
		
		}
//nuovo		
public static  List<String> getListaAttrByNameEntitaByInfo(String nomeEntita, WizardInfo info) {
			
			Type tipoEntita = (Type)info.getMapNomeTipo().get(nomeEntita);
			
			EList<Field> listaAttributi =((ComplexType) tipoEntita).getAllFields();
			
			List<String> listaAttrEntita = new ArrayList<String>();
			
 			 for(Field attr :listaAttributi) {
 				 
 				 if(attr.getType() instanceof SimpleType) {
 					
 					listaAttrEntita.add(attr.getName());
 				 }
 			 }
 			 return listaAttrEntita;
		}

//nuovo		
public static  Map<String, Type> getMapAttrByNameEntitaByInfo(String nomeEntita, WizardInfo info) {
			
			Type tipoEntita = (Type)info.getMapNomeTipo().get(nomeEntita);
			
			EList<Field> listaAttributi =((ComplexType) tipoEntita).getAllFields();
			
			Map <String, Type> mapListaAttrEntita = new HashMap<String, Type>();
			
			 for(Field attr :listaAttributi) {
				 
				 if(attr.getType() instanceof SimpleType) {
					 mapListaAttrEntita.put(attr.getName(), attr.getType());
				 }
			 }
			 return mapListaAttrEntita;
		}

public static  List<String> getListaAttrByNameEntitaByInfoFull(String nomeEntita, WizardInfo info) {
	
	Type tipoEntita = (Type)info.getMapNomeTipo().get(nomeEntita);
	
	EList<Field> listaAttributi =((ComplexType) tipoEntita).getAllFields();
	
	List<String> listaAttrEntita = new ArrayList<String>();
	Map <String, Type> mapListaAttrEntita = new HashMap<String, Type>();
	
		 for(Field attr :listaAttributi) {
			 
			 if(attr.getType() instanceof SimpleType) {
				
				listaAttrEntita.add(attr.getName());
				 mapListaAttrEntita.put(attr.getName(), attr.getType());
			 }
		 }
		 info.setMapListaAttrEntita(mapListaAttrEntita);
		 info.setListaAttrEntita(listaAttrEntita);
		 
		 return listaAttrEntita;
}

		
		public static EList<Field> getListaAttributiByComplexType(ComplexType tipoEntita) {
			
				return  ((ComplexType) tipoEntita).getAllFields();
				
		}
		
		public static List<String> getListaAttributi(EList<Field> listaAttributi) {
			
			List<String> listaAttrEntita = new ArrayList<String>();
			
		  			 for(Field attr :listaAttributi) {
		  				 
		  				 if(attr.getType() instanceof SimpleType) {
		  					
		  					listaAttrEntita.add(attr.getName());
		  				 }
		  			 }
		  			 return listaAttrEntita;
		}
		
		public static Map<String, Type> getMappaAttributi(EList<Field> listaAttributi) {
			
			Map <String, Type> mapListaAttrEntita = new HashMap<String, Type>();
		
		  			 for(Field attr :listaAttributi) {
		  				 
		  				 if(attr.getType() instanceof SimpleType) {
		  					
		  					mapListaAttrEntita.put(attr.getName(), attr.getType());
		  				 }
		  			 }
		  			 return mapListaAttrEntita;
		}

		public static void setIdPkByName(String item) {
			
			
		}

		public static String[] creaListaFiltri( String[] listaAttrSel) {
			
			for (int i = 0; i < listaAttrSel.length; i++) {
	        	 
	        	 listaUnica.add(listaAttrSel[i]);
	        	 
	        }
			return (String[] )listaUnica.toArray(new String[listaUnica.size()]);
			
		}

		public static String[] rimuoviCampiFiltri(String[] listaAttrDelete) {
			 for (int i = 0; i < listaAttrDelete.length; i++) {
		        	
	        	  listaUnica.remove(listaAttrDelete[i]);
	        	
	          } 
	          return (String[] )listaUnica.toArray(new String[listaUnica.size()]);
			
		}

		public static void pulisciCampiFiltri() {
			listaUnica.clear();
		}
		
		public static String[] creaListaColonneTab( String[] listaAttrSel) {
			
			for (int i = 0; i < listaAttrSel.length; i++) {
	        	 
				listaUnicaTab.add(listaAttrSel[i]);
	        	 
	        }
			return (String[] )listaUnicaTab.toArray(new String[listaUnicaTab.size()]);
			
		}
		
		public static String[] rimuoviCampiTab(String[] listaAttrDelete) {
			 for (int i = 0; i < listaAttrDelete.length; i++) {
		        	
				 listaUnicaTab.remove(listaAttrDelete[i]);
	        	
	          } 
	          return (String[] )listaUnicaTab.toArray(new String[listaUnicaTab.size()]);
			
		}
		
		public static void pulisciCampiTab() {
			listaUnicaTab.clear();
			
		}
		
		
		public static void creaAppMod(String nomeEntita) throws IOException {
			AppModule nuovoAppMod = null;
			Map<Object, Object> options = new HashMap<Object, Object>();
			String uri = "";
			String  lowerNomeEntita = nomeEntita.toLowerCase();
			
			uri = file.getParent().toString().substring(1)+"/"+ FOLDER_NAME_MOD_SEC + "/" + lowerNomeEntita+ "/" + lowerNomeEntita+EXT_FILE;
			
			URI modAppNuovoFileURI = URI.createPlatformResourceURI(uri, true);
			
			Resource resourceAppModule = resourceSet.createResource(modAppNuovoFileURI);
			
			nuovoAppMod = guigenFactory.createAppModule();
			nuovoAppMod.setName(nomeEntita);
			nuovoAppMod.setSecure(true);
			
			guiModel.getStructure().getAppWindow().getAppArea().getExtModules().add(nuovoAppMod);
			resourceAppModule.getContents().add(nuovoAppMod);
			
			setAppModule(nuovoAppMod);//9-10-2012
			setResourceAppMod(resourceAppModule);
			
			resourceAppModule.save(options);
			WizardHelper.modPrincResource.save(options);
			
//	9-10 mattina		String uriModPrin = file.getFullPath().toString();
//			URI urimod = URI.createPlatformResourceURI(uriModPrin, true);
//			Resource resModPrinc = resourceSet.getResource(urimod, true);
//			resModPrinc.save(options);

		}
		
		public static void creaAppDataGroup(String nomeEntita, WizardInfo info) throws IOException {
			Map<Object, Object> options = new HashMap<Object, Object>();
			String uri = "";
			String  lowerNomeEntita = nomeEntita.toLowerCase();
			//mi preparo a costruire il file
			uri = file.getParent().toString().substring(1)+"/"+ FOLDER_NAME_MOD_SEC + "/" + lowerNomeEntita+ "/" + lowerNomeEntita+APPDATA+EXT_FILE;
			
			URI appDataGroupFileURI = URI.createPlatformResourceURI(uri, true);
			
			Resource appDataResource = resourceSet.createResource(appDataGroupFileURI);
			
			//creo l'App Data Group
			AppDataGroup appDataGroup = guigenFactory.createAppDataGroup();
			appDataGroup.setName(lowerNomeEntita+APPDATA);
			 
			//creo appdata per il filtro dell'entità
			ApplicationData adf = guigenFactory.createApplicationData();
			adf.setName(FILTRO+nomeEntita);
			adf.setLifetimeExtent(DataLifetimeType.USER_SESSION);
			Type tipoEntita = getTipoByNameEntita(nomeEntita, info);
			adf.setType(tipoEntita);
			//setto la varibile di Helper
			appDataFiltro = adf;
			//lo aggiungo all'appdata group
			appDataGroup.getAppData().add(adf);
			
			//creo appdata per la PK
			ApplicationData adPK = guigenFactory.createApplicationData();
			adPK.setLifetimeExtent(DataLifetimeType.USER_SESSION);
			//adPK.setName(info.getIdPrimaryKey());
			adPK.setName(ID+SELEZIONATO+nomeEntita);
			//recupero il tipo della PK
			Type tipoPK = WizardHelper.getTypeByAttrName(info.getIdPrimaryKey(), info);
			adPK.setType(tipoPK);
			//imposto la variabile dell'helper
			appDataPK = adPK;
			//lo aggiungo all'appdata group
			appDataGroup.getAppData().add(adPK);
			
			//creo appdata per dettaglio
			ApplicationData adDett = guigenFactory.createApplicationData();
			adDett.setName(DETTAGLIO+nomeEntita);
			adDett.setLifetimeExtent(DataLifetimeType.USER_SESSION);
			adDett.setType(tipoEntita);
			//setto la varibile di Helper
			appDataDett = adDett;
			//lo aggiungo all'appdata group
			appDataGroup.getAppData().add(adDett);
			
			//creo appdata per elencoNomeEntità ovvero un vettore dell'entità
			ApplicationData adElencoNomeEntita = guigenFactory.createApplicationData();
			adElencoNomeEntita.setName(ELENCO+nomeEntita);
			adElencoNomeEntita.setLifetimeExtent(DataLifetimeType.USER_SESSION);
			//per definire il tipo devo costruire un vettore di oggetti di tipo nomeEntita NOOOOOO DEVO RECUPERARLO
//			TypedArray tipoElencoNomeEntita = guigenFactory.createTypedArray();
//			tipoElencoNomeEntita.setName(ELENCO+nomeEntita+PARENTESI_QUADRE);
//			tipoElencoNomeEntita.setComponentType(tipoEntita);
			//comentato 12-10 Type tipoElencoNomeEntita = getTipoArrayByName(nomeEntita);
			//12-10-2012
			Type tipoElencoNomeEntita = getTipoArrayProva(nomeEntita, info);
			
			
			
			adElencoNomeEntita.setType(tipoElencoNomeEntita);
			//setto la varibile di Helper
			appDataElencoEntita = adElencoNomeEntita;
			//lo aggiungo all'appdata group
			appDataGroup.getAppData().add(adElencoNomeEntita);
			
			//associo l'appaData group al modello 
			guiModel.getAppDataDefs().getExtGroups().add(appDataGroup);
			appDataResource.getContents().add(appDataGroup);
			
			//11-10-2012 start 
			setRisorsaAppdDataGroup(appDataResource);
			//11-10-2012 end
			appDataResource.save(options);
			modPrincResource.save(options);
			
			
			
		}
		public static Type getTypeByAttrName(String nomeAttributo, WizardInfo info) throws IOException {
			 Map<String, Type> mapListaAttrEntita = info.getMapListaAttrEntita();
			Type tipoAttr =  mapListaAttrEntita.get(nomeAttributo);
			 
			return tipoAttr;
		}
		

		
		public static void creaCPRicercaByAppModule(WizardInfo info) throws IOException {
			
			Map<Object, Object> options = new HashMap<Object, Object>();
			
			 ContentPanel cp = guigenFactory.createContentPanel();
			 
			 FormPanel fp = guigenFactory.createFormPanel();
			 
			
			 
			 guiModel.getStructure().getAppWindow().getAppArea().setHomePage(cp);
			 
			 cp.setName(CP_RICERCA+info.getNomeEntita());
			 //aggiungere app data al cp
			 cp.getAppData().add(appDataFiltro);
			 cp.getAppData().add(appDataPK);
			 cp.getAppData().add(appDataDett);
			 cp.getAppData().add(appDataElencoEntita);
			 
			 fp.setName(FP_RICERCA+info.getNomeEntita());
			 fp.setLabel(RICERCA+ " "+info.getNomeEntita());
			 
			 UDLRCPanelLayout udlrPL = guigenFactory.createUDLRCPanelLayout();
			 fp.setLayout(udlrPL);
			 
			 FormPanel fpUp = guigenFactory.createFormPanel();
			 fpUp.setName("fpUp");
			 //nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
			 fpUp.setLayout(vfpUp);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgUp.setValue(UDLRCSpecConstants.UP);
			 fpUp.setLayoutSpec(udlrcWdgUp);
			
			 FormPanel fpCenter = guigenFactory.createFormPanel();
			 fpCenter.setName("fpCenter");
			//nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpCenter = guigenFactory.createVerticalFlowPanelLayout();
			 fpCenter.setLayout(vfpCenter);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgCenter = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgCenter.setValue(UDLRCSpecConstants.CENTER);
			 fpCenter.setLayoutSpec(udlrcWdgCenter);
			 
			 //15-10-2012
			 StdMessagePanel stdMsgPanel = guigenFactory.createStdMessagePanel();
			 stdMsgPanel.setName(STANDARD_MSG+RICERCA+info.getNomeEntita());
			 stdMsgPanel.setShowFieldErrorDetails(true);
			 stdMsgPanel.setShowFieldErrors(true);
			 stdMsgPanel.setShowGlobalMessages(true);
			 fpCenter.getSubpanels().add(stdMsgPanel);
			 
			 
			 WidgetsPanel wdgPanel = guigenFactory.createWidgetsPanel();
			 wdgPanel.setName(WP+upperFirstLetter(FILTRO)+RICERCA+info.getNomeEntita());
			 
			 VerticalFlowPanelLayout vfplWdg = guigenFactory.createVerticalFlowPanelLayout();
			 wdgPanel.setLayout(vfplWdg);
			 
			 fpCenter.getSubpanels().add(wdgPanel);
			 //16-10 start
			 String[] campiFiltri = getListaFiltri();//costituita da tipi semplici
			 creaWidgetSemplici(campiFiltri, appDataFiltro, info, wdgPanel);
			 //16-10 end
			 
			 FormPanel fpLeft = guigenFactory.createFormPanel();
			 fpLeft.setName("fpLeft");
			//nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpLeft = guigenFactory.createVerticalFlowPanelLayout();
			 fpLeft.setLayout(vfpLeft);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgLeft = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgLeft.setValue(UDLRCSpecConstants.LEFT);
			 fpLeft.setLayoutSpec(udlrcWdgLeft);
			 //inserisco il form panel nel content panel 
			 cp.setPanels(fp);
			 //inserisco i tre figli form panel nel form panel padre
			fp.getSubpanels().add(fpUp);
			fp.getSubpanels().add(fpCenter);
			fp.getSubpanels().add(fpLeft);
			
			//11-10 parte nuova costruisco il command panel 
			 CommandPanel cmdPanel = guigenFactory.createCommandPanel();
			 cmdPanel.setName("cmdFiltroRicerca"+info.getNomeEntita());
			 
			 HorizontalFlowPanelLayout hfpl = guigenFactory.createHorizontalFlowPanelLayout();
			 cmdPanel.setLayout(hfpl);
			 
			 fpCenter.getSubpanels().add(cmdPanel);
			 
			 Button btRicerca = guigenFactory.createButton();
			 btRicerca.setName(BT_RICERCA);
			 btRicerca.setLabel(RICERCA);
			 //16-10
			 btRicerca.setFunctionSpecifier(CommandFunctions.SEARCH);
			 
			 cmdPanel.getWidgets().add(btRicerca);
			 
			 EventHandler evHandlerRicerca = guigenFactory.createEventHandler();
			 evHandlerRicerca.setEventType(EventTypes.CLICKED);
			
			 btRicerca.getEventHandlers().add(evHandlerRicerca);
			 
			 ExecCommand execCommandRicerca = guigenFactory.createExecCommand();
			 execCommandRicerca.setMethodName(lowerFirstLetter(RICERCA)+info.getNomeEntita());
			 
			 CommandOutcome cmdOutOkRic = guigenFactory.createCommandOutcome();
			 cmdOutOkRic.setResultCode(RESULT_CODE);
			 
			 evHandlerRicerca.setCommand(execCommandRicerca);//ok
			 execCommandRicerca.getResults().add(cmdOutOkRic);
			 
			 //15-10
			 CommandOutcome cmdOutKoRic = guigenFactory.createCommandOutcome();
			 cmdOutKoRic.setResultCode(RESULT_CODE_KO);
			 execCommandRicerca.getResults().add(cmdOutKoRic);
			 //seq ricerca ok 
			 SequenceCommand seqCmdRicerca = guigenFactory.createSequenceCommand();
			 //seq ricerca ko 
			 SequenceCommand seqCmdRicercaKo = guigenFactory.createSequenceCommand();
			 
			 RefreshViewCommand refreshVCRicerca = guigenFactory.createRefreshViewCommand();
			 refreshVCRicerca.getTargetWidgets().add(btRicerca);
			 refreshVCRicerca.getTargetPanels().add(fpCenter);
			 
			 RefreshViewCommand refreshVCRicercaKo = guigenFactory.createRefreshViewCommand();
			 refreshVCRicercaKo.getTargetWidgets().add(btRicerca);
			 refreshVCRicercaKo.getTargetPanels().add(fpCenter);
			 
			 //aggancio mul
			 ActivateMultiPanelItemCommand activeMPanelItemCmd = guigenFactory.createActivateMultiPanelItemCommand();
			 ActivateMultiPanelItemCommand activeMPanelItemCmdKo = guigenFactory.createActivateMultiPanelItemCommand();
			
			 //costruisco il multi panel
			 MultiPanel multiPanel = guigenFactory.createMultiPanel();
			 multiPanel.setName(MULTI_PANEL+upperFirstLetter(ELENCO)+info.getNomeEntita());
			 multiPanel.setLabel(ELENCO+info.getNomeEntita());
			 fpCenter.getSubpanels().add(multiPanel);///////ccccccccccccccccc
			 
			 activeMPanelItemCmd.setMultipanel(multiPanel);//imposto il multi panel
			 activeMPanelItemCmdKo.setMultipanel(multiPanel);//17-10
			
			 //costruisco il form panel per la tabella
			 FormPanel fpMultiPanelTab = guigenFactory.createFormPanel();
			 fpMultiPanelTab.setName(FORM_PANEL+upperFirstLetter(ELENCO)+info.getNomeEntita());
			 
			 activeMPanelItemCmd.setActiveItem(fpMultiPanelTab);//imposto il form panel per la tabella 
			
			 // ok prima ma tolto per nuovo modello cmdOut.setCommand(activeMPanelItemCmd);//nuovo 12-10 modificata il 15-10-2012 con seqcmd
			
			 seqCmdRicerca.getCommands().add(activeMPanelItemCmd);//15-10
			 seqCmdRicerca.getCommands().add(refreshVCRicerca); //15-10
			 
			 seqCmdRicercaKo.getCommands().add(activeMPanelItemCmdKo);//17-10
			 seqCmdRicercaKo.getCommands().add(refreshVCRicercaKo); //17-10
			 
			 cmdOutOkRic.setCommand(seqCmdRicerca);//15-10 add non vedevo
			 cmdOutKoRic.setCommand(seqCmdRicercaKo);//15-10 add non vedevo
			 //17-10 cmdOutKoRic.setCommand(refreshVCRicercaKo);
			 
			 multiPanel.getPanels().add(fpMultiPanelTab);//pomer
			 
			 VerticalFlowPanelLayout vfpl = guigenFactory.createVerticalFlowPanelLayout();
			 fpMultiPanelTab.setLayout(vfpl);
			 
			 WidgetsPanel wdgPanelElencoEntita = guigenFactory.createWidgetsPanel();
			 wdgPanelElencoEntita.setName(WP+upperFirstLetter(ELENCO)+info.getNomeEntita());
			 wdgPanelElencoEntita.setLabel(upperFirstLetter(ELENCO)+" "+info.getNomeEntita());
			 
			 VerticalFlowPanelLayout vfplWdgElencoEntita = guigenFactory.createVerticalFlowPanelLayout();
			 wdgPanelElencoEntita.setLayout(vfplWdgElencoEntita);
			 
			 fpMultiPanelTab.getSubpanels().add(wdgPanelElencoEntita);
			 
			
			 Table tabella = guigenFactory.createTable();
			 tabella.setName(TAB+info.getNomeEntita());
			 Type tipoPk = getTypeByAttrName(info.getIdPrimaryKey(), info);
			 tabella.setDataType( tipoPk);
			
		
			 AppDataBinding appDataBindingTab = guigenFactory.createAppDataBinding();
			 appDataBindingTab.setAppData(appDataPK);
			 tabella.setDatabinding(appDataBindingTab);
			 
			 AppDataBinding multiDataBind = guigenFactory.createAppDataBinding();
			 multiDataBind.setAppData(appDataElencoEntita);
			 tabella.setMultiDataBinding(multiDataBind);
			 
			 wdgPanelElencoEntita.getWidgets().add(tabella);
			 
			 ColumnModel columnModel= guigenFactory.createColumnModel();
			 columnModel.setValueSelector(info.getIdPrimaryKey());
			 //ciclo per costruire le colonne selezioante nella 5 pag del wizard
			 //for()
			 //recupero la lista dei nomi delle colonne
			 String[] colTabScelte = getListaCampi();//costituita da tipi semplici
			 Column col = null;
			 SimpleType tipoAttr = null; //16-10
			 for (int i = 0; i < colTabScelte.length; i++) {
				 
					tipoAttr = (SimpleType)getTypeByAttrName(colTabScelte[i], info);
					col = guigenFactory.createColumn();
					col.setSelector(colTabScelte[i]);
					col.setLabel(splitCamelCase(colTabScelte[i]));
					columnModel.getColumns().add(col);/////aiiiiiiiiiiiiiiiiiiiii
			 }
			
			 
			 tabella.setColumnModel(columnModel);//nuooooooooooooooooo
			 
			 
			 //15-10 bottone inserisci 
			 Button btInserisciEntita = guigenFactory.createButton();
			 btInserisciEntita.setName(BT+INSERISCI+info.getNomeEntita());
			 btInserisciEntita.setLabel(INSERISCI);
			 
			 btInserisciEntita.setFunctionSpecifier(CommandFunctions.ADD_ITEM);
			 
			 cmdPanel.getWidgets().add(btInserisciEntita);
			 
			 EventHandler evHandleInsert= guigenFactory.createEventHandler();
			 evHandleInsert.setEventType(EventTypes.CLICKED);
			
			 btInserisciEntita.getEventHandlers().add(evHandleInsert);
			 
			 JumpCommand jCmdFromInsertToDett = guigenFactory.createJumpCommand();
			 evHandleInsert.setCommand(jCmdFromInsertToDett);
			 ContentPanel cpDettaglio = creaCPDettaglioByAppModule(info,cp);
			 jCmdFromInsertToDett.setJumpTo(cpDettaglio);
			 
			 ///15-10- fine bottone inserisci
			 
			 
			 //15-10 creo il command del form tab entità in cui inserisco due bottoni: dettaglio ed elimina
			 CommandPanel cmdPanelTabEntita = guigenFactory.createCommandPanel();
			 cmdPanelTabEntita.setName("cmdTab"+info.getNomeEntita());
			 
			 HorizontalFlowPanelLayout hfplTabEntita = guigenFactory.createHorizontalFlowPanelLayout();
			 cmdPanelTabEntita.setLayout(hfplTabEntita);
			 
			 fpMultiPanelTab.getSubpanels().add(cmdPanelTabEntita);
			 //bottone elimina
			 Button btEliminaEntita = guigenFactory.createButton();
			 btEliminaEntita.setName("bt"+ELIMINA+info.getNomeEntita());
			 btEliminaEntita.setLabel(ELIMINA);
			 
			 btEliminaEntita.setFunctionSpecifier(CommandFunctions.DELETE_ITEM);
			 
			 EventHandler evHandlerElimina = guigenFactory.createEventHandler();
			 evHandlerElimina.setEventType(EventTypes.CLICKED);
			 btEliminaEntita.getEventHandlers().add(evHandlerElimina);
			 
			 ExecCommand execCommandElimina = guigenFactory.createExecCommand();
			 execCommandElimina.setMethodName(lowerFirstLetter(ELIMINA)+info.getNomeEntita());
			 
			 CommandOutcome cmdOutOkElimina = guigenFactory.createCommandOutcome();
			 cmdOutOkElimina.setResultCode(RESULT_CODE);
			 //nuovo 
			 execCommandElimina.getResults().add(cmdOutOkElimina);
			 evHandlerElimina.setCommand(execCommandElimina);
			
			 RefreshViewCommand refreshVCmdOkElimina = guigenFactory.createRefreshViewCommand();
			 cmdOutOkElimina.setCommand(refreshVCmdOkElimina);
			 refreshVCmdOkElimina.getTargetWidgets().add(btEliminaEntita);
			 refreshVCmdOkElimina.getTargetPanels().add(fpMultiPanelTab);
			 
			 CommandOutcome cmdOutKoElimina = guigenFactory.createCommandOutcome();
			 cmdOutKoElimina.setResultCode(RESULT_CODE_KO);
			 execCommandElimina.getResults().add(cmdOutKoElimina);
			 
			 RefreshViewCommand refreshVCmdKoElimina = guigenFactory.createRefreshViewCommand();
			 cmdOutKoElimina.setCommand(refreshVCmdKoElimina);
			 refreshVCmdKoElimina.getTargetWidgets().add(btEliminaEntita);
			 refreshVCmdKoElimina.getTargetPanels().add(fpMultiPanelTab);
			 
			 
			 cmdPanelTabEntita.getWidgets().add(btEliminaEntita);
			 
			//start 13-10-2012 bottone dettaglio
			 Button btDettEntita = guigenFactory.createButton();
			 btDettEntita.setName(BT+DETTAGLIO+info.getNomeEntita());
			 btDettEntita.setLabel(upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
			 
			 btDettEntita.setFunctionSpecifier(CommandFunctions.DETAIL);
			 
			 cmdPanelTabEntita.getWidgets().add(btDettEntita);
			 
			 EventHandler evHandlerDett = guigenFactory.createEventHandler();
			 evHandlerDett.setEventType(EventTypes.CLICKED);
			
			 btDettEntita.getEventHandlers().add(evHandlerDett);
			 
			 ExecCommand execCommandDett = guigenFactory.createExecCommand();
			 execCommandDett.setMethodName("vaiDett"+info.getNomeEntita());
			 
			 CommandOutcome cmdOutShowDetail = guigenFactory.createCommandOutcome();
			 cmdOutShowDetail.setResultCode(SHOW_DETAIL);
			 
			 evHandlerDett.setCommand(execCommandDett);
			 execCommandDett.getResults().add(cmdOutShowDetail);
			 
			 CommandOutcome cmdOutNoItemSelected = guigenFactory.createCommandOutcome();
			 cmdOutNoItemSelected.setResultCode(NO_ITEM_SELECTED);
			 
			 execCommandDett.getResults().add(cmdOutNoItemSelected);
			 
			 JumpCommand jCmdFromTabToDett = guigenFactory.createJumpCommand();
			 cmdOutShowDetail.setCommand(jCmdFromTabToDett);
			
			 jCmdFromTabToDett.setJumpTo(cpDettaglio);
			 
			 RefreshViewCommand refreshVCDett = guigenFactory.createRefreshViewCommand();
			 cmdOutNoItemSelected.setCommand(refreshVCDett);
			 refreshVCDett.getTargetWidgets().add(btDettEntita);
			 refreshVCDett.getTargetPanels().add(fpMultiPanelTab);
			 // bottone dettaglio end 13-10
			 
			 //16-10 start parte cp CommandEnter
			 CPCommands cpCmds = guigenFactory.createCPCommands();
			 CPCommand cpCmdEnter = guigenFactory.createCPCommand();
			 cpCmdEnter.setExecute(CPCommandExecutionTypes.ON_ENTER);
			 cpCmds.getCommands().add(cpCmdEnter);
			 
			 cp.setCpCommands(cpCmds);//aggancio al content panel di ricerca
			 
			 SequenceCommand seqCmdCPEnter = guigenFactory.createSequenceCommand();
			 
			 cpCmdEnter.setCommand(seqCmdCPEnter);
			 
			 ClearAppdataCommand clearAppdata = guigenFactory.createClearAppdataCommand();
			 clearAppdata.getAppData().add(appDataFiltro);
			 clearAppdata.getAppData().add(appDataPK);
			 clearAppdata.getAppData().add(appDataElencoEntita);
			 
		 seqCmdCPEnter.getCommands().add(clearAppdata);
			 //activeMPanelItemCmd.setActiveItem(null);
		 ActivateMultiPanelItemCommand activeMPanelItemCmdcopy = guigenFactory.createActivateMultiPanelItemCommand();
		 activeMPanelItemCmdcopy.setActiveItem(null);
		 activeMPanelItemCmdcopy.setMultipanel(multiPanel);
			 seqCmdCPEnter.getCommands().add(activeMPanelItemCmdcopy);
			 
			 //16-10- fine parte cp CommandEnter
			
			//inserisco il content panel di ricerca nell'appModule
			 appModule.getContentPanels().add(cp);
			 
			 appModule.getContentPanels().add(cpDettaglio);
			 
			 resourceAppMod.save(options);
			 modPrincResource.save(options);
			
			
		}
		
		public static ContentPanel creaCPDettaglioByAppModule( WizardInfo info, ContentPanel cpRicerca) throws IOException {
				ContentPanel cp = guigenFactory.createContentPanel();
			 
			 FormPanel fp = guigenFactory.createFormPanel();
			 
			 cp.setName("cp"+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
			 //aggiungere app data al cp
			 cp.getAppData().add(appDataDett);
			
			 
			 fp.setName("fp"+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
			 fp.setLabel(upperFirstLetter(DETTAGLIO)+BLANK+info.getNomeEntita());
			 
			 UDLRCPanelLayout udlrPL = guigenFactory.createUDLRCPanelLayout();
			 fp.setLayout(udlrPL);
			 
			 FormPanel fpUp = guigenFactory.createFormPanel();
			 fpUp.setName("fpUp");
			 //nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
			 fpUp.setLayout(vfpUp);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgUp.setValue(UDLRCSpecConstants.UP);
			 fpUp.setLayoutSpec(udlrcWdgUp);
			
			 FormPanel fpCenter = guigenFactory.createFormPanel();
			 fpCenter.setName("fpCenter");
			//nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpCenter = guigenFactory.createVerticalFlowPanelLayout();
			 fpCenter.setLayout(vfpCenter);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgCenter = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgCenter.setValue(UDLRCSpecConstants.CENTER);
			 fpCenter.setLayoutSpec(udlrcWdgCenter);
			 
			 //16-10-2012
			 StdMessagePanel stdMsgPanelDett = guigenFactory.createStdMessagePanel();
			 stdMsgPanelDett.setName(STANDARD_MSG+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
			 stdMsgPanelDett.setShowFieldErrorDetails(true);
			 stdMsgPanelDett.setShowFieldErrors(true);
			 stdMsgPanelDett.setShowGlobalMessages(true);
			 fpCenter.getSubpanels().add(stdMsgPanelDett);
			 
			 WidgetsPanel wdgPanel = guigenFactory.createWidgetsPanel();
			 wdgPanel.setName("wp"+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
			 
			 VerticalFlowPanelLayout vfplWdg = guigenFactory.createVerticalFlowPanelLayout();
			 wdgPanel.setLayout(vfplWdg);
			 
			 fpCenter.getSubpanels().add(wdgPanel);
			 
			 
			 //ciclo sugli attributi presenti nella lista della tabella per ricavare il tipo  
			 //e costruisco campi di tipo textField o calendar
			 List listaAttrEntita = info.getListaAttrEntita();
			
			 String[] campi = (String[])listaAttrEntita.toArray(new String[listaAttrEntita.size()]);
			 
			 creaWidgetSemplici(campi, appDataDett, info, wdgPanel); //16-10
			 //15-10
			 CommandPanel cmdPanelNuovaRicerca = guigenFactory.createCommandPanel();
			 cmdPanelNuovaRicerca.setName("cmdNuovaRicerca"+info.getNomeEntita());
			 
			 HorizontalFlowPanelLayout hfpl = guigenFactory.createHorizontalFlowPanelLayout();
			 cmdPanelNuovaRicerca.setLayout(hfpl);
			 
			 fpCenter.getSubpanels().add(cmdPanelNuovaRicerca);
			 
			 Button btNuovaRicerca = guigenFactory.createButton();
			 btNuovaRicerca.setName(BT+upperFirstLetter(NUOVA)+RICERCA);
			 btNuovaRicerca.setLabel(upperFirstLetter(NUOVA)+BLANK+RICERCA);
			 
			 btNuovaRicerca.setFunctionSpecifier(CommandFunctions.SEARCH);
			 
			 cmdPanelNuovaRicerca.getWidgets().add(btNuovaRicerca);
			 
			 EventHandler evHandlerNuovaRicerca = guigenFactory.createEventHandler();
			 evHandlerNuovaRicerca.setEventType(EventTypes.CLICKED);
			
			 btNuovaRicerca.getEventHandlers().add(evHandlerNuovaRicerca);
			 
			 Button btSalva = guigenFactory.createButton();
			 btSalva.setName(BT+SALVA);
			 btSalva.setLabel(SALVA);
			 
			 btSalva.setFunctionSpecifier(CommandFunctions.SAVE);
			 
			 EventHandler evHandlerSalva = guigenFactory.createEventHandler();
			 evHandlerSalva.setEventType(EventTypes.CLICKED);
			 btSalva.getEventHandlers().add(evHandlerSalva);
//			 NOPCommand nopCmdSalva = guigenFactory.createNOPCommand();
//			 evHandlerSalva.setCommand(nopCmdSalva);
			 ExecCommand execCommandSalva = guigenFactory.createExecCommand();
			 execCommandSalva.setMethodName(lowerFirstLetter(SALVA)+info.getNomeEntita());
			 
			 CommandOutcome cmdOutOkSalva = guigenFactory.createCommandOutcome();
			 cmdOutOkSalva.setResultCode(RESULT_CODE);
			 
			 execCommandSalva.getResults().add(cmdOutOkSalva);
			 evHandlerSalva.setCommand(execCommandSalva);
			 
			 RefreshViewCommand refreshVCmdOkSalva = guigenFactory.createRefreshViewCommand();
			 cmdOutOkSalva.setCommand(refreshVCmdOkSalva);
			 refreshVCmdOkSalva.getTargetWidgets().add(btSalva);
			 refreshVCmdOkSalva.getTargetPanels().add(fpCenter);
			 
			 CommandOutcome cmdOutKoSalva = guigenFactory.createCommandOutcome();
			 cmdOutKoSalva.setResultCode(RESULT_CODE_KO);
			 execCommandSalva.getResults().add(cmdOutKoSalva);
			 
			 RefreshViewCommand refreshVCmdKoSalva = guigenFactory.createRefreshViewCommand();
			 cmdOutKoSalva.setCommand(refreshVCmdKoSalva);
			 refreshVCmdKoSalva.getTargetWidgets().add(btSalva);
			 refreshVCmdKoSalva.getTargetPanels().add(fpCenter);
			 
			 
			 
			 
			 cmdPanelNuovaRicerca.getWidgets().add(btSalva);
			 
			 
			 JumpCommand jCmdFromDettToRicerca = guigenFactory.createJumpCommand();
			 evHandlerNuovaRicerca.setCommand(jCmdFromDettToRicerca);
			 
			// ContentPanel cpRicerca = findContentPanelByName("cp"+RICERCA+info.getNomeEntita());
			 jCmdFromDettToRicerca.setJumpTo(cpRicerca);
			 
			
			 //end 15-10
			 
			 
			 
			 FormPanel fpLeft = guigenFactory.createFormPanel();
			 fpLeft.setName("fpLeft");
			//nuova perchè segnalato errore manca il flow vertical
			 VerticalFlowPanelLayout vfpLeft = guigenFactory.createVerticalFlowPanelLayout();
			 fpLeft.setLayout(vfpLeft);
			 ///////////////////////////////////////////
			 UDLRCWidgetLayoutSpec udlrcWdgLeft = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgLeft.setValue(UDLRCSpecConstants.LEFT);
			 fpLeft.setLayoutSpec(udlrcWdgLeft);
			 //inserisco il form panel nel content panel 
			 cp.setPanels(fp);
			 //inserisco i tre figli form panel nel form panel padre
			fp.getSubpanels().add(fpUp);
			fp.getSubpanels().add(fpCenter);
			fp.getSubpanels().add(fpLeft);
			
			return cp;
			
		}



public static  Type getTipoArrayByName(String nomeEntita)  {
	
	Typedefs tipi = guiModel.getTypedefs();//getcontainer cast namespace vai su e poi cerchi suo fratello
	Type res = null;
	
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	
	for (TypeNamespace typeNamespace : listaNamespace) {
	  	listaTipi = typeNamespace.getTypes();
	  	for(Type tipo : listaTipi) {
	  		if(tipo instanceof TypedArray) {
	  			if(((TypedArray) tipo).getComponentType().getName().equals(nomeEntita)){
	  				
	  				res = tipo;
	  			}
	  		}
	  	}
	}
	return res;
}

public static  Type getTipoArrayProva(String nomeEntita, WizardInfo info)  {
	
	Typedefs tipi = guiModel.getTypedefs();//getcontainer cast namespace vai su e poi cerchi suo fratello
	Type res = null;
	
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	//recupero il tipo dell'entita
	//è di tipo complesso recupero il padre che mi dà il namespace da cui recupero il nome del TNS
	Type tipoEntita = getTipoByNameEntita(nomeEntita, info);
	String tnsSource = null;
	if(tipoEntita instanceof ComplexType) {
			TypeNamespace padre = (TypeNamespace)tipoEntita.eContainer();
			tnsSource = padre.getName();
	}
	for (TypeNamespace namespace : listaNamespace) {
		//se il namespace che scorro (for) ha quel nome
		if(namespace.getName().equals(tnsSource)) {
			//lì farò la mia ricerca: recupero lista tipi e cercherò quello con component type =nome entità.
			listaTipi = namespace.getTypes();
		  	for(Type tipo : listaTipi) {
		  		if(tipo instanceof TypedArray && ((TypedArray) tipo).getComponentType().getName().equals(nomeEntita)) {
		  			res = tipo;
		  		}
		  	}
	  	}
	}
	return res;
}

static private List<Integer> findIndex(String word) {
	
	List<Integer> idxList = new ArrayList();
	
	Pattern p = Pattern.compile("\\p{Lu}");
	Matcher m = p.matcher(word);
	
	while(m.find()){
		idxList.add(m.start());
	}
	
	return idxList;
}

public static  String splitCamelCase(String wordCamelCase)  {
	
	StringBuffer res = new StringBuffer();
	int size = 0;
	
	if(wordCamelCase != null && !wordCamelCase.equals("") ) {
			
		List<Integer> listaIndex = findIndex(wordCamelCase);
		size = listaIndex.size();
		
		if(size == 0 ) {
			return res.append(wordCamelCase.substring(0,1).toUpperCase()+wordCamelCase.substring(1,wordCamelCase.length())+ " ").toString();
		}
		if(size == 1 && listaIndex.get(0)== 0) {
			return res.append(wordCamelCase.substring(0,1).toUpperCase()+wordCamelCase.substring(1,wordCamelCase.length())+ " ").toString();
		}
		int i = 0;
		res.append(wordCamelCase.substring(0,1).toUpperCase()+wordCamelCase.substring(1,listaIndex.get(i))+ " ");
		while (i<size-1) {
			res.append(wordCamelCase.substring(listaIndex.get(i),listaIndex.get(i+1))+ " ");
			i++;
		}
		res.append(wordCamelCase.substring(listaIndex.get(i),wordCamelCase.length()));
		}
	
	return res.toString();
}

public static  String upperFirstLetter(String word)  {
	
	StringBuffer res = new StringBuffer();
	int size = 0;
	if(word != null && !word.equals("") ) {
		size = word.length();
		if(size == 1) {
			return res.append(word.substring(0,1).toUpperCase()).toString();
		}
			res.append(word.substring(0,1).toUpperCase()+word.substring(1, word.length()));
		}
	
	return res.toString();
}

public static  String lowerFirstLetter(String word)  {
	
	StringBuffer res = new StringBuffer();
	int size = 0;
	if(word != null && !word.equals("") ) {
		size = word.length();
		if(size == 1) {
			return res.append(word.substring(0,1).toLowerCase()).toString();
		}
			res.append(word.substring(0,1).toLowerCase()+word.substring(1, word.length()));
		}
	
	return res.toString();
}

public static  ContentPanel findContentPanelByName(String namePanel)  {
	ContentPanel res = null;
	EList< ContentPanel> listaCP = guiModel.getStructure().getAppWindow().getAppArea().getContentPanels();
	for (ContentPanel cp : listaCP) {
		if(cp.getName().equals(namePanel)) {
			res = cp;
		}	
	}
	return res;
}

// costruisce campi di tipo textField o calendar o boolean data la lista delgi attributi semplici e l'appdata 
public static  void creaWidgetSemplici( String[] campiFiltri, ApplicationData appData, WizardInfo info,  WidgetsPanel wdgPanel) throws IOException {
	// String[] campiFiltri = getListaFiltri();//costituita da tipi semplici
	 SimpleType tipoAttr = null;
	 TextField textField = null;
	 Calendar calendar = null;
	 AppDataBinding appDataBinding = null;
	 for (int i = 0; i < campiFiltri.length; i++) {
		 
		tipoAttr = (SimpleType)getTypeByAttrName(campiFiltri[i], info);
		SimpleTypeCodes stc = tipoAttr.getCode();
		
		appDataBinding = guigenFactory.createAppDataBinding();
		appDataBinding.setPath(campiFiltri[i]);
		//appDataBinding.setAppData(appDataFiltro);
		appDataBinding.setAppData(appData);
		
		int stcVal = stc.getValue();
		switch(stcVal) {
		
			case SimpleTypeCodes.STRING_VALUE: 
			case SimpleTypeCodes.INT_VALUE: {
		  
				textField = guigenFactory.createTextField();
				
				textField.setName(TEXT_FIELD+upperFirstLetter(campiFiltri[i]));
				textField.setDataType(tipoAttr);
				textField.setLabel(splitCamelCase(campiFiltri[i]));
				
				textField.setDatabinding(appDataBinding);
				
				wdgPanel.getWidgets().add(textField);
				break;
				}
			case SimpleTypeCodes.BOOLEAN_VALUE: {
				CheckBox checkBox = guigenFactory.createCheckBox();
				
				checkBox.setName(CHECK_BOX+upperFirstLetter(campiFiltri[i]));
				checkBox.setDataType(tipoAttr);
				checkBox.setLabel(splitCamelCase(campiFiltri[i]));
				
				checkBox.setDatabinding(appDataBinding);
				
				wdgPanel.getWidgets().add(checkBox);
				break;
			}
			case SimpleTypeCodes.DATE_VALUE: {
			
				calendar = guigenFactory.createCalendar();
				
				calendar.setName(CALENDAR+upperFirstLetter(campiFiltri[i]));
				calendar.setDataType(tipoAttr);
				calendar.setLabel(splitCamelCase(campiFiltri[i]));
				
				calendar.setDatabinding(appDataBinding);
		
				wdgPanel.getWidgets().add(calendar);
				break;
			}
		}
	 }
}
	 



//public static void cancellaRisorse() throws IOException {
//	
//	Map<Object, Object> options = new HashMap<Object, Object>();
//	
//	resourceAppMod.delete(options);
//	risorsaAppdDataGroup.delete(options);
//	
//}


		
		

	

		

}
