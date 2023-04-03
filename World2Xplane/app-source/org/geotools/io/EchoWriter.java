/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public class EchoWriter extends FilterWriter {
/*     */   private final Writer echo;
/*     */   
/*     */   public EchoWriter(Writer main) {
/*  51 */     super(main);
/*  52 */     this.echo = NumberedLineWriter.OUT;
/*     */   }
/*     */   
/*     */   public EchoWriter(Writer main, Writer echo) {
/*  62 */     super(main);
/*  63 */     this.echo = echo;
/*     */   }
/*     */   
/*     */   public void write(int c) throws IOException {
/*  74 */     synchronized (this.lock) {
/*  75 */       this.out.write(c);
/*  76 */       this.echo.write(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(char[] cbuf) throws IOException {
/*  88 */     synchronized (this.lock) {
/*  89 */       this.out.write(cbuf);
/*  90 */       this.echo.write(cbuf);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(char[] cbuf, int offset, int length) throws IOException {
/* 104 */     synchronized (this.lock) {
/* 105 */       this.out.write(cbuf, offset, length);
/* 106 */       this.echo.write(cbuf, offset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String string) throws IOException {
/* 118 */     synchronized (this.lock) {
/* 119 */       this.out.write(string);
/* 120 */       this.echo.write(string);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String string, int offset, int length) throws IOException {
/* 134 */     synchronized (this.lock) {
/* 135 */       this.out.write(string, offset, length);
/* 136 */       this.echo.write(string, offset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 147 */     synchronized (this.lock) {
/* 148 */       this.out.flush();
/* 149 */       this.echo.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 162 */     synchronized (this.lock) {
/* 163 */       this.out.close();
/* 164 */       this.echo.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\EchoWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */