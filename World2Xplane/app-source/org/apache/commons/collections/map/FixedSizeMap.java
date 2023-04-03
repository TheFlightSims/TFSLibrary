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
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public class FixedSizeMap extends AbstractMapDecorator implements Map, BoundedMap, Serializable {
/*     */   private static final long serialVersionUID = 7450927208116179316L;
/*     */   
/*     */   public static Map decorate(Map map) {
/*  73 */     return new FixedSizeMap(map);
/*     */   }
/*     */   
/*     */   protected FixedSizeMap(Map map) {
/*  84 */     super(map);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  96 */     out.defaultWriteObject();
/*  97 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 109 */     in.defaultReadObject();
/* 110 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 115 */     if (!this.map.containsKey(key))
/* 116 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size"); 
/* 118 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 122 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 123 */       if (!mapToCopy.containsKey(it.next()))
/* 124 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size"); 
/*     */     } 
/* 127 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 131 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 135 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 139 */     Set set = this.map.entrySet();
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
/*     */   public boolean isFull() {
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 159 */     return size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\FixedSizeMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */