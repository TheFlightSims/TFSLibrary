/*     */ package org.openstreetmap.osmosis.core.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class CollectionWrapper<E> implements Collection<E> {
/*     */   private Collection<E> wrappedCollection;
/*     */   
/*     */   public CollectionWrapper(Collection<E> wrappedCollection) {
/*  31 */     this.wrappedCollection = wrappedCollection;
/*     */   }
/*     */   
/*     */   public boolean add(E e) {
/*  40 */     return this.wrappedCollection.add(e);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/*  49 */     return this.wrappedCollection.addAll(c);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  58 */     this.wrappedCollection.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  67 */     return this.wrappedCollection.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  76 */     return this.wrappedCollection.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  85 */     return this.wrappedCollection.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/*  94 */     return this.wrappedCollection.iterator();
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 103 */     return this.wrappedCollection.remove(o);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 112 */     return this.wrappedCollection.removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 121 */     return this.wrappedCollection.retainAll(c);
/*     */   }
/*     */   
/*     */   public int size() {
/* 130 */     return this.wrappedCollection.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 139 */     return this.wrappedCollection.toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 148 */     return this.wrappedCollection.toArray(a);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\CollectionWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */