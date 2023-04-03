/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in10 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in10", Boolean.class, new Parameter[] { 
/*  34 */         FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class), FunctionNameImpl.parameter("in6", Object.class), FunctionNameImpl.parameter("in7", Object.class), FunctionNameImpl.parameter("in8", Object.class), FunctionNameImpl.parameter("in9", Object.class), 
/*  34 */         FunctionNameImpl.parameter("in10", Object.class) });
/*     */   
/*     */   public FilterFunction_in10() {
/*  48 */     super(NAME);
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
/*     */     Object arg10;
/*     */     try {
/*  65 */       arg0 = getExpression(0).evaluate(feature);
/*  66 */     } catch (Exception e) {
/*  68 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  73 */       arg1 = getExpression(1).evaluate(feature);
/*  74 */     } catch (Exception e) {
/*  76 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  81 */       arg2 = getExpression(2).evaluate(feature);
/*  82 */     } catch (Exception e) {
/*  84 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  89 */       arg3 = getExpression(3).evaluate(feature);
/*  90 */     } catch (Exception e) {
/*  92 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  97 */       arg4 = getExpression(4).evaluate(feature);
/*  98 */     } catch (Exception e) {
/* 100 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/* 105 */       arg5 = getExpression(5).evaluate(feature);
/* 106 */     } catch (Exception e) {
/* 108 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #5 - expected type Object");
/*     */     } 
/*     */     try {
/* 113 */       arg6 = getExpression(6).evaluate(feature);
/* 114 */     } catch (Exception e) {
/* 116 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #6 - expected type Object");
/*     */     } 
/*     */     try {
/* 121 */       arg7 = getExpression(7).evaluate(feature);
/* 122 */     } catch (Exception e) {
/* 124 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #7 - expected type Object");
/*     */     } 
/*     */     try {
/* 129 */       arg8 = getExpression(8).evaluate(feature);
/* 130 */     } catch (Exception e) {
/* 132 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #8 - expected type Object");
/*     */     } 
/*     */     try {
/* 137 */       arg9 = getExpression(9).evaluate(feature);
/* 138 */     } catch (Exception e) {
/* 140 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #9 - expected type Object");
/*     */     } 
/*     */     try {
/* 145 */       arg10 = getExpression(10).evaluate(feature);
/* 146 */     } catch (Exception e) {
/* 148 */       throw new IllegalArgumentException("Filter Function problem for function in10 argument #10 - expected type Object");
/*     */     } 
/* 152 */     return new Boolean(StaticGeometry.in10(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */