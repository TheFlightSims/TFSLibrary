/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGByte implements PGType {
/*    */   private Byte val;
/*    */   
/*    */   public PGByte(Byte x) {
/* 29 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Byte val, int targetType) throws PSQLException {
/*    */     try {
/* 38 */       switch (targetType) {
/*    */         case -7:
/* 41 */           return new PGBoolean((val.byteValue() == 0) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case -6:
/*    */         case 5:
/* 45 */           return new PGByte(val);
/*    */         case 7:
/* 47 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 6:
/*    */         case 8:
/* 50 */           return new PGDouble(new Double(val.doubleValue()));
/*    */         case 2:
/*    */         case 3:
/* 53 */           return new PGBigDecimal(new BigDecimal(val.toString()));
/*    */         case -1:
/*    */         case 12:
/* 56 */           return new PGString(val.toString());
/*    */       } 
/* 58 */       return new PGUnknown(val);
/* 61 */     } catch (Exception ex) {
/* 63 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 69 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGByte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */