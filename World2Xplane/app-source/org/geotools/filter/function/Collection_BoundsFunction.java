/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.visitor.BoundsVisitor;
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
/*     */ public class Collection_BoundsFunction extends FunctionExpressionImpl {
/*  55 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  57 */   FeatureCollection<FeatureType, Feature> previousFeatureCollection = null;
/*     */   
/*  58 */   Object bounds = null;
/*     */   
/*  60 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Bounds", FunctionNameImpl.parameter("bounds", Object.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*     */   
/*     */   public Collection_BoundsFunction() {
/*  67 */     super(NAME);
/*     */   }
/*     */   
/*     */   static CalcResult calculateBounds(FeatureCollection<? extends FeatureType, ? extends Feature> collection) throws IllegalFilterException, IOException {
/*  83 */     BoundsVisitor boundsVisitor = new BoundsVisitor();
/*  84 */     collection.accepts((FeatureVisitor)boundsVisitor, null);
/*  86 */     return boundsVisitor.getResult();
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/*  90 */     setParameters(Collections.singletonList(e));
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> args) {
/* 111 */     if (args.size() != 1)
/* 112 */       throw new IllegalArgumentException("Require a single argument for " + getName()); 
/* 117 */     Expression expr = args.get(0);
/* 118 */     expr = (Expression)expr.accept((ExpressionVisitor)new CollectionFeatureMemberFilterVisitor(), null);
/* 119 */     args.set(0, expr);
/* 120 */     super.setParameters(args);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 125 */     if (feature == null)
/* 126 */       return new Integer(0); 
/* 128 */     FeatureCollection<FeatureType, Feature> featureCollection = (FeatureCollection<FeatureType, Feature>)feature;
/* 129 */     synchronized (featureCollection) {
/* 130 */       if (featureCollection != this.previousFeatureCollection) {
/* 131 */         this.previousFeatureCollection = featureCollection;
/* 132 */         this.bounds = null;
/*     */         try {
/* 134 */           CalcResult result = calculateBounds(featureCollection);
/* 135 */           if (result != null)
/* 136 */             this.bounds = result.getValue(); 
/* 138 */         } catch (IllegalFilterException e) {
/* 139 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/* 140 */         } catch (IOException e) {
/* 141 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 145 */     return this.bounds;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_BoundsFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */