package it.csi.mddtools.guigen.editor.wizards.crud

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
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entità (camel case con prima minuscola)
	 * @param dettaglioAppdataName nome dell'appdata del dettaglio dell'entità (camel case con prima minuscola)
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
	 * snippet di codice per la funzione di dettaglio dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entità (camel case con prima minuscola)
	 */
	def insertEntitySnippet(String entityName, String crudOperationAppdataName)'''
			theModel.setAppData«crudOperationAppdataName»("OP_C");
			result.setResultCode(INSERTDETT«entityName.toUpperCase()»_OUTCOME_CODE__VIEW_DETAIL);

			result.setModel(theModel);
			return result;
	'''	
	
	/**
	 * snippet di codice per la funzione d'eliminazione dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param idSelezionatoAppdataName nome dell'appdata dell'id dell'azienda selezionata (camel case con prima minuscola)
	 * @param elencoAppdataName nome dell'appdata dell'elenco risultati (camel case con prima minuscola)
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
	 * snippet di codice per la funzione d'eliminazione dell'entita'.
	 * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
	 * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entità (camel case con prima minuscola)
	 * @param dettaglioAppdataName nome dell'appdata di dettaglio dell'entità (camel case con prima minuscola)
	 */
	def salvaEntitySnippet(String entityName, String crudOperationAppdataName, String dettaglioAppdataName)'''
			String «crudOperationAppdataName» = theModel.getAppData«crudOperationAppdataName»();
			«entityName» «dettaglioAppdataName» = theModel.getAppData«dettaglioAppdataName»();
			if(«crudOperationAppdataName».equals("OP_C")) {
				result = validaInsert«entityName»(«dettaglioAppdataName», result);
				if(result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {
					boolean executedSuccess = crea«entityName»();
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
					boolean executedSuccess = modifica«entityName»();
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
	
}