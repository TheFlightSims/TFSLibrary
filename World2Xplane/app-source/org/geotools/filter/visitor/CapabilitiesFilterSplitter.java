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
/*      */ import org.geotools.filter.Capabilities;
/*      */ import org.geotools.filter.IllegalFilterException;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.feature.type.FeatureType;
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
/*      */ public class CapabilitiesFilterSplitter implements FilterVisitor, ExpressionVisitor {
/*  150 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*      */   
/*  157 */   private Stack postStack = new Stack();
/*      */   
/*  163 */   private Stack preStack = new Stack();
/*      */   
/*  170 */   private Set changedStack = new HashSet();
/*      */   
/*  175 */   private Capabilities fcs = null;
/*      */   
/*  177 */   private FeatureType parent = null;
/*      */   
/*  179 */   private Filter original = null;
/*      */   
/*      */   private ClientTransactionAccessor transactionAccessor;
/*      */   
/*      */   private FilterFactory ff;
/*      */   
/*      */   public CapabilitiesFilterSplitter(Capabilities fcs, FeatureType parent, ClientTransactionAccessor transactionAccessor) {
/*  204 */     this.ff = CommonFactoryFinder.getFilterFactory(null);
/*  205 */     this.fcs = fcs;
/*  206 */     this.parent = parent;
/*  207 */     this.transactionAccessor = transactionAccessor;
/*      */   }
/*      */   
/*      */   public Filter getFilterPost() {
/*  218 */     if (!this.changedStack.isEmpty())
/*  221 */       return this.original; 
/*  223 */     if (this.postStack.size() > 1)
/*  224 */       LOGGER.warning("Too many post stack items after run: " + this.postStack.size()); 
/*  229 */     Filter f = this.postStack.isEmpty() ? (Filter)Filter.INCLUDE : this.postStack.peek();
/*  230 */     return f;
/*      */   }
/*      */   
/*      */   public Filter getFilterPre() {
/*      */     And and;
/*      */     Or or;
/*  239 */     if (this.preStack.isEmpty())
/*  240 */       return (Filter)Filter.INCLUDE; 
/*  243 */     if (this.preStack.size() > 1)
/*  244 */       LOGGER.warning("Too many pre stack items after run: " + this.preStack.size()); 
/*  249 */     Filter f = this.preStack.isEmpty() ? (Filter)Filter.INCLUDE : this.preStack.peek();
/*  251 */     if (this.transactionAccessor != null && f != null && f != Filter.EXCLUDE) {
/*  252 */       Filter deleteFilter = this.transactionAccessor.getDeleteFilter();
/*  253 */       if (deleteFilter != null) {
/*      */         ExcludeFilter excludeFilter;
/*  254 */         if (deleteFilter == Filter.EXCLUDE) {
/*  255 */           excludeFilter = Filter.EXCLUDE;
/*      */         } else {
/*  257 */           and = this.ff.and((Filter)excludeFilter, (Filter)this.ff.not(deleteFilter));
/*      */         } 
/*      */       } 
/*      */     } 
/*  262 */     if (this.changedStack.isEmpty())
/*  263 */       return (Filter)and; 
/*  265 */     Iterator<Filter> iter = this.changedStack.iterator();
/*  266 */     Filter updateFilter = iter.next();
/*  267 */     while (iter.hasNext()) {
/*  268 */       Filter next = iter.next();
/*  269 */       if (next == Filter.INCLUDE) {
/*  270 */         updateFilter = next;
/*      */         break;
/*      */       } 
/*  273 */       or = this.ff.or(updateFilter, next);
/*      */     } 
/*  276 */     if (or == Filter.INCLUDE || and == Filter.INCLUDE)
/*  277 */       return (Filter)Filter.INCLUDE; 
/*  278 */     return (Filter)this.ff.or((Filter)and, (Filter)or);
/*      */   }
/*      */   
/*      */   public void visit(IncludeFilter filter) {}
/*      */   
/*      */   public void visit(ExcludeFilter filter) {
/*  298 */     if (this.fcs.supports((Filter)Filter.EXCLUDE)) {
/*  299 */       this.preStack.push(filter);
/*      */     } else {
/*  301 */       this.postStack.push(filter);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsBetween filter, Object extradata) {
/*  314 */     if (this.original == null)
/*  315 */       this.original = (Filter)filter; 
/*  318 */     if (this.fcs.supports((Filter)filter)) {
/*  323 */       int i = this.postStack.size();
/*  325 */       Expression lowerBound = filter.getLowerBoundary();
/*  326 */       Expression expr = filter.getExpression();
/*  327 */       Expression upperBound = filter.getUpperBoundary();
/*  328 */       if (lowerBound == null || upperBound == null || expr == null) {
/*  331 */         this.postStack.push(filter);
/*  332 */         return null;
/*      */       } 
/*  338 */       lowerBound.accept(this, null);
/*  341 */       if (i < this.postStack.size()) {
/*  345 */         this.postStack.pop();
/*  346 */         this.postStack.push(filter);
/*  348 */         return null;
/*      */       } 
/*  354 */       expr.accept(this, null);
/*  357 */       if (i < this.postStack.size()) {
/*  362 */         this.preStack.pop();
/*  363 */         this.postStack.pop();
/*  364 */         this.postStack.push(filter);
/*  366 */         return null;
/*      */       } 
/*  370 */       upperBound.accept(this, null);
/*  372 */       if (i < this.postStack.size()) {
/*  374 */         this.postStack.pop();
/*  375 */         this.preStack.pop();
/*  376 */         this.preStack.pop();
/*  377 */         this.postStack.push(filter);
/*  379 */         return null;
/*      */       } 
/*  390 */       this.preStack.pop();
/*  391 */       this.preStack.pop();
/*  392 */       this.preStack.pop();
/*  397 */       this.preStack.push(filter);
/*      */     } else {
/*  406 */       this.postStack.push(filter);
/*      */     } 
/*  408 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsEqualTo filter, Object notUsed) {
/*  412 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  413 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsGreaterThan filter, Object notUsed) {
/*  417 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  418 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object notUsed) {
/*  422 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  423 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLessThan filter, Object notUsed) {
/*  427 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  428 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object notUsed) {
/*  432 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  433 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNotEqualTo filter, Object notUsed) {
/*  437 */     visitBinaryComparisonOperator((BinaryComparisonOperator)filter);
/*  438 */     return null;
/*      */   }
/*      */   
/*      */   private void visitBinaryOperator(Filter filter, Expression leftValue, Expression rightValue) {
/*  442 */     if (this.original == null)
/*  443 */       this.original = filter; 
/*  446 */     if (!this.fcs.supports(filter)) {
/*  447 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  451 */     int i = this.postStack.size();
/*  452 */     if (leftValue == null || rightValue == null) {
/*  453 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  457 */     leftValue.accept(this, null);
/*  459 */     if (i < this.postStack.size()) {
/*  460 */       this.postStack.pop();
/*  461 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  466 */     rightValue.accept(this, null);
/*  468 */     if (i < this.postStack.size()) {
/*  469 */       this.preStack.pop();
/*  470 */       this.postStack.pop();
/*  471 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  476 */     this.preStack.pop();
/*  477 */     this.preStack.pop();
/*  478 */     this.preStack.push(filter);
/*      */   }
/*      */   
/*      */   private void visitBinaryComparisonOperator(BinaryComparisonOperator filter) {
/*  482 */     visitBinaryOperator((Filter)filter, filter.getExpression1(), filter.getExpression2());
/*      */   }
/*      */   
/*      */   public Object visit(BBOX filter, Object notUsed) {
/*  486 */     if (!this.fcs.supports((Filter)filter)) {
/*  487 */       this.postStack.push(filter);
/*      */     } else {
/*  489 */       this.preStack.push(filter);
/*      */     } 
/*  491 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Beyond filter, Object notUsed) {
/*  495 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  496 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Contains filter, Object notUsed) {
/*  500 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  501 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Crosses filter, Object notUsed) {
/*  505 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  506 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Disjoint filter, Object notUsed) {
/*  510 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  511 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(DWithin filter, Object notUsed) {
/*  515 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  516 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Equals filter, Object notUsed) {
/*  520 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  521 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Intersects filter, Object notUsed) {
/*  525 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  526 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Overlaps filter, Object notUsed) {
/*  530 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  531 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Touches filter, Object notUsed) {
/*  535 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  536 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Within filter, Object notUsed) {
/*  540 */     visitBinarySpatialOperator((BinarySpatialOperator)filter);
/*  541 */     return null;
/*      */   }
/*      */   
/*      */   private void visitBinarySpatialOperator(BinarySpatialOperator filter) {
/*  545 */     if (this.original == null)
/*  546 */       this.original = (Filter)filter; 
/*  548 */     Class[] spatialOps = { Beyond.class, Contains.class, Crosses.class, Disjoint.class, DWithin.class, Equals.class, Intersects.class, Overlaps.class, Touches.class, Within.class };
/*      */     int i;
/*  552 */     for (i = 0; i < spatialOps.length; i++) {
/*  553 */       if (spatialOps[i].isAssignableFrom(filter.getClass())) {
/*  555 */         if (!this.fcs.supports((Filter)filter)) {
/*  556 */           this.postStack.push(filter);
/*      */           return;
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/*  567 */     i = this.postStack.size();
/*  570 */     Expression leftGeometry = filter.getExpression1();
/*  571 */     Expression rightGeometry = filter.getExpression2();
/*  573 */     if (leftGeometry == null || rightGeometry == null) {
/*  574 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  577 */     leftGeometry.accept(this, null);
/*  579 */     if (i < this.postStack.size()) {
/*  580 */       this.postStack.pop();
/*  581 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  586 */     rightGeometry.accept(this, null);
/*  588 */     if (i < this.postStack.size()) {
/*  589 */       this.preStack.pop();
/*  590 */       this.postStack.pop();
/*  591 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  596 */     this.preStack.pop();
/*  597 */     this.preStack.pop();
/*  598 */     this.preStack.push(filter);
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsLike filter, Object notUsed) {
/*  602 */     if (this.original == null)
/*  603 */       this.original = (Filter)filter; 
/*  606 */     if (!this.fcs.supports((Filter)filter)) {
/*  607 */       this.postStack.push(filter);
/*  609 */       return null;
/*      */     } 
/*  612 */     int i = this.postStack.size();
/*  613 */     filter.getExpression().accept(this, null);
/*  615 */     if (i < this.postStack.size()) {
/*  616 */       this.postStack.pop();
/*  617 */       this.postStack.push(filter);
/*  619 */       return null;
/*      */     } 
/*  622 */     this.preStack.pop();
/*  623 */     this.preStack.push(filter);
/*  624 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(And filter, Object notUsed) {
/*  628 */     visitLogicOperator((Filter)filter);
/*  629 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Not filter, Object notUsed) {
/*  633 */     visitLogicOperator((Filter)filter);
/*  634 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Or filter, Object notUsed) {
/*  638 */     visitLogicOperator((Filter)filter);
/*  639 */     return null;
/*      */   }
/*      */   
/*      */   private void visitLogicOperator(Filter filter) {
/*  643 */     if (this.original == null)
/*  644 */       this.original = filter; 
/*  646 */     if (!this.fcs.supports(filter)) {
/*  650 */       if (filter instanceof And) {
/*  652 */         Iterator<Filter> it = ((And)filter).getChildren().iterator();
/*  653 */         Filter supportedChild = null;
/*  654 */         List<Filter> otherChildren = new ArrayList<Filter>();
/*  655 */         while (it.hasNext()) {
/*  656 */           Filter child = it.next();
/*  657 */           if (supportedChild == null && this.fcs.supports(child)) {
/*  658 */             supportedChild = child;
/*      */             continue;
/*      */           } 
/*  660 */           otherChildren.add(child);
/*      */         } 
/*  664 */         if (supportedChild == null) {
/*  666 */           this.postStack.push(filter);
/*      */           return;
/*      */         } 
/*  672 */         this.preStack.push(supportedChild);
/*  675 */         if (otherChildren.size() == 1) {
/*  676 */           this.postStack.push(otherChildren.get(0));
/*      */         } else {
/*  678 */           this.postStack.push(this.ff.and(otherChildren));
/*      */         } 
/*      */         return;
/*      */       } 
/*  683 */       this.postStack.push(filter);
/*      */       return;
/*      */     } 
/*  688 */     int i = this.postStack.size();
/*  689 */     int j = this.preStack.size();
/*  690 */     if (filter instanceof Not) {
/*  692 */       if (((Not)filter).getFilter() != null) {
/*  693 */         Filter next = ((Not)filter).getFilter();
/*  694 */         next.accept(this, null);
/*  696 */         if (i < this.postStack.size()) {
/*  702 */           popToSize(this.postStack, i);
/*  703 */           popToSize(this.preStack, j);
/*  704 */           this.postStack.push(filter);
/*      */         } else {
/*  706 */           popToSize(this.preStack, j);
/*  707 */           this.preStack.push(filter);
/*      */         } 
/*      */       } 
/*  711 */     } else if (filter instanceof Or) {
/*      */       try {
/*  715 */         Filter orReplacement = translateOr((Or)filter);
/*  716 */         orReplacement.accept(this, null);
/*  717 */       } catch (IllegalFilterException e) {
/*  718 */         popToSize(this.preStack, j);
/*  719 */         this.postStack.push(filter);
/*      */         return;
/*      */       } 
/*  722 */       if (this.postStack.size() > i) {
/*  723 */         popToSize(this.postStack, i);
/*  724 */         this.postStack.push(filter);
/*      */         return;
/*      */       } 
/*  729 */       this.preStack.pop();
/*  730 */       this.preStack.push(filter);
/*      */     } else {
/*  733 */       Iterator<Filter> it = ((And)filter).getChildren().iterator();
/*  735 */       while (it.hasNext()) {
/*  736 */         Filter next = it.next();
/*  737 */         next.accept(this, null);
/*      */       } 
/*  741 */       if (i < this.postStack.size()) {
/*  742 */         if (filter instanceof And) {
/*      */           And and;
/*  743 */           Filter f = this.postStack.pop();
/*  745 */           while (this.postStack.size() > i)
/*  746 */             and = this.ff.and(f, this.postStack.pop()); 
/*  748 */           this.postStack.push(and);
/*  750 */           if (j < this.preStack.size()) {
/*      */             And and1;
/*  751 */             Filter filter1 = this.preStack.pop();
/*  753 */             while (this.preStack.size() > j)
/*  754 */               and1 = this.ff.and(filter1, this.preStack.pop()); 
/*  755 */             this.preStack.push(and1);
/*      */           } 
/*      */         } else {
/*  758 */           LOGGER.warning("LogicFilter found which is not 'and, or, not");
/*  760 */           popToSize(this.postStack, i);
/*  761 */           popToSize(this.preStack, j);
/*  763 */           this.postStack.push(filter);
/*      */         } 
/*      */       } else {
/*  766 */         popToSize(this.preStack, j);
/*  767 */         this.preStack.push(filter);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void popToSize(Stack stack, int j) {
/*  774 */     while (j < stack.size())
/*  775 */       stack.pop(); 
/*      */   }
/*      */   
/*      */   public Object visitNullFilter(Object notUsed) {
/*  780 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(IncludeFilter filter, Object notUsed) {
/*  784 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(ExcludeFilter filter, Object notUsed) {
/*  788 */     if (this.fcs.supports((Filter)Filter.EXCLUDE)) {
/*  789 */       this.preStack.push(filter);
/*      */     } else {
/*  791 */       this.postStack.push(filter);
/*      */     } 
/*  793 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNull filter, Object notUsed) {
/*  797 */     return visitNullNil((Filter)filter, filter.getExpression());
/*      */   }
/*      */   
/*      */   public Object visit(PropertyIsNil filter, Object extraData) {
/*  801 */     return visitNullNil((Filter)filter, filter.getExpression());
/*      */   }
/*      */   
/*      */   Object visitNullNil(Filter filter, Expression e) {
/*  805 */     if (this.original == null)
/*  806 */       this.original = filter; 
/*  808 */     if (!this.fcs.supports(filter)) {
/*  809 */       this.postStack.push(filter);
/*  811 */       return null;
/*      */     } 
/*  814 */     int i = this.postStack.size();
/*  815 */     ((PropertyIsNull)filter).getExpression().accept(this, null);
/*  817 */     if (i < this.postStack.size()) {
/*  818 */       this.postStack.pop();
/*  819 */       this.postStack.push(filter);
/*      */     } 
/*  822 */     this.preStack.pop();
/*  823 */     this.preStack.push(filter);
/*  825 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Id filter, Object notUsed) {
/*  828 */     if (this.original == null)
/*  829 */       this.original = (Filter)filter; 
/*  831 */     if (!this.fcs.supports((Filter)filter)) {
/*  832 */       this.postStack.push(filter);
/*      */     } else {
/*  834 */       this.preStack.push(filter);
/*      */     } 
/*  837 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(PropertyName expression, Object notUsed) {
/*  842 */     if (this.parent != null && expression.evaluate(this.parent) == null)
/*  843 */       throw new IllegalArgumentException("Property '" + expression.getPropertyName() + "' could not be found in " + this.parent.getName()); 
/*  846 */     if (this.transactionAccessor != null) {
/*  847 */       Filter updateFilter = this.transactionAccessor.getUpdateFilter(expression.getPropertyName());
/*  849 */       if (updateFilter != null) {
/*  850 */         if (updateFilter == Filter.EXCLUDE) {
/*  852 */           this.postStack.push(expression);
/*      */         } else {
/*  854 */           this.changedStack.add(updateFilter);
/*  855 */           this.preStack.push(updateFilter);
/*      */         } 
/*      */       } else {
/*  858 */         this.preStack.push(expression);
/*      */       } 
/*      */     } else {
/*  860 */       this.preStack.push(expression);
/*      */     } 
/*  862 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Literal expression, Object notUsed) {
/*  866 */     this.preStack.push(expression);
/*  867 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Add filter, Object notUsed) {
/*  871 */     visitMathExpression((BinaryExpression)filter);
/*  872 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Divide filter, Object notUsed) {
/*  876 */     visitMathExpression((BinaryExpression)filter);
/*  877 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Multiply filter, Object notUsed) {
/*  881 */     visitMathExpression((BinaryExpression)filter);
/*  882 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(Subtract filter, Object notUsed) {
/*  886 */     visitMathExpression((BinaryExpression)filter);
/*  887 */     return null;
/*      */   }
/*      */   
/*      */   private void visitMathExpression(BinaryExpression expression) {
/*  893 */     if (!this.fcs.fullySupports((Expression)expression)) {
/*  894 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  898 */     int i = this.postStack.size();
/*  899 */     Expression leftValue = expression.getExpression1();
/*  900 */     Expression rightValue = expression.getExpression2();
/*  901 */     if (leftValue == null || rightValue == null) {
/*  902 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  905 */     leftValue.accept(this, null);
/*  907 */     if (i < this.postStack.size()) {
/*  908 */       this.postStack.pop();
/*  909 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  914 */     rightValue.accept(this, null);
/*  916 */     if (i < this.postStack.size()) {
/*  917 */       this.preStack.pop();
/*  918 */       this.postStack.pop();
/*  919 */       this.postStack.push(expression);
/*      */       return;
/*      */     } 
/*  924 */     this.preStack.pop();
/*  925 */     this.preStack.pop();
/*  926 */     this.preStack.push(expression);
/*      */   }
/*      */   
/*      */   public Object visit(Function expression, Object notUsed) {
/*  934 */     if (!this.fcs.fullySupports((Expression)expression)) {
/*  935 */       this.postStack.push(expression);
/*  936 */       return null;
/*      */     } 
/*  939 */     if (expression.getName() == null) {
/*  940 */       this.postStack.push(expression);
/*  941 */       return null;
/*      */     } 
/*  944 */     int i = this.postStack.size();
/*  945 */     int j = this.preStack.size();
/*  947 */     for (int k = 0; k < expression.getParameters().size(); k++) {
/*  948 */       ((Expression)expression.getParameters().get(i)).accept(this, null);
/*  950 */       if (i < this.postStack.size()) {
/*  951 */         while (j < this.preStack.size())
/*  952 */           this.preStack.pop(); 
/*  953 */         this.postStack.pop();
/*  954 */         this.postStack.push(expression);
/*  956 */         return null;
/*      */       } 
/*      */     } 
/*  959 */     while (j < this.preStack.size())
/*  960 */       this.preStack.pop(); 
/*  961 */     this.preStack.push(expression);
/*  962 */     return null;
/*      */   }
/*      */   
/*      */   public Object visit(NilExpression nilExpression, Object notUsed) {
/*  966 */     this.postStack.push(nilExpression);
/*  967 */     return null;
/*      */   }
/*      */   
/*      */   private Filter translateOr(Or filter) throws IllegalFilterException {
/*  971 */     if (!(filter instanceof Or))
/*  972 */       return (Filter)filter; 
/*  979 */     Iterator<Filter> i = filter.getChildren().iterator();
/*  980 */     List<Filter> translated = new ArrayList();
/*  982 */     while (i.hasNext()) {
/*  983 */       Filter f = i.next();
/*  985 */       if (f instanceof Not) {
/*  987 */         Not logic = (Not)f;
/*  988 */         Filter next = logic.getFilter();
/*  989 */         translated.add(next);
/*      */         continue;
/*      */       } 
/*  991 */       translated.add(this.ff.not(f));
/*      */     } 
/*  995 */     And and = this.ff.and(translated);
/*  996 */     return (Filter)this.ff.not((Filter)and);
/*      */   }
/*      */   
/*      */   public Object visit(After after, Object extraData) {
/* 1000 */     return visit((BinaryTemporalOperator)after, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 1004 */     return visit((BinaryTemporalOperator)anyInteracts, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Before before, Object extraData) {
/* 1008 */     return visit((BinaryTemporalOperator)before, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Begins begins, Object extraData) {
/* 1012 */     return visit((BinaryTemporalOperator)begins, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(BegunBy begunBy, Object extraData) {
/* 1016 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(During during, Object extraData) {
/* 1020 */     return visit((BinaryTemporalOperator)during, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(EndedBy endedBy, Object extraData) {
/* 1024 */     return visit((BinaryTemporalOperator)endedBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Ends ends, Object extraData) {
/* 1028 */     return visit((BinaryTemporalOperator)ends, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(Meets meets, Object extraData) {
/* 1032 */     return visit((BinaryTemporalOperator)meets, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(MetBy metBy, Object extraData) {
/* 1036 */     return visit((BinaryTemporalOperator)metBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 1040 */     return visit((BinaryTemporalOperator)overlappedBy, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TContains contains, Object extraData) {
/* 1044 */     return visit((BinaryTemporalOperator)contains, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TEquals equals, Object extraData) {
/* 1048 */     return visit((BinaryTemporalOperator)equals, extraData);
/*      */   }
/*      */   
/*      */   public Object visit(TOverlaps contains, Object extraData) {
/* 1052 */     return visit((BinaryTemporalOperator)contains, extraData);
/*      */   }
/*      */   
/*      */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 1056 */     visitBinaryOperator((Filter)filter, filter.getExpression1(), filter.getExpression2());
/* 1057 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\CapabilitiesFilterSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */