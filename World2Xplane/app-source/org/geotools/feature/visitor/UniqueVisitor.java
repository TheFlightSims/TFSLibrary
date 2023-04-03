/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class UniqueVisitor implements FeatureCalc, FeatureAttributeVisitor, LimitingVisitor {
/*     */   private Expression expr;
/*     */   
/*  50 */   Set set = new HashSet();
/*     */   
/*  51 */   Set skipped = new HashSet();
/*     */   
/*  52 */   int startIndex = 0;
/*     */   
/*  53 */   int maxFeatures = Integer.MAX_VALUE;
/*     */   
/*  54 */   int currentItem = 0;
/*     */   
/*     */   boolean preserveOrder = false;
/*     */   
/*     */   public UniqueVisitor(String attributeTypeName) {
/*  58 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  59 */     this.expr = (Expression)factory.property(attributeTypeName);
/*     */   }
/*     */   
/*     */   public UniqueVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  66 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  67 */     this.expr = (Expression)factory.property(type.getDescriptor(attributeTypeIndex).getLocalName());
/*     */   }
/*     */   
/*     */   public UniqueVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  72 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  73 */     this.expr = (Expression)factory.property(type.getDescriptor(attrName).getLocalName());
/*     */   }
/*     */   
/*     */   public UniqueVisitor(Expression expr) {
/*  77 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public void setStartIndex(int startIndex) {
/*  86 */     this.startIndex = startIndex;
/*     */   }
/*     */   
/*     */   public void setMaxFeatures(int maxFeatures) {
/*  91 */     this.maxFeatures = maxFeatures;
/*     */   }
/*     */   
/*     */   public void setPreserveOrder(boolean preserveOrder) {
/*  95 */     this.preserveOrder = preserveOrder;
/*  96 */     this.set = createNewSet(Collections.EMPTY_LIST);
/*     */   }
/*     */   
/*     */   public int getStartIndex() {
/* 103 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public int getMaxFeatures() {
/* 109 */     return this.maxFeatures;
/*     */   }
/*     */   
/*     */   public List<Expression> getExpressions() {
/* 116 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/* 120 */     visit(feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/* 124 */     Object value = this.expr.evaluate(feature);
/* 125 */     if (value != null && 
/* 126 */       !this.set.contains(value) && !this.skipped.contains(value)) {
/* 127 */       if (this.currentItem >= this.startIndex && this.currentItem < this.startIndex + this.maxFeatures) {
/* 128 */         this.set.add(value);
/*     */       } else {
/* 130 */         this.skipped.add(value);
/*     */       } 
/* 132 */       this.currentItem++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 138 */     return this.expr;
/*     */   }
/*     */   
/*     */   public Set getUnique() {
/* 145 */     return this.set;
/*     */   }
/*     */   
/*     */   public void setValue(Object newSet) {
/* 150 */     if (newSet instanceof Collection) {
/* 151 */       this.set = createNewSet((Collection)newSet);
/*     */     } else {
/* 153 */       Collection collection = (Collection)Converters.convert(newSet, List.class);
/* 154 */       if (collection != null) {
/* 155 */         this.set = createNewSet(collection);
/*     */       } else {
/* 157 */         this.set = createNewSet(Collections.singleton(newSet));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Set createNewSet(Collection collection) {
/* 163 */     return UniqueResult.createNewSet(collection, this.preserveOrder);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 173 */     this.set = createNewSet(Collections.EMPTY_LIST);
/* 174 */     this.skipped = new HashSet();
/* 176 */     this.currentItem = 0;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 180 */     if (this.set.size() < 1)
/* 181 */       return CalcResult.NULL_RESULT; 
/* 183 */     return new UniqueResult(this.set, this.preserveOrder);
/*     */   }
/*     */   
/*     */   public static class UniqueResult extends AbstractCalcResult {
/*     */     private Set unique;
/*     */     
/*     */     private boolean preserveOrder = false;
/*     */     
/*     */     public UniqueResult(Set newSet) {
/* 191 */       this.unique = newSet;
/*     */     }
/*     */     
/*     */     public UniqueResult(Set newSet, boolean preserveOrder) {
/* 195 */       this.unique = newSet;
/* 196 */       this.preserveOrder = preserveOrder;
/*     */     }
/*     */     
/*     */     public static Set createNewSet(Collection<?> collection, boolean preserveOrder) {
/* 200 */       if (preserveOrder)
/* 201 */         return new LinkedHashSet(collection); 
/* 203 */       return new HashSet(collection);
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 208 */       return createNewSet(this.unique, this.preserveOrder);
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 213 */       if (targetResults instanceof UniqueResult || targetResults == CalcResult.NULL_RESULT)
/* 213 */         return true; 
/* 214 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 218 */       if (!isCompatible(resultsToAdd))
/* 219 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 223 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 224 */         return this; 
/* 227 */       if (resultsToAdd instanceof UniqueResult) {
/* 229 */         Set newSet = createNewSet(this.unique, this.preserveOrder);
/* 230 */         newSet.addAll((Set)resultsToAdd.getValue());
/* 231 */         return new UniqueResult(newSet, this.preserveOrder);
/*     */       } 
/* 233 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasLimits() {
/* 241 */     return (this.startIndex > 0 || this.maxFeatures < Integer.MAX_VALUE);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\UniqueVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */