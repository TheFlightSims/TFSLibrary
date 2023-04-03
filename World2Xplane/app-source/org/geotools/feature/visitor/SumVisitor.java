/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class SumVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*     */   SumStrategy strategy;
/*     */   
/*     */   public SumVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  51 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  52 */     AttributeDescriptor attributeType = type.getDescriptor(attributeTypeIndex);
/*  53 */     this.expr = (Expression)factory.property(attributeType.getLocalName());
/*  54 */     createStrategy(attributeType.getType().getBinding());
/*     */   }
/*     */   
/*     */   public SumVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  59 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  60 */     AttributeDescriptor attributeType = type.getDescriptor(attrName);
/*  61 */     this.expr = (Expression)factory.property(attributeType.getLocalName());
/*  62 */     createStrategy(attributeType.getType().getBinding());
/*     */   }
/*     */   
/*     */   public SumVisitor(Expression expr) throws IllegalFilterException {
/*  66 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/*  75 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   private static SumStrategy createStrategy(Class<Integer> type) {
/*  86 */     if (type == Integer.class)
/*  87 */       return new IntegerSumStrategy(); 
/*  88 */     if (type == Long.class)
/*  89 */       return new LongSumStrategy(); 
/*  90 */     if (type == Float.class)
/*  91 */       return new FloatSumStrategy(); 
/*  92 */     if (Number.class.isAssignableFrom(type))
/*  93 */       return new DoubleSumStrategy(); 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/* 100 */     visit(feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/* 103 */     Object value = this.expr.evaluate(feature);
/* 105 */     if (value != null) {
/* 106 */       if (this.strategy == null)
/* 107 */         this.strategy = createStrategy(value.getClass()); 
/* 110 */       this.strategy.add(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 115 */     return this.expr;
/*     */   }
/*     */   
/*     */   public Object getSum() {
/* 119 */     return this.strategy.getResult();
/*     */   }
/*     */   
/*     */   public void setValue(Object newSum) {
/* 123 */     this.strategy = createStrategy(newSum.getClass());
/* 124 */     this.strategy.add(newSum);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 128 */     this.strategy = null;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 132 */     if (this.strategy == null)
/* 133 */       return CalcResult.NULL_RESULT; 
/* 135 */     return new SumResult(this.strategy);
/*     */   }
/*     */   
/*     */   static interface SumStrategy {
/*     */     void add(Object param1Object);
/*     */     
/*     */     Object getResult();
/*     */   }
/*     */   
/*     */   static class DoubleSumStrategy implements SumStrategy {
/* 145 */     double number = 0.0D;
/*     */     
/*     */     public void add(Object value) {
/* 148 */       this.number += ((Number)value).doubleValue();
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 152 */       return new Double(this.number);
/*     */     }
/*     */   }
/*     */   
/*     */   static class FloatSumStrategy implements SumStrategy {
/* 157 */     float number = 0.0F;
/*     */     
/*     */     public void add(Object value) {
/* 160 */       this.number += ((Number)value).floatValue();
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 164 */       return new Float(this.number);
/*     */     }
/*     */   }
/*     */   
/*     */   static class LongSumStrategy implements SumStrategy {
/* 169 */     long number = 0L;
/*     */     
/*     */     public void add(Object value) {
/* 172 */       this.number += ((Number)value).longValue();
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 176 */       return new Long(this.number);
/*     */     }
/*     */   }
/*     */   
/*     */   static class IntegerSumStrategy implements SumStrategy {
/* 181 */     int number = 0;
/*     */     
/*     */     public void add(Object value) {
/* 184 */       this.number += ((Number)value).intValue();
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 188 */       return new Integer(this.number);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SumResult extends AbstractCalcResult {
/*     */     private SumVisitor.SumStrategy sum;
/*     */     
/*     */     public SumResult(SumVisitor.SumStrategy newSum) {
/* 196 */       this.sum = newSum;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 200 */       return this.sum.getResult();
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 204 */       if (targetResults == CalcResult.NULL_RESULT)
/* 204 */         return true; 
/* 205 */       if (targetResults instanceof SumResult)
/* 205 */         return true; 
/* 206 */       if (targetResults instanceof CountVisitor.CountResult)
/* 206 */         return true; 
/* 207 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 211 */       if (!isCompatible(resultsToAdd))
/* 212 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 216 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 217 */         return this; 
/* 220 */       if (resultsToAdd instanceof SumResult) {
/* 222 */         Number[] sums = new Number[2];
/* 223 */         sums[0] = (Number)this.sum.getResult();
/* 223 */         sums[1] = (Number)resultsToAdd.getValue();
/* 224 */         SumVisitor.SumStrategy newSum = SumVisitor.createStrategy(CalcUtil.getObject((Object[])sums).getClass());
/* 226 */         newSum.add(sums[0]);
/* 227 */         newSum.add(sums[1]);
/* 228 */         return new SumResult(newSum);
/*     */       } 
/* 229 */       if (resultsToAdd instanceof CountVisitor.CountResult) {
/* 231 */         int count = resultsToAdd.toInt();
/* 232 */         AverageVisitor.AverageResult newResult = new AverageVisitor.AverageResult(count, this.sum.getResult());
/* 234 */         return newResult;
/*     */       } 
/* 236 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\SumVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */