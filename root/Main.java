package root;

import javax.xml.stream.XMLStreamException;

import java.io.IOException;

import static root.InterfacciaXML.*;


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

    public static void main(String[] args) throws XMLStreamException, IOException {

        Persona[] listaPersone = new Persona[getNumElementiPersona()];
        leggiPersone(listaPersone);

        Comune[] listaComuni = new Comune[getNumElementiComuni()];
        leggiComuni(listaComuni);

        CodiceFiscale[] listaCF = new CodiceFiscale[getNumElementiCF()];
        leggiCF(listaCF);


        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        InterfacciaXML.scriviPersone(listaPersone,listaCF);

        Persona[] listaPersone2 = new Persona[InterfacciaJSON.contaPersone()];
        InterfacciaJSON.leggiPersone(listaPersone2);

        Comune[] listaComuni2 = new Comune[InterfacciaJSON.contaComuni()];
        InterfacciaJSON.leggiComuni(listaComuni2);

        CodiceFiscale[] listaCF2 = new CodiceFiscale[InterfacciaJSON.contaCF()];
        InterfacciaJSON.leggiCF(listaCF2);

        creaControllaCF(listaPersone2,listaComuni2,listaCF2);
        validazione(listaCF2,listaComuni2);

        InterfacciaJSON.scriviPersone(listaPersone2,listaCF2);
    }

}
