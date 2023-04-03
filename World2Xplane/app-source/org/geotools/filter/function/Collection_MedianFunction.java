/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.MedianVisitor;
/*     */ import org.geotools.filter.Expression;
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
/*     */ public class Collection_MedianFunction extends FunctionExpressionImpl {
/*  54 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  56 */   FeatureCollection<? extends FeatureType, ? extends Feature> previousFeatureCollection = null;
/*     */   
/*  57 */   Object median = null;
/*     */   
/*  60 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Median", FunctionNameImpl.parameter("median", Comparable.class), new Parameter[] { FunctionNameImpl.parameter("expression", Comparable.class) });
/*     */   
/*     */   public Collection_MedianFunction() {
/*  67 */     super(NAME);
/*     */   }
/*     */   
/*     */   static CalcResult calculateMedian(FeatureCollection<? extends FeatureType, ? extends Feature> collection, Expression expression) throws IllegalFilterException, IOException {
/*  83 */     MedianVisitor medianVisitor = new MedianVisitor((Expression)expression);
/*  84 */     collection.accepts((FeatureVisitor)medianVisitor, null);
/*  86 */     return medianVisitor.getResult();
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
/* 119 */     SimpleFeatureCollection simpleFeatureCollection = (SimpleFeatureCollection)feature;
/* 120 */     synchronized (simpleFeatureCollection) {
/* 121 */       if (simpleFeatureCollection != this.previousFeatureCollection) {
/* 122 */         this.previousFeatureCollection = (FeatureCollection<? extends FeatureType, ? extends Feature>)simpleFeatureCollection;
/* 123 */         this.median = null;
/*     */         try {
/* 125 */           CalcResult result = calculateMedian((FeatureCollection<? extends FeatureType, ? extends Feature>)simpleFeatureCollection, expr);
/* 126 */           if (result != null)
/* 127 */             this.median = result.getValue(); 
/* 129 */         } catch (IllegalFilterException e) {
/* 130 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 131 */         } catch (IOException e) {
/* 132 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 136 */     return this.median;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 140 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_MedianFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */