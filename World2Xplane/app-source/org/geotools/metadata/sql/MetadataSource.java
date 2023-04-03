/*     */ package org.geotools.metadata.sql;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.MetaData;
/*     */ import org.opengis.util.CodeList;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class MetadataSource {
/*  80 */   final String metadataPackage = "org.opengis.metadata.";
/*     */   
/*     */   private final Connection connection;
/*     */   
/*  92 */   private final String query = "SELECT * FROM metadata.\"?\" WHERE id=?";
/*     */   
/*  99 */   private final String codeQuery = "SELECT name FROM metadata.\"?\" WHERE code=?";
/*     */   
/* 105 */   private final Map<Class<?>, MetadataResult> statements = new HashMap<Class<?>, MetadataResult>();
/*     */   
/* 112 */   private final Properties geoApiToIso = new Properties();
/*     */   
/* 117 */   private final Properties collectionTypes = new Properties();
/*     */   
/*     */   private final ClassLoader loader;
/*     */   
/*     */   public MetadataSource(Connection connection) {
/* 130 */     this.connection = connection;
/*     */     try {
/* 132 */       InputStream in = MetaData.class.getResourceAsStream("GeoAPI_to_ISO.properties");
/* 133 */       this.geoApiToIso.load(in);
/* 134 */       in.close();
/* 135 */       in = MetaData.class.getResourceAsStream("CollectionTypes.properties");
/* 137 */       if (in != null) {
/* 138 */         this.collectionTypes.load(in);
/* 139 */         in.close();
/*     */       } 
/* 141 */     } catch (IOException exception) {
/* 148 */       throw new MetadataException("Can't read resources.", exception);
/*     */     } 
/* 150 */     this.loader = getClass().getClassLoader();
/*     */   }
/*     */   
/*     */   public synchronized Object getEntry(Class<?> type, String identifier) throws SQLException {
/* 170 */     if (CodeList.class.isAssignableFrom(type))
/* 171 */       return getCodeList(type, identifier); 
/* 173 */     return Proxy.newProxyInstance(this.loader, new Class[] { type }, new MetadataEntity(identifier, this));
/*     */   }
/*     */   
/*     */   final synchronized Object getValue(Class<?> type, Method method, String identifier) throws SQLException {
/* 189 */     String className = getClassName(type);
/* 190 */     MetadataResult result = this.statements.get(type);
/* 191 */     if (result == null) {
/* 192 */       result = new MetadataResult(this.connection, "SELECT * FROM metadata.\"?\" WHERE id=?", getTableName(className));
/* 193 */       this.statements.put(type, result);
/*     */     } 
/* 195 */     String columnName = getColumnName(className, method);
/* 196 */     Class<?> valueType = method.getReturnType();
/* 202 */     if (Collection.class.isAssignableFrom(valueType)) {
/*     */       Collection<Object> collection;
/* 204 */       if (List.class.isAssignableFrom(valueType)) {
/* 205 */         collection = new ArrayList();
/* 206 */       } else if (SortedSet.class.isAssignableFrom(valueType)) {
/* 207 */         collection = new TreeSet();
/*     */       } else {
/* 209 */         collection = new LinkedHashSet();
/*     */       } 
/* 211 */       assert valueType.isAssignableFrom(collection.getClass());
/* 212 */       Object elements = result.getArray(identifier, columnName);
/* 213 */       if (elements != null) {
/* 214 */         Class<?> elementType = getElementType(className, method);
/* 215 */         boolean isMetadata = isMetadata(elementType);
/* 216 */         int length = Array.getLength(elements);
/* 217 */         for (int i = 0; i < length; i++)
/* 218 */           collection.add(isMetadata ? getEntry(elementType, Array.get(elements, i).toString()) : convert(elementType, Array.get(elements, i))); 
/*     */       } 
/* 222 */       return collection;
/*     */     } 
/* 228 */     if (valueType.isInterface() && isMetadata(valueType)) {
/* 229 */       String foreigner = result.getString(identifier, columnName);
/* 230 */       return result.wasNull() ? null : getEntry(valueType, foreigner);
/*     */     } 
/* 232 */     if (CodeList.class.isAssignableFrom(valueType)) {
/* 233 */       String foreigner = result.getString(identifier, columnName);
/* 234 */       return result.wasNull() ? null : getCodeList(valueType, foreigner);
/*     */     } 
/* 240 */     return convert(valueType, result.getObject(identifier, columnName));
/*     */   }
/*     */   
/*     */   private boolean isMetadata(Class valueType) {
/* 247 */     return valueType.getName().startsWith("org.opengis.metadata.");
/*     */   }
/*     */   
/*     */   private static Object convert(Class<?> valueType, Object value) {
/* 256 */     if (value != null && !valueType.isAssignableFrom(value.getClass())) {
/* 257 */       if (InternationalString.class.isAssignableFrom(valueType))
/* 258 */         return new SimpleInternationalString(value.toString()); 
/* 260 */       if (URL.class.isAssignableFrom(valueType))
/*     */         try {
/* 261 */           return new URL(value.toString());
/* 262 */         } catch (MalformedURLException exception) {
/* 264 */           throw new MetadataException("Illegal value.", exception);
/*     */         }  
/* 266 */       if (URI.class.isAssignableFrom(valueType))
/*     */         try {
/* 267 */           return new URI(value.toString());
/* 268 */         } catch (URISyntaxException exception) {
/* 270 */           throw new MetadataException("Illegal value.", exception);
/*     */         }  
/*     */     } 
/* 273 */     return value;
/*     */   }
/*     */   
/*     */   private CodeList getCodeList(Class<?> type, String identifier) throws SQLException {
/*     */     byte b;
/*     */     boolean bool;
/*     */     CodeList[] arrayOfCodeList;
/* 286 */     assert Thread.holdsLock(this);
/* 287 */     String className = getClassName(type);
/*     */     try {
/* 291 */       b = Integer.parseInt(identifier);
/* 292 */       bool = true;
/* 293 */     } catch (NumberFormatException exception) {
/* 294 */       b = 0;
/* 295 */       bool = false;
/*     */     } 
/* 300 */     if (bool) {
/* 301 */       MetadataResult result = this.statements.get(type);
/* 302 */       if (result == null) {
/* 303 */         result = new MetadataResult(this.connection, "SELECT name FROM metadata.\"?\" WHERE code=?", getTableName(className));
/* 304 */         this.statements.put(type, result);
/*     */       } 
/* 306 */       identifier = result.getString(identifier);
/*     */     } 
/*     */     try {
/* 315 */       arrayOfCodeList = (CodeList[])type.getMethod("values", (Class[])null).invoke(null, (Object[])null);
/* 317 */     } catch (NoSuchMethodException exception) {
/* 318 */       throw new MetadataException("Can't read code list.", exception);
/* 319 */     } catch (IllegalAccessException exception) {
/* 320 */       throw new MetadataException("Can't read code list.", exception);
/* 321 */     } catch (InvocationTargetException exception) {
/* 322 */       throw new MetadataException("Can't read code list.", exception);
/*     */     } 
/* 325 */     StringBuilder candidateName = new StringBuilder(className);
/* 326 */     candidateName.append('.');
/* 327 */     int base = candidateName.length();
/* 328 */     if (b >= 1 && b < arrayOfCodeList.length) {
/* 329 */       CodeList<?> candidate = arrayOfCodeList[b - 1];
/* 330 */       candidateName.append(candidate.name());
/* 331 */       if (identifier.equals(this.geoApiToIso.getProperty(candidateName.toString())))
/* 332 */         return candidate; 
/*     */     } 
/* 342 */     for (int i = 0; i < arrayOfCodeList.length; i++) {
/* 343 */       CodeList<?> candidate = arrayOfCodeList[i];
/* 344 */       candidateName.setLength(base);
/* 345 */       candidateName.append(candidate.name());
/* 346 */       if (identifier.equals(this.geoApiToIso.getProperty(candidateName.toString())))
/* 347 */         return candidate; 
/*     */     } 
/* 351 */     throw new SQLException("Unknow code list: \"" + identifier + "\" in table \"" + getTableName(className) + '"');
/*     */   }
/*     */   
/*     */   private static String getClassName(Class<?> type) {
/* 360 */     String className = type.getName();
/* 361 */     return className.substring(className.lastIndexOf('.') + 1);
/*     */   }
/*     */   
/*     */   private String getTableName(String className) {
/* 369 */     String tableName = this.geoApiToIso.getProperty(className);
/* 370 */     return (tableName != null) ? tableName : className;
/*     */   }
/*     */   
/*     */   private String getColumnName(String className, Method method) {
/* 377 */     String methodName = method.getName();
/* 378 */     String columnName = this.geoApiToIso.getProperty(className + '.' + methodName);
/* 379 */     return (columnName != null) ? columnName : methodName;
/*     */   }
/*     */   
/*     */   private Class getElementType(String className, Method method) {
/* 386 */     String key = className + '.' + method.getName();
/* 387 */     String typeName = this.collectionTypes.getProperty(key);
/* 388 */     Exception cause = null;
/* 389 */     if (typeName != null)
/*     */       try {
/* 390 */         return Class.forName(typeName);
/* 391 */       } catch (ClassNotFoundException exception) {
/* 392 */         cause = exception;
/*     */       }  
/* 395 */     MetadataException e = new MetadataException("Unknow element type for " + key);
/* 396 */     if (cause != null)
/* 397 */       e.initCause(cause); 
/* 399 */     throw e;
/*     */   }
/*     */   
/*     */   public synchronized void close() throws SQLException {
/* 406 */     for (Iterator<MetadataResult> it = this.statements.values().iterator(); it.hasNext(); ) {
/* 407 */       ((MetadataResult)it.next()).close();
/* 408 */       it.remove();
/*     */     } 
/* 410 */     this.connection.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\sql\MetadataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */