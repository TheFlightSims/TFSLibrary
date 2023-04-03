/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class PredicatedSortedSet extends PredicatedSet implements SortedSet {
/*     */   private static final long serialVersionUID = -9110948148132275052L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set, Predicate predicate) {
/*  60 */     return new PredicatedSortedSet(set, predicate);
/*     */   }
/*     */   
/*     */   protected PredicatedSortedSet(SortedSet set, Predicate predicate) {
/*  76 */     super(set, predicate);
/*     */   }
/*     */   
/*     */   private SortedSet getSortedSet() {
/*  85 */     return (SortedSet)getCollection();
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  90 */     SortedSet sub = getSortedSet().subSet(fromElement, toElement);
/*  91 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/*  95 */     SortedSet sub = getSortedSet().headSet(toElement);
/*  96 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 100 */     SortedSet sub = getSortedSet().tailSet(fromElement);
/* 101 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public Object first() {
/* 105 */     return getSortedSet().first();
/*     */   }
/*     */   
/*     */   public Object last() {
/* 109 */     return getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 113 */     return getSortedSet().comparator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\PredicatedSortedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */