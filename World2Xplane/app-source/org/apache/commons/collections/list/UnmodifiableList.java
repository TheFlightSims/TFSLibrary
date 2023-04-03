/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ 
/*     */ public final class UnmodifiableList extends AbstractSerializableListDecorator implements Unmodifiable {
/*     */   private static final long serialVersionUID = 6595182819922443652L;
/*     */   
/*     */   public static List decorate(List list) {
/*  52 */     if (list instanceof Unmodifiable)
/*  53 */       return list; 
/*  55 */     return new UnmodifiableList(list);
/*     */   }
/*     */   
/*     */   private UnmodifiableList(List list) {
/*  66 */     super(list);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  71 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  75 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  87 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 100 */     return UnmodifiableListIterator.decorate(getList().listIterator());
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 104 */     return UnmodifiableListIterator.decorate(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 124 */     List sub = getList().subList(fromIndex, toIndex);
/* 125 */     return new UnmodifiableList(sub);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\UnmodifiableList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */