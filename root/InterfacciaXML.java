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
import UnibsLib.AnsiColors;

public final class InterfacciaXML
{
    static XMLInputFactory xmlif = null;
    static XMLStreamReader xmlr = null;
    static XMLOutputFactory xmlof = null;
    static XMLStreamWriter xmlw = null;

    /**
     * @param filename path del file con cui inizializzare
     */
    public static void inizializzaXMLLettura(String filename)
    {

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println(Costanti.ERR_INIZ_READER);
            System.out.println(e.getMessage());

        }
    }
    /**
     * @param filename path del file con cui inizializzare
     */
    public static void inizializzaXMLScrittura(String filename)
    {
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), "utf-8"); xmlw.writeStartDocument("utf-8", "2.0");
        } catch (Exception e) {
            System.out.println(Costanti.ERR_INIZ_WRITER);
            System.out.println(e.getMessage());
        }
    }


    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     * @param listaPersone
     * @throws XMLStreamException
     */
    public static void leggiPersone(Persona[] listaPersone) throws XMLStreamException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_PERSONE);
        int id=0;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_PERSONA:
                        {
                            id = Integer.parseInt(xmlr.getAttributeValue(0)); //id attributo è la posizione nell array listaPersone
                            listaPersone[id] = new Persona(); //creare la persona
                        }
                        break;
                        case Costanti.TAG_NOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)  //vai fino a caratteri
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setNome(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_COGNOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setCognome(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_SESSO:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setSesso(xmlr.getText().charAt(0));
                            }

                            break;
                        }
                        case Costanti.TAG_COMUNE_NASCITA:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone[id].setLuogo(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_DATA_NASCITA:
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
        id++;
        System.out.println(AnsiColors.GREEN+"Sono state acquisite "+AnsiColors.RED+id+AnsiColors.GREEN+" persone correttamente"+AnsiColors.RESET);
    }

    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     * @param listaComuni
     * @throws XMLStreamException
     */
    public static void leggiComuni(Comune[] listaComuni) throws XMLStreamException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_COMUNI);
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_COMUNE:
                        {
                            id++;
                            listaComuni[id] = new Comune();
                        }
                        break;
                        case Costanti.TAG_NOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaComuni[id].setNomeComune(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_CODICE:
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
        id++;
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+id+AnsiColors.GREEN+" comuni correttamente"+AnsiColors.RESET);
    }
    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     * @param listaCF
     * @throws XMLStreamException
     */
    public static void leggiCF(CodiceFiscale[] listaCF) throws XMLStreamException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_CF);
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_CODICE:
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
        id++;
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+id+AnsiColors.GREEN+" codici fiscali correttamente"+AnsiColors.RESET);

    }

    /**
     * Scrive su un fil xml
     * @param listaPersone
     * @param listaCF
     */
    public static void scriviPersone(Persona[] listaPersone, CodiceFiscale[] listaCF)
    {

        inizializzaXMLScrittura(Costanti.NOME_FILE_OUTPUT_XML);
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement(Costanti.TAG_OUTPUT); // scrittura del tag radice <programmaArnaldo>
            xmlw.writeStartElement(Costanti.TAG_PERSONE);
            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(listaPersone.length));
            for (int i = 0; i < listaPersone.length; i++) {
                xmlw.writeStartElement(Costanti.TAG_PERSONA); // scrittura del tag autore...
                xmlw.writeAttribute(Costanti.TAG_ID, Integer.toString(i)); // ...con attributo id...
                xmlw.writeStartElement(Costanti.TAG_NOME);
                xmlw.writeCharacters(listaPersone[i].getNome());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_COGNOME);
                xmlw.writeCharacters(listaPersone[i].getCognome());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_SESSO);
                xmlw.writeCharacters(String.valueOf(listaPersone[i].getSesso()));
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_COMUNE_NASCITA);
                xmlw.writeCharacters(listaPersone[i].getLuogo());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_DATA_NASCITA);
                xmlw.writeCharacters(listaPersone[i].getDataDiNascita().toString());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_CF);
                xmlw.writeCharacters(listaPersone[i].getCodiceFiscale());
                xmlw.writeEndElement();
                xmlw.writeEndElement();

            }
            xmlw.writeEndElement(); // chiusura di </programmaArnaldo>

            xmlw.writeStartElement(Costanti.TAG_CODICE);
            xmlw.writeStartElement(Costanti.TAG_INVALIDI);

            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(getNumeroInvalidi(listaCF)));
            for (int i = 0; i < listaCF.length; i++) {
                if(listaCF[i].getValiditaCF().equals(ValiditaCF.INVALIDO))
                {
                    xmlw.writeStartElement(Costanti.TAG_CODICE);
                    xmlw.writeCharacters(listaCF[i].getNome());
                    xmlw.writeEndElement();
                }
            }
            xmlw.writeEndElement();

            xmlw.writeStartElement(Costanti.TAG_SPAIATI);

            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(getNumeroSpaiati(listaCF)));
            for (int i = 0; i < listaCF.length; i++) {
                if(listaCF[i].getValiditaCF().equals(ValiditaCF.SPAIATO))
                {
                    xmlw.writeStartElement(Costanti.TAG_CODICE);
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
            System.out.println(Costanti.ERR_SCRITTURA);
        }
    }

    /**
     * Conta quanti cf spaiati ci sono
     * @param listaCF
     * @return
     */
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

    /**
     * Conta quanti cf validi ci sono
     * @param listaCF
     * @return
     */
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


    /**
     * @return numero degli elementi che vengono indicati come primo attributo in un file xml
     * @throws XMLStreamException
     */
    public static int leggiNumeroElementi() throws XMLStreamException
    {
        xmlr.next();
        return Integer.parseInt(xmlr.getAttributeValue(0));
    }

}
