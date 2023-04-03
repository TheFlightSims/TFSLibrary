/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.ResettableIterator;
/*    */ 
/*    */ public class EmptyIterator extends AbstractEmptyIterator implements ResettableIterator {
/* 41 */   public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
/*    */   
/* 46 */   public static final Iterator INSTANCE = (Iterator)RESETTABLE_INSTANCE;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\EmptyIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */