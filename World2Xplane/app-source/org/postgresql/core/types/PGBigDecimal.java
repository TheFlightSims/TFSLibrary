/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGBigDecimal implements PGType {
/*    */   private BigDecimal val;
/*    */   
/*    */   protected PGBigDecimal(BigDecimal x) {
/* 29 */     this.val = new BigDecimal(x.toString());
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(BigDecimal val, int targetType) throws PSQLException {
/*    */     try {
/* 37 */       switch (targetType) {
/*    */         case -7:
/* 40 */           return new PGBoolean((val.doubleValue() == 0.0D) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case -5:
/* 42 */           return new PGLong(new Long(val.longValue()));
/*    */         case 4:
/* 44 */           return new PGInteger(new Integer(val.intValue()));
/*    */         case -6:
/*    */         case 5:
/* 47 */           return new PGShort(new Short(val.shortValue()));
/*    */         case -1:
/*    */         case 12:
/* 50 */           return new PGString(val.toString());
/*    */         case 2:
/*    */         case 3:
/*    */         case 6:
/*    */         case 7:
/*    */         case 8:
/* 56 */           return new PGBigDecimal(val);
/*    */       } 
/* 58 */       return new PGUnknown(val);
/* 61 */     } catch (Exception ex) {
/* 63 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGBigDecimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */