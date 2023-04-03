/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public abstract class StackableException extends Exception {
/*     */   private Exception parent;
/*     */   
/*     */   public StackableException() {}
/*     */   
/*     */   public StackableException(String message, Exception ex) {
/*  80 */     super(message);
/*  81 */     this.parent = ex;
/*     */   }
/*     */   
/*     */   public StackableException(String message) {
/*  90 */     super(message);
/*     */   }
/*     */   
/*     */   public Exception getParent() {
/*  99 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream stream) {
/* 108 */     super.printStackTrace(stream);
/* 109 */     if (getParent() != null) {
/* 110 */       stream.println("ParentException: ");
/* 111 */       getParent().printStackTrace(stream);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter writer) {
/* 121 */     super.printStackTrace(writer);
/* 122 */     if (getParent() != null) {
/* 123 */       writer.println("ParentException: ");
/* 124 */       getParent().printStackTrace(writer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 165 */     synchronized (System.err) {
/* 166 */       printStackTrace(System.err);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\StackableException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */