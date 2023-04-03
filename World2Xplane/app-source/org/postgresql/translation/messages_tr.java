/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_tr extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: jdbc-tr\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2009-06-01 17:02-0700\nPO-Revision-Date: 2009-05-31 21:47+0200\nLast-Translator: Devrim GÜNDÜZ <devrim@gunduz.org>\nLanguage-Team: Turkish <pgsql-tr-genel@PostgreSQL.org>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Generator: KBabel 1.3.1\nX-Poedit-Language: Turkish\nX-Poedit-Country: TURKEY\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "driverconfig.properties dosyasından varsayılan ayarları yükleme hatası");
/*   9 */     hashtable.put("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to.", "Güvenlik politikanız bağlantının kurulmasını engelledi. java.net.SocketPermission'a veritabanına ve de bağlanacağı porta bağlantı izni vermelisiniz.");
/*  10 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Sıradışı bir durum sürücünün hata vermesine sebep oldu. Lütfen bu durumu geliştiricilere bildirin.");
/*  11 */     hashtable.put("Connection attempt timed out.", "Bağlantı denemesi zaman aşımına uğradı.");
/*  12 */     hashtable.put("Interrupted while attempting to connect.", "Bağlanırken kesildi.");
/*  13 */     hashtable.put("Method {0} is not yet implemented.", "{0} yöntemi henüz kodlanmadı.");
/*  14 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "İstenilen protokol ile bağlantı kurulamadı {0}");
/*  15 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Giriş akımında beklenmeyen dosya sonu, {0} bayt beklenirken sadece {1} bayt alındı.");
/*  16 */     hashtable.put("Expected an EOF from server, got: {0}", "Sunucudan EOF beklendi; ama {0} alındı.");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Geçersiz UTF-8 çoklu bayt karakteri: {0}/{1} baytı 10xxxxxx değildir: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Geçersiz UTF-8 çoklu bayt karakteri: {0} bayt, {1} bayt değeri kodlamak için kullanılmış: {2}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Geçersiz UTF-8 çoklu bayt karakteri: ilk bayt {0}: {1}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Geçersiz UTF-8 çoklu bayt karakteri: son değer sıra dışıdır: {0}");
/*  21 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Geçersiz UTF-8 çoklu bayt karakteri: son değer yapay bir değerdir: {0}");
/*  22 */     hashtable.put("Zero bytes may not occur in string parameters.", "String parametrelerinde sıfır bayt olamaz.");
/*  23 */     hashtable.put("Zero bytes may not occur in identifiers.", "Belirteçlerde sıfır bayt olamaz.");
/*  24 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "{0} instance, {1} tipine dönüştürülemiyor");
/*  25 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Bağlantı reddedildi. Sunucu adı ve portun doğru olup olmadığını ve postmaster''in TCP/IP bağlantılarını kabul edip etmediğini kontrol ediniz.");
/*  26 */     hashtable.put("The connection attempt failed.", "Bağlantı denemesi başarısız oldu.");
/*  27 */     hashtable.put("The server does not support SSL.", "Sunucu SSL desteklemiyor.");
/*  28 */     hashtable.put("An error occured while setting up the SSL connection.", "SSL bağlantısı ayarlanırken bir hata oluştu.");
/*  29 */     hashtable.put("Connection rejected: {0}.", "Bağlantı reddedildi {0}");
/*  30 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Sunucu şifre tabanlı yetkilendirme istedi; ancak bir şifre sağlanmadı.");
/*  31 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "{0} yetkinlendirme tipi desteklenmemektedir. pg_hba.conf dosyanızı istemcinin IP adresini ya da subnetini içerecek şekilde ayarlayıp ayarlamadığınızı ve sürücü tarafından desteklenen yetkilendirme yöntemlerinden birisini kullanıp kullanmadığını kontrol ediniz.");
/*  32 */     hashtable.put("Protocol error.  Session setup failed.", "Protokol hatası.  Oturum kurulumu başarısız oldu.");
/*  33 */     hashtable.put("Backend start-up failed: {0}.", "Backend başlaması başarısız oldu: {0}");
/*  34 */     hashtable.put("An unexpected result was returned by a query.", "Sorgu beklenmeyen bir sonuç döndürdü.");
/*  35 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Sütun gçstergesi kapsam dışıdır: {0}, sütun sayısı: {1}.");
/*  36 */     hashtable.put("No value specified for parameter {0}.", "{0} parametresi için hiç bir değer belirtilmedi.");
/*  37 */     hashtable.put("Expected command status BEGIN, got {0}.", "BEGIN komut durumunu beklenirken {0} alındı.");
/*  38 */     hashtable.put("Unexpected command status: {0}.", "Beklenmeyen komut durumu: {0}.");
/*  39 */     hashtable.put("An I/O error occured while sending to the backend.", "Backend''e gönderirken bir I/O hatası oluştu.");
/*  40 */     hashtable.put("Unknown Response Type {0}.", "Bilinmeyen yanıt tipi {0}");
/*  41 */     hashtable.put("Ran out of memory retrieving query results.", "Sorgu sonuçları alınırken bellek yetersiz.");
/*  42 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Komut tamamlama etiketinde update sayısı yorumlanamıyor: {0}.");
/*  43 */     hashtable.put("Unable to bind parameter values for statement.", "Komut için parametre değerlei bağlanamadı.");
/*  44 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "Bind mesaj uzunluğu ({0}) fazla uzun. Bu durum InputStream yalnış uzunluk belirtimlerden kaynaklanabilir.");
/*  45 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "İstemcinin client_encoding parametresi {0} değerine değiştirilmiştir. JDBC sürücüsünün doğru çalışması için client_encoding parameteresinin UNICODE olması gerekir.");
/*  46 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "Sunucunun DateStyle parametresi {0} olarak değiştirildi. JDBC sürücüsü doğru işlemesi için DateStyle tanımının ISO işle başlamasını gerekir.");
/*  47 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "İstemcinin client_standard_conforming_strings parametresi {0} olarak raporlandı. JDBC sürücüsü on ya da off olarak bekliyordu.");
/*  48 */     hashtable.put("The driver currently does not support COPY operations.", "Bu sunucu şu aşamada COPY işlemleri desteklememktedir.");
/*  49 */     hashtable.put("This PooledConnection has already been closed.", "Geçerli PooledConnection zaten önceden kapatıldı.");
/*  50 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "PooledConnection kapatıldığı için veya aynı PooledConnection için yeni bir bağlantı açıldığı için geçerli bağlantı otomatik kapatıldı.");
/*  51 */     hashtable.put("Connection has been closed.", "Bağlantı kapatıldı.");
/*  52 */     hashtable.put("Statement has been closed.", "Komut kapatıldı.");
/*  53 */     hashtable.put("DataSource has been closed.", "DataSource kapatıldı.");
/*  54 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Fastpath call {0} - Integer beklenirken hiçbir sonuç getirilmedi.");
/*  55 */     hashtable.put("The fastpath function {0} is unknown.", "{0} fastpath fonksiyonu bilinmemektedir.");
/*  56 */     hashtable.put("Conversion to type {0} failed: {1}.", "{0} veri tipine dönüştürme hatası: {1}.");
/*  57 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Pathın açık mı kapalı olduğunu tespit edilemiyor: {0}.");
/*  58 */     hashtable.put("The array index is out of range: {0}", "Dizi göstergesi kapsam dışıdır: {0}");
/*  59 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Dizin göstergisi kapsam dışıdır: {0}, öğe sayısı: {1}.");
/*  60 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "Large objectlerin temizlenmesi 8.3 ve sonraki sürümlerde kodlanmıştır.");
/*  61 */     hashtable.put("LOB positioning offsets start at 1.", "LOB bağlangıç adresi 1Den başlıyor");
/*  62 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "PostgreSQL LOB göstergeleri sadece {0} referans edebilir");
/*  63 */     hashtable.put("free() was called on this LOB previously", "Bu LOB'da free() daha önce çağırıldı");
/*  64 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "strinftype parametresi için destekleneyen değer: {0}");
/*  65 */     hashtable.put("No results were returned by the query.", "Sorgudan hiç bir sonuç dönmedi.");
/*  66 */     hashtable.put("A result was returned when none was expected.", "Hiçbir sonuç kebklenimezken sonuç getirildi.");
/*  67 */     hashtable.put("Custom type maps are not supported.", "Özel tip eşleştirmeleri desteklenmiyor.");
/*  68 */     hashtable.put("Failed to create object for: {0}.", "{0} için nesne oluşturma hatası.");
/*  69 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "{1} veri tipinden sorumlu {0} sınıfı yüklenemedi");
/*  70 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Transaction ortasında geçerli transactionun read-only özellği değiştirilemez.");
/*  71 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Transaction ortasında geçerli transactionun transaction isolation level özellği değiştirilemez.");
/*  72 */     hashtable.put("Transaction isolation level {0} not supported.", "Transaction isolation level {0} desteklenmiyor.");
/*  73 */     hashtable.put("Finalizing a Connection that was never closed:", "Kapatılmamış bağlantı sonlandırılıyor.");
/*  74 */     hashtable.put("Unable to translate data into the desired encoding.", "Veri, istenilen dil kodlamasına çevrilemiyor.");
/*  75 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Sistem kataloğu olmadığından MaxIndexKeys değerini tespit edilememektedir.");
/*  76 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Sistem kataloglarında name veri tipi bulunamıyor.");
/*  77 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "İşlem, kaydırılabilen ResultSet gerektirir, ancak bu ResultSet FORWARD_ONLYdir.");
/*  78 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Large-object nesnesinden karakter veriyi çözerken beklenmeyen hata.");
/*  79 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Insert kaydı üzerinde relative move method kullanılamaz.");
/*  80 */     hashtable.put("Invalid fetch direction constant: {0}.", "Getirme yönü değişmezi geçersiz: {0}.");
/*  81 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Insert edilmiş kaydın üzerindeyken cancelRowUpdates() çağırılamaz.");
/*  82 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Insert  kaydı üzerinde deleteRow() çağırılamaz.");
/*  83 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Şu an ResultSet başlangcıından önce konumlandı. deleteRow() burada çağırabilirsiniz.");
/*  84 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Şu an ResultSet sonucundan sonra konumlandı. deleteRow() burada çağırabilirsiniz.");
/*  85 */     hashtable.put("There are no rows in this ResultSet.", "Bu ResultSet içinde kayıt bulunamadı.");
/*  86 */     hashtable.put("Not on the insert row.", "Insert kaydı değil.");
/*  87 */     hashtable.put("You must specify at least one column value to insert a row.", "Bir satır eklemek için en az bir sütun değerini belirtmelisiniz.");
/*  88 */     hashtable.put("The JVM claims not to support the encoding: {0}", "JVM, {0} dil kodlamasını desteklememektedir.");
/*  89 */     hashtable.put("Provided InputStream failed.", "Sağlanmış InputStream başarısız.");
/*  90 */     hashtable.put("Provided Reader failed.", "Sağlanmış InputStream başarısız.");
/*  91 */     hashtable.put("Can''t refresh the insert row.", "Inser satırı yenilenemiyor.");
/*  92 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Insert  kaydı üzerinde updateRow() çağırılamaz.");
/*  93 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "ResultSet, sonuçların ilk kaydından önce veya son kaydından sonra olduğu için güncelleme yapılamamaktadır.");
/*  94 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "Eş zamanlama CONCUR_READ_ONLY olan ResultSet''ler değiştirilemez");
/*  95 */     hashtable.put("No primary key found for table {0}.", "{0} tablosunda primary key yok.");
/*  96 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Fetch boyutu sıfır veya daha büyük bir değer olmalıdır.");
/*  97 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Geçersiz karakterler bulunmuştur. Bunun sebebi, verilerde veritabanın desteklediği dil kodlamadaki karakterlerin dışında bir karaktere rastlamasıdır. Bunun en yaygın örneği 8 bitlik veriyi SQL_ASCII veritabanında saklamasıdır.");
/*  98 */     hashtable.put("Bad value for type {0} : {1}", "{0} veri tipi için geçersiz değer : {1}");
/*  99 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Bu ResultSet içinde {0} sütun adı bulunamadı.");
/* 100 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "ResultSet değiştirilemez. Bu sonucu üreten sorgu tek bir tablodan sorgulamalı ve tablonun tüm primary key alanları belirtmelidir. Daha fazla bilgi için bk. JDBC 2.1 API Specification, section 5.6.");
/* 101 */     hashtable.put("This ResultSet is closed.", "ResultSet kapalıdır.");
/* 102 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "ResultSet doğru konumlanmamıştır, next işlemi çağırmanız gerekir.");
/* 103 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "PreparedStatement ile sorgu satırı alan sorgu yöntemleri kullanılamaz.");
/* 104 */     hashtable.put("Multiple ResultSets were returned by the query.", "Sorgu tarafından birden fazla ResultSet getirildi.");
/* 105 */     hashtable.put("A CallableStatement was executed with nothing returned.", "CallableStatement çalıştırma sonucunda veri getirilmedi.");
/* 106 */     hashtable.put("A CallableStatement was executed with an invalid number of parameters", "CallableStatement geçersiz sayıda parametre ile çalıştırıldı.");
/* 107 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "CallableStatement çalıştırıldı, ancak {2} tipi kaydedilmesine rağmen döndürme parametresi {0} ve tipi {1} idi.");
/* 108 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "En büyük getirilecek satır sayısı sıfırdan büyük olmalıdır.");
/* 109 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Sorgu zaman aşımı değer sıfır veya sıfırdan büyük bir sayı olmalıdır.");
/* 110 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "En büyük alan boyutu sıfır ya da sıfırdan büyük bir değer olmalı.");
/* 111 */     hashtable.put("Unknown Types value.", "Geçersiz Types değeri.");
/* 112 */     hashtable.put("Invalid stream length {0}.", "Geçersiz akım uzunluğu {0}.");
/* 113 */     hashtable.put("The JVM claims not to support the {0} encoding.", "JVM, {0} dil kodlamasını desteklememektedir.");
/* 114 */     hashtable.put("Unknown type {0}.", "Bilinmeyen tip {0}.");
/* 115 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "{0} tipi {1} tipine dönüştürülemiyor");
/* 116 */     hashtable.put("Unsupported Types value: {0}", "Geçersiz Types değeri: {0}");
/* 117 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "{0}''nin örneği ile kullanılacak SQL tip bulunamadı. Kullanılacak tip belirtmek için kesin Types değerleri ile setObject() kullanın.");
/* 118 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Bu komut OUT parametresi bildirmemektedir.  Bildirmek için '{' ?= call ... '}' kullanın.");
/* 119 */     hashtable.put("wasNull cannot be call before fetching a result.", "wasNull sonuç çekmeden önce çağırılamaz.");
/* 120 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "{0} adresinde fonksiyon veya yordamda kaçış söz dizimi geçersiz.");
/* 121 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "{0} tipinde parametre tanıtıldı, ancak {1} (sqltype={2}) tipinde geri getirmek için çağrı yapıldı.");
/* 122 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "CallableStatement bildirildi ancak registerOutParameter(1, < bir tip>) tanıtımı yapılmadı.");
/* 123 */     hashtable.put("No function outputs were registered.", "Hiçbir fonksiyon çıktısı kaydedilmedi.");
/* 124 */     hashtable.put("Results cannot be retrieved from a CallableStatement before it is executed.", "CallableStatement çalıştırılmadan sonuçlar ondan alınamaz.");
/* 125 */     hashtable.put("This statement has been closed.", "Bu komut kapatıldı.");
/* 126 */     hashtable.put("Too many update results were returned.", "Çok fazla güncelleme sonucu döndürüldü.");
/* 127 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "Tpilı iş girişi {0} {1} durduruldu.  Nedenini görmek için getNextException fonksiyonu çağırın.");
/* 128 */     hashtable.put("Unexpected error writing large object to database.", "Large object veritabanına yazılırken beklenmeyan hata.");
/* 129 */     hashtable.put("{0} function takes one and only one argument.", "{0} fonksiyonunu yalnız tek bir parametre alabilir.");
/* 130 */     hashtable.put("{0} function takes two and only two arguments.", "{0} fonksiyonunu sadece iki parametre alabilir.");
/* 131 */     hashtable.put("{0} function takes four and only four argument.", "{0} fonksiyonunu yalnız dört parametre alabilir.");
/* 132 */     hashtable.put("{0} function takes two or three arguments.", "{0} fonksiyonu yalnız iki veya üç argüman alabilir.");
/* 133 */     hashtable.put("{0} function doesn''t take any argument.", "{0} fonksiyonu parametre almaz.");
/* 134 */     hashtable.put("{0} function takes three and only three arguments.", "{0} fonksiyonunu sadece üç parametre alabilir.");
/* 135 */     hashtable.put("Interval {0} not yet implemented", "{0} aralığı henüz kodlanmadı.");
/* 136 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Timestamp veri tipinde sonsuz değer bulunmuştur. Buna uygun bir gösterim yoktur.");
/* 137 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "{0} sınıfı org.postgresql.util.PGobject implemente etmiyor.");
/* 138 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "ResultSet tutabilme ayarı geçersiz: {0}.");
/* 139 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Sunucunun 8.0''dan  önceki sürümler savepoint desteklememektedir.");
/* 140 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Auto-commit biçimde savepoint oluşturulamıyor.");
/* 141 */     hashtable.put("Returning autogenerated keys is not supported.", "Otomatik üretilen değerlerin getirilmesi desteklenememktedir.");
/* 142 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "Dizin göstergisi kapsam dışıdır: {0}, öğe sayısı: {1}.");
/* 143 */     hashtable.put("Returning autogenerated keys is only supported for 8.2 and later servers.", "Otomatik üretilen anahtarların döndürülmesi sadece 8.2 ve üzerindeki sürümlerdeki sunucularda desteklenmektedir.");
/* 144 */     hashtable.put("Returning autogenerated keys by column index is not supported.", "Kolonların indexlenmesi ile otomatik olarak oluşturulan anahtarların döndürülmesi desteklenmiyor.");
/* 145 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Bırakıldıktan sonra savepoint referans edilemez.");
/* 146 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Adlandırılmış savepointin id değerine erişilemiyor.");
/* 147 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Adı verilmemiş savepointin id değerine erişilemiyor.");
/* 148 */     hashtable.put("Invalid UUID data.", "Geçersiz UUID verisi.");
/* 149 */     hashtable.put("Unable to find server array type for provided name {0}.", "Belirtilen {0} adı için sunucu array tipi bulunamadı.");
/* 150 */     hashtable.put("ClientInfo property not supported.", "Clientinfo property'si desteklenememktedir.");
/* 151 */     hashtable.put("Unable to decode xml data.", "XML verisinin kodu çözülemedi.");
/* 152 */     hashtable.put("Unknown XML Source class: {0}", "Bilinmeyen XML Kaynak Sınıfı: {0}");
/* 153 */     hashtable.put("Unable to create SAXResult for SQLXML.", "SQLXML için SAXResult yaratılamadı.");
/* 154 */     hashtable.put("Unable to create StAXResult for SQLXML", "SQLXML için StAXResult yaratılamadı");
/* 155 */     hashtable.put("Unknown XML Result class: {0}", "Bilinmeyen XML Sonuç sınıfı: {0}.");
/* 156 */     hashtable.put("This SQLXML object has already been freed.", "Bu SQLXML nesnesi zaten boşaltılmış.");
/* 157 */     hashtable.put("This SQLXML object has not been initialized, so you cannot retrieve data from it.", "Bu SQLXML nesnesi ilklendirilmemiş; o yüzden ondan veri alamazsınız.");
/* 158 */     hashtable.put("Failed to convert binary xml data to encoding: {0}.", "xml verisinin şu dil kodlamasına çevirilmesi başarısız oldu: {0}");
/* 159 */     hashtable.put("Unable to convert DOMResult SQLXML data to a string.", "DOMResult SQLXML verisini diziye dönüştürülemedi.");
/* 160 */     hashtable.put("This SQLXML object has already been initialized, so you cannot manipulate it further.", "Bu SQLXML nesnesi daha önceden ilklendirilmiştir; o yüzden daha fazla müdahale edilemez.");
/* 161 */     hashtable.put("Failed to initialize LargeObject API", "LArgeObject API ilklendirme hatası");
/* 162 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Auto-commit biçimde large object kullanılamaz.");
/* 163 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "SSLSocketFactory {0} ile örneklenmedi.");
/* 164 */     hashtable.put("Conversion of interval failed", "Interval dönüştürmesi başarısız.");
/* 165 */     hashtable.put("Conversion of money failed.", "Money dönüştürmesi başarısız.");
/* 166 */     hashtable.put("Detail: {0}", "Ayrıntı: {0}");
/* 167 */     hashtable.put("Hint: {0}", "İpucu: {0}");
/* 168 */     hashtable.put("Position: {0}", "Position: {0}");
/* 169 */     hashtable.put("Where: {0}", "Where: {0}");
/* 170 */     hashtable.put("Internal Query: {0}", "Internal Query: {0}");
/* 171 */     hashtable.put("Internal Position: {0}", "Internal Position: {0}");
/* 172 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Yer: Dosya: {0}, Yordam: {1}, Satır: {2}");
/* 173 */     hashtable.put("Server SQLState: {0}", "Sunucu SQLState: {0}");
/* 174 */     hashtable.put("Invalid flags", "Geçersiz seçenekler");
/* 175 */     hashtable.put("xid must not be null", "xid null olamaz");
/* 176 */     hashtable.put("Connection is busy with another transaction", "Bağlantı, başka bir transaction tarafından meşgul ediliyor");
/* 177 */     hashtable.put("suspend/resume not implemented", "suspend/resume desteklenmiyor");
/* 178 */     hashtable.put("Transaction interleaving not implemented", "Transaction interleaving desteklenmiyor.");
/* 179 */     hashtable.put("Error disabling autocommit", "autocommit'i devre dışı bırakma sırasında hata");
/* 180 */     hashtable.put("tried to call end without corresponding start call", "start çağırımı olmadan end çağırılmıştır");
/* 181 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Desteklenmiyor: Prepare, transaction başlatran bağlantı tarafından çağırmalıdır");
/* 182 */     hashtable.put("Prepare called before end", "Sondan önce prepare çağırılmış");
/* 183 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Sunucunun 8.1''den  önceki sürümler two-phase commit desteklememektedir.");
/* 184 */     hashtable.put("Error preparing transaction", "Transaction hazırlama hatası");
/* 185 */     hashtable.put("Invalid flag", "Geçersiz seçenek");
/* 186 */     hashtable.put("Error during recover", "Kurtarma sırasında hata");
/* 187 */     hashtable.put("Error rolling back prepared transaction", "Hazırlanmış transaction rollback hatası");
/* 188 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Desteklenmiyor: one-phase commit, işlevinde başlatan ve bitiren bağlantı aynı olmalıdır");
/* 189 */     hashtable.put("commit called before end", "commit, sondan önce çağırıldı");
/* 190 */     hashtable.put("Error during one-phase commit", "One-phase commit sırasında hata");
/* 191 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Desteklenmiyor: 2nd phase commit, atıl bir bağlantıdan başlatılmalıdır");
/* 192 */     hashtable.put("Heuristic commit/rollback not supported", "Heuristic commit/rollback desteklenmiyor");
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_tr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */