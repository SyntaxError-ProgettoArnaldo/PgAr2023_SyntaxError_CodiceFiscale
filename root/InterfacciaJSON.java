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
        String filePath = Costanti.PATH_INPUT_PERSONE_JSON;  //file output json
        FileReader fileReader = new FileReader(filePath);
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<PersonaJSON>>(){}.getType();  //tipo utente
        ArrayList<PersonaJSON> personeMap = gson.fromJson(fileReader, userType);  //mappa utente

        //riempimento
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
        FileReader fileReader = new FileReader(filePath);
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<Comune>>(){}.getType();
        ArrayList<Comune> comuniMap = gson.fromJson(fileReader, userType);

        listaComuni.addAll(comuniMap);

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+comuniMap.size()+AnsiColors.GREEN+" comini correttamente"+AnsiColors.RESET);

    }


    /**
     * Legge codici fiscali
     */
    public static void leggiCF(ArrayList<CodiceFiscale> listaCF) throws IOException {
        String filePath = Costanti.PATH_INPUT_CF_JSON;
        FileReader fileReader = new FileReader(filePath);
        Gson gson = new Gson();

        Type userType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> cfMap = gson.fromJson(fileReader, userType);

        for (String s : cfMap) {
            listaCF.add(new CodiceFiscale(s));
        }

        fileReader.close();
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+cfMap.size()+AnsiColors.GREEN+" codici fiscali correttamente"+AnsiColors.RESET);

    }

    public static void scriviPersone(ArrayList<Persona> listaPersone, ArrayList<CodiceFiscale> listaCF) throws IOException {
        String filePath = Costanti.NOME_FILE_OUTPUT_JSON;   //file output json
        FileWriter fileWriter = new FileWriter(filePath);  //apertura file
        Gson gson = new Gson();

        //lista e mappa persone
        HashMap<String,String> datiPersone= new HashMap<>();
        ArrayList<HashMap<String,String>> listaMapPersone= new ArrayList<>();

        for (Persona persona : listaPersone) {
            datiPersone.put("nome", persona.getNome());
            datiPersone.put("cognome", persona.getCognome());
            datiPersone.put("sesso", String.valueOf(persona.getSesso()));
            datiPersone.put("luogo_nascita", persona.getLuogo());
            datiPersone.put("data_nascita", persona.getDataDiNascita().toString());
            datiPersone.put("codice_fiscale", persona.getCodiceFiscale());
            listaMapPersone.add(new HashMap<>(datiPersone));
        }

        //codici fiscali invalidi
        ArrayList<String> cfInvalidi = new ArrayList<>();

        //riempimento codici fiscali spaiati
        for (CodiceFiscale codiceFiscale : listaCF) {
            if (codiceFiscale.getValiditaCF().equals(ValiditaCF.INVALIDO)) {
                cfInvalidi.add(codiceFiscale.getNome());
            }
        }

        //codici fiscali spaiati
        ArrayList<String> cfSpaiati = new ArrayList<>();

        //riempimento codici fiscali spaiati
        for (CodiceFiscale codiceFiscale : listaCF) {
            if (codiceFiscale.getValiditaCF().equals(ValiditaCF.SPAIATO)) {
                cfSpaiati.add(codiceFiscale.getNome());
            }
        }

        //riempimento mappa finale
        Map<String, Object> map = new HashMap<>();
        map.put("persona",listaMapPersone);
        map.put("invalidi", cfInvalidi);
        map.put("spaiati", cfSpaiati);
        gson.toJson(map,fileWriter);

        //chiusura file
        fileWriter.close();

    }
}
