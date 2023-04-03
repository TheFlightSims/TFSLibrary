/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.immutable.package$;
/*     */ import scala.collection.parallel.mutable.ParArray;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tufaB\001\003!\003\r\t!\003\002\f'\026\f8\013\0357jiR,'O\003\002\004\t\005A\001/\031:bY2,GN\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M)\001aC\b\037CA\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\007A\t2#D\001\003\023\t\021\"A\001\tJi\026\024\030M\0317f'Bd\027\016\036;feB\021A#\006\007\001\t\0311\002\001\"b\001/\t\tA+\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bc\001\t '%\021\001E\001\002\025\003V<W.\0328uK\022\034V-]%uKJ\fGo\034:\021\007A\0213#\003\002$\005\ty\001K]3dSN,7\013\0357jiR,'\017C\003&\001\021\005a%\001\004%S:LG\017\n\013\002OA\021A\002K\005\003S\031\021A!\0268ji\")1\006\001D\001Y\005\031A-\0369\026\0035\0022\001\005\001\024\021\025y\003A\"\0011\003\025\031\b\017\\5u+\005\t\004c\001\032;[9\0211\007\017\b\003i]j\021!\016\006\003m!\ta\001\020:p_Rt\024\"A\004\n\005e2\021a\0029bG.\fw-Z\005\003wq\0221aU3r\025\tId\001C\003?\001\031\005q(\001\004qgBd\027\016\036\013\003c\001CQ!Q\037A\002\t\013Qa]5{KN\0042\001D\"F\023\t!eA\001\006=e\026\004X-\031;fIz\002\"\001\004$\n\005\0353!aA%oi\")\021\n\001C!a\005\0312\017\0357ji^KG\017[*jO:\fG\016\\5oO\")1\n\001C\001\031\006!\002o\0359mSR<\026\016\0365TS\036t\027\r\0347j]\036$\"!M'\t\013\005S\005\031\001\"\t\013=\003a\021\001)\002\023I,W.Y5oS:<W#A#\007\tI\003\001a\025\002\006)\006\\WM\\\n\004#Rk\003CA+W\033\005\001\021B\001*\022\021!A\026K!A!\002\023)\025A\001;l\021\025Q\026\013\"\001\\\003\031a\024N\\5u}Q\021A,\030\t\003+FCQ\001W-A\002\025CQaK)\005B1BQaL)\005BABQAP)\005\002\005$\"!\r2\t\013\005\003\007\031\001\"\t\r\021\004A\021\t\003f\003!qWm\036+bW\026tGC\001/g\021\02597\r1\001F\003\025)h\016^5m\021\025I\007\001\"\021k\003\021!\030m[3\025\0055Z\007\"\0027i\001\004)\025!\0018\t\0139\004A\021I8\002\013Md\027nY3\025\0075\002(\017C\003r[\002\007Q)A\003ge>l\027\007C\003t[\002\007Q)\001\004v]RLG.\r\004\005k\002\001aO\001\004NCB\004X\rZ\013\003on\0342\001\036=~!\r)\026P_\005\003kF\001\"\001F>\005\013q$(\031A\f\003\003M\0032\001\005\001{\021%yHO!A!\002\023\t\t!A\001g!\025a\0211A\n{\023\r\t)A\002\002\n\rVt7\r^5p]FBaA\027;\005\002\005%A\003BA\006\003\033\0012!\026;{\021\035y\030q\001a\001\003\003Aaa\013;\005B\005EQ#A?\t\r=\"H\021IA\013+\t\t9\002E\0023uuDaA\020;\005\002\005mA\003BA\f\003;Aa!QA\r\001\004\021\005bBA\021\001\021\005\0231E\001\004[\006\004X\003BA\023\003W!B!a\n\002.A!Q\013^A\025!\r!\0221\006\003\007y\006}!\031A\f\t\017}\fy\0021\001\0020A1A\"a\001\024\003S1a!a\r\001\001\005U\"\001C!qa\026tG-\0323\026\r\005]\022qHA$'\031\t\t$!\017\002NA9Q+a\017\002>\005\025\023bAA\032#A\031A#a\020\005\021\005\005\023\021\007b\001\003\007\022\021!V\t\003'm\0012\001FA$\t!\tI%!\rC\002\005-#A\001)J#\rA\022Q\n\t\005!\001\ti\004C\007\002R\005E\"\021!Q\001\n\005\025\0231K\001\003SRLA!!\026\002<\005!A\017[1u\021\035Q\026\021\007C\001\0033\"B!a\027\002^A9Q+!\r\002>\005\025\003\002CA)\003/\002\r!!\022\t\017-\n\t\004\"\021\002bU\021\021Q\n\005\b_\005EB\021IA3+\t\t9\007\005\0033u\0055\003b\002 \0022\021\005\0211\016\013\005\003O\ni\007\003\004B\003S\002\rA\021\005\b\003c\002A\021AA:\0031\t\007\017]3oIB\013'oU3r+\031\t)(a\037\002\000Q!\021qOAC!\035)\026\021GA=\003{\0022\001FA>\t!\t\t%a\034C\002\005\r\003c\001\013\002\000\021A\021\021JA8\005\004\t\t)E\002\031\003\007\003B\001\005\001\002z!A\021QKA8\001\004\tiH\002\004\002\n\002\001\0211\022\002\0075&\004\b/\0323\026\t\0055\025QS\n\007\003\017\013y)a&\021\013U\013\t*a%\n\007\005%\025\003E\002\025\003+#a\001`AD\005\0049\002\003\002\t\001\0033\003b\001DAN'\005M\025bAAO\r\t1A+\0369mKJBQ\"!)\002\b\n\005\t\025!\003\002$\006\025\026A\001;j!\021\001\002!a%\n\t\005U\023\021\023\005\b5\006\035E\021AAU)\021\tY+!,\021\013U\0139)a%\t\021\005\005\026q\025a\001\003GCqaKAD\t\003\n\t,\006\002\002\030\"9q&a\"\005B\005UVCAA\\!\021\021$(a&\t\017y\n9\t\"\001\002<R!\021qWA_\021\035\ty,!/A\002\t\0131a\035>t\021\035\t\031\r\001C!\003\013\f\021B_5q!\006\0248+Z9\026\t\005\035\027Q\032\013\005\003\023\fy\rE\003V\003\017\013Y\rE\002\025\003\033$a\001`Aa\005\0049\002\002CA+\003\003\004\r!!5\021\tA\001\0211\032\004\007\003+\004\001!a6\003\023iK\007\017]3e\0032dWCBAm\003C\f)o\005\004\002T\006m\027q\035\t\b+\006u\027q\\Ar\023\r\t).\005\t\004)\005\005H\001CA!\003'\024\r!a\021\021\007Q\t)\017\002\004}\003'\024\ra\006\t\005!\001\tI\017E\004\r\0037\013y.a9\t\033\005\005\0261\033B\001B\003%\021Q^Ax!\021\001\002!a9\n\t\005U\023Q\034\005\016\003g\f\031N!A!\002\023\ty.!>\002\013QD\027n]3\n\t\005]\030Q\\\001\ti\"L7/\0327f[\"i\0211`Aj\005\003\005\013\021BAr\003{\fQ\001\0365bi\026LA!a@\002^\006AA\017[1uK2,W\016C\004[\003'$\tAa\001\025\021\t\025!q\001B\005\005\027\001r!VAj\003?\f\031\017\003\005\002\"\n\005\001\031AAw\021!\t\031P!\001A\002\005}\007\002CA~\005\003\001\r!a9\t\017-\n\031\016\"\021\003\020U\021\021q\035\005\t\005'\t\031\016\"\003\003\026\0059\001/\031;dQ\026lWC\001B\f!\035a\0211\024B\r\003[\004B\001\005\001\002`\"9q&a5\005B\tuQC\001B\020!\021\021$(a:\t\017y\n\031\016\"\001\003$Q!!q\004B\023\021\031\t%\021\005a\001\005\"9!\021\006\001\005B\t-\022\001\004>ja\006cG\016U1s'\026\fX\003\003B\027\005\021\031Da\016\025\021\t=\"\021\tB#\005\023\002r!VAj\005c\021)\004E\002\025\005g!\001\"!\021\003(\t\007\0211\t\t\004)\t]B\001\003B\035\005O\021\rAa\017\003\003I\0132A!\020\034!\r!\"q\b\003\007y\n\035\"\031A\f\t\021\005U#q\005a\001\005\007\002B\001\005\001\003>!A!q\tB\024\001\004\021\t$\001\005uQ&\034X\t\\3n\021!\021YEa\nA\002\tU\022\001\003;iCR,E.Z7\t\r\t=\003\001\"\001-\003\035\021XM^3sg\0264aAa\025\001\001\tU#a\002)bi\016DW\rZ\013\005\005/\022ifE\003\003R-\021I\006\005\003\021\001\tm\003c\001\013\003^\021A\021\021\tB)\005\004\t\031\005\003\006\003b\tE#\021!Q\001\n\025\013AA\032:p[\"Y!Q\rB)\005\003\005\013\021\002B-\003\025\001\030\r^2i\021)\021IG!\025\003\002\003\006I!R\001\te\026\004H.Y2fI\"9!L!\025\005\002\t5D\003\003B8\005c\022\031H!\036\021\013U\023\tFa\027\t\017\t\005$1\016a\001\013\"A!Q\rB6\001\004\021I\006C\004\003j\t-\004\031A#\t\023\te$\021\013Q\001\n\tm\024\001\002;sS>\004rA! \0022\tmS\006E\004.\003c\021YF!\027\t\021\t\005%\021\013C\001\005\007\013q\001[1t\035\026DH/\006\002\003\006B\031ABa\"\n\007\t%eAA\004C_>dW-\0318\t\021\t5%\021\013C\001\005\037\013AA\\3yiR\021!1\f\005\007\037\nEC\021\001)\t\017-\022\t\006\"\001\003\026V\021!q\023\t\006[\tE#1\f\005\b_\tEC\021\001BN+\t\021i\n\005\0033u\te\003b\002 \003R\021\005!\021\025\013\005\005;\023\031\013\003\004B\005?\003\rA\021\005\b\005O\003A\021\001BU\003-\001\030\r^2i!\006\0248+Z9\026\t\t-&\021\027\013\t\005[\023\031L!.\003<B)QK!\025\0030B\031AC!-\005\021\005\005#Q\025b\001\003\007BqA!\031\003&\002\007Q\t\003\005\0038\n\025\006\031\001B]\003)\001\030\r^2i\0132,Wn\035\t\005!\001\021y\013C\004\003j\t\025\006\031A#")
/*     */ public interface SeqSplitter<T> extends IterableSplitter<T>, AugmentedSeqIterator<T>, PreciseSplitter<T> {
/*     */   SeqSplitter<T> dup();
/*     */   
/*     */   Seq<SeqSplitter<T>> split();
/*     */   
/*     */   Seq<SeqSplitter<T>> psplit(Seq<Object> paramSeq);
/*     */   
/*     */   Seq<SeqSplitter<T>> splitWithSignalling();
/*     */   
/*     */   Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> paramSeq);
/*     */   
/*     */   int remaining();
/*     */   
/*     */   Taken newTaken(int paramInt);
/*     */   
/*     */   SeqSplitter<T> take(int paramInt);
/*     */   
/*     */   SeqSplitter<T> slice(int paramInt1, int paramInt2);
/*     */   
/*     */   <S> Mapped<S> map(Function1<T, S> paramFunction1);
/*     */   
/*     */   <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI paramPI);
/*     */   
/*     */   <S> Zipped<S> zipParSeq(SeqSplitter<S> paramSeqSplitter);
/*     */   
/*     */   <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> paramSeqSplitter, U paramU, R paramR);
/*     */   
/*     */   SeqSplitter<T> reverse();
/*     */   
/*     */   <U> Patched<U> patchParSeq(int paramInt1, SeqSplitter<U> paramSeqSplitter, int paramInt2);
/*     */   
/*     */   public class SeqSplitter$$anonfun$splitWithSignalling$2 extends AbstractFunction1<SeqSplitter<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(SeqSplitter x$8) {
/* 553 */       x$8.signalDelegate_$eq(this.$outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public SeqSplitter$$anonfun$splitWithSignalling$2(SeqSplitter $outer) {}
/*     */   }
/*     */   
/*     */   public class SeqSplitter$$anonfun$psplitWithSignalling$1 extends AbstractFunction1<SeqSplitter<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(SeqSplitter x$9) {
/* 559 */       x$9.signalDelegate_$eq(this.$outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public SeqSplitter$$anonfun$psplitWithSignalling$1(SeqSplitter $outer) {}
/*     */   }
/*     */   
/*     */   public class Taken extends IterableSplitter<T>.Taken implements SeqSplitter<T> {
/*     */     public Seq<SeqSplitter<T>> splitWithSignalling() {
/* 574 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<T>> psplitWithSignalling(Seq sizes) {
/* 574 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public Taken newTaken(int until) {
/* 574 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> take(int n) {
/* 574 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> slice(int from1, int until1) {
/* 574 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<T>.Mapped<S> map(Function1 f) {
/* 574 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<T>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 574 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 574 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 574 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> reverse() {
/* 574 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<T>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 574 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 574 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 574 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 574 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 574 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 574 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 574 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 574 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Taken(SeqSplitter<T> $outer, int tk) {
/* 574 */       super($outer, tk);
/* 574 */       AugmentedSeqIterator$class.$init$(this);
/* 574 */       SeqSplitter$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> dup() {
/* 575 */       return (SeqSplitter<T>)super.dup();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<T>> split() {
/* 576 */       return (Seq)super.split();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<T>> psplit(Seq sizes) {
/* 577 */       return takeSeq(scala$collection$parallel$SeqSplitter$Taken$$$outer().psplit(sizes), (Function2<SeqSplitter<T>, Object, SeqSplitter<T>>)new SeqSplitter$Taken$$anonfun$psplit$1(this));
/*     */     }
/*     */     
/*     */     public class SeqSplitter$Taken$$anonfun$psplit$1 extends AbstractFunction2<SeqSplitter<T>, Object, SeqSplitter<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SeqSplitter<T> apply(SeqSplitter<T> p, int n) {
/* 577 */         return p.take(n);
/*     */       }
/*     */       
/*     */       public SeqSplitter$Taken$$anonfun$psplit$1(SeqSplitter.Taken $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Mapped<S> extends IterableSplitter<T>.Mapped<S> implements SeqSplitter<S> {
/*     */     public final Function1<T, S> scala$collection$parallel$SeqSplitter$Mapped$$f;
/*     */     
/*     */     public Seq<SeqSplitter<S>> splitWithSignalling() {
/* 583 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<S>> psplitWithSignalling(Seq sizes) {
/* 583 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S>.Taken newTaken(int until) {
/* 583 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> take(int n) {
/* 583 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> slice(int from1, int until1) {
/* 583 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> Mapped<S> map(Function1 f) {
/* 583 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<S>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 583 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<S>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 583 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<S>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 583 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> reverse() {
/* 583 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<S>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 583 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 583 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 583 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 583 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 583 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 583 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 583 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 583 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Mapped(SeqSplitter<T> $outer, Function1<T, S> f) {
/* 583 */       super($outer, f);
/* 583 */       AugmentedSeqIterator$class.$init$(this);
/* 583 */       SeqSplitter$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> dup() {
/* 584 */       return (SeqSplitter<S>)super.dup();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<S>> split() {
/* 585 */       return (Seq)super.split();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<S>> psplit(Seq sizes) {
/* 586 */       return (Seq<SeqSplitter<S>>)scala$collection$parallel$SeqSplitter$Mapped$$$outer().psplit(sizes).map((Function1)new SeqSplitter$Mapped$$anonfun$psplit$2(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class SeqSplitter$Mapped$$anonfun$psplit$2 extends AbstractFunction1<SeqSplitter<T>, Mapped<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SeqSplitter<T>.Mapped<S> apply(SeqSplitter x$10) {
/* 586 */         return x$10.map(this.$outer.scala$collection$parallel$SeqSplitter$Mapped$$f);
/*     */       }
/*     */       
/*     */       public SeqSplitter$Mapped$$anonfun$psplit$2(SeqSplitter.Mapped $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Appended<U, PI extends SeqSplitter<U>> extends IterableSplitter<T>.Appended<U, PI> implements SeqSplitter<U> {
/*     */     public Seq<SeqSplitter<U>> splitWithSignalling() {
/* 591 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> psplitWithSignalling(Seq sizes) {
/* 591 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U>.Taken newTaken(int until) {
/* 591 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> take(int n) {
/* 591 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> slice(int from1, int until1) {
/* 591 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<U>.Mapped<S> map(Function1 f) {
/* 591 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 591 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<U>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 591 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<U>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 591 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> reverse() {
/* 591 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<U>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 591 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 591 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 591 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 591 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 591 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 591 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 591 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 591 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Appended(SeqSplitter<T> $outer, SeqSplitter it) {
/* 591 */       super($outer, (PI)it);
/* 591 */       AugmentedSeqIterator$class.$init$(this);
/* 591 */       SeqSplitter$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> dup() {
/* 592 */       return (SeqSplitter<U>)super.dup();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> split() {
/* 593 */       return (Seq)super.split();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> psplit(Seq sizes) {
/* 594 */       if (firstNonEmpty()) {
/* 595 */         int selfrem = scala$collection$parallel$SeqSplitter$Appended$$$outer().remaining();
/* 598 */         BooleanRef appendMiddle = new BooleanRef(false);
/* 599 */         Seq szcum = (Seq)sizes.scanLeft(BoxesRunTime.boxToInteger(0), (Function2)new SeqSplitter$Appended$$anonfun$1(this), Seq$.MODULE$.canBuildFrom());
/* 600 */         Seq splitsizes = (Seq)((TraversableLike)sizes.zip((GenIterable)((IterableLike)szcum.init()).zip((GenIterable)szcum.tail(), Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).flatMap((Function1)new SeqSplitter$Appended$$anonfun$6(this, selfrem, (Appended<U, PI>)appendMiddle), Seq$.MODULE$.canBuildFrom());
/* 607 */         Tuple2 tuple2 = ((TraversableLike)splitsizes.zip((GenIterable)szcum.init(), Seq$.MODULE$.canBuildFrom())).span((Function1)new $anonfun$7(this, selfrem));
/* 607 */         if (tuple2 != null) {
/* 607 */           Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 607 */           Seq selfszfrom = (Seq)tuple21._1(), thatszfrom = (Seq)tuple21._2();
/* 608 */           Tuple2 tuple22 = new Tuple2(selfszfrom.map((Function1)new $anonfun$8(this), Seq$.MODULE$.canBuildFrom()), thatszfrom.map((Function1)new $anonfun$9(this), Seq$.MODULE$.canBuildFrom()));
/* 608 */           if (tuple22 != null) {
/* 608 */             Tuple2 tuple23 = new Tuple2(tuple22._1(), tuple22._2());
/* 608 */             Seq selfsizes = (Seq)tuple23._1(), thatsizes = (Seq)tuple23._2();
/* 611 */             Seq selfs = scala$collection$parallel$SeqSplitter$Appended$$$outer().psplit(selfsizes);
/* 612 */             Seq thats = ((SeqSplitter)that()).psplit(thatsizes);
/* 615 */             (new Appended[1])[0] = ((SeqSplitter)selfs.last()).appendParSeq(thats.head());
/*     */           } else {
/*     */             throw new MatchError(tuple22);
/*     */           } 
/*     */         } else {
/*     */           throw new MatchError(tuple2);
/*     */         } 
/*     */       } else {
/*     */       
/*     */       } 
/* 617 */       return ((SeqSplitter<U>)curr()).psplit(sizes);
/*     */     }
/*     */     
/*     */     public class SeqSplitter$Appended$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$11, int x$12) {
/*     */         return x$11 + x$12;
/*     */       }
/*     */       
/*     */       public int apply$mcIII$sp(int x$11, int x$12) {
/*     */         return x$11 + x$12;
/*     */       }
/*     */       
/*     */       public SeqSplitter$Appended$$anonfun$1(SeqSplitter.Appended $outer) {}
/*     */     }
/*     */     
/*     */     public class SeqSplitter$Appended$$anonfun$6 extends AbstractFunction1<Tuple2<Object, Tuple2<Object, Object>>, Seq<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int selfrem$1;
/*     */       
/*     */       private final BooleanRef appendMiddle$1;
/*     */       
/*     */       public SeqSplitter$Appended$$anonfun$6(SeqSplitter.Appended $outer, int selfrem$1, BooleanRef appendMiddle$1) {}
/*     */       
/*     */       public final Seq<Object> apply(Tuple2 t) {
/*     */         if (t != null && t._2() != null) {
/*     */           Tuple3 tuple3 = new Tuple3(BoxesRunTime.boxToInteger(t._1$mcI$sp()), BoxesRunTime.boxToInteger(((Tuple2)t._2())._1$mcI$sp()), BoxesRunTime.boxToInteger(((Tuple2)t._2())._2$mcI$sp()));
/*     */           int sz = BoxesRunTime.unboxToInt(tuple3._1()), from = BoxesRunTime.unboxToInt(tuple3._2()), until = BoxesRunTime.unboxToInt(tuple3._3());
/*     */           this.appendMiddle$1.elem = true;
/*     */           return (from < this.selfrem$1 && until > this.selfrem$1) ? (Seq<Object>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { this.selfrem$1 - from, until - this.selfrem$1 })) : (Seq<Object>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { sz }));
/*     */         } 
/*     */         throw new MatchError(t);
/*     */       }
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction1<Tuple2<Object, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int selfrem$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x$14) {
/*     */         return (x$14._2$mcI$sp() < this.selfrem$1);
/*     */       }
/*     */       
/*     */       public $anonfun$7(SeqSplitter.Appended $outer, int selfrem$1) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$8 extends AbstractFunction1<Tuple2<Object, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(Tuple2 x$16) {
/*     */         return x$16._1$mcI$sp();
/*     */       }
/*     */       
/*     */       public $anonfun$8(SeqSplitter.Appended $outer) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$9 extends AbstractFunction1<Tuple2<Object, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(Tuple2 x$17) {
/*     */         return x$17._1$mcI$sp();
/*     */       }
/*     */       
/*     */       public $anonfun$9(SeqSplitter.Appended $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Zipped<S> extends IterableSplitter<T>.Zipped<S> implements SeqSplitter<Tuple2<T, S>> {
/*     */     public Seq<SeqSplitter<Tuple2<T, S>>> splitWithSignalling() {
/* 622 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<T, S>>> psplitWithSignalling(Seq sizes) {
/* 622 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<T, S>>.Taken newTaken(int until) {
/* 622 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<T, S>> take(int n) {
/* 622 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<T, S>> slice(int from1, int until1) {
/* 622 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<Tuple2<T, S>>.Mapped<S> map(Function1 f) {
/* 622 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<Tuple2<T, S>>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 622 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> Zipped<S> zipParSeq(SeqSplitter that) {
/* 622 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<Tuple2<T, S>>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 622 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<T, S>> reverse() {
/* 622 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<Tuple2<T, S>>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 622 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 622 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 622 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 622 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 622 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 622 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 622 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 622 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Zipped(SeqSplitter<T> $outer, SeqSplitter<S> ti) {
/* 622 */       super($outer, ti);
/* 622 */       AugmentedSeqIterator$class.$init$(this);
/* 622 */       SeqSplitter$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<T, S>> dup() {
/* 623 */       return (SeqSplitter<Tuple2<T, S>>)super.dup();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<T, S>>> split() {
/* 624 */       return (Seq)super.split();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<T, S>>> psplit(Seq szs) {
/* 625 */       return (Seq<SeqSplitter<Tuple2<T, S>>>)((TraversableLike)scala$collection$parallel$SeqSplitter$Zipped$$$outer().psplit(szs).zip((GenIterable)that().psplit(szs), Seq$.MODULE$.canBuildFrom())).map((Function1)new SeqSplitter$Zipped$$anonfun$psplit$3(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class SeqSplitter$Zipped$$anonfun$psplit$3 extends AbstractFunction1<Tuple2<SeqSplitter<T>, SeqSplitter<S>>, Zipped<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SeqSplitter<T>.Zipped<S> apply(Tuple2 p) {
/* 625 */         return ((SeqSplitter)p._1()).zipParSeq((SeqSplitter)p._2());
/*     */       }
/*     */       
/*     */       public SeqSplitter$Zipped$$anonfun$psplit$3(SeqSplitter.Zipped $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class ZippedAll<U, S> extends IterableSplitter<T>.ZippedAll<U, S> implements SeqSplitter<Tuple2<U, S>> {
/*     */     public Seq<SeqSplitter<Tuple2<U, S>>> splitWithSignalling() {
/* 630 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<U, S>>> psplitWithSignalling(Seq sizes) {
/* 630 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<U, S>>.Taken newTaken(int until) {
/* 630 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<U, S>> take(int n) {
/* 630 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<U, S>> slice(int from1, int until1) {
/* 630 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<Tuple2<U, S>>.Mapped<S> map(Function1 f) {
/* 630 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<Tuple2<U, S>>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 630 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<Tuple2<U, S>>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 630 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 630 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<U, S>> reverse() {
/* 630 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<Tuple2<U, S>>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 630 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 630 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 630 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 630 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 630 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 630 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 630 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 630 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public ZippedAll(SeqSplitter<T> $outer, SeqSplitter<S> ti, Object thise, Object thate) {
/* 630 */       super($outer, ti, (U)thise, (S)thate);
/* 630 */       AugmentedSeqIterator$class.$init$(this);
/* 630 */       SeqSplitter$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Tuple2<U, S>> dup() {
/* 631 */       return (SeqSplitter<Tuple2<U, S>>)super.dup();
/*     */     }
/*     */     
/*     */     private Tuple2<SeqSplitter<U>, SeqSplitter<S>> patchem() {
/* 633 */       int selfrem = scala$collection$parallel$SeqSplitter$ZippedAll$$$outer().remaining();
/* 634 */       int thatrem = that().remaining();
/* 635 */       SeqSplitter thisit = (selfrem < thatrem) ? scala$collection$parallel$SeqSplitter$ZippedAll$$$outer().appendParSeq(package$.MODULE$.repetition(thiselem(), thatrem - selfrem).splitter()) : scala$collection$parallel$SeqSplitter$ZippedAll$$$outer();
/* 636 */       SeqSplitter thatit = (selfrem > thatrem) ? that().appendParSeq(package$.MODULE$.repetition(thatelem(), selfrem - thatrem).splitter()) : that();
/* 637 */       return new Tuple2(thisit, thatit);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<U, S>>> split() {
/* 640 */       Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple2 = patchem();
/* 640 */       if (tuple2 != null) {
/* 640 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 640 */         SeqSplitter thisit = (SeqSplitter)tuple21._1(), thatit = (SeqSplitter)tuple21._2();
/* 641 */         SeqSplitter.Zipped<S> zipped = thisit.zipParSeq(thatit);
/* 642 */         return zipped.split();
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Tuple2<U, S>>> psplit(Seq sizes) {
/* 645 */       Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple2 = patchem();
/* 645 */       if (tuple2 != null) {
/* 645 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 645 */         SeqSplitter thisit = (SeqSplitter)tuple21._1(), thatit = (SeqSplitter)tuple21._2();
/* 646 */         SeqSplitter.Zipped<S> zipped = thisit.zipParSeq(thatit);
/* 647 */         return zipped.psplit(sizes);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqSplitter$$anon$1 extends ParArray<T>.ParArrayIterator {
/*     */     public SeqSplitter$$anon$1(SeqSplitter $outer, ParArray pa$1) {
/* 655 */       super(pa$1, pa$1.ParArrayIterator().$lessinit$greater$default$1(), pa$1.ParArrayIterator().$lessinit$greater$default$2(), pa$1.ParArrayIterator().$lessinit$greater$default$3());
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> reverse() {
/* 656 */       return this.$outer;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Patched<U> implements SeqSplitter<U> {
/*     */     private final int from;
/*     */     
/*     */     private final SeqSplitter<U> patch;
/*     */     
/*     */     private final int replaced;
/*     */     
/*     */     private final SeqSplitter<U>.Appended<U, SeqSplitter<T>> trio;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Seq<SeqSplitter<U>> splitWithSignalling() {
/* 660 */       return SeqSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> psplitWithSignalling(Seq sizes) {
/* 660 */       return SeqSplitter$class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U>.Taken newTaken(int until) {
/* 660 */       return SeqSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> take(int n) {
/* 660 */       return SeqSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> slice(int from1, int until1) {
/* 660 */       return SeqSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<U>.Mapped<S> map(Function1 f) {
/* 660 */       return SeqSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<U>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 660 */       return SeqSplitter$class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<U>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 660 */       return SeqSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<U>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 660 */       return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<U> reverse() {
/* 660 */       return SeqSplitter$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 660 */       return SeqSplitter$class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/* 660 */       return AugmentedSeqIterator$class.prefixLength(this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/* 660 */       return AugmentedSeqIterator$class.indexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/* 660 */       return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 660 */       return AugmentedSeqIterator$class.corresponds(this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 660 */       return AugmentedSeqIterator$class.reverse2combiner(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 660 */       return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 660 */       return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Signalling signalDelegate() {
/* 660 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 660 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 660 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 660 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 660 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<U>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 660 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<U>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 660 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 660 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 660 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 660 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 660 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 660 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 660 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 660 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 660 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 660 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 660 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 660 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 660 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> U min(Ordering ord) {
/* 660 */       return (U)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> U max(Ordering ord) {
/* 660 */       return (U)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 660 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 660 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 660 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 660 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 660 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 660 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 660 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 660 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 660 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> seq() {
/* 660 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 660 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 660 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 660 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> drop(int n) {
/* 660 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 660 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 660 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<U> filter(Function1 p) {
/* 660 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 660 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> withFilter(Function1 p) {
/* 660 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> filterNot(Function1 p) {
/* 660 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 660 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 660 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 660 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<U> takeWhile(Function1 p) {
/* 660 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> partition(Function1 p) {
/* 660 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> span(Function1 p) {
/* 660 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> dropWhile(Function1 p) {
/* 660 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<U, B>> zip(Iterator that) {
/* 660 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 660 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, Object>> zipWithIndex() {
/* 660 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 660 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 660 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 660 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 660 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 660 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<U> find(Function1 p) {
/* 660 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 660 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<U> buffered() {
/* 660 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<U>.GroupedIterator<B> grouped(int size) {
/* 660 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<U>.GroupedIterator<B> sliding(int size, int step) {
/* 660 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 660 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> duplicate() {
/* 660 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 660 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 660 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<U> toTraversable() {
/* 660 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> toIterator() {
/* 660 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<U> toStream() {
/* 660 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 660 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 660 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<U> reversed() {
/* 660 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 660 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 660 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 660 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 660 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 660 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 660 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 660 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 660 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 660 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> U maxBy(Function1 f, Ordering cmp) {
/* 660 */       return (U)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> U minBy(Function1 f, Ordering cmp) {
/* 660 */       return (U)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 660 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 660 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 660 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 660 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<U> toList() {
/* 660 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<U> toIterable() {
/* 660 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<U> toSeq() {
/* 660 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<U> toIndexedSeq() {
/* 660 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 660 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 660 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<U> toVector() {
/* 660 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 660 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 660 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 660 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 660 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 660 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 660 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 660 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 660 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 660 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Patched(SeqSplitter $outer, int from, SeqSplitter<U> patch, int replaced) {
/* 660 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 660 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 660 */       Iterator.class.$init$(this);
/* 660 */       RemainsIterator$class.$init$(this);
/* 660 */       AugmentedIterableIterator$class.$init$(this);
/* 660 */       DelegatedSignalling.class.$init$(this);
/* 660 */       IterableSplitter$class.$init$(this);
/* 660 */       AugmentedSeqIterator$class.$init$(this);
/* 660 */       SeqSplitter$class.$init$(this);
/* 661 */       signalDelegate_$eq($outer.signalDelegate());
/* 663 */       Seq pits = $outer.psplit((Seq)Predef$.MODULE$.wrapIntArray(new int[] { from, replaced, $outer.remaining() - from - replaced }));
/* 664 */       this.trio = ((SeqSplitter)pits.apply(0)).appendParSeq(patch).appendParSeq((SeqSplitter<T>)pits.apply(2));
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 666 */       return this.trio.hasNext();
/*     */     }
/*     */     
/*     */     public U next() {
/* 667 */       return this.trio.next();
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 668 */       return this.trio.remaining();
/*     */     }
/*     */     
/*     */     public Patched<U> dup() {
/* 669 */       return scala$collection$parallel$SeqSplitter$Patched$$$outer().dup().patchParSeq(this.from, this.patch, this.replaced);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> split() {
/* 670 */       return this.trio.split();
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<U>> psplit(Seq sizes) {
/* 671 */       return this.trio.psplit(sizes);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\SeqSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */