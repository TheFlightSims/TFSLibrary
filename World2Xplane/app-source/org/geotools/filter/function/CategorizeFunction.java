/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.FilterAttributeExtractor;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public class CategorizeFunction implements Function {
/*     */   public static final String SUCCEEDING = "succeeding";
/*     */   
/*     */   public static final String PRECEDING = "preceding";
/*     */   
/*     */   public static final String RASTER_DATA = "Rasterdata";
/*     */   
/*     */   private final List<Expression> parameters;
/*     */   
/*     */   private final Literal fallback;
/*     */   
/*     */   private boolean staticTable = true;
/*     */   
/*     */   double[] thresholds;
/*     */   
/*     */   Expression[] values;
/*     */   
/*     */   volatile Object[] convertedValues;
/*     */   
/*     */   private Class convertedValuesContext;
/*     */   
/*     */   private String belongsTo;
/*     */   
/*  93 */   public static final FunctionName NAME = (FunctionName)new FunctionNameImpl("Categorize", new String[] { "LookupValue", "Value", "Threshold 1", "Value 1", "Threshold 2", "Value 2", "succeeding or preceding" });
/*     */   
/*     */   public CategorizeFunction() {
/*  97 */     this(new ArrayList<Expression>(), null);
/*     */   }
/*     */   
/*     */   public CategorizeFunction(List<Expression> parameters, Literal fallback) {
/* 101 */     this.parameters = parameters;
/* 102 */     this.fallback = fallback;
/* 105 */     if (parameters.size() % 2 != 0) {
/* 107 */       Expression lastParameter = parameters.get(parameters.size() - 1);
/* 108 */       String lastValue = (String)lastParameter.evaluate(null, String.class);
/* 110 */       if ("preceding".equalsIgnoreCase(lastValue)) {
/* 111 */         this.belongsTo = "preceding";
/* 112 */       } else if ("succeeding".equalsIgnoreCase(lastValue)) {
/* 113 */         this.belongsTo = "succeeding";
/*     */       } else {
/* 115 */         throw new IllegalArgumentException("The valid structure of a categorize function call is \"lookup, value, [threshold, value]*, [succeeding|preceding]\", yet there is a odd number of parameters and the last value is not succeeding nor preceeding");
/*     */       } 
/*     */     } 
/* 122 */     FilterAttributeExtractor extractor = new FilterAttributeExtractor();
/* 123 */     this.thresholds = new double[(parameters.size() - 1) / 2];
/* 124 */     this.values = new Expression[this.thresholds.length + 1];
/* 125 */     for (int i = 1; i < parameters.size(); i++) {
/* 126 */       Expression expression = parameters.get(i);
/* 127 */       if (expression != null) {
/* 128 */         extractor.clear();
/* 129 */         expression.accept((ExpressionVisitor)extractor, null);
/* 130 */         if (!extractor.isConstantExpression()) {
/* 131 */           this.staticTable = false;
/* 132 */           this.thresholds = null;
/*     */           break;
/*     */         } 
/* 135 */         if (i % 2 == 0) {
/* 136 */           Double threshold = (Double)expression.evaluate(null, Double.class);
/* 137 */           if (threshold == null) {
/* 138 */             this.staticTable = false;
/* 139 */             this.thresholds = null;
/*     */             break;
/*     */           } 
/* 142 */           this.thresholds[i / 2 - 1] = threshold.doubleValue();
/*     */         } else {
/* 145 */           this.values[i / 2] = expression;
/*     */         } 
/*     */       } 
/*     */     } 
/* 151 */     if (this.thresholds != null)
/* 152 */       Arrays.sort(this.thresholds); 
/*     */   }
/*     */   
/*     */   public String getName() {
/* 157 */     return NAME.getName();
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName() {
/* 160 */     return NAME;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/* 163 */     return Collections.unmodifiableList(this.parameters);
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 167 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/* 171 */     return evaluate(object, Object.class);
/*     */   }
/*     */   
/*     */   public <T> T evaluate(Object object, Class<T> context) {
/*     */     List<Expression> splits;
/* 175 */     Expression lookupExp = this.parameters.get(0);
/* 178 */     Double value = (Double)lookupExp.evaluate(object, Double.class);
/* 179 */     if (value == null)
/* 180 */       value = (Double)Converters.convert(object, Double.class); 
/* 183 */     if (value != null && this.staticTable) {
/* 184 */       int valIdx, expIdx = Arrays.binarySearch(this.thresholds, value.doubleValue());
/* 186 */       if (expIdx >= 0) {
/* 188 */         if ("preceding".equals(this.belongsTo)) {
/* 189 */           valIdx = expIdx;
/*     */         } else {
/* 191 */           valIdx = expIdx + 1;
/*     */         } 
/*     */       } else {
/* 195 */         valIdx = -expIdx - 1;
/*     */       } 
/* 199 */       if (this.convertedValues == null)
/* 200 */         synchronized (this) {
/* 201 */           if (this.convertedValues == null) {
/* 202 */             this.convertedValues = new Object[this.values.length];
/* 203 */             for (int j = 0; j < this.convertedValues.length; j++)
/* 204 */               this.convertedValues[j] = this.values[j].evaluate(object, context); 
/* 206 */             this.convertedValuesContext = context;
/*     */           } 
/*     */         }  
/* 212 */       if (this.convertedValuesContext == context)
/* 213 */         return (T)this.convertedValues[valIdx]; 
/* 215 */       return (T)this.values[valIdx].evaluate(object, context);
/*     */     } 
/* 220 */     Expression currentExp = this.parameters.get(1);
/* 222 */     if (this.parameters.size() == 2)
/* 223 */       return (T)currentExp.evaluate(object, context); 
/* 224 */     if (this.parameters.size() % 2 == 0) {
/* 225 */       splits = this.parameters.subList(2, this.parameters.size());
/*     */     } else {
/* 227 */       splits = this.parameters.subList(2, this.parameters.size() - 1);
/*     */     } 
/* 230 */     FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/* 231 */     for (int i = 0; i < splits.size(); ) {
/*     */       PropertyIsGreaterThanOrEqualTo propertyIsGreaterThanOrEqualTo;
/* 232 */       Expression threshholdExp = splits.get(i);
/* 233 */       Expression rangedExp = splits.get(i + 1);
/* 236 */       if ("preceding".equals(this.belongsTo)) {
/* 237 */         PropertyIsGreaterThan propertyIsGreaterThan = ff.greater(lookupExp, threshholdExp);
/*     */       } else {
/* 239 */         propertyIsGreaterThanOrEqualTo = ff.greaterOrEqual(lookupExp, threshholdExp);
/*     */       } 
/* 241 */       if (propertyIsGreaterThanOrEqualTo.evaluate(object)) {
/* 242 */         currentExp = rangedExp;
/*     */         i += 2;
/*     */       } 
/*     */     } 
/* 247 */     return (T)currentExp.evaluate(object, context);
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 251 */     return this.fallback;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\CategorizeFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */