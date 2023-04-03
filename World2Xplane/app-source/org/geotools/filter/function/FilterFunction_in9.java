/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in9 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in9", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class), FunctionNameImpl.parameter("in6", Object.class), FunctionNameImpl.parameter("in7", Object.class), FunctionNameImpl.parameter("in8", Object.class), FunctionNameImpl.parameter("in9", Object.class) });
/*     */   
/*     */   public FilterFunction_in9() {
/*  47 */     super(NAME);
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
/*     */     Object arg7;
/*     */     Object arg8;
/*     */     Object arg9;
/*     */     try {
/*  63 */       arg0 = getExpression(0).evaluate(feature);
/*  64 */     } catch (Exception e) {
/*  66 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  71 */       arg1 = getExpression(1).evaluate(feature);
/*  72 */     } catch (Exception e) {
/*  74 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  79 */       arg2 = getExpression(2).evaluate(feature);
/*  80 */     } catch (Exception e) {
/*  82 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  87 */       arg3 = getExpression(3).evaluate(feature);
/*  88 */     } catch (Exception e) {
/*  90 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  95 */       arg4 = getExpression(4).evaluate(feature);
/*  96 */     } catch (Exception e) {
/*  98 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/* 103 */       arg5 = getExpression(5).evaluate(feature);
/* 104 */     } catch (Exception e) {
/* 106 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #5 - expected type Object");
/*     */     } 
/*     */     try {
/* 111 */       arg6 = getExpression(6).evaluate(feature);
/* 112 */     } catch (Exception e) {
/* 114 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #6 - expected type Object");
/*     */     } 
/*     */     try {
/* 119 */       arg7 = getExpression(7).evaluate(feature);
/* 120 */     } catch (Exception e) {
/* 122 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #7 - expected type Object");
/*     */     } 
/*     */     try {
/* 127 */       arg8 = getExpression(8).evaluate(feature);
/* 128 */     } catch (Exception e) {
/* 130 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #8 - expected type Object");
/*     */     } 
/*     */     try {
/* 135 */       arg9 = getExpression(9).evaluate(feature);
/* 136 */     } catch (Exception e) {
/* 138 */       throw new IllegalArgumentException("Filter Function problem for function in9 argument #9 - expected type Object");
/*     */     } 
/* 142 */     return new Boolean(StaticGeometry.in9(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */