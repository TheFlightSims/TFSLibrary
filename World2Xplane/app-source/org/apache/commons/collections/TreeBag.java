/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ public class TreeBag extends DefaultMapBag implements SortedBag {
/*    */   public TreeBag() {
/* 41 */     super(new TreeMap());
/*    */   }
/*    */   
/*    */   public TreeBag(Comparator comparator) {
/* 51 */     super(new TreeMap(comparator));
/*    */   }
/*    */   
/*    */   public TreeBag(Collection coll) {
/* 61 */     this();
/* 62 */     addAll(coll);
/*    */   }
/*    */   
/*    */   public Object first() {
/* 66 */     return ((SortedMap)getMap()).firstKey();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 70 */     return ((SortedMap)getMap()).lastKey();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 74 */     return ((SortedMap)getMap()).comparator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\TreeBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */