/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class UnmodifiableLazyStringList extends AbstractList<String> implements LazyStringList, RandomAccess {
/*     */   private final LazyStringList list;
/*     */   
/*     */   public UnmodifiableLazyStringList(LazyStringList list) {
/*  50 */     this.list = list;
/*     */   }
/*     */   
/*     */   public String get(int index) {
/*  55 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int size() {
/*  60 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public ByteString getByteString(int index) {
/*  65 */     return this.list.getByteString(index);
/*     */   }
/*     */   
/*     */   public void add(ByteString element) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public ListIterator<String> listIterator(final int index) {
/*  75 */     return new ListIterator<String>() {
/*  76 */         ListIterator<String> iter = UnmodifiableLazyStringList.this.list.listIterator(index);
/*     */         
/*     */         public boolean hasNext() {
/*  80 */           return this.iter.hasNext();
/*     */         }
/*     */         
/*     */         public String next() {
/*  85 */           return this.iter.next();
/*     */         }
/*     */         
/*     */         public boolean hasPrevious() {
/*  90 */           return this.iter.hasPrevious();
/*     */         }
/*     */         
/*     */         public String previous() {
/*  95 */           return this.iter.previous();
/*     */         }
/*     */         
/*     */         public int nextIndex() {
/* 100 */           return this.iter.nextIndex();
/*     */         }
/*     */         
/*     */         public int previousIndex() {
/* 105 */           return this.iter.previousIndex();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 110 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void set(String o) {
/* 115 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void add(String o) {
/* 120 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Iterator<String> iterator() {
/* 127 */     return new Iterator<String>() {
/* 128 */         Iterator<String> iter = UnmodifiableLazyStringList.this.list.iterator();
/*     */         
/*     */         public boolean hasNext() {
/* 132 */           return this.iter.hasNext();
/*     */         }
/*     */         
/*     */         public String next() {
/* 137 */           return this.iter.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 142 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\UnmodifiableLazyStringList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */