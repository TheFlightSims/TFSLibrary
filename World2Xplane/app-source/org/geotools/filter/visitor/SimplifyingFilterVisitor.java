/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.filter.FilterAttributeExtractor;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
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
/*     */ import org.opengis.filter.PropertyIsNil;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ 
/*     */ public class SimplifyingFilterVisitor extends DuplicatingFilterVisitor {
/*  84 */   FilterAttributeExtractor attributeExtractor = new FilterAttributeExtractor();
/*     */   
/*  99 */   public static final FIDValidator ANY_FID_VALID = new FIDValidator() {
/*     */       public boolean isValid(String fid) {
/* 101 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   public static interface FIDValidator {
/*     */     boolean isValid(String param1String);
/*     */   }
/*     */   
/*     */   public static class RegExFIDValidator implements FIDValidator {
/*     */     private Pattern pattern;
/*     */     
/*     */     public RegExFIDValidator(String regularExpression) {
/* 120 */       this.pattern = Pattern.compile(regularExpression);
/*     */     }
/*     */     
/*     */     public boolean isValid(String fid) {
/* 124 */       return this.pattern.matcher(fid).matches();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TypeNameDotNumberFidValidator extends RegExFIDValidator {
/*     */     public TypeNameDotNumberFidValidator(String typeName) {
/* 139 */       super(typeName + "\\.\\d+");
/*     */     }
/*     */   }
/*     */   
/* 143 */   private FIDValidator fidValidator = ANY_FID_VALID;
/*     */   
/*     */   public void setFIDValidator(FIDValidator validator) {
/* 146 */     this.fidValidator = (validator == null) ? ANY_FID_VALID : validator;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/* 153 */     List<Filter> newChildren = new ArrayList<Filter>(filter.getChildren().size());
/* 154 */     for (Filter child : filter.getChildren()) {
/* 156 */       Filter cloned = (Filter)child.accept(this, extraData);
/* 161 */       if (cloned == Filter.EXCLUDE)
/* 163 */         return Filter.EXCLUDE; 
/* 167 */       if (cloned == Filter.INCLUDE)
/*     */         continue; 
/* 172 */       if (cloned instanceof And) {
/* 174 */         And and = (And)cloned;
/* 175 */         newChildren.addAll(and.getChildren());
/*     */         continue;
/*     */       } 
/* 179 */       newChildren.add(cloned);
/*     */     } 
/* 184 */     for (int i = 0; i < newChildren.size(); i++) {
/* 185 */       for (int j = i + 1; j < newChildren.size(); ) {
/* 186 */         Filter f1 = newChildren.get(i);
/* 187 */         Filter f2 = newChildren.get(j);
/* 188 */         if (f1.equals(f2)) {
/* 189 */           newChildren.remove(j);
/*     */           continue;
/*     */         } 
/* 190 */         if (dualFilters(f1, f2))
/* 191 */           return Filter.EXCLUDE; 
/* 193 */         j++;
/*     */       } 
/*     */     } 
/* 199 */     if (newChildren.size() == 0)
/* 201 */       return Filter.INCLUDE; 
/* 205 */     if (newChildren.size() == 1)
/* 207 */       return newChildren.get(0); 
/* 211 */     return getFactory(extraData).and(newChildren);
/*     */   }
/*     */   
/*     */   private boolean dualFilters(Filter f1, Filter f2) {
/* 227 */     if (f1 instanceof Not) {
/* 228 */       Not not = (Not)f1;
/* 229 */       return f2.equals(not.getFilter());
/*     */     } 
/* 230 */     if (f2 instanceof Not) {
/* 231 */       Not not = (Not)f2;
/* 232 */       return f1.equals(not.getFilter());
/*     */     } 
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/* 242 */     List<Filter> newChildren = new ArrayList<Filter>(filter.getChildren().size());
/* 243 */     for (Filter child : filter.getChildren()) {
/* 245 */       Filter cloned = (Filter)child.accept(this, extraData);
/* 250 */       if (cloned == Filter.INCLUDE)
/* 252 */         return Filter.INCLUDE; 
/* 256 */       if (cloned == Filter.EXCLUDE)
/*     */         continue; 
/* 261 */       if (cloned instanceof Or) {
/* 263 */         Or or = (Or)cloned;
/* 264 */         newChildren.addAll(or.getChildren());
/*     */         continue;
/*     */       } 
/* 268 */       newChildren.add(cloned);
/*     */     } 
/* 273 */     for (int i = 0; i < newChildren.size(); i++) {
/* 274 */       for (int j = i + 1; j < newChildren.size(); ) {
/* 275 */         Filter f1 = newChildren.get(i);
/* 276 */         Filter f2 = newChildren.get(j);
/* 277 */         if (f1.equals(f2)) {
/* 278 */           newChildren.remove(j);
/*     */           continue;
/*     */         } 
/* 279 */         if (dualFilters(f1, f2))
/* 280 */           return Filter.INCLUDE; 
/* 282 */         j++;
/*     */       } 
/*     */     } 
/* 288 */     if (newChildren.size() == 0)
/* 290 */       return Filter.EXCLUDE; 
/* 294 */     if (newChildren.size() == 1)
/* 296 */       return newChildren.get(0); 
/* 300 */     return getFactory(extraData).or(newChildren);
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/*     */     Id id;
/* 313 */     if (filter.getIDs().size() == 0)
/* 314 */       return Filter.EXCLUDE; 
/* 317 */     Set<Identifier> validFids = new HashSet<Identifier>();
/* 319 */     for (Identifier identifier : filter.getIdentifiers()) {
/* 320 */       if (identifier instanceof org.opengis.filter.identity.FeatureId || identifier instanceof org.opengis.filter.identity.GmlObjectId)
/* 324 */         if (this.fidValidator.isValid((String)identifier.getID()))
/* 325 */           validFids.add(identifier);  
/*     */     } 
/* 331 */     if (validFids.size() == 0) {
/* 332 */       ExcludeFilter excludeFilter = Filter.EXCLUDE;
/*     */     } else {
/* 334 */       id = getFactory(extraData).id(validFids);
/*     */     } 
/* 336 */     return id;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/* 340 */     if (filter.getFilter() instanceof Not) {
/* 342 */       Not inner = (Not)filter.getFilter();
/* 343 */       return inner.getFilter().accept(this, extraData);
/*     */     } 
/* 345 */     return super.visit(filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Function function, Object extraData) {
/* 351 */     if (function instanceof org.opengis.filter.expression.VolatileFunction)
/* 352 */       return super.visit(function, extraData); 
/* 356 */     if (this.attributeExtractor == null) {
/* 357 */       this.attributeExtractor = new FilterAttributeExtractor();
/*     */     } else {
/* 359 */       this.attributeExtractor.clear();
/*     */     } 
/* 361 */     function.accept((ExpressionVisitor)this.attributeExtractor, null);
/* 364 */     if (this.attributeExtractor.isConstantExpression()) {
/* 365 */       Object result = function.evaluate(null);
/* 366 */       return this.ff.literal(result);
/*     */     } 
/* 368 */     return super.visit(function, extraData);
/*     */   }
/*     */   
/*     */   public static Filter simplify(Filter filter) {
/* 379 */     if (filter == Filter.INCLUDE || filter == Filter.EXCLUDE || filter == null)
/* 380 */       return filter; 
/* 383 */     SimplifyingFilterVisitor visitor = new SimplifyingFilterVisitor();
/* 384 */     return (Filter)filter.accept(visitor, null);
/*     */   }
/*     */   
/*     */   private boolean isConstant(Expression ex) {
/* 389 */     if (ex instanceof org.opengis.filter.expression.Literal)
/* 390 */       return true; 
/* 391 */     if (ex instanceof org.opengis.filter.expression.NilExpression)
/* 392 */       return true; 
/* 393 */     if (ex instanceof org.opengis.filter.expression.PropertyName)
/* 394 */       return false; 
/* 397 */     this.attributeExtractor.clear();
/* 398 */     ex.accept((ExpressionVisitor)this.attributeExtractor, null);
/* 399 */     return this.attributeExtractor.isConstantExpression();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 403 */     PropertyIsBetween clone = (PropertyIsBetween)super.visit(filter, extraData);
/* 404 */     if (isConstant(clone.getExpression()) && isConstant(clone.getLowerBoundary()) && isConstant(clone.getUpperBoundary()))
/* 405 */       return staticFilterEvaluate((Filter)clone); 
/* 407 */     return clone;
/*     */   }
/*     */   
/*     */   private Object staticFilterEvaluate(Filter filter) {
/* 412 */     if (filter.evaluate(null))
/* 413 */       return Filter.INCLUDE; 
/* 415 */     return Filter.EXCLUDE;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 420 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   private Object simplifyBinaryComparisonOperator(BinaryComparisonOperator clone) {
/* 424 */     if (isConstant(clone.getExpression1()) && isConstant(clone.getExpression2()))
/* 425 */       return staticFilterEvaluate((Filter)clone); 
/* 427 */     return clone;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 432 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 436 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 440 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 444 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 448 */     return simplifyBinaryComparisonOperator((BinaryComparisonOperator)super.visit(filter, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 453 */     PropertyIsLike clone = (PropertyIsLike)super.visit(filter, extraData);
/* 454 */     if (isConstant(clone.getExpression()))
/* 455 */       return staticFilterEvaluate((Filter)clone); 
/* 457 */     return clone;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 463 */     PropertyIsNil clone = (PropertyIsNil)super.visit(filter, extraData);
/* 464 */     if (isConstant(clone.getExpression()))
/* 465 */       return staticFilterEvaluate((Filter)clone); 
/* 467 */     return clone;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 473 */     PropertyIsNull clone = (PropertyIsNull)super.visit(filter, extraData);
/* 474 */     if (isConstant(clone.getExpression()))
/* 475 */       return staticFilterEvaluate((Filter)clone); 
/* 477 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\SimplifyingFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */