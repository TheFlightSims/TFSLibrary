/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import org.apache.commons.lang.exception.Nestable;
/*     */ import org.apache.commons.lang.exception.NestableDelegate;
/*     */ 
/*     */ public class NotImplementedException extends UnsupportedOperationException implements Nestable {
/*     */   private static final String DEFAULT_MESSAGE = "Code is not implemented";
/*     */   
/*     */   private static final long serialVersionUID = -6894122266938754088L;
/*     */   
/*  67 */   private NestableDelegate delegate = new NestableDelegate(this);
/*     */   
/*     */   private Throwable cause;
/*     */   
/*     */   public NotImplementedException() {
/*  82 */     super("Code is not implemented");
/*     */   }
/*     */   
/*     */   public NotImplementedException(String msg) {
/*  92 */     super((msg == null) ? "Code is not implemented" : msg);
/*     */   }
/*     */   
/*     */   public NotImplementedException(Throwable cause) {
/* 103 */     super("Code is not implemented");
/* 104 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public NotImplementedException(String msg, Throwable cause) {
/* 116 */     super((msg == null) ? "Code is not implemented" : msg);
/* 117 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public NotImplementedException(Class clazz) {
/* 127 */     super((clazz == null) ? "Code is not implemented" : ("Code is not implemented in " + clazz));
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/* 138 */     return this.cause;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 148 */     if (super.getMessage() != null)
/* 149 */       return super.getMessage(); 
/* 150 */     if (this.cause != null)
/* 151 */       return this.cause.toString(); 
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   public String getMessage(int index) {
/* 169 */     if (index == 0)
/* 170 */       return super.getMessage(); 
/* 172 */     return this.delegate.getMessage(index);
/*     */   }
/*     */   
/*     */   public String[] getMessages() {
/* 184 */     return this.delegate.getMessages();
/*     */   }
/*     */   
/*     */   public Throwable getThrowable(int index) {
/* 197 */     return this.delegate.getThrowable(index);
/*     */   }
/*     */   
/*     */   public int getThrowableCount() {
/* 208 */     return this.delegate.getThrowableCount();
/*     */   }
/*     */   
/*     */   public Throwable[] getThrowables() {
/* 220 */     return this.delegate.getThrowables();
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type) {
/* 233 */     return this.delegate.indexOfThrowable(type, 0);
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type, int fromIndex) {
/* 249 */     return this.delegate.indexOfThrowable(type, fromIndex);
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 259 */     this.delegate.printStackTrace();
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 270 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 281 */     this.delegate.printStackTrace(out);
/*     */   }
/*     */   
/*     */   public final void printPartialStackTrace(PrintWriter out) {
/* 292 */     super.printStackTrace(out);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\NotImplementedException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */