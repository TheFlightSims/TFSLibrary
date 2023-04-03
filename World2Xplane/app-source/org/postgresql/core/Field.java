/*     */ package org.postgresql.core;
/*     */ 
/*     */ public class Field {
/*     */   public static final int TEXT_FORMAT = 0;
/*     */   
/*     */   public static final int BINARY_FORMAT = 1;
/*     */   
/*     */   private final int length;
/*     */   
/*     */   private final int oid;
/*     */   
/*     */   private final int mod;
/*     */   
/*     */   private final String columnLabel;
/*     */   
/*     */   private String columnName;
/*     */   
/*  28 */   private int format = 0;
/*     */   
/*     */   private final int tableOid;
/*     */   
/*     */   private final int positionInTable;
/*     */   
/*  39 */   private String tableName = "";
/*     */   
/*  40 */   private String schemaName = "";
/*     */   
/*  41 */   private int nullable = 2;
/*     */   
/*     */   private boolean autoIncrement = false;
/*     */   
/*     */   public Field(String name, int oid, int length, int mod) {
/*  53 */     this(name, name, oid, length, mod, 0, 0);
/*     */   }
/*     */   
/*     */   public Field(String name, int oid) {
/*  65 */     this(name, oid, 0, -1);
/*     */   }
/*     */   
/*     */   public Field(String columnLabel, String columnName, int oid, int length, int mod, int tableOid, int positionInTable) {
/*  80 */     this.columnLabel = columnLabel;
/*  81 */     this.columnName = columnName;
/*  82 */     this.oid = oid;
/*  83 */     this.length = length;
/*  84 */     this.mod = mod;
/*  85 */     this.tableOid = tableOid;
/*  86 */     this.positionInTable = positionInTable;
/*     */   }
/*     */   
/*     */   public int getOID() {
/*  94 */     return this.oid;
/*     */   }
/*     */   
/*     */   public int getMod() {
/* 102 */     return this.mod;
/*     */   }
/*     */   
/*     */   public String getColumnLabel() {
/* 110 */     return this.columnLabel;
/*     */   }
/*     */   
/*     */   public int getLength() {
/* 118 */     return this.length;
/*     */   }
/*     */   
/*     */   public int getFormat() {
/* 126 */     return this.format;
/*     */   }
/*     */   
/*     */   public void setFormat(int format) {
/* 134 */     this.format = format;
/*     */   }
/*     */   
/*     */   public int getTableOid() {
/* 142 */     return this.tableOid;
/*     */   }
/*     */   
/*     */   public int getPositionInTable() {
/* 147 */     return this.positionInTable;
/*     */   }
/*     */   
/*     */   public void setNullable(int nullable) {
/* 152 */     this.nullable = nullable;
/*     */   }
/*     */   
/*     */   public int getNullable() {
/* 157 */     return this.nullable;
/*     */   }
/*     */   
/*     */   public void setAutoIncrement(boolean autoIncrement) {
/* 162 */     this.autoIncrement = autoIncrement;
/*     */   }
/*     */   
/*     */   public boolean getAutoIncrement() {
/* 167 */     return this.autoIncrement;
/*     */   }
/*     */   
/*     */   public void setColumnName(String columnName) {
/* 172 */     this.columnName = columnName;
/*     */   }
/*     */   
/*     */   public String getColumnName() {
/* 177 */     return this.columnName;
/*     */   }
/*     */   
/*     */   public void setTableName(String tableName) {
/* 182 */     this.tableName = tableName;
/*     */   }
/*     */   
/*     */   public String getTableName() {
/* 187 */     return this.tableName;
/*     */   }
/*     */   
/*     */   public void setSchemaName(String schemaName) {
/* 192 */     this.schemaName = schemaName;
/*     */   }
/*     */   
/*     */   public String getSchemaName() {
/* 197 */     return this.schemaName;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */