/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_zh_CN extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL JDBC Driver 8.3\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2008-01-30 02:40-0700\nPO-Revision-Date: 2008-01-31 14:34+0800\nLast-Translator: 郭朝益(ChaoYi, Kuo) <Kuo.ChaoYi@gmail.com>\nLanguage-Team: The PostgreSQL Development Team <Kuo.ChaoYi@gmail.com>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Poedit-Language: Chinese\nX-Poedit-Country: CHINA\nX-Poedit-SourceCharset: utf-8\n");
/*   8 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "不明的原因导致驱动程序造成失败，请回报这个例外。");
/*   9 */     hashtable.put("Connection attempt timed out.", "Connection 尝试逾时。");
/*  10 */     hashtable.put("Method {0} is not yet implemented.", "这个 {0} 方法尚未被实作。");
/*  11 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "无法以要求的通讯协定 {0} 建立连线。");
/*  12 */     hashtable.put("Zero bytes may not occur in string parameters.", "字符参数不能有 0 个位元组。");
/*  13 */     hashtable.put("Zero bytes may not occur in identifiers.", "在标识识别符中不存在零位元组。");
/*  14 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "无法转换 {0} 到类型 {1} 的实例");
/*  15 */     hashtable.put("The driver does not support SSL.", "驱动程序不支援 SSL 连线。");
/*  16 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "连线被拒，请检查主机名称和埠号，并确定 postmaster 可以接受 TCP/IP 连线。");
/*  17 */     hashtable.put("The connection attempt failed.", "尝试连线已失败。");
/*  18 */     hashtable.put("The server does not support SSL.", "服务器不支援 SSL 连线。");
/*  19 */     hashtable.put("An error occured while setting up the SSL connection.", "进行 SSL 连线时发生错误。");
/*  20 */     hashtable.put("Connection rejected: {0}.", "连线已被拒绝：{0}。");
/*  21 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "服务器要求使用密码验证，但是密码并未提供。");
/*  22 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "不支援 {0} 验证类型。请核对您已经组态 pg_hba.conf 文件包含客户端的IP位址或网路区段，以及驱动程序所支援的验证架构模式已被支援。");
/*  23 */     hashtable.put("Protocol error.  Session setup failed.", "通讯协定错误，Session 初始化失败。");
/*  24 */     hashtable.put("Backend start-up failed: {0}.", "后端启动失败：{0}。");
/*  25 */     hashtable.put("An unexpected result was returned by a query.", "传回非预期的查询结果。");
/*  26 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "栏位索引超过许可范围：{0}，栏位数：{1}。");
/*  27 */     hashtable.put("No value specified for parameter {0}.", "未设定参数值 {0} 的内容。");
/*  28 */     hashtable.put("An I/O error occured while sending to the backend.", "传送数据至后端时发生 I/O 错误。");
/*  29 */     hashtable.put("Unknown Response Type {0}.", "不明的回应类型 {0}。");
/*  30 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "无法解读命令完成标签中的更新计数：{0}。");
/*  31 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "这服务器的 client_encoding 参数被改成 {0}，JDBC 驱动程序请求需要 client_encoding 为 UNICODE 以正确工作。");
/*  32 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "这服务器的 DateStyle 参数被更改成 {0}，JDBC 驱动程序请求需要 DateStyle 以 ISO 开头以正确工作。");
/*  33 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "这服务器的 standard_conforming_strings 参数已回报为 {0}，JDBC 驱动程序已预期开启或是关闭。");
/*  34 */     hashtable.put("The driver currently does not support COPY operations.", "驱动程序目前不支援 COPY 操作。");
/*  35 */     hashtable.put("This PooledConnection has already been closed.", "这个 PooledConnection 已经被关闭。");
/*  36 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Connection 已自动结束，因为一个新的  PooledConnection 连线被开启或者或 PooledConnection 已被关闭。");
/*  37 */     hashtable.put("Connection has been closed.", "Connection 已经被关闭。");
/*  38 */     hashtable.put("Statement has been closed.", "Sstatement 已经被关闭。");
/*  39 */     hashtable.put("DataSource has been closed.", "DataSource 已经被关闭。");
/*  40 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Fastpath 呼叫 {0} - 没有传回值，且应该传回一个整数。");
/*  41 */     hashtable.put("The fastpath function {0} is unknown.", "不明的 fastpath 函式 {0}。");
/*  42 */     hashtable.put("Conversion to type {0} failed: {1}.", "转换类型 {0} 失败：{1}。");
/*  43 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "无法得知 path 是开启或关闭：{0}。");
/*  44 */     hashtable.put("The array index is out of range: {0}", "阵列索引超过许可范围：{0}");
/*  45 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "阵列索引超过许可范围：{0}，元素数量：{1}。");
/*  46 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "大型对象的截断(Truncation)仅被实作执行在 8.3 和后来的服务器。");
/*  47 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "PostgreSQL LOBs 仅能索引到：{0}");
/*  48 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "字符类型参数值未被支持：{0}");
/*  49 */     hashtable.put("No results were returned by the query.", "查询没有传回任何结果。");
/*  50 */     hashtable.put("A result was returned when none was expected.", "传回预期之外的结果。");
/*  51 */     hashtable.put("Failed to create object for: {0}.", "为 {0} 建立对象失败。");
/*  52 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "不能在事物交易过程中改变事物交易唯读属性。");
/*  53 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "不能在事务交易过程中改变事物交易隔绝等级。");
/*  54 */     hashtable.put("Transaction isolation level {0} not supported.", "不支援交易隔绝等级 {0} 。");
/*  55 */     hashtable.put("Unable to translate data into the desired encoding.", "无法将数据转成目标编码。");
/*  56 */     hashtable.put("Unable to find name datatype in the system catalogs.", "在系统 catalog 中找不到名称数据类型(datatype)。");
/*  57 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "操作要求可卷动的 ResultSet，但此 ResultSet 是 FORWARD_ONLY。");
/*  58 */     hashtable.put("Unexpected error while decoding character data from a large object.", "从大型对象(large object)解码字元数据时发生错误。");
/*  59 */     hashtable.put("Can''t use relative move methods while on the insert row.", "不能在新增的数据列上使用相对位置 move 方法。");
/*  60 */     hashtable.put("Invalid fetch direction constant: {0}.", "无效的 fetch 方向常数：{0}。");
/*  61 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "不能在新增的数据列上呼叫 cancelRowUpdates()。");
/*  62 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "不能在新增的数据上呼叫 deleteRow()。");
/*  63 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "不能在 ResultSet 的第一笔数据之前呼叫 deleteRow()。");
/*  64 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "不能在 ResultSet 的最后一笔数据之后呼叫 deleteRow()。");
/*  65 */     hashtable.put("There are no rows in this ResultSet.", "ResultSet 中找不到数据列。");
/*  66 */     hashtable.put("Not on the insert row.", "不在新增的数据列上。");
/*  67 */     hashtable.put("The JVM claims not to support the encoding: {0}", "JVM 声明并不支援编码：{0} 。");
/*  68 */     hashtable.put("Provided InputStream failed.", "提供的 InputStream 已失败。");
/*  69 */     hashtable.put("Provided Reader failed.", "提供的 Reader 已失败。");
/*  70 */     hashtable.put("Can''t refresh the insert row.", "无法重读新增的数据列。");
/*  71 */     hashtable.put("Cannot call updateRow() when on the insert row.", "不能在新增的数据列上呼叫 deleteRow()。");
/*  72 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "无法更新 ResultSet，可能在第一笔数据之前或最未笔数据之后。");
/*  73 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets 与并发同作(Concurrency) CONCUR_READ_ONLY 不能被更新。");
/*  74 */     hashtable.put("No primary key found for table {0}.", "{0} 数据表中未找到主键(Primary key)。");
/*  75 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "数据读取笔数(fetch size)必须大于或等于 0。");
/*  76 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "发现不合法的字元，可能的原因是欲储存的数据中包含数据库的字元集不支援的字码，其中最常见例子的就是将 8 位元数据存入使用 SQL_ASCII 编码的数据库中。");
/*  77 */     hashtable.put("Bad value for type {0} : {1}", "不良的类型值 {0} : {1}");
/*  78 */     hashtable.put("The column name {0} was not found in this ResultSet.", "ResultSet 中找不到栏位名称 {0}。");
/*  79 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "不可更新的 ResultSet。用来产生这个 ResultSet 的 SQL 命令只能操作一个数据表，并且必需选择所有主键栏位，详细请参阅 JDBC 2.1 API 规格书 5.6 节。");
/*  80 */     hashtable.put("This ResultSet is closed.", "这个 ResultSet 已经被关闭。");
/*  81 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "查询结果指标位置不正确，您也许需要呼叫 ResultSet 的 next() 方法。");
/*  82 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "在 PreparedStatement 上不能使用获取查询字符的查询方法。");
/*  83 */     hashtable.put("Multiple ResultSets were returned by the query.", "查询传回多个 ResultSet。");
/*  84 */     hashtable.put("A CallableStatement was executed with nothing returned.", "一个 CallableStatement 执行函式后没有传回值。");
/*  85 */     hashtable.put("A CallableStatement was excecuted with an invalid number of parameters", "一个 CallableStatement 已执行包括一个无效的参数数值");
/*  86 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "一个 CallableStatement 执行函式后输出的参数类型为 {1} 值为 {0}，但是已注册的类型是 {2}。");
/*  87 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "最大数据读取笔数必须大于或等于 0。");
/*  88 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "查询逾时等候时间必须大于或等于 0。");
/*  89 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "最大栏位容量必须大于或等于 0。");
/*  90 */     hashtable.put("Unknown Types value.", "不明的类型值。");
/*  91 */     hashtable.put("Invalid stream length {0}.", "无效的串流长度 {0}.");
/*  92 */     hashtable.put("The JVM claims not to support the {0} encoding.", "JVM 声明并不支援 {0} 编码。");
/*  93 */     hashtable.put("Unknown type {0}.", "不明的类型 {0}");
/*  94 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "不能转换一个 {0} 实例到类型 {1}");
/*  95 */     hashtable.put("Unsupported Types value: {0}", "未被支持的类型值：{0}");
/*  96 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "这个 statement 未宣告 OUT 参数，使用 '{' ?= call ... '}' 宣告一个。");
/*  97 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "不正确的函式或程序 escape 语法于 {0}。");
/*  98 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "已注册参数类型 {0}，但是又呼叫了get{1}(sqltype={2})。");
/*  99 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "已经宣告 CallableStatement 函式，但是尚未呼叫 registerOutParameter (1, <some_type>) 。");
/* 100 */     hashtable.put("This statement has been closed.", "这个 statement 已经被关闭。");
/* 101 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "批次处理 {0} {1} 被中止，呼叫 getNextException 以取得原因。");
/* 102 */     hashtable.put("Unexpected error writing large object to database.", "将大型对象(large object)写入数据库时发生不明错误。");
/* 103 */     hashtable.put("{0} function takes one and only one argument.", "{0} 函式取得一个且仅有一个引数。");
/* 104 */     hashtable.put("{0} function takes two and only two arguments.", "{0} 函式取得二个且仅有二个引数。");
/* 105 */     hashtable.put("{0} function takes four and only four argument.", "{0} 函式取得四个且仅有四个引数。");
/* 106 */     hashtable.put("{0} function takes two or three arguments.", "{0} 函式取得二个或三个引数。");
/* 107 */     hashtable.put("{0} function doesn''t take any argument.", "{0} 函式无法取得任何的引数。");
/* 108 */     hashtable.put("{0} function takes three and only three arguments.", "{0} 函式取得三个且仅有三个引数。");
/* 109 */     hashtable.put("Interval {0} not yet implemented", "隔绝 {0} 尚未被实作。");
/* 110 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "类别 {0} 未实做 org.postgresql.util.PGobject。");
/* 111 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "未知的 ResultSet 可适用的设置：{0}。");
/* 112 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "8.0 版之前的服务器不支援储存点(SavePints)。");
/* 113 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "在自动确认事物交易模式无法建立储存点(Savepoint)。");
/* 114 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "参数索引超出许可范围：{0}，参数总数：{1}。");
/* 115 */     hashtable.put("Cannot reference a savepoint after it has been released.", "无法参照已经被释放的储存点。");
/* 116 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "无法取得已命名储存点的 id。");
/* 117 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "无法取得未命名储存点(Savepoint)的名称。");
/* 118 */     hashtable.put("Failed to initialize LargeObject API", "初始化 LargeObject API 失败");
/* 119 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "大型对象无法被使用在自动确认事物交易模式。");
/* 120 */     hashtable.put("Conversion of interval failed", "隔绝(Interval)转换失败。");
/* 121 */     hashtable.put("Conversion of money failed.", "money 转换失败。");
/* 122 */     hashtable.put("Exception: {0}", "例外：{0}");
/* 123 */     hashtable.put("Stack Trace:", "堆叠追纵：");
/* 124 */     hashtable.put("End of Stack Trace", "堆叠追纵结束");
/* 125 */     hashtable.put("Detail: {0}", "详细：{0}");
/* 126 */     hashtable.put("Hint: {0}", "建议：{0}");
/* 127 */     hashtable.put("Position: {0}", "位置：{0}");
/* 128 */     hashtable.put("Where: {0}", "在位置：{0}");
/* 129 */     hashtable.put("Internal Query: {0}", "内部查询：{0}");
/* 130 */     hashtable.put("Internal Position: {0}", "内部位置：{0}");
/* 131 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "位置：文件：{0}，常式：{1}，行：{2}");
/* 132 */     hashtable.put("Server SQLState: {0}", "服务器 SQLState：{0}");
/* 133 */     hashtable.put("Invalid flags", "无效的旗标");
/* 134 */     hashtable.put("suspend/resume not implemented", "暂停(suspend)/再继续(resume)尚未被实作。");
/* 135 */     hashtable.put("Transaction interleaving not implemented", "事物交易隔绝(Transaction interleaving)未被实作。");
/* 136 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "8.1 版之前的服务器不支援二段式提交(Two-Phase Commit)。");
/* 137 */     hashtable.put("Invalid flag", "无效的旗标");
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_zh_CN.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */