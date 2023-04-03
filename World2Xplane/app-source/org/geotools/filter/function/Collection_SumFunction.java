/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.SumVisitor;
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
/*     */ public class Collection_SumFunction extends FunctionExpressionImpl {
/*  51 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  53 */   SimpleFeatureCollection previousFeatureCollection = null;
/*     */   
/*  54 */   Object sum = null;
/*     */   
/*  57 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Sum", FunctionNameImpl.parameter("sum", Number.class), new Parameter[] { FunctionNameImpl.parameter("expression", Number.class) });
/*     */   
/*     */   public Collection_SumFunction() {
/*  65 */     super(NAME);
/*     */   }
/*     */   
/*     */   static CalcResult calculateSum(SimpleFeatureCollection collection, Expression expression) throws IllegalFilterException, IOException {
/*  81 */     SumVisitor sumVisitor = new SumVisitor((Expression)expression);
/*  82 */     collection.accepts((FeatureVisitor)sumVisitor, null);
/*  84 */     return sumVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> args) {
/* 106 */     Expression expr = args.get(0);
/* 107 */     expr = (Expression)expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/* 108 */     args.set(0, expr);
/* 109 */     super.setParameters(args);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 113 */     if (feature == null)
/* 114 */       return new Integer(0); 
/* 116 */     SimpleFeatureCollection featureCollection = (SimpleFeatureCollection)feature;
/* 117 */     Expression expr = (Expression)getExpression(0);
/* 118 */     synchronized (featureCollection) {
/* 119 */       if (featureCollection != this.previousFeatureCollection) {
/* 120 */         this.previousFeatureCollection = featureCollection;
/* 121 */         this.sum = null;
/*     */         try {
/* 123 */           CalcResult result = calculateSum(featureCollection, expr);
/* 124 */           if (result != null)
/* 125 */             this.sum = result.getValue(); 
/* 127 */         } catch (IllegalFilterException e) {
/* 128 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 129 */         } catch (IOException e) {
/* 130 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 134 */     return this.sum;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 138 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_SumFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */