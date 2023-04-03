/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ public class NumberedLineWriter extends IndentedLineWriter {
/*  43 */   public static final PrintWriter OUT = new PrintWriter(new Uncloseable(Arguments.getWriter(System.out)), true);
/*     */   
/*     */   private static final class Uncloseable extends NumberedLineWriter {
/*     */     public Uncloseable(Writer out) {
/*  53 */       super(out);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/*  59 */       flush();
/*     */     }
/*     */   }
/*     */   
/*  66 */   private int width = 3;
/*     */   
/*  71 */   private int current = 1;
/*     */   
/*     */   public NumberedLineWriter(Writer out) {
/*  79 */     super(out);
/*     */   }
/*     */   
/*     */   public int getLineNumber() {
/*  88 */     return this.current;
/*     */   }
/*     */   
/*     */   public void setLineNumber(int line) {
/*  97 */     synchronized (this.lock) {
/*  98 */       this.current = line;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void beginNewLine() throws IOException {
/* 110 */     String number = String.valueOf(this.current++);
/* 111 */     this.out.write(91);
/* 112 */     this.out.write(Utilities.spaces(this.width - number.length()));
/* 113 */     this.out.write(number);
/* 114 */     this.out.write("] ");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\NumberedLineWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */