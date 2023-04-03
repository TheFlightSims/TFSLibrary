/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_in5 extends FunctionExpressionImpl {
/*  34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in5", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class), FunctionNameImpl.parameter("in5", Object.class) });
/*     */   
/*     */   public FilterFunction_in5() {
/*  43 */     super(NAME);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*     */     Object arg0;
/*     */     Object arg1;
/*     */     Object arg2;
/*     */     Object arg3;
/*     */     Object arg4;
/*     */     Object arg5;
/*     */     try {
/*  55 */       arg0 = getExpression(0).evaluate(feature);
/*  56 */     } catch (Exception e) {
/*  58 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #0 - expected type Object");
/*     */     } 
/*     */     try {
/*  63 */       arg1 = getExpression(1).evaluate(feature);
/*  64 */     } catch (Exception e) {
/*  66 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #1 - expected type Object");
/*     */     } 
/*     */     try {
/*  71 */       arg2 = getExpression(2).evaluate(feature);
/*  72 */     } catch (Exception e) {
/*  74 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #2 - expected type Object");
/*     */     } 
/*     */     try {
/*  79 */       arg3 = getExpression(3).evaluate(feature);
/*  80 */     } catch (Exception e) {
/*  82 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #3 - expected type Object");
/*     */     } 
/*     */     try {
/*  87 */       arg4 = getExpression(4).evaluate(feature);
/*  88 */     } catch (Exception e) {
/*  90 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #4 - expected type Object");
/*     */     } 
/*     */     try {
/*  95 */       arg5 = getExpression(5).evaluate(feature);
/*  96 */     } catch (Exception e) {
/*  98 */       throw new IllegalArgumentException("Filter Function problem for function in5 argument #5 - expected type Object");
/*     */     } 
/* 102 */     return new Boolean(StaticGeometry.in5(arg0, arg1, arg2, arg3, arg4, arg5));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */