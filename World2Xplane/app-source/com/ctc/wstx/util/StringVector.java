/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ public final class StringVector {
/*     */   private String[] mStrings;
/*     */   
/*     */   private int mSize;
/*     */   
/*     */   public StringVector(int initialCount) {
/*  22 */     this.mStrings = new String[initialCount];
/*     */   }
/*     */   
/*     */   public int size() {
/*  31 */     return this.mSize;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  33 */     return (this.mSize == 0);
/*     */   }
/*     */   
/*     */   public String getString(int index) {
/*  36 */     if (index < 0 || index >= this.mSize)
/*  37 */       throw new IllegalArgumentException("Index " + index + " out of valid range; current size: " + this.mSize + "."); 
/*  39 */     return this.mStrings[index];
/*     */   }
/*     */   
/*     */   public String getLastString() {
/*  43 */     if (this.mSize < 1)
/*  44 */       throw new IllegalStateException("getLastString() called on empty StringVector."); 
/*  46 */     return this.mStrings[this.mSize - 1];
/*     */   }
/*     */   
/*     */   public String[] getInternalArray() {
/*  50 */     return this.mStrings;
/*     */   }
/*     */   
/*     */   public String[] asArray() {
/*  54 */     String[] strs = new String[this.mSize];
/*  55 */     System.arraycopy(this.mStrings, 0, strs, 0, this.mSize);
/*  56 */     return strs;
/*     */   }
/*     */   
/*     */   public boolean containsInterned(String value) {
/*  60 */     String[] str = this.mStrings;
/*  61 */     for (int i = 0, len = this.mSize; i < len; i++) {
/*  62 */       if (str[i] == value)
/*  63 */         return true; 
/*     */     } 
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public void addString(String str) {
/*  76 */     if (this.mSize == this.mStrings.length) {
/*  77 */       String[] old = this.mStrings;
/*  78 */       int oldSize = old.length;
/*  79 */       this.mStrings = new String[oldSize + (oldSize << 1)];
/*  80 */       System.arraycopy(old, 0, this.mStrings, 0, oldSize);
/*     */     } 
/*  82 */     this.mStrings[this.mSize++] = str;
/*     */   }
/*     */   
/*     */   public void addStrings(String str1, String str2) {
/*  86 */     if (this.mSize + 2 > this.mStrings.length) {
/*  87 */       String[] old = this.mStrings;
/*  88 */       int oldSize = old.length;
/*  89 */       this.mStrings = new String[oldSize + (oldSize << 1)];
/*  90 */       System.arraycopy(old, 0, this.mStrings, 0, oldSize);
/*     */     } 
/*  92 */     this.mStrings[this.mSize] = str1;
/*  93 */     this.mStrings[this.mSize + 1] = str2;
/*  94 */     this.mSize += 2;
/*     */   }
/*     */   
/*     */   public void setString(int index, String str) {
/*  98 */     this.mStrings[index] = str;
/*     */   }
/*     */   
/*     */   public void clear(boolean removeRefs) {
/* 102 */     if (removeRefs)
/* 103 */       for (int i = 0, len = this.mSize; i < len; i++)
/* 104 */         this.mStrings[i] = null;  
/* 107 */     this.mSize = 0;
/*     */   }
/*     */   
/*     */   public String removeLast() {
/* 111 */     String result = this.mStrings[--this.mSize];
/* 112 */     this.mStrings[this.mSize] = null;
/* 113 */     return result;
/*     */   }
/*     */   
/*     */   public void removeLast(int count) {
/* 117 */     while (--count >= 0)
/* 118 */       this.mStrings[--this.mSize] = null; 
/*     */   }
/*     */   
/*     */   public String findLastFromMap(String key) {
/* 138 */     int index = this.mSize;
/* 139 */     for (index -= 2; index >= 0;) {
/* 140 */       if (this.mStrings[index] == key)
/* 141 */         return this.mStrings[index + 1]; 
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   public String findLastNonInterned(String key) {
/* 149 */     int index = this.mSize;
/* 150 */     for (index -= 2; index >= 0; ) {
/* 151 */       String curr = this.mStrings[index];
/* 152 */       if (curr == key || (curr != null && curr.equals(key)))
/* 153 */         return this.mStrings[index + 1]; 
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */   
/*     */   public int findLastIndexNonInterned(String key) {
/* 160 */     int index = this.mSize;
/* 161 */     for (index -= 2; index >= 0; ) {
/* 162 */       String curr = this.mStrings[index];
/* 163 */       if (curr == key || (curr != null && curr.equals(key)))
/* 164 */         return index; 
/*     */     } 
/* 167 */     return -1;
/*     */   }
/*     */   
/*     */   public String findLastByValueNonInterned(String value) {
/* 171 */     for (int index = this.mSize - 1; index > 0; index -= 2) {
/* 172 */       String currVal = this.mStrings[index];
/* 173 */       if (currVal == value || (currVal != null && currVal.equals(value)))
/* 174 */         return this.mStrings[index - 1]; 
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   public int findLastIndexByValueNonInterned(String value) {
/* 181 */     for (int index = this.mSize - 1; index > 0; index -= 2) {
/* 182 */       String currVal = this.mStrings[index];
/* 183 */       if (currVal == value || (currVal != null && currVal.equals(value)))
/* 184 */         return index - 1; 
/*     */     } 
/* 187 */     return -1;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 226 */     StringBuffer sb = new StringBuffer(this.mSize * 16);
/* 227 */     sb.append("[(size = ");
/* 228 */     sb.append(this.mSize);
/* 229 */     sb.append(" ) ");
/* 230 */     for (int i = 0; i < this.mSize; i++) {
/* 231 */       if (i > 0)
/* 232 */         sb.append(", "); 
/* 234 */       sb.append('"');
/* 235 */       sb.append(this.mStrings[i]);
/* 236 */       sb.append('"');
/* 238 */       sb.append(" == ");
/* 239 */       sb.append(Integer.toHexString(System.identityHashCode(this.mStrings[i])));
/*     */     } 
/* 241 */     sb.append(']');
/* 242 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\StringVector.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */