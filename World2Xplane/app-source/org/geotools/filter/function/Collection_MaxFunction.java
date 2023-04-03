/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.MaxVisitor;
/*     */ import org.geotools.filter.Expression;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class Collection_MaxFunction extends FunctionExpressionImpl {
/*  52 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  54 */   SimpleFeatureCollection previousFeatureCollection = null;
/*     */   
/*  55 */   Object max = null;
/*     */   
/*  58 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Max", FunctionNameImpl.parameter("max", Comparable.class), new Parameter[] { FunctionNameImpl.parameter("expression", Comparable.class) });
/*     */   
/*     */   public Collection_MaxFunction() {
/*  66 */     super(NAME);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  70 */     return 1;
/*     */   }
/*     */   
/*     */   static CalcResult calculateMax(SimpleFeatureCollection collection, Expression expression) throws IllegalFilterException, IOException {
/*  86 */     MaxVisitor maxVisitor = new MaxVisitor((Expression)expression);
/*  87 */     collection.accepts((FeatureVisitor)maxVisitor, null);
/*  89 */     return maxVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> args) {
/* 111 */     Expression expr = args.get(0);
/* 112 */     expr = (Expression)expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/* 113 */     args.set(0, expr);
/* 114 */     super.setParameters(args);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 118 */     if (feature == null)
/* 119 */       return new Integer(0); 
/* 121 */     Expression expr = (Expression)getExpression(0);
/* 122 */     SimpleFeatureCollection featureCollection = (SimpleFeatureCollection)feature;
/* 123 */     synchronized (featureCollection) {
/* 124 */       if (featureCollection != this.previousFeatureCollection) {
/* 125 */         this.previousFeatureCollection = featureCollection;
/* 126 */         this.max = null;
/*     */         try {
/* 128 */           CalcResult result = calculateMax(featureCollection, expr);
/* 129 */           if (result != null)
/* 130 */             this.max = result.getValue(); 
/* 132 */         } catch (IllegalFilterException e) {
/* 133 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 134 */         } catch (IOException e) {
/* 135 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 139 */     return this.max;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 143 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_MaxFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */