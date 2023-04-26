package root;

import UnibsLib.AnsiColors;

import javax.xml.stream.XMLStreamException;

import java.io.IOException;
import java.util.ArrayList;


public class Main
{
    public static void creaControllaCF(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF)
    {
        for (int i = 0; i < listaPersone.size(); i++) {
            GestioneCF.creaCF(listaPersone.get(i),listaComuni);
            listaPersone.get(i).setCodiceFiscale(GestioneCF.cercaCF(listaCF,listaPersone.get(i).getCodiceFiscale()));
            //System.out.println(listaPersone[i].toString());
            //System.out.println(listaPersone[i].getCodiceFiscale());
        }
    }

    public static void validazione(ArrayList<CodiceFiscale> listaCF, ArrayList<Comune> listaComuni)
    {
        for (int i = 0; i < listaCF.size(); i++)
        {
            GestioneCF.validazioneCF(listaCF.get(i),listaComuni);
            //System.out.println(listaCF[i].toString());
        }
    }

    public static void inputXML(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF) throws XMLStreamException {
        System.out.println(AnsiColors.BLUE+Costanti.MESS_INIZIO_LETTURA_XML +AnsiColors.RESET);
        InterfacciaXML.leggiPersone(listaPersone);
        InterfacciaXML.leggiComuni(listaComuni);
        InterfacciaXML.leggiCF(listaCF);
    }
    public static void inputJSON(ArrayList<Persona> listaPersone, ArrayList<Comune> listaComuni, ArrayList<CodiceFiscale> listaCF) throws XMLStreamException, IOException {
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

    public static void main(String[] args) throws XMLStreamException, IOException {

        //DATI XML
        ArrayList<Persona> listaPersoneXML = new ArrayList<>();
        ArrayList<Comune> listaComuniXML = new ArrayList<>();
        ArrayList<CodiceFiscale> listaCFXML = new ArrayList<>();

        //DATI JSON
        ArrayList<Persona> listaPersoneJSON = new ArrayList<>();
        ArrayList<Comune> listaComuniJSON = new ArrayList<>();
        ArrayList<CodiceFiscale> listaCFJSON = new ArrayList<>();

        //INPUT
        inputXML(listaPersoneXML,listaComuniXML,listaCFXML);
        inputJSON(listaPersoneJSON,listaComuniJSON,listaCFJSON);

        //OUTPUT
        outputXML(listaPersoneXML,listaComuniXML,listaCFXML);
        outputJSON(listaPersoneJSON,listaComuniJSON,listaCFJSON);

    }

}
