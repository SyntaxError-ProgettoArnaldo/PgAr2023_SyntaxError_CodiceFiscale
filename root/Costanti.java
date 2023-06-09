package root;

public class Costanti
{
    //PATH XML
    public static final String PATH_INPUT_PERSONE = "TestFile/InputPersone.xml";
    public static final String PATH_INPUT_CF = "TestFile/CodiciFiscali.xml";
    public static final String PATH_INPUT_COMUNI = "TestFile/Comuni.xml";
    //PATH JSON
    public static final String PATH_INPUT_PERSONE_JSON = "TestFile/InputPersone.json";
    public static final String PATH_INPUT_CF_JSON = "TestFile/CodiciFiscali.json";
    public static final String PATH_INPUT_COMUNI_JSON = "TestFile/Comuni.json";
    //STRINGHE
    public static final String MESS_INIZIO_LETTURA_XML = "Inizio fase di lettura dei file xml...";
    public static final String MESS_INIZIO_LETTURA_JSON = "Inizio fase di lettura dei file json...";
    public static final String MESS_FINE_SCRITTURA_XML = "File di output xml creato correttamente";
    public static final String MESS_FINE_SCRITTURA_JSON = "File di output json creato correttamente";
    public static final String CHAR_CONTROLLO_DEFAULT = "A";
    public static final String NOME_FILE_OUTPUT_XML = "codiciPersone.xml";
    public static final String NOME_FILE_OUTPUT_JSON = "codiciPersone.json";
    public static final String ERR_SCRITTURA = "Errore nella scrittura del file";
    public static final String ERR_INIZ_WRITER = "Errore nell'inizializzazione del writer:";
    public static final String ERR_FILE_INPUT_NOT_FOUND = "File di input non presenti";
    public static final String ERR_OUTPUT = "Errore nel file di output";
    public static final char SESSOF = 'F';

    //INTERI
    public static final int AGG_FEMM = 40;
    public static final int MAX_DOPPIACIFRA = 10;
    public static final int N_CHAR_DA_NON_CONTROLLARE = 1;

    //ELEMENTI
    public static final String TAG_CODICE = "codice";
    public static final String TAG_OUTPUT = "output";
    public static final String TAG_SPAIATI = "spaiati";
    public static final String TAG_INVALIDI = "invalidi";
    public static final String TAG_PERSONE = "persone";
    public static final String TAG_PERSONA = "persona";
    public static final String TAG_ID = "id";
    public static final String TAG_NOME = "nome";
    public static final String TAG_COGNOME = "cognome";
    public static final String TAG_SESSO = "sesso";
    public static final String TAG_COMUNE_NASCITA = "comune_nascita";
    public static final String TAG_DATA_NASCITA = "data_nascita";
    public static final String TAG_CF = "codice_fiscale";
    public static final String TAG_COMUNE = "comune";

    //ATTRIBUTI
    public static final String ATT_NUMERO = "numero";

}
