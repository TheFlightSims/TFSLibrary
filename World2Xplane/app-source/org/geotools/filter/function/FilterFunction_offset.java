/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_offset extends FunctionExpressionImpl implements GeometryTransformation {
/*  41 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("offset", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("offsetX", Double.class), FunctionNameImpl.parameter("offsetY", Double.class) });
/*     */   
/*     */   public FilterFunction_offset() {
/*  47 */     super(NAME);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  51 */     return 3;
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*  55 */     Geometry geom = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/*  56 */     Double offsetX = (Double)getExpression(1).evaluate(feature, Double.class);
/*  57 */     if (offsetX == null)
/*  58 */       offsetX = Double.valueOf(0.0D); 
/*  59 */     Double offsetY = (Double)getExpression(2).evaluate(feature, Double.class);
/*  60 */     if (offsetY == null)
/*  61 */       offsetY = Double.valueOf(0.0D); 
/*  63 */     if (geom != null) {
/*  64 */       Geometry offseted = (Geometry)geom.clone();
/*  65 */       offseted.apply(new OffsetOrdinateFilter(offsetX.doubleValue(), offsetY.doubleValue()));
/*  66 */       return offseted;
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope invert(ReferencedEnvelope renderingEnvelope) {
/*  78 */     Double offsetX = (Double)getExpression(1).evaluate(null, Double.class);
/*  79 */     Double offsetY = (Double)getExpression(2).evaluate(null, Double.class);
/*  81 */     if (offsetX != null && offsetY != null) {
/*  82 */       ReferencedEnvelope offseted = new ReferencedEnvelope(renderingEnvelope);
/*  83 */       offseted.translate(-offsetX.doubleValue(), -offsetY.doubleValue());
/*  84 */       return offseted;
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */   
/*     */   public static class OffsetOrdinateFilter implements CoordinateSequenceFilter {
/*     */     double offsetX;
/*     */     
/*     */     double offsetY;
/*     */     
/*     */     public OffsetOrdinateFilter(double offsetX, double offsetY) {
/*  98 */       this.offsetX = offsetX;
/*  99 */       this.offsetY = offsetY;
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int i) {
/* 103 */       seq.setOrdinate(i, 0, seq.getOrdinate(i, 0) + this.offsetX);
/* 104 */       seq.setOrdinate(i, 1, seq.getOrdinate(i, 1) + this.offsetY);
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 108 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 112 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_offset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */