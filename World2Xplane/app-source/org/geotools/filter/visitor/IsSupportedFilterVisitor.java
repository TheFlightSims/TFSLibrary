/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
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
/*     */ public class IsSupportedFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   private FilterCapabilities capabilities;
/*     */   
/*     */   public IsSupportedFilterVisitor(FilterCapabilities capabilities) {
/* 109 */     this.capabilities = capabilities;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object extraData) {
/* 114 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object extraData) {
/* 118 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/* 122 */     return Boolean.valueOf((this.capabilities.getScalarCapabilities() != null && this.capabilities.getScalarCapabilities().hasLogicalOperators()));
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/* 127 */     return Boolean.valueOf((this.capabilities.getIdCapabilities() != null && (this.capabilities.getIdCapabilities().hasFID() || this.capabilities.getIdCapabilities().hasEID())));
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/* 133 */     return Boolean.valueOf((this.capabilities.getScalarCapabilities() != null && this.capabilities.getScalarCapabilities().hasLogicalOperators()));
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/* 138 */     return Boolean.valueOf((this.capabilities.getScalarCapabilities() != null && this.capabilities.getScalarCapabilities().hasLogicalOperators()));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 143 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 144 */     if (scalar == null)
/* 144 */       return Boolean.valueOf(false); 
/* 146 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 147 */     if (operators == null)
/* 147 */       return Boolean.valueOf(false); 
/* 149 */     return Boolean.valueOf((operators.getOperator("Between") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 153 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 154 */     if (scalar == null)
/* 154 */       return Boolean.valueOf(false); 
/* 156 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 157 */     if (operators == null)
/* 157 */       return Boolean.valueOf(false); 
/* 159 */     return Boolean.valueOf((operators.getOperator("EqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 163 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 164 */     if (scalar == null)
/* 164 */       return Boolean.valueOf(false); 
/* 166 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 167 */     if (operators == null)
/* 167 */       return Boolean.valueOf(false); 
/* 169 */     return Boolean.valueOf((operators.getOperator("NotEqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 173 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 174 */     if (scalar == null)
/* 174 */       return Boolean.valueOf(false); 
/* 176 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 177 */     if (operators == null)
/* 177 */       return Boolean.valueOf(false); 
/* 179 */     return Boolean.valueOf((operators.getOperator("GreaterThan") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 183 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 184 */     if (scalar == null)
/* 184 */       return Boolean.valueOf(false); 
/* 186 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 187 */     if (operators == null)
/* 187 */       return Boolean.valueOf(false); 
/* 189 */     return Boolean.valueOf((operators.getOperator("GreaterThanOrEqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 193 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 194 */     if (scalar == null)
/* 194 */       return Boolean.valueOf(false); 
/* 196 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 197 */     if (operators == null)
/* 197 */       return Boolean.valueOf(false); 
/* 199 */     return Boolean.valueOf((operators.getOperator("LessThan") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 203 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 204 */     if (scalar == null)
/* 204 */       return Boolean.valueOf(false); 
/* 206 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 207 */     if (operators == null)
/* 207 */       return Boolean.valueOf(false); 
/* 209 */     return Boolean.valueOf((operators.getOperator("LessThanOrEqualTo") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 213 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 214 */     if (scalar == null)
/* 214 */       return Boolean.valueOf(false); 
/* 216 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 217 */     if (operators == null)
/* 217 */       return Boolean.valueOf(false); 
/* 219 */     return Boolean.valueOf((operators.getOperator("Like") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 223 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 224 */     if (scalar == null)
/* 224 */       return Boolean.valueOf(false); 
/* 226 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 227 */     if (operators == null)
/* 227 */       return Boolean.valueOf(false); 
/* 229 */     return Boolean.valueOf((operators.getOperator("NullCheck") != null));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 233 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 234 */     if (scalar == null)
/* 234 */       return Boolean.valueOf(false); 
/* 236 */     ComparisonOperators operators = scalar.getComparisonOperators();
/* 237 */     if (operators == null)
/* 237 */       return Boolean.valueOf(false); 
/* 239 */     return Boolean.valueOf((operators.getOperator("Nil") != null));
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 243 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 244 */     if (spatial == null)
/* 244 */       return Boolean.valueOf(false); 
/* 246 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 247 */     if (operators == null)
/* 247 */       return Boolean.valueOf(false); 
/* 249 */     return Boolean.valueOf((operators.getOperator("BBOX") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 253 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 254 */     if (spatial == null)
/* 254 */       return Boolean.valueOf(false); 
/* 256 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 257 */     if (operators == null)
/* 257 */       return Boolean.valueOf(false); 
/* 259 */     return Boolean.valueOf((operators.getOperator("Beyond") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 263 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 264 */     if (spatial == null)
/* 264 */       return Boolean.valueOf(false); 
/* 266 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 267 */     if (operators == null)
/* 267 */       return Boolean.valueOf(false); 
/* 269 */     return Boolean.valueOf((operators.getOperator("Contains") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 273 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 274 */     if (spatial == null)
/* 274 */       return Boolean.valueOf(false); 
/* 276 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 277 */     if (operators == null)
/* 277 */       return Boolean.valueOf(false); 
/* 279 */     return Boolean.valueOf((operators.getOperator("Crosses") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 283 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 284 */     if (spatial == null)
/* 284 */       return Boolean.valueOf(false); 
/* 286 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 287 */     if (operators == null)
/* 287 */       return Boolean.valueOf(false); 
/* 289 */     return Boolean.valueOf((operators.getOperator("Disjoint") != null));
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 293 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 294 */     if (spatial == null)
/* 294 */       return Boolean.valueOf(false); 
/* 296 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 297 */     if (operators == null)
/* 297 */       return Boolean.valueOf(false); 
/* 299 */     return Boolean.valueOf((operators.getOperator("DWithin") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 303 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 304 */     if (spatial == null)
/* 304 */       return Boolean.valueOf(false); 
/* 306 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 307 */     if (operators == null)
/* 307 */       return Boolean.valueOf(false); 
/* 309 */     return Boolean.valueOf((operators.getOperator("Equals") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 313 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 314 */     if (spatial == null)
/* 314 */       return Boolean.valueOf(false); 
/* 316 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 317 */     if (operators == null)
/* 317 */       return Boolean.valueOf(false); 
/* 319 */     return Boolean.valueOf((operators.getOperator("Intersects") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 323 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 324 */     if (spatial == null)
/* 324 */       return Boolean.valueOf(false); 
/* 326 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 327 */     if (operators == null)
/* 327 */       return Boolean.valueOf(false); 
/* 329 */     return Boolean.valueOf((operators.getOperator("Overlaps") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 333 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 334 */     if (spatial == null)
/* 334 */       return Boolean.valueOf(false); 
/* 336 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 337 */     if (operators == null)
/* 337 */       return Boolean.valueOf(false); 
/* 339 */     return Boolean.valueOf((operators.getOperator("Touches") != null));
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 343 */     SpatialCapabilities spatial = this.capabilities.getSpatialCapabilities();
/* 344 */     if (spatial == null)
/* 344 */       return Boolean.valueOf(false); 
/* 346 */     SpatialOperators operators = spatial.getSpatialOperators();
/* 347 */     if (operators == null)
/* 347 */       return Boolean.valueOf(false); 
/* 349 */     return Boolean.valueOf((operators.getOperator("Within") != null));
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object extraData) {
/* 353 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object extraData) {
/* 361 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object extraData) {
/* 365 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 366 */     if (scalar == null)
/* 366 */       return Boolean.valueOf(false); 
/* 368 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 369 */     if (operators == null)
/* 369 */       return Boolean.valueOf(false); 
/* 371 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object extraData) {
/* 375 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 376 */     if (scalar == null)
/* 376 */       return Boolean.valueOf(false); 
/* 378 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 379 */     if (operators == null)
/* 379 */       return Boolean.valueOf(false); 
/* 381 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(Function function, Object extraData) {
/* 385 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 386 */     if (scalar == null)
/* 386 */       return Boolean.valueOf(false); 
/* 388 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 389 */     if (operators == null)
/* 389 */       return Boolean.valueOf(false); 
/* 391 */     Functions functions = operators.getFunctions();
/* 392 */     if (functions == null)
/* 392 */       return Boolean.valueOf(false); 
/* 395 */     FunctionName found = functions.getFunctionName(function.getName());
/* 397 */     return Boolean.valueOf((found != null));
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object extraData) {
/* 401 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object extraData) {
/* 405 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 406 */     if (scalar == null)
/* 406 */       return Boolean.valueOf(false); 
/* 408 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 409 */     if (operators == null)
/* 409 */       return Boolean.valueOf(false); 
/* 411 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object extraData) {
/* 419 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object extraData) {
/* 423 */     ScalarCapabilities scalar = this.capabilities.getScalarCapabilities();
/* 424 */     if (scalar == null)
/* 424 */       return Boolean.valueOf(false); 
/* 426 */     ArithmeticOperators operators = scalar.getArithmeticOperators();
/* 427 */     if (operators == null)
/* 427 */       return Boolean.valueOf(false); 
/* 429 */     return Boolean.valueOf(operators.hasSimpleArithmetic());
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 433 */     return visit((BinaryTemporalOperator)after, "After");
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 437 */     return visit((BinaryTemporalOperator)anyInteracts, "AnyInteracts");
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 441 */     return visit((BinaryTemporalOperator)before, "Before");
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 445 */     return visit((BinaryTemporalOperator)begins, "Begins");
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 449 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 453 */     return visit((BinaryTemporalOperator)during, "During");
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 457 */     return visit((BinaryTemporalOperator)endedBy, "EndedBy");
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 461 */     return visit((BinaryTemporalOperator)ends, "Ends");
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 465 */     return visit((BinaryTemporalOperator)meets, "Meets");
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 469 */     return visit((BinaryTemporalOperator)metBy, "MetBy");
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 473 */     return visit((BinaryTemporalOperator)overlappedBy, "OverlappedBy");
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 477 */     return visit((BinaryTemporalOperator)contains, "TContains");
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 481 */     return visit((BinaryTemporalOperator)equals, "TEquals");
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 485 */     return visit((BinaryTemporalOperator)contains, "TOverlaps");
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 489 */     TemporalCapabilities temporal = this.capabilities.getTemporalCapabilities();
/* 490 */     if (temporal == null)
/* 490 */       return Boolean.valueOf(false); 
/* 492 */     TemporalOperators operators = temporal.getTemporalOperators();
/* 493 */     if (operators == null)
/* 493 */       return Boolean.valueOf(false); 
/* 495 */     return Boolean.valueOf((operators.getOperator((String)data) != null));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\IsSupportedFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */