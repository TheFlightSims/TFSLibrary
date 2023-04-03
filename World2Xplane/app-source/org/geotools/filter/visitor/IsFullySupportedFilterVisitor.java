/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNil;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.capability.ArithmeticOperators;
/*     */ import org.opengis.filter.capability.ComparisonOperators;
/*     */ import org.opengis.filter.capability.FilterCapabilities;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.capability.Functions;
/*     */ import org.opengis.filter.capability.IdCapabilities;
/*     */ import org.opengis.filter.capability.ScalarCapabilities;
/*     */ import org.opengis.filter.capability.SpatialCapabilities;
/*     */ import org.opengis.filter.capability.SpatialOperators;
/*     */ import org.opengis.filter.capability.TemporalCapabilities;
/*     */ import org.opengis.filter.capability.TemporalOperators;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.identity.Identifier;
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
/*     */ import org.opengis.filter.temporal.After;
/*     */ import org.opengis.filter.temporal.AnyInteracts;
/*     */ import org.opengis.filter.temporal.Before;
/*     */ import org.opengis.filter.temporal.Begins;
/*     */ import org.opengis.filter.temporal.BegunBy;
/*     */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*     */ import org.opengis.filter.temporal.During;
/*     */ import org.opengis.filter.temporal.EndedBy;
/*     */ import org.opengis.filter.temporal.Ends;
/*     */ import org.opengis.filter.temporal.Meets;
/*     */ import org.opengis.filter.temporal.MetBy;
/*     */ import org.opengis.filter.temporal.OverlappedBy;
/*     */ import org.opengis.filter.temporal.TContains;
/*     */ import org.opengis.filter.temporal.TEquals;
/*     */ import org.opengis.filter.temporal.TOverlaps;
/*     */ 
/*     */ public class IsFullySupportedFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   private FilterCapabilities capabilities;
/*     */   
/*     */   public IsFullySupportedFilterVisitor(FilterCapabilities capabilities) {
/* 115 */     this.capabilities = capabilities;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object extraData) {
/* 120 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object extraData) {
/* 124 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/* 128 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 129 */     if (scalar == null || !scalar.hasLogicalOperators())
/* 130 */       return Boolean.valueOf(false); 
/* 132 */     List<Filter> children = filter.getChildren();
/* 133 */     if (children == null)
/* 133 */       return Boolean.valueOf(false); 
/* 134 */     for (Filter child : children) {
/* 135 */       boolean yes = ((Boolean)child.accept(this, null)).booleanValue();
/* 136 */       if (!yes)
/* 136 */         return Boolean.valueOf(false); 
/*     */     } 
/* 138 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/* 142 */     IdCapabilities idCapabilities = this.capabilities.getIdCapabilities();
/* 143 */     if (idCapabilities == null)
/* 143 */       return Boolean.valueOf(false); 
/* 145 */     Set<Identifier> identifiers = filter.getIdentifiers();
/* 146 */     if (identifiers == null)
/* 146 */       return null; 
/* 147 */     for (Identifier identifier : identifiers) {
/* 148 */       if (identifier instanceof org.opengis.filter.identity.FeatureId && this.capabilities.getIdCapabilities().hasFID())
/*     */         continue; 
/* 152 */       if (identifier instanceof org.opengis.filter.identity.ObjectId && this.capabilities.getIdCapabilities().hasEID())
/*     */         continue; 
/* 157 */       return Boolean.valueOf(false);
/*     */     } 
/* 160 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/* 164 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 165 */     if (scalar == null || !scalar.hasLogicalOperators())
/* 166 */       return Boolean.valueOf(false); 
/* 168 */     return filter.getFilter().accept(this, null);
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/* 172 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 173 */     if (scalar == null || !scalar.hasLogicalOperators())
/* 174 */       return Boolean.valueOf(false); 
/* 176 */     List<Filter> children = filter.getChildren();
/* 177 */     if (children == null)
/* 177 */       return Boolean.valueOf(false); 
/* 178 */     for (Filter child : children) {
/* 179 */       boolean yes = ((Boolean)child.accept(this, null)).booleanValue();
/* 180 */       if (!yes)
/* 180 */         return Boolean.valueOf(false); 
/*     */     } 
/* 182 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 186 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 187 */     if (scalar == null)
/* 187 */       return Boolean.valueOf(false); 
/* 189 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 190 */     if (operators == null)
/* 190 */       return Boolean.valueOf(false); 
/* 192 */     if (operators.getOperator("Between") == null)
/* 192 */       return Boolean.valueOf(false); 
/* 194 */     return Boolean.valueOf((((Boolean)filter.getLowerBoundary().accept(this, null)).booleanValue() && ((Boolean)filter.getExpression().accept(this, null)).booleanValue() && ((Boolean)filter.getUpperBoundary().accept(this, null)).booleanValue()));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 200 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 201 */     if (scalar == null)
/* 201 */       return Boolean.valueOf(false); 
/* 203 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 204 */     if (operators == null)
/* 204 */       return Boolean.valueOf(false); 
/* 206 */     if (operators.getOperator("EqualTo") == null)
/* 206 */       return Boolean.valueOf(false); 
/* 208 */     return Boolean.valueOf((((Boolean)filter.getExpression1().accept(this, null)).booleanValue() && ((Boolean)filter.getExpression2().accept(this, null)).booleanValue()));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 213 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 214 */     if (scalar == null)
/* 214 */       return Boolean.valueOf(false); 
/* 216 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 217 */     if (operators == null)
/* 217 */       return Boolean.valueOf(false); 
/* 219 */     if (operators.getOperator("NotEqualTo") == null)
/* 219 */       return Boolean.valueOf(false); 
/* 221 */     return Boolean.valueOf((((Boolean)filter.getExpression1().accept(this, null)).booleanValue() && ((Boolean)filter.getExpression2().accept(this, null)).booleanValue()));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 226 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 227 */     if (scalar == null)
/* 227 */       return Boolean.valueOf(false); 
/* 229 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 230 */     if (operators == null)
/* 230 */       return Boolean.valueOf(false); 
/* 232 */     if (operators.getOperator("GreaterThan") == null)
/* 232 */       return Boolean.valueOf(false); 
/* 234 */     return Boolean.valueOf((((Boolean)filter.getExpression1().accept(this, null)).booleanValue() && ((Boolean)filter.getExpression2().accept(this, null)).booleanValue()));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 239 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 240 */     if (scalar == null)
/* 240 */       return Boolean.valueOf(false); 
/* 242 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 243 */     if (operators == null)
/* 243 */       return Boolean.valueOf(false); 
/* 245 */     return Boolean.valueOf((operators.getOperator("GreaterThanOrEqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 249 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 250 */     if (scalar == null)
/* 250 */       return Boolean.valueOf(false); 
/* 252 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 253 */     if (operators == null)
/* 253 */       return Boolean.valueOf(false); 
/* 255 */     return Boolean.valueOf((operators.getOperator("LessThan") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 259 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 260 */     if (scalar == null)
/* 260 */       return Boolean.valueOf(false); 
/* 262 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 263 */     if (operators == null)
/* 263 */       return Boolean.valueOf(false); 
/* 265 */     return Boolean.valueOf((operators.getOperator("LessThanOrEqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 269 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 270 */     if (scalar == null)
/* 270 */       return Boolean.valueOf(false); 
/* 272 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 273 */     if (operators == null)
/* 273 */       return Boolean.valueOf(false); 
/* 275 */     return Boolean.valueOf((operators.getOperator("Like") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 279 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 280 */     if (scalar == null)
/* 280 */       return Boolean.valueOf(false); 
/* 282 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 283 */     if (operators == null)
/* 283 */       return Boolean.valueOf(false); 
/* 285 */     return Boolean.valueOf((operators.getOperator("NullCheck") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 289 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 290 */     if (scalar == null)
/* 290 */       return Boolean.valueOf(false); 
/* 292 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 293 */     if (operators == null)
/* 293 */       return Boolean.valueOf(false); 
/* 295 */     return Boolean.valueOf((operators.getOperator("Nil") != null));
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 299 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 300 */     if (spatial == null)
/* 300 */       return Boolean.valueOf(false); 
/* 302 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 303 */     if (operators == null)
/* 303 */       return Boolean.valueOf(false); 
/* 305 */     return Boolean.valueOf((operators.getOperator("BBOX") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 309 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 310 */     if (spatial == null)
/* 310 */       return Boolean.valueOf(false); 
/* 312 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 313 */     if (operators == null)
/* 313 */       return Boolean.valueOf(false); 
/* 315 */     return Boolean.valueOf((operators.getOperator("Beyond") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 319 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 320 */     if (spatial == null)
/* 320 */       return Boolean.valueOf(false); 
/* 322 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 323 */     if (operators == null)
/* 323 */       return Boolean.valueOf(false); 
/* 325 */     return Boolean.valueOf((operators.getOperator("Contains") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 329 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 330 */     if (spatial == null)
/* 330 */       return Boolean.valueOf(false); 
/* 332 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 333 */     if (operators == null)
/* 333 */       return Boolean.valueOf(false); 
/* 335 */     return Boolean.valueOf((operators.getOperator("Crosses") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 339 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 340 */     if (spatial == null)
/* 340 */       return Boolean.valueOf(false); 
/* 342 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 343 */     if (operators == null)
/* 343 */       return Boolean.valueOf(false); 
/* 345 */     return Boolean.valueOf((operators.getOperator("Disjoint") != null));
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 349 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 350 */     if (spatial == null)
/* 350 */       return Boolean.valueOf(false); 
/* 352 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 353 */     if (operators == null)
/* 353 */       return Boolean.valueOf(false); 
/* 355 */     return Boolean.valueOf((operators.getOperator("DWithin") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 359 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 360 */     if (spatial == null)
/* 360 */       return Boolean.valueOf(false); 
/* 362 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 363 */     if (operators == null)
/* 363 */       return Boolean.valueOf(false); 
/* 365 */     return Boolean.valueOf((operators.getOperator("Equals") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 369 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 370 */     if (spatial == null)
/* 370 */       return Boolean.valueOf(false); 
/* 372 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 373 */     if (operators == null)
/* 373 */       return Boolean.valueOf(false); 
/* 375 */     return Boolean.valueOf((operators.getOperator("Intersects") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 379 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 380 */     if (spatial == null)
/* 380 */       return Boolean.valueOf(false); 
/* 382 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 383 */     if (operators == null)
/* 383 */       return Boolean.valueOf(false); 
/* 385 */     return Boolean.valueOf((operators.getOperator("Overlaps") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 389 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 390 */     if (spatial == null)
/* 390 */       return Boolean.valueOf(false); 
/* 392 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 393 */     if (operators == null)
/* 393 */       return Boolean.valueOf(false); 
/* 395 */     return Boolean.valueOf((operators.getOperator("Touches") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 399 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 400 */     if (spatial == null)
/* 400 */       return Boolean.valueOf(false); 
/* 402 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 403 */     if (operators == null)
/* 403 */       return Boolean.valueOf(false); 
/* 405 */     return Boolean.valueOf((operators.getOperator("Within") != null));
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object extraData) {
/* 409 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object extraData) {
/* 416 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object extraData) {
/* 420 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 421 */     if (scalar == null)
/* 421 */       return Boolean.valueOf(false); 
/* 423 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 424 */     if (operators == null)
/* 424 */       return Boolean.valueOf(false); 
/* 426 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object extraData) {
/* 430 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 431 */     if (scalar == null)
/* 431 */       return Boolean.valueOf(false); 
/* 433 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 434 */     if (operators == null)
/* 434 */       return Boolean.valueOf(false); 
/* 436 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(Function function, Object extraData) {
/* 440 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 441 */     if (scalar == null)
/* 441 */       return Boolean.valueOf(false); 
/* 443 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 444 */     if (operators == null)
/* 444 */       return Boolean.valueOf(false); 
/* 446 */     Functions functions = operators.getFunctions();
/* 447 */     if (functions == null)
/* 447 */       return Boolean.valueOf(false); 
/* 450 */     FunctionName found = functions.getFunctionName(function.getName());
/* 452 */     return Boolean.valueOf((found != null));
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object extraData) {
/* 456 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object extraData) {
/* 460 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 461 */     if (scalar == null)
/* 461 */       return Boolean.valueOf(false); 
/* 463 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 464 */     if (operators == null)
/* 464 */       return Boolean.valueOf(false); 
/* 466 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object extraData) {
/* 474 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object extraData) {
/* 478 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 479 */     if (scalar == null)
/* 479 */       return Boolean.valueOf(false); 
/* 481 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 482 */     if (operators == null)
/* 482 */       return Boolean.valueOf(false); 
/* 484 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 488 */     return visit((BinaryTemporalOperator)after, "After");
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 492 */     return visit((BinaryTemporalOperator)anyInteracts, "AnyInteracts");
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 496 */     return visit((BinaryTemporalOperator)before, "Before");
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 500 */     return visit((BinaryTemporalOperator)begins, "Begins");
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 504 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 508 */     return visit((BinaryTemporalOperator)during, "During");
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 512 */     return visit((BinaryTemporalOperator)endedBy, "EndedBy");
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 516 */     return visit((BinaryTemporalOperator)ends, "Ends");
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 520 */     return visit((BinaryTemporalOperator)meets, "Meets");
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 524 */     return visit((BinaryTemporalOperator)metBy, "MetBy");
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 528 */     return visit((BinaryTemporalOperator)overlappedBy, "OverlappedBy");
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 532 */     return visit((BinaryTemporalOperator)contains, "TContains");
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 536 */     return visit((BinaryTemporalOperator)equals, "TEquals");
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 540 */     return visit((BinaryTemporalOperator)contains, "TOverlaps");
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 544 */     TemporalCapabilities temporal = this.capabilities.getTemporalCapabilities();
/* 545 */     if (temporal == null)
/* 545 */       return Boolean.valueOf(false); 
/* 547 */     TemporalOperators operators = temporal.getTemporalOperators();
/* 548 */     if (operators == null)
/* 548 */       return Boolean.valueOf(false); 
/* 550 */     return Boolean.valueOf((operators.getOperator((String)data) != null));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\IsFullySupportedFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */