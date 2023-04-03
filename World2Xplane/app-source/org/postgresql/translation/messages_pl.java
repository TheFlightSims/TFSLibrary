/*    */ package org.postgresql.translation;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class messages_pl extends ResourceBundle {
/*    */   private static final Hashtable table;
/*    */   
/*    */   static {
/*  6 */     Hashtable hashtable = new Hashtable();
/*  7 */     hashtable.put("", "Project-Id-Version: head-pl\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2005-05-17 10:55-0700\nPO-Revision-Date: 2005-05-22 03:01+0200\nLast-Translator: Jarosław Jan Pyszny <jarek@pyszny.net>\nLanguage-Team:  <pl@li.org>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Generator: KBabel 1.10\nPlural-Forms:  nplurals=3; plural=(n==1 ? 0 : n%10>=2 && n%10<=4 && (n%100<10 || n%100>=20) ? 1 : 2);\n");
/*  8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Błąd podczas wczytywania ustawień domyślnych z driverconfig.properties");
/*  9 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Coś niezwykłego spowodowało pad sterownika. Proszę, zgłoś ten wyjątek.");
/* 10 */     hashtable.put("Method {0} is not yet implemented.", "Metoda {0}nie jest jeszcze obsługiwana.");
/* 11 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "Nie można było nawiązać połączenia stosując żądany protokołu {0}.");
/* 12 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Przedwczesny koniec strumienia wejściowego, oczekiwano {0} bajtów, odczytano tylko {1}.");
/* 13 */     hashtable.put("The driver does not support SSL.", "Sterownik nie wspiera SSL.");
/* 14 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Połączenie odrzucone. Sprawdź, czy prawidłowo ustawiłeś nazwę hosta oraz port i upewnij się, czy postmaster przyjmuje połączenia TCP/IP.");
/* 15 */     hashtable.put("The connection attempt failed.", "Próba nawiązania połączenia nie powiodła się.");
/* 16 */     hashtable.put("The server does not support SSL.", "Serwer nie obsługuje SSL.");
/* 17 */     hashtable.put("An error occured while setting up the SSL connection.", "Wystąpił błąd podczas ustanawiania połączenia SSL.");
/* 18 */     hashtable.put("Connection rejected: {0}.", "Połączenie odrzucone: {0}.");
/* 19 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Serwer zażądał uwierzytelnienia opartego na haśle, ale żadne hasło nie zostało dostarczone.");
/* 20 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "Uwierzytelnienie typu {0} nie jest obsługiwane. Upewnij się, że skonfigurowałeś plik pg_hba.conf tak, że zawiera on adres IP lub podsieć klienta oraz że użyta metoda uwierzytelnienia jest wspierana przez ten sterownik.");
/* 21 */     hashtable.put("Protocol error.  Session setup failed.", "Błąd protokołu. Nie udało się utworzyć sesji.");
/* 22 */     hashtable.put("Backend start-up failed: {0}.", "Start serwera się nie powiódł: {0}.");
/* 23 */     hashtable.put("An unexpected result was returned by a query.", "Zapytanie zwróciło nieoczekiwany wynik.");
/* 24 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Indeks kolumny jest poza zakresem: {0}, liczba kolumn: {1}.");
/* 25 */     hashtable.put("No value specified for parameter {0}.", "Nie podano wartości dla parametru {0}.");
/* 26 */     hashtable.put("Expected command status BEGIN, got {0}.", "Spodziewano się statusu komendy BEGIN, otrzymano {0}.");
/* 27 */     hashtable.put("Unexpected command status: {0}.", "Nieoczekiwany status komendy: {0}.");
/* 28 */     hashtable.put("An I/O error occured while sending to the backend.", "Wystąpił błąd We/Wy podczas wysyłania do serwera.");
/* 29 */     hashtable.put("Unknown Response Type {0}.", "Nieznany typ odpowiedzi {0}.");
/* 30 */     hashtable.put("Zero bytes may not occur in string parameters.", "Zerowe bajty nie mogą pojawiać się w parametrach typu łańcuch znakowy.");
/* 31 */     hashtable.put("The driver currently does not support COPY operations.", "Sterownik nie obsługuje aktualnie operacji COPY.");
/* 32 */     hashtable.put("DataSource has been closed.", "DataSource zostało zamknięte.");
/* 33 */     hashtable.put("This PooledConnection has already been closed.", "To PooledConnection zostało już zamknięte.");
/* 34 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Połączenie zostało zamknięte automatycznie, ponieważ nowe połączenie zostało otwarte dla tego samego PooledConnection lub PooledConnection zostało zamknięte.");
/* 35 */     hashtable.put("Connection has been closed.", "Połączenie zostało zamknięte.");
/* 36 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Wywołanie fastpath {0} - Nie otrzymano żadnego wyniku, a oczekiwano liczby całkowitej.");
/* 37 */     hashtable.put("The fastpath function {0} is unknown.", "Funkcja fastpath {0} jest nieznana.");
/* 38 */     hashtable.put("Conversion to type {0} failed: {1}.", "Konwersja do typu {0} nie powiodła się: {1}.");
/* 39 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Nie można stwierdzić, czy ścieżka jest otwarta czy zamknięta: {0}.");
/* 40 */     hashtable.put("The array index is out of range: {0}", "Indeks tablicy jest poza zakresem: {0}");
/* 41 */     hashtable.put("Multi-dimensional arrays are currently not supported.", "Wielowymiarowe tablice nie są aktualnie obsługiwane.");
/* 42 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Indeks tablicy jest poza zakresem: {0}, liczba elementów: {1}.");
/* 43 */     hashtable.put("No results were returned by the query.", "Zapytanie nie zwróciło żadnych wyników.");
/* 44 */     hashtable.put("A result was returned when none was expected.", "Zwrócono wynik zapytania, choć nie był on oczekiwany.");
/* 45 */     hashtable.put("Failed to create object for: {0}.", "Nie powiodło się utworzenie obiektu dla: {0}.");
/* 46 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Nie jest możliwe załadowanie klasy {0} odpowiedzialnej za typ danych {1}");
/* 47 */     hashtable.put("Transaction isolation level {0} not supported.", "Poziom izolacji transakcji {0} nie jest obsługiwany.");
/* 48 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Nie można wywołać cancelRowUpdates() na wstawianym rekordzie.");
/* 49 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Nie można wywołać deleteRow() na wstawianym rekordzie.");
/* 50 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Aktualna pozycja przed początkiem ResultSet. Nie można wywołać deleteRow().");
/* 51 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Aktualna pozycja za końcem ResultSet. Nie można wywołać deleteRow().");
/* 52 */     hashtable.put("There are no rows in this ResultSet.", "Nie ma żadnych wierszy w tym ResultSet.");
/* 53 */     hashtable.put("Not on the insert row.", "Nie na wstawianym rekordzie.");
/* 54 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Nie można wywołać updateRow() na wstawianym rekordzie.");
/* 55 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Rozmiar pobierania musi być wartością dodatnią lub 0.");
/* 56 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Znaleziono nieprawidłowy znak. Najprawdopodobniej jest to spowodowane przechowywaniem w bazie znaków, które nie pasują do zestawu znaków wybranego podczas tworzenia bazy danych. Najczęstszy przykład to przechowywanie 8-bitowych znaków w bazie o kodowaniu SQL_ASCII.");
/* 57 */     hashtable.put("Bad value for type {0} : {1}", "Zła wartość dla typu {0}: {1}");
/* 58 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "ResultSet nie jest modyfikowalny (not updateable). Zapytanie, które zwróciło ten wynik musi dotyczyć tylko jednej tabeli oraz musi pobierać wszystkie klucze główne tej tabeli. Zobacz Specyfikację JDBC 2.1 API, rozdział 5.6, by uzyskać więcej szczegółów.");
/* 59 */     hashtable.put("This ResultSet is closed.", "Ten ResultSet jest zamknięty.");
/* 60 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "Zła pozycja w ResultSet, może musisz wywołać next.");
/* 61 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "Funkcja CallableStatement została zadeklarowana, ale nie wywołano registerOutParameter (1, <jakiś typ>).");
/* 62 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Maksymalna liczba rekordów musi być wartością dodatnią lub 0.");
/* 63 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Timeout zapytania musi być wartością dodatnią lub 0.");
/* 64 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "Maksymalny rozmiar pola musi być wartością dodatnią lub 0.");
/* 65 */     hashtable.put("Unknown Types value.", "Nieznana wartość Types.");
/* 66 */     hashtable.put("Unknown type {0}.", "Nieznany typ {0}.");
/* 67 */     hashtable.put("Unsupported Types value: {0}", "Nieznana wartość Types: {0}");
/* 68 */     hashtable.put("Too many update results were returned.", "Zapytanie nie zwróciło żadnych wyników.");
/* 69 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "Zadanie wsadowe {0} {1} zostało przerwane. Wywołaj getNextException by poznać przyczynę.");
/* 70 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "Klasa {0} nie implementuje org.postgresql.util.PGobject.");
/* 71 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Indeks parametru jest poza zakresem: {0}, liczba parametrów: {1}.");
/* 72 */     hashtable.put("Failed to initialize LargeObject API", "Nie udało się zainicjować LargeObject API");
/* 73 */     hashtable.put("Conversion of interval failed", "Konwersja typu interval nie powiodła się");
/* 74 */     hashtable.put("Conversion of money failed.", "Konwersja typu money nie powiodła się.");
/* 75 */     hashtable.put("Exception: {0}", "Wyjątek: {0}");
/* 76 */     hashtable.put("Stack Trace:", "Ślad stosu:");
/* 77 */     hashtable.put("End of Stack Trace", "Koniec śladu stosu");
/* 78 */     hashtable.put("Exception generating stacktrace for: {0} encountered: {1}", "Ślad stosu utworzony dla wyjątku: {0} napotkanego: {1}");
/* 79 */     hashtable.put("Detail: {0}", "Szczegóły: {0}");
/* 80 */     hashtable.put("Hint: {0}", "Wskazówka: {0}");
/* 81 */     hashtable.put("Position: {0}", "Pozycja: {0}");
/* 82 */     hashtable.put("Where: {0}", "Gdzie: {0}");
/* 83 */     hashtable.put("Internal Query: {0}", "Wewnętrzne Zapytanie: {0}");
/* 84 */     hashtable.put("Internal Position: {0}", "Wewnętrzna Pozycja: {0}");
/* 85 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Lokalizacja: Plik: {0}, Procedura: {1}, Linia: {2}");
/* 86 */     hashtable.put("Server SQLState: {0}", "Serwer SQLState: {0}");
/* 87 */     table = hashtable;
/*    */   }
/*    */   
/*    */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 90 */     return table.get(paramString);
/*    */   }
/*    */   
/*    */   public Enumeration getKeys() {
/* 93 */     return table.keys();
/*    */   }
/*    */   
/*    */   public ResourceBundle getParent() {
/* 96 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_pl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */