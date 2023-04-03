/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGLong implements PGType {
/*    */   private Long val;
/*    */   
/*    */   protected PGLong(Long x) {
/* 27 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Long val, int targetType) throws PSQLException {
/*    */     try {
/* 34 */       switch (targetType) {
/*    */         case -7:
/* 37 */           return new PGBoolean((val.longValue() == 0L) ? Boolean.FALSE : Boolean.TRUE);
/*    */         case 7:
/* 39 */           return new PGFloat(new Float(val.floatValue()));
/*    */         case 6:
/*    */         case 8:
/* 42 */           return new PGDouble(new Double(val.doubleValue()));
/*    */         case -1:
/*    */         case 12:
/* 45 */           return new PGString(val.toString());
/*    */         case -5:
/* 47 */           return new PGLong(val);
/*    */         case 4:
/* 49 */           return new PGInteger(new Integer(val.intValue()));
/*    */         case -6:
/*    */         case 5:
/* 52 */           return new PGShort(new Short(val.shortValue()));
/*    */         case 2:
/*    */         case 3:
/* 55 */           return new PGBigDecimal(new BigDecimal(val.toString()));
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGLong.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */