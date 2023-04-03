/*      */ package org.geotools.filter.visitor;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.Stack;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.filter.FilterCapabilities;
/*      */ import org.geotools.filter.IllegalFilterException;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.filter.And;
/*      */ import org.opengis.filter.BinaryComparisonOperator;
/*      */ import org.opengis.filter.ExcludeFilter;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.FilterVisitor;
/*      */ import org.opengis.filter.Id;
/*      */ import org.opengis.filter.IncludeFilter;
/*      */ import org.opengis.filter.Not;
/*      */ import org.opengis.filter.Or;
/*      */ import org.opengis.filter.PropertyIsBetween;
/*      */ import org.opengis.filter.PropertyIsEqualTo;
/*      */ import org.opengis.filter.PropertyIsGreaterThan;
/*      */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*      */ import org.opengis.filter.PropertyIsLessThan;
/*      */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*      */ import org.opengis.filter.PropertyIsLike;
/*      */ import org.opengis.filter.PropertyIsNil;
/*      */ import org.opengis.filter.PropertyIsNotEqualTo;
/*      */ import org.opengis.filter.PropertyIsNull;
/*      */ import org.opengis.filter.expression.Add;
/*      */ import org.opengis.filter.expression.BinaryExpression;
/*      */ import org.opengis.filter.expression.Divide;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.ExpressionVisitor;
/*      */ import org.opengis.filter.expression.Function;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.Multiply;
/*      */ import org.opengis.filter.expression.NilExpression;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.filter.expression.Subtract;
/*      */ import org.opengis.filter.spatial.BBOX;
/*      */ import org.opengis.filter.spatial.BBOX3D;
/*      */ import org.opengis.filter.spatial.Beyond;
/*      */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*      */ import org.opengis.filter.spatial.Contains;
/*      */ import org.opengis.filter.spatial.Crosses;
/*      */ import org.opengis.filter.spatial.DWithin;
/*      */ import org.opengis.filter.spatial.Disjoint;
/*      */ import org.opengis.filter.spatial.Equals;
/*      */ import org.opengis.filter.spatial.Intersects;
/*      */ import org.opengis.filter.spatial.Overlaps;
/*      */ import org.opengis.filter.spatial.Touches;
/*      */ import org.opengis.filter.spatial.Within;
/*      */ import org.opengis.filter.temporal.After;
/*      */ import org.opengis.filter.temporal.AnyInteracts;
/*      */ import org.opengis.filter.temporal.Before;
/*      */ import org.opengis.filter.temporal.Begins;
/*      */ import org.opengis.filter.temporal.BegunBy;
/*      */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*      */ import org.opengis.filter.temporal.During;
/*      */ import org.opengis.filter.temporal.EndedBy;
/*      */ import org.opengis.filter.temporal.Ends;
/*      */ import org.opengis.filter.temporal.Meets;
/*      */ import org.opengis.filter.temporal.MetBy;
/*      */ import org.opengis.filter.temporal.OverlappedBy;
/*      */ import org.opengis.filter.temporal.TContains;
/*      */ import org.opengis.filter.temporal.TEquals;
/*      */ import org.opengis.filter.temporal.TOverlaps;
/*      */ 
/*      */ public class PostPreProcessFilterSplittingVisitor implements FilterVisitor, ExpressionVisitor {
/*  143 */   private static final Logger logger = Logging.getLogger("org.geotools.filter");
/*      */   
/*  149 */   protected Stack postStack = new Stack();
/*      */   
/*  155 */   protected Stack preStack = new Stack();
/*      */   
/*  161 */   private Set changedStack = new HashSet();
/*      */   
/*  166 */   protected FilterCapabilities fcs = null;
/*      */   
/*  167 */   private SimpleFeatureType parent = null;
/*      */   
/*  168 */   private Filter original = null;
/*      */   
/*      */   private ClientTransactionAccessor transactionAccessor;
/*      */   
/*  176 */   private FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*      */   
/*      */   private PostPreProcessFilterSplittingVisitor() {}
/*      */   
/*      */   public PostPreProcessFilterSplittingVisitor(FilterCapabilities fcs, SimpleFeatureType parent, ClientTransactionAccessor transactionAccessor) {
/*  190 */     this.fcs = fcs;
/*  191 */     this.parent = parent;
/*  192 */     this.transactionAccessor = transactionAccessor;
/*      */   }
/*      */   
/*      */   public Filter getFilterPost() {
/*  201 */     if (!this.changedStack.isEmpty())
/*  204 */       return this.original; 
/*  206 */     if (this.postStack.size() > 1)
/*  207 */       logger.warning("Too many post stack items after run: " + this.postStack.size()); 
/*  212 */     Filter f = this.postStack.isEmpty() ? (Filter)Filter.INCLUDE : this.postStack.peek();
/*  213 */     return f;
/*      */   }
/*      */   
/*      */   public Filter getFilterPre() {
/*      */     And and;
/*      */     Or or;
/*  222 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*  223 */     if (this.preStack.isEmpty())
/*  224 */       return (Filter)Filter.INCLUDE; 
/*  227 */     if (this.preStack.size() > 1)
/*  228 */       logger.warning("Too many pre stack items after run: " + this.preStack.size()); 
/*  234 */     Filter f = this.preStack.isEmpty() ? (Filter)Filter.INCLUDE : this.preStack.peek();
/*  236 */     if (this.transactionAccessor != null && 
/*  237 */       f != null && f != Filter.EXCLUDE) {
/*  238 */       Filter deleteFilter = this.transactionAccessor.getDeleteFilter();
/*  239 */       if (deleteFilter != null) {
/*      */         ExcludeFilter excludeFilter;
/*  240 */         if (deleteFilter == Filter.EXCLUDE) {
/*  241 */           excludeFilter = Filter.EXCLUDE;
/*      */         } else {
/*  243 */           and = ff.and((Filter)excludeFilter, (Filter)ff.not(deleteFilter));
/*      */         } 
/*      */       } 
/*      */     } 
/*  248 */     if (this.changedStack.isEmpty())
/*  249 */       return (Filter)and; 
/*  251 */     Iterator<Filter> iter = this.changedStack.iterator();
/*  252 */     Filter updateFilter = iter.next();
/*  253 */     while (iter.hasNext()) {
/*  254 */       Filter next = iter.next();
/*  255 */       if (next == Filter.INCLUDE) {
/*  256 */         updateFilter = next;
/*      */         break;
/*      */       } 
/*  259 */       or = ff.or(updateFilter, next);
/*      */     } 
/*  262 */     if (or == Filter.INCLUDE || and == Filter.INCLUDE)
/*  263 */       return (Filter)Filter.INCLUDE; 
/*  264 */     return (Filter)ff.or((Filter)and, (Filter)or);
/*      */   }
/*      */   
/*      */   public void visit(IncludeFilter filter) {}
/*      */   
/*      */   public void visit(ExcludeFilter filter) {
/*  282 */     if (this.fcs.supports((Filter)Filter.EXCLUDE)) {
/*  283 */       this.preStack.push(filter);
/*      */     } else {
/*  285 */       this.postStack.push(filter);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsBetween filter, Object extradata) {
/*  300 */     if (this.original == null)
/*  301 */       this.original = (Filter)filter; 
/*  304 */     if (this.fcs.supports(PropertyIsBetween.class)) {
/*  309 */       int i = this.postStack.size();
/*  311 */       Expression lowerBound = filter.getLowerBoundary();
/*  312 */       Expression expr = filter.getExpression();
/*  313 */       Expression upperBound = filter.getUpperBoundary();
/*  314 */       if (lowerBound == null || upperBound == null || expr == null) {
/*  319 */         this.postStack.push(filter);
/*  320 */         return null;
/*      */       } 
/*  326 */       lowerBound.accept(this, null);
/*  329 */       if (i < this.postStack.size()) {
/*  333 */         this.postStack.pop();
/*  334 */         this.postStack.push(filter);
/*  336 */         return null;
/*      */       } 
/*  342 */       expr.accept(this, null);
/*  345 */       if (i < this.postStack.size()) {
/*  350 */         this.preStack.pop();
/*  351 */         this.postStack.pop();
/*  352 */         this.postStack.push(filter);
/*  354 */         return null;
/*      */       } 
/*  358 */       upperBound.accept(this, null);
/*  360 */       if (i < this.postStack.size()) {
/*  362 */         this.postStack.pop();
/*  363 */         this.preStack.pop();
/*  364 */         this.preStack.pop();
/*  365 */         this.postStack.push(filter);
/*  367 */         return null;
/*      */       } 
/*  378 */       this.preStack.pop();
/*  379 */       this.preStack.pop();
/*  380 */       this.preStack.pop();
/*  386 */       this.preStack.push(filter);
/*      */     } else {
/*  395 */       this.postStack.push(filter);
/*      */     } 
/*  397 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsEqualTo filter, Object notUsed) {
/*  403 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  404 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsGreaterThan filter, Object notUsed) {
/*  407 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  408 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object notUsed) {
/*  411 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  412 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLessThan filter, Object notUsed) {
/*  415 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  416 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object notUsed) {
/*  419 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  420 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNotEqualTo filter, Object notUsed) {
/*  423 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  424 */     return null;
/*      */   }
/*      */   
/*      */   private void visitBinaryComparisonOperator(BinaryComparisonOperator filter) {
/*  428 */     if (this.original == null)
/*  429 */       this.original = (Filter)filter; 
/*  432 */     if (!this.fcs.supports(FilterCapabilities.SIMPLE_COMPARISONS_OPENGIS)) {
/*  433 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  437 */     int i = this.postStack.size();
/*  438 */     Expression leftValue = filter.getExpression1();
/*  439 */     Expression rightValue = filter.getExpression2();
/*  440 */     if (leftValue == null || rightValue == null) {
/*  441 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  445 */     leftValue.accept(this, null);
/*  447 */     if (i < this.postStack.size()) {
/*  448 */       this.postStack.pop();
/*  449 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  454 */     rightValue.accept(this, null);
/*  456 */     if (i < this.postStack.size()) {
/*  457 */       this.preStack.pop();
/*  458 */       this.postStack.pop();
/*  459 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  464 */     this.preStack.pop();
/*  465 */     this.preStack.pop();
/*  466 */     this.preStack.push(filter);
/*      */   }
/*      */   
/*      */   public Object visit(BBOX filter, Object notUsed) {
/*  472 */     if (filter instanceof BBOX3D && !this.fcs.supports(BBOX3D.class)) {
/*  473 */       this.postStack.push(filter);
/*  474 */     } else if (!this.fcs.supports(BBOX.class)) {
/*  475 */       this.postStack.push(filter);
/*      */     } else {
/*  477 */       this.preStack.push(filter);
/*      */     } 
/*  480 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Beyond filter, Object notUsed) {
/*  484 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  485 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Contains filter, Object notUsed) {
/*  488 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  489 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Crosses filter, Object notUsed) {
/*  492 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  493 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Disjoint filter, Object notUsed) {
/*  496 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  497 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(DWithin filter, Object notUsed) {
/*  500 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  501 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Equals filter, Object notUsed) {
/*  504 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  505 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Intersects filter, Object notUsed) {
/*  508 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  509 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Overlaps filter, Object notUsed) {
/*  512 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  513 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Touches filter, Object notUsed) {
/*  516 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  517 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Within filter, Object notUsed) {
/*  520 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  521 */     return null;
/*      */   }
/*      */   
/*      */   private void visitBinarySpatialOperator(BinarySpatialOperator filter) {
/*  525 */     if (this.original == null)
/*  526 */       this.original = (Filter)filter; 
/*  528 */     Class[] spatialOps = { Beyond.class, Contains.class, Crosses.class, Disjoint.class, DWithin.class, Equals.class, Intersects.class, Overlaps.class, Touches.class, Within.class };
/*      */     int i;
/*  533 */     for (i = 0; i < spatialOps.length; i++) {
/*  534 */       if (spatialOps[i].isAssignableFrom(filter.getClass())) {
/*  535 */         if (!this.fcs.supports(spatialOps[i])) {
/*  536 */           this.postStack.push(filter);
/*      */           return;
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/*  547 */     i = this.postStack.size();
/*  550 */     Expression leftGeometry = filter.getExpression1();
/*  551 */     Expression rightGeometry = filter.getExpression2();
/*  553 */     if (leftGeometry == null || rightGeometry == null) {
/*  554 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  557 */     leftGeometry.accept(this, null);
/*  559 */     if (i < this.postStack.size()) {
/*  560 */       this.postStack.pop();
/*  561 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  566 */     rightGeometry.accept(this, null);
/*  568 */     if (i < this.postStack.size()) {
/*  569 */       this.preStack.pop();
/*  570 */       this.postStack.pop();
/*  571 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  576 */     this.preStack.pop();
/*  577 */     this.preStack.pop();
/*  578 */     this.preStack.push(filter);
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLike filter, Object notUsed) {
/*  582 */     if (this.original == null)
/*  583 */       this.original = (Filter)filter; 
/*  585 */     if (!this.fcs.supports(PropertyIsLike.class)) {
/*  586 */       this.postStack.push(filter);
/*  588 */       return null;
/*      */     } 
/*  591 */     int i = this.postStack.size();
/*  592 */     filter.getExpression().accept(this, null);
/*  594 */     if (i < this.postStack.size()) {
/*  595 */       this.postStack.pop();
/*  596 */       this.postStack.push(filter);
/*  598 */       return null;
/*      */     } 
/*  601 */     this.preStack.pop();
/*  602 */     this.preStack.push(filter);
/*  603 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(And filter, Object notUsed) {
/*  608 */     visitLogicOperator((Filter)filter, And.class);
/*  609 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Not filter, Object notUsed) {
/*  612 */     visitLogicOperator((Filter)filter, Not.class);
/*  613 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Or filter, Object notUsed) {
/*  616 */     visitLogicOperator((Filter)filter, Or.class);
/*  617 */     return null;
/*      */   }
/*      */   
/*      */   private void visitLogicOperator(Filter filter, Class filterInterface) {
/*  621 */     if (this.original == null)
/*  622 */       this.original = filter; 
/*  624 */     if (!this.fcs.supports(filterInterface)) {
/*  625 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  629 */     int i = this.postStack.size();
/*  630 */     int j = this.preStack.size();
/*  631 */     if (filter instanceof Not) {
/*  633 */       if (((Not)filter).getFilter() != null) {
/*  634 */         Filter next = ((Not)filter).getFilter();
/*  635 */         next.accept(this, null);
/*  637 */         if (i < this.postStack.size()) {
/*  642 */           popToSize(this.postStack, i);
/*  643 */           popToSize(this.preStack, j);
/*  644 */           this.postStack.push(filter);
/*      */         } else {
/*  646 */           popToSize(this.preStack, j);
/*  647 */           this.preStack.push(filter);
/*      */         } 
/*      */       } 
/*  651 */     } else if (filter instanceof Or) {
/*      */       try {
/*  655 */         Filter orReplacement = translateOr((Or)filter);
/*  656 */         orReplacement.accept(this, null);
/*  657 */       } catch (IllegalFilterException e) {
/*  658 */         popToSize(this.preStack, j);
/*  659 */         this.postStack.push(filter);
/*      */         return;
/*      */       } 
/*  662 */       if (this.postStack.size() > i) {
/*  663 */         popToSize(this.postStack, i);
/*  664 */         this.postStack.push(filter);
/*      */         return;
/*      */       } 
/*  669 */       this.preStack.pop();
/*  670 */       this.preStack.push(filter);
/*      */     } else {
/*  673 */       Iterator<Filter> it = ((And)filter).getChildren().iterator();
/*  675 */       while (it.hasNext()) {
/*  676 */         Filter next = it.next();
/*  677 */         next.accept(this, null);
/*      */       } 
/*  681 */       if (i < this.postStack.size()) {
/*  682 */         if (filter instanceof And) {
/*      */           And and;
/*  683 */           Filter f = this.postStack.pop();
/*  685 */           while (this.postStack.size() > i)
/*  686 */             and = this.ff.and(f, this.postStack.pop()); 
/*  688 */           this.postStack.push(and);
/*  690 */           if (j < this.preStack.size()) {
/*      */             And and1;
/*  691 */             Filter filter1 = this.preStack.pop();
/*  693 */             while (this.preStack.size() > j)
/*  694 */               and1 = this.ff.and(filter1, this.preStack.pop()); 
/*  695 */             this.preStack.push(and1);
/*      */           } 
/*      */         } else {
/*  698 */           logger.warning("LogicFilter found which is not 'and, or, not");
/*  701 */           popToSize(this.postStack, i);
/*  702 */           popToSize(this.preStack, j);
/*  704 */           this.postStack.push(filter);
/*      */         } 
/*      */       } else {
/*  707 */         popToSize(this.preStack, j);
/*  708 */         this.preStack.push(filter);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void popToSize(Stack stack, int j) {
/*  715 */     while (j < stack.size())
/*  716 */       stack.pop(); 
/*      */   }
/*      */   
/*      */   public Object visitNullFilter(Object notUsed) {
/*  721 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(IncludeFilter filter, Object notUsed) {
/*  725 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(ExcludeFilter filter, Object notUsed) {
/*  729 */     if (this.fcs.supports((Filter)Filter.EXCLUDE)) {
/*  730 */       this.preStack.push(filter);
/*      */     } else {
/*  732 */       this.postStack.push(filter);
/*      */     } 
/*  734 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNil filter, Object extraData) {
/*  738 */     return visitNullNil((Filter)filter, filter.getExpression());
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNull filter, Object notUsed) {
/*  742 */     return visitNullNil((Filter)filter, filter.getExpression());
/*      */   }
/*      */   
/*      */   Object visitNullNil(Filter filter, Expression e) {
/*  746 */     if (this.original == null)
/*  747 */       this.original = filter; 
/*  749 */     if (!this.fcs.supports(PropertyIsNull.class)) {
/*  750 */       this.postStack.push(filter);
/*  752 */       return null;
/*      */     } 
/*  755 */     int i = this.postStack.size();
/*  756 */     e.accept(this, null);
/*  758 */     if (i < this.postStack.size()) {
/*  759 */       this.postStack.pop();
/*  760 */       this.postStack.push(filter);
/*  761 */       return null;
/*      */     } 
/*  764 */     this.preStack.pop();
/*  765 */     this.preStack.push(filter);
/*  767 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Id filter, Object notUsed) {
/*  772 */     if (this.original == null)
/*  773 */       this.original = (Filter)filter; 
/*  775 */     if (!this.fcs.supports((Filter)filter)) {
/*  776 */       this.postStack.push(filter);
/*      */     } else {
/*  778 */       this.preStack.push(filter);
/*      */     } 
/*  781 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyName expression, Object notUsed) {
/*  786 */     if (this.parent != null && expression.evaluate(this.parent) == null)
/*  787 */       throw new IllegalArgumentException("Property '" + expression.getPropertyName() + "' could not be found in " + this.parent.getTypeName()); 
/*  790 */     if (this.transactionAccessor != null) {
/*  791 */       Filter updateFilter = this.transactionAccessor.getUpdateFilter(expression.getPropertyName());
/*  792 */       if (updateFilter != null) {
/*  793 */         this.changedStack.add(updateFilter);
/*  794 */         this.preStack.push(updateFilter);
/*      */       } else {
/*  796 */         this.preStack.push(expression);
/*      */       } 
/*      */     } else {
/*  798 */       this.preStack.push(expression);
/*      */     } 
/*  800 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Literal expression, Object notUsed) {
/*  804 */     this.preStack.push(expression);
/*  805 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Add filter, Object notUsed) {
/*  810 */     visitMathExpression((BinaryExpression)filter);
/*  811 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Divide filter, Object notUsed) {
/*  814 */     visitMathExpression((BinaryExpression)filter);
/*  815 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Multiply filter, Object notUsed) {
/*  818 */     visitMathExpression((BinaryExpression)filter);
/*  819 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Subtract filter, Object notUsed) {
/*  822 */     visitMathExpression((BinaryExpression)filter);
/*  823 */     return null;
/*      */   }
/*      */   
/*      */   private void visitMathExpression(BinaryExpression expression) {
/*  827 */     if (!this.fcs.supports(Add.class) && !this.fcs.supports(Subtract.class) && !this.fcs.supports(Multiply.class) && !this.fcs.supports(Divide.class)) {
/*  829 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  833 */     int i = this.postStack.size();
/*  834 */     Expression leftValue = expression.getExpression1();
/*  835 */     Expression rightValue = expression.getExpression2();
/*  836 */     if (leftValue == null || rightValue == null) {
/*  837 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  840 */     leftValue.accept(this, null);
/*  842 */     if (i < this.postStack.size()) {
/*  843 */       this.postStack.pop();
/*  844 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  849 */     rightValue.accept(this, null);
/*  851 */     if (i < this.postStack.size()) {
/*  852 */       this.preStack.pop();
/*  853 */       this.postStack.pop();
/*  854 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  859 */     this.preStack.pop();
/*  860 */     this.preStack.pop();
/*  861 */     this.preStack.push(expression);
/*      */   }
/*      */   
/*      */   public Object visit(Function expression, Object notUsed) {
/*  869 */     if (!this.fcs.supports(expression.getClass())) {
/*  870 */       this.postStack.push(expression);
/*  871 */       return null;
/*      */     } 
/*  874 */     if (expression.getName() == null) {
/*  875 */       this.postStack.push(expression);
/*  876 */       return null;
/*      */     } 
/*  879 */     int i = this.postStack.size();
/*  880 */     int j = this.preStack.size();
/*  882 */     for (int k = 0; k < expression.getParameters().size(); k++) {
/*  883 */       ((Expression)expression.getParameters().get(i)).accept(this, null);
/*  885 */       if (i < this.postStack.size()) {
/*  886 */         while (j < this.preStack.size())
/*  887 */           this.preStack.pop(); 
/*  888 */         this.postStack.pop();
/*  889 */         this.postStack.push(expression);
/*  891 */         return null;
/*      */       } 
/*      */     } 
/*  894 */     while (j < this.preStack.size())
/*  895 */       this.preStack.pop(); 
/*  896 */     this.preStack.push(expression);
/*  897 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(NilExpression nilExpression, Object notUsed) {
/*  901 */     this.postStack.push(nilExpression);
/*  902 */     return null;
/*      */   }
/*      */   
/*      */   private Filter translateOr(Or filter) throws IllegalFilterException {
/*  907 */     if (!(filter instanceof Or))
/*  908 */       return (Filter)filter; 
/*  915 */     Iterator<Filter> i = filter.getChildren().iterator();
/*  916 */     List<Filter> translated = new ArrayList();
/*  918 */     while (i.hasNext()) {
/*  919 */       Filter f = i.next();
/*  921 */       if (f instanceof Not) {
/*  923 */         Not logic = (Not)f;
/*  924 */         Filter next = logic.getFilter();
/*  925 */         translated.add(next);
/*      */         continue;
/*      */       } 
/*  927 */       translated.add(this.ff.not(f));
/*      */     } 
/*  931 */     And and = this.ff.and(translated);
/*  932 */     return (Filter)this.ff.not((Filter)and);
/*      */   }
/*      */   
/*      */   public Object visit(After after, Object extraData) {
/*  937 */     return visit((BinaryTemporalOperator)after, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/*  941 */     return visit((BinaryTemporalOperator)anyInteracts, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Before before, Object extraData) {
/*  945 */     return visit((BinaryTemporalOperator)before, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Begins begins, Object extraData) {
/*  949 */     return visit((BinaryTemporalOperator)begins, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(BegunBy begunBy, Object extraData) {
/*  953 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(During during, Object extraData) {
/*  957 */     return visit((BinaryTemporalOperator)during, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(EndedBy endedBy, Object extraData) {
/*  961 */     return visit((BinaryTemporalOperator)endedBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Ends ends, Object extraData) {
/*  965 */     return visit((BinaryTemporalOperator)ends, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Meets meets, Object extraData) {
/*  969 */     return visit((BinaryTemporalOperator)meets, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(MetBy metBy, Object extraData) {
/*  973 */     return visit((BinaryTemporalOperator)metBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/*  977 */     return visit((BinaryTemporalOperator)overlappedBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TContains contains, Object extraData) {
/*  981 */     return visit((BinaryTemporalOperator)contains, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TEquals equals, Object extraData) {
/*  985 */     return visit((BinaryTemporalOperator)equals, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TOverlaps contains, Object extraData) {
/*  989 */     return visit((BinaryTemporalOperator)contains, extraData);
/*      */   }
/*      */   
/*      */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/*  993 */     if (this.original == null)
/*  994 */       this.original = (Filter)filter; 
/*  997 */     if (!this.fcs.supports((Filter)filter)) {
/*  998 */       this.postStack.push(filter);
/*  999 */       return null;
/*      */     } 
/* 1002 */     Expression leftValue = filter.getExpression1();
/* 1003 */     Expression rightValue = filter.getExpression2();
/* 1005 */     int i = this.postStack.size();
/* 1006 */     if (leftValue == null || rightValue == null) {
/* 1007 */       this.postStack.push(filter);
/* 1008 */       return null;
/*      */     } 
/* 1011 */     leftValue.accept(this, null);
/* 1013 */     if (i < this.postStack.size()) {
/* 1014 */       this.postStack.pop();
/* 1015 */       this.postStack.push(filter);
/* 1017 */       return null;
/*      */     } 
/* 1020 */     rightValue.accept(this, null);
/* 1022 */     if (i < this.postStack.size()) {
/* 1023 */       this.preStack.pop();
/* 1024 */       this.postStack.pop();
/* 1025 */       this.postStack.push(filter);
/* 1027 */       return null;
/*      */     } 
/* 1030 */     this.preStack.pop();
/* 1031 */     this.preStack.pop();
/* 1032 */     this.preStack.push(filter);
/* 1033 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\PostPreProcessFilterSplittingVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */