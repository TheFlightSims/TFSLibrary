/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.CountVisitor;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ 
/*     */ public class Collection_CountFunction extends FunctionExpressionImpl {
/*  47 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  49 */   SimpleFeatureCollection previousFeatureCollection = null;
/*     */   
/*  51 */   Object count = null;
/*     */   
/*  53 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Collection_Count", FunctionNameImpl.parameter("count", Object.class), new org.opengis.parameter.Parameter[0]);
/*     */   
/*     */   public Collection_CountFunction() {
/*  60 */     super(NAME);
/*     */   }
/*     */   
/*     */   static CalcResult calculateCount(SimpleFeatureCollection collection) throws IllegalFilterException, IOException {
/*  76 */     CountVisitor countVisitor = new CountVisitor();
/*  77 */     collection.accepts((FeatureVisitor)countVisitor, null);
/*  78 */     return countVisitor.getResult();
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*  82 */     if (feature == null)
/*  83 */       return new Integer(0); 
/*  85 */     SimpleFeatureCollection featureCollection = (SimpleFeatureCollection)feature;
/*  86 */     synchronized (featureCollection) {
/*  87 */       if (featureCollection != this.previousFeatureCollection) {
/*  88 */         this.previousFeatureCollection = featureCollection;
/*  89 */         this.count = null;
/*     */         try {
/*  91 */           CalcResult result = calculateCount(featureCollection);
/*  92 */           if (result != null)
/*  93 */             this.count = result.getValue(); 
/*  95 */         } catch (IllegalFilterException e) {
/*  96 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), (Throwable)e);
/*  97 */         } catch (IOException e) {
/*  98 */           LOGGER.log(Level.FINER, e.getLocalizedMessage(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 102 */     return this.count;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Collection_CountFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */