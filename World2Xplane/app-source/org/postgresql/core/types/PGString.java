/*    */ package org.postgresql.core.types;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PGString implements PGType {
/*    */   String val;
/*    */   
/*    */   protected PGString(String x) {
/* 28 */     this.val = x;
/*    */   }
/*    */   
/*    */   public static PGType castToServerType(String val, int targetType) throws PSQLException {
/*    */     try {
/* 37 */       switch (targetType) {
/*    */         case -7:
/* 41 */           if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("1") || val.equalsIgnoreCase("t"))
/* 42 */             return new PGBoolean(Boolean.TRUE); 
/* 43 */           if (val.equalsIgnoreCase("false") || val.equalsIgnoreCase("0") || val.equalsIgnoreCase("f"))
/* 44 */             return new PGBoolean(Boolean.FALSE); 
/* 47 */           return new PGBoolean(Boolean.FALSE);
/*    */         case -1:
/*    */         case 12:
/* 51 */           return new PGString(val);
/*    */         case -5:
/* 53 */           return new PGLong(new Long(Long.parseLong(val)));
/*    */         case 4:
/* 55 */           return new PGInteger(new Integer(Integer.parseInt(val)));
/*    */         case -6:
/* 57 */           return new PGShort(new Short(Short.parseShort(val)));
/*    */         case 6:
/*    */         case 8:
/* 60 */           return new PGDouble(new Double(Double.parseDouble(val)));
/*    */         case 7:
/* 62 */           return new PGFloat(new Float(Float.parseFloat(val)));
/*    */         case 2:
/*    */         case 3:
/* 65 */           return new PGBigDecimal(new BigDecimal(val));
/*    */       } 
/* 67 */       return new PGUnknown(val);
/* 71 */     } catch (Exception ex) {
/* 73 */       throw new PSQLException(GT.tr("Cannot convert an instance of {0} to type {1}", new Object[] { val.getClass().getName(), "Types.OTHER" }), PSQLState.INVALID_PARAMETER_TYPE, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     return this.val;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\types\PGString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */