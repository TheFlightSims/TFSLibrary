/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSet;
/*     */ import scala.collection.BitSet;
/*     */ import scala.collection.BitSetLike;
/*     */ import scala.collection.BitSetLike$;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.SortedSetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ue!B\001\003\003\003I!A\002\"jiN+GO\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\n\007\001)\021b\003\007\017\021\007-aa\"D\001\005\023\tiAAA\006BEN$(/Y2u'\026$\bCA\b\021\033\0051\021BA\t\007\005\rIe\016\036\t\004'QqQ\"\001\002\n\005U\021!!C*peR,GmU3u!\tYq#\003\002\002\tA\0311\"G\016\n\005i!!A\003\"jiN+G\017T5lKB\0211\003\001\t\003\037uI!A\b\004\003\031M+'/[1mSj\f'\r\\3\t\013\001\002A\021A\021\002\rqJg.\033;?)\005Y\002\"B\022\001\t\003\"\023!B3naRLX#A\016\t\013\031\002A\021A\024\002\023\031\024x.\\!se\006LHCA\016)\021\025IS\0051\001+\003\025)G.Z7t!\ry1&L\005\003Y\031\021Q!\021:sCf\004\"a\004\030\n\005=2!\001\002'p]\036DC!J\0315mA\021qBM\005\003g\031\021!\002Z3qe\026\034\027\r^3eC\005)\024aM+tK\002\022\025\016^*fi:2'o\\7CSRl\025m]6\\\035>\034u\016]=^A%t7\017^3bI\002zg\r\t4s_6\f%O]1zC\0059\024A\002\032/cAr\003\007C\003:\001\021E!(A\tge>l')\033;NCN\\gj\\\"paf$\"aG\036\t\013%B\004\031\001\026\t\013u\002a\021\003 \002\025U\004H-\031;f/>\024H\rF\002\034\005CQ\001\021\037A\0029\t1!\0333y\021\025\021E\b1\001.\003\0059\b\"\002#\001\t\003)\025!\002\023qYV\034HCA\016G\021\02595\t1\001\017\003\021)G.Z7\t\013%\003A\021\001&\002\r\021j\027N\\;t)\tY2\nC\003H\021\002\007a\002K\002\001\033B\003\"a\004(\n\005=3!\001E*fe&\fGNV3sg&|g.V%E=!1B,?\032NV\034W}!\002*\003\021\003\031\026A\002\"jiN+G\017\005\002\024)\032)\021A\001E\001+N!AKV-\035!\tyq+\003\002Y\r\t1\021I\\=SK\032\0042AW/\034\033\005Y&B\001/\005\003\0359WM\\3sS\016L!AX.\003\033\tKGoU3u\r\006\034Go\034:z\021\025\001C\013\"\001a)\005\031\006bB\022U\005\004%\t\001\n\005\007GR\003\013\021B\016\002\r\025l\007\017^=!\021\025)G\013\"\001g\003)qWm\036\"vS2$WM]\013\002OB!\001n\033\b\034\033\005I'B\0016\005\003\035iW\017^1cY\026L!\001\\5\003\017\t+\030\016\0343fe\")a\016\026C\002_\006a1-\0318Ck&dGM\022:p[V\t\001\017E\003[cnq1$\003\002s7\na1)\0318Ck&dGM\022:p[\")a\005\026C\001iR\0211$\036\005\006SM\004\rA\013\025\005gF:h'I\001y\0031*6/\032\021ge>l')\033;NCN\\7LT8D_BLX\fI5ogR,\027\r\032\021pM\0022'o\\7BeJ\f\027\020C\003{)\022\00510A\006ge>l')\033;NCN\\GCA\016}\021\025I\023\0201\001+\021\025ID\013\"\001)\tYr\020C\003*{\002\007!F\002\004\002\004Q\003\021Q\001\002\b\005&$8+\032;2'\r\t\ta\007\005\013S\005\005!Q1A\005\002\005%Q#A\027\t\025\0055\021\021\001B\001B\003%Q&\001\004fY\026l7\017\t\005\bA\005\005A\021AA\t)\021\t\031\"a\006\021\t\005U\021\021A\007\002)\"1\021&a\004A\0025B\001\"a\007\002\002\021E\021QD\001\007]^|'\017Z:\026\0039A\001\"!\t\002\002\021E\0211E\001\005o>\024H\rF\002.\003KAa\001QA\020\001\004q\001bB\037\002\002\021E\021\021\006\013\0067\005-\022Q\006\005\007\001\006\035\002\031\001\b\t\r\t\0139\0031\001.\r\031\t\t\004\026\001\0024\t9!)\033;TKR\0244cAA\0307!Y\021qGA\030\005\013\007I\021AA\005\003\031)G.Z7ta!Q\0211HA\030\005\003\005\013\021B\027\002\017\025dW-\\:1A!Q\021qHA\030\005\003\005\013\021B\027\002\r\025dW-\\:2\021\035\001\023q\006C\001\003\007\"b!!\022\002H\005%\003\003BA\013\003_Aq!a\016\002B\001\007Q\006C\004\002@\005\005\003\031A\027\t\021\005m\021q\006C\t\003;A\001\"!\t\0020\021E\021q\n\013\004[\005E\003B\002!\002N\001\007a\002C\004>\003_!\t\"!\026\025\013m\t9&!\027\t\r\001\013\031\0061\001\017\021\031\021\0251\013a\001[\0311\021Q\f+\001\003?\022qAQ5u'\026$hjE\002\002\\mA!\"KA.\005\013\007I\021AA2+\005Q\003BCA\007\0037\022\t\021)A\005U!9\001%a\027\005\002\005%D\003BA6\003[\002B!!\006\002\\!1\021&a\032A\002)B\001\"a\007\002\\\021E\021Q\004\005\t\003C\tY\006\"\005\002tQ\031Q&!\036\t\r\001\013\t\b1\001\017\021\035i\0241\fC\t\003s\"RaGA>\003{Ba\001QA<\001\004q\001B\002\"\002x\001\007Q\006C\005\002\002R\013\t\021\"\003\002\004\006Y!/Z1e%\026\034x\016\034<f)\t\t)\t\005\003\002\b\006EUBAAE\025\021\tY)!$\002\t1\fgn\032\006\003\003\037\013AA[1wC&!\0211SAE\005\031y%M[3di\002")
/*     */ public abstract class BitSet extends AbstractSet<Object> implements SortedSet<Object>, BitSet, BitSetLike<BitSet>, Serializable {
/*     */   public static final long serialVersionUID = 1611436763290191562L;
/*     */   
/*     */   public long[] toBitMask() {
/*  27 */     return BitSetLike.class.toBitMask(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  27 */     return BitSetLike.class.size(this);
/*     */   }
/*     */   
/*     */   public Ordering<Object> ordering() {
/*  27 */     return BitSetLike.class.ordering(this);
/*     */   }
/*     */   
/*     */   public BitSet rangeImpl(Option from, Option until) {
/*  27 */     return (BitSet)BitSetLike.class.rangeImpl(this, from, until);
/*     */   }
/*     */   
/*     */   public Iterator<Object> iterator() {
/*  27 */     return BitSetLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  27 */     BitSetLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public BitSetLike $bar(BitSet other) {
/*  27 */     return BitSetLike.class.$bar(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $amp(BitSet other) {
/*  27 */     return BitSetLike.class.$amp(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $amp$tilde(BitSet other) {
/*  27 */     return BitSetLike.class.$amp$tilde(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $up(BitSet other) {
/*  27 */     return BitSetLike.class.$up(this, other);
/*     */   }
/*     */   
/*     */   public boolean contains(int elem) {
/*  27 */     return BitSetLike.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(BitSet other) {
/*  27 */     return BitSetLike.class.subsetOf(this, other);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder sb, String start, String sep, String end) {
/*  27 */     return BitSetLike.class.addString(this, sb, start, sep, end);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  27 */     return BitSetLike.class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
/*  27 */     return GenSetLike.class.subsetOf((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public SortedSet keySet() {
/*  27 */     return SortedSetLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/*  27 */     return SortedSetLike.class.firstKey(this);
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  27 */     return SortedSetLike.class.lastKey(this);
/*     */   }
/*     */   
/*     */   public SortedSet from(Object from) {
/*  27 */     return SortedSetLike.class.from(this, from);
/*     */   }
/*     */   
/*     */   public SortedSet until(Object until) {
/*  27 */     return SortedSetLike.class.until(this, until);
/*     */   }
/*     */   
/*     */   public SortedSet range(Object from, Object until) {
/*  27 */     return SortedSetLike.class.range(this, from, until);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  27 */     return SortedSetLike.class.subsetOf(this, that);
/*     */   }
/*     */   
/*     */   public int compare(Object k0, Object k1) {
/*  27 */     return Sorted.class.compare((Sorted)this, k0, k1);
/*     */   }
/*     */   
/*     */   public Sorted to(Object to) {
/*  27 */     return Sorted.class.to((Sorted)this, to);
/*     */   }
/*     */   
/*     */   public boolean hasAll(Iterator j) {
/*  27 */     return Sorted.class.hasAll((Sorted)this, j);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Set> companion() {
/*  27 */     return Set$class.companion(this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  27 */     return Set$class.toSet(this);
/*     */   }
/*     */   
/*     */   public Set<Object> seq() {
/*  27 */     return Set$class.seq(this);
/*     */   }
/*     */   
/*     */   public Combiner<Object, ParSet<Object>> parCombiner() {
/*  27 */     return Set$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public BitSet() {
/*  27 */     Traversable$class.$init$(this);
/*  27 */     Iterable$class.$init$(this);
/*  27 */     Set$class.$init$(this);
/*  27 */     Sorted.class.$init$((Sorted)this);
/*  27 */     SortedSetLike.class.$init$(this);
/*  27 */     SortedSet.class.$init$(this);
/*  27 */     SortedSet$class.$init$(this);
/*  27 */     BitSetLike.class.$init$(this);
/*  27 */     BitSet.class.$init$(this);
/*     */   }
/*     */   
/*     */   public BitSet empty() {
/*  32 */     return BitSet$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public BitSet fromArray(long[] elems) {
/*  35 */     return fromBitMaskNoCopy(elems);
/*     */   }
/*     */   
/*     */   public BitSet fromBitMaskNoCopy(long[] elems) {
/*  37 */     return BitSet$.MODULE$.fromBitMaskNoCopy(elems);
/*     */   }
/*     */   
/*     */   public BitSet $plus(int elem) {
/*  46 */     boolean bool = (elem >= 0) ? true : false;
/*  46 */     Predef$ predef$ = Predef$.MODULE$;
/*  46 */     if (bool) {
/*  49 */       int idx = elem >> BitSetLike$.MODULE$.LogWL();
/*  50 */       return contains(elem) ? this : updateWord(idx, word(idx) | 1L << elem);
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("bitset element must be >= 0").toString());
/*     */   }
/*     */   
/*     */   public BitSet $minus(int elem) {
/*  57 */     boolean bool = (elem >= 0) ? true : false;
/*  57 */     Predef$ predef$ = Predef$.MODULE$;
/*  57 */     if (bool) {
/*  59 */       int idx = elem >> BitSetLike$.MODULE$.LogWL();
/*  60 */       return contains(elem) ? updateWord(idx, word(idx) & (1L << elem ^ 0xFFFFFFFFFFFFFFFFL)) : 
/*  61 */         this;
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("bitset element must be >= 0").toString());
/*     */   }
/*     */   
/*     */   public static Object bitsetCanBuildFrom() {
/*     */     return BitSet$.MODULE$.bitsetCanBuildFrom();
/*     */   }
/*     */   
/*     */   public static BitSet fromBitMask(long[] paramArrayOflong) {
/*     */     return BitSet$.MODULE$.fromBitMask(paramArrayOflong);
/*     */   }
/*     */   
/*     */   public static CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
/*     */     return BitSet$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public abstract BitSet updateWord(int paramInt, long paramLong);
/*     */   
/*     */   public static class BitSet$$anon$1 implements Builder<Object, BitSet> {
/*     */     private final scala.collection.mutable.BitSet b;
/*     */     
/*     */     public void sizeHint(int size) {
/*  74 */       Builder.class.sizeHint(this, size);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/*  74 */       Builder.class.sizeHint(this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/*  74 */       Builder.class.sizeHint(this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  74 */       Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<Object, NewTo> mapResult(Function1 f) {
/*  74 */       return Builder.class.mapResult(this, f);
/*     */     }
/*     */     
/*     */     public Growable<Object> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  74 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<Object> $plus$plus$eq(TraversableOnce xs) {
/*  74 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     }
/*     */     
/*     */     public BitSet$$anon$1() {
/*  74 */       Growable.class.$init$((Growable)this);
/*  74 */       Builder.class.$init$(this);
/*  75 */       this.b = new scala.collection.mutable.BitSet();
/*     */     }
/*     */     
/*     */     public BitSet$$anon$1 $plus$eq(int x) {
/*  76 */       this.b.$plus$eq(x);
/*  76 */       return this;
/*     */     }
/*     */     
/*     */     public void clear() {
/*  77 */       this.b.clear();
/*     */     }
/*     */     
/*     */     public BitSet result() {
/*  78 */       return this.b.toImmutable();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BitSet1 extends BitSet {
/*     */     private final long elems;
/*     */     
/*     */     public long elems() {
/* 112 */       return this.elems;
/*     */     }
/*     */     
/*     */     public BitSet1(long elems) {}
/*     */     
/*     */     public int nwords() {
/* 113 */       return 1;
/*     */     }
/*     */     
/*     */     public long word(int idx) {
/* 114 */       return (idx == 0) ? elems() : 0L;
/*     */     }
/*     */     
/*     */     public BitSet updateWord(int idx, long w) {
/* 116 */       return (idx == 0) ? new BitSet1(w) : (
/* 117 */         (idx == 1) ? new BitSet.BitSet2(elems(), w) : 
/* 118 */         fromBitMaskNoCopy(BitSetLike$.MODULE$.updateArray(new long[] { elems() }, idx, w)));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BitSet2 extends BitSet {
/*     */     private final long elems0;
/*     */     
/*     */     private final long elems1;
/*     */     
/*     */     public long elems0() {
/* 121 */       return this.elems0;
/*     */     }
/*     */     
/*     */     public BitSet2(long elems0, long elems1) {}
/*     */     
/*     */     public int nwords() {
/* 122 */       return 2;
/*     */     }
/*     */     
/*     */     public long word(int idx) {
/* 123 */       return (idx == 0) ? elems0() : ((idx == 1) ? this.elems1 : 0L);
/*     */     }
/*     */     
/*     */     public BitSet updateWord(int idx, long w) {
/* 125 */       return (idx == 0) ? new BitSet2(w, this.elems1) : (
/* 126 */         (idx == 1) ? new BitSet2(elems0(), w) : 
/* 127 */         fromBitMaskNoCopy(BitSetLike$.MODULE$.updateArray(new long[] { elems0(), this.elems1 }, idx, w)));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BitSetN extends BitSet {
/*     */     private final long[] elems;
/*     */     
/*     */     public long[] elems() {
/* 136 */       return this.elems;
/*     */     }
/*     */     
/*     */     public BitSetN(long[] elems) {}
/*     */     
/*     */     public int nwords() {
/* 137 */       return (elems()).length;
/*     */     }
/*     */     
/*     */     public long word(int idx) {
/* 138 */       return (idx < nwords()) ? elems()[idx] : 0L;
/*     */     }
/*     */     
/*     */     public BitSet updateWord(int idx, long w) {
/* 139 */       return fromBitMaskNoCopy(BitSetLike$.MODULE$.updateArray(elems(), idx, w));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\BitSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */