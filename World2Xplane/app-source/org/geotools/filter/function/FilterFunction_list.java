/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_list extends FunctionExpressionImpl {
/* 41 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("list", FunctionNameImpl.parameter("list", List.class), new Parameter[] { FunctionNameImpl.parameter("item", Object.class, 1, -1) });
/*    */   
/*    */   public FilterFunction_list() {
/* 46 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 51 */     List<Object> result = new ArrayList();
/* 53 */     for (Expression expr : getParameters()) {
/*    */       try {
/* 55 */         Object value = expr.evaluate(feature);
/* 56 */         if (value instanceof java.util.Collection) {
/* 57 */           for (Object item : value)
/* 58 */             result.add(item); 
/*    */           continue;
/*    */         } 
/* 61 */         result.add(value);
/* 63 */       } catch (Exception e) {
/* 64 */         throw new IllegalArgumentException(e);
/*    */       } 
/*    */     } 
/* 68 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_list.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */