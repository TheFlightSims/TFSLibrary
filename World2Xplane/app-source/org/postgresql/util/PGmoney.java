/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class PGmoney extends PGobject implements Serializable, Cloneable {
/*    */   public double val;
/*    */   
/*    */   public PGmoney(double value) {
/* 31 */     this();
/* 32 */     this.val = value;
/*    */   }
/*    */   
/*    */   public PGmoney(String value) throws SQLException {
/* 37 */     this();
/* 38 */     setValue(value);
/*    */   }
/*    */   
/*    */   public PGmoney() {
/* 46 */     setType("money");
/*    */   }
/*    */   
/*    */   public void setValue(String s) throws SQLException {
/*    */     try {
/* 56 */       boolean negative = (s.charAt(0) == '(');
/* 59 */       String s1 = PGtokenizer.removePara(s).substring(1);
/* 62 */       int pos = s1.indexOf(',');
/* 63 */       while (pos != -1) {
/* 65 */         s1 = s1.substring(0, pos) + s1.substring(pos + 1);
/* 66 */         pos = s1.indexOf(',');
/*    */       } 
/* 69 */       this.val = Double.valueOf(s1).doubleValue();
/* 70 */       this.val = negative ? -this.val : this.val;
/* 73 */     } catch (NumberFormatException e) {
/* 75 */       throw new PSQLException(GT.tr("Conversion of money failed."), PSQLState.NUMERIC_CONSTANT_OUT_OF_RANGE, e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 81 */     if (obj instanceof PGmoney) {
/* 83 */       PGmoney p = (PGmoney)obj;
/* 84 */       return (this.val == p.val);
/*    */     } 
/* 86 */     return false;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 91 */     if (this.val < 0.0D)
/* 93 */       return "-$" + -this.val; 
/* 97 */     return "$" + this.val;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PGmoney.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */