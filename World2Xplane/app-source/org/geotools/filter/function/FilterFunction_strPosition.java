/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import org.geotools.data.Parameter;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.geotools.text.Text;
/*    */ import org.geotools.util.KVP;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strPosition extends FunctionExpressionImpl {
/*    */   public static FunctionName NAME;
/*    */   
/*    */   static {
/* 50 */     Parameter<String> method = new Parameter("method", String.class, Text.text("direction"), Text.text("direction to search"), true, 1, 1, "forward", (Map)new KVP(new Object[] { "options", Arrays.asList(new String[] { "forward", "backToFront" }) }));
/* 58 */     NAME = (FunctionName)new FunctionNameImpl("strPosition", FunctionNameImpl.parameter("string", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("lookup", String.class), (Parameter)method });
/*    */   }
/*    */   
/*    */   public FilterFunction_strPosition() {
/* 65 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 70 */     String lookup = (String)getExpression(0).evaluate(object, String.class);
/* 71 */     String string = (String)getExpression(1).evaluate(object, String.class);
/* 72 */     String dir = (String)getExpression(2).evaluate(object, String.class);
/* 74 */     if ("backToFront".equalsIgnoreCase(dir))
/* 75 */       return Integer.valueOf((new StringBuffer(string)).lastIndexOf(lookup) + 1); 
/* 78 */     return Integer.valueOf(string.indexOf(lookup) + 1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */