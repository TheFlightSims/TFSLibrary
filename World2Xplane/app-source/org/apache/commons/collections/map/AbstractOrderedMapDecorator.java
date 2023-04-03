/*    */ package org.apache.commons.collections.map;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ import org.apache.commons.collections.OrderedMap;
/*    */ import org.apache.commons.collections.OrderedMapIterator;
/*    */ 
/*    */ public abstract class AbstractOrderedMapDecorator extends AbstractMapDecorator implements OrderedMap {
/*    */   protected AbstractOrderedMapDecorator() {}
/*    */   
/*    */   public AbstractOrderedMapDecorator(OrderedMap map) {
/* 58 */     super((Map)map);
/*    */   }
/*    */   
/*    */   protected OrderedMap getOrderedMap() {
/* 67 */     return (OrderedMap)this.map;
/*    */   }
/*    */   
/*    */   public Object firstKey() {
/* 72 */     return getOrderedMap().firstKey();
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 76 */     return getOrderedMap().lastKey();
/*    */   }
/*    */   
/*    */   public Object nextKey(Object key) {
/* 80 */     return getOrderedMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public Object previousKey(Object key) {
/* 84 */     return getOrderedMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public MapIterator mapIterator() {
/* 88 */     return getOrderedMap().mapIterator();
/*    */   }
/*    */   
/*    */   public OrderedMapIterator orderedMapIterator() {
/* 92 */     return getOrderedMap().orderedMapIterator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractOrderedMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */