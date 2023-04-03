/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ public class ExpandedTabWriter extends FilterWriter {
/*  41 */   private int tabWidth = 8;
/*     */   
/*  46 */   private int column = 0;
/*     */   
/*     */   public ExpandedTabWriter(Writer out) {
/*  55 */     super(out);
/*     */   }
/*     */   
/*     */   public ExpandedTabWriter(Writer out, int tabWidth) throws IllegalArgumentException {
/*  67 */     super(out);
/*  68 */     setTabWidth(tabWidth);
/*     */   }
/*     */   
/*     */   public void setTabWidth(int tabWidth) throws IllegalArgumentException {
/*  78 */     synchronized (this.lock) {
/*  79 */       if (tabWidth > 0) {
/*  80 */         this.tabWidth = tabWidth;
/*     */       } else {
/*  82 */         throw new IllegalArgumentException(Integer.toString(tabWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getTabWidth() {
/*  93 */     return this.tabWidth;
/*     */   }
/*     */   
/*     */   private void expand() throws IOException {
/* 102 */     int width = this.tabWidth - this.column % this.tabWidth;
/* 103 */     this.out.write(Utilities.spaces(width));
/* 104 */     this.column += width;
/*     */   }
/*     */   
/*     */   public void write(int c) throws IOException {
/* 114 */     synchronized (this.lock) {
/* 115 */       switch (c) {
/*     */         case 10:
/*     */         case 13:
/* 117 */           this.column = 0;
/*     */           break;
/*     */         case 9:
/* 118 */           expand();
/*     */           return;
/*     */         default:
/* 119 */           this.column++;
/*     */           break;
/*     */       } 
/* 121 */       this.out.write(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(char[] buffer, int offset, int length) throws IOException {
/* 135 */     synchronized (this.lock) {
/* 136 */       int start = offset;
/* 137 */       length += offset;
/* 138 */       for (int end = offset; end < length; end++) {
/* 139 */         char c = buffer[end];
/* 140 */         switch (c) {
/*     */           case '\n':
/*     */           case '\r':
/* 142 */             this.column = 0;
/*     */             break;
/*     */           case '\t':
/* 145 */             this.out.write(buffer, start, end - start);
/* 146 */             start = end + 1;
/* 147 */             expand();
/*     */             break;
/*     */           default:
/* 150 */             this.column++;
/*     */             break;
/*     */         } 
/*     */       } 
/* 154 */       this.out.write(buffer, start, length - start);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String string, int offset, int length) throws IOException {
/* 168 */     synchronized (this.lock) {
/* 169 */       int start = offset;
/* 170 */       length += offset;
/* 171 */       for (int end = offset; end < length; end++) {
/* 172 */         char c = string.charAt(end);
/* 173 */         switch (c) {
/*     */           case '\n':
/*     */           case '\r':
/* 175 */             this.column = 0;
/*     */             break;
/*     */           case '\t':
/* 178 */             this.out.write(string, start, end - start);
/* 179 */             start = end + 1;
/* 180 */             expand();
/*     */             break;
/*     */           default:
/* 183 */             this.column++;
/*     */             break;
/*     */         } 
/*     */       } 
/* 187 */       this.out.write(string, start, length - start);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\ExpandedTabWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */