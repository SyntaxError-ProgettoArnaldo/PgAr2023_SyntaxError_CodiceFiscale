package root;

import UnibsLib.AnsiColors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public final class InterfacciaJSON {

    public static void leggiPersone(ArrayList<Persona> listaPersona) throws IOException {
        String filePath = Costanti.PATH_INPUT_PERSONE_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<PersonaJSON>>(){}.getType();
        ArrayList<PersonaJSON> personeMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < personeMap.size(); i++) {
            listaPersona.add(new Persona());
            listaPersona.get(i).setNome(personeMap.get(i).getNome());
            listaPersona.get(i).setCognome(personeMap.get(i).getCognome());
            listaPersona.get(i).setSesso(personeMap.get(i).getSesso());
            listaPersona.get(i).setLuogo(personeMap.get(i).getLuogo());
            listaPersona.get(i).setDataDiNascita(LocalDate.parse(personeMap.get(i).getData()));
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono state acquisite "+AnsiColors.RED+personeMap.size()+AnsiColors.GREEN+" persone correttamente"+AnsiColors.RESET);

    }

    public static void leggiComuni(ArrayList<Comune> listaComuni) throws IOException {
        String filePath = Costanti.PATH_INPUT_COMUNI_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<Comune>>(){}.getType();
        ArrayList<Comune> comuniMap = gson.fromJson(fileReader, userType);

        listaComuni.addAll(comuniMap);

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+comuniMap.size()+AnsiColors.GREEN+" comini correttamente"+AnsiColors.RESET);

    }


    public static void leggiCF(ArrayList<CodiceFiscale> listaCF) throws IOException {
        String filePath = Costanti.PATH_INPUT_CF_JSON;
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> cfMap = gson.fromJson(fileReader, userType);

        for (int i = 0; i < cfMap.size(); i++) {
            listaCF.add(new CodiceFiscale(cfMap.get(i)));
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+cfMap.size()+AnsiColors.GREEN+" codici fiscali correttamente"+AnsiColors.RESET);

    }

    public static void scriviPersone(ArrayList<Persona> listaPersone, ArrayList<CodiceFiscale> listaCF) throws IOException {
        String filePath = Costanti.NOME_FILE_OUTPUT_JSON;
        FileWriter fileWriter = new FileWriter(new File(filePath));
        Gson gson = new Gson();

        HashMap<String,ArrayList<HashMap<String,String>>> personaMap = new HashMap<>();

        HashMap<String,String> datiPersone= new HashMap<>();
        ArrayList<HashMap<String,String>> listaMapPersone= new ArrayList<>();

        for (int i = 0; i < listaPersone.size(); i++) {
            datiPersone.put("nome",listaPersone.get(i).getNome());
            datiPersone.put("cognome",listaPersone.get(i).getCognome());
            datiPersone.put("sesso",String.valueOf(listaPersone.get(i).getSesso()));
            datiPersone.put("luogo_nascita",listaPersone.get(i).getLuogo());
            datiPersone.put("data_nascita",listaPersone.get(i).getDataDiNascita().toString());
            datiPersone.put("codice_fiscale",listaPersone.get(i).getCodiceFiscale());
            listaMapPersone.add(new HashMap<>(datiPersone));
        }

        personaMap.put("persona",listaMapPersone);

        HashMap<String,ArrayList<String>> invalidiMap = new HashMap<>();
        ArrayList<String> cfInvalidi = new ArrayList<>();

        for (int i = 0; i < listaCF.size(); i++) {
            if(listaCF.get(i).getValiditaCF().equals(ValiditaCF.INVALIDO))  {
                cfInvalidi.add(listaCF.get(i).getNome());
            }
        }

        invalidiMap.put("invalidi",cfInvalidi);

        HashMap<String,ArrayList<String>> spaiatiMap = new HashMap<>();
        ArrayList<String> cfSpaiati = new ArrayList<>();

        for (int i = 0; i < listaCF.size(); i++) {
            if(listaCF.get(i).getValiditaCF().equals(ValiditaCF.SPAIATO))  {
                cfSpaiati.add(listaCF.get(i).getNome());
            }
        }

        spaiatiMap.put("spaiati",cfSpaiati);

        Map<String, Object> map = new HashMap<>();
        map.put("persona",listaMapPersone);
        map.put("invalidi", invalidiMap);
        map.put("spaiati", spaiatiMap);
        gson.toJson(map,fileWriter);

        fileWriter.close();

    }
}
