/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class JenksNaturalBreaksFunction extends ClassificationFunction {
/*     */   ProgressListener progress;
/*     */   
/*  49 */   private static final Logger logger = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*  51 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Jenks", RangedClassifier.class, new Parameter[] { FunctionNameImpl.parameter("value", Double.class), FunctionNameImpl.parameter("classes", Integer.class) });
/*     */   
/*     */   public JenksNaturalBreaksFunction() {
/*  57 */     super(NAME);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*  66 */     if (!(feature instanceof org.geotools.feature.FeatureCollection))
/*  67 */       return null; 
/*  69 */     return calculate((SimpleFeatureCollection)feature);
/*     */   }
/*     */   
/*     */   private Object calculate(SimpleFeatureCollection featureCollection) {
/*  80 */     SimpleFeatureIterator features = featureCollection.features();
/*  81 */     ArrayList<Double> data = new ArrayList<Double>();
/*     */     try {
/*  83 */       while (features.hasNext()) {
/*  84 */         SimpleFeature feature = (SimpleFeature)features.next();
/*  85 */         Object result = getExpression().evaluate(feature);
/*  86 */         logger.finest("importing " + result);
/*  87 */         if (result != null) {
/*  88 */           Double e = new Double(result.toString());
/*  89 */           if (!e.isInfinite() && !e.isNaN())
/*  90 */             data.add(e); 
/*     */         } 
/*     */       } 
/*  93 */     } catch (NumberFormatException e) {
/*  94 */       return null;
/*     */     } 
/*  96 */     Collections.sort(data);
/*  97 */     int k = getClasses();
/*  98 */     int m = data.size();
/*  99 */     if (k == m) {
/* 100 */       logger.info("Number of classes (" + k + ") is equal to number of data points (" + m + ") " + "unique classification returned");
/* 102 */       Comparable[] arrayOfComparable1 = new Comparable[k];
/* 103 */       Comparable[] arrayOfComparable2 = new Comparable[k];
/* 105 */       for (int id = 0; id < k - 1; id++) {
/* 107 */         arrayOfComparable2[id] = data.get(id + 1);
/* 108 */         arrayOfComparable1[id] = data.get(id);
/*     */       } 
/* 110 */       arrayOfComparable2[k - 1] = data.get(k - 1);
/* 111 */       arrayOfComparable1[k - 1] = data.get(k - 1);
/* 112 */       return new RangedClassifier(arrayOfComparable1, arrayOfComparable2);
/*     */     } 
/* 114 */     int[][] iwork = new int[m + 1][k + 1];
/* 115 */     double[][] work = new double[m + 1][k + 1];
/* 117 */     for (int j = 1; j <= k; j++) {
/* 119 */       iwork[0][j] = 1;
/* 120 */       iwork[1][j] = 1;
/* 122 */       work[1][j] = 0.0D;
/* 123 */       for (int i1 = 2; i1 <= m; i1++)
/* 124 */         work[i1][j] = Double.MAX_VALUE; 
/*     */     } 
/*     */     int i;
/* 129 */     for (i = 1; i <= m; i++) {
/* 131 */       double s1 = 0.0D;
/* 133 */       double s2 = 0.0D;
/* 135 */       double var = 0.0D;
/* 137 */       for (int ii = 1; ii <= i; ii++) {
/* 139 */         int i3 = i - ii + 1;
/* 141 */         double val = ((Double)data.get(i3 - 1)).doubleValue();
/* 143 */         s2 += val * val;
/* 144 */         s1 += val;
/* 145 */         double s0 = ii;
/* 148 */         var = s2 - s1 * s1 / s0;
/* 151 */         int i1 = i3 - 1;
/* 152 */         if (i1 != 0)
/* 154 */           for (int i2 = 2; i2 <= k; i2++) {
/* 157 */             if (work[i][i2] >= var + work[i1][i2 - 1]) {
/* 159 */               iwork[i][i2] = i3 - 1;
/* 161 */               work[i][i2] = var + work[i1][i2 - 1];
/*     */             } 
/*     */           }  
/*     */       } 
/* 167 */       iwork[i][1] = 1;
/* 168 */       work[i][1] = var;
/*     */     } 
/* 170 */     if (logger.getLevel() == Level.FINER)
/* 171 */       for (i = 0; i < m; i++) {
/* 172 */         String tmp = i + ": " + data.get(i);
/* 173 */         for (int i1 = 2; i1 <= k; i1++)
/* 174 */           tmp = tmp + "\t" + iwork[i][i1]; 
/* 176 */         logger.finer(tmp);
/*     */       }  
/* 180 */     int ik = m - 1;
/* 182 */     Comparable[] localMin = new Comparable[k];
/* 183 */     Comparable[] localMax = new Comparable[k];
/* 184 */     localMax[k - 1] = data.get(ik);
/* 185 */     for (int n = k; n >= 2; n--) {
/* 186 */       logger.finest("index " + ik + ", class" + n);
/* 187 */       int id = iwork[ik][n] - 1;
/* 190 */       localMax[n - 2] = data.get(id);
/* 191 */       localMin[n - 1] = data.get(id);
/* 192 */       ik = iwork[ik][n] - 1;
/*     */     } 
/* 194 */     localMin[0] = data.get(0);
/* 198 */     return new RangedClassifier(localMin, localMax);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\JenksNaturalBreaksFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */