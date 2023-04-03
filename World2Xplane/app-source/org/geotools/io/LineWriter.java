/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.geotools.resources.XArray;
/*     */ 
/*     */ public class LineWriter extends FilterWriter {
/*     */   private String lineSeparator;
/*     */   
/*     */   private boolean skipCR;
/*     */   
/*  60 */   private char[] buffer = new char[64];
/*     */   
/*  65 */   private int count = 0;
/*     */   
/*     */   public LineWriter(Writer out) {
/*  74 */     this(out, System.getProperty("line.separator", "\n"));
/*     */   }
/*     */   
/*     */   public LineWriter(Writer out, String lineSeparator) {
/*  85 */     super(out);
/*  86 */     this.lineSeparator = lineSeparator;
/*  87 */     if (out == null || lineSeparator == null)
/*  88 */       throw new IllegalArgumentException(); 
/*     */   }
/*     */   
/*     */   public String getLineSeparator() {
/*  98 */     return this.lineSeparator;
/*     */   }
/*     */   
/*     */   public void setLineSeparator(String lineSeparator) {
/* 109 */     if (lineSeparator == null)
/* 110 */       throw new IllegalArgumentException(); 
/* 112 */     synchronized (this.lock) {
/* 113 */       this.lineSeparator = lineSeparator;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeEOL() throws IOException {
/* 123 */     assert this.count == 0 : this.count;
/* 125 */     this.out.write(this.lineSeparator);
/*     */   }
/*     */   
/*     */   private boolean bufferBlank() throws IOException {
/* 133 */     for (int i = this.count; --i >= 0;) {
/* 134 */       if (!isWhitespace(this.buffer[i]))
/* 135 */         return false; 
/*     */     } 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   private void flushBuffer() throws IOException {
/* 147 */     assert bufferBlank();
/* 148 */     if (this.count != 0) {
/* 149 */       this.out.write(this.buffer, 0, this.count);
/* 150 */       this.count = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeLine(char[] cbuf, int lower, int upper) throws IOException {
/* 159 */     while (upper != lower) {
/* 160 */       char c = cbuf[upper - 1];
/* 161 */       assert c != '\r' && c != '\n';
/* 162 */       if (isWhitespace(c)) {
/* 163 */         upper--;
/*     */         continue;
/*     */       } 
/* 166 */       flushBuffer();
/* 167 */       this.out.write(cbuf, lower, upper - lower);
/*     */       return;
/*     */     } 
/* 170 */     assert bufferBlank();
/* 171 */     this.count = 0;
/*     */   }
/*     */   
/*     */   private void writeLine(String str, int lower, int upper) throws IOException {
/* 179 */     while (upper != lower) {
/* 180 */       char c = str.charAt(upper - 1);
/* 181 */       assert c != '\r' && c != '\n';
/* 182 */       if (isWhitespace(c)) {
/* 183 */         upper--;
/*     */         continue;
/*     */       } 
/* 186 */       flushBuffer();
/* 187 */       this.out.write(str, lower, upper - lower);
/*     */       return;
/*     */     } 
/* 190 */     assert bufferBlank();
/* 191 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public void write(int c) throws IOException {
/* 201 */     synchronized (this.lock) {
/* 202 */       switch (c) {
/*     */         case 13:
/* 204 */           assert bufferBlank();
/* 205 */           this.count = 0;
/* 206 */           writeEOL();
/* 207 */           this.skipCR = true;
/*     */           break;
/*     */         case 10:
/* 211 */           if (!this.skipCR) {
/* 212 */             assert bufferBlank();
/* 213 */             this.count = 0;
/* 214 */             writeEOL();
/*     */           } 
/* 216 */           this.skipCR = false;
/*     */           break;
/*     */         default:
/* 220 */           if (c >= 0 && c <= 65535 && isWhitespace((char)c)) {
/* 221 */             if (this.count >= this.buffer.length)
/* 222 */               this.buffer = XArray.resize(this.buffer, this.count + Math.min(8192, this.count)); 
/* 224 */             this.buffer[this.count++] = (char)c;
/*     */           } else {
/* 226 */             flushBuffer();
/* 227 */             this.out.write(c);
/*     */           } 
/* 229 */           this.skipCR = false;
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(char[] cbuf, int offset, int length) throws IOException {
/* 246 */     if (offset < 0 || length < 0 || offset + length > cbuf.length)
/* 247 */       throw new IndexOutOfBoundsException(); 
/* 249 */     if (length == 0)
/*     */       return; 
/* 252 */     synchronized (this.lock) {
/* 253 */       if (this.skipCR && cbuf[offset] == '\n') {
/* 254 */         offset++;
/* 255 */         length--;
/*     */       } 
/* 257 */       int upper = offset;
/* 258 */       for (; length != 0; length--) {
/* 259 */         switch (cbuf[upper++]) {
/*     */           case '\r':
/* 261 */             writeLine(cbuf, offset, upper - 1);
/* 262 */             writeEOL();
/* 263 */             if (length > 1 && cbuf[upper] == '\n') {
/* 264 */               upper++;
/* 265 */               length--;
/*     */             } 
/* 267 */             offset = upper;
/*     */             break;
/*     */           case '\n':
/* 271 */             writeLine(cbuf, offset, upper - 1);
/* 272 */             writeEOL();
/* 273 */             offset = upper;
/*     */             break;
/*     */         } 
/*     */       } 
/* 278 */       this.skipCR = (cbuf[upper - 1] == '\r');
/* 283 */       for (int i = upper; --i >= offset;) {
/* 284 */         if (!isWhitespace(cbuf[i])) {
/* 285 */           writeLine(cbuf, offset, offset = i + 1);
/*     */           break;
/*     */         } 
/*     */       } 
/* 289 */       length = upper - offset;
/* 290 */       int newCount = this.count + length;
/* 291 */       if (newCount > this.buffer.length)
/* 292 */         this.buffer = XArray.resize(this.buffer, newCount); 
/* 294 */       System.arraycopy(cbuf, offset, this.buffer, this.count, length);
/* 295 */       this.count = newCount;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String string, int offset, int length) throws IOException {
/* 309 */     if (offset < 0 || length < 0 || offset + length > string.length())
/* 310 */       throw new IndexOutOfBoundsException(); 
/* 312 */     if (length == 0)
/*     */       return; 
/* 315 */     synchronized (this.lock) {
/* 316 */       if (this.skipCR && string.charAt(offset) == '\n') {
/* 317 */         offset++;
/* 318 */         length--;
/*     */       } 
/* 320 */       int upper = offset;
/* 321 */       for (; length != 0; length--) {
/* 322 */         switch (string.charAt(upper++)) {
/*     */           case '\r':
/* 324 */             writeLine(string, offset, upper - 1);
/* 325 */             writeEOL();
/* 326 */             if (length > 1 && string.charAt(upper) == '\n') {
/* 327 */               upper++;
/* 328 */               length--;
/*     */             } 
/* 330 */             offset = upper;
/*     */             break;
/*     */           case '\n':
/* 334 */             writeLine(string, offset, upper - 1);
/* 335 */             writeEOL();
/* 336 */             offset = upper;
/*     */             break;
/*     */         } 
/*     */       } 
/* 341 */       this.skipCR = (string.charAt(upper - 1) == '\r');
/* 346 */       for (int i = upper; --i >= offset;) {
/* 347 */         if (!isWhitespace(string.charAt(i))) {
/* 348 */           writeLine(string, offset, offset = i + 1);
/*     */           break;
/*     */         } 
/*     */       } 
/* 352 */       length = upper - offset;
/* 353 */       int newCount = this.count + length;
/* 354 */       if (newCount > this.buffer.length)
/* 355 */         this.buffer = XArray.resize(this.buffer, newCount); 
/* 357 */       while (--length >= 0)
/* 358 */         this.buffer[this.count++] = string.charAt(offset++); 
/* 360 */       assert this.count == newCount : newCount;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 373 */     synchronized (this.lock) {
/* 374 */       flushBuffer();
/* 375 */       super.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isWhitespace(char c) throws IOException {
/* 391 */     return Character.isSpaceChar(c);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\LineWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */