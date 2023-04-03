/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class GeneralDirectPosition extends AbstractDirectPosition implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 9071833698385715524L;
/*     */   
/*     */   public final double[] ordinates;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public GeneralDirectPosition(CoordinateReferenceSystem crs) {
/*  84 */     this(crs.getCoordinateSystem().getDimension());
/*  85 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(int numDim) throws NegativeArraySizeException {
/*  95 */     this.ordinates = new double[numDim];
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(double[] ordinates) {
/* 105 */     this.ordinates = (double[])ordinates.clone();
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(double x, double y) {
/* 119 */     this.ordinates = new double[] { x, y };
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(double x, double y, double z) {
/* 134 */     this.ordinates = new double[] { x, y, z };
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(Point2D point) {
/* 143 */     this(point.getX(), point.getY());
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition(DirectPosition point) {
/* 154 */     this.ordinates = point.getCoordinate();
/* 155 */     this.crs = point.getCoordinateReferenceSystem();
/*     */   }
/*     */   
/*     */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 167 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 180 */     checkCoordinateReferenceSystemDimension(crs, getDimension());
/* 181 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public final int getDimension() {
/* 192 */     return this.ordinates.length;
/*     */   }
/*     */   
/*     */   public final double[] getCoordinate() {
/* 203 */     return (double[])this.ordinates.clone();
/*     */   }
/*     */   
/*     */   public final double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 214 */     return this.ordinates[dimension];
/*     */   }
/*     */   
/*     */   public final void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
/* 225 */     this.ordinates[dimension] = value;
/*     */   }
/*     */   
/*     */   public final void setLocation(DirectPosition position) throws MismatchedDimensionException {
/* 239 */     ensureDimensionMatch("position", position.getDimension(), this.ordinates.length);
/* 240 */     setCoordinateReferenceSystem(position.getCoordinateReferenceSystem());
/* 241 */     for (int i = 0; i < this.ordinates.length; i++)
/* 242 */       this.ordinates[i] = position.getOrdinate(i); 
/*     */   }
/*     */   
/*     */   public final void setLocation(GeneralDirectPosition position) throws MismatchedDimensionException {
/* 255 */     ensureDimensionMatch("position", position.ordinates.length, this.ordinates.length);
/* 256 */     setCoordinateReferenceSystem(position.crs);
/* 257 */     System.arraycopy(position.ordinates, 0, this.ordinates, 0, this.ordinates.length);
/*     */   }
/*     */   
/*     */   public final void setLocation(Point2D point) throws MismatchedDimensionException {
/* 268 */     if (this.ordinates.length != 2)
/* 269 */       throw new MismatchedDimensionException(Errors.format(127, Integer.valueOf(this.ordinates.length))); 
/* 272 */     this.ordinates[0] = point.getX();
/* 273 */     this.ordinates[1] = point.getY();
/*     */   }
/*     */   
/*     */   public Point2D toPoint2D() throws IllegalStateException {
/* 284 */     if (this.ordinates.length != 2)
/* 285 */       throw new IllegalStateException(Errors.format(127, Integer.valueOf(this.ordinates.length))); 
/* 288 */     return new Point2D.Double(this.ordinates[0], this.ordinates[1]);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 296 */     int code = Arrays.hashCode(this.ordinates);
/* 297 */     if (this.crs != null)
/* 298 */       code += this.crs.hashCode(); 
/* 300 */     assert code == super.hashCode();
/* 301 */     return code;
/*     */   }
/*     */   
/*     */   public GeneralDirectPosition clone() {
/* 309 */     return new GeneralDirectPosition(this.ordinates);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\GeneralDirectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */