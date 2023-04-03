/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import org.postgresql.PGResultSetMetaData;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc2ResultSetMetaData implements PGResultSetMetaData {
/*     */   protected final BaseConnection connection;
/*     */   
/*     */   protected final Field[] fields;
/*     */   
/*     */   private boolean fieldInfoFetched;
/*     */   
/*     */   public AbstractJdbc2ResultSetMetaData(BaseConnection connection, Field[] fields) {
/*  35 */     this.connection = connection;
/*  36 */     this.fields = fields;
/*  37 */     this.fieldInfoFetched = false;
/*     */   }
/*     */   
/*     */   public int getColumnCount() throws SQLException {
/*  48 */     return this.fields.length;
/*     */   }
/*     */   
/*     */   public boolean isAutoIncrement(int column) throws SQLException {
/*  61 */     fetchFieldMetaData();
/*  62 */     Field field = getField(column);
/*  63 */     return field.getAutoIncrement();
/*     */   }
/*     */   
/*     */   public boolean isCaseSensitive(int column) throws SQLException {
/*  76 */     Field field = getField(column);
/*  77 */     return this.connection.getTypeInfo().isCaseSensitive(field.getOID());
/*     */   }
/*     */   
/*     */   public boolean isSearchable(int column) throws SQLException {
/*  94 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isCurrency(int column) throws SQLException {
/* 108 */     String type_name = getPGType(column);
/* 110 */     return (type_name.equals("cash") || type_name.equals("money"));
/*     */   }
/*     */   
/*     */   public int isNullable(int column) throws SQLException {
/* 122 */     fetchFieldMetaData();
/* 123 */     Field field = getField(column);
/* 124 */     return field.getNullable();
/*     */   }
/*     */   
/*     */   public boolean isSigned(int column) throws SQLException {
/* 138 */     Field field = getField(column);
/* 139 */     return this.connection.getTypeInfo().isSigned(field.getOID());
/*     */   }
/*     */   
/*     */   public int getColumnDisplaySize(int column) throws SQLException {
/* 151 */     Field field = getField(column);
/* 152 */     return this.connection.getTypeInfo().getDisplaySize(field.getOID(), field.getMod());
/*     */   }
/*     */   
/*     */   public String getColumnLabel(int column) throws SQLException {
/* 162 */     Field field = getField(column);
/* 163 */     return field.getColumnLabel();
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) throws SQLException {
/* 175 */     return getColumnLabel(column);
/*     */   }
/*     */   
/*     */   public String getBaseColumnName(int column) throws SQLException {
/* 179 */     fetchFieldMetaData();
/* 180 */     Field field = getField(column);
/* 181 */     return field.getColumnName();
/*     */   }
/*     */   
/*     */   public String getSchemaName(int column) throws SQLException {
/* 191 */     return "";
/*     */   }
/*     */   
/*     */   private void fetchFieldMetaData() throws SQLException {
/* 195 */     if (this.fieldInfoFetched)
/*     */       return; 
/* 198 */     this.fieldInfoFetched = true;
/* 200 */     StringBuffer sql = new StringBuffer();
/* 201 */     sql.append("SELECT c.oid, a.attnum, a.attname, c.relname, n.nspname, ");
/* 202 */     sql.append("a.attnotnull OR (t.typtype = 'd' AND t.typnotnull), ");
/* 203 */     sql.append("pg_catalog.pg_get_expr(d.adbin, d.adrelid) LIKE '%nextval(%' ");
/* 204 */     sql.append("FROM pg_catalog.pg_class c ");
/* 205 */     sql.append("JOIN pg_catalog.pg_namespace n ON (c.relnamespace = n.oid) ");
/* 206 */     sql.append("JOIN pg_catalog.pg_attribute a ON (c.oid = a.attrelid) ");
/* 207 */     sql.append("JOIN pg_catalog.pg_type t ON (a.atttypid = t.oid) ");
/* 208 */     sql.append("LEFT JOIN pg_catalog.pg_attrdef d ON (d.adrelid = a.attrelid AND d.adnum = a.attnum) ");
/* 209 */     sql.append("JOIN (");
/* 214 */     boolean hasSourceInfo = false;
/* 215 */     for (int i = 0; i < this.fields.length; i++) {
/* 216 */       if (this.fields[i].getTableOid() != 0) {
/* 219 */         if (hasSourceInfo)
/* 220 */           sql.append(" UNION ALL "); 
/* 222 */         sql.append("SELECT ");
/* 223 */         sql.append(this.fields[i].getTableOid());
/* 224 */         if (!hasSourceInfo)
/* 225 */           sql.append(" AS oid "); 
/* 226 */         sql.append(", ");
/* 227 */         sql.append(this.fields[i].getPositionInTable());
/* 228 */         if (!hasSourceInfo)
/* 229 */           sql.append(" AS attnum"); 
/* 231 */         if (!hasSourceInfo)
/* 232 */           hasSourceInfo = true; 
/*     */       } 
/*     */     } 
/* 234 */     sql.append(") vals ON (c.oid = vals.oid AND a.attnum = vals.attnum) ");
/* 236 */     if (!hasSourceInfo)
/*     */       return; 
/* 239 */     Statement stmt = this.connection.createStatement();
/* 240 */     ResultSet rs = stmt.executeQuery(sql.toString());
/* 241 */     while (rs.next()) {
/* 242 */       int table = rs.getInt(1);
/* 243 */       int column = rs.getInt(2);
/* 244 */       String columnName = rs.getString(3);
/* 245 */       String tableName = rs.getString(4);
/* 246 */       String schemaName = rs.getString(5);
/* 247 */       int nullable = rs.getBoolean(6) ? 0 : 1;
/* 248 */       boolean autoIncrement = rs.getBoolean(7);
/* 249 */       for (int j = 0; j < this.fields.length; j++) {
/* 250 */         if (this.fields[j].getTableOid() == table && this.fields[j].getPositionInTable() == column) {
/* 251 */           this.fields[j].setColumnName(columnName);
/* 252 */           this.fields[j].setTableName(tableName);
/* 253 */           this.fields[j].setSchemaName(schemaName);
/* 254 */           this.fields[j].setNullable(nullable);
/* 255 */           this.fields[j].setAutoIncrement(autoIncrement);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getBaseSchemaName(int column) throws SQLException {
/* 263 */     fetchFieldMetaData();
/* 264 */     Field field = getField(column);
/* 265 */     return field.getSchemaName();
/*     */   }
/*     */   
/*     */   public int getPrecision(int column) throws SQLException {
/* 277 */     Field field = getField(column);
/* 278 */     return this.connection.getTypeInfo().getPrecision(field.getOID(), field.getMod());
/*     */   }
/*     */   
/*     */   public int getScale(int column) throws SQLException {
/* 291 */     Field field = getField(column);
/* 292 */     return this.connection.getTypeInfo().getScale(field.getOID(), field.getMod());
/*     */   }
/*     */   
/*     */   public String getTableName(int column) throws SQLException {
/* 302 */     return "";
/*     */   }
/*     */   
/*     */   public String getBaseTableName(int column) throws SQLException {
/* 307 */     fetchFieldMetaData();
/* 308 */     Field field = getField(column);
/* 309 */     return field.getTableName();
/*     */   }
/*     */   
/*     */   public String getCatalogName(int column) throws SQLException {
/* 323 */     return "";
/*     */   }
/*     */   
/*     */   public int getColumnType(int column) throws SQLException {
/* 337 */     return getSQLType(column);
/*     */   }
/*     */   
/*     */   public String getColumnTypeName(int column) throws SQLException {
/* 349 */     String type = getPGType(column);
/* 350 */     if (isAutoIncrement(column)) {
/* 351 */       if ("int4".equals(type))
/* 352 */         return "serial"; 
/* 353 */       if ("int8".equals(type))
/* 354 */         return "bigserial"; 
/*     */     } 
/* 358 */     return type;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly(int column) throws SQLException {
/* 373 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWritable(int column) throws SQLException {
/* 388 */     return !isReadOnly(column);
/*     */   }
/*     */   
/*     */   public boolean isDefinitelyWritable(int column) throws SQLException {
/* 403 */     return false;
/*     */   }
/*     */   
/*     */   protected Field getField(int columnIndex) throws SQLException {
/* 421 */     if (columnIndex < 1 || columnIndex > this.fields.length)
/* 422 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(columnIndex), new Integer(this.fields.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/* 423 */     return this.fields[columnIndex - 1];
/*     */   }
/*     */   
/*     */   protected String getPGType(int columnIndex) throws SQLException {
/* 428 */     return this.connection.getTypeInfo().getPGType(getField(columnIndex).getOID());
/*     */   }
/*     */   
/*     */   protected int getSQLType(int columnIndex) throws SQLException {
/* 433 */     return this.connection.getTypeInfo().getSQLType(getField(columnIndex).getOID());
/*     */   }
/*     */   
/*     */   public String getColumnClassName(int column) throws SQLException {
/* 457 */     Field field = getField(column);
/* 458 */     String result = this.connection.getTypeInfo().getJavaClass(field.getOID());
/* 460 */     if (result != null)
/* 461 */       return result; 
/* 463 */     int sqlType = getSQLType(column);
/* 464 */     switch (sqlType) {
/*     */       case 2003:
/* 466 */         return "java.sql.Array";
/*     */     } 
/* 468 */     String type = getPGType(column);
/* 469 */     if ("unknown".equals(type))
/* 470 */       return "java.lang.String"; 
/* 472 */     return "java.lang.Object";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2ResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */