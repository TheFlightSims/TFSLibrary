/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class TimeSeriesDataItem implements Cloneable, Comparable, Serializable {
/*     */   private static final long serialVersionUID = -2235346966016401302L;
/*     */   
/*     */   private RegularTimePeriod period;
/*     */   
/*     */   private Number value;
/*     */   
/*     */   public TimeSeriesDataItem(RegularTimePeriod period, Number value) {
/*  95 */     if (period == null)
/*  96 */       throw new IllegalArgumentException("Null 'period' argument."); 
/*  98 */     this.period = period;
/*  99 */     this.value = value;
/*     */   }
/*     */   
/*     */   public TimeSeriesDataItem(RegularTimePeriod period, double value) {
/* 109 */     this(period, new Double(value));
/*     */   }
/*     */   
/*     */   public RegularTimePeriod getPeriod() {
/* 118 */     return this.period;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/* 127 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Number value) {
/* 136 */     this.value = value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 147 */     if (this == o)
/* 148 */       return true; 
/* 150 */     if (!(o instanceof TimeSeriesDataItem))
/* 151 */       return false; 
/* 153 */     TimeSeriesDataItem timeSeriesDataItem = (TimeSeriesDataItem)o;
/* 154 */     if (this.period != null) {
/* 155 */       if (!this.period.equals(timeSeriesDataItem.period))
/* 156 */         return false; 
/* 159 */     } else if (timeSeriesDataItem.period != null) {
/* 160 */       return false;
/*     */     } 
/* 163 */     if (this.value != null) {
/* 164 */       if (!this.value.equals(timeSeriesDataItem.value))
/* 165 */         return false; 
/* 168 */     } else if (timeSeriesDataItem.value != null) {
/* 169 */       return false;
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 182 */     int result = (this.period != null) ? this.period.hashCode() : 0;
/* 183 */     result = 29 * result + ((this.value != null) ? this.value.hashCode() : 0);
/* 184 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 205 */     if (o1 instanceof TimeSeriesDataItem) {
/* 206 */       TimeSeriesDataItem datapair = (TimeSeriesDataItem)o1;
/* 207 */       result = getPeriod().compareTo((T)datapair.getPeriod());
/*     */     } else {
/* 214 */       result = 1;
/*     */     } 
/* 217 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 228 */     Object clone = null;
/*     */     try {
/* 230 */       clone = super.clone();
/* 232 */     } catch (CloneNotSupportedException e) {
/* 233 */       e.printStackTrace();
/*     */     } 
/* 235 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimeSeriesDataItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */