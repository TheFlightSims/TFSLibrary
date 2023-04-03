/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ public final class UnmodifiableSortedSet extends AbstractSortedSetDecorator implements Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = -725356885467962424L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set) {
/*  54 */     if (set instanceof Unmodifiable)
/*  55 */       return set; 
/*  57 */     return new UnmodifiableSortedSet(set);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  68 */     out.defaultWriteObject();
/*  69 */     out.writeObject(this.collection);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  80 */     in.defaultReadObject();
/*  81 */     this.collection = (Collection)in.readObject();
/*     */   }
/*     */   
/*     */   private UnmodifiableSortedSet(SortedSet set) {
/*  92 */     super(set);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  97 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/* 126 */     SortedSet sub = getSortedSet().subSet(fromElement, toElement);
/* 127 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 131 */     SortedSet sub = getSortedSet().headSet(toElement);
/* 132 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 136 */     SortedSet sub = getSortedSet().tailSet(fromElement);
/* 137 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\UnmodifiableSortedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */