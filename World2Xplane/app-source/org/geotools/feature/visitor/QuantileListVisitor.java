/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class QuantileListVisitor implements FeatureCalc {
/*     */   private Expression expr;
/*     */   
/*  40 */   private int count = 0;
/*     */   
/*     */   private int bins;
/*     */   
/*  42 */   private List items = new ArrayList();
/*     */   
/*     */   private List[] bin;
/*     */   
/*     */   boolean visited = false;
/*     */   
/*  46 */   int countNull = 0;
/*     */   
/*  47 */   int countNaN = 0;
/*     */   
/*     */   public QuantileListVisitor(Expression expr, int bins) {
/*  50 */     this.expr = expr;
/*  51 */     this.bins = bins;
/*  52 */     this.bin = (List[])new ArrayList[bins];
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public CalcResult getResult() {
/*  60 */     if (this.bins == 0 || this.count == 0)
/*  61 */       return CalcResult.NULL_RESULT; 
/*  65 */     Collections.sort(this.items);
/*  67 */     if (this.bins > this.count) {
/*  68 */       this.bins = this.count;
/*  69 */       this.bin = (List[])new ArrayList[this.bins];
/*     */     } 
/*  73 */     int binPop = (new Double(Math.ceil(this.count / this.bins))).intValue();
/*  75 */     int lastBigBin = this.count % this.bins;
/*  76 */     if (lastBigBin == 0) {
/*  76 */       lastBigBin = this.bins;
/*     */     } else {
/*  77 */       lastBigBin--;
/*     */     } 
/*  80 */     int item = 0;
/*  81 */     for (int binIndex = 0; binIndex < this.bins; binIndex++) {
/*  82 */       this.bin[binIndex] = new ArrayList();
/*  83 */       for (int binMember = 0; binMember < binPop; binMember++)
/*  84 */         this.bin[binIndex].add(this.items.get(item++)); 
/*  86 */       if (lastBigBin == binIndex)
/*  87 */         binPop--; 
/*     */     } 
/*  89 */     return new AbstractCalcResult() {
/*     */         public Object getValue() {
/*  91 */           return QuantileListVisitor.this.bin;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/*  97 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/* 100 */     Object value = this.expr.evaluate(feature);
/* 102 */     if (value == null) {
/* 103 */       this.countNull++;
/*     */       return;
/*     */     } 
/* 107 */     if (value instanceof Double) {
/* 108 */       double doubleVal = ((Double)value).doubleValue();
/* 109 */       if (Double.isNaN(doubleVal) || Double.isInfinite(doubleVal)) {
/* 110 */         this.countNaN++;
/*     */         return;
/*     */       } 
/*     */     } 
/* 115 */     this.count++;
/* 116 */     this.items.add(value);
/*     */   }
/*     */   
/*     */   public void reset(int bins) {
/* 120 */     this.bins = bins;
/* 121 */     this.count = 0;
/* 122 */     this.items = new ArrayList();
/* 123 */     this.bin = (List[])new ArrayList[bins];
/* 124 */     this.countNull = 0;
/* 125 */     this.countNaN = 0;
/*     */   }
/*     */   
/*     */   public int getNaNCount() {
/* 132 */     return this.countNaN;
/*     */   }
/*     */   
/*     */   public int getNullCount() {
/* 139 */     return this.countNull;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\QuantileListVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */