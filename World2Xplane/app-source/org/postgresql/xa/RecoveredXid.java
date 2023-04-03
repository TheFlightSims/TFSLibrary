/*    */ package org.postgresql.xa;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import javax.transaction.xa.Xid;
/*    */ import org.postgresql.util.Base64;
/*    */ 
/*    */ class RecoveredXid implements Xid {
/*    */   int formatId;
/*    */   
/*    */   byte[] globalTransactionId;
/*    */   
/*    */   byte[] branchQualifier;
/*    */   
/*    */   public int getFormatId() {
/* 23 */     return this.formatId;
/*    */   }
/*    */   
/*    */   public byte[] getGlobalTransactionId() {
/* 27 */     return this.globalTransactionId;
/*    */   }
/*    */   
/*    */   public byte[] getBranchQualifier() {
/* 31 */     return this.branchQualifier;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 35 */     if (o == this)
/* 36 */       return true; 
/* 38 */     if (!(o instanceof Xid))
/* 39 */       return false; 
/* 41 */     Xid other = (Xid)o;
/* 42 */     if (other.getFormatId() != this.formatId)
/* 43 */       return false; 
/* 44 */     if (!Arrays.equals(this.globalTransactionId, other.getGlobalTransactionId()))
/* 45 */       return false; 
/* 46 */     if (!Arrays.equals(this.branchQualifier, other.getBranchQualifier()))
/* 47 */       return false; 
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return xidToString(this);
/*    */   }
/*    */   
/*    */   static String xidToString(Xid xid) {
/* 63 */     return xid.getFormatId() + "_" + Base64.encodeBytes(xid.getGlobalTransactionId(), 8) + "_" + Base64.encodeBytes(xid.getBranchQualifier(), 8);
/*    */   }
/*    */   
/*    */   static Xid stringToXid(String s) {
/* 74 */     RecoveredXid xid = new RecoveredXid();
/* 76 */     int a = s.indexOf("_");
/* 77 */     int b = s.lastIndexOf("_");
/* 79 */     if (a == b)
/* 80 */       return null; 
/*    */     try {
/* 84 */       xid.formatId = Integer.parseInt(s.substring(0, a));
/* 85 */       xid.globalTransactionId = Base64.decode(s.substring(a + 1, b));
/* 86 */       xid.branchQualifier = Base64.decode(s.substring(b + 1));
/* 88 */       if (xid.globalTransactionId == null || xid.branchQualifier == null)
/* 89 */         return null; 
/* 91 */     } catch (Exception ex) {
/* 93 */       return null;
/*    */     } 
/* 96 */     return xid;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\RecoveredXid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */