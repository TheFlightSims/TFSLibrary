/*    */ package com.jhlabs.map;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.text.NumberFormat;
/*    */ import java.text.ParseException;
/*    */ 
/*    */ public class Unit implements Serializable {
/*    */   static final long serialVersionUID = -6704954923429734628L;
/*    */   
/*    */   public static final int ANGLE_UNIT = 0;
/*    */   
/*    */   public static final int LENGTH_UNIT = 1;
/*    */   
/*    */   public static final int AREA_UNIT = 2;
/*    */   
/*    */   public static final int VOLUME_UNIT = 3;
/*    */   
/*    */   public String name;
/*    */   
/*    */   public String plural;
/*    */   
/*    */   public String abbreviation;
/*    */   
/*    */   public double value;
/*    */   
/* 36 */   public static NumberFormat format = NumberFormat.getNumberInstance();
/*    */   
/*    */   static {
/* 37 */     format.setMaximumFractionDigits(2);
/* 38 */     format.setGroupingUsed(false);
/*    */   }
/*    */   
/*    */   public Unit(String name, String plural, String abbreviation, double value) {
/* 42 */     this.name = name;
/* 43 */     this.plural = plural;
/* 44 */     this.abbreviation = abbreviation;
/* 45 */     this.value = value;
/*    */   }
/*    */   
/*    */   public double toBase(double n) {
/* 49 */     return n * this.value;
/*    */   }
/*    */   
/*    */   public double fromBase(double n) {
/* 53 */     return n / this.value;
/*    */   }
/*    */   
/*    */   public double parse(String s) throws NumberFormatException {
/*    */     try {
/* 58 */       return format.parse(s).doubleValue();
/* 60 */     } catch (ParseException e) {
/* 61 */       throw new NumberFormatException(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public String format(double n) {
/* 66 */     return format.format(n) + " " + this.abbreviation;
/*    */   }
/*    */   
/*    */   public String format(double n, boolean abbrev) {
/* 70 */     if (abbrev)
/* 71 */       return format.format(n) + " " + this.abbreviation; 
/* 72 */     return format.format(n);
/*    */   }
/*    */   
/*    */   public String format(double x, double y, boolean abbrev) {
/* 76 */     if (abbrev)
/* 77 */       return format.format(x) + "/" + format.format(y) + " " + this.abbreviation; 
/* 78 */     return format.format(x) + "/" + format.format(y);
/*    */   }
/*    */   
/*    */   public String format(double x, double y) {
/* 82 */     return format(x, y, true);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 86 */     return this.plural;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 90 */     if (o instanceof Unit)
/* 91 */       return (((Unit)o).value == this.value); 
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\Unit.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */