package root;

import UnibsLib.AnsiColors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public final class InterfacciaJSON {

    public static int contaPersone() throws IOException {
        String filePath = Costanti.PATH_INPUT_PERSONE_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<PersonaJSON>>(){}.getType();
        ArrayList<PersonaJSON> personeMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return personeMap.size();
    }
    public static void leggiPersone(Persona[] listaPersona) throws IOException {
        String filePath = Costanti.PATH_INPUT_PERSONE_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<PersonaJSON>>(){}.getType();
        ArrayList<PersonaJSON> personeMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < personeMap.size(); i++) {
            listaPersona[i]= new Persona();
            listaPersona[i].setNome(personeMap.get(i).getNome());
            listaPersona[i].setCognome(personeMap.get(i).getCognome());
            listaPersona[i].setSesso(personeMap.get(i).getSesso());
            listaPersona[i].setLuogo(personeMap.get(i).getLuogo());
            listaPersona[i].setDataDiNascita(LocalDate.parse(personeMap.get(i).getData()));
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono state acquisite "+AnsiColors.RED+personeMap.size()+AnsiColors.GREEN+" persone correttamente"+AnsiColors.RESET);

    }

    public static void leggiComuni(Comune[] listaComuni) throws IOException {
        String filePath = Costanti.PATH_INPUT_COMUNI_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<Comune>>(){}.getType();
        ArrayList<Comune> comuniMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < comuniMap.size(); i++) {
            listaComuni[i]= new Comune();
            listaComuni[i].setNomeComune(comuniMap.get(i).getNomeComune());
            listaComuni[i].setCodiceComune(comuniMap.get(i).getCodiceComune());
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+comuniMap.size()+AnsiColors.GREEN+" comini correttamente"+AnsiColors.RESET);

    }

    public static int contaComuni() throws IOException {
        String filePath = Costanti.PATH_INPUT_COMUNI_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<Comune>>(){}.getType();
        ArrayList<Comune> comuniMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return comuniMap.size();
    }


    public static int contaCF() throws IOException {
        String filePath = Costanti.PATH_INPUT_CF_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> cfMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return cfMap.size();
    }

    public static void leggiCF(CodiceFiscale[] listaCF) throws IOException {
        String filePath = Costanti.PATH_INPUT_CF_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> cfMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < cfMap.size(); i++) {
            listaCF[i]= new CodiceFiscale();
            listaCF[i].setNome(cfMap.get(i));
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+cfMap.size()+AnsiColors.GREEN+" codici fiscali correttamente"+AnsiColors.RESET);

    }

    public static void scriviPersone(Persona[] listaPersone, CodiceFiscale[] listaCF) throws IOException {
        String filePath = Costanti.NOME_FILE_OUTPUT_JSON;
        FileWriter fileWriter = new FileWriter(new File(filePath));
        Gson gson = new Gson();

        HashMap<String,ArrayList<HashMap<String,String>>> personaMap = new HashMap<>();

        HashMap<String,String> datiPersone= new HashMap<>();
        ArrayList<HashMap<String,String>> listaMapPersone= new ArrayList<>();

        for (int i = 0; i < listaPersone.length; i++) {
            datiPersone.put("nome",listaPersone[i].getNome());
            datiPersone.put("cognome",listaPersone[i].getCognome());
            datiPersone.put("sesso",String.valueOf(listaPersone[i].getSesso()));
            datiPersone.put("luogo_nascita",listaPersone[i].getLuogo());
            datiPersone.put("data_nascita",listaPersone[i].getDataDiNascita().toString());
            datiPersone.put("codice_fisclae",listaPersone[i].getCodiceFiscale());
            listaMapPersone.add(new HashMap<>(datiPersone));
        }

        personaMap.put("persona",listaMapPersone);

        HashMap<String,ArrayList<String>> invalidiMap = new HashMap<>();
        ArrayList<HashMap<String,String>> cfInvalidi = new ArrayList<>();
        HashMap<String,String> datiInvalidi = new HashMap<>();

        for (int i = 0; i < listaCF.length; i++) {
            if(listaCF[i].getValiditaCF().equals(ValiditaCF.INVALIDO))  {
                datiInvalidi.put("",listaCF[i].getNome());
                cfInvalidi.add(new HashMap<>(datiInvalidi));
            }
        }


        personaMap.put("invalidi",cfInvalidi);


        HashMap<String,ArrayList<String>> spaiatiMap = new HashMap<>();
        ArrayList<String> cfSpaiati = new ArrayList<>();

        for (int i = 0; i < listaCF.length; i++) {
            if(listaCF[i].getValiditaCF().equals(ValiditaCF.SPAIATO))  {
                cfSpaiati.add(listaCF[i].getNome());
            }
        }

        spaiatiMap.put("spaiati",cfSpaiati);

        gson.toJson(personaMap, fileWriter);
        gson.toJson(spaiatiMap,fileWriter);

        fileWriter.close();

    }
}
