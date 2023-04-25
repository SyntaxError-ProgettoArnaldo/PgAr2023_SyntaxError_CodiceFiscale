package root;
import java.util.HashMap;

public final class GestioneCF
{

    //mappa dei mesi per creare il carattere relativo al mese di nascita
    //fonte: wikipedia.org
    static HashMap<String,Character> mappaMesi = new HashMap<>();

    /**
     * Inizializza la mappaMesi con i dati presi da wikipedia
     */
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


    /**
     * Genera e inserisce il codice fiscale
     * @param persona La persona di cui si vuole generare il codice fiscale
     * @param listaComuni Lista dei comuni e dei loro relativi codici
     */
    public static void creaCF(Persona persona,Comune[] listaComuni)
    {
        setMappaMesi();
        String cf = "";
        cf+= creaCodiceCognome(persona);
        cf+= creaCodiceNome(persona);
        cf+= creaCodiceAnno(persona);
        cf+= creaCodiceMese(persona);
        cf+= creaCodiceGiornoSesso(persona);
        cf+= creaCodiceComune(persona,listaComuni);
        cf+=Costanti.CHAR_CONTROLLO_DEFAULT;
        persona.setCodiceFiscale(cf);

    }

    /**
     * Genera il codice relativo al comune di nascita della persona
     * @param persona Persona di cui si vuole generare il codice
     * @param listaComuni
     * @return il codice
     */
    private static String creaCodiceComune(Persona persona, Comune[] listaComuni)
    {
        for (int i = 0; i < listaComuni.length; i++) {
            if(persona.getLuogo().equalsIgnoreCase(listaComuni[i].getNomeComune()))
            {
                return listaComuni[i].getCodiceComune();
            }
        }
        return null;
    }

    /**
     * Crea il codice relativo al giorno ed al sesso della persona
     * @param persona Persona di cui si vuole generare il codice
     * @return il codice
     */
    private static String creaCodiceGiornoSesso(Persona persona)
    {
        if(persona.getSesso()==Costanti.SESSOF)
        {
            return String.valueOf(persona.getDataDiNascita().getDayOfMonth()+Costanti.AGG_FEMM);
        }
        if(persona.getDataDiNascita().getDayOfMonth()<Costanti.MAX_DOPPIACIFRA)
        {
            return "0"+persona.getDataDiNascita().getDayOfMonth();
        }
        return String.valueOf(persona.getDataDiNascita().getDayOfMonth());


    }

    /**
     * Genera il codice relativo al mese di nascita
     * @param persona Persona di cui si vuole generare il codice
     * @return
     */
    private static char creaCodiceMese(Persona persona)
    {
        return mappaMesi.get(String.valueOf(persona.getDataDiNascita().getMonth()));

    }

    /**
     * @param s La stringa iniziale
     * @param n Il numero di ultii caratteri che si vuole ottenere
     * @return gli ultimi caratteri inseriti in una stringa
     */
    public static String prendiUltimiCaratteri(String s, int n)
    {
        if (s == null || n > s.length()) {
            return s;
        }
        return s.substring(s.length() - n);
    }


    /**
     * Genera il codice relativo all anno di nascita
     * @param persona Persona di cui si vuole generare il codice
     * @return
     */
    private static String creaCodiceAnno(Persona persona) {
        return prendiUltimiCaratteri(String.valueOf(persona.getDataDiNascita().getYear()),2);
    }

    /**
     * Genera il codice relativo al cognome
     * @param persona Persona di cui si vuole generare il codice
     * @return il codice
     */
    public static String creaCodiceCognome(Persona persona)
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

    /**
     * Genera il codice relativo al nome
     * @param persona Persona di cui si vuole generare il codice
     * @return il codice
     */
    public static String creaCodiceNome(Persona persona)
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
        int count = 0;  //SOMMA VOCALI
        char[] vocali = {'a','e','i','o','u','A','E','I','O','U'};
        for (int i = 0; i < stringa.length(); i++) {
            for (int j=0 ; j<vocali.length;j++)
            {
                if(vocali[j] == stringa.charAt(i))
                    count++;
            }
        }
        return stringa.length()-count;  //consonanti = lenght stringa iniziale - vocali
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

    /**
     * Controlla se un
     * @param codiciFiscali
     * @param cf
     * @return
     */
    public static String cercaCF(CodiceFiscale[] codiciFiscali, String cf)
    {
        boolean cfTrovato = false;
        for (int i = 0; i < codiciFiscali.length; i++) {
            if(codiciFiscali[i].getNome().substring(0,codiciFiscali[i].getNome().length()-Costanti.N_CHAR_DA_NON_CONTROLLARE).equalsIgnoreCase(cf.substring(0,cf.length()-Costanti.N_CHAR_DA_NON_CONTROLLARE)))
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

    /**
     * Controlla se un codice fiscale è valido oppure no
     * Nel caso non lo sia verra asseganto nell oggetto attributo validitaCF su INVALIDO
     * @param cf
     * @param listaComuni
     */
    public static void validazioneCF(CodiceFiscale cf, Comune[] listaComuni)
    {
        for (int i = 0; i < 6; i++) {
            if(Character.isDigit(cf.getNome().charAt(i)))
            {
                cf.setValiditaCF(ValiditaCF.INVALIDO);
                break;
            }
        }

        for (int i = 0; i < 2; i++) {
            if(!isConsonante(cf.getNome().charAt(i)) && (isConsonante(cf.getNome().charAt(i+1))) && cf.getNome().charAt(i+1)!='X')
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

        for (int i = 3; i < 5; i++) {
            if(!isConsonante(cf.getNome().charAt(i)) && isConsonante(cf.getNome().charAt(i+1)) && cf.getNome().charAt(i+1)!='X')
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
        else if(!(Integer.parseInt(cf.getNome().substring(9,11))>0 && Integer.parseInt(cf.getNome().substring(9,11))<32 || Integer.parseInt(cf.getNome().substring(9,11))>40 && Integer.parseInt(cf.getNome().substring(9,11))<72))
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

    /**
     * Controlla se il codice comune esiste nella lista comuni
     * @param listaComuni
     * @param cf
     * @return true/false se è stato trovato
     */
    public static boolean codiceComuneEsiste(Comune[] listaComuni,CodiceFiscale cf)
    {
        for (int i = 0; i < listaComuni.length; i++) {
            if(listaComuni[i].getCodiceComune().equalsIgnoreCase(cf.getNome().substring(11,15)))
            {
                return true;
            }
        }
        return false;
    }








}
