/*     */ package org.jfree.threads;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ReaderWriterLock {
/*     */   private static class ReaderWriterNode {
/*     */     protected static final int READER = 0;
/*     */     
/*     */     protected static final int WRITER = 1;
/*     */     
/*     */     protected Thread t;
/*     */     
/*     */     protected int state;
/*     */     
/*     */     protected int nAcquires;
/*     */     
/*     */     private ReaderWriterNode(Thread t, int state) {
/*  80 */       this.t = t;
/*  81 */       this.state = state;
/*  82 */       this.nAcquires = 0;
/*     */     }
/*     */   }
/*     */   
/*  94 */   private ArrayList waiters = new ArrayList();
/*     */   
/*     */   public synchronized void lockRead() {
/*     */     ReaderWriterNode node;
/* 102 */     Thread me = Thread.currentThread();
/* 103 */     int index = getIndex(me);
/* 104 */     if (index == -1) {
/* 105 */       node = new ReaderWriterNode(me, 0);
/* 106 */       this.waiters.add(node);
/*     */     } else {
/* 109 */       node = this.waiters.get(index);
/*     */     } 
/* 111 */     while (getIndex(me) > firstWriter()) {
/*     */       try {
/* 113 */         wait();
/* 115 */       } catch (Exception e) {
/* 116 */         System.err.println("ReaderWriterLock.lockRead(): exception.");
/* 117 */         System.err.print(e.getMessage());
/*     */       } 
/*     */     } 
/* 120 */     node.nAcquires++;
/*     */   }
/*     */   
/*     */   public synchronized void lockWrite() {
/*     */     ReaderWriterNode node;
/* 128 */     Thread me = Thread.currentThread();
/* 129 */     int index = getIndex(me);
/* 130 */     if (index == -1) {
/* 131 */       node = new ReaderWriterNode(me, 1);
/* 132 */       this.waiters.add(node);
/*     */     } else {
/* 135 */       node = this.waiters.get(index);
/* 136 */       if (node.state == 0)
/* 137 */         throw new IllegalArgumentException("Upgrade lock"); 
/* 139 */       node.state = 1;
/*     */     } 
/* 141 */     while (getIndex(me) != 0) {
/*     */       try {
/* 143 */         wait();
/* 145 */       } catch (Exception e) {
/* 146 */         System.err.println("ReaderWriterLock.lockWrite(): exception.");
/* 147 */         System.err.print(e.getMessage());
/*     */       } 
/*     */     } 
/* 150 */     node.nAcquires++;
/*     */   }
/*     */   
/*     */   public synchronized void unlock() {
/* 159 */     Thread me = Thread.currentThread();
/* 160 */     int index = getIndex(me);
/* 161 */     if (index > firstWriter())
/* 162 */       throw new IllegalArgumentException("Lock not held"); 
/* 164 */     ReaderWriterNode node = this.waiters.get(index);
/* 165 */     node.nAcquires--;
/* 166 */     if (node.nAcquires == 0)
/* 167 */       this.waiters.remove(index); 
/* 169 */     notifyAll();
/*     */   }
/*     */   
/*     */   private int firstWriter() {
/* 178 */     Iterator e = this.waiters.iterator();
/* 179 */     int index = 0;
/* 180 */     while (e.hasNext()) {
/* 181 */       ReaderWriterNode node = e.next();
/* 182 */       if (node.state == 1)
/* 183 */         return index; 
/* 185 */       index++;
/*     */     } 
/* 187 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private int getIndex(Thread t) {
/* 198 */     Iterator e = this.waiters.iterator();
/* 199 */     int index = 0;
/* 200 */     while (e.hasNext()) {
/* 201 */       ReaderWriterNode node = e.next();
/* 202 */       if (node.t == t)
/* 203 */         return index; 
/* 205 */       index++;
/*     */     } 
/* 207 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\threads\ReaderWriterLock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */