/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGDouble implements PGType {
/*    */   private Double val;
/*    */   
/*    */   protected PGDouble(Double x) {
/* 28 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Double val, int targetType) throws PSQLException {
/*    */     try {
/* 35 */       switch (targetType) {
/*    */         case -7:
/* 38 */           return new PGBoolean((val.doubleValue() == 0.0D) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case -5:
/* 41 */           return new PGLong(new Long(val.longValue()));
/*    */         case 4:
/* 43 */           return new PGInteger(new Integer(val.intValue()));
/*    */         case -6:
/*    */         case 5:
/* 46 */           return new PGShort(new Short(val.shortValue()));
/*    */         case -1:
/*    */         case 12:
/* 49 */           return new PGString(val.toString());
/*    */         case 6:
/*    */         case 8:
/* 52 */           return new PGDouble(val);
/*    */         case 7:
/* 54 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 2:
/*    */         case 3:
/* 57 */           return new PGBigDecimal(new BigDecimal(val.toString()));
/*    */       } 
/* 59 */       return new PGUnknown(val);
/* 62 */     } catch (Exception ex) {
/* 64 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 69 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGDouble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */