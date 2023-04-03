/*    */ package org.apache.commons.collections.bidimap;
/*    */ 
/*    */ import org.apache.commons.collections.BidiMap;
/*    */ import org.apache.commons.collections.OrderedBidiMap;
/*    */ import org.apache.commons.collections.OrderedMapIterator;
/*    */ 
/*    */ public abstract class AbstractOrderedBidiMapDecorator extends AbstractBidiMapDecorator implements OrderedBidiMap {
/*    */   protected AbstractOrderedBidiMapDecorator(OrderedBidiMap map) {
/* 49 */     super((BidiMap)map);
/*    */   }
/*    */   
/*    */   protected OrderedBidiMap getOrderedBidiMap() {
/* 58 */     return (OrderedBidiMap)this.map;
/*    */   }
/*    */   
/*    */   public OrderedMapIterator orderedMapIterator() {
/* 63 */     return getOrderedBidiMap().orderedMapIterator();
/*    */   }
/*    */   
/*    */   public Object firstKey() {
/* 67 */     return getOrderedBidiMap().firstKey();
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 71 */     return getOrderedBidiMap().lastKey();
/*    */   }
/*    */   
/*    */   public Object nextKey(Object key) {
/* 75 */     return getOrderedBidiMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public Object previousKey(Object key) {
/* 79 */     return getOrderedBidiMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 83 */     return getOrderedBidiMap().inverseOrderedBidiMap();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\AbstractOrderedBidiMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */