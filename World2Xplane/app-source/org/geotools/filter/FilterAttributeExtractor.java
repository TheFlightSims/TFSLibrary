/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.visitor.DefaultFilterVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ 
/*     */ public class FilterAttributeExtractor extends DefaultFilterVisitor {
/*  58 */   FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
/*     */   
/*  61 */   protected Set<String> attributeNames = new HashSet<String>();
/*     */   
/*  62 */   protected Set<PropertyName> propertyNames = new HashSet<PropertyName>();
/*     */   
/*     */   protected boolean usingVolatileFunctions;
/*     */   
/*     */   protected boolean usingDynamicProperties;
/*     */   
/*     */   protected SimpleFeatureType featureType;
/*     */   
/*     */   public FilterAttributeExtractor() {
/*  73 */     this(null);
/*     */   }
/*     */   
/*     */   public FilterAttributeExtractor(SimpleFeatureType featureType) {
/*  82 */     this.featureType = featureType;
/*     */   }
/*     */   
/*     */   public Set<String> getAttributeNameSet() {
/*  90 */     return Collections.unmodifiableSet(this.attributeNames);
/*     */   }
/*     */   
/*     */   public Set<PropertyName> getPropertyNameSet() {
/* 100 */     return this.propertyNames;
/*     */   }
/*     */   
/*     */   public String[] getAttributeNames() {
/* 109 */     return this.attributeNames.<String>toArray(new String[this.attributeNames.size()]);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 116 */     this.attributeNames = new HashSet<String>();
/* 117 */     this.usingVolatileFunctions = false;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 121 */     if (data != null && data != this.attributeNames)
/* 122 */       this.attributeNames = (Set<String>)data; 
/* 124 */     this.propertyNames.add(expression);
/* 126 */     if (this.featureType != null) {
/* 130 */       AttributeDescriptor type = (AttributeDescriptor)expression.evaluate(this.featureType);
/* 131 */       if (type != null) {
/* 132 */         this.attributeNames.add(type.getLocalName());
/*     */       } else {
/* 135 */         this.attributeNames.add(expression.getPropertyName());
/*     */       } 
/*     */     } else {
/* 139 */       this.attributeNames.add(expression.getPropertyName());
/*     */     } 
/* 142 */     return this.attributeNames;
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 146 */     if (expression instanceof org.opengis.filter.expression.VolatileFunction)
/* 147 */       this.usingVolatileFunctions = true; 
/* 149 */     if (expression instanceof org.geotools.filter.function.FilterFunction_property) {
/* 150 */       boolean foundLiteral = false;
/* 152 */       if (expression.getParameters() != null && expression.getParameters().size() > 0) {
/* 153 */         Expression firstParam = expression.getParameters().get(0);
/* 155 */         FilterAttributeExtractor secondary = new FilterAttributeExtractor();
/* 156 */         firstParam.accept((ExpressionVisitor)secondary, null);
/* 157 */         if (secondary.isConstantExpression()) {
/* 158 */           String name = (String)firstParam.evaluate(null, String.class);
/* 159 */           if (name != null) {
/* 160 */             this.attributeNames.add(name);
/* 161 */             this.propertyNames.add(this.ff.property(name));
/* 162 */             foundLiteral = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 167 */       if (!foundLiteral)
/* 168 */         this.usingDynamicProperties = true; 
/*     */     } 
/* 171 */     return super.visit(expression, data);
/*     */   }
/*     */   
/*     */   public boolean isConstantExpression() {
/* 181 */     return (!this.usingVolatileFunctions && !this.usingDynamicProperties && getAttributeNameSet().isEmpty());
/*     */   }
/*     */   
/*     */   public boolean isUsingDynamincProperties() {
/* 191 */     return this.usingDynamicProperties;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterAttributeExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */