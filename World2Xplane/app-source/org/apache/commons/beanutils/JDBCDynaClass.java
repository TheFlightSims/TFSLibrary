/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Date;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ abstract class JDBCDynaClass implements DynaClass, Serializable {
/*     */   protected boolean lowerCase = true;
/*     */   
/*     */   private boolean useColumnLabel;
/*     */   
/*  58 */   protected DynaProperty[] properties = null;
/*     */   
/*  66 */   protected Map propertiesMap = new HashMap();
/*     */   
/*     */   private Map columnNameXref;
/*     */   
/*     */   public String getName() {
/*  84 */     return getClass().getName();
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/*  99 */     if (name == null)
/* 100 */       throw new IllegalArgumentException("No property name specified"); 
/* 102 */     return (DynaProperty)this.propertiesMap.get(name);
/*     */   }
/*     */   
/*     */   public DynaProperty[] getDynaProperties() {
/* 113 */     return this.properties;
/*     */   }
/*     */   
/*     */   public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
/* 131 */     throw new UnsupportedOperationException("newInstance() not supported");
/*     */   }
/*     */   
/*     */   public void setUseColumnLabel(boolean useColumnLabel) {
/* 141 */     this.useColumnLabel = useColumnLabel;
/*     */   }
/*     */   
/*     */   protected Class loadClass(String className) throws SQLException {
/*     */     try {
/* 158 */       ClassLoader cl = Thread.currentThread().getContextClassLoader();
/* 159 */       if (cl == null)
/* 160 */         cl = getClass().getClassLoader(); 
/* 163 */       return Class.forName(className, false, cl);
/* 164 */     } catch (Exception e) {
/* 165 */       throw new SQLException("Cannot load column class '" + className + "': " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DynaProperty createDynaProperty(ResultSetMetaData metadata, int i) throws SQLException {
/* 185 */     String columnName = null;
/* 186 */     if (this.useColumnLabel)
/* 187 */       columnName = metadata.getColumnLabel(i); 
/* 189 */     if (columnName == null || columnName.trim().length() == 0)
/* 190 */       columnName = metadata.getColumnName(i); 
/* 192 */     String name = this.lowerCase ? columnName.toLowerCase() : columnName;
/* 193 */     if (!name.equals(columnName)) {
/* 194 */       if (this.columnNameXref == null)
/* 195 */         this.columnNameXref = new HashMap(); 
/* 197 */       this.columnNameXref.put(name, columnName);
/*     */     } 
/* 199 */     String className = null;
/*     */     try {
/* 201 */       int sqlType = metadata.getColumnType(i);
/* 202 */       switch (sqlType) {
/*     */         case 91:
/* 204 */           return new DynaProperty(name, Date.class);
/*     */         case 93:
/* 206 */           return new DynaProperty(name, Timestamp.class);
/*     */         case 92:
/* 208 */           return new DynaProperty(name, Time.class);
/*     */       } 
/* 210 */       className = metadata.getColumnClassName(i);
/* 212 */     } catch (SQLException e) {}
/* 219 */     Class clazz = Object.class;
/* 220 */     if (className != null)
/* 221 */       clazz = loadClass(className); 
/* 223 */     return new DynaProperty(name, clazz);
/*     */   }
/*     */   
/*     */   protected void introspect(ResultSet resultSet) throws SQLException {
/* 241 */     ArrayList list = new ArrayList();
/* 242 */     ResultSetMetaData metadata = resultSet.getMetaData();
/* 243 */     int n = metadata.getColumnCount();
/*     */     int i;
/* 244 */     for (i = 1; i <= n; i++) {
/* 245 */       DynaProperty dynaProperty = createDynaProperty(metadata, i);
/* 246 */       if (dynaProperty != null)
/* 247 */         list.add(dynaProperty); 
/*     */     } 
/* 252 */     this.properties = list.<DynaProperty>toArray(new DynaProperty[list.size()]);
/* 254 */     for (i = 0; i < this.properties.length; i++)
/* 255 */       this.propertiesMap.put(this.properties[i].getName(), this.properties[i]); 
/*     */   }
/*     */   
/*     */   protected Object getObject(ResultSet resultSet, String name) throws SQLException {
/* 270 */     DynaProperty property = getDynaProperty(name);
/* 271 */     if (property == null)
/* 272 */       throw new IllegalArgumentException("Invalid name '" + name + "'"); 
/* 274 */     String columnName = getColumnName(name);
/* 275 */     Class type = property.getType();
/* 278 */     if (type.equals(Date.class))
/* 279 */       return resultSet.getDate(columnName); 
/* 283 */     if (type.equals(Timestamp.class))
/* 284 */       return resultSet.getTimestamp(columnName); 
/* 288 */     if (type.equals(Time.class))
/* 289 */       return resultSet.getTime(columnName); 
/* 292 */     return resultSet.getObject(columnName);
/*     */   }
/*     */   
/*     */   protected String getColumnName(String name) {
/* 303 */     if (this.columnNameXref != null && this.columnNameXref.containsKey(name))
/* 304 */       return (String)this.columnNameXref.get(name); 
/* 306 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\JDBCDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */