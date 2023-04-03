/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_if_then_else extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("if_then_else", Object.class, new Parameter[] { FunctionNameImpl.parameter("condition", Boolean.class), FunctionNameImpl.parameter("then", Object.class), FunctionNameImpl.parameter("else", Object.class) });
/*    */   
/*    */   public FilterFunction_if_then_else() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     boolean select;
/*    */     try {
/* 49 */       select = ((Boolean)getExpression(0).evaluate(feature, Boolean.class)).booleanValue();
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function if_then_else argument #0 - expected type boolean");
/*    */     } 
/* 55 */     if (select)
/*    */       try {
/* 57 */         Object arg1 = getExpression(1).evaluate(feature);
/* 58 */         return arg1;
/* 59 */       } catch (Exception e) {
/* 61 */         throw new IllegalArgumentException("Filter Function problem for function if_then_else argument #1 - expected type Object");
/*    */       }  
/*    */     try {
/* 67 */       Object arg2 = getExpression(2).evaluate(feature);
/* 68 */       return arg2;
/* 69 */     } catch (Exception e) {
/* 71 */       throw new IllegalArgumentException("Filter Function problem for function if_then_else argument #2 - expected type Object");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_if_then_else.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */