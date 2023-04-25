package root;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;

public final class InterfacciaXML
{
    static XMLInputFactory xmlif = null;
    static XMLStreamReader xmlr = null;

    static XMLOutputFactory xmlof = null;
    static XMLStreamWriter xmlw = null;
    public static void inizializzaXMLLettura(String filename)
    {

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());

        }
    }

    public static void inizializzaXMLScrittura(String filename)
    {
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), "utf-8"); xmlw.writeStartDocument("utf-8", "2.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:"); System.out.println(e.getMessage());
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

    public static void scriviPersone(Persona[] listaPersone, CodiceFiscale[] listaCF)
    {
        inizializzaXMLScrittura("codiciPersone.xml");
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement("output"); // scrittura del tag radice <programmaArnaldo>
            xmlw.writeStartElement("persone");
            xmlw.writeAttribute("numero",String.valueOf(listaPersone.length));
            for (int i = 0; i < listaPersone.length; i++) {
                xmlw.writeStartElement("persona"); // scrittura del tag autore...
                xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
                xmlw.writeStartElement("nome");
                xmlw.writeCharacters(listaPersone[i].getNome());
                xmlw.writeEndElement();
                xmlw.writeStartElement("cognome");
                xmlw.writeCharacters(listaPersone[i].getCognome());
                xmlw.writeEndElement();
                xmlw.writeStartElement("sesso");
                xmlw.writeCharacters(String.valueOf(listaPersone[i].getSesso()));
                xmlw.writeEndElement();
                xmlw.writeStartElement("comune_nascita");
                xmlw.writeCharacters(listaPersone[i].getLuogo());
                xmlw.writeEndElement();
                xmlw.writeStartElement("data_nascita");
                xmlw.writeCharacters(listaPersone[i].getDataDiNascita().toString());
                xmlw.writeEndElement();
                xmlw.writeStartElement("codice_fiscale");
                xmlw.writeCharacters(listaPersone[i].getCodiceFiscale());
                xmlw.writeEndElement();
                xmlw.writeEndElement();

            }
            xmlw.writeEndElement(); // chiusura di </programmaArnaldo>

            xmlw.writeStartElement("codici");
            xmlw.writeStartElement("invalidi");

            xmlw.writeAttribute("numero",String.valueOf(getNumeroInvalidi(listaCF)));
            for (int i = 0; i < listaCF.length; i++) {
                if(listaCF[i].getValiditaCF().equals(ValiditaCF.INVALIDO))
                {
                    xmlw.writeStartElement("codice");
                    xmlw.writeCharacters(listaCF[i].getNome());
                    xmlw.writeEndElement();
                }
            }
            xmlw.writeEndElement();

            xmlw.writeStartElement("spaiati");

            xmlw.writeAttribute("numero",String.valueOf(getNumeroSpaiati(listaCF)));
            for (int i = 0; i < listaCF.length; i++) {
                if(listaCF[i].getValiditaCF().equals(ValiditaCF.SPAIATO))
                {
                    xmlw.writeStartElement("codice");
                    xmlw.writeCharacters(listaCF[i].getNome());
                    xmlw.writeEndElement();
                }
            }
            xmlw.writeEndElement();
            xmlw.writeEndElement();



            xmlw.writeEndDocument(); // scrittura della fine del documento
            xmlw.flush();// svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        }
        catch (Exception e)
        {   // se c’è un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura");
        }
    }

    private static int getNumeroSpaiati(CodiceFiscale[] listaCF)
    {
        int count = 0;
        for (int i = 0; i < listaCF.length; i++) {
            if(listaCF[i].getValiditaCF().equals(ValiditaCF.SPAIATO))
            {
                count++;
            }
        }
        return count;
    }

    private static int getNumeroInvalidi(CodiceFiscale[] listaCF)
    {
        int count = 0;
        for (int i = 0; i < listaCF.length; i++) {
            if(listaCF[i].getValiditaCF().equals(ValiditaCF.INVALIDO))
            {
                count++;
            }
        }
        return count;

    }


    public static int leggiNumeroElementi() throws XMLStreamException
    {
        xmlr.next();
        return Integer.parseInt(xmlr.getAttributeValue(0));
    }

}
