/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.SortedSet;
/*    */ 
/*    */ public abstract class AbstractSortedSetDecorator extends AbstractSetDecorator implements SortedSet {
/*    */   protected AbstractSortedSetDecorator() {}
/*    */   
/*    */   protected AbstractSortedSetDecorator(Set set) {
/* 50 */     super(set);
/*    */   }
/*    */   
/*    */   protected SortedSet getSortedSet() {
/* 59 */     return (SortedSet)getCollection();
/*    */   }
/*    */   
/*    */   public SortedSet subSet(Object fromElement, Object toElement) {
/* 64 */     return getSortedSet().subSet(fromElement, toElement);
/*    */   }
/*    */   
/*    */   public SortedSet headSet(Object toElement) {
/* 68 */     return getSortedSet().headSet(toElement);
/*    */   }
/*    */   
/*    */   public SortedSet tailSet(Object fromElement) {
/* 72 */     return getSortedSet().tailSet(fromElement);
/*    */   }
/*    */   
/*    */   public Object first() {
/* 76 */     return getSortedSet().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 80 */     return getSortedSet().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 84 */     return getSortedSet().comparator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\AbstractSortedSetDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */