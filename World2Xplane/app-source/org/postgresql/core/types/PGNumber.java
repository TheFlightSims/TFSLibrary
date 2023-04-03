/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGNumber implements PGType {
/*    */   private Number val;
/*    */   
/*    */   protected PGNumber(Number x) {
/* 27 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Number val, int targetType) throws PSQLException {
/*    */     try {
/* 33 */       switch (targetType) {
/*    */         case -7:
/* 36 */           return new PGBoolean((val.doubleValue() == 0.0D) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case -5:
/* 39 */           return new PGLong(new Long(val.longValue()));
/*    */         case 4:
/* 41 */           return new PGInteger(new Integer(val.intValue()));
/*    */         case -6:
/*    */         case 5:
/* 44 */           return new PGShort(new Short(val.shortValue()));
/*    */         case -1:
/*    */         case 12:
/* 47 */           return new PGString(val.toString());
/*    */         case 6:
/*    */         case 8:
/* 50 */           return new PGDouble(new Double(val.doubleValue()));
/*    */         case 7:
/* 52 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 2:
/*    */         case 3:
/* 55 */           return new PGNumber(val);
/*    */       } 
/* 57 */       return new PGUnknown(val);
/* 60 */     } catch (Exception ex) {
/* 62 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 67 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */