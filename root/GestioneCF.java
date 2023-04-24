package root;

public final class GestioneCF
{
    public static String creaCF(Persona persona)
    {
        String CF = "";
        CF+=creaCognome(persona);
        CF+=creaNome(persona);
        return CF;

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

        /*     if(contaConsonanti(persona.getCognome())>2)
        {
            for (int i = 0; i < persona.getCognome().length(); i++)
            {
                if(isConsonante(persona.getCognome().charAt(i)))
                {
                    cognome+= persona.getCognome().charAt(i);
                    conta++;
                    if(conta==3) break;
                }
            }
        }
        else if(persona.getCognome().length()<3)
        {
            cognome+=persona.getCognome();
            for (int i = 0; i < 3-persona.getCognome().length(); i++)
            {
                cognome+="X";
            }
        }
        else    {
            for (int i = 0; i < persona.getCognome().length(); i++)
            {
             if(isConsonante(persona.getCognome().charAt(i)))
             {
                 cognome+=persona.getCognome().charAt(i);
             }
            }
            for (int i = 0; i < persona.getCognome().length() && cognome.length()<3; i++) {
                if(!isConsonante(persona.getCognome().charAt(i)))
                {
                    cognome+=persona.getCognome().charAt(i);
                }
            }
        }*/

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

}
