/*    */ package ch.qos.logback.core.spi;
/*    */ 
/*    */ import ch.qos.logback.core.filter.Filter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CopyOnWriteArrayList;
/*    */ 
/*    */ public final class FilterAttachableImpl<E> implements FilterAttachable<E> {
/* 29 */   CopyOnWriteArrayList<Filter<E>> filterList = new CopyOnWriteArrayList<Filter<E>>();
/*    */   
/*    */   public void addFilter(Filter<E> newFilter) {
/* 35 */     this.filterList.add(newFilter);
/*    */   }
/*    */   
/*    */   public void clearAllFilters() {
/* 42 */     this.filterList.clear();
/*    */   }
/*    */   
/*    */   public FilterReply getFilterChainDecision(E event) {
/* 51 */     for (Filter<E> f : this.filterList) {
/* 52 */       FilterReply r = f.decide(event);
/* 53 */       if (r == FilterReply.DENY || r == FilterReply.ACCEPT)
/* 54 */         return r; 
/*    */     } 
/* 57 */     return FilterReply.NEUTRAL;
/*    */   }
/*    */   
/*    */   public List<Filter<E>> getCopyOfAttachedFiltersList() {
/* 61 */     return new ArrayList<Filter<E>>(this.filterList);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\spi\FilterAttachableImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */