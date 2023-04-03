/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ class RubberSheetTransform extends AbstractMathTransform implements MathTransform2D {
/*  53 */   private TINTriangle previousTriangle = null;
/*     */   
/*     */   private HashMap trianglesToKeysMap;
/*     */   
/*     */   public RubberSheetTransform(HashMap trianglesToAffineTransform) {
/*  70 */     this.trianglesToKeysMap = trianglesToAffineTransform;
/*     */   }
/*     */   
/*     */   public final int getSourceDimensions() {
/*  79 */     return 2;
/*     */   }
/*     */   
/*     */   public final int getTargetDimensions() {
/*  88 */     return 2;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 103 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 104 */     StringBuilder buffer = new StringBuilder();
/* 106 */     for (Iterator<TINTriangle> i = this.trianglesToKeysMap.keySet().iterator(); i.hasNext(); ) {
/* 107 */       TINTriangle trian = i.next();
/* 108 */       MathTransform mt = (MathTransform)this.trianglesToKeysMap.get(trian);
/* 109 */       buffer.append(trian.toString());
/* 110 */       buffer.append(lineSeparator);
/* 111 */       buffer.append(mt.toString());
/* 112 */       buffer.append(lineSeparator);
/*     */     } 
/* 115 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPt, int dstOff, int numPts) throws TransformException {
/* 123 */     for (int i = srcOff; i < numPts; i++) {
/* 124 */       DirectPosition2D directPosition2D = new DirectPosition2D(srcPts[2 * i], srcPts[2 * i + 1]);
/* 126 */       TINTriangle triangle = searchTriangle((DirectPosition)directPosition2D);
/* 128 */       AffineTransform AT = (AffineTransform)this.trianglesToKeysMap.get(triangle);
/* 130 */       Point2D dst = AT.transform((Point2D)directPosition2D, null);
/* 132 */       dstPt[2 * i] = dst.getX();
/* 133 */       dstPt[2 * i + 1] = dst.getY();
/*     */     } 
/*     */   }
/*     */   
/*     */   private TINTriangle searchTriangle(DirectPosition p) throws TransformException {
/* 152 */     TINTriangle potentialTriangle = this.previousTriangle;
/* 153 */     if (potentialTriangle != null && potentialTriangle.containsOrIsVertex(p))
/* 155 */       return potentialTriangle; 
/* 158 */     for (Iterator<TINTriangle> i = this.trianglesToKeysMap.keySet().iterator(); i.hasNext(); ) {
/* 159 */       TINTriangle triangle = i.next();
/* 161 */       if (triangle.containsOrIsVertex(p)) {
/* 162 */         this.previousTriangle = triangle;
/* 164 */         return triangle;
/*     */       } 
/*     */     } 
/* 167 */     throw new TransformException("Points are outside the scope");
/*     */   }
/*     */   
/*     */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 176 */     return (MathTransform2D)super.inverse();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\RubberSheetTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */