/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.feature.visitor.CalcResult;
/*    */ import org.geotools.feature.visitor.StandardDeviationVisitor;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.feature.FeatureVisitor;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.parameter.Parameter;
/*    */ import org.opengis.util.ProgressListener;
/*    */ 
/*    */ public class StandardDeviationFunction extends ClassificationFunction {
/* 41 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("StandardDeviation", RangedClassifier.class, new Parameter[] { FunctionNameImpl.parameter("value", Double.class), FunctionNameImpl.parameter("classes", Integer.class) });
/*    */   
/*    */   public StandardDeviationFunction() {
/* 47 */     super(NAME);
/*    */   }
/*    */   
/*    */   private Object calculate(SimpleFeatureCollection featureCollection) {
/*    */     try {
/* 52 */       int classNum = getClasses();
/* 55 */       StandardDeviationVisitor sdVisit = new StandardDeviationVisitor((Expression)getExpression());
/* 57 */       featureCollection.accepts((FeatureVisitor)sdVisit, (ProgressListener)this.progress);
/* 58 */       if (this.progress != null && this.progress.isCanceled())
/* 59 */         return null; 
/* 61 */       CalcResult calcResult = sdVisit.getResult();
/* 62 */       if (calcResult == null)
/* 63 */         return null; 
/* 65 */       double standardDeviation = calcResult.toDouble();
/* 68 */       Double[] min = new Double[classNum];
/* 69 */       Double[] max = new Double[classNum];
/* 70 */       for (int i = 0; i < classNum; i++) {
/* 71 */         min[i] = getMin(i, classNum, sdVisit.getMean(), standardDeviation);
/* 72 */         max[i] = getMax(i, classNum, sdVisit.getMean(), standardDeviation);
/*    */       } 
/* 74 */       return new RangedClassifier((Comparable[])min, (Comparable[])max);
/* 75 */     } catch (IOException e) {
/* 76 */       LOGGER.log(Level.SEVERE, "StandardDeviationFunction calculate failed", e);
/* 77 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 82 */     if (!(feature instanceof org.geotools.feature.FeatureCollection))
/* 83 */       return null; 
/* 85 */     return calculate((SimpleFeatureCollection)feature);
/*    */   }
/*    */   
/*    */   private Double getMin(int index, int numClasses, double average, double standardDeviation) {
/* 89 */     if (index <= 0 || index >= numClasses)
/* 90 */       return null; 
/* 91 */     return new Double(average - (numClasses / 2.0D - index) * standardDeviation);
/*    */   }
/*    */   
/*    */   private Double getMax(int index, int numClasses, double average, double standardDeviation) {
/* 95 */     if (index < 0 || index >= numClasses - 1)
/* 96 */       return null; 
/* 97 */     return new Double(average - (numClasses / 2.0D - 1.0D - index) * standardDeviation);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\StandardDeviationFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */