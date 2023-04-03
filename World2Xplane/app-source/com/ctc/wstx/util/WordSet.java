/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class WordSet {
/*     */   static final char CHAR_NULL = '\000';
/*     */   
/*     */   static final int NEGATIVE_OFFSET = 49152;
/*     */   
/*     */   static final int MIN_BINARY_SEARCH = 7;
/*     */   
/*     */   final char[] mData;
/*     */   
/*     */   private WordSet(char[] data) {
/*  66 */     this.mData = data;
/*     */   }
/*     */   
/*     */   public static WordSet constructSet(TreeSet wordSet) {
/*  71 */     return new WordSet((new Builder(wordSet)).construct());
/*     */   }
/*     */   
/*     */   public static char[] constructRaw(TreeSet wordSet) {
/*  76 */     return (new Builder(wordSet)).construct();
/*     */   }
/*     */   
/*     */   public boolean contains(char[] buf, int start, int end) {
/*  86 */     return contains(this.mData, buf, start, end);
/*     */   }
/*     */   
/*     */   public static boolean contains(char[] data, char[] str, int start, int end) {
/*  91 */     int ptr = 0;
/*     */     do {
/*  95 */       int left = end - start;
/*  98 */       if (left == 0)
/*  99 */         return (data[ptr + 1] == '\000'); 
/* 102 */       int count = data[ptr++];
/* 105 */       if (count >= 49152) {
/* 107 */         int expCount = count - 49152;
/* 108 */         if (left != expCount)
/* 109 */           return false; 
/* 111 */         while (start < end) {
/* 112 */           if (data[ptr] != str[start])
/* 113 */             return false; 
/* 115 */           ptr++;
/* 116 */           start++;
/*     */         } 
/* 118 */         return true;
/*     */       } 
/* 122 */       char c = str[start++];
/* 125 */       if (count < 7) {
/* 127 */         if (data[ptr] == c) {
/* 128 */           ptr = data[ptr + 1];
/* 131 */         } else if (data[ptr + 2] == c) {
/* 132 */           ptr = data[ptr + 3];
/*     */         } else {
/* 135 */           int branchEnd = ptr + (count << 1);
/* 137 */           ptr += 4;
/*     */           while (true) {
/* 137 */             if (ptr < branchEnd) {
/* 138 */               if (data[ptr] == c) {
/* 139 */                 ptr = data[ptr + 1];
/*     */                 break;
/*     */               } 
/*     */               ptr += 2;
/*     */               continue;
/*     */             } 
/* 143 */             return false;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 147 */         int low = 0;
/* 148 */         int high = count - 1;
/*     */         while (true) {
/* 151 */           if (low <= high) {
/* 152 */             int mid = low + high >> 1;
/* 153 */             int ix = ptr + (mid << 1);
/* 154 */             int diff = data[ix] - c;
/* 155 */             if (diff > 0) {
/* 156 */               high = mid - 1;
/*     */               continue;
/*     */             } 
/* 157 */             if (diff < 0) {
/* 158 */               low = mid + 1;
/*     */               continue;
/*     */             } 
/* 160 */             ptr = data[ix + 1];
/*     */             break;
/*     */           } 
/* 167 */           return false;
/*     */         } 
/*     */       } 
/* 169 */     } while (ptr != 0);
/* 172 */     return (start == end);
/*     */   }
/*     */   
/*     */   public boolean contains(String str) {
/* 176 */     return contains(this.mData, str);
/*     */   }
/*     */   
/*     */   public static boolean contains(char[] data, String str) {
/* 182 */     int ptr = 0;
/* 183 */     int start = 0;
/* 184 */     int end = str.length();
/*     */     do {
/* 188 */       int left = end - start;
/* 191 */       if (left == 0)
/* 192 */         return (data[ptr + 1] == '\000'); 
/* 195 */       int count = data[ptr++];
/* 198 */       if (count >= 49152) {
/* 200 */         int expCount = count - 49152;
/* 201 */         if (left != expCount)
/* 202 */           return false; 
/* 204 */         while (start < end) {
/* 205 */           if (data[ptr] != str.charAt(start))
/* 206 */             return false; 
/* 208 */           ptr++;
/* 209 */           start++;
/*     */         } 
/* 211 */         return true;
/*     */       } 
/* 215 */       char c = str.charAt(start++);
/* 218 */       if (count < 7) {
/* 220 */         if (data[ptr] == c) {
/* 221 */           ptr = data[ptr + 1];
/* 224 */         } else if (data[ptr + 2] == c) {
/* 225 */           ptr = data[ptr + 3];
/*     */         } else {
/* 228 */           int branchEnd = ptr + (count << 1);
/* 230 */           ptr += 4;
/*     */           while (true) {
/* 230 */             if (ptr < branchEnd) {
/* 231 */               if (data[ptr] == c) {
/* 232 */                 ptr = data[ptr + 1];
/*     */                 break;
/*     */               } 
/*     */               ptr += 2;
/*     */               continue;
/*     */             } 
/* 236 */             return false;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 240 */         int low = 0;
/* 241 */         int high = count - 1;
/*     */         while (true) {
/* 244 */           if (low <= high) {
/* 245 */             int mid = low + high >> 1;
/* 246 */             int ix = ptr + (mid << 1);
/* 247 */             int diff = data[ix] - c;
/* 248 */             if (diff > 0) {
/* 249 */               high = mid - 1;
/*     */               continue;
/*     */             } 
/* 250 */             if (diff < 0) {
/* 251 */               low = mid + 1;
/*     */               continue;
/*     */             } 
/* 253 */             ptr = data[ix + 1];
/*     */             break;
/*     */           } 
/* 260 */           return false;
/*     */         } 
/*     */       } 
/* 262 */     } while (ptr != 0);
/* 265 */     return (start == end);
/*     */   }
/*     */   
/*     */   private static final class Builder {
/*     */     final String[] mWords;
/*     */     
/*     */     char[] mData;
/*     */     
/*     */     int mSize;
/*     */     
/*     */     public Builder(TreeSet wordSet) {
/* 292 */       int wordCount = wordSet.size();
/* 293 */       this.mWords = new String[wordCount];
/* 294 */       wordSet.toArray((Object[])this.mWords);
/* 300 */       int size = wordCount * 12;
/* 301 */       if (size < 256)
/* 302 */         size = 256; 
/* 304 */       this.mData = new char[size];
/*     */     }
/*     */     
/*     */     public char[] construct() {
/* 316 */       if (this.mWords.length == 1) {
/* 317 */         constructLeaf(0, 0);
/*     */       } else {
/* 319 */         constructBranch(0, 0, this.mWords.length);
/*     */       } 
/* 323 */       char[] result = new char[this.mSize];
/* 324 */       System.arraycopy(this.mData, 0, result, 0, this.mSize);
/* 325 */       return result;
/*     */     }
/*     */     
/*     */     private void constructBranch(int charIndex, int start, int end) {
/* 344 */       if (this.mSize >= this.mData.length)
/* 345 */         expand(1); 
/* 347 */       this.mData[this.mSize++] = Character.MIN_VALUE;
/* 352 */       int structStart = this.mSize + 1;
/* 353 */       int groupCount = 0;
/* 354 */       int groupStart = start;
/* 355 */       String[] words = this.mWords;
/* 363 */       if (words[groupStart].length() == charIndex) {
/* 364 */         if (this.mSize + 2 > this.mData.length)
/* 365 */           expand(2); 
/* 370 */         this.mData[this.mSize++] = Character.MIN_VALUE;
/* 371 */         this.mData[this.mSize++] = Character.MIN_VALUE;
/* 374 */         groupStart++;
/* 375 */         groupCount++;
/*     */       } 
/* 379 */       while (groupStart < end) {
/* 381 */         char c = words[groupStart].charAt(charIndex);
/* 382 */         int j = groupStart + 1;
/* 383 */         while (j < end && words[j].charAt(charIndex) == c)
/* 384 */           j++; 
/* 390 */         if (this.mSize + 2 > this.mData.length)
/* 391 */           expand(2); 
/* 393 */         this.mData[this.mSize++] = c;
/* 394 */         this.mData[this.mSize++] = (char)(j - groupStart);
/* 395 */         groupStart = j;
/* 396 */         groupCount++;
/*     */       } 
/* 403 */       this.mData[structStart - 2] = (char)groupCount;
/* 404 */       groupStart = start;
/* 407 */       if (this.mData[structStart] == '\000') {
/* 408 */         structStart += 2;
/* 409 */         groupStart++;
/*     */       } 
/* 412 */       int structEnd = this.mSize;
/* 413 */       charIndex++;
/* 414 */       for (; structStart < structEnd; structStart += 2) {
/* 415 */         groupCount = this.mData[structStart];
/* 417 */         this.mData[structStart] = (char)this.mSize;
/* 418 */         if (groupCount == 1) {
/* 422 */           String word = words[groupStart];
/* 423 */           if (word.length() == charIndex) {
/* 424 */             this.mData[structStart] = Character.MIN_VALUE;
/*     */           } else {
/* 426 */             constructLeaf(charIndex, groupStart);
/*     */           } 
/*     */         } else {
/* 429 */           constructBranch(charIndex, groupStart, groupStart + groupCount);
/*     */         } 
/* 432 */         groupStart += groupCount;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void constructLeaf(int charIndex, int wordIndex) {
/* 444 */       String word = this.mWords[wordIndex];
/* 445 */       int len = word.length();
/* 446 */       char[] data = this.mData;
/* 449 */       if (this.mSize + len + 1 >= data.length)
/* 450 */         data = expand(len + 1); 
/* 453 */       data[this.mSize++] = (char)(49152 + len - charIndex);
/* 454 */       for (; charIndex < len; charIndex++)
/* 455 */         data[this.mSize++] = word.charAt(charIndex); 
/*     */     }
/*     */     
/*     */     private char[] expand(int needSpace) {
/* 461 */       char[] old = this.mData;
/* 462 */       int len = old.length;
/* 463 */       int newSize = len + ((len < 4096) ? len : (len >> 1));
/* 468 */       if (newSize < this.mSize + needSpace)
/* 469 */         newSize = this.mSize + needSpace + 64; 
/* 471 */       this.mData = new char[newSize];
/* 472 */       System.arraycopy(old, 0, this.mData, 0, len);
/* 473 */       return this.mData;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\WordSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */