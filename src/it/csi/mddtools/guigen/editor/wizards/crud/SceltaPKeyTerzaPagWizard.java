package it.csi.mddtools.guigen.editor.wizards.crud;

import java.io.IOException;

import it.csi.mddtools.guigen.AppModule;
import it.csi.mddtools.guigen.ComplexType;
import it.csi.mddtools.guigen.Field;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.SimpleType;
import it.csi.mddtools.guigen.Type;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;


/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SceltaPKeyTerzaPagWizard extends WizardPage {
	
	private NewEntityCRUDWizard wizard;
	
	private GUIModel guiModel;//6-10-2012
	
	
	public GUIModel getGuiModel() {
		return guiModel;
	}

	public void setGuiModel(GUIModel guiModel) {
		this.guiModel = guiModel;
	}

	private ISelection selection;
	
	private Combo comboAttrEntity;
	
	
	

	public Combo getComboAttrEntity() {
		return comboAttrEntity;
	}

	public void setComboAttrEntity(Combo comboAttrEntity) {
		this.comboAttrEntity = comboAttrEntity;
	}

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SceltaPKeyTerzaPagWizard(ISelection selection, NewEntityCRUDWizard wizard) {
		super("wizardPage");
		this.wizard = wizard;
		setTitle("Scelta della Primary Key dell'entità.");
		setDescription("Scegli la primary key dell'entità selezionata.");
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

		Label comboAttrEntityLabel = new Label(composite, SWT.LEFT);
		{
			comboAttrEntityLabel.setText("Lista degli attributi delle entità:");

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			comboAttrEntityLabel.setLayoutData(data);
		}
		comboAttrEntity = new Combo(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			comboAttrEntity.setLayoutData(data);
		}
		
		
		comboAttrEntity.select(0);
		comboAttrEntity.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  int indexComboSelez = comboAttrEntity.getSelectionIndex();
		    	  if(indexComboSelez != -1 ) {
		    	
		    		  wizard.getInfo().setIdPrimaryKey(comboAttrEntity.getItem(indexComboSelez));
		    		  updateStatus(null);
		    	  }

		      }
		    });
		
		dialogChanged();
		setControl(composite);
	
	}

	private void dialogChanged() {
		
		if(comboAttrEntity.getSelectionIndex() == -1) {
			updateStatus("ricordati di selezionare la primary key");
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
			 if(wiz instanceof SceltaFiltriEntitaQuartaPagWizard) {
				 
				List wdgListAttrEntita = ((SceltaFiltriEntitaQuartaPagWizard)wiz).getWdgListAttrEntita();
				List wdgListFiltriEntita = ((SceltaFiltriEntitaQuartaPagWizard)wiz).getWdgListFiltriEntita();
				
					String[] items = WizardHelper.getVettoreByList(wizard.getInfo().getListaAttrEntita());
					wdgListAttrEntita.setItems(items);
					
					if(WizardHelper.getListaUnica().isEmpty()) {
						wdgListFiltriEntita.removeAll();
					}
					
			}
		}
		
			 
		return wiz;	
	}

	
}