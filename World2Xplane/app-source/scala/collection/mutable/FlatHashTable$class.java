/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Arrays;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.util.Random;
/*     */ 
/*     */ public abstract class FlatHashTable$class {
/*     */   private static final boolean tableDebug(FlatHashTable $this) {
/*  27 */     return false;
/*     */   }
/*     */   
/*     */   public static void $init$(FlatHashTable $this) {
/*  29 */     $this._loadFactor_$eq(FlatHashTable$.MODULE$.defaultLoadFactor());
/*  33 */     $this.table_$eq(new Object[initialCapacity($this)]);
/*  37 */     $this.tableSize_$eq(0);
/*  41 */     $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), initialCapacity($this)));
/*  45 */     $this.sizemap_$eq((int[])null);
/*  47 */     $this.seedvalue_$eq($this.tableSizeSeed());
/*     */   }
/*     */   
/*     */   public static int capacity(FlatHashTable $this, int expectedSize) {
/*  51 */     return (expectedSize == 0) ? 1 : HashTable$.MODULE$.powerOfTwo(expectedSize);
/*     */   }
/*     */   
/*     */   public static int initialSize(FlatHashTable $this) {
/*  55 */     return 32;
/*     */   }
/*     */   
/*     */   private static int initialCapacity(FlatHashTable $this) {
/*  57 */     return $this.capacity($this.initialSize());
/*     */   }
/*     */   
/*     */   public static int randomSeed(FlatHashTable $this) {
/*  59 */     return ((Random)FlatHashTable$.MODULE$.seedGenerator().get()).nextInt();
/*     */   }
/*     */   
/*     */   public static int tableSizeSeed(FlatHashTable $this) {
/*  61 */     return Integer.bitCount(($this.table()).length - 1);
/*     */   }
/*     */   
/*     */   public static void init(FlatHashTable<Object> $this, ObjectInputStream in, Function1 f) {
/*  71 */     in.defaultReadObject();
/*  73 */     $this._loadFactor_$eq(in.readInt());
/*  74 */     Predef$.MODULE$.assert(($this._loadFactor() > 0));
/*  76 */     int size = in.readInt();
/*  77 */     $this.tableSize_$eq(0);
/*  78 */     Predef$.MODULE$.assert((size >= 0));
/*  80 */     $this.table_$eq(new Object[$this.capacity(FlatHashTable$.MODULE$.sizeForThreshold(size, $this._loadFactor()))]);
/*  81 */     $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), Predef$.MODULE$.refArrayOps($this.table()).size()));
/*  83 */     $this.seedvalue_$eq(in.readInt());
/*  85 */     boolean smDefined = in.readBoolean();
/*  86 */     if (smDefined) {
/*  86 */       $this.sizeMapInit(($this.table()).length);
/*     */     } else {
/*  86 */       $this.sizemap_$eq((int[])null);
/*     */     } 
/*  88 */     int index = 0;
/*  89 */     while (index < size) {
/*  90 */       Object elem = in.readObject();
/*  91 */       f.apply(elem);
/*  92 */       $this.addEntry(elem);
/*  93 */       index++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void serializeTo(FlatHashTable<A> $this, ObjectOutputStream out) {
/* 103 */     out.defaultWriteObject();
/* 104 */     out.writeInt($this._loadFactor());
/* 105 */     out.writeInt($this.tableSize());
/* 106 */     out.writeInt($this.seedvalue());
/* 107 */     out.writeBoolean($this.isSizeMapDefined());
/* 108 */     $this.iterator().foreach((Function1)new FlatHashTable$$anonfun$serializeTo$1($this, (FlatHashTable<A>)out));
/*     */   }
/*     */   
/*     */   public static Option findEntry(FlatHashTable $this, Object elem) {
/* 113 */     Object entry = findEntryImpl($this, elem);
/* 114 */     return (entry == null) ? (Option)None$.MODULE$ : (Option)new Some(entry);
/*     */   }
/*     */   
/*     */   public static boolean containsEntry(FlatHashTable $this, Object elem) {
/* 119 */     return !(findEntryImpl($this, elem) == null);
/*     */   }
/*     */   
/*     */   private static Object findEntryImpl(FlatHashTable<Object> $this, Object elem) {
/* 123 */     int h = $this.index($this.elemHashCode(elem));
/* 124 */     Object entry = $this.table()[h];
/*     */     while (true) {
/* 125 */       if (entry != null)
/* 125 */         if (!((entry == elem) ? 1 : ((entry == null) ? 0 : ((entry instanceof Number) ? BoxesRunTime.equalsNumObject((Number)entry, elem) : ((entry instanceof Character) ? BoxesRunTime.equalsCharObject((Character)entry, elem) : entry.equals(elem)))))) {
/* 126 */           h = (h + 1) % ($this.table()).length;
/* 127 */           entry = $this.table()[h];
/*     */           continue;
/*     */         }  
/* 129 */       return entry;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean addEntry(FlatHashTable<Object> $this, Object elem) {
/* 136 */     int h = $this.index($this.elemHashCode(elem));
/* 137 */     Object entry = $this.table()[h];
/*     */     while (true) {
/* 138 */       if (entry == null) {
/* 144 */         $this.table()[h] = elem;
/* 145 */         $this.tableSize_$eq($this.tableSize() + 1);
/* 146 */         $this.nnSizeMapAdd(h);
/* 147 */         if ($this.tableSize() >= $this.threshold())
/* 147 */           growTable($this); 
/* 148 */         return true;
/*     */       } 
/*     */       if ((entry == elem) ? true : ((entry == null) ? false : ((entry instanceof Number) ? BoxesRunTime.equalsNumObject((Number)entry, elem) : ((entry instanceof Character) ? BoxesRunTime.equalsCharObject((Character)entry, elem) : entry.equals(elem)))))
/*     */         return false; 
/*     */       h = (h + 1) % ($this.table()).length;
/*     */       entry = $this.table()[h];
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Option removeEntry(FlatHashTable<Object> $this, Object elem) {
/* 153 */     if (tableDebug($this))
/* 153 */       checkConsistent($this); 
/* 159 */     int h = $this.index($this.elemHashCode(elem));
/* 160 */     Object entry = $this.table()[h];
/*     */     while (true) {
/* 161 */       if (entry == null)
/* 184 */         return (Option)None$.MODULE$; 
/*     */       if ((entry == elem) ? true : ((entry == null) ? false : ((entry instanceof Number) ? BoxesRunTime.equalsNumObject((Number)entry, elem) : ((entry instanceof Character) ? BoxesRunTime.equalsCharObject((Character)entry, elem) : entry.equals(elem))))) {
/*     */         int h0 = h;
/*     */         int h1 = (h + 1) % ($this.table()).length;
/*     */         while (true) {
/*     */           if ($this.table()[h1] == null) {
/*     */             $this.table()[h0] = null;
/*     */             $this.tableSize_$eq($this.tableSize() - 1);
/*     */             $this.nnSizeMapRemove(h0);
/*     */             if (tableDebug($this))
/*     */               checkConsistent($this); 
/*     */             return (Option)new Some(entry);
/*     */           } 
/*     */           int h2 = $this.index($this.elemHashCode($this.table()[h1]));
/*     */           if (h2 != h1 && precedes$1($this, h2, h0)) {
/*     */             $this.table()[h0] = $this.table()[h1];
/*     */             h0 = h1;
/*     */           } 
/*     */           h1 = (h1 + 1) % ($this.table()).length;
/*     */         } 
/*     */         break;
/*     */       } 
/*     */       h = (h + 1) % ($this.table()).length;
/*     */       entry = $this.table()[h];
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final boolean precedes$1(FlatHashTable $this, int i, int j) {
/*     */     int d = ($this.table()).length >> 1;
/*     */     return (i <= j) ? ((j - i < d)) : ((i - j > d));
/*     */   }
/*     */   
/*     */   public static Iterator iterator(FlatHashTable<A> $this) {
/* 187 */     return (Iterator)new FlatHashTable$$anon$1($this);
/*     */   }
/*     */   
/*     */   private static void growTable(FlatHashTable<Object> $this) {
/* 199 */     Object[] oldtable = $this.table();
/* 200 */     $this.table_$eq(new Object[($this.table()).length * 2]);
/* 201 */     $this.tableSize_$eq(0);
/* 202 */     $this.nnSizeMapReset(($this.table()).length);
/* 203 */     $this.seedvalue_$eq($this.tableSizeSeed());
/* 204 */     $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), ($this.table()).length));
/* 205 */     int i = 0;
/* 206 */     while (i < oldtable.length) {
/* 207 */       Object entry = oldtable[i];
/* 208 */       if (entry != null)
/* 208 */         $this.addEntry(entry); 
/* 209 */       i++;
/*     */     } 
/* 211 */     if (tableDebug($this))
/* 211 */       checkConsistent($this); 
/*     */   }
/*     */   
/*     */   private static void checkConsistent(FlatHashTable<A> $this) {
/* 215 */     Predef$ predef$ = Predef$.MODULE$;
/* 215 */     int i = ($this.table()).length;
/* 215 */     Range$ range$ = Range$.MODULE$;
/* 215 */     FlatHashTable$$anonfun$checkConsistent$1 flatHashTable$$anonfun$checkConsistent$1 = new FlatHashTable$$anonfun$checkConsistent$1($this);
/*     */     Range range;
/* 215 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)flatHashTable$$anonfun$checkConsistent$1))
/* 215 */       for (int i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 215 */         if ($this.table()[i1] != null && !$this.containsEntry((A)$this.table()[i1])) {
/* 215 */           FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 = new FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1(flatHashTable$$anonfun$checkConsistent$1, i1);
/* 215 */           Predef$ predef$1 = Predef$.MODULE$;
/* 215 */           if (!false) {
/* 215 */             Object[] arrayOfObject = flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1.$outer.$outer.table();
/* 215 */             Predef$ predef$2 = Predef$.MODULE$;
/* 215 */             throw new AssertionError((new StringBuilder()).append("assertion failed: ").append((new StringBuilder()).append(i1).append(" ").append(flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1.$outer.$outer.table()[i1]).append(" ").append(TraversableOnce.class.mkString(new ArrayOps.ofRef(arrayOfObject))).toString()).toString());
/*     */           } 
/*     */         } 
/* 215 */         i1 += step1;
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void nnSizeMapAdd(FlatHashTable $this, int h) {
/* 235 */     if ($this.sizemap() != null) {
/* 236 */       int p = h >> $this.sizeMapBucketBitSize();
/* 237 */       int[] arrayOfInt = $this.sizemap();
/* 237 */       arrayOfInt[p] = arrayOfInt[p] + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void nnSizeMapRemove(FlatHashTable $this, int h) {
/* 240 */     if ($this.sizemap() != null) {
/* 241 */       int arrayOfInt[] = $this.sizemap(), i = h >> $this.sizeMapBucketBitSize();
/* 241 */       arrayOfInt[i] = arrayOfInt[i] - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void nnSizeMapReset(FlatHashTable $this, int tableLength) {
/* 244 */     if ($this.sizemap() != null) {
/* 245 */       int nsize = $this.calcSizeMapSize(tableLength);
/* 246 */       if (($this.sizemap()).length != nsize) {
/* 246 */         $this.sizemap_$eq(new int[nsize]);
/*     */       } else {
/* 247 */         Arrays.fill($this.sizemap(), 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final int totalSizeMapBuckets(FlatHashTable $this) {
/* 250 */     return (($this.table()).length - 1) / $this.sizeMapBucketSize() + 1;
/*     */   }
/*     */   
/*     */   public static int calcSizeMapSize(FlatHashTable $this, int tableLength) {
/* 252 */     return (tableLength >> $this.sizeMapBucketBitSize()) + 1;
/*     */   }
/*     */   
/*     */   public static void sizeMapInit(FlatHashTable $this, int tableLength) {
/* 256 */     $this.sizemap_$eq(new int[$this.calcSizeMapSize(tableLength)]);
/*     */   }
/*     */   
/*     */   public static void sizeMapInitAndRebuild(FlatHashTable $this) {
/* 262 */     $this.sizeMapInit(($this.table()).length);
/* 265 */     int totalbuckets = $this.totalSizeMapBuckets();
/* 266 */     int bucketidx = 0;
/* 267 */     int tableidx = 0;
/* 268 */     Object[] tbl = $this.table();
/* 269 */     int i = $this.sizeMapBucketSize();
/* 269 */     Predef$ predef$ = Predef$.MODULE$;
/* 269 */     int tableuntil = RichInt$.MODULE$.min$extension(i, tbl.length);
/* 270 */     while (bucketidx < totalbuckets) {
/* 271 */       int currbucketsz = 0;
/* 272 */       while (tableidx < tableuntil) {
/* 273 */         if (tbl[tableidx] != null)
/* 273 */           currbucketsz++; 
/* 274 */         tableidx++;
/*     */       } 
/* 276 */       $this.sizemap()[bucketidx] = currbucketsz;
/* 277 */       tableuntil += $this.sizeMapBucketSize();
/* 278 */       bucketidx++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void printSizeMap(FlatHashTable $this) {
/* 283 */     Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps($this.sizemap()).mkString("szmap: [", ", ", "]"));
/*     */   }
/*     */   
/*     */   public static void printContents(FlatHashTable $this) {
/* 287 */     Predef$.MODULE$.println(Predef$.MODULE$.refArrayOps($this.table()).mkString("[", ", ", "]"));
/*     */   }
/*     */   
/*     */   public static void sizeMapDisable(FlatHashTable $this) {
/* 290 */     $this.sizemap_$eq((int[])null);
/*     */   }
/*     */   
/*     */   public static boolean isSizeMapDefined(FlatHashTable $this) {
/* 292 */     return ($this.sizemap() != null);
/*     */   }
/*     */   
/*     */   public static boolean alwaysInitSizeMap(FlatHashTable $this) {
/* 294 */     return false;
/*     */   }
/*     */   
/*     */   public static final int index(FlatHashTable $this, int hcode) {
/* 303 */     int improved = $this.improve(hcode, $this.seedvalue());
/* 304 */     int ones = ($this.table()).length - 1;
/* 305 */     return improved >>> 32 - Integer.bitCount(ones) & ones;
/*     */   }
/*     */   
/*     */   public static void clearTable(FlatHashTable $this) {
/* 320 */     int i = ($this.table()).length - 1;
/* 321 */     while (i >= 0) {
/* 321 */       $this.table()[i] = null;
/* 321 */       i--;
/*     */     } 
/* 322 */     $this.tableSize_$eq(0);
/* 323 */     $this.nnSizeMapReset(($this.table()).length);
/*     */   }
/*     */   
/*     */   public static FlatHashTable.Contents hashTableContents(FlatHashTable $this) {
/* 326 */     return new FlatHashTable.Contents(
/* 327 */         $this._loadFactor(), 
/* 328 */         $this.table(), 
/* 329 */         $this.tableSize(), 
/* 330 */         $this.threshold(), 
/* 331 */         $this.seedvalue(), 
/* 332 */         $this.sizemap());
/*     */   }
/*     */   
/*     */   public static void initWithContents(FlatHashTable $this, FlatHashTable.Contents c) {
/* 336 */     if (c != null) {
/* 337 */       $this._loadFactor_$eq(c.loadFactor());
/* 338 */       $this.table_$eq(c.table());
/* 339 */       $this.tableSize_$eq(c.tableSize());
/* 340 */       $this.threshold_$eq(c.threshold());
/* 341 */       $this.seedvalue_$eq(c.seedvalue());
/* 342 */       $this.sizemap_$eq(c.sizemap());
/*     */     } 
/* 344 */     if ($this.alwaysInitSizeMap() && $this.sizemap() == null)
/* 344 */       $this.sizeMapInitAndRebuild(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\FlatHashTable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */