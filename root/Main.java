package root;

import javax.xml.stream.XMLStreamException;

public class Main
{
    public static void main(String[] args) throws XMLStreamException
    {
        InterfacciaXML.inizializzaXML("TestFile/InputPersone.xml");
        Persona[] listaPersone = new Persona[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiPersone(listaPersone);

        for (int i = 0; i< listaPersone.length ; i++) {
            System.out.println(listaPersone[i].toString());
        }

        InterfacciaXML.inizializzaXML("TestFile/Comuni.xml");
        Comune[] listaComuni = new Comune[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiComuni(listaComuni);

        for (int i = 0; i< listaComuni.length ; i++) {
            System.out.println(listaComuni[i].toString());
        }

        InterfacciaXML.inizializzaXML("TestFile/CodiciFiscali.xml");
        CodiceFiscale[] listaCF = new CodiceFiscale[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiCF(listaCF);

        for (int i = 0; i< listaCF.length ; i++) {
            System.out.println(listaCF[i].toString());
        }

        for (int i = 0; i < listaPersone.length; i++) {
            System.out.println( GestioneCF.creaCF(listaPersone[i]));
        }

    }

}
