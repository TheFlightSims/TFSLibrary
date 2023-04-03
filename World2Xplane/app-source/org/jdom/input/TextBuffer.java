/*     */ package org.jdom.input;
/*     */ 
/*     */ import org.jdom.Verifier;
/*     */ 
/*     */ class TextBuffer {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: TextBuffer.java,v $ $Revision: 1.10 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   private String prefixString;
/*     */   
/*  97 */   private char[] array = new char[4096];
/*     */   
/*  98 */   private int arraySize = 0;
/*     */   
/*     */   void append(char[] source, int start, int count) {
/* 103 */     if (this.prefixString == null) {
/* 105 */       this.prefixString = new String(source, start, count);
/*     */     } else {
/* 109 */       ensureCapacity(this.arraySize + count);
/* 110 */       System.arraycopy(source, start, this.array, this.arraySize, count);
/* 111 */       this.arraySize += count;
/*     */     } 
/*     */   }
/*     */   
/*     */   int size() {
/* 117 */     if (this.prefixString == null)
/* 118 */       return 0; 
/* 121 */     return this.prefixString.length() + this.arraySize;
/*     */   }
/*     */   
/*     */   void clear() {
/* 127 */     this.arraySize = 0;
/* 128 */     this.prefixString = null;
/*     */   }
/*     */   
/*     */   boolean isAllWhitespace() {
/* 132 */     if (this.prefixString == null || this.prefixString.length() == 0)
/* 133 */       return true; 
/* 136 */     int size = this.prefixString.length();
/*     */     int i;
/* 137 */     for (i = 0; i < size; i++) {
/* 138 */       if (!Verifier.isXMLWhitespace(this.prefixString.charAt(i)))
/* 139 */         return false; 
/*     */     } 
/* 143 */     for (i = 0; i < this.arraySize; i++) {
/* 144 */       if (!Verifier.isXMLWhitespace(this.array[i]))
/* 145 */         return false; 
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 153 */     if (this.prefixString == null)
/* 154 */       return ""; 
/* 157 */     String str = "";
/* 158 */     if (this.arraySize == 0) {
/* 160 */       str = this.prefixString;
/*     */     } else {
/* 165 */       str = (new StringBuffer(this.prefixString.length() + this.arraySize)).append(this.prefixString).append(this.array, 0, this.arraySize).toString();
/*     */     } 
/* 170 */     return str;
/*     */   }
/*     */   
/*     */   private void ensureCapacity(int csize) {
/* 175 */     int capacity = this.array.length;
/* 176 */     if (csize > capacity) {
/* 177 */       char[] old = this.array;
/* 178 */       int nsize = capacity;
/* 179 */       while (csize > nsize)
/* 180 */         nsize += capacity / 2; 
/* 182 */       this.array = new char[nsize];
/* 183 */       System.arraycopy(old, 0, this.array, 0, this.arraySize);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\TextBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */