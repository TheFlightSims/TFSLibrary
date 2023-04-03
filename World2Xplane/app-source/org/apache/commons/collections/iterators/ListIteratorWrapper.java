/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class ListIteratorWrapper implements ResettableListIterator {
/*     */   private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";
/*     */   
/*     */   private final Iterator iterator;
/*     */   
/*  54 */   private final List list = new ArrayList();
/*     */   
/*  57 */   private int currentIndex = 0;
/*     */   
/*  59 */   private int wrappedIteratorIndex = 0;
/*     */   
/*     */   public ListIteratorWrapper(Iterator iterator) {
/*  72 */     if (iterator == null)
/*  73 */       throw new NullPointerException("Iterator must not be null"); 
/*  75 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public void add(Object obj) throws UnsupportedOperationException {
/*  87 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  96 */     if (this.currentIndex == this.wrappedIteratorIndex)
/*  97 */       return this.iterator.hasNext(); 
/*  99 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/* 108 */     if (this.currentIndex == 0)
/* 109 */       return false; 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public Object next() throws NoSuchElementException {
/* 121 */     if (this.currentIndex < this.wrappedIteratorIndex) {
/* 122 */       this.currentIndex++;
/* 123 */       return this.list.get(this.currentIndex - 1);
/*     */     } 
/* 126 */     Object retval = this.iterator.next();
/* 127 */     this.list.add(retval);
/* 128 */     this.currentIndex++;
/* 129 */     this.wrappedIteratorIndex++;
/* 130 */     return retval;
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 139 */     return this.currentIndex;
/*     */   }
/*     */   
/*     */   public Object previous() throws NoSuchElementException {
/* 149 */     if (this.currentIndex == 0)
/* 150 */       throw new NoSuchElementException(); 
/* 152 */     this.currentIndex--;
/* 153 */     return this.list.get(this.currentIndex);
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 162 */     return this.currentIndex - 1;
/*     */   }
/*     */   
/*     */   public void remove() throws UnsupportedOperationException {
/* 171 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */   
/*     */   public void set(Object obj) throws UnsupportedOperationException {
/* 181 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */   
/*     */   public void reset() {
/* 193 */     this.currentIndex = 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ListIteratorWrapper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */