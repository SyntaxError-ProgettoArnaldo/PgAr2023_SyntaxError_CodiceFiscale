package root;

import javax.xml.stream.XMLStreamException;

public class Main
{
    public static void main(String[] args) throws XMLStreamException
    {
        GestioneCF.setMappaMesi();

        InterfacciaXML.inizializzaXMLLettura("TestFile/InputPersone.xml");
        Persona[] listaPersone = new Persona[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiPersone(listaPersone);


        InterfacciaXML.inizializzaXMLLettura("TestFile/Comuni.xml");
        Comune[] listaComuni = new Comune[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiComuni(listaComuni);




        InterfacciaXML.inizializzaXMLLettura("TestFile/CodiciFiscali.xml");
        CodiceFiscale[] listaCF = new CodiceFiscale[InterfacciaXML.leggiNumeroElementi()];
        InterfacciaXML.leggiCF(listaCF);



        for (int i = 0; i < listaPersone.length; i++) {
            GestioneCF.creaCF(listaPersone[i],listaComuni);
            listaPersone[i].setCodiceFiscale(GestioneCF.cercaCF(listaCF,listaPersone[i].getCodiceFiscale()));
            System.out.println(listaPersone[i].toString());
            //System.out.println(listaPersone[i].getCodiceFiscale());
        }

        for (int i = 0; i < listaCF.length; i++)
        {
            GestioneCF.validazioneCF(listaCF[i],listaComuni);
            System.out.println(listaCF[i].toString());
        }

        InterfacciaXML.scriviPersone(listaPersone,listaCF);

    }

}
