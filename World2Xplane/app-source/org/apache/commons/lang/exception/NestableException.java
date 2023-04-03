/*     */ package org.apache.commons.lang.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class NestableException extends Exception implements Nestable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/* 103 */   protected NestableDelegate delegate = new NestableDelegate(this);
/*     */   
/* 109 */   private Throwable cause = null;
/*     */   
/*     */   public NestableException() {}
/*     */   
/*     */   public NestableException(String msg) {
/* 126 */     super(msg);
/*     */   }
/*     */   
/*     */   public NestableException(Throwable cause) {
/* 138 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public NestableException(String msg, Throwable cause) {
/* 150 */     super(msg);
/* 151 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/* 158 */     return this.cause;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 169 */     if (super.getMessage() != null)
/* 170 */       return super.getMessage(); 
/* 171 */     if (this.cause != null)
/* 172 */       return this.cause.toString(); 
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public String getMessage(int index) {
/* 182 */     if (index == 0)
/* 183 */       return super.getMessage(); 
/* 185 */     return this.delegate.getMessage(index);
/*     */   }
/*     */   
/*     */   public String[] getMessages() {
/* 192 */     return this.delegate.getMessages();
/*     */   }
/*     */   
/*     */   public Throwable getThrowable(int index) {
/* 199 */     return this.delegate.getThrowable(index);
/*     */   }
/*     */   
/*     */   public int getThrowableCount() {
/* 206 */     return this.delegate.getThrowableCount();
/*     */   }
/*     */   
/*     */   public Throwable[] getThrowables() {
/* 213 */     return this.delegate.getThrowables();
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type) {
/* 220 */     return this.delegate.indexOfThrowable(type, 0);
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type, int fromIndex) {
/* 227 */     return this.delegate.indexOfThrowable(type, fromIndex);
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 234 */     this.delegate.printStackTrace();
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 241 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 248 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public final void printPartialStackTrace(PrintWriter out) {
/* 255 */     super.printStackTrace(out);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\exception\NestableException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */