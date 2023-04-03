/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in7 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in7", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class), FunctionNameImpl.parameter("in6", Object.class), FunctionNameImpl.parameter("in7", Object.class) });
/*     */   
/*     */   public FilterFunction_in7() {
/*  45 */     super(NAME);
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
/*     */     try {
/*  59 */       arg0 = getExpression(0).evaluate(feature);
/*  60 */     } catch (Exception e) {
/*  62 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  67 */       arg1 = getExpression(1).evaluate(feature);
/*  68 */     } catch (Exception e) {
/*  70 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  75 */       arg2 = getExpression(2).evaluate(feature);
/*  76 */     } catch (Exception e) {
/*  78 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  83 */       arg3 = getExpression(3).evaluate(feature);
/*  84 */     } catch (Exception e) {
/*  86 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  91 */       arg4 = getExpression(4).evaluate(feature);
/*  92 */     } catch (Exception e) {
/*  94 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/*  99 */       arg5 = getExpression(5).evaluate(feature);
/* 100 */     } catch (Exception e) {
/* 102 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #5 - expected type Object");
/*     */     } 
/*     */     try {
/* 107 */       arg6 = getExpression(6).evaluate(feature);
/* 108 */     } catch (Exception e) {
/* 110 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #6 - expected type Object");
/*     */     } 
/*     */     try {
/* 115 */       arg7 = getExpression(7).evaluate(feature);
/* 116 */     } catch (Exception e) {
/* 118 */       throw new IllegalArgumentException("Filter Function problem for function in7 argument #7 - expected type Object");
/*     */     } 
/* 122 */     return new Boolean(StaticGeometry.in7(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */