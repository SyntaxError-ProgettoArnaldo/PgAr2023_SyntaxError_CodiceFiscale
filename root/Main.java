package root;

import javax.xml.stream.XMLStreamException;

import static root.InterfacciaXML.*;


public class Main
{
    public static void creaControllaCF(Persona[] listaPersone, Comune[] listaComuni, CodiceFiscale[] listaCF)
    {
        for (int i = 0; i < listaPersone.length; i++) {
            GestioneCF.creaCF(listaPersone[i],listaComuni);
            listaPersone[i].setCodiceFiscale(GestioneCF.cercaCF(listaCF,listaPersone[i].getCodiceFiscale()));
            System.out.println(listaPersone[i].toString());
            //System.out.println(listaPersone[i].getCodiceFiscale());
        }
    }

    public static void validazione(CodiceFiscale[] listaCF,Comune[] listaComuni)
    {
        for (int i = 0; i < listaCF.length; i++)
        {
            GestioneCF.validazioneCF(listaCF[i],listaComuni);
            System.out.println(listaCF[i].toString());
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

    public static void main(String[] args) throws XMLStreamException
    {

        Persona[] listaPersone = new Persona[getNumElementiPersona()];
        leggiPersone(listaPersone);

        Comune[] listaComuni = new Comune[getNumElementiComuni()];
        leggiComuni(listaComuni);

        CodiceFiscale[] listaCF = new CodiceFiscale[getNumElementiCF()];
        leggiCF(listaCF);


        creaControllaCF(listaPersone,listaComuni,listaCF);
        validazione(listaCF,listaComuni);
        scriviPersone(listaPersone,listaCF);

    }

}
