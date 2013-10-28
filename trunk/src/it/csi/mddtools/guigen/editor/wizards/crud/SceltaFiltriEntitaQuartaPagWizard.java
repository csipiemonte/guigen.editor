package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.GUIModel;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.IWizardPage;
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

public class SceltaFiltriEntitaQuartaPagWizard extends WizardPage {
	
	private NewEntityCRUDWizard wizard;
	private GUIModel guiModel;
	
	public GUIModel getGuiModel() {
		return guiModel;
	}

	public void setGuiModel(GUIModel guiModel) {
		this.guiModel = guiModel;
	}
	
	private ISelection selection;
	private List wdgListAttrEntita;
	private List wdgListFiltriEntita;
	
	public List getWdgListAttrEntita() {
		return wdgListAttrEntita;
	}

	public void setWdgListAttrEntita(List wdgListAttrEntita) {
		this.wdgListAttrEntita = wdgListAttrEntita;
	}

	public List getWdgListFiltriEntita() {
		return wdgListFiltriEntita;
	}

	public void setWdgListFiltriEntita(List wdgListFiltriEntita) {
		this.wdgListFiltriEntita = wdgListFiltriEntita;
	}

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SceltaFiltriEntitaQuartaPagWizard(ISelection selection, NewEntityCRUDWizard wizard) {
		super("wizardPage");
		this.wizard = wizard;
		setTitle("Scelta Gestione Filtri dell'entità");
		setDescription("Questa pagina ti permetterà di scegliere i filtri della tua ricerca. Potrai sceglierli dalla prima lista che ti viene mostrata selezionandoli in modo singolo o multiplo. ");
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
		 (new Label(composite, SWT.NULL)).setText("Filtri selezionati:");
		    
		    wdgListAttrEntita = new List(composite,SWT.MULTI | SWT.BORDER| SWT.V_SCROLL);
		    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		    wdgListAttrEntita.setLayoutData(gd);
		    
		    wdgListFiltriEntita = new List(composite, SWT.MULTI | SWT.BORDER| SWT.V_SCROLL);
		    wdgListFiltriEntita.setLayoutData(gd);
		    
		    Button btAddAttr = new Button(composite, SWT.PUSH);
		    btAddAttr.setText("Add");
		    btAddAttr.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					 if(wdgListAttrEntita.getSelectionIndices().length > 0) {
				          String[] listaAttrSel = wdgListAttrEntita.getSelection();
				          String[] listaFiltri = null;
				          listaFiltri = WizardHelper.creaListaFiltri(listaAttrSel);

				            wdgListFiltriEntita.setItems(listaFiltri);
				            if( listaFiltri != null && listaFiltri.length >0) {
				            	updateStatus(null);
				            }
				    }
				}
			});
		    
			Button btRemoveAttr = new Button(composite, SWT.PUSH);
			btRemoveAttr.setText("Remove");
			btRemoveAttr.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if(wdgListFiltriEntita.getSelectionIndices().length > 0) {
						String[] temp = null; 
						String[] listaAttrDelete = wdgListFiltriEntita.getSelection();
				        temp = WizardHelper.rimuoviCampiFiltri(listaAttrDelete);
				        wdgListFiltriEntita.setItems(temp);
					}
				}
			});
		setPageComplete(false); 
		setControl(composite);
	}
	
	private void dialogChanged() {
		if(WizardHelper.getListaUnica() != null && WizardHelper.getListaUnica().size() > 0) {
				updateStatus("ricordati di selezionare i campi dei tuoi filtri");
			}
		}
	
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	@Override
	public IWizardPage getNextPage() {
		IWizardPage wiz = super.getNextPage();
		if(this.isPageComplete()) {
			 if(wiz instanceof SceltaCampiTabQuintaPagWizard) {
				 	List wdgListAttrEntitaTab = ((SceltaCampiTabQuintaPagWizard)wiz).getWdgListAttrEntitaTab();
				 	List wdgListCampiTabEntita = ((SceltaCampiTabQuintaPagWizard)wiz).getWdgListCampiTabEntita();
					String[] items = WizardHelper.getVettoreByList(wizard.getInfo().getListaAttrEntita());
					wdgListAttrEntitaTab.setItems(items);
					if(WizardHelper.getListaUnicaTab().isEmpty()) {
						wdgListCampiTabEntita.removeAll();
					}
					wizard.getInfo().setCampiFiltri(WizardHelper.getListaFiltri());
			 } 
		}
		return wiz;	
	}

	
}