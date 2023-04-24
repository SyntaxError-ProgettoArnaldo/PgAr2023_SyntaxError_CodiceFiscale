package root;

public class CodiceFiscale
{
    private String nome;
    private ValiditaCF validitaCF;

    public CodiceFiscale() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValiditaCF(ValiditaCF validitaCF) {
        this.validitaCF = validitaCF;
    }

    @Override
    public String toString() {
        return "CodiceFiscale{" +
                "nome='" + nome + '\'' +
                ", validitaCF=" + validitaCF +
                '}';
    }
}
