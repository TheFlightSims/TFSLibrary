/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ public class IndentedLineWriter extends FilterWriter {
/*  42 */   private String margin = "";
/*     */   
/*     */   private boolean newLine = true;
/*     */   
/*     */   private boolean waitLF;
/*     */   
/*     */   public IndentedLineWriter(Writer out) {
/*  60 */     super(out);
/*     */   }
/*     */   
/*     */   public int getIdentation() {
/*  69 */     return this.margin.length();
/*     */   }
/*     */   
/*     */   public void setIndentation(int width) {
/*  78 */     synchronized (this.lock) {
/*  79 */       this.margin = Utilities.spaces(width);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void beginNewLine() throws IOException {
/*  90 */     this.out.write(this.margin);
/*     */   }
/*     */   
/*     */   private void doWrite(int c) throws IOException {
/*  99 */     assert Thread.holdsLock(this.lock);
/* 100 */     if (this.newLine && (c != 10 || !this.waitLF))
/* 101 */       beginNewLine(); 
/* 103 */     this.out.write(c);
/* 104 */     if ((this.newLine = (c == 13 || c == 10)) == true)
/* 105 */       this.waitLF = (c == 13); 
/*     */   }
/*     */   
/*     */   public void write(int c) throws IOException {
/* 116 */     synchronized (this.lock) {
/* 117 */       doWrite(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(char[] buffer, int offset, int length) throws IOException {
/* 131 */     int upper = offset + length;
/* 132 */     synchronized (this.lock) {
/* 133 */       label22: while (offset < upper) {
/* 134 */         if (this.newLine) {
/* 135 */           doWrite(buffer[offset++]);
/*     */           continue;
/*     */         } 
/* 138 */         int lower = offset;
/*     */         while (true) {
/* 140 */           char c = buffer[offset];
/* 141 */           if (c == '\r' || c == '\n') {
/* 142 */             this.out.write(buffer, lower, offset - lower);
/* 143 */             doWrite(c);
/* 144 */             offset++;
/*     */             continue label22;
/*     */           } 
/* 147 */           if (++offset >= upper) {
/* 148 */             this.out.write(buffer, lower, offset - lower);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String string, int offset, int length) throws IOException {
/* 164 */     int upper = offset + length;
/* 165 */     synchronized (this.lock) {
/* 166 */       label22: while (offset < upper) {
/* 167 */         if (this.newLine) {
/* 168 */           doWrite(string.charAt(offset++));
/*     */           continue;
/*     */         } 
/* 171 */         int lower = offset;
/*     */         while (true) {
/* 173 */           char c = string.charAt(offset);
/* 174 */           if (c == '\r' || c == '\n') {
/* 175 */             this.out.write(string, lower, offset - lower);
/* 176 */             doWrite(c);
/* 177 */             offset++;
/*     */             continue label22;
/*     */           } 
/* 180 */           if (++offset >= upper) {
/* 181 */             this.out.write(string, lower, offset - lower);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\IndentedLineWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */