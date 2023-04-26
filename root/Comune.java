package root;

import com.google.gson.annotations.SerializedName;

public class Comune
{
    @SerializedName("nome")
    private String nomeComune;
    @SerializedName("codice")
    private String codiceComune;

    public Comune() {}

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCodiceComune(String codiceComune) {
        this.codiceComune = codiceComune;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public String getCodiceComune() {
        return codiceComune;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "nomeComune='" + nomeComune + '\'' +
                ", codiceComune='" + codiceComune + '\'' +
                '}';
    }
}
