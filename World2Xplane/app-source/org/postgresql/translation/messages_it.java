/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_it extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL JDBC Driver 8.2\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2006-06-23 17:18+0200\nPO-Revision-Date: 2006-06-23 17:25+0200\nLast-Translator: Giuseppe Sacco <eppesuig@debian.org>\nLanguage-Team: Italian <tp@lists.linux.it>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Si è verificato un errore caricando le impostazioni predefinite da «driverconfig.properties».");
/*   9 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Qualcosa di insolito si è verificato causando il fallimento del driver. Per favore riferire all''autore del driver questa eccezione.");
/*  10 */     hashtable.put("Connection attempt timed out.", "Il tentativo di connessione è scaduto.");
/*  11 */     hashtable.put("Interrupted while attempting to connect.", "Si è verificata una interruzione durante il tentativo di connessione.");
/*  12 */     hashtable.put("Method {0} is not yet implemented.", "Il metodo «{0}» non è stato ancora implementato.");
/*  13 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Non è stato possibile attivare la connessione utilizzando il protocollo richiesto {0}.");
/*  14 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Il flusso di input è stato interrotto, sono arrivati {1} byte al posto dei {0} attesi.");
/*  15 */     hashtable.put("Expected an EOF from server, got: {0}", "Ricevuto dal server «{0}» mentre era atteso un EOF");
/*  16 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Sequenza UTF-8 illegale: il byte {0} di una sequenza di {1} byte non è 10xxxxxx: {2}");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Sequenza UTF-8 illegale: {0} byte utilizzati per codificare un valore di {1} byte: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Sequenza UTF-8 illegale: il byte iniziale è {0}: {1}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Sequenza UTF-8 illegale: il valore finale è fuori dall''intervallo permesso: {0}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Sequenza UTF-8 illegale: il valore è finale è un surrogato: {0}");
/*  21 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "Non è possibile convertire una istanza di «{0}» nel tipo «{1}»");
/*  22 */     hashtable.put("The driver does not support SSL.", "Il driver non supporta SSL.");
/*  23 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Connessione rifiutata. Controllare che il nome dell''host e la porta siano corretti, e che il server (postmaster) sia in esecuzione con l''opzione -i, che abilita le connessioni attraverso la rete TCP/IP.");
/*  24 */     hashtable.put("The connection attempt failed.", "Il tentativo di connessione è fallito.");
/*  25 */     hashtable.put("The server does not support SSL.", "Il server non supporta SSL.");
/*  26 */     hashtable.put("An error occured while setting up the SSL connection.", "Si è verificato un errore impostando la connessione SSL.");
/*  27 */     hashtable.put("Connection rejected: {0}.", "Connessione rifiutata: {0}.");
/*  28 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Il server ha richiesto l''autenticazione con password, ma tale password non è stata fornita.");
/*  29 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "L''autenticazione di tipo {0} non è supportata. Verificare che nel file di configurazione pg_hba.conf sia presente l''indirizzo IP o la sottorete del client, e che lo schema di autenticazione utilizzato sia supportato dal driver.");
/*  30 */     hashtable.put("Protocol error.  Session setup failed.", "Errore di protocollo. Impostazione della sessione fallita.");
/*  31 */     hashtable.put("Backend start-up failed: {0}.", "Attivazione del backend fallita: {0}.");
/*  32 */     hashtable.put("An unexpected result was returned by a query.", "Un risultato inaspettato è stato ricevuto dalla query.");
/*  33 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Indice di colonna, {0}, è maggiore del numero di colonne {1}.");
/*  34 */     hashtable.put("No value specified for parameter {0}.", "Nessun valore specificato come parametro {0}.");
/*  35 */     hashtable.put("Expected command status BEGIN, got {0}.", "Lo stato del comando avrebbe dovuto essere BEGIN, mentre invece è {0}.");
/*  36 */     hashtable.put("Unexpected command status: {0}.", "Stato del comando non previsto: {0}.");
/*  37 */     hashtable.put("An I/O error occured while sending to the backend.", "Si è verificato un errore di I/O nella spedizione di dati al server.");
/*  38 */     hashtable.put("Unknown Response Type {0}.", "Risposta di tipo sconosciuto {0}.");
/*  39 */     hashtable.put("Ran out of memory retrieving query results.", "Fine memoria scaricando i risultati della query.");
/*  40 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Impossibile interpretare il numero degli aggiornamenti nel «tag» di completamento del comando: {0}.");
/*  41 */     hashtable.put("Zero bytes may not occur in string parameters.", "Byte con valore zero non possono essere contenuti nei parametri stringa.");
/*  42 */     hashtable.put("Unable to bind parameter values for statement.", "Impossibile fare il «bind» dei valori passati come parametri per lo statement.");
/*  43 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "Il messaggio di «bind» è troppo lungo ({0}). Questo può essere causato da una dimensione eccessiva o non corretta dei parametri dell''«InputStream».");
/*  44 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "Il parametro «client_encoding» del server è stato cambiato in {0}. Il driver JDBC richiede che «client_encoding» sia UNICODE per un corretto funzionamento.");
/*  45 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "Il parametro del server «DateStyle» è stato cambiato in {0}. Il driver JDBC richiede che «DateStyle» cominci con «ISO» per un corretto funzionamento.");
/*  46 */     hashtable.put("The driver currently does not support COPY operations.", "Il driver non supporta al momento l''operazione «COPY».");
/*  47 */     hashtable.put("DataSource has been closed.", "Questo «DataSource» è stato chiuso.");
/*  48 */     hashtable.put("This PooledConnection has already been closed.", "Questo «PooledConnection» è stato chiuso.");
/*  49 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "La «Connection» è stata chiusa automaticamente perché una nuova l''ha sostituita nello stesso «PooledConnection», oppure il «PooledConnection» è stato chiuso.");
/*  50 */     hashtable.put("Connection has been closed.", "Questo «Connection» è stato chiuso.");
/*  51 */     hashtable.put("Statement has been closed.", "Questo «Statement» è stato chiuso.");
/*  52 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Chiamata Fastpath «{0}»: Nessun risultato restituito mentre ci si aspettava un intero.");
/*  53 */     hashtable.put("The fastpath function {0} is unknown.", "La funzione fastpath «{0}» è sconosciuta.");
/*  54 */     hashtable.put("Conversion to type {0} failed: {1}.", "Conversione al tipo {0} fallita: {1}.");
/*  55 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Impossibile stabilire se il percorso è aperto o chiuso: {0}.");
/*  56 */     hashtable.put("The array index is out of range: {0}", "Indice di colonna fuori dall''intervallo ammissibile: {0}");
/*  57 */     hashtable.put("Multi-dimensional arrays are currently not supported.", "Gli array multidimensionali non sono attualmente gestiti.");
/*  58 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "L''indice dell''array è fuori intervallo: {0}, numero di elementi: {1}.");
/*  59 */     hashtable.put("LOB positioning offsets start at 1.", "L''offset per la posizione dei LOB comincia da 1.");
/*  60 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "Il massimo valore per l''indice dei LOB di PostgreSQL è {0}. ");
/*  61 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "Il valore per il parametro di tipo string «{0}» non è supportato.");
/*  62 */     hashtable.put("No results were returned by the query.", "Nessun risultato è stato restituito dalla query.");
/*  63 */     hashtable.put("A result was returned when none was expected.", "È stato restituito un valore nonostante non ne fosse atteso nessuno.");
/*  64 */     hashtable.put("Failed to create object for: {0}.", "Fallita la creazione dell''oggetto per: {0}.");
/*  65 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Non è possibile caricare la class «{0}» per gestire il tipo «{1}».");
/*  66 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Non è possibile modificare la proprietà «read-only» delle transazioni nel mezzo di una transazione.");
/*  67 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Non è possibile cambiare il livello di isolamento delle transazioni nel mezzo di una transazione.");
/*  68 */     hashtable.put("Transaction isolation level {0} not supported.", "Il livello di isolamento delle transazioni «{0}» non è supportato.");
/*  69 */     hashtable.put("Finalizing a Connection that was never closed:", "Finalizzazione di una «Connection» che non è stata chiusa.");
/*  70 */     hashtable.put("Unable to translate data into the desired encoding.", "Impossibile tradurre i dati nella codifica richiesta.");
/*  71 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Non è possibile trovare il valore di «MaxIndexKeys» nel catalogo si sistema.");
/*  72 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Non è possibile trovare il datatype «name» nel catalogo di sistema.");
/*  73 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "L''operazione richiete un «ResultSet» scorribile mentre questo è «FORWARD_ONLY».");
/*  74 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Errore non previsto durante la decodifica di caratteri a partire da un «large object».");
/*  75 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Non è possibile utilizzare gli spostamenti relativi durante l''inserimento di una riga.");
/*  76 */     hashtable.put("Invalid fetch direction constant: {0}.", "Costante per la direzione dell''estrazione non valida: {0}.");
/*  77 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Non è possibile invocare «cancelRowUpdates()» durante l''inserimento di una riga.");
/*  78 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Non è possibile invocare «deleteRow()» durante l''inserimento di una riga.");
/*  79 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "La posizione attuale è precedente all''inizio del ResultSet. Non è possibile invocare «deleteRow()» qui.");
/*  80 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "La posizione attuale è successiva alla fine del ResultSet. Non è possibile invocare «deleteRow()» qui.");
/*  81 */     hashtable.put("There are no rows in this ResultSet.", "Non ci sono righe in questo «ResultSet».");
/*  82 */     hashtable.put("Not on the insert row.", "Non si è in una nuova riga.");
/*  83 */     hashtable.put("You must specify at least one column value to insert a row.", "Per inserire un record si deve specificare almeno il valore di una colonna.");
/*  84 */     hashtable.put("The JVM claims not to support the encoding: {0}", "La JVM sostiene di non supportare la codifica: {0}.");
/*  85 */     hashtable.put("Provided InputStream failed.", "L''«InputStream» fornito è fallito.");
/*  86 */     hashtable.put("Provided Reader failed.", "Il «Reader» fornito è fallito.");
/*  87 */     hashtable.put("Can''t refresh the insert row.", "Non è possibile aggiornare la riga in inserimento.");
/*  88 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Non è possibile invocare «updateRow()» durante l''inserimento di una riga.");
/*  89 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "Non è possibile aggiornare il «ResultSet» perché la posizione attuale è precedente all''inizio o successiva alla file dei risultati.");
/*  90 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "I «ResultSet» in modalità CONCUR_READ_ONLY non possono essere aggiornati.");
/*  91 */     hashtable.put("No primary key found for table {0}.", "Non è stata trovata la chiave primaria della tabella «{0}».");
/*  92 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "La dimensione dell''area di «fetch» deve essere maggiore o eguale a 0.");
/*  93 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Sono stati trovati caratteri non validi tra i dati. Molto probabilmente sono stati memorizzati dei caratteri che non sono validi per la codifica dei caratteri impostata alla creazione del database. Il caso più diffuso è quello nel quale si memorizzano caratteri a 8bit in un database con codifica SQL_ASCII.");
/*  94 */     hashtable.put("Bad value for type {0} : {1}", "Il valore «{1}» non è adeguato al tipo «{0}».");
/*  95 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Colonna denominata «{0}» non è presente in questo «ResultSet».");
/*  96 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "Il «ResultSet» non è aggiornabile. La query che lo genera deve selezionare una sola tabella e deve selezionarne tutti i campi che ne compongono la chiave primaria. Si vedano le specifiche dell''API JDBC 2.1, sezione 5.6, per ulteriori dettagli.");
/*  97 */     hashtable.put("This ResultSet is closed.", "Questo «ResultSet» è chiuso.");
/*  98 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "Il «ResultSet» non è correttamente posizionato; forse è necessario invocare «next()».");
/*  99 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "Non si possono utilizzare i metodi \"query\" che hanno come argomento una stringa nel caso di «PreparedStatement».");
/* 100 */     hashtable.put("Multiple ResultSets were returned by the query.", "La query ha restituito «ResultSet» multipli.");
/* 101 */     hashtable.put("A CallableStatement was executed with nothing returned.", "Un «CallableStatement» è stato eseguito senza produrre alcun risultato. ");
/* 102 */     hashtable.put("A CallableStatement was excecuted with an invalid number of parameters", "Un «CallableStatement» è stato eseguito con un numero errato di parametri.");
/* 103 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "È stato eseguito un «CallableStatement» ma il parametro in uscita «{0}» era di tipo «{1}» al posto di «{2}», che era stato dichiarato.");
/* 104 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Il numero massimo di righe deve essere maggiore o eguale a 0.");
/* 105 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Il timeout relativo alle query deve essere maggiore o eguale a 0.");
/* 106 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "La dimensione massima del campo deve essere maggiore o eguale a 0.");
/* 107 */     hashtable.put("Unknown Types value.", "Valore di tipo sconosciuto.");
/* 108 */     hashtable.put("Invalid stream length {0}.", "La dimensione specificata, {0}, per lo «stream» non è valida.");
/* 109 */     hashtable.put("The JVM claims not to support the {0} encoding.", "La JVM sostiene di non supportare la codifica {0}.");
/* 110 */     hashtable.put("Unknown type {0}.", "Tipo sconosciuto {0}.");
/* 111 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Non è possibile fare il cast di una istanza di «{0}» al tipo «{1}».");
/* 112 */     hashtable.put("Unsupported Types value: {0}", "Valore di tipo «{0}» non supportato.");
/* 113 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "Non è possibile identificare il tipo SQL da usare per l''istanza di tipo «{0}». Usare «setObject()» specificando esplicitamente il tipo da usare per questo valore.");
/* 114 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Questo statement non dichiara il parametro in uscita. Usare «{ ?= call ... }» per farlo.");
/* 115 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Sequenza di escape definita erroneamente nella funzione o procedura all''offset {0}.");
/* 116 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "È stato definito il parametro di tipo «{0}», ma poi è stato invocato il metodo «get{1}()» (sqltype={2}).");
/* 117 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "È stato definito un «CallableStatement» ma non è stato invocato il metodo «registerOutParameter(1, <tipo>)».");
/* 118 */     hashtable.put("This statement has been closed.", "Questo statement è stato chiuso.");
/* 119 */     hashtable.put("Too many update results were returned.", "Sono stati restituiti troppi aggiornamenti.");
/* 120 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "L''operazione «batch» {0} {1} è stata interrotta. Chiamare «getNextException» per scoprirne il motivo.");
/* 121 */     hashtable.put("Unexpected error writing large object to database.", "Errore inatteso inviando un «large object» al database.");
/* 122 */     hashtable.put("{0} function takes one and only one argument.", "Il metodo «{0}» accetta un ed un solo argomento.");
/* 123 */     hashtable.put("{0} function takes two and only two arguments.", "Il metodo «{0}» accetta due e solo due argomenti.");
/* 124 */     hashtable.put("rand function only takes zero or one argument(the seed).", "Il metodo «rand» vuole al massimo un argomento (il seme).");
/* 125 */     hashtable.put("{0} function takes four and only four argument.", "Il metodo «{0}» accetta quattro e solo quattro argomenti.");
/* 126 */     hashtable.put("{0} function takes two or three arguments.", "Il metodo «{0}» accetta due o tre argomenti.");
/* 127 */     hashtable.put("{0} function doesn''t take any argument.", "Il metodo «{0}» non accetta argomenti.");
/* 128 */     hashtable.put("{0} function takes three and only three arguments.", "Il metodo «{0}» accetta tre e solo tre argomenti.");
/* 129 */     hashtable.put("Interval {0} not yet implemented", "L''intervallo «{0}» non è stato ancora implementato.");
/* 130 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Il valore specificato per il tipo «timestamp» o «date», infinito, non può essere rappresentato come «time».");
/* 131 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "La class «{0}» non implementa «org.postgresql.util.PGobject».");
/* 132 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "Il parametro «holdability» per il «ResultSet» è sconosciuto: {0}.");
/* 133 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Le versioni del server precedenti alla 8.0 non permettono i punti di ripristino.");
/* 134 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Non è possibile impostare i punti di ripristino in modalità «auto-commit».");
/* 135 */     hashtable.put("Returning autogenerated keys is not supported.", "La restituzione di chiavi autogenerate non è supportata.");
/* 136 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Il parametro indice è fuori intervallo: {0}, numero di elementi: {1}.");
/* 137 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Non è possibile utilizzare un punto di ripristino successivamente al suo rilascio.");
/* 138 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Non è possibile trovare l''id del punto di ripristino indicato.");
/* 139 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Non è possibile trovare il nome di un punto di ripristino anonimo.");
/* 140 */     hashtable.put("Failed to initialize LargeObject API", "Inizializzazione di LargeObject API fallita.");
/* 141 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Non è possibile impostare i «Large Object» in modalità «auto-commit».");
/* 142 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "La classe «SSLSocketFactory» specificata, «{0}», non può essere istanziata.");
/* 143 */     hashtable.put("Conversion of interval failed", "Fallita la conversione di un «interval».");
/* 144 */     hashtable.put("Conversion of money failed.", "Fallita la conversione di un «money».");
/* 145 */     hashtable.put("Exception: {0}", "Eccezione: {0}.");
/* 146 */     hashtable.put("Stack Trace:", "Stack trace:");
/* 147 */     hashtable.put("End of Stack Trace", "Fine dello stack trace");
/* 148 */     hashtable.put("Exception generating stacktrace for: {0} encountered: {1}", "Eccezione durante la generazione dello stack trace per: «{0}» trovata: {1}");
/* 149 */     hashtable.put("Detail: {0}", "Dettaglio: {0}");
/* 150 */     hashtable.put("Hint: {0}", "Suggerimento: {0}");
/* 151 */     hashtable.put("Position: {0}", "Posizione: {0}");
/* 152 */     hashtable.put("Where: {0}", "Dove: {0}");
/* 153 */     hashtable.put("Internal Query: {0}", "Query interna: {0}");
/* 154 */     hashtable.put("Internal Position: {0}", "Posizione interna: {0}");
/* 155 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Individuazione: file: \"{0}\", routine: {1}, linea: {2}");
/* 156 */     hashtable.put("Server SQLState: {0}", "SQLState del server: {0}");
/* 157 */     hashtable.put("Invalid flags", "Flag non validi");
/* 158 */     hashtable.put("xid must not be null", "xid non può essere NULL");
/* 159 */     hashtable.put("Connection is busy with another transaction", "La connessione è utilizzata da un''altra transazione");
/* 160 */     hashtable.put("suspend/resume and join not implemented", "«Suspend», «resume» e «join» non sono implementati");
/* 161 */     hashtable.put("Transaction interleaving not implemented", "L''\"interleaving\" delle transazioni «{0}» non è supportato.");
/* 162 */     hashtable.put("tried to call end without corresponding start call", "È stata chiamata «end» senza la corrispondente chiamata a «start»");
/* 163 */     hashtable.put("suspend/resume not implemented", "«suspend»/«resume» non implementato");
/* 164 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Non implementato: «Prepare» deve essere eseguito nella stessa connessione che ha iniziato la transazione.");
/* 165 */     hashtable.put("Prepare called before end", "«Prepare» invocato prima della fine");
/* 166 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Le versioni del server precedenti alla 8.1 non permettono i commit \"two-phase\".");
/* 167 */     hashtable.put("Error preparing transaction", "Errore nel preparare una transazione");
/* 168 */     hashtable.put("Invalid flag", "Flag non valido");
/* 169 */     hashtable.put("Error during recover", "Errore durante il ripristino");
/* 170 */     hashtable.put("Error rolling back prepared transaction", "Errore durante il «rollback» di una transazione preparata");
/* 171 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Non implementato: il commit \"one-phase\" deve essere invocato sulla stessa connessione che ha iniziato la transazione.");
/* 172 */     hashtable.put("commit called before end", "«Commit» è stato chiamato prima della fine");
/* 173 */     hashtable.put("Error during one-phase commit", "Errore durante il commit \"one-phase\"");
/* 174 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Non implementato: la seconda fase del «commit» deve essere effettuata con una connessione non in uso");
/* 175 */     hashtable.put("Heuristic commit/rollback not supported", "«Commit» e «rollback» euristici non sono supportati");
/* 176 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 179 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 182 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 185 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_it.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */