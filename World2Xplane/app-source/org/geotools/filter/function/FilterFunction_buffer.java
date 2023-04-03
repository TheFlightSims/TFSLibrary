/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.geotools.geometry.jts.JTS;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_buffer extends FunctionExpressionImpl implements GeometryTransformation {
/* 41 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("buffer", FunctionNameImpl.parameter("buffer", Geometry.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("distance", Number.class) });
/*    */   
/*    */   public FilterFunction_buffer() {
/* 47 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     double arg1;
/*    */     try {
/* 55 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function buffer argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 63 */       arg1 = ((Double)getExpression(1).evaluate(feature, Double.class)).doubleValue();
/* 64 */     } catch (Exception e) {
/* 66 */       throw new IllegalArgumentException("Filter Function problem for function buffer argument #1 - expected type double");
/*    */     } 
/* 70 */     return StaticGeometry.buffer(arg0, Double.valueOf(arg1));
/*    */   }
/*    */   
/*    */   public ReferencedEnvelope invert(ReferencedEnvelope renderingEnvelope) {
/* 79 */     Double buffer = (Double)getExpression(1).evaluate(null, Double.class);
/* 80 */     if (buffer == null || buffer.doubleValue() <= 0.0D)
/* 81 */       return null; 
/* 84 */     Envelope bufferedEnvelope = JTS.toGeometry((Envelope)renderingEnvelope).buffer(buffer.doubleValue()).getEnvelopeInternal();
/* 85 */     return new ReferencedEnvelope(bufferedEnvelope, renderingEnvelope.getCoordinateReferenceSystem());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_buffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */