/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.functors.UniquePredicate;
/*    */ 
/*    */ public class UniqueFilterIterator extends FilterIterator {
/*    */   public UniqueFilterIterator(Iterator iterator) {
/* 43 */     super(iterator, UniquePredicate.getInstance());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\UniqueFilterIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */