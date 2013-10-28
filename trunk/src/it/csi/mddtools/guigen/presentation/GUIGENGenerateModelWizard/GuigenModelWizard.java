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
package it.csi.mddtools.guigen.presentation.GUIGENGenerateModelWizard;


import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.presentation.GuigenEditorPlugin;
import it.csi.mddtools.guigen.provider.GuigenEditPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;


/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GuigenModelWizard extends Wizard implements INewWizard {
	
	/**
	 * The supported extensions for created files.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<String> FILE_EXTENSIONS =
		Collections.unmodifiableList(Arrays.asList(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameExtensions").split("\\s*,\\s*")));

	/**
	 * A formatted list of supported file extensions, suitable for display.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String FORMATTED_FILE_EXTENSIONS =
		GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");

	/**
	 * This caches an instance of the model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuigenPackage guigenPackage = GuigenPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuigenFactory guigenFactory = guigenPackage.getGuigenFactory();

	
	/**
	 * This is the file creation page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuigenModelWizardNewFileCreationPage newFileCreationPage;
	
	
	/**
	 * This is the initial object creation page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected GuigenModelWizardInitialObjectCreationPage initialObjectCreationPage;
	
	/**
	 * Pagina scelta ModelloPrincipale
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected GuiModelFilesLocChooserWizardPage guiModelFilesPage;
	
	/**
	 * Remember the selection during initialization for populating the default container.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * Remember the workbench during initialization.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IWorkbench workbench;

	/**
	 * Caches the names of the types that can be created as the root object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected List<String> initialObjectNames;

	/**
	 * This just records the information.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		// TITOLO E IMMAGINE DEL WIZARD
		setWindowTitle(GuigenEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(GuigenEditorPlugin.INSTANCE.getImage("full/wizban/NewGuigen")));
	}

	/**
	 * Returns the names of the types that can be created as the root object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected Collection<String> getInitialObjectNames() {
		if (initialObjectNames == null) {
			initialObjectNames = new ArrayList<String>();
			for (EClassifier eClassifier : guigenPackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass)eClassifier;
					if (!eClass.isAbstract()&& canCreate(eClass)) {
						initialObjectNames.add(eClass.getName());
					}
				}
			}
			Collections.sort(initialObjectNames, CommonPlugin.INSTANCE.getComparator());
		}
		return initialObjectNames;
	}

	protected boolean canCreate(EClass cl){
		if (
				cl.getName().equals("AppModule") ||
				cl.getName().equals("TypeNamespace") ||
				cl.getName().equals("SecurityModel") ||
				cl.getName().equals("AppDataGroup")||				
				cl.getName().equals("PanelDef") ||
				cl.getName().equals("WAYFProfile")||
				cl.getName().equals("SecurityProfile")||
				cl.getName().equals("PortalProfile"))
			return true;
		else
			return false;
	}
	/**
	 * Create a new model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected EObject createInitialModel() {
		EClass eClass = (EClass)guigenPackage.getEClassifier(initialObjectCreationPage.getInitialObjectName());
		EObject rootObject = guigenFactory.create(eClass);	
		return rootObject;
	}

	/**
	 * Do the work after everything is specified.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean performFinish() {
		try {
			// Remember the file.
			//
			final IFile modelFile = getModelFile();

			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation =
				new WorkspaceModifyOperation() {
					@Override
					protected void execute(IProgressMonitor progressMonitor) {
						try {
							// Create a resource set
							ResourceSet resourceSet = new ResourceSetImpl();

							// Get the URI of the model file.
							URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

							// Create a resource for this file.
							Resource resource = resourceSet.createResource(fileURI);
							
							
							// Add the initial model object to the contents.
							EObject rootObject = createInitialModel();
							if (rootObject != null) {
								resource.getContents().add(rootObject);
							}

							// Save the contents of the resource to the file system.
							Map<Object, Object> options = new HashMap<Object, Object>();
							options.put(XMLResource.OPTION_ENCODING, initialObjectCreationPage.getEncoding());
							

							////// Load modello principale

							if(!guiModelFilesPage.isSaltaWizard()){
								String modPrincFilePath = guiModelFilesPage.getGuiModelFilePath().toString();
									
								URI modPrincFileURI = URI.createPlatformResourceURI(modPrincFilePath, true);
								Resource modPrincResource = resourceSet.createResource(modPrincFileURI);
								modPrincResource.load(options);
								EList emfModPrincContent = (EList)modPrincResource.getContents();
								GUIModel modPrincModule = (GUIModel)(emfModPrincContent.get(0));
									
								if (rootObject instanceof AppModule){
									AppModule appmodule = (AppModule)rootObject;
									appmodule.setName(initialObjectCreationPage.getModelNameText());
									modPrincModule.getStructure().getAppWindow().getAppArea().getExtModules().add(appmodule);
									appmodule.setExtSecurityModel(modPrincModule.getExtSecurityModel());
									modPrincResource.save(options);
								}
									
								else if (rootObject instanceof TypeNamespace){
									TypeNamespace typeNamespace = (TypeNamespace)rootObject;		
									typeNamespace.setName(initialObjectCreationPage.getModelNameText());
									modPrincModule.getTypedefs().getExtNamespaces().add(typeNamespace);
									modPrincResource.save(options);
								}
									
								else if (rootObject instanceof AppDataGroup){
									AppDataGroup appDataGroup = (AppDataGroup)rootObject;	
									appDataGroup.setName(initialObjectCreationPage.getModelNameText());
									modPrincModule.getAppDataDefs().getExtGroups().add(appDataGroup);
									modPrincResource.save(options);
								}
							}
							resource.save(options);
						}
						catch (Exception exception) {
							GuigenEditorPlugin.INSTANCE.log(exception);
						}
						finally {
							progressMonitor.done();
						}
					}
				};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(modelFile);
				getShell().getDisplay().asyncExec
					(new Runnable() {
						 public void run() {
							 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
						 }
					 });
			}

			// Open an editor on the new file.
			try {
				page.openEditor
					(new FileEditorInput(modelFile),
					 workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
			}
			catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), GuigenEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
				return false;
			}

			return true;
		}
		catch (Exception exception) {
			GuigenEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/**
	 * This is the one page of the wizard.
	 * PAGINA WIZARD PER IL SALVATAGGIO DEL NOME
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public class GuigenModelWizardNewFileCreationPage extends WizardNewFileCreationPage {
		/**
		 * Pass in the selection.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public GuigenModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection) {
			super(pageId, selection);
		}

		/**
		 * The framework calls this to see if the file is correct.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@Override
		protected boolean validatePage() {
			if (super.validatePage()) {
				String extension = new Path(getFileName()).getFileExtension();
				if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
					String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
					setErrorMessage(GuigenEditorPlugin.INSTANCE.getString(key, new Object [] { FORMATTED_FILE_EXTENSIONS }));
					return false;
				}
				return true;
			}
			return false;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public IFile getModelFile() {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
		}
	}


	/**
	 * This is the page where the type of object to create is selected.
	 * PAGINA WIZARD PER LA SCELTA DEL TIPO DI MODELLO DA CREARE
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public class GuigenModelWizardInitialObjectCreationPage extends WizardPage {
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo initialObjectField;

		/**
		 * @generated
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 */
		protected List<String> encodings;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo encodingField;

		
		private Text modelNameText;

		private Label labelModelName;
		public String getModelNameText() {
			return modelNameText!=null && modelNameText.getText()!=null ? modelNameText.getText() : "";
		}

		/**
		 * Pass in the selection.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public GuigenModelWizardInitialObjectCreationPage(String pageId) {
			super(pageId);
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE); {
				GridLayout layout = new GridLayout();
				layout.numColumns = 1;
				layout.verticalSpacing = 12;
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				composite.setLayoutData(data);
			}

			Label containerLabel = new Label(composite, SWT.LEFT);
			{
				containerLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				containerLabel.setLayoutData(data);
			}

			
			initialObjectField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				initialObjectField.setLayoutData(data);
			}

			//DEFINIZIONE LISTA MODELLI
			for (String objectName : getInitialObjectNames()) {
				initialObjectField.add(getLabel(objectName));
			}

			if (initialObjectField.getItemCount() == 1) {
				initialObjectField.select(0);
			}
			initialObjectField.addModifyListener(validator);

			Label encodingLabel = new Label(composite, SWT.LEFT);
			{
				encodingLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				encodingLabel.setLayoutData(data);
			}
			encodingField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				encodingField.setLayoutData(data);
				
			}

			for (String encoding : getEncodings()) {
				encodingField.add(encoding);
			}

			encodingField.select(0);
			encodingField.addModifyListener(validator);
			
			labelModelName = new Label(composite, SWT.LEFT);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				labelModelName.setLayoutData(data);
				labelModelName.setVisible(false);
			}
			
			modelNameText = new Text(composite, SWT.BORDER | SWT.SINGLE); 
			{
				GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
				modelNameText.setLayoutData(gd2);	
				modelNameText.setVisible(false);
				modelNameText.addModifyListener(validator);
			}
			setPageComplete(validatePage());
			setControl(composite);
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated NOT
		 */
		protected ModifyListener validator =
			new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					boolean enabledWizard = getEnabledGuiModelFilesLocChooserWizardPage(getInitialObjectName());
					guiModelFilesPage.setEnabled(enabledWizard);
					labelModelName.setText("Nome "+initialObjectField.getText());
					labelModelName.setVisible(true);
					modelNameText.setVisible(true);
					if(modelNameText.getText().equalsIgnoreCase("")){
						setPageComplete(false);
						setMessage("Inserire un nome "+initialObjectField.getText());
					}
				}
			};
			
			/**
			 * Salta pagina associazione modello principale
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			@Override
			public IWizardPage getNextPage() {
				IWizardPage wiz = super.getNextPage();
			
				if(wiz instanceof GuiModelFilesLocChooserWizardPage){
					
					boolean flgSaltaWizard = getEnabledGuiModelFilesLocChooserWizardPage(getInitialObjectName());
					
					if(!flgSaltaWizard){
						((GuiModelFilesLocChooserWizardPage)wiz).setSaltaWizard(true);
						((GuiModelFilesLocChooserWizardPage)wiz).setPageComplete(true);						
						wiz = ((GuiModelFilesLocChooserWizardPage)wiz).getNextPage();			
						
					}
				}
				
				return wiz;
					
			}			
			
		private boolean getEnabledGuiModelFilesLocChooserWizardPage(String value){
			if( value != null && 
				(value.equals("PanelDef") ||
				value.equals("WAYFProfile")||
				value.equals("SecurityProfile")||
				value.equals("PortalProfile")))
				return false;
				
		return true;
				
		}
		
		private void updateStatus(String message) {
		
				setErrorMessage(message);
				setPageComplete(message == null);
			
		}
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected boolean validatePage() {
			return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible) {
				if (initialObjectField.getItemCount() == 1) {
					initialObjectField.clearSelection();
					encodingField.setFocus();
				}
				else {
					encodingField.clearSelection();
					initialObjectField.setFocus();
				}
			}
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public String getInitialObjectName() {
			String label = initialObjectField.getText();

			for (String name : getInitialObjectNames()) {
				if (getLabel(name).equals(label)) {
					return name;
				}
			}
			return null;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public String getEncoding() {
			return encodingField.getText();
		}

		/**
		 * Returns the label for the specified type name.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected String getLabel(String typeName) {
			try {
				return GuigenEditPlugin.INSTANCE.getString("_UI_" + typeName + "_type");
			}
			catch(MissingResourceException mre) {
				GuigenEditorPlugin.INSTANCE.log(mre);
			}
			return typeName;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected Collection<String> getEncodings() {
			if (encodings == null) {
				encodings = new ArrayList<String>();
				for (StringTokenizer stringTokenizer = new StringTokenizer(GuigenEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); ) {
					encodings.add(stringTokenizer.nextToken());
				}
			}
			return encodings;
		}
	}


	/**
	 * The framework calls this to create the contents of the wizard.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
		@Override
	public void addPages() {
			
		String modPrincFilePathTmp = "";
			
		//PAGINA Nome Modello
			
		// Create a page, set the title, and the initial model file name.
		newFileCreationPage = new GuigenModelWizardNewFileCreationPage("Whatever", selection);
		newFileCreationPage.setTitle(GuigenEditorPlugin.INSTANCE.getString("_UI_GUIGENGenerateModelWizard_label"));
		newFileCreationPage.setDescription(GuigenEditorPlugin.INSTANCE.getString("_UI_GUIGENGenerateModelWizard_description"));
		newFileCreationPage.setFileName(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				IResource selectedResource = (IResource)selectedElement;
				modPrincFilePathTmp = selectedResource.getFullPath().toPortableString();
				if (selectedResource.getType() == IResource.FILE) {
					
					
					modPrincFilePathTmp =selectedResource.getFullPath().toPortableString();					
					selectedResource = selectedResource.getParent();
					URI modPrincFileURI = URI.createPlatformResourceURI(modPrincFilePathTmp, true);
					ResourceSet resourceSet = new ResourceSetImpl();
					Resource modPrincResource = resourceSet.createResource(modPrincFileURI);
					Map<Object, Object> options = new HashMap<Object, Object>();
							
					try {
						modPrincResource.load(options);
						EList emfModPrincContent = (EList)modPrincResource.getContents();
						if ( !((emfModPrincContent.get(0)) instanceof GUIModel))
							modPrincFilePathTmp = selectedResource.getFullPath().toPortableString();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}

				// This gives us a directory...
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					String defaultModelBaseFilename = GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameDefaultBase");
					String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
					}
					newFileCreationPage.setFileName(modelFilename);
					
				}
			}
		}
		
		//PAGINA Scelta Tipo Modello
		initialObjectCreationPage = new GuigenModelWizardInitialObjectCreationPage("Whatever2");
		initialObjectCreationPage.setTitle(GuigenEditorPlugin.INSTANCE.getString("_UI_GUIGENGenerateModelWizard_label"));
		initialObjectCreationPage.setDescription(GuigenEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
		addPage(initialObjectCreationPage);
		
		//PAGINA Referenziazione Modello Principale
		guiModelFilesPage = new GuiModelFilesLocChooserWizardPage(selection);
		guiModelFilesPage.setGuiModelFilePath(modPrincFilePathTmp);
		
		addPage(guiModelFilesPage);
		
	}

	/**
	 * Get the file from the page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IFile getModelFile() {
		return newFileCreationPage.getModelFile();
	}

}
