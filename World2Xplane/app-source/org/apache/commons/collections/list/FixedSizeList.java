/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ public class FixedSizeList extends AbstractSerializableListDecorator implements BoundedCollection {
/*     */   private static final long serialVersionUID = -2218010673611160319L;
/*     */   
/*     */   public static List decorate(List list) {
/*  56 */     return new FixedSizeList(list);
/*     */   }
/*     */   
/*     */   protected FixedSizeList(List list) {
/*  67 */     super(list);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  72 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/*  76 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  80 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  84 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void clear() {
/*  88 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  92 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  96 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 100 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 104 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 108 */     return (ListIterator)new FixedSizeListIterator(getList().listIterator(0));
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 112 */     return (ListIterator)new FixedSizeListIterator(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 116 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 120 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 124 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 128 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 132 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 136 */     List sub = getList().subList(fromIndex, toIndex);
/* 137 */     return new FixedSizeList(sub);
/*     */   }
/*     */   
/*     */   static class FixedSizeListIterator extends AbstractListIteratorDecorator {
/*     */     protected FixedSizeListIterator(ListIterator iterator) {
/* 145 */       super(iterator);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 148 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 151 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 160 */     return size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\FixedSizeList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */