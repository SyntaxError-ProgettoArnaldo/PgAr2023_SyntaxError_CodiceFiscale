package root;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class BagOfPrimitives {

    @SerializedName("persona")
    BagOfPrimitives persone;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cognome")
    private String cognome;

    @SerializedName("sesso")
    private String sesso;

    @SerializedName("comune_nascita")
    private String luogo;

    @SerializedName("data_nascita")
    private String data;

    public final String getCognome() {
        return this.cognome;
    }

    public final String getData() {
        return this.data;
    }

    public final String getNome() {
        return this.nome;
    }

    public final String getSesso() {
        return this.sesso;
    }

    public final String getLuogo() {
        return this.luogo;
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", sesso='" + sesso + '\'' +
                ", luogo='" + luogo + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}