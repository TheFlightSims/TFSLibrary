/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in8 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in8", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class), FunctionNameImpl.parameter("in6", Object.class), FunctionNameImpl.parameter("in7", Object.class), FunctionNameImpl.parameter("in8", Object.class) });
/*     */   
/*     */   public FilterFunction_in8() {
/*  46 */     super(NAME);
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
/*     */     try {
/*  61 */       arg0 = getExpression(0).evaluate(feature);
/*  62 */     } catch (Exception e) {
/*  64 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  69 */       arg1 = getExpression(1).evaluate(feature);
/*  70 */     } catch (Exception e) {
/*  72 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  77 */       arg2 = getExpression(2).evaluate(feature);
/*  78 */     } catch (Exception e) {
/*  80 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  85 */       arg3 = getExpression(3).evaluate(feature);
/*  86 */     } catch (Exception e) {
/*  88 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  93 */       arg4 = getExpression(4).evaluate(feature);
/*  94 */     } catch (Exception e) {
/*  96 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/* 101 */       arg5 = getExpression(5).evaluate(feature);
/* 102 */     } catch (Exception e) {
/* 104 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #5 - expected type Object");
/*     */     } 
/*     */     try {
/* 109 */       arg6 = getExpression(6).evaluate(feature);
/* 110 */     } catch (Exception e) {
/* 112 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #6 - expected type Object");
/*     */     } 
/*     */     try {
/* 117 */       arg7 = getExpression(7).evaluate(feature);
/* 118 */     } catch (Exception e) {
/* 120 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #7 - expected type Object");
/*     */     } 
/*     */     try {
/* 125 */       arg8 = getExpression(8).evaluate(feature);
/* 126 */     } catch (Exception e) {
/* 128 */       throw new IllegalArgumentException("Filter Function problem for function in8 argument #8 - expected type Object");
/*     */     } 
/* 132 */     return new Boolean(StaticGeometry.in8(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */