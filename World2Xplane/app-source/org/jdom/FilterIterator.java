/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jdom.filter.Filter;
/*     */ 
/*     */ class FilterIterator implements Iterator {
/*     */   private Iterator iterator;
/*     */   
/*     */   private Filter filter;
/*     */   
/*     */   private Object nextObject;
/*     */   
/*     */   private static final String CVS_ID = "@(#) $RCSfile: FilterIterator.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public FilterIterator(Iterator iterator, Filter filter) {
/*  78 */     if (iterator == null || filter == null)
/*  79 */       throw new IllegalArgumentException("null parameter"); 
/*  81 */     this.iterator = iterator;
/*  82 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  86 */     if (this.nextObject != null)
/*  87 */       return true; 
/*  90 */     while (this.iterator.hasNext()) {
/*  91 */       Object obj = this.iterator.next();
/*  92 */       if (this.filter.matches(obj)) {
/*  93 */         this.nextObject = obj;
/*  94 */         return true;
/*     */       } 
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 101 */     if (!hasNext())
/* 102 */       throw new NoSuchElementException(); 
/* 105 */     Object obj = this.nextObject;
/* 106 */     this.nextObject = null;
/* 107 */     return obj;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 113 */     this.iterator.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\FilterIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */