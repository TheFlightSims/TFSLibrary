/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ class HistogramHash {
/*     */   int capacity;
/*     */   
/*     */   int[] colors;
/*     */   
/*     */   int[] counts;
/*     */   
/*     */   int size;
/*     */   
/*     */   int hashsize;
/*     */   
/*     */   boolean packed = false;
/*     */   
/*     */   int[] newColors;
/*     */   
/*     */   int[] newCounts;
/*     */   
/*     */   public HistogramHash(int capacity) {
/* 465 */     this.capacity = capacity;
/* 466 */     this.hashsize = capacity * 4 / 3;
/* 467 */     this.colors = new int[this.hashsize];
/* 468 */     this.counts = new int[this.hashsize];
/*     */   }
/*     */   
/*     */   void init() {
/* 472 */     this.size = 0;
/* 473 */     this.packed = false;
/* 474 */     for (int i = 0; i < this.hashsize; i++) {
/* 475 */       this.colors[i] = -1;
/* 476 */       this.counts[i] = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean insert(int node) {
/* 481 */     int hashPos = hashCode(node);
/* 482 */     if (this.colors[hashPos] == -1) {
/* 483 */       this.colors[hashPos] = node;
/* 484 */       this.counts[hashPos] = this.counts[hashPos] + 1;
/* 485 */       this.size++;
/* 486 */       return (this.size <= this.capacity);
/*     */     } 
/* 487 */     if (this.colors[hashPos] == node) {
/* 488 */       this.counts[hashPos] = this.counts[hashPos] + 1;
/* 489 */       return (this.size <= this.capacity);
/*     */     } 
/* 491 */     for (int next = hashPos + 1; next != hashPos; next++) {
/* 492 */       next %= this.hashsize;
/* 493 */       if (this.colors[next] == -1) {
/* 494 */         this.colors[next] = node;
/* 495 */         this.counts[next] = this.counts[next] + 1;
/* 496 */         this.size++;
/* 497 */         return (this.size <= this.capacity);
/*     */       } 
/* 498 */       if (this.colors[next] == node) {
/* 499 */         this.counts[next] = this.counts[next] + 1;
/* 500 */         return (this.size <= this.capacity);
/*     */       } 
/*     */     } 
/* 504 */     return (this.size <= this.capacity);
/*     */   }
/*     */   
/*     */   boolean isFull() {
/* 508 */     return (this.size > this.capacity);
/*     */   }
/*     */   
/*     */   void put(int node, int value) {
/* 512 */     int hashPos = hashCode(node);
/* 513 */     if (this.colors[hashPos] == -1) {
/* 514 */       this.colors[hashPos] = node;
/* 515 */       this.counts[hashPos] = value;
/* 516 */       this.size++;
/*     */       return;
/*     */     } 
/* 518 */     if (this.colors[hashPos] == node) {
/* 519 */       this.counts[hashPos] = value;
/*     */       return;
/*     */     } 
/* 522 */     for (int next = hashPos + 1; next != hashPos; next++) {
/* 523 */       next %= this.hashsize;
/* 524 */       if (this.colors[next] == -1) {
/* 525 */         this.colors[next] = node;
/* 526 */         this.counts[next] = value;
/* 527 */         this.size++;
/*     */         return;
/*     */       } 
/* 529 */       if (this.colors[next] == node) {
/* 530 */         this.counts[next] = value;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   int get(int node) {
/* 539 */     int hashPos = hashCode(node);
/* 540 */     if (this.colors[hashPos] == node)
/* 541 */       return this.counts[hashPos]; 
/* 543 */     for (int next = hashPos + 1; next != hashPos; next++) {
/* 544 */       next %= this.hashsize;
/* 545 */       if (this.colors[next] == node)
/* 546 */         return this.counts[next]; 
/*     */     } 
/* 550 */     return -1;
/*     */   }
/*     */   
/*     */   int[] getCounts() {
/* 554 */     if (!this.packed)
/* 555 */       pack(); 
/* 556 */     return this.newCounts;
/*     */   }
/*     */   
/*     */   int[] getColors() {
/* 560 */     if (!this.packed)
/* 561 */       pack(); 
/* 562 */     return this.newColors;
/*     */   }
/*     */   
/*     */   void pack() {
/* 566 */     this.newColors = new int[this.capacity];
/* 567 */     this.newCounts = new int[this.capacity];
/* 569 */     for (int i = 0, j = 0; i < this.hashsize; i++) {
/* 570 */       if (this.colors[i] != -1) {
/* 571 */         this.newColors[j] = this.colors[i];
/* 572 */         this.newCounts[j] = this.counts[i];
/* 573 */         j++;
/*     */       } 
/*     */     } 
/* 577 */     this.packed = true;
/*     */   }
/*     */   
/*     */   int hashCode(int value) {
/* 581 */     return ((value >> 16) * 33023 + (value >> 8 & 0xFF) * 30013 + (value & 0xFF) * 27011) % this.hashsize;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\HistogramHash.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */