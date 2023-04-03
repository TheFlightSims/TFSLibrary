/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.SortedBag;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public final class UnmodifiableSortedBag extends AbstractSortedBagDecorator implements Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = -3190437252665717841L;
/*     */   
/*     */   public static SortedBag decorate(SortedBag bag) {
/*  58 */     if (bag instanceof Unmodifiable)
/*  59 */       return bag; 
/*  61 */     return new UnmodifiableSortedBag(bag);
/*     */   }
/*     */   
/*     */   private UnmodifiableSortedBag(SortedBag bag) {
/*  72 */     super(bag);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  83 */     out.defaultWriteObject();
/*  84 */     out.writeObject(this.collection);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  95 */     in.defaultReadObject();
/*  96 */     this.collection = (Collection)in.readObject();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 101 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int count) {
/* 130 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int count) {
/* 134 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 138 */     Set set = getBag().uniqueSet();
/* 139 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\UnmodifiableSortedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */