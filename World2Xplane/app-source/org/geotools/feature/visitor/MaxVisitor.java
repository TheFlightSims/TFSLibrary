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
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class MaxVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*     */   Comparable maxvalue;
/*     */   
/*     */   Comparable curvalue;
/*     */   
/*     */   boolean visited = false;
/*     */   
/*  45 */   int countNull = 0;
/*     */   
/*  46 */   int countNaN = 0;
/*     */   
/*     */   public MaxVisitor(String attributeTypeName) {
/*  49 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  50 */     this.expr = (Expression)factory.property(attributeTypeName);
/*     */   }
/*     */   
/*     */   public MaxVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  55 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  56 */     this.expr = (Expression)factory.property(type.getDescriptor(attributeTypeIndex).getLocalName());
/*     */   }
/*     */   
/*     */   public MaxVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  61 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  62 */     this.expr = (Expression)factory.property(type.getDescriptor(attrName).getLocalName());
/*     */   }
/*     */   
/*     */   public MaxVisitor(Expression expr) throws IllegalFilterException {
/*  66 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/*  75 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/*  84 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/*  87 */     Object attribValue = this.expr.evaluate(feature);
/*  89 */     if (attribValue == null) {
/*  90 */       this.countNull++;
/*     */       return;
/*     */     } 
/*  94 */     if (attribValue instanceof Double) {
/*  95 */       double doubleVal = ((Double)attribValue).doubleValue();
/*  96 */       if (Double.isNaN(doubleVal) || Double.isInfinite(doubleVal)) {
/*  97 */         this.countNaN++;
/*     */         return;
/*     */       } 
/*     */     } 
/* 102 */     this.curvalue = (Comparable)attribValue;
/* 104 */     if (!this.visited || this.curvalue.compareTo(this.maxvalue) > 0) {
/* 105 */       this.maxvalue = this.curvalue;
/* 106 */       this.visited = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Comparable getMax() {
/* 120 */     if (!this.visited)
/* 121 */       throw new IllegalStateException("Must visit before max value is ready!"); 
/* 125 */     return this.maxvalue;
/*     */   }
/*     */   
/*     */   public int getNaNCount() {
/* 132 */     return this.countNaN;
/*     */   }
/*     */   
/*     */   public int getNullCount() {
/* 139 */     return this.countNull;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 146 */     this.visited = false;
/* 147 */     this.maxvalue = new Integer(-2147483648);
/* 148 */     this.countNaN = 0;
/* 149 */     this.countNull = 0;
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 153 */     return this.expr;
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 157 */     if (!this.visited)
/* 158 */       return CalcResult.NULL_RESULT; 
/* 161 */     return new MaxResult(this.maxvalue);
/*     */   }
/*     */   
/*     */   public void setValue(Object result) {
/* 175 */     this.visited = true;
/* 176 */     this.maxvalue = (Comparable)result;
/*     */   }
/*     */   
/*     */   public static class MaxResult extends AbstractCalcResult {
/*     */     private Comparable maxValue;
/*     */     
/*     */     public MaxResult(Comparable newMaxValue) {
/* 183 */       this.maxValue = newMaxValue;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 187 */       Comparable max = this.maxValue;
/* 189 */       return max;
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 194 */       if (targetResults instanceof MaxResult || targetResults == CalcResult.NULL_RESULT)
/* 195 */         return true; 
/* 198 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 202 */       if (!isCompatible(resultsToAdd))
/* 203 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 207 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 208 */         return this; 
/* 211 */       if (resultsToAdd instanceof MaxResult) {
/* 213 */         Comparable<Comparable> toAdd = (Comparable)resultsToAdd.getValue();
/* 214 */         Comparable<Comparable> newMax = this.maxValue;
/* 216 */         if (newMax.getClass() != toAdd.getClass()) {
/* 217 */           Class<?> bestClass = CalcUtil.bestClass(new Object[] { toAdd, newMax });
/* 218 */           if (bestClass != toAdd.getClass())
/* 219 */             toAdd = (Comparable)CalcUtil.convert(toAdd, bestClass); 
/* 220 */           if (bestClass != newMax.getClass())
/* 221 */             newMax = (Comparable)CalcUtil.convert(newMax, bestClass); 
/*     */         } 
/* 223 */         if (newMax.compareTo(toAdd) < 0)
/* 224 */           newMax = toAdd; 
/* 227 */         return new MaxResult(newMax);
/*     */       } 
/* 229 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\MaxVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */