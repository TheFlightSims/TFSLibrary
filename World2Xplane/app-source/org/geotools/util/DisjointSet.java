/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DisjointSet<E> extends AbstractSet<E> implements Serializable {
/*     */   private static final long serialVersionUID = -7933552571588598563L;
/*     */   
/*     */   private final Map<E, DisjointSet<E>> map;
/*     */   
/*     */   private final DisjointSet<E> trash;
/*     */   
/*     */   public DisjointSet() {
/* 110 */     this(false);
/*     */   }
/*     */   
/*     */   public DisjointSet(boolean hasTrash) {
/* 126 */     this.map = new LinkedHashMap<E, DisjointSet<E>>();
/* 127 */     this.trash = hasTrash ? new DisjointSet(this.map) : null;
/*     */   }
/*     */   
/*     */   public DisjointSet(DisjointSet<E> disjointSet) {
/* 141 */     this.map = disjointSet.map;
/* 142 */     this.trash = disjointSet.trash;
/*     */   }
/*     */   
/*     */   private DisjointSet(Map<E, DisjointSet<E>> map) {
/* 149 */     this.map = map;
/* 150 */     this.trash = null;
/*     */   }
/*     */   
/*     */   public Set<E> getTrash() {
/* 160 */     return this.trash;
/*     */   }
/*     */   
/*     */   public int size() {
/* 168 */     synchronized (this.map) {
/* 169 */       int count = 0;
/* 170 */       for (Iterator<DisjointSet<E>> it = this.map.values().iterator(); it.hasNext();) {
/* 171 */         if (it.next() == this)
/* 172 */           count++; 
/*     */       } 
/* 175 */       return count;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/* 187 */     synchronized (this.map) {
/* 188 */       return (this.map.get(element) == this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(E element) {
/* 202 */     synchronized (this.map) {
/* 203 */       return (this.map.put(element, this) != this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/* 217 */     synchronized (this.map) {
/* 218 */       if (this.map.get(element) != this)
/* 219 */         return false; 
/* 220 */       if (this.trash != null) {
/* 223 */         DisjointSet<E> old = this.map.put((E)element, this.trash);
/* 224 */         return (old != this.trash);
/*     */       } 
/* 227 */       return (this.map.remove(element) != null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 242 */     synchronized (this.map) {
/* 243 */       return super.containsAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/* 256 */     synchronized (this.map) {
/* 257 */       return super.addAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 271 */     synchronized (this.map) {
/* 272 */       return super.removeAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 286 */     synchronized (this.map) {
/* 287 */       return super.retainAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 297 */     synchronized (this.map) {
/* 298 */       super.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 306 */     synchronized (this.map) {
/* 307 */       return new Iter();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 318 */     synchronized (this.map) {
/* 319 */       return super.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 334 */     synchronized (this.map) {
/* 335 */       return (T[])super.toArray((Object[])a);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 344 */     synchronized (this.map) {
/* 345 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 354 */     synchronized (this.map) {
/* 355 */       return super.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object set) {
/* 366 */     synchronized (this.map) {
/* 367 */       return super.equals(set);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator<E> {
/* 403 */     private final Iterator<Map.Entry<E, DisjointSet<E>>> iterator = DisjointSet.this.map.entrySet().iterator();
/*     */     
/*     */     private Map.Entry<E, DisjointSet<E>> prefetch;
/*     */     
/*     */     private Map.Entry<E, DisjointSet<E>> toRemove;
/*     */     
/*     */     private Map.Entry<E, DisjointSet<E>> prefetch() {
/* 412 */       this.toRemove = null;
/* 413 */       if (this.prefetch == null)
/* 414 */         while (this.iterator.hasNext()) {
/* 415 */           Map.Entry<E, DisjointSet<E>> next = this.iterator.next();
/* 416 */           if (next.getValue() == DisjointSet.this) {
/* 417 */             this.prefetch = next;
/*     */             break;
/*     */           } 
/*     */         }  
/* 422 */       return this.prefetch;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 429 */       return (prefetch() != null);
/*     */     }
/*     */     
/*     */     public E next() {
/* 436 */       this.toRemove = prefetch();
/* 437 */       this.prefetch = null;
/* 438 */       if (this.toRemove != null)
/* 439 */         return this.toRemove.getKey(); 
/* 441 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 449 */       if (this.toRemove != null) {
/* 450 */         if (DisjointSet.this.trash != null) {
/* 452 */           this.toRemove.setValue(DisjointSet.this.trash);
/*     */         } else {
/* 454 */           this.iterator.remove();
/*     */         } 
/* 456 */         this.toRemove = null;
/*     */       } else {
/* 458 */         throw new IllegalStateException();
/*     */       } 
/*     */     }
/*     */     
/*     */     private Iter() {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DisjointSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */