package root;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InterfacciaJSON {

    public static int contaPersone() throws IOException {
        String filePath = "TestFile/InputPersone.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<Map<String,ArrayList<BagOfPrimitives>>>(){}.getType();
        Map<String,ArrayList<BagOfPrimitives>> users = gson.fromJson(fileReader, userType);

        fileReader.close();

        return users.get("persone").size();
    }
    public static void leggiPersone(Persona[] listaPersona) throws IOException {
        String filePath = "TestFile/InputPersone.json";
        FileReader fileReader = new FileReader(new File(filePath));
        Gson gson = new Gson();

        Type userType = new TypeToken<Map<String,ArrayList<BagOfPrimitives>>>(){}.getType();
        Map<String,ArrayList<BagOfPrimitives>> users = gson.fromJson(fileReader, userType);

        for (int i = 0; i < users.get("persone").size(); i++) {
            listaPersona[i]= new Persona();
            listaPersona[i].setNome(users.get("persone").get(i).getNome());
            listaPersona[i].setCognome(users.get("persone").get(i).getCognome());
            listaPersona[i].setSesso(users.get("persone").get(i).getSesso().charAt(0));
            listaPersona[i].setLuogo(users.get("persone").get(i).getLuogo());
            listaPersona[i].setDataDiNascita(LocalDate.parse(users.get("persone").get(i).getData()));
        }

        fileReader.close();
    }
}
