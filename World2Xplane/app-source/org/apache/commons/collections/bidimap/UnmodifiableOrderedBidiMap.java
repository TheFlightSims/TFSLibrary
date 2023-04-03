/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections.map.UnmodifiableEntrySet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableOrderedBidiMap extends AbstractOrderedBidiMapDecorator implements Unmodifiable {
/*     */   private UnmodifiableOrderedBidiMap inverse;
/*     */   
/*     */   public static OrderedBidiMap decorate(OrderedBidiMap map) {
/*  57 */     if (map instanceof Unmodifiable)
/*  58 */       return map; 
/*  60 */     return new UnmodifiableOrderedBidiMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableOrderedBidiMap(OrderedBidiMap map) {
/*  71 */     super(map);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  76 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  88 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  92 */     Set set = super.entrySet();
/*  93 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  97 */     Set set = super.keySet();
/*  98 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 102 */     Collection coll = super.values();
/* 103 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public Object removeValue(Object value) {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 112 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 116 */     return (BidiMap)inverseOrderedBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 121 */     OrderedMapIterator it = getOrderedBidiMap().orderedMapIterator();
/* 122 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 126 */     if (this.inverse == null) {
/* 127 */       this.inverse = new UnmodifiableOrderedBidiMap(getOrderedBidiMap().inverseOrderedBidiMap());
/* 128 */       this.inverse.inverse = this;
/*     */     } 
/* 130 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\UnmodifiableOrderedBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */