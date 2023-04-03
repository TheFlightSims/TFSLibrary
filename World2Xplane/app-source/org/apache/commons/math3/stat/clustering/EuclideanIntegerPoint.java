/*     */ package org.apache.commons.math3.stat.clustering;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class EuclideanIntegerPoint implements Clusterable<EuclideanIntegerPoint>, Serializable {
/*     */   private static final long serialVersionUID = 3946024775784901369L;
/*     */   
/*     */   private final int[] point;
/*     */   
/*     */   public EuclideanIntegerPoint(int[] point) {
/*  44 */     this.point = point;
/*     */   }
/*     */   
/*     */   public int[] getPoint() {
/*  52 */     return this.point;
/*     */   }
/*     */   
/*     */   public double distanceFrom(EuclideanIntegerPoint p) {
/*  57 */     return MathArrays.distance(this.point, p.getPoint());
/*     */   }
/*     */   
/*     */   public EuclideanIntegerPoint centroidOf(Collection<EuclideanIntegerPoint> points) {
/*  62 */     int[] centroid = new int[(getPoint()).length];
/*  63 */     for (EuclideanIntegerPoint p : points) {
/*  64 */       for (int j = 0; j < centroid.length; j++)
/*  65 */         centroid[j] = centroid[j] + p.getPoint()[j]; 
/*     */     } 
/*  68 */     for (int i = 0; i < centroid.length; i++)
/*  69 */       centroid[i] = centroid[i] / points.size(); 
/*  71 */     return new EuclideanIntegerPoint(centroid);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  77 */     if (!(other instanceof EuclideanIntegerPoint))
/*  78 */       return false; 
/*  80 */     int[] otherPoint = ((EuclideanIntegerPoint)other).getPoint();
/*  81 */     if (this.point.length != otherPoint.length)
/*  82 */       return false; 
/*  84 */     for (int i = 0; i < this.point.length; i++) {
/*  85 */       if (this.point[i] != otherPoint[i])
/*  86 */         return false; 
/*     */     } 
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     int hashCode = 0;
/*  96 */     for (int arr$[] = this.point, len$ = arr$.length, i$ = 0; i$ < len$; ) {
/*  96 */       Integer i = Integer.valueOf(arr$[i$]);
/*  97 */       hashCode += i.hashCode() * 13 + 7;
/*     */       i$++;
/*     */     } 
/*  99 */     return hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     StringBuilder buff = new StringBuilder("(");
/* 109 */     int[] coordinates = getPoint();
/* 110 */     for (int i = 0; i < coordinates.length; i++) {
/* 111 */       buff.append(coordinates[i]);
/* 112 */       if (i < coordinates.length - 1)
/* 113 */         buff.append(","); 
/*     */     } 
/* 116 */     buff.append(")");
/* 117 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\clustering\EuclideanIntegerPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */