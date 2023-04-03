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
/*     */ public class MinVisitor implements FeatureCalc, FeatureAttributeVisitor {
/*     */   private Expression expr;
/*     */   
/*     */   Comparable minvalue;
/*     */   
/*     */   Comparable curvalue;
/*     */   
/*     */   boolean visited = false;
/*     */   
/*     */   public MinVisitor(String attributeTypeName) {
/*  47 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  48 */     this.expr = (Expression)factory.property(attributeTypeName);
/*     */   }
/*     */   
/*     */   public MinVisitor(int attributeTypeIndex, SimpleFeatureType type) throws IllegalFilterException {
/*  53 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  54 */     this.expr = (Expression)factory.property(type.getDescriptor(attributeTypeIndex).getLocalName());
/*     */   }
/*     */   
/*     */   public MinVisitor(String attrName, SimpleFeatureType type) throws IllegalFilterException {
/*  59 */     FilterFactory factory = CommonFactoryFinder.getFilterFactory(null);
/*  60 */     this.expr = (Expression)factory.property(type.getDescriptor(attrName).getLocalName());
/*     */   }
/*     */   
/*     */   public MinVisitor(Expression expr) throws IllegalFilterException {
/*  64 */     this.expr = expr;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeatureCollection collection) {}
/*     */   
/*     */   public List<Expression> getExpressions() {
/*  73 */     return Arrays.asList(new Expression[] { this.expr });
/*     */   }
/*     */   
/*     */   public void visit(SimpleFeature feature) {
/*  82 */     visit((Feature)feature);
/*     */   }
/*     */   
/*     */   public void visit(Feature feature) {
/*  85 */     Object attribValue = this.expr.evaluate(feature);
/*  87 */     if (attribValue == null)
/*     */       return; 
/*  91 */     this.curvalue = (Comparable)attribValue;
/*  92 */     if (!this.visited || this.curvalue.compareTo(this.minvalue) < 0) {
/*  93 */       this.minvalue = this.curvalue;
/*  94 */       this.visited = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Comparable getMin() {
/* 109 */     if (!this.visited)
/* 110 */       throw new IllegalStateException("Must visit before min value is ready!"); 
/* 114 */     return this.minvalue;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 121 */     this.visited = false;
/* 122 */     this.minvalue = new Integer(0);
/*     */   }
/*     */   
/*     */   public CalcResult getResult() {
/* 126 */     if (!this.visited)
/* 127 */       return CalcResult.NULL_RESULT; 
/* 130 */     return new MinResult(this.minvalue);
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 134 */     return this.expr;
/*     */   }
/*     */   
/*     */   public void setValue(Object result) {
/* 148 */     this.visited = true;
/* 149 */     this.minvalue = (Comparable)result;
/*     */   }
/*     */   
/*     */   public static class MinResult extends AbstractCalcResult {
/*     */     private Comparable minValue;
/*     */     
/*     */     public MinResult(Comparable newMinValue) {
/* 156 */       this.minValue = newMinValue;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 160 */       Comparable min = this.minValue;
/* 162 */       return min;
/*     */     }
/*     */     
/*     */     public boolean isCompatible(CalcResult targetResults) {
/* 167 */       if (targetResults instanceof MinResult || targetResults == CalcResult.NULL_RESULT)
/* 168 */         return true; 
/* 171 */       return false;
/*     */     }
/*     */     
/*     */     public CalcResult merge(CalcResult resultsToAdd) {
/* 175 */       if (!isCompatible(resultsToAdd))
/* 176 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 180 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 181 */         return this; 
/* 184 */       if (resultsToAdd instanceof MinResult) {
/* 186 */         Comparable<Comparable> toAdd = (Comparable)resultsToAdd.getValue();
/* 187 */         Comparable<Comparable> newMin = this.minValue;
/* 189 */         if (newMin.getClass() != toAdd.getClass()) {
/* 190 */           Class<?> bestClass = CalcUtil.bestClass(new Object[] { toAdd, newMin });
/* 191 */           if (bestClass != toAdd.getClass())
/* 192 */             toAdd = (Comparable)CalcUtil.convert(toAdd, bestClass); 
/* 193 */           if (bestClass != newMin.getClass())
/* 194 */             newMin = (Comparable)CalcUtil.convert(newMin, bestClass); 
/*     */         } 
/* 196 */         if (newMin.compareTo(toAdd) > 0)
/* 197 */           newMin = toAdd; 
/* 200 */         return new MinResult(newMin);
/*     */       } 
/* 202 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\MinVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */