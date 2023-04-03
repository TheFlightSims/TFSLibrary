/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGBoolean implements PGType {
/*    */   private Boolean val;
/*    */   
/*    */   public PGBoolean(Boolean x) {
/* 27 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(Boolean val, int targetType) throws PSQLException {
/*    */     try {
/* 33 */       switch (targetType) {
/*    */         case -5:
/* 36 */           return new PGLong(new Long((val.booleanValue() == true) ? 1L : 0L));
/*    */         case 4:
/* 38 */           return new PGInteger(new Integer((val.booleanValue() == true) ? 1 : 0));
/*    */         case -6:
/*    */         case 5:
/* 41 */           return new PGShort(new Short((val.booleanValue() == true) ? 1 : 0));
/*    */         case -1:
/*    */         case 12:
/* 44 */           return new PGString((val.booleanValue() == true) ? "true" : "false");
/*    */         case 6:
/*    */         case 8:
/* 47 */           return new PGDouble(new Double((val.booleanValue() == true) ? 1.0D : 0.0D));
/*    */         case 7:
/* 49 */           return new PGFloat(new Float((val.booleanValue() == true) ? 1.0F : 0.0F));
/*    */         case 2:
/*    */         case 3:
/* 52 */           return new PGBigDecimal(new BigDecimal((val.booleanValue() == true) ? 1 : 0));
/*    */         case -7:
/* 55 */           return new PGBoolean(val);
/*    */       } 
/* 57 */       return new PGUnknown(val);
/* 60 */     } catch (Exception ex) {
/* 62 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 67 */     return (this.val.booleanValue() == true) ? "true" : "false";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */