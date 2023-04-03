/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_sr extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL 8.1\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2009-06-01 16:37-0700\nPO-Revision-Date: 2009-05-26 11:13+0100\nLast-Translator: Bojan Škaljac <skaljac (at) gmail.com>\nLanguage-Team: Srpski <skaljac@gmail.com>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Poedit-Language: Serbian\nX-Poedit-Country: YUGOSLAVIA\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Greška u čitanju standardnih podešavanja iz driverconfig.properties");
/*   9 */     hashtable.put("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to.", "Sigurnosna podešavanja su sprečila konekciju. Verovatno je potrebno da dozvolite konekciju klasi java.net.SocketPermission na bazu na serveru.");
/*  10 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Nešto neobično se dogodilo i drajver je zakazao. Molim prijavite ovaj izuzetak.");
/*  11 */     hashtable.put("Connection attempt timed out.", "Isteklo je vreme za pokušaj konektovanja.");
/*  12 */     hashtable.put("Interrupted while attempting to connect.", "Prekinut pokušaj konektovanja.");
/*  13 */     hashtable.put("Method {0} is not yet implemented.", "Metod {0} nije još impelemtiran.");
/*  14 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Konekciju nije moguće kreirati uz pomoć protokola {0}.");
/*  15 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Prevremen završetak ulaznog toka podataka,očekivano {0} bajtova, a pročitano samo {1}.");
/*  16 */     hashtable.put("Expected an EOF from server, got: {0}", "Očekivan EOF od servera, a dobijeno: {0}");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Ilegalna UTF-8 sekvenca: bajt {0} od {1} bajtova sekvence nije 10xxxxxx: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Ilegalna UTF-8 sekvenca: {0} bytes used to encode a {1} byte value: {2}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Ilegalna UTF-8 sekvenca: inicijalni bajt je {0}: {1}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Ilegalna UTF-8 sekvenca: finalna vrednost je van opsega: {0}");
/*  21 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Ilegalna UTF-8 sekvenca: finalna vrednost je zamena vrednosti: {0}");
/*  22 */     hashtable.put("Zero bytes may not occur in string parameters.", "Nula bajtovji se ne smeju pojavljivati u string parametrima.");
/*  23 */     hashtable.put("Zero bytes may not occur in identifiers.", "Nula bajtovji se ne smeju pojavljivati u identifikatorima.");
/*  24 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "Nije moguće konvertovati instancu {0} u tip {1}");
/*  25 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Konekcija odbijena. Proverite dali je ime domćina (host) koretno i da postmaster podržava TCP/IP konekcije.");
/*  26 */     hashtable.put("The connection attempt failed.", "Pokušaj konektovanja propao.");
/*  27 */     hashtable.put("The server does not support SSL.", "Server ne podržava SSL.");
/*  28 */     hashtable.put("An error occured while setting up the SSL connection.", "Greška se dogodila prilikom podešavanja SSL konekcije.");
/*  29 */     hashtable.put("Connection rejected: {0}.", "Konekcija odbačena: {0}.");
/*  30 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Server zahteva autentifikaciju baziranu na šifri, ali šifra nije prosleđena.");
/*  31 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "Tip autentifikacije {0} nije podržan. Proverite dali imate podešen pg_hba.conf fajl koji uključuje klijentovu IP adresu ili podmrežu, i da ta mreža koristi šemu autentifikacije koja je podržana od strane ovog drajvera.");
/*  32 */     hashtable.put("Protocol error.  Session setup failed.", "Greška protokola.  Zakazivanje sesije propalo.");
/*  33 */     hashtable.put("Backend start-up failed: {0}.", "Pozadinsko startovanje propalo: {0}.");
/*  34 */     hashtable.put("An unexpected result was returned by a query.", "Nepredviđen rezultat je vraćen od strane upita.");
/*  35 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Indeks kolone van osega: {0}, broj kolona: {1}.");
/*  36 */     hashtable.put("No value specified for parameter {0}.", "Nije zadata vrednost za parametar {0}.");
/*  37 */     hashtable.put("Expected command status BEGIN, got {0}.", "Očekivan status komande je BEGIN, a dobijeno je {0}.");
/*  38 */     hashtable.put("Unexpected command status: {0}.", "Neočekivan komandni status: {0}.");
/*  39 */     hashtable.put("An I/O error occured while sending to the backend.", "Ulazno/izlazna greška se dogodila prilikom slanja podataka pozadinskom procesu.");
/*  40 */     hashtable.put("Unknown Response Type {0}.", "Nepoznat tip odziva {0}.");
/*  41 */     hashtable.put("Ran out of memory retrieving query results.", "Nestalo je memorije prilikom preuzimanja rezultata upita.");
/*  42 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Neuspešno prekidanje prebrojavanja ažurivanja u tagu zakompletiranje komandi: {0}.");
/*  43 */     hashtable.put("Unable to bind parameter values for statement.", "Nije moguće naći vrednost vezivnog parametra za izjavu (statement).");
/*  44 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "Dužina vezivne poruke {0} prevelika.  Ovo je možda rezultat veoma velike ili pogrešne dužine specifikacije za InputStream parametre.");
/*  45 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "Serverov client_encoding parametar je promenjen u  {0}.JDBC darajver zahteva UNICODE client_encoding  za uspešno izvršavanje operacije.");
/*  46 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "Serverov DataStyle parametar promenjen u {0}. JDBC zahteva da DateStyle počinje sa ISO za uspešno završavanje operacije.");
/*  47 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "Serverov standard_conforming_strings parametar javlja {0}. JDBC drajver ocekuje on ili off.");
/*  48 */     hashtable.put("The driver currently does not support COPY operations.", "Drajver trenutno ne podržava COPY operacije.");
/*  49 */     hashtable.put("This PooledConnection has already been closed.", "PooledConnection je već zatvoren.");
/*  50 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Konekcija je zatvorena automatski zato što je nova konekcija otvorena za isti PooledConnection ili je PooledConnection zatvoren.");
/*  51 */     hashtable.put("Connection has been closed.", "Konekcija je već zatvorena.");
/*  52 */     hashtable.put("Statement has been closed.", "Statemen je već zatvoren.");
/*  53 */     hashtable.put("DataSource has been closed.", "DataSource je zatvoren.");
/*  54 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Fastpath poziv {0} - Nikakav rezultat nije vraćen a očekivan je integer.");
/*  55 */     hashtable.put("The fastpath function {0} is unknown.", "Fastpath funkcija {0} je nepoznata.");
/*  56 */     hashtable.put("Conversion to type {0} failed: {1}.", "Konverzija u tip {0} propala: {1}.");
/*  57 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Nije moguće utvrditi dali je putanja otvorena ili zatvorena: {0}.");
/*  58 */     hashtable.put("The array index is out of range: {0}", "Indeks niza je van opsega: {0}");
/*  59 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Indeks niza je van opsega: {0}, broj elemenata: {1}.");
/*  60 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "Skraćivanje velikih objekata je implementirano samo u 8.3 i novijim serverima.");
/*  61 */     hashtable.put("LOB positioning offsets start at 1.", "LOB pozicija ofset počinje kod 1.");
/*  62 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "PostgreSQL LOB mogu jedino da označavaju: {0}");
/*  63 */     hashtable.put("free() was called on this LOB previously", "free() je pozvan na ovom LOB-u prethodno");
/*  64 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "Vrednost za parametar tipa string nije podržana: {0}");
/*  65 */     hashtable.put("No results were returned by the query.", "Nikakav rezultat nije vraćen od strane upita.");
/*  66 */     hashtable.put("A result was returned when none was expected.", "Rezultat vraćen ali nikakav rezultat nije očekivan.");
/*  67 */     hashtable.put("Custom type maps are not supported.", "Mape sa korisnički definisanim tipovima nisu podržane.");
/*  68 */     hashtable.put("Failed to create object for: {0}.", "Propao pokušaj kreiranja objekta za: {0}.");
/*  69 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Nije moguće učitati kalsu {0} odgovornu za tip podataka {1}");
/*  70 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Nije moguće izmeniti read-only osobinu transakcije u sred izvršavanja transakcije.");
/*  71 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Nije moguće izmeniti nivo izolacije transakcije u sred izvršavanja transakcije.");
/*  72 */     hashtable.put("Transaction isolation level {0} not supported.", "Nivo izolacije transakcije {0} nije podržan.");
/*  73 */     hashtable.put("Finalizing a Connection that was never closed:", "Dovršavanje konekcije koja nikada nije zatvorena:");
/*  74 */     hashtable.put("Unable to translate data into the desired encoding.", "Nije moguće prevesti podatke u odabrani encoding format.");
/*  75 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Nije moguće odrediti vrednost za MaxIndexKezs zbog nedostatka podataka u sistemskom katalogu.");
/*  76 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Nije moguće pronaći ime tipa podatka u sistemskom katalogu.");
/*  77 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "Operacija zahteva skrolabilan ResultSet,ali ovaj ResultSet je FORWARD_ONLY.");
/*  78 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Neočekivana greška prilikom dekodiranja karaktera iz velikog objekta.");
/*  79 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Ne može se koristiti metod relativnog pomeranja prilikom ubacivanja redova.");
/*  80 */     hashtable.put("Invalid fetch direction constant: {0}.", "Pogrešna konstanta za direkciju donošenja: {0}.");
/*  81 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Nije moguće pozvati cancelRowUpdates() prilikom ubacivanja redova.");
/*  82 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Nije moguće pozvati deleteRow() prilikom ubacivanja redova.");
/*  83 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Trenutna pozicija pre početka ResultSet-a.  Ne možete pozvati deleteRow() na toj poziciji.");
/*  84 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Trenutna pozicija posle kraja ResultSet-a.  Ne možete pozvati deleteRow() na toj poziciji.");
/*  85 */     hashtable.put("There are no rows in this ResultSet.", "U ResultSet-u nema redova.");
/*  86 */     hashtable.put("Not on the insert row.", "Nije mod ubacivanja redova.");
/*  87 */     hashtable.put("You must specify at least one column value to insert a row.", "Morate specificirati barem jednu vrednost za kolonu da bi ste ubacili red.");
/*  88 */     hashtable.put("The JVM claims not to support the encoding: {0}", "JVM tvrdi da ne podržava encoding: {0}");
/*  89 */     hashtable.put("Provided InputStream failed.", "Pribaljeni InputStream zakazao.");
/*  90 */     hashtable.put("Provided Reader failed.", "Pribavljeni čitač (Reader) zakazao.");
/*  91 */     hashtable.put("Can''t refresh the insert row.", "Nije moguće osvežiti ubačeni red.");
/*  92 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Nije moguće pozvati updateRow() prilikom ubacivanja redova.");
/*  93 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "Nije moguće ažurirati ResultSet zato što je ili početak ili kraj rezultata.");
/*  94 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets sa osobinom CONCUR_READ_ONLY ne moeže biti ažuriran.");
/*  95 */     hashtable.put("No primary key found for table {0}.", "Nije pronađen ključ za tabelu {0}.");
/*  96 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Doneta veličina mora biti vrednost veća ili jednaka 0.");
/*  97 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Pronađeni su nevažeći karakter podaci. Uzrok je najverovatnije to što pohranjeni podaci sadrže karaktere koji su nevažeći u setu karaktera sa kojima je baza kreirana.  Npr. Čuvanje 8bit podataka u SQL_ASCII bazi podataka.");
/*  98 */     hashtable.put("Bad value for type {0} : {1}", "Pogrešna vrednost za tip {0} : {1}");
/*  99 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Ime kolone {0} nije pronadjeno u ResultSet.");
/* 100 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "ResultSet nije moguće ažurirati. Upit koji je generisao ovaj razultat mora selektoati jedino tabelu,i mora selektovati sve primrne ključeve iz te tabele. Pogledajte API specifikaciju za JDBC 2.1, sekciju 5.6 za više detalja.");
/* 101 */     hashtable.put("This ResultSet is closed.", "ResultSet je zatvoren.");
/* 102 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "ResultSet nije pravilno pozicioniran, možda je potrebno da pozovete next.");
/* 103 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "Ne možete da koristite metode za upit koji uzimaju string iz upita u PreparedStatement-u.");
/* 104 */     hashtable.put("Multiple ResultSets were returned by the query.", "Višestruki ResultSet-vi su vraćeni od strane upita.");
/* 105 */     hashtable.put("A CallableStatement was executed with nothing returned.", "CallableStatement je izvršen ali ništa nije vrećeno kao rezultat.");
/* 106 */     hashtable.put("A CallableStatement was executed with an invalid number of parameters", "CallableStatement je izvršen sa nevažećim brojem parametara");
/* 107 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "CallableStatement funkcija je izvršena dok je izlazni parametar {0} tipa {1} a tip {2} je registrovan kao izlazni parametar.");
/* 108 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Maksimalni broj redova mora biti vrednosti veće ili jednake 0.");
/* 109 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Tajm-aut mora biti vrednost veća ili jednaka 0.");
/* 110 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "Maksimalna vrednost veličine polja mora biti vrednost veća ili jednaka 0.");
/* 111 */     hashtable.put("Unknown Types value.", "Nepoznata vrednost za Types.");
/* 112 */     hashtable.put("Invalid stream length {0}.", "Nevažeća dužina toka {0}.");
/* 113 */     hashtable.put("The JVM claims not to support the {0} encoding.", "JVM tvrdi da ne podržava {0} encoding.");
/* 114 */     hashtable.put("Unknown type {0}.", "Nepoznat tip {0}.");
/* 115 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Nije moguće kastovati instancu {0} u tip {1}");
/* 116 */     hashtable.put("Unsupported Types value: {0}", "Za tip nije podržana vrednost: {0}");
/* 117 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "Nije moguće zaključiti SQL tip koji bi se koristio sa instancom {0}. Koristite setObject() sa zadatim eksplicitnim tipom vrednosti.");
/* 118 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Izraz ne deklariše izlazni parametar. Koristite '{' ?= poziv ... '}' za deklarisanje.");
/* 119 */     hashtable.put("wasNull cannot be call before fetching a result.", "wasNull nemože biti pozvan pre zahvatanja rezultata.");
/* 120 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Pogrešna sintaksa u funkciji ili proceduri na poziciji {0}.");
/* 121 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "Parametar tipa {0} je registrovan,ali poziv za get{1} (sql tip={2}) je izvršen.");
/* 122 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "CallableStatement jedeklarisan ali nije bilo poziva registerOutParameter (1, <neki_tip>).");
/* 123 */     hashtable.put("No function outputs were registered.", "Nije registrovan nikakv izlaz iz funkcije.");
/* 124 */     hashtable.put("Results cannot be retrieved from a CallableStatement before it is executed.", "Razultat nemože da se primi iz CallableStatement pre nego što se on izvrši.");
/* 125 */     hashtable.put("This statement has been closed.", "Statement je zatvoren.");
/* 126 */     hashtable.put("Too many update results were returned.", "Previše rezultata za ažuriranje je vraćeno.");
/* 127 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "Smeša prijava {0} {1} je odbačena. Pozovite getNextException da proverite rezlog.");
/* 128 */     hashtable.put("Unexpected error writing large object to database.", "Neočekivana greška prilikom upisa velikog objekta u bazu podataka.");
/* 129 */     hashtable.put("{0} function takes one and only one argument.", "Funkcija {0} prima jedan i samo jedan parametar.");
/* 130 */     hashtable.put("{0} function takes two and only two arguments.", "Funkcija {0} prima dva i samo dva parametra.");
/* 131 */     hashtable.put("{0} function takes four and only four argument.", "Funkcija {0} prima četiri i samo četiri parametra.");
/* 132 */     hashtable.put("{0} function takes two or three arguments.", "Funkcija {0} prima dva ili tri parametra.");
/* 133 */     hashtable.put("{0} function doesn''t take any argument.", "Funkcija {0} nema parametara.");
/* 134 */     hashtable.put("{0} function takes three and only three arguments.", "Funkcija {0} prima tri i samo tri parametra.");
/* 135 */     hashtable.put("Interval {0} not yet implemented", "Interval {0} još nije implementiran.");
/* 136 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Beskonačna vrednost je pronađena za tipestamp/date. To se nemože predstaviti kao vreme.");
/* 137 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "Klasa {0} ne implementira org.postgresql.util.PGobject.");
/* 138 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "Nepoznata ResultSet podešavanja za mogućnost držanja (holdability): {0}.");
/* 139 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Verzije servera manje od 8.0 ne podržavaju tačke snimanja.");
/* 140 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "U auto-commit modu nije moguće podešavanje tački snimanja.");
/* 141 */     hashtable.put("Returning autogenerated keys is not supported.", "Vraćanje autogenerisanih ključeva nije podržano.");
/* 142 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Index parametra je van opsega: {0}, broj parametara je: {1}.");
/* 143 */     hashtable.put("Returning autogenerated keys is only supported for 8.2 and later servers.", "Vraćanje autogenerisanih ključeva je podržano samo za verzije servera od 8.2 pa na dalje.");
/* 144 */     hashtable.put("Returning autogenerated keys by column index is not supported.", "Vraćanje autogenerisanih ključeva po kloloni nije podržano.");
/* 145 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Nije moguće referenciranje tačke snimanja nakon njenog oslobađanja.");
/* 146 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Nije moguće primiti id imena tačke snimanja.");
/* 147 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Nije moguće izvaditi ime tačke snimanja koja nema ime.");
/* 148 */     hashtable.put("Invalid UUID data.", "Nevažeća UUID podatak.");
/* 149 */     hashtable.put("Unable to find server array type for provided name {0}.", "Neuspešno nalaženje liste servera za zadato ime {0}.");
/* 150 */     hashtable.put("ClientInfo property not supported.", "ClientInfo property nije podržan.");
/* 151 */     hashtable.put("Unable to decode xml data.", "Neuspešno dekodiranje XML podataka.");
/* 152 */     hashtable.put("Unknown XML Source class: {0}", "Nepoznata XML ulazna klasa: {0}");
/* 153 */     hashtable.put("Unable to create SAXResult for SQLXML.", "Nije moguće kreirati SAXResult za SQLXML.");
/* 154 */     hashtable.put("Unable to create StAXResult for SQLXML", "Nije moguće kreirati StAXResult za SQLXML");
/* 155 */     hashtable.put("Unknown XML Result class: {0}", "nepoznata XML klasa rezultata: {0}");
/* 156 */     hashtable.put("This SQLXML object has already been freed.", "Ovaj SQLXML je već obrisan.");
/* 157 */     hashtable.put("This SQLXML object has not been initialized, so you cannot retrieve data from it.", "SQLXML objekat nije inicijalizovan tako da nije moguće preuzimati podatke iz njega.");
/* 158 */     hashtable.put("Failed to convert binary xml data to encoding: {0}.", "Neuspešno konvertovanje binarnih XML podataka u kodnu stranu: {0}.");
/* 159 */     hashtable.put("Unable to convert DOMResult SQLXML data to a string.", "Nije moguće konvertovati DOMResult SQLXML podatke u string.");
/* 160 */     hashtable.put("This SQLXML object has already been initialized, so you cannot manipulate it further.", "SQLXML objekat je već inicijalizovan, tako da ga nije moguće dodatno menjati.");
/* 161 */     hashtable.put("Failed to initialize LargeObject API", "Propao pokušaj inicijalizacije LargeObject API-ja.");
/* 162 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Veliki objekti (Large Object) se nemogu koristiti u auto-commit modu.");
/* 163 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "SSLSocketFactory klasa koju pruža {0} se nemože instancirati.");
/* 164 */     hashtable.put("Conversion of interval failed", "Konverzija intervala propala.");
/* 165 */     hashtable.put("Conversion of money failed.", "Konverzija novca (money) propala.");
/* 166 */     hashtable.put("Detail: {0}", "Detalji: {0}");
/* 167 */     hashtable.put("Hint: {0}", "Nagovest: {0}");
/* 168 */     hashtable.put("Position: {0}", "Pozicija: {0}");
/* 169 */     hashtable.put("Where: {0}", "Gde: {0}");
/* 170 */     hashtable.put("Internal Query: {0}", "Interni upit: {0}");
/* 171 */     hashtable.put("Internal Position: {0}", "Interna pozicija: {0}");
/* 172 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Lokacija: Fajl: {0}, Rutina: {1}, Linija: {2}");
/* 173 */     hashtable.put("Server SQLState: {0}", "SQLState servera: {0}");
/* 174 */     hashtable.put("Invalid flags", "Nevažeće zastavice");
/* 175 */     hashtable.put("xid must not be null", "xid ne sme biti null");
/* 176 */     hashtable.put("Connection is busy with another transaction", "Konekcija je zauzeta sa drugom transakciom.");
/* 177 */     hashtable.put("suspend/resume not implemented", "obustavljanje/nastavljanje nije implementirano.");
/* 178 */     hashtable.put("Transaction interleaving not implemented", "Preplitanje transakcija nije implementirano.");
/* 179 */     hashtable.put("Error disabling autocommit", "Greška u isključivanju autokomita");
/* 180 */     hashtable.put("tried to call end without corresponding start call", "Pokušaj pozivanja kraja pre odgovarajućeg početka.");
/* 181 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Nije implementirano: Spremanje mora biti pozvano uz korišćenje iste konekcije koja se koristi za startovanje transakcije.");
/* 182 */     hashtable.put("Prepare called before end", "Pripremanje poziva pre kraja.");
/* 183 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Verzije servera pre 8.1 verzije ne podržavaju commit iz dve faze.");
/* 184 */     hashtable.put("Error preparing transaction", "Greška u pripremanju transakcije.");
/* 185 */     hashtable.put("Invalid flag", "Nevažeća zastavica (flag)");
/* 186 */     hashtable.put("Error during recover", "Greška prilikom oporavljanja.");
/* 187 */     hashtable.put("Error rolling back prepared transaction", "Greška prilikom povratka na prethodo pripremljenu transakciju.");
/* 188 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Nije implementirano: Commit iz jedne faze mora biti izdat uz korištenje iste konekcije koja je korištena za startovanje.");
/* 189 */     hashtable.put("commit called before end", "commit pozvan pre kraja.");
/* 190 */     hashtable.put("Error during one-phase commit", "Kreška prilikom commit-a iz jedne faze.");
/* 191 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Nije implementirano: Dvofazni commit mora biti izdat uz korištenje besposlene konekcije.");
/* 192 */     hashtable.put("Heuristic commit/rollback not supported", "Heuristički commit/rollback nije podržan.");
/* 193 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 196 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 199 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 202 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_sr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */