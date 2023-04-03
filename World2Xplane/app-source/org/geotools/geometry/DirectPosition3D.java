/*     */ package org.geotools.geometry;
/*     */ 
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
/*     */ public class DirectPosition3D implements DirectPosition, Serializable, Cloneable {
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   
/*     */   public double z;
/*     */   
/*     */   private static final long serialVersionUID = 835130287438466996L;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public DirectPosition3D() {}
/*     */   
/*     */   public DirectPosition3D(CoordinateReferenceSystem crs) {
/*  67 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public DirectPosition3D(double x, double y, double z) {
/*  78 */     this.x = x;
/*  79 */     this.y = y;
/*  80 */     this.z = z;
/*     */   }
/*     */   
/*     */   public DirectPosition3D(CoordinateReferenceSystem crs, double x, double y, double z) {
/*  94 */     this(x, y, z);
/*  95 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public DirectPosition3D(DirectPosition point) {
/* 103 */     setLocation(point);
/*     */   }
/*     */   
/*     */   public DirectPosition getDirectPosition() {
/* 112 */     return this;
/*     */   }
/*     */   
/*     */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 124 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 133 */     AbstractDirectPosition.checkCoordinateReferenceSystemDimension(crs, 3);
/* 134 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public final int getDimension() {
/* 144 */     return 3;
/*     */   }
/*     */   
/*     */   public double[] getCoordinate() {
/* 154 */     return new double[] { this.x, this.y, this.z };
/*     */   }
/*     */   
/*     */   public final double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 166 */     switch (dimension) {
/*     */       case 0:
/* 167 */         return this.x;
/*     */       case 1:
/* 168 */         return this.y;
/*     */       case 2:
/* 169 */         return this.z;
/*     */     } 
/* 170 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public final void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
/* 184 */     switch (dimension) {
/*     */       case 0:
/* 185 */         this.x = value;
/*     */         return;
/*     */       case 1:
/* 186 */         this.y = value;
/*     */         return;
/*     */       case 2:
/* 187 */         this.z = value;
/*     */         return;
/*     */     } 
/* 188 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public void setLocation(DirectPosition position) throws MismatchedDimensionException {
/* 201 */     AbstractDirectPosition.ensureDimensionMatch("position", position.getDimension(), 3);
/* 202 */     setCoordinateReferenceSystem(position.getCoordinateReferenceSystem());
/* 203 */     this.x = position.getOrdinate(0);
/* 204 */     this.y = position.getOrdinate(1);
/* 205 */     this.z = position.getOrdinate(2);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 213 */     return AbstractDirectPosition.toString(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 224 */     return AbstractDirectPosition.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 241 */     if (object instanceof DirectPosition) {
/* 242 */       DirectPosition other = (DirectPosition)object;
/* 243 */       if (other.getDimension() == 3 && Utilities.equals(other.getOrdinate(0), this.x) && Utilities.equals(other.getOrdinate(1), this.y) && Utilities.equals(other.getOrdinate(2), this.z) && Utilities.equals(other.getCoordinateReferenceSystem(), this.crs)) {
/* 249 */         assert hashCode() == other.hashCode() : this;
/* 250 */         return true;
/*     */       } 
/* 252 */       return false;
/*     */     } 
/* 254 */     return false;
/*     */   }
/*     */   
/*     */   public DirectPosition3D clone() {
/* 264 */     return new DirectPosition3D(this);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 272 */     out.defaultWriteObject();
/* 273 */     out.writeDouble(this.x);
/* 274 */     out.writeDouble(this.y);
/* 275 */     out.writeDouble(this.z);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 283 */     in.defaultReadObject();
/* 284 */     this.x = in.readDouble();
/* 285 */     this.y = in.readDouble();
/* 286 */     this.z = in.readDouble();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\DirectPosition3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */