/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class StackableRuntimeException extends RuntimeException {
/*     */   private Exception parent;
/*     */   
/*     */   public StackableRuntimeException() {}
/*     */   
/*     */   public StackableRuntimeException(String message, Exception ex) {
/*  78 */     super(message);
/*  79 */     this.parent = ex;
/*     */   }
/*     */   
/*     */   public StackableRuntimeException(String message) {
/*  88 */     super(message);
/*     */   }
/*     */   
/*     */   public Exception getParent() {
/*  97 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream stream) {
/* 106 */     super.printStackTrace(stream);
/* 107 */     if (getParent() != null) {
/* 108 */       stream.println("ParentException: ");
/* 109 */       getParent().printStackTrace(stream);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter writer) {
/* 119 */     super.printStackTrace(writer);
/* 120 */     if (getParent() != null) {
/* 121 */       writer.println("ParentException: ");
/* 122 */       getParent().printStackTrace(writer);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\StackableRuntimeException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */