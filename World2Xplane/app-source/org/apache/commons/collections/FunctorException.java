/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class FunctorException extends RuntimeException {
/*     */   private static final boolean JDK_SUPPORTS_NESTED;
/*     */   
/*     */   private final Throwable rootCause;
/*     */   
/*     */   static {
/*  39 */     boolean flag = false;
/*     */     try {
/*  41 */       Throwable.class.getDeclaredMethod("getCause", new Class[0]);
/*  42 */       flag = true;
/*  43 */     } catch (NoSuchMethodException ex) {
/*  44 */       flag = false;
/*     */     } 
/*  46 */     JDK_SUPPORTS_NESTED = flag;
/*     */   }
/*     */   
/*     */   public FunctorException() {
/*  60 */     this.rootCause = null;
/*     */   }
/*     */   
/*     */   public FunctorException(String msg) {
/*  70 */     super(msg);
/*  71 */     this.rootCause = null;
/*     */   }
/*     */   
/*     */   public FunctorException(Throwable rootCause) {
/*  82 */     super((rootCause == null) ? null : rootCause.getMessage());
/*  83 */     this.rootCause = rootCause;
/*     */   }
/*     */   
/*     */   public FunctorException(String msg, Throwable rootCause) {
/*  95 */     super(msg);
/*  96 */     this.rootCause = rootCause;
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/* 105 */     return this.rootCause;
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 112 */     printStackTrace(System.err);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 121 */     synchronized (out) {
/* 122 */       PrintWriter pw = new PrintWriter(out, false);
/* 123 */       printStackTrace(pw);
/* 125 */       pw.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 135 */     synchronized (out) {
/* 136 */       super.printStackTrace(out);
/* 137 */       if (this.rootCause != null && !JDK_SUPPORTS_NESTED) {
/* 138 */         out.print("Caused by: ");
/* 139 */         this.rootCause.printStackTrace(out);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\FunctorException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */