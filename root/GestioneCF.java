package root;

public final class GestioneCF
{
    public static String creaCF(Persona persona)
    {
        String CF = "";
        CF+=creaCognome(persona);
        return CF;

    }
    public static int contaConsonanti(String stringa)
    {
        int count = 0;
        char[] vocali = {'a','e','i','o','u'};
        for (int i = 0; i < stringa.length(); i++) {
            for (int j=0 ; j<vocali.length;j++)
            {
                if(vocali[j] == stringa.charAt(i))
                    count++;
            }
        }
        return stringa.length()-count;



    }
    public static String creaCognome(Persona persona)
    {
        String cognome="";
        int conta=0;
        if(contaConsonanti(persona.getCognome())>3)
        {
            for (int i = 0; i < persona.getCognome().length(); i++) {
                if(isConsonante(persona.getCognome().charAt(i)))    {
                    cognome+= persona.getCognome().charAt(i);
                    conta++;
                    if(conta==3) break;
                }
            }
        }
        return cognome;
    }

    public static boolean isConsonante(char carattere){
        char[] vocali = {'a','e','i','o','u'};

        for (int i = 0; i < vocali.length; i++) {
            if(carattere==vocali[i])    {
                return false;
            }
        }
        return true;
    }



}
