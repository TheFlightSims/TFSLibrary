/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.UniqueVisitor;
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
/*     */ public class Collection_UniqueFunction extends FunctionExpressionImpl {
/*  51 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  53 */   SimpleFeatureCollection previousFeatureCollection = null;
/*     */   
/*  54 */   Object unique = null;
/*     */   
/*  56 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Unique", FunctionNameImpl.parameter("unique", Object.class), new Parameter[] { FunctionNameImpl.parameter("expression", Object.class) });
/*     */   
/*     */   public Collection_UniqueFunction() {
/*  64 */     super(NAME);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  68 */     return 1;
/*     */   }
/*     */   
/*     */   static CalcResult calculateUnique(SimpleFeatureCollection collection, Expression expression) throws IllegalFilterException, IOException {
/*  84 */     UniqueVisitor uniqueVisitor = new UniqueVisitor((Expression)expression);
/*  85 */     collection.accepts((FeatureVisitor)uniqueVisitor, null);
/*  87 */     return uniqueVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> args) {
/* 109 */     Expression expr = args.get(0);
/* 110 */     expr = (Expression)expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/* 111 */     args.set(0, expr);
/* 112 */     super.setParameters(args);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 116 */     if (feature == null)
/* 117 */       return new Integer(0); 
/* 119 */     SimpleFeatureCollection featureCollection = (SimpleFeatureCollection)feature;
/* 120 */     Expression expr = (Expression)getExpression(0);
/* 121 */     synchronized (featureCollection) {
/* 122 */       if (featureCollection != this.previousFeatureCollection) {
/* 123 */         this.previousFeatureCollection = featureCollection;
/* 124 */         this.unique = null;
/*     */         try {
/* 126 */           CalcResult result = calculateUnique(featureCollection, expr);
/* 127 */           if (result != null)
/* 128 */             this.unique = result.getValue(); 
/* 130 */         } catch (IllegalFilterException e) {
/* 131 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 132 */         } catch (IOException e) {
/* 133 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 137 */     return this.unique;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 141 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_UniqueFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */