/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ 
/*     */ class AutoCommitTransaction implements Transaction {
/*     */   public Set<String> getAuthorizations() {
/*  47 */     throw new UnsupportedOperationException("Authorization IDs are not valid for AutoCommit Transaction");
/*     */   }
/*     */   
/*     */   public void putState(Object key, Transaction.State state) {
/*  65 */     throw new UnsupportedOperationException("AutoCommit does not support the putState opperations");
/*     */   }
/*     */   
/*     */   public void removeState(Object key) {
/*  82 */     throw new UnsupportedOperationException("AutoCommit does not support the removeState opperations");
/*     */   }
/*     */   
/*     */   public Transaction.State getState(Object key) {
/* 102 */     throw new UnsupportedOperationException("AutoCommit does not support the getState opperations");
/*     */   }
/*     */   
/*     */   public void commit() {}
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public void rollback() throws IOException {
/* 143 */     throw new IOException("AutoCommit cannot support the rollback opperation");
/*     */   }
/*     */   
/*     */   public void addAuthorization(String authID) throws IOException {
/* 158 */     throw new IOException("Authorization IDs are not valid for AutoCommit Transaction");
/*     */   }
/*     */   
/*     */   public Object getProperty(Object key) {
/* 176 */     throw new UnsupportedOperationException("AutoCommit does not support the getProperty opperations");
/*     */   }
/*     */   
/*     */   public void putProperty(Object key, Object value) {
/* 192 */     throw new UnsupportedOperationException("AutoCommit does not support the addProperty opperations");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AutoCommitTransaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */