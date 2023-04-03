/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.QuantileListVisitor;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.ProgressListener;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.parameter.Parameter;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class QuantileFunction extends ClassificationFunction {
/*  46 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Quantile", RangedClassifier.class, new Parameter[] { FunctionNameImpl.parameter("value", Double.class), FunctionNameImpl.parameter("classes", Integer.class) });
/*     */   
/*     */   public QuantileFunction() {
/*  52 */     super(NAME);
/*     */   }
/*     */   
/*     */   private Object calculate(SimpleFeatureCollection featureCollection) {
/*  57 */     QuantileListVisitor quantileVisit = new QuantileListVisitor((Expression)getExpression(), getClasses());
/*  58 */     if (this.progress == null)
/*  58 */       this.progress = (ProgressListener)new NullProgressListener(); 
/*     */     try {
/*  60 */       featureCollection.accepts((FeatureVisitor)quantileVisit, (ProgressListener)this.progress);
/*  61 */     } catch (IOException e) {
/*  62 */       LOGGER.log(Level.SEVERE, "QuantileFunction calculate(SimpleFeatureCollection) failed", e);
/*  63 */       return null;
/*     */     } 
/*  65 */     if (this.progress.isCanceled())
/*  65 */       return null; 
/*  66 */     CalcResult calcResult = quantileVisit.getResult();
/*  67 */     if (calcResult == null)
/*  67 */       return null; 
/*  68 */     List[] bin = (List[])calcResult.getValue();
/*  71 */     Comparable globalMin = (Comparable)bin[0].toArray()[0];
/*  72 */     Object[] lastBin = bin[bin.length - 1].toArray();
/*  73 */     if (lastBin.length == 0)
/*  74 */       return null; 
/*  76 */     Comparable globalMax = (Comparable)lastBin[lastBin.length - 1];
/*  78 */     if (globalMin instanceof Number && globalMax instanceof Number)
/*  79 */       return calculateNumerical(bin, globalMin, globalMax); 
/*  81 */     return calculateNonNumerical(bin);
/*     */   }
/*     */   
/*     */   private Object calculateNumerical(List[] bin, Comparable globalMin, Comparable globalMax) {
/*  86 */     int classNum = bin.length;
/*  88 */     Comparable[] localMin = new Comparable[classNum];
/*  89 */     Comparable[] localMax = new Comparable[classNum];
/*  92 */     for (int i = 0; i < classNum; i++) {
/*  94 */       List<Comparable> thisBin = bin[i];
/*  95 */       localMin[i] = thisBin.get(0);
/*  96 */       localMax[i] = thisBin.get(thisBin.size() - 1);
/*  98 */       double slotWidth = ((Number)localMax[i]).doubleValue() - ((Number)localMin[i]).doubleValue();
/*  99 */       if (slotWidth == 0.0D)
/* 100 */         slotWidth = (((Number)globalMax).doubleValue() - ((Number)globalMin).doubleValue()) / classNum; 
/* 103 */       int decPlaces = decimalPlaces(slotWidth);
/* 104 */       decPlaces = Math.max(decPlaces, decimalPlaces(((Number)localMin[i]).doubleValue()));
/* 105 */       decPlaces = Math.max(decPlaces, decimalPlaces(((Number)localMax[i]).doubleValue()));
/* 107 */       if (decPlaces > -1) {
/* 108 */         localMin[i] = new Double(round(((Number)localMin[i]).doubleValue(), decPlaces));
/* 109 */         localMax[i] = new Double(round(((Number)localMax[i]).doubleValue(), decPlaces));
/*     */       } 
/* 112 */       if (i == 0) {
/* 114 */         if (localMin[i].compareTo(new Double(((Number)globalMin).doubleValue())) > 0)
/* 115 */           localMin[i] = new Double(fixRound(((Number)localMin[i]).doubleValue(), decPlaces, false)); 
/* 116 */       } else if (i == classNum - 1) {
/* 118 */         if (localMax[i].compareTo(new Double(((Number)globalMax).doubleValue())) < 0)
/* 119 */           localMax[i] = new Double(fixRound(((Number)localMax[i]).doubleValue(), decPlaces, true)); 
/*     */       } 
/* 123 */       if (i != 0)
/* 124 */         localMax[i - 1] = localMin[i]; 
/*     */     } 
/* 129 */     return new RangedClassifier(localMin, localMax);
/*     */   }
/*     */   
/*     */   private Object calculateNonNumerical(List[] bin) {
/* 133 */     int classNum = bin.length;
/* 135 */     Set[] values = new Set[classNum];
/* 136 */     for (int i = 0; i < classNum; i++) {
/* 137 */       values[i] = new HashSet();
/* 138 */       Iterator<?> iterator = bin[i].iterator();
/* 139 */       while (iterator.hasNext())
/* 140 */         values[i].add(iterator.next()); 
/*     */     } 
/* 143 */     return new ExplicitClassifier(values);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 150 */     if (!(feature instanceof org.geotools.feature.FeatureCollection))
/* 151 */       return null; 
/* 153 */     return calculate((SimpleFeatureCollection)feature);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\QuantileFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */