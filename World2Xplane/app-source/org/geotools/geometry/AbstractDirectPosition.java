/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public abstract class AbstractDirectPosition implements DirectPosition {
/*     */   public DirectPosition getDirectPosition() {
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public void setPosition(DirectPosition position) {
/*  70 */     int dimension = getDimension();
/*  71 */     if (position != null) {
/*  72 */       ensureDimensionMatch("position", position.getDimension(), dimension);
/*  73 */       for (int i = 0; i < dimension; i++)
/*  74 */         setOrdinate(i, position.getOrdinate(i)); 
/*     */     } else {
/*  77 */       for (int i = 0; i < dimension; i++)
/*  78 */         setOrdinate(i, Double.NaN); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] getCoordinate() {
/*  90 */     double[] ordinates = new double[getDimension()];
/*  91 */     for (int i = 0; i < ordinates.length; i++)
/*  92 */       ordinates[i] = getOrdinate(i); 
/*  94 */     return ordinates;
/*     */   }
/*     */   
/*     */   static void checkCoordinateReferenceSystemDimension(CoordinateReferenceSystem crs, int expected) throws MismatchedDimensionException {
/* 108 */     if (crs != null) {
/* 109 */       int dimension = crs.getCoordinateSystem().getDimension();
/* 110 */       if (dimension != expected)
/* 111 */         throw new MismatchedDimensionException(Errors.format(94, crs.getName().getCode(), Integer.valueOf(dimension), Integer.valueOf(expected))); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static void ensureDimensionMatch(String name, int dimension, int expectedDimension) throws MismatchedDimensionException {
/* 131 */     if (dimension != expectedDimension)
/* 132 */       throw new MismatchedDimensionException(Errors.format(94, name, Integer.valueOf(dimension), Integer.valueOf(expectedDimension))); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     return toString(this);
/*     */   }
/*     */   
/*     */   static String toString(DirectPosition position) {
/* 152 */     StringBuilder buffer = (new StringBuilder(Classes.getShortClassName(position))).append('[');
/* 153 */     int dimension = position.getDimension();
/* 154 */     for (int i = 0; i < dimension; i++) {
/* 155 */       if (i != 0)
/* 156 */         buffer.append(", "); 
/* 158 */       buffer.append(position.getOrdinate(i));
/*     */     } 
/* 160 */     return buffer.append(']').toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 170 */     return hashCode(this);
/*     */   }
/*     */   
/*     */   static int hashCode(DirectPosition position) {
/* 177 */     int dimension = position.getDimension();
/* 178 */     int code = 1;
/* 179 */     for (int i = 0; i < dimension; i++) {
/* 180 */       long bits = Double.doubleToLongBits(position.getOrdinate(i));
/* 181 */       code = 31 * code + ((int)bits ^ (int)(bits >>> 32L));
/*     */     } 
/* 183 */     CoordinateReferenceSystem crs = position.getCoordinateReferenceSystem();
/* 184 */     if (crs != null)
/* 185 */       code += crs.hashCode(); 
/* 187 */     return code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 200 */     if (object instanceof DirectPosition) {
/* 201 */       DirectPosition that = (DirectPosition)object;
/* 202 */       int dimension = getDimension();
/* 203 */       if (dimension == that.getDimension()) {
/* 204 */         for (int i = 0; i < dimension; i++) {
/* 205 */           if (!Utilities.equals(getOrdinate(i), that.getOrdinate(i)))
/* 206 */             return false; 
/*     */         } 
/* 209 */         if (Utilities.equals(getCoordinateReferenceSystem(), that.getCoordinateReferenceSystem())) {
/* 212 */           assert hashCode() == that.hashCode() : this;
/* 213 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\AbstractDirectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */