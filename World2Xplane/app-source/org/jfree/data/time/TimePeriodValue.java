/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class TimePeriodValue implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 3390443360845711275L;
/*     */   
/*     */   private TimePeriod period;
/*     */   
/*     */   private Number value;
/*     */   
/*     */   public TimePeriodValue(TimePeriod period, Number value) {
/*  68 */     this.period = period;
/*  69 */     this.value = value;
/*     */   }
/*     */   
/*     */   public TimePeriodValue(TimePeriod period, double value) {
/*  79 */     this(period, new Double(value));
/*     */   }
/*     */   
/*     */   public TimePeriod getPeriod() {
/*  88 */     return this.period;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/*  97 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Number value) {
/* 106 */     this.value = value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 117 */     if (this == obj)
/* 118 */       return true; 
/* 120 */     if (!(obj instanceof TimePeriodValue))
/* 121 */       return false; 
/* 124 */     TimePeriodValue timePeriodValue = (TimePeriodValue)obj;
/* 126 */     if ((this.period != null) ? !this.period.equals(timePeriodValue.period) : (timePeriodValue.period != null))
/* 128 */       return false; 
/* 130 */     if ((this.value != null) ? !this.value.equals(timePeriodValue.value) : (timePeriodValue.value != null))
/* 132 */       return false; 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 145 */     int result = (this.period != null) ? this.period.hashCode() : 0;
/* 146 */     result = 29 * result + ((this.value != null) ? this.value.hashCode() : 0);
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 159 */     Object clone = null;
/*     */     try {
/* 161 */       clone = super.clone();
/* 163 */     } catch (CloneNotSupportedException e) {
/* 164 */       System.err.println("Operation not supported.");
/*     */     } 
/* 166 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimePeriodValue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */