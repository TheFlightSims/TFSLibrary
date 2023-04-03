/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_de extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: head-de\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2008-09-18 17:59-0600\nPO-Revision-Date: 2008-09-12 14:22+0200\nLast-Translator: Andre Bialojahn <ab.spamnews@freenet.de>\nLanguage-Team: Deutsch\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Generator: KBabel 1.0.2\nX-Poedit-Language: German\nX-Poedit-Country: GERMANY\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Fehler beim Laden der Voreinstellungen aus driverconfig.properties");
/*   9 */     hashtable.put("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to.", "Ihre Sicherheitsrichtlinie hat den Versuch des Verbindungsaufbaus verhindert. Sie müssen wahrscheinlich der Verbindung zum Datenbankrechner java.net.SocketPermission gewähren, um den Rechner auf dem gewählten Port zu erreichen.");
/*  10 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Etwas Ungewöhnliches ist passiert, das den Treiber fehlschlagen ließ. Bitte teilen Sie diesen Fehler mit.");
/*  11 */     hashtable.put("Connection attempt timed out.", "Keine Verbindung innerhalb des Zeitintervalls möglich.");
/*  12 */     hashtable.put("Interrupted while attempting to connect.", "Beim Verbindungsversuch trat eine Unterbrechung auf.");
/*  13 */     hashtable.put("Method {0} is not yet implemented.", "Die Methode {0} ist noch nicht implementiert.");
/*  14 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Es konnte keine Verbindung unter Verwendung des Protokolls {0} hergestellt werden.");
/*  15 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Vorzeitiges Ende des Eingabedatenstroms. Es wurden {0} Bytes erwartet, jedoch nur {1} gelesen.");
/*  16 */     hashtable.put("Expected an EOF from server, got: {0}", "Vom Server wurde ein EOF erwartet, jedoch {0} gelesen.");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Ungültige UTF-8-Sequenz: Byte {0} der {1} Bytesequenz ist nicht 10xxxxxx: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Ungültige UTF-8-Sequenz: {0} Bytes wurden verwendet um einen {1} Bytewert zu kodieren: {2}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Ungültige UTF-8-Sequenz: das erste Byte ist {0}: {1}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Ungültige UTF-8-Sequenz: Der letzte Wert ist außerhalb des zulässigen Bereichs: {0}");
/*  21 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Ungültige UTF-8-Sequenz: der letzte Wert ist ein Ersatzwert: {0}");
/*  22 */     hashtable.put("Zero bytes may not occur in string parameters.", "Stringparameter dürfen keine Nullbytes enthalten.");
/*  23 */     hashtable.put("Zero bytes may not occur in identifiers.", "Nullbytes dürfen in Bezeichnern nicht vorkommen.");
/*  24 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "Die Typwandlung für eine Instanz von {0} nach {1} ist nicht möglich.");
/*  25 */     hashtable.put("The driver does not support SSL.", "Der Treiber unterstützt SSL nicht.");
/*  26 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Verbindung verweigert. Überprüfen Sie die Korrektheit von Hostnamen und der Portnummer und dass der Datenbankserver TCP/IP-Verbindungen annimmt.");
/*  27 */     hashtable.put("The connection attempt failed.", "Der Verbindungsversuch schlug fehl.");
/*  28 */     hashtable.put("The server does not support SSL.", "Der Server unterstützt SSL nicht.");
/*  29 */     hashtable.put("An error occured while setting up the SSL connection.", "Beim Aufbau der SSL-Verbindung trat ein Fehler auf.");
/*  30 */     hashtable.put("Connection rejected: {0}.", "Verbindung abgewiesen: {0}.");
/*  31 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Der Server verlangt passwortbasierte Authentifizierung, jedoch wurde kein Passwort angegeben.");
/*  32 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "Der Authentifizierungstyp {0} wird nicht unterstützt. Stellen Sie sicher, dass die Datei ''pg_hba.conf'' die IP-Adresse oder das Subnetz des Clients enthält und dass der Client ein Authentifizierungsschema nutzt, das vom Treiber unterstützt wird.");
/*  33 */     hashtable.put("Protocol error.  Session setup failed.", "Protokollfehler.  Die Sitzung konnte nicht gestartet werden.");
/*  34 */     hashtable.put("Backend start-up failed: {0}.", "Das Backend konnte nicht gestartet werden: {0}.");
/*  35 */     hashtable.put("An unexpected result was returned by a query.", "Eine Abfrage lieferte ein unerwartetes Resultat.");
/*  36 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Der Spaltenindex {0} ist außerhalb des gültigen Bereichs. Anzahl Spalten: {1}.");
/*  37 */     hashtable.put("No value specified for parameter {0}.", "Für den Parameter {0} wurde kein Wert angegeben.");
/*  38 */     hashtable.put("Expected command status BEGIN, got {0}.", "Statt des erwarteten Befehlsstatus BEGIN, wurde {0} empfangen.");
/*  39 */     hashtable.put("Unexpected command status: {0}.", "Unerwarteter Befehlsstatus: {0}.");
/*  40 */     hashtable.put("An I/O error occured while sending to the backend.", "Eingabe/Ausgabe-Fehler {0} beim Senden an das Backend.");
/*  41 */     hashtable.put("Unknown Response Type {0}.", "Die Antwort weist einen unbekannten Typ auf: {0}.");
/*  42 */     hashtable.put("Ran out of memory retrieving query results.", "Nicht genügend Speicher beim Abholen der Abfrageergebnisse.");
/*  43 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Der Updatecount aus der Kommandovervollständigungsmarkierung(?) {0} konnte nicht interpretiert werden.");
/*  44 */     hashtable.put("Unable to bind parameter values for statement.", "Der Anweisung konnten keine Parameterwerte zugewiesen werden.");
/*  45 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "Die Nachrichtenlänge {0} ist zu groß. Das kann von sehr großen oder inkorrekten Längenangaben eines InputStream-Parameters herrühren.");
/*  46 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "Der Parameter ''client_encoding'' wurde auf dem Server auf {0} verändert. Der JDBC-Treiber setzt für korrektes Funktionieren die Einstellung UNICODE voraus.");
/*  47 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "Der Parameter ''Date Style'' wurde auf dem Server auf {0} verändert. Der JDBC-Treiber setzt für korrekte Funktion voraus, dass ''Date Style'' mit ''ISO'' beginnt.");
/*  48 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "Der standard_conforming_strings Parameter des Servers steht auf {0}. Der JDBC-Treiber erwartete on oder off.");
/*  49 */     hashtable.put("The driver currently does not support COPY operations.", "Der Treiber unterstützt derzeit keine COPY-Operationen.");
/*  50 */     hashtable.put("This PooledConnection has already been closed.", "Diese PooledConnection ist bereits geschlossen worden.");
/*  51 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Die Verbindung wurde automatisch geschlossen, da entweder eine neue Verbindung für die gleiche PooledConnection geöffnet wurde, oder die PooledConnection geschlossen worden ist..");
/*  52 */     hashtable.put("Connection has been closed.", "Die Verbindung wurde geschlossen.");
/*  53 */     hashtable.put("Statement has been closed.", "Die Anweisung wurde geschlossen.");
/*  54 */     hashtable.put("DataSource has been closed.", "Die Datenquelle wurde geschlossen.");
/*  55 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Der Fastpath-Aufruf {0} gab kein Ergebnis zurück, jedoch wurde ein Integer erwartet.");
/*  56 */     hashtable.put("The fastpath function {0} is unknown.", "Die Fastpath-Funktion {0} ist unbekannt.");
/*  57 */     hashtable.put("Conversion to type {0} failed: {1}.", "Die Umwandlung in den Typ {0} schlug fehl: {1}.");
/*  58 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Es konnte nicht ermittelt werden, ob der Pfad offen oder geschlossen ist: {0}.");
/*  59 */     hashtable.put("The array index is out of range: {0}", "Der Arrayindex ist außerhalb des gültigen Bereichs: {0}.");
/*  60 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Der Arrayindex {0} ist außerhalb des gültigen Bereichs. Vorhandene Elemente: {1}.");
/*  61 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "Das Abschneiden großer Objekte ist nur in Versionen nach 8.3 implementiert.");
/*  62 */     hashtable.put("LOB positioning offsets start at 1.", "Positionsoffsets für LOBs beginnen bei 1.");
/*  63 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "LOBs in PostgreSQL können nur auf {0} verweisen.");
/*  64 */     hashtable.put("free() was called on this LOB previously", "free() wurde bereits für dieses LOB aufgerufen.");
/*  65 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "Nichtunterstützter Wert für den Stringparameter: {0}");
/*  66 */     hashtable.put("No results were returned by the query.", "Die Abfrage lieferte kein Ergebnis.");
/*  67 */     hashtable.put("A result was returned when none was expected.", "Die Anweisung lieferte ein Ergebnis obwohl keines erwartet wurde.");
/*  68 */     hashtable.put("Failed to create object for: {0}.", "Erstellung des Objektes schlug fehl für: {0}.");
/*  69 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Die für den Datentyp {1} verantwortliche Klasse {0} konnte nicht geladen werden.");
/*  70 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Die Nur-Lesen-Eigenschaft einer Transaktion kann nicht während der Transaktion verändert werden.");
/*  71 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Die Transaktions-Trennungsstufe kann nicht während einer Transaktion verändert werden.");
/*  72 */     hashtable.put("Transaction isolation level {0} not supported.", "Die Transaktions-Trennungsstufe {0} ist nicht unterstützt.");
/*  73 */     hashtable.put("Finalizing a Connection that was never closed:", "Eine Connection wurde finalisiert, die nie geschlossen wurde:");
/*  74 */     hashtable.put("Unable to translate data into the desired encoding.", "Die Daten konnten nicht in die gewünschte Kodierung gewandelt werden.");
/*  75 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Es konnte kein Wert für MaxIndexKeys gefunden werden, da die Systemkatalogdaten fehlen.");
/*  76 */     hashtable.put("Unable to find name datatype in the system catalogs.", "In den Systemkatalogen konnte der Namensdatentyp nicht gefunden werden.");
/*  77 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "Die Operation erfordert ein scrollbares ResultSet, dieses jedoch ist FORWARD_ONLY.");
/*  78 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Ein unerwarteter Fehler trat beim Dekodieren von Zeichen aus einem LargeObject (LOB) auf.");
/*  79 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Relative Bewegungen können in der Einfügezeile nicht durchgeführt werden.");
/*  80 */     hashtable.put("Invalid fetch direction constant: {0}.", "Unzulässige Richtungskonstante bei fetch: {0}.");
/*  81 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "''cancelRowUpdates()'' kann in der Einfügezeile nicht aufgerufen werden.");
/*  82 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "''deleteRow()'' kann in der Einfügezeile nicht aufgerufen werden.");
/*  83 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Die augenblickliche Position ist vor dem Beginn des ResultSets.  Dort kann ''deleteRow()'' nicht aufgerufen werden.");
/*  84 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Die augenblickliche Position ist hinter dem Ende des ResultSets.  Dort kann ''deleteRow()'' nicht aufgerufen werden.");
/*  85 */     hashtable.put("There are no rows in this ResultSet.", "Es gibt keine Zeilen in diesem ResultSet.");
/*  86 */     hashtable.put("Not on the insert row.", "Nicht in der Einfügezeile.");
/*  87 */     hashtable.put("You must specify at least one column value to insert a row.", "Sie müssen mindestens einen Spaltenwert angeben, um eine Zeile einzufügen.");
/*  88 */     hashtable.put("The JVM claims not to support the encoding: {0}", "Die JVM behauptet, die Zeichenkodierung {0} nicht zu unterstützen.");
/*  89 */     hashtable.put("Provided InputStream failed.", "Der bereitgestellte InputStream scheiterte.");
/*  90 */     hashtable.put("Provided Reader failed.", "Der bereitgestellte Reader scheiterte.");
/*  91 */     hashtable.put("Can''t refresh the insert row.", "Die Einfügezeile kann nicht aufgefrischt werden.");
/*  92 */     hashtable.put("Cannot call updateRow() when on the insert row.", "''updateRow()'' kann in der Einfügezeile nicht aufgerufen werden.");
/*  93 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "Das ResultSet kann nicht aktualisiert werden, da es entweder vor oder nach dem Ende der Ergebnisse ist.");
/*  94 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets, deren Zugriffsart CONCUR_READ_ONLY ist, können nicht aktualisiert werden.");
/*  95 */     hashtable.put("No primary key found for table {0}.", "Für die Tabelle {0} konnte kein Primärschlüssel gefunden werden.");
/*  96 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Die Fetch-Größe muss ein Wert größer oder gleich Null sein.");
/*  97 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Ungültige Zeichendaten.  Das ist höchstwahrscheinlich von in der Datenbank gespeicherten Zeichen hervorgerufen, die in einer anderen Kodierung vorliegen, als die, in der die Datenbank erstellt wurde.  Das häufigste Beispiel dafür ist es, 8Bit-Daten in SQL_ASCII-Datenbanken abzulegen.");
/*  98 */     hashtable.put("Bad value for type {0} : {1}", "Unzulässiger Wert für den Typ {0} : {1}.");
/*  99 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Der Spaltenname {0} wurde in diesem ResultSet nicht gefunden.");
/* 100 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "Das ResultSet kann nicht aktualisiert werden.  Die Abfrage, die es erzeugte, darf nur eine Tabelle und muss darin alle Primärschlüssel auswählen. Siehe JDBC 2.1 API-Spezifikation, Abschnitt 5.6 für mehr Details.");
/* 101 */     hashtable.put("This ResultSet is closed.", "Dieses ResultSet ist geschlossen.");
/* 102 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "Das ResultSet ist nicht richtig positioniert. Eventuell muss ''next'' aufgerufen werden.");
/* 103 */     hashtable.put("Multiple ResultSets were returned by the query.", "Die Abfrage ergab mehrere ResultSets.");
/* 104 */     hashtable.put("A CallableStatement was executed with nothing returned.", "Ein CallableStatement wurde ausgeführt ohne etwas zurückzugeben.");
/* 105 */     hashtable.put("A CallableStatement was executed with an invalid number of parameters", "Ein CallableStatement wurde mit einer falschen Anzahl Parameter ausgeführt.");
/* 106 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "Eine CallableStatement-Funktion wurde ausgeführt und der Rückgabewert {0} war vom Typ {1}. Jedoch wurde der Typ {2} dafür registriert.");
/* 107 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Die maximale Zeilenzahl muss ein Wert größer oder gleich Null sein.");
/* 108 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Das Abfragetimeout muss ein Wert größer oder gleich Null sein.");
/* 109 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "Die maximale Feldgröße muss ein Wert größer oder gleich Null sein.");
/* 110 */     hashtable.put("Unknown Types value.", "Unbekannter Typ.");
/* 111 */     hashtable.put("Invalid stream length {0}.", "Ungültige Länge des Datenstroms: {0}.");
/* 112 */     hashtable.put("The JVM claims not to support the {0} encoding.", "Die JVM behauptet, die Zeichenkodierung {0} nicht zu unterstützen.");
/* 113 */     hashtable.put("Unknown type {0}.", "Unbekannter Typ {0}.");
/* 114 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Die Typwandlung für eine Instanz von {0} nach {1} ist nicht möglich.");
/* 115 */     hashtable.put("Unsupported Types value: {0}", "Unbekannter Typ: {0}.");
/* 116 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "Der in SQL für eine Instanz von {0} zu verwendende Datentyp kann nicht abgeleitet werden. Benutzen Sie ''setObject()'' mit einem expliziten Typ, um ihn festzulegen.");
/* 117 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Diese Anweisung deklariert keinen OUT-Parameter. Benutzen Sie '{' ?= call ... '}' um das zu tun.");
/* 118 */     hashtable.put("wasNull cannot be call before fetching a result.", "wasNull kann nicht aufgerufen werden, bevor ein Ergebnis abgefragt wurde.");
/* 119 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Unzulässige Syntax für ein Funktions- oder Prozedur-Escape an Offset {0}.");
/* 120 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "Ein Parameter des Typs {0} wurde registriert, jedoch erfolgte ein Aufruf get{1} (sqltype={2}).");
/* 121 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "Ein CallableStatement wurde deklariert, aber kein Aufruf von ''registerOutParameter(1, <some type>)'' erfolgte.");
/* 122 */     hashtable.put("Results cannot be retrieved from a CallableStatement before it is executed.", "Ergebnisse können nicht von einem CallableStatement abgerufen werden, bevor es ausgeführt wurde.");
/* 123 */     hashtable.put("This statement has been closed.", "Die Anweisung wurde geschlossen.");
/* 124 */     hashtable.put("Too many update results were returned.", "Zu viele Updateergebnisse wurden zurückgegeben.");
/* 125 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "Batch-Eintrag {0} {1} wurde abgebrochen.  Rufen Sie ''getNextException'' auf, um die Ursache zu erfahren.");
/* 126 */     hashtable.put("Unexpected error writing large object to database.", "Beim Schreiben eines LargeObjects (LOB) in die Datenbank trat ein unerwarteter Fehler auf.");
/* 127 */     hashtable.put("{0} function takes one and only one argument.", "Die {0}-Funktion erwartet nur genau ein Argument.");
/* 128 */     hashtable.put("{0} function takes two and only two arguments.", "Die {0}-Funktion erwartet genau zwei Argumente.");
/* 129 */     hashtable.put("{0} function takes four and only four argument.", "Die {0}-Funktion erwartet genau vier Argumente.");
/* 130 */     hashtable.put("{0} function takes two or three arguments.", "Die {0}-Funktion erwartet zwei oder drei Argumente.");
/* 131 */     hashtable.put("{0} function doesn''t take any argument.", "Die {0}-Funktion akzeptiert kein Argument.");
/* 132 */     hashtable.put("{0} function takes three and only three arguments.", "Die {0}-Funktion erwartet genau drei Argumente.");
/* 133 */     hashtable.put("Interval {0} not yet implemented", "Intervall {0} ist noch nicht implementiert.");
/* 134 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Für den Zeitstempel oder das Datum wurde der Wert ''unendlich'' gefunden. Dies kann nicht als Zeit repräsentiert werden.");
/* 135 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "Die Klasse {0} implementiert nicht ''org.postgresql.util.PGobject''.");
/* 136 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "Unbekannte Einstellung für die Haltbarkeit des ResultSets: {0}.");
/* 137 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Der Server unterstützt keine Rettungspunkte vor Version 8.0.");
/* 138 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Ein Rettungspunkt kann im Modus ''auto-commit'' nicht erstellt werden.");
/* 139 */     hashtable.put("Returning autogenerated keys is not supported.", "Die Rückgabe automatisch generierter Schlüssel wird nicht unterstützt,");
/* 140 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Der Parameterindex {0} ist außerhalb des gültigen Bereichs. Es gibt {1} Parameter.");
/* 141 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Ein Rettungspunkt kann nicht angesprochen werden, nach dem er entfernt wurde.");
/* 142 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Die ID eines benamten Rettungspunktes kann nicht ermittelt werden.");
/* 143 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Der Name eines namenlosen Rettungpunktes kann nicht ermittelt werden.");
/* 144 */     hashtable.put("ClientInfo property not supported.", "Die ClientInfo-Eigenschaft ist nicht unterstützt.");
/* 145 */     hashtable.put("Failed to initialize LargeObject API", "Die LargeObject-API konnte nicht initialisiert werden.");
/* 146 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "LargeObjects (LOB) dürfen im Modus ''auto-commit'' nicht verwendet werden.");
/* 147 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "Die von {0} bereitgestellte SSLSocketFactory-Klasse konnte nicht instanziiert werden.");
/* 148 */     hashtable.put("Conversion of interval failed", "Die Umwandlung eines Intervalls schlug fehl.");
/* 149 */     hashtable.put("Conversion of money failed.", "Die Umwandlung eines Währungsbetrags schlug fehl.");
/* 150 */     hashtable.put("Exception: {0}", "Exception: {0}.");
/* 151 */     hashtable.put("Stack Trace:", "Stack-Trace:");
/* 152 */     hashtable.put("End of Stack Trace", "Ende des Stack-Traces.");
/* 153 */     hashtable.put("Exception generating stacktrace for: {0} encountered: {1}", "Beim Erstellen eines Stack-Traces für {0} trat eine Exception auf: {1}");
/* 154 */     hashtable.put("Detail: {0}", "Detail: {0}");
/* 155 */     hashtable.put("Hint: {0}", "Hinweis: {0}");
/* 156 */     hashtable.put("Position: {0}", "Position: {0}");
/* 157 */     hashtable.put("Where: {0}", "Wobei: {0}");
/* 158 */     hashtable.put("Internal Query: {0}", "Interne Abfrage: {0}");
/* 159 */     hashtable.put("Internal Position: {0}", "Interne Position: {0}");
/* 160 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Ort: Datei: {0}, Routine: {1}, Zeile: {2}.");
/* 161 */     hashtable.put("Server SQLState: {0}", "Server SQLState: {0}");
/* 162 */     hashtable.put("Invalid flags", "Ungültige Flags");
/* 163 */     hashtable.put("xid must not be null", "Die xid darf nicht null sein.");
/* 164 */     hashtable.put("Connection is busy with another transaction", "Die Verbindung ist derzeit mit einer anderen Transaktion beschäftigt.");
/* 165 */     hashtable.put("suspend/resume not implemented", "Anhalten/Fortsetzen ist nicht implementiert.");
/* 166 */     hashtable.put("Transaction interleaving not implemented", "Transaktionsinterleaving ist nicht implementiert.");
/* 167 */     hashtable.put("Error disabling autocommit", "Fehler beim Abschalten von Autocommit.");
/* 168 */     hashtable.put("tried to call end without corresponding start call", "Es wurde versucht, ohne dazugehörigen ''start''-Aufruf ''end'' aufzurufen.");
/* 169 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Nicht implementiert: ''Prepare'' muss über die selbe Verbindung abgesetzt werden, die die Transaktion startete.");
/* 170 */     hashtable.put("Prepare called before end", "''Prepare'' wurde vor ''end'' aufgerufen.");
/* 171 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Der Server unterstützt keine zweiphasige Bestätigung vor Version 8.1.");
/* 172 */     hashtable.put("Error preparing transaction", "Beim Vorbereiten der Transaktion trat ein Fehler auf.");
/* 173 */     hashtable.put("Invalid flag", "Ungültiges Flag.");
/* 174 */     hashtable.put("Error during recover", "Beim Wiederherstellen trat ein Fehler auf.");
/* 175 */     hashtable.put("Error rolling back prepared transaction", "Fehler beim Rollback einer vorbereiteten Transaktion.");
/* 176 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Nicht implementiert: Die einphasige Bestätigung muss über die selbe Verbindung abgewickelt werden, die verwendet wurde, um sie zu beginnen.");
/* 177 */     hashtable.put("commit called before end", "''Commit'' wurde vor ''end'' aufgerufen.");
/* 178 */     hashtable.put("Error during one-phase commit", "Bei der einphasigen Bestätigung trat ein Fehler auf.");
/* 179 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Nicht implementiert: Die zweite Bestätigungsphase muss über eine im Leerlauf befindliche Verbindung abgewickelt werden.");
/* 180 */     hashtable.put("Heuristic commit/rollback not supported", "Heuristisches Commit/Rollback wird nicht unterstützt.");
/* 181 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 184 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 187 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 190 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_de.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */