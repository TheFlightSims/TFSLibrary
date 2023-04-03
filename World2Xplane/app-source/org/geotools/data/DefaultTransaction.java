/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class DefaultTransaction implements Transaction {
/*  41 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*  44 */   Map stateLookup = new HashMap<Object, Object>();
/*     */   
/*  47 */   Map propertyLookup = new HashMap<Object, Object>();
/*     */   
/*     */   String handle;
/*     */   
/*  53 */   Set authorizations = new HashSet();
/*     */   
/*     */   public DefaultTransaction() {
/*  56 */     Throwable t = new Throwable("who called me?");
/*  57 */     StackTraceElement e = t.getStackTrace()[1];
/*  58 */     this.handle = e.getClassName() + "." + e.getMethodName() + " Transaction";
/*     */   }
/*     */   
/*     */   public DefaultTransaction(String handle) {
/*  62 */     this.handle = handle;
/*     */   }
/*     */   
/*     */   public void putState(Object key, Transaction.State state) {
/*  91 */     if (this.stateLookup == null)
/*     */       return; 
/*  94 */     if (this.stateLookup.containsKey(key)) {
/*  95 */       Transaction.State current = (Transaction.State)this.stateLookup.get(key);
/*  97 */       if (state == current)
/*  98 */         throw new IllegalArgumentException("Transaction already has an this State for key: " + key + ". Please check for existing State before creating your own."); 
/* 102 */       throw new IllegalArgumentException("Transaction already has an entry for key:" + key + ". Please check for existing State before creating your own.");
/*     */     } 
/* 106 */     this.stateLookup.put(key, state);
/* 109 */     state.setTransaction(this);
/*     */   }
/*     */   
/*     */   public void removeState(Object key) {
/* 129 */     if (this.stateLookup == null)
/* 130 */       throw new IllegalStateException("Transaction has been closed"); 
/* 132 */     if (this.stateLookup.containsKey(key)) {
/* 133 */       Transaction.State state = (Transaction.State)this.stateLookup.remove(key);
/* 134 */       state.setTransaction(null);
/*     */     } else {
/* 136 */       throw new IllegalArgumentException("Transaction does not no anything about key:" + key + ". Has this key already been removed?");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Transaction.State getState(Object key) {
/* 156 */     if (this.stateLookup == null)
/* 157 */       throw new IllegalStateException("Transaction has been closed"); 
/* 159 */     return (Transaction.State)this.stateLookup.get(key);
/*     */   }
/*     */   
/*     */   public void commit() throws IOException {
/* 179 */     int problemCount = 0;
/* 180 */     IOException io = null;
/* 182 */     for (Iterator<Transaction.State> i = this.stateLookup.values().iterator(); i.hasNext(); ) {
/* 183 */       Transaction.State state = i.next();
/*     */       try {
/* 186 */         state.commit();
/* 187 */       } catch (IOException e) {
/* 188 */         problemCount++;
/* 189 */         io = e;
/*     */       } 
/*     */     } 
/* 193 */     if (io != null) {
/* 194 */       if (problemCount == 1)
/* 195 */         throw io; 
/* 198 */       throw new DataSourceException("Commit encountered " + problemCount + " problems - the first was", io);
/*     */     } 
/* 201 */     this.authorizations.clear();
/*     */   }
/*     */   
/*     */   public void rollback() throws IOException {
/* 220 */     int problemCount = 0;
/* 221 */     IOException io = null;
/* 224 */     for (Iterator<Transaction.State> i = this.stateLookup.values().iterator(); i.hasNext(); ) {
/* 225 */       Transaction.State state = i.next();
/*     */       try {
/* 228 */         state.rollback();
/* 229 */       } catch (IOException e) {
/* 230 */         problemCount++;
/* 231 */         io = e;
/*     */       } 
/*     */     } 
/* 235 */     if (io != null) {
/* 236 */       if (problemCount == 1)
/* 237 */         throw io; 
/* 240 */       throw new DataSourceException("Rollback encountered " + problemCount + " problems - the first was", io);
/*     */     } 
/* 243 */     this.authorizations.clear();
/*     */   }
/*     */   
/*     */   public synchronized void close() {
/* 250 */     for (Iterator<Transaction.State> i = this.stateLookup.values().iterator(); i.hasNext(); ) {
/* 251 */       Transaction.State state = i.next();
/* 252 */       state.setTransaction(null);
/*     */     } 
/* 254 */     this.stateLookup.clear();
/* 255 */     this.stateLookup = null;
/* 256 */     this.authorizations.clear();
/* 257 */     this.authorizations = null;
/* 258 */     this.propertyLookup.clear();
/* 259 */     this.propertyLookup = null;
/*     */   }
/*     */   
/*     */   public Set getAuthorizations() {
/* 272 */     if (this.authorizations == null)
/* 273 */       throw new IllegalStateException("Transaction has been closed"); 
/* 275 */     return Collections.unmodifiableSet(this.authorizations);
/*     */   }
/*     */   
/*     */   public void addAuthorization(String authID) throws IOException {
/* 296 */     if (this.authorizations == null)
/* 297 */       throw new IllegalStateException("Transaction has been closed"); 
/* 299 */     int problemCount = 0;
/* 300 */     IOException io = null;
/* 302 */     this.authorizations.add(authID);
/* 304 */     for (Iterator<Transaction.State> i = this.stateLookup.values().iterator(); i.hasNext(); ) {
/* 305 */       Transaction.State state = i.next();
/*     */       try {
/* 308 */         state.addAuthorization(authID);
/* 309 */       } catch (IOException e) {
/* 310 */         problemCount++;
/* 311 */         io = e;
/*     */       } 
/*     */     } 
/* 315 */     if (io != null) {
/* 316 */       if (problemCount == 1)
/* 317 */         throw io; 
/* 319 */       throw new DataSourceException("setAuthorization encountered " + problemCount + " problems - the first was", io);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 330 */     return this.handle;
/*     */   }
/*     */   
/*     */   public Object getProperty(Object key) {
/* 341 */     if (this.propertyLookup == null)
/* 342 */       throw new IllegalStateException("Transaction has been closed"); 
/* 344 */     return this.propertyLookup.get(key);
/*     */   }
/*     */   
/*     */   public void putProperty(Object key, Object value) throws IOException {
/* 357 */     if (this.propertyLookup == null)
/* 358 */       throw new IllegalStateException("Transaction has been closed"); 
/* 360 */     this.propertyLookup.put(key, value);
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 364 */     if (this.stateLookup != null) {
/* 365 */       LOGGER.severe("There's code leaving transaction unclosed. Call Transaction.close() after using them to ensure they do not hold state such as JDCB connections or file handles");
/* 368 */       LOGGER.severe("Unclosed transaction handle is '" + this.handle + "'");
/* 369 */       close();
/*     */     } 
/* 371 */     super.finalize();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultTransaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */