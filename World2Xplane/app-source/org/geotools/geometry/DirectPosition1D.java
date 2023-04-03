/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class DirectPosition1D extends AbstractDirectPosition implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 3235094562875693710L;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public double ordinate;
/*     */   
/*     */   public DirectPosition1D() {}
/*     */   
/*     */   public DirectPosition1D(CoordinateReferenceSystem crs) {
/*  68 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public DirectPosition1D(double ordinate) {
/*  77 */     this.ordinate = ordinate;
/*     */   }
/*     */   
/*     */   public DirectPosition1D(DirectPosition point) {
/*  86 */     setLocation(point);
/*     */   }
/*     */   
/*     */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/*  98 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 107 */     checkCoordinateReferenceSystemDimension(crs, 1);
/* 108 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public final int getDimension() {
/* 118 */     return 1;
/*     */   }
/*     */   
/*     */   public double[] getCoordinate() {
/* 129 */     return new double[] { this.ordinate };
/*     */   }
/*     */   
/*     */   public final double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 142 */     if (dimension == 0)
/* 143 */       return this.ordinate; 
/* 145 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public final void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
/* 159 */     if (dimension == 0) {
/* 160 */       this.ordinate = value;
/*     */     } else {
/* 162 */       throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLocation(DirectPosition position) throws MismatchedDimensionException {
/* 175 */     AbstractDirectPosition.ensureDimensionMatch("position", position.getDimension(), 1);
/* 176 */     setCoordinateReferenceSystem(position.getCoordinateReferenceSystem());
/* 177 */     this.ordinate = position.getOrdinate(0);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 185 */     long value = Double.doubleToLongBits(this.ordinate);
/* 186 */     int code = 31 + ((int)value ^ (int)(value >>> 32L));
/* 187 */     if (this.crs != null)
/* 188 */       code += this.crs.hashCode(); 
/* 190 */     assert code == super.hashCode();
/* 191 */     return code;
/*     */   }
/*     */   
/*     */   public DirectPosition1D clone() {
/*     */     try {
/* 200 */       return (DirectPosition1D)super.clone();
/* 201 */     } catch (CloneNotSupportedException exception) {
/* 203 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\DirectPosition1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */