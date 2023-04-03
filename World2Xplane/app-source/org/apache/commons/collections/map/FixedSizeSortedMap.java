/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public class FixedSizeSortedMap extends AbstractSortedMapDecorator implements SortedMap, BoundedMap, Serializable {
/*     */   private static final long serialVersionUID = 3126019624511683653L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map) {
/*  74 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   protected FixedSizeSortedMap(SortedMap map) {
/*  85 */     super(map);
/*     */   }
/*     */   
/*     */   protected SortedMap getSortedMap() {
/*  94 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 102 */     out.defaultWriteObject();
/* 103 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 110 */     in.defaultReadObject();
/* 111 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 116 */     if (!this.map.containsKey(key))
/* 117 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size"); 
/* 119 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 123 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 124 */       if (!mapToCopy.containsKey(it.next()))
/* 125 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size"); 
/*     */     } 
/* 128 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 132 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 136 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 140 */     Set set = this.map.entrySet();
/* 141 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 145 */     Set set = this.map.keySet();
/* 146 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 150 */     Collection coll = this.map.values();
/* 151 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 156 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 157 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 161 */     SortedMap map = getSortedMap().headMap(toKey);
/* 162 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 166 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 167 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 175 */     return size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\FixedSizeSortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */