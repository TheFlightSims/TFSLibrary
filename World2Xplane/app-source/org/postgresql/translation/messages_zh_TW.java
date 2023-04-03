/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_zh_TW extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL JDBC Driver 8.3\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2008-01-30 02:07-0700\nPO-Revision-Date: 2008-01-21 16:50+0800\nLast-Translator: 郭朝益(ChaoYi, Kuo) <Kuo.ChaoYi@gmail.com>\nLanguage-Team: The PostgreSQL Development Team <Kuo.ChaoYi@gmail.com>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Poedit-Language: Chinese\nX-Poedit-Country: TAIWAN\nX-Poedit-SourceCharset: utf-8\n");
/*   8 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "不明的原因導致驅動程式造成失敗，請回報這個例外。");
/*   9 */     hashtable.put("Connection attempt timed out.", "Connection 嘗試逾時。");
/*  10 */     hashtable.put("Method {0} is not yet implemented.", "這個 {0} 方法尚未被實作。");
/*  11 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "無法以要求的通訊協定 {0} 建立連線。");
/*  12 */     hashtable.put("Zero bytes may not occur in string parameters.", "字串參數不能有 0 個位元組。");
/*  13 */     hashtable.put("Zero bytes may not occur in identifiers.", "在標識識別符中不存在零位元組。");
/*  14 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "無法轉換 {0} 到類型 {1} 的實例");
/*  15 */     hashtable.put("The driver does not support SSL.", "驅動程式不支援 SSL 連線。");
/*  16 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "連線被拒，請檢查主機名稱和埠號，並確定 postmaster 可以接受 TCP/IP 連線。");
/*  17 */     hashtable.put("The connection attempt failed.", "嘗試連線已失敗。");
/*  18 */     hashtable.put("The server does not support SSL.", "伺服器不支援 SSL 連線。");
/*  19 */     hashtable.put("An error occured while setting up the SSL connection.", "進行 SSL 連線時發生錯誤。");
/*  20 */     hashtable.put("Connection rejected: {0}.", "連線已被拒絕：{0}。");
/*  21 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "伺服器要求使用密碼驗證，但是密碼並未提供。");
/*  22 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "不支援 {0} 驗證型別。請核對您已經組態 pg_hba.conf 檔案包含客戶端的IP位址或網路區段，以及驅動程式所支援的驗證架構模式已被支援。");
/*  23 */     hashtable.put("Protocol error.  Session setup failed.", "通訊協定錯誤，Session 初始化失敗。");
/*  24 */     hashtable.put("Backend start-up failed: {0}.", "後端啟動失敗：{0}。");
/*  25 */     hashtable.put("An unexpected result was returned by a query.", "傳回非預期的查詢結果。");
/*  26 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "欄位索引超過許可範圍：{0}，欄位數：{1}。");
/*  27 */     hashtable.put("No value specified for parameter {0}.", "未設定參數值 {0} 的內容。");
/*  28 */     hashtable.put("An I/O error occured while sending to the backend.", "傳送資料至後端時發生 I/O 錯誤。");
/*  29 */     hashtable.put("Unknown Response Type {0}.", "不明的回應類型 {0}。");
/*  30 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "無法解讀命令完成標籤中的更新計數：{0}。");
/*  31 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "這伺服器的 client_encoding 參數被改成 {0}，JDBC 驅動程式請求需要 client_encoding 為 UNICODE 以正確工作。");
/*  32 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "這伺服器的 DateStyle 參數被更改成 {0}，JDBC 驅動程式請求需要 DateStyle 以 ISO 開頭以正確工作。");
/*  33 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "這伺服器的 standard_conforming_strings 參數已回報為 {0}，JDBC 驅動程式已預期開啟或是關閉。");
/*  34 */     hashtable.put("The driver currently does not support COPY operations.", "驅動程式目前不支援 COPY 操作。");
/*  35 */     hashtable.put("This PooledConnection has already been closed.", "這個 PooledConnection 已經被關閉。");
/*  36 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Connection 已自動結束，因為一個新的  PooledConnection 連線被開啟或者或 PooledConnection 已被關閉。");
/*  37 */     hashtable.put("Connection has been closed.", "Connection 已經被關閉。");
/*  38 */     hashtable.put("Statement has been closed.", "Sstatement 已經被關閉。");
/*  39 */     hashtable.put("DataSource has been closed.", "DataSource 已經被關閉。");
/*  40 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Fastpath 呼叫 {0} - 沒有傳回值，且應該傳回一個整數。");
/*  41 */     hashtable.put("The fastpath function {0} is unknown.", "不明的 fastpath 函式 {0}。");
/*  42 */     hashtable.put("Conversion to type {0} failed: {1}.", "轉換型別 {0} 失敗：{1}。");
/*  43 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "無法得知 path 是開啟或關閉：{0}。");
/*  44 */     hashtable.put("The array index is out of range: {0}", "陣列索引超過許可範圍：{0}");
/*  45 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "陣列索引超過許可範圍：{0}，元素數量：{1}。");
/*  46 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "大型物件的截斷(Truncation)僅被實作執行在 8.3 和後來的伺服器。");
/*  47 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "PostgreSQL LOBs 僅能索引到：{0}");
/*  48 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "字串型別參數值未被支持：{0}");
/*  49 */     hashtable.put("No results were returned by the query.", "查詢沒有傳回任何結果。");
/*  50 */     hashtable.put("A result was returned when none was expected.", "傳回預期之外的結果。");
/*  51 */     hashtable.put("Failed to create object for: {0}.", "為 {0} 建立物件失敗。");
/*  52 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "不能在事物交易過程中改變事物交易唯讀屬性。");
/*  53 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "不能在事務交易過程中改變事物交易隔絕等級。");
/*  54 */     hashtable.put("Transaction isolation level {0} not supported.", "不支援交易隔絕等級 {0} 。");
/*  55 */     hashtable.put("Unable to translate data into the desired encoding.", "無法將資料轉成目標編碼。");
/*  56 */     hashtable.put("Unable to find name datatype in the system catalogs.", "在系統 catalog 中找不到名稱資料類型(datatype)。");
/*  57 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "操作要求可捲動的 ResultSet，但此 ResultSet 是 FORWARD_ONLY。");
/*  58 */     hashtable.put("Unexpected error while decoding character data from a large object.", "從大型物件(large object)解碼字元資料時發生錯誤。");
/*  59 */     hashtable.put("Can''t use relative move methods while on the insert row.", "不能在新增的資料列上使用相對位置 move 方法。");
/*  60 */     hashtable.put("Invalid fetch direction constant: {0}.", "無效的 fetch 方向常數：{0}。");
/*  61 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "不能在新增的資料列上呼叫 cancelRowUpdates()。");
/*  62 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "不能在新增的資料上呼叫 deleteRow()。");
/*  63 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "不能在 ResultSet 的第一筆資料之前呼叫 deleteRow()。");
/*  64 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "不能在 ResultSet 的最後一筆資料之後呼叫 deleteRow()。");
/*  65 */     hashtable.put("There are no rows in this ResultSet.", "ResultSet 中找不到資料列。");
/*  66 */     hashtable.put("Not on the insert row.", "不在新增的資料列上。");
/*  67 */     hashtable.put("The JVM claims not to support the encoding: {0}", "JVM 聲明並不支援編碼：{0} 。");
/*  68 */     hashtable.put("Provided InputStream failed.", "提供的 InputStream 已失敗。");
/*  69 */     hashtable.put("Provided Reader failed.", "提供的 Reader 已失敗。");
/*  70 */     hashtable.put("Can''t refresh the insert row.", "無法重讀新增的資料列。");
/*  71 */     hashtable.put("Cannot call updateRow() when on the insert row.", "不能在新增的資料列上呼叫 deleteRow()。");
/*  72 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "無法更新 ResultSet，可能在第一筆資料之前或最未筆資料之後。");
/*  73 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets 與並發同作(Concurrency) CONCUR_READ_ONLY 不能被更新。");
/*  74 */     hashtable.put("No primary key found for table {0}.", "{0} 資料表中未找到主鍵(Primary key)。");
/*  75 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "資料讀取筆數(fetch size)必須大於或等於 0。");
/*  76 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "發現不合法的字元，可能的原因是欲儲存的資料中包含資料庫的字元集不支援的字碼，其中最常見例子的就是將 8 位元資料存入使用 SQL_ASCII 編碼的資料庫中。");
/*  77 */     hashtable.put("Bad value for type {0} : {1}", "不良的型別值 {0} : {1}");
/*  78 */     hashtable.put("The column name {0} was not found in this ResultSet.", "ResultSet 中找不到欄位名稱 {0}。");
/*  79 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "不可更新的 ResultSet。用來產生這個 ResultSet 的 SQL 命令只能操作一個資料表，並且必需選擇所有主鍵欄位，詳細請參閱 JDBC 2.1 API 規格書 5.6 節。");
/*  80 */     hashtable.put("This ResultSet is closed.", "這個 ResultSet 已經被關閉。");
/*  81 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "查詢結果指標位置不正確，您也許需要呼叫 ResultSet 的 next() 方法。");
/*  82 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "在 PreparedStatement 上不能使用獲取查詢字串的查詢方法。");
/*  83 */     hashtable.put("Multiple ResultSets were returned by the query.", "查詢傳回多個 ResultSet。");
/*  84 */     hashtable.put("A CallableStatement was executed with nothing returned.", "一個 CallableStatement 執行函式後沒有傳回值。");
/*  85 */     hashtable.put("A CallableStatement was excecuted with an invalid number of parameters", "一個 CallableStatement 已執行包括一個無效的參數數值");
/*  86 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "一個 CallableStatement 執行函式後輸出的參數型別為 {1} 值為 {0}，但是已註冊的型別是 {2}。");
/*  87 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "最大資料讀取筆數必須大於或等於 0。");
/*  88 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "查詢逾時等候時間必須大於或等於 0。");
/*  89 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "最大欄位容量必須大於或等於 0。");
/*  90 */     hashtable.put("Unknown Types value.", "不明的型別值。");
/*  91 */     hashtable.put("Invalid stream length {0}.", "無效的串流長度 {0}.");
/*  92 */     hashtable.put("The JVM claims not to support the {0} encoding.", "JVM 聲明並不支援 {0} 編碼。");
/*  93 */     hashtable.put("Unknown type {0}.", "不明的型別 {0}");
/*  94 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "不能轉換一個 {0} 實例到型別 {1}");
/*  95 */     hashtable.put("Unsupported Types value: {0}", "未被支持的型別值：{0}");
/*  96 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "這個 statement 未宣告 OUT 參數，使用 '{' ?= call ... '}' 宣告一個。");
/*  97 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "不正確的函式或程序 escape 語法於 {0}。");
/*  98 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "已註冊參數型別 {0}，但是又呼叫了get{1}(sqltype={2})。");
/*  99 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "已經宣告 CallableStatement 函式，但是尚未呼叫 registerOutParameter (1, <some_type>) 。");
/* 100 */     hashtable.put("This statement has been closed.", "這個 statement 已經被關閉。");
/* 101 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "批次處理 {0} {1} 被中止，呼叫 getNextException 以取得原因。");
/* 102 */     hashtable.put("Unexpected error writing large object to database.", "將大型物件(large object)寫入資料庫時發生不明錯誤。");
/* 103 */     hashtable.put("{0} function takes one and only one argument.", "{0} 函式取得一個且僅有一個引數。");
/* 104 */     hashtable.put("{0} function takes two and only two arguments.", "{0} 函式取得二個且僅有二個引數。");
/* 105 */     hashtable.put("{0} function takes four and only four argument.", "{0} 函式取得四個且僅有四個引數。");
/* 106 */     hashtable.put("{0} function takes two or three arguments.", "{0} 函式取得二個或三個引數。");
/* 107 */     hashtable.put("{0} function doesn''t take any argument.", "{0} 函式無法取得任何的引數。");
/* 108 */     hashtable.put("{0} function takes three and only three arguments.", "{0} 函式取得三個且僅有三個引數。");
/* 109 */     hashtable.put("Interval {0} not yet implemented", "隔絕 {0} 尚未被實作。");
/* 110 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "類別 {0} 未實做 org.postgresql.util.PGobject。");
/* 111 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "未知的 ResultSet 可適用的設置：{0}。");
/* 112 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "8.0 版之前的伺服器不支援儲存點(SavePints)。");
/* 113 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "在自動確認事物交易模式無法建立儲存點(Savepoint)。");
/* 114 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "參數索引超出許可範圍：{0}，參數總數：{1}。");
/* 115 */     hashtable.put("Cannot reference a savepoint after it has been released.", "無法參照已經被釋放的儲存點。");
/* 116 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "無法取得已命名儲存點的 id。");
/* 117 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "無法取得未命名儲存點(Savepoint)的名稱。");
/* 118 */     hashtable.put("Failed to initialize LargeObject API", "初始化 LargeObject API 失敗");
/* 119 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "大型物件無法被使用在自動確認事物交易模式。");
/* 120 */     hashtable.put("Conversion of interval failed", "隔絕(Interval)轉換失敗。");
/* 121 */     hashtable.put("Conversion of money failed.", "money 轉換失敗。");
/* 122 */     hashtable.put("Exception: {0}", "例外：{0}");
/* 123 */     hashtable.put("Stack Trace:", "堆疊追縱：");
/* 124 */     hashtable.put("End of Stack Trace", "堆疊追縱結束");
/* 125 */     hashtable.put("Detail: {0}", "詳細：{0}");
/* 126 */     hashtable.put("Hint: {0}", "建議：{0}");
/* 127 */     hashtable.put("Position: {0}", "位置：{0}");
/* 128 */     hashtable.put("Where: {0}", "在位置：{0}");
/* 129 */     hashtable.put("Internal Query: {0}", "內部查詢：{0}");
/* 130 */     hashtable.put("Internal Position: {0}", "內部位置：{0}");
/* 131 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "位置：檔案：{0}，常式：{1}，行：{2}");
/* 132 */     hashtable.put("Server SQLState: {0}", "伺服器 SQLState：{0}");
/* 133 */     hashtable.put("Invalid flags", "無效的旗標");
/* 134 */     hashtable.put("suspend/resume not implemented", "暫停(suspend)/再繼續(resume)尚未被實作。");
/* 135 */     hashtable.put("Transaction interleaving not implemented", "事物交易隔絕(Transaction interleaving)未被實作。");
/* 136 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "8.1 版之前的伺服器不支援二段式提交(Two-Phase Commit)。");
/* 137 */     hashtable.put("Invalid flag", "無效的旗標");
/* 138 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 141 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 144 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 147 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_zh_TW.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */