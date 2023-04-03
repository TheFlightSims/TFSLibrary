/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.filter.AttributeExpression;
/*     */ import org.geotools.filter.BetweenFilter;
/*     */ import org.geotools.filter.CompareFilter;
/*     */ import org.geotools.filter.Expression;
/*     */ import org.geotools.filter.FidFilter;
/*     */ import org.geotools.filter.Filter;
/*     */ import org.geotools.filter.FilterVisitor;
/*     */ import org.geotools.filter.Filters;
/*     */ import org.geotools.filter.FunctionExpression;
/*     */ import org.geotools.filter.GeometryFilter;
/*     */ import org.geotools.filter.LikeFilter;
/*     */ import org.geotools.filter.LiteralExpression;
/*     */ import org.geotools.filter.LogicFilter;
/*     */ import org.geotools.filter.MathExpression;
/*     */ import org.geotools.filter.NullFilter;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.BinaryLogicOperator;
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
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
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
/*     */ public class AbstractFilterVisitor implements FilterVisitor, FilterVisitor {
/* 109 */   private static Logger LOGGER = Logging.getLogger("org.geotools.filter.visitor");
/*     */   
/*     */   private ExpressionVisitor expressionVisitor;
/*     */   
/*     */   public AbstractFilterVisitor() {
/* 118 */     this(new NullExpressionVisitor());
/*     */   }
/*     */   
/*     */   public AbstractFilterVisitor(ExpressionVisitor expressionVisitor) {
/* 129 */     this.expressionVisitor = expressionVisitor;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/* 136 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/* 143 */     return data;
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   public void visit(Filter filter) {}
/*     */   
/*     */   public void visit(BetweenFilter filter) {
/* 166 */     if (filter.getLeftValue() != null)
/* 167 */       filter.getLeftValue().accept(this); 
/* 170 */     if (filter.getMiddleValue() != null)
/* 171 */       filter.getMiddleValue().accept(this); 
/* 174 */     if (filter.getRightValue() != null)
/* 175 */       filter.getRightValue().accept(this); 
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 184 */     if (filter.getLowerBoundary() != null)
/* 185 */       filter.getLowerBoundary().accept(this.expressionVisitor, data); 
/* 187 */     if (filter.getExpression() != null)
/* 188 */       filter.getExpression().accept(this.expressionVisitor, data); 
/* 190 */     if (filter.getUpperBoundary() != null)
/* 191 */       filter.getUpperBoundary().accept(this.expressionVisitor, data); 
/* 193 */     return filter;
/*     */   }
/*     */   
/*     */   public void visit(CompareFilter filter) {
/* 203 */     if (filter.getLeftValue() != null)
/* 204 */       filter.getLeftValue().accept(this); 
/* 207 */     if (filter.getRightValue() != null)
/* 208 */       filter.getRightValue().accept(this); 
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryComparisonOperator filter, Object data) {
/* 217 */     if (this.expressionVisitor != null) {
/* 218 */       if (filter.getExpression1() != null)
/* 219 */         filter.getExpression1().accept(this.expressionVisitor, data); 
/* 221 */       if (filter.getExpression2() != null)
/* 222 */         filter.getExpression2().accept(this.expressionVisitor, data); 
/*     */     } 
/* 226 */     return filter;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 234 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 241 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 248 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 255 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 262 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 269 */     return visit((BinaryComparisonOperator)filter, data);
/*     */   }
/*     */   
/*     */   public void visit(GeometryFilter filter) {
/* 277 */     if (filter.getLeftGeometry() != null)
/* 278 */       filter.getLeftGeometry().accept(this); 
/* 281 */     if (filter.getRightGeometry() != null)
/* 282 */       filter.getRightGeometry().accept(this); 
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/* 290 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   protected Object visit(BinarySpatialOperator filter, Object data) {
/* 298 */     if (this.expressionVisitor != null) {
/* 299 */       if (filter.getExpression1() != null)
/* 300 */         filter.getExpression1().accept(this.expressionVisitor, data); 
/* 302 */       if (filter.getExpression2() != null)
/* 303 */         filter.getExpression2().accept(this.expressionVisitor, data); 
/*     */     } 
/* 307 */     return filter;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 315 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 322 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 329 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 336 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 343 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 350 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 357 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 364 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 371 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 378 */     return visit((BinarySpatialOperator)filter, data);
/*     */   }
/*     */   
/*     */   public void visit(LikeFilter filter) {
/* 386 */     if (filter.getValue() != null)
/* 387 */       filter.getValue().accept(this); 
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 395 */     if (this.expressionVisitor != null && 
/* 396 */       filter.getExpression() != null)
/* 397 */       filter.getExpression().accept(this.expressionVisitor, null); 
/* 401 */     return filter;
/*     */   }
/*     */   
/*     */   public void visit(LogicFilter filter) {
/* 410 */     for (Iterator<Filter> it = filter.getFilterIterator(); it.hasNext();)
/* 411 */       Filters.accept(it.next(), this); 
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryLogicOperator filter, Object data) {
/* 419 */     if (filter.getChildren() != null)
/* 420 */       for (Iterator<Filter> i = filter.getChildren().iterator(); i.hasNext(); ) {
/* 421 */         Filter child = i.next();
/* 422 */         child.accept(this, data);
/*     */       }  
/* 426 */     return filter;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/* 433 */     return visit((BinaryLogicOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 439 */     return visit((BinaryLogicOperator)filter, data);
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 446 */     if (filter.getFilter() != null)
/* 447 */       filter.getFilter().accept(this, data); 
/* 450 */     return filter;
/*     */   }
/*     */   
/*     */   public void visit(NullFilter filter) {
/* 460 */     if (filter.getNullCheckValue() != null)
/* 461 */       filter.getNullCheckValue().accept(this); 
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 469 */     if (this.expressionVisitor != null && 
/* 470 */       filter.getExpression() != null)
/* 471 */       filter.getExpression().accept(this.expressionVisitor, data); 
/* 474 */     return filter;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 482 */     if (this.expressionVisitor != null && 
/* 483 */       filter.getExpression() != null)
/* 484 */       filter.getExpression().accept(this.expressionVisitor, extraData); 
/* 487 */     return filter;
/*     */   }
/*     */   
/*     */   public void visit(FidFilter filter) {}
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 504 */     return filter;
/*     */   }
/*     */   
/*     */   public void visit(AttributeExpression expression) {}
/*     */   
/*     */   public void visit(Expression expression) {}
/*     */   
/*     */   public void visit(LiteralExpression expression) {}
/*     */   
/*     */   public void visit(MathExpression expression) {
/* 533 */     if (expression.getLeftValue() != null)
/* 534 */       expression.getLeftValue().accept(this); 
/* 537 */     if (expression.getRightValue() != null)
/* 538 */       expression.getRightValue().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(FunctionExpression expression) {
/* 546 */     Expression[] args = expression.getArgs();
/* 548 */     for (int i = 0; i < args.length; i++) {
/* 549 */       if (args[i] != null)
/* 550 */         args[i].accept(this); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 556 */     return visit((BinaryTemporalOperator)after, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 560 */     return visit((BinaryTemporalOperator)anyInteracts, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 564 */     return visit((BinaryTemporalOperator)before, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 568 */     return visit((BinaryTemporalOperator)begins, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 572 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 576 */     return visit((BinaryTemporalOperator)during, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 580 */     return visit((BinaryTemporalOperator)endedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 584 */     return visit((BinaryTemporalOperator)ends, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 588 */     return visit((BinaryTemporalOperator)meets, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 592 */     return visit((BinaryTemporalOperator)metBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 596 */     return visit((BinaryTemporalOperator)overlappedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 600 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 604 */     return visit((BinaryTemporalOperator)equals, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 608 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 612 */     if (this.expressionVisitor != null) {
/* 613 */       if (filter.getExpression1() != null)
/* 614 */         filter.getExpression1().accept(this.expressionVisitor, data); 
/* 616 */       if (filter.getExpression2() != null)
/* 617 */         filter.getExpression2().accept(this.expressionVisitor, data); 
/*     */     } 
/* 621 */     return filter;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\AbstractFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */