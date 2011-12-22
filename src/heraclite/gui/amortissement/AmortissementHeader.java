package heraclite.gui.amortissement;

public enum AmortissementHeader {
  PERIODE("Période"), CAPITAL_DEBUT("Capital debut"), VERSEMENT_TOTAL("Versement total"), VERSEMENT_INTERET("Versement interet"), VERSEMENT_CAPITAL("Versement capital"), CAPITAL_FIN("Capital fin"), VERSEMENT_TOTAL_CUMULATIF(
      "Versement total cumulatif"), VERSEMENT_INTERET_CUMULATIF("Versement interet cumulatif"), VERSEMENT_CAPITAL_CUMULATIF("Versement capital cumulatif");

  private String enumText;

  private AmortissementHeader(String text) {
    enumText = text;
  }

  public String toString() {
    return enumText;
  }

}
