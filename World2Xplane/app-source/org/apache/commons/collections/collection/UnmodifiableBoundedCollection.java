/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ public final class UnmodifiableBoundedCollection extends AbstractSerializableCollectionDecorator implements BoundedCollection {
/*     */   private static final long serialVersionUID = -7112672385450340330L;
/*     */   
/*     */   public static BoundedCollection decorate(BoundedCollection coll) {
/*  57 */     return new UnmodifiableBoundedCollection(coll);
/*     */   }
/*     */   
/*     */   public static BoundedCollection decorateUsing(Collection coll) {
/*  71 */     if (coll == null)
/*  72 */       throw new IllegalArgumentException("The collection must not be null"); 
/*  76 */     for (int i = 0; i < 1000 && 
/*  77 */       !(coll instanceof BoundedCollection); i++) {
/*  79 */       if (coll instanceof AbstractCollectionDecorator) {
/*  80 */         coll = ((AbstractCollectionDecorator)coll).collection;
/*  81 */       } else if (coll instanceof SynchronizedCollection) {
/*  82 */         coll = ((SynchronizedCollection)coll).collection;
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */     } 
/*  88 */     if (!(coll instanceof BoundedCollection))
/*  89 */       throw new IllegalArgumentException("The collection is not a bounded collection"); 
/*  91 */     return new UnmodifiableBoundedCollection((BoundedCollection)coll);
/*     */   }
/*     */   
/*     */   private UnmodifiableBoundedCollection(BoundedCollection coll) {
/* 101 */     super((Collection)coll);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 106 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 110 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 114 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 130 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 135 */     return ((BoundedCollection)this.collection).isFull();
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 139 */     return ((BoundedCollection)this.collection).maxSize();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\UnmodifiableBoundedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */