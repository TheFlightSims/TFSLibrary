/*     */ package ch.qos.logback.core.db.dialect;
/*     */ 
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class DBUtil extends ContextAwareBase {
/*     */   private static final String POSTGRES_PART = "postgresql";
/*     */   
/*     */   private static final String MYSQL_PART = "mysql";
/*     */   
/*     */   private static final String ORACLE_PART = "oracle";
/*     */   
/*     */   private static final String MSSQL_PART = "microsoft";
/*     */   
/*     */   private static final String HSQL_PART = "hsql";
/*     */   
/*     */   private static final String H2_PART = "h2";
/*     */   
/*     */   private static final String SYBASE_SQLANY_PART = "sql anywhere";
/*     */   
/*     */   private static final String SQLITE_PART = "sqlite";
/*     */   
/*     */   public static SQLDialectCode discoverSQLDialect(DatabaseMetaData meta) {
/*  38 */     SQLDialectCode dialectCode = SQLDialectCode.UNKNOWN_DIALECT;
/*     */     try {
/*  42 */       String dbName = meta.getDatabaseProductName().toLowerCase();
/*  44 */       if (dbName.indexOf("postgresql") != -1)
/*  45 */         return SQLDialectCode.POSTGRES_DIALECT; 
/*  46 */       if (dbName.indexOf("mysql") != -1)
/*  47 */         return SQLDialectCode.MYSQL_DIALECT; 
/*  48 */       if (dbName.indexOf("oracle") != -1)
/*  49 */         return SQLDialectCode.ORACLE_DIALECT; 
/*  50 */       if (dbName.indexOf("microsoft") != -1)
/*  51 */         return SQLDialectCode.MSSQL_DIALECT; 
/*  52 */       if (dbName.indexOf("hsql") != -1)
/*  53 */         return SQLDialectCode.HSQL_DIALECT; 
/*  54 */       if (dbName.indexOf("h2") != -1)
/*  55 */         return SQLDialectCode.H2_DIALECT; 
/*  56 */       if (dbName.indexOf("sql anywhere") != -1)
/*  57 */         return SQLDialectCode.SYBASE_SQLANYWHERE_DIALECT; 
/*  58 */       if (dbName.indexOf("sqlite") != -1)
/*  59 */         return SQLDialectCode.SQLITE_DIALECT; 
/*  61 */       return SQLDialectCode.UNKNOWN_DIALECT;
/*  63 */     } catch (SQLException sqle) {
/*  67 */       return dialectCode;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SQLDialect getDialectFromCode(SQLDialectCode sqlDialectType) {
/*  71 */     SQLDialect sqlDialect = null;
/*  73 */     switch (sqlDialectType) {
/*     */       case POSTGRES_DIALECT:
/*  75 */         sqlDialect = new PostgreSQLDialect();
/*     */         break;
/*     */       case MYSQL_DIALECT:
/*  79 */         sqlDialect = new MySQLDialect();
/*     */         break;
/*     */       case ORACLE_DIALECT:
/*  83 */         sqlDialect = new OracleDialect();
/*     */         break;
/*     */       case MSSQL_DIALECT:
/*  87 */         sqlDialect = new MsSQLDialect();
/*     */         break;
/*     */       case HSQL_DIALECT:
/*  91 */         sqlDialect = new HSQLDBDialect();
/*     */         break;
/*     */       case H2_DIALECT:
/*  95 */         sqlDialect = new H2Dialect();
/*     */         break;
/*     */       case SYBASE_SQLANYWHERE_DIALECT:
/*  99 */         sqlDialect = new SybaseSqlAnywhereDialect();
/*     */         break;
/*     */       case SQLITE_DIALECT:
/* 103 */         sqlDialect = new SQLiteDialect();
/*     */         break;
/*     */     } 
/* 106 */     return sqlDialect;
/*     */   }
/*     */   
/*     */   public boolean supportsGetGeneratedKeys(DatabaseMetaData meta) {
/*     */     try {
/* 119 */       return ((Boolean)DatabaseMetaData.class.getMethod("supportsGetGeneratedKeys", (Class[])null).invoke(meta, (Object[])null)).booleanValue();
/* 122 */     } catch (Throwable e) {
/* 123 */       addInfo("Could not call supportsGetGeneratedKeys method. This may be recoverable");
/* 124 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean supportsBatchUpdates(DatabaseMetaData meta) {
/*     */     try {
/* 135 */       return meta.supportsBatchUpdates();
/* 136 */     } catch (Throwable e) {
/* 137 */       addInfo("Missing DatabaseMetaData.supportsBatchUpdates method.");
/* 138 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\DBUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */