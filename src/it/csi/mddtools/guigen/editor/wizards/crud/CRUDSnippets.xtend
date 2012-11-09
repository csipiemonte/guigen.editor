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
	def RicercaEntitySnippet(String entityName, String filtroAppdataName, String elencoAppdataName)'''
			«entityName» «filtroAppdataName» = theModel.getAppData«filtroAppdataName»();
			result = validaFiltri(«filtroAppdataName», result);
			if (result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {
				ArrayList<«entityName»> «elencoAppdataName» = findElenco«entityName»(«filtroAppdataName»);
				if («elencoAppdataName» != null &&  !«elencoAppdataName».isEmpty()) {
					theModel.setAppData«elencoAppdataName»(«elencoAppdataName»);
					result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__OK);
				} else {
					theModel.setAppData«elencoAppdataName»(null);
					result.getGlobalMessages().add("Non ci sono elementi che corrispondono ai criteri di ricerca selezionati");
					result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
				}
			} else {
				result.setResultCode(RICERCA«entityName.toUpperCase()»_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
	'''
}