/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.visitor.AverageVisitor;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class Collection_AverageFunction extends FunctionExpressionImpl {
/*  53 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  55 */   FeatureCollection<FeatureType, Feature> previousFeatureCollection = null;
/*     */   
/*  56 */   Object average = null;
/*     */   
/*     */   Expression expr;
/*     */   
/*  59 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Average", FunctionNameImpl.parameter("average", Number.class), new Parameter[] { FunctionNameImpl.parameter("expression", Number.class) });
/*     */   
/*     */   public Collection_AverageFunction() {
/*  67 */     super(NAME);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  71 */     return 1;
/*     */   }
/*     */   
/*     */   static CalcResult calculateAverage(FeatureCollection<? extends FeatureType, ? extends Feature> collection, Expression expression) throws IllegalFilterException, IOException {
/*  87 */     AverageVisitor averageVisitor = new AverageVisitor(expression);
/*  88 */     collection.accepts((FeatureVisitor)averageVisitor, null);
/*  90 */     return averageVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setParameters(List params) {
/* 111 */     super.setParameters(params);
/* 112 */     if (params.size() != 1)
/* 113 */       throw new IllegalArgumentException("Require a single argument for average"); 
/* 116 */     this.expr = getExpression(0);
/* 118 */     this.expr = (Expression)this.expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 123 */     if (feature == null)
/* 124 */       return new Integer(0); 
/* 126 */     FeatureCollection<FeatureType, Feature> featureCollection = (FeatureCollection<FeatureType, Feature>)feature;
/* 127 */     synchronized (featureCollection) {
/* 128 */       if (featureCollection != this.previousFeatureCollection) {
/* 129 */         this.previousFeatureCollection = featureCollection;
/* 130 */         this.average = null;
/*     */         try {
/* 132 */           CalcResult result = calculateAverage(featureCollection, this.expr);
/* 133 */           if (result != null)
/* 134 */             this.average = result.getValue(); 
/* 136 */         } catch (IllegalFilterException e) {
/* 137 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 138 */         } catch (IOException e) {
/* 139 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 143 */     return this.average;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 147 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 157 */     return "Collection_Average(" + this.expr + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_AverageFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */