/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ public class NoContentIterator implements Iterator<SimpleFeature> {
/*    */   Throwable origionalProblem;
/*    */   
/*    */   public NoContentIterator(Throwable t) {
/* 46 */     this.origionalProblem = t;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 49 */     return (this.origionalProblem != null);
/*    */   }
/*    */   
/*    */   public SimpleFeature next() {
/* 52 */     if (this.origionalProblem == null)
/* 56 */       throw new NoSuchElementException(); 
/* 58 */     NoSuchElementException cantFind = new NoSuchElementException("Could not aquire feature:" + this.origionalProblem);
/* 59 */     cantFind.initCause(this.origionalProblem);
/* 60 */     this.origionalProblem = null;
/* 61 */     throw cantFind;
/*    */   }
/*    */   
/*    */   public void remove() {
/* 65 */     if (this.origionalProblem == null)
/* 67 */       throw new UnsupportedOperationException(); 
/* 70 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\NoContentIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */