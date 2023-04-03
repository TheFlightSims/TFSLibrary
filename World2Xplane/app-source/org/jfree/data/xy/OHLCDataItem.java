/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class OHLCDataItem implements Comparable, Serializable {
/*     */   private static final long serialVersionUID = 7753817154401169901L;
/*     */   
/*     */   private Date date;
/*     */   
/*     */   private Number open;
/*     */   
/*     */   private Number high;
/*     */   
/*     */   private Number low;
/*     */   
/*     */   private Number close;
/*     */   
/*     */   private Number volume;
/*     */   
/*     */   public OHLCDataItem(Date date, double open, double high, double low, double close, double volume) {
/*  94 */     if (date == null)
/*  95 */       throw new IllegalArgumentException("Null 'date' argument."); 
/*  97 */     this.date = date;
/*  98 */     this.open = new Double(open);
/*  99 */     this.high = new Double(high);
/* 100 */     this.low = new Double(low);
/* 101 */     this.close = new Double(close);
/* 102 */     this.volume = new Double(volume);
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 111 */     return this.date;
/*     */   }
/*     */   
/*     */   public Number getOpen() {
/* 120 */     return this.open;
/*     */   }
/*     */   
/*     */   public Number getHigh() {
/* 129 */     return this.high;
/*     */   }
/*     */   
/*     */   public Number getLow() {
/* 138 */     return this.low;
/*     */   }
/*     */   
/*     */   public Number getClose() {
/* 147 */     return this.close;
/*     */   }
/*     */   
/*     */   public Number getVolume() {
/* 156 */     return this.volume;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 167 */     if (obj == this)
/* 168 */       return true; 
/* 170 */     if (!(obj instanceof OHLCDataItem))
/* 171 */       return false; 
/* 173 */     OHLCDataItem that = (OHLCDataItem)obj;
/* 174 */     if (!this.date.equals(that.date))
/* 175 */       return false; 
/* 177 */     if (!this.high.equals(that.high))
/* 178 */       return false; 
/* 180 */     if (!this.low.equals(that.low))
/* 181 */       return false; 
/* 183 */     if (!this.open.equals(that.open))
/* 184 */       return false; 
/* 186 */     if (!this.close.equals(that.close))
/* 187 */       return false; 
/* 189 */     return true;
/*     */   }
/*     */   
/*     */   public int compareTo(Object object) {
/* 203 */     if (object instanceof OHLCDataItem) {
/* 204 */       OHLCDataItem item = (OHLCDataItem)object;
/* 205 */       return this.date.compareTo(item.date);
/*     */     } 
/* 208 */     throw new ClassCastException("OHLCDataItem.compareTo().");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\OHLCDataItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */