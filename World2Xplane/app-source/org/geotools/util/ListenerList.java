/*     */ package org.geotools.util;
/*     */ 
/*     */ public class ListenerList {
/*     */   private int capacity;
/*     */   
/*     */   private int size;
/*     */   
/*  64 */   private Object[] listeners = null;
/*     */   
/*  70 */   private static final Object[] EmptyArray = new Object[0];
/*     */   
/*     */   public ListenerList() {
/*  76 */     this(1);
/*     */   }
/*     */   
/*     */   public ListenerList(int capacity) {
/*  86 */     assert capacity >= 1;
/*  87 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public void add(Object listener) {
/*  97 */     assert listener != null;
/*  98 */     if (this.size == 0) {
/*  99 */       this.listeners = new Object[this.capacity];
/*     */     } else {
/* 102 */       for (int i = 0; i < this.size; i++) {
/* 103 */         if (this.listeners[i] == listener)
/*     */           return; 
/*     */       } 
/* 108 */       if (this.size == this.listeners.length)
/* 109 */         System.arraycopy(this.listeners, 0, this.listeners = new Object[this.size * 2 + 1], 0, this.size); 
/*     */     } 
/* 114 */     this.listeners[this.size] = listener;
/* 115 */     this.size++;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 122 */     this.size = 0;
/* 123 */     this.listeners = null;
/*     */   }
/*     */   
/*     */   public Object[] getListeners() {
/* 141 */     if (this.size == 0)
/* 142 */       return EmptyArray; 
/* 143 */     Object[] result = new Object[this.size];
/* 144 */     System.arraycopy(this.listeners, 0, result, 0, this.size);
/* 145 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 155 */     return (this.size == 0);
/*     */   }
/*     */   
/*     */   public void remove(Object listener) {
/* 165 */     assert listener != null;
/* 166 */     for (int i = 0; i < this.size; i++) {
/* 167 */       if (this.listeners[i] == listener) {
/* 168 */         if (this.size == 1) {
/* 169 */           this.listeners = null;
/* 170 */           this.size = 0;
/*     */         } else {
/* 172 */           System.arraycopy(this.listeners, i + 1, this.listeners, i, --this.size - i);
/* 175 */           this.listeners[this.size] = null;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 188 */     return this.size;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ListenerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */