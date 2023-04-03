/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TickUnits implements TickUnitSource, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 1134174035901467545L;
/*     */   
/*  82 */   private List tickUnits = new ArrayList();
/*     */   
/*     */   public void add(TickUnit unit) {
/*  94 */     if (unit == null)
/*  95 */       throw new NullPointerException("Null 'unit' argument."); 
/*  97 */     this.tickUnits.add(unit);
/*  98 */     Collections.sort(this.tickUnits);
/*     */   }
/*     */   
/*     */   public int size() {
/* 110 */     return this.tickUnits.size();
/*     */   }
/*     */   
/*     */   public TickUnit get(int pos) {
/* 123 */     return this.tickUnits.get(pos);
/*     */   }
/*     */   
/*     */   public TickUnit getLargerTickUnit(TickUnit unit) {
/* 135 */     int index = Collections.binarySearch(this.tickUnits, unit);
/* 136 */     if (index >= 0) {
/* 137 */       index++;
/*     */     } else {
/* 140 */       index = -index;
/*     */     } 
/* 143 */     return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
/*     */   }
/*     */   
/*     */   public TickUnit getCeilingTickUnit(TickUnit unit) {
/* 159 */     int index = Collections.binarySearch(this.tickUnits, unit);
/* 160 */     if (index >= 0)
/* 161 */       return this.tickUnits.get(index); 
/* 164 */     index = -(index + 1);
/* 165 */     return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
/*     */   }
/*     */   
/*     */   public TickUnit getCeilingTickUnit(double size) {
/* 181 */     return getCeilingTickUnit(new NumberTickUnit(size, NumberFormat.getInstance()));
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 194 */     TickUnits clone = (TickUnits)super.clone();
/* 195 */     clone.tickUnits = new ArrayList(this.tickUnits);
/* 196 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 207 */     if (object == null)
/* 208 */       return false; 
/* 210 */     if (object == this)
/* 211 */       return true; 
/* 213 */     if (object instanceof TickUnits) {
/* 214 */       TickUnits tu = (TickUnits)object;
/* 215 */       return tu.tickUnits.equals(this.tickUnits);
/*     */     } 
/* 217 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\TickUnits.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */