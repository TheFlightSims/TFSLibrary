/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class FilterListIterator implements ListIterator {
/*     */   private ListIterator iterator;
/*     */   
/*     */   private Predicate predicate;
/*     */   
/*     */   private Object nextObject;
/*     */   
/*     */   private boolean nextObjectSet = false;
/*     */   
/*     */   private Object previousObject;
/*     */   
/*     */   private boolean previousObjectSet = false;
/*     */   
/*  70 */   private int nextIndex = 0;
/*     */   
/*     */   public FilterListIterator() {}
/*     */   
/*     */   public FilterListIterator(ListIterator iterator) {
/*  90 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public FilterListIterator(ListIterator iterator, Predicate predicate) {
/* 101 */     this.iterator = iterator;
/* 102 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   public FilterListIterator(Predicate predicate) {
/* 113 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   public void add(Object o) {
/* 119 */     throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 123 */     if (this.nextObjectSet)
/* 124 */       return true; 
/* 126 */     return setNextObject();
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/* 131 */     if (this.previousObjectSet)
/* 132 */       return true; 
/* 134 */     return setPreviousObject();
/*     */   }
/*     */   
/*     */   public Object next() {
/* 139 */     if (!this.nextObjectSet && 
/* 140 */       !setNextObject())
/* 141 */       throw new NoSuchElementException(); 
/* 144 */     this.nextIndex++;
/* 145 */     Object temp = this.nextObject;
/* 146 */     clearNextObject();
/* 147 */     return temp;
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 151 */     return this.nextIndex;
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 155 */     if (!this.previousObjectSet && 
/* 156 */       !setPreviousObject())
/* 157 */       throw new NoSuchElementException(); 
/* 160 */     this.nextIndex--;
/* 161 */     Object temp = this.previousObject;
/* 162 */     clearPreviousObject();
/* 163 */     return temp;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 167 */     return this.nextIndex - 1;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 172 */     throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
/*     */   }
/*     */   
/*     */   public void set(Object o) {
/* 177 */     throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
/*     */   }
/*     */   
/*     */   public ListIterator getListIterator() {
/* 187 */     return this.iterator;
/*     */   }
/*     */   
/*     */   public void setListIterator(ListIterator iterator) {
/* 197 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public Predicate getPredicate() {
/* 207 */     return this.predicate;
/*     */   }
/*     */   
/*     */   public void setPredicate(Predicate predicate) {
/* 216 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   private void clearNextObject() {
/* 221 */     this.nextObject = null;
/* 222 */     this.nextObjectSet = false;
/*     */   }
/*     */   
/*     */   private boolean setNextObject() {
/* 230 */     if (this.previousObjectSet) {
/* 231 */       clearPreviousObject();
/* 232 */       if (!setNextObject())
/* 233 */         return false; 
/* 235 */       clearNextObject();
/*     */     } 
/* 239 */     while (this.iterator.hasNext()) {
/* 240 */       Object object = this.iterator.next();
/* 241 */       if (this.predicate.evaluate(object)) {
/* 242 */         this.nextObject = object;
/* 243 */         this.nextObjectSet = true;
/* 244 */         return true;
/*     */       } 
/*     */     } 
/* 247 */     return false;
/*     */   }
/*     */   
/*     */   private void clearPreviousObject() {
/* 251 */     this.previousObject = null;
/* 252 */     this.previousObjectSet = false;
/*     */   }
/*     */   
/*     */   private boolean setPreviousObject() {
/* 260 */     if (this.nextObjectSet) {
/* 261 */       clearNextObject();
/* 262 */       if (!setPreviousObject())
/* 263 */         return false; 
/* 265 */       clearPreviousObject();
/*     */     } 
/* 269 */     while (this.iterator.hasPrevious()) {
/* 270 */       Object object = this.iterator.previous();
/* 271 */       if (this.predicate.evaluate(object)) {
/* 272 */         this.previousObject = object;
/* 273 */         this.previousObjectSet = true;
/* 274 */         return true;
/*     */       } 
/*     */     } 
/* 277 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\FilterListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */