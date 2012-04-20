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
package it.csi.mddtools.guigen.presentation;


import it.csi.mddtools.guigen.AppDataGroup;
import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.AppWindow;
import it.csi.mddtools.guigen.ApplicationArea;
import it.csi.mddtools.guigen.ApplicationDataDefs;
import it.csi.mddtools.guigen.Footer;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GUIStructure;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.Header;
import it.csi.mddtools.guigen.SecurityModel;
import it.csi.mddtools.guigen.Statusbar;
import it.csi.mddtools.guigen.TargetPlatform;
import it.csi.mddtools.guigen.TargetPlatformCodes;
import it.csi.mddtools.guigen.Titlebar;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.Typedefs;
import it.csi.mddtools.guigen.provider.GuigenEditPlugin;

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
	 * Dati configurazione portale e Target Platform
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	
	private GuiModelDatiAggiuntiviWizardPage datiAggiuntiviPage;
	
	

	/**
	 * This is the initial object creation page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	
	protected GuigenModelWizardAnaprodDataCreationPage anaprodDataCreationPage;
	

	/**
	 * Wizard commonFile
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */

	protected CommonFilesLocChooserWizardPage commonFilesPage;

	
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
		if (cl.getName().equals("GUIModel"))
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
		if (initialObjectCreationPage.getInitialObjectName().indexOf("GUIModel")!=-1){
			GUIModel model = (GUIModel)rootObject;
			// aggiunge dati identificativi
			model.setCodProdotto(anaprodDataCreationPage.getCodProdotto());
			model.setCodComponente(anaprodDataCreationPage.getCodComponente());
			model.setVersioneProdotto(anaprodDataCreationPage.getVerProdotto());
			model.setVersioneComponente(anaprodDataCreationPage.getVerComponente());
			// aggiunge struttura minimale
			GUIStructure structure = guigenFactory.createGUIStructure();
			model.setStructure(structure);
			AppWindow appwin = guigenFactory.createAppWindow();
			structure.setAppWindow(appwin);
			Header header = guigenFactory.createHeader();
			Footer footer = guigenFactory.createFooter();
			appwin.setHeader(header);
			appwin.setFooter(footer);
			ApplicationArea apparea = guigenFactory.createApplicationArea();
			appwin.setAppArea(apparea);
			Titlebar titlebar = guigenFactory.createTitlebar();
			apparea.setTitlebar(titlebar);
			Statusbar statusbar = guigenFactory.createStatusbar();
			apparea.setStatusbar(statusbar); 
			Typedefs baseTypesContainer = guigenFactory.createTypedefs();
			model.setTypedefs(baseTypesContainer);
			// inserisce il contenitore di appdata
			ApplicationDataDefs appDataDefs = guigenFactory.createApplicationDataDefs();
			model.setAppDataDefs(appDataDefs);
		}
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
							
							if(rootObject instanceof GUIModel){
								GUIModel guimodel = (GUIModel)rootObject;
								
								//// APPDATA common
								String commonAppdataPath= commonFilesPage.getCommonFilesFolder().toString();
								if (!commonAppdataPath.endsWith("/"))
									commonAppdataPath+="/";
								commonAppdataPath+="commonAppdata.guigen";
								
								URI appdataFileURI = URI.createPlatformResourceURI(commonAppdataPath, true);
								Resource commonAppdataResource = resourceSet.createResource(appdataFileURI);
								commonAppdataResource.load(options);
								EList emfContent = (EList)commonAppdataResource.getContents();
								AppDataGroup commonADG = (AppDataGroup)(emfContent.get(0));
								
								guimodel.getAppDataDefs().getExtGroups().add(commonADG);
							
								////// TNS common
								String commonTNSPath = commonFilesPage.getCommonFilesFolder().toString();
								if (!commonTNSPath.endsWith("/"))
									commonTNSPath+="/";
								commonTNSPath+="commonTNS.guigen";
								
								URI tnsFileURI = URI.createPlatformResourceURI(commonTNSPath, true);
								Resource commonTNSResource = resourceSet.createResource(tnsFileURI);
								commonTNSResource.load(options);
								emfContent = (EList)commonTNSResource.getContents();
								TypeNamespace commonTNS = (TypeNamespace)(emfContent.get(0));
								guimodel.getTypedefs().getExtNamespaces().add(commonTNS);
								
								//SECURITY MODEL
								URI secModelURI = URI.createPlatformResourceURI(modelFile.getFullPath().removeLastSegments(1).toString()+"/"+"securityModel.guigen", true);
								Resource securityModelRes = resourceSet.createResource(secModelURI);
								SecurityModel secmodel = GuigenFactory.eINSTANCE.createSecurityModel();
								guimodel.setExtSecurityModel(secmodel);
								securityModelRes.getContents().add(secmodel);
								securityModelRes.save(options);
								
								//HEADER DATI CONFIGURAZIONE PORTALE
								Header header = guimodel.getStructure().getAppWindow().getHeader();
								header.setCodApplicativo(datiAggiuntiviPage.getCodApplicativo());
								header.setCodCanale(datiAggiuntiviPage.getCodCanale());
								header.setLinkCanale(datiAggiuntiviPage.getLinkCanale());
								header.setNomeApplicativo(datiAggiuntiviPage.getNomeApplicativo());
								header.setNomeCanale(datiAggiuntiviPage.getNomeCanale());
								
								//TARGET PLATFORM
								guimodel.setTargetPlatform(guigenFactory.createTargetPlatform());								
								guimodel.getTargetPlatform().setCode(TargetPlatformCodes.getByName(datiAggiuntiviPage.getCodeServerCombo()));
								guimodel.getTargetPlatform().setEnableRichUIBehavior(datiAggiuntiviPage.getEnrichmentChecBox().getSelection());
								
								//MENUBAR								
								guimodel.getStructure().getAppWindow().getAppArea().setMenubar(guigenFactory.createMenubar());						
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
			//
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
			//
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
				
				}
			};

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
		 * This is the page where the type of object to create is selected.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated NOT
		 */
		public class GuigenModelWizardAnaprodDataCreationPage extends WizardPage {
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			protected org.eclipse.swt.widgets.Text codProdotto;
			
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			protected org.eclipse.swt.widgets.Text codComponente;
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			protected org.eclipse.swt.widgets.Text verProdotto;
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			protected org.eclipse.swt.widgets.Text verComponente;
		
			/**
			 * Pass in the selection.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public GuigenModelWizardAnaprodDataCreationPage(String pageId) {
				super(pageId);
			}
		
			
			public void setEnabled(boolean b) {
				this.codComponente.setEnabled(b);
				this.codProdotto.setEnabled(b);
				this.verComponente.setEnabled(b);
				this.verProdotto.setEnabled(b);
				if (!b){
					codComponente.setText("");
					codProdotto.setText("");
					verComponente.setText("");
					verProdotto.setText("");
				}
			}


			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public String getCodProdotto() {
				String txt = codProdotto.getText();
				if (txt==null || txt.length()==0)
					return null;
				else
					return txt;
			}
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public String getCodComponente() {
				String txt = codComponente.getText();
				if (txt==null || txt.length()==0)
					return null;
				else
					return txt;
			}
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public String getVerProdotto() {
				String txt = verProdotto.getText();
				if (txt==null || txt.length()==0)
					return null;
				else
					return txt;
			}
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public String getVerComponente() {
				String txt = verComponente.getText();
				if (txt==null || txt.length()==0)
					return null;
				else
					return txt;
			}
			
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			public void createControl(Composite parent) {
				Composite composite = new Composite(parent, SWT.NONE);
				{
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
		
				Label codProdottoLabel = new Label(composite, SWT.LEFT);
				{
					codProdottoLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_CodProdotto_label"));
		
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					codProdottoLabel.setLayoutData(data);
				}
		
				codProdotto = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
				{
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					data.grabExcessHorizontalSpace = true;
					codProdotto.setLayoutData(data);
					codProdotto.addModifyListener(validator);
				}
				
				Label verProdottoLabel = new Label(composite, SWT.LEFT);
				{
					verProdottoLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_VerProdotto_label"));
		
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					verProdottoLabel.setLayoutData(data);
				}
				
				verProdotto = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
				{
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					data.grabExcessHorizontalSpace = true;
					verProdotto.setLayoutData(data);
					verProdotto.addModifyListener(validator);
				}
				
				Label codComponenteLabel = new Label(composite, SWT.LEFT);
				{
					codComponenteLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_CodComponente_label"));
		
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					codComponenteLabel.setLayoutData(data);
				}
				
				codComponente = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
				{
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					data.grabExcessHorizontalSpace = true;
					codComponente.setLayoutData(data);
					codComponente.addModifyListener(validator);
				}
		
				Label verComponenteLabel = new Label(composite, SWT.LEFT);
				{
					verComponenteLabel.setText(GuigenEditorPlugin.INSTANCE.getString("_UI_VerComponente_label"));
		
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					verComponenteLabel.setLayoutData(data);
				}
				
				verComponente = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
				{
					GridData data = new GridData();
					data.horizontalAlignment = GridData.FILL;
					data.grabExcessHorizontalSpace = true;
					verComponente.setLayoutData(data);
					verComponente.addModifyListener(validator);
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
						
					}
				};
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			protected boolean validatePage() {
			if (codProdotto.isEnabled()) {
				return (getCodProdotto() != null && getCodComponente() != null
						&& getVerProdotto() != null && getVerComponente() != null);
			} else
				return true;
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
			@Override
			public void setVisible(boolean visible) {
				super.setVisible(visible);
				if (visible) {
	//				if (initialObjectField.getItemCount() == 1) {
	//					initialObjectField.clearSelection();
	//					encodingField.setFocus();
	//				}
	//				else {
	//					encodingField.clearSelection();
	//					initialObjectField.setFocus();
	//				}
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
	//		public String getInitialObjectName() {
	//			String label = initialObjectField.getText();
	//	
	//			for (String name : getInitialObjectNames()) {
	//				if (getLabel(name).equals(label)) {
	//					return name;
	//				}
	//			}
	//			return null;
	//		}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
			 */
	//		public String getEncoding() {
	//			return encodingField.getText();
	//		}
		
			/**
			 * Returns the label for the specified type name.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated NOT
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
			 * @generated NOT
			 */
	//		protected Collection<String> getEncodings() {
	//			if (encodings == null) {
	//				encodings = new ArrayList<String>();
	//				for (StringTokenizer stringTokenizer = new StringTokenizer(Servicegen_metamodelEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); ) {
	//					encodings.add(stringTokenizer.nextToken());
	//				}
	//			}
	//			return encodings;
	//		}
		}

	/**
	 * The framework calls this to create the contents of the wizard.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
		@Override
	public void addPages() {
			
		//PAGINA Nome Modello

		// Create a page, set the title, and the initial model file name.
		newFileCreationPage = new GuigenModelWizardNewFileCreationPage("Whatever", selection);
		newFileCreationPage.setTitle(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenModelWizard_label"));
		newFileCreationPage.setDescription(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenModelWizard_description"));
		newFileCreationPage.setFileName(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				IResource selectedResource = (IResource)selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
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
	
		//PAGINA Scelta Modello
		initialObjectCreationPage = new GuigenModelWizardInitialObjectCreationPage("Whatever2");
		initialObjectCreationPage.setTitle(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenModelWizard_label"));
		initialObjectCreationPage.setDescription(GuigenEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
		addPage(initialObjectCreationPage);
		
		//PAGINA Definizione anagrafica prodotto
		anaprodDataCreationPage = new GuigenModelWizardAnaprodDataCreationPage("anaprodData");
		anaprodDataCreationPage.setTitle("Dati identificazione del componente");
		anaprodDataCreationPage.setDescription("Inserire i dati di identificazione del componente risultante come da specifiche ANAPROD.\n"+
				"I dati sono significativi solo se si sta creando un oggetto GUIModel. Potranno comunque essere inseriti "+
				"successivamente nelle propriet� dell'oggetto GUIModel.");
		addPage(anaprodDataCreationPage);
		
		//PAGINA Definizione Dati Configurazione Portale e Target Platform
		datiAggiuntiviPage = new GuiModelDatiAggiuntiviWizardPage(selection);
		addPage(datiAggiuntiviPage);
		
		//PAGINA Definizione commonFile
		commonFilesPage = new CommonFilesLocChooserWizardPage(selection);
		commonFilesPage.setTitle("Cartella file comuni");
		commonFilesPage.setDescription("Selezionare la cartella contenente i file \"commonTNS.guigen\" e \"commonAppdata.guigen\"");
		addPage(commonFilesPage);
	
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
