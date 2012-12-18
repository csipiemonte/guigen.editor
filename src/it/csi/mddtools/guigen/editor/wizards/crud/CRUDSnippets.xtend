package it.csi.mddtools.guigen.editor.wizards.crud

import it.csi.mddtools.guigen.ApplicationData
import java.util.ArrayList

/**
 * contiene i template degli snippet di codice da inserire nei vari ExecCommand
 */
class CRUDSnippets {
	
	/**
	 * snippet di codice per la funzione di ricerca delle entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param filtroAppdataName nome dell'appdata del filtro (camel case con prima minuscola)
	 * @param elencoAppdataName nome dell'appdata dell'elenco risultati (camel case con prima minuscola)
	 */
	def ricercaEntitySnippet(String entityName, String filtroAppdataName, String elencoAppdataName)'''
			«entityName» «filtroAppdataName» = theModel.getAppData«filtroAppdataName»();
			result = validaFiltri(«filtroAppdataName», result);
			if (result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {
				ArrayList<«entityName»> «elencoAppdataName» = findElenco«entityName»(«filtroAppdataName»);
				if («elencoAppdataName» != null &&  !«elencoAppdataName».isEmpty()) {
					theModel.setAppData«elencoAppdataName»(«elencoAppdataName»);
					result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__OK);
				} else {
					theModel.setAppData«elencoAppdataName»(null);
					result.getGlobalMessages().add("Non ci sono elementi che corrispondono ai criteri di ricerca selezionati.");
					result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
				}
			} else {
				result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
	'''
	
	/**
	 * snippet di codice per la funzione di dettaglio dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param idSelezionatoAppdataName nome dell'appdata dell'id dell'azienda selezionata (camel case con prima minuscola)
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita' (camel case con prima minuscola)
	 * @param dettaglioAppdataName nome dell'appdata del dettaglio dell'entita' (camel case con prima minuscola)
	 */
	def dettaglioEntitySnippet(String entityName, String idSelezionatoAppdataName, String crudOperationAppdataName, String dettaglioAppdataName, String tipoPk)'''
			theModel.setAppData«crudOperationAppdataName»("OP_U");
			«tipoPk» id«entityName» = theModel.getAppData«idSelezionatoAppdataName»();
			if (id«entityName» != null && !id«entityName».isEmpty()) {
				«entityName» «entityName.toFirstLower» = find«entityName»ById(id«entityName»);
				theModel.setAppData«dettaglioAppdataName»(«entityName.toFirstLower»);
				result.setResultCode(VAIDETT«entityName.toUpperCase()»_OUTCOME_CODE__SHOW_DETAIL);
			} else {
				result.getGlobalErrors().add(
						"Attenzione! Selezionare un record.");
				result.setResultCode(VAIDETT«entityName.toUpperCase()»_OUTCOME_CODE__NO_ITEM_SELECTED);
			}
			result.setModel(theModel);
			return result;
	'''	
	
	/**
	 * snippet di codice per la funzione di creazione dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita' (camel case con prima minuscola)
	 * @param adDtl nome dell'appdata di dettaglio
	 */
	def insertEntitySnippet(String entityName, String crudOperationAppdataName, ApplicationData adDtl)'''
			theModel.setAppData«crudOperationAppdataName»("OP_C");
			«entityName» «entityName.toFirstLower» = new «entityName»();
			
			theModel.setAppData«adDtl.name»(«entityName.toFirstLower»);
			
			result.setResultCode(INSERTDETT«entityName.toUpperCase()»_OUTCOME_CODE__VIEW_DETAIL);

			result.setModel(theModel);
			return result;
	'''	
	
	/**
	 * snippet di codice per la funzione d'eliminazione dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param idSelezionatoAppdataName nome dell'appdata dell'id dell'azienda selezionata (camel case con prima minuscola)
	 * @param elencoAppdataName nome dell'appdata dell'elenco risultati (camel case con prima minuscola)
	 * @param tipoPk il tipo della primary key
	 */
	def eliminaEntitySnippet(String entityName, String idSelezionatoAppdataName, String elencoAppdataName, String tipoPk)'''
			«tipoPk» id«entityName» = theModel.getAppData«idSelezionatoAppdataName»();
			if (id«entityName» != null && !id«entityName».isEmpty()) {
				«entityName» «entityName.toFirstLower» = find«entityName»ById(id«entityName»);
				ArrayList<«entityName»> «elencoAppdataName» = elimina«entityName»(«entityName.toFirstLower»);
				theModel.setAppData«elencoAppdataName»(«elencoAppdataName»);
				theModel.setAppData«idSelezionatoAppdataName»(null);
				result.setResultCode(ELIMINA«entityName.toUpperCase()»_OUTCOME_CODE__OK);
			} else {
				result.getGlobalErrors().add(
						"Attenzione! Selezionare un record.");
				result.setResultCode(ELIMINA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
	'''	
	
	/**
	 * snippet di codice per la funzione di salvataggio dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita' 
	 * @param dettaglioAppdataName nome dell'appdata di dettaglio dell'entita' 
	 */
	def salvaEntitySnippet(String entityName, String crudOperationAppdataName, String dettaglioAppdataName)'''
			String «crudOperationAppdataName» = theModel.getAppData«crudOperationAppdataName»();
			«entityName» «dettaglioAppdataName» = theModel.getAppData«dettaglioAppdataName»();
			if(«crudOperationAppdataName».equals("OP_C")) {
				result = validaInsert«entityName»(«dettaglioAppdataName», result);
				if(result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {
					boolean executedSuccess = crea«entityName»(«dettaglioAppdataName»);
					if(executedSuccess) {
						result.getGlobalMessages().add(
								"L'operazione è stata eseguita con successo.");
						theModel.setAppData«crudOperationAppdataName»(("OP_U"));
						result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__OK);
					 }else {
						result.getGlobalErrors().add(
								"L'operazione non ha avuto successo.");
						result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
					 }
				}else {
					result.getGlobalErrors().add(
							"La validazione non ha avuto successo.");
					result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
				}
			}else if(«crudOperationAppdataName».equals("OP_U")) {
				result = validaUpdate«entityName»(«dettaglioAppdataName», result);
				if(result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {
					boolean executedSuccess = modificaAziendaNew(«dettaglioAppdataName»);
					if(executedSuccess) {
						result.getGlobalMessages().add(
								"L'operazione è stata eseguita con successo.");
						result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__OK);
					}else {
						result.getGlobalErrors().add(
								"L'operazione non ha avuto successo.");
						result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
					}
				}else {
					result.getGlobalErrors().add(
							"La validazione non ha avuto successo.");
					result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
				}
			}else {
				result.setResultCode(SALVA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
	'''	
	
	/**
	 * snippet di codice per la funzione di caricamento delle combo.
	 * @param nomeMainAppData appData del pannello corrente
	 * @param nameAttr nome dell'attributo dell'entita'
	 * @param tipoBase tipo dell'oggetto contenitore della combo
	 */
	def loadComboEntitySnippet(ApplicationData nomeMainAppData, String nameAttr, String tipoBase)'''
		ArrayList<«tipoBase»> lista = getAppData«nomeMainAppData.name.toFirstUpper»Elements();
			if(lista != null) {
				theModel.setAppData«nomeMainAppData.name»(lista);
				result.setResultCode(LOAD«nomeMainAppData.name.toUpperCase»_OUTCOME_CODE__LOAD«nomeMainAppData.name.toUpperCase»_OK);
			} else {
				theModel.setAppData«nomeMainAppData.name»(new ArrayList<«tipoBase»>());
				result.getGlobalErrors().add("Attenzione! Si è verificato un problema nel caricamento dei dati.");
				result.setResultCode(LOAD«nomeMainAppData.name.toUpperCase»_OUTCOME_CODE__LOAD«nomeMainAppData.name.toUpperCase»_KO);
			}
			result.setModel(theModel);
			return result;
	'''
	
	/**
	 * snippet di codice per la funzione riparatrice dell'appaData associato alla combo semplice.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param nomeMainAppData appData del pannello corrente
	 * @param adElenco appData dell'elenco di decodifica
	 * @param tipoBase tipo dell'oggetto contenitore della combo
	 * @param nomeCampoKey  nome del campo che rappresenta la chiave 
	 */
	def fixComboSingleEntitySnippet(String entityName, ApplicationData nomeMainAppData, ApplicationData adElenco, String tipoBase, String nomeCampoKey)'''
		«entityName» «nomeMainAppData.name» = theModel.getAppData«nomeMainAppData.name»();
		String codice = «nomeMainAppData.name».get«tipoBase»().get«nomeCampoKey.toFirstUpper»();
		ArrayList<«tipoBase»> lista«tipoBase» = theModel.getAppData«adElenco.name»();
		ArrayList keySel = new ArrayList();
		keySel.add(codice);
		ArrayList<«tipoBase»> elementoSel = DTOUtils.findElementInListById(lista«tipoBase», keySel,"«nomeCampoKey.toFirstUpper»");
		if (elementoSel.size() > 0) {
			«tipoBase» clone = («tipoBase») DTOUtils.deepClone(elementoSel.get(0));
			«nomeMainAppData.name».set«tipoBase»(clone);
		}
		result.setResultCode(FIX«nomeMainAppData.name.toUpperCase»ELEMENTS_OUTCOME_CODE__«nomeMainAppData.name.toUpperCase»_ELEMENTS_OK);
		result.setModel(theModel);
		return result;
	'''
	
	/**
	 * snippet di codice per la funzione per la funzione riparatrice dell'appaData associato alla combo multipla.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param filtroDettAppData appData del filtro di dettaglio
	 * @param nomeMainAppData appData dell'elenco di decodifica
	 * @param nameAttr nome dell'attributo dell'entita' 
	 * @param tipoBase tipo dell'oggetto contenitore della combo
	 * @param nomeCampoKey  nome del campo che rappresenta la chiave 
	 */
	def fixComboMultipleEntitySnippet(String entityName, ApplicationData filtroDettAppData, ApplicationData nomeMainAppData, ApplicationData selectedKeysAppData, String nameAttr, String tipoBase, String nomeCampoKey)'''
			«entityName» «filtroDettAppData.name» = theModel.getAppData«filtroDettAppData.name»();
			ArrayList<String> listaKeysSelezionati = theModel.getAppData«selectedKeysAppData.name»();
			ArrayList<«tipoBase»> lista«tipoBase» = theModel.getAppData«nomeMainAppData.name»();
			ArrayList<«tipoBase»> listaElementSel = DTOUtils.findElementInListById(lista«tipoBase», listaKeysSelezionati,"«nomeCampoKey.toFirstUpper»");
			if (listaElementSel.size() > 0) {
				ArrayList<«tipoBase»> listaClone = new ArrayList<«tipoBase»>();
				for («tipoBase» ele : listaElementSel) {
					listaClone.add((«tipoBase») DTOUtils.deepClone(ele));
				}
					«filtroDettAppData.name».set«nameAttr.toFirstUpper»(listaClone);
			}
			result.setResultCode(FIX«nomeMainAppData.name.toUpperCase»ELEMENTS_OUTCOME_CODE__«nomeMainAppData.name.toUpperCase»_ELEMENTS_OK);
			result.setModel(theModel);
			return result;
	'''
	
	/**
	 * snippet di codice relativo al pannello di dettaglio per la corretta visualizzazione della lista delle chiavi.
	 * @param entityName nome dell'entita' (gia in camel-case e con la prima maiuscola)
	 * @param filtroDettAppData appData di filtro dettaglio
	 * @param selectedKeysAppData appData delle chiavi selezionate
	 * @param nameAttr nome dell'attributo dell'entita'
	 * @param tipoBase tipo dell'oggetto contenitore della combo
	 */
	def fixViewEntitySnippet(String entityName, ApplicationData filtroDettAppData, ApplicationData selectedKeysAppData, String nameAttr, String tipoBase)'''
			«entityName» «filtroDettAppData.name» = theModel.getAppData«filtroDettAppData.name»();
			if(«filtroDettAppData.name» != null) {
				ArrayList listaCodici = findCodiceByLista«tipoBase»(«filtroDettAppData.name».get«nameAttr.toFirstUpper»());
				if(listaCodici != null && listaCodici.size() > 0)  {
					theModel.setAppData«selectedKeysAppData.name»(listaCodici);
				} else {
					theModel.setAppData«selectedKeysAppData.name»(new ArrayList());
				}
				result.setResultCode(FIX«filtroDettAppData.name.toUpperCase»VIEW_OUTCOME_CODE__FIX«filtroDettAppData.name.toUpperCase»VIEW_OK);
			} else {
				result.setResultCode(FIX«filtroDettAppData.name.toUpperCase»VIEW_OUTCOME_CODE__FIX«filtroDettAppData.name.toUpperCase»VIEW_KO);
			}
			result.setModel(theModel);
			return result;	
	'''
	
	/**
	 * snippet di codice relativo al pannello di ricerca per la corretta visualizzazione della lista delle chiavi.
	 * @param entityName nome dell'entita (gia in camel-case e con la prima maiuscola)
	 * @param filtroDettAppData  appData di filtro dettaglio
	 * @param selectedKeysAppData  appData delle chiavi selezionate
	 * @param nameAttr  nome dell'attributo dell'entita'
	 * @param tipoBase tipo dell'oggetto contenitore della combo
	 */
	def fixViewRicercaEntitySnippet(String entityName, ApplicationData filtroDettAppData, ApplicationData selectedKeysAppData, String nameAttr, String tipoBase)'''
			theModel.setAppData«selectedKeysAppData.name»(new ArrayList());
			result.setResultCode(FIX«filtroDettAppData.name.toUpperCase»VIEW_OUTCOME_CODE__FIX«filtroDettAppData.name.toUpperCase»VIEW_OK);
			result.setModel(theModel);
			return result;	
	'''
	
}