/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in6 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in6", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class), FunctionNameImpl.parameter("in6", Object.class) });
/*     */   
/*     */   public FilterFunction_in6() {
/*  44 */     super(NAME);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*     */     Object arg0;
/*     */     Object arg1;
/*     */     Object arg2;
/*     */     Object arg3;
/*     */     Object arg4;
/*     */     Object arg5;
/*     */     Object arg6;
/*     */     try {
/*  57 */       arg0 = getExpression(0).evaluate(feature);
/*  58 */     } catch (Exception e) {
/*  60 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  65 */       arg1 = getExpression(1).evaluate(feature);
/*  66 */     } catch (Exception e) {
/*  68 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  73 */       arg2 = getExpression(2).evaluate(feature);
/*  74 */     } catch (Exception e) {
/*  76 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  81 */       arg3 = getExpression(3).evaluate(feature);
/*  82 */     } catch (Exception e) {
/*  84 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  89 */       arg4 = getExpression(4).evaluate(feature);
/*  90 */     } catch (Exception e) {
/*  92 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/*  97 */       arg5 = getExpression(5).evaluate(feature);
/*  98 */     } catch (Exception e) {
/* 100 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #5 - expected type Object");
/*     */     } 
/*     */     try {
/* 105 */       arg6 = getExpression(6).evaluate(feature);
/* 106 */     } catch (Exception e) {
/* 108 */       throw new IllegalArgumentException("Filter Function problem for function in6 argument #6 - expected type Object");
/*     */     } 
/* 112 */     return new Boolean(StaticGeometry.in6(arg0, arg1, arg2, arg3, arg4, arg5, arg6));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */