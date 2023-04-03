/*    */ package org.apache.commons.collections.map;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ 
/*    */ public abstract class AbstractSortedMapDecorator extends AbstractMapDecorator implements SortedMap {
/*    */   protected AbstractSortedMapDecorator() {}
/*    */   
/*    */   public AbstractSortedMapDecorator(SortedMap map) {
/* 57 */     super(map);
/*    */   }
/*    */   
/*    */   protected SortedMap getSortedMap() {
/* 66 */     return (SortedMap)this.map;
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 71 */     return getSortedMap().comparator();
/*    */   }
/*    */   
/*    */   public Object firstKey() {
/* 75 */     return getSortedMap().firstKey();
/*    */   }
/*    */   
/*    */   public SortedMap headMap(Object toKey) {
/* 79 */     return getSortedMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 83 */     return getSortedMap().lastKey();
/*    */   }
/*    */   
/*    */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 87 */     return getSortedMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap tailMap(Object fromKey) {
/* 91 */     return getSortedMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractSortedMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */