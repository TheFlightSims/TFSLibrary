/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PrintStreamLogTarget implements LogTarget, Serializable {
/*     */   private static final long serialVersionUID = 6510564403264504688L;
/*     */   
/*     */   private PrintStream printStream;
/*     */   
/*     */   public PrintStreamLogTarget() {
/*  70 */     this(System.out);
/*     */   }
/*     */   
/*     */   public PrintStreamLogTarget(PrintStream printStream) {
/*  79 */     if (printStream == null)
/*  80 */       throw new NullPointerException(); 
/*  82 */     this.printStream = printStream;
/*     */   }
/*     */   
/*     */   public void log(int level, Object message) {
/*  94 */     if (level > 3)
/*  95 */       level = 3; 
/*  97 */     this.printStream.print(LogTarget.LEVELS[level]);
/*  98 */     this.printStream.println(message);
/*  99 */     if (level < 3)
/* 100 */       System.out.flush(); 
/*     */   }
/*     */   
/*     */   public void log(int level, Object message, Exception e) {
/* 116 */     if (level > 3)
/* 117 */       level = 3; 
/* 119 */     this.printStream.print(LogTarget.LEVELS[level]);
/* 120 */     this.printStream.println(message);
/* 121 */     e.printStackTrace(this.printStream);
/* 122 */     if (level < 3)
/* 123 */       System.out.flush(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\PrintStreamLogTarget.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */