package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.ApplicationData;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * contiene i template degli snippet di codice da inserire nei vari ExecCommand
 */
@SuppressWarnings("all")
public class CRUDSnippets {
  /**
   * snippet di codice per la funzione di ricerca delle entita'.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param filtroAppdataName nome dell'appdata del filtro (camel case con prima minuscola)
   * @param elencoAppdataName nome dell'appdata dell'elenco risultati (camel case con prima minuscola)
   */
  public CharSequence ricercaEntitySnippet(final String entityName, final String filtroAppdataName, final String elencoAppdataName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(entityName);
    _builder.append(" ");
    _builder.append(filtroAppdataName);
    _builder.append(" = theModel.getAppData");
    _builder.append(filtroAppdataName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("result = validaFiltri(");
    _builder.append(filtroAppdataName);
    _builder.append(", result);");
    _builder.newLineIfNotEmpty();
    _builder.append("if (result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ArrayList<");
    _builder.append(entityName, "\t");
    _builder.append("> ");
    _builder.append(elencoAppdataName, "\t");
    _builder.append(" = findElenco");
    _builder.append(entityName, "\t");
    _builder.append("(");
    _builder.append(filtroAppdataName, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (");
    _builder.append(elencoAppdataName, "\t");
    _builder.append(" != null &&  !");
    _builder.append(elencoAppdataName, "\t");
    _builder.append(".isEmpty()) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    _builder.append(elencoAppdataName, "\t\t");
    _builder.append("(");
    _builder.append(elencoAppdataName, "\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(RICERCA");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "\t\t");
    _builder.append("_OUTCOME_CODE__OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    _builder.append(elencoAppdataName, "\t\t");
    _builder.append("(null);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("result.getGlobalMessages().add(\"Non ci sono elementi che corrispondono ai criteri di ricerca selezionati.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(RICERCA");
    String _upperCase_1 = entityName.toUpperCase();
    _builder.append(_upperCase_1, "\t\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(RICERCA");
    String _upperCase_2 = entityName.toUpperCase();
    _builder.append(_upperCase_2, "\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione di dettaglio dell'entita'.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param idSelezionatoAppdataName nome dell'appdata dell'id dell'azienda selezionata (camel case con prima minuscola)
   * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita' (camel case con prima minuscola)
   * @param dettaglioAppdataName nome dell'appdata del dettaglio dell'entita' (camel case con prima minuscola)
   */
  public CharSequence dettaglioEntitySnippet(final String entityName, final String idSelezionatoAppdataName, final String crudOperationAppdataName, final String dettaglioAppdataName, final String tipoPk) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("theModel.setAppData");
    _builder.append(crudOperationAppdataName);
    _builder.append("(\"OP_U\");");
    _builder.newLineIfNotEmpty();
    _builder.append(tipoPk);
    _builder.append(" id");
    _builder.append(entityName);
    _builder.append(" = theModel.getAppData");
    _builder.append(idSelezionatoAppdataName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("if (id");
    _builder.append(entityName);
    _builder.append(" != null && !id");
    _builder.append(entityName);
    _builder.append(".isEmpty()) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(entityName, "\t");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "\t");
    _builder.append(" = find");
    _builder.append(entityName, "\t");
    _builder.append("ById(id");
    _builder.append(entityName, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("theModel.setAppData");
    _builder.append(dettaglioAppdataName, "\t");
    _builder.append("(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("result.setResultCode(VAIDETT");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("_OUTCOME_CODE__SHOW_DETAIL);");
    _builder.newLineIfNotEmpty();
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("\"Attenzione! Selezionare un record.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(VAIDETT");
    String _upperCase_1 = entityName.toUpperCase();
    _builder.append(_upperCase_1, "\t");
    _builder.append("_OUTCOME_CODE__NO_ITEM_SELECTED);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione di creazione dell'entita'.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita' (camel case con prima minuscola)
   * @param adDtl nome dell'appdata di dettaglio
   */
  public CharSequence insertEntitySnippet(final String entityName, final String crudOperationAppdataName, final ApplicationData adDtl) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("theModel.setAppData");
    _builder.append(crudOperationAppdataName);
    _builder.append("(\"OP_C\");");
    _builder.newLineIfNotEmpty();
    _builder.append(entityName);
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower);
    _builder.append(" = new ");
    _builder.append(entityName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("theModel.setAppData");
    String _name = adDtl.getName();
    _builder.append(_name);
    _builder.append("(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("result.setResultCode(INSERTDETT");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase);
    _builder.append("_OUTCOME_CODE__VIEW_DETAIL);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione d'eliminazione dell'entita'.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param idSelezionatoAppdataName nome dell'appdata dell'id dell'azienda selezionata (camel case con prima minuscola)
   * @param elencoAppdataName nome dell'appdata dell'elenco risultati (camel case con prima minuscola)
   * @param tipoPk il tipo della primary key
   */
  public CharSequence eliminaEntitySnippet(final String entityName, final String idSelezionatoAppdataName, final String elencoAppdataName, final String tipoPk) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(tipoPk);
    _builder.append(" id");
    _builder.append(entityName);
    _builder.append(" = theModel.getAppData");
    _builder.append(idSelezionatoAppdataName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("if (id");
    _builder.append(entityName);
    _builder.append(" != null && !id");
    _builder.append(entityName);
    _builder.append(".isEmpty()) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(entityName, "\t");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "\t");
    _builder.append(" = find");
    _builder.append(entityName, "\t");
    _builder.append("ById(id");
    _builder.append(entityName, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ArrayList<");
    _builder.append(entityName, "\t");
    _builder.append("> ");
    _builder.append(elencoAppdataName, "\t");
    _builder.append(" = elimina");
    _builder.append(entityName, "\t");
    _builder.append("(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("theModel.setAppData");
    _builder.append(elencoAppdataName, "\t");
    _builder.append("(");
    _builder.append(elencoAppdataName, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("theModel.setAppData");
    _builder.append(idSelezionatoAppdataName, "\t");
    _builder.append("(null);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("result.setResultCode(ELIMINA");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("_OUTCOME_CODE__OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("\"Attenzione! Selezionare un record.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(ELIMINA");
    String _upperCase_1 = entityName.toUpperCase();
    _builder.append(_upperCase_1, "\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione di salvataggio dell'entita'.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param crudOperationAppdataName nome dell'appdata dell'operazione crud dell'entita'
   * @param dettaglioAppdataName nome dell'appdata di dettaglio dell'entita'
   */
  public CharSequence salvaEntitySnippet(final String entityName, final String crudOperationAppdataName, final String dettaglioAppdataName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("String ");
    _builder.append(crudOperationAppdataName);
    _builder.append(" = theModel.getAppData");
    _builder.append(crudOperationAppdataName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append(entityName);
    _builder.append(" ");
    _builder.append(dettaglioAppdataName);
    _builder.append(" = theModel.getAppData");
    _builder.append(dettaglioAppdataName);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("if(");
    _builder.append(crudOperationAppdataName);
    _builder.append(".equals(\"OP_C\")) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("result = validaInsert");
    _builder.append(entityName, "\t");
    _builder.append("(");
    _builder.append(dettaglioAppdataName, "\t");
    _builder.append(", result);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("boolean executedSuccess = crea");
    _builder.append(entityName, "\t\t");
    _builder.append("(");
    _builder.append(dettaglioAppdataName, "\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if(executedSuccess) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.getGlobalMessages().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"L\'operazione è stata eseguita con successo.\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("theModel.setAppData");
    _builder.append(crudOperationAppdataName, "\t\t\t");
    _builder.append("((\"OP_U\"));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "\t\t\t");
    _builder.append("_OUTCOME_CODE__OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t ");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"L\'operazione non ha avuto successo.\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_1 = entityName.toUpperCase();
    _builder.append(_upperCase_1, "\t\t\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("\"La validazione non ha avuto successo.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_2 = entityName.toUpperCase();
    _builder.append(_upperCase_2, "\t\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}else if(");
    _builder.append(crudOperationAppdataName);
    _builder.append(".equals(\"OP_U\")) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("result = validaUpdate");
    _builder.append(entityName, "\t");
    _builder.append("(");
    _builder.append(dettaglioAppdataName, "\t");
    _builder.append(", result);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(result.getGlobalErrors().isEmpty() && result.getFldErrors().isEmpty()) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("boolean executedSuccess = modificaAziendaNew(");
    _builder.append(dettaglioAppdataName, "\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if(executedSuccess) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.getGlobalMessages().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"L\'operazione è stata eseguita con successo.\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_3 = entityName.toUpperCase();
    _builder.append(_upperCase_3, "\t\t\t");
    _builder.append("_OUTCOME_CODE__OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"L\'operazione non ha avuto successo.\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_4 = entityName.toUpperCase();
    _builder.append(_upperCase_4, "\t\t\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.getGlobalErrors().add(");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("\"La validazione non ha avuto successo.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_5 = entityName.toUpperCase();
    _builder.append(_upperCase_5, "\t\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(SALVA");
    String _upperCase_6 = entityName.toUpperCase();
    _builder.append(_upperCase_6, "\t");
    _builder.append("_OUTCOME_CODE__KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione di caricamento delle combo.
   * @param nomeMainAppData appData del pannello corrente
   * @param nameAttr nome dell'attributo dell'entita'
   * @param tipoBase tipo dell'oggetto contenitore della combo
   */
  public CharSequence loadComboEntitySnippet(final ApplicationData nomeMainAppData, final String nameAttr, final String tipoBase) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ArrayList<");
    _builder.append(tipoBase);
    _builder.append("> lista = getAppData");
    String _firstUpper = StringExtensions.toFirstUpper(nomeMainAppData.getName());
    _builder.append(_firstUpper);
    _builder.append("Elements();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(lista != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    String _name = nomeMainAppData.getName();
    _builder.append(_name, "\t\t");
    _builder.append("(lista);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(LOAD");
    String _upperCase = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase, "\t\t");
    _builder.append("_OUTCOME_CODE__LOAD");
    String _upperCase_1 = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase_1, "\t\t");
    _builder.append("_OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    String _name_1 = nomeMainAppData.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("(new ArrayList<");
    _builder.append(tipoBase, "\t\t");
    _builder.append(">());");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("result.getGlobalErrors().add(\"Attenzione! Si è verificato un problema nel caricamento dei dati.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result.setResultCode(LOAD");
    String _upperCase_2 = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase_2, "\t\t");
    _builder.append("_OUTCOME_CODE__LOAD");
    String _upperCase_3 = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase_3, "\t\t");
    _builder.append("_KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione riparatrice dell'appaData associato alla combo semplice.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param nomeMainAppData appData del pannello corrente
   * @param adElenco appData dell'elenco di decodifica
   * @param tipoBase tipo dell'oggetto contenitore della combo
   * @param nomeCampoKey  nome del campo che rappresenta la chiave
   */
  public CharSequence fixComboSingleEntitySnippet(final String entityName, final ApplicationData nomeMainAppData, final ApplicationData adElenco, final String tipoBase, final String nomeCampoKey) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(entityName);
    _builder.append(" ");
    String _name = nomeMainAppData.getName();
    _builder.append(_name);
    _builder.append(" = theModel.getAppData");
    String _name_1 = nomeMainAppData.getName();
    _builder.append(_name_1);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("String codice = ");
    String _name_2 = nomeMainAppData.getName();
    _builder.append(_name_2);
    _builder.append(".get");
    _builder.append(tipoBase);
    _builder.append("().get");
    String _firstUpper = StringExtensions.toFirstUpper(nomeCampoKey);
    _builder.append(_firstUpper);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("ArrayList<");
    _builder.append(tipoBase);
    _builder.append("> lista");
    _builder.append(tipoBase);
    _builder.append(" = theModel.getAppData");
    String _name_3 = adElenco.getName();
    _builder.append(_name_3);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("ArrayList keySel = new ArrayList();");
    _builder.newLine();
    _builder.append("keySel.add(codice);");
    _builder.newLine();
    _builder.append("ArrayList<");
    _builder.append(tipoBase);
    _builder.append("> elementoSel = DTOUtils.findElementInListById(lista");
    _builder.append(tipoBase);
    _builder.append(", keySel,\"");
    String _firstUpper_1 = StringExtensions.toFirstUpper(nomeCampoKey);
    _builder.append(_firstUpper_1);
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("if (elementoSel.size() > 0) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(tipoBase, "\t");
    _builder.append(" clone = (");
    _builder.append(tipoBase, "\t");
    _builder.append(") DTOUtils.deepClone(elementoSel.get(0));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _name_4 = nomeMainAppData.getName();
    _builder.append(_name_4, "\t");
    _builder.append(".set");
    _builder.append(tipoBase, "\t");
    _builder.append("(clone);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setResultCode(FIX");
    String _upperCase = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("ELEMENTS_OUTCOME_CODE__");
    String _upperCase_1 = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase_1);
    _builder.append("_ELEMENTS_OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice per la funzione per la funzione riparatrice dell'appaData associato alla combo multipla.
   * @param entityName nome dell'entita' (gia' in camel-case e con la prima maiuscola)
   * @param filtroDettAppData appData del filtro di dettaglio
   * @param nomeMainAppData appData dell'elenco di decodifica
   * @param nameAttr nome dell'attributo dell'entita'
   * @param tipoBase tipo dell'oggetto contenitore della combo
   * @param nomeCampoKey  nome del campo che rappresenta la chiave
   */
  public CharSequence fixComboMultipleEntitySnippet(final String entityName, final ApplicationData filtroDettAppData, final ApplicationData nomeMainAppData, final ApplicationData selectedKeysAppData, final String nameAttr, final String tipoBase, final String nomeCampoKey) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(entityName);
    _builder.append(" ");
    String _name = filtroDettAppData.getName();
    _builder.append(_name);
    _builder.append(" = theModel.getAppData");
    String _name_1 = filtroDettAppData.getName();
    _builder.append(_name_1);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("ArrayList<String> listaKeysSelezionati = theModel.getAppData");
    String _name_2 = selectedKeysAppData.getName();
    _builder.append(_name_2);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("ArrayList<");
    _builder.append(tipoBase);
    _builder.append("> lista");
    _builder.append(tipoBase);
    _builder.append(" = theModel.getAppData");
    String _name_3 = nomeMainAppData.getName();
    _builder.append(_name_3);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("ArrayList<");
    _builder.append(tipoBase);
    _builder.append("> listaElementSel = DTOUtils.findElementInListById(lista");
    _builder.append(tipoBase);
    _builder.append(", listaKeysSelezionati,\"");
    String _firstUpper = StringExtensions.toFirstUpper(nomeCampoKey);
    _builder.append(_firstUpper);
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("if (listaElementSel.size() > 0) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ArrayList<");
    _builder.append(tipoBase, "\t");
    _builder.append("> listaClone = new ArrayList<");
    _builder.append(tipoBase, "\t");
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("for (");
    _builder.append(tipoBase, "\t");
    _builder.append(" ele : listaElementSel) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("listaClone.add((");
    _builder.append(tipoBase, "\t\t");
    _builder.append(") DTOUtils.deepClone(ele));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    String _name_4 = filtroDettAppData.getName();
    _builder.append(_name_4, "\t\t");
    _builder.append(".set");
    String _firstUpper_1 = StringExtensions.toFirstUpper(nameAttr);
    _builder.append(_firstUpper_1, "\t\t");
    _builder.append("(listaClone);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setResultCode(FIX");
    String _upperCase = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("ELEMENTS_OUTCOME_CODE__");
    String _upperCase_1 = nomeMainAppData.getName().toUpperCase();
    _builder.append(_upperCase_1);
    _builder.append("_ELEMENTS_OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice relativo al pannello di dettaglio per la corretta visualizzazione della lista delle chiavi.
   * @param entityName nome dell'entita' (gia in camel-case e con la prima maiuscola)
   * @param filtroDettAppData appData di filtro dettaglio
   * @param selectedKeysAppData appData delle chiavi selezionate
   * @param nameAttr nome dell'attributo dell'entita'
   * @param tipoBase tipo dell'oggetto contenitore della combo
   */
  public CharSequence fixViewEntitySnippet(final String entityName, final ApplicationData filtroDettAppData, final ApplicationData selectedKeysAppData, final String nameAttr, final String tipoBase) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(entityName);
    _builder.append(" ");
    String _name = filtroDettAppData.getName();
    _builder.append(_name);
    _builder.append(" = theModel.getAppData");
    String _name_1 = filtroDettAppData.getName();
    _builder.append(_name_1);
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("if(");
    String _name_2 = filtroDettAppData.getName();
    _builder.append(_name_2);
    _builder.append(" != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ArrayList listaCodici = findCodiceByLista");
    _builder.append(tipoBase, "\t");
    _builder.append("(");
    String _name_3 = filtroDettAppData.getName();
    _builder.append(_name_3, "\t");
    _builder.append(".get");
    String _firstUpper = StringExtensions.toFirstUpper(nameAttr);
    _builder.append(_firstUpper, "\t");
    _builder.append("());");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(listaCodici != null && listaCodici.size() > 0)  {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    String _name_4 = selectedKeysAppData.getName();
    _builder.append(_name_4, "\t\t");
    _builder.append("(listaCodici);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("theModel.setAppData");
    String _name_5 = selectedKeysAppData.getName();
    _builder.append(_name_5, "\t\t");
    _builder.append("(new ArrayList());");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(FIX");
    String _upperCase = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("VIEW_OUTCOME_CODE__FIX");
    String _upperCase_1 = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase_1, "\t");
    _builder.append("VIEW_OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result.setResultCode(FIX");
    String _upperCase_2 = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase_2, "\t");
    _builder.append("VIEW_OUTCOME_CODE__FIX");
    String _upperCase_3 = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase_3, "\t");
    _builder.append("VIEW_KO);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;\t");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * snippet di codice relativo al pannello di ricerca per la corretta visualizzazione della lista delle chiavi.
   * @param entityName nome dell'entita (gia in camel-case e con la prima maiuscola)
   * @param filtroDettAppData  appData di filtro dettaglio
   * @param selectedKeysAppData  appData delle chiavi selezionate
   * @param nameAttr  nome dell'attributo dell'entita'
   * @param tipoBase tipo dell'oggetto contenitore della combo
   */
  public CharSequence fixViewRicercaEntitySnippet(final String entityName, final ApplicationData filtroDettAppData, final ApplicationData selectedKeysAppData, final String nameAttr, final String tipoBase) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("theModel.setAppData");
    String _name = selectedKeysAppData.getName();
    _builder.append(_name);
    _builder.append("(new ArrayList());");
    _builder.newLineIfNotEmpty();
    _builder.append("result.setResultCode(FIX");
    String _upperCase = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("VIEW_OUTCOME_CODE__FIX");
    String _upperCase_1 = filtroDettAppData.getName().toUpperCase();
    _builder.append(_upperCase_1);
    _builder.append("VIEW_OK);");
    _builder.newLineIfNotEmpty();
    _builder.append("result.setModel(theModel);");
    _builder.newLine();
    _builder.append("return result;\t");
    _builder.newLine();
    return _builder;
  }
}
