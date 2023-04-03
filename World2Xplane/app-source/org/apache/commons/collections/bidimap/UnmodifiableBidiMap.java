/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ import org.apache.commons.collections.map.UnmodifiableEntrySet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableBidiMap extends AbstractBidiMapDecorator implements Unmodifiable {
/*     */   private UnmodifiableBidiMap inverse;
/*     */   
/*     */   public static BidiMap decorate(BidiMap map) {
/*  55 */     if (map instanceof Unmodifiable)
/*  56 */       return map; 
/*  58 */     return new UnmodifiableBidiMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableBidiMap(BidiMap map) {
/*  69 */     super(map);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  90 */     Set set = super.entrySet();
/*  91 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  95 */     Set set = super.keySet();
/*  96 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 100 */     Collection coll = super.values();
/* 101 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public Object removeValue(Object value) {
/* 106 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 110 */     MapIterator it = getBidiMap().mapIterator();
/* 111 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 115 */     if (this.inverse == null) {
/* 116 */       this.inverse = new UnmodifiableBidiMap(getBidiMap().inverseBidiMap());
/* 117 */       this.inverse.inverse = this;
/*     */     } 
/* 119 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\UnmodifiableBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */