/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.SortedBag;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class TransformedSortedBag extends TransformedBag implements SortedBag {
/*    */   private static final long serialVersionUID = -251737742649401930L;
/*    */   
/*    */   public static SortedBag decorate(SortedBag bag, Transformer transformer) {
/* 57 */     return new TransformedSortedBag(bag, transformer);
/*    */   }
/*    */   
/*    */   protected TransformedSortedBag(SortedBag bag, Transformer transformer) {
/* 72 */     super((Bag)bag, transformer);
/*    */   }
/*    */   
/*    */   protected SortedBag getSortedBag() {
/* 81 */     return (SortedBag)this.collection;
/*    */   }
/*    */   
/*    */   public Object first() {
/* 86 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 90 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 94 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\TransformedSortedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */