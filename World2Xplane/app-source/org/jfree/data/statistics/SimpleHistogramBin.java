/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class SimpleHistogramBin implements Comparable, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 3480862537505941742L;
/*     */   
/*     */   private double lowerBound;
/*     */   
/*     */   private double upperBound;
/*     */   
/*     */   private boolean includeLowerBound;
/*     */   
/*     */   private boolean includeUpperBound;
/*     */   
/*     */   private int itemCount;
/*     */   
/*     */   public SimpleHistogramBin(double lowerBound, double upperBound) {
/*  87 */     this(lowerBound, upperBound, true, true);
/*     */   }
/*     */   
/*     */   public SimpleHistogramBin(double lowerBound, double upperBound, boolean includeLowerBound, boolean includeUpperBound) {
/* 101 */     if (lowerBound >= upperBound)
/* 102 */       throw new IllegalArgumentException("Invalid bounds"); 
/* 104 */     this.lowerBound = lowerBound;
/* 105 */     this.upperBound = upperBound;
/* 106 */     this.includeLowerBound = includeLowerBound;
/* 107 */     this.includeUpperBound = includeUpperBound;
/* 108 */     this.itemCount = 0;
/*     */   }
/*     */   
/*     */   public double getLowerBound() {
/* 117 */     return this.lowerBound;
/*     */   }
/*     */   
/*     */   public double getUpperBound() {
/* 126 */     return this.upperBound;
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 135 */     return this.itemCount;
/*     */   }
/*     */   
/*     */   public void setItemCount(int count) {
/* 144 */     this.itemCount = count;
/*     */   }
/*     */   
/*     */   public boolean accepts(double value) {
/* 156 */     if (Double.isNaN(value))
/* 157 */       return false; 
/* 159 */     if (value < this.lowerBound)
/* 160 */       return false; 
/* 162 */     if (value > this.upperBound)
/* 163 */       return false; 
/* 165 */     if (value == this.lowerBound)
/* 166 */       return this.includeLowerBound; 
/* 168 */     if (value == this.upperBound)
/* 169 */       return this.includeUpperBound; 
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public boolean overlapsWith(SimpleHistogramBin bin) {
/* 183 */     if (this.upperBound < bin.lowerBound)
/* 184 */       return false; 
/* 186 */     if (this.lowerBound > bin.upperBound)
/* 187 */       return false; 
/* 189 */     if (this.upperBound == bin.lowerBound)
/* 190 */       return (this.includeUpperBound && bin.includeLowerBound); 
/* 192 */     if (this.lowerBound == bin.upperBound)
/* 193 */       return (this.includeLowerBound && bin.includeUpperBound); 
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 208 */     if (!(obj instanceof SimpleHistogramBin))
/* 209 */       return 0; 
/* 211 */     SimpleHistogramBin bin = (SimpleHistogramBin)obj;
/* 212 */     if (this.lowerBound < bin.lowerBound)
/* 213 */       return -1; 
/* 215 */     if (this.lowerBound > bin.lowerBound)
/* 216 */       return 1; 
/* 219 */     if (this.upperBound < bin.upperBound)
/* 220 */       return -1; 
/* 222 */     if (this.upperBound > bin.upperBound)
/* 223 */       return 1; 
/* 225 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 236 */     if (!(obj instanceof SimpleHistogramBin))
/* 237 */       return false; 
/* 239 */     SimpleHistogramBin that = (SimpleHistogramBin)obj;
/* 240 */     if (this.lowerBound != that.lowerBound)
/* 241 */       return false; 
/* 243 */     if (this.upperBound != that.upperBound)
/* 244 */       return false; 
/* 246 */     if (this.includeLowerBound != that.includeLowerBound)
/* 247 */       return false; 
/* 249 */     if (this.includeUpperBound != that.includeUpperBound)
/* 250 */       return false; 
/* 252 */     if (this.itemCount != that.itemCount)
/* 253 */       return false; 
/* 255 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 266 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\SimpleHistogramBin.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */