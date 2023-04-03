/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ 
/*     */ public class NumberTickUnit extends TickUnit implements Serializable {
/*     */   private static final long serialVersionUID = 3849459506627654442L;
/*     */   
/*     */   private NumberFormat formatter;
/*     */   
/*     */   public NumberTickUnit(double size) {
/*  72 */     this(size, NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   public NumberTickUnit(double size, NumberFormat formatter) {
/*  83 */     super(size);
/*  84 */     if (formatter == null)
/*  85 */       throw new IllegalArgumentException("Null 'formatter' argument."); 
/*  87 */     this.formatter = formatter;
/*     */   }
/*     */   
/*     */   public String valueToString(double value) {
/*  98 */     return this.formatter.format(value);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 109 */     if (obj == this)
/* 110 */       return true; 
/* 112 */     if (!(obj instanceof NumberTickUnit))
/* 113 */       return false; 
/* 115 */     if (!super.equals(obj))
/* 116 */       return false; 
/* 118 */     NumberTickUnit that = (NumberTickUnit)obj;
/* 119 */     if (!this.formatter.equals(that.formatter))
/* 120 */       return false; 
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 131 */     int result = super.hashCode();
/* 132 */     result = 29 * result + ((this.formatter != null) ? this.formatter.hashCode() : 0);
/* 133 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\NumberTickUnit.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */