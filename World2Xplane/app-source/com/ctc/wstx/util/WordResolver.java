/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class WordResolver {
/*     */   public static final int MAX_WORDS = 8192;
/*     */   
/*     */   static final char CHAR_NULL = '\000';
/*     */   
/*     */   static final int NEGATIVE_OFFSET = 57344;
/*     */   
/*     */   static final int MIN_BINARY_SEARCH = 7;
/*     */   
/*     */   final char[] mData;
/*     */   
/*     */   final String[] mWords;
/*     */   
/*     */   private WordResolver(String[] words, char[] index) {
/*  63 */     this.mWords = words;
/*  64 */     this.mData = index;
/*     */   }
/*     */   
/*     */   public static WordResolver constructInstance(TreeSet wordSet) {
/*  80 */     if (wordSet.size() > 8192)
/*  81 */       return null; 
/*  83 */     return (new Builder(wordSet)).construct();
/*     */   }
/*     */   
/*     */   public int size() {
/*  96 */     return this.mWords.length;
/*     */   }
/*     */   
/*     */   public String find(char[] str, int start, int end) {
/* 117 */     char[] data = this.mData;
/* 120 */     if (data == null)
/* 121 */       return findFromOne(str, start, end); 
/* 124 */     int ptr = 0;
/* 125 */     int offset = start;
/*     */     while (true) {
/* 129 */       if (offset == end) {
/* 130 */         if (data[ptr + 1] == '\000')
/* 131 */           return this.mWords[data[ptr + 2] - 57344]; 
/* 133 */         return null;
/*     */       } 
/* 136 */       int count = data[ptr++];
/* 138 */       char c = str[offset++];
/* 143 */       if (count < 7) {
/* 145 */         if (data[ptr] == c) {
/* 146 */           ptr = data[ptr + 1];
/* 149 */         } else if (data[ptr + 2] == c) {
/* 150 */           ptr = data[ptr + 3];
/*     */         } else {
/* 153 */           int branchEnd = ptr + (count << 1);
/* 155 */           ptr += 4;
/*     */           while (true) {
/* 155 */             if (ptr < branchEnd) {
/* 156 */               if (data[ptr] == c) {
/* 157 */                 ptr = data[ptr + 1];
/*     */                 break;
/*     */               } 
/*     */               ptr += 2;
/*     */               continue;
/*     */             } 
/* 161 */             return null;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 163 */         int low = 0;
/* 164 */         int high = count - 1;
/*     */         while (true) {
/* 167 */           if (low <= high) {
/* 168 */             int mid = low + high >> 1;
/* 169 */             int ix = ptr + (mid << 1);
/* 170 */             int diff = data[ix] - c;
/* 171 */             if (diff > 0) {
/* 172 */               high = mid - 1;
/*     */               continue;
/*     */             } 
/* 173 */             if (diff < 0) {
/* 174 */               low = mid + 1;
/*     */               continue;
/*     */             } 
/* 176 */             ptr = data[ix + 1];
/*     */             break;
/*     */           } 
/* 180 */           return null;
/*     */         } 
/*     */       } 
/* 185 */       if (ptr >= 57344) {
/* 186 */         String word = this.mWords[ptr - 57344];
/* 187 */         int expLen = end - start;
/* 188 */         if (word.length() != expLen)
/* 189 */           return null; 
/* 191 */         for (int i = offset - start; offset < end; i++, offset++) {
/* 192 */           if (word.charAt(i) != str[offset])
/* 193 */             return null; 
/*     */         } 
/* 196 */         return word;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String findFromOne(char[] str, int start, int end) {
/* 204 */     String word = this.mWords[0];
/* 205 */     int len = end - start;
/* 206 */     if (word.length() != len)
/* 207 */       return null; 
/* 209 */     for (int i = 0; i < len; i++) {
/* 210 */       if (word.charAt(i) != str[start + i])
/* 211 */         return null; 
/*     */     } 
/* 214 */     return word;
/*     */   }
/*     */   
/*     */   public String find(String str) {
/* 223 */     char[] data = this.mData;
/* 226 */     if (data == null) {
/* 227 */       String word = this.mWords[0];
/* 228 */       return word.equals(str) ? word : null;
/*     */     } 
/* 231 */     int ptr = 0;
/* 232 */     int offset = 0;
/* 233 */     int end = str.length();
/*     */     while (true) {
/* 237 */       if (offset == end) {
/* 238 */         if (data[ptr + 1] == '\000')
/* 239 */           return this.mWords[data[ptr + 2] - 57344]; 
/* 241 */         return null;
/*     */       } 
/* 244 */       int count = data[ptr++];
/* 246 */       char c = str.charAt(offset++);
/* 251 */       if (count < 7) {
/* 253 */         if (data[ptr] == c) {
/* 254 */           ptr = data[ptr + 1];
/* 257 */         } else if (data[ptr + 2] == c) {
/* 258 */           ptr = data[ptr + 3];
/*     */         } else {
/* 261 */           int branchEnd = ptr + (count << 1);
/* 263 */           ptr += 4;
/*     */           while (true) {
/* 263 */             if (ptr < branchEnd) {
/* 264 */               if (data[ptr] == c) {
/* 265 */                 ptr = data[ptr + 1];
/*     */                 break;
/*     */               } 
/*     */               ptr += 2;
/*     */               continue;
/*     */             } 
/* 269 */             return null;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 271 */         int low = 0;
/* 272 */         int high = count - 1;
/*     */         while (true) {
/* 275 */           if (low <= high) {
/* 276 */             int mid = low + high >> 1;
/* 277 */             int ix = ptr + (mid << 1);
/* 278 */             int diff = data[ix] - c;
/* 279 */             if (diff > 0) {
/* 280 */               high = mid - 1;
/*     */               continue;
/*     */             } 
/* 281 */             if (diff < 0) {
/* 282 */               low = mid + 1;
/*     */               continue;
/*     */             } 
/* 284 */             ptr = data[ix + 1];
/*     */             break;
/*     */           } 
/* 288 */           return null;
/*     */         } 
/*     */       } 
/* 293 */       if (ptr >= 57344) {
/* 294 */         String word = this.mWords[ptr - 57344];
/* 295 */         if (word.length() != str.length())
/* 296 */           return null; 
/* 298 */         for (; offset < end; offset++) {
/* 299 */           if (word.charAt(offset) != str.charAt(offset))
/* 300 */             return null; 
/*     */         } 
/* 303 */         return word;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 317 */     StringBuffer sb = new StringBuffer(16 + (this.mWords.length << 3));
/* 318 */     for (int i = 0, len = this.mWords.length; i < len; i++) {
/* 319 */       if (i > 0)
/* 320 */         sb.append(", "); 
/* 322 */       sb.append(this.mWords[i]);
/*     */     } 
/* 324 */     return sb.toString();
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
/* 352 */       int wordCount = wordSet.size();
/* 354 */       this.mWords = new String[wordCount];
/* 355 */       wordSet.toArray((Object[])this.mWords);
/* 361 */       if (wordCount < 2) {
/* 362 */         if (wordCount == 0)
/* 363 */           throw new IllegalArgumentException(); 
/* 365 */         this.mData = null;
/*     */       } else {
/* 371 */         int size = wordCount * 6;
/* 372 */         if (size < 256)
/* 373 */           size = 256; 
/* 375 */         this.mData = new char[size];
/*     */       } 
/*     */     }
/*     */     
/*     */     public WordResolver construct() {
/*     */       char[] result;
/* 391 */       if (this.mData == null) {
/* 392 */         result = null;
/*     */       } else {
/* 394 */         constructBranch(0, 0, this.mWords.length);
/* 397 */         if (this.mSize > 57344)
/* 398 */           return null; 
/* 401 */         result = new char[this.mSize];
/* 402 */         System.arraycopy(this.mData, 0, result, 0, this.mSize);
/*     */       } 
/* 405 */       return new WordResolver(this.mWords, result);
/*     */     }
/*     */     
/*     */     private void constructBranch(int charIndex, int start, int end) {
/*     */       boolean gotRunt;
/* 424 */       if (this.mSize >= this.mData.length)
/* 425 */         expand(1); 
/* 427 */       this.mData[this.mSize++] = Character.MIN_VALUE;
/* 432 */       int structStart = this.mSize + 1;
/* 433 */       int groupCount = 0;
/* 434 */       int groupStart = start;
/* 435 */       String[] words = this.mWords;
/* 445 */       if (words[groupStart].length() == charIndex) {
/* 446 */         if (this.mSize + 2 > this.mData.length)
/* 447 */           expand(2); 
/* 452 */         this.mData[this.mSize++] = Character.MIN_VALUE;
/* 453 */         this.mData[this.mSize++] = (char)(57344 + groupStart);
/* 456 */         groupStart++;
/* 457 */         groupCount++;
/* 458 */         gotRunt = true;
/*     */       } else {
/* 460 */         gotRunt = false;
/*     */       } 
/* 464 */       while (groupStart < end) {
/* 466 */         char c = words[groupStart].charAt(charIndex);
/* 467 */         int j = groupStart + 1;
/* 468 */         while (j < end && words[j].charAt(charIndex) == c)
/* 469 */           j++; 
/* 475 */         if (this.mSize + 2 > this.mData.length)
/* 476 */           expand(2); 
/* 478 */         this.mData[this.mSize++] = c;
/* 479 */         this.mData[this.mSize++] = (char)(j - groupStart);
/* 480 */         groupStart = j;
/* 481 */         groupCount++;
/*     */       } 
/* 488 */       this.mData[structStart - 2] = (char)groupCount;
/* 489 */       groupStart = start;
/* 492 */       if (gotRunt) {
/* 493 */         structStart += 2;
/* 494 */         groupStart++;
/*     */       } 
/* 497 */       int structEnd = this.mSize;
/* 498 */       charIndex++;
/* 499 */       for (; structStart < structEnd; structStart += 2) {
/* 500 */         groupCount = this.mData[structStart];
/* 504 */         if (groupCount == 1) {
/* 505 */           this.mData[structStart] = (char)(57344 + groupStart);
/*     */         } else {
/* 507 */           this.mData[structStart] = (char)this.mSize;
/* 508 */           constructBranch(charIndex, groupStart, groupStart + groupCount);
/*     */         } 
/* 511 */         groupStart += groupCount;
/*     */       } 
/*     */     }
/*     */     
/*     */     private char[] expand(int needSpace) {
/* 519 */       char[] old = this.mData;
/* 520 */       int len = old.length;
/* 521 */       int newSize = len + ((len < 4096) ? len : (len >> 1));
/* 526 */       if (newSize < this.mSize + needSpace)
/* 527 */         newSize = this.mSize + needSpace + 64; 
/* 529 */       this.mData = new char[newSize];
/* 530 */       System.arraycopy(old, 0, this.mData, 0, len);
/* 531 */       return this.mData;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\WordResolver.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */