/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class MaxFunction extends FunctionExpressionImpl {
/* 40 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Max", FunctionNameImpl.parameter("max", Double.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class), FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public MaxFunction() {
/* 48 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 59 */     Expression expA = getParameters().get(0);
/* 60 */     Expression expB = getParameters().get(1);
/* 62 */     double first = ((Number)expA.evaluate(feature)).doubleValue();
/* 63 */     double second = ((Number)expB.evaluate(feature)).doubleValue();
/* 65 */     return new Double(Math.max(first, second));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MaxFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */