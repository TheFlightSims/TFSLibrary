/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
/*    */ import org.apache.commons.collections.ResettableIterator;
/*    */ 
/*    */ public class EmptyOrderedMapIterator extends AbstractEmptyIterator implements OrderedMapIterator, ResettableIterator {
/* 36 */   public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\EmptyOrderedMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */