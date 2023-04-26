package root;

import com.google.gson.annotations.SerializedName;

public class PersonaJSON {
    @SerializedName("nome")
    private String nome;

    @SerializedName("cognome")
    private String cognome;

    @SerializedName("sesso")
    private char sesso;

    @SerializedName("comune_nascita")
    private String luogo;

    @SerializedName("data_nascita")
    private String data;
    public final String getNome() {
        return this.nome;
    }
    public final String getCognome() {
        return this.cognome;
    }
    public final char getSesso() {
        return this.sesso;
    }
    public final String getLuogo() {
        return this.luogo;
    }
    public final String getData() {
        return this.data;
    }
    public PersonaJSON(String nome, String cognome, char sesso, String luogo, String data) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.luogo = luogo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "PersonaJSON{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", sesso='" + sesso + '\'' +
                ", luogo='" + luogo + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}