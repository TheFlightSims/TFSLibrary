/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class MapBackedSet implements Set, Serializable {
/*     */   private static final long serialVersionUID = 6723912213766056587L;
/*     */   
/*     */   protected final Map map;
/*     */   
/*     */   protected final Object dummyValue;
/*     */   
/*     */   public static Set decorate(Map map) {
/*  57 */     return decorate(map, null);
/*     */   }
/*     */   
/*     */   public static Set decorate(Map map, Object dummyValue) {
/*  68 */     if (map == null)
/*  69 */       throw new IllegalArgumentException("The map must not be null"); 
/*  71 */     return new MapBackedSet(map, dummyValue);
/*     */   }
/*     */   
/*     */   private MapBackedSet(Map map, Object dummyValue) {
/*  84 */     this.map = map;
/*  85 */     this.dummyValue = dummyValue;
/*     */   }
/*     */   
/*     */   public int size() {
/*  90 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  94 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  98 */     return this.map.keySet().iterator();
/*     */   }
/*     */   
/*     */   public boolean contains(Object obj) {
/* 102 */     return this.map.containsKey(obj);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 106 */     return this.map.keySet().containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 110 */     int size = this.map.size();
/* 111 */     this.map.put(obj, this.dummyValue);
/* 112 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 116 */     int size = this.map.size();
/* 117 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 118 */       Object obj = it.next();
/* 119 */       this.map.put(obj, this.dummyValue);
/*     */     } 
/* 121 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 125 */     int size = this.map.size();
/* 126 */     this.map.remove(obj);
/* 127 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 131 */     return this.map.keySet().removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 135 */     return this.map.keySet().retainAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 139 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 143 */     return this.map.keySet().toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 147 */     return this.map.keySet().toArray(array);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 151 */     return this.map.keySet().equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 155 */     return this.map.keySet().hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\MapBackedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */