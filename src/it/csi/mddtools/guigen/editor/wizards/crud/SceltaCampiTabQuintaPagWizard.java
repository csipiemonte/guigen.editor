package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.Type;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SceltaCampiTabQuintaPagWizard extends WizardPage {
	
	private NewEntityCRUDWizard wizard;
	
	private GUIModel guiModel;
	
	
	public GUIModel getGuiModel() {
		return guiModel;
	}

	public void setGuiModel(GUIModel guiModel) {
		this.guiModel = guiModel;
	}
	
	private ISelection selection;
	
	private List wdgListAttrEntitaTab;
	
	private List wdgListCampiTabEntita;
	
	
	public NewEntityCRUDWizard getWizard() {
		return wizard;
	}

	public void setWizard(NewEntityCRUDWizard wizard) {
		this.wizard = wizard;
	}

	public List getWdgListAttrEntitaTab() {
		return wdgListAttrEntitaTab;
	}

	public void setWdgListAttrEntitaTab(List wdgListAttrEntitaTab) {
		this.wdgListAttrEntitaTab = wdgListAttrEntitaTab;
	}

	public List getWdgListCampiTabEntita() {
		return wdgListCampiTabEntita;
	}

	public void setWdgListCampiTabEntita(List wdgListCampiTabEntita) {
		this.wdgListCampiTabEntita = wdgListCampiTabEntita;
	}

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SceltaCampiTabQuintaPagWizard(ISelection selection, NewEntityCRUDWizard wizard) {
		super("wizardPage");
		this.wizard = wizard;
		setTitle("Scelta delle COLONNE della tua TABELLA.");
		setDescription("Questa pagina ti permetterà di scegliere gli attributi della tua entità che andranno a costituire le colonne della tabella dei risultati.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		layout.verticalSpacing = 9;
		
		 (new Label(composite, SWT.NULL)).setText("Attributi dell'Entità:");
		 (new Label(composite, SWT.NULL)).setText("Campi della tabella:");
		    
		 wdgListAttrEntitaTab = new List(composite,SWT.MULTI | SWT.BORDER| SWT.V_SCROLL);
		    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		    wdgListAttrEntitaTab.setLayoutData(gd);
		    
		    wdgListCampiTabEntita = new List(composite, SWT.MULTI | SWT.BORDER| SWT.V_SCROLL);
		    wdgListCampiTabEntita.setLayoutData(gd);
		    
		    Button btAddAttr = new Button(composite, SWT.PUSH);
		    btAddAttr.setText("Add");
		    btAddAttr.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					 if(wdgListAttrEntitaTab.getSelectionIndices().length > 0) {
				          String[] listaAttrSel = wdgListAttrEntitaTab.getSelection();
				          String[]	a =null;
				         a =  WizardHelper.creaListaColonneTab(listaAttrSel);

				         wdgListCampiTabEntita.setItems(a);
				         if( a != null && a.length >0) {//10-10
				            	updateStatus(null);
				            }
				           
				    }
				}
			});
		    
			Button btRemoveAttr = new Button(composite, SWT.PUSH);
			btRemoveAttr.setText("Remove");
			btRemoveAttr.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					
					if(wdgListCampiTabEntita.getSelectionIndices().length > 0) {
						
						String[] b =null;
						String[] listaAttrDelete = wdgListCampiTabEntita.getSelection();
				         b = WizardHelper.rimuoviCampiTab(listaAttrDelete);
				          
				         wdgListCampiTabEntita.setItems(b);
					}
				}
			});
			dialogChanged();
		setControl(composite);
	}
	
	
	

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		
		if(WizardHelper.getListaUnicaTab() != null && WizardHelper.getListaUnicaTab().size() > 0) {
			updateStatus("ricordati di selezionare i campi della tua tabella");
		}
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	
	
}