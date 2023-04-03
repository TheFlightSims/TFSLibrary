/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.SortedBag;
/*    */ 
/*    */ public abstract class AbstractSortedBagDecorator extends AbstractBagDecorator implements SortedBag {
/*    */   protected AbstractSortedBagDecorator() {}
/*    */   
/*    */   protected AbstractSortedBagDecorator(SortedBag bag) {
/* 51 */     super((Bag)bag);
/*    */   }
/*    */   
/*    */   protected SortedBag getSortedBag() {
/* 60 */     return (SortedBag)getCollection();
/*    */   }
/*    */   
/*    */   public Object first() {
/* 65 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 69 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 73 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\AbstractSortedBagDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */