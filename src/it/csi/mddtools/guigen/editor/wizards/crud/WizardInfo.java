package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WizardInfo {
	
	private Map<String, Type> mapNomeTipo =  new HashMap<String, Type>();
	
	public Map<String, Type> getMapNomeTipo() {
		return mapNomeTipo;
	}

	public void setMapNomeTipo(Map<String, Type> mapNomeTipo) {
		this.mapNomeTipo = mapNomeTipo;
	}

	 private Map<String, Type> mapListaAttrEntita =  new HashMap<String, Type>();
	
	
	 public Map<String, Type> getMapListaAttrEntita() {
		return mapListaAttrEntita;
	}

	public void setMapListaAttrEntita(Map<String, Type> mapListaAttrEntita) {
		this.mapListaAttrEntita = mapListaAttrEntita;
	}
	
	private Map<String, FieldContext> mapListaAttrEntitaContext =  new HashMap<String, FieldContext>();
	 
	 
	public Map<String, FieldContext> getMapListaAttrEntitaContext() {
		return mapListaAttrEntitaContext;
	}

	public void setMapListaAttrEntitaContext(
			Map<String, FieldContext> mapListaAttrEntitaContext) {
		this.mapListaAttrEntitaContext = mapListaAttrEntitaContext;
	}

	List listaAttrEntita = new ArrayList();
	
	public List getListaAttrEntita() {
		return listaAttrEntita;
	}

	public void setListaAttrEntita(List listaAttrEntita) {
		this.listaAttrEntita = listaAttrEntita;
	}

	private String nomeEntita;
	
	private String idPrimaryKey;
	
	private String[] campiFiltri;
	
	private String[] campiTabella;

	public String getNomeEntita() {
		return nomeEntita;
	}

	public void setNomeEntita(String nomeEntita) {
		this.nomeEntita = nomeEntita;
	}

	public String getIdPrimaryKey() {
		return idPrimaryKey;
	}

	public void setIdPrimaryKey(String idPrimaryKey) {
		this.idPrimaryKey = idPrimaryKey;
	}

	public String[] getCampiFiltri() {
		return campiFiltri;
	}

	public void setCampiFiltri(String[] campiFiltri) {
		this.campiFiltri = campiFiltri;
	}

	public String[] getCampiTabella() {
		return campiTabella;
	}

	public void setCampiTabella(String[] campiTabella) {
		this.campiTabella = campiTabella;
	}
	
	
}
