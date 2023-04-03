/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class FilteringIterator<F extends Feature> implements Iterator<F> {
/*    */   Iterator<F> delegate;
/*    */   
/*    */   Filter filter;
/*    */   
/*    */   F next;
/*    */   
/*    */   public FilteringIterator(Iterator<F> delegate, Filter filter) {
/* 51 */     this.delegate = delegate;
/* 52 */     this.filter = filter;
/*    */   }
/*    */   
/*    */   public Iterator<F> getDelegate() {
/* 56 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public void remove() {
/* 60 */     this.delegate.remove();
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 64 */     if (this.next != null)
/* 65 */       return true; 
/* 68 */     while (this.delegate.hasNext()) {
/* 69 */       Feature feature = (Feature)this.delegate.next();
/* 70 */       if (this.filter.evaluate(feature)) {
/* 71 */         this.next = (F)feature;
/*    */         break;
/*    */       } 
/*    */     } 
/* 76 */     return (this.next != null);
/*    */   }
/*    */   
/*    */   public F next() {
/* 80 */     F f = this.next;
/* 81 */     this.next = null;
/* 82 */     return f;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FilteringIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */