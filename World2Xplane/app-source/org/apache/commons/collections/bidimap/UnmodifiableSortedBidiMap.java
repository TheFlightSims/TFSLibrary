/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.SortedBidiMap;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections.map.UnmodifiableEntrySet;
/*     */ import org.apache.commons.collections.map.UnmodifiableSortedMap;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableSortedBidiMap extends AbstractSortedBidiMapDecorator implements Unmodifiable {
/*     */   private UnmodifiableSortedBidiMap inverse;
/*     */   
/*     */   public static SortedBidiMap decorate(SortedBidiMap map) {
/*  60 */     if (map instanceof Unmodifiable)
/*  61 */       return map; 
/*  63 */     return new UnmodifiableSortedBidiMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableSortedBidiMap(SortedBidiMap map) {
/*  74 */     super(map);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  87 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  95 */     Set set = super.entrySet();
/*  96 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 100 */     Set set = super.keySet();
/* 101 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 105 */     Collection coll = super.values();
/* 106 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public Object removeValue(Object value) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 115 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 119 */     return (BidiMap)inverseSortedBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 124 */     OrderedMapIterator it = getSortedBidiMap().orderedMapIterator();
/* 125 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 129 */     return (OrderedBidiMap)inverseSortedBidiMap();
/*     */   }
/*     */   
/*     */   public SortedBidiMap inverseSortedBidiMap() {
/* 134 */     if (this.inverse == null) {
/* 135 */       this.inverse = new UnmodifiableSortedBidiMap(getSortedBidiMap().inverseSortedBidiMap());
/* 136 */       this.inverse.inverse = this;
/*     */     } 
/* 138 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 142 */     SortedMap sm = getSortedBidiMap().subMap(fromKey, toKey);
/* 143 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 147 */     SortedMap sm = getSortedBidiMap().headMap(toKey);
/* 148 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 152 */     SortedMap sm = getSortedBidiMap().tailMap(fromKey);
/* 153 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\UnmodifiableSortedBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */