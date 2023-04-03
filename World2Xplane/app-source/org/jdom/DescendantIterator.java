/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ class DescendantIterator implements Iterator {
/*     */   private Iterator iterator;
/*     */   
/*     */   private Iterator nextIterator;
/*     */   
/*  76 */   private List stack = new ArrayList();
/*     */   
/*     */   private static final String CVS_ID = "@(#) $RCSfile: DescendantIterator.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   DescendantIterator(Parent parent) {
/*  87 */     if (parent == null)
/*  88 */       throw new IllegalArgumentException("parent parameter was null"); 
/*  90 */     this.iterator = parent.getContent().iterator();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  99 */     if (this.iterator != null && this.iterator.hasNext())
/*  99 */       return true; 
/* 100 */     if (this.nextIterator != null && this.nextIterator.hasNext())
/* 100 */       return true; 
/* 101 */     if (stackHasAnyNext())
/* 101 */       return true; 
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 111 */     if (!hasNext())
/* 112 */       throw new NoSuchElementException(); 
/* 118 */     if (this.nextIterator != null) {
/* 119 */       push(this.iterator);
/* 120 */       this.iterator = this.nextIterator;
/* 121 */       this.nextIterator = null;
/*     */     } 
/* 125 */     while (!this.iterator.hasNext()) {
/* 126 */       if (this.stack.size() > 0) {
/* 127 */         this.iterator = pop();
/*     */         continue;
/*     */       } 
/* 130 */       throw new NoSuchElementException("Somehow we lost our iterator");
/*     */     } 
/* 134 */     Content child = this.iterator.next();
/* 135 */     if (child instanceof Element)
/* 136 */       this.nextIterator = ((Element)child).getContent().iterator(); 
/* 138 */     return child;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 148 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   private Iterator pop() {
/* 152 */     int stackSize = this.stack.size();
/* 153 */     if (stackSize == 0)
/* 154 */       throw new NoSuchElementException("empty stack"); 
/* 156 */     return this.stack.remove(stackSize - 1);
/*     */   }
/*     */   
/*     */   private void push(Iterator itr) {
/* 160 */     this.stack.add(itr);
/*     */   }
/*     */   
/*     */   private boolean stackHasAnyNext() {
/* 164 */     int size = this.stack.size();
/* 165 */     for (int i = 0; i < size; i++) {
/* 166 */       Iterator itr = this.stack.get(i);
/* 167 */       if (itr.hasNext())
/* 168 */         return true; 
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\DescendantIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */