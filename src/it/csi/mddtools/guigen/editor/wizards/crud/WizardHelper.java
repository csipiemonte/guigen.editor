package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.ActivateMultiPanelItemCommand;
import it.csi.mddtools.guigen.Annotation;
import it.csi.mddtools.guigen.AnnotationDetail;
import it.csi.mddtools.guigen.AppDataBinding;
import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.AppModule;
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
import it.csi.mddtools.guigen.ComboBox;
import it.csi.mddtools.guigen.CommandFunctions;
import it.csi.mddtools.guigen.CommandOutcome;
import it.csi.mddtools.guigen.CommandPanel;
import it.csi.mddtools.guigen.ComplexType;
import it.csi.mddtools.guigen.ContentPanel;
import it.csi.mddtools.guigen.DataLifetimeType;
import it.csi.mddtools.guigen.DataWidget;
import it.csi.mddtools.guigen.EventHandler;
import it.csi.mddtools.guigen.EventTypes;
import it.csi.mddtools.guigen.ExecCommand;
import it.csi.mddtools.guigen.Field;
import it.csi.mddtools.guigen.FormPanel;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.HorizontalFlowPanelLayout;
import it.csi.mddtools.guigen.InlineCodeSnippet;
import it.csi.mddtools.guigen.JumpCommand;
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
import it.csi.mddtools.guigen.Widget;
import it.csi.mddtools.guigen.WidgetsPanel;
import it.csi.mddtools.guigen.impl.AppDataBindingImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.internal.resources.File;
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
	private static final String CP = "cp";
	private static final String FP = "fp";
	private static final String CENTER = "Center";
	private static final String UP = "Up";
	private static final String ELENCO = "elenco";
	private static final String LISTA = "lista";
	private static final String FILTRO = "filtro";
	private static final String FLT = "flt";
	private static final String ID = "id";
	private static final String SELEZIONATO = "Selezionato";
	private static final String DETTAGLIO = "dettaglio";
	private static final String DTL = "dtl";
	private static final String ITEMS = "Items";
	private static final String SELECTED_KEYS = "SelectedKeys";
	private static final String LOAD = "load";
	private static final String ELEMENTS = "Elements";
	private static final String DETT = "Dett";
	private static final String DETT_M = "dett";
	private static final String PARENTESI_QUADRE = "[]";
	private static final String RICERCA = "Ricerca";
	private static final String FIX = "fix";
	private static final String VIEW = "view";
	private static final String NUOVA = "nuova";
	private static final String BLANK = " ";
	private static final String RESULT_CODE = "OK";
	private static final String RESULT_CODE_KO = "KO";
	private static final String OK = "OK";
	private static final String KO = "KO";
	private static final String FIXVIEW = "FIXVIEW";
	private static final String MULTI_PANEL = "mp";
	private static final String TAB = "tab";
	private static final String FORM_PANEL = "fp";
	private static final String WP = "wp";
	private static final String BT = "bt";
	private static final String CMD = "cmd";
	private static final String TEXT_FIELD = "tf";
	private static final String CALENDAR = "cal";
	private static final String ELIMINA = "Elimina";
	private static final String INSERISCI = "Inserisci";
	private static final String SALVA = "Salva";
	private static final String SHOW_DETAIL = "SHOW_DETAIL";
	private static final String NO_ITEM_SELECTED ="NO_ITEM_SELECTED";
	private static final String VIEW_DETAIL = "VIEW_DETAIL";
	private static final String STANDARD_MSG = "stdMsg";
	private static final String CHECK_BOX = "chk";
	private static final String COMBO_BOX = "cmbx";
	private static final String GUIGEN = "guigen";
	private static final String AGGREGATED = "aggregated";
	private static final String DECODED = "decoded";
	private static final String SINGLE_FIELD_ID = "single-field-id"; 
	private static final String SHORT_DESCRIPTION = "short-description";
	private static final String RELATION_TYPE = "relation-type";
	private static final String RELATION_MIN_CARDINALITY = "relation-min-cardinality";
	private static final String RELATION_MAX_CARDINALITY = "relation-max-cardinality";
	private static final String UNO = "1";
	private static final String PUNTO = ".";
	private static final String CRUD_OPERATION = "CrudOperation";
	private static final String COMMON = "common";
	private static final String STRING = "String";
	private static ApplicationData appDataFiltro = null;
	private static AppModule appModule = null;
	private static AppDataGroup appDataGroup = null; 
	private static CPCommand cpCmdEnter = null; 
	
	private static CPCommand cpCmdEnterDett = null;
	
	public static ArrayList<ExecCommand> listaRicercaExecCmdFix = new ArrayList<ExecCommand>();
	
	public static ArrayList<ExecCommand> listaDettExecCmdFix = new ArrayList<ExecCommand>();
	
	public static CPCommand getCpCmdEnter() {
		return cpCmdEnter;
	}

	public static void setCpCmdEnter(CPCommand cpCmdEnter) {
		WizardHelper.cpCmdEnter = cpCmdEnter;
	}
	
	public static CPCommand getCpCmdEnterDett() {
		return cpCmdEnterDett;
	}

	public static void setCpCmdEnterDett(CPCommand cpCmdEnterDett) {
		WizardHelper.cpCmdEnterDett = cpCmdEnterDett;
	}

	public static AppDataGroup getAppDataGroup() {
		return appDataGroup;
	}

	public static void setAppDataGroup(AppDataGroup appDataGroup) {
		WizardHelper.appDataGroup = appDataGroup;
	}

	private  MultiPanel multiPanelRicerca = null;
	
	public  MultiPanel getMultiPanelRicerca() {
		return multiPanelRicerca;
	}
	
	public  void setMultiPanelRicerca(MultiPanel multiPanelRicerca) {
		this.multiPanelRicerca = multiPanelRicerca;
	}

	private static ContentPanel cpDettaglio = null;
	
	public static ContentPanel getCpDettaglio() {
		return cpDettaglio;
	}

	public static void setCpDettaglio(ContentPanel cpDettaglio) {
		WizardHelper.cpDettaglio = cpDettaglio;
	}

	
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
	
	private static ApplicationData appDataMethod= null;
	
	public static ApplicationData getAppDataMethod() {
		return appDataMethod;
	}

	public static void setAppDataMethod(ApplicationData appDataMethod) {
		WizardHelper.appDataMethod = appDataMethod;
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

	private static GUIModel guiModel;
	
	
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

public static EList<TypeNamespace>  getAllNamespace(GUIModel guiModel) throws IOException {
	
	Typedefs tipi = guiModel.getTypedefs();
	
	EList<TypeNamespace> listaNameSpace = tipi.getExtNamespaces();
	
		return listaNameSpace;
	}

	public static List<String>  getNameComplexTypeByNamespace(EList<TypeNamespace> listaNamespace) throws IOException {
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
	int dim = lista.size();
	String[] items= new String[dim];
	for (int i = 0; i < lista.size(); i++) {
		items[i] = (String)lista.get(i);
	}
	return items;
}
		
public static String[] getNameComplexType(GUIModel guiModel, WizardInfo info) {
	List<String> lista = WizardHelper.getNameComplexTypeByModPrincFull(guiModel, info); 
	return WizardHelper.getVettoreByList(lista);
}

public static Type getTipoByNameEntita(String nomeEntita, WizardInfo info) {
	Type tipoEntita = (Type)info.getMapNomeTipo().get(nomeEntita);
	return tipoEntita;
}
	
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

public static  void popolaListaAttrSempliciAggregati(String nomeEntita, WizardInfo info) {
	ComplexType tipoEntita = (ComplexType)info.getMapNomeTipo().get(nomeEntita);
	EList<Field> listaAttributi  = tipoEntita.getAllFields() ;
	List<String> listaAttrEntita = info.getListaAttrEntita();
	Map <String, FieldContext> mapListaAttrEntita = info.getMapListaAttrEntitaContext();
	Type tipoAttr = null;
	FieldContext fieldContext = null;
		 for(Field attr :listaAttributi) {
			 tipoAttr = attr.getType();
			 if (tipoAttr instanceof SimpleType) {
				listaAttrEntita.add(attr.getName());
				fieldContext = new FieldContext();
				fieldContext.setPath(attr.getName());
				fieldContext.setField(attr);
				mapListaAttrEntita.put(attr.getName(), fieldContext);	 
			 } else if(tipoAttr instanceof ComplexType && isDecodedField(attr)) {     
				 listaAttrEntita.add(attr.getName());
				 fieldContext = new FieldContext();
				 fieldContext.setPath(attr.getName());
				 fieldContext.setField(attr);
				 mapListaAttrEntita.put(attr.getName(), fieldContext);	
			 }
			 else if(tipoAttr instanceof TypedArray && isDecodedField(attr)) {     
				 listaAttrEntita.add(attr.getName());
				 fieldContext = new FieldContext();
				 fieldContext.setPath(attr.getName());
				 fieldContext.setField(attr);
				 mapListaAttrEntita.put(attr.getName(), fieldContext);	
			 }
		 }
		 List<Field> aggrSemplici = getSubsectionFields(tipoEntita);
		 Iterator<Field> it = aggrSemplici.iterator();
		 while(it.hasNext()) {
		   Field field = it.next();
		   popolaListaAttrSempliciAggregatiSubFolderMod(field, info, field.getName());
		 }
				   	
}
public static  void popolaListaAttrSempliciAggregatiSubFolderMod(Field field, WizardInfo info, String prefisso) {
	Field nField = null; 
	FieldContext fieldContext = null; 
	ComplexType ct= (ComplexType)field.getType();
		List<Field> ll= ct.getAllFields();
		Iterator<Field> it = ll.iterator();
		String prefissoNomeCampo= "";
		 while(it.hasNext()) {
		   Field f= it.next();
		   if(f.getType() instanceof SimpleType) {
			   fieldContext = new FieldContext();
			   fieldContext.setPath(prefisso+PUNTO+f.getName());
			   fieldContext.setField(f);
			   info.getListaAttrEntita().add(prefisso+PUNTO+f.getName());
			   info.getMapListaAttrEntitaContext().put(prefisso+PUNTO+f.getName(), fieldContext);
		   }
		   else if(f.getType() instanceof ComplexType && isAggregatedSimple(f)) {
			  prefissoNomeCampo = prefisso +PUNTO+f.getName(); 
			  popolaListaAttrSempliciAggregatiSubFolderMod(f, info, prefissoNomeCampo );
		   }
		   else if(f.getType() instanceof TypedArray ) {
				  prefissoNomeCampo = prefisso +PUNTO+f.getName(); 
				  popolaListaAttrSempliciAggregatiSubFolderMod(f, info, prefissoNomeCampo );//probabile sarà da togliere per gestire livelli >1
			}
		 }

}

// aggregato semplice: cardinalita min e max = 1
public  static boolean isAggregatedSimple(Field attr) {
	boolean res = false;	
	EList<Annotation> listaAnnotazioni = null;
	String source = null;
	EList<AnnotationDetail> listaAnnotazDett = null;
	String relationType = null;
	String relationMinCard = null;
	String relationMaxCard = null;
	
				 listaAnnotazioni = attr.getAnnotations();
				 if(listaAnnotazioni != null) {
					 for(Annotation annotazione :listaAnnotazioni) {
						  source = annotazione.getSource();
						  if(source.equals(GUIGEN)) {
							  listaAnnotazDett = annotazione.getDetails();
							  relationType = getValueBykeyByListaAnnotationDetail(RELATION_TYPE, listaAnnotazDett);
							  if(relationType != null && relationType.equals(AGGREGATED)) {//se la relazione e ti tipo aggregated con card=1
								  relationMinCard = getValueBykeyByListaAnnotationDetail(RELATION_MIN_CARDINALITY, listaAnnotazDett);
								  relationMaxCard = getValueBykeyByListaAnnotationDetail(RELATION_MAX_CARDINALITY, listaAnnotazDett);
								  if(relationMinCard != null && relationMaxCard != null &&
									!relationMinCard.isEmpty()  && !relationMaxCard.isEmpty() &&
									 relationMinCard.equals(UNO) && relationMaxCard.equals(UNO)) {
									  res = true;
								  }
								 }
							  }
						  }
					 }
	return res;
}

//field di codifica (es. Categoria)
public  static boolean isDecodedField(Field attr) {
	boolean res = false;	

	EList<Annotation> listaAnnotazioni = null;
	String source = null;
	EList<AnnotationDetail> listaAnnotazDett = null;
	String relationType = null;
				 listaAnnotazioni = attr.getAnnotations();
				 if(listaAnnotazioni != null) {
					 for(Annotation annotazione :listaAnnotazioni) {
						  source = annotazione.getSource();
						  if(source.equals(GUIGEN)) {
							  listaAnnotazDett = annotazione.getDetails();
							  relationType = getValueBykeyByListaAnnotationDetail(RELATION_TYPE, listaAnnotazDett);
							  if(relationType != null && relationType.equals(DECODED)) {//se la relazione è di tipo decoded
								    res = true;
								 }
							  }
						  }
					 }
	return res;
}

public  static Field getCodiceField(ComplexType tipo) {
	Field result = null;	
	List<Field> listaAttributi= tipo.getAllFields();
	Iterator<Field> it = listaAttributi.iterator();
	while(it.hasNext()) {
	   Field f= it.next();
	   if(f.getType() instanceof SimpleType && isSingleField(f) ) {
		  result = f;
		  break;
	   }	
	 }
	 return result;
}
	   
   public  static boolean isShortDescription(Field attr) {
	  boolean res = false;	
	  EList<Annotation> listaAnnotazioni = null;
	  String source = null;
	  EList<AnnotationDetail> listaAnnotazDett = null;
	  String singleFieldId = null;
	
	  listaAnnotazioni = attr.getAnnotations();
	  if(listaAnnotazioni != null) {
		  for(Annotation annotazione :listaAnnotazioni) {
			  	source = annotazione.getSource();
			  	if(source.equals(GUIGEN)) {
			  		listaAnnotazDett = annotazione.getDetails();
			  		singleFieldId = getValueBykeyByListaAnnotationDetail(SHORT_DESCRIPTION , listaAnnotazDett);
			  		if(singleFieldId != null && singleFieldId.equalsIgnoreCase("true")) {
			  			res = true;
			  		}
			  	}
		  }
	  }
			return res;
   }
	  
   public  static Field getDescrizioneField(ComplexType tipo) {
		Field result = null;	
		
		List<Field> listaAttributi= tipo.getAllFields();
		Iterator<Field> it = listaAttributi.iterator();
		while(it.hasNext()) {
		   Field f= it.next();
		   if(f.getType() instanceof SimpleType && isShortDescription(f) ) {
			  result = f;
			  break;
		   }	
		 }
		 return result;
	}
   
   public  static boolean isSingleField(Field attr) {
	   boolean res = false;	
	   EList<Annotation> listaAnnotazioni = null;
	   String source = null;
	   EList<AnnotationDetail> listaAnnotazDett = null;
	   String singleFieldId = null;

	   listaAnnotazioni = attr.getAnnotations();
	   if(listaAnnotazioni != null) {
		   for(Annotation annotazione :listaAnnotazioni) {
			   source = annotazione.getSource();
			   if(source.equals(GUIGEN)) {
				   listaAnnotazDett = annotazione.getDetails();
				   singleFieldId = getValueBykeyByListaAnnotationDetail(SINGLE_FIELD_ID , listaAnnotazDett);
				   if(singleFieldId != null && singleFieldId.equalsIgnoreCase("true")) {
					   res = true;
				   }
			 	}
		   }
	   }
			return res;
   	}

public static FormPanel buildFpUI(ComplexType entity, List <FieldContext> listaFiltriSel, WizardInfo info, ApplicationData mainAppData, String prefisso) {
	FormPanel fp = null; 
	String prefissoNomeCampo = null;
	fp = guigenFactory.createFormPanel();
	List<Field> foglie = getLeavesNode(entity);
	
	WidgetsPanel level0WP = buildWpUI(foglie, listaFiltriSel,info,  mainAppData, prefisso);
	if(prefisso.equals("")) {
		level0WP.setName(WP+RICERCA+upperFirstLetter(entity.getName()));
	}
	else {
		level0WP.setName(WP+RICERCA+upperFirstLetter(prefisso.replace(".", "")));
	}
	if(!level0WP.getWidgets().isEmpty()){
		 fp.getSubpanels().add(level0WP);
	}
	List<Field> subsectionFields = getSubsectionFields(entity);
	Iterator<Field> itSubsections = subsectionFields.iterator();
	while (itSubsections.hasNext()) {
		Field field = itSubsections.next();
		prefissoNomeCampo = prefisso +field.getName()+PUNTO;
		FormPanel currSubsectionFP = buildFpUI((ComplexType)field.getType(), listaFiltriSel, info,mainAppData, prefissoNomeCampo);
		currSubsectionFP.setName(FP+RICERCA+upperFirstLetter(prefissoNomeCampo.replace(".", "")));
		currSubsectionFP.setLabel(splitCamelCase(prefissoNomeCampo.replace(".", "")));
		if(!currSubsectionFP.getSubpanels().isEmpty()) {
			VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
			currSubsectionFP.setLayout(vfpUp);
			fp.getSubpanels().add(currSubsectionFP);
		}
	}
	return fp;
}

public static WidgetsPanel buildWpUI (List<Field> fieldfoglia, List<FieldContext> listFiltriSelez, WizardInfo info, ApplicationData mainAppData, String prefisso){
	WidgetsPanel wp = null;

	wp = guigenFactory.createWidgetsPanel();
	VerticalFlowPanelLayout vfplWdg = guigenFactory.createVerticalFlowPanelLayout();
	wp.setLayout(vfplWdg);
	 
	Iterator<Field> itFields = fieldfoglia.iterator();
	while (itFields.hasNext()) {
		Field field = itFields.next();
	
		Widget currW = buildWidgetUIM(field, listFiltriSelez,info, mainAppData, prefisso);
		if(currW != null) {
			wp.getWidgets().add(currW);
		}
	}
	return wp;
}

public static WidgetsPanel buildWidgetCodifica(Field f,  WizardInfo info, ApplicationData mainAppData, String nomeFieldNodo) {
	SimpleType fieldType = null;
	WidgetsPanel wp = null;
	wp = guigenFactory.createWidgetsPanel();
	VerticalFlowPanelLayout vfplWdg = guigenFactory.createVerticalFlowPanelLayout();
	wp.setLayout(vfplWdg);
	 
	DataWidget w = null;
	AppDataBinding appDataBinding = null; 
	String bindingPath= "";
	String campoFiltroNoPunto = null;
	
	appDataBinding = guigenFactory.createAppDataBinding();
	appDataBinding.setAppData(mainAppData);
	bindingPath =  nomeFieldNodo+f.getName();
	Field codice = getCodiceField((ComplexType)f.getType());
	appDataBinding.setPath(codice.getName()); 
	//appDataBinding.setPath(bindingPath); 
	campoFiltroNoPunto = bindingPath.replace(".", "");
	
	ComboBox combokBox = guigenFactory.createComboBox();
	w = combokBox;
	w.setName(COMBO_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
	w.setDataType(fieldType);
	w.setLabel(splitCamelCase(campoFiltroNoPunto));
	w.setEnableEnrichment(true);
	w.setDatabinding(appDataBinding);
	wp.getWidgets().add(w);
	   
	return wp;
}

//per cpRicerca
public static Widget buildWidgetUIM(Field f, List<FieldContext> listFiltriSelez, WizardInfo info, ApplicationData mainAppData, String nomeFieldNodo) {
	SimpleType fieldType = null;
	int stcVal = 0;
	DataWidget w = null;
	AppDataBinding appDataBinding = null; 
	String bindingPath= "";
	String campoFiltroNoPunto = null;
	ExecCommand execCmdFix = null;
	String nomeEntita = info.getNomeEntita();
	CPCommand cpCmnd = getCpCmdEnter(); 
	
	FieldContext fieldContext =info.getMapListaAttrEntitaContext().get(nomeFieldNodo+f.getName());
	if(listFiltriSelez.contains(fieldContext) && f.getType() instanceof SimpleType) {
		 fieldType = (SimpleType)f.getType();
		 stcVal = fieldType.getCode().getValue();
		appDataBinding = guigenFactory.createAppDataBinding();
		appDataBinding.setAppData(mainAppData);
		bindingPath =  nomeFieldNodo+f.getName();
		appDataBinding.setPath(bindingPath); 
		campoFiltroNoPunto = bindingPath.replace(".", "");
		
		switch(stcVal) {
			case SimpleTypeCodes.STRING_VALUE: 
			case SimpleTypeCodes.INT_VALUE: {
				w = guigenFactory.createTextField(); 
				w.setName(TEXT_FIELD+RICERCA+upperFirstLetter(campoFiltroNoPunto));
				w.setDataType(fieldType);
				w.setLabel(splitCamelCase(campoFiltroNoPunto));
				w.setEnableEnrichment(true);
				w.setDatabinding(appDataBinding);
				break;
				}
			case SimpleTypeCodes.BOOLEAN_VALUE: {
				CheckBox checkBox = guigenFactory.createCheckBox();
				w = checkBox;
				checkBox.setName(CHECK_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
				checkBox.setDataType(fieldType);
				checkBox.setLabel(splitCamelCase(campoFiltroNoPunto));
				checkBox.setEnableEnrichment(true);
				checkBox.setDatabinding(appDataBinding);
				break;
			}
			case SimpleTypeCodes.DATE_VALUE: {
				Calendar calendar = guigenFactory.createCalendar();
				w = calendar;
				calendar.setName(CALENDAR+RICERCA+upperFirstLetter(campoFiltroNoPunto));
				calendar.setDataType(fieldType);
				calendar.setLabel(splitCamelCase(campoFiltroNoPunto));
				calendar.setEnableEnrichment(true);
				calendar.setDatabinding(appDataBinding);
				break;
			}
		}
	}
	else if (listFiltriSelez.contains(fieldContext) && f.getType() instanceof ComplexType){        
		//creo appdatabinding 
		appDataBinding = guigenFactory.createAppDataBinding();
		appDataBinding.setAppData(mainAppData);//imposto con appdatafiltro
		bindingPath =  nomeFieldNodo+f.getName();
		Field codice = getCodiceField((ComplexType)f.getType());
		appDataBinding.setPath(bindingPath+"."+codice.getName()); 
		
		campoFiltroNoPunto = bindingPath.replace(".", "");
		ComboBox combokBox = guigenFactory.createComboBox();
		w = combokBox;
		combokBox.setName(COMBO_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
		combokBox.setDataType(codice.getType());
		combokBox.setKeySelector(codice.getName());
		Field desc = getDescrizioneField((ComplexType)f.getType());
		combokBox.setValueSelector(desc.getName());
		combokBox.setLabel(splitCamelCase(campoFiltroNoPunto));
		combokBox.setEnableEnrichment(true);
		
		combokBox.setDatabinding(appDataBinding);
		//creo appdata per listaDecodifica ovvero un vettore di oggetti di decodifica
		ApplicationData adElencoDecodifica = guigenFactory.createApplicationData();
		adElencoDecodifica.setName(FLT+nomeEntita+upperFirstLetter(f.getName())+ITEMS);
		adElencoDecodifica.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		Type tipoElencoNomeEntita = getTipoArrayByNameRic(f);
		adElencoDecodifica.setType(tipoElencoNomeEntita);
		
		AppDataBinding  multiAppDataBinding = guigenFactory.createAppDataBinding();
		multiAppDataBinding.setAppData(adElencoDecodifica);
		((MultiDataWidget) combokBox).setMultiDataBinding(multiAppDataBinding);
		
		getAppDataGroup().getAppData().add(adElencoDecodifica);
	
		EList<ContentPanel> listaCP = appModule.getContentPanels();
		for (ContentPanel contentPanel : listaCP) {
			contentPanel.getAppData().add(adElencoDecodifica);
		}
		buildMethodLoadCombo(cpCmnd,
							 FLT,
							 nomeEntita,
							 adElencoDecodifica,
							 f.getName(),
							 upperFirstLetter(f.getName()));//tipo base
		execCmdFix = fixComboSingleElememts(info.getNomeEntita(),  
											 mainAppData,
											 adElencoDecodifica,
								             upperFirstLetter(f.getName()),
								             codice.getName());
		listaRicercaExecCmdFix.add(execCmdFix);
		try {
			Resource risorsa = getRisorsaAppdDataGroup();
			risorsa.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}   
	else if (listFiltriSelez.contains(fieldContext) && f.getType() instanceof TypedArray) {         
		//creo appdatabinding 
		appDataBinding = guigenFactory.createAppDataBinding();
		ApplicationData listaCodiciSel = guigenFactory.createApplicationData();
		listaCodiciSel.setName(FLT+nomeEntita+upperFirstLetter(f.getName())+SELECTED_KEYS);
		listaCodiciSel.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		Type tipoString = WizardHelper.getTipoArrayString(COMMON);
		
		listaCodiciSel.setType(tipoString);
		appDataBinding.setAppData(listaCodiciSel);//imposto con appdatafiltro
		//e path nomefieldnodo.codice
		bindingPath =  nomeFieldNodo+f.getName();
		campoFiltroNoPunto = bindingPath.replace(".", "");
		ComboBox combokBox = guigenFactory.createComboBox();
		w = combokBox;
		combokBox.setName(COMBO_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
		//w.setDataType(fieldType);
		ComplexType componentType = (ComplexType) ((TypedArray)f.getType()).getComponentType();
		Field codice = getCodiceField(componentType);
		combokBox.setDataType(codice.getType());
		combokBox.setKeySelector(codice.getName());
		Field desc = getDescrizioneField(componentType);
		combokBox.setValueSelector(desc.getName());
		combokBox.setLabel(splitCamelCase(campoFiltroNoPunto));
		combokBox.setEnableEnrichment(true);
		combokBox.setDatabinding(appDataBinding);
		//creo appdata per listaDecodifica ovvero un vettore di oggetti di decodifica
		ApplicationData adElencoDecodifica = guigenFactory.createApplicationData();
		adElencoDecodifica.setName(FLT+nomeEntita+upperFirstLetter(f.getName())+ITEMS);
		adElencoDecodifica.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		adElencoDecodifica.setType(f.getType());
		
		AppDataBinding  multiAppDataBinding = guigenFactory.createAppDataBinding();
		multiAppDataBinding.setAppData(adElencoDecodifica);
		((MultiDataWidget) combokBox).setMultiDataBinding(multiAppDataBinding);
		
		getAppDataGroup().getAppData().add(adElencoDecodifica);
		getAppDataGroup().getAppData().add(listaCodiciSel);
		
		EList<ContentPanel> listaCP = appModule.getContentPanels();
		for (ContentPanel contentPanel : listaCP) {
			contentPanel.getAppData().add(adElencoDecodifica);
			contentPanel.getAppData().add(listaCodiciSel);
		}
		buildMethodLoadCombo(cpCmnd,
							 FLT,
							 nomeEntita,
							 adElencoDecodifica,
							 f.getName(),
							 componentType.getName());
		execCmdFix = fixComboMultipleElememts( info.getNomeEntita(), 
												appDataFiltro,
											   adElencoDecodifica,
											   listaCodiciSel,
											   f.getName(),
											 componentType.getName(),
											codice.getName());
		listaRicercaExecCmdFix.add(execCmdFix);
		buildFixViewRicerca(cpCmnd,
					 nomeEntita, 
					 appDataFiltro, 
				 	listaCodiciSel,
				 	f.getName(),
				 	componentType.getName());
		try {
			Resource risorsa = getRisorsaAppdDataGroup();
			risorsa.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return w;
}
////////////////////////////////////////////
public static void buildMethodLoadCombo(CPCommand cpCmnd, String dltOFlt, String nomeEntita, ApplicationData mainAppData, String nameAttr, String tipoBase) {
	SequenceCommand seqCmd = (SequenceCommand)cpCmnd.getCommand();
	ExecCommand execCommandLoad = guigenFactory.createExecCommand();
	execCommandLoad.setMethodName(LOAD+upperFirstLetter(dltOFlt)+nomeEntita+upperFirstLetter(nameAttr)+ITEMS);
	
	CommandOutcome cmdOutOkLoad = guigenFactory.createCommandOutcome();
	CommandOutcome cmdOutKoLoad = guigenFactory.createCommandOutcome();
	cmdOutOkLoad.setResultCode(upperFirstLetter(LOAD)+upperFirstLetter(dltOFlt)+nomeEntita+upperFirstLetter(nameAttr)+ITEMS+"_"+OK);
	cmdOutKoLoad.setResultCode(upperFirstLetter(LOAD)+upperFirstLetter(dltOFlt)+nomeEntita+upperFirstLetter(nameAttr)+ITEMS+"_"+KO);
	execCommandLoad.getResults().add(cmdOutOkLoad);
	execCommandLoad.getResults().add(cmdOutKoLoad);
	NOPCommand nopCmdOK = guigenFactory.createNOPCommand();
	
	cmdOutOkLoad.setCommand(nopCmdOK);
	NOPCommand nopCmdKO = guigenFactory.createNOPCommand();
	cmdOutKoLoad.setCommand(nopCmdKO);
	seqCmd.getCommands().add(execCommandLoad);
	
	InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	inlineCS.setLang("JAVA");
	inlineCS.setSnippetName("body");
	inlineCS.setSnippetPosition("executedMethod");
	inlineCS.setSnippetCode(new CRUDSnippets().loadComboEntitySnippet(mainAppData, nameAttr, tipoBase).toString());
	
	execCommandLoad.getInlineCodeSnippets().add(inlineCS);
}

public static ExecCommand fixComboSingleElememts(String nomeEntita, ApplicationData mainAppData, ApplicationData adElenco, String tipoBase, String nomeCampoKey) {
	ExecCommand execCommandFix = guigenFactory.createExecCommand();
	execCommandFix.setMethodName(FIX+upperFirstLetter(mainAppData.getName())+ELEMENTS);
	
	CommandOutcome cmdOutOk = guigenFactory.createCommandOutcome();
	CommandOutcome cmdOutKo = guigenFactory.createCommandOutcome();
	cmdOutOk.setResultCode(mainAppData.getName().toUpperCase()+"_"+ELEMENTS.toUpperCase()+"_"+RESULT_CODE); 
	cmdOutKo.setResultCode(mainAppData.getName().toUpperCase()+"_"+ELEMENTS.toUpperCase()+"_"+RESULT_CODE_KO);
	execCommandFix.getResults().add(cmdOutOk);
	execCommandFix.getResults().add(cmdOutKo);
	NOPCommand nopCmdOK = guigenFactory.createNOPCommand();
	cmdOutOk.setCommand(nopCmdOK);
	NOPCommand nopCmdKO = guigenFactory.createNOPCommand();
	cmdOutKo.setCommand(nopCmdKO);
	
	InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	inlineCS.setLang("JAVA");
	inlineCS.setSnippetName("body");
	inlineCS.setSnippetPosition("executedMethod");
	inlineCS.setSnippetCode(new CRUDSnippets().fixComboSingleEntitySnippet(nomeEntita, mainAppData, adElenco, tipoBase, nomeCampoKey).toString());
	
	execCommandFix.getInlineCodeSnippets().add(inlineCS);
	return execCommandFix;
}

public static ExecCommand fixComboMultipleElememts(String nomeEntita, ApplicationData filtroDett, ApplicationData mainAppData, ApplicationData listaCodiciSel, String nameAttr, String tipoBase, String nomeCampoKey) {
	ExecCommand execCommandFix = guigenFactory.createExecCommand();
	execCommandFix.setMethodName(FIX+upperFirstLetter(mainAppData.getName())+ELEMENTS);
	
	CommandOutcome cmdOutOk = guigenFactory.createCommandOutcome();
	CommandOutcome cmdOutKo = guigenFactory.createCommandOutcome();
	cmdOutOk.setResultCode(mainAppData.getName().toUpperCase()+"_"+ELEMENTS.toUpperCase()+"_"+RESULT_CODE);
	cmdOutKo.setResultCode(mainAppData.getName().toUpperCase()+"_"+ELEMENTS.toUpperCase()+"_"+RESULT_CODE_KO);
	execCommandFix.getResults().add(cmdOutOk);
	execCommandFix.getResults().add(cmdOutKo);
	NOPCommand nopCmdOK = guigenFactory.createNOPCommand();
	cmdOutOk.setCommand(nopCmdOK);
	NOPCommand nopCmdKO = guigenFactory.createNOPCommand();
	cmdOutKo.setCommand(nopCmdKO);
	
	InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	inlineCS.setLang("JAVA");
	inlineCS.setSnippetName("body");
	inlineCS.setSnippetPosition("executedMethod");
	inlineCS.setSnippetCode(new CRUDSnippets().fixComboMultipleEntitySnippet(nomeEntita, filtroDett, mainAppData, listaCodiciSel, nameAttr, tipoBase, nomeCampoKey).toString());
	
	execCommandFix.getInlineCodeSnippets().add(inlineCS);
	return execCommandFix;
}

public static void buildFixView(CPCommand cpCmnd, String nomeEntita, ApplicationData filtroDett, ApplicationData listaCodiciSel, String nameAttr, String tipoBase) {
	SequenceCommand seqCmd = (SequenceCommand)cpCmnd.getCommand();
	ExecCommand execCommandFixView = guigenFactory.createExecCommand();
	execCommandFixView.setMethodName(FIX+upperFirstLetter(filtroDett.getName())+upperFirstLetter(VIEW));
	
	CommandOutcome cmdOutOk = guigenFactory.createCommandOutcome();
	CommandOutcome cmdOutKo = guigenFactory.createCommandOutcome();
	cmdOutOk.setResultCode(FIX.toUpperCase()+filtroDett.getName().toUpperCase()+VIEW.toUpperCase()+"_"+OK);
	cmdOutKo.setResultCode(FIX.toUpperCase()+filtroDett.getName().toUpperCase()+VIEW.toUpperCase()+"_"+KO);
	execCommandFixView.getResults().add(cmdOutOk);
	execCommandFixView.getResults().add(cmdOutKo);
	NOPCommand nopCmdOK = guigenFactory.createNOPCommand();
	
	cmdOutOk.setCommand(nopCmdOK);
	NOPCommand nopCmdKO = guigenFactory.createNOPCommand();
	cmdOutKo.setCommand(nopCmdKO);
	seqCmd.getCommands().add(execCommandFixView);
	
	InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	inlineCS.setLang("JAVA");
	inlineCS.setSnippetName("body");
	inlineCS.setSnippetPosition("executedMethod");
	
	inlineCS.setSnippetCode(new CRUDSnippets().fixViewEntitySnippet(nomeEntita, filtroDett, listaCodiciSel, nameAttr, tipoBase).toString());
	
	execCommandFixView.getInlineCodeSnippets().add(inlineCS);
}

public static void buildFixViewRicerca(CPCommand cpCmnd, String nomeEntita, ApplicationData filtroDett, ApplicationData listaCodiciSel, String nameAttr, String tipoBase) {
	SequenceCommand seqCmd = (SequenceCommand)cpCmnd.getCommand();
	ExecCommand execCommandFixView = guigenFactory.createExecCommand();
	execCommandFixView.setMethodName(FIX+upperFirstLetter(filtroDett.getName())+upperFirstLetter(VIEW));
	
	CommandOutcome cmdOutOk = guigenFactory.createCommandOutcome();
	CommandOutcome cmdOutKo = guigenFactory.createCommandOutcome();
	cmdOutOk.setResultCode(FIX.toUpperCase()+filtroDett.getName().toUpperCase()+VIEW.toUpperCase()+"_"+OK);
	cmdOutKo.setResultCode(FIX.toUpperCase()+filtroDett.getName().toUpperCase()+VIEW.toUpperCase()+"_"+KO);
	execCommandFixView.getResults().add(cmdOutOk);
	execCommandFixView.getResults().add(cmdOutKo);
	NOPCommand nopCmdOK = guigenFactory.createNOPCommand();
	
	cmdOutOk.setCommand(nopCmdOK);
	NOPCommand nopCmdKO = guigenFactory.createNOPCommand();
	cmdOutKo.setCommand(nopCmdKO);
	seqCmd.getCommands().add(execCommandFixView);
	
	InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	inlineCS.setLang("JAVA");
	inlineCS.setSnippetName("body");
	inlineCS.setSnippetPosition("executedMethod");
	
	inlineCS.setSnippetCode(new CRUDSnippets().fixViewRicercaEntitySnippet(nomeEntita, filtroDett, listaCodiciSel, nameAttr, tipoBase).toString());
	
	execCommandFixView.getInlineCodeSnippets().add(inlineCS);
}

public static FormPanel buildUI(ComplexType entity, ApplicationData mainAppData, String prefisso){
	FormPanel fp = null; 
	String prefissoNomeCampo = null;
	// costruisco il form panel vuoto
	fp = guigenFactory.createFormPanel();
	// costruisco il WP associato ai field foglia 
	List<Field> foglie = getLeavesNode(entity);
	
	WidgetsPanel level0WP = buildUI(foglie, mainAppData,prefisso,entity.getName());
	if(prefisso.equals("")) {
		level0WP.setName(WP+upperFirstLetter(entity.getName()));
	}
	else {
		level0WP.setName(WP+upperFirstLetter(prefisso.replace(".", "")));
	}
	 fp.getSubpanels().add(level0WP);
	// costruisci la struttura associata ai Field "sottosezione"
	List<Field> subsectionFields = getSubsectionFields(entity);
	Iterator<Field> itSubsections = subsectionFields.iterator();
	while (itSubsections.hasNext()) {
		Field field = itSubsections.next();
		prefissoNomeCampo = prefisso +field.getName()+PUNTO;
		FormPanel currSubsectionFP = buildUI((ComplexType)field.getType(), mainAppData, prefissoNomeCampo);
		currSubsectionFP.setName(FP+upperFirstLetter(prefissoNomeCampo.replace(".", "")));
	    currSubsectionFP.setLabel(splitCamelCase(prefissoNomeCampo.replace(".", "")));
		VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
		currSubsectionFP.setLayout(vfpUp);
		fp.getSubpanels().add(currSubsectionFP);
	}
	return fp;
}

public static WidgetsPanel buildUI (List<Field> fieldfoglia, ApplicationData mainAppData, String prefisso, String nameEntity){
	// crea WP vuoto
	 WidgetsPanel wp = null;
	
	 wp = guigenFactory.createWidgetsPanel();
	 VerticalFlowPanelLayout vfplWdg = guigenFactory.createVerticalFlowPanelLayout();
	 wp.setLayout(vfplWdg);
	 
	// per ogni fieldfoglia:
	Iterator<Field> itFields=fieldfoglia.iterator();
	while (itFields.hasNext()) {
		Field field = itFields.next();
	//// crea il widgetCorrispondente 
		Widget currW = buildWidget(field, mainAppData, prefisso, nameEntity);
		wp.getWidgets().add(currW);
	}
	return wp;
}

public static DataWidget buildWidgetdarimetter2911(Field f, ApplicationData mainAppData, String nomeFieldNodo){
	SimpleType fieldType = (SimpleType)f.getType();
	int stcVal = fieldType.getCode().getValue();
	DataWidget w = null;
	AppDataBinding appDataBinding = null; //
	
	appDataBinding = guigenFactory.createAppDataBinding();
	appDataBinding.setAppData(mainAppData);
	
	String bindingPath= "";
	bindingPath =  nomeFieldNodo+f.getName();
	
	appDataBinding.setPath(bindingPath); 
	String campoFiltroNoPunto = bindingPath.replace(".", "");
	switch(stcVal) {
		case SimpleTypeCodes.STRING_VALUE: 
		case SimpleTypeCodes.INT_VALUE: {
			w = guigenFactory.createTextField(); 
			w.setName(TEXT_FIELD+upperFirstLetter(campoFiltroNoPunto));
			w.setDataType(fieldType);
		    w.setLabel(splitCamelCase(campoFiltroNoPunto));
			w.setEnableEnrichment(true);
			w.setDatabinding(appDataBinding);
			break;
			}
		case SimpleTypeCodes.BOOLEAN_VALUE: {
			CheckBox checkBox = guigenFactory.createCheckBox();
			w = checkBox;
			checkBox.setName(CHECK_BOX+upperFirstLetter(campoFiltroNoPunto));
			checkBox.setDataType(fieldType);
		    checkBox.setLabel(splitCamelCase(campoFiltroNoPunto));
			checkBox.setEnableEnrichment(true);
			checkBox.setDatabinding(appDataBinding);
			break;
		}
		case SimpleTypeCodes.DATE_VALUE: {
			Calendar calendar = guigenFactory.createCalendar();
			w = calendar;
			calendar.setName(CALENDAR+upperFirstLetter(campoFiltroNoPunto));
			calendar.setDataType(fieldType);
			calendar.setLabel(splitCamelCase(campoFiltroNoPunto));
			calendar.setEnableEnrichment(true);
			calendar.setDatabinding(appDataBinding);
			break;
		}
	}
	return w;
}

//cpDett
public static DataWidget buildWidget(Field f, ApplicationData mainAppData, String nomeFieldNodo,  String nomeEntity){
	SimpleType fieldType = null;
	int stcVal = 0;
	DataWidget w = null;
	AppDataBinding appDataBinding = null; 
	String bindingPath= "";
	String campoFiltroNoPunto =null;
	ExecCommand execCmdFix = null;
	CPCommand cpCmndDett = getCpCmdEnterDett();
			
	if(f.getType() instanceof SimpleType) {
		fieldType = (SimpleType)f.getType();
		stcVal = fieldType.getCode().getValue();
		
		appDataBinding = guigenFactory.createAppDataBinding();
		appDataBinding.setAppData(mainAppData);
		
		bindingPath =  nomeFieldNodo+f.getName();
		appDataBinding.setPath(bindingPath); 
		campoFiltroNoPunto = bindingPath.replace(".", "");
		switch(stcVal) {
			case SimpleTypeCodes.STRING_VALUE: 
			case SimpleTypeCodes.INT_VALUE: {
				w = guigenFactory.createTextField(); 
				w.setName(TEXT_FIELD+upperFirstLetter(campoFiltroNoPunto));
				w.setDataType(fieldType);
				w.setLabel(splitCamelCase(campoFiltroNoPunto));
				w.setEnableEnrichment(true);
				w.setDatabinding(appDataBinding);
				break;
				}
			case SimpleTypeCodes.BOOLEAN_VALUE: {
				CheckBox checkBox = guigenFactory.createCheckBox();
				w = checkBox;
				checkBox.setName(CHECK_BOX+upperFirstLetter(campoFiltroNoPunto));
				checkBox.setDataType(fieldType);
			    checkBox.setLabel(splitCamelCase(campoFiltroNoPunto));
				checkBox.setEnableEnrichment(true);
				checkBox.setDatabinding(appDataBinding);
				break;
			}
			case SimpleTypeCodes.DATE_VALUE: {
				Calendar calendar = guigenFactory.createCalendar();
				w = calendar;
				calendar.setName(CALENDAR+upperFirstLetter(campoFiltroNoPunto));
				calendar.setDataType(fieldType);
				calendar.setLabel(splitCamelCase(campoFiltroNoPunto));
				calendar.setEnableEnrichment(true);
				calendar.setDatabinding(appDataBinding);
				break;
			}
		}
	} else if(f.getType() instanceof ComplexType) {
		appDataBinding = guigenFactory.createAppDataBinding();
		appDataBinding.setAppData(mainAppData);
		bindingPath =  nomeFieldNodo+f.getName();
		Field codice = getCodiceField((ComplexType)f.getType());
		appDataBinding.setPath(bindingPath+"."+codice.getName()); 
		campoFiltroNoPunto = bindingPath.replace(".", "");
		
		ComboBox combokBox = guigenFactory.createComboBox();
		w = combokBox;
		combokBox.setName(COMBO_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
		combokBox.setDataType(codice.getType());
		combokBox.setKeySelector(codice.getName());
		Field desc = getDescrizioneField((ComplexType)f.getType());
		combokBox.setValueSelector(desc.getName());
		
		combokBox.setLabel(splitCamelCase(campoFiltroNoPunto));
		combokBox.setEnableEnrichment(true);
		combokBox.setDatabinding(appDataBinding);
		//creo appdata per listaDecodifica ovvero un vettore di oggetti di decodifica
		ApplicationData adElencoDecodifica = guigenFactory.createApplicationData();
		adElencoDecodifica.setName(DTL+nomeEntity+upperFirstLetter(f.getName())+ITEMS);
		adElencoDecodifica.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		Type tipoElencoNomeEntita = getTipoArrayByNameDett(f);
		adElencoDecodifica.setType(tipoElencoNomeEntita);
		
		AppDataBinding  multiAppDataBinding = guigenFactory.createAppDataBinding();
		multiAppDataBinding.setAppData(adElencoDecodifica);
		((MultiDataWidget) combokBox).setMultiDataBinding(multiAppDataBinding);
		
		getAppDataGroup().getAppData().add(adElencoDecodifica);
		
		EList<ContentPanel> listaCP = appModule.getContentPanels();
		for (ContentPanel contentPanel : listaCP) {
			contentPanel.getAppData().add(adElencoDecodifica);
		}
		buildMethodLoadCombo(cpCmndDett,
							 DTL,
							 nomeEntity,
							 adElencoDecodifica,
							 f.getName(),
							 upperFirstLetter(f.getName()));
		execCmdFix = fixComboSingleElememts(nomeEntity, 
											mainAppData,
											adElencoDecodifica,
								            upperFirstLetter(f.getName()),
								            codice.getName());
		listaDettExecCmdFix.add(execCmdFix);
		try {
			Resource risorsa = getRisorsaAppdDataGroup();
			risorsa.save(null);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	} else if(f.getType() instanceof TypedArray) {
		appDataBinding = guigenFactory.createAppDataBinding();
		ApplicationData listaCodiciSel = guigenFactory.createApplicationData();
		listaCodiciSel.setName(DTL+nomeEntity+upperFirstLetter(f.getName())+SELECTED_KEYS);
		listaCodiciSel.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		Type tipoString = WizardHelper.getTipoArrayString(COMMON);
		
		listaCodiciSel.setType(tipoString);
		appDataBinding.setAppData(listaCodiciSel);//imposto con appdatafiltro
		
		bindingPath =  nomeFieldNodo+f.getName();
		campoFiltroNoPunto = bindingPath.replace(".", "");
		
		ComboBox combokBox = guigenFactory.createComboBox();
		w = combokBox;
		combokBox.setName(COMBO_BOX+RICERCA+upperFirstLetter(campoFiltroNoPunto));
		combokBox.setDataType(fieldType);
		ComplexType componentType = (ComplexType) ((TypedArray)f.getType()).getComponentType();
		Field codice = getCodiceField(componentType);
		combokBox.setDataType(codice.getType());
		combokBox.setKeySelector(codice.getName());
		Field desc = getDescrizioneField(componentType);
		combokBox.setValueSelector(desc.getName());
	
		combokBox.setLabel(splitCamelCase(campoFiltroNoPunto));
		combokBox.setEnableEnrichment(true);
		combokBox.setDatabinding(appDataBinding);
		
		//creo appdata per listaDecodifica ovvero un vettore di oggetti di decodifica
		ApplicationData adElencoDecodifica = guigenFactory.createApplicationData();
		adElencoDecodifica.setName(DTL+nomeEntity+upperFirstLetter(f.getName())+ITEMS);
		adElencoDecodifica.setLifetimeExtent(DataLifetimeType.USER_SESSION);
		adElencoDecodifica.setType(f.getType());
		AppDataBinding  multiAppDataBinding = guigenFactory.createAppDataBinding();
		multiAppDataBinding.setAppData(adElencoDecodifica);
		((MultiDataWidget) combokBox).setMultiDataBinding(multiAppDataBinding);
		
		getAppDataGroup().getAppData().add(adElencoDecodifica);
		getAppDataGroup().getAppData().add(listaCodiciSel);
		
		EList<ContentPanel> listaCP = appModule.getContentPanels();
		for (ContentPanel contentPanel : listaCP) {
			contentPanel.getAppData().add(adElencoDecodifica);
			contentPanel.getAppData().add(listaCodiciSel);
		}
		buildMethodLoadCombo(cpCmndDett,
							 DTL,
							 nomeEntity,
							 adElencoDecodifica, 
							 f.getName(),
							 componentType.getName());
		execCmdFix = fixComboMultipleElememts( nomeEntity, 
												appDataDett, 
												adElencoDecodifica,
												listaCodiciSel,
												f.getName(),
				 								componentType.getName(),
				 								codice.getName());
		listaDettExecCmdFix.add(execCmdFix);
		buildFixView(cpCmndDett,
					 nomeEntity, 
					 appDataDett, 
					 listaCodiciSel,
					 f.getName(),
					 componentType.getName());
		try {
			Resource risorsa = getRisorsaAppdDataGroup();
			risorsa.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return w;
}

public static List<Field> getSubsectionFields(ComplexType tipo){
	 EList<Field> listaAttrEntita  = tipo.getAllFields() ;
	 List<Field> listaSubsectionField = new ArrayList<Field>();
	 for (Field field : listaAttrEntita) {
	 if(field.getType() instanceof ComplexType && isAggregatedSimple(field)) { 
			 listaSubsectionField.add(field);
		 }
	}
	return listaSubsectionField; 
}
 
////////////////////////////////////////////

public static int getNumeroFormPanel(ComplexType entity){
	int numFormpanel = 0;
	
	List<Field> subsectionFields = getSubsectionFields(entity);
	if(subsectionFields != null){
		numFormpanel = subsectionFields.size();
	}
	return numFormpanel;
}

public static List<Field> getLeavesNode(ComplexType tipo) {
	List<Field> listaFoglie = new ArrayList<Field>();
	if(tipo != null) {
		EList<Field> children = tipo.getAllFields();
		Iterator<Field> iter = children.iterator();
		Field attr = null;
		 if(iter != null) {
		    while(iter.hasNext()) {
			     attr = (Field)iter.next();
			     if(attr.getType() instanceof SimpleType) {
			    	 listaFoglie.add(attr);
			     } else if(attr.getType() instanceof ComplexType && isDecodedField(attr)) {
			    	 listaFoglie.add(attr);
			     } else if(attr.getType() instanceof TypedArray && isDecodedField(attr)) {
			    	 listaFoglie.add(attr);
			     }
		    }
		 }
	}
	return listaFoglie;	
	}

public static FormPanel creaFPDett(String nomeForm, UDLRCSpecConstants udlrSpecConstants) { 
	
	 FormPanel fpUp = guigenFactory.createFormPanel();
	 fpUp.setName(nomeForm);
	
	 VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
	 fpUp.setLayout(vfpUp);
	
	 UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
	 udlrcWdgUp.setValue(udlrSpecConstants);
	 fpUp.setLayoutSpec(udlrcWdgUp);
	 return fpUp;
}

public static FormPanel creaFPPrincipaleDett(WizardInfo info) {
	FormPanel fp = guigenFactory.createFormPanel();
	 
	 fp.setName(FP+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
	 fp.setLabel(upperFirstLetter(DETTAGLIO)+BLANK+info.getNomeEntita());
	 
	 UDLRCPanelLayout udlrPL = guigenFactory.createUDLRCPanelLayout();
	 fp.setLayout(udlrPL);
	 
	 return fp;
}

public static String getValueBykeyByListaAnnotationDetail (String key, EList<AnnotationDetail> listaAnnotazDett) {
	String chiave = null;
	String valore = null;
	String res = null;
	
	for(AnnotationDetail anDett :listaAnnotazDett) {
		 chiave = anDett.getKey().trim();
		 valore = anDett.getValue();
		 if(chiave.equals(key)){
			 res = valore;
			 break;
	      }
	 }
	return res;
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
		
		public static void clearListAndMapAttrEntity(WizardInfo info) {
			info.getListaAttrEntita().clear();
			info.getMapListaAttrEntita().clear();
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
			
			setAppModule(nuovoAppMod);
			setResourceAppMod(resourceAppModule);
			
			resourceAppModule.save(options);
			WizardHelper.modPrincResource.save(options);
		}
		
		public static void creaAppDataGroup(String nomeEntita, WizardInfo info) throws IOException {
			
			try {
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
				setAppDataGroup(appDataGroup);
				//creo appdata per il filtro dell'entita
				ApplicationData adf = guigenFactory.createApplicationData();
				adf.setName(FLT+nomeEntita);
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
				adPK.setName(ID+SELEZIONATO+nomeEntita);
				//recupero il tipo della PK
				Type tipoPK = WizardHelper.getTypeByAttrNameField(info.getIdPrimaryKey(), info);
				adPK.setType(tipoPK);
				//imposto la variabile dell'helper
				appDataPK = adPK;
				//lo aggiungo all'appdata group
				appDataGroup.getAppData().add(adPK);
				//creo appDataMethod
				ApplicationData apdMethod = guigenFactory.createApplicationData();
				apdMethod.setLifetimeExtent(DataLifetimeType.USER_SESSION);  
				apdMethod.setName(WizardHelper.lowerFirstLetter(nomeEntita)+CRUD_OPERATION);
				Type tipoString = WizardHelper.getTipoPrimitivo(COMMON, STRING);
				apdMethod.setType(tipoString);
				appDataMethod = apdMethod;
				appDataGroup.getAppData().add(apdMethod);
				//creo appdata per dettaglio
				ApplicationData adDett = guigenFactory.createApplicationData();
				adDett.setName(DTL+nomeEntita);
				adDett.setLifetimeExtent(DataLifetimeType.USER_SESSION);
				adDett.setType(tipoEntita);
				//setto la varibile di Helper
				appDataDett = adDett;
				//lo aggiungo all'appdata group
				appDataGroup.getAppData().add(adDett);
				//creo appdata per elencoNomeEntita ovvero un vettore dell'entita'
				ApplicationData adElencoNomeEntita = guigenFactory.createApplicationData();
				adElencoNomeEntita.setName(ELENCO+nomeEntita);
				adElencoNomeEntita.setLifetimeExtent(DataLifetimeType.USER_SESSION);

				Type tipoElencoNomeEntita = getTipoArrayByName(nomeEntita, info);
				adElencoNomeEntita.setType(tipoElencoNomeEntita);
				//setto la variabile di Helper
				appDataElencoEntita = adElencoNomeEntita;
				//lo aggiungo all'appdata group
				appDataGroup.getAppData().add(adElencoNomeEntita);
				//associo l'appaData group al modello 
				guiModel.getAppDataDefs().getExtGroups().add(appDataGroup);
				appDataResource.getContents().add(appDataGroup);
				
				setRisorsaAppdDataGroup(appDataResource);
				appDataResource.save(options);
				modPrincResource.save(options);
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		public static Type getTypeByAttrName(String nomeAttributo, WizardInfo info) throws IOException {
			 Map<String, Type> mapListaAttrEntita = info.getMapListaAttrEntita();
			Type tipoAttr =  mapListaAttrEntita.get(nomeAttributo);
			 
			return tipoAttr;
		}
	
		public static Type getTypeByAttrNameField(String nomeAttributo, WizardInfo info)  {//14-11 ho tolto throws IOException
			 Map<String, FieldContext> mapListaAttrEntita = info.getMapListaAttrEntitaContext();
			 FieldContext fieldContext =  mapListaAttrEntita.get(nomeAttributo);
			return fieldContext.getField().getType();
		}
		
		public  void creaCPRicercaByAppModule(WizardInfo info) throws IOException { 
			try {
			
			 Map<Object, Object> options = new HashMap<Object, Object>();
			
			 String nomeEntita = info.getNomeEntita();
			 ContentPanel cp = guigenFactory.createContentPanel();
			 FormPanel fp = guigenFactory.createFormPanel();
			 guiModel.getStructure().getAppWindow().getAppArea().setHomePage(cp);
			 
			 cp.setName(CP+RICERCA+nomeEntita);
			 //aggiungere app data al cp
			 cp.getAppData().add(appDataFiltro);
			 cp.getAppData().add(appDataPK);
			 cp.getAppData().add(appDataDett);
			 cp.getAppData().add(appDataElencoEntita);
			 cp.getAppData().add(appDataMethod);
			 
			 appModule.getContentPanels().add(cp);
			 cpDettaglio = guigenFactory.createContentPanel();
			 appModule.getContentPanels().add(cpDettaglio);
			 multiPanelRicerca = guigenFactory.createMultiPanel();
			
			 CPCommands cpCmds = guigenFactory.createCPCommands();
			 CPCommand cpCmdEnter = guigenFactory.createCPCommand();
			 setCpCmdEnter(cpCmdEnter);
			 cpCmdEnter.setExecute(CPCommandExecutionTypes.ON_ENTER);
			 cpCmds.getCommands().add(cpCmdEnter);
			 
			 cp.setCpCommands(cpCmds);//aggancio al content panel di ricerca
			 
			 SequenceCommand seqCmdCPEnter = guigenFactory.createSequenceCommand();
			 
			 cpCmdEnter.setCommand(seqCmdCPEnter);
			 
			 ClearAppdataCommand clearAppdata = guigenFactory.createClearAppdataCommand();
			 clearAppdata.getAppData().add(appDataFiltro);
			 clearAppdata.getAppData().add(appDataPK);
			 clearAppdata.getAppData().add(appDataElencoEntita);
			 clearAppdata.getAppData().add(appDataDett);
			 
			 seqCmdCPEnter.getCommands().add(clearAppdata);
				
			 ActivateMultiPanelItemCommand activeMPanelItemCmdcopy = guigenFactory.createActivateMultiPanelItemCommand();
			 activeMPanelItemCmdcopy.setActiveItem(null);
			 activeMPanelItemCmdcopy.setMultipanel(getMultiPanelRicerca());
		    
			 seqCmdCPEnter.getCommands().add(activeMPanelItemCmdcopy);
			
			 fp.setName(FP+RICERCA+nomeEntita);
			 fp.setLabel(RICERCA+ " "+nomeEntita);
			 
			 UDLRCPanelLayout udlrPL = guigenFactory.createUDLRCPanelLayout();
			 fp.setLayout(udlrPL);

			 FormPanel fpUp = creaFormPanelUp();
			 
			 String[] campiFiltri = getListaFiltri();//costituita da tipi semplici
			 		
			 ComplexType tipoEntita = (ComplexType)info.getMapNomeTipo().get(info.getNomeEntita());
			 
			 List<FieldContext> listaFiltriSel = WizardHelper.findListFieldFilter(campiFiltri, info);
			 			 
			 FormPanel fpCenter = buildFpUI(tipoEntita, listaFiltriSel, info, appDataFiltro, "");
			 fpCenter.setName(FP+CENTER);
			 VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
			 fpCenter.setLayout(vfpUp);
			 UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
			 udlrcWdgUp.setValue( UDLRCSpecConstants.CENTER);
			 fpCenter.setLayoutSpec(udlrcWdgUp);
			 StdMessagePanel stdMsgPanel = creaStdMsgPanelRicerca(info, true, true, true);
			 fpCenter.getSubpanels().add(0, stdMsgPanel);
			
			 FormPanel fpLeft = creaFormPanelLeft(); 
			 //inserisco il form panel nel content panel 
			 cp.setPanels(fp);
			
			 fp.getSubpanels().add(fpUp);
			 fp.getSubpanels().add(fpCenter);
			 fp.getSubpanels().add(fpLeft);
			
			 CommandPanel cmdPanel = creaCmdPanelRicerca(info, fpCenter, cp);
			
			 fpCenter.getSubpanels().add(fpCenter.getSubpanels().size()-1, cmdPanel);
			 /* Esempio con un solo FormPanel (4 subpanels, il cmdPanel lo metto nella posizione i=3(4-1)
			  * 0 StdM 1
			  * 1 Widg  2
			  * 2 FormPanel 3
			  * 3 CmdPanel
			 	4 MultiPanel  4
			 */
			 resourceAppMod.save(options);
			 modPrincResource.save(options);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static List<FieldContext> findListFieldFilter(String[] campiFiltri, WizardInfo info) {
			List<FieldContext> listaFiltri  = new ArrayList<FieldContext>();
			FieldContext  fieldContext = null;
			for( int i = 0; i< campiFiltri.length; i++){
				fieldContext = new FieldContext();
				fieldContext.setPath(info.getMapListaAttrEntitaContext().get(campiFiltri[i]).getPath());
				fieldContext.setField(info.getMapListaAttrEntitaContext().get(campiFiltri[i]).getField());
				listaFiltri.add(fieldContext);
			}
		return listaFiltri;	
		}
		
		public  static ContentPanel creaCPDettaglioByAppModule( WizardInfo info, ContentPanel cpRicerca) throws IOException { 
				CPCommands cpCmds = guigenFactory.createCPCommands();
				CPCommand cpCmdEnterDett = guigenFactory.createCPCommand();
				setCpCmdEnterDett(cpCmdEnterDett);
				cpCmdEnterDett.setExecute(CPCommandExecutionTypes.ON_ENTER);
				cpCmds.getCommands().add(cpCmdEnterDett);
				cpDettaglio.setCpCommands(cpCmds);//aggancio al content panel di dettaglio
				SequenceCommand seqCmdCPEnterDett = guigenFactory.createSequenceCommand();
				cpCmdEnterDett.setCommand(seqCmdCPEnterDett);
				
				 FormPanel fp = guigenFactory.createFormPanel();
				 
				 cpDettaglio.setName(CP+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
				 //aggiungere app data al cp
				 cpDettaglio.getAppData().add(appDataDett);
				 cpDettaglio.getAppData().add(appDataMethod);
				
				 fp.setName(FP+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
				 fp.setLabel(upperFirstLetter(DETTAGLIO)+BLANK+info.getNomeEntita());
				 
				 UDLRCPanelLayout udlrPL = guigenFactory.createUDLRCPanelLayout();
				 fp.setLayout(udlrPL);
				 
				 FormPanel fpUp = creaFPDett(FP+UP, UDLRCSpecConstants.UP);
				 
				 StdMessagePanel stdMsgPanelDett = creaStdMsgPanelDettaglio(info, true, true, true);
				 
				 List listaAttrEntita = info.getListaAttrEntita();
				 
				 Collections.sort(listaAttrEntita);
				
				 String[] campi = (String[])listaAttrEntita.toArray(new String[listaAttrEntita.size()]);
				 
				 ComplexType tipoEntita = (ComplexType)info.getMapNomeTipo().get(info.getNomeEntita());
				 
				 FormPanel fpCenter = buildUI(tipoEntita, appDataDett, "");
				
				 fpCenter.getSubpanels().add(0, stdMsgPanelDett);
				
			     int numeroFormPanel = getNumeroFormPanel(tipoEntita)+2;//quello std + widget iniziale
			    
				 fpCenter.setName(FP+CENTER);
				 VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
				 fpCenter.setLayout(vfpUp);
				 UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
				 udlrcWdgUp.setValue( UDLRCSpecConstants.CENTER);
				 fpCenter.setLayoutSpec(udlrcWdgUp);
				
				 CommandPanel cmdPanelNuovaRicerca = guigenFactory.createCommandPanel();
				 cmdPanelNuovaRicerca.setName("cmdNuovaRicerca"+info.getNomeEntita());
				 
				 HorizontalFlowPanelLayout hfpl = guigenFactory.createHorizontalFlowPanelLayout();
				 cmdPanelNuovaRicerca.setLayout(hfpl);
				 
				 fpCenter.getSubpanels().add(numeroFormPanel, cmdPanelNuovaRicerca);
				 
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
				 SequenceCommand seqCmdSalvaRoot = guigenFactory.createSequenceCommand(); 
				 evHandlerSalva.setCommand(seqCmdSalvaRoot);
				 
				 for(ExecCommand execCmdFix :listaDettExecCmdFix) {
					 seqCmdSalvaRoot.getCommands().add(execCmdFix); 
				 }
				 ExecCommand execCommandSalva = guigenFactory.createExecCommand();
				 execCommandSalva.setMethodName(lowerFirstLetter(SALVA)+info.getNomeEntita());
				 seqCmdSalvaRoot.getCommands().add(execCommandSalva); 
				 
				 InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
				 inlineCS.setLang("JAVA");
				 inlineCS.setSnippetName("body");
				 inlineCS.setSnippetPosition("executedMethod");
				 inlineCS.setSnippetCode(new CRUDSnippets().salvaEntitySnippet(info.getNomeEntita(), appDataMethod.getName(), appDataDett.getName()).toString());//6-12-2012
				 
				 execCommandSalva.getInlineCodeSnippets().add(inlineCS);
				 
				 CommandOutcome cmdOutOkSalva = guigenFactory.createCommandOutcome();
				 cmdOutOkSalva.setResultCode(RESULT_CODE);
				 
				 execCommandSalva.getResults().add(cmdOutOkSalva);
				
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
				 
				 jCmdFromDettToRicerca.setJumpTo(cpRicerca);
				 
				 FormPanel fpLeft = guigenFactory.createFormPanel();
				 fpLeft.setName("fpLeft");
				
				 VerticalFlowPanelLayout vfpLeft = guigenFactory.createVerticalFlowPanelLayout();
				 fpLeft.setLayout(vfpLeft);
				 
				 UDLRCWidgetLayoutSpec udlrcWdgLeft = guigenFactory.createUDLRCWidgetLayoutSpec();
				 udlrcWdgLeft.setValue(UDLRCSpecConstants.LEFT);
				 fpLeft.setLayoutSpec(udlrcWdgLeft);
				
				 cpDettaglio.setPanels(fp);
				 fp.getSubpanels().add(fpUp);
				 fp.getSubpanels().add(fpCenter);
				 fp.getSubpanels().add(fpLeft);
			return cpDettaglio;
		}

public static  Type getTipoArrayByName2(String nomeEntita)  {
	Typedefs tipi = guiModel.getTypedefs();
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

public static  Type getTipoArrayByName(String nomeEntita, WizardInfo info)  {
	Typedefs tipi = guiModel.getTypedefs();
	Type res = null;
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	//recupero il tipo dell'entita
	//e di tipo complesso recupero il padre che mi d il namespace da cui recupero il nome del TNS
	Type tipoEntita = getTipoByNameEntita(nomeEntita, info);
	String tnsSource = null;
	if(tipoEntita instanceof ComplexType) {
			TypeNamespace padre = (TypeNamespace)tipoEntita.eContainer();
			tnsSource = padre.getName();
	}
	for (TypeNamespace namespace : listaNamespace) {
		//se il namespace che scorro (for) ha quel nome
		if(namespace.getName().equals(tnsSource)) {
			//l far  la mia ricerca: recupero lista tipi e cercher quello con component type =nome entit.
			listaTipi = namespace.getTypes();
		  	for(Type tipo : listaTipi) {
		  		if(tipo instanceof TypedArray && ((TypedArray) tipo).getComponentType().getName().equals(nomeEntita)) {
		  			res = tipo;
		  			break;
		  		}
		  	}
	  	}
	}
	return res;
}

public static  Type getTipoArrayByNameDett(Field nomeEntita)  {
	Typedefs tipi = guiModel.getTypedefs();
	Type res = null;
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	//recupero il tipo dell'entita
	// di tipo complesso recupero il padre che mi d il namespace da cui recupero il nome del TNS
	Type tipoEntita = nomeEntita.getType();
	String tnsSource = null;
	if(tipoEntita instanceof ComplexType || tipoEntita instanceof TypedArray) {//TypedArray
			TypeNamespace padre = (TypeNamespace)tipoEntita.eContainer();
			tnsSource = padre.getName();
	}
	for (TypeNamespace namespace : listaNamespace) {
		//se il namespace che scorro (for) ha quel nome
		if(namespace.getName().equals(tnsSource)) {
			//l faro la mia ricerca: recupero lista tipi e cercher quello con component type =nome entit.
			listaTipi = namespace.getTypes();
		  	for(Type tipo : listaTipi) {
		  		if(tipo instanceof TypedArray && ((TypedArray) tipo).getComponentType().getName().equals(upperFirstLetter(nomeEntita.getName()))) {
		  		 if(tipo instanceof TypedArray ) {
		  			res = tipo;
		  			break;
		  		 }
		  		}
		  	}
		}
	}
	return res;
}

public static  Type getTipoArrayByNameRic(Field f)  {
	Typedefs tipi = guiModel.getTypedefs();
	Type res = null;
	
	EList<Type> listaTipi;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	//recupero il tipo dell'entita
	Type tipoEntita = f.getType();
	String tnsSource = null;
	if(tipoEntita instanceof ComplexType) {
			TypeNamespace padre = (TypeNamespace)tipoEntita.eContainer();
			tnsSource = padre.getName();
	}
	for (TypeNamespace namespace : listaNamespace) {
		//se il namespace che scorro (for) ha quel nome
		if(namespace.getName().equals(tnsSource)) {
			listaTipi = namespace.getTypes();
		  	for(Type tipo : listaTipi) {
		  		if(tipo instanceof TypedArray && ((TypedArray) tipo).getComponentType().getName().equals(tipoEntita.getName())){
		  			res = tipo;
		  			break;
		  		 }
		  	}
		}
	}
	return res;
}

public static  Type getTipoPrimitivo(String nomeNamespace, String nomePrimitivo)  {
	Type res = null;
	EList<Type> listaTipi = null;
	TypeNamespace namespace = findNamespace(nomeNamespace);
	listaTipi = namespace.getTypes();
	for(Type tipo : listaTipi) {
  		if((tipo instanceof SimpleType) && ((SimpleType)tipo).getCode().getName().equals(nomePrimitivo.toUpperCase())) {
  			res = tipo;
  			break;
  		}
	}
	return res;
}

public static  TypeNamespace findNamespace(String nomeNamespace)  {
	Typedefs tipi = guiModel.getTypedefs();
	TypeNamespace res = null;
	EList<TypeNamespace> listaNamespace = tipi.getExtNamespaces();
	for (TypeNamespace namespace : listaNamespace) {
		if(namespace.getName().equals(nomeNamespace)) {
			res = namespace;
			break;
		}
	}
	return res;
}

public static Type getTipoArrayString(String nomeNamespace) {
	
	Type res = null;
	EList<Type> listaTipi = null;
	TypeNamespace namespace = findNamespace(nomeNamespace);
	listaTipi = namespace.getTypes();
	for(Type tipo : listaTipi) {
		
  		if(tipo instanceof TypedArray && ((TypedArray)tipo).getComponentType().getName().equals(STRING)){
  			res = tipo;
  			break;
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
	return splitCamelCase(wordCamelCase, " ");
}

public static  String splitCamelCase(String wordCamelCase, String separatore)  {
	StringBuffer res = new StringBuffer();
	String ww = upperFirstLetter(wordCamelCase);
	int size = 0;
	if(wordCamelCase == null) {
		return res.toString();
	}
	if(wordCamelCase.length() == 0) {
		return res.toString();
	}
	List<Integer> listaIndex = findIndex(ww);
	size = listaIndex.size();
	if(size == 0 ) {
		return res.append(upperFirstLetter(ww)).toString();
	}
	if(size == 1 && listaIndex.get(0)== 0) {
		return res.append(upperFirstLetter(ww)).toString();
	}
	int i = 0;
	while (i<size-1) {
		res.append(ww.substring(listaIndex.get(i), listaIndex.get(i+1))+ separatore);
		i++;
	}
	return res.append(wordCamelCase.substring(listaIndex.get(i), wordCamelCase.length())).toString();

	//return res.toString();
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

public static FormPanel  creaFormPanelUp()  {
	FormPanel fpUp = guigenFactory.createFormPanel();
	fpUp.setName(FP+UP);
	VerticalFlowPanelLayout vfpUp = guigenFactory.createVerticalFlowPanelLayout();
	fpUp.setLayout(vfpUp);

	UDLRCWidgetLayoutSpec udlrcWdgUp = guigenFactory.createUDLRCWidgetLayoutSpec();
	udlrcWdgUp.setValue(UDLRCSpecConstants.UP);
	fpUp.setLayoutSpec(udlrcWdgUp);
	return fpUp;
}

public static FormPanel  creaFormPanelCenter()  {
	FormPanel fpCenter = guigenFactory.createFormPanel();
	fpCenter.setName(FP+CENTER);
	
	VerticalFlowPanelLayout vfpCenter = guigenFactory.createVerticalFlowPanelLayout();
	fpCenter.setLayout(vfpCenter);
	
	UDLRCWidgetLayoutSpec udlrcWdgCenter = guigenFactory.createUDLRCWidgetLayoutSpec();
	udlrcWdgCenter.setValue(UDLRCSpecConstants.CENTER);
	fpCenter.setLayoutSpec(udlrcWdgCenter);
	return fpCenter;
}

public static FormPanel  creaFormPanelLeft()  {
		FormPanel fpLeft = guigenFactory.createFormPanel();
		fpLeft.setName("fpLeft");
		
		VerticalFlowPanelLayout vfpLeft = guigenFactory.createVerticalFlowPanelLayout();
		fpLeft.setLayout(vfpLeft);
		
		UDLRCWidgetLayoutSpec udlrcWdgLeft = guigenFactory.createUDLRCWidgetLayoutSpec();
		udlrcWdgLeft.setValue(UDLRCSpecConstants.LEFT);
		fpLeft.setLayoutSpec(udlrcWdgLeft);
		return fpLeft;
}

public static StdMessagePanel  creaStdMsgPanelRicerca(WizardInfo info, boolean glbMsg, boolean errors, boolean errorDetails) {
	
		StdMessagePanel stdMsgPanel = guigenFactory.createStdMessagePanel();
		stdMsgPanel.setName(STANDARD_MSG+RICERCA+info.getNomeEntita());
		stdMsgPanel.setShowGlobalMessages(glbMsg);
		stdMsgPanel.setShowFieldErrors(errors);
		stdMsgPanel.setShowFieldErrorDetails(errorDetails);
		return stdMsgPanel;
}

public static StdMessagePanel  creaStdMsgPanelDettaglio(WizardInfo info, boolean glbMsg, boolean errors, boolean errorDetails) {
	 StdMessagePanel stdMsgPanelDett = guigenFactory.createStdMessagePanel();
	 stdMsgPanelDett.setName(STANDARD_MSG+upperFirstLetter(DETTAGLIO)+info.getNomeEntita());
	 stdMsgPanelDett.setShowFieldErrorDetails(true);
	 stdMsgPanelDett.setShowFieldErrors(true);
	 stdMsgPanelDett.setShowGlobalMessages(true);
	 return stdMsgPanelDett;
}

public  CommandPanel  creaCmdPanelRicerca(WizardInfo info, FormPanel fpCenter, ContentPanel cp) throws IOException{//7-11 era statico 
	 String nomeEntita= info.getNomeEntita();
	 
	 CommandPanel cmdPanel = guigenFactory.createCommandPanel();
	 cmdPanel.setName(CMD+upperFirstLetter(FILTRO)+RICERCA+nomeEntita);
	 
	 HorizontalFlowPanelLayout hfpl = guigenFactory.createHorizontalFlowPanelLayout();
	 cmdPanel.setLayout(hfpl);
	 
	 Button btRicerca = creaBottone(info, RICERCA, cmdPanel, CommandFunctions.SEARCH);
	 
	 EventHandler evHandlerRicerca = creaEventoHandlerBottone(btRicerca);
	 
	 SequenceCommand seqCmdRicercaRoot = guigenFactory.createSequenceCommand(); 
	 evHandlerRicerca.setCommand(seqCmdRicercaRoot);
	 
	 for(ExecCommand execCmdFix :listaRicercaExecCmdFix) {
		 seqCmdRicercaRoot.getCommands().add(execCmdFix); 
	 }
	 ExecCommand execCommandRicerca = guigenFactory.createExecCommand();
	 execCommandRicerca.setMethodName(lowerFirstLetter(RICERCA)+nomeEntita);
	 seqCmdRicercaRoot.getCommands().add(execCommandRicerca); 
	 
	 InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	 inlineCS.setLang("JAVA");
	 inlineCS.setSnippetName("body");
	 inlineCS.setSnippetPosition("executedMethod");
	 inlineCS.setSnippetCode(new CRUDSnippets().ricercaEntitySnippet(nomeEntita, appDataFiltro.getName(), appDataElencoEntita.getName()).toString());
	 
	 execCommandRicerca.getInlineCodeSnippets().add(inlineCS);
	 
	 CommandOutcome cmdOutOkRic = guigenFactory.createCommandOutcome();
	 cmdOutOkRic.setResultCode(RESULT_CODE);
	 
	 execCommandRicerca.getResults().add(cmdOutOkRic);
	 
	 CommandOutcome cmdOutKoRic = guigenFactory.createCommandOutcome();
	 cmdOutKoRic.setResultCode(RESULT_CODE_KO);
	 execCommandRicerca.getResults().add(cmdOutKoRic);
	
	 SequenceCommand seqCmdRicerca = guigenFactory.createSequenceCommand();
	 SequenceCommand seqCmdRicercaKo = guigenFactory.createSequenceCommand();
	 
	 RefreshViewCommand refreshVCRicerca = guigenFactory.createRefreshViewCommand();
	 refreshVCRicerca.getTargetWidgets().add(btRicerca);
	 refreshVCRicerca.getTargetPanels().add(fpCenter);
	 
	 RefreshViewCommand refreshVCRicercaKo = guigenFactory.createRefreshViewCommand();
	 refreshVCRicercaKo.getTargetWidgets().add(btRicerca);
	 refreshVCRicercaKo.getTargetPanels().add(fpCenter);
	 
	 seqCmdRicerca.getCommands().add(refreshVCRicerca); 
	 seqCmdRicercaKo.getCommands().add(refreshVCRicercaKo); 
	 
	 cmdOutOkRic.setCommand(seqCmdRicerca);
	 cmdOutKoRic.setCommand(seqCmdRicercaKo);
	 //aggancio mul
	 ActivateMultiPanelItemCommand activeMPanelItemCmd = guigenFactory.createActivateMultiPanelItemCommand();
	 ActivateMultiPanelItemCommand activeMPanelItemCmdKo = guigenFactory.createActivateMultiPanelItemCommand();
	 //creo il multiPanel di ricerca
	 multiPanelRicerca.setName(MULTI_PANEL+upperFirstLetter(ELENCO)+info.getNomeEntita());
	 multiPanelRicerca.setLabel(ELENCO+info.getNomeEntita());
	
	 MultiPanel mpRicerca = multiPanelRicerca;
	 setMultiPanelRicerca(mpRicerca);
	 
	 fpCenter.getSubpanels().add(mpRicerca);
	
	 activeMPanelItemCmd.setMultipanel(mpRicerca);//imposto il multi panel
	 activeMPanelItemCmdKo.setMultipanel(mpRicerca);
   
	 cmdOutOkRic.setCommand(seqCmdRicerca);
	 cmdOutKoRic.setCommand(seqCmdRicercaKo);
	 
	 seqCmdRicerca.getCommands().add(activeMPanelItemCmd);
	 seqCmdRicercaKo.getCommands().add(activeMPanelItemCmdKo);
	 //costruisco il formPanel del multiPanel di ricerca per ELENCO ENTITA
	 FormPanel fpMultiPanelTab = creaFormMPTab(info);

	 creaTabellaEntita(info, fpMultiPanelTab);
	 
	 activeMPanelItemCmd.setActiveItem(fpMultiPanelTab);//imposto il form panel per la tabella 
	  
	 mpRicerca.getPanels().add(fpMultiPanelTab);
	 
	 Button btInserisciEntita = creaBottone(info, INSERISCI, cmdPanel, CommandFunctions.ADD_ITEM);
	 EventHandler evHandleInsert = creaEventoHandlerBottone(btInserisciEntita);
	 
	 ExecCommand execCommandInsert = guigenFactory.createExecCommand();
	 execCommandInsert.setMethodName("insertDett"+info.getNomeEntita());
	  
	 InlineCodeSnippet inlineInsertCS = guigenFactory.createInlineCodeSnippet();
	 inlineInsertCS.setLang("JAVA");
	 inlineInsertCS.setSnippetName("body");
	 inlineInsertCS.setSnippetPosition("executedMethod");

	 inlineInsertCS.setSnippetCode(new CRUDSnippets().insertEntitySnippet(nomeEntita, appDataMethod.getName(), appDataDett).toString());
	 execCommandInsert.getInlineCodeSnippets().add(inlineInsertCS);
	 
	 CommandOutcome cmdOutViewDetail = guigenFactory.createCommandOutcome();
	 cmdOutViewDetail.setResultCode(VIEW_DETAIL);
	 
	 evHandleInsert.setCommand(execCommandInsert);
	 execCommandInsert.getResults().add(cmdOutViewDetail);
	 
	 JumpCommand jCmdFromInsertToDett = guigenFactory.createJumpCommand();
	 cmdOutViewDetail.setCommand(jCmdFromInsertToDett);
	 
	 cpDettaglio = creaCPDettaglioByAppModule(info, cp);
	 setCpDettaglio(cpDettaglio); 
	 jCmdFromInsertToDett.setJumpTo(cpDettaglio);
	
	 creaCmdPanelTabellaEntita(info, fpMultiPanelTab, fpCenter);
	 
	 return cmdPanel;
}

public static void  creaTabellaEntita(WizardInfo info, FormPanel fpMultiPanelTab ) throws IOException {
	 String nomeEntita = info.getNomeEntita();
	
	 VerticalFlowPanelLayout vfpl = guigenFactory.createVerticalFlowPanelLayout();
	 fpMultiPanelTab.setLayout(vfpl);
	 
	 WidgetsPanel wdgPanelElencoEntita = guigenFactory.createWidgetsPanel();
	 wdgPanelElencoEntita.setName(WP+upperFirstLetter(ELENCO)+nomeEntita);
	 wdgPanelElencoEntita.setLabel(upperFirstLetter(ELENCO)+" "+nomeEntita);
	 
	 VerticalFlowPanelLayout vfplWdgElencoEntita = guigenFactory.createVerticalFlowPanelLayout();
	 wdgPanelElencoEntita.setLayout(vfplWdgElencoEntita);
	 
	 fpMultiPanelTab.getSubpanels().add(wdgPanelElencoEntita);
	
	 Table tabella = guigenFactory.createTable();
	 tabella.setName(TAB+nomeEntita);
	 Type tipoPk = getTypeByAttrNameField(info.getIdPrimaryKey(), info);
	
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
	
	 String[] colTabScelte = getListaCampi();//costituita da tipi semplici
	 Column col = null;
	 SimpleType tipoAttr = null; 
	 for (int i = 0; i < colTabScelte.length; i++) {
			tipoAttr = (SimpleType)getTypeByAttrName(colTabScelte[i], info);
			col = guigenFactory.createColumn();
			col.setSelector(colTabScelte[i]);
			col.setLabel(splitCamelCase(colTabScelte[i]));
			columnModel.getColumns().add(col);
	 }
	 tabella.setColumnModel(columnModel);
}

public static FormPanel creaFormMPTab(WizardInfo info) {
	FormPanel fpMultiPanelTab = guigenFactory.createFormPanel();
	fpMultiPanelTab.setName(FORM_PANEL+upperFirstLetter(ELENCO)+info.getNomeEntita());
	return fpMultiPanelTab;
}

public MultiPanel creaMultiPanelRicerca(WizardInfo info) {
	//4-12-2012 if(multiPanelRicerca == null ) {
	//	multiPanelRicerca = guigenFactory.createMultiPanel();
		multiPanelRicerca.setName(MULTI_PANEL+upperFirstLetter(ELENCO)+info.getNomeEntita());
		multiPanelRicerca.setLabel(ELENCO+info.getNomeEntita());
	//4-12-2012}
	return multiPanelRicerca;
}

public static void creaCmdPanelTabellaEntita( WizardInfo info, FormPanel fpMultiPanelTab, FormPanel fpCenter) {
	 String nomeEntita = info.getNomeEntita();
	 
	 Type tipoPk = getTypeByAttrNameField(info.getIdPrimaryKey(), info);
	
	 CommandPanel cmdPanelTabEntita = guigenFactory.createCommandPanel();
	 cmdPanelTabEntita.setName("cmdTab"+nomeEntita);
	 
	 HorizontalFlowPanelLayout hfplTabEntita = guigenFactory.createHorizontalFlowPanelLayout();
	 cmdPanelTabEntita.setLayout(hfplTabEntita);
	 
	 fpMultiPanelTab.getSubpanels().add(cmdPanelTabEntita);// AGGANCIO IL CmdPanel AL FormPanel
	 
	 Button btEliminaEntita = guigenFactory.createButton();
	 btEliminaEntita.setName("bt"+ELIMINA+nomeEntita);
	 btEliminaEntita.setLabel(ELIMINA);
	 
	 btEliminaEntita.setFunctionSpecifier(CommandFunctions.DELETE_ITEM);
	 
	 EventHandler evHandlerElimina = guigenFactory.createEventHandler();
	 evHandlerElimina.setEventType(EventTypes.CLICKED);
	 btEliminaEntita.getEventHandlers().add(evHandlerElimina);
	 
	 ExecCommand execCommandElimina = guigenFactory.createExecCommand();
	 execCommandElimina.setMethodName(lowerFirstLetter(ELIMINA)+info.getNomeEntita());
	 
	 InlineCodeSnippet inlineCSElim = guigenFactory.createInlineCodeSnippet();
	 inlineCSElim.setLang("JAVA");
	 inlineCSElim.setSnippetName("body");
	 inlineCSElim.setSnippetPosition("executedMethod");
	 inlineCSElim.setSnippetCode(new CRUDSnippets().eliminaEntitySnippet(nomeEntita, appDataPK.getName(), appDataElencoEntita.getName(), tipoPk.getName()).toString());
	 execCommandElimina.getInlineCodeSnippets().add(inlineCSElim);
	 
	 CommandOutcome cmdOutOkElimina = guigenFactory.createCommandOutcome();
	 cmdOutOkElimina.setResultCode(RESULT_CODE);
	
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
	 refreshVCmdKoElimina.getTargetPanels().add(fpCenter.getSubpanels().get(0)); 
	 
	 cmdPanelTabEntita.getWidgets().add(btEliminaEntita);
	 
	 Button btDettEntita = guigenFactory.createButton();
	 btDettEntita.setName(BT+DETTAGLIO+nomeEntita);
	 btDettEntita.setLabel(upperFirstLetter(DETTAGLIO)+nomeEntita);
	 
	 btDettEntita.setFunctionSpecifier(CommandFunctions.DETAIL);
	 
	 cmdPanelTabEntita.getWidgets().add(btDettEntita);
	 
	 EventHandler evHandlerDett = guigenFactory.createEventHandler();
	 evHandlerDett.setEventType(EventTypes.CLICKED);
	
	 btDettEntita.getEventHandlers().add(evHandlerDett);
	 
	 ExecCommand execCommandDett = guigenFactory.createExecCommand();
	 execCommandDett.setMethodName("vaiDett"+info.getNomeEntita());
	 
	 InlineCodeSnippet inlineCS = guigenFactory.createInlineCodeSnippet();
	 inlineCS.setLang("JAVA");
	 inlineCS.setSnippetName("body");
	 inlineCS.setSnippetPosition("executedMethod");
	 inlineCS.setSnippetCode(new CRUDSnippets().dettaglioEntitySnippet(nomeEntita, appDataPK.getName(),appDataMethod.getName(), appDataDett.getName(), tipoPk.getName()).toString());
	 
	 execCommandDett.getInlineCodeSnippets().add(inlineCS);
		
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
	 refreshVCDett.getTargetPanels().add(fpCenter.getSubpanels().get(0)); 
}

public static Button creaBottone(WizardInfo info, String nomeBottone, CommandPanel cmdPanel,  CommandFunctions cmdFunction)  {
	 Button btRicerca = guigenFactory.createButton();
	 btRicerca.setName(BT+nomeBottone);
	 btRicerca.setLabel(nomeBottone);
	
	 btRicerca.setFunctionSpecifier(cmdFunction);
	 cmdPanel.getWidgets().add(btRicerca);
	 
	 return btRicerca;
}

public static EventHandler creaEventoHandlerBottone(Button button) {
	EventHandler evHandler= guigenFactory.createEventHandler();
	evHandler.setEventType(EventTypes.CLICKED);
	
	button.getEventHandlers().add(evHandler);
	 
	return  evHandler;
}

public static EventHandler findEvent(Widget w, EventTypes typeEvent) {
	EventHandler res = null;
	Iterator<EventHandler> eventIter = w.getEventHandlers().iterator();
	while(eventIter.hasNext()){
		EventHandler eh = eventIter.next();
		if(eh.getEventType().equals(typeEvent)) {
			res = eh;
			break;
		}
	}
	return res;	
}

}
