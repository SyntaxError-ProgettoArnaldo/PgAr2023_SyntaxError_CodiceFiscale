package root;

import UnibsLib.AnsiColors;

import javax.xml.stream.XMLStreamException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main
{
    /**
     * Crea il codice fiscale per ogni persona e lo assegna
     */
    public static void creaControllaCF(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF)
    {
        for (Persona persona : listaPersone) {
            GestioneCF.creaCF(persona, listaComuni);
            persona.setCodiceFiscale(GestioneCF.cercaCF(listaCF, persona.getCodiceFiscale()));
        }
    }

    /**
     * Valida codice fiscale
     */
    public static void validazione(ArrayList<CodiceFiscale> listaCF, ArrayList<Comune> listaComuni)
    {
        for (CodiceFiscale codiceFiscale : listaCF) {
            GestioneCF.validazioneCF(codiceFiscale, listaComuni);
            //System.out.println(listaCF[i].toString());
        }
    }

    public static void inputXML(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF) throws XMLStreamException, FileNotFoundException {
        System.out.println(AnsiColors.BLUE+Costanti.MESS_INIZIO_LETTURA_XML +AnsiColors.RESET);
        InterfacciaXML.leggiPersone(listaPersone);
        InterfacciaXML.leggiComuni(listaComuni);
        InterfacciaXML.leggiCF(listaCF);

    }
    public static void inputJSON(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF) throws IOException {
        System.out.println(AnsiColors.BLUE+Costanti.MESS_INIZIO_LETTURA_JSON +AnsiColors.RESET);
        InterfacciaJSON.leggiPersone(listaPersone);
        InterfacciaJSON.leggiComuni(listaComuni);
        InterfacciaJSON.leggiCF(listaCF);
    }

    public static void outputXML(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF)
    {
        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        InterfacciaXML.scriviPersone(listaPersone,listaCF);
        System.out.println(AnsiColors.BLUE+Costanti.MESS_FINE_SCRITTURA_XML+AnsiColors.RESET);

    }

    public static void outputJSON(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF) throws IOException {
        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        InterfacciaJSON.scriviPersone(listaPersone,listaCF);
        System.out.println(AnsiColors.BLUE+Costanti.MESS_FINE_SCRITTURA_JSON+AnsiColors.RESET);
    }

    public static void main(String[] args){

        //DATI XML
        ArrayList<Persona> listaPersoneXML = new ArrayList<>();
        ArrayList<Comune> listaComuniXML = new ArrayList<>();
        ArrayList<CodiceFiscale> listaCFXML = new ArrayList<>();

        //DATI JSON
        ArrayList<Persona> listaPersoneJSON = new ArrayList<>();
        ArrayList<Comune> listaComuniJSON = new ArrayList<>();
        ArrayList<CodiceFiscale> listaCFJSON = new ArrayList<>();

        //INPUT
        try{
            inputXML(listaPersoneXML,listaComuniXML,listaCFXML);
            inputJSON(listaPersoneJSON,listaComuniJSON,listaCFJSON);
        }catch (XMLStreamException | IOException exception)
        {
            System.out.println(AnsiColors.RED+Costanti.ERR_FILE_INPUT_NOT_FOUND+AnsiColors.RESET);
            return;
        }

        //OUTPUT
        try{
            outputXML(listaPersoneXML,listaComuniXML,listaCFXML);
            outputJSON(listaPersoneJSON,listaComuniJSON,listaCFJSON);
        }catch (IOException exception)
        {
            System.out.println(AnsiColors.RED+Costanti.ERR_OUTPUT+AnsiColors.RESET);
        }


    }

}
