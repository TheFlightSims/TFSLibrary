/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import javax.xml.stream.Location;
/*     */ 
/*     */ public final class ElementIdMap {
/*     */   protected static final int DEFAULT_SIZE = 128;
/*     */   
/*     */   protected static final int MIN_SIZE = 16;
/*     */   
/*     */   protected static final int FILL_PCT = 80;
/*     */   
/*     */   protected ElementId[] mTable;
/*     */   
/*     */   protected int mSize;
/*     */   
/*     */   protected int mSizeThreshold;
/*     */   
/*     */   protected int mIndexMask;
/*     */   
/*     */   protected ElementId mHead;
/*     */   
/*     */   protected ElementId mTail;
/*     */   
/*     */   public ElementIdMap() {
/* 102 */     this(128);
/*     */   }
/*     */   
/*     */   public ElementIdMap(int initialSize) {
/* 111 */     int actual = 16;
/* 112 */     while (actual < initialSize)
/* 113 */       actual += actual; 
/* 115 */     this.mTable = new ElementId[actual];
/* 117 */     this.mIndexMask = actual - 1;
/* 118 */     this.mSize = 0;
/* 119 */     this.mSizeThreshold = actual * 80 / 100;
/* 120 */     this.mHead = this.mTail = null;
/*     */   }
/*     */   
/*     */   public ElementId getFirstUndefined() {
/* 135 */     return this.mHead;
/*     */   }
/*     */   
/*     */   public ElementId addReferenced(char[] buffer, int start, int len, int hash, Location loc, PrefixedName elemName, PrefixedName attrName) {
/* 146 */     int index = hash & this.mIndexMask;
/* 147 */     ElementId id = this.mTable[index];
/* 149 */     while (id != null) {
/* 150 */       if (id.idMatches(buffer, start, len))
/* 151 */         return id; 
/* 153 */       id = id.nextColliding();
/*     */     } 
/* 159 */     if (this.mSize >= this.mSizeThreshold) {
/* 160 */       rehash();
/* 162 */       index = hash & this.mIndexMask;
/*     */     } 
/* 164 */     this.mSize++;
/* 167 */     String idStr = new String(buffer, start, len);
/* 168 */     id = new ElementId(idStr, loc, false, elemName, attrName);
/* 171 */     id.setNextColliding(this.mTable[index]);
/* 172 */     this.mTable[index] = id;
/* 175 */     if (this.mHead == null) {
/* 176 */       this.mHead = this.mTail = id;
/*     */     } else {
/* 178 */       this.mTail.linkUndefined(id);
/* 179 */       this.mTail = id;
/*     */     } 
/* 181 */     return id;
/*     */   }
/*     */   
/*     */   public ElementId addReferenced(String idStr, Location loc, PrefixedName elemName, PrefixedName attrName) {
/* 187 */     int hash = calcHash(idStr);
/* 188 */     int index = hash & this.mIndexMask;
/* 189 */     ElementId id = this.mTable[index];
/* 191 */     while (id != null) {
/* 192 */       if (id.idMatches(idStr))
/* 193 */         return id; 
/* 195 */       id = id.nextColliding();
/*     */     } 
/* 201 */     if (this.mSize >= this.mSizeThreshold) {
/* 202 */       rehash();
/* 204 */       index = hash & this.mIndexMask;
/*     */     } 
/* 206 */     this.mSize++;
/* 209 */     id = new ElementId(idStr, loc, false, elemName, attrName);
/* 212 */     id.setNextColliding(this.mTable[index]);
/* 213 */     this.mTable[index] = id;
/* 216 */     if (this.mHead == null) {
/* 217 */       this.mHead = this.mTail = id;
/*     */     } else {
/* 219 */       this.mTail.linkUndefined(id);
/* 220 */       this.mTail = id;
/*     */     } 
/* 222 */     return id;
/*     */   }
/*     */   
/*     */   public ElementId addDefined(char[] buffer, int start, int len, int hash, Location loc, PrefixedName elemName, PrefixedName attrName) {
/* 236 */     int index = hash & this.mIndexMask;
/* 237 */     ElementId id = this.mTable[index];
/* 239 */     while (id != null && 
/* 240 */       !id.idMatches(buffer, start, len))
/* 243 */       id = id.nextColliding(); 
/* 249 */     if (id == null) {
/* 251 */       if (this.mSize >= this.mSizeThreshold) {
/* 252 */         rehash();
/* 253 */         index = hash & this.mIndexMask;
/*     */       } 
/* 255 */       this.mSize++;
/* 256 */       String idStr = new String(buffer, start, len);
/* 257 */       id = new ElementId(idStr, loc, true, elemName, attrName);
/* 258 */       id.setNextColliding(this.mTable[index]);
/* 259 */       this.mTable[index] = id;
/* 265 */     } else if (!id.isDefined()) {
/* 271 */       id.markDefined(loc);
/* 276 */       if (id == this.mHead) {
/*     */         do {
/* 278 */           this.mHead = this.mHead.nextUndefined();
/* 279 */         } while (this.mHead != null && this.mHead.isDefined());
/* 282 */         if (this.mHead == null)
/* 283 */           this.mTail = null; 
/*     */       } 
/*     */     } 
/* 289 */     return id;
/*     */   }
/*     */   
/*     */   public ElementId addDefined(String idStr, Location loc, PrefixedName elemName, PrefixedName attrName) {
/* 295 */     int hash = calcHash(idStr);
/* 296 */     int index = hash & this.mIndexMask;
/* 297 */     ElementId id = this.mTable[index];
/* 299 */     while (id != null && 
/* 300 */       !id.idMatches(idStr))
/* 303 */       id = id.nextColliding(); 
/* 309 */     if (id == null) {
/* 310 */       if (this.mSize >= this.mSizeThreshold) {
/* 311 */         rehash();
/* 312 */         index = hash & this.mIndexMask;
/*     */       } 
/* 314 */       this.mSize++;
/* 315 */       id = new ElementId(idStr, loc, true, elemName, attrName);
/* 316 */       id.setNextColliding(this.mTable[index]);
/* 317 */       this.mTable[index] = id;
/* 323 */     } else if (!id.isDefined()) {
/* 329 */       id.markDefined(loc);
/* 334 */       if (id == this.mHead) {
/*     */         do {
/* 336 */           this.mHead = this.mHead.nextUndefined();
/* 337 */         } while (this.mHead != null && this.mHead.isDefined());
/* 338 */         if (this.mHead == null)
/* 339 */           this.mTail = null; 
/*     */       } 
/*     */     } 
/* 345 */     return id;
/*     */   }
/*     */   
/*     */   public static int calcHash(char[] buffer, int start, int len) {
/* 362 */     int hash = buffer[0];
/* 363 */     for (int i = 1; i < len; i++)
/* 364 */       hash = hash * 31 + buffer[i]; 
/* 366 */     return hash;
/*     */   }
/*     */   
/*     */   public static int calcHash(String key) {
/* 371 */     int hash = key.charAt(0);
/* 372 */     for (int i = 1, len = key.length(); i < len; i++)
/* 373 */       hash = hash * 31 + key.charAt(i); 
/* 376 */     return hash;
/*     */   }
/*     */   
/*     */   private void rehash() {
/* 394 */     int size = this.mTable.length;
/* 400 */     int newSize = size << 2;
/* 401 */     ElementId[] oldSyms = this.mTable;
/* 402 */     this.mTable = new ElementId[newSize];
/* 405 */     this.mIndexMask = newSize - 1;
/* 406 */     this.mSizeThreshold <<= 2;
/* 408 */     int count = 0;
/* 410 */     for (int i = 0; i < size; i++) {
/* 411 */       for (ElementId id = oldSyms[i]; id != null; ) {
/* 412 */         count++;
/* 413 */         int index = calcHash(id.getId()) & this.mIndexMask;
/* 414 */         ElementId nextIn = id.nextColliding();
/* 415 */         id.setNextColliding(this.mTable[index]);
/* 416 */         this.mTable[index] = id;
/* 417 */         id = nextIn;
/*     */       } 
/*     */     } 
/* 421 */     if (count != this.mSize)
/* 422 */       ExceptionUtil.throwInternal("on rehash(): had " + this.mSize + " entries; now have " + count + "."); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\ElementIdMap.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */