package org.hsqldb.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Wrapper;
import org.hsqldb.FunctionCustom;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.types.Type;

public class JDBCDatabaseMetaData implements DatabaseMetaData, Wrapper {
  static final Integer INT_COLUMNS_NO_NULLS = new Integer(0);
  
  private JDBCConnection connection;
  
  private final boolean useSchemaDefault;
  
  private boolean concatNulls = true;
  
  private boolean nullsFirst = true;
  
  private boolean nullsOrder = true;
  
  private boolean uniqueNulls = true;
  
  private static final String BRI_SESSION_SCOPE_IN_LIST = "(2)";
  
  private static final String BRI_TEMPORARY_SCOPE_IN_LIST = "(0,1,2)";
  
  private static final String BRI_TRANSACTION_SCOPE_IN_LIST = "(1,2)";
  
  private static final String selstar = "SELECT * FROM INFORMATION_SCHEMA.";
  
  private static final String whereTrue = " WHERE TRUE";
  
  public static final int JDBC_MAJOR = 4;
  
  public boolean allProceduresAreCallable() throws SQLException {
    return true;
  }
  
  public boolean allTablesAreSelectable() throws SQLException {
    return true;
  }
  
  public String getURL() throws SQLException {
    return this.connection.getURL();
  }
  
  public String getUserName() throws SQLException {
    ResultSet resultSet = execute("CALL USER()");
    resultSet.next();
    String str = resultSet.getString(1);
    resultSet.close();
    return str;
  }
  
  public boolean isReadOnly() throws SQLException {
    ResultSet resultSet = execute("CALL IS_READONLY_DATABASE()");
    resultSet.next();
    boolean bool = resultSet.getBoolean(1);
    resultSet.close();
    return bool;
  }
  
  public boolean nullsAreSortedHigh() throws SQLException {
    setCurrentProperties();
    return (!this.nullsFirst) & (!this.nullsOrder);
  }
  
  public boolean nullsAreSortedLow() throws SQLException {
    setCurrentProperties();
    return this.nullsFirst & (!this.nullsOrder);
  }
  
  public boolean nullsAreSortedAtStart() throws SQLException {
    setCurrentProperties();
    return this.nullsFirst & this.nullsOrder;
  }
  
  public boolean nullsAreSortedAtEnd() throws SQLException {
    setCurrentProperties();
    return (!this.nullsFirst) & this.nullsOrder;
  }
  
  public String getDatabaseProductName() throws SQLException {
    return "HSQL Database Engine";
  }
  
  public String getDatabaseProductVersion() throws SQLException {
    ResultSet resultSet = execute("call database_version()");
    resultSet.next();
    return resultSet.getString(1);
  }
  
  public String getDriverName() throws SQLException {
    return "HSQL Database Engine Driver";
  }
  
  public String getDriverVersion() throws SQLException {
    return "2.3.0";
  }
  
  public int getDriverMajorVersion() {
    return 2;
  }
  
  public int getDriverMinorVersion() {
    return 3;
  }
  
  public boolean usesLocalFiles() throws SQLException {
    return false;
  }
  
  public boolean usesLocalFilePerTable() throws SQLException {
    return false;
  }
  
  public boolean supportsMixedCaseIdentifiers() throws SQLException {
    return false;
  }
  
  public boolean storesUpperCaseIdentifiers() throws SQLException {
    return true;
  }
  
  public boolean storesLowerCaseIdentifiers() throws SQLException {
    return false;
  }
  
  public boolean storesMixedCaseIdentifiers() throws SQLException {
    return false;
  }
  
  public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
    return true;
  }
  
  public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
    return false;
  }
  
  public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
    return false;
  }
  
  public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
    return false;
  }
  
  public String getIdentifierQuoteString() throws SQLException {
    return "\"";
  }
  
  public String getSQLKeywords() throws SQLException {
    return "";
  }
  
  public String getNumericFunctions() throws SQLException {
    return StringUtil.getList(FunctionCustom.openGroupNumericFunctions, ",", "");
  }
  
  public String getStringFunctions() throws SQLException {
    return StringUtil.getList(FunctionCustom.openGroupStringFunctions, ",", "");
  }
  
  public String getSystemFunctions() throws SQLException {
    return StringUtil.getList(FunctionCustom.openGroupSystemFunctions, ",", "");
  }
  
  public String getTimeDateFunctions() throws SQLException {
    return StringUtil.getList(FunctionCustom.openGroupDateTimeFunctions, ",", "");
  }
  
  public String getSearchStringEscape() throws SQLException {
    return "\\";
  }
  
  public String getExtraNameCharacters() throws SQLException {
    return "";
  }
  
  public boolean supportsAlterTableWithAddColumn() throws SQLException {
    return true;
  }
  
  public boolean supportsAlterTableWithDropColumn() throws SQLException {
    return true;
  }
  
  public boolean supportsColumnAliasing() throws SQLException {
    return true;
  }
  
  public boolean nullPlusNonNullIsNull() throws SQLException {
    return this.concatNulls;
  }
  
  public boolean supportsConvert() throws SQLException {
    return true;
  }
  
  public boolean supportsConvert(int paramInt1, int paramInt2) throws SQLException {
    Type type1 = Type.getDefaultTypeWithSize(Type.getHSQLDBTypeCode(paramInt1));
    Type type2 = Type.getDefaultTypeWithSize(Type.getHSQLDBTypeCode(paramInt2));
    return (type1 == null || type2 == null) ? false : ((paramInt1 == 0 && paramInt2 == 2003) ? true : type2.canConvertFrom(type1));
  }
  
  public boolean supportsTableCorrelationNames() throws SQLException {
    return true;
  }
  
  public boolean supportsDifferentTableCorrelationNames() throws SQLException {
    return false;
  }
  
  public boolean supportsExpressionsInOrderBy() throws SQLException {
    return true;
  }
  
  public boolean supportsOrderByUnrelated() throws SQLException {
    return true;
  }
  
  public boolean supportsGroupBy() throws SQLException {
    return true;
  }
  
  public boolean supportsGroupByUnrelated() throws SQLException {
    return true;
  }
  
  public boolean supportsGroupByBeyondSelect() throws SQLException {
    return true;
  }
  
  public boolean supportsLikeEscapeClause() throws SQLException {
    return true;
  }
  
  public boolean supportsMultipleResultSets() throws SQLException {
    return true;
  }
  
  public boolean supportsMultipleTransactions() throws SQLException {
    return true;
  }
  
  public boolean supportsNonNullableColumns() throws SQLException {
    return true;
  }
  
  public boolean supportsMinimumSQLGrammar() throws SQLException {
    return true;
  }
  
  public boolean supportsCoreSQLGrammar() throws SQLException {
    return true;
  }
  
  public boolean supportsExtendedSQLGrammar() throws SQLException {
    return true;
  }
  
  public boolean supportsANSI92EntryLevelSQL() throws SQLException {
    return true;
  }
  
  public boolean supportsANSI92IntermediateSQL() throws SQLException {
    return true;
  }
  
  public boolean supportsANSI92FullSQL() throws SQLException {
    return true;
  }
  
  public boolean supportsIntegrityEnhancementFacility() throws SQLException {
    return true;
  }
  
  public boolean supportsOuterJoins() throws SQLException {
    return true;
  }
  
  public boolean supportsFullOuterJoins() throws SQLException {
    return true;
  }
  
  public boolean supportsLimitedOuterJoins() throws SQLException {
    return true;
  }
  
  public String getSchemaTerm() throws SQLException {
    return "SCHEMA";
  }
  
  public String getProcedureTerm() throws SQLException {
    return "PROCEDURE";
  }
  
  public String getCatalogTerm() throws SQLException {
    return "CATALOG";
  }
  
  public boolean isCatalogAtStart() throws SQLException {
    return true;
  }
  
  public String getCatalogSeparator() throws SQLException {
    return ".";
  }
  
  public boolean supportsSchemasInDataManipulation() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsSchemasInProcedureCalls() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsSchemasInTableDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsSchemasInIndexDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsCatalogsInDataManipulation() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsCatalogsInProcedureCalls() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsCatalogsInTableDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
    return !this.useSchemaDefault;
  }
  
  public boolean supportsPositionedDelete() throws SQLException {
    return true;
  }
  
  public boolean supportsPositionedUpdate() throws SQLException {
    return true;
  }
  
  public boolean supportsSelectForUpdate() throws SQLException {
    return true;
  }
  
  public boolean supportsStoredProcedures() throws SQLException {
    return true;
  }
  
  public boolean supportsSubqueriesInComparisons() throws SQLException {
    return true;
  }
  
  public boolean supportsSubqueriesInExists() throws SQLException {
    return true;
  }
  
  public boolean supportsSubqueriesInIns() throws SQLException {
    return true;
  }
  
  public boolean supportsSubqueriesInQuantifieds() throws SQLException {
    return true;
  }
  
  public boolean supportsCorrelatedSubqueries() throws SQLException {
    return true;
  }
  
  public boolean supportsUnion() throws SQLException {
    return true;
  }
  
  public boolean supportsUnionAll() throws SQLException {
    return true;
  }
  
  public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
    return true;
  }
  
  public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
    return false;
  }
  
  public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
    return true;
  }
  
  public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
    return true;
  }
  
  public int getMaxBinaryLiteralLength() throws SQLException {
    return 0;
  }
  
  public int getMaxCharLiteralLength() throws SQLException {
    return 0;
  }
  
  public int getMaxColumnNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxColumnsInGroupBy() throws SQLException {
    return 0;
  }
  
  public int getMaxColumnsInIndex() throws SQLException {
    return 0;
  }
  
  public int getMaxColumnsInOrderBy() throws SQLException {
    return 0;
  }
  
  public int getMaxColumnsInSelect() throws SQLException {
    return 0;
  }
  
  public int getMaxColumnsInTable() throws SQLException {
    return 0;
  }
  
  public int getMaxConnections() throws SQLException {
    return 0;
  }
  
  public int getMaxCursorNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxIndexLength() throws SQLException {
    return 0;
  }
  
  public int getMaxSchemaNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxProcedureNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxCatalogNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxRowSize() throws SQLException {
    return 0;
  }
  
  public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
    return true;
  }
  
  public int getMaxStatementLength() throws SQLException {
    return 0;
  }
  
  public int getMaxStatements() throws SQLException {
    return 0;
  }
  
  public int getMaxTableNameLength() throws SQLException {
    return 128;
  }
  
  public int getMaxTablesInSelect() throws SQLException {
    return 0;
  }
  
  public int getMaxUserNameLength() throws SQLException {
    return 128;
  }
  
  public int getDefaultTransactionIsolation() throws SQLException {
    ResultSet resultSet = execute("CALL DATABASE_ISOLATION_LEVEL()");
    resultSet.next();
    String str = resultSet.getString(1);
    resultSet.close();
    return str.startsWith("READ COMMITTED") ? 2 : (str.startsWith("READ UNCOMMITTED") ? 1 : (str.startsWith("SERIALIZABLE") ? 8 : 2));
  }
  
  public boolean supportsTransactions() throws SQLException {
    return true;
  }
  
  public boolean supportsTransactionIsolationLevel(int paramInt) throws SQLException {
    return (paramInt == 1 || paramInt == 2 || paramInt == 4 || paramInt == 8);
  }
  
  public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
    return false;
  }
  
  public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
    return true;
  }
  
  public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
    return true;
  }
  
  public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
    return false;
  }
  
  public ResultSet getProcedures(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (wantsIsNull(paramString3))
      return executeSelect("SYSTEM_PROCEDURES", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_PROCEDURES").append(and("PROCEDURE_CAT", "=", paramString1)).append(and("PROCEDURE_SCHEM", "LIKE", paramString2)).append(and("PROCEDURE_NAME", "LIKE", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getProcedureColumns(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    if (wantsIsNull(paramString3) || wantsIsNull(paramString4))
      return executeSelect("SYSTEM_PROCEDURECOLUMNS", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_PROCEDURECOLUMNS").append(and("PROCEDURE_CAT", "=", paramString1)).append(and("PROCEDURE_SCHEM", "LIKE", paramString2)).append(and("PROCEDURE_NAME", "LIKE", paramString3)).append(and("COLUMN_NAME", "LIKE", paramString4));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getTables(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) throws SQLException {
    if (wantsIsNull(paramString3) || (paramArrayOfString != null && paramArrayOfString.length == 0))
      return executeSelect("SYSTEM_TABLES", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_TABLES").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "LIKE", paramString2)).append(and("TABLE_NAME", "LIKE", paramString3));
    if (paramArrayOfString != null)
      stringBuffer.append(" AND TABLE_TYPE IN (").append(StringUtil.getList(paramArrayOfString, ",", "'")).append(')'); 
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getSchemas() throws SQLException {
    return executeSelect("SYSTEM_SCHEMAS", null);
  }
  
  public ResultSet getCatalogs() throws SQLException {
    String str = "SELECT CATALOG_NAME AS TABLE_CAT FROM INFORMATION_SCHEMA.INFORMATION_SCHEMA_CATALOG_NAME";
    return execute(str);
  }
  
  public ResultSet getTableTypes() throws SQLException {
    return executeSelect("SYSTEM_TABLETYPES", null);
  }
  
  public ResultSet getColumns(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    if (wantsIsNull(paramString3) || wantsIsNull(paramString4))
      return executeSelect("SYSTEM_COLUMNS", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_COLUMNS").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "LIKE", paramString2)).append(and("TABLE_NAME", "LIKE", paramString3)).append(and("COLUMN_NAME", "LIKE", paramString4));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getColumnPrivileges(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    String str = "SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM,TABLE_NAME, COLUMN_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES WHERE TRUE " + and("TABLE_CATALOG", "=", paramString1) + and("TABLE_SCHEMA", "=", paramString2) + and("TABLE_NAME", "=", paramString3) + and("COLUMN_NAME", "LIKE", paramString4);
    return execute(str);
  }
  
  public ResultSet getTablePrivileges(String paramString1, String paramString2, String paramString3) throws SQLException {
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    String str = "SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM,TABLE_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE FROM INFORMATION_SCHEMA.TABLE_PRIVILEGES WHERE TRUE " + and("TABLE_CATALOG", "=", paramString1) + and("TABLE_SCHEMA", "LIKE", paramString2) + and("TABLE_NAME", "LIKE", paramString3);
    return execute(str);
  }
  
  public ResultSet getBestRowIdentifier(String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean) throws SQLException {
    String str;
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    switch (paramInt) {
      case 0:
        str = "(0,1,2)";
        break;
      case 1:
        str = "(1,2)";
        break;
      case 2:
        str = "(2)";
        break;
      default:
        throw JDBCUtil.invalidArgument("scope");
    } 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    Integer integer = paramBoolean ? null : INT_COLUMNS_NO_NULLS;
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_BESTROWIDENTIFIER").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "=", paramString2)).append(and("TABLE_NAME", "=", paramString3)).append(and("NULLABLE", "=", integer)).append(" AND SCOPE IN " + str);
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getVersionColumns(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_VERSIONCOLUMNS").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "=", paramString2)).append(and("TABLE_NAME", "=", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getPrimaryKeys(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_PRIMARYKEYS").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "=", paramString2)).append(and("TABLE_NAME", "=", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getImportedKeys(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_CROSSREFERENCE").append(and("FKTABLE_CAT", "=", paramString1)).append(and("FKTABLE_SCHEM", "=", paramString2)).append(and("FKTABLE_NAME", "=", paramString3)).append(" ORDER BY PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, KEY_SEQ");
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getExportedKeys(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_CROSSREFERENCE").append(and("PKTABLE_CAT", "=", paramString1)).append(and("PKTABLE_SCHEM", "=", paramString2)).append(and("PKTABLE_NAME", "=", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getCrossReference(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("parentTable"); 
    if (paramString6 == null)
      throw JDBCUtil.nullArgument("foreignTable"); 
    paramString1 = translateCatalog(paramString1);
    paramString4 = translateCatalog(paramString4);
    paramString2 = translateSchema(paramString2);
    paramString5 = translateSchema(paramString5);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_CROSSREFERENCE").append(and("PKTABLE_CAT", "=", paramString1)).append(and("PKTABLE_SCHEM", "=", paramString2)).append(and("PKTABLE_NAME", "=", paramString3)).append(and("FKTABLE_CAT", "=", paramString4)).append(and("FKTABLE_SCHEM", "=", paramString5)).append(and("FKTABLE_NAME", "=", paramString6));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getTypeInfo() throws SQLException {
    return executeSelect("SYSTEM_TYPEINFO", null);
  }
  
  public ResultSet getIndexInfo(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2) throws SQLException {
    if (paramString3 == null)
      throw JDBCUtil.nullArgument("table"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    Boolean bool = paramBoolean1 ? Boolean.FALSE : null;
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_INDEXINFO").append(and("TABLE_CAT", "=", paramString1)).append(and("TABLE_SCHEM", "=", paramString2)).append(and("TABLE_NAME", "=", paramString3)).append(and("NON_UNIQUE", "=", bool));
    return execute(stringBuffer.toString());
  }
  
  public boolean supportsResultSetType(int paramInt) throws SQLException {
    return (paramInt == 1003 || paramInt == 1004);
  }
  
  public boolean supportsResultSetConcurrency(int paramInt1, int paramInt2) throws SQLException {
    return (supportsResultSetType(paramInt1) && (paramInt2 == 1007 || paramInt2 == 1008));
  }
  
  public boolean ownUpdatesAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean ownDeletesAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean ownInsertsAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean othersUpdatesAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean othersDeletesAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean othersInsertsAreVisible(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean updatesAreDetected(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean deletesAreDetected(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean insertsAreDetected(int paramInt) throws SQLException {
    return false;
  }
  
  public boolean supportsBatchUpdates() throws SQLException {
    return true;
  }
  
  public ResultSet getUDTs(String paramString1, String paramString2, String paramString3, int[] paramArrayOfint) throws SQLException {
    if (wantsIsNull(paramString3) || (paramArrayOfint != null && paramArrayOfint.length == 0))
      executeSelect("SYSTEM_UDTS", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_UDTS").append(and("TYPE_CAT", "=", paramString1)).append(and("TYPE_SCHEM", "LIKE", paramString2)).append(and("TYPE_NAME", "LIKE", paramString3));
    if (paramArrayOfint != null)
      stringBuffer.append(" AND DATA_TYPE IN (").append(StringUtil.getList(paramArrayOfint, ",", "")).append(')'); 
    return execute(stringBuffer.toString());
  }
  
  public Connection getConnection() throws SQLException {
    return this.connection;
  }
  
  public boolean supportsSavepoints() throws SQLException {
    return true;
  }
  
  public boolean supportsNamedParameters() throws SQLException {
    return true;
  }
  
  public boolean supportsMultipleOpenResults() throws SQLException {
    return true;
  }
  
  public boolean supportsGetGeneratedKeys() throws SQLException {
    return true;
  }
  
  public ResultSet getSuperTypes(String paramString1, String paramString2, String paramString3) throws SQLException {
    if (wantsIsNull(paramString3))
      return executeSelect("SYSTEM_SUPERTYPES", "0=1"); 
    paramString1 = translateCatalog(paramString1);
    paramString2 = translateSchema(paramString2);
    StringBuffer stringBuffer = toQueryPrefixNoSelect("SELECT * FROM (SELECT USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_SCHEMA, USER_DEFINED_TYPE_NAME,CAST (NULL AS INFORMATION_SCHEMA.SQL_IDENTIFIER), CAST (NULL AS INFORMATION_SCHEMA.SQL_IDENTIFIER), DATA_TYPE FROM INFORMATION_SCHEMA.USER_DEFINED_TYPES UNION SELECT DOMAIN_CATALOG, DOMAIN_SCHEMA, DOMAIN_NAME,NULL,NULL, DATA_TYPE FROM INFORMATION_SCHEMA.DOMAINS) AS SUPERTYPES(TYPE_CAT, TYPE_SCHEM, TYPE_NAME, SUPERTYPE_CAT, SUPERTYPE_SCHEM, SUPERTYPE_NAME) ").append(and("TYPE_CAT", "=", paramString1)).append(and("TYPE_SCHEM", "LIKE", paramString2)).append(and("TYPE_NAME", "LIKE", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getSuperTables(String paramString1, String paramString2, String paramString3) throws SQLException {
    StringBuffer stringBuffer = toQueryPrefixNoSelect("SELECT TABLE_NAME AS TABLE_CAT, TABLE_NAME AS TABLE_SCHEM, TABLE_NAME, TABLE_NAME AS SUPERTABLE_NAME FROM INFORMATION_SCHEMA.TABLES ").append(and("TABLE_NAME", "=", ""));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getAttributes(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    StringBuffer stringBuffer = toQueryPrefixNoSelect("SELECT TABLE_NAME AS TYPE_CAT, TABLE_NAME AS TYPE_SCHME, TABLE_NAME AS TYPE_NAME, TABLE_NAME AS ATTR_NAME, CAST(0 AS INTEGER) AS DATA_TYPE, TABLE_NAME AS ATTR_TYPE_NAME, CAST(0 AS INTEGER) AS ATTR_SIZE, CAST(0 AS INTEGER) AS DECIMAL_DIGITS, CAST(0 AS INTEGER) AS NUM_PREC_RADIX, CAST(0 AS INTEGER) AS NULLABLE, '' AS REMARK, '' AS ATTR_DEF, CAST(0 AS INTEGER) AS SQL_DATA_TYPE, CAST(0 AS INTEGER) AS SQL_DATETIME_SUB, CAST(0 AS INTEGER) AS CHAR_OCTECT_LENGTH, CAST(0 AS INTEGER) AS ORDINAL_POSITION, '' AS NULLABLE, '' AS SCOPE_CATALOG, '' AS SCOPE_SCHEMA, '' AS SCOPE_TABLE, CAST(0 AS SMALLINT) AS SCOPE_DATA_TYPE FROM INFORMATION_SCHEMA.TABLES ").append(and("TABLE_NAME", "=", ""));
    return execute(stringBuffer.toString());
  }
  
  public boolean supportsResultSetHoldability(int paramInt) throws SQLException {
    return (paramInt == 1 || paramInt == 2);
  }
  
  public int getResultSetHoldability() throws SQLException {
    return 1;
  }
  
  public int getDatabaseMajorVersion() throws SQLException {
    ResultSet resultSet = execute("call database_version()");
    resultSet.next();
    String str = resultSet.getString(1);
    resultSet.close();
    return Integer.parseInt(str.substring(0, str.indexOf(".")));
  }
  
  public int getDatabaseMinorVersion() throws SQLException {
    ResultSet resultSet = execute("call database_version()");
    resultSet.next();
    String str = resultSet.getString(1);
    resultSet.close();
    int i = str.indexOf(".") + 1;
    return Integer.parseInt(str.substring(i, str.indexOf(".", i)));
  }
  
  public int getJDBCMajorVersion() throws SQLException {
    return 4;
  }
  
  public int getJDBCMinorVersion() throws SQLException {
    return 0;
  }
  
  public int getSQLStateType() throws SQLException {
    return 2;
  }
  
  public boolean locatorsUpdateCopy() throws SQLException {
    return false;
  }
  
  public boolean supportsStatementPooling() throws SQLException {
    return true;
  }
  
  public RowIdLifetime getRowIdLifetime() throws SQLException {
    return RowIdLifetime.ROWID_UNSUPPORTED;
  }
  
  public ResultSet getSchemas(String paramString1, String paramString2) throws SQLException {
    StringBuffer stringBuffer = toQueryPrefix("SYSTEM_SCHEMAS").append(and("TABLE_CATALOG", "=", paramString1)).append(and("TABLE_SCHEM", "LIKE", paramString2));
    return execute(stringBuffer.toString());
  }
  
  public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
    return true;
  }
  
  public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
    return false;
  }
  
  public ResultSet getClientInfoProperties() throws SQLException {
    String str = "SELECT * FROM INFORMATION_SCHEMA.SYSTEM_CONNECTION_PROPERTIES";
    return execute(str);
  }
  
  public ResultSet getFunctions(String paramString1, String paramString2, String paramString3) throws SQLException {
    StringBuffer stringBuffer = new StringBuffer(256);
    stringBuffer.append("select ").append("sp.procedure_cat as FUNCTION_CAT,").append("sp.procedure_schem as FUNCTION_SCHEM,").append("sp.procedure_name as FUNCTION_NAME,").append("sp.remarks as REMARKS,").append("1 as FUNCTION_TYPE,").append("sp.specific_name as SPECIFIC_NAME ").append("from information_schema.system_procedures sp ").append("where sp.procedure_type = 2 ");
    if (wantsIsNull(paramString3))
      return execute(stringBuffer.append("and 1=0").toString()); 
    paramString2 = translateSchema(paramString2);
    stringBuffer.append(and("sp.procedure_cat", "=", paramString1)).append(and("sp.procedure_schem", "LIKE", paramString2)).append(and("sp.procedure_name", "LIKE", paramString3));
    return execute(stringBuffer.toString());
  }
  
  public ResultSet getFunctionColumns(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    StringBuffer stringBuffer = new StringBuffer(256);
    stringBuffer.append("select pc.procedure_cat as FUNCTION_CAT,").append("pc.procedure_schem as FUNCTION_SCHEM,").append("pc.procedure_name as FUNCTION_NAME,").append("pc.column_name as COLUMN_NAME,").append("case pc.column_type").append(" when 3 then 5").append(" when 4 then 3").append(" when 5 then 4").append(" else pc.column_type").append(" end as COLUMN_TYPE,").append("pc.DATA_TYPE,").append("pc.TYPE_NAME,").append("pc.PRECISION,").append("pc.LENGTH,").append("pc.SCALE,").append("pc.RADIX,").append("pc.NULLABLE,").append("pc.REMARKS,").append("pc.CHAR_OCTET_LENGTH,").append("pc.ORDINAL_POSITION,").append("pc.IS_NULLABLE,").append("pc.SPECIFIC_NAME,").append("case pc.column_type").append(" when 3 then 1").append(" else 0").append(" end AS COLUMN_GROUP ").append("from information_schema.system_procedurecolumns pc ").append("join (select procedure_schem,").append("procedure_name,").append("specific_name ").append("from information_schema.system_procedures ").append("where procedure_type = 2) p ").append("on pc.procedure_schem = p.procedure_schem ").append("and pc.procedure_name = p.procedure_name ").append("and pc.specific_name = p.specific_name ").append("and ((pc.column_type = 3 and pc.column_name = '@p0') ").append("or ").append("(pc.column_type <> 3)) ");
    if (wantsIsNull(paramString3) || wantsIsNull(paramString4))
      return execute(stringBuffer.append("where 1=0").toString()); 
    paramString2 = translateSchema(paramString2);
    stringBuffer.append("where 1=1 ").append(and("pc.procedure_cat", "=", paramString1)).append(and("pc.procedure_schem", "LIKE", paramString2)).append(and("pc.procedure_name", "LIKE", paramString3)).append(and("pc.column_name", "LIKE", paramString4)).append(" order by 1, 2, 3, 17, 18 , 15");
    return execute(stringBuffer.toString());
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  public ResultSet getPseudoColumns(String paramString1, String paramString2, String paramString3, String paramString4) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public boolean generatedKeyAlwaysReturned() throws SQLException {
    return true;
  }
  
  JDBCDatabaseMetaData(JDBCConnection paramJDBCConnection) throws SQLException {
    this.connection = paramJDBCConnection;
    this.useSchemaDefault = paramJDBCConnection.isInternal ? false : paramJDBCConnection.connProperties.isPropertyTrue("default_schema");
  }
  
  private static String and(String paramString1, String paramString2, Object paramObject) {
    if (paramObject == null)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    boolean bool = paramObject instanceof String;
    if (bool && ((String)paramObject).length() == 0)
      return stringBuffer.append(" AND ").append(paramString1).append(" IS NULL").toString(); 
    String str = bool ? Type.SQL_VARCHAR.convertToSQLString(paramObject) : String.valueOf(paramObject);
    stringBuffer.append(" AND ").append(paramString1).append(' ');
    if (bool && "LIKE".equalsIgnoreCase(paramString2)) {
      if (str.indexOf('_') < 0 && str.indexOf('%') < 0) {
        stringBuffer.append("=").append(' ').append(str);
      } else {
        stringBuffer.append("LIKE").append(' ').append(str);
        if (str.indexOf("\\_") >= 0 || str.indexOf("\\%") >= 0)
          stringBuffer.append(" ESCAPE '\\'"); 
      } 
    } else {
      stringBuffer.append(paramString2).append(' ').append(str);
    } 
    return stringBuffer.toString();
  }
  
  private ResultSet execute(String paramString) throws SQLException {
    JDBCStatement jDBCStatement = (JDBCStatement)this.connection.createStatement(1004, 1007);
    jDBCStatement.maxRows = -1;
    ResultSet resultSet = jDBCStatement.executeQuery(paramString);
    ((JDBCResultSet)resultSet).autoClose = true;
    return resultSet;
  }
  
  private ResultSet executeSelect(String paramString1, String paramString2) throws SQLException {
    String str = "SELECT * FROM INFORMATION_SCHEMA." + paramString1;
    if (paramString2 != null)
      str = str + " WHERE " + paramString2; 
    return execute(str);
  }
  
  private StringBuffer toQueryPrefix(String paramString) {
    StringBuffer stringBuffer = new StringBuffer(255);
    return stringBuffer.append("SELECT * FROM INFORMATION_SCHEMA.").append(paramString).append(" WHERE TRUE");
  }
  
  private StringBuffer toQueryPrefixNoSelect(String paramString) {
    StringBuffer stringBuffer = new StringBuffer(255);
    return stringBuffer.append(paramString).append(" WHERE TRUE");
  }
  
  private static boolean wantsIsNull(String paramString) {
    return (paramString != null && paramString.length() == 0);
  }
  
  private void setCurrentProperties() throws SQLException {
    ResultSet resultSet = executeSelect("SYSTEM_PROPERTIES", "PROPERTY_NAME IN ('sql.concat_nulls', 'sql.nulls_first' , 'sql.nulls_order', 'sql.unique_nulls')");
    while (resultSet.next()) {
      String str = resultSet.getString(2);
      boolean bool = Boolean.valueOf(resultSet.getString(3)).booleanValue();
      if (str.equals("sql.concat_nulls")) {
        this.concatNulls = bool;
        continue;
      } 
      if (str.equals("sql.nulls_first")) {
        this.nullsFirst = bool;
        continue;
      } 
      if (str.equals("sql.nulls_order")) {
        this.nullsOrder = bool;
        continue;
      } 
      if (str.equals("sql.unique_nulls"))
        this.uniqueNulls = bool; 
    } 
  }
  
  String getDatabaseDefaultSchema() throws SQLException {
    ResultSet resultSet = executeSelect("SYSTEM_SCHEMAS", "IS_DEFAULT=TRUE");
    return resultSet.next() ? resultSet.getString(1) : null;
  }
  
  String getConnectionDefaultSchema() throws SQLException {
    ResultSet resultSet = execute("CALL CURRENT_SCHEMA");
    resultSet.next();
    String str = resultSet.getString(1);
    resultSet.close();
    return str;
  }
  
  void setConnectionDefaultSchema(String paramString) throws SQLException {
    execute("SET SCHEMA " + StringConverter.toQuotedString(paramString, '"', true));
  }
  
  private String translateSchema(String paramString) throws SQLException {
    if (this.useSchemaDefault && paramString != null && paramString.length() == 0) {
      String str = getDatabaseDefaultSchema();
      if (str != null)
        paramString = str; 
    } 
    return paramString;
  }
  
  String getDatabaseDefaultCatalog() throws SQLException {
    ResultSet resultSet = executeSelect("SYSTEM_SCHEMAS", "IS_DEFAULT=TRUE");
    return resultSet.next() ? resultSet.getString(2) : null;
  }
  
  private String translateCatalog(String paramString) throws SQLException {
    if (this.useSchemaDefault && paramString != null && paramString.length() == 0) {
      String str = getDatabaseDefaultCatalog();
      if (str != null)
        paramString = str; 
    } 
    return paramString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCDatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */