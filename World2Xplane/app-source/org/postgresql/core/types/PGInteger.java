/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGInteger implements PGType {
/*    */   private Integer val;
/*    */   
/*    */   protected PGInteger(Integer x) {
/* 28 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Integer val, int targetType) throws PSQLException {
/*    */     try {
/* 35 */       switch (targetType) {
/*    */         case -7:
/* 38 */           return new PGBoolean((val.intValue() == 0) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case 7:
/* 40 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 6:
/*    */         case 8:
/* 43 */           return new PGDouble(new Double(val.doubleValue()));
/*    */         case -1:
/*    */         case 12:
/* 46 */           return new PGString(val.toString());
/*    */         case -6:
/*    */         case 5:
/* 49 */           return new PGShort(new Short(val.shortValue()));
/*    */         case 4:
/* 51 */           return new PGInteger(val);
/*    */         case 2:
/*    */         case 3:
/* 54 */           return new PGBigDecimal(new BigDecimal(val.toString()));
/*    */       } 
/* 56 */       return new PGUnknown(val);
/* 59 */     } catch (Exception ex) {
/* 61 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 66 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGInteger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */