/*    */ package org.postgresql.core.v3;
/*    */ 
/*    */ import java.lang.ref.PhantomReference;
/*    */ import org.postgresql.core.ResultCursor;
/*    */ import org.postgresql.core.Utils;
/*    */ 
/*    */ class Portal implements ResultCursor {
/*    */   private final SimpleQuery query;
/*    */   
/*    */   private final String portalName;
/*    */   
/*    */   private final byte[] encodedName;
/*    */   
/*    */   private PhantomReference cleanupRef;
/*    */   
/*    */   Portal(SimpleQuery query, String portalName) {
/* 25 */     this.query = query;
/* 26 */     this.portalName = portalName;
/* 27 */     this.encodedName = Utils.encodeUTF8(portalName);
/*    */   }
/*    */   
/*    */   public void close() {
/* 31 */     if (this.cleanupRef != null) {
/* 33 */       this.cleanupRef.clear();
/* 34 */       this.cleanupRef.enqueue();
/* 35 */       this.cleanupRef = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   String getPortalName() {
/* 40 */     return this.portalName;
/*    */   }
/*    */   
/*    */   byte[] getEncodedPortalName() {
/* 44 */     return this.encodedName;
/*    */   }
/*    */   
/*    */   SimpleQuery getQuery() {
/* 48 */     return this.query;
/*    */   }
/*    */   
/*    */   void setCleanupRef(PhantomReference cleanupRef) {
/* 52 */     this.cleanupRef = cleanupRef;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     return this.portalName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\Portal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */