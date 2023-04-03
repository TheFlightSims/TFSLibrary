/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class LinkedMap extends AbstractLinkedMap implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 9077234323521161066L;
/*     */   
/*     */   public LinkedMap() {
/*  75 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */   public LinkedMap(int initialCapacity) {
/*  85 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public LinkedMap(int initialCapacity, float loadFactor) {
/*  98 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public LinkedMap(Map map) {
/* 108 */     super(map);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 118 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 125 */     out.defaultWriteObject();
/* 126 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 133 */     in.defaultReadObject();
/* 134 */     doReadObject(in);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 146 */     return getEntry(index).getKey();
/*     */   }
/*     */   
/*     */   public Object getValue(int index) {
/* 157 */     return getEntry(index).getValue();
/*     */   }
/*     */   
/*     */   public int indexOf(Object key) {
/* 167 */     key = convertKey(key);
/* 168 */     int i = 0;
/* 169 */     for (AbstractLinkedMap.LinkEntry entry = this.header.after; entry != this.header; entry = entry.after, i++) {
/* 170 */       if (isEqualKey(key, entry.key))
/* 171 */         return i; 
/*     */     } 
/* 174 */     return -1;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 186 */     return remove(get(index));
/*     */   }
/*     */   
/*     */   public List asList() {
/* 205 */     return new LinkedMapList(this);
/*     */   }
/*     */   
/*     */   static class LinkedMapList extends AbstractList {
/*     */     final LinkedMap parent;
/*     */     
/*     */     LinkedMapList(LinkedMap parent) {
/* 216 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 220 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public Object get(int index) {
/* 224 */       return this.parent.get(index);
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 228 */       return this.parent.containsKey(obj);
/*     */     }
/*     */     
/*     */     public int indexOf(Object obj) {
/* 232 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public int lastIndexOf(Object obj) {
/* 236 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection coll) {
/* 240 */       return this.parent.keySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public Object remove(int index) {
/* 244 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 248 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection coll) {
/* 252 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection coll) {
/* 256 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 260 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 264 */       return this.parent.keySet().toArray();
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] array) {
/* 268 */       return this.parent.keySet().toArray(array);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 272 */       return UnmodifiableIterator.decorate(this.parent.keySet().iterator());
/*     */     }
/*     */     
/*     */     public ListIterator listIterator() {
/* 276 */       return UnmodifiableListIterator.decorate(super.listIterator());
/*     */     }
/*     */     
/*     */     public ListIterator listIterator(int fromIndex) {
/* 280 */       return UnmodifiableListIterator.decorate(super.listIterator(fromIndex));
/*     */     }
/*     */     
/*     */     public List subList(int fromIndexInclusive, int toIndexExclusive) {
/* 284 */       return UnmodifiableList.decorate(super.subList(fromIndexInclusive, toIndexExclusive));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\LinkedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */