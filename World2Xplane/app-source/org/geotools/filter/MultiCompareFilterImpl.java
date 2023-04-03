/*    */ package org.geotools.filter;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public abstract class MultiCompareFilterImpl extends CompareFilterImpl {
/*    */   protected MultiValuedFilter.MatchAction matchAction;
/*    */   
/*    */   protected MultiCompareFilterImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 39 */     super(factory, e1, e2);
/* 40 */     this.matchAction = MultiValuedFilter.MatchAction.ANY;
/*    */   }
/*    */   
/*    */   protected MultiCompareFilterImpl(FilterFactory factory, Expression e1, Expression e2, boolean matchCase) {
/* 45 */     super(factory, e1, e2, matchCase);
/* 46 */     this.matchAction = MultiValuedFilter.MatchAction.ANY;
/*    */   }
/*    */   
/*    */   protected MultiCompareFilterImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 51 */     super(factory, e1, e2);
/* 52 */     this.matchAction = matchAction;
/*    */   }
/*    */   
/*    */   protected MultiCompareFilterImpl(FilterFactory factory, Expression e1, Expression e2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/* 57 */     super(factory, e1, e2, matchCase);
/* 58 */     this.matchAction = matchAction;
/*    */   }
/*    */   
/*    */   public MultiValuedFilter.MatchAction getMatchAction() {
/* 62 */     return this.matchAction;
/*    */   }
/*    */   
/*    */   public final boolean evaluate(Object feature) {
/* 66 */     Object object1 = eval(this.expression1, feature);
/* 67 */     Object object2 = eval(this.expression2, feature);
/* 69 */     if (!(object1 instanceof Collection) && !(object2 instanceof Collection))
/* 70 */       return evaluateInternal(object1, object2); 
/* 73 */     Collection<Object> leftValues = (object1 instanceof Collection) ? (Collection<Object>)object1 : Collections.<Object>singletonList(object1);
/* 75 */     Collection<Object> rightValues = (object2 instanceof Collection) ? (Collection<Object>)object2 : Collections.<Object>singletonList(object2);
/* 78 */     int count = 0;
/* 80 */     for (Object value1 : leftValues) {
/* 81 */       for (Object value2 : rightValues) {
/* 82 */         boolean temp = evaluateInternal(value1, value2);
/* 83 */         if (temp)
/* 84 */           count++; 
/* 87 */         switch (this.matchAction) {
/*    */           case ONE:
/* 88 */             if (count > 1)
/* 88 */               return false; 
/*    */           case ALL:
/* 89 */             if (!temp)
/* 89 */               return false; 
/*    */           case ANY:
/* 90 */             if (temp)
/* 90 */               return true; 
/*    */         } 
/*    */       } 
/*    */     } 
/* 95 */     switch (this.matchAction) {
/*    */       case ONE:
/* 96 */         return (count == 1);
/*    */       case ALL:
/* 97 */         return true;
/*    */       case ANY:
/* 98 */         return false;
/*    */     } 
/* 99 */     return false;
/*    */   }
/*    */   
/*    */   public abstract boolean evaluateInternal(Object paramObject1, Object paramObject2);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MultiCompareFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */