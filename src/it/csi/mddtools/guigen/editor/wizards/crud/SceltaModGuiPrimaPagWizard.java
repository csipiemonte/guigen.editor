package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.ComplexType;
import it.csi.mddtools.guigen.ContentPanel;
import it.csi.mddtools.guigen.Field;
import it.csi.mddtools.guigen.FormPanel;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.SimpleType;
import it.csi.mddtools.guigen.Type;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.Typedefs;
import it.csi.mddtools.guigen.impl.ComplexTypeImpl;
import it.csi.mddtools.guigen.impl.SimpleTypeImpl;
import it.csi.mddtools.guigen.presentation.GUIGENGenerateModelWizard.GuigenModelWizard.GuigenModelWizardInitialObjectCreationPage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.internal.ide.misc.ResourceAndContainerGroup;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SceltaModGuiPrimaPagWizard extends WizardPage {
	
	private NewEntityCRUDWizard wizard;
	
	private Text containerText;

	private Text fileText;

	private ISelection selection;
	
	

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
	 * This is the initial object creation page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected GuigenModelWizardInitialObjectCreationPage initialObjectCreationPage;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SceltaModGuiPrimaPagWizard(ISelection selection, NewEntityCRUDWizard wizard) {
		super("wizardPage");
		this.wizard = wizard;
		setTitle("Guigen Model (modello principale Gui Model)");
		setDescription("Questo wizard ti guider� nella creazione del CRUD di un'entit�. Comicia con il selezionare il Gui Model della tua applicazione");
		this.selection = selection;
		
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 30;
		
		Label label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();//viene chiamata dopo aver selezionato il file nelle risorse  
			}
		});
		

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		//add 10-10
		
		dialogChanged();
//				setErrorMessage(null);
//				setPageComplete(false);
		
		setControl(container);
		
	}

	

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */



	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	
	private void handleBrowse() {
		File resulta = null;
		IPath pathFile = null;
		IContainer  dirPadreModPrinc = null;
		ResourceSelectionDialog dialog = new ResourceSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				"Seleziona il file del modello pricipale");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			 resulta = ((File) result[0]);//resulta.toString() L/wizardcrudmdd/src/model/wizardcrud/wizardcrud.guigen
			 pathFile = resulta.getFullPath();//pathFile:/wizardcrudmdd/src/model/wizardcrud/wizardcrud.guigen
			 dirPadreModPrinc = resulta.getParent();//getParent:" +resulta.getParent().toString().substring(1)); /wizardcrudmdd/src/model/wizardcrud
			if (result.length == 1) {
				fileText.setText(pathFile.toString());
				WizardHelper.setFile(resulta);
				//WizardHelper.setPahtFile(dirPadreModPrinc.toString().substring(1));
			}
		}
		
	}
	

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		
		String fileName = getFileName();
		
		//stamane 10-10
		if(fileName == null || fileName.equals("") ) {
			updateStatus("Selezionare un file di tipo GuiModel ");
			return;
		}
	
		if(!validatePage(fileName)){
			updateStatus("Selezionare un modello di tipo GuiModel");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}
	protected boolean validatePage(String filename) {
		boolean res = false;
			try {
				
//				EObject emfMod= WizardHelper.getResource(filename);
//				
//				if (emfMod instanceof GUIModel) {
//					this.setGuiModel((GUIModel)emfMod);
//					WizardHelper.setGuiModel(guiModel);//8-10-2012
//					res = true;
//				}
				res = WizardHelper.caricaModPrinc(filename);
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		
		return res;
		
	}
	
	
	/**
	 * Pass in the selection.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public IWizardPage getNextPage() {
		IWizardPage wizPage = super.getNextPage();
		

		if(this.isPageComplete()) {
		
			GUIModel modello = WizardHelper.getGuiModel();
		
			if(modello != null) {	
				
				 if(wizPage instanceof SceltaEntitaSecPagWizard) {
					 
					Combo comboEntity = ((SceltaEntitaSecPagWizard)wizPage).getComboEntity();
					
					int numEleCombo = comboEntity.getItems().length;
					
					if(numEleCombo == 0 ) {
						
						String[] listaNomiComplexType = WizardHelper.getNameComplexType(modello,wizard.getInfo());
						
						comboEntity.setItems(listaNomiComplexType);
					}
					
				}
			}
		}
	
		
		return wizPage;
			
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

	
	
	
	
	
}