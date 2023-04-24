package root;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public final class InterfacciaXML
{
    static XMLInputFactory xmlif = null;
    static XMLStreamReader xmlr = null;
    public static void inizializzaXML(String filename)
    {

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());

        }
    }
    public static void leggiPersone(Persona[] listaPersone) throws XMLStreamException {
        int id=0;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    System.out.println("Start Read Doc "); break;
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case "persona":
                        {
                            id= Integer.parseInt(xmlr.getAttributeValue(0));
                            listaPersone[id] = new Persona();
                        }
                        break;
                        case "nome":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setNome(xmlr.getText());
                            }

                            break;
                        }
                        case "cognome":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setCognome(xmlr.getText());
                            }

                            break;
                        }
                        case "sesso":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setSesso(xmlr.getText().charAt(0));
                            }

                            break;
                        }
                        case "comune_nascita":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setLuogo(xmlr.getText());
                            }

                            break;
                        }
                        case "data_nascita":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setDataDiNascita(LocalDate.parse(xmlr.getText()));
                            }

                            break;
                        }

                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();

    }

    public static void leggiComuni(Comune[] listaComuni) throws XMLStreamException {
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    System.out.println("Start Read Doc "); break;
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case "comune":
                        {
                            id++;
                            listaComuni[id] = new Comune();
                        }
                        break;
                        case "nome":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaComuni[id].setNomeComune(xmlr.getText());
                            }

                            break;
                        }
                        case "codice":
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaComuni[id].setCodiceComune(xmlr.getText());
                            }

                            break;
                        }


                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();

    }
    public static void leggiCF(CodiceFiscale[] listaCF) throws XMLStreamException {
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    System.out.println("Start Read Doc "); break;
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case "codice":
                        {
                            id++;
                            listaCF[id] = new CodiceFiscale();
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaCF[id].setNome(xmlr.getText());
                            }
                        }
                        break;

                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();


    }


    public static int leggiNumeroElementi() throws XMLStreamException
    {
        xmlr.next();
        return Integer.parseInt(xmlr.getAttributeValue(0));
    }

}
