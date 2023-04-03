/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ final class AuthorityCodes extends AbstractSet<String> implements Serializable {
/*     */   private static final long serialVersionUID = 7105664579449680562L;
/*     */   
/*     */   private final DirectEpsgFactory factory;
/*     */   
/*     */   public final Class<?> type;
/*     */   
/*     */   private final boolean isProjection;
/*     */   
/*     */   private transient java.util.Map<String, String> asMap;
/*     */   
/*     */   final String sqlAll;
/*     */   
/*     */   private final String sqlSingle;
/*     */   
/*     */   private transient PreparedStatement queryAll;
/*     */   
/*     */   private transient PreparedStatement querySingle;
/*     */   
/* 115 */   private int size = -1;
/*     */   
/*     */   public AuthorityCodes(TableInfo table, Class<?> type, DirectEpsgFactory factory) {
/* 128 */     this.factory = factory;
/* 129 */     StringBuilder buffer = new StringBuilder("SELECT ");
/* 130 */     buffer.append(table.codeColumn);
/* 131 */     if (table.nameColumn != null)
/* 132 */       buffer.append(", ").append(table.nameColumn); 
/* 134 */     buffer.append(" FROM ").append(table.table);
/* 135 */     boolean hasWhere = false;
/* 136 */     Class<?> tableType = table.type;
/* 137 */     if (table.typeColumn != null) {
/* 138 */       for (int i = 0; i < table.subTypes.length; i++) {
/* 139 */         Class<?> candidate = table.subTypes[i];
/* 140 */         if (candidate.isAssignableFrom(type)) {
/* 141 */           buffer.append(" WHERE (").append(table.typeColumn).append(" LIKE '").append(table.typeNames[i]).append("%'");
/* 143 */           hasWhere = true;
/* 144 */           tableType = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/* 148 */       if (hasWhere)
/* 149 */         buffer.append(')'); 
/*     */     } 
/* 152 */     this.type = tableType;
/* 153 */     this.isProjection = Projection.class.isAssignableFrom(tableType);
/* 154 */     int length = buffer.length();
/* 155 */     buffer.append(" ORDER BY ").append(table.codeColumn);
/* 156 */     this.sqlAll = factory.adaptSQL(buffer.toString());
/* 157 */     buffer.setLength(length);
/* 158 */     buffer.append(hasWhere ? " AND " : " WHERE ").append(table.codeColumn).append(" = ?");
/* 159 */     this.sqlSingle = factory.adaptSQL(buffer.toString());
/*     */   }
/*     */   
/*     */   protected PreparedStatement validateStatement(PreparedStatement stmt, String sql) throws SQLException {
/* 163 */     Connection conn = null;
/* 164 */     if (stmt != null)
/*     */       try {
/* 166 */         conn = stmt.getConnection();
/* 167 */       } catch (SQLException sqle) {
/* 169 */         stmt = null;
/*     */       }  
/* 172 */     if (conn != null && !this.factory.isConnectionValid(conn))
/* 173 */       stmt = null; 
/* 175 */     if (stmt == null)
/* 176 */       stmt = this.factory.getConnection().prepareStatement(sql); 
/* 178 */     return stmt;
/*     */   }
/*     */   
/*     */   private ResultSet getAll() throws SQLException {
/* 185 */     assert Thread.holdsLock(this);
/* 186 */     this.queryAll = validateStatement(this.queryAll, this.sqlAll);
/*     */     try {
/* 188 */       return this.queryAll.executeQuery();
/* 189 */     } catch (SQLException ignore) {
/* 198 */       this.queryAll.close();
/* 199 */       this.queryAll = null;
/* 200 */       recoverableException("getAll", ignore);
/* 202 */       this.queryAll = validateStatement(this.queryAll, this.sqlAll);
/* 203 */       return this.queryAll.executeQuery();
/*     */     } 
/*     */   }
/*     */   
/*     */   private ResultSet getSingle(Object code) throws SQLException {
/* 210 */     assert Thread.holdsLock(this);
/* 211 */     this.querySingle = validateStatement(this.querySingle, this.sqlSingle);
/* 212 */     this.querySingle.setString(1, code.toString());
/* 213 */     return this.querySingle.executeQuery();
/*     */   }
/*     */   
/*     */   private boolean isAcceptable(ResultSet results) throws SQLException {
/* 221 */     if (!this.isProjection)
/* 222 */       return true; 
/* 224 */     String code = results.getString(1);
/* 225 */     synchronized (this.factory) {
/* 226 */       return this.factory.isProjection(code);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isAcceptable(String code) throws SQLException {
/* 235 */     if (!this.isProjection)
/* 236 */       return true; 
/* 238 */     synchronized (this.factory) {
/* 239 */       return this.factory.isProjection(code);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized boolean isEmpty() {
/* 249 */     if (this.size != -1)
/* 250 */       return (this.size == 0); 
/* 252 */     boolean empty = true;
/*     */     try {
/* 254 */       ResultSet results = getAll();
/* 255 */       while (results.next()) {
/* 256 */         if (isAcceptable(results)) {
/* 257 */           empty = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 261 */       results.close();
/* 262 */     } catch (SQLException exception) {
/* 263 */       unexpectedException("isEmpty", exception);
/*     */     } 
/* 265 */     this.size = empty ? 0 : -2;
/* 266 */     return empty;
/*     */   }
/*     */   
/*     */   public synchronized int size() {
/* 273 */     if (this.size >= 0)
/* 274 */       return this.size; 
/* 276 */     int count = 0;
/*     */     try {
/* 278 */       ResultSet results = getAll();
/* 279 */       while (results.next()) {
/* 280 */         if (isAcceptable(results))
/* 281 */           count++; 
/*     */       } 
/* 284 */       results.close();
/* 285 */     } catch (SQLException exception) {
/* 286 */       unexpectedException("size", exception);
/*     */     } 
/* 288 */     this.size = count;
/* 289 */     return count;
/*     */   }
/*     */   
/*     */   public synchronized boolean contains(Object code) {
/* 297 */     boolean exists = false;
/* 298 */     if (code != null)
/*     */       try {
/* 299 */         ResultSet results = getSingle(code);
/* 300 */         while (results.next()) {
/* 301 */           if (isAcceptable(results)) {
/* 302 */             exists = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 306 */         results.close();
/* 307 */       } catch (SQLException exception) {
/* 308 */         unexpectedException("contains", exception);
/*     */       }  
/* 310 */     return exists;
/*     */   }
/*     */   
/*     */   public synchronized java.util.Iterator<String> iterator() {
/*     */     try {
/* 320 */       Iterator iterator = new Iterator(getAll());
/* 326 */       this.queryAll = null;
/* 327 */       return iterator;
/* 328 */     } catch (SQLException exception) {
/* 329 */       unexpectedException("iterator", exception);
/* 330 */       Set<String> empty = Collections.emptySet();
/* 331 */       return empty.iterator();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object writeReplace() throws ObjectStreamException {
/* 341 */     return new LinkedHashSet(this);
/*     */   }
/*     */   
/*     */   protected synchronized void finalize() throws SQLException {
/* 351 */     if (this.querySingle != null) {
/* 352 */       this.querySingle.close();
/* 353 */       this.querySingle = null;
/*     */     } 
/* 355 */     if (this.queryAll != null) {
/* 356 */       this.queryAll.close();
/* 357 */       this.queryAll = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void unexpectedException(String method, SQLException exception) {
/* 367 */     unexpectedException(AuthorityCodes.class, method, exception);
/*     */   }
/*     */   
/*     */   static void unexpectedException(Class<?> classe, String method, SQLException exception) {
/* 377 */     Logging.unexpectedException(classe, method, exception);
/*     */   }
/*     */   
/*     */   private static void recoverableException(String method, SQLException exception) {
/* 385 */     LogRecord record = Loggings.format(Level.FINE, 43);
/* 386 */     record.setSourceClassName(AuthorityCodes.class.getName());
/* 387 */     record.setSourceMethodName(method);
/* 388 */     record.setThrown(exception);
/* 389 */     Logger logger = Logging.getLogger(AuthorityCodes.class);
/* 390 */     record.setLoggerName(logger.getName());
/* 391 */     logger.log(record);
/*     */   }
/*     */   
/*     */   private final class Iterator implements java.util.Iterator<String> {
/*     */     private ResultSet results;
/*     */     
/*     */     private transient String next;
/*     */     
/*     */     Iterator(ResultSet results) throws SQLException {
/* 408 */       assert Thread.holdsLock(AuthorityCodes.this);
/* 409 */       this.results = results;
/* 410 */       toNext();
/*     */     }
/*     */     
/*     */     private void toNext() throws SQLException {
/* 415 */       while (this.results.next()) {
/* 416 */         this.next = this.results.getString(1);
/* 417 */         if (AuthorityCodes.this.isAcceptable(this.next))
/*     */           return; 
/*     */       } 
/* 421 */       finalize();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 426 */       return (this.results != null);
/*     */     }
/*     */     
/*     */     public String next() {
/* 431 */       if (this.results == null)
/* 432 */         throw new NoSuchElementException(); 
/* 434 */       String current = this.next;
/*     */       try {
/* 436 */         toNext();
/* 437 */       } catch (SQLException exception) {
/* 438 */         this.results = null;
/* 439 */         AuthorityCodes.unexpectedException(Iterator.class, "next", exception);
/*     */       } 
/* 441 */       return current;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 446 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     protected void finalize() throws SQLException {
/* 452 */       this.next = null;
/* 453 */       if (this.results != null) {
/* 454 */         PreparedStatement owner = (PreparedStatement)this.results.getStatement();
/* 455 */         this.results.close();
/* 456 */         this.results = null;
/* 457 */         synchronized (AuthorityCodes.this) {
/* 464 */           assert owner != AuthorityCodes.this.queryAll;
/* 465 */           if (AuthorityCodes.this.queryAll == null) {
/* 466 */             AuthorityCodes.this.queryAll = owner;
/*     */           } else {
/* 468 */             owner.close();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   final java.util.Map<String, String> asMap() {
/* 479 */     if (this.asMap == null)
/* 480 */       this.asMap = new Map(); 
/* 482 */     return this.asMap;
/*     */   }
/*     */   
/*     */   private final class Map extends AbstractMap<String, String> {
/*     */     private Map() {}
/*     */     
/*     */     public int size() {
/* 495 */       return AuthorityCodes.this.size();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 503 */       return AuthorityCodes.this.isEmpty();
/*     */     }
/*     */     
/*     */     public String get(Object code) {
/* 511 */       String value = null;
/* 512 */       if (code != null)
/*     */         try {
/* 513 */           synchronized (AuthorityCodes.this) {
/* 514 */             ResultSet results = AuthorityCodes.this.getSingle(code);
/* 515 */             while (results.next()) {
/* 516 */               if (AuthorityCodes.this.isAcceptable(results)) {
/* 517 */                 value = results.getString(2);
/*     */                 break;
/*     */               } 
/*     */             } 
/* 521 */             results.close();
/*     */           } 
/* 523 */         } catch (SQLException exception) {
/* 524 */           AuthorityCodes.unexpectedException("get", exception);
/*     */         }  
/* 526 */       return value;
/*     */     }
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 534 */       return AuthorityCodes.this.contains(key);
/*     */     }
/*     */     
/*     */     public Set<String> keySet() {
/* 542 */       return AuthorityCodes.this;
/*     */     }
/*     */     
/*     */     public Set<java.util.Map.Entry<String, String>> entrySet() {
/* 551 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\AuthorityCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */