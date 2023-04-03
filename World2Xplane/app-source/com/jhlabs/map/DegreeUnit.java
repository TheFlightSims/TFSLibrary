/*    */ package com.jhlabs.map;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ 
/*    */ public class DegreeUnit extends Unit {
/*    */   static final long serialVersionUID = -3212757578604686538L;
/*    */   
/* 25 */   private static AngleFormat format = new AngleFormat("DdM", true);
/*    */   
/*    */   public DegreeUnit() {
/* 28 */     super("degree", "degrees", "deg", 1.0D);
/*    */   }
/*    */   
/*    */   public double parse(String s) throws NumberFormatException {
/*    */     try {
/* 33 */       return format.parse(s).doubleValue();
/* 35 */     } catch (ParseException e) {
/* 36 */       throw new NumberFormatException(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public String format(double n) {
/* 41 */     return format.format(n) + " " + this.abbreviation;
/*    */   }
/*    */   
/*    */   public String format(double n, boolean abbrev) {
/* 45 */     if (abbrev)
/* 46 */       return format.format(n) + " " + this.abbreviation; 
/* 47 */     return format.format(n);
/*    */   }
/*    */   
/*    */   public String format(double x, double y, boolean abbrev) {
/* 51 */     if (abbrev)
/* 52 */       return format.format(x) + "/" + format.format(y) + " " + this.abbreviation; 
/* 53 */     return format.format(x) + "/" + format.format(y);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\DegreeUnit.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */