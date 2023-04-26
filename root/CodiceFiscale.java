package root;

import com.google.gson.annotations.SerializedName;

public class CodiceFiscale
{
    private String nome;
    private ValiditaCF validitaCF = ValiditaCF.SPAIATO;

    public CodiceFiscale() {}

    public CodiceFiscale(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setValiditaCF(ValiditaCF validitaCF)
    {
        this.validitaCF = validitaCF;
    }

    public String getNome()
    {
        return nome;
    }

    public ValiditaCF getValiditaCF()
    {
        return validitaCF;
    }

    @Override
    public String toString()
    {
        return "CodiceFiscale{" +
                "nome='" + nome + '\'' +
                ", validitaCF=" + validitaCF +
                '}';
    }


}
