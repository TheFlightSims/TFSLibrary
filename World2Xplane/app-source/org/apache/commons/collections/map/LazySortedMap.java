/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class LazySortedMap extends LazyMap implements SortedMap {
/*     */   private static final long serialVersionUID = 2715322183617658933L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Factory factory) {
/*  76 */     return new LazySortedMap(map, factory);
/*     */   }
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Transformer factory) {
/*  87 */     return new LazySortedMap(map, factory);
/*     */   }
/*     */   
/*     */   protected LazySortedMap(SortedMap map, Factory factory) {
/*  99 */     super(map, factory);
/*     */   }
/*     */   
/*     */   protected LazySortedMap(SortedMap map, Transformer factory) {
/* 110 */     super(map, factory);
/*     */   }
/*     */   
/*     */   protected SortedMap getSortedMap() {
/* 120 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 125 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 129 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 133 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 137 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 138 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 142 */     SortedMap map = getSortedMap().headMap(toKey);
/* 143 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 147 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 148 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\LazySortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */