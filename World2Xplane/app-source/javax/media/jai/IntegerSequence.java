/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IntegerSequence {
/*     */   private int min;
/*     */   
/*     */   private int max;
/*     */   
/*     */   private static final int DEFAULT_CAPACITY = 16;
/*     */   
/*  44 */   private int[] iArray = null;
/*     */   
/*  47 */   private int capacity = 0;
/*     */   
/*  50 */   private int numElts = 0;
/*     */   
/*     */   private boolean isSorted = false;
/*     */   
/*  56 */   private int currentIndex = -1;
/*     */   
/*     */   public IntegerSequence(int min, int max) {
/*  64 */     if (min > max)
/*  65 */       throw new IllegalArgumentException(JaiI18N.getString("IntegerSequence1")); 
/*  66 */     this.min = min;
/*  67 */     this.max = max;
/*  69 */     this.capacity = 16;
/*  70 */     this.iArray = new int[this.capacity];
/*  71 */     this.numElts = 0;
/*  72 */     this.isSorted = true;
/*     */   }
/*     */   
/*     */   public IntegerSequence() {
/*  77 */     this(-2147483648, 2147483647);
/*     */   }
/*     */   
/*     */   public void insert(int element) {
/*  90 */     if (element < this.min || element > this.max)
/*     */       return; 
/*  94 */     if (this.numElts >= this.capacity) {
/*  95 */       int newCapacity = 2 * this.capacity;
/*  96 */       int[] newArray = new int[newCapacity];
/*  97 */       System.arraycopy(this.iArray, 0, newArray, 0, this.capacity);
/*  99 */       this.capacity = newCapacity;
/* 100 */       this.iArray = newArray;
/*     */     } 
/* 102 */     this.isSorted = false;
/* 103 */     this.iArray[this.numElts++] = element;
/*     */   }
/*     */   
/*     */   public void startEnumeration() {
/* 108 */     if (!this.isSorted) {
/* 110 */       Arrays.sort(this.iArray, 0, this.numElts);
/* 113 */       int readPos = 1;
/* 114 */       int writePos = 1;
/* 115 */       int prevElt = this.iArray[0];
/* 121 */       for (readPos = 1; readPos < this.numElts; readPos++) {
/* 122 */         int currElt = this.iArray[readPos];
/* 123 */         if (currElt != prevElt) {
/* 124 */           this.iArray[writePos++] = currElt;
/* 125 */           prevElt = currElt;
/*     */         } 
/*     */       } 
/* 129 */       this.numElts = writePos;
/* 130 */       this.isSorted = true;
/*     */     } 
/* 133 */     this.currentIndex = 0;
/*     */   }
/*     */   
/*     */   public boolean hasMoreElements() {
/* 138 */     return (this.currentIndex < this.numElts);
/*     */   }
/*     */   
/*     */   public int nextElement() {
/* 150 */     if (this.currentIndex < this.numElts)
/* 151 */       return this.iArray[this.currentIndex++]; 
/* 153 */     throw new NoSuchElementException(JaiI18N.getString("IntegerSequence0"));
/*     */   }
/*     */   
/*     */   public int getNumElements() {
/* 161 */     return this.numElts;
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     String s;
/* 169 */     if (this.numElts == 0) {
/* 170 */       s = "[<empty>]";
/*     */     } else {
/* 172 */       s = "[";
/* 174 */       startEnumeration();
/* 175 */       for (int i = 0; i < this.numElts - 1; i++) {
/* 176 */         s = s + this.iArray[i];
/* 177 */         s = s + ", ";
/*     */       } 
/* 180 */       s = s + this.iArray[this.numElts - 1];
/* 181 */       s = s + "]";
/*     */     } 
/* 184 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\IntegerSequence.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */