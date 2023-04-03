/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class LargePrefixedNameSet extends PrefixedNameSet {
/*     */   static final int MIN_HASH_AREA = 8;
/*     */   
/*     */   final boolean mNsAware;
/*     */   
/*     */   final PrefixedName[] mNames;
/*     */   
/*     */   final Bucket[] mBuckets;
/*     */   
/*     */   public LargePrefixedNameSet(boolean nsAware, PrefixedName[] names) {
/*  53 */     this.mNsAware = nsAware;
/*  54 */     int len = names.length;
/*  57 */     int minSize = len + (len + 7 >> 3);
/*  59 */     int tableSize = 8;
/*  61 */     while (tableSize < minSize)
/*  62 */       tableSize += tableSize; 
/*  65 */     this.mNames = new PrefixedName[tableSize];
/*  68 */     Bucket[] buckets = null;
/*  69 */     int mask = tableSize - 1;
/*  71 */     for (int i = 0; i < len; i++) {
/*  72 */       PrefixedName nk = names[i];
/*  73 */       int ix = nk.hashCode() & mask;
/*  74 */       if (this.mNames[ix] == null) {
/*  75 */         this.mNames[ix] = nk;
/*     */       } else {
/*     */         Bucket old;
/*  77 */         ix >>= 2;
/*  80 */         if (buckets == null) {
/*  81 */           buckets = new Bucket[tableSize >> 2];
/*  82 */           old = null;
/*     */         } else {
/*  84 */           old = buckets[ix];
/*     */         } 
/*  86 */         buckets[ix] = new Bucket(nk, old);
/*     */       } 
/*     */     } 
/*  90 */     this.mBuckets = buckets;
/*     */   }
/*     */   
/*     */   public boolean hasMultiple() {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(PrefixedName name) {
/* 100 */     PrefixedName[] hashArea = this.mNames;
/* 101 */     int index = name.hashCode() & hashArea.length - 1;
/* 102 */     PrefixedName res = hashArea[index];
/* 104 */     if (res != null && res.equals(name))
/* 105 */       return true; 
/* 108 */     Bucket[] buckets = this.mBuckets;
/* 109 */     if (buckets != null)
/* 110 */       for (Bucket bucket = buckets[index >> 2]; bucket != null; 
/* 111 */         bucket = bucket.getNext()) {
/* 112 */         res = bucket.getName();
/* 113 */         if (res.equals(name))
/* 114 */           return true; 
/*     */       }  
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public void appendNames(StringBuffer sb, String sep) {
/* 128 */     TreeSet ts = new TreeSet();
/*     */     int i;
/* 129 */     for (i = 0; i < this.mNames.length; i++) {
/* 130 */       PrefixedName name = this.mNames[i];
/* 131 */       if (name != null)
/* 132 */         ts.add(name); 
/*     */     } 
/* 137 */     if (this.mBuckets != null)
/* 138 */       for (i = 0; i < this.mNames.length >> 2; i++) {
/* 139 */         Bucket b = this.mBuckets[i];
/* 140 */         while (b != null) {
/* 141 */           ts.add(b.getName());
/* 142 */           b = b.getNext();
/*     */         } 
/*     */       }  
/* 148 */     Iterator it = ts.iterator();
/* 149 */     boolean first = true;
/* 150 */     while (it.hasNext()) {
/* 151 */       if (first) {
/* 152 */         first = false;
/*     */       } else {
/* 154 */         sb.append(sep);
/*     */       } 
/* 156 */       sb.append(it.next().toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Bucket {
/*     */     final PrefixedName mName;
/*     */     
/*     */     final Bucket mNext;
/*     */     
/*     */     public Bucket(PrefixedName name, Bucket next) {
/* 173 */       this.mName = name;
/* 174 */       this.mNext = next;
/*     */     }
/*     */     
/*     */     public PrefixedName getName() {
/* 177 */       return this.mName;
/*     */     }
/*     */     
/*     */     public Bucket getNext() {
/* 178 */       return this.mNext;
/*     */     }
/*     */     
/*     */     public boolean contains(PrefixedName n) {
/* 181 */       return this.mName.equals(n);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\LargePrefixedNameSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */