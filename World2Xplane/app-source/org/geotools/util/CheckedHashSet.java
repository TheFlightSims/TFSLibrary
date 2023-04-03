/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class CheckedHashSet<E> extends LinkedHashSet<E> implements CheckedCollection<E>, Cloneable {
/*     */   private static final long serialVersionUID = -9014541457174735097L;
/*     */   
/*     */   private final Class<E> type;
/*     */   
/*     */   public CheckedHashSet(Class<E> type) {
/*  69 */     this.type = type;
/*  70 */     ensureNonNull();
/*     */   }
/*     */   
/*     */   public CheckedHashSet(Class<E> type, int capacity) {
/*  82 */     super(capacity);
/*  83 */     this.type = type;
/*  84 */     ensureNonNull();
/*     */   }
/*     */   
/*     */   private void ensureNonNull() {
/*  91 */     if (this.type == null)
/*  92 */       throw new IllegalArgumentException(Errors.format(143, "type")); 
/*     */   }
/*     */   
/*     */   public Class<E> getElementType() {
/* 102 */     return this.type;
/*     */   }
/*     */   
/*     */   protected void ensureValidType(E element) throws IllegalArgumentException {
/* 113 */     if (element != null && !this.type.isInstance(element))
/* 114 */       throw new IllegalArgumentException(Errors.format(61, element.getClass(), this.type)); 
/*     */   }
/*     */   
/*     */   private void ensureValid(Collection<? extends E> collection) throws IllegalArgumentException {
/* 126 */     if (collection != null)
/* 127 */       for (E element : collection)
/* 128 */         ensureValidType(element);  
/*     */   }
/*     */   
/*     */   protected void checkWritePermission() throws UnsupportedOperationException {}
/*     */   
/*     */   protected Object getLock() {
/* 157 */     return this;
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 165 */     Object lock = getLock();
/* 166 */     synchronized (lock) {
/* 167 */       return new SynchronizedIterator<E>(super.iterator(), lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 176 */     synchronized (getLock()) {
/* 177 */       return super.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 186 */     synchronized (getLock()) {
/* 187 */       return super.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 196 */     synchronized (getLock()) {
/* 197 */       return super.contains(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(E element) throws IllegalArgumentException, UnsupportedOperationException {
/* 213 */     ensureValidType(element);
/* 214 */     synchronized (getLock()) {
/* 215 */       checkWritePermission();
/* 216 */       return super.add(element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> collection) throws IllegalArgumentException, UnsupportedOperationException {
/* 232 */     ensureValid(collection);
/* 233 */     synchronized (getLock()) {
/* 234 */       checkWritePermission();
/* 235 */       return super.addAll(collection);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) throws UnsupportedOperationException {
/* 246 */     synchronized (getLock()) {
/* 247 */       checkWritePermission();
/* 248 */       return super.remove(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
/* 259 */     synchronized (getLock()) {
/* 260 */       checkWritePermission();
/* 261 */       return super.removeAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
/* 272 */     synchronized (getLock()) {
/* 273 */       checkWritePermission();
/* 274 */       return super.retainAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() throws UnsupportedOperationException {
/* 285 */     synchronized (getLock()) {
/* 286 */       checkWritePermission();
/* 287 */       super.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 296 */     synchronized (getLock()) {
/* 297 */       return super.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 308 */     synchronized (getLock()) {
/* 309 */       return (T[])super.toArray((Object[])a);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 318 */     synchronized (getLock()) {
/* 319 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 328 */     synchronized (getLock()) {
/* 329 */       return super.equals(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 338 */     synchronized (getLock()) {
/* 339 */       return super.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public CheckedHashSet<E> clone() {
/* 351 */     synchronized (getLock()) {
/* 352 */       return (CheckedHashSet<E>)super.clone();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CheckedHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */