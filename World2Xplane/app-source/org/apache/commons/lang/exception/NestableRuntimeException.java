/*     */ package org.apache.commons.lang.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class NestableRuntimeException extends RuntimeException implements Nestable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*  47 */   protected NestableDelegate delegate = new NestableDelegate(this);
/*     */   
/*  53 */   private Throwable cause = null;
/*     */   
/*     */   public NestableRuntimeException() {}
/*     */   
/*     */   public NestableRuntimeException(String msg) {
/*  70 */     super(msg);
/*     */   }
/*     */   
/*     */   public NestableRuntimeException(Throwable cause) {
/*  82 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public NestableRuntimeException(String msg, Throwable cause) {
/*  94 */     super(msg);
/*  95 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/* 102 */     return this.cause;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 113 */     if (super.getMessage() != null)
/* 114 */       return super.getMessage(); 
/* 115 */     if (this.cause != null)
/* 116 */       return this.cause.toString(); 
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public String getMessage(int index) {
/* 126 */     if (index == 0)
/* 127 */       return super.getMessage(); 
/* 129 */     return this.delegate.getMessage(index);
/*     */   }
/*     */   
/*     */   public String[] getMessages() {
/* 136 */     return this.delegate.getMessages();
/*     */   }
/*     */   
/*     */   public Throwable getThrowable(int index) {
/* 143 */     return this.delegate.getThrowable(index);
/*     */   }
/*     */   
/*     */   public int getThrowableCount() {
/* 150 */     return this.delegate.getThrowableCount();
/*     */   }
/*     */   
/*     */   public Throwable[] getThrowables() {
/* 157 */     return this.delegate.getThrowables();
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type) {
/* 164 */     return this.delegate.indexOfThrowable(type, 0);
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type, int fromIndex) {
/* 171 */     return this.delegate.indexOfThrowable(type, fromIndex);
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 178 */     this.delegate.printStackTrace();
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 185 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 192 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public final void printPartialStackTrace(PrintWriter out) {
/* 199 */     super.printStackTrace(out);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\exception\NestableRuntimeException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */