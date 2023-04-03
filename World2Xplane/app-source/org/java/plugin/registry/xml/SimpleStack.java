/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ class SimpleStack<T> {
/* 100 */   private LinkedList<T> data = new LinkedList<T>();
/*     */   
/*     */   T pop() {
/* 104 */     return this.data.isEmpty() ? null : this.data.removeLast();
/*     */   }
/*     */   
/*     */   void push(T obj) {
/* 108 */     this.data.addLast(obj);
/*     */   }
/*     */   
/*     */   int size() {
/* 112 */     return this.data.size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\SimpleStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */