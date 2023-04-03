/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class MultipleSourceIterator<T> implements ReleasableIterator<T> {
/*    */   private List<ReleasableIterator<T>> sources;
/*    */   
/*    */   public MultipleSourceIterator(List<ReleasableIterator<T>> sources) {
/* 32 */     this.sources = new LinkedList<ReleasableIterator<T>>(sources);
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 41 */     while (this.sources.size() > 0) {
/* 42 */       if (((ReleasableIterator)this.sources.get(0)).hasNext())
/* 43 */         return true; 
/* 45 */       ((ReleasableIterator)this.sources.remove(0)).release();
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public T next() {
/* 58 */     if (!hasNext())
/* 59 */       throw new NoSuchElementException(); 
/* 62 */     return (T)((ReleasableIterator)this.sources.get(0)).next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 71 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void release() {
/* 80 */     for (ReleasableIterator<T> source : this.sources)
/* 81 */       source.release(); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\MultipleSourceIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */