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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SceltaEntitaSecPagWizard extends WizardPage {
	
	private NewEntityCRUDWizard wizard;
	
	private Combo comboEntity;

	public Combo getComboEntity() {
		return comboEntity;
	}

	public void setComboEntity(Combo comboEntity) {
		this.comboEntity = comboEntity;
	}

	private ISelection selection;
	
	private GUIModel guiModel;
	
	
	public GUIModel getGuiModel() {
		return guiModel;
	}

	public void setGuiModel(GUIModel guiModel) {
		this.guiModel = guiModel;
	}
	
	/**
	 * Constructor for SceltaEntitaSecPagWizard.
	 * 
	 * @param pageName
	 */
	public SceltaEntitaSecPagWizard(ISelection selection, NewEntityCRUDWizard wizard) {
		super("wizardPage");
		this.wizard = wizard;
		setTitle("Scelta Gestione Entità");
		setDescription("Scegli l'entità per la quale vuoi costruire il CRUD.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
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

		Label comboEntityLabel = new Label(composite, SWT.LEFT);
		{
			comboEntityLabel.setText("Lista delle entità:");

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			comboEntityLabel.setLayoutData(data);
		}
		comboEntity = new Combo(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			comboEntity.setLayoutData(data);
		}
		
		comboEntity.select(0);
		comboEntity.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		      
		       if(comboEntity.getSelectionIndex() != -1 ) {
		    	    WizardHelper.clearListAndMapAttrEntity(wizard.getInfo());
		    	    WizardHelper.popolaListaAttrSempliciAggregati(comboEntity.getText(), wizard.getInfo());
		    	   	wizard.getInfo().setNomeEntita(comboEntity.getText());
		    	   	WizardHelper.pulisciCampiFiltri();
		    	   	WizardHelper.pulisciCampiTab();
		    	   	updateStatus(null);
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
		if(comboEntity.getSelectionIndex() == -1) {
			updateStatus("ricordati di selezionare l'entità");
		}
		else {
			updateStatus(null);
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
			 if(wiz instanceof SceltaPKeyTerzaPagWizard) {
				Combo comboAttrEntity = ((SceltaPKeyTerzaPagWizard)wiz).getComboAttrEntity();
					String[] items = WizardHelper.getVettoreByList(wizard.getInfo().getListaAttrEntita());
					comboAttrEntity.setItems(items);}
		}
		return wiz;	
	}
	
	
}