/*     */ package org.postgresql.translation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class messages_pt_BR extends ResourceBundle {
/*     */   private static final Hashtable table;
/*     */   
/*     */   static {
/*   6 */     Hashtable hashtable = new Hashtable();
/*   7 */     hashtable.put("", "Project-Id-Version: PostgreSQL 8.4\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2009-05-30 20:13-0300\nPO-Revision-Date: 2004-10-31 20:48-0300\nLast-Translator: Euler Taveira de Oliveira <euler@timbira.com>\nLanguage-Team: Brazilian Portuguese <pgbr-dev@listas.postgresql.org.br>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\n");
/*   8 */     hashtable.put("Error loading default settings from driverconfig.properties", "Erro ao carregar configurações padrão do driverconfig.properties");
/*   9 */     hashtable.put("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to.", "Sua política de segurança impediu que a conexão pudesse ser estabelecida. Você provavelmente precisa conceder permissão em java.net.SocketPermission para a máquina e a porta do servidor de banco de dados que você deseja se conectar.");
/*  10 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Alguma coisa não usual ocorreu para causar a falha do driver. Por favor reporte esta exceção.");
/*  11 */     hashtable.put("Connection attempt timed out.", "Tentativa de conexão falhou.");
/*  12 */     hashtable.put("Interrupted while attempting to connect.", "Interrompido ao tentar se conectar.");
/*  13 */     hashtable.put("Method {0} is not yet implemented.", "Método {0} ainda não foi implementado.");
/*  14 */     hashtable.put("A connection could not be made using the requested protocol {0}.", "A conexão não pode ser feita usando protocolo informado {0}.");
/*  15 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Fim de entrada prematuro, eram esperados {0} bytes, mas somente {1} foram lidos.");
/*  16 */     hashtable.put("Expected an EOF from server, got: {0}", "Esperado um EOF do servidor, recebido: {0}");
/*  17 */     hashtable.put("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", "Sequência UTF-8 ilegal: byte {0} da sequência de bytes {1} não é 10xxxxxx: {2}");
/*  18 */     hashtable.put("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", "Sequência UTF-8 ilegal: {0} bytes utilizados para codificar um valor de {1} bytes: {2}");
/*  19 */     hashtable.put("Illegal UTF-8 sequence: initial byte is {0}: {1}", "Sequência UTF-8 ilegal: byte inicial é {0}: {1}");
/*  20 */     hashtable.put("Illegal UTF-8 sequence: final value is out of range: {0}", "Sequência UTF-8 ilegal: valor final está fora do intervalo: {0}");
/*  21 */     hashtable.put("Illegal UTF-8 sequence: final value is a surrogate value: {0}", "Sequência UTF-8 ilegal: valor final é um valor suplementar: {0}");
/*  22 */     hashtable.put("Zero bytes may not occur in string parameters.", "Zero bytes não podem ocorrer em parâmetros de cadeia de caracteres.");
/*  23 */     hashtable.put("Zero bytes may not occur in identifiers.", "Zero bytes não podem ocorrer em identificadores.");
/*  24 */     hashtable.put("Cannot convert an instance of {0} to type {1}", "Não pode converter uma instância de {0} para tipo {1}");
/*  25 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Conexão negada. Verifique se o nome da máquina e a porta estão corretos e se o postmaster está aceitando conexões TCP/IP.");
/*  26 */     hashtable.put("The connection attempt failed.", "A tentativa de conexão falhou.");
/*  27 */     hashtable.put("The server does not support SSL.", "O servidor não suporta SSL.");
/*  28 */     hashtable.put("An error occured while setting up the SSL connection.", "Um erro ocorreu ao estabelecer uma conexão SSL.");
/*  29 */     hashtable.put("Connection rejected: {0}.", "Conexão negada: {0}.");
/*  30 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "O servidor pediu autenticação baseada em senha, mas nenhuma senha foi fornecida.");
/*  31 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", "O tipo de autenticação {0} não é suportado. Verifique se você configurou o arquivo pg_hba.conf incluindo a subrede ou endereço IP do cliente, e se está utilizando o esquema de autenticação suportado pelo driver.");
/*  32 */     hashtable.put("Protocol error.  Session setup failed.", "Erro de Protocolo. Configuração da sessão falhou.");
/*  33 */     hashtable.put("Backend start-up failed: {0}.", "Inicialização do processo servidor falhou: {0}.");
/*  34 */     hashtable.put("An unexpected result was returned by a query.", "Um resultado inesperado foi retornado pela consulta.");
/*  35 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "O índice da coluna está fora do intervalo: {0}, número de colunas: {1}.");
/*  36 */     hashtable.put("No value specified for parameter {0}.", "Nenhum valor especificado para parâmetro {0}.");
/*  37 */     hashtable.put("Expected command status BEGIN, got {0}.", "Status do comando BEGIN esperado, recebeu {0}.");
/*  38 */     hashtable.put("Unexpected command status: {0}.", "Status do comando inesperado: {0}.");
/*  39 */     hashtable.put("An I/O error occured while sending to the backend.", "Um erro de E/S ocorreu ao enviar para o processo servidor.");
/*  40 */     hashtable.put("Unknown Response Type {0}.", "Tipo de Resposta Desconhecido {0}.");
/*  41 */     hashtable.put("Ran out of memory retrieving query results.", "Memória insuficiente ao recuperar resultados da consulta.");
/*  42 */     hashtable.put("Unable to interpret the update count in command completion tag: {0}.", "Não foi possível interpretar o contador de atualização na marcação de comando completo: {0}.");
/*  43 */     hashtable.put("Unable to bind parameter values for statement.", "Não foi possível ligar valores de parâmetro ao comando.");
/*  44 */     hashtable.put("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", "Tamanho de mensagem de ligação {0} é muito longo. Isso pode ser causado por especificações de tamanho incorretas ou muito grandes nos parâmetros do InputStream.");
/*  45 */     hashtable.put("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UNICODE for correct operation.", "O parâmetro do servidor client_encoding foi alterado para {0}. O driver JDBC requer que o client_encoding seja UNICODE para operação normal.");
/*  46 */     hashtable.put("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", "O parâmetro do servidor DateStyle foi alterado para {0}. O driver JDBC requer que o DateStyle começe com ISO para operação normal.");
/*  47 */     hashtable.put("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", "O parâmetro do servidor standard_conforming_strings foi definido como {0}. O driver JDBC espera que seja on ou off.");
/*  48 */     hashtable.put("The driver currently does not support COPY operations.", "O driver atualmente não suporta operações COPY.");
/*  49 */     hashtable.put("This PooledConnection has already been closed.", "Este PooledConnection já foi fechado.");
/*  50 */     hashtable.put("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.", "Conexão foi fechada automaticamente porque uma nova conexão foi aberta pelo mesmo PooledConnection ou o PooledConnection foi fechado.");
/*  51 */     hashtable.put("Connection has been closed.", "Conexão foi fechada.");
/*  52 */     hashtable.put("Statement has been closed.", "Comando foi fechado.");
/*  53 */     hashtable.put("DataSource has been closed.", "DataSource foi fechado.");
/*  54 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Chamada ao Fastpath {0} - Nenhum resultado foi retornado e nós esperávamos um inteiro.");
/*  55 */     hashtable.put("The fastpath function {0} is unknown.", "A função do fastpath {0} é desconhecida.");
/*  56 */     hashtable.put("Conversion to type {0} failed: {1}.", "Conversão para tipo {0} falhou: {1}.");
/*  57 */     hashtable.put("Cannot tell if path is open or closed: {0}.", "Não pode dizer se caminho está aberto ou fechado: {0}.");
/*  58 */     hashtable.put("GSS Authentication failed", "Autenticação GSS falhou");
/*  59 */     hashtable.put("The array index is out of range: {0}", "O índice da matriz está fora do intervalo: {0}");
/*  60 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "O índice da matriz está fora do intervalo: {0}, número de elementos: {1}.");
/*  61 */     hashtable.put("Truncation of large objects is only implemented in 8.3 and later servers.", "Truncar objetos grandes só é implementado por servidores 8.3 ou superiores.");
/*  62 */     hashtable.put("LOB positioning offsets start at 1.", "Deslocamentos da posição de LOB começam em 1.");
/*  63 */     hashtable.put("PostgreSQL LOBs can only index to: {0}", "LOBs do PostgreSQL só podem indexar até: {0}");
/*  64 */     hashtable.put("free() was called on this LOB previously", "free() já foi chamado neste LOB");
/*  65 */     hashtable.put("Unsupported value for stringtype parameter: {0}", "Valor do parâmetro stringtype não é suportado: {0}");
/*  66 */     hashtable.put("No results were returned by the query.", "Nenhum resultado foi retornado pela consulta.");
/*  67 */     hashtable.put("A result was returned when none was expected.", "Um resultado foi retornado quando nenhum era esperado.");
/*  68 */     hashtable.put("Custom type maps are not supported.", "Mapeamento de tipos personalizados não são suportados.");
/*  69 */     hashtable.put("Failed to create object for: {0}.", "Falhou ao criar objeto para: {0}.");
/*  70 */     hashtable.put("Unable to load the class {0} responsible for the datatype {1}", "Não foi possível carregar a classe {0} responsável pelo tipo de dado {1}");
/*  71 */     hashtable.put("Cannot change transaction read-only property in the middle of a transaction.", "Não pode mudar propriedade somente-leitura da transação no meio de uma transação.");
/*  72 */     hashtable.put("Cannot change transaction isolation level in the middle of a transaction.", "Não pode mudar nível de isolamento da transação no meio de uma transação.");
/*  73 */     hashtable.put("Transaction isolation level {0} not supported.", "Nível de isolamento da transação {0} não é suportado.");
/*  74 */     hashtable.put("Finalizing a Connection that was never closed:", "Fechando uma Conexão que não foi fechada:");
/*  75 */     hashtable.put("Unable to translate data into the desired encoding.", "Não foi possível traduzir dado para codificação desejada.");
/*  76 */     hashtable.put("Unable to determine a value for MaxIndexKeys due to missing system catalog data.", "Não foi possível determinar um valor para MaxIndexKeys por causa de falta de dados no catálogo do sistema.");
/*  77 */     hashtable.put("Unable to find name datatype in the system catalogs.", "Não foi possível encontrar tipo de dado name nos catálogos do sistema.");
/*  78 */     hashtable.put("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY.", "Operação requer um ResultSet rolável, mas este ResultSet é FORWARD_ONLY (somente para frente).");
/*  79 */     hashtable.put("Unexpected error while decoding character data from a large object.", "Erro inesperado ao decodificar caracter de um objeto grande.");
/*  80 */     hashtable.put("Can''t use relative move methods while on the insert row.", "Não pode utilizar métodos de movimentação relativos enquanto estiver inserindo registro.");
/*  81 */     hashtable.put("Invalid fetch direction constant: {0}.", "Constante de direção da busca é inválida: {0}.");
/*  82 */     hashtable.put("Cannot call cancelRowUpdates() when on the insert row.", "Não pode chamar cancelRowUpdates() quando estiver inserindo registro.");
/*  83 */     hashtable.put("Cannot call deleteRow() when on the insert row.", "Não pode chamar deleteRow() quando estiver inserindo registro.");
/*  84 */     hashtable.put("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here.", "Posicionado antes do início do ResultSet.  Você não pode chamar deleteRow() aqui.");
/*  85 */     hashtable.put("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here.", "Posicionado depois do fim do ResultSet.  Você não pode chamar deleteRow() aqui.");
/*  86 */     hashtable.put("There are no rows in this ResultSet.", "Não há nenhum registro neste ResultSet.");
/*  87 */     hashtable.put("Not on the insert row.", "Não está inserindo um registro.");
/*  88 */     hashtable.put("You must specify at least one column value to insert a row.", "Você deve especificar pelo menos uma coluna para inserir um registro.");
/*  89 */     hashtable.put("The JVM claims not to support the encoding: {0}", "A JVM reclamou que não suporta a codificação: {0}");
/*  90 */     hashtable.put("Provided InputStream failed.", "InputStream fornecido falhou.");
/*  91 */     hashtable.put("Provided Reader failed.", "Reader fornecido falhou.");
/*  92 */     hashtable.put("Can''t refresh the insert row.", "Não pode renovar um registro inserido.");
/*  93 */     hashtable.put("Cannot call updateRow() when on the insert row.", "Não pode chamar updateRow() quando estiver inserindo registro.");
/*  94 */     hashtable.put("Cannot update the ResultSet because it is either before the start or after the end of the results.", "Não pode atualizar o ResultSet porque ele está antes do início ou depois do fim dos resultados.");
/*  95 */     hashtable.put("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated.", "ResultSets com CONCUR_READ_ONLY concorrentes não podem ser atualizados.");
/*  96 */     hashtable.put("No primary key found for table {0}.", "Nenhuma chave primária foi encontrada para tabela {0}.");
/*  97 */     hashtable.put("Fetch size must be a value greater to or equal to 0.", "Tamanho da busca deve ser um valor maior ou igual a 0.");
/*  98 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Caracter inválido foi encontrado. Isso é mais comumente causado por dado armazenado que contém caracteres que são inválidos para a codificação que foi criado o banco de dados. O exemplo mais comum disso é armazenar dados de 8 bits em um banco de dados SQL_ASCII.");
/*  99 */     hashtable.put("Bad value for type {0} : {1}", "Valor inválido para tipo {0} : {1}");
/* 100 */     hashtable.put("The column name {0} was not found in this ResultSet.", "A nome da coluna {0} não foi encontrado neste ResultSet.");
/* 101 */     hashtable.put("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details.", "ResultSet não é atualizável. A consulta que gerou esse conjunto de resultados deve selecionar somente uma tabela, e deve selecionar todas as chaves primárias daquela tabela. Veja a especificação na API do JDBC 2.1, seção 5.6 para obter mais detalhes.");
/* 102 */     hashtable.put("This ResultSet is closed.", "Este ResultSet está fechado.");
/* 103 */     hashtable.put("ResultSet not positioned properly, perhaps you need to call next.", "ResultSet não está posicionado corretamente, talvez você precise chamar next.");
/* 104 */     hashtable.put("Can''t use query methods that take a query string on a PreparedStatement.", "Não pode utilizar métodos de consulta que pegam uma consulta de um comando preparado.");
/* 105 */     hashtable.put("Multiple ResultSets were returned by the query.", "ResultSets múltiplos foram retornados pela consulta.");
/* 106 */     hashtable.put("A CallableStatement was executed with nothing returned.", "Uma função foi executada e nada foi retornado.");
/* 107 */     hashtable.put("A CallableStatement was executed with an invalid number of parameters", "Uma função foi executada com um número inválido de parâmetros");
/* 108 */     hashtable.put("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", "Uma função foi executada e o parâmetro de retorno {0} era do tipo {1} contudo tipo {2} foi registrado.");
/* 109 */     hashtable.put("Maximum number of rows must be a value grater than or equal to 0.", "Número máximo de registros deve ser um valor maior ou igual a 0.");
/* 110 */     hashtable.put("Query timeout must be a value greater than or equals to 0.", "Tempo de espera da consulta deve ser um valor maior ou igual a 0.");
/* 111 */     hashtable.put("The maximum field size must be a value greater than or equal to 0.", "O tamanho máximo de um campo deve ser um valor maior ou igual a 0.");
/* 112 */     hashtable.put("Unknown Types value.", "Valor de Types desconhecido.");
/* 113 */     hashtable.put("Invalid stream length {0}.", "Tamanho de dado {0} é inválido.");
/* 114 */     hashtable.put("The JVM claims not to support the {0} encoding.", "A JVM reclamou que não suporta a codificação {0}.");
/* 115 */     hashtable.put("Unknown type {0}.", "Tipo desconhecido {0}.");
/* 116 */     hashtable.put("Cannot cast an instance of {0} to type {1}", "Não pode converter uma instância de {0} para tipo {1}");
/* 117 */     hashtable.put("Unsupported Types value: {0}", "Valor de Types não é suportado: {0}");
/* 118 */     hashtable.put("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", "Não pode inferir um tipo SQL a ser usado para uma instância de {0}. Use setObject() com um valor de Types explícito para especificar o tipo a ser usado.");
/* 119 */     hashtable.put("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one.", "Este comando não declara um parâmetro de saída. Utilize '{' ?= chamada ... '}' para declarar um)");
/* 120 */     hashtable.put("wasNull cannot be call before fetching a result.", "wasNull não pode ser chamado antes de obter um resultado.");
/* 121 */     hashtable.put("Malformed function or procedure escape syntax at offset {0}.", "Sintaxe de escape mal formada da função ou do procedimento no deslocamento {0}.");
/* 122 */     hashtable.put("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", "Parâmetro do tipo {0} foi registrado, mas uma chamada a get{1} (tiposql={2}) foi feita.");
/* 123 */     hashtable.put("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made.", "Uma função foi declarada mas nenhuma chamada a registerOutParameter (1, <algum_tipo>) foi feita.");
/* 124 */     hashtable.put("No function outputs were registered.", "Nenhum saída de função foi registrada.");
/* 125 */     hashtable.put("Results cannot be retrieved from a CallableStatement before it is executed.", "Resultados não podem ser recuperados de uma função antes dela ser executada.");
/* 126 */     hashtable.put("This statement has been closed.", "Este comando foi fechado.");
/* 127 */     hashtable.put("Too many update results were returned.", "Muitos resultados de atualização foram retornados.");
/* 128 */     hashtable.put("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", "Entrada em lote {0} {1} foi abortada. Chame getNextException para ver a causa.");
/* 129 */     hashtable.put("Unexpected error writing large object to database.", "Erro inesperado ao escrever objeto grande no banco de dados.");
/* 130 */     hashtable.put("{0} function takes one and only one argument.", "função {0} recebe somente um argumento.");
/* 131 */     hashtable.put("{0} function takes two and only two arguments.", "função {0} recebe somente dois argumentos.");
/* 132 */     hashtable.put("{0} function takes four and only four argument.", "função {0} recebe somente quatro argumentos.");
/* 133 */     hashtable.put("{0} function takes two or three arguments.", "função {0} recebe dois ou três argumentos.");
/* 134 */     hashtable.put("{0} function doesn''t take any argument.", "função {0} não recebe nenhum argumento.");
/* 135 */     hashtable.put("{0} function takes three and only three arguments.", "função {0} recebe três e somente três argumentos.");
/* 136 */     hashtable.put("Interval {0} not yet implemented", "Intervalo {0} ainda não foi implementado");
/* 137 */     hashtable.put("Infinite value found for timestamp/date. This cannot be represented as time.", "Valor infinito encontrado em timestamp/date. Isto não pode ser representado como tempo.");
/* 138 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "A classe {0} não implementa org.postgresql.util.PGobject.");
/* 139 */     hashtable.put("Unknown ResultSet holdability setting: {0}.", "Definição de durabilidade do ResultSet desconhecida: {0}.");
/* 140 */     hashtable.put("Server versions prior to 8.0 do not support savepoints.", "Versões do servidor anteriores a 8.0 não suportam savepoints.");
/* 141 */     hashtable.put("Cannot establish a savepoint in auto-commit mode.", "Não pode estabelecer um savepoint no modo de efetivação automática (auto-commit).");
/* 142 */     hashtable.put("Returning autogenerated keys is not supported.", "Retorno de chaves geradas automaticamente não é suportado.");
/* 143 */     hashtable.put("The parameter index is out of range: {0}, number of parameters: {1}.", "O índice de parâmetro está fora do intervalo: {0}, número de parâmetros: {1}.");
/* 144 */     hashtable.put("Returning autogenerated keys is only supported for 8.2 and later servers.", "Retorno de chaves geradas automaticamente só é suportado por servidores 8.2 ou mais recentes.");
/* 145 */     hashtable.put("Returning autogenerated keys by column index is not supported.", "Retorno de chaves geradas automaticamente por índice de coluna não é suportado.");
/* 146 */     hashtable.put("Cannot reference a savepoint after it has been released.", "Não pode referenciar um savepoint após ele ser descartado.");
/* 147 */     hashtable.put("Cannot retrieve the id of a named savepoint.", "Não pode recuperar o id de um savepoint com nome.");
/* 148 */     hashtable.put("Cannot retrieve the name of an unnamed savepoint.", "Não pode recuperar o nome de um savepoint sem nome.");
/* 149 */     hashtable.put("Invalid UUID data.", "dado UUID é inválido.");
/* 150 */     hashtable.put("Unable to find server array type for provided name {0}.", "Não foi possível encontrar tipo matriz para nome fornecido {0}.");
/* 151 */     hashtable.put("ClientInfo property not supported.", "propriedade ClientInfo não é suportada.");
/* 152 */     hashtable.put("Unable to decode xml data.", "Não foi possível decodificar dado xml.");
/* 153 */     hashtable.put("Unknown XML Source class: {0}", "Classe XML Source desconhecida: {0}");
/* 154 */     hashtable.put("Unable to create SAXResult for SQLXML.", "Não foi possível criar SAXResult para SQLXML.");
/* 155 */     hashtable.put("Unable to create StAXResult for SQLXML", "Não foi possível criar StAXResult para SQLXML");
/* 156 */     hashtable.put("Unknown XML Result class: {0}", "Classe XML Result desconhecida: {0}");
/* 157 */     hashtable.put("This SQLXML object has already been freed.", "Este objeto SQLXML já foi liberado.");
/* 158 */     hashtable.put("This SQLXML object has not been initialized, so you cannot retrieve data from it.", "Este objeto SQLXML não foi inicializado, então você não pode recuperar dados dele.");
/* 159 */     hashtable.put("Failed to convert binary xml data to encoding: {0}.", "Falhou ao converter dados xml binários para codificação: {0}.");
/* 160 */     hashtable.put("Unable to convert DOMResult SQLXML data to a string.", "Não foi possível converter dado SQLXML do DOMResult para uma cadeia de caracteres.");
/* 161 */     hashtable.put("This SQLXML object has already been initialized, so you cannot manipulate it further.", "Este objeto SQLXML já foi inicializado, então você não pode manipulá-lo depois.");
/* 162 */     hashtable.put("Failed to initialize LargeObject API", "Falhou ao inicializar API de Objetos Grandes");
/* 163 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Objetos Grandes não podem ser usados no modo de efetivação automática (auto-commit).");
/* 164 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "A classe SSLSocketFactory forneceu {0} que não pôde ser instanciado.");
/* 165 */     hashtable.put("Conversion of interval failed", "Conversão de interval falhou");
/* 166 */     hashtable.put("Conversion of money failed.", "Conversão de money falhou.");
/* 167 */     hashtable.put("Detail: {0}", "Detalhe: {0}");
/* 168 */     hashtable.put("Hint: {0}", "Dica: {0}");
/* 169 */     hashtable.put("Position: {0}", "Posição: {0}");
/* 170 */     hashtable.put("Where: {0}", "Onde: {0}");
/* 171 */     hashtable.put("Internal Query: {0}", "Consulta Interna: {0}");
/* 172 */     hashtable.put("Internal Position: {0}", "Posição Interna: {0}");
/* 173 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Local: Arquivo: {0}, Rotina: {1}, Linha: {2}");
/* 174 */     hashtable.put("Server SQLState: {0}", "SQLState: {0}");
/* 175 */     hashtable.put("Invalid flags", "Marcadores inválidos");
/* 176 */     hashtable.put("xid must not be null", "xid não deve ser nulo");
/* 177 */     hashtable.put("Connection is busy with another transaction", "Conexão está ocupada com outra transação");
/* 178 */     hashtable.put("suspend/resume not implemented", "suspender/recomeçar não está implementado");
/* 179 */     hashtable.put("Transaction interleaving not implemented", "Intercalação de transação não está implementado");
/* 180 */     hashtable.put("Error disabling autocommit", "Erro ao desabilitar autocommit");
/* 181 */     hashtable.put("tried to call end without corresponding start call", "tentou executar end sem a chamada ao start correspondente");
/* 182 */     hashtable.put("Not implemented: Prepare must be issued using the same connection that started the transaction", "Não está implementado: Prepare deve ser executado utilizando a mesma conexão que iniciou a transação");
/* 183 */     hashtable.put("Prepare called before end", "Prepare executado antes do end");
/* 184 */     hashtable.put("Server versions prior to 8.1 do not support two-phase commit.", "Versões do servidor anteriores a 8.1 não suportam efetivação em duas fases.");
/* 185 */     hashtable.put("Error preparing transaction", "Erro ao preparar transação");
/* 186 */     hashtable.put("Invalid flag", "Marcador inválido");
/* 187 */     hashtable.put("Error during recover", "Erro durante recuperação");
/* 188 */     hashtable.put("Error rolling back prepared transaction", "Erro ao cancelar transação preparada");
/* 189 */     hashtable.put("Not implemented: one-phase commit must be issued using the same connection that was used to start it", "Não está implementado: efetivada da primeira fase deve ser executada utilizando a mesma conexão que foi utilizada para iniciá-la");
/* 190 */     hashtable.put("commit called before end", "commit executado antes do end");
/* 191 */     hashtable.put("Error during one-phase commit", "Erro durante efetivação de uma fase");
/* 192 */     hashtable.put("Not implemented: 2nd phase commit must be issued using an idle connection", "Não está implementado: efetivação da segunda fase deve ser executada utilizado uma conexão ociosa");
/* 193 */     hashtable.put("Heuristic commit/rollback not supported", "Efetivação/Cancelamento heurístico não é suportado");
/* 194 */     table = hashtable;
/*     */   }
/*     */   
/*     */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 197 */     return table.get(paramString);
/*     */   }
/*     */   
/*     */   public Enumeration getKeys() {
/* 200 */     return table.keys();
/*     */   }
/*     */   
/*     */   public ResourceBundle getParent() {
/* 203 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_pt_BR.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */