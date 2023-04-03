/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class DirectPosition2D extends Point2D.Double implements DirectPosition, Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 835130287438466996L;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public DirectPosition2D() {}
/*     */   
/*     */   public DirectPosition2D(CoordinateReferenceSystem crs) {
/*  97 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public DirectPosition2D(double x, double y) {
/* 112 */     super(x, y);
/*     */   }
/*     */   
/*     */   public DirectPosition2D(CoordinateReferenceSystem crs, double x, double y) {
/* 131 */     super(x, y);
/* 132 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public DirectPosition2D(Point2D point) {
/* 141 */     super(point.getX(), point.getY());
/* 142 */     if (point instanceof DirectPosition)
/* 143 */       setCoordinateReferenceSystem(((DirectPosition)point).getCoordinateReferenceSystem()); 
/*     */   }
/*     */   
/*     */   public DirectPosition2D(DirectPosition point) {
/* 153 */     setLocation(point);
/*     */   }
/*     */   
/*     */   public DirectPosition getDirectPosition() {
/* 163 */     return this;
/*     */   }
/*     */   
/*     */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 175 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 184 */     AbstractDirectPosition.checkCoordinateReferenceSystemDimension(crs, 2);
/* 185 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public final int getDimension() {
/* 195 */     return 2;
/*     */   }
/*     */   
/*     */   public double[] getCoordinate() {
/* 205 */     return new double[] { this.x, this.y };
/*     */   }
/*     */   
/*     */   public final double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 218 */     switch (dimension) {
/*     */       case 0:
/* 219 */         return this.x;
/*     */       case 1:
/* 220 */         return this.y;
/*     */     } 
/* 221 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public final void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
/* 235 */     switch (dimension) {
/*     */       case 0:
/* 236 */         this.x = value;
/*     */         return;
/*     */       case 1:
/* 237 */         this.y = value;
/*     */         return;
/*     */     } 
/* 238 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public void setLocation(DirectPosition position) throws MismatchedDimensionException {
/* 251 */     AbstractDirectPosition.ensureDimensionMatch("position", position.getDimension(), 2);
/* 252 */     setCoordinateReferenceSystem(position.getCoordinateReferenceSystem());
/* 253 */     this.x = position.getOrdinate(0);
/* 254 */     this.y = position.getOrdinate(1);
/*     */   }
/*     */   
/*     */   public Point2D toPoint2D() {
/* 263 */     return new Point2D.Double(this.x, this.y);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 275 */     return AbstractDirectPosition.toString(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 286 */     return AbstractDirectPosition.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 304 */     if (object instanceof DirectPosition) {
/* 305 */       DirectPosition other = (DirectPosition)object;
/* 306 */       if (other.getDimension() == 2 && Utilities.equals(other.getOrdinate(0), this.x) && Utilities.equals(other.getOrdinate(1), this.y) && Utilities.equals(other.getCoordinateReferenceSystem(), this.crs)) {
/* 311 */         assert hashCode() == other.hashCode() : this;
/* 312 */         return true;
/*     */       } 
/* 314 */       return false;
/*     */     } 
/* 321 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public DirectPosition2D clone() {
/* 331 */     return (DirectPosition2D)super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 339 */     out.defaultWriteObject();
/* 340 */     out.writeDouble(this.x);
/* 341 */     out.writeDouble(this.y);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 349 */     in.defaultReadObject();
/* 350 */     this.x = in.readDouble();
/* 351 */     this.y = in.readDouble();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\DirectPosition2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */