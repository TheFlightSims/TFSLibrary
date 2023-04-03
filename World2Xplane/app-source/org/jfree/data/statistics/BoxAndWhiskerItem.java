/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class BoxAndWhiskerItem implements Serializable {
/*     */   private static final long serialVersionUID = 7329649623148167423L;
/*     */   
/*     */   private Number mean;
/*     */   
/*     */   private Number median;
/*     */   
/*     */   private Number q1;
/*     */   
/*     */   private Number q3;
/*     */   
/*     */   private Number minRegularValue;
/*     */   
/*     */   private Number maxRegularValue;
/*     */   
/*     */   private Number minOutlier;
/*     */   
/*     */   private Number maxOutlier;
/*     */   
/*     */   private List outliers;
/*     */   
/*     */   public BoxAndWhiskerItem(Number mean, Number median, Number q1, Number q3, Number minRegularValue, Number maxRegularValue, Number minOutlier, Number maxOutlier, List outliers) {
/* 113 */     this.mean = mean;
/* 114 */     this.median = median;
/* 115 */     this.q1 = q1;
/* 116 */     this.q3 = q3;
/* 117 */     this.minRegularValue = minRegularValue;
/* 118 */     this.maxRegularValue = maxRegularValue;
/* 119 */     this.minOutlier = minOutlier;
/* 120 */     this.maxOutlier = maxOutlier;
/* 121 */     this.outliers = outliers;
/*     */   }
/*     */   
/*     */   public Number getMean() {
/* 131 */     return this.mean;
/*     */   }
/*     */   
/*     */   public Number getMedian() {
/* 140 */     return this.median;
/*     */   }
/*     */   
/*     */   public Number getQ1() {
/* 149 */     return this.q1;
/*     */   }
/*     */   
/*     */   public Number getQ3() {
/* 158 */     return this.q3;
/*     */   }
/*     */   
/*     */   public Number getMinRegularValue() {
/* 167 */     return this.minRegularValue;
/*     */   }
/*     */   
/*     */   public Number getMaxRegularValue() {
/* 176 */     return this.maxRegularValue;
/*     */   }
/*     */   
/*     */   public Number getMinOutlier() {
/* 185 */     return this.minOutlier;
/*     */   }
/*     */   
/*     */   public Number getMaxOutlier() {
/* 194 */     return this.maxOutlier;
/*     */   }
/*     */   
/*     */   public List getOutliers() {
/* 203 */     if (this.outliers == null)
/* 204 */       return null; 
/* 206 */     return Collections.unmodifiableList(this.outliers);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 218 */     if (obj == this)
/* 219 */       return true; 
/* 221 */     if (!(obj instanceof BoxAndWhiskerItem))
/* 222 */       return false; 
/* 224 */     BoxAndWhiskerItem that = (BoxAndWhiskerItem)obj;
/* 225 */     if (!ObjectUtilities.equal(this.mean, that.mean))
/* 226 */       return false; 
/* 228 */     if (!ObjectUtilities.equal(this.median, that.median))
/* 229 */       return false; 
/* 231 */     if (!ObjectUtilities.equal(this.q1, that.q1))
/* 232 */       return false; 
/* 234 */     if (!ObjectUtilities.equal(this.q3, that.q3))
/* 235 */       return false; 
/* 237 */     if (!ObjectUtilities.equal(this.minRegularValue, that.minRegularValue))
/* 240 */       return false; 
/* 242 */     if (!ObjectUtilities.equal(this.maxRegularValue, that.maxRegularValue))
/* 245 */       return false; 
/* 247 */     if (!ObjectUtilities.equal(this.minOutlier, that.minOutlier))
/* 248 */       return false; 
/* 250 */     if (!ObjectUtilities.equal(this.maxOutlier, that.maxOutlier))
/* 251 */       return false; 
/* 253 */     if (!ObjectUtilities.equal(this.outliers, that.outliers))
/* 254 */       return false; 
/* 256 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\BoxAndWhiskerItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */