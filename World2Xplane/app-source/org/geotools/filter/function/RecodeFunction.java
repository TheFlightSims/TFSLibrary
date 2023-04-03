/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.FilterAttributeExtractor;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public class RecodeFunction implements Function {
/*  62 */   private static final FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/*     */   private final List<Expression> parameters;
/*     */   
/*     */   private boolean staticTable = true;
/*     */   
/*  68 */   private volatile Map fastLookup = null;
/*     */   
/*  70 */   private Class lastKeyType = null;
/*     */   
/*  72 */   private Class lastContextType = null;
/*     */   
/*     */   private final Literal fallback;
/*     */   
/*  79 */   public static final FunctionName NAME = (FunctionName)new FunctionNameImpl("Recode", new String[] { "LookupValue", "Data 1", "Value 1", "Data 2", "Value 2" });
/*     */   
/*     */   public RecodeFunction() {
/*  83 */     this(new ArrayList<Expression>(), null);
/*     */   }
/*     */   
/*     */   public RecodeFunction(List<Expression> parameters, Literal fallback) {
/*  87 */     this.parameters = parameters;
/*  88 */     this.fallback = fallback;
/*  91 */     if (parameters.size() % 2 != 1 && parameters.size() != 0)
/*  92 */       throw new IllegalArgumentException("There must be an equal number of lookup data and return values"); 
/*  97 */     FilterAttributeExtractor extractor = new FilterAttributeExtractor();
/*  98 */     for (int i = 1; i < parameters.size(); i++) {
/*  99 */       Expression expression = parameters.get(i);
/* 100 */       if (expression != null) {
/* 101 */         extractor.clear();
/* 102 */         expression.accept((ExpressionVisitor)extractor, null);
/* 103 */         if (!extractor.isConstantExpression()) {
/* 104 */           this.staticTable = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getName() {
/* 113 */     return "Recode";
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName() {
/* 116 */     return NAME;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/* 119 */     return Collections.unmodifiableList(this.parameters);
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 123 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/* 127 */     return evaluate(object, Object.class);
/*     */   }
/*     */   
/*     */   public <T> T evaluate(Object object, Class<T> context) {
/* 131 */     Expression lookupExp = this.parameters.get(0);
/* 132 */     List<Expression> pairList = this.parameters.subList(1, this.parameters.size());
/* 135 */     if (this.staticTable) {
/* 136 */       Object lookup = lookupExp.evaluate(object);
/* 137 */       if (lookup != null) {
/* 138 */         if (this.fastLookup == null)
/* 139 */           synchronized (this) {
/* 140 */             if (this.fastLookup == null) {
/* 142 */               this.fastLookup = new HashMap<Object, Object>();
/* 143 */               this.lastKeyType = lookup.getClass();
/* 144 */               this.lastContextType = context;
/* 145 */               for (int j = 0; j < pairList.size(); j += 2) {
/* 146 */                 Object key = ((Expression)pairList.get(j)).evaluate(object, this.lastKeyType);
/* 147 */                 Object value = ((Expression)pairList.get(j + 1)).evaluate(object, context);
/* 148 */                 this.fastLookup.put(key, value);
/*     */               } 
/*     */             } 
/*     */           }  
/* 154 */         if (this.fastLookup != null && lookup.getClass() == this.lastKeyType && context == this.lastContextType) {
/* 155 */           T result = (T)this.fastLookup.get(lookup);
/* 156 */           return result;
/*     */         } 
/*     */       } 
/*     */     } 
/* 162 */     for (int i = 0; i < pairList.size(); i += 2) {
/* 163 */       Expression keyExpr = pairList.get(i);
/* 164 */       Expression valueExpr = pairList.get(i + 1);
/* 168 */       PropertyIsEqualTo propertyIsEqualTo = ff.equal(lookupExp, keyExpr, false);
/* 170 */       if (propertyIsEqualTo.evaluate(object))
/* 171 */         return (T)valueExpr.evaluate(object, context); 
/*     */     } 
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 179 */     return this.fallback;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\RecodeFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */