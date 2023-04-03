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
/*     */ public class AverageVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*     */   private boolean isOptimized = false;
/*     */   
/*     */   AverageStrategy strategy;
/*     */   
/*     */   public AverageVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  61 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  62 */     AttributeDescriptor attributeType = type.getDescriptor(attributeTypeIndex);
/*  63 */     this.expr = (Expression)factory.property(attributeType.getLocalName());
/*  64 */     createStrategy(attributeType.getType().getBinding());
/*     */   }
/*     */   
/*     */   public AverageVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  77 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  78 */     AttributeDescriptor attributeType = type.getDescriptor(attrName);
/*  79 */     this.expr = (Expression)factory.property(attributeType.getLocalName());
/*  80 */     createStrategy(attributeType.getType().getBinding());
/*     */   }
/*     */   
/*     */   public AverageVisitor(Expression expr) throws IllegalFilterException {
/*  91 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/* 100 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   private static AverageStrategy createStrategy(Class<Integer> type) {
/* 114 */     if (type == Integer.class)
/* 115 */       return new IntegerAverageStrategy(); 
/* 116 */     if (type == Long.class)
/* 117 */       return new LongAverageStrategy(); 
/* 118 */     if (type == Float.class)
/* 119 */       return new FloatAverageStrategy(); 
/* 120 */     if (Number.class.isAssignableFrom(type))
/* 121 */       return new DoubleAverageStrategy(); 
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/* 127 */     visit(feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/* 131 */     Object value = this.expr.evaluate(feature);
/* 133 */     if (value != null) {
/* 134 */       if (this.strategy == null) {
/* 135 */         Class<?> type = value.getClass();
/* 136 */         this.strategy = createStrategy(type);
/*     */       } 
/* 139 */       this.strategy.add(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 144 */     return this.expr;
/*     */   }
/*     */   
/*     */   public Object getAverage() {
/* 153 */     return this.strategy.getResult();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 160 */     this.strategy = null;
/* 161 */     this.isOptimized = false;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 169 */     if (this.strategy == null)
/* 170 */       return CalcResult.NULL_RESULT; 
/* 172 */     return new AverageResult(this.strategy, this.isOptimized);
/*     */   }
/*     */   
/*     */   public void setValue(Object newAverage) {
/* 176 */     reset();
/* 178 */     Class<?> type = newAverage.getClass();
/* 179 */     this.strategy = createStrategy(type);
/* 180 */     this.strategy.add(newAverage);
/* 181 */     this.isOptimized = true;
/*     */   }
/*     */   
/*     */   public void setValue(int newCount, Object newSum) {
/* 185 */     reset();
/* 186 */     this.strategy = createStrategy(newSum.getClass());
/* 187 */     this.strategy.set(newCount, newSum);
/* 188 */     this.isOptimized = false;
/*     */   }
/*     */   
/*     */   static interface AverageStrategy {
/*     */     void add(Object param1Object);
/*     */     
/*     */     Object getResult();
/*     */     
/*     */     Object getSum();
/*     */     
/*     */     int getCount();
/*     */     
/*     */     void set(int param1Int, Object param1Object);
/*     */   }
/*     */   
/*     */   static class DoubleAverageStrategy implements AverageStrategy {
/* 213 */     double number = 0.0D;
/*     */     
/* 214 */     int count = 0;
/*     */     
/*     */     public void add(Object value) {
/* 217 */       this.number += ((Number)value).doubleValue();
/* 218 */       this.count++;
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 222 */       return new Double(this.number / this.count);
/*     */     }
/*     */     
/*     */     public Object getSum() {
/* 226 */       return new Double(this.number);
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 230 */       return this.count;
/*     */     }
/*     */     
/*     */     public void set(int newCount, Object sum) {
/* 234 */       this.number = ((Number)sum).doubleValue();
/* 235 */       this.count = newCount;
/*     */     }
/*     */   }
/*     */   
/*     */   static class FloatAverageStrategy implements AverageStrategy {
/* 243 */     float number = 0.0F;
/*     */     
/* 244 */     int count = 0;
/*     */     
/*     */     public void add(Object value) {
/* 247 */       this.number += ((Number)value).floatValue();
/* 248 */       this.count++;
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 252 */       return new Float(this.number / this.count);
/*     */     }
/*     */     
/*     */     public Object getSum() {
/* 256 */       return new Float(this.number);
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 260 */       return this.count;
/*     */     }
/*     */     
/*     */     public void set(int newCount, Object sum) {
/* 264 */       this.number = ((Number)sum).floatValue();
/* 265 */       this.count = newCount;
/*     */     }
/*     */   }
/*     */   
/*     */   static class LongAverageStrategy implements AverageStrategy {
/* 273 */     long number = 0L;
/*     */     
/* 274 */     int count = 0;
/*     */     
/*     */     public void add(Object value) {
/* 277 */       this.number += ((Number)value).longValue();
/* 278 */       this.count++;
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 282 */       return new Double(this.number / this.count);
/*     */     }
/*     */     
/*     */     public Object getSum() {
/* 286 */       return new Long(this.number);
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 290 */       return this.count;
/*     */     }
/*     */     
/*     */     public void set(int newCount, Object sum) {
/* 294 */       this.number = ((Number)sum).longValue();
/* 295 */       this.count = newCount;
/*     */     }
/*     */   }
/*     */   
/*     */   static class IntegerAverageStrategy implements AverageStrategy {
/* 303 */     int number = 0;
/*     */     
/* 304 */     int count = 0;
/*     */     
/*     */     public void add(Object value) {
/* 307 */       this.number += ((Number)value).intValue();
/* 308 */       this.count++;
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 312 */       return new Double(this.number / this.count);
/*     */     }
/*     */     
/*     */     public Object getSum() {
/* 316 */       return new Integer(this.number);
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 320 */       return this.count;
/*     */     }
/*     */     
/*     */     public void set(int newCount, Object sum) {
/* 324 */       this.number = ((Number)sum).intValue();
/* 325 */       this.count = newCount;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AverageResult extends AbstractCalcResult {
/*     */     private AverageVisitor.AverageStrategy averageStrategy;
/*     */     
/*     */     private boolean isOptimized = false;
/*     */     
/*     */     public AverageResult(Object newAverageStrategy) {
/* 337 */       this.averageStrategy = (AverageVisitor.AverageStrategy)newAverageStrategy;
/*     */     }
/*     */     
/*     */     public AverageResult(Object newAverageStrategy, boolean isOptimized) {
/* 341 */       this.averageStrategy = (AverageVisitor.AverageStrategy)newAverageStrategy;
/* 342 */       this.isOptimized = isOptimized;
/*     */     }
/*     */     
/*     */     public AverageResult(int newCount, Object newSum) {
/* 346 */       this.averageStrategy = AverageVisitor.createStrategy(newSum.getClass());
/* 347 */       this.averageStrategy.set(newCount, newSum);
/*     */     }
/*     */     
/*     */     public Object getResult() {
/* 351 */       return this.averageStrategy.getResult();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 355 */       return this.averageStrategy.getResult();
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 364 */       if (this.isOptimized)
/* 365 */         return -1; 
/* 367 */       return this.averageStrategy.getCount();
/*     */     }
/*     */     
/*     */     public Object getSum() {
/* 376 */       if (this.isOptimized)
/* 377 */         return null; 
/* 379 */       return this.averageStrategy.getSum();
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 392 */       if (targetResults instanceof AverageResult || targetResults == CalcResult.NULL_RESULT)
/* 393 */         return true; 
/* 395 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 412 */       if (!isCompatible(resultsToAdd))
/* 413 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 417 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 418 */         return this; 
/* 421 */       if (resultsToAdd instanceof AverageResult) {
/* 422 */         AverageResult moreResults = (AverageResult)resultsToAdd;
/* 425 */         if (this.isOptimized || moreResults.isOptimized)
/* 426 */           throw new IllegalArgumentException("Optimized average results cannot be merged."); 
/* 430 */         Number[] sums = { (Number)this.averageStrategy.getSum(), (Number)moreResults.averageStrategy.getSum() };
/* 434 */         Number newSum = CalcUtil.sum(sums);
/* 435 */         Number newCount = new Integer(this.averageStrategy.getCount() + moreResults.averageStrategy.getCount());
/* 437 */         Number[] params = { newSum, newCount };
/* 438 */         Object newAverage = CalcUtil.getObject((Object[])params);
/* 440 */         AverageVisitor.AverageStrategy newAverageObj = AverageVisitor.createStrategy(newAverage.getClass());
/* 441 */         newAverageObj.set(newCount.intValue(), newSum);
/* 443 */         return new AverageResult(newAverageObj);
/*     */       } 
/* 445 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\AverageVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */