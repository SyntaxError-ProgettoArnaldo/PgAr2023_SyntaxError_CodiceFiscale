package root;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public final class InterfacciaJSON {

    public static int contaPersone() throws IOException {
        String filePath = "TestFile/InputPersone.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<PersonaJSON>>>(){}.getType();
        HashMap<String,ArrayList<PersonaJSON>> personeMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return personeMap.get("persone").size();
    }
    public static void leggiPersone(Persona[] listaPersona) throws IOException {
        String filePath = "TestFile/InputPersone.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<PersonaJSON>>>(){}.getType();
        HashMap<String,ArrayList<PersonaJSON>> personeMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < personeMap.get("persone").size(); i++) {
            listaPersona[i]= new Persona();
            listaPersona[i].setNome(personeMap.get("persone").get(i).getNome());
            listaPersona[i].setCognome(personeMap.get("persone").get(i).getCognome());
            listaPersona[i].setSesso(personeMap.get("persone").get(i).getSesso());
            listaPersona[i].setLuogo(personeMap.get("persone").get(i).getLuogo());
            listaPersona[i].setDataDiNascita(LocalDate.parse(personeMap.get("persone").get(i).getData()));
        }

        fileReader.close();
    }

    public static void leggiComuni(Comune[] listaComuni) throws IOException {
        String filePath = "TestFile/Comuni.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<Comune>>>(){}.getType();
        HashMap<String,ArrayList<Comune>> comuniMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < comuniMap.get("comuni").size(); i++) {
            listaComuni[i]= new Comune();
            listaComuni[i].setNomeComune(comuniMap.get("comuni").get(i).getNomeComune());
            listaComuni[i].setCodiceComune(comuniMap.get("comuni").get(i).getCodiceComune());
        }

        fileReader.close();
    }

    public static int contaComuni() throws IOException {
        String filePath = "TestFile/Comuni.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<Comune>>>(){}.getType();
        HashMap<String,ArrayList<Comune>> comuniMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return comuniMap.get("comuni").size();
    }


    public static int contaCF() throws IOException {
        String filePath = "TestFile/CodiciFiscali.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<String>>>(){}.getType();
        HashMap<String,ArrayList<String>> cfMap = gson.fromJson(fileReader, userType);

        fileReader.close();

        return cfMap.get("codici").size();
    }

    public static void leggiCF(CodiceFiscale[] listaCF) throws IOException {
        String filePath = "TestFile/CodiciFiscali.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<HashMap<String,ArrayList<String>>>(){}.getType();
        HashMap<String,ArrayList<String>> cfMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < cfMap.get("codici").size(); i++) {
            listaCF[i]= new CodiceFiscale();
            listaCF[i].setNome(cfMap.get("codici").get(i));
        }

        fileReader.close();
    }

    public static void scriviPersone(Persona[] listaPersone, CodiceFiscale[] listaCF) throws IOException {
        String filePath = "codiciPersone.json";
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
