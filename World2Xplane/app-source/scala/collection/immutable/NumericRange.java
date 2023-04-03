/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t%h!B\001\003\003\003I!\001\004(v[\026\024\030n\031*b]\036,'BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M!\001aC\016 !\raQbD\007\002\t%\021a\002\002\002\f\003\n\034HO]1diN+\027\017\005\002\021#1\001A!\002\n\001\005\004\031\"!\001+\022\005QA\002CA\013\027\033\0051\021BA\f\007\005\035qu\016\0365j]\036\004\"!F\r\n\005i1!aA!osB\031A$H\b\016\003\tI!A\b\002\003\025%sG-\032=fIN+\027\017\005\002\026A%\021\021E\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\tG\001\021)\031!C\001I\005)1\017^1siV\tq\002\003\005'\001\t\005\t\025!\003\020\003\031\031H/\031:uA!A\001\006\001BC\002\023\005A%A\002f]\022D\001B\013\001\003\002\003\006IaD\001\005K:$\007\005\003\005-\001\t\025\r\021\"\001%\003\021\031H/\0329\t\0219\002!\021!Q\001\n=\tQa\035;fa\002B\001\002\r\001\003\006\004%\t!M\001\fSNLen\0317vg&4X-F\0013!\t)2'\003\0025\r\t9!i\\8mK\006t\007\002\003\034\001\005\003\005\013\021\002\032\002\031%\034\030J\\2mkNLg/\032\021\t\021a\002!\021!Q\001\fe\n1A\\;n!\rQ$i\004\b\003w\001s!\001P \016\003uR!A\020\005\002\rq\022xn\034;?\023\0059\021BA!\007\003\035\001\030mY6bO\026L!a\021#\003\021%sG/Z4sC2T!!\021\004\t\013\031\003A\021A$\002\rqJg.\033;?)\025A5\nT'O)\tI%\nE\002\035\001=AQ\001O#A\004eBQaI#A\002=AQ\001K#A\002=AQ\001L#A\002=AQ\001M#A\002IB\001\002\025\001\t\006\004%I!U\001\021]Vl'+\0318hK\026cW-\\3oiN,\022A\025\t\003+MK!\001\026\004\003\007%sG\017\003\005W\001!\005\t\025)\003S\003EqW/\034*b]\036,W\t\\3nK:$8\017\t\005\0061\002!\t%U\001\007Y\026tw\r\0365\t\013i\003A\021I\031\002\017%\034X)\0349us\"AA\f\001EC\002\023\005C%\001\003mCN$\b\002\0030\001\021\003\005\013\025B\b\002\0131\f7\017\036\021\t\013\001\004A\021A1\002\005\tLHCA%c\021\025\031w\f1\001\020\003\035qWm^*uKBDQ!\032\001\007\002\031\fAaY8qsR!\021j\0325j\021\025\031C\r1\001\020\021\025AC\r1\001\020\021\025aC\r1\001\020\021\025Y\007\001\"\021m\003\0351wN]3bG\",\"!\\<\025\0059\f\bCA\013p\023\t\001hA\001\003V]&$\b\"\002:k\001\004\031\030!\0014\021\tU!xB^\005\003k\032\021\021BR;oGRLwN\\\031\021\005A9H!\002=k\005\004\031\"!A+\t\013i\004A\021B>\002\023M\\\027\016]\"pk:$HC\001*}\021\025i\030\0201\001\003\005\001\b\003B\013u\037IBq!!\001\001\t\023\t\031!\001\njg^KG\017[5o\005>,h\016Z1sS\026\034Hc\001\032\002\006!1\021qA@A\002=\tA!\0327f[\"9\0211\002\001\005\n\0055\021A\0047pG\006$\030n\0348BMR,'O\024\013\004\037\005=\001bBA\t\003\023\001\rAU\001\002]\"9\021Q\003\001\005\n\005]\021!\0048fo\026k\007\017^=SC:<W\r\006\003\002\032\tm\002#BA\016\003\017{ab\001\017\002\036\0359\021q\004\002\t\002\005\005\022\001\004(v[\026\024\030n\031*b]\036,\007c\001\017\002$\0311\021A\001E\001\003K\031R!a\t\002(}\0012!FA\025\023\r\tYC\002\002\007\003:L(+\0324\t\017\031\013\031\003\"\001\0020Q\021\021\021\005\005\t\003g\t\031\003\"\001\0026\005)1m\\;oiV!\021qGA!))\tI$a\021\002F\005\035\023\021\n\013\004%\006m\002b\002\035\0022\001\017\021Q\b\t\005u\t\013y\004E\002\021\003\003\"aAEA\031\005\004\031\002bB\022\0022\001\007\021q\b\005\bQ\005E\002\031AA \021\035a\023\021\007a\001\003Aa\001MA\031\001\004\021daBA'\003G\001\021q\n\002\n\023:\034G.^:jm\026,B!!\025\002XM!\0211JA*!\021a\002!!\026\021\007A\t9\006\002\004\023\003\027\022\ra\005\005\fG\005-#\021!Q\001\n\005U#\005C\006)\003\027\022\t\021)A\005\003+:\003b\003\027\002L\t\005\t\025!\003\002V-B!\002OA&\005\003\005\0131BA1!\021Q$)!\026\t\017\031\013Y\005\"\001\002fQA\021qMA8\003c\n\031\b\006\003\002j\0055\004CBA6\003\027\n)&\004\002\002$!9\001(a\031A\004\005\005\004bB\022\002d\001\007\021Q\013\005\bQ\005\r\004\031AA+\021\035a\0231\ra\001\003+Bq!ZA&\t\003\t9\b\006\005\002j\005e\0241PA?\021\035\031\023Q\017a\001\003+Bq\001KA;\001\004\t)\006C\004-\003k\002\r!!\026\t\021\005\005\0251\nC\001\003\007\013\021\"\032=dYV\034\030N^3\026\005\005\025\005CBA6\003\017\013)FB\004\002\n\006\r\002!a#\003\023\025C8\r\\;tSZ,W\003BAG\003'\033B!a\"\002\020B!A\004AAI!\r\001\0221\023\003\007%\005\035%\031A\n\t\027\r\n9I!A!\002\023\t\tJ\t\005\fQ\005\035%\021!Q\001\n\005Eu\005C\006-\003\017\023\t\021)A\005\003#[\003B\003\035\002\b\n\005\t\025a\003\002\036B!!HQAI\021\0351\025q\021C\001\003C#\002\"a)\002*\006-\026Q\026\013\005\003K\0139\013\005\004\002l\005\035\025\021\023\005\bq\005}\0059AAO\021\035\031\023q\024a\001\003#Cq\001KAP\001\004\t\t\nC\004-\003?\003\r!!%\t\017\025\f9\t\"\001\0022RA\021QUAZ\003k\0139\fC\004$\003_\003\r!!%\t\017!\ny\0131\001\002\022\"9A&a,A\002\005E\005\002CA^\003\017#\t!!0\002\023%t7\r\\;tSZ,WCAA`!\031\tY'a\023\002\022\"A\0211YA\022\t\003\t)-A\003baBd\0270\006\003\002H\006=G\003CAe\003+\f9.!7\025\t\005-\027\021\033\t\007\003W\n9)!4\021\007A\ty\r\002\004\023\003\003\024\ra\005\005\bq\005\005\0079AAj!\021Q$)!4\t\017\r\n\t\r1\001\002N\"9\001&!1A\002\0055\007b\002\027\002B\002\007\021Q\032\005\t\003w\013\031\003\"\001\002^V!\021q\\At)!\t\t/!<\002p\006EH\003BAr\003S\004b!a\033\002L\005\025\bc\001\t\002h\0221!#a7C\002MAq\001OAn\001\b\tY\017\005\003;\005\006\025\bbB\022\002\\\002\007\021Q\035\005\bQ\005m\007\031AAs\021\035a\0231\034a\001\003KD1\"!>\002$\t\007I\021\001\003\002x\006yA-\0324bk2$xJ\0353fe&tw-\006\002\002zB9A$a?\002\000\n]\021bAA\005\t\031Q*\03191\t\t\005!q\002\t\007\005\007\021IA!\004\016\005\t\025!b\001B\004\r\005!Q.\031;i\023\021\021YA!\002\003\0179+X.\032:jGB\031\001Ca\004\005\027\tE!1CA\001\002\003\025\ta\005\002\004?\022\n\004\"\003B\013\003G\001\013\021BA}\003A!WMZ1vYR|%\017Z3sS:<\007\005\r\003\003\032\t\005\002C\002B\002\0057\021y\"\003\003\003\036\t\025!\001C(sI\026\024\030N\\4\021\007A\021\t\003B\006\003$\tM\021\021!A\001\006\003\031\"aA0%e!Q!qEA\022\003\003%IA!\013\002\027I,\027\r\032*fg>dg/\032\013\003\005W\001BA!\f\00385\021!q\006\006\005\005c\021\031$\001\003mC:<'B\001B\033\003\021Q\027M^1\n\t\te\"q\006\002\007\037\nTWm\031;\t\017\tu\0221\003a\001\037\005)a/\0317vK\"9!\021\t\001\005F\t\r\023\001\002;bW\026$2!\023B#\021\035\t\tBa\020A\002ICqA!\023\001\t\013\022Y%\001\003ee>\004HcA%\003N!9\021\021\003B$\001\004\021\006bBAb\001\021\005!\021\013\013\004\037\tM\003b\002B+\005\037\002\rAU\001\004S\022D\bb\002B-\001\021\005#1L\001\004[&tW\003\002B/\005S\"2a\004B0\021!\021\tGa\026A\004\t\r\024aA8sIB)!H!\032\003h%\031!Q\004#\021\007A\021I\007\002\005\003l\t]#\031\001B7\005\t!\026'\005\002\0201!9!\021\017\001\005B\tM\024aA7bqV!!Q\017B?)\ry!q\017\005\t\005C\022y\007q\001\003zA)!H!\032\003|A\031\001C! \005\021\t-$q\016b\001\005[B\001B!!\001\t\003\021!1Q\001\t[\006\004(+\0318hKV!!Q\021BG)\021\0219Ia&\025\t\t%%\021\023\t\0059\001\021Y\tE\002\021\005\033#qAa$\003\000\t\0071CA\001B\021!\021\031Ja A\004\tU\025\001B;ok6\004BA\017\"\003\f\"A!\021\024B@\001\004\021Y*\001\002g[B)Q\003^\b\003\f\"9!q\024\001\005\002\t\005\026!D2p]R\f\027N\\:UsB,G\rF\0023\005GCqA!*\003\036\002\007q\"A\001y\021\035\021I\013\001C!\005W\013\001bY8oi\006Lgn\035\013\004e\t5\006b\002BS\005O\003\r\001\007\005\b\005c\003AQ\tBZ\003\r\031X/\\\013\005\005k\023I\f\006\003\0038\nu\006c\001\t\003:\022A!1\030BX\005\004\021iGA\001C\021\035A$q\026a\002\005\003RA\017Ba\005oK1Aa\003E\021%\021)\r\001EC\002\023\005\023+\001\005iCND7i\0343f\021%\021I\r\001E\001B\003&!+A\005iCND7i\0343fA!9!Q\032\001\005B\t=\027AB3rk\006d7\017F\0023\005#DqAa5\003L\002\007\001$A\003pi\",'\017C\004\003X\002!\tE!7\002\021Q|7\013\036:j]\036$\"Aa7\021\t\tu'1\035\b\004+\t}\027b\001Bq\r\0051\001K]3eK\032LAA!:\003h\n11\013\036:j]\036T1A!9\007\001")
/*     */ public abstract class NumericRange<T> extends AbstractSeq<T> implements IndexedSeq<T>, Serializable {
/*     */   private final T start;
/*     */   
/*     */   private final T end;
/*     */   
/*     */   private final T step;
/*     */   
/*     */   private final boolean isInclusive;
/*     */   
/*     */   private final Integral<T> num;
/*     */   
/*     */   private int numRangeElements;
/*     */   
/*     */   private T last;
/*     */   
/*     */   private int hashCode;
/*     */   
/*     */   private volatile byte bitmap$0;
/*     */   
/*     */   public GenericCompanion<IndexedSeq> companion() {
/*  42 */     return IndexedSeq$class.companion(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toIndexedSeq() {
/*  42 */     return IndexedSeq$class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> seq() {
/*  42 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> thisCollection() {
/*  42 */     return IndexedSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toCollection(Object repr) {
/*  42 */     return IndexedSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/*  42 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  42 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public Seq<T> toSeq() {
/*  42 */     return Seq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParSeq<T>> parCombiner() {
/*  42 */     return Seq$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public T start() {
/*  43 */     return this.start;
/*     */   }
/*     */   
/*     */   public T end() {
/*  43 */     return this.end;
/*     */   }
/*     */   
/*     */   public T step() {
/*  43 */     return this.step;
/*     */   }
/*     */   
/*     */   public boolean isInclusive() {
/*  43 */     return this.isInclusive;
/*     */   }
/*     */   
/*     */   public NumericRange(Object start, Object end, Object step, boolean isInclusive, Integral<T> num) {
/*  43 */     Traversable$class.$init$(this);
/*  43 */     Iterable$class.$init$(this);
/*  43 */     Seq$class.$init$(this);
/*  43 */     IndexedSeqLike.class.$init$(this);
/*  43 */     IndexedSeq.class.$init$(this);
/*  43 */     IndexedSeq$class.$init$(this);
/*     */   }
/*     */   
/*     */   private int numRangeElements$lzycompute() {
/*  52 */     synchronized (this) {
/*  52 */       if ((byte)(this.bitmap$0 & 0x1) == 0) {
/*  52 */         this.numRangeElements = 
/*  53 */           NumericRange$.MODULE$.count(start(), end(), step(), isInclusive(), this.num);
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/NumericRange}} */
/*     */       return this.numRangeElements;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int numRangeElements() {
/*     */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? numRangeElements$lzycompute() : this.numRangeElements;
/*     */   }
/*     */   
/*     */   public int length() {
/*  55 */     return numRangeElements();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  56 */     return (length() == 0);
/*     */   }
/*     */   
/*     */   private Object last$lzycompute() {
/*  57 */     synchronized (this) {
/*  57 */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/*  57 */         this.last = 
/*  58 */           (length() == 0) ? (T)Nil$.MODULE$.last() : 
/*  59 */           locationAfterN(length() - 1);
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/NumericRange}} */
/*     */       return this.last;
/*     */     } 
/*     */   }
/*     */   
/*     */   public T last() {
/*     */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? (T)last$lzycompute() : this.last;
/*     */   }
/*     */   
/*     */   public NumericRange<T> by(Object newStep) {
/*  64 */     return copy(start(), end(), (T)newStep);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  71 */     int count = 0;
/*  72 */     Object current = start();
/*  73 */     while (count < length()) {
/*  74 */       f.apply(current);
/*  75 */       current = this.num.mkNumericOps(current).$plus(step());
/*  76 */       count++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int skipCount(Function1 p) {
/*  86 */     Object current = start();
/*  87 */     int counted = 0;
/*  89 */     while (counted < length() && BoxesRunTime.unboxToBoolean(p.apply(current))) {
/*  90 */       counted++;
/*  91 */       current = this.num.mkNumericOps(current).$plus(step());
/*     */     } 
/*  93 */     return counted;
/*     */   }
/*     */   
/*     */   private boolean isWithinBoundaries(Object elem) {
/*  97 */     return (!isEmpty() && ((
/*  98 */       this.num.mkOrderingOps(step()).$greater(this.num.zero()) && this.num.mkOrderingOps(start()).$less$eq(elem) && this.num.mkOrderingOps(elem).$less$eq(last())) || (
/*  99 */       this.num.mkOrderingOps(step()).$less(this.num.zero()) && this.num.mkOrderingOps(last()).$less$eq(elem) && this.num.mkOrderingOps(elem).$less$eq(start()))));
/*     */   }
/*     */   
/*     */   private T locationAfterN(int n) {
/* 103 */     return (T)this.num.mkNumericOps(start()).$plus(this.num.mkNumericOps(step()).$times(this.num.fromInt(n)));
/*     */   }
/*     */   
/*     */   private Exclusive<T> newEmptyRange(Object value) {
/* 109 */     return NumericRange$.MODULE$.apply((T)value, (T)value, step(), this.num);
/*     */   }
/*     */   
/*     */   public final NumericRange<T> take(int n) {
/* 112 */     return (n <= 0 || length() == 0) ? newEmptyRange(start()) : (
/* 113 */       (n >= length()) ? this : 
/* 114 */       new Inclusive<T>(start(), locationAfterN(n - 1), step(), this.num));
/*     */   }
/*     */   
/*     */   public final NumericRange<T> drop(int n) {
/* 118 */     return (n <= 0 || length() == 0) ? this : (
/* 119 */       (n >= length()) ? newEmptyRange(end()) : 
/* 120 */       copy(locationAfterN(n), end(), step()));
/*     */   }
/*     */   
/*     */   public T apply(int idx) {
/* 124 */     if (idx < 0 || idx >= length())
/* 124 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/* 124 */     return 
/* 125 */       locationAfterN(idx);
/*     */   }
/*     */   
/*     */   public <T1> T min(Ordering ord) {
/* 131 */     return (ord == NumericRange$.MODULE$.defaultOrdering().apply(this.num)) ? (
/* 132 */       (this.num.signum(step()) > 0) ? start() : 
/* 133 */       last()) : 
/* 134 */       (T)TraversableOnce.class.min((TraversableOnce)this, ord);
/*     */   }
/*     */   
/*     */   public <T1> T max(Ordering ord) {
/* 137 */     return (ord == NumericRange$.MODULE$.defaultOrdering().apply(this.num)) ? (
/* 138 */       (this.num.signum(step()) > 0) ? last() : 
/* 139 */       start()) : 
/* 140 */       (T)TraversableOnce.class.max((TraversableOnce)this, ord);
/*     */   }
/*     */   
/*     */   public <A> NumericRange<A> mapRange(Function1 fm, Integral unum) {
/* 165 */     return new NumericRange$$anon$1(this, fm, unum, this);
/*     */   }
/*     */   
/*     */   public class NumericRange$$anon$1 extends NumericRange<A> {
/*     */     private NumericRange<T> underlyingRange;
/*     */     
/*     */     public final Function1 fm$1;
/*     */     
/*     */     private final Integral unum$1;
/*     */     
/*     */     private final NumericRange self$1;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public NumericRange$$anon$1(NumericRange $outer, Function1 fm$1, Integral<A> unum$1, NumericRange self$1) {
/* 168 */       super((A)fm$1.apply($outer.start()), (A)fm$1.apply($outer.end()), (A)fm$1.apply($outer.step()), $outer.isInclusive(), unum$1);
/*     */     }
/*     */     
/*     */     public NumericRange<A> copy(Object start, Object end, Object step) {
/* 170 */       return isInclusive() ? NumericRange$.MODULE$.<A>inclusive((A)start, (A)end, (A)step, this.unum$1) : 
/* 171 */         NumericRange$.MODULE$.<A>apply((A)start, (A)end, (A)step, this.unum$1);
/*     */     }
/*     */     
/*     */     private NumericRange underlyingRange$lzycompute() {
/* 173 */       synchronized (this) {
/* 173 */         if (!this.bitmap$0) {
/* 173 */           this.underlyingRange = this.self$1;
/* 173 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/NumericRange$$anon$1}} */
/* 173 */         this.self$1 = null;
/* 173 */         return this.underlyingRange;
/*     */       } 
/*     */     }
/*     */     
/*     */     private NumericRange<T> underlyingRange() {
/* 173 */       return this.bitmap$0 ? this.underlyingRange : underlyingRange$lzycompute();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 174 */       underlyingRange().foreach((Function1<T, ?>)new NumericRange$$anon$1$$anonfun$foreach$1(this, ($anon$1)f));
/*     */     }
/*     */     
/*     */     public class NumericRange$$anon$1$$anonfun$foreach$1 extends AbstractFunction1<T, U> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       public final U apply(Object x) {
/* 174 */         return (U)this.f$1.apply(this.$outer.fm$1.apply(x));
/*     */       }
/*     */       
/*     */       public NumericRange$$anon$1$$anonfun$foreach$1(NumericRange$$anon$1 $outer, Function1 f$1) {}
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 175 */       return underlyingRange().isEmpty();
/*     */     }
/*     */     
/*     */     public A apply(int idx) {
/* 176 */       return (A)this.fm$1.apply(underlyingRange().apply(idx));
/*     */     }
/*     */     
/*     */     public boolean containsTyped(Object el) {
/* 177 */       return underlyingRange().exists((Function1)new NumericRange$$anon$1$$anonfun$containsTyped$1(this, ($anon$1)el));
/*     */     }
/*     */     
/*     */     public class NumericRange$$anon$1$$anonfun$containsTyped$1 extends AbstractFunction1<T, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object el$1;
/*     */       
/*     */       public final boolean apply(Object x) {
/* 177 */         Object object2 = this.el$1;
/*     */         Object object1;
/* 177 */         return (((object1 = this.$outer.fm$1.apply(x)) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*     */       }
/*     */       
/*     */       public NumericRange$$anon$1$$anonfun$containsTyped$1(NumericRange$$anon$1 $outer, Object el$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containsTyped(Object x) {
/* 183 */     if (isWithinBoundaries((T)x)) {
/* 183 */       Object object2 = this.num.zero();
/*     */       Object object1;
/* 183 */       if (((object1 = this.num.mkNumericOps(this.num.mkNumericOps(x).$minus(start())).$percent(step())) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*     */     } 
/* 183 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(Object x) {
/*     */     try {
/*     */     
/* 186 */     } catch (ClassCastException classCastException) {}
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public final <B> B sum(Numeric num) {
/* 191 */     return isEmpty() ? (B)this.num.fromInt(0) : (
/* 192 */       (numRangeElements() == 1) ? (B)head() : 
/* 193 */       (B)this.num.mkNumericOps(this.num.mkNumericOps(this.num.fromInt(numRangeElements())).$times(this.num.mkNumericOps(head()).$plus(last()))).$div(this.num.fromInt(2)));
/*     */   }
/*     */   
/*     */   private int hashCode$lzycompute() {
/* 196 */     synchronized (this) {
/* 196 */       if ((byte)(this.bitmap$0 & 0x4) == 0) {
/* 196 */         this.hashCode = IndexedSeqLike.class.hashCode(this);
/* 196 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/NumericRange}} */
/* 196 */       return this.hashCode;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 196 */     return ((byte)(this.bitmap$0 & 0x4) == 0) ? hashCode$lzycompute() : this.hashCode;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/collection/immutable/NumericRange
/*     */     //   4: ifeq -> 208
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/collection/immutable/NumericRange
/*     */     //   11: astore #4
/*     */     //   13: aload #4
/*     */     //   15: aload_0
/*     */     //   16: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   19: ifeq -> 202
/*     */     //   22: aload_0
/*     */     //   23: invokevirtual length : ()I
/*     */     //   26: aload #4
/*     */     //   28: invokevirtual length : ()I
/*     */     //   31: if_icmpne -> 202
/*     */     //   34: aload_0
/*     */     //   35: invokevirtual length : ()I
/*     */     //   38: iconst_0
/*     */     //   39: if_icmpeq -> 198
/*     */     //   42: aload_0
/*     */     //   43: invokevirtual start : ()Ljava/lang/Object;
/*     */     //   46: aload #4
/*     */     //   48: invokevirtual start : ()Ljava/lang/Object;
/*     */     //   51: astore_3
/*     */     //   52: dup
/*     */     //   53: astore_2
/*     */     //   54: aload_3
/*     */     //   55: if_acmpne -> 62
/*     */     //   58: iconst_1
/*     */     //   59: goto -> 111
/*     */     //   62: aload_2
/*     */     //   63: ifnonnull -> 70
/*     */     //   66: iconst_0
/*     */     //   67: goto -> 111
/*     */     //   70: aload_2
/*     */     //   71: instanceof java/lang/Number
/*     */     //   74: ifeq -> 88
/*     */     //   77: aload_2
/*     */     //   78: checkcast java/lang/Number
/*     */     //   81: aload_3
/*     */     //   82: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   85: goto -> 111
/*     */     //   88: aload_2
/*     */     //   89: instanceof java/lang/Character
/*     */     //   92: ifeq -> 106
/*     */     //   95: aload_2
/*     */     //   96: checkcast java/lang/Character
/*     */     //   99: aload_3
/*     */     //   100: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   103: goto -> 111
/*     */     //   106: aload_2
/*     */     //   107: aload_3
/*     */     //   108: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   111: ifeq -> 202
/*     */     //   114: aload_0
/*     */     //   115: invokevirtual last : ()Ljava/lang/Object;
/*     */     //   118: aload #4
/*     */     //   120: invokevirtual last : ()Ljava/lang/Object;
/*     */     //   123: astore #6
/*     */     //   125: dup
/*     */     //   126: astore #5
/*     */     //   128: aload #6
/*     */     //   130: if_acmpne -> 137
/*     */     //   133: iconst_1
/*     */     //   134: goto -> 195
/*     */     //   137: aload #5
/*     */     //   139: ifnonnull -> 146
/*     */     //   142: iconst_0
/*     */     //   143: goto -> 195
/*     */     //   146: aload #5
/*     */     //   148: instanceof java/lang/Number
/*     */     //   151: ifeq -> 167
/*     */     //   154: aload #5
/*     */     //   156: checkcast java/lang/Number
/*     */     //   159: aload #6
/*     */     //   161: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   164: goto -> 195
/*     */     //   167: aload #5
/*     */     //   169: instanceof java/lang/Character
/*     */     //   172: ifeq -> 188
/*     */     //   175: aload #5
/*     */     //   177: checkcast java/lang/Character
/*     */     //   180: aload #6
/*     */     //   182: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   185: goto -> 195
/*     */     //   188: aload #5
/*     */     //   190: aload #6
/*     */     //   192: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   195: ifeq -> 202
/*     */     //   198: iconst_1
/*     */     //   199: goto -> 203
/*     */     //   202: iconst_0
/*     */     //   203: istore #7
/*     */     //   205: goto -> 215
/*     */     //   208: aload_0
/*     */     //   209: aload_1
/*     */     //   210: invokestatic equals : (Lscala/collection/GenSeqLike;Ljava/lang/Object;)Z
/*     */     //   213: istore #7
/*     */     //   215: iload #7
/*     */     //   217: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #198	-> 0
/*     */     //   #197	-> 0
/*     */     //   #199	-> 13
/*     */     //   #200	-> 34
/*     */     //   #201	-> 42
/*     */     //   #199	-> 198
/*     */     //   #204	-> 208
/*     */     //   #197	-> 215
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	218	0	this	Lscala/collection/immutable/NumericRange;
/*     */     //   0	218	1	other	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 208 */     String endStr = (length() > Range$.MODULE$.MAX_PRINT()) ? ", ... )" : ")";
/* 209 */     return take(Range$.MODULE$.MAX_PRINT()).mkString("NumericRange(", ", ", endStr);
/*     */   }
/*     */   
/*     */   public static <T> Inclusive<T> inclusive(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return NumericRange$.MODULE$.inclusive(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public abstract NumericRange<T> copy(T paramT1, T paramT2, T paramT3);
/*     */   
/*     */   public static class Inclusive<T> extends NumericRange<T> {
/*     */     private final Integral<T> num;
/*     */     
/*     */     public Inclusive(Object start, Object end, Object step, Integral<T> num) {
/* 255 */       super(
/* 256 */           (T)start, (T)end, (T)step, true, num);
/*     */     }
/*     */     
/*     */     public Inclusive<T> copy(Object start, Object end, Object step) {
/* 258 */       return NumericRange$.MODULE$.inclusive((T)start, (T)end, (T)step, this.num);
/*     */     }
/*     */     
/*     */     public NumericRange.Exclusive<T> exclusive() {
/* 260 */       return NumericRange$.MODULE$.apply(start(), end(), step(), this.num);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exclusive<T> extends NumericRange<T> {
/*     */     private final Integral<T> num;
/*     */     
/*     */     public Exclusive(Object start, Object end, Object step, Integral<T> num) {
/* 263 */       super(
/* 264 */           (T)start, (T)end, (T)step, false, num);
/*     */     }
/*     */     
/*     */     public Exclusive<T> copy(Object start, Object end, Object step) {
/* 266 */       return NumericRange$.MODULE$.apply((T)start, (T)end, (T)step, this.num);
/*     */     }
/*     */     
/*     */     public NumericRange.Inclusive<T> inclusive() {
/* 268 */       return NumericRange$.MODULE$.inclusive(start(), end(), step(), this.num);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\NumericRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */