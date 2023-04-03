/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.Attribute;
/*     */ import org.opengis.feature.ComplexAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class FilterAbstract implements Filter {
/*     */   protected FilterFactory factory;
/*     */   
/*     */   protected FilterAbstract(FilterFactory factory) {
/*  48 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public boolean evaluate(SimpleFeature feature) {
/*  57 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public boolean accepts(SimpleFeature feature) {
/*  75 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/*  81 */     return extraData;
/*     */   }
/*     */   
/*     */   protected Object eval(Expression expression, SimpleFeature feature) {
/*  91 */     if (expression == null || feature == null)
/*  91 */       return null; 
/*  92 */     return expression.evaluate(feature);
/*     */   }
/*     */   
/*     */   private Object unpack(Object value) {
/* 103 */     if (value instanceof ComplexAttribute) {
/* 104 */       Property simpleContent = ((ComplexAttribute)value).getProperty((Name)new NameImpl("simpleContent"));
/* 105 */       if (simpleContent == null)
/* 106 */         return null; 
/* 108 */       return simpleContent.getValue();
/*     */     } 
/* 112 */     if (value instanceof Attribute)
/* 113 */       return ((Attribute)value).getValue(); 
/* 116 */     return value;
/*     */   }
/*     */   
/*     */   protected Object eval(Expression expression, Object object) {
/* 128 */     if (expression == null)
/* 128 */       return null; 
/* 129 */     Object value = expression.evaluate(object);
/* 131 */     if (value instanceof java.util.Collection) {
/* 133 */       List<Object> list = new ArrayList();
/* 134 */       for (Object member : value)
/* 135 */         list.add(unpack(member)); 
/* 137 */       return list;
/*     */     } 
/* 140 */     return unpack(value);
/*     */   }
/*     */   
/*     */   protected Object eval(Expression expression, Object object, Class context) {
/* 150 */     if (expression == null)
/* 150 */       return null; 
/* 151 */     return expression.evaluate(object, context);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterAbstract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */