package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.GUIModel;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

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
				         String[]	listaColTab = null;
				         listaColTab =  WizardHelper.creaListaColonneTab(listaAttrSel);
				         wdgListCampiTabEntita.setItems(listaColTab);
				         if(listaColTab != null && listaColTab.length >0) {
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
						String[] campiTab = null;
						String[] listaAttrDelete = wdgListCampiTabEntita.getSelection();
						campiTab = WizardHelper.rimuoviCampiTab(listaAttrDelete);
				         wdgListCampiTabEntita.setItems(campiTab);
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