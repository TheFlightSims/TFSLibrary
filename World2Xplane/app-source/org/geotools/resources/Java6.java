/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ public final class Java6 {
/*     */   public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
/*  50 */     for (int i = fromIndex; i < toIndex; i++) {
/*  51 */       int v = a[i];
/*  52 */       if (v == key)
/*  53 */         return i; 
/*  55 */       if (v > key)
/*  56 */         return i ^ 0xFFFFFFFF; 
/*     */     } 
/*  59 */     return toIndex ^ 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */   public static Reader consoleReader() {
/*     */     Method method;
/*     */     try {
/*  71 */       method = System.class.getMethod("console", (Class[])null);
/*  72 */     } catch (NoSuchMethodException exception) {
/*  73 */       return null;
/*     */     } 
/*     */     try {
/*  76 */       Object console = method.invoke(null, (Object[])null);
/*  77 */       if (console != null) {
/*  78 */         method = console.getClass().getMethod("reader", (Class[])null);
/*  79 */         return (Reader)method.invoke(console, (Object[])null);
/*     */       } 
/*  81 */     } catch (RuntimeException e) {
/*  82 */       throw e;
/*  83 */     } catch (Exception e) {
/*  85 */       throw new AssertionError(e);
/*     */     } 
/*  87 */     return null;
/*     */   }
/*     */   
/*     */   public static PrintWriter consoleWriter() {
/*     */     Method method;
/*     */     try {
/*  99 */       method = System.class.getMethod("console", (Class[])null);
/* 100 */     } catch (NoSuchMethodException exception) {
/* 101 */       return null;
/*     */     } 
/*     */     try {
/* 104 */       Object console = method.invoke(null, (Object[])null);
/* 105 */       if (console != null) {
/* 106 */         method = console.getClass().getMethod("writer", (Class[])null);
/* 107 */         return (PrintWriter)method.invoke(console, (Object[])null);
/*     */       } 
/* 109 */     } catch (RuntimeException e) {
/* 110 */       throw e;
/* 111 */     } catch (Exception e) {
/* 113 */       throw new AssertionError(e);
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\Java6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */