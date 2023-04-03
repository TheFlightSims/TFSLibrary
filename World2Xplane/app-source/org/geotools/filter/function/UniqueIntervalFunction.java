/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.CalcResult;
/*     */ import org.geotools.feature.visitor.UniqueVisitor;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.ProgressListener;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.parameter.Parameter;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class UniqueIntervalFunction extends ClassificationFunction {
/*  48 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("UniqueInterval", RangedClassifier.class, new Parameter[] { FunctionNameImpl.parameter("value", Double.class), FunctionNameImpl.parameter("classes", Integer.class) });
/*     */   
/*     */   public UniqueIntervalFunction() {
/*  54 */     super(NAME);
/*     */   }
/*     */   
/*     */   private Object calculate(SimpleFeatureCollection featureCollection) {
/*     */     try {
/*     */       Set[] values;
/*  59 */       int classNum = getClasses();
/*  61 */       UniqueVisitor uniqueVisit = new UniqueVisitor((Expression)getExpression());
/*  62 */       if (this.progress == null)
/*  62 */         this.progress = (ProgressListener)new NullProgressListener(); 
/*  63 */       featureCollection.accepts((FeatureVisitor)uniqueVisit, (ProgressListener)this.progress);
/*  64 */       if (this.progress.isCanceled())
/*  64 */         return null; 
/*  66 */       CalcResult calcResult = uniqueVisit.getResult();
/*  67 */       if (calcResult == null)
/*  67 */         return null; 
/*  68 */       List<?> result = calcResult.toList();
/*  70 */       Collections.sort(result, new Comparator() {
/*     */             public int compare(Object o1, Object o2) {
/*  73 */               if (o1 == null) {
/*  74 */                 if (o2 == null)
/*  75 */                   return 0; 
/*  77 */                 return -1;
/*     */               } 
/*  78 */               if (o2 == null)
/*  79 */                 return 1; 
/*  81 */               if (o1 instanceof String && o2 instanceof String)
/*  82 */                 return ((String)o1).compareTo((String)o2); 
/*  84 */               return 0;
/*     */             }
/*     */           });
/*  88 */       Object[] results = result.toArray();
/*  91 */       if (classNum < results.length) {
/*  93 */         values = new Set[classNum];
/*  95 */         int binPop = (new Double(Math.ceil(results.length / classNum))).intValue();
/*  97 */         int lastBigBin = results.length % classNum;
/*  98 */         if (lastBigBin == 0) {
/*  98 */           lastBigBin = classNum;
/*     */         } else {
/*  99 */           lastBigBin--;
/*     */         } 
/* 101 */         int itemIndex = 0;
/* 103 */         for (int binIndex = 0; binIndex < classNum; binIndex++) {
/* 104 */           HashSet<Object> val = new HashSet();
/* 106 */           for (int binItem = 0; binItem < binPop; binItem++)
/* 107 */             val.add(results[itemIndex++]); 
/* 108 */           if (lastBigBin == binIndex)
/* 109 */             binPop--; 
/* 112 */           values[binIndex] = val;
/*     */         } 
/*     */       } else {
/* 115 */         if (classNum > results.length)
/* 116 */           classNum = results.length; 
/* 119 */         values = new Set[classNum];
/* 121 */         for (int i = 0; i < classNum; i++) {
/* 122 */           HashSet<Object> val = new HashSet();
/* 123 */           val.add(results[i]);
/* 124 */           values[i] = val;
/*     */         } 
/*     */       } 
/* 128 */       return new ExplicitClassifier(values);
/* 129 */     } catch (IOException e) {
/* 130 */       LOGGER.log(Level.SEVERE, "UniqueIntervalFunction calculate failed", e);
/* 131 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 136 */     if (!(feature instanceof org.geotools.feature.FeatureCollection))
/* 137 */       return null; 
/* 139 */     return calculate((SimpleFeatureCollection)feature);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/* 143 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\UniqueIntervalFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */