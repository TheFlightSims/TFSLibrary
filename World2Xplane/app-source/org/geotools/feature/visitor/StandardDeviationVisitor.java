/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class StandardDeviationVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*     */   boolean visited = false;
/*     */   
/*  52 */   int countNull = 0;
/*     */   
/*  53 */   int countNaN = 0;
/*     */   
/*  54 */   int count = 0;
/*     */   
/*  55 */   double mean = 0.0D;
/*     */   
/*  56 */   double m2 = 0.0D;
/*     */   
/*     */   @Deprecated
/*     */   public StandardDeviationVisitor(Expression expr, double average) {
/*  68 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public StandardDeviationVisitor(Expression expr) {
/*  77 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/*  86 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/*  90 */     if (this.count == 0)
/*  91 */       return CalcResult.NULL_RESULT; 
/*  93 */     return new AbstractCalcResult() {
/*     */         public Object getValue() {
/*  95 */           if (StandardDeviationVisitor.this.count == 0)
/*  95 */             return null; 
/*  96 */           return new Double(Math.sqrt(StandardDeviationVisitor.this.m2 / StandardDeviationVisitor.this.count));
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/* 102 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/* 105 */     Object value = this.expr.evaluate(feature);
/* 107 */     if (value == null) {
/* 108 */       this.countNull++;
/*     */       return;
/*     */     } 
/* 112 */     if (value instanceof Double) {
/* 113 */       double doubleVal = ((Double)value).doubleValue();
/* 114 */       if (Double.isNaN(doubleVal) || Double.isInfinite(doubleVal)) {
/* 115 */         this.countNaN++;
/*     */         return;
/*     */       } 
/*     */     } 
/* 120 */     double x = ((Number)value).doubleValue();
/* 121 */     this.count++;
/* 122 */     double delta = x - this.mean;
/* 123 */     this.mean += delta / this.count;
/* 124 */     this.m2 += delta * (x - this.mean);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 128 */     this.count = 0;
/* 129 */     this.countNull = 0;
/* 130 */     this.countNaN = 0;
/* 131 */     this.m2 = 0.0D;
/* 132 */     this.mean = 0.0D;
/*     */   }
/*     */   
/*     */   public double getMean() {
/* 137 */     return this.mean;
/*     */   }
/*     */   
/*     */   public int getNaNCount() {
/* 143 */     return this.countNaN;
/*     */   }
/*     */   
/*     */   public int getNullCount() {
/* 150 */     return this.countNull;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\StandardDeviationVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */