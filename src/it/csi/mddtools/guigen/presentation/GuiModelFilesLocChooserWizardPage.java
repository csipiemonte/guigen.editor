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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.csi.mddtools.guigen.GUIModel;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (guigen).
 */

public class GuiModelFilesLocChooserWizardPage extends WizardPage {
	private Text guiModelFilesContainerText;
	
	private Button guiModelButtonBrowse;

//	private Path guiModelFilesFolder;
	
	private ISelection selection;
	
	private static String TITLE_WIZARD = "Selezione GuiModel";
	private static String DESCRIPTION_WIZARD = "Selezionare il modello principale";
	private static String LABEL_WIZARD = "GuiModel";
	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public GuiModelFilesLocChooserWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle(TITLE_WIZARD);
		setDescription(DESCRIPTION_WIZARD);
		this.selection = selection;
	}

	
	public void setEnabled(boolean b) {
		this.guiModelFilesContainerText.setEnabled(b);
		this.guiModelButtonBrowse.setEnabled(b);
	}
	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NULL);
		
		// DEFINIZIONE LAYOUT PAGINA WIZARD
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;	
		
		//DEFINZIONE LABEL BOX SELEZIONE CARTELLA SECURITY
		Label label = new Label(container, SWT.NULL);
		label.setText(LABEL_WIZARD);
		
		//DEFINZIONE BOX SELEZIONE CARTELLA SECURITY		
		guiModelFilesContainerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		guiModelFilesContainerText.setLayoutData(gd);		
		guiModelFilesContainerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogCommonContainerChanged();
			}
		});

		//DEFINIZIONE PULSANTE BROWSE FOLDER
		guiModelButtonBrowse = new Button(container, SWT.PUSH);
		guiModelButtonBrowse.setText("Browse...");
		guiModelButtonBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowseCommonContainer();
			}
		});
		
		initialize();
		dialogCommonContainerChanged();
		
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				guiModelFilesContainerText.setText(container.getFullPath().toString());
			}
		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowseCommonContainer() {
		
    
        ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "Scegliere file del modello");
		if (dialog.open() == ResourceSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length >0 && result[0] instanceof IFile) {
				String modelFileSelected = ((IFile) result[0]).getFullPath().toString(); 
				guiModelFilesContainerText.setText(modelFileSelected);
			}
		}
	}

	
	
	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogCommonContainerChanged() {
		
		IResource _guiModelRes = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getGuiModelFilePath()));

		if (getGuiModelFilePath().length() == 0) {
			updateStatus("Specificare il path del GuiModel");
			return;
		}
		
		if (!(_guiModelRes.getType() == IResource.FILE)){
			updateStatus("Occorre selezionare almeno un file");
			return;	
		}
		
		if (_guiModelRes == null){
			updateStatus("Il modello principale deve esistere");
			return;	
		}
		
		if (!_guiModelRes.isAccessible()) {
			updateStatus("Il progetto deve essere writable");
			return;
		}
		
		updateStatus(null);
	}
	
	private void updateStatus(String message) {
		if(guiModelFilesContainerText.isEnabled()){
			setErrorMessage(message);
			setPageComplete(message == null && validatePage());
		}
		else{
			setErrorMessage("");
			setPageComplete(true);
		}
	}
	
	
	//TODO : RIVEDERE
	/**
	 * The framework calls this to see if the file is correct.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected boolean validatePage() {
		String modPrincFilePath = this.getGuiModelFilePath().toString();
		URI modPrincFileURI = URI.createPlatformResourceURI(modPrincFilePath, true);
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource modPrincResource = resourceSet.createResource(modPrincFileURI);
		Map<Object, Object> options = new HashMap<Object, Object>();
//		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		try {
			modPrincResource.load(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EList emfModPrincContent = (EList)modPrincResource.getContents();
		if ( (emfModPrincContent.get(0)) instanceof GUIModel)
			return true;
//		
		return false;
		
	}

	
	public String getGuiModelFilePath() {
		return guiModelFilesContainerText.getText();
	}

}