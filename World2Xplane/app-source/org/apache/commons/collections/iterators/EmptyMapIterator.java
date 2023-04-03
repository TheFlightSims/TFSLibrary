/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ import org.apache.commons.collections.ResettableIterator;
/*    */ 
/*    */ public class EmptyMapIterator extends AbstractEmptyIterator implements MapIterator, ResettableIterator {
/* 36 */   public static final MapIterator INSTANCE = new EmptyMapIterator();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\EmptyMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */