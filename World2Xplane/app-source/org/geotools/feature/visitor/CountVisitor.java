/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ 
/*     */ public class CountVisitor implements FeatureCalc {
/*  35 */   Integer count = null;
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/*  41 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/*  45 */     if (this.count == null)
/*  46 */       this.count = Integer.valueOf(0); 
/*  48 */     Integer integer1 = this.count, integer2 = this.count = Integer.valueOf(this.count.intValue() + 1);
/*     */   }
/*     */   
/*     */   public int getCount() {
/*  52 */     if (this.count == null)
/*  53 */       return 0; 
/*  55 */     return this.count.intValue();
/*     */   }
/*     */   
/*     */   public void setValue(int count) {
/*  59 */     this.count = Integer.valueOf(count);
/*     */   }
/*     */   
/*     */   public void reset() {
/*  63 */     this.count = null;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/*  67 */     if (this.count == null)
/*  68 */       return CalcResult.NULL_RESULT; 
/*  70 */     return new CountResult(this.count.intValue());
/*     */   }
/*     */   
/*     */   public static class CountResult extends AbstractCalcResult {
/*     */     private int count;
/*     */     
/*     */     public CountResult(int newcount) {
/*  77 */       this.count = newcount;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/*  81 */       return new Integer(this.count);
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/*  85 */       if (targetResults == CalcResult.NULL_RESULT)
/*  85 */         return true; 
/*  86 */       if (targetResults instanceof CountResult)
/*  86 */         return true; 
/*  87 */       if (targetResults instanceof SumVisitor.SumResult)
/*  87 */         return true; 
/*  88 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/*  92 */       if (!isCompatible(resultsToAdd))
/*  93 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/*  97 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/*  98 */         return this; 
/* 101 */       if (resultsToAdd instanceof CountResult) {
/* 103 */         int toAdd = resultsToAdd.toInt();
/* 104 */         return new CountResult(this.count + toAdd);
/*     */       } 
/* 105 */       if (resultsToAdd instanceof SumVisitor.SumResult)
/* 108 */         return resultsToAdd.merge(this); 
/* 110 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\CountVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */