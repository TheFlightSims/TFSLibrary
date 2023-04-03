/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class LengthFunction extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("length", FunctionNameImpl.parameter("length", Integer.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class) });
/*    */   
/*    */   public LengthFunction() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 49 */     Expression ae = getParameters().get(0);
/* 50 */     String value = (String)ae.evaluate(feature, String.class);
/* 51 */     return new Integer((value == null) ? 0 : value.length());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LengthFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */