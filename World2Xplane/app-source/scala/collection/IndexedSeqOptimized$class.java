/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class IndexedSeqOptimized$class {
/*     */   public static void $init$(IndexedSeqOptimized $this) {}
/*     */   
/*     */   public static boolean isEmpty(IndexedSeqOptimized $this) {
/*  27 */     return ($this.length() == 0);
/*     */   }
/*     */   
/*     */   public static void foreach(IndexedSeqOptimized $this, Function1 f) {
/*  31 */     int i = 0;
/*  32 */     int len = $this.length();
/*  33 */     while (i < len) {
/*  33 */       f.apply($this.apply(i));
/*  33 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean forall(IndexedSeqOptimized $this, Function1 p) {
/*  37 */     return ($this.prefixLength((Function1)new IndexedSeqOptimized$$anonfun$forall$1($this, (IndexedSeqOptimized<A, Repr>)p)) == $this.length());
/*     */   }
/*     */   
/*     */   public static boolean exists(IndexedSeqOptimized $this, Function1 p) {
/*  40 */     return ($this.prefixLength((Function1)new IndexedSeqOptimized$$anonfun$exists$1($this, (IndexedSeqOptimized<A, Repr>)p)) != $this.length());
/*     */   }
/*     */   
/*     */   public static Option find(IndexedSeqOptimized $this, Function1 p) {
/*  44 */     int i = $this.prefixLength((Function1)new IndexedSeqOptimized$$anonfun$1($this, (IndexedSeqOptimized<A, Repr>)p));
/*  45 */     return (i < $this.length()) ? (Option)new Some($this.apply(i)) : (Option)None$.MODULE$;
/*     */   }
/*     */   
/*     */   private static Object foldl(IndexedSeqOptimized $this, int start, int end, Object z, Function2 op) {
/*     */     while (true) {
/*  50 */       if (start == end)
/*  50 */         return z; 
/*  51 */       z = op.apply(z, $this.apply(start));
/*  51 */       end = end;
/*  51 */       start++;
/*  51 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object foldr(IndexedSeqOptimized $this, int start, int end, Object z, Function2 op) {
/*     */     while (true) {
/*  55 */       if (start == end)
/*  55 */         return z; 
/*  56 */       z = op.apply($this.apply(end - 1), z);
/*  56 */       end--;
/*  56 */       start = start;
/*  56 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object foldLeft(IndexedSeqOptimized $this, Object z, Function2 op) {
/*  60 */     return foldl($this, 0, $this.length(), z, op);
/*     */   }
/*     */   
/*     */   public static Object foldRight(IndexedSeqOptimized $this, Object z, Function2 op) {
/*  64 */     return foldr($this, 0, $this.length(), z, op);
/*     */   }
/*     */   
/*     */   public static Object reduceLeft(IndexedSeqOptimized $this, Function2 op) {
/*  68 */     return ($this.length() > 0) ? foldl($this, 1, $this.length(), $this.apply(0), op) : $this.scala$collection$IndexedSeqOptimized$$super$reduceLeft(op);
/*     */   }
/*     */   
/*     */   public static Object reduceRight(IndexedSeqOptimized $this, Function2 op) {
/*  72 */     return ($this.length() > 0) ? foldr($this, 0, $this.length() - 1, $this.apply($this.length() - 1), op) : $this.scala$collection$IndexedSeqOptimized$$super$reduceRight(op);
/*     */   }
/*     */   
/*     */   public static Object zip(IndexedSeqOptimized $this, GenIterable that, CanBuildFrom bf) {
/*     */     Object object;
/*  75 */     if (that instanceof IndexedSeq) {
/*  75 */       IndexedSeq indexedSeq = (IndexedSeq)that;
/*  77 */       Builder b = bf.apply($this.repr());
/*  78 */       int i = 0;
/*  79 */       int j = $this.length();
/*  79 */       Predef$ predef$ = Predef$.MODULE$;
/*  79 */       int len = RichInt$.MODULE$.min$extension(j, indexedSeq.length());
/*  80 */       b.sizeHint(len);
/*  81 */       while (i < len) {
/*  82 */         b.$plus$eq(new Tuple2($this.apply(i), indexedSeq.apply(i)));
/*  83 */         i++;
/*     */       } 
/*  85 */       object = b.result();
/*     */     } else {
/*  87 */       object = $this.scala$collection$IndexedSeqOptimized$$super$zip(that, bf);
/*     */     } 
/*     */     return object;
/*     */   }
/*     */   
/*     */   public static Object zipWithIndex(IndexedSeqOptimized $this, CanBuildFrom bf) {
/*  92 */     Builder b = bf.apply($this.repr());
/*  93 */     int len = $this.length();
/*  94 */     b.sizeHint(len);
/*  95 */     int i = 0;
/*  96 */     while (i < len) {
/*  97 */       b.$plus$eq(new Tuple2($this.apply(i), BoxesRunTime.boxToInteger(i)));
/*  98 */       i++;
/*     */     } 
/* 100 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object slice(IndexedSeqOptimized $this, int from, int until) {
/* 105 */     int lo = package$.MODULE$.max(from, 0);
/* 106 */     int hi = package$.MODULE$.min(package$.MODULE$.max(until, 0), $this.length());
/* 107 */     int elems = package$.MODULE$.max(hi - lo, 0);
/* 108 */     Builder b = $this.newBuilder();
/* 109 */     b.sizeHint(elems);
/* 111 */     int i = lo;
/* 112 */     while (i < hi) {
/* 113 */       b.$plus$eq($this.apply(i));
/* 114 */       i++;
/*     */     } 
/* 116 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object head(IndexedSeqOptimized $this) {
/* 120 */     return $this.isEmpty() ? $this.scala$collection$IndexedSeqOptimized$$super$head() : $this.apply(0);
/*     */   }
/*     */   
/*     */   public static Object tail(IndexedSeqOptimized $this) {
/* 123 */     return $this.isEmpty() ? $this.scala$collection$IndexedSeqOptimized$$super$tail() : $this.slice(1, $this.length());
/*     */   }
/*     */   
/*     */   public static Object last(IndexedSeqOptimized $this) {
/* 126 */     return ($this.length() > 0) ? $this.apply($this.length() - 1) : $this.scala$collection$IndexedSeqOptimized$$super$last();
/*     */   }
/*     */   
/*     */   public static Object init(IndexedSeqOptimized $this) {
/* 129 */     return ($this.length() > 0) ? $this.slice(0, $this.length() - 1) : $this.scala$collection$IndexedSeqOptimized$$super$init();
/*     */   }
/*     */   
/*     */   public static Object take(IndexedSeqOptimized $this, int n) {
/* 132 */     return $this.slice(0, n);
/*     */   }
/*     */   
/*     */   public static Object drop(IndexedSeqOptimized $this, int n) {
/* 135 */     return $this.slice(n, $this.length());
/*     */   }
/*     */   
/*     */   public static Object takeRight(IndexedSeqOptimized $this, int n) {
/* 138 */     return $this.slice($this.length() - n, $this.length());
/*     */   }
/*     */   
/*     */   public static Object dropRight(IndexedSeqOptimized $this, int n) {
/* 141 */     return $this.slice(0, $this.length() - n);
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt(IndexedSeqOptimized $this, int n) {
/* 144 */     return new Tuple2($this.take(n), $this.drop(n));
/*     */   }
/*     */   
/*     */   public static Object takeWhile(IndexedSeqOptimized $this, Function1 p) {
/* 147 */     return $this.take($this.prefixLength(p));
/*     */   }
/*     */   
/*     */   public static Object dropWhile(IndexedSeqOptimized $this, Function1 p) {
/* 150 */     return $this.drop($this.prefixLength(p));
/*     */   }
/*     */   
/*     */   public static Tuple2 span(IndexedSeqOptimized $this, Function1 p) {
/* 153 */     return $this.splitAt($this.prefixLength(p));
/*     */   }
/*     */   
/*     */   public static boolean sameElements(IndexedSeqOptimized $this, GenIterable that) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/collection/IndexedSeq
/*     */     //   4: ifeq -> 159
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/collection/IndexedSeq
/*     */     //   11: astore_2
/*     */     //   12: aload_0
/*     */     //   13: invokeinterface length : ()I
/*     */     //   18: istore #6
/*     */     //   20: iload #6
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface length : ()I
/*     */     //   28: if_icmpne -> 153
/*     */     //   31: iconst_0
/*     */     //   32: istore #5
/*     */     //   34: iload #5
/*     */     //   36: iload #6
/*     */     //   38: if_icmpge -> 134
/*     */     //   41: aload_0
/*     */     //   42: iload #5
/*     */     //   44: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   49: aload_2
/*     */     //   50: iload #5
/*     */     //   52: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   57: astore #4
/*     */     //   59: dup
/*     */     //   60: astore_3
/*     */     //   61: aload #4
/*     */     //   63: if_acmpne -> 70
/*     */     //   66: iconst_1
/*     */     //   67: goto -> 122
/*     */     //   70: aload_3
/*     */     //   71: ifnonnull -> 78
/*     */     //   74: iconst_0
/*     */     //   75: goto -> 122
/*     */     //   78: aload_3
/*     */     //   79: instanceof java/lang/Number
/*     */     //   82: ifeq -> 97
/*     */     //   85: aload_3
/*     */     //   86: checkcast java/lang/Number
/*     */     //   89: aload #4
/*     */     //   91: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   94: goto -> 122
/*     */     //   97: aload_3
/*     */     //   98: instanceof java/lang/Character
/*     */     //   101: ifeq -> 116
/*     */     //   104: aload_3
/*     */     //   105: checkcast java/lang/Character
/*     */     //   108: aload #4
/*     */     //   110: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   113: goto -> 122
/*     */     //   116: aload_3
/*     */     //   117: aload #4
/*     */     //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   122: ifeq -> 134
/*     */     //   125: iload #5
/*     */     //   127: iconst_1
/*     */     //   128: iadd
/*     */     //   129: istore #5
/*     */     //   131: goto -> 34
/*     */     //   134: iload #5
/*     */     //   136: iload #6
/*     */     //   138: if_icmpne -> 145
/*     */     //   141: iconst_1
/*     */     //   142: goto -> 146
/*     */     //   145: iconst_0
/*     */     //   146: ifeq -> 153
/*     */     //   149: iconst_1
/*     */     //   150: goto -> 154
/*     */     //   153: iconst_0
/*     */     //   154: istore #7
/*     */     //   156: goto -> 168
/*     */     //   159: aload_0
/*     */     //   160: aload_1
/*     */     //   161: invokeinterface scala$collection$IndexedSeqOptimized$$super$sameElements : (Lscala/collection/GenIterable;)Z
/*     */     //   166: istore #7
/*     */     //   168: iload #7
/*     */     //   170: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #157	-> 0
/*     */     //   #156	-> 0
/*     */     //   #158	-> 12
/*     */     //   #159	-> 20
/*     */     //   #160	-> 31
/*     */     //   #161	-> 34
/*     */     //   #162	-> 134
/*     */     //   #159	-> 149
/*     */     //   #157	-> 154
/*     */     //   #165	-> 159
/*     */     //   #156	-> 168
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	171	0	$this	Lscala/collection/IndexedSeqOptimized;
/*     */     //   0	171	1	that	Lscala/collection/GenIterable;
/*     */     //   20	151	6	len	I
/*     */     //   34	137	5	i	I
/*     */   }
/*     */   
/*     */   public static void copyToArray(IndexedSeqOptimized $this, Object xs, int start, int len) {
/* 170 */     int i = 0;
/* 171 */     int j = start;
/* 172 */     int k = $this.length();
/* 172 */     Predef$ predef$1 = Predef$.MODULE$;
/* 172 */     int m = RichInt$.MODULE$.min$extension(k, len);
/* 172 */     Predef$ predef$2 = Predef$.MODULE$;
/* 172 */     int end = RichInt$.MODULE$.min$extension(m, ScalaRunTime$.MODULE$.array_length(xs) - start);
/* 173 */     while (i < end) {
/* 174 */       ScalaRunTime$.MODULE$.array_update(xs, j, $this.apply(i));
/* 175 */       i++;
/* 176 */       j++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int lengthCompare(IndexedSeqOptimized $this, int len) {
/* 183 */     return $this.length() - len;
/*     */   }
/*     */   
/*     */   public static int segmentLength(IndexedSeqOptimized $this, Function1 p, int from) {
/* 187 */     int len = $this.length();
/* 188 */     int i = from;
/* 189 */     for (; i < len && BoxesRunTime.unboxToBoolean(p.apply($this.apply(i))); i++);
/* 190 */     return i - from;
/*     */   }
/*     */   
/*     */   private static int negLength(IndexedSeqOptimized $this, int n) {
/* 193 */     return (n >= $this.length()) ? -1 : n;
/*     */   }
/*     */   
/*     */   public static int indexWhere(IndexedSeqOptimized $this, Function1 p, int from) {
/* 197 */     Predef$ predef$ = Predef$.MODULE$;
/* 197 */     int start = RichInt$.MODULE$.max$extension(from, 0);
/* 198 */     return negLength($this, start + $this.segmentLength((Function1)new IndexedSeqOptimized$$anonfun$indexWhere$1($this, (IndexedSeqOptimized<A, Repr>)p), start));
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(IndexedSeqOptimized $this, Function1 p, int end) {
/* 203 */     int i = end;
/* 204 */     for (; i >= 0 && !BoxesRunTime.unboxToBoolean(p.apply($this.apply(i))); i--);
/* 205 */     return i;
/*     */   }
/*     */   
/*     */   public static Object reverse(IndexedSeqOptimized $this) {
/* 210 */     Builder b = $this.newBuilder();
/* 211 */     b.sizeHint($this.length());
/* 212 */     int i = $this.length();
/* 213 */     while (0 < i) {
/* 214 */       i--;
/* 215 */       b.$plus$eq($this.apply(i));
/*     */     } 
/* 217 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Iterator reverseIterator(IndexedSeqOptimized<A, Repr> $this) {
/* 221 */     return new IndexedSeqOptimized$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static boolean startsWith(IndexedSeqOptimized $this, GenSeq that, int offset) {
/*     */     boolean bool;
/* 232 */     if (that instanceof IndexedSeq) {
/* 232 */       IndexedSeq<Object> indexedSeq = (IndexedSeq)that;
/* 234 */       int i = offset;
/* 235 */       int j = 0;
/* 236 */       int thisLen = $this.length();
/* 237 */       int thatLen = indexedSeq.length();
/* 238 */       while (i < thisLen && j < thatLen) {
/* 238 */         Object object1 = indexedSeq.apply(j);
/*     */         Object object;
/* 238 */         if (((object = $this.apply(i)) == object1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, object1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, object1) : object.equals(object1))))) {
/* 239 */           i++;
/* 240 */           j++;
/*     */         } 
/*     */       } 
/* 242 */       bool = (j == thatLen) ? true : false;
/*     */     } else {
/* 244 */       int i = offset;
/* 245 */       int thisLen = $this.length();
/* 246 */       Iterator<Object> thatElems = that.iterator();
/* 247 */       while (i < thisLen && thatElems.hasNext()) {
/* 248 */         Object object1 = thatElems.next();
/*     */         Object object;
/* 248 */         if (((object = $this.apply(i)) == object1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, object1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, object1) : object.equals(object1))))) {
/* 251 */           i++;
/*     */           continue;
/*     */         } 
/*     */         return false;
/*     */       } 
/* 253 */       bool = thatElems.hasNext() ? false : true;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public static boolean endsWith(IndexedSeqOptimized $this, GenSeq that) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/collection/IndexedSeq
/*     */     //   4: ifeq -> 160
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/collection/IndexedSeq
/*     */     //   11: astore_2
/*     */     //   12: aload_0
/*     */     //   13: invokeinterface length : ()I
/*     */     //   18: iconst_1
/*     */     //   19: isub
/*     */     //   20: istore #5
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface length : ()I
/*     */     //   28: iconst_1
/*     */     //   29: isub
/*     */     //   30: istore #6
/*     */     //   32: iload #6
/*     */     //   34: iload #5
/*     */     //   36: if_icmpgt -> 154
/*     */     //   39: iload #6
/*     */     //   41: iconst_0
/*     */     //   42: if_icmplt -> 146
/*     */     //   45: aload_0
/*     */     //   46: iload #5
/*     */     //   48: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   53: aload_2
/*     */     //   54: iload #6
/*     */     //   56: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   61: astore #4
/*     */     //   63: dup
/*     */     //   64: astore_3
/*     */     //   65: aload #4
/*     */     //   67: if_acmpne -> 74
/*     */     //   70: iconst_1
/*     */     //   71: goto -> 126
/*     */     //   74: aload_3
/*     */     //   75: ifnonnull -> 82
/*     */     //   78: iconst_0
/*     */     //   79: goto -> 126
/*     */     //   82: aload_3
/*     */     //   83: instanceof java/lang/Number
/*     */     //   86: ifeq -> 101
/*     */     //   89: aload_3
/*     */     //   90: checkcast java/lang/Number
/*     */     //   93: aload #4
/*     */     //   95: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   98: goto -> 126
/*     */     //   101: aload_3
/*     */     //   102: instanceof java/lang/Character
/*     */     //   105: ifeq -> 120
/*     */     //   108: aload_3
/*     */     //   109: checkcast java/lang/Character
/*     */     //   112: aload #4
/*     */     //   114: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   117: goto -> 126
/*     */     //   120: aload_3
/*     */     //   121: aload #4
/*     */     //   123: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   126: ifeq -> 144
/*     */     //   129: iload #5
/*     */     //   131: iconst_1
/*     */     //   132: isub
/*     */     //   133: istore #5
/*     */     //   135: iload #6
/*     */     //   137: iconst_1
/*     */     //   138: isub
/*     */     //   139: istore #6
/*     */     //   141: goto -> 39
/*     */     //   144: iconst_0
/*     */     //   145: ireturn
/*     */     //   146: iconst_1
/*     */     //   147: ifeq -> 154
/*     */     //   150: iconst_1
/*     */     //   151: goto -> 155
/*     */     //   154: iconst_0
/*     */     //   155: istore #7
/*     */     //   157: goto -> 169
/*     */     //   160: aload_0
/*     */     //   161: aload_1
/*     */     //   162: invokeinterface scala$collection$IndexedSeqOptimized$$super$endsWith : (Lscala/collection/GenSeq;)Z
/*     */     //   167: istore #7
/*     */     //   169: iload #7
/*     */     //   171: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #258	-> 0
/*     */     //   #257	-> 0
/*     */     //   #259	-> 12
/*     */     //   #260	-> 22
/*     */     //   #262	-> 32
/*     */     //   #263	-> 39
/*     */     //   #264	-> 45
/*     */     //   #266	-> 129
/*     */     //   #267	-> 135
/*     */     //   #265	-> 144
/*     */     //   #269	-> 146
/*     */     //   #262	-> 150
/*     */     //   #258	-> 155
/*     */     //   #272	-> 160
/*     */     //   #257	-> 169
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	172	0	$this	Lscala/collection/IndexedSeqOptimized;
/*     */     //   0	172	1	that	Lscala/collection/GenSeq;
/*     */     //   22	150	5	i	I
/*     */     //   32	140	6	j	I
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IndexedSeqOptimized$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */