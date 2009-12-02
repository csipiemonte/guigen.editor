package it.csi.mddtools.guigen.presentation.GUIModelNormalizerWizard;

import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.TypeNamespace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
	 * Salva tutte le nuove risorse più la risorsa del modello (che è sicuramente stata modificata)
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
		// TODO Auto-generated method stub
		
	}

	private void externalizeAppdatas()  throws CoreException{
		// TODO Auto-generated method stub
		
	}

	private void externalizeTypes()  throws CoreException{
		
		
	}

	private void externalizeSecurityModel()  throws CoreException{
		// TODO Auto-generated method stub
		
	}

	private void includeStdLib()  throws CoreException{
		guimodel.getTypedefs().getExtNamespaces().add(commonTNS);
		guimodel.getAppDataDefs().getExtGroups().add(commonAppdata);
		
	}

	/**
	 * Carica le risorse iniziali (model, common tns, common appdata)
	 */
	private void initExistingResources() throws CoreException{
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
	}
	
}
