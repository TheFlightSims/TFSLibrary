/*     */ package org.postgresql.jdbc4;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.RowIdLifetime;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Vector;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseStatement;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.jdbc3.AbstractJdbc3Connection;
/*     */ import org.postgresql.jdbc3.AbstractJdbc3DatabaseMetaData;
/*     */ 
/*     */ public abstract class AbstractJdbc4DatabaseMetaData extends AbstractJdbc3DatabaseMetaData {
/*     */   public AbstractJdbc4DatabaseMetaData(AbstractJdbc4Connection conn) {
/*  22 */     super((AbstractJdbc3Connection)conn);
/*     */   }
/*     */   
/*     */   public RowIdLifetime getRowIdLifetime() throws SQLException {
/*  27 */     throw Driver.notImplemented(getClass(), "getRowIdLifetime()");
/*     */   }
/*     */   
/*     */   public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
/*  32 */     return getSchemas(4, catalog, schemaPattern);
/*     */   }
/*     */   
/*     */   public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
/*  37 */     return true;
/*     */   }
/*     */   
/*     */   public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public ResultSet getClientInfoProperties() throws SQLException {
/*  47 */     Field[] f = new Field[4];
/*  48 */     f[0] = new Field("NAME", 1043);
/*  49 */     f[1] = new Field("MAX_LEN", 23);
/*  50 */     f[2] = new Field("DEFAULT_VALUE", 1043);
/*  51 */     f[3] = new Field("DESCRIPTION", 1043);
/*  53 */     Vector<byte[][]> v = new Vector();
/*  55 */     if (this.connection.haveMinimumServerVersion("9.0")) {
/*  56 */       byte[][] tuple = new byte[4][];
/*  57 */       tuple[0] = this.connection.encodeString("ApplicationName");
/*  58 */       tuple[1] = this.connection.encodeString(Integer.toString(getMaxNameLength()));
/*  59 */       tuple[2] = this.connection.encodeString("");
/*  60 */       tuple[3] = this.connection.encodeString("The name of the application currently utilizing the connection.");
/*  61 */       v.addElement(tuple);
/*     */     } 
/*  64 */     return ((BaseStatement)createMetaDataStatement()).createDriverResultSet(f, v);
/*     */   }
/*     */   
/*     */   public boolean providesQueryObjectGenerator() throws SQLException {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/*  74 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*     */   }
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/*  79 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*     */   }
/*     */   
/*     */   public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
/*  84 */     throw Driver.notImplemented(getClass(), "getFunction(String, String, String)");
/*     */   }
/*     */   
/*     */   public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
/*  89 */     throw Driver.notImplemented(getClass(), "getFunctionColumns(String, String, String, String)");
/*     */   }
/*     */   
/*     */   public int getJDBCMajorVersion() throws SQLException {
/*  94 */     return 4;
/*     */   }
/*     */   
/*     */   public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/*  99 */     return getColumns(4, catalog, schemaPattern, tableNamePattern, columnNamePattern);
/*     */   }
/*     */   
/*     */   public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
/* 104 */     return getProcedures(4, catalog, schemaPattern, procedureNamePattern);
/*     */   }
/*     */   
/*     */   public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
/* 109 */     return getProcedureColumns(4, catalog, schemaPattern, procedureNamePattern, columnNamePattern);
/*     */   }
/*     */   
/*     */   public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/* 114 */     throw Driver.notImplemented(getClass(), "getPseudoColumns(String, String, String, String)");
/*     */   }
/*     */   
/*     */   public boolean generatedKeyAlwaysReturned() throws SQLException {
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4DatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */