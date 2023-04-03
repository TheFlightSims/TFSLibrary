/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.Ordering$Int$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class BitSetLike$class {
/*     */   public static void $init$(BitSetLike $this) {}
/*     */   
/*     */   public static long[] toBitMask(BitSetLike $this) {
/*  54 */     long[] a = new long[$this.nwords()];
/*  55 */     int i = a.length;
/*  56 */     while (i > 0) {
/*  57 */       i--;
/*  58 */       a[i] = $this.word(i);
/*     */     } 
/*  60 */     return a;
/*     */   }
/*     */   
/*     */   public static int size(BitSetLike $this) {
/*  64 */     int s = 0;
/*  65 */     int i = $this.nwords();
/*  66 */     while (i > 0) {
/*  67 */       i--;
/*  68 */       s += Long.bitCount($this.word(i));
/*     */     } 
/*  70 */     return s;
/*     */   }
/*     */   
/*     */   public static Ordering ordering(BitSetLike $this) {
/*  73 */     return (Ordering)Ordering$Int$.MODULE$;
/*     */   }
/*     */   
/*     */   public static BitSetLike rangeImpl(BitSetLike<BitSetLike> $this, Option from, Option until) {
/*  76 */     long[] a = $this.toBitMask();
/*  77 */     int len = a.length;
/*  78 */     if (from.isDefined()) {
/*  79 */       int f = BoxesRunTime.unboxToInt(from.get());
/*  80 */       int pos = 0;
/*  81 */       while (f >= 64 && pos < len) {
/*  82 */         f -= 64;
/*  83 */         a[pos] = 0L;
/*  84 */         pos++;
/*     */       } 
/*  86 */       if (f > 0 && pos < len)
/*  86 */         a[pos] = a[pos] & ((1L << f) - 1L ^ 0xFFFFFFFFFFFFFFFFL); 
/*     */     } 
/*  88 */     if (until.isDefined()) {
/*  89 */       int u = BoxesRunTime.unboxToInt(until.get());
/*  90 */       int w = u / 64;
/*  91 */       int b = u % 64;
/*  92 */       int clearw = w + 1;
/*  93 */       while (clearw < len) {
/*  94 */         a[clearw] = 0L;
/*  95 */         clearw++;
/*     */       } 
/*  97 */       if (w < len)
/*  97 */         a[w] = a[w] & (1L << b) - 1L; 
/*     */     } 
/*  99 */     return $this.fromBitMaskNoCopy(a);
/*     */   }
/*     */   
/*     */   public static Iterator iterator(BitSetLike<This> $this) {
/* 102 */     return new BitSetLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static void foreach(BitSetLike $this, Function1 f) {
/* 115 */     Predef$ predef$ = Predef$.MODULE$;
/* 115 */     int i = $this.nwords();
/* 115 */     Range$ range$ = Range$.MODULE$;
/* 115 */     BitSetLike$$anonfun$foreach$1 bitSetLike$$anonfun$foreach$1 = new BitSetLike$$anonfun$foreach$1($this, (BitSetLike<This>)f);
/*     */     Range range;
/* 115 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$foreach$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 115 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 115 */         long w1 = $this.word(i1);
/* 115 */         int j = i1 * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/* 115 */         Predef$ predef$1 = Predef$.MODULE$;
/* 115 */         int k = (i1 + 1) * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/* 115 */         Range$ range$1 = Range$.MODULE$;
/* 115 */         BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 = new BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1(bitSetLike$$anonfun$foreach$1, w1);
/*     */         Range range1;
/* 115 */         if ((range1 = new Range(j, k, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1)) {
/*     */           int terminal2;
/*     */           int step2;
/*     */           int i3;
/* 115 */           for (i3 = range1.start(), terminal2 = range1.terminalElement(), step2 = range1.step(); i3 != terminal2; ) {
/* 115 */             int m = i3;
/* 115 */             ((w1 & 1L << m) != 0L) ? f.apply(BoxesRunTime.boxToInteger(m)) : BoxedUnit.UNIT;
/* 115 */             i3 += step2;
/*     */           } 
/*     */         } 
/* 115 */         i1 += step1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static BitSetLike $bar(BitSetLike<BitSetLike> $this, BitSet other) {
/* 131 */     int i = $this.nwords();
/* 131 */     Predef$ predef$1 = Predef$.MODULE$;
/* 131 */     int len = RichInt$.MODULE$.max$extension(i, other.nwords());
/* 132 */     long[] words = new long[len];
/* 133 */     Predef$ predef$2 = Predef$.MODULE$;
/* 133 */     Range$ range$ = Range$.MODULE$;
/* 133 */     BitSetLike$$anonfun$$bar$1 bitSetLike$$anonfun$$bar$1 = new BitSetLike$$anonfun$$bar$1($this, words, other);
/*     */     Range range;
/* 133 */     if ((range = new Range(0, len, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$$bar$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 133 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 133 */         words[i1] = $this.word(i1) | other.word(i1);
/* 133 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 135 */     return $this.fromBitMaskNoCopy(words);
/*     */   }
/*     */   
/*     */   public static BitSetLike $amp(BitSetLike<BitSetLike> $this, BitSet other) {
/* 145 */     int i = $this.nwords();
/* 145 */     Predef$ predef$1 = Predef$.MODULE$;
/* 145 */     int len = RichInt$.MODULE$.min$extension(i, other.nwords());
/* 146 */     long[] words = new long[len];
/* 147 */     Predef$ predef$2 = Predef$.MODULE$;
/* 147 */     Range$ range$ = Range$.MODULE$;
/* 147 */     BitSetLike$$anonfun$$amp$1 bitSetLike$$anonfun$$amp$1 = new BitSetLike$$anonfun$$amp$1($this, words, other);
/*     */     Range range;
/* 147 */     if ((range = new Range(0, len, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$$amp$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 147 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 147 */         words[i1] = $this.word(i1) & other.word(i1);
/* 147 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 149 */     return $this.fromBitMaskNoCopy(words);
/*     */   }
/*     */   
/*     */   public static BitSetLike $amp$tilde(BitSetLike<BitSetLike> $this, BitSet other) {
/* 160 */     int len = $this.nwords();
/* 161 */     long[] words = new long[len];
/* 162 */     Predef$ predef$ = Predef$.MODULE$;
/* 162 */     Range$ range$ = Range$.MODULE$;
/* 162 */     BitSetLike$$anonfun$$amp$tilde$1 bitSetLike$$anonfun$$amp$tilde$1 = new BitSetLike$$anonfun$$amp$tilde$1($this, words, other);
/*     */     Range range;
/* 162 */     if ((range = new Range(0, len, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$$amp$tilde$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 162 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 162 */         words[i1] = $this.word(i1) & (other.word(i1) ^ 0xFFFFFFFFFFFFFFFFL);
/* 162 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 164 */     return $this.fromBitMaskNoCopy(words);
/*     */   }
/*     */   
/*     */   public static BitSetLike $up(BitSetLike<BitSetLike> $this, BitSet other) {
/* 175 */     int i = $this.nwords();
/* 175 */     Predef$ predef$1 = Predef$.MODULE$;
/* 175 */     int len = RichInt$.MODULE$.max$extension(i, other.nwords());
/* 176 */     long[] words = new long[len];
/* 177 */     Predef$ predef$2 = Predef$.MODULE$;
/* 177 */     Range$ range$ = Range$.MODULE$;
/* 177 */     BitSetLike$$anonfun$$up$1 bitSetLike$$anonfun$$up$1 = new BitSetLike$$anonfun$$up$1($this, words, other);
/*     */     Range range;
/* 177 */     if ((range = new Range(0, len, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$$up$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 177 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 177 */         words[i1] = $this.word(i1) ^ other.word(i1);
/* 177 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 179 */     return $this.fromBitMaskNoCopy(words);
/*     */   }
/*     */   
/*     */   public static boolean contains(BitSetLike $this, int elem) {
/* 183 */     return (0 <= elem && ($this.word(elem >> BitSetLike$.MODULE$.LogWL()) & 1L << elem) != 0L);
/*     */   }
/*     */   
/*     */   public static boolean subsetOf(BitSetLike $this, BitSet other) {
/* 192 */     Predef$ predef$ = Predef$.MODULE$;
/* 192 */     return RichInt$.MODULE$.until$extension0(0, $this.nwords()).forall((Function1)new BitSetLike$$anonfun$subsetOf$1($this, other));
/*     */   }
/*     */   
/*     */   public static StringBuilder addString(BitSetLike $this, StringBuilder sb, String start, String sep, String end) {
/* 195 */     sb.append(start);
/* 196 */     ObjectRef pre = new ObjectRef("");
/* 197 */     Predef$ predef$ = Predef$.MODULE$;
/* 197 */     int i = $this.nwords() * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/* 197 */     Range$ range$ = Range$.MODULE$;
/* 197 */     BitSetLike$$anonfun$addString$1 bitSetLike$$anonfun$addString$1 = new BitSetLike$$anonfun$addString$1($this, pre, sb, (BitSetLike<This>)sep);
/*     */     Range range;
/* 197 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)bitSetLike$$anonfun$addString$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 197 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 197 */         if ($this.contains(i1)) {
/* 197 */           sb.append((String)pre.elem).append(i1);
/* 197 */           pre.elem = sep;
/*     */         } 
/* 197 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 202 */     return sb.append(end);
/*     */   }
/*     */   
/*     */   public static String stringPrefix(BitSetLike $this) {
/* 205 */     return "BitSet";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\BitSetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */