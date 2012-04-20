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

import it.csi.mddtools.guigen.TargetPlatformCodes;

import java.util.Iterator;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
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
import org.eclipse.swt.widgets.Text;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (guigen).
 */

public class GuiModelDatiAggiuntiviWizardPage extends WizardPage {
	
	private Text codApplicativo;
	private Text codCanale;
	private Text linkCanale;
	private Text nomeApplicativo;
	private Text nomeCanale;
	
	private ISelection selection;
	private Combo codeServerCombo;
	private Button enrichmentChecBox;
	
	private static String TITLE_WIZARD = GuigenEditorPlugin.INSTANCE.getString("_UI_DatiAggiuntiviPage_title"); 
	private static String DESCRIPTION_WIZARD = GuigenEditorPlugin.INSTANCE.getString("_UI_DatiAggiuntiviPage_descrizione"); 
	
	private static String COD_APPLICATIVO_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_CodApplicativo_label");
	private static String COD_CANALE_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_CodCanale_label");
	private static String LINK_CANALE_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_LinkCanale_label");
	private static String NOME_APPLICATIVO_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_NomeApplicativo_label");
	private static String NOME_CANALE_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_NomeCanale_label");
	private static String SERVER_CODE_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_ServerCode_label");
	private static String ENRICHMENT_LABEL = GuigenEditorPlugin.INSTANCE.getString("_UI_Enrichment_label");
	
	
	private static String COD_APPLICATIVO_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_CodApplicativo_tooltip");
	private static String COD_CANALE_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_CodCanale_tooltip");
	private static String LINK_CANALE_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_LinkCanale_tooltip");
	private static String NOME_APPLICATIVO_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_NomeApplicativo_tooltip");
	private static String NOME_CANALE_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_NomeCanale_tooltip");
	private static String SERVER_CODE_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_ServerCode_tooltip");
	private static String ENRICHMENT_TOOLTIP = GuigenEditorPlugin.INSTANCE.getString("_UI_Enrichment_tooltip");
	
	
	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public GuiModelDatiAggiuntiviWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle(TITLE_WIZARD);
		setDescription(DESCRIPTION_WIZARD);
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

		
		Label codApplicativoLabel = new Label(composite, SWT.LEFT);
		{
			codApplicativoLabel.setText(COD_APPLICATIVO_LABEL);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			codApplicativoLabel.setLayoutData(data);
			codApplicativoLabel.setToolTipText(COD_APPLICATIVO_TOOLTIP);
		}

		codApplicativo = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			codApplicativo.setLayoutData(data);
			codApplicativo.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					
				}
			});
		}
		
		

		Label codCanaleLabel = new Label(composite, SWT.LEFT);
		{
			codCanaleLabel.setText(COD_CANALE_LABEL);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			codCanaleLabel.setLayoutData(data);
			codCanaleLabel.setToolTipText(COD_CANALE_TOOLTIP);
		}

		codCanale = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			codCanale.setLayoutData(data);
			codCanale.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					
				}
			});
		}
		
		Label linkCanaleLabel = new Label(composite, SWT.LEFT);
		{
			linkCanaleLabel.setText(LINK_CANALE_LABEL);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			linkCanaleLabel.setLayoutData(data);
			linkCanaleLabel.setToolTipText(LINK_CANALE_TOOLTIP);
		}

		linkCanale = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			linkCanale.setLayoutData(data);
			linkCanale.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					
				}
			});
		}
		
		Label nomeApplicativoLabel = new Label(composite, SWT.LEFT);
		{
			nomeApplicativoLabel.setText(NOME_APPLICATIVO_LABEL);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			nomeApplicativoLabel.setLayoutData(data);
			nomeApplicativoLabel.setToolTipText(NOME_APPLICATIVO_TOOLTIP);
		}

		nomeApplicativo = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			nomeApplicativo.setLayoutData(data);
			nomeApplicativo.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					
				}
			});
		}
		
		
		Label nomeCanaleLabel = new Label(composite, SWT.LEFT);
		{
			nomeCanaleLabel.setText(NOME_CANALE_LABEL);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			nomeCanaleLabel.setLayoutData(data);
			nomeCanaleLabel.setToolTipText(NOME_CANALE_TOOLTIP);
		}

		nomeCanale = new org.eclipse.swt.widgets.Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			nomeCanale.setLayoutData(data);
			nomeCanale.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
					
				}
			});
		}
		
		Label codeServerLabel = new Label(composite, SWT.LEFT);
		{
			codeServerLabel.setText(SERVER_CODE_LABEL);
			codeServerLabel.setToolTipText(SERVER_CODE_TOOLTIP);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			codeServerLabel.setLayoutData(data);
		}

		codeServerCombo = new Combo(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			codeServerCombo.setLayoutData(data);
		}
		
		
		//TODO : enumeration 		
		for (Iterator<TargetPlatformCodes> iterator = TargetPlatformCodes.VALUES.iterator(); iterator.hasNext();) {
			TargetPlatformCodes targetPlatformCodes = (TargetPlatformCodes) iterator.next();
			codeServerCombo.add(targetPlatformCodes.getName());
			
		}
		codeServerCombo.select(0);
		codeServerCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		
		
		Label entichmentCheckBoxlabel = new Label(composite, SWT.LEFT);
		{
			entichmentCheckBoxlabel.setText(ENRICHMENT_LABEL);
			entichmentCheckBoxlabel.setToolTipText(ENRICHMENT_TOOLTIP);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			entichmentCheckBoxlabel.setLayoutData(data);
		}
		enrichmentChecBox = new Button(composite, SWT.CHECK | SWT.LEFT);
	
		setPageComplete(validatePage());
		setControl(composite);
		
		
	}
	
	
	protected boolean validatePage() {		
		return (getCodApplicativo() != null && getCodCanale() != null
					&& getNomeApplicativo() != null && getNomeCanale() != null && getLinkCanale() != null);
	}
	
	
	private String validaCampo(Text value){
		String txt = value != null ? value.getText() : null;
		if (txt==null || txt.length()==0)
			return null;
		else
			return txt;
	}
	
	
	public String getCodApplicativo() {
		return validaCampo(codApplicativo);
	}


	public String getCodCanale() {
		return validaCampo(codCanale);
	}


	public String getLinkCanale() {
		return validaCampo(linkCanale);
	}


	public String getNomeApplicativo() {
		return validaCampo(nomeApplicativo);
	}


	public String getNomeCanale() {
		return validaCampo(nomeCanale);
	}


	public String getCodeServerCombo() {
		
		String txt = codeServerCombo != null ? codeServerCombo.getText(): null;
		if (txt==null || txt.length()==0)
			return null;
		else
			return txt;
	}


	public Button getEnrichmentChecBox() {
		return enrichmentChecBox;
	}

	
}