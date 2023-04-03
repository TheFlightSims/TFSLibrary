/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class MinFunction extends FunctionExpressionImpl {
/* 43 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Min", FunctionNameImpl.parameter("min", Double.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class), FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public MinFunction() {
/* 51 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 62 */     Expression expA = getParameters().get(0);
/* 63 */     Expression expB = getParameters().get(1);
/* 65 */     double first = ((Number)expA.evaluate(feature)).doubleValue();
/* 66 */     double second = ((Number)expB.evaluate(feature)).doubleValue();
/* 68 */     return new Double(Math.min(first, second));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MinFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */