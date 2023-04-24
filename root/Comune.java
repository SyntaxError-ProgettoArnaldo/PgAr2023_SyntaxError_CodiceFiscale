package root;

public class Comune
{
    private String nomeComune;
    private String codiceComune;

    public Comune() {}

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCodiceComune(String codiceComune) {
        this.codiceComune = codiceComune;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "nomeComune='" + nomeComune + '\'' +
                ", codiceComune='" + codiceComune + '\'' +
                '}';
    }
}
