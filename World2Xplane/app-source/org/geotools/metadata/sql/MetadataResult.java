/*     */ package org.geotools.metadata.sql;
/*     */ 
/*     */ import java.sql.Array;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ final class MetadataResult {
/*     */   private final String tableName;
/*     */   
/*     */   private final PreparedStatement statement;
/*     */   
/*     */   private ResultSet results;
/*     */   
/*     */   private String identifier;
/*     */   
/*     */   public MetadataResult(Connection connection, String query, String tableName) throws SQLException {
/*  77 */     this.tableName = tableName;
/*  78 */     int index = query.indexOf('?');
/*  79 */     if (index < 0)
/*  81 */       throw new SQLException("Invalid query"); 
/*  83 */     StringBuilder buffer = new StringBuilder(query);
/*  84 */     buffer.replace(index, index + 1, tableName);
/*  85 */     this.statement = connection.prepareStatement(buffer.toString());
/*     */   }
/*     */   
/*     */   private ResultSet getResultSet(String identifier) throws SQLException {
/*  96 */     if (this.results != null) {
/*  97 */       if (this.identifier.equals(identifier))
/*  98 */         return this.results; 
/* 100 */       if (this.results.next())
/* 102 */         Logging.getLogger(MetadataResult.class).warning("Duplicate identifier: " + identifier); 
/* 104 */       this.results.close();
/* 105 */       this.results = null;
/*     */     } 
/* 107 */     this.identifier = identifier;
/* 108 */     this.statement.setString(1, identifier);
/* 109 */     this.results = this.statement.executeQuery();
/* 110 */     if (!this.results.next()) {
/* 111 */       this.results.close();
/* 112 */       this.results = null;
/* 113 */       throw new SQLException("Metadata not found: \"" + identifier + "\" in table \"" + this.tableName + '"');
/*     */     } 
/* 116 */     return this.results;
/*     */   }
/*     */   
/*     */   public Object getObject(String identifier, String columnName) throws SQLException {
/* 128 */     return getResultSet(identifier).getObject(columnName);
/*     */   }
/*     */   
/*     */   public Object getArray(String identifier, String columnName) throws SQLException {
/* 140 */     Array array = getResultSet(identifier).getArray(columnName);
/* 141 */     return (array != null) ? array.getArray() : null;
/*     */   }
/*     */   
/*     */   public int getInt(String identifier, String columnName) throws SQLException {
/* 153 */     return getResultSet(identifier).getInt(columnName);
/*     */   }
/*     */   
/*     */   public String getString(String identifier, String columnName) throws SQLException {
/* 165 */     return getResultSet(identifier).getString(columnName);
/*     */   }
/*     */   
/*     */   public String getString(String code) throws SQLException {
/* 177 */     return getResultSet(code).getString(1);
/*     */   }
/*     */   
/*     */   public boolean wasNull() throws SQLException {
/* 184 */     return this.results.wasNull();
/*     */   }
/*     */   
/*     */   public void close() throws SQLException {
/* 194 */     if (this.results != null) {
/* 195 */       this.results.close();
/* 196 */       this.results = null;
/*     */     } 
/* 198 */     this.statement.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\sql\MetadataResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */