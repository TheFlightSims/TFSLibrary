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
/*    */ public class FilterFunction_strTrim2 extends FunctionExpressionImpl {
/*    */   public static FunctionName NAME;
/*    */   
/*    */   static {
/* 50 */     Parameter<String> method = new Parameter("method", String.class, Text.text("method"), Text.text("Method used to trim the provided text"), true, 1, 1, "both", (Map)new KVP(new Object[] { "options", Arrays.asList(new String[] { "leading", "trailing", "both" }) }));
/* 58 */     NAME = (FunctionName)new FunctionNameImpl("strTrim2", FunctionNameImpl.parameter("trim", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), (Parameter)method, FunctionNameImpl.parameter("character", String.class) });
/*    */   }
/*    */   
/*    */   public FilterFunction_strTrim2() {
/* 65 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 71 */     String str = (String)getExpression(0).evaluate(object, String.class);
/* 74 */     String pos = (String)getExpression(1).evaluate(object, String.class);
/* 77 */     String ch = (String)getExpression(2).evaluate(object, String.class);
/* 80 */     if ("".equals(ch.trim()) && "both".equalsIgnoreCase(pos))
/* 81 */       return str.trim(); 
/* 84 */     if ("both".equalsIgnoreCase(pos) || "leading".equalsIgnoreCase(pos))
/* 85 */       str = str.replaceAll(String.format("^%s*", new Object[] { ch }), ""); 
/* 87 */     if ("both".equalsIgnoreCase(pos) || "trailing".equalsIgnoreCase(pos))
/* 88 */       str = str.replaceAll(String.format("%s*$", new Object[] { ch }), ""); 
/* 90 */     return str;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strTrim2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */