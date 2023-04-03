/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class MedianVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*  44 */   private List list = new ArrayList();
/*     */   
/*  52 */   private Object median = null;
/*     */   
/*     */   public MedianVisitor(String attributeTypeName) {
/*  55 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  56 */     this.expr = (Expression)factory.property(attributeTypeName);
/*     */   }
/*     */   
/*     */   public MedianVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  61 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  62 */     this.expr = (Expression)factory.property(type.getDescriptor(attributeTypeIndex).getLocalName());
/*     */   }
/*     */   
/*     */   public MedianVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  67 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  68 */     this.expr = (Expression)factory.property(type.getDescriptor(attrName).getLocalName());
/*     */   }
/*     */   
/*     */   public MedianVisitor(Expression expr) throws IllegalFilterException {
/*  72 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/*  81 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/*  85 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/*  91 */     Object result = this.expr.evaluate(feature);
/*  92 */     if (result == null)
/*     */       return; 
/*  96 */     if (result instanceof Comparable) {
/*  97 */       Comparable value = (Comparable)result;
/*  98 */       this.list.add(value);
/*     */     } else {
/* 100 */       throw new IllegalStateException("Expression is not comparable!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 105 */     return this.expr;
/*     */   }
/*     */   
/*     */   public Object getMedian() {
/* 112 */     if (this.median != null)
/* 114 */       return this.median; 
/* 117 */     Object newMedian = findMedian(this.list);
/* 118 */     if (newMedian == null)
/* 119 */       throw new IllegalStateException("Must visit before median value is ready!"); 
/* 122 */     return newMedian;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 130 */     this.list.clear();
/* 131 */     this.median = null;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 135 */     if (this.median != null)
/* 137 */       return new MedianResult(this.median); 
/* 138 */     if (this.list.size() < 1)
/* 140 */       return CalcResult.NULL_RESULT; 
/* 143 */     return new MedianResult(this.list);
/*     */   }
/*     */   
/*     */   public void setValue(List list) {
/* 148 */     reset();
/* 149 */     this.list = list;
/*     */   }
/*     */   
/*     */   public void setValue(Comparable median) {
/* 153 */     reset();
/* 154 */     this.median = median;
/*     */   }
/*     */   
/*     */   public static class MedianResult extends AbstractCalcResult {
/*     */     private List list;
/*     */     
/*     */     private Object median;
/*     */     
/*     */     public MedianResult(List newList) {
/* 166 */       this.list = newList;
/* 167 */       this.median = null;
/*     */     }
/*     */     
/*     */     public MedianResult(Object median) {
/* 171 */       this.list = null;
/* 172 */       this.median = median;
/*     */     }
/*     */     
/*     */     public List getList() {
/* 176 */       return this.list;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 180 */       if (this.median != null)
/* 181 */         return this.median; 
/* 183 */       return MedianVisitor.findMedian(this.list);
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 189 */       if (targetResults instanceof MedianResult || targetResults == CalcResult.NULL_RESULT)
/* 189 */         return true; 
/* 190 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isOptimized() {
/* 194 */       if (this.median != null)
/* 194 */         return true; 
/* 195 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 199 */       if (!isCompatible(resultsToAdd))
/* 200 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 204 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 205 */         return this; 
/* 208 */       if (resultsToAdd instanceof MedianResult) {
/* 209 */         MedianResult moreResults = (MedianResult)resultsToAdd;
/* 211 */         if (isOptimized() || moreResults.isOptimized())
/* 212 */           throw new IllegalArgumentException("Optimized median results cannot be merged."); 
/* 215 */         List toAdd = moreResults.getList();
/* 216 */         List<Comparable> newList = new ArrayList();
/* 218 */         int size = this.list.size() + toAdd.size();
/* 219 */         Object[] values = new Object[size];
/*     */         int i;
/* 221 */         for (i = 0; i < this.list.size(); i++)
/* 222 */           values[i] = this.list.get(i); 
/* 223 */         for (int j = 0; j < toAdd.size(); j++)
/* 224 */           values[i + j] = toAdd.get(j); 
/* 225 */         Class<?> bestClass = CalcUtil.bestClass(values);
/* 226 */         for (int k = 0; k < size; k++) {
/* 227 */           if (values[k].getClass() != bestClass)
/* 228 */             values[k] = CalcUtil.convert(values[k], bestClass); 
/* 229 */           newList.add((Comparable)values[k]);
/*     */         } 
/* 231 */         return new MedianResult(newList);
/*     */       } 
/* 233 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */   
/*     */   private static Object findMedian(List<Comparable> list) {
/*     */     Object median;
/* 250 */     if (list.size() < 1)
/* 251 */       return null; 
/* 254 */     Collections.sort(list);
/* 256 */     int index = -1;
/* 257 */     index = list.size() / 2;
/* 259 */     if (list.size() % 2 == 0) {
/* 262 */       Object input1 = list.get(index - 1);
/* 263 */       Object input2 = list.get(index);
/* 265 */       if (input1 instanceof Number && input2 instanceof Number) {
/* 266 */         Number num1 = (Number)input1;
/* 267 */         Number num2 = (Number)input2;
/* 268 */         Number[] numbers = new Number[2];
/* 269 */         numbers[0] = num1;
/* 269 */         numbers[1] = num2;
/* 270 */         median = CalcUtil.average(numbers);
/*     */       } else {
/* 273 */         List<Object> newList = new ArrayList();
/* 274 */         newList.add(input1);
/* 275 */         newList.add(input2);
/* 276 */         median = newList;
/*     */       } 
/*     */     } else {
/* 282 */       median = list.get(index);
/*     */     } 
/* 284 */     return median;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\MedianVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */