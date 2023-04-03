/*      */ package org.postgresql.jdbc2;
/*      */ 
/*      */ import java.sql.Array;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import org.postgresql.Driver;
/*      */ import org.postgresql.core.BaseStatement;
/*      */ import org.postgresql.core.Field;
/*      */ import org.postgresql.util.GT;
/*      */ import org.postgresql.util.PSQLException;
/*      */ import org.postgresql.util.PSQLState;
/*      */ 
/*      */ public abstract class AbstractJdbc2DatabaseMetaData {
/*      */   private static final String keywords = "abort,acl,add,aggregate,append,archive,arch_store,backward,binary,boolean,change,cluster,copy,database,delimiter,delimiters,do,extend,explain,forward,heavy,index,inherits,isnull,light,listen,load,merge,nothing,notify,notnull,oids,purge,rename,replace,retrieve,returns,rule,recipe,setof,stdin,stdout,store,vacuum,verbose,version";
/*      */   
/*      */   protected final AbstractJdbc2Connection connection;
/*      */   
/*      */   private int NAMEDATALEN;
/*      */   
/*      */   private int INDEX_MAX_KEYS;
/*      */   
/*      */   public AbstractJdbc2DatabaseMetaData(AbstractJdbc2Connection conn) {
/*   40 */     this.NAMEDATALEN = 0;
/*   41 */     this.INDEX_MAX_KEYS = 0;
/*      */     this.connection = conn;
/*      */   }
/*      */   
/*      */   protected int getMaxIndexKeys() throws SQLException {
/*   44 */     if (this.INDEX_MAX_KEYS == 0) {
/*      */       String sql;
/*   47 */       if (this.connection.haveMinimumServerVersion("8.0")) {
/*   48 */         sql = "SELECT setting FROM pg_catalog.pg_settings WHERE name='max_index_keys'";
/*      */       } else {
/*      */         String from;
/*   51 */         if (this.connection.haveMinimumServerVersion("7.3")) {
/*   53 */           from = "pg_catalog.pg_namespace n, pg_catalog.pg_type t1, pg_catalog.pg_type t2 WHERE t1.typnamespace=n.oid AND n.nspname='pg_catalog' AND ";
/*      */         } else {
/*   57 */           from = "pg_type t1, pg_type t2 WHERE ";
/*      */         } 
/*   59 */         sql = "SELECT t1.typlen/t2.typlen FROM " + from + " t1.typelem=t2.oid AND t1.typname='oidvector'";
/*      */       } 
/*   61 */       ResultSet rs = this.connection.createStatement().executeQuery(sql);
/*   62 */       if (!rs.next())
/*   64 */         throw new PSQLException(GT.tr("Unable to determine a value for MaxIndexKeys due to missing system catalog data."), PSQLState.UNEXPECTED_ERROR); 
/*   66 */       this.INDEX_MAX_KEYS = rs.getInt(1);
/*   67 */       rs.close();
/*      */     } 
/*   69 */     return this.INDEX_MAX_KEYS;
/*      */   }
/*      */   
/*      */   protected int getMaxNameLength() throws SQLException {
/*   73 */     if (this.NAMEDATALEN == 0) {
/*      */       String sql;
/*   76 */       if (this.connection.haveMinimumServerVersion("7.3")) {
/*   78 */         sql = "SELECT t.typlen FROM pg_catalog.pg_type t, pg_catalog.pg_namespace n WHERE t.typnamespace=n.oid AND t.typname='name' AND n.nspname='pg_catalog'";
/*      */       } else {
/*   82 */         sql = "SELECT typlen FROM pg_type WHERE typname='name'";
/*      */       } 
/*   84 */       ResultSet rs = this.connection.createStatement().executeQuery(sql);
/*   85 */       if (!rs.next())
/*   87 */         throw new PSQLException(GT.tr("Unable to find name datatype in the system catalogs."), PSQLState.UNEXPECTED_ERROR); 
/*   89 */       this.NAMEDATALEN = rs.getInt("typlen");
/*   90 */       rs.close();
/*      */     } 
/*   92 */     return this.NAMEDATALEN - 1;
/*      */   }
/*      */   
/*      */   public boolean allProceduresAreCallable() throws SQLException {
/*  105 */     return true;
/*      */   }
/*      */   
/*      */   public boolean allTablesAreSelectable() throws SQLException {
/*  117 */     return true;
/*      */   }
/*      */   
/*      */   public String getURL() throws SQLException {
/*  128 */     return this.connection.getURL();
/*      */   }
/*      */   
/*      */   public String getUserName() throws SQLException {
/*  139 */     return this.connection.getUserName();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/*  150 */     return this.connection.isReadOnly();
/*      */   }
/*      */   
/*      */   public boolean nullsAreSortedHigh() throws SQLException {
/*  161 */     return this.connection.haveMinimumServerVersion("7.2");
/*      */   }
/*      */   
/*      */   public boolean nullsAreSortedLow() throws SQLException {
/*  172 */     return false;
/*      */   }
/*      */   
/*      */   public boolean nullsAreSortedAtStart() throws SQLException {
/*  183 */     return false;
/*      */   }
/*      */   
/*      */   public boolean nullsAreSortedAtEnd() throws SQLException {
/*  194 */     return !this.connection.haveMinimumServerVersion("7.2");
/*      */   }
/*      */   
/*      */   public String getDatabaseProductName() throws SQLException {
/*  206 */     return "PostgreSQL";
/*      */   }
/*      */   
/*      */   public String getDatabaseProductVersion() throws SQLException {
/*  217 */     return this.connection.getDBVersionNumber();
/*      */   }
/*      */   
/*      */   public String getDriverName() throws SQLException {
/*  229 */     return "PostgreSQL Native Driver";
/*      */   }
/*      */   
/*      */   public String getDriverVersion() throws SQLException {
/*  241 */     return Driver.getVersion();
/*      */   }
/*      */   
/*      */   public int getDriverMajorVersion() {
/*  251 */     return 9;
/*      */   }
/*      */   
/*      */   public int getDriverMinorVersion() {
/*  261 */     return 1;
/*      */   }
/*      */   
/*      */   public boolean usesLocalFiles() throws SQLException {
/*  273 */     return false;
/*      */   }
/*      */   
/*      */   public boolean usesLocalFilePerTable() throws SQLException {
/*  285 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsMixedCaseIdentifiers() throws SQLException {
/*  298 */     return false;
/*      */   }
/*      */   
/*      */   public boolean storesUpperCaseIdentifiers() throws SQLException {
/*  309 */     return false;
/*      */   }
/*      */   
/*      */   public boolean storesLowerCaseIdentifiers() throws SQLException {
/*  320 */     return true;
/*      */   }
/*      */   
/*      */   public boolean storesMixedCaseIdentifiers() throws SQLException {
/*  331 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
/*  344 */     return true;
/*      */   }
/*      */   
/*      */   public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
/*  355 */     return false;
/*      */   }
/*      */   
/*      */   public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
/*  366 */     return false;
/*      */   }
/*      */   
/*      */   public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
/*  377 */     return false;
/*      */   }
/*      */   
/*      */   public String getIdentifierQuoteString() throws SQLException {
/*  390 */     return "\"";
/*      */   }
/*      */   
/*      */   public String getSQLKeywords() throws SQLException {
/*  411 */     return "abort,acl,add,aggregate,append,archive,arch_store,backward,binary,boolean,change,cluster,copy,database,delimiter,delimiters,do,extend,explain,forward,heavy,index,inherits,isnull,light,listen,load,merge,nothing,notify,notnull,oids,purge,rename,replace,retrieve,returns,rule,recipe,setof,stdin,stdout,store,vacuum,verbose,version";
/*      */   }
/*      */   
/*      */   public String getNumericFunctions() throws SQLException {
/*  420 */     return "abs,acos,asin,atan,atan2,ceiling,cos,cot,degrees,exp,floor,log,log10,mod,pi,power,radians,round,sign,sin,sqrt,tan,truncate";
/*      */   }
/*      */   
/*      */   public String getStringFunctions() throws SQLException {
/*  437 */     String funcs = "ascii,char,concat,lcase,left,length,ltrim,repeat,rtrim,space,substring,ucase";
/*  453 */     if (this.connection.haveMinimumServerVersion("7.3"))
/*  454 */       funcs = funcs + ",replace"; 
/*  457 */     return funcs;
/*      */   }
/*      */   
/*      */   public String getSystemFunctions() throws SQLException {
/*  462 */     if (this.connection.haveMinimumServerVersion("7.3"))
/*  463 */       return "database,ifnull,user"; 
/*  466 */     return "ifnull,user";
/*      */   }
/*      */   
/*      */   public String getTimeDateFunctions() throws SQLException {
/*  473 */     String timeDateFuncs = "curdate,curtime,dayname,dayofmonth,dayofweek,dayofyear,hour,minute,month,monthname,now,quarter,second,week,year";
/*  482 */     if (this.connection.haveMinimumServerVersion("8.0"))
/*  483 */       timeDateFuncs = timeDateFuncs + ",timestampadd"; 
/*  488 */     return timeDateFuncs;
/*      */   }
/*      */   
/*      */   public String getSearchStringEscape() throws SQLException {
/*  511 */     return "\\";
/*      */   }
/*      */   
/*      */   public String getExtraNameCharacters() throws SQLException {
/*  531 */     return "";
/*      */   }
/*      */   
/*      */   public boolean supportsAlterTableWithAddColumn() throws SQLException {
/*  543 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsAlterTableWithDropColumn() throws SQLException {
/*  554 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsColumnAliasing() throws SQLException {
/*  577 */     return true;
/*      */   }
/*      */   
/*      */   public boolean nullPlusNonNullIsNull() throws SQLException {
/*  589 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsConvert() throws SQLException {
/*  594 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsConvert(int fromType, int toType) throws SQLException {
/*  599 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsTableCorrelationNames() throws SQLException {
/*  611 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsDifferentTableCorrelationNames() throws SQLException {
/*  623 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsExpressionsInOrderBy() throws SQLException {
/*  636 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsOrderByUnrelated() throws SQLException {
/*  647 */     return this.connection.haveMinimumServerVersion("6.4");
/*      */   }
/*      */   
/*      */   public boolean supportsGroupBy() throws SQLException {
/*  659 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsGroupByUnrelated() throws SQLException {
/*  670 */     return this.connection.haveMinimumServerVersion("6.4");
/*      */   }
/*      */   
/*      */   public boolean supportsGroupByBeyondSelect() throws SQLException {
/*  685 */     return this.connection.haveMinimumServerVersion("6.4");
/*      */   }
/*      */   
/*      */   public boolean supportsLikeEscapeClause() throws SQLException {
/*  697 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public boolean supportsMultipleResultSets() throws SQLException {
/*  708 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsMultipleTransactions() throws SQLException {
/*  721 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsNonNullableColumns() throws SQLException {
/*  736 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsMinimumSQLGrammar() throws SQLException {
/*  753 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsCoreSQLGrammar() throws SQLException {
/*  765 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsExtendedSQLGrammar() throws SQLException {
/*  778 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsANSI92EntryLevelSQL() throws SQLException {
/*  795 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsANSI92IntermediateSQL() throws SQLException {
/*  807 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsANSI92FullSQL() throws SQLException {
/*  818 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsIntegrityEnhancementFacility() throws SQLException {
/*  830 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsOuterJoins() throws SQLException {
/*  841 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public boolean supportsFullOuterJoins() throws SQLException {
/*  852 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public boolean supportsLimitedOuterJoins() throws SQLException {
/*  863 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public String getSchemaTerm() throws SQLException {
/*  876 */     return "schema";
/*      */   }
/*      */   
/*      */   public String getProcedureTerm() throws SQLException {
/*  888 */     return "function";
/*      */   }
/*      */   
/*      */   public String getCatalogTerm() throws SQLException {
/*  899 */     return "database";
/*      */   }
/*      */   
/*      */   public boolean isCatalogAtStart() throws SQLException {
/*  911 */     return true;
/*      */   }
/*      */   
/*      */   public String getCatalogSeparator() throws SQLException {
/*  922 */     return ".";
/*      */   }
/*      */   
/*      */   public boolean supportsSchemasInDataManipulation() throws SQLException {
/*  933 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsSchemasInProcedureCalls() throws SQLException {
/*  944 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsSchemasInTableDefinitions() throws SQLException {
/*  955 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsSchemasInIndexDefinitions() throws SQLException {
/*  966 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
/*  977 */     return this.connection.haveMinimumServerVersion("7.3");
/*      */   }
/*      */   
/*      */   public boolean supportsCatalogsInDataManipulation() throws SQLException {
/*  988 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsCatalogsInProcedureCalls() throws SQLException {
/*  999 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsCatalogsInTableDefinitions() throws SQLException {
/* 1010 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
/* 1021 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
/* 1032 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsPositionedDelete() throws SQLException {
/* 1044 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsPositionedUpdate() throws SQLException {
/* 1055 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsSelectForUpdate() throws SQLException {
/* 1066 */     return this.connection.haveMinimumServerVersion("6.5");
/*      */   }
/*      */   
/*      */   public boolean supportsStoredProcedures() throws SQLException {
/* 1078 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsSubqueriesInComparisons() throws SQLException {
/* 1090 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsSubqueriesInExists() throws SQLException {
/* 1102 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsSubqueriesInIns() throws SQLException {
/* 1114 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsSubqueriesInQuantifieds() throws SQLException {
/* 1129 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsCorrelatedSubqueries() throws SQLException {
/* 1143 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public boolean supportsUnion() throws SQLException {
/* 1154 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsUnionAll() throws SQLException {
/* 1165 */     return this.connection.haveMinimumServerVersion("7.1");
/*      */   }
/*      */   
/*      */   public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
/* 1176 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
/* 1187 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
/* 1201 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
/* 1215 */     return true;
/*      */   }
/*      */   
/*      */   public int getMaxBinaryLiteralLength() throws SQLException {
/* 1226 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxCharLiteralLength() throws SQLException {
/* 1238 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxColumnNameLength() throws SQLException {
/* 1249 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxColumnsInGroupBy() throws SQLException {
/* 1260 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxColumnsInIndex() throws SQLException {
/* 1271 */     return getMaxIndexKeys();
/*      */   }
/*      */   
/*      */   public int getMaxColumnsInOrderBy() throws SQLException {
/* 1282 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxColumnsInSelect() throws SQLException {
/* 1293 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxColumnsInTable() throws SQLException {
/* 1310 */     return 1600;
/*      */   }
/*      */   
/*      */   public int getMaxConnections() throws SQLException {
/* 1327 */     return 8192;
/*      */   }
/*      */   
/*      */   public int getMaxCursorNameLength() throws SQLException {
/* 1338 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxIndexLength() throws SQLException {
/* 1352 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxSchemaNameLength() throws SQLException {
/* 1357 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxProcedureNameLength() throws SQLException {
/* 1368 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxCatalogNameLength() throws SQLException {
/* 1373 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxRowSize() throws SQLException {
/* 1384 */     if (this.connection.haveMinimumServerVersion("7.1"))
/* 1385 */       return 1073741824; 
/* 1387 */     return 8192;
/*      */   }
/*      */   
/*      */   public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
/* 1399 */     return false;
/*      */   }
/*      */   
/*      */   public int getMaxStatementLength() throws SQLException {
/* 1410 */     if (this.connection.haveMinimumServerVersion("7.0"))
/* 1411 */       return 0; 
/* 1413 */     return 16384;
/*      */   }
/*      */   
/*      */   public int getMaxStatements() throws SQLException {
/* 1425 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxTableNameLength() throws SQLException {
/* 1436 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getMaxTablesInSelect() throws SQLException {
/* 1448 */     return 0;
/*      */   }
/*      */   
/*      */   public int getMaxUserNameLength() throws SQLException {
/* 1459 */     return getMaxNameLength();
/*      */   }
/*      */   
/*      */   public int getDefaultTransactionIsolation() throws SQLException {
/* 1472 */     return 2;
/*      */   }
/*      */   
/*      */   public boolean supportsTransactions() throws SQLException {
/* 1485 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
/* 1501 */     if (level == 8 || level == 2)
/* 1503 */       return true; 
/* 1504 */     if (this.connection.haveMinimumServerVersion("8.0") && (level == 1 || level == 4))
/* 1505 */       return true; 
/* 1507 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
/* 1519 */     return true;
/*      */   }
/*      */   
/*      */   public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
/* 1531 */     return false;
/*      */   }
/*      */   
/*      */   public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
/* 1555 */     return false;
/*      */   }
/*      */   
/*      */   public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
/* 1566 */     return false;
/*      */   }
/*      */   
/*      */   protected String escapeQuotes(String s) throws SQLException {
/* 1575 */     StringBuffer sb = new StringBuffer();
/* 1576 */     if (!this.connection.getStandardConformingStrings() && this.connection.haveMinimumServerVersion("8.1"))
/* 1577 */       sb.append("E"); 
/* 1579 */     sb.append("'");
/* 1580 */     sb.append(this.connection.escapeString(s));
/* 1581 */     sb.append("'");
/* 1582 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
/* 1619 */     return getProcedures(2, catalog, schemaPattern, procedureNamePattern);
/*      */   }
/*      */   
/*      */   protected ResultSet getProcedures(int jdbcVersion, String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
/*      */     String sql;
/* 1625 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 1627 */       sql = "SELECT NULL AS PROCEDURE_CAT, n.nspname AS PROCEDURE_SCHEM, p.proname AS PROCEDURE_NAME, NULL, NULL, NULL, d.description AS REMARKS, 2 AS PROCEDURE_TYPE ";
/* 1628 */       if (jdbcVersion >= 4)
/* 1629 */         sql = sql + ", p.proname || '_' || p.oid AS SPECIFIC_NAME "; 
/* 1631 */       sql = sql + " FROM pg_catalog.pg_namespace n, pg_catalog.pg_proc p  LEFT JOIN pg_catalog.pg_description d ON (p.oid=d.objoid)  LEFT JOIN pg_catalog.pg_class c ON (d.classoid=c.oid AND c.relname='pg_proc')  LEFT JOIN pg_catalog.pg_namespace pn ON (c.relnamespace=pn.oid AND pn.nspname='pg_catalog')  WHERE p.pronamespace=n.oid ";
/* 1636 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 1638 */         sql = sql + " AND n.nspname LIKE " + escapeQuotes(schemaPattern); 
/* 1640 */       if (procedureNamePattern != null)
/* 1642 */         sql = sql + " AND p.proname LIKE " + escapeQuotes(procedureNamePattern); 
/* 1644 */       sql = sql + " ORDER BY PROCEDURE_SCHEM, PROCEDURE_NAME, p.oid::text ";
/* 1646 */     } else if (this.connection.haveMinimumServerVersion("7.1")) {
/* 1648 */       sql = "SELECT NULL AS PROCEDURE_CAT, NULL AS PROCEDURE_SCHEM, p.proname AS PROCEDURE_NAME, NULL, NULL, NULL, d.description AS REMARKS, 2 AS PROCEDURE_TYPE ";
/* 1649 */       if (jdbcVersion >= 4)
/* 1650 */         sql = sql + ", p.proname || '_' || p.oid AS SPECIFIC_NAME "; 
/* 1652 */       sql = sql + " FROM pg_proc p  LEFT JOIN pg_description d ON (p.oid=d.objoid) ";
/* 1654 */       if (this.connection.haveMinimumServerVersion("7.2"))
/* 1656 */         sql = sql + " LEFT JOIN pg_class c ON (d.classoid=c.oid AND c.relname='pg_proc') "; 
/* 1658 */       if (procedureNamePattern != null)
/* 1660 */         sql = sql + " WHERE p.proname LIKE " + escapeQuotes(procedureNamePattern); 
/* 1662 */       sql = sql + " ORDER BY PROCEDURE_NAME, p.oid::text ";
/*      */     } else {
/* 1666 */       sql = "SELECT NULL AS PROCEDURE_CAT, NULL AS PROCEDURE_SCHEM, p.proname AS PROCEDURE_NAME, NULL, NULL, NULL, NULL AS REMARKS, 2 AS PROCEDURE_TYPE ";
/* 1667 */       if (jdbcVersion >= 4)
/* 1668 */         sql = sql + ", p.proname || '_' || p.oid AS SPECIFIC_NAME "; 
/* 1670 */       sql = sql + " FROM pg_proc p ";
/* 1671 */       if (procedureNamePattern != null)
/* 1673 */         sql = sql + " WHERE p.proname LIKE " + escapeQuotes(procedureNamePattern); 
/* 1675 */       sql = sql + " ORDER BY PROCEDURE_NAME, p.oid::text ";
/*      */     } 
/* 1677 */     return createMetaDataStatement().executeQuery(sql);
/*      */   }
/*      */   
/*      */   public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
/* 1728 */     return getProcedureColumns(2, catalog, schemaPattern, procedureNamePattern, columnNamePattern);
/*      */   }
/*      */   
/*      */   protected ResultSet getProcedureColumns(int jdbcVersion, String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
/*      */     String sql;
/* 1733 */     int columns = 13;
/* 1734 */     if (jdbcVersion >= 4)
/* 1735 */       columns += 7; 
/* 1737 */     Field[] f = new Field[columns];
/* 1738 */     Vector<byte[][]> v = new Vector();
/* 1740 */     f[0] = new Field("PROCEDURE_CAT", 1043);
/* 1741 */     f[1] = new Field("PROCEDURE_SCHEM", 1043);
/* 1742 */     f[2] = new Field("PROCEDURE_NAME", 1043);
/* 1743 */     f[3] = new Field("COLUMN_NAME", 1043);
/* 1744 */     f[4] = new Field("COLUMN_TYPE", 21);
/* 1745 */     f[5] = new Field("DATA_TYPE", 21);
/* 1746 */     f[6] = new Field("TYPE_NAME", 1043);
/* 1747 */     f[7] = new Field("PRECISION", 23);
/* 1748 */     f[8] = new Field("LENGTH", 23);
/* 1749 */     f[9] = new Field("SCALE", 21);
/* 1750 */     f[10] = new Field("RADIX", 21);
/* 1751 */     f[11] = new Field("NULLABLE", 21);
/* 1752 */     f[12] = new Field("REMARKS", 1043);
/* 1753 */     if (jdbcVersion >= 4) {
/* 1754 */       f[13] = new Field("COLUMN_DEF", 1043);
/* 1755 */       f[14] = new Field("SQL_DATA_TYPE", 23);
/* 1756 */       f[15] = new Field("SQL_DATETIME_SUB", 23);
/* 1757 */       f[16] = new Field("CHAR_OCTECT_LENGTH", 23);
/* 1758 */       f[17] = new Field("ORDINAL_POSITION", 23);
/* 1759 */       f[18] = new Field("IS_NULLABLE", 1043);
/* 1760 */       f[19] = new Field("SPECIFIC_NAME", 1043);
/*      */     } 
/* 1764 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 1766 */       sql = "SELECT n.nspname,p.proname,p.prorettype,p.proargtypes, t.typtype,t.typrelid ";
/* 1768 */       if (this.connection.haveMinimumServerVersion("8.1")) {
/* 1769 */         sql = sql + ", p.proargnames, p.proargmodes, p.proallargtypes  ";
/* 1770 */       } else if (this.connection.haveMinimumServerVersion("8.0")) {
/* 1771 */         sql = sql + ", p.proargnames, NULL AS proargmodes, NULL AS proallargtypes ";
/*      */       } else {
/* 1773 */         sql = sql + ", NULL AS proargnames, NULL AS proargmodes, NULL AS proallargtypes ";
/*      */       } 
/* 1774 */       sql = sql + ", p.oid  FROM pg_catalog.pg_proc p, pg_catalog.pg_namespace n, pg_catalog.pg_type t  WHERE p.pronamespace=n.oid AND p.prorettype=t.oid ";
/* 1777 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 1779 */         sql = sql + " AND n.nspname LIKE " + escapeQuotes(schemaPattern); 
/* 1781 */       if (procedureNamePattern != null)
/* 1783 */         sql = sql + " AND p.proname LIKE " + escapeQuotes(procedureNamePattern); 
/* 1785 */       sql = sql + " ORDER BY n.nspname, p.proname, p.oid::text ";
/*      */     } else {
/* 1789 */       sql = "SELECT NULL AS nspname,p.proname,p.prorettype,p.proargtypes,t.typtype,t.typrelid, NULL AS proargnames, NULL AS proargmodes, NULL AS proallargtypes, p.oid  FROM pg_proc p,pg_type t  WHERE p.prorettype=t.oid ";
/* 1792 */       if (procedureNamePattern != null)
/* 1794 */         sql = sql + " AND p.proname LIKE " + escapeQuotes(procedureNamePattern); 
/* 1796 */       sql = sql + " ORDER BY p.proname, p.oid::text ";
/*      */     } 
/* 1799 */     byte[] isnullableUnknown = new byte[0];
/* 1801 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 1802 */     while (rs.next()) {
/* 1804 */       byte[] schema = rs.getBytes("nspname");
/* 1805 */       byte[] procedureName = rs.getBytes("proname");
/* 1806 */       byte[] specificName = this.connection.encodeString(rs.getString("proname") + "_" + rs.getString("oid"));
/* 1807 */       int returnType = (int)rs.getLong("prorettype");
/* 1808 */       String returnTypeType = rs.getString("typtype");
/* 1809 */       int returnTypeRelid = (int)rs.getLong("typrelid");
/* 1811 */       String strArgTypes = rs.getString("proargtypes");
/* 1812 */       StringTokenizer st = new StringTokenizer(strArgTypes);
/* 1813 */       Vector<Long> argTypes = new Vector();
/* 1814 */       while (st.hasMoreTokens())
/* 1816 */         argTypes.addElement(new Long(st.nextToken())); 
/* 1819 */       String[] argNames = null;
/* 1820 */       Array argNamesArray = rs.getArray("proargnames");
/* 1821 */       if (argNamesArray != null)
/* 1822 */         argNames = (String[])argNamesArray.getArray(); 
/* 1824 */       String[] argModes = null;
/* 1825 */       Array argModesArray = rs.getArray("proargmodes");
/* 1826 */       if (argModesArray != null)
/* 1827 */         argModes = (String[])argModesArray.getArray(); 
/* 1829 */       int numArgs = argTypes.size();
/* 1831 */       Long[] allArgTypes = null;
/* 1832 */       Array allArgTypesArray = rs.getArray("proallargtypes");
/* 1833 */       if (allArgTypesArray != null) {
/* 1838 */         if (this.connection.haveMinimumCompatibleVersion("8.3")) {
/* 1839 */           allArgTypes = (Long[])allArgTypesArray.getArray();
/*      */         } else {
/* 1841 */           long[] tempAllArgTypes = (long[])allArgTypesArray.getArray();
/* 1842 */           allArgTypes = new Long[tempAllArgTypes.length];
/* 1843 */           for (int j = 0; j < tempAllArgTypes.length; j++)
/* 1844 */             allArgTypes[j] = new Long(tempAllArgTypes[j]); 
/*      */         } 
/* 1847 */         numArgs = allArgTypes.length;
/*      */       } 
/* 1851 */       if (returnTypeType.equals("b") || returnTypeType.equals("d") || (returnTypeType.equals("p") && argModesArray == null)) {
/* 1853 */         byte[][] tuple = new byte[columns][];
/* 1854 */         tuple[0] = null;
/* 1855 */         tuple[1] = schema;
/* 1856 */         tuple[2] = procedureName;
/* 1857 */         tuple[3] = this.connection.encodeString("returnValue");
/* 1858 */         tuple[4] = this.connection.encodeString(Integer.toString(5));
/* 1859 */         tuple[5] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(returnType)));
/* 1860 */         tuple[6] = this.connection.encodeString(this.connection.getTypeInfo().getPGType(returnType));
/* 1861 */         tuple[7] = null;
/* 1862 */         tuple[8] = null;
/* 1863 */         tuple[9] = null;
/* 1864 */         tuple[10] = null;
/* 1865 */         tuple[11] = this.connection.encodeString(Integer.toString(2));
/* 1866 */         tuple[12] = null;
/* 1867 */         if (jdbcVersion >= 4) {
/* 1868 */           tuple[17] = this.connection.encodeString(Integer.toString(0));
/* 1869 */           tuple[18] = isnullableUnknown;
/* 1870 */           tuple[19] = specificName;
/*      */         } 
/* 1872 */         v.addElement(tuple);
/*      */       } 
/* 1876 */       for (int i = 0; i < numArgs; i++) {
/*      */         int argOid;
/* 1878 */         byte[][] tuple = new byte[columns][];
/* 1879 */         tuple[0] = null;
/* 1880 */         tuple[1] = schema;
/* 1881 */         tuple[2] = procedureName;
/* 1883 */         if (argNames != null) {
/* 1884 */           tuple[3] = this.connection.encodeString(argNames[i]);
/*      */         } else {
/* 1886 */           tuple[3] = this.connection.encodeString("$" + (i + 1));
/*      */         } 
/* 1888 */         int columnMode = 1;
/* 1889 */         if (argModes != null && argModes[i].equals("o")) {
/* 1890 */           columnMode = 4;
/* 1891 */         } else if (argModes != null && argModes[i].equals("b")) {
/* 1892 */           columnMode = 2;
/*      */         } 
/* 1894 */         tuple[4] = this.connection.encodeString(Integer.toString(columnMode));
/* 1897 */         if (allArgTypes != null) {
/* 1898 */           argOid = allArgTypes[i].intValue();
/*      */         } else {
/* 1900 */           argOid = ((Long)argTypes.elementAt(i)).intValue();
/*      */         } 
/* 1902 */         tuple[5] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(argOid)));
/* 1903 */         tuple[6] = this.connection.encodeString(this.connection.getTypeInfo().getPGType(argOid));
/* 1904 */         tuple[7] = null;
/* 1905 */         tuple[8] = null;
/* 1906 */         tuple[9] = null;
/* 1907 */         tuple[10] = null;
/* 1908 */         tuple[11] = this.connection.encodeString(Integer.toString(2));
/* 1909 */         tuple[12] = null;
/* 1910 */         if (jdbcVersion >= 4) {
/* 1911 */           tuple[17] = this.connection.encodeString(Integer.toString(i + 1));
/* 1912 */           tuple[18] = isnullableUnknown;
/* 1913 */           tuple[19] = specificName;
/*      */         } 
/* 1915 */         v.addElement(tuple);
/*      */       } 
/* 1919 */       if (returnTypeType.equals("c") || (returnTypeType.equals("p") && argModesArray != null)) {
/* 1921 */         String columnsql = "SELECT a.attname,a.atttypid FROM ";
/* 1922 */         if (this.connection.haveMinimumServerVersion("7.3"))
/* 1923 */           columnsql = columnsql + "pg_catalog."; 
/* 1925 */         columnsql = columnsql + "pg_attribute a WHERE a.attrelid = " + returnTypeRelid + " AND a.attnum > 0 ORDER BY a.attnum ";
/* 1926 */         ResultSet columnrs = this.connection.createStatement().executeQuery(columnsql);
/* 1927 */         while (columnrs.next()) {
/* 1929 */           int columnTypeOid = (int)columnrs.getLong("atttypid");
/* 1930 */           byte[][] tuple = new byte[columns][];
/* 1931 */           tuple[0] = null;
/* 1932 */           tuple[1] = schema;
/* 1933 */           tuple[2] = procedureName;
/* 1934 */           tuple[3] = columnrs.getBytes("attname");
/* 1935 */           tuple[4] = this.connection.encodeString(Integer.toString(3));
/* 1936 */           tuple[5] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(columnTypeOid)));
/* 1937 */           tuple[6] = this.connection.encodeString(this.connection.getTypeInfo().getPGType(columnTypeOid));
/* 1938 */           tuple[7] = null;
/* 1939 */           tuple[8] = null;
/* 1940 */           tuple[9] = null;
/* 1941 */           tuple[10] = null;
/* 1942 */           tuple[11] = this.connection.encodeString(Integer.toString(2));
/* 1943 */           tuple[12] = null;
/* 1944 */           if (jdbcVersion >= 4) {
/* 1945 */             tuple[17] = this.connection.encodeString(Integer.toString(0));
/* 1946 */             tuple[18] = isnullableUnknown;
/* 1947 */             tuple[19] = specificName;
/*      */           } 
/* 1949 */           v.addElement(tuple);
/*      */         } 
/* 1951 */         columnrs.close();
/*      */       } 
/*      */     } 
/* 1954 */     rs.close();
/* 1956 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
/*      */     String select, orderby, useSchemas;
/* 1999 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 2001 */       useSchemas = "SCHEMAS";
/* 2002 */       select = "SELECT NULL AS TABLE_CAT, n.nspname AS TABLE_SCHEM, c.relname AS TABLE_NAME,  CASE n.nspname ~ '^pg_' OR n.nspname = 'information_schema'  WHEN true THEN CASE  WHEN n.nspname = 'pg_catalog' OR n.nspname = 'information_schema' THEN CASE c.relkind   WHEN 'r' THEN 'SYSTEM TABLE'   WHEN 'v' THEN 'SYSTEM VIEW'   WHEN 'i' THEN 'SYSTEM INDEX'   ELSE NULL   END  WHEN n.nspname = 'pg_toast' THEN CASE c.relkind   WHEN 'r' THEN 'SYSTEM TOAST TABLE'   WHEN 'i' THEN 'SYSTEM TOAST INDEX'   ELSE NULL   END  ELSE CASE c.relkind   WHEN 'r' THEN 'TEMPORARY TABLE'   WHEN 'i' THEN 'TEMPORARY INDEX'   WHEN 'S' THEN 'TEMPORARY SEQUENCE'   WHEN 'v' THEN 'TEMPORARY VIEW'   ELSE NULL   END  END  WHEN false THEN CASE c.relkind  WHEN 'r' THEN 'TABLE'  WHEN 'i' THEN 'INDEX'  WHEN 'S' THEN 'SEQUENCE'  WHEN 'v' THEN 'VIEW'  WHEN 'c' THEN 'TYPE'  WHEN 'f' THEN 'FOREIGN TABLE'  ELSE NULL  END  ELSE NULL  END  AS TABLE_TYPE, d.description AS REMARKS  FROM pg_catalog.pg_namespace n, pg_catalog.pg_class c  LEFT JOIN pg_catalog.pg_description d ON (c.oid = d.objoid AND d.objsubid = 0)  LEFT JOIN pg_catalog.pg_class dc ON (d.classoid=dc.oid AND dc.relname='pg_class')  LEFT JOIN pg_catalog.pg_namespace dn ON (dn.oid=dc.relnamespace AND dn.nspname='pg_catalog')  WHERE c.relnamespace = n.oid ";
/* 2041 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 2043 */         select = select + " AND n.nspname LIKE " + escapeQuotes(schemaPattern); 
/* 2045 */       orderby = " ORDER BY TABLE_TYPE,TABLE_SCHEM,TABLE_NAME ";
/*      */     } else {
/* 2049 */       useSchemas = "NOSCHEMAS";
/* 2050 */       String tableType = " CASE c.relname ~ '^pg_'  WHEN true THEN CASE c.relname ~ '^pg_toast_'  WHEN true THEN CASE c.relkind   WHEN 'r' THEN 'SYSTEM TOAST TABLE'   WHEN 'i' THEN 'SYSTEM TOAST INDEX'   ELSE NULL   END  WHEN false THEN CASE c.relname ~ '^pg_temp_'   WHEN true THEN CASE c.relkind    WHEN 'r' THEN 'TEMPORARY TABLE'    WHEN 'i' THEN 'TEMPORARY INDEX'    WHEN 'S' THEN 'TEMPORARY SEQUENCE'    WHEN 'v' THEN 'TEMPORARY VIEW'    ELSE NULL    END   WHEN false THEN CASE c.relkind    WHEN 'r' THEN 'SYSTEM TABLE'    WHEN 'v' THEN 'SYSTEM VIEW'    WHEN 'i' THEN 'SYSTEM INDEX'    ELSE NULL    END   ELSE NULL   END  ELSE NULL  END  WHEN false THEN CASE c.relkind  WHEN 'r' THEN 'TABLE'  WHEN 'i' THEN 'INDEX'  WHEN 'S' THEN 'SEQUENCE'  WHEN 'v' THEN 'VIEW'  WHEN 'c' THEN 'TYPE'  ELSE NULL  END  ELSE NULL  END ";
/* 2086 */       orderby = " ORDER BY TABLE_TYPE,TABLE_NAME ";
/* 2087 */       if (this.connection.haveMinimumServerVersion("7.2")) {
/* 2089 */         select = "SELECT NULL AS TABLE_CAT, NULL AS TABLE_SCHEM, c.relname AS TABLE_NAME, " + tableType + " AS TABLE_TYPE, d.description AS REMARKS " + " FROM pg_class c " + " LEFT JOIN pg_description d ON (c.oid=d.objoid AND d.objsubid = 0) " + " LEFT JOIN pg_class dc ON (d.classoid = dc.oid AND dc.relname='pg_class') " + " WHERE true ";
/* 2095 */       } else if (this.connection.haveMinimumServerVersion("7.1")) {
/* 2097 */         select = "SELECT NULL AS TABLE_CAT, NULL AS TABLE_SCHEM, c.relname AS TABLE_NAME, " + tableType + " AS TABLE_TYPE, d.description AS REMARKS " + " FROM pg_class c " + " LEFT JOIN pg_description d ON (c.oid=d.objoid) " + " WHERE true ";
/*      */       } else {
/* 2104 */         select = "SELECT NULL AS TABLE_CAT, NULL AS TABLE_SCHEM, c.relname AS TABLE_NAME, " + tableType + " AS TABLE_TYPE, NULL AS REMARKS " + " FROM pg_class c " + " WHERE true ";
/*      */       } 
/*      */     } 
/* 2110 */     if (tableNamePattern != null && !"".equals(tableNamePattern))
/* 2112 */       select = select + " AND c.relname LIKE " + escapeQuotes(tableNamePattern); 
/* 2114 */     if (types != null) {
/* 2115 */       select = select + " AND (false ";
/* 2116 */       for (int i = 0; i < types.length; i++) {
/* 2118 */         Hashtable clauses = (Hashtable)tableTypeClauses.get(types[i]);
/* 2119 */         if (clauses != null) {
/* 2121 */           String clause = (String)clauses.get(useSchemas);
/* 2122 */           select = select + " OR ( " + clause + " ) ";
/*      */         } 
/*      */       } 
/* 2125 */       select = select + ") ";
/*      */     } 
/* 2127 */     String sql = select + orderby;
/* 2129 */     return createMetaDataStatement().executeQuery(sql);
/*      */   }
/*      */   
/* 2134 */   private static final Hashtable tableTypeClauses = new Hashtable<Object, Object>();
/*      */   
/*      */   static {
/* 2135 */     Hashtable<Object, Object> ht = new Hashtable<Object, Object>();
/* 2136 */     tableTypeClauses.put("TABLE", ht);
/* 2137 */     ht.put("SCHEMAS", "c.relkind = 'r' AND n.nspname !~ '^pg_' AND n.nspname <> 'information_schema'");
/* 2138 */     ht.put("NOSCHEMAS", "c.relkind = 'r' AND c.relname !~ '^pg_'");
/* 2139 */     ht = new Hashtable<Object, Object>();
/* 2140 */     tableTypeClauses.put("VIEW", ht);
/* 2141 */     ht.put("SCHEMAS", "c.relkind = 'v' AND n.nspname <> 'pg_catalog' AND n.nspname <> 'information_schema'");
/* 2142 */     ht.put("NOSCHEMAS", "c.relkind = 'v' AND c.relname !~ '^pg_'");
/* 2143 */     ht = new Hashtable<Object, Object>();
/* 2144 */     tableTypeClauses.put("INDEX", ht);
/* 2145 */     ht.put("SCHEMAS", "c.relkind = 'i' AND n.nspname !~ '^pg_' AND n.nspname <> 'information_schema'");
/* 2146 */     ht.put("NOSCHEMAS", "c.relkind = 'i' AND c.relname !~ '^pg_'");
/* 2147 */     ht = new Hashtable<Object, Object>();
/* 2148 */     tableTypeClauses.put("SEQUENCE", ht);
/* 2149 */     ht.put("SCHEMAS", "c.relkind = 'S'");
/* 2150 */     ht.put("NOSCHEMAS", "c.relkind = 'S'");
/* 2151 */     ht = new Hashtable<Object, Object>();
/* 2152 */     tableTypeClauses.put("TYPE", ht);
/* 2153 */     ht.put("SCHEMAS", "c.relkind = 'c' AND n.nspname !~ '^pg_' AND n.nspname <> 'information_schema'");
/* 2154 */     ht.put("NOSCHEMAS", "c.relkind = 'c' AND c.relname !~ '^pg_'");
/* 2155 */     ht = new Hashtable<Object, Object>();
/* 2156 */     tableTypeClauses.put("SYSTEM TABLE", ht);
/* 2157 */     ht.put("SCHEMAS", "c.relkind = 'r' AND (n.nspname = 'pg_catalog' OR n.nspname = 'information_schema')");
/* 2158 */     ht.put("NOSCHEMAS", "c.relkind = 'r' AND c.relname ~ '^pg_' AND c.relname !~ '^pg_toast_' AND c.relname !~ '^pg_temp_'");
/* 2159 */     ht = new Hashtable<Object, Object>();
/* 2160 */     tableTypeClauses.put("SYSTEM TOAST TABLE", ht);
/* 2161 */     ht.put("SCHEMAS", "c.relkind = 'r' AND n.nspname = 'pg_toast'");
/* 2162 */     ht.put("NOSCHEMAS", "c.relkind = 'r' AND c.relname ~ '^pg_toast_'");
/* 2163 */     ht = new Hashtable<Object, Object>();
/* 2164 */     tableTypeClauses.put("SYSTEM TOAST INDEX", ht);
/* 2165 */     ht.put("SCHEMAS", "c.relkind = 'i' AND n.nspname = 'pg_toast'");
/* 2166 */     ht.put("NOSCHEMAS", "c.relkind = 'i' AND c.relname ~ '^pg_toast_'");
/* 2167 */     ht = new Hashtable<Object, Object>();
/* 2168 */     tableTypeClauses.put("SYSTEM VIEW", ht);
/* 2169 */     ht.put("SCHEMAS", "c.relkind = 'v' AND (n.nspname = 'pg_catalog' OR n.nspname = 'information_schema') ");
/* 2170 */     ht.put("NOSCHEMAS", "c.relkind = 'v' AND c.relname ~ '^pg_'");
/* 2171 */     ht = new Hashtable<Object, Object>();
/* 2172 */     tableTypeClauses.put("SYSTEM INDEX", ht);
/* 2173 */     ht.put("SCHEMAS", "c.relkind = 'i' AND (n.nspname = 'pg_catalog' OR n.nspname = 'information_schema') ");
/* 2174 */     ht.put("NOSCHEMAS", "c.relkind = 'v' AND c.relname ~ '^pg_' AND c.relname !~ '^pg_toast_' AND c.relname !~ '^pg_temp_'");
/* 2175 */     ht = new Hashtable<Object, Object>();
/* 2176 */     tableTypeClauses.put("TEMPORARY TABLE", ht);
/* 2177 */     ht.put("SCHEMAS", "c.relkind = 'r' AND n.nspname ~ '^pg_temp_' ");
/* 2178 */     ht.put("NOSCHEMAS", "c.relkind = 'r' AND c.relname ~ '^pg_temp_' ");
/* 2179 */     ht = new Hashtable<Object, Object>();
/* 2180 */     tableTypeClauses.put("TEMPORARY INDEX", ht);
/* 2181 */     ht.put("SCHEMAS", "c.relkind = 'i' AND n.nspname ~ '^pg_temp_' ");
/* 2182 */     ht.put("NOSCHEMAS", "c.relkind = 'i' AND c.relname ~ '^pg_temp_' ");
/* 2183 */     ht = new Hashtable<Object, Object>();
/* 2184 */     tableTypeClauses.put("TEMPORARY VIEW", ht);
/* 2185 */     ht.put("SCHEMAS", "c.relkind = 'v' AND n.nspname ~ '^pg_temp_' ");
/* 2186 */     ht.put("NOSCHEMAS", "c.relkind = 'v' AND c.relname ~ '^pg_temp_' ");
/* 2187 */     ht = new Hashtable<Object, Object>();
/* 2188 */     tableTypeClauses.put("TEMPORARY SEQUENCE", ht);
/* 2189 */     ht.put("SCHEMAS", "c.relkind = 'S' AND n.nspname ~ '^pg_temp_' ");
/* 2190 */     ht.put("NOSCHEMAS", "c.relkind = 'S' AND c.relname ~ '^pg_temp_' ");
/* 2191 */     ht = new Hashtable<Object, Object>();
/* 2192 */     tableTypeClauses.put("FOREIGN TABLE", ht);
/* 2193 */     ht.put("SCHEMAS", "c.relkind = 'f'");
/* 2194 */     ht.put("NOSCHEMAS", "c.relkind = 'f'");
/*      */   }
/*      */   
/*      */   public ResultSet getSchemas() throws SQLException {
/* 2211 */     return getSchemas(2, null, null);
/*      */   }
/*      */   
/*      */   protected ResultSet getSchemas(int jdbcVersion, String catalog, String schemaPattern) throws SQLException {
/*      */     String sql;
/* 2218 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 2223 */       String tempSchema = "substring(textin(array_out(pg_catalog.current_schemas(true))) from '{(pg_temp_[0-9]+),')";
/* 2224 */       if (this.connection.haveMinimumServerVersion("7.4"))
/* 2225 */         tempSchema = "(pg_catalog.current_schemas(true))[1]"; 
/* 2227 */       sql = "SELECT nspname AS TABLE_SCHEM ";
/* 2228 */       if (jdbcVersion >= 3)
/* 2229 */         sql = sql + ", NULL AS TABLE_CATALOG "; 
/* 2230 */       sql = sql + " FROM pg_catalog.pg_namespace WHERE nspname <> 'pg_toast' AND (nspname !~ '^pg_temp_' OR nspname = " + tempSchema + ") AND (nspname !~ '^pg_toast_temp_' OR nspname = replace(" + tempSchema + ", 'pg_temp_', 'pg_toast_temp_')) ";
/* 2231 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 2233 */         sql = sql + " AND nspname LIKE " + escapeQuotes(schemaPattern); 
/* 2235 */       sql = sql + " ORDER BY TABLE_SCHEM";
/*      */     } else {
/* 2239 */       sql = "SELECT ''::text AS TABLE_SCHEM ";
/* 2240 */       if (jdbcVersion >= 3)
/* 2241 */         sql = sql + ", NULL AS TABLE_CATALOG "; 
/* 2243 */       if (schemaPattern != null)
/* 2245 */         sql = sql + " WHERE ''::text LIKE " + escapeQuotes(schemaPattern); 
/*      */     } 
/* 2248 */     return createMetaDataStatement().executeQuery(sql);
/*      */   }
/*      */   
/*      */   public ResultSet getCatalogs() throws SQLException {
/* 2269 */     Field[] f = new Field[1];
/* 2270 */     Vector<byte[][]> v = new Vector();
/* 2271 */     f[0] = new Field("TABLE_CAT", 1043);
/* 2272 */     byte[][] tuple = new byte[1][];
/* 2273 */     tuple[0] = this.connection.encodeString(this.connection.getCatalog());
/* 2274 */     v.addElement(tuple);
/* 2276 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getTableTypes() throws SQLException {
/* 2295 */     String[] types = new String[tableTypeClauses.size()];
/* 2296 */     Enumeration<String> e = tableTypeClauses.keys();
/* 2297 */     int i = 0;
/* 2298 */     while (e.hasMoreElements())
/* 2300 */       types[i++] = e.nextElement(); 
/* 2302 */     sortStringArray(types);
/* 2304 */     Field[] f = new Field[1];
/* 2305 */     Vector<byte[][]> v = new Vector();
/* 2306 */     f[0] = new Field("TABLE_TYPE", 1043);
/* 2307 */     for (i = 0; i < types.length; i++) {
/* 2309 */       byte[][] tuple = new byte[1][];
/* 2310 */       tuple[0] = this.connection.encodeString(types[i]);
/* 2311 */       v.addElement(tuple);
/*      */     } 
/* 2314 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   protected ResultSet getColumns(int jdbcVersion, String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/*      */     int numberOfFields;
/* 2320 */     if (jdbcVersion >= 4) {
/* 2321 */       numberOfFields = 23;
/* 2322 */     } else if (jdbcVersion >= 3) {
/* 2323 */       numberOfFields = 22;
/*      */     } else {
/* 2325 */       numberOfFields = 18;
/*      */     } 
/* 2327 */     Vector<byte[][]> v = new Vector();
/* 2328 */     Field[] f = new Field[numberOfFields];
/* 2330 */     f[0] = new Field("TABLE_CAT", 1043);
/* 2331 */     f[1] = new Field("TABLE_SCHEM", 1043);
/* 2332 */     f[2] = new Field("TABLE_NAME", 1043);
/* 2333 */     f[3] = new Field("COLUMN_NAME", 1043);
/* 2334 */     f[4] = new Field("DATA_TYPE", 21);
/* 2335 */     f[5] = new Field("TYPE_NAME", 1043);
/* 2336 */     f[6] = new Field("COLUMN_SIZE", 23);
/* 2337 */     f[7] = new Field("BUFFER_LENGTH", 1043);
/* 2338 */     f[8] = new Field("DECIMAL_DIGITS", 23);
/* 2339 */     f[9] = new Field("NUM_PREC_RADIX", 23);
/* 2340 */     f[10] = new Field("NULLABLE", 23);
/* 2341 */     f[11] = new Field("REMARKS", 1043);
/* 2342 */     f[12] = new Field("COLUMN_DEF", 1043);
/* 2343 */     f[13] = new Field("SQL_DATA_TYPE", 23);
/* 2344 */     f[14] = new Field("SQL_DATETIME_SUB", 23);
/* 2345 */     f[15] = new Field("CHAR_OCTET_LENGTH", 1043);
/* 2346 */     f[16] = new Field("ORDINAL_POSITION", 23);
/* 2347 */     f[17] = new Field("IS_NULLABLE", 1043);
/* 2349 */     if (jdbcVersion >= 3) {
/* 2350 */       f[18] = new Field("SCOPE_CATLOG", 1043);
/* 2351 */       f[19] = new Field("SCOPE_SCHEMA", 1043);
/* 2352 */       f[20] = new Field("SCOPE_TABLE", 1043);
/* 2353 */       f[21] = new Field("SOURCE_DATA_TYPE", 21);
/*      */     } 
/* 2356 */     if (jdbcVersion >= 4)
/* 2357 */       f[22] = new Field("IS_AUTOINCREMENT", 1043); 
/* 2361 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 2371 */       if (this.connection.haveMinimumServerVersion("8.4")) {
/* 2372 */         sql = "SELECT * FROM (";
/*      */       } else {
/* 2374 */         sql = "";
/*      */       } 
/* 2376 */       sql = sql + "SELECT n.nspname,c.relname,a.attname,a.atttypid,a.attnotnull OR (t.typtype = 'd' AND t.typnotnull) AS attnotnull,a.atttypmod,a.attlen,";
/* 2378 */       if (this.connection.haveMinimumServerVersion("8.4")) {
/* 2379 */         sql = sql + "row_number() OVER (PARTITION BY a.attrelid ORDER BY a.attnum) AS attnum, ";
/*      */       } else {
/* 2381 */         sql = sql + "a.attnum,";
/*      */       } 
/* 2383 */       sql = sql + "pg_catalog.pg_get_expr(def.adbin, def.adrelid) AS adsrc,dsc.description,t.typbasetype,t.typtype  FROM pg_catalog.pg_namespace n  JOIN pg_catalog.pg_class c ON (c.relnamespace = n.oid)  JOIN pg_catalog.pg_attribute a ON (a.attrelid=c.oid)  JOIN pg_catalog.pg_type t ON (a.atttypid = t.oid)  LEFT JOIN pg_catalog.pg_attrdef def ON (a.attrelid=def.adrelid AND a.attnum = def.adnum)  LEFT JOIN pg_catalog.pg_description dsc ON (c.oid=dsc.objoid AND a.attnum = dsc.objsubid)  LEFT JOIN pg_catalog.pg_class dc ON (dc.oid=dsc.classoid AND dc.relname='pg_class')  LEFT JOIN pg_catalog.pg_namespace dn ON (dc.relnamespace=dn.oid AND dn.nspname='pg_catalog')  WHERE a.attnum > 0 AND NOT a.attisdropped ";
/* 2394 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 2396 */         sql = sql + " AND n.nspname LIKE " + escapeQuotes(schemaPattern); 
/* 2399 */       if (tableNamePattern != null && !"".equals(tableNamePattern))
/* 2401 */         sql = sql + " AND c.relname LIKE " + escapeQuotes(tableNamePattern); 
/* 2404 */       if (this.connection.haveMinimumServerVersion("8.4"))
/* 2405 */         sql = sql + ") c WHERE true "; 
/* 2408 */     } else if (this.connection.haveMinimumServerVersion("7.2")) {
/* 2410 */       sql = "SELECT NULL::text AS nspname,c.relname,a.attname,a.atttypid,a.attnotnull,a.atttypmod,a.attlen,a.attnum,pg_get_expr(def.adbin,def.adrelid) AS adsrc,dsc.description,NULL::oid AS typbasetype,t.typtype  FROM pg_class c  JOIN pg_attribute a ON (a.attrelid=c.oid)  JOIN pg_type t ON (a.atttypid = t.oid)  LEFT JOIN pg_attrdef def ON (a.attrelid=def.adrelid AND a.attnum = def.adnum)  LEFT JOIN pg_description dsc ON (c.oid=dsc.objoid AND a.attnum = dsc.objsubid)  LEFT JOIN pg_class dc ON (dc.oid=dsc.classoid AND dc.relname='pg_class')  WHERE a.attnum > 0 ";
/* 2419 */     } else if (this.connection.haveMinimumServerVersion("7.1")) {
/* 2421 */       sql = "SELECT NULL::text AS nspname,c.relname,a.attname,a.atttypid,a.attnotnull,a.atttypmod,a.attlen,a.attnum,def.adsrc,dsc.description,NULL::oid AS typbasetype, 'b' AS typtype   FROM pg_class c  JOIN pg_attribute a ON (a.attrelid=c.oid)  LEFT JOIN pg_attrdef def ON (a.attrelid=def.adrelid AND a.attnum = def.adnum)  LEFT JOIN pg_description dsc ON (a.oid=dsc.objoid)  WHERE a.attnum > 0 ";
/*      */     } else {
/* 2431 */       sql = "SELECT NULL::text AS nspname,c.relname,a.attname,a.atttypid,a.attnotnull,a.atttypmod,a.attlen,a.attnum,NULL AS adsrc,NULL AS description,NULL AS typbasetype, 'b' AS typtype  FROM pg_class c, pg_attribute a  WHERE a.attrelid=c.oid AND a.attnum > 0 ";
/*      */     } 
/* 2436 */     if (!this.connection.haveMinimumServerVersion("7.3") && tableNamePattern != null && !"".equals(tableNamePattern))
/* 2438 */       sql = sql + " AND c.relname LIKE " + escapeQuotes(tableNamePattern); 
/* 2440 */     if (columnNamePattern != null && !"".equals(columnNamePattern))
/* 2442 */       sql = sql + " AND attname LIKE " + escapeQuotes(columnNamePattern); 
/* 2444 */     String sql = sql + " ORDER BY nspname,c.relname,attnum ";
/* 2446 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 2447 */     while (rs.next()) {
/*      */       int sqlType;
/* 2449 */       byte[][] tuple = new byte[numberOfFields][];
/* 2450 */       int typeOid = (int)rs.getLong("atttypid");
/* 2451 */       int typeMod = rs.getInt("atttypmod");
/* 2453 */       tuple[0] = null;
/* 2454 */       tuple[1] = rs.getBytes("nspname");
/* 2455 */       tuple[2] = rs.getBytes("relname");
/* 2456 */       tuple[3] = rs.getBytes("attname");
/* 2458 */       String typtype = rs.getString("typtype");
/* 2460 */       if ("c".equals(typtype)) {
/* 2461 */         sqlType = 2002;
/* 2462 */       } else if ("d".equals(typtype)) {
/* 2463 */         sqlType = 2001;
/*      */       } else {
/* 2465 */         sqlType = this.connection.getTypeInfo().getSQLType(typeOid);
/*      */       } 
/* 2468 */       tuple[4] = this.connection.encodeString(Integer.toString(sqlType));
/* 2469 */       String pgType = this.connection.getTypeInfo().getPGType(typeOid);
/* 2470 */       tuple[5] = this.connection.encodeString(pgType);
/* 2471 */       tuple[7] = null;
/* 2474 */       String defval = rs.getString("adsrc");
/* 2476 */       if (defval != null)
/* 2478 */         if (pgType.equals("int4")) {
/* 2480 */           if (defval.indexOf("nextval(") != -1)
/* 2481 */             tuple[5] = this.connection.encodeString("serial"); 
/* 2483 */         } else if (pgType.equals("int8")) {
/* 2485 */           if (defval.indexOf("nextval(") != -1)
/* 2486 */             tuple[5] = this.connection.encodeString("bigserial"); 
/*      */         }  
/* 2490 */       int decimalDigits = this.connection.getTypeInfo().getScale(typeOid, typeMod);
/* 2491 */       int columnSize = this.connection.getTypeInfo().getPrecision(typeOid, typeMod);
/* 2492 */       if (columnSize == 0)
/* 2493 */         columnSize = this.connection.getTypeInfo().getDisplaySize(typeOid, typeMod); 
/* 2496 */       tuple[6] = this.connection.encodeString(Integer.toString(columnSize));
/* 2497 */       tuple[8] = this.connection.encodeString(Integer.toString(decimalDigits));
/* 2500 */       tuple[9] = this.connection.encodeString("10");
/* 2502 */       if (pgType.equals("bit") || pgType.equals("varbit"))
/* 2504 */         tuple[9] = this.connection.encodeString("2"); 
/* 2507 */       tuple[10] = this.connection.encodeString(Integer.toString(rs.getBoolean("attnotnull") ? 0 : 1));
/* 2508 */       tuple[11] = rs.getBytes("description");
/* 2509 */       tuple[12] = rs.getBytes("adsrc");
/* 2510 */       tuple[13] = null;
/* 2511 */       tuple[14] = null;
/* 2512 */       tuple[15] = tuple[6];
/* 2513 */       tuple[16] = rs.getBytes("attnum");
/* 2514 */       tuple[17] = this.connection.encodeString(rs.getBoolean("attnotnull") ? "NO" : "YES");
/* 2516 */       if (jdbcVersion >= 3) {
/* 2517 */         int baseTypeOid = (int)rs.getLong("typbasetype");
/* 2519 */         tuple[18] = null;
/* 2520 */         tuple[19] = null;
/* 2521 */         tuple[20] = null;
/* 2522 */         tuple[21] = (baseTypeOid == 0) ? null : this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(baseTypeOid)));
/*      */       } 
/* 2525 */       if (jdbcVersion >= 4) {
/* 2526 */         String autoinc = "NO";
/* 2527 */         if (defval != null && defval.indexOf("nextval(") != -1)
/* 2528 */           autoinc = "YES"; 
/* 2530 */         tuple[22] = this.connection.encodeString(autoinc);
/*      */       } 
/* 2533 */       v.addElement(tuple);
/*      */     } 
/* 2535 */     rs.close();
/* 2537 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/* 2590 */     return getColumns(2, catalog, schemaPattern, tableNamePattern, columnNamePattern);
/*      */   }
/*      */   
/*      */   public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
/* 2622 */     Field[] f = new Field[8];
/* 2623 */     Vector<byte[][]> v = new Vector();
/* 2625 */     if (table == null)
/* 2626 */       table = "%"; 
/* 2628 */     if (columnNamePattern == null)
/* 2629 */       columnNamePattern = "%"; 
/* 2631 */     f[0] = new Field("TABLE_CAT", 1043);
/* 2632 */     f[1] = new Field("TABLE_SCHEM", 1043);
/* 2633 */     f[2] = new Field("TABLE_NAME", 1043);
/* 2634 */     f[3] = new Field("COLUMN_NAME", 1043);
/* 2635 */     f[4] = new Field("GRANTOR", 1043);
/* 2636 */     f[5] = new Field("GRANTEE", 1043);
/* 2637 */     f[6] = new Field("PRIVILEGE", 1043);
/* 2638 */     f[7] = new Field("IS_GRANTABLE", 1043);
/* 2641 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 2643 */       sql = "SELECT n.nspname,c.relname,u.usename,c.relacl,a.attname  FROM pg_catalog.pg_namespace n, pg_catalog.pg_class c, pg_catalog.pg_user u, pg_catalog.pg_attribute a  WHERE c.relnamespace = n.oid  AND u.usesysid = c.relowner  AND c.oid = a.attrelid  AND c.relkind = 'r'  AND a.attnum > 0 AND NOT a.attisdropped ";
/* 2650 */       if (schema != null && !"".equals(schema))
/* 2652 */         sql = sql + " AND n.nspname = " + escapeQuotes(schema); 
/*      */     } else {
/* 2657 */       sql = "SELECT NULL::text AS nspname,c.relname,u.usename,c.relacl,a.attname FROM pg_class c, pg_user u,pg_attribute a  WHERE u.usesysid = c.relowner  AND c.oid = a.attrelid  AND a.attnum > 0  AND c.relkind = 'r' ";
/*      */     } 
/* 2665 */     String sql = sql + " AND c.relname = " + escapeQuotes(table);
/* 2666 */     if (columnNamePattern != null && !"".equals(columnNamePattern))
/* 2668 */       sql = sql + " AND a.attname LIKE " + escapeQuotes(columnNamePattern); 
/* 2670 */     sql = sql + " ORDER BY attname ";
/* 2672 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 2673 */     while (rs.next()) {
/* 2675 */       byte[] schemaName = rs.getBytes("nspname");
/* 2676 */       byte[] tableName = rs.getBytes("relname");
/* 2677 */       byte[] column = rs.getBytes("attname");
/* 2678 */       String owner = rs.getString("usename");
/* 2679 */       String acl = rs.getString("relacl");
/* 2680 */       Hashtable permissions = parseACL(acl, owner);
/* 2681 */       String[] permNames = new String[permissions.size()];
/* 2682 */       Enumeration<String> e = permissions.keys();
/* 2683 */       int i = 0;
/* 2684 */       while (e.hasMoreElements())
/* 2686 */         permNames[i++] = e.nextElement(); 
/* 2688 */       sortStringArray(permNames);
/* 2689 */       for (i = 0; i < permNames.length; i++) {
/* 2691 */         byte[] privilege = this.connection.encodeString(permNames[i]);
/* 2692 */         Vector<String> grantees = (Vector)permissions.get(permNames[i]);
/* 2693 */         for (int j = 0; j < grantees.size(); j++) {
/* 2695 */           String grantee = grantees.elementAt(j);
/* 2696 */           String grantable = owner.equals(grantee) ? "YES" : "NO";
/* 2697 */           byte[][] tuple = new byte[8][];
/* 2698 */           tuple[0] = null;
/* 2699 */           tuple[1] = schemaName;
/* 2700 */           tuple[2] = tableName;
/* 2701 */           tuple[3] = column;
/* 2702 */           tuple[4] = this.connection.encodeString(owner);
/* 2703 */           tuple[5] = this.connection.encodeString(grantee);
/* 2704 */           tuple[6] = privilege;
/* 2705 */           tuple[7] = this.connection.encodeString(grantable);
/* 2706 */           v.addElement(tuple);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2710 */     rs.close();
/* 2712 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
/* 2747 */     Field[] f = new Field[7];
/* 2748 */     Vector<byte[][]> v = new Vector();
/* 2750 */     f[0] = new Field("TABLE_CAT", 1043);
/* 2751 */     f[1] = new Field("TABLE_SCHEM", 1043);
/* 2752 */     f[2] = new Field("TABLE_NAME", 1043);
/* 2753 */     f[3] = new Field("GRANTOR", 1043);
/* 2754 */     f[4] = new Field("GRANTEE", 1043);
/* 2755 */     f[5] = new Field("PRIVILEGE", 1043);
/* 2756 */     f[6] = new Field("IS_GRANTABLE", 1043);
/* 2759 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 2761 */       sql = "SELECT n.nspname,c.relname,u.usename,c.relacl  FROM pg_catalog.pg_namespace n, pg_catalog.pg_class c, pg_catalog.pg_user u  WHERE c.relnamespace = n.oid  AND u.usesysid = c.relowner  AND c.relkind = 'r' ";
/* 2766 */       if (schemaPattern != null && !"".equals(schemaPattern))
/* 2768 */         sql = sql + " AND n.nspname LIKE " + escapeQuotes(schemaPattern); 
/*      */     } else {
/* 2773 */       sql = "SELECT NULL::text AS nspname,c.relname,u.usename,c.relacl FROM pg_class c, pg_user u  WHERE u.usesysid = c.relowner  AND c.relkind = 'r' ";
/*      */     } 
/* 2779 */     if (tableNamePattern != null && !"".equals(tableNamePattern))
/* 2781 */       sql = sql + " AND c.relname LIKE " + escapeQuotes(tableNamePattern); 
/* 2783 */     String sql = sql + " ORDER BY nspname, relname ";
/* 2785 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 2786 */     while (rs.next()) {
/* 2788 */       byte[] schema = rs.getBytes("nspname");
/* 2789 */       byte[] table = rs.getBytes("relname");
/* 2790 */       String owner = rs.getString("usename");
/* 2791 */       String acl = rs.getString("relacl");
/* 2792 */       Hashtable permissions = parseACL(acl, owner);
/* 2793 */       String[] permNames = new String[permissions.size()];
/* 2794 */       Enumeration<String> e = permissions.keys();
/* 2795 */       int i = 0;
/* 2796 */       while (e.hasMoreElements())
/* 2798 */         permNames[i++] = e.nextElement(); 
/* 2800 */       sortStringArray(permNames);
/* 2801 */       for (i = 0; i < permNames.length; i++) {
/* 2803 */         byte[] privilege = this.connection.encodeString(permNames[i]);
/* 2804 */         Vector<String> grantees = (Vector)permissions.get(permNames[i]);
/* 2805 */         for (int j = 0; j < grantees.size(); j++) {
/* 2807 */           String grantee = grantees.elementAt(j);
/* 2808 */           String grantable = owner.equals(grantee) ? "YES" : "NO";
/* 2809 */           byte[][] tuple = new byte[7][];
/* 2810 */           tuple[0] = null;
/* 2811 */           tuple[1] = schema;
/* 2812 */           tuple[2] = table;
/* 2813 */           tuple[3] = this.connection.encodeString(owner);
/* 2814 */           tuple[4] = this.connection.encodeString(grantee);
/* 2815 */           tuple[5] = privilege;
/* 2816 */           tuple[6] = this.connection.encodeString(grantable);
/* 2817 */           v.addElement(tuple);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2821 */     rs.close();
/* 2823 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   private static void sortStringArray(String[] s) {
/* 2827 */     for (int i = 0; i < s.length - 1; i++) {
/* 2829 */       for (int j = i + 1; j < s.length; j++) {
/* 2831 */         if (s[i].compareTo(s[j]) > 0) {
/* 2833 */           String tmp = s[i];
/* 2834 */           s[i] = s[j];
/* 2835 */           s[j] = tmp;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Vector parseACLArray(String aclString) {
/* 2845 */     Vector<String> acls = new Vector();
/* 2846 */     if (aclString == null || aclString.length() == 0)
/* 2848 */       return acls; 
/* 2850 */     boolean inQuotes = false;
/* 2852 */     int beginIndex = 1;
/* 2853 */     char prevChar = ' ';
/*      */     int i;
/* 2854 */     for (i = beginIndex; i < aclString.length(); i++) {
/* 2857 */       char c = aclString.charAt(i);
/* 2858 */       if (c == '"' && prevChar != '\\') {
/* 2860 */         inQuotes = !inQuotes;
/* 2862 */       } else if (c == ',' && !inQuotes) {
/* 2864 */         acls.addElement(aclString.substring(beginIndex, i));
/* 2865 */         beginIndex = i + 1;
/*      */       } 
/* 2867 */       prevChar = c;
/*      */     } 
/* 2870 */     acls.addElement(aclString.substring(beginIndex, aclString.length() - 1));
/* 2873 */     for (i = 0; i < acls.size(); i++) {
/* 2875 */       String acl = acls.elementAt(i);
/* 2876 */       if (acl.startsWith("\"") && acl.endsWith("\"")) {
/* 2878 */         acl = acl.substring(1, acl.length() - 1);
/* 2879 */         acls.setElementAt(acl, i);
/*      */       } 
/*      */     } 
/* 2882 */     return acls;
/*      */   }
/*      */   
/*      */   private void addACLPrivileges(String acl, Hashtable<String, Vector> privileges) {
/* 2890 */     int equalIndex = acl.lastIndexOf("=");
/* 2891 */     if (equalIndex == -1)
/*      */       return; 
/* 2894 */     String name = acl.substring(0, equalIndex);
/* 2895 */     if (name.length() == 0)
/* 2897 */       name = "PUBLIC"; 
/* 2899 */     String privs = acl.substring(equalIndex + 1);
/* 2900 */     for (int i = 0; i < privs.length(); i++) {
/*      */       String sqlpriv;
/* 2902 */       char c = privs.charAt(i);
/* 2904 */       switch (c) {
/*      */         case 'a':
/* 2907 */           sqlpriv = "INSERT";
/*      */           break;
/*      */         case 'r':
/* 2910 */           sqlpriv = "SELECT";
/*      */           break;
/*      */         case 'w':
/* 2913 */           sqlpriv = "UPDATE";
/*      */           break;
/*      */         case 'd':
/* 2916 */           sqlpriv = "DELETE";
/*      */           break;
/*      */         case 'D':
/* 2919 */           sqlpriv = "TRUNCATE";
/*      */           break;
/*      */         case 'R':
/* 2922 */           sqlpriv = "RULE";
/*      */           break;
/*      */         case 'x':
/* 2925 */           sqlpriv = "REFERENCES";
/*      */           break;
/*      */         case 't':
/* 2928 */           sqlpriv = "TRIGGER";
/*      */           break;
/*      */         case 'X':
/* 2933 */           sqlpriv = "EXECUTE";
/*      */           break;
/*      */         case 'U':
/* 2936 */           sqlpriv = "USAGE";
/*      */           break;
/*      */         case 'C':
/* 2939 */           sqlpriv = "CREATE";
/*      */           break;
/*      */         case 'T':
/* 2942 */           sqlpriv = "CREATE TEMP";
/*      */           break;
/*      */         default:
/* 2945 */           sqlpriv = "UNKNOWN";
/*      */           break;
/*      */       } 
/* 2947 */       Vector<String> usersWithPermission = (Vector)privileges.get(sqlpriv);
/* 2948 */       if (usersWithPermission == null) {
/* 2950 */         usersWithPermission = new Vector();
/* 2951 */         privileges.put(sqlpriv, usersWithPermission);
/*      */       } 
/* 2953 */       usersWithPermission.addElement(name);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Hashtable parseACL(String aclArray, String owner) {
/* 2963 */     if (aclArray == null) {
/* 2966 */       String perms = "arwdRxt";
/* 2967 */       if (this.connection.haveMinimumServerVersion("8.2")) {
/* 2969 */         perms = "arwdxt";
/* 2970 */       } else if (this.connection.haveMinimumServerVersion("8.4")) {
/* 2972 */         perms = "arwdDxt";
/*      */       } 
/* 2974 */       aclArray = "{" + owner + "=" + perms + "}";
/*      */     } 
/* 2977 */     Vector<String> acls = parseACLArray(aclArray);
/* 2978 */     Hashtable<Object, Object> privileges = new Hashtable<Object, Object>();
/* 2979 */     for (int i = 0; i < acls.size(); i++) {
/* 2981 */       String acl = acls.elementAt(i);
/* 2982 */       addACLPrivileges(acl, privileges);
/*      */     } 
/* 2984 */     return privileges;
/*      */   }
/*      */   
/*      */   public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
/* 3024 */     Field[] f = new Field[8];
/* 3025 */     Vector<byte[][]> v = new Vector();
/* 3027 */     f[0] = new Field("SCOPE", 21);
/* 3028 */     f[1] = new Field("COLUMN_NAME", 1043);
/* 3029 */     f[2] = new Field("DATA_TYPE", 21);
/* 3030 */     f[3] = new Field("TYPE_NAME", 1043);
/* 3031 */     f[4] = new Field("COLUMN_SIZE", 23);
/* 3032 */     f[5] = new Field("BUFFER_LENGTH", 23);
/* 3033 */     f[6] = new Field("DECIMAL_DIGITS", 21);
/* 3034 */     f[7] = new Field("PSEUDO_COLUMN", 21);
/* 3042 */     if (this.connection.haveMinimumServerVersion("8.1")) {
/* 3044 */       sql = "SELECT a.attname, a.atttypid, atttypmod FROM pg_catalog.pg_class ct   JOIN pg_catalog.pg_attribute a ON (ct.oid = a.attrelid)   JOIN pg_catalog.pg_namespace n ON (ct.relnamespace = n.oid)   JOIN (SELECT i.indexrelid, i.indrelid, i.indisprimary,              information_schema._pg_expandarray(i.indkey) AS keys         FROM pg_catalog.pg_index i) i     ON (a.attnum = (i.keys).x AND a.attrelid = i.indrelid) WHERE true ";
/* 3053 */       if (schema != null && !"".equals(schema))
/* 3055 */         sql = sql + " AND n.nspname = " + escapeQuotes(schema); 
/*      */     } else {
/* 3061 */       String from, where = "";
/* 3062 */       if (this.connection.haveMinimumServerVersion("7.3")) {
/* 3064 */         from = " FROM pg_catalog.pg_namespace n, pg_catalog.pg_class ct, pg_catalog.pg_class ci, pg_catalog.pg_attribute a, pg_catalog.pg_index i ";
/* 3065 */         where = " AND ct.relnamespace = n.oid ";
/* 3066 */         if (schema != null && !"".equals(schema))
/* 3068 */           where = where + " AND n.nspname = " + escapeQuotes(schema); 
/*      */       } else {
/* 3073 */         from = " FROM pg_class ct, pg_class ci, pg_attribute a, pg_index i ";
/*      */       } 
/* 3075 */       sql = "SELECT a.attname, a.atttypid, a.atttypmod " + from + " WHERE ct.oid=i.indrelid AND ci.oid=i.indexrelid " + " AND a.attrelid=ci.oid " + where;
/*      */     } 
/* 3082 */     String sql = sql + " AND ct.relname = " + escapeQuotes(table) + " AND i.indisprimary " + " ORDER BY a.attnum ";
/* 3086 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 3087 */     while (rs.next()) {
/* 3089 */       byte[][] tuple = new byte[8][];
/* 3090 */       int typeOid = (int)rs.getLong("atttypid");
/* 3091 */       int typeMod = rs.getInt("atttypmod");
/* 3092 */       int decimalDigits = this.connection.getTypeInfo().getScale(typeOid, typeMod);
/* 3093 */       int columnSize = this.connection.getTypeInfo().getPrecision(typeOid, typeMod);
/* 3094 */       if (columnSize == 0)
/* 3095 */         columnSize = this.connection.getTypeInfo().getDisplaySize(typeOid, typeMod); 
/* 3097 */       tuple[0] = this.connection.encodeString(Integer.toString(scope));
/* 3098 */       tuple[1] = rs.getBytes("attname");
/* 3099 */       tuple[2] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(typeOid)));
/* 3100 */       tuple[3] = this.connection.encodeString(this.connection.getTypeInfo().getPGType(typeOid));
/* 3101 */       tuple[4] = this.connection.encodeString(Integer.toString(columnSize));
/* 3102 */       tuple[5] = null;
/* 3103 */       tuple[6] = this.connection.encodeString(Integer.toString(decimalDigits));
/* 3104 */       tuple[7] = this.connection.encodeString(Integer.toString(1));
/* 3105 */       v.addElement(tuple);
/*      */     } 
/* 3108 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
/* 3141 */     Field[] f = new Field[8];
/* 3142 */     Vector<byte[][]> v = new Vector();
/* 3144 */     f[0] = new Field("SCOPE", 21);
/* 3145 */     f[1] = new Field("COLUMN_NAME", 1043);
/* 3146 */     f[2] = new Field("DATA_TYPE", 21);
/* 3147 */     f[3] = new Field("TYPE_NAME", 1043);
/* 3148 */     f[4] = new Field("COLUMN_SIZE", 23);
/* 3149 */     f[5] = new Field("BUFFER_LENGTH", 23);
/* 3150 */     f[6] = new Field("DECIMAL_DIGITS", 21);
/* 3151 */     f[7] = new Field("PSEUDO_COLUMN", 21);
/* 3153 */     byte[][] tuple = new byte[8][];
/* 3165 */     tuple[0] = null;
/* 3166 */     tuple[1] = this.connection.encodeString("ctid");
/* 3167 */     tuple[2] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType("tid")));
/* 3168 */     tuple[3] = this.connection.encodeString("tid");
/* 3169 */     tuple[4] = null;
/* 3170 */     tuple[5] = null;
/* 3171 */     tuple[6] = null;
/* 3172 */     tuple[7] = this.connection.encodeString(Integer.toString(2));
/* 3173 */     v.addElement(tuple);
/* 3178 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
/* 3204 */     if (this.connection.haveMinimumServerVersion("8.1")) {
/* 3206 */       sql = "SELECT NULL AS TABLE_CAT, n.nspname AS TABLE_SCHEM,   ct.relname AS TABLE_NAME, a.attname AS COLUMN_NAME,   (i.keys).n AS KEY_SEQ, ci.relname AS PK_NAME FROM pg_catalog.pg_class ct   JOIN pg_catalog.pg_attribute a ON (ct.oid = a.attrelid)   JOIN pg_catalog.pg_namespace n ON (ct.relnamespace = n.oid)   JOIN (SELECT i.indexrelid, i.indrelid, i.indisprimary,              information_schema._pg_expandarray(i.indkey) AS keys         FROM pg_catalog.pg_index i) i     ON (a.attnum = (i.keys).x AND a.attrelid = i.indrelid)   JOIN pg_catalog.pg_class ci ON (ci.oid = i.indexrelid) WHERE true ";
/* 3218 */       if (schema != null && !"".equals(schema))
/* 3220 */         sql = sql + " AND n.nspname = " + escapeQuotes(schema); 
/*      */     } else {
/* 3225 */       String select, from, where = "";
/* 3227 */       if (this.connection.haveMinimumServerVersion("7.3")) {
/* 3229 */         select = "SELECT NULL AS TABLE_CAT, n.nspname AS TABLE_SCHEM, ";
/* 3230 */         from = " FROM pg_catalog.pg_namespace n, pg_catalog.pg_class ct, pg_catalog.pg_class ci, pg_catalog.pg_attribute a, pg_catalog.pg_index i ";
/* 3231 */         where = " AND ct.relnamespace = n.oid ";
/* 3232 */         if (schema != null && !"".equals(schema))
/* 3234 */           where = where + " AND n.nspname = " + escapeQuotes(schema); 
/*      */       } else {
/* 3239 */         select = "SELECT NULL AS TABLE_CAT, NULL AS TABLE_SCHEM, ";
/* 3240 */         from = " FROM pg_class ct, pg_class ci, pg_attribute a, pg_index i ";
/*      */       } 
/* 3243 */       sql = select + " ct.relname AS TABLE_NAME, " + " a.attname AS COLUMN_NAME, " + " a.attnum AS KEY_SEQ, " + " ci.relname AS PK_NAME " + from + " WHERE ct.oid=i.indrelid AND ci.oid=i.indexrelid " + " AND a.attrelid=ci.oid " + where;
/*      */     } 
/* 3254 */     if (table != null && !"".equals(table))
/* 3256 */       sql = sql + " AND ct.relname = " + escapeQuotes(table); 
/* 3259 */     String sql = sql + " AND i.indisprimary  ORDER BY table_name, pk_name, key_seq";
/* 3262 */     return createMetaDataStatement().executeQuery(sql);
/*      */   }
/*      */   
/*      */   protected ResultSet getImportedExportedKeys(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
/*      */     String select, from;
/* 3277 */     Field[] f = new Field[14];
/* 3279 */     f[0] = new Field("PKTABLE_CAT", 1043);
/* 3280 */     f[1] = new Field("PKTABLE_SCHEM", 1043);
/* 3281 */     f[2] = new Field("PKTABLE_NAME", 1043);
/* 3282 */     f[3] = new Field("PKCOLUMN_NAME", 1043);
/* 3283 */     f[4] = new Field("FKTABLE_CAT", 1043);
/* 3284 */     f[5] = new Field("FKTABLE_SCHEM", 1043);
/* 3285 */     f[6] = new Field("FKTABLE_NAME", 1043);
/* 3286 */     f[7] = new Field("FKCOLUMN_NAME", 1043);
/* 3287 */     f[8] = new Field("KEY_SEQ", 21);
/* 3288 */     f[9] = new Field("UPDATE_RULE", 21);
/* 3289 */     f[10] = new Field("DELETE_RULE", 21);
/* 3290 */     f[11] = new Field("FK_NAME", 1043);
/* 3291 */     f[12] = new Field("PK_NAME", 1043);
/* 3292 */     f[13] = new Field("DEFERRABILITY", 21);
/* 3297 */     String where = "";
/* 3310 */     if (this.connection.haveMinimumServerVersion("7.4")) {
/* 3312 */       String str = "SELECT NULL::text AS PKTABLE_CAT, pkn.nspname AS PKTABLE_SCHEM, pkc.relname AS PKTABLE_NAME, pka.attname AS PKCOLUMN_NAME, NULL::text AS FKTABLE_CAT, fkn.nspname AS FKTABLE_SCHEM, fkc.relname AS FKTABLE_NAME, fka.attname AS FKCOLUMN_NAME, pos.n AS KEY_SEQ, CASE con.confupdtype  WHEN 'c' THEN 0 WHEN 'n' THEN 2 WHEN 'd' THEN 4 WHEN 'r' THEN 1 WHEN 'a' THEN 3 ELSE NULL END AS UPDATE_RULE, CASE con.confdeltype  WHEN 'c' THEN 0 WHEN 'n' THEN 2 WHEN 'd' THEN 4 WHEN 'r' THEN 1 WHEN 'a' THEN 3 ELSE NULL END AS DELETE_RULE, con.conname AS FK_NAME, pkic.relname AS PK_NAME, CASE  WHEN con.condeferrable AND con.condeferred THEN 5 WHEN con.condeferrable THEN 6 ELSE 7 END AS DEFERRABILITY  FROM  pg_catalog.pg_namespace pkn, pg_catalog.pg_class pkc, pg_catalog.pg_attribute pka,  pg_catalog.pg_namespace fkn, pg_catalog.pg_class fkc, pg_catalog.pg_attribute fka,  pg_catalog.pg_constraint con, ";
/* 3339 */       if (this.connection.haveMinimumServerVersion("8.0")) {
/* 3340 */         str = str + " pg_catalog.generate_series(1, " + getMaxIndexKeys() + ") pos(n), ";
/*      */       } else {
/* 3342 */         str = str + " information_schema._pg_keypositions() pos(n), ";
/*      */       } 
/* 3344 */       str = str + " pg_catalog.pg_depend dep, pg_catalog.pg_class pkic  WHERE pkn.oid = pkc.relnamespace AND pkc.oid = pka.attrelid AND pka.attnum = con.confkey[pos.n] AND con.confrelid = pkc.oid  AND fkn.oid = fkc.relnamespace AND fkc.oid = fka.attrelid AND fka.attnum = con.conkey[pos.n] AND con.conrelid = fkc.oid  AND con.contype = 'f' AND con.oid = dep.objid AND pkic.oid = dep.refobjid AND pkic.relkind = 'i' AND dep.classid = 'pg_constraint'::regclass::oid AND dep.refclassid = 'pg_class'::regclass::oid ";
/* 3348 */       if (primarySchema != null && !"".equals(primarySchema))
/* 3350 */         str = str + " AND pkn.nspname = " + escapeQuotes(primarySchema); 
/* 3352 */       if (foreignSchema != null && !"".equals(foreignSchema))
/* 3354 */         str = str + " AND fkn.nspname = " + escapeQuotes(foreignSchema); 
/* 3356 */       if (primaryTable != null && !"".equals(primaryTable))
/* 3358 */         str = str + " AND pkc.relname = " + escapeQuotes(primaryTable); 
/* 3360 */       if (foreignTable != null && !"".equals(foreignTable))
/* 3362 */         str = str + " AND fkc.relname = " + escapeQuotes(foreignTable); 
/* 3365 */       if (primaryTable != null) {
/* 3367 */         str = str + " ORDER BY fkn.nspname,fkc.relname,pos.n";
/*      */       } else {
/* 3371 */         str = str + " ORDER BY pkn.nspname,pkc.relname,pos.n";
/*      */       } 
/* 3374 */       return createMetaDataStatement().executeQuery(str);
/*      */     } 
/* 3376 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 3378 */       select = "SELECT DISTINCT n1.nspname as pnspname,n2.nspname as fnspname, ";
/* 3379 */       from = " FROM pg_catalog.pg_namespace n1  JOIN pg_catalog.pg_class c1 ON (c1.relnamespace = n1.oid)  JOIN pg_catalog.pg_index i ON (c1.oid=i.indrelid)  JOIN pg_catalog.pg_class ic ON (i.indexrelid=ic.oid)  JOIN pg_catalog.pg_attribute a ON (ic.oid=a.attrelid),  pg_catalog.pg_namespace n2  JOIN pg_catalog.pg_class c2 ON (c2.relnamespace=n2.oid),  pg_catalog.pg_trigger t1  JOIN pg_catalog.pg_proc p1 ON (t1.tgfoid=p1.oid),  pg_catalog.pg_trigger t2  JOIN pg_catalog.pg_proc p2 ON (t2.tgfoid=p2.oid) ";
/* 3390 */       if (primarySchema != null && !"".equals(primarySchema))
/* 3392 */         where = where + " AND n1.nspname = " + escapeQuotes(primarySchema); 
/* 3394 */       if (foreignSchema != null && !"".equals(foreignSchema))
/* 3396 */         where = where + " AND n2.nspname = " + escapeQuotes(foreignSchema); 
/*      */     } else {
/* 3401 */       select = "SELECT DISTINCT NULL::text as pnspname, NULL::text as fnspname, ";
/* 3402 */       from = " FROM pg_class c1  JOIN pg_index i ON (c1.oid=i.indrelid)  JOIN pg_class ic ON (i.indexrelid=ic.oid)  JOIN pg_attribute a ON (ic.oid=a.attrelid),  pg_class c2,  pg_trigger t1  JOIN pg_proc p1 ON (t1.tgfoid=p1.oid),  pg_trigger t2  JOIN pg_proc p2 ON (t2.tgfoid=p2.oid) ";
/*      */     } 
/* 3413 */     String sql = select + "c1.relname as prelname, " + "c2.relname as frelname, " + "t1.tgconstrname, " + "a.attnum as keyseq, " + "ic.relname as fkeyname, " + "t1.tgdeferrable, " + "t1.tginitdeferred, " + "t1.tgnargs,t1.tgargs, " + "p1.proname as updaterule, " + "p2.proname as deleterule " + from + "WHERE " + "(t1.tgrelid=c1.oid " + "AND t1.tgisconstraint " + "AND t1.tgconstrrelid=c2.oid " + "AND p1.proname ~ '^RI_FKey_.*_upd$') " + "AND " + "(t2.tgrelid=c1.oid " + "AND t2.tgisconstraint " + "AND t2.tgconstrrelid=c2.oid " + "AND p2.proname ~ '^RI_FKey_.*_del$') " + "AND i.indisprimary " + where;
/* 3442 */     if (primaryTable != null)
/* 3444 */       sql = sql + "AND c1.relname=" + escapeQuotes(primaryTable); 
/* 3446 */     if (foreignTable != null)
/* 3448 */       sql = sql + "AND c2.relname=" + escapeQuotes(foreignTable); 
/* 3451 */     sql = sql + "ORDER BY ";
/* 3458 */     if (primaryTable != null) {
/* 3460 */       if (this.connection.haveMinimumServerVersion("7.3"))
/* 3462 */         sql = sql + "fnspname,"; 
/* 3464 */       sql = sql + "frelname";
/*      */     } else {
/* 3468 */       if (this.connection.haveMinimumServerVersion("7.3"))
/* 3470 */         sql = sql + "pnspname,"; 
/* 3472 */       sql = sql + "prelname";
/*      */     } 
/* 3475 */     sql = sql + ",keyseq";
/* 3477 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 3495 */     Vector<byte[][]> tuples = new Vector();
/* 3497 */     while (rs.next()) {
/* 3499 */       byte[][] tuple = new byte[14][];
/* 3501 */       tuple[1] = rs.getBytes(1);
/* 3502 */       tuple[5] = rs.getBytes(2);
/* 3503 */       tuple[2] = rs.getBytes(3);
/* 3504 */       tuple[6] = rs.getBytes(4);
/* 3505 */       String fKeyName = rs.getString(5);
/* 3506 */       String updateRule = rs.getString(12);
/* 3508 */       if (updateRule != null) {
/* 3512 */         String rule = updateRule.substring(8, updateRule.length() - 4);
/* 3514 */         int action = 3;
/* 3516 */         if (rule == null || "noaction".equals(rule))
/* 3517 */           action = 3; 
/* 3518 */         if ("cascade".equals(rule)) {
/* 3519 */           action = 0;
/* 3520 */         } else if ("setnull".equals(rule)) {
/* 3521 */           action = 2;
/* 3522 */         } else if ("setdefault".equals(rule)) {
/* 3523 */           action = 4;
/* 3524 */         } else if ("restrict".equals(rule)) {
/* 3525 */           action = 1;
/*      */         } 
/* 3527 */         tuple[9] = this.connection.encodeString(Integer.toString(action));
/*      */       } 
/* 3531 */       String deleteRule = rs.getString(13);
/* 3533 */       if (deleteRule != null) {
/* 3536 */         String rule = deleteRule.substring(8, deleteRule.length() - 4);
/* 3538 */         int action = 3;
/* 3539 */         if ("cascade".equals(rule)) {
/* 3540 */           action = 0;
/* 3541 */         } else if ("setnull".equals(rule)) {
/* 3542 */           action = 2;
/* 3543 */         } else if ("setdefault".equals(rule)) {
/* 3544 */           action = 4;
/* 3545 */         } else if ("restrict".equals(rule)) {
/* 3546 */           action = 1;
/*      */         } 
/* 3547 */         tuple[10] = this.connection.encodeString(Integer.toString(action));
/*      */       } 
/* 3551 */       int keySequence = rs.getInt(6);
/* 3554 */       String fkeyColumn = "";
/* 3555 */       String pkeyColumn = "";
/* 3556 */       String fkName = "";
/* 3562 */       String targs = rs.getString(11);
/* 3568 */       Vector<String> tokens = tokenize(targs, "\\000");
/* 3569 */       if (tokens.size() > 0)
/* 3571 */         fkName = tokens.elementAt(0); 
/* 3574 */       if (fkName.startsWith("<unnamed>"))
/* 3576 */         fkName = targs; 
/* 3579 */       int element = 4 + (keySequence - 1) * 2;
/* 3580 */       if (tokens.size() > element)
/* 3582 */         fkeyColumn = tokens.elementAt(element); 
/* 3585 */       element++;
/* 3586 */       if (tokens.size() > element)
/* 3588 */         pkeyColumn = tokens.elementAt(element); 
/* 3591 */       tuple[3] = this.connection.encodeString(pkeyColumn);
/* 3592 */       tuple[7] = this.connection.encodeString(fkeyColumn);
/* 3594 */       tuple[8] = rs.getBytes(6);
/* 3595 */       tuple[11] = this.connection.encodeString(fkName);
/* 3596 */       tuple[12] = rs.getBytes(7);
/* 3599 */       int deferrability = 7;
/* 3600 */       boolean deferrable = rs.getBoolean(8);
/* 3601 */       boolean initiallyDeferred = rs.getBoolean(9);
/* 3602 */       if (deferrable)
/* 3604 */         if (initiallyDeferred) {
/* 3605 */           deferrability = 5;
/*      */         } else {
/* 3607 */           deferrability = 6;
/*      */         }  
/* 3609 */       tuple[13] = this.connection.encodeString(Integer.toString(deferrability));
/* 3611 */       tuples.addElement(tuple);
/*      */     } 
/* 3614 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, tuples);
/*      */   }
/*      */   
/*      */   public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
/* 3670 */     return getImportedExportedKeys(null, null, null, catalog, schema, table);
/*      */   }
/*      */   
/*      */   public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
/* 3728 */     return getImportedExportedKeys(catalog, schema, table, null, null, null);
/*      */   }
/*      */   
/*      */   public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
/* 3789 */     return getImportedExportedKeys(primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable);
/*      */   }
/*      */   
/*      */   public ResultSet getTypeInfo() throws SQLException {
/*      */     String sql;
/* 3840 */     Field[] f = new Field[18];
/* 3841 */     Vector<byte[][]> v = new Vector();
/* 3843 */     f[0] = new Field("TYPE_NAME", 1043);
/* 3844 */     f[1] = new Field("DATA_TYPE", 21);
/* 3845 */     f[2] = new Field("PRECISION", 23);
/* 3846 */     f[3] = new Field("LITERAL_PREFIX", 1043);
/* 3847 */     f[4] = new Field("LITERAL_SUFFIX", 1043);
/* 3848 */     f[5] = new Field("CREATE_PARAMS", 1043);
/* 3849 */     f[6] = new Field("NULLABLE", 21);
/* 3850 */     f[7] = new Field("CASE_SENSITIVE", 16);
/* 3851 */     f[8] = new Field("SEARCHABLE", 21);
/* 3852 */     f[9] = new Field("UNSIGNED_ATTRIBUTE", 16);
/* 3853 */     f[10] = new Field("FIXED_PREC_SCALE", 16);
/* 3854 */     f[11] = new Field("AUTO_INCREMENT", 16);
/* 3855 */     f[12] = new Field("LOCAL_TYPE_NAME", 1043);
/* 3856 */     f[13] = new Field("MINIMUM_SCALE", 21);
/* 3857 */     f[14] = new Field("MAXIMUM_SCALE", 21);
/* 3858 */     f[15] = new Field("SQL_DATA_TYPE", 23);
/* 3859 */     f[16] = new Field("SQL_DATETIME_SUB", 23);
/* 3860 */     f[17] = new Field("NUM_PREC_RADIX", 23);
/* 3863 */     if (this.connection.haveMinimumServerVersion("7.3")) {
/* 3865 */       sql = "SELECT t.typname,t.oid FROM pg_catalog.pg_type t JOIN pg_catalog.pg_namespace n ON (t.typnamespace = n.oid)  WHERE n.nspname != 'pg_toast'";
/*      */     } else {
/* 3871 */       sql = "SELECT typname,oid FROM pg_type WHERE NOT (typname ~ '^pg_toast_') ";
/*      */     } 
/* 3875 */     ResultSet rs = this.connection.createStatement().executeQuery(sql);
/* 3878 */     byte[] bZero = this.connection.encodeString("0");
/* 3879 */     byte[] b10 = this.connection.encodeString("10");
/* 3880 */     byte[] bf = this.connection.encodeString("f");
/* 3881 */     byte[] bt = this.connection.encodeString("t");
/* 3882 */     byte[] bliteral = this.connection.encodeString("'");
/* 3883 */     byte[] bNullable = this.connection.encodeString(Integer.toString(1));
/* 3884 */     byte[] bSearchable = this.connection.encodeString(Integer.toString(3));
/* 3886 */     while (rs.next()) {
/* 3888 */       byte[][] tuple = new byte[18][];
/* 3889 */       String typname = rs.getString(1);
/* 3890 */       int typeOid = (int)rs.getLong(2);
/* 3892 */       tuple[0] = this.connection.encodeString(typname);
/* 3893 */       tuple[1] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getSQLType(typname)));
/* 3894 */       tuple[2] = this.connection.encodeString(Integer.toString(this.connection.getTypeInfo().getMaximumPrecision(typeOid)));
/* 3896 */       if (this.connection.getTypeInfo().requiresQuoting(typeOid)) {
/* 3898 */         tuple[3] = bliteral;
/* 3899 */         tuple[4] = bliteral;
/*      */       } 
/* 3902 */       tuple[6] = bNullable;
/* 3903 */       tuple[7] = this.connection.getTypeInfo().isCaseSensitive(typeOid) ? bt : bf;
/* 3904 */       tuple[8] = bSearchable;
/* 3905 */       tuple[9] = this.connection.getTypeInfo().isSigned(typeOid) ? bf : bt;
/* 3906 */       tuple[10] = bf;
/* 3907 */       tuple[11] = bf;
/* 3908 */       tuple[13] = bZero;
/* 3910 */       tuple[14] = (typeOid == 1700) ? this.connection.encodeString("1000") : bZero;
/* 3914 */       tuple[17] = b10;
/* 3915 */       v.addElement(tuple);
/* 3918 */       if (typname.equals("int4")) {
/* 3920 */         byte[][] tuple1 = (byte[][])tuple.clone();
/* 3922 */         tuple1[0] = this.connection.encodeString("serial");
/* 3923 */         tuple1[11] = bt;
/* 3924 */         v.addElement(tuple1);
/*      */         continue;
/*      */       } 
/* 3926 */       if (typname.equals("int8")) {
/* 3928 */         byte[][] tuple1 = (byte[][])tuple.clone();
/* 3930 */         tuple1[0] = this.connection.encodeString("bigserial");
/* 3931 */         tuple1[11] = bt;
/* 3932 */         v.addElement(tuple1);
/*      */       } 
/*      */     } 
/* 3936 */     rs.close();
/* 3938 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*      */   }
/*      */   
/*      */   public ResultSet getIndexInfo(String catalog, String schema, String tableName, boolean unique, boolean approximate) throws SQLException {
/* 4008 */     if (this.connection.haveMinimumServerVersion("8.3")) {
/* 4010 */       sql = "SELECT NULL AS TABLE_CAT, n.nspname AS TABLE_SCHEM,   ct.relname AS TABLE_NAME, NOT i.indisunique AS NON_UNIQUE,   NULL AS INDEX_QUALIFIER, ci.relname AS INDEX_NAME,   CASE i.indisclustered     WHEN true THEN 1    ELSE CASE am.amname       WHEN 'hash' THEN 2      ELSE 3    END   END AS TYPE,   (i.keys).n AS ORDINAL_POSITION,   pg_catalog.pg_get_indexdef(ci.oid, (i.keys).n, false) AS COLUMN_NAME,   CASE am.amcanorder     WHEN true THEN CASE i.indoption[(i.keys).n - 1] & 1       WHEN 1 THEN 'D'       ELSE 'A'     END     ELSE NULL   END AS ASC_OR_DESC,   ci.reltuples AS CARDINALITY,   ci.relpages AS PAGES,   pg_catalog.pg_get_expr(i.indpred, i.indrelid) AS FILTER_CONDITION FROM pg_catalog.pg_class ct   JOIN pg_catalog.pg_namespace n ON (ct.relnamespace = n.oid)   JOIN (SELECT i.indexrelid, i.indrelid, i.indoption,           i.indisunique, i.indisclustered, i.indpred,           i.indexprs,           information_schema._pg_expandarray(i.indkey) AS keys         FROM pg_catalog.pg_index i) i     ON (ct.oid = i.indrelid)   JOIN pg_catalog.pg_class ci ON (ci.oid = i.indexrelid)   JOIN pg_catalog.pg_am am ON (ci.relam = am.oid) WHERE true ";
/* 4044 */       if (schema != null && !"".equals(schema))
/* 4046 */         sql = sql + " AND n.nspname = " + escapeQuotes(schema); 
/*      */     } else {
/* 4051 */       String select, from, where = "";
/* 4053 */       if (this.connection.haveMinimumServerVersion("7.3")) {
/* 4055 */         select = "SELECT NULL AS TABLE_CAT, n.nspname AS TABLE_SCHEM, ";
/* 4056 */         from = " FROM pg_catalog.pg_namespace n, pg_catalog.pg_class ct, pg_catalog.pg_class ci, pg_catalog.pg_attribute a, pg_catalog.pg_am am ";
/* 4057 */         where = " AND n.oid = ct.relnamespace ";
/* 4059 */         if (!this.connection.haveMinimumServerVersion("7.4")) {
/* 4060 */           from = from + ", pg_catalog.pg_attribute ai, pg_catalog.pg_index i LEFT JOIN pg_catalog.pg_proc ip ON (i.indproc = ip.oid) ";
/* 4061 */           where = where + " AND ai.attnum = i.indkey[0] AND ai.attrelid = ct.oid ";
/*      */         } else {
/* 4063 */           from = from + ", pg_catalog.pg_index i ";
/*      */         } 
/* 4065 */         if (schema != null && !"".equals(schema))
/* 4067 */           where = where + " AND n.nspname = " + escapeQuotes(schema); 
/*      */       } else {
/* 4072 */         select = "SELECT NULL AS TABLE_CAT, NULL AS TABLE_SCHEM, ";
/* 4073 */         from = " FROM pg_class ct, pg_class ci, pg_attribute a, pg_am am, pg_attribute ai, pg_index i LEFT JOIN pg_proc ip ON (i.indproc = ip.oid) ";
/* 4074 */         where = " AND ai.attnum = i.indkey[0] AND ai.attrelid = ct.oid ";
/*      */       } 
/* 4077 */       sql = select + " ct.relname AS TABLE_NAME, NOT i.indisunique AS NON_UNIQUE, NULL AS INDEX_QUALIFIER, ci.relname AS INDEX_NAME, " + " CASE i.indisclustered " + " WHEN true THEN " + '\001' + " ELSE CASE am.amname " + " WHEN 'hash' THEN " + '\002' + " ELSE " + '\003' + " END " + " END AS TYPE, " + " a.attnum AS ORDINAL_POSITION, ";
/* 4088 */       if (this.connection.haveMinimumServerVersion("7.4")) {
/* 4090 */         sql = sql + " CASE WHEN i.indexprs IS NULL THEN a.attname ELSE pg_catalog.pg_get_indexdef(ci.oid,a.attnum,false) END AS COLUMN_NAME, ";
/*      */       } else {
/* 4094 */         sql = sql + " CASE i.indproc WHEN 0 THEN a.attname ELSE ip.proname || '(' || ai.attname || ')' END AS COLUMN_NAME, ";
/*      */       } 
/* 4098 */       sql = sql + " NULL AS ASC_OR_DESC,  ci.reltuples AS CARDINALITY,  ci.relpages AS PAGES, ";
/* 4102 */       if (this.connection.haveMinimumServerVersion("7.3")) {
/* 4104 */         sql = sql + " pg_catalog.pg_get_expr(i.indpred, i.indrelid) AS FILTER_CONDITION ";
/* 4106 */       } else if (this.connection.haveMinimumServerVersion("7.2")) {
/* 4108 */         sql = sql + " pg_get_expr(i.indpred, i.indrelid) AS FILTER_CONDITION ";
/*      */       } else {
/* 4112 */         sql = sql + " NULL AS FILTER_CONDITION ";
/*      */       } 
/* 4115 */       sql = sql + from + " WHERE ct.oid=i.indrelid AND ci.oid=i.indexrelid AND a.attrelid=ci.oid AND ci.relam=am.oid " + where;
/*      */     } 
/* 4120 */     String sql = sql + " AND ct.relname = " + escapeQuotes(tableName);
/* 4122 */     if (unique)
/* 4124 */       sql = sql + " AND i.indisunique "; 
/* 4126 */     sql = sql + " ORDER BY NON_UNIQUE, TYPE, INDEX_NAME, ORDINAL_POSITION ";
/* 4127 */     return createMetaDataStatement().executeQuery(sql);
/*      */   }
/*      */   
/*      */   private static Vector tokenize(String input, String delimiter) {
/* 4134 */     Vector<String> result = new Vector();
/* 4135 */     int start = 0;
/* 4136 */     int end = input.length();
/* 4137 */     int delimiterSize = delimiter.length();
/* 4139 */     while (start < end) {
/* 4141 */       int delimiterIndex = input.indexOf(delimiter, start);
/* 4142 */       if (delimiterIndex < 0) {
/* 4144 */         result.addElement(input.substring(start));
/*      */         break;
/*      */       } 
/* 4149 */       String token = input.substring(start, delimiterIndex);
/* 4150 */       result.addElement(token);
/* 4151 */       start = delimiterIndex + delimiterSize;
/*      */     } 
/* 4154 */     return result;
/*      */   }
/*      */   
/*      */   public boolean supportsResultSetType(int type) throws SQLException {
/* 4169 */     return (type != 1005);
/*      */   }
/*      */   
/*      */   public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
/* 4185 */     if (type == 1005)
/* 4186 */       return false; 
/* 4189 */     if (concurrency == 1008)
/* 4190 */       return true; 
/* 4193 */     return true;
/*      */   }
/*      */   
/*      */   public boolean ownUpdatesAreVisible(int type) throws SQLException {
/* 4200 */     return true;
/*      */   }
/*      */   
/*      */   public boolean ownDeletesAreVisible(int type) throws SQLException {
/* 4205 */     return true;
/*      */   }
/*      */   
/*      */   public boolean ownInsertsAreVisible(int type) throws SQLException {
/* 4211 */     return true;
/*      */   }
/*      */   
/*      */   public boolean othersUpdatesAreVisible(int type) throws SQLException {
/* 4216 */     return false;
/*      */   }
/*      */   
/*      */   public boolean othersDeletesAreVisible(int i) throws SQLException {
/* 4221 */     return false;
/*      */   }
/*      */   
/*      */   public boolean othersInsertsAreVisible(int type) throws SQLException {
/* 4226 */     return false;
/*      */   }
/*      */   
/*      */   public boolean updatesAreDetected(int type) throws SQLException {
/* 4231 */     return false;
/*      */   }
/*      */   
/*      */   public boolean deletesAreDetected(int i) throws SQLException {
/* 4236 */     return false;
/*      */   }
/*      */   
/*      */   public boolean insertsAreDetected(int type) throws SQLException {
/* 4241 */     return false;
/*      */   }
/*      */   
/*      */   public boolean supportsBatchUpdates() throws SQLException {
/* 4249 */     return true;
/*      */   }
/*      */   
/*      */   public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
/* 4267 */     String sql = "select null as type_cat, n.nspname as type_schem, t.typname as type_name,  null as class_name, CASE WHEN t.typtype='c' then 2002 else 2001 end as data_type, pg_catalog.obj_description(t.oid, 'pg_type')  as remarks, CASE WHEN t.typtype = 'd' then  (select CASE";
/* 4272 */     for (Iterator<String> i = this.connection.getTypeInfo().getPGTypeNamesWithSQLTypes(); i.hasNext(); ) {
/* 4273 */       String pgType = i.next();
/* 4274 */       int sqlType = this.connection.getTypeInfo().getSQLType(pgType);
/* 4275 */       sql = sql + " when typname = " + escapeQuotes(pgType) + " then " + sqlType;
/*      */     } 
/* 4278 */     sql = sql + " else 1111 end from pg_type where oid=t.typbasetype) else null end as base_type from pg_catalog.pg_type t, pg_catalog.pg_namespace n where t.typnamespace = n.oid and n.nspname != 'pg_catalog' and n.nspname != 'pg_toast'";
/* 4284 */     String toAdd = "";
/* 4285 */     if (types != null) {
/* 4287 */       toAdd = toAdd + " and (false ";
/* 4288 */       for (int j = 0; j < types.length; j++) {
/* 4290 */         switch (types[j]) {
/*      */           case 2002:
/* 4293 */             toAdd = toAdd + " or t.typtype = 'c'";
/*      */             break;
/*      */           case 2001:
/* 4296 */             toAdd = toAdd + " or t.typtype = 'd'";
/*      */             break;
/*      */         } 
/*      */       } 
/* 4300 */       toAdd = toAdd + " ) ";
/*      */     } else {
/* 4304 */       toAdd = toAdd + " and t.typtype IN ('c','d') ";
/*      */     } 
/* 4309 */     if (typeNamePattern != null) {
/* 4312 */       int firstQualifier = typeNamePattern.indexOf('.');
/* 4313 */       int secondQualifier = typeNamePattern.lastIndexOf('.');
/* 4315 */       if (firstQualifier != -1) {
/* 4317 */         if (firstQualifier != secondQualifier) {
/* 4320 */           schemaPattern = typeNamePattern.substring(firstQualifier + 1, secondQualifier);
/*      */         } else {
/* 4325 */           schemaPattern = typeNamePattern.substring(0, firstQualifier);
/*      */         } 
/* 4328 */         typeNamePattern = typeNamePattern.substring(secondQualifier + 1);
/*      */       } 
/* 4330 */       toAdd = toAdd + " and t.typname like " + escapeQuotes(typeNamePattern);
/*      */     } 
/* 4334 */     if (schemaPattern != null)
/* 4336 */       toAdd = toAdd + " and n.nspname like " + escapeQuotes(schemaPattern); 
/* 4338 */     sql = sql + toAdd;
/* 4339 */     sql = sql + " order by data_type, type_schem, type_name";
/* 4340 */     ResultSet rs = createMetaDataStatement().executeQuery(sql);
/* 4342 */     return rs;
/*      */   }
/*      */   
/*      */   public Connection getConnection() throws SQLException {
/* 4353 */     return (Connection)this.connection;
/*      */   }
/*      */   
/*      */   public boolean rowChangesAreDetected(int type) throws SQLException {
/* 4360 */     return false;
/*      */   }
/*      */   
/*      */   public boolean rowChangesAreVisible(int type) throws SQLException {
/* 4365 */     return false;
/*      */   }
/*      */   
/*      */   protected Statement createMetaDataStatement() throws SQLException {
/* 4370 */     return this.connection.createStatement(1004, 1007);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2DatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */