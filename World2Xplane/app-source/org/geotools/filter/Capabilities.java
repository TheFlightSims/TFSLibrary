/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.filter.capability.ArithmeticOperatorsImpl;
/*     */ import org.geotools.filter.capability.ComparisonOperatorsImpl;
/*     */ import org.geotools.filter.capability.FilterCapabilitiesImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.filter.capability.FunctionsImpl;
/*     */ import org.geotools.filter.capability.OperatorImpl;
/*     */ import org.geotools.filter.capability.SpatialOperatorImpl;
/*     */ import org.geotools.filter.capability.SpatialOperatorsImpl;
/*     */ import org.geotools.filter.visitor.IsFullySupportedFilterVisitor;
/*     */ import org.geotools.filter.visitor.IsSupportedFilterVisitor;
/*     */ import org.geotools.filter.visitor.OperatorNameFilterVisitor;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.capability.FilterCapabilities;
/*     */ import org.opengis.filter.capability.GeometryOperand;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ 
/*     */ public class Capabilities {
/* 100 */   private static Map<Class<?>, String> scalarNames = new HashMap<Class<?>, String>();
/*     */   
/*     */   static {
/* 101 */     scalarNames.put(PropertyIsEqualTo.class, "EqualTo");
/* 102 */     scalarNames.put(PropertyIsNotEqualTo.class, "NotEqualTo");
/* 103 */     scalarNames.put(PropertyIsGreaterThan.class, "GreaterThan");
/* 104 */     scalarNames.put(PropertyIsGreaterThanOrEqualTo.class, "GreaterThanOrEqualTo");
/* 105 */     scalarNames.put(PropertyIsLessThan.class, "LessThan");
/* 106 */     scalarNames.put(PropertyIsLessThanOrEqualTo.class, "LessThanOrEqualTo");
/* 107 */     scalarNames.put(PropertyIsNull.class, "NullCheck");
/* 108 */     scalarNames.put(PropertyIsLike.class, "Like");
/* 109 */     scalarNames.put(PropertyIsBetween.class, "Between");
/*     */   }
/*     */   
/* 113 */   private static Map<Class<?>, String> spatialNames = new HashMap<Class<?>, String>();
/*     */   
/*     */   static {
/* 114 */     spatialNames.put(BBOX.class, "BBOX");
/* 115 */     spatialNames.put(Equals.class, "Equals");
/* 116 */     spatialNames.put(Disjoint.class, "Disjoint");
/* 117 */     spatialNames.put(Intersects.class, "Intersects");
/* 118 */     spatialNames.put(Touches.class, "Touches");
/* 119 */     spatialNames.put(Crosses.class, "Crosses");
/* 120 */     spatialNames.put(Within.class, "Within");
/* 121 */     spatialNames.put(Contains.class, "Contains");
/* 122 */     spatialNames.put(Overlaps.class, "Overlaps");
/* 123 */     spatialNames.put(Beyond.class, "Beyond");
/* 124 */     spatialNames.put(DWithin.class, "DWithin");
/*     */   }
/*     */   
/* 128 */   private static Map<Class<?>, String> logicalNames = new HashMap<Class<?>, String>();
/*     */   
/*     */   static {
/* 129 */     logicalNames.put(And.class, "And");
/* 130 */     logicalNames.put(Or.class, "Or");
/* 131 */     logicalNames.put(Not.class, "Not");
/*     */   }
/*     */   
/* 135 */   private static Map<Class<?>, String> filterNames = new HashMap<Class<?>, String>();
/*     */   
/*     */   static {
/* 136 */     filterNames.putAll(scalarNames);
/* 137 */     filterNames.putAll(spatialNames);
/* 138 */     filterNames.putAll(logicalNames);
/* 140 */     filterNames.put(Id.class, "Id");
/*     */   }
/*     */   
/* 145 */   private static Map<Class<? extends Expression>, String> arithmaticNames = new HashMap<Class<? extends Expression>, String>();
/*     */   
/*     */   static {
/* 146 */     arithmaticNames.put(Add.class, "Add");
/* 147 */     arithmaticNames.put(Subtract.class, "Sub");
/* 148 */     arithmaticNames.put(Multiply.class, "Mul");
/* 149 */     arithmaticNames.put(Divide.class, "Div");
/*     */   }
/*     */   
/* 153 */   private static Map<Class<? extends Expression>, String> exprNames = new HashMap<Class<? extends Expression>, String>();
/*     */   
/*     */   static {
/* 154 */     exprNames.putAll(arithmaticNames);
/* 157 */     exprNames.put(Function.class, "Function");
/*     */   }
/*     */   
/* 160 */   private static final OperatorNameFilterVisitor operationNameVisitor = new OperatorNameFilterVisitor();
/*     */   
/* 165 */   public static Capabilities LOGICAL = new Capabilities();
/*     */   
/*     */   static {
/* 166 */     LOGICAL.addType(And.class);
/* 167 */     LOGICAL.addType(Not.class);
/* 168 */     LOGICAL.addType(Or.class);
/*     */   }
/*     */   
/* 170 */   public static Capabilities LOGICAL_OPENGIS = LOGICAL;
/*     */   
/* 176 */   public static Capabilities SIMPLE_COMPARISONS = new Capabilities();
/*     */   
/*     */   static {
/* 177 */     SIMPLE_COMPARISONS.addType(PropertyIsEqualTo.class);
/* 178 */     SIMPLE_COMPARISONS.addType(PropertyIsGreaterThan.class);
/* 179 */     SIMPLE_COMPARISONS.addType(PropertyIsGreaterThanOrEqualTo.class);
/* 180 */     SIMPLE_COMPARISONS.addType(PropertyIsLessThan.class);
/* 181 */     SIMPLE_COMPARISONS.addType(PropertyIsGreaterThanOrEqualTo.class);
/* 182 */     SIMPLE_COMPARISONS.addType(PropertyIsNotEqualTo.class);
/*     */   }
/*     */   
/* 184 */   public static Capabilities SIMPLE_COMPARISONS_OPENGIS = SIMPLE_COMPARISONS;
/*     */   
/*     */   IsSupportedFilterVisitor supportedVisitor;
/*     */   
/*     */   IsFullySupportedFilterVisitor fullySupportedVisitor;
/*     */   
/*     */   FilterCapabilitiesImpl contents;
/*     */   
/*     */   public Capabilities() {
/* 204 */     this((FilterCapabilities)new FilterCapabilitiesImpl());
/*     */   }
/*     */   
/*     */   public Capabilities(FilterCapabilities contents) {
/* 208 */     if (contents instanceof FilterCapabilitiesImpl) {
/* 209 */       this.contents = (FilterCapabilitiesImpl)contents;
/*     */     } else {
/* 212 */       this.contents = new FilterCapabilitiesImpl(contents);
/*     */     } 
/* 214 */     this.supportedVisitor = new IsSupportedFilterVisitor(contents);
/* 215 */     this.fullySupportedVisitor = new IsFullySupportedFilterVisitor(contents);
/*     */   }
/*     */   
/*     */   public FilterCapabilitiesImpl getContents() {
/* 225 */     return this.contents;
/*     */   }
/*     */   
/*     */   public void addType(Class type) {
/* 236 */     String name = toOperationName(type);
/* 237 */     if (name == null)
/*     */       return; 
/* 239 */     addName(name);
/*     */   }
/*     */   
/*     */   public void addName(String name) {
/* 266 */     if (name == null)
/*     */       return; 
/* 269 */     if (spatialNames.containsValue(name)) {
/* 270 */       SpatialOperatorsImpl operators = this.contents.getSpatialCapabilities().getSpatialOperators();
/* 271 */       if (operators.getOperator(name) == null) {
/* 272 */         SpatialOperatorImpl operator = new SpatialOperatorImpl(name);
/* 274 */         operator.getGeometryOperands().add(GeometryOperand.LineString);
/* 275 */         operator.getGeometryOperands().add(GeometryOperand.Point);
/* 276 */         operator.getGeometryOperands().add(GeometryOperand.Polygon);
/* 278 */         operators.getOperators().add(operator);
/*     */       } 
/* 281 */     } else if (scalarNames.containsValue(name)) {
/* 282 */       ComparisonOperatorsImpl operators = this.contents.getScalarCapabilities().getComparisonOperators();
/* 283 */       if (operators.getOperator(name) == null) {
/* 284 */         OperatorImpl operator = new OperatorImpl(name);
/* 285 */         operators.getOperators().add(operator);
/*     */       } 
/* 288 */     } else if (arithmaticNames.containsValue(name)) {
/* 289 */       ArithmeticOperatorsImpl operators = this.contents.getScalarCapabilities().getArithmeticOperators();
/* 290 */       operators.setSimpleArithmetic(true);
/* 292 */     } else if (logicalNames.containsValue(name)) {
/* 293 */       this.contents.getScalarCapabilities().setLogicalOperators(true);
/* 295 */     } else if ("Id".equals(name)) {
/* 296 */       this.contents.getIdCapabilities().setFID(true);
/*     */     } else {
/* 299 */       FunctionsImpl functions = this.contents.getScalarCapabilities().getArithmeticOperators().getFunctions();
/* 300 */       if (functions.getFunctionName(name) == null) {
/* 301 */         FunctionNameImpl function = new FunctionNameImpl(name, 0);
/* 302 */         functions.getFunctionNames().add(function);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addName(String name, int argumentCount) {
/* 317 */     FunctionsImpl functions = this.contents.getScalarCapabilities().getArithmeticOperators().getFunctions();
/* 318 */     if (functions.getFunctionName(name) == null) {
/* 319 */       FunctionNameImpl function = new FunctionNameImpl(name, argumentCount);
/* 320 */       functions.getFunctionNames().add(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addName(String name, String... argumentNames) {
/* 334 */     FunctionsImpl functions = this.contents.getScalarCapabilities().getArithmeticOperators().getFunctions();
/* 335 */     if (functions.getFunctionName(name) == null) {
/* 336 */       FunctionNameImpl function = new FunctionNameImpl(name, argumentNames);
/* 337 */       functions.getFunctionNames().add(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean supports(Filter filter) {
/* 348 */     if (filter == null)
/* 349 */       return false; 
/* 351 */     if (this.supportedVisitor == null)
/* 352 */       this.supportedVisitor = new IsSupportedFilterVisitor((FilterCapabilities)this.contents); 
/* 354 */     return ((Boolean)filter.accept((FilterVisitor)this.supportedVisitor, null)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean fullySupports(Filter filter) {
/* 370 */     if (filter == null)
/* 371 */       return false; 
/* 373 */     if (this.fullySupportedVisitor == null)
/* 374 */       this.fullySupportedVisitor = new IsFullySupportedFilterVisitor((FilterCapabilities)this.contents); 
/* 376 */     return ((Boolean)filter.accept((FilterVisitor)this.fullySupportedVisitor, null)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean fullySupports(Expression expression) {
/* 389 */     if (expression == null)
/* 390 */       return false; 
/* 392 */     if (this.fullySupportedVisitor == null)
/* 393 */       this.fullySupportedVisitor = new IsFullySupportedFilterVisitor((FilterCapabilities)this.contents); 
/* 395 */     return ((Boolean)expression.accept((ExpressionVisitor)this.fullySupportedVisitor, null)).booleanValue();
/*     */   }
/*     */   
/*     */   public String toOperationName(Filter filter) {
/* 406 */     if (filter == null)
/* 406 */       return null; 
/* 407 */     return (String)filter.accept((FilterVisitor)operationNameVisitor, null);
/*     */   }
/*     */   
/*     */   public String toOperationName(Class<?> filterType) {
/* 422 */     if (filterType == null)
/* 422 */       return null; 
/* 424 */     String quick = filterNames.get(filterType);
/* 425 */     if (quick != null)
/* 426 */       return quick; 
/* 430 */     for (Map.Entry<Class<?>, String> mapping : filterNames.entrySet()) {
/* 431 */       if (((Class)mapping.getKey()).isAssignableFrom(filterType))
/* 432 */         return mapping.getValue(); 
/*     */     } 
/* 505 */     return null;
/*     */   }
/*     */   
/*     */   public void addAll(Capabilities copy) {
/* 509 */     addAll((FilterCapabilities)copy.getContents());
/*     */   }
/*     */   
/*     */   public void addAll(FilterCapabilities copy) {
/* 512 */     this.contents.addAll(copy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\Capabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */