/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*    */ 
/*    */ public abstract class AbstractBagDecorator extends AbstractCollectionDecorator implements Bag {
/*    */   protected AbstractBagDecorator() {}
/*    */   
/*    */   protected AbstractBagDecorator(Bag bag) {
/* 52 */     super((Collection)bag);
/*    */   }
/*    */   
/*    */   protected Bag getBag() {
/* 61 */     return (Bag)getCollection();
/*    */   }
/*    */   
/*    */   public int getCount(Object object) {
/* 66 */     return getBag().getCount(object);
/*    */   }
/*    */   
/*    */   public boolean add(Object object, int count) {
/* 70 */     return getBag().add(object, count);
/*    */   }
/*    */   
/*    */   public boolean remove(Object object, int count) {
/* 74 */     return getBag().remove(object, count);
/*    */   }
/*    */   
/*    */   public Set uniqueSet() {
/* 78 */     return getBag().uniqueSet();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\AbstractBagDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */