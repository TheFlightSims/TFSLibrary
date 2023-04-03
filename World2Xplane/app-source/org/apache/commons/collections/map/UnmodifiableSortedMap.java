/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableSortedMap extends AbstractSortedMapDecorator implements Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = 5805344239827376360L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map) {
/*  57 */     if (map instanceof Unmodifiable)
/*  58 */       return map; 
/*  60 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   private UnmodifiableSortedMap(SortedMap map) {
/*  71 */     super(map);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  83 */     out.defaultWriteObject();
/*  84 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  96 */     in.defaultReadObject();
/*  97 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 102 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 106 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 110 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 114 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 118 */     Set set = super.entrySet();
/* 119 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 123 */     Set set = super.keySet();
/* 124 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 128 */     Collection coll = super.values();
/* 129 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 134 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 138 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 142 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 146 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 147 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 151 */     SortedMap map = getSortedMap().headMap(toKey);
/* 152 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 156 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 157 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\UnmodifiableSortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */