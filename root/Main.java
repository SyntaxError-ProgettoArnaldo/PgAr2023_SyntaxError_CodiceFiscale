package root;

import UnibsLib.AnsiColors;

import javax.xml.stream.XMLStreamException;

import java.io.IOException;



public class Main
{
    public static void creaControllaCF(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF)
    {
        for (int i = 0; i < listaPersone.length; i++) {
            GestioneCF.creaCF(listaPersone[i],listaComuni);
            listaPersone[i].setCodiceFiscale(GestioneCF.cercaCF(listaCF,listaPersone[i].getCodiceFiscale()));
            //System.out.println(listaPersone[i].toString());
            //System.out.println(listaPersone[i].getCodiceFiscale());
        }
    }

    public static void validazione(CodiceFiscale[] listaCF,Comune[] listaComuni)
    {
        for (int i = 0; i < listaCF.length; i++)
        {
            GestioneCF.validazioneCF(listaCF[i],listaComuni);
            //System.out.println(listaCF[i].toString());
        }
    }

    public static int getNumElementiPersona() throws XMLStreamException {
        InterfacciaXML.inizializzaXMLLettura(Costanti.PATH_INPUT_PERSONE);
        return InterfacciaXML.leggiNumeroElementi();
    }
    public static int getNumElementiComuni() throws XMLStreamException {
        GestioneCF.setMappaMesi();
        InterfacciaXML.inizializzaXMLLettura(Costanti.PATH_INPUT_COMUNI);
        return InterfacciaXML.leggiNumeroElementi();
    }
    public static int getNumElementiCF() throws XMLStreamException {
        InterfacciaXML.inizializzaXMLLettura(Costanti.PATH_INPUT_CF);
        return InterfacciaXML.leggiNumeroElementi();
    }

    public static void inputXML(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF) throws XMLStreamException {
        System.out.println(AnsiColors.BLUE+Costanti.MESS_INIZIO_LETTURA_XML +AnsiColors.RESET);
        InterfacciaXML.leggiPersone(listaPersone);
        InterfacciaXML.leggiComuni(listaComuni);
        InterfacciaXML.leggiCF(listaCF);
    }
    public static void inputJSON(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF) throws XMLStreamException, IOException {
        System.out.println(AnsiColors.BLUE+Costanti.MESS_INIZIO_LETTURA_JSON +AnsiColors.RESET);
        InterfacciaJSON.leggiPersone(listaPersone);
        InterfacciaJSON.leggiComuni(listaComuni);
        InterfacciaJSON.leggiCF(listaCF);
    }

    public static void outputXML(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF)
    {
        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        InterfacciaXML.scriviPersone(listaPersone,listaCF);
        System.out.println(AnsiColors.BLUE+Costanti.MESS_FINE_SCRITTURA_XML+AnsiColors.RESET);

    }

    public static void outputJSON(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF)
    {
        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        InterfacciaXML.scriviPersone(listaPersone,listaCF);
        System.out.println(AnsiColors.BLUE+Costanti.MESS_FINE_SCRITTURA_JSON+AnsiColors.RESET);
    }

    public static void main(String[] args) throws XMLStreamException, IOException {

        //DATI XML
        Persona[] listaPersone = new Persona[getNumElementiPersona()];
        Comune[] listaComuni = new Comune[getNumElementiComuni()];
        CodiceFiscale[] listaCF = new CodiceFiscale[getNumElementiCF()];

        //DATI JSON
        Persona[] listaPersone2 = new Persona[InterfacciaJSON.contaPersone()];
        Comune[] listaComuni2 = new Comune[InterfacciaJSON.contaComuni()];
        CodiceFiscale[] listaCF2 = new CodiceFiscale[InterfacciaJSON.contaCF()];

        //INPUT
        inputXML(listaPersone,listaComuni,listaCF);
        inputJSON(listaPersone2,listaComuni2,listaCF2);

        //OUTPUT
        outputXML(listaPersone,listaComuni,listaCF);
        outputJSON(listaPersone2,listaComuni2,listaCF2);

    }

}
