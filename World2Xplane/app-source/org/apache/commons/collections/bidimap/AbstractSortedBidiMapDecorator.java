/*    */ package org.apache.commons.collections.bidimap;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ import org.apache.commons.collections.OrderedBidiMap;
/*    */ import org.apache.commons.collections.SortedBidiMap;
/*    */ 
/*    */ public abstract class AbstractSortedBidiMapDecorator extends AbstractOrderedBidiMapDecorator implements SortedBidiMap {
/*    */   public AbstractSortedBidiMapDecorator(SortedBidiMap map) {
/* 51 */     super((OrderedBidiMap)map);
/*    */   }
/*    */   
/*    */   protected SortedBidiMap getSortedBidiMap() {
/* 60 */     return (SortedBidiMap)this.map;
/*    */   }
/*    */   
/*    */   public SortedBidiMap inverseSortedBidiMap() {
/* 65 */     return getSortedBidiMap().inverseSortedBidiMap();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 69 */     return getSortedBidiMap().comparator();
/*    */   }
/*    */   
/*    */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 73 */     return getSortedBidiMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap headMap(Object toKey) {
/* 77 */     return getSortedBidiMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public SortedMap tailMap(Object fromKey) {
/* 81 */     return getSortedBidiMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\AbstractSortedBidiMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */