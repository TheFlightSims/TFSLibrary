/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Arrays;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class HashTable$class {
/*     */   public static void $init$(HashTable $this) {
/*  40 */     $this._loadFactor_$eq(HashTable$.MODULE$.defaultLoadFactor());
/*  44 */     $this.table_$eq(new HashEntry[initialCapacity($this)]);
/*  48 */     $this.tableSize_$eq(0);
/*  52 */     $this.threshold_$eq(initialThreshold($this, $this._loadFactor()));
/*  56 */     $this.sizemap_$eq((int[])null);
/*  58 */     $this.seedvalue_$eq($this.tableSizeSeed());
/*     */   }
/*     */   
/*     */   public static int tableSizeSeed(HashTable $this) {
/*  60 */     return Integer.bitCount(($this.table()).length - 1);
/*     */   }
/*     */   
/*     */   public static int initialSize(HashTable $this) {
/*  64 */     return 16;
/*     */   }
/*     */   
/*     */   private static int initialThreshold(HashTable $this, int _loadFactor) {
/*  68 */     return HashTable$.MODULE$.newThreshold(_loadFactor, initialCapacity($this));
/*     */   }
/*     */   
/*     */   private static int initialCapacity(HashTable $this) {
/*  70 */     return HashTable$.MODULE$.capacity($this.initialSize());
/*     */   }
/*     */   
/*     */   public static int scala$collection$mutable$HashTable$$lastPopulatedIndex(HashTable $this) {
/*  73 */     int idx = ($this.table()).length - 1;
/*  74 */     while ($this.table()[idx] == null && idx > 0)
/*  75 */       idx--; 
/*  77 */     return idx;
/*     */   }
/*     */   
/*     */   public static void init(HashTable $this, ObjectInputStream in, Function0 readEntry) {
/*  85 */     in.defaultReadObject();
/*  87 */     $this._loadFactor_$eq(in.readInt());
/*  88 */     Predef$.MODULE$.assert(($this._loadFactor() > 0));
/*  90 */     int size = in.readInt();
/*  91 */     $this.tableSize_$eq(0);
/*  92 */     Predef$.MODULE$.assert((size >= 0));
/*  94 */     $this.seedvalue_$eq(in.readInt());
/*  96 */     boolean smDefined = in.readBoolean();
/*  98 */     $this.table_$eq(new HashEntry[HashTable$.MODULE$.capacity(HashTable$.MODULE$.sizeForThreshold($this._loadFactor(), size))]);
/*  99 */     $this.threshold_$eq(HashTable$.MODULE$.newThreshold($this._loadFactor(), Predef$.MODULE$.refArrayOps((Object[])$this.table()).size()));
/* 101 */     if (smDefined) {
/* 101 */       $this.sizeMapInit(($this.table()).length);
/*     */     } else {
/* 101 */       $this.sizemap_$eq((int[])null);
/*     */     } 
/* 103 */     int index = 0;
/* 104 */     while (index < size) {
/* 105 */       $this.addEntry(readEntry.apply());
/* 106 */       index++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void serializeTo(HashTable $this, ObjectOutputStream out, Function1 writeEntry) {
/* 118 */     out.defaultWriteObject();
/* 119 */     out.writeInt($this._loadFactor());
/* 120 */     out.writeInt($this.tableSize());
/* 121 */     out.writeInt($this.seedvalue());
/* 122 */     out.writeBoolean($this.isSizeMapDefined());
/* 124 */     $this.foreachEntry(writeEntry);
/*     */   }
/*     */   
/*     */   public static HashEntry findEntry(HashTable $this, Object key) {
/* 130 */     return scala$collection$mutable$HashTable$$findEntry0($this, key, $this.index($this.elemHashCode(key)));
/*     */   }
/*     */   
/*     */   public static HashEntry scala$collection$mutable$HashTable$$findEntry0(HashTable $this, Object key, int h) {
/* 133 */     HashEntry<A, Entry> e = $this.table()[h];
/*     */     while (true) {
/* 134 */       if (e == null || $this.elemEquals(e.key(), key))
/* 135 */         return e; 
/*     */       e = (HashEntry<A, Entry>)e.next();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addEntry(HashTable $this, HashEntry e) {
/* 142 */     scala$collection$mutable$HashTable$$addEntry0($this, e, $this.index($this.elemHashCode(e.key())));
/*     */   }
/*     */   
/*     */   public static void scala$collection$mutable$HashTable$$addEntry0(HashTable $this, HashEntry<A, Entry> e, int h) {
/* 146 */     e.next_$eq($this.table()[h]);
/* 147 */     $this.table()[h] = e;
/* 148 */     $this.tableSize_$eq($this.tableSize() + 1);
/* 149 */     $this.nnSizeMapAdd(h);
/* 150 */     if ($this.tableSize() > $this.threshold())
/* 151 */       resize($this, 2 * ($this.table()).length); 
/*     */   }
/*     */   
/*     */   public static HashEntry findOrAddEntry(HashTable<Object, HashEntry> $this, Object key, Object value) {
/* 161 */     int h = $this.index($this.elemHashCode(key));
/* 162 */     HashEntry e = scala$collection$mutable$HashTable$$findEntry0($this, key, h);
/* 163 */     scala$collection$mutable$HashTable$$addEntry0($this, $this.createNewEntry(key, value), h);
/* 163 */     return (e != null) ? e : null;
/*     */   }
/*     */   
/*     */   public static HashEntry removeEntry(HashTable $this, Object key) {
/* 175 */     int h = $this.index($this.elemHashCode(key));
/* 176 */     HashEntry<A, Entry> e = $this.table()[h];
/* 177 */     if (e != null) {
/* 178 */       if ($this.elemEquals(e.key(), key)) {
/* 179 */         $this.table()[h] = (HashEntry<A, Entry>)e.next();
/* 180 */         $this.tableSize_$eq($this.tableSize() - 1);
/* 181 */         $this.nnSizeMapRemove(h);
/* 182 */         return e;
/*     */       } 
/* 184 */       HashEntry<A, Entry> e1 = (HashEntry)e.next();
/*     */       while (true) {
/* 185 */         if (e1 == null || $this.elemEquals(e1.key(), key)) {
/* 189 */           if (e1 != null) {
/* 190 */             e.next_$eq((Entry)e1.next());
/* 191 */             $this.tableSize_$eq($this.tableSize() - 1);
/* 192 */             $this.nnSizeMapRemove(h);
/* 193 */             return e1;
/*     */           } 
/*     */           return null;
/*     */         } 
/*     */         e = e1;
/*     */         e1 = (HashEntry<A, Entry>)e1.next();
/*     */       } 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static Iterator entriesIterator(HashTable<A, Entry> $this) {
/* 202 */     return (Iterator)new HashTable$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static void foreachEntry(HashTable $this, Function1 f) {
/* 221 */     HashEntry[] iterTable = $this.table();
/* 222 */     int idx = scala$collection$mutable$HashTable$$lastPopulatedIndex($this);
/* 223 */     HashEntry es = iterTable[idx];
/*     */     while (true) {
/* 225 */       if (es == null)
/*     */         return; 
/* 226 */       f.apply(es);
/* 227 */       es = (HashEntry)es.next();
/* 229 */       while (es == null && idx > 0) {
/* 230 */         idx--;
/* 231 */         es = iterTable[idx];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void clearTable(HashTable $this) {
/* 239 */     int i = ($this.table()).length - 1;
/* 240 */     while (i >= 0) {
/* 240 */       $this.table()[i] = null;
/* 240 */       i--;
/*     */     } 
/* 241 */     $this.tableSize_$eq(0);
/* 242 */     $this.nnSizeMapReset(0);
/*     */   }
/*     */   
/*     */   private static void resize(HashTable $this, int newSize) {
/* 246 */     HashEntry[] oldTable = $this.table();
/* 247 */     $this.table_$eq(new HashEntry[newSize]);
/* 248 */     $this.nnSizeMapReset(($this.table()).length);
/* 249 */     int i = oldTable.length - 1;
/* 250 */     label13: while (i >= 0) {
/* 251 */       HashEntry<A, Entry> e = oldTable[i];
/*     */       while (true) {
/* 252 */         if (e == null) {
/* 260 */           i--;
/*     */           continue label13;
/*     */         } 
/*     */         int h = $this.index($this.elemHashCode(e.key()));
/*     */         HashEntry<A, Entry> e1 = (HashEntry)e.next();
/*     */         e.next_$eq($this.table()[h]);
/*     */         $this.table()[h] = e;
/*     */         e = e1;
/*     */         $this.nnSizeMapAdd(h);
/*     */       } 
/*     */     } 
/* 262 */     $this.threshold_$eq(HashTable$.MODULE$.newThreshold($this._loadFactor(), newSize));
/*     */   }
/*     */   
/*     */   public static void nnSizeMapAdd(HashTable $this, int h) {
/* 284 */     if ($this.sizemap() != null) {
/* 285 */       int arrayOfInt[] = $this.sizemap(), i = h >> $this.sizeMapBucketBitSize();
/* 285 */       arrayOfInt[i] = arrayOfInt[i] + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void nnSizeMapRemove(HashTable $this, int h) {
/* 288 */     if ($this.sizemap() != null) {
/* 289 */       int arrayOfInt[] = $this.sizemap(), i = h >> $this.sizeMapBucketBitSize();
/* 289 */       arrayOfInt[i] = arrayOfInt[i] - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void nnSizeMapReset(HashTable $this, int tableLength) {
/* 292 */     if ($this.sizemap() != null) {
/* 293 */       int nsize = $this.calcSizeMapSize(tableLength);
/* 294 */       if (($this.sizemap()).length != nsize) {
/* 294 */         $this.sizemap_$eq(new int[nsize]);
/*     */       } else {
/* 295 */         Arrays.fill($this.sizemap(), 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final int totalSizeMapBuckets(HashTable $this) {
/* 298 */     return ($this.sizeMapBucketSize() < ($this.table()).length) ? 1 : (($this.table()).length / $this.sizeMapBucketSize());
/*     */   }
/*     */   
/*     */   public static int calcSizeMapSize(HashTable $this, int tableLength) {
/* 300 */     return (tableLength >> $this.sizeMapBucketBitSize()) + 1;
/*     */   }
/*     */   
/*     */   public static void sizeMapInit(HashTable $this, int tableLength) {
/* 304 */     $this.sizemap_$eq(new int[$this.calcSizeMapSize(tableLength)]);
/*     */   }
/*     */   
/*     */   public static void sizeMapInitAndRebuild(HashTable $this) {
/*     */     int i;
/* 309 */     $this.sizeMapInit(($this.table()).length);
/* 312 */     int tableidx = 0;
/* 313 */     int bucketidx = 0;
/* 314 */     HashEntry[] tbl = $this.table();
/* 315 */     if (tbl.length < $this.sizeMapBucketSize()) {
/* 315 */       i = tbl.length;
/*     */     } else {
/* 315 */       i = $this.sizeMapBucketSize();
/*     */     } 
/* 317 */     int totalbuckets = $this.totalSizeMapBuckets();
/* 318 */     while (bucketidx < totalbuckets) {
/* 319 */       int currbucketsize = 0;
/* 320 */       while (tableidx < i) {
/* 321 */         HashEntry e = tbl[tableidx];
/* 322 */         while (e != null) {
/* 323 */           currbucketsize++;
/* 324 */           e = (HashEntry)e.next();
/*     */         } 
/* 326 */         tableidx++;
/*     */       } 
/* 328 */       $this.sizemap()[bucketidx] = currbucketsize;
/* 329 */       i += $this.sizeMapBucketSize();
/* 330 */       bucketidx++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void printSizeMap(HashTable $this) {
/* 335 */     Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps($this.sizemap()).toList());
/*     */   }
/*     */   
/*     */   public static void sizeMapDisable(HashTable $this) {
/* 338 */     $this.sizemap_$eq((int[])null);
/*     */   }
/*     */   
/*     */   public static boolean isSizeMapDefined(HashTable $this) {
/* 340 */     return ($this.sizemap() != null);
/*     */   }
/*     */   
/*     */   public static boolean alwaysInitSizeMap(HashTable $this) {
/* 343 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean elemEquals(HashTable $this, Object key1, Object key2) {
/* 347 */     return ((key1 == key2) ? true : ((key1 == null) ? false : ((key1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key1, key2) : ((key1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key1, key2) : key1.equals(key2)))));
/*     */   }
/*     */   
/*     */   public static final int index(HashTable $this, int hcode) {
/* 353 */     int ones = ($this.table()).length - 1;
/* 354 */     int improved = $this.improve(hcode, $this.seedvalue());
/* 355 */     int shifted = improved >> 32 - Integer.bitCount(ones) & ones;
/* 356 */     return shifted;
/*     */   }
/*     */   
/*     */   public static void initWithContents(HashTable $this, HashTable.Contents c) {
/* 360 */     if (c != null) {
/* 361 */       $this._loadFactor_$eq(c.loadFactor());
/* 362 */       $this.table_$eq(c.table());
/* 363 */       $this.tableSize_$eq(c.tableSize());
/* 364 */       $this.threshold_$eq(c.threshold());
/* 365 */       $this.seedvalue_$eq(c.seedvalue());
/* 366 */       $this.sizemap_$eq(c.sizemap());
/*     */     } 
/* 368 */     if ($this.alwaysInitSizeMap() && $this.sizemap() == null)
/* 368 */       $this.sizeMapInitAndRebuild(); 
/*     */   }
/*     */   
/*     */   public static HashTable.Contents hashTableContents(HashTable $this) {
/* 371 */     return new HashTable.Contents<Object, HashEntry>(
/* 372 */         $this._loadFactor(), 
/* 373 */         (HashEntry<?, HashEntry>[])$this.table(), 
/* 374 */         $this.tableSize(), 
/* 375 */         $this.threshold(), 
/* 376 */         $this.seedvalue(), 
/* 377 */         $this.sizemap());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashTable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */