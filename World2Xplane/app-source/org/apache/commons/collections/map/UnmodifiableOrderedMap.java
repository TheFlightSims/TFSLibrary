/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableOrderedMap extends AbstractOrderedMapDecorator implements Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = 8136428161720526266L;
/*     */   
/*     */   public static OrderedMap decorate(OrderedMap map) {
/*  60 */     if (map instanceof Unmodifiable)
/*  61 */       return map; 
/*  63 */     return new UnmodifiableOrderedMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableOrderedMap(OrderedMap map) {
/*  74 */     super(map);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  86 */     out.defaultWriteObject();
/*  87 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  99 */     in.defaultReadObject();
/* 100 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 105 */     MapIterator it = getOrderedMap().mapIterator();
/* 106 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 110 */     OrderedMapIterator it = getOrderedMap().orderedMapIterator();
/* 111 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 131 */     Set set = super.entrySet();
/* 132 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 136 */     Set set = super.keySet();
/* 137 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 141 */     Collection coll = super.values();
/* 142 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\UnmodifiableOrderedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */