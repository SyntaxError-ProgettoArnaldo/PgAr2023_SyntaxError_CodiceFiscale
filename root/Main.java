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
            System.out.println(listaPersone[i].getNome());
        }


        InterfacciaXML.inizializzaXML("TestFile/Comuni.xml");
        Comune[] listaComuni = new Comune[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.inizializzaXML("TestFile/CodiciFiscali.xml");
        CodiceFiscale[] listaCF = new CodiceFiscale[InterfacciaXML.leggiNumeroElementi()];



    }

}
