/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class MeanAndStandardDeviation implements Serializable {
/*     */   private static final long serialVersionUID = 7413468697315721515L;
/*     */   
/*     */   private Number mean;
/*     */   
/*     */   private Number standardDeviation;
/*     */   
/*     */   public MeanAndStandardDeviation(double mean, double standardDeviation) {
/*  73 */     this(new Double(mean), new Double(standardDeviation));
/*     */   }
/*     */   
/*     */   public MeanAndStandardDeviation(Number mean, Number standardDeviation) {
/*  84 */     this.mean = mean;
/*  85 */     this.standardDeviation = standardDeviation;
/*     */   }
/*     */   
/*     */   public Number getMean() {
/*  94 */     return this.mean;
/*     */   }
/*     */   
/*     */   public Number getStandardDeviation() {
/* 103 */     return this.standardDeviation;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 114 */     if (obj == this)
/* 115 */       return true; 
/* 117 */     if (!(obj instanceof MeanAndStandardDeviation))
/* 118 */       return false; 
/* 120 */     MeanAndStandardDeviation that = (MeanAndStandardDeviation)obj;
/* 121 */     if (!ObjectUtilities.equal(this.mean, that.mean))
/* 122 */       return false; 
/* 124 */     if (!ObjectUtilities.equal(this.standardDeviation, that.standardDeviation))
/* 127 */       return false; 
/* 129 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\MeanAndStandardDeviation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */