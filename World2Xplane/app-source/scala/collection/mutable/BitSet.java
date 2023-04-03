/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.BitSet;
/*     */ import scala.collection.BitSetLike;
/*     */ import scala.collection.BitSetLike$;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.SortedSetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.BitSet$;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]c\001B\001\003\001%\021aAQ5u'\026$(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\031r\001\001\006\023+aar\004E\002\f\0319i\021AA\005\003\033\t\0211\"\0212tiJ\f7\r^*fiB\021q\002E\007\002\r%\021\021C\002\002\004\023:$\bcA\006\024\035%\021AC\001\002\n'>\024H/\0323TKR\004\"AF\f\016\003\021I!!\001\003\021\007YI2$\003\002\033\t\tQ!)\033;TKRd\025n[3\021\005-\001\001\003B\006\036\035mI!A\b\002\003\017M+G\017T5lKB\021q\002I\005\003C\031\021AbU3sS\006d\027N_1cY\026D\001b\t\001\003\002\004%\t\002J\001\006K2,Wn]\013\002KA\031qB\n\025\n\005\0352!!B!se\006L\bCA\b*\023\tQcA\001\003M_:<\007\002\003\027\001\005\003\007I\021C\027\002\023\025dW-\\:`I\025\fHC\001\0302!\tyq&\003\0021\r\t!QK\\5u\021\035\0214&!AA\002\025\n1\001\037\0232\021!!\004A!A!B\023)\023AB3mK6\034\b\005C\0037\001\021\005q'\001\004=S:LGO\020\013\0037aBQaI\033A\002\025BQA\017\001\005Bm\nQ!Z7qif,\022a\007\005\006m\001!\t!\020\013\0037yBQa\020\037A\0029\t\001\"\0338jiNK'0\032\005\006m\001!\t!\021\013\0027!)1\t\001C\t\t\0061an^8sIN,\022A\004\005\006\r\002!\tbR\001\005o>\024H\r\006\002)\021\")\021*\022a\001\035\005\031\021\016\032=\t\013-\003A\021\002'\002\025U\004H-\031;f/>\024H\rF\002/\033:CQ!\023&A\0029AQa\024&A\002!\n\021a\036\005\006#\002!\tBU\001\022MJ|WNQ5u\033\006\0348NT8D_BLHCA\016T\021\025!\006\0131\001&\003\0259xN\0353t\021\0251\006\001\"\021X\003\r\tG\r\032\013\0031n\003\"aD-\n\005i3!a\002\"p_2,\027M\034\005\0069V\003\rAD\001\005K2,W\016C\003_\001\021\005s,\001\004sK6|g/\032\013\0031\002DQ\001X/A\0029AQA\031\001\005\002\r\f\001\002\n9mkN$S-\035\013\003I\026l\021\001\001\005\0069\006\004\rA\004\005\006O\002!\t\001[\001\nI5Lg.^:%KF$\"\001Z5\t\013q3\007\031\001\b\t\013-\004A\021\t7\002\013\rdW-\031:\025\0039BQA\034\001\005\002=\f1\002^8J[6,H/\0312mKV\t\001\017\005\002ri6\t!O\003\002t\t\005I\021.\\7vi\006\024G.Z\005\003\003IDQA\036\001\005B\005\013Qa\0317p]\026D3\001\001=|!\ty\0210\003\002{\r\t\0012+\032:jC24VM]:j_:,\026\n\022\020\tkj\0062>\f#\034g\037)QP\001E\001}\0061!)\033;TKR\004\"aC@\007\r\005\021\001\022AA\001'\031y\0301AA\005?A\031q\"!\002\n\007\005\035aA\001\004B]f\024VM\032\t\006\003\027\t\tbG\007\003\003\033Q1!a\004\005\003\0359WM\\3sS\016LA!a\005\002\016\ti!)\033;TKR4\025m\031;pefDaAN@\005\002\005]A#\001@\t\013izH\021A\036\t\017\005uq\020\"\001\002 \005Qa.Z<Ck&dG-\032:\026\005\005\005\002#B\006\002$9Y\022bAA\023\005\t9!)^5mI\026\024\bbBA\025\022\r\0211F\001\rG\006t')^5mI\032\023x.\\\013\003\003[\001r!a\003\0020mq1$\003\003\0022\0055!\001D\"b]\n+\030\016\0343Ge>l\007bBA\033\022\005\021qG\001\fMJ|WNQ5u\033\006\0348\016F\002\034\003sAaaIA\032\001\004)\003BB)\000\t\003\ti\004F\002\034\003AaaIA\036\001\004)\003\"CA\"\006\005I\021BA#\003-\021X-\0313SKN|GN^3\025\005\005\035\003\003BA%\003'j!!a\023\013\t\0055\023qJ\001\005Y\006twM\003\002\002R\005!!.\031<b\023\021\t)&a\023\003\r=\023'.Z2u\001")
/*     */ public class BitSet extends AbstractSet<Object> implements SortedSet<Object>, BitSet, BitSetLike<BitSet>, SetLike<Object, BitSet>, Serializable {
/*     */   public static final long serialVersionUID = 8483111450368547763L;
/*     */   
/*     */   private long[] elems;
/*     */   
/*     */   public long[] toBitMask() {
/*  39 */     return BitSetLike.class.toBitMask(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  39 */     return BitSetLike.class.size(this);
/*     */   }
/*     */   
/*     */   public Ordering<Object> ordering() {
/*  39 */     return BitSetLike.class.ordering(this);
/*     */   }
/*     */   
/*     */   public BitSet rangeImpl(Option from, Option until) {
/*  39 */     return (BitSet)BitSetLike.class.rangeImpl(this, from, until);
/*     */   }
/*     */   
/*     */   public Iterator<Object> iterator() {
/*  39 */     return BitSetLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  39 */     BitSetLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public BitSetLike $bar(BitSet other) {
/*  39 */     return BitSetLike.class.$bar(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $amp(BitSet other) {
/*  39 */     return BitSetLike.class.$amp(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $amp$tilde(BitSet other) {
/*  39 */     return BitSetLike.class.$amp$tilde(this, other);
/*     */   }
/*     */   
/*     */   public BitSetLike $up(BitSet other) {
/*  39 */     return BitSetLike.class.$up(this, other);
/*     */   }
/*     */   
/*     */   public boolean contains(int elem) {
/*  39 */     return BitSetLike.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(BitSet other) {
/*  39 */     return BitSetLike.class.subsetOf(this, other);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder sb, String start, String sep, String end) {
/*  39 */     return BitSetLike.class.addString(this, sb, start, sep, end);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  39 */     return BitSetLike.class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
/*  39 */     return GenSetLike.class.subsetOf((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public SortedSet keySet() {
/*  39 */     return SortedSetLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/*  39 */     return SortedSetLike.class.firstKey(this);
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  39 */     return SortedSetLike.class.lastKey(this);
/*     */   }
/*     */   
/*     */   public SortedSet from(Object from) {
/*  39 */     return SortedSetLike.class.from(this, from);
/*     */   }
/*     */   
/*     */   public SortedSet until(Object until) {
/*  39 */     return SortedSetLike.class.until(this, until);
/*     */   }
/*     */   
/*     */   public SortedSet range(Object from, Object until) {
/*  39 */     return SortedSetLike.class.range(this, from, until);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  39 */     return SortedSetLike.class.subsetOf(this, that);
/*     */   }
/*     */   
/*     */   public int compare(Object k0, Object k1) {
/*  39 */     return Sorted.class.compare((Sorted)this, k0, k1);
/*     */   }
/*     */   
/*     */   public Sorted to(Object to) {
/*  39 */     return Sorted.class.to((Sorted)this, to);
/*     */   }
/*     */   
/*     */   public boolean hasAll(Iterator j) {
/*  39 */     return Sorted.class.hasAll((Sorted)this, j);
/*     */   }
/*     */   
/*     */   public long[] elems() {
/*  39 */     return this.elems;
/*     */   }
/*     */   
/*     */   public void elems_$eq(long[] x$1) {
/*  39 */     this.elems = x$1;
/*     */   }
/*     */   
/*     */   public BitSet(long[] elems) {
/*  39 */     Sorted.class.$init$((Sorted)this);
/*  39 */     SortedSetLike.class.$init$(this);
/*  39 */     SortedSet.class.$init$(this);
/*  39 */     SortedSet$class.$init$(this);
/*  39 */     BitSetLike.class.$init$(this);
/*  39 */     BitSet.class.$init$(this);
/*     */   }
/*     */   
/*     */   public BitSet empty() {
/*  46 */     return BitSet$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public BitSet(int initSize) {
/*  52 */     this(new long[RichInt$.MODULE$.max$extension(i, 1)]);
/*     */   }
/*     */   
/*     */   public BitSet() {
/*  54 */     this(0);
/*     */   }
/*     */   
/*     */   public int nwords() {
/*  56 */     return (elems()).length;
/*     */   }
/*     */   
/*     */   public long word(int idx) {
/*  58 */     return (idx < nwords()) ? elems()[idx] : 0L;
/*     */   }
/*     */   
/*     */   private void updateWord(int idx, long w) {
/*  61 */     if (idx >= nwords()) {
/*  62 */       int newlen = nwords();
/*  63 */       for (; idx >= newlen; newlen *= 2);
/*  64 */       long[] elems1 = new long[newlen];
/*  65 */       Array$.MODULE$.copy(elems(), 0, elems1, 0, nwords());
/*  66 */       elems_$eq(elems1);
/*     */     } 
/*  68 */     elems()[idx] = w;
/*     */   }
/*     */   
/*     */   public BitSet fromBitMaskNoCopy(long[] words) {
/*  71 */     return new BitSet(words);
/*     */   }
/*     */   
/*     */   public boolean add(int elem) {
/*  74 */     Predef$.MODULE$.require((elem >= 0));
/*  77 */     int idx = elem >> BitSetLike$.MODULE$.LogWL();
/*  78 */     updateWord(idx, word(idx) | 1L << elem);
/*     */     return !contains(elem);
/*     */   }
/*     */   
/*     */   public boolean remove(int elem) {
/*  84 */     Predef$.MODULE$.require((elem >= 0));
/*  86 */     int idx = elem >> BitSetLike$.MODULE$.LogWL();
/*  87 */     updateWord(idx, word(idx) & (1L << elem ^ 0xFFFFFFFFFFFFFFFFL));
/*     */     return contains(elem);
/*     */   }
/*     */   
/*     */   public BitSet $plus$eq(int elem) {
/*  92 */     add(elem);
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public BitSet $minus$eq(int elem) {
/*  93 */     remove(elem);
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  96 */     elems_$eq(new long[(elems()).length]);
/*     */   }
/*     */   
/*     */   public scala.collection.immutable.BitSet toImmutable() {
/* 106 */     return BitSet$.MODULE$.fromBitMaskNoCopy(elems());
/*     */   }
/*     */   
/*     */   public BitSet clone() {
/* 109 */     long[] elems1 = new long[(elems()).length];
/* 110 */     Array$.MODULE$.copy(elems(), 0, elems1, 0, (elems()).length);
/* 111 */     return new BitSet(elems1);
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
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BitSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */