/*    */ package org.geotools.styling.visitor;
/*    */ 
/*    */ import javax.measure.quantity.Length;
/*    */ import javax.measure.unit.Unit;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class RescaleToPixelsFunction extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("rescaleToPixels", String.class, new Parameter[] { FunctionNameImpl.parameter("value", String.class), FunctionNameImpl.parameter("defaultUnit", Unit.class), FunctionNameImpl.parameter("scaleFactor", Double.class), FunctionNameImpl.parameter("rescalingMode", RescalingMode.class, 0, 1) });
/*    */   
/*    */   public RescaleToPixelsFunction() {
/* 57 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 62 */     String value = (String)getExpression(0).evaluate(feature, String.class);
/* 63 */     if (value == null || value.trim().isEmpty())
/* 64 */       return null; 
/* 67 */     Unit<Length> defaultUnit = (Unit<Length>)getExpression(1).evaluate(feature, Unit.class);
/* 69 */     Double scaleFactor = (Double)getExpression(2).evaluate(feature, Double.class);
/* 70 */     if (scaleFactor == null)
/* 71 */       throw new IllegalArgumentException("Invalid scale factor, it should be non null"); 
/* 75 */     RescalingMode mode = RescalingMode.KeepUnits;
/* 76 */     if (getParameters().size() >= 3) {
/* 77 */       RescalingMode theMode = (RescalingMode)getExpression(3).evaluate(feature, RescalingMode.class);
/* 78 */       if (theMode != null)
/* 79 */         mode = theMode; 
/*    */     } 
/* 84 */     Measure measure = new Measure(value, defaultUnit);
/* 85 */     String result = mode.rescaleToString(scaleFactor.doubleValue(), measure);
/* 87 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\RescaleToPixelsFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */