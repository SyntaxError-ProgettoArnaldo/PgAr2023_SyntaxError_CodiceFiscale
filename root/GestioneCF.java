package root;


import javax.xml.stream.XMLStreamException;
import java.util.HashMap;

public final class GestioneCF
{

    static HashMap<String,Character> mappaMesi = new HashMap<>();

    public static void setMappaMesi()
    {
        mappaMesi.put("JANUARY",'A');
        mappaMesi.put("FEBRUARY",'B');
        mappaMesi.put("MARCH",'C');
        mappaMesi.put("APRIL",'D');
        mappaMesi.put("MAY",'E');
        mappaMesi.put("JUNE",'H');
        mappaMesi.put("JULY",'L');
        mappaMesi.put("AUGUST",'M');
        mappaMesi.put("SEPTEMBER",'P');
        mappaMesi.put("OCTOBER",'R');
        mappaMesi.put("NOVEMBER",'S');
        mappaMesi.put("DECEMBER",'T');
    }


    public static void creaCF(Persona persona,Comune[] listaComuni)
    {
        setMappaMesi();
        String cf = "";
        cf+=creaCognome(persona);
        cf+=creaNome(persona);
        cf+=creaAnno(persona);
        cf+=creaMese(persona);
        cf+=creaGiornoSesso(persona);
        cf+=creaComune(persona,listaComuni);
        cf+="A";
        persona.setCodiceFiscale(cf);

    }

    private static String creaComune(Persona persona,Comune[] listaComuni)
    {
        for (int i = 0; i < listaComuni.length; i++) {
            if(persona.getLuogo().equalsIgnoreCase(listaComuni[i].getNomeComune()))
            {
                return listaComuni[i].getCodiceComune();
            }
        }
        return null;
    }

    private static String creaGiornoSesso(Persona persona)
    {
        if(persona.getSesso()=='F')
        {
            return String.valueOf(persona.getDataDiNascita().getDayOfMonth()+40);
        }
        if(persona.getDataDiNascita().getDayOfMonth()<10)
        {
            return "0"+persona.getDataDiNascita().getDayOfMonth();
        }
        return String.valueOf(persona.getDataDiNascita().getDayOfMonth());


    }

    private static char creaMese(Persona persona)
    {
        return mappaMesi.get(String.valueOf(persona.getDataDiNascita().getMonth()));

    }

    public static String prendiUltimiCaratteri(String s, int n)
    {
        if (s == null || n > s.length()) {
            return s;
        }
        return s.substring(s.length() - n);
    }



    private static String creaAnno(Persona persona) {
        return prendiUltimiCaratteri(String.valueOf(persona.getDataDiNascita().getYear()),2);
    }

    public static String creaCognome(Persona persona)
    {
        String cognome="";
        int conta=0;

        for (int i = 0; i < persona.getCognome().length() && conta<3; i++)
        {
            if(isConsonante(persona.getCognome().charAt(i)))
            {
                cognome+=persona.getCognome().charAt(i);
                conta++;
            }
        }
        if(conta<3)     {
            for (int i = 0; i < persona.getCognome().length() && cognome.length()<3; i++)
            {
                if(!isConsonante(persona.getCognome().charAt(i)))
                {
                    cognome+=persona.getCognome().charAt(i);
                }
            }
            if(persona.getCognome().length()<3)
            {
                for (int i = 0; i < 3-cognome.length(); i++)
                {
                    cognome+="X";
                }
            }
        }

        return cognome;
    }

    public static String creaNome(Persona persona)
    {

        String nome="";
        int conta=0;

        if(contaConsonanti(persona.getNome())>3)    {
            for (int i = 0; i < persona.getNome().length() && conta<4; i++)
            {
                if(isConsonante(persona.getNome().charAt(i)))
                {
                    if(conta!=1)    {
                        nome+=persona.getNome().charAt(i);
                    }
                    conta++;
                }
            }
        }
        else {

            for (int i = 0; i < persona.getNome().length() && conta<3; i++)
            {
                if(isConsonante(persona.getNome().charAt(i)))
                {
                    nome+=persona.getNome().charAt(i);
                    conta++;
                }
            }
            if(conta<3)     {
                for (int i = 0; i < persona.getNome().length() && conta < 2; i++) {
                    if (isConsonante(persona.getNome().charAt(i))) {
                        if (conta == 1) {
                            nome += persona.getNome().charAt(i);
                        }
                        conta++;
                    }
                }
                for (int i = 0; i < persona.getNome().length() && nome.length() < 3; i++) {
                    if (!isConsonante(persona.getNome().charAt(i))) {
                        nome += persona.getNome().charAt(i);
                    }
                }
                if (persona.getNome().length() < 3) {
                    for (int i = 0; i < 3 - nome.length(); i++) {
                        nome += "X";
                    }
                }
            }
        }
        return nome;
    }

    public static int contaConsonanti(String stringa)
    {
        int count = 0;
        char[] vocali = {'a','e','i','o','u','A','E','I','O','U'};
        for (int i = 0; i < stringa.length(); i++) {
            for (int j=0 ; j<vocali.length;j++)
            {
                if(vocali[j] == stringa.charAt(i))
                    count++;
            }
        }
        return stringa.length()-count;
    }

    public static boolean isConsonante(char carattere)
    {
        char[] vocali = {'a','e','i','o','u','A','E','I','O','U'};

        for (int i = 0; i < vocali.length; i++) {
            if(carattere==vocali[i])    {
                return false;
            }
        }
        return true;
    }

    public static String cercaCF(CodiceFiscale[] codiciFiscali, String cf)
    {
        boolean cfTrovato = false;
        for (int i = 0; i < codiciFiscali.length; i++) {
            if(codiciFiscali[i].getNome().substring(0,codiciFiscali[i].getNome().length()-2).equalsIgnoreCase(cf.substring(0,cf.length()-2)))
            {
                codiciFiscali[i].setValiditaCF(ValiditaCF.VALIDO);
                cfTrovato=true;
                cf= codiciFiscali[i].getNome();

            }
        }
        if(!cfTrovato)
        {
            cf="ASSENTE";

        }
        return cf;
    }

    public static void validazioneCF(CodiceFiscale cf, Comune[] listaComuni)
    {
        for (int i = 0; i < 6; i++) {
            if(Character.isDigit(cf.getNome().charAt(i)))
            {
                cf.setValiditaCF(ValiditaCF.INVALIDO);
                break;
            }
        }
        for (int i = 6; i < 8; i++)
        {
            if(!Character.isDigit(cf.getNome().charAt(i)))
            {
                cf.setValiditaCF(ValiditaCF.INVALIDO);
                break;
            }
        }
        if(!mappaMesi.containsValue(cf.getNome().charAt(8)))
        {
            cf.setValiditaCF(ValiditaCF.INVALIDO);
        }
        //negato
        else if(!(Integer.parseInt(cf.getNome().substring(9,10))>0 && Integer.parseInt(cf.getNome().substring(9,10))<32 || Integer.parseInt(cf.getNome().substring(9,10))>40 && Integer.parseInt(cf.getNome().substring(9,10))<72))
        {
            cf.setValiditaCF(ValiditaCF.INVALIDO);
        }

        else if(codiceComuneEsiste(listaComuni,cf))
        {
            cf.setValiditaCF(ValiditaCF.INVALIDO);
        }
        else if(Character.isDigit(cf.getNome().charAt(cf.getNome().length()-1)))
        {
            cf.setValiditaCF(ValiditaCF.INVALIDO);
        }

        else if(cf.getNome().length()!=16)
        {
            cf.setValiditaCF(ValiditaCF.INVALIDO);
        }


    }

    public static boolean codiceComuneEsiste(Comune[] listaComuni,CodiceFiscale cf)
    {
        boolean trovato = false;
        for (int i = 0; i < listaComuni.length; i++) {
            if(listaComuni[i].getCodiceComune().equalsIgnoreCase(cf.getNome().substring(11,14)))
            {
                trovato = true;
            }
        }
        return trovato;
    }








}
