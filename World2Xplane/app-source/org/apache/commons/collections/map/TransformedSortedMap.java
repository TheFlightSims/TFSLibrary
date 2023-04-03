/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class TransformedSortedMap extends TransformedMap implements SortedMap {
/*     */   private static final long serialVersionUID = -8751771676410385778L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/*  66 */     return new TransformedSortedMap(map, keyTransformer, valueTransformer);
/*     */   }
/*     */   
/*     */   public static SortedMap decorateTransform(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/*  84 */     TransformedSortedMap decorated = new TransformedSortedMap(map, keyTransformer, valueTransformer);
/*  85 */     if (map.size() > 0) {
/*  86 */       Map transformed = decorated.transformMap(map);
/*  87 */       decorated.clear();
/*  88 */       decorated.getMap().putAll(transformed);
/*     */     } 
/*  90 */     return decorated;
/*     */   }
/*     */   
/*     */   protected TransformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/* 106 */     super(map, keyTransformer, valueTransformer);
/*     */   }
/*     */   
/*     */   protected SortedMap getSortedMap() {
/* 116 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 121 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 125 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 129 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 133 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 134 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 138 */     SortedMap map = getSortedMap().headMap(toKey);
/* 139 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 143 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 144 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\TransformedSortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */