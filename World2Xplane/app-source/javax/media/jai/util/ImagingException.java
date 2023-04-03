/*     */ package javax.media.jai.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.PrivilegedActionException;
/*     */ 
/*     */ public class ImagingException extends RuntimeException {
/*  35 */   private Throwable cause = null;
/*     */   
/*     */   public ImagingException() {}
/*     */   
/*     */   public ImagingException(String message) {
/*  48 */     super(message);
/*     */   }
/*     */   
/*     */   public ImagingException(Throwable cause) {
/*  58 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public ImagingException(String message, Throwable cause) {
/*  69 */     super(message);
/*  70 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/*  77 */     return this.cause;
/*     */   }
/*     */   
/*     */   public Throwable getRootCause() {
/*  85 */     Throwable rootCause = this.cause;
/*  86 */     Throwable atop = this;
/*  88 */     while (rootCause != atop && rootCause != null) {
/*     */       try {
/*  90 */         atop = rootCause;
/*  91 */         Method getCause = rootCause.getClass().getMethod("getCause", null);
/*  92 */         rootCause = (Throwable)getCause.invoke(rootCause, null);
/*  93 */       } catch (Exception e) {
/*  97 */         if (rootCause instanceof InvocationTargetException) {
/*  98 */           rootCause = ((InvocationTargetException)rootCause).getTargetException();
/*  99 */         } else if (rootCause instanceof PrivilegedActionException) {
/* 100 */           rootCause = ((PrivilegedActionException)rootCause).getException();
/*     */         } else {
/* 101 */           rootCause = atop;
/*     */         } 
/*     */       } finally {
/* 103 */         if (rootCause == null)
/* 104 */           rootCause = atop; 
/*     */       } 
/*     */     } 
/* 108 */     return rootCause;
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 119 */     printStackTrace(System.err);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream s) {
/* 131 */     synchronized (s) {
/* 132 */       super.printStackTrace(s);
/* 133 */       boolean is14 = false;
/*     */       try {
/* 135 */         String version = System.getProperty("java.version");
/* 136 */         is14 = (version.indexOf("1.4") >= 0);
/* 137 */       } catch (Exception e) {}
/* 140 */       if (!is14 && this.cause != null) {
/* 141 */         s.println("Caused by:");
/* 142 */         this.cause.printStackTrace(s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter s) {
/* 157 */     synchronized (s) {
/* 158 */       super.printStackTrace(s);
/* 159 */       boolean is14 = false;
/*     */       try {
/* 161 */         String version = System.getProperty("java.version");
/* 162 */         is14 = (version.indexOf("1.4") >= 0);
/* 163 */       } catch (Exception e) {}
/* 166 */       if (!is14 && this.cause != null) {
/* 167 */         s.println("Caused by:");
/* 168 */         this.cause.printStackTrace(s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\ja\\util\ImagingException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */