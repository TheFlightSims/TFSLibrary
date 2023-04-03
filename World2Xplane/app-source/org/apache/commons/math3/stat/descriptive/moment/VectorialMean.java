/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ 
/*     */ public class VectorialMean implements Serializable {
/*     */   private static final long serialVersionUID = 8223009086481006892L;
/*     */   
/*     */   private final Mean[] means;
/*     */   
/*     */   public VectorialMean(int dimension) {
/*  41 */     this.means = new Mean[dimension];
/*  42 */     for (int i = 0; i < dimension; i++)
/*  43 */       this.means[i] = new Mean(); 
/*     */   }
/*     */   
/*     */   public void increment(double[] v) {
/*  53 */     if (v.length != this.means.length)
/*  54 */       throw new DimensionMismatchException(v.length, this.means.length); 
/*  56 */     for (int i = 0; i < v.length; i++)
/*  57 */       this.means[i].increment(v[i]); 
/*     */   }
/*     */   
/*     */   public double[] getResult() {
/*  66 */     double[] result = new double[this.means.length];
/*  67 */     for (int i = 0; i < result.length; i++)
/*  68 */       result[i] = this.means[i].getResult(); 
/*  70 */     return result;
/*     */   }
/*     */   
/*     */   public long getN() {
/*  78 */     return (this.means.length == 0) ? 0L : this.means[0].getN();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  84 */     int prime = 31;
/*  85 */     int result = 1;
/*  86 */     result = 31 * result + Arrays.hashCode((Object[])this.means);
/*  87 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  93 */     if (this == obj)
/*  94 */       return true; 
/*  96 */     if (!(obj instanceof VectorialMean))
/*  97 */       return false; 
/*  99 */     VectorialMean other = (VectorialMean)obj;
/* 100 */     if (!Arrays.equals((Object[])this.means, (Object[])other.means))
/* 101 */       return false; 
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\VectorialMean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */