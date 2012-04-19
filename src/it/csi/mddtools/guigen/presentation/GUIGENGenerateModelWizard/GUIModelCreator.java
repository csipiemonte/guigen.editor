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
import it.csi.mddtools.guigen.AppWindow;
import it.csi.mddtools.guigen.ApplicationArea;
import it.csi.mddtools.guigen.ApplicationDataDefs;
import it.csi.mddtools.guigen.Footer;
import it.csi.mddtools.guigen.GUIModel;
import it.csi.mddtools.guigen.GUIStructure;
import it.csi.mddtools.guigen.GuigenFactory;
import it.csi.mddtools.guigen.GuigenPackage;
import it.csi.mddtools.guigen.Header;
import it.csi.mddtools.guigen.Statusbar;
import it.csi.mddtools.guigen.Titlebar;
import it.csi.mddtools.guigen.TypeNamespace;
import it.csi.mddtools.guigen.Typedefs;

public class GUIModelCreator {
	private String codProd;

	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public String getCodComp() {
		return codComp;
	}

	public void setCodComp(String codComp) {
		this.codComp = codComp;
	}

	public String getVerProd() {
		return verProd;
	}

	public void setVerProd(String verProd) {
		this.verProd = verProd;
	}

	public String getVerComp() {
		return verComp;
	}

	public void setVerComp(String verComp) {
		this.verComp = verComp;
	}

	public TypeNamespace getCommonTNS() {
		return commonTNS;
	}

	public void setCommonTNS(TypeNamespace commonTNS) {
		this.commonTNS = commonTNS;
	}

	public AppDataGroup getCommonADG() {
		return commonADG;
	}

	public void setCommonADG(AppDataGroup commonADG) {
		this.commonADG = commonADG;
	}

	private String codComp;
	private String verProd;
	private String verComp;

	private TypeNamespace commonTNS;
	private AppDataGroup commonADG;

	protected GuigenPackage guigenPackage = GuigenPackage.eINSTANCE;

	protected GuigenFactory guigenFactory = guigenPackage.getGuigenFactory();
	
	/**
	 * 
	 * @return il nuovo GUIModel con i riferimenti ad appdata common e namespace common
	 */
	public GUIModel getNewGUIModel(){
		
			GUIModel model = guigenFactory.createGUIModel();
			// aggiunge dati identificativi
			model.setCodProdotto(getCodProd());
			model.setCodComponente(getCodComp());
			model.setVersioneProdotto(getVerProd());
			model.setVersioneComponente(getVerComp());
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
//			// crea tipi base
//			it.csi.mddtools.guigen.Type [] baseCSITypes = it.csi.mddtools.guigen.genutils.GenUtils.generateCSIBaseTypes();
			Typedefs baseTypesContainer = guigenFactory.createTypedefs();
			model.setTypedefs(baseTypesContainer);
			baseTypesContainer.getExtNamespaces().add(getCommonTNS());
//			for (int i = 0; i < baseCSITypes.length; i++) {
//				baseTypesContainer.getTypes().add(baseCSITypes[i]);
//			}
			// inserisce il contenitore di appdata
			ApplicationDataDefs appDataDefs = guigenFactory.createApplicationDataDefs();
			model.setAppDataDefs(appDataDefs);
			appDataDefs.getExtGroups().add(commonADG);
			return model;
	}
}
