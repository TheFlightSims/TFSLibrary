/*     */ package org.postgresql.jdbc3;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2DatabaseMetaData;
/*     */ 
/*     */ public abstract class AbstractJdbc3DatabaseMetaData extends AbstractJdbc2DatabaseMetaData {
/*     */   public AbstractJdbc3DatabaseMetaData(AbstractJdbc3Connection conn) {
/*  20 */     super(conn);
/*     */   }
/*     */   
/*     */   public boolean supportsSavepoints() throws SQLException {
/*  34 */     return this.connection.haveMinimumServerVersion("8.0");
/*     */   }
/*     */   
/*     */   public boolean supportsNamedParameters() throws SQLException {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */   public boolean supportsMultipleOpenResults() throws SQLException {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public boolean supportsGetGeneratedKeys() throws SQLException {
/*  81 */     return this.connection.haveMinimumServerVersion("8.2");
/*     */   }
/*     */   
/*     */   public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
/* 127 */     throw Driver.notImplemented(getClass(), "getSuperTypes(String,String,String)");
/*     */   }
/*     */   
/*     */   public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
/* 166 */     throw Driver.notImplemented(getClass(), "getSuperTables(String,String,String,String)");
/*     */   }
/*     */   
/*     */   public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
/* 243 */     throw Driver.notImplemented(getClass(), "getAttributes(String,String,String,String)");
/*     */   }
/*     */   
/*     */   public boolean supportsResultSetHoldability(int holdability) throws SQLException {
/* 259 */     return true;
/*     */   }
/*     */   
/*     */   public int getResultSetHoldability() throws SQLException {
/* 274 */     return 1;
/*     */   }
/*     */   
/*     */   public int getDatabaseMajorVersion() throws SQLException {
/* 286 */     return this.connection.getServerMajorVersion();
/*     */   }
/*     */   
/*     */   public int getDatabaseMinorVersion() throws SQLException {
/* 298 */     return this.connection.getServerMinorVersion();
/*     */   }
/*     */   
/*     */   public int getJDBCMajorVersion() throws SQLException {
/* 311 */     return 3;
/*     */   }
/*     */   
/*     */   public int getJDBCMinorVersion() throws SQLException {
/* 324 */     return 0;
/*     */   }
/*     */   
/*     */   public int getSQLStateType() throws SQLException {
/* 338 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean locatorsUpdateCopy() throws SQLException {
/* 357 */     return true;
/*     */   }
/*     */   
/*     */   public boolean supportsStatementPooling() throws SQLException {
/* 370 */     return false;
/*     */   }
/*     */   
/*     */   public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
/* 375 */     return getColumns(3, catalog, schemaPattern, tableNamePattern, columnNamePattern);
/*     */   }
/*     */   
/*     */   public ResultSet getSchemas() throws SQLException {
/* 380 */     return getSchemas(3, null, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3DatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */