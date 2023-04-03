/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.MinVisitor;
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
/*     */ public class Collection_MinFunction extends FunctionExpressionImpl {
/*  50 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  52 */   SimpleFeatureCollection previousFeatureCollection = null;
/*     */   
/*  53 */   Object min = null;
/*     */   
/*  56 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Min", FunctionNameImpl.parameter("min", Comparable.class), new Parameter[] { FunctionNameImpl.parameter("expression", Comparable.class) });
/*     */   
/*     */   public Collection_MinFunction() {
/*  64 */     super(NAME);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  68 */     return 1;
/*     */   }
/*     */   
/*     */   static CalcResult calculateMin(SimpleFeatureCollection collection, Expression expression) throws IllegalFilterException, IOException {
/*  84 */     MinVisitor minVisitor = new MinVisitor((Expression)expression);
/*  85 */     collection.accepts((FeatureVisitor)minVisitor, null);
/*  86 */     return minVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> args) {
/* 108 */     Expression expr = args.get(0);
/* 109 */     expr = (Expression)expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/* 110 */     args.set(0, expr);
/* 111 */     super.setParameters(args);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 115 */     if (feature == null)
/* 116 */       return new Integer(0); 
/* 118 */     Expression expr = (Expression)getExpression(0);
/* 119 */     SimpleFeatureCollection featureCollection = (SimpleFeatureCollection)feature;
/* 120 */     synchronized (featureCollection) {
/* 121 */       if (featureCollection != this.previousFeatureCollection) {
/* 122 */         this.previousFeatureCollection = featureCollection;
/* 123 */         this.min = null;
/*     */         try {
/* 125 */           CalcResult result = calculateMin(featureCollection, expr);
/* 126 */           if (result != null)
/* 127 */             this.min = result.getValue(); 
/* 129 */         } catch (IllegalFilterException e) {
/* 130 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 131 */         } catch (IOException e) {
/* 132 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 136 */     return this.min;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 140 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_MinFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */