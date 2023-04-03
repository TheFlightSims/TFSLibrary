/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.IterableMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.EntrySetMapIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableMap extends AbstractMapDecorator implements IterableMap, Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = 2737023427269031941L;
/*     */   
/*     */   public static Map decorate(Map map) {
/*  59 */     if (map instanceof Unmodifiable)
/*  60 */       return map; 
/*  62 */     return new UnmodifiableMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableMap(Map map) {
/*  73 */     super(map);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  85 */     out.defaultWriteObject();
/*  86 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  98 */     in.defaultReadObject();
/*  99 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 120 */     if (this.map instanceof IterableMap) {
/* 121 */       MapIterator it = ((IterableMap)this.map).mapIterator();
/* 122 */       return UnmodifiableMapIterator.decorate(it);
/*     */     } 
/* 124 */     EntrySetMapIterator entrySetMapIterator = new EntrySetMapIterator(this.map);
/* 125 */     return UnmodifiableMapIterator.decorate((MapIterator)entrySetMapIterator);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 130 */     Set set = super.entrySet();
/* 131 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 135 */     Set set = super.keySet();
/* 136 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 140 */     Collection coll = super.values();
/* 141 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\UnmodifiableMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */