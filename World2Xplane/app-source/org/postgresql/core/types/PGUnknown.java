/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ public class PGUnknown implements PGType {
/*    */   Object val;
/*    */   
/*    */   public PGUnknown(Object x) {
/* 24 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Object val, int targetType) {
/* 28 */     return new PGUnknown(val);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGUnknown.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */