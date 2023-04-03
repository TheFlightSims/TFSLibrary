/*     */ package org.apache.commons.lang.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class NestableError extends Error implements Nestable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*  43 */   protected NestableDelegate delegate = new NestableDelegate(this);
/*     */   
/*  49 */   private Throwable cause = null;
/*     */   
/*     */   public NestableError() {}
/*     */   
/*     */   public NestableError(String msg) {
/*  66 */     super(msg);
/*     */   }
/*     */   
/*     */   public NestableError(Throwable cause) {
/*  78 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public NestableError(String msg, Throwable cause) {
/*  90 */     super(msg);
/*  91 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/*  98 */     return this.cause;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 109 */     if (super.getMessage() != null)
/* 110 */       return super.getMessage(); 
/* 111 */     if (this.cause != null)
/* 112 */       return this.cause.toString(); 
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   public String getMessage(int index) {
/* 122 */     if (index == 0)
/* 123 */       return super.getMessage(); 
/* 125 */     return this.delegate.getMessage(index);
/*     */   }
/*     */   
/*     */   public String[] getMessages() {
/* 132 */     return this.delegate.getMessages();
/*     */   }
/*     */   
/*     */   public Throwable getThrowable(int index) {
/* 139 */     return this.delegate.getThrowable(index);
/*     */   }
/*     */   
/*     */   public int getThrowableCount() {
/* 146 */     return this.delegate.getThrowableCount();
/*     */   }
/*     */   
/*     */   public Throwable[] getThrowables() {
/* 153 */     return this.delegate.getThrowables();
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type) {
/* 160 */     return this.delegate.indexOfThrowable(type, 0);
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type, int fromIndex) {
/* 167 */     return this.delegate.indexOfThrowable(type, fromIndex);
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 174 */     this.delegate.printStackTrace();
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 181 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 188 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public final void printPartialStackTrace(PrintWriter out) {
/* 195 */     super.printStackTrace(out);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\exception\NestableError.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */