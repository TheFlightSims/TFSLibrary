/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequenceFactory;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class DefaultCoordinateSequenceTransformer implements CoordinateSequenceTransformer {
/*  58 */   private final transient double[] buffer = new double[96];
/*     */   
/*     */   private final CoordinateSequenceFactory csFactory;
/*     */   
/*     */   public DefaultCoordinateSequenceTransformer() {
/*  69 */     this.csFactory = (CoordinateSequenceFactory)CoordinateArraySequenceFactory.instance();
/*     */   }
/*     */   
/*     */   public DefaultCoordinateSequenceTransformer(CoordinateSequenceFactory csFactory) {
/*  73 */     this.csFactory = csFactory;
/*     */   }
/*     */   
/*     */   public CoordinateSequence transform(CoordinateSequence sequence, MathTransform transform) throws TransformException {
/*  81 */     int sourceDim = transform.getSourceDimensions();
/*  82 */     int targetDim = transform.getTargetDimensions();
/*  83 */     int size = sequence.size();
/*  84 */     Coordinate[] tcs = new Coordinate[size];
/*  85 */     int bufferCapacity = this.buffer.length / Math.max(sourceDim, targetDim);
/*  86 */     int remainingBeforeFlush = Math.min(bufferCapacity, size);
/*  87 */     int ib = 0;
/*  88 */     int it = 0;
/*  92 */     int targetCSDim = targetDim + sequence.getDimension() - sourceDim;
/*  93 */     CoordinateSequence result = this.csFactory.create(sequence.size(), targetCSDim);
/*  95 */     for (int i = 0; i < size; i++) {
/*  96 */       switch (sourceDim) {
/*     */         default:
/*  98 */           throw new MismatchedDimensionException();
/*     */         case 3:
/* 101 */           this.buffer[ib + 2] = sequence.getOrdinate(i, 2);
/*     */         case 2:
/* 104 */           this.buffer[ib + 1] = sequence.getY(i);
/*     */         case 1:
/* 107 */           this.buffer[ib] = sequence.getX(i);
/*     */           break;
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 113 */       ib += sourceDim;
/* 115 */       if (--remainingBeforeFlush == 0) {
/* 120 */         assert ib % sourceDim == 0;
/* 122 */         int n = ib / sourceDim;
/* 123 */         transform.transform(this.buffer, 0, this.buffer, 0, n);
/* 124 */         ib = 0;
/* 126 */         for (int j = 0; j < n; j++) {
/* 130 */           int oi = 0;
/* 131 */           for (; oi < targetDim; oi++)
/* 132 */             result.setOrdinate(it, oi, this.buffer[ib++]); 
/* 135 */           for (; oi < targetCSDim; oi++)
/* 136 */             result.setOrdinate(it, oi, sequence.getOrdinate(it, oi + targetDim - sourceDim)); 
/* 141 */           for (; oi < result.getDimension(); oi++)
/* 142 */             result.setOrdinate(it, oi, Double.NaN); 
/* 144 */           it++;
/*     */         } 
/* 146 */         assert ib == n * targetDim;
/* 147 */         ib = 0;
/* 148 */         remainingBeforeFlush = Math.min(bufferCapacity, size - i + 1);
/*     */       } 
/*     */     } 
/* 151 */     assert it == tcs.length : tcs.length - it;
/* 153 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\DefaultCoordinateSequenceTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */