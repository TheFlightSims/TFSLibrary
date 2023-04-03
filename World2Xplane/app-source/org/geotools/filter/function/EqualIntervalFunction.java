/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.visitor.MaxVisitor;
/*     */ import org.geotools.feature.visitor.MinVisitor;
/*     */ import org.geotools.feature.visitor.UniqueVisitor;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.ProgressListener;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.parameter.Parameter;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class EqualIntervalFunction extends ClassificationFunction {
/*  50 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("EqualInterval", RangedClassifier.class, new Parameter[] { FunctionNameImpl.parameter("value", Double.class), FunctionNameImpl.parameter("classes", Integer.class) });
/*     */   
/*     */   public EqualIntervalFunction() {
/*  56 */     super(NAME);
/*     */   }
/*     */   
/*     */   private RangedClassifier calculate(SimpleFeatureCollection featureCollection) {
/*  60 */     int classNum = getClasses();
/*     */     try {
/*  64 */       MinVisitor minVisit = new MinVisitor((Expression)getExpression());
/*  65 */       if (this.progress == null)
/*  65 */         this.progress = (ProgressListener)new NullProgressListener(); 
/*  66 */       featureCollection.accepts((FeatureVisitor)minVisit, (ProgressListener)this.progress);
/*  67 */       if (this.progress.isCanceled())
/*  67 */         return null; 
/*  68 */       Comparable globalMin = (Comparable)minVisit.getResult().getValue();
/*  70 */       MaxVisitor maxVisit = new MaxVisitor((Expression)getExpression());
/*  71 */       featureCollection.accepts((FeatureVisitor)maxVisit, (ProgressListener)this.progress);
/*  72 */       if (this.progress.isCanceled())
/*  72 */         return null; 
/*  73 */       Comparable globalMax = (Comparable)maxVisit.getResult().getValue();
/*  75 */       if (globalMin instanceof Number && globalMax instanceof Number)
/*  76 */         return calculateNumerical(classNum, globalMin, globalMax); 
/*  78 */       return calculateNonNumerical(classNum, (FeatureCollection<?, ?>)featureCollection);
/*  80 */     } catch (IllegalFilterException e) {
/*  81 */       LOGGER.log(Level.SEVERE, "EqualIntervalFunction calculate(SimpleFeatureCollection) failed", (Throwable)e);
/*  82 */       return null;
/*  83 */     } catch (IOException e) {
/*  84 */       LOGGER.log(Level.SEVERE, "EqualIntervalFunction calculate(SimpleFeatureCollection) failed", e);
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private RangedClassifier calculateNumerical(int classNum, Comparable globalMin, Comparable globalMax) {
/*  91 */     double slotWidth = (((Number)globalMax).doubleValue() - ((Number)globalMin).doubleValue()) / classNum;
/*  93 */     Comparable[] localMin = new Comparable[classNum];
/*  94 */     Comparable[] localMax = new Comparable[classNum];
/*  95 */     for (int i = 0; i < classNum; i++) {
/*  97 */       localMin[i] = new Double(((Number)globalMin).doubleValue() + i * slotWidth);
/*  98 */       localMax[i] = new Double(((Number)globalMax).doubleValue() - (classNum - i - 1) * slotWidth);
/* 100 */       int decPlaces = decimalPlaces(slotWidth);
/* 102 */       if (decPlaces > -1) {
/* 103 */         localMin[i] = new Double(round(((Number)localMin[i]).doubleValue(), decPlaces));
/* 104 */         localMax[i] = new Double(round(((Number)localMax[i]).doubleValue(), decPlaces));
/*     */       } 
/* 107 */       if (i == 0) {
/* 109 */         if (localMin[i].compareTo(new Double(((Number)globalMin).doubleValue())) < 0)
/* 110 */           localMin[i] = new Double(fixRound(((Number)localMin[i]).doubleValue(), decPlaces, false)); 
/* 111 */       } else if (i == classNum - 1) {
/* 113 */         if (localMax[i].compareTo(new Double(((Number)globalMax).doubleValue())) > 0)
/* 114 */           localMax[i] = new Double(fixRound(((Number)localMax[i]).doubleValue(), decPlaces, true)); 
/*     */       } 
/* 117 */       if (i != 0 && !localMin[i].equals(localMax[i - 1]))
/* 118 */         localMin[i] = localMax[i - 1]; 
/*     */     } 
/* 121 */     return new RangedClassifier(localMin, localMax);
/*     */   }
/*     */   
/*     */   private RangedClassifier calculateNonNumerical(int classNum, FeatureCollection<?, ?> featureCollection) throws IOException {
/* 127 */     UniqueVisitor uniqueVisit = new UniqueVisitor((Expression)getExpression());
/* 128 */     featureCollection.accepts((FeatureVisitor)uniqueVisit, (ProgressListener)new NullProgressListener());
/* 129 */     List<Comparable> result = uniqueVisit.getResult().toList();
/* 131 */     Collections.sort(result);
/* 133 */     Comparable[] values = result.<Comparable>toArray(new Comparable[result.size()]);
/* 136 */     Comparable[] localMin = new Comparable[classNum];
/* 137 */     Comparable[] localMax = new Comparable[classNum];
/* 146 */     int binPop = (new Double(Math.ceil(values.length / classNum))).intValue();
/* 148 */     int lastBigBin = values.length % classNum;
/* 149 */     if (lastBigBin == 0) {
/* 149 */       lastBigBin = classNum;
/*     */     } else {
/* 150 */       lastBigBin--;
/*     */     } 
/* 152 */     int itemIndex = 0;
/* 154 */     for (int binIndex = 0; binIndex < classNum; binIndex++) {
/* 156 */       if (binIndex < localMin.length) {
/* 157 */         localMin[binIndex] = (itemIndex < values.length) ? values[itemIndex] : values[values.length - 1];
/*     */       } else {
/* 159 */         localMin[localMin.length - 1] = (itemIndex < values.length) ? values[itemIndex] : values[values.length - 1];
/*     */       } 
/* 160 */       itemIndex += binPop;
/* 162 */       if (binIndex == classNum - 1) {
/* 163 */         if (binIndex < localMax.length) {
/* 164 */           localMax[binIndex] = (itemIndex < values.length) ? values[itemIndex] : values[values.length - 1];
/*     */         } else {
/* 166 */           localMax[localMax.length - 1] = (itemIndex < values.length) ? values[itemIndex] : values[values.length - 1];
/*     */         } 
/* 168 */       } else if (binIndex < localMax.length) {
/* 169 */         localMax[binIndex] = (itemIndex + 1 < values.length) ? values[itemIndex + 1] : values[values.length - 1];
/*     */       } else {
/* 171 */         localMax[localMax.length - 1] = (itemIndex + 1 < values.length) ? values[itemIndex + 1] : values[values.length - 1];
/*     */       } 
/* 173 */       if (lastBigBin == binIndex)
/* 174 */         binPop--; 
/*     */     } 
/* 177 */     return new RangedClassifier(localMin, localMax);
/*     */   }
/*     */   
/*     */   public RangedClassifier evaluate(Object object) {
/* 181 */     if (!(object instanceof FeatureCollection))
/* 182 */       return null; 
/* 184 */     return calculate((SimpleFeatureCollection)object);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/* 188 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\EqualIntervalFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */