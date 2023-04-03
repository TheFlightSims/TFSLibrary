/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_cs extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL JDBC Driver 8.0\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2005-08-21 22:50-0700\nPO-Revision-Date: 2005-08-21 20:00+0200\nLast-Translator: Petr Dittrich <bodyn@medoro.org>\nLanguage-Team: \nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Chyba načítání standardního nastavení z driverconfig.properties");
/*   9 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Něco neobvyklého přinutilo ovladač selhat. Prosím nahlaste tuto vyjímku.");
/*  10 */     hashtable.put("Method {0} is not yet implemented.", "Metoda {0} není implementována.");
/*  11 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Spojení nelze vytvořit s použitím žádaného protokolu {0}.");
/*  12 */     hashtable.put("The driver does not support SSL.", "Ovladač nepodporuje SSL.");
/*  13 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Spojení odmítnuto. Zkontrolujte zda je jméno hosta a port správné a zda postmaster přijímá TCP/IP spojení.");
/*  14 */     hashtable.put("The connection attempt failed.", "Pokus o připojení selhal.");
/*  15 */     hashtable.put("The server does not support SSL.", "Server nepodporuje SSL.");
/*  16 */     hashtable.put("An error occured while setting up the SSL connection.", "Nastala chyba při nastavení SSL spojení.");
/*  17 */     hashtable.put("Connection rejected: {0}.", "Spojení odmítnuto: {0}.");
/*  18 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Server vyžaduje ověření heslem, ale žádné nebylo posláno.");
/*  19 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "Ověření typu {0} není podporováno. Zkontrolujte zda konfigurační soubor pg_hba.conf obsahuje klientskou IP adresu či podsíť a zda je použité ověřenovací schéma podporováno ovladačem.");
/*  20 */     hashtable.put("Protocol error.  Session setup failed.", "Chyba protokolu. Nastavení relace selhalo.");
/*  21 */     hashtable.put("Backend start-up failed: {0}.", "Selhal start backendu: {0}.");
/*  22 */     hashtable.put("An unexpected result was returned by a query.", "Obdržen neočekávaný výsledek dotazu.");
/*  23 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Index sloupece je mimo rozsah: {0}, počet sloupců: {1}.");
/*  24 */     hashtable.put("No value specified for parameter {0}.", "Nespecifikována hodnota parametru {0}.");
/*  25 */     hashtable.put("Expected command status BEGIN, got {0}.", "Očekáván příkaz BEGIN, obdržen {0}.");
/*  26 */     hashtable.put("Unexpected command status: {0}.", "Neočekávaný stav příkazu: {0}.");
/*  27 */     hashtable.put("An I/O error occured while sending to the backend.", "Vystupně/výstupní chyba při odesílání k backend.");
/*  28 */     hashtable.put("Unknown Response Type {0}.", "Neznámý typ odpovědi {0}.");
/*  29 */     hashtable.put("The driver currently does not support COPY operations.", "Ovladač nyní nepodporuje příkaz COPY.");
/*  30 */     hashtable.put("DataSource has been closed.", "DataSource byl uzavřen.");
/*  31 */     hashtable.put("This PooledConnection has already been closed.", "Tento PooledConnection byl uzavřen.");
/*  32 */     hashtable.put("Connection has been closed.", "Spojeni bylo uzavřeno.");
/*  33 */     hashtable.put("Statement has been closed.", "Statement byl uzavřen.");
/*  34 */     hashtable.put("The array index is out of range: {0}", "Index pole mimo rozsah: {0}");
/*  35 */     hashtable.put("Multi-dimensional arrays are currently not supported.", "Více-rozměrné pole nejsou nyní podporovány.");
/*  36 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Index pole mimo rozsah: {0}, počet prvků: {1}.");
/*  37 */     hashtable.put("LOB positioning offsets start at 1.", "Začátek pozicování LOB začína na 1.");
/*  38 */     hashtable.put("No results were returned by the query.", "Neobdržen žádný výsledek dotazu.");
/*  39 */     hashtable.put("A result was returned when none was expected.", "Obdržen výsledek, ikdyž žádný nebyl očekáván.");
/*  40 */     hashtable.put("Failed to create object for: {0}.", "Selhalo vytvoření objektu: {0}.");
/*  41 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Nemohu načíst třídu {0} odpovědnou za typ {1}");
/*  42 */     hashtable.put("Unable to translate data into the desired encoding.", "Nemohu přeložit data do požadovaného kódování.");
/*  43 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Nemohu najít název typu v systémovém katalogu.");
/*  44 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Neočekávaná chyba běham dekódování znaku z velkého objektu.");
/*  45 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Nemůžete používat relativní přesuny při vkládání řádku.");
/*  46 */     hashtable.put("Invalid fetch direction constant: {0}.", "Špatný směr čtení: {0}.");
/*  47 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Nemůžete volat cancelRowUpdates() při vkládání řádku.");
/*  48 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Nemůžete volat deleteRow() při vkládání řádku.");
/*  49 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Právě jste za pozicí konce ResultSetu. Zde nemůžete volat deleteRow().s");
/*  50 */     hashtable.put("There are no rows in this ResultSet.", "Žádný řádek v ResultSet.");
/*  51 */     hashtable.put("Not on the insert row.", "Ne na vkládaném řádku.");
/*  52 */     hashtable.put("You must specify at least one column value to insert a row.", "Musíte vyplnit alespoň jeden sloupec pro vložení řádku.");
/*  53 */     hashtable.put("The JVM claims not to support the encoding: {0}", "JVM tvrdí, že nepodporuje kodování: {0}");
/*  54 */     hashtable.put("Provided InputStream failed.", "Selhal poskytnutý InputStream.");
/*  55 */     hashtable.put("Provided Reader failed.", "Selhal poskytnutý Reader.");
/*  56 */     hashtable.put("Can''t refresh the insert row.", "Nemohu obnovit vkládaný řádek.");
/*  57 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Nemohu volat updateRow() na vlkádaném řádku.");
/*  58 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets se souběžností CONCUR_READ_ONLY nemůže být aktualizováno");
/*  59 */     hashtable.put("No primary key found for table {0}.", "Nenalezen primární klíč pro tabulku {0}.");
/*  60 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Nabraná velikost musí být nezáporná.");
/*  61 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Nalezena vada ve znakových datech. Toto může být způsobeno uloženými daty obsahujícími znaky, které jsou závadné pro znakovou sadu nastavenou při zakládání databáze. Nejznámejší příklad je ukládání 8bitových dat vSQL_ASCII databázi.");
/*  62 */     hashtable.put("Bad value for type {0} : {1}", "Špatná hodnota pro typ {0} : {1}");
/*  63 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Sloupec pojmenovaný {0} nebyl nalezen v ResultSet.");
/*  64 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "ResultSet není aktualizavatelný. Dotaz musí vybírat pouze z jedné tabulky a musí obsahovat všechny primární klíče tabulky. Koukni do JDBC 2.1 API Specifikace, sekce 5.6 pro více podrobností.");
/*  65 */     hashtable.put("This ResultSet is closed.", "Tento ResultSet je uzavřený.");
/*  66 */     hashtable.put("Multiple ResultSets were returned by the query.", "Vícenásobný ResultSet byl vrácen dotazem.");
/*  67 */     hashtable.put("A CallableStatement was executed with nothing returned.", "CallableStatement byl spuštěn, leč nic nebylo vráceno.");
/*  68 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Maximální počet řádek musí být nezáporné číslo.");
/*  69 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Časový limit dotazu musí být nezáporné číslo.");
/*  70 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "Maximální velikost pole musí být nezáporné číslo.");
/*  71 */     hashtable.put("Unknown Types value.", "Neznámá hodnota typu.");
/*  72 */     hashtable.put("Invalid stream length {0}.", "Vadná délka proudu {0}.");
/*  73 */     hashtable.put("The JVM claims not to support the {0} encoding.", "JVM tvrdí, že nepodporuje kodování {0}.");
/*  74 */     hashtable.put("Unknown type {0}.", "Neznámý typ {0}.");
/*  75 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Nemohu přetypovat instanci {0} na typ {1}");
/*  76 */     hashtable.put("Unsupported Types value: {0}", "Nepodporovaná hodnota typu: {0}");
/*  77 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Poškozená funkce nebo opuštění procedury na pozici {0}.");
/*  78 */     hashtable.put("This statement has been closed.", "Příkaz byl uzavřen.");
/*  79 */     hashtable.put("Too many update results were returned.", "Bylo vráceno příliš mnoho výsledků aktualizací.");
/*  80 */     hashtable.put("Unexpected error writing large object to database.", "Neočekávaná chyba při zapisování velkého objektu do databáze.");
/*  81 */     hashtable.put("{0} function takes one and only one argument.", "Funkce {0} bere jeden argument.");
/*  82 */     hashtable.put("{0} function takes two and only two arguments.", "Funkce {0} bere právě dva argumenty.");
/*  83 */     hashtable.put("rand function only takes zero or one argument(the seed).", "Funkce rand bere žádný nebo jen jeden argument(seed).");
/*  84 */     hashtable.put("{0} function takes four and only four argument.", "Funkce {0} bere přesně čtyři argumenty.");
/*  85 */     hashtable.put("{0} function takes two or three arguments.", "Funkce {0} bere dva nebo tři argumenty.");
/*  86 */     hashtable.put("{0} function doesn''t take any argument.", "Funkce {0} nebere žádný argument.");
/*  87 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Nekonečná hodnota pro timestamp/date. Toto nemůže reprezentovat čas.");
/*  88 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "Třída {0} nepodporuje org.postgresql.util.PGobject.");
/*  89 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Verze serveru nižší než 8.0 nepodporují savepoints.");
/*  90 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Nemohu vytvořit savepoint v auto-commit modu.");
/*  91 */     hashtable.put("Returning autogenerated keys is not supported.", "Vrácení automaticky generovaných klíčů není podporováno.");
/*  92 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Index parametru mimo rozsah: {0}, počet parametrů {1}.");
/*  93 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Nemohu získat odkaz na savepoint, když byl uvolněn.");
/*  94 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Nemohu získat id nepojmenovaného savepointu.");
/*  95 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Nemohu získat název nepojmenovaného savepointu.");
/*  96 */     hashtable.put("Failed to initialize LargeObject API", "Selhala inicializace LargeObject API");
/*  97 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Velké objecky nemohou být použity v auto-commit modu.");
/*  98 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "Třída SSLSocketFactory poskytla {0} což nemůže být instancionizováno.");
/*  99 */     hashtable.put("Conversion of money failed.", "Převod peněz selhal.");
/* 100 */     hashtable.put("Exception: {0}", "Vyjímka: {0}");
/* 101 */     hashtable.put("Stack Trace:", "Výpis zásobníku:");
/* 102 */     hashtable.put("End of Stack Trace", "Konec výpisu zásobníku");
/* 103 */     hashtable.put("Exception generating stacktrace for: {0} encountered: {1}", "Vyjímka tvořící výpis zásobníku pro: {0} encountered: {1}");
/* 104 */     hashtable.put("Detail: {0}", "Detail: {0}");
/* 105 */     hashtable.put("Hint: {0}", "Rada: {0}");
/* 106 */     hashtable.put("Position: {0}", "Pozice: {0}");
/* 107 */     hashtable.put("Where: {0}", "Kde: {0}");
/* 108 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Poloha: Soubor: {0}, Rutina: {1}, Řádek: {2}");
/* 109 */     hashtable.put("Server SQLState: {0}", "Server SQLState: {0}");
/* 110 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 113 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 116 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 119 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_cs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */