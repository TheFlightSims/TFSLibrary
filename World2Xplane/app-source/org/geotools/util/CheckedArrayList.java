/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class CheckedArrayList<E> extends ArrayList<E> implements CheckedCollection<E>, Cloneable {
/*     */   private static final long serialVersionUID = -587331971085094268L;
/*     */   
/*     */   private final Class<E> type;
/*     */   
/*     */   public CheckedArrayList(Class<E> type) {
/*  69 */     this.type = type;
/*  70 */     ensureNonNull();
/*     */   }
/*     */   
/*     */   public CheckedArrayList(Class<E> type, int capacity) {
/*  82 */     super(capacity);
/*  83 */     this.type = type;
/*  84 */     ensureNonNull();
/*     */   }
/*     */   
/*     */   public Class<E> getElementType() {
/*  93 */     return this.type;
/*     */   }
/*     */   
/*     */   private void ensureNonNull() {
/* 100 */     if (this.type == null)
/* 101 */       throw new IllegalArgumentException(Errors.format(143, "type")); 
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
/* 181 */     synchronized (getLock()) {
/* 182 */       return super.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 191 */     synchronized (getLock()) {
/* 192 */       return super.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 201 */     synchronized (getLock()) {
/* 202 */       return super.contains(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(Object o) {
/* 212 */     synchronized (getLock()) {
/* 213 */       return super.indexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 223 */     synchronized (getLock()) {
/* 224 */       return super.lastIndexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public E get(int index) {
/* 233 */     synchronized (getLock()) {
/* 234 */       return super.get(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public E set(int index, E element) throws IllegalArgumentException, UnsupportedOperationException {
/* 252 */     ensureValidType(element);
/* 253 */     synchronized (getLock()) {
/* 254 */       checkWritePermission();
/* 255 */       return super.set(index, element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(E element) throws IllegalArgumentException, UnsupportedOperationException {
/* 271 */     ensureValidType(element);
/* 272 */     synchronized (getLock()) {
/* 273 */       checkWritePermission();
/* 274 */       return super.add(element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(int index, E element) throws IllegalArgumentException, UnsupportedOperationException {
/* 291 */     ensureValidType(element);
/* 292 */     synchronized (getLock()) {
/* 293 */       checkWritePermission();
/* 294 */       super.add(index, element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> collection) throws IllegalArgumentException, UnsupportedOperationException {
/* 311 */     ensureValid(collection);
/* 312 */     synchronized (getLock()) {
/* 313 */       checkWritePermission();
/* 314 */       return super.addAll(collection);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> collection) throws IllegalArgumentException, UnsupportedOperationException {
/* 332 */     ensureValid(collection);
/* 333 */     synchronized (getLock()) {
/* 334 */       checkWritePermission();
/* 335 */       return super.addAll(index, collection);
/*     */     } 
/*     */   }
/*     */   
/*     */   public E remove(int index) throws UnsupportedOperationException {
/* 346 */     synchronized (getLock()) {
/* 347 */       checkWritePermission();
/* 348 */       return super.remove(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) throws UnsupportedOperationException {
/* 359 */     synchronized (getLock()) {
/* 360 */       checkWritePermission();
/* 361 */       return super.remove(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
/* 372 */     synchronized (getLock()) {
/* 373 */       checkWritePermission();
/* 374 */       return super.removeAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
/* 385 */     synchronized (getLock()) {
/* 386 */       checkWritePermission();
/* 387 */       return super.retainAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void trimToSize() {
/* 396 */     synchronized (getLock()) {
/* 397 */       super.trimToSize();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ensureCapacity(int minCapacity) {
/* 407 */     synchronized (getLock()) {
/* 408 */       super.ensureCapacity(minCapacity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() throws UnsupportedOperationException {
/* 419 */     synchronized (getLock()) {
/* 420 */       checkWritePermission();
/* 421 */       super.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 430 */     synchronized (getLock()) {
/* 431 */       return super.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 442 */     synchronized (getLock()) {
/* 443 */       return super.toArray(a);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 452 */     synchronized (getLock()) {
/* 453 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 462 */     synchronized (getLock()) {
/* 463 */       return super.equals(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 472 */     synchronized (getLock()) {
/* 473 */       return super.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public CheckedArrayList<E> clone() {
/* 485 */     synchronized (getLock()) {
/* 486 */       return (CheckedArrayList<E>)super.clone();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CheckedArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */