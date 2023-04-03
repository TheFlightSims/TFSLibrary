/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class PredicatedSortedMap extends PredicatedMap implements SortedMap {
/*     */   private static final long serialVersionUID = 3359846175935304332L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Predicate keyPredicate, Predicate valuePredicate) {
/*  68 */     return new PredicatedSortedMap(map, keyPredicate, valuePredicate);
/*     */   }
/*     */   
/*     */   protected PredicatedSortedMap(SortedMap map, Predicate keyPredicate, Predicate valuePredicate) {
/*  81 */     super(map, keyPredicate, valuePredicate);
/*     */   }
/*     */   
/*     */   protected SortedMap getSortedMap() {
/*  91 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/*  96 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 100 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 104 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 108 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 109 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 113 */     SortedMap map = getSortedMap().headMap(toKey);
/* 114 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 118 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 119 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\PredicatedSortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */