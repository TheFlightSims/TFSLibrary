/*    */ package org.geotools.styling.visitor;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.measure.unit.NonSI;
/*    */ import org.opengis.filter.FilterFactory2;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class DpiRescaleStyleVisitor extends RescaleStyleVisitor {
/*    */   public DpiRescaleStyleVisitor(double scale) {
/* 42 */     super(scale);
/*    */   }
/*    */   
/*    */   public DpiRescaleStyleVisitor(FilterFactory2 filterFactory, double scale) {
/* 46 */     super(filterFactory, scale);
/*    */   }
/*    */   
/*    */   protected Expression rescale(Expression expr) {
/* 52 */     if (expr == null)
/* 53 */       return null; 
/* 55 */     if (expr == Expression.NIL)
/* 56 */       return Expression.NIL; 
/* 61 */     Measure v = new Measure(expr, this.defaultUnit);
/* 62 */     return RescalingMode.Pixels.rescaleToExpression(this.scale, v);
/*    */   }
/*    */   
/*    */   float[] rescale(float[] values) {
/* 67 */     if (this.defaultUnit == null || this.defaultUnit == NonSI.PIXEL)
/* 68 */       return super.rescale(values); 
/* 70 */     return values;
/*    */   }
/*    */   
/*    */   protected void rescaleOption(Map<String, String> options, String key, double defaultValue) {
/* 76 */     double scaleFactor = ((Double)this.scale.evaluate(null, Double.class)).doubleValue();
/* 77 */     String value = options.get(key);
/* 78 */     if (value == null)
/* 79 */       value = String.valueOf(defaultValue); 
/* 82 */     Measure v = new Measure(value, this.defaultUnit);
/* 83 */     String rescaled = RescalingMode.Pixels.rescaleToString(scaleFactor, v);
/* 84 */     options.put(key, String.valueOf(rescaled));
/*    */   }
/*    */   
/*    */   protected void rescaleOption(Map<String, String> options, String key, int defaultValue) {
/* 89 */     double scaleFactor = ((Double)this.scale.evaluate(null, Double.class)).doubleValue();
/* 90 */     String value = options.get(key);
/* 91 */     if (value == null)
/* 92 */       value = String.valueOf(defaultValue); 
/* 95 */     Measure v = new Measure(value, this.defaultUnit);
/* 96 */     String rescaled = RescalingMode.Pixels.rescaleToString(scaleFactor, v);
/* 97 */     options.put(key, String.valueOf(rescaled));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\DpiRescaleStyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */