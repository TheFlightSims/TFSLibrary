/*    */ package org.postgresql.jdbc2;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ 
/*    */ public class ResultWrapper {
/*    */   private final ResultSet rs;
/*    */   
/*    */   private final int updateCount;
/*    */   
/*    */   private final long insertOID;
/*    */   
/*    */   private ResultWrapper next;
/*    */   
/*    */   public ResultWrapper(ResultSet rs) {
/* 24 */     this.rs = rs;
/* 25 */     this.updateCount = -1;
/* 26 */     this.insertOID = -1L;
/*    */   }
/*    */   
/*    */   public ResultWrapper(int updateCount, long insertOID) {
/* 30 */     this.rs = null;
/* 31 */     this.updateCount = updateCount;
/* 32 */     this.insertOID = insertOID;
/*    */   }
/*    */   
/*    */   public ResultSet getResultSet() {
/* 36 */     return this.rs;
/*    */   }
/*    */   
/*    */   public int getUpdateCount() {
/* 40 */     return this.updateCount;
/*    */   }
/*    */   
/*    */   public long getInsertOID() {
/* 44 */     return this.insertOID;
/*    */   }
/*    */   
/*    */   public ResultWrapper getNext() {
/* 48 */     return this.next;
/*    */   }
/*    */   
/*    */   public void append(ResultWrapper newResult) {
/* 52 */     ResultWrapper tail = this;
/* 53 */     while (tail.next != null)
/* 54 */       tail = tail.next; 
/* 56 */     tail.next = newResult;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\ResultWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */