/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public interface Transaction {
/* 128 */   public static final Transaction AUTO_COMMIT = new AutoCommitTransaction();
/*     */   
/*     */   Object getProperty(Object paramObject);
/*     */   
/*     */   Set<String> getAuthorizations();
/*     */   
/*     */   void putState(Object paramObject, State paramState);
/*     */   
/*     */   void removeState(Object paramObject);
/*     */   
/*     */   State getState(Object paramObject);
/*     */   
/*     */   void commit() throws IOException;
/*     */   
/*     */   void rollback() throws IOException;
/*     */   
/*     */   void addAuthorization(String paramString) throws IOException;
/*     */   
/*     */   void putProperty(Object paramObject1, Object paramObject2) throws IOException;
/*     */   
/*     */   void close() throws IOException;
/*     */   
/*     */   public static interface State {
/*     */     void setTransaction(Transaction param1Transaction);
/*     */     
/*     */     void addAuthorization(String param1String) throws IOException;
/*     */     
/*     */     void commit() throws IOException;
/*     */     
/*     */     void rollback() throws IOException;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Transaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */