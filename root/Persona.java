package root;

import java.time.LocalDate;

public class Persona
{
    private String nome;
    private String cognome;
    private char sesso;
    private String luogo;
    private LocalDate dataDiNascita;
    private String codiceFiscale;
    private boolean controlloCF =  false;        //Inizializzo il controllo della corrispondenza del codice fiscale a false

    public Persona(){
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setControlloCF(boolean controlloCF) {
        this.controlloCF = controlloCF;
    }

    public String getNome()
    {
        return nome;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", sesso=" + sesso +
                ", luogo='" + luogo + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", controlloCF=" + controlloCF +
                '}';
    }
}
