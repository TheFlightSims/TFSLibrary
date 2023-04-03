/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGShort implements PGType {
/*    */   Short val;
/*    */   
/*    */   protected PGShort(Short x) {
/* 27 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Short val, int targetType) throws PSQLException {
/*    */     try {
/* 33 */       switch (targetType) {
/*    */         case -7:
/* 36 */           return new PGBoolean((val.shortValue() == 0) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case -6:
/*    */         case 5:
/* 40 */           return new PGShort(val);
/*    */         case 7:
/* 42 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 6:
/*    */         case 8:
/* 45 */           return new PGDouble(new Double(val.doubleValue()));
/*    */         case -1:
/*    */         case 12:
/* 48 */           return new PGString(val.toString());
/*    */         case 2:
/*    */         case 3:
/* 51 */           return new PGBigDecimal(new BigDecimal(val.toString()));
/*    */       } 
/* 53 */       return new PGUnknown(val);
/* 56 */     } catch (Exception ex) {
/* 58 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 64 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGShort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */