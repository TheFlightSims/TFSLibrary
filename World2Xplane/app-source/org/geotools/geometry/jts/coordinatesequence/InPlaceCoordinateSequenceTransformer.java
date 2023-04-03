/*     */ package org.geotools.geometry.jts.coordinatesequence;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
/*     */ import org.geotools.geometry.jts.CoordinateSequenceTransformer;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class InPlaceCoordinateSequenceTransformer implements CoordinateSequenceTransformer {
/*     */   public CoordinateSequence transform(CoordinateSequence cs, MathTransform transform) throws TransformException {
/*  59 */     if (cs instanceof PackedCoordinateSequence)
/*  60 */       return transformInternal((PackedCoordinateSequence)cs, transform); 
/*  62 */     throw new TransformException(cs.getClass().getName() + " is not a implementation that is known to be transformable in place");
/*     */   }
/*     */   
/*  65 */   FlyWeightDirectPosition start = new FlyWeightDirectPosition(2);
/*     */   
/*     */   private CoordinateSequence transformInternal(PackedCoordinateSequence sequence, MathTransform transform) throws TransformException {
/*  69 */     this.start.setSequence(sequence);
/*  70 */     for (int i = 0; i < sequence.size(); i++) {
/*  71 */       this.start.setOffset(i);
/*     */       try {
/*  73 */         transform.transform(this.start, this.start);
/*  74 */       } catch (MismatchedDimensionException e) {
/*  75 */         throw new TransformException("", e);
/*     */       } 
/*     */     } 
/*  78 */     return (CoordinateSequence)sequence;
/*     */   }
/*     */   
/*     */   private class FlyWeightDirectPosition implements DirectPosition {
/*     */     PackedCoordinateSequence sequence;
/*     */     
/*  83 */     int offset = 0;
/*     */     
/*     */     private int dimension;
/*     */     
/*     */     public FlyWeightDirectPosition(int dim) {
/*  91 */       this.dimension = dim;
/*     */     }
/*     */     
/*     */     public void setOffset(int offset) {
/*  98 */       this.offset = offset;
/*     */     }
/*     */     
/*     */     public void setSequence(PackedCoordinateSequence sequence) {
/* 105 */       this.sequence = sequence;
/*     */     }
/*     */     
/*     */     public int getDimension() {
/* 112 */       return this.dimension;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public double[] getCoordinates() {
/* 120 */       return getCoordinate();
/*     */     }
/*     */     
/*     */     public double[] getCoordinate() {
/* 127 */       return new double[] { this.sequence.getX(this.offset), this.sequence.getY(this.offset), this.sequence.getOrdinate(this.offset, 2) };
/*     */     }
/*     */     
/*     */     public double getOrdinate(int arg0) throws IndexOutOfBoundsException {
/* 134 */       return this.sequence.getOrdinate(this.offset, arg0);
/*     */     }
/*     */     
/*     */     public void setOrdinate(int arg0, double arg1) throws IndexOutOfBoundsException {
/* 141 */       this.sequence.setOrdinate(this.offset, arg0, arg1);
/*     */     }
/*     */     
/*     */     public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 149 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public FlyWeightDirectPosition clone() {
/* 156 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public DirectPosition getPosition() {
/* 163 */       return this;
/*     */     }
/*     */     
/*     */     public DirectPosition getDirectPosition() {
/* 170 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\InPlaceCoordinateSequenceTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */