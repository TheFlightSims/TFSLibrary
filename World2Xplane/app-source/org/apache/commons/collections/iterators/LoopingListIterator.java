/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class LoopingListIterator implements ResettableListIterator {
/*     */   private List list;
/*     */   
/*     */   private ListIterator iterator;
/*     */   
/*     */   public LoopingListIterator(List list) {
/*  60 */     if (list == null)
/*  61 */       throw new NullPointerException("The list must not be null"); 
/*  63 */     this.list = list;
/*  64 */     reset();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  76 */     return !this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  88 */     if (this.list.isEmpty())
/*  89 */       throw new NoSuchElementException("There are no elements for this iterator to loop on"); 
/*  92 */     if (!this.iterator.hasNext())
/*  93 */       reset(); 
/*  95 */     return this.iterator.next();
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 110 */     if (this.list.isEmpty())
/* 111 */       throw new NoSuchElementException("There are no elements for this iterator to loop on"); 
/* 114 */     if (!this.iterator.hasNext())
/* 115 */       return 0; 
/* 117 */     return this.iterator.nextIndex();
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/* 130 */     return !this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 143 */     if (this.list.isEmpty())
/* 144 */       throw new NoSuchElementException("There are no elements for this iterator to loop on"); 
/* 147 */     if (!this.iterator.hasPrevious()) {
/* 148 */       Object result = null;
/* 149 */       while (this.iterator.hasNext())
/* 150 */         result = this.iterator.next(); 
/* 152 */       this.iterator.previous();
/* 153 */       return result;
/*     */     } 
/* 155 */     return this.iterator.previous();
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 171 */     if (this.list.isEmpty())
/* 172 */       throw new NoSuchElementException("There are no elements for this iterator to loop on"); 
/* 175 */     if (!this.iterator.hasPrevious())
/* 176 */       return this.list.size() - 1; 
/* 178 */     return this.iterator.previousIndex();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 201 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   public void add(Object obj) {
/* 220 */     this.iterator.add(obj);
/*     */   }
/*     */   
/*     */   public void set(Object obj) {
/* 236 */     this.iterator.set(obj);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 243 */     this.iterator = this.list.listIterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 252 */     return this.list.size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\LoopingListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */