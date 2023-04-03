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
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tmhaB\001\003!\003\r\t!\003\002\021\023R,'/\0312mKN\003H.\033;uKJT!a\001\003\002\021A\f'/\0317mK2T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\013\024\r\001YqBH\021(!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\002\n\005I\021!!G!vO6,g\016^3e\023R,'/\0312mK&#XM]1u_J\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021\001V\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020E\002\021?MI!\001\t\002\003\021M\003H.\033;uKJ\004\"AI\023\016\003\rR!\001\n\003\002\017\035,g.\032:jG&\021ae\t\002\013'&<g.\0317mS:<\007C\001\022)\023\tI3EA\nEK2,w-\031;fINKwM\\1mY&tw\rC\003,\001\021\005A&\001\004%S:LG\017\n\013\002[A\021ABL\005\003_\031\021A!\0268ji\"9\021\007\001a\001\n\003\021\024AD:jO:\fG\016R3mK\036\fG/Z\013\002C!9A\007\001a\001\n\003)\024AE:jO:\fG\016R3mK\036\fG/Z0%KF$\"!\f\034\t\017]\032\024\021!a\001C\005\031\001\020J\031\t\re\002\001\025)\003\"\003=\031\030n\0328bY\022+G.Z4bi\026\004\003\"B\036\001\r\003a\024a\0013vaV\tQ\bE\002\021\001MAQa\020\001\007\002\001\013Qa\0359mSR,\022!\021\t\004\005*kdBA\"I\035\t!u)D\001F\025\t1\005\"\001\004=e>|GOP\005\002\017%\021\021JB\001\ba\006\0347.Y4f\023\tYEJA\002TKFT!!\023\004\t\0139\003A\021\001!\002'M\004H.\033;XSRD7+[4oC2d\027N\\4\t\013A\003A\021A)\002%MDw.\0367e'Bd\027\016\036$veRDWM]\013\003%r#2a\025,_!\taA+\003\002V\r\t9!i\\8mK\006t\007\"B,P\001\004A\026\001B2pY2\0042\001E-\\\023\tQ&AA\006QCJLE/\032:bE2,\007C\001\013]\t\025ivJ1\001\030\005\005\031\006\"B0P\001\004\001\027\001\0059be\006dG.\0327jg6dUM^3m!\ta\021-\003\002c\r\t\031\021J\034;\t\013\021\004a\021A3\002\023I,W.Y5oS:<W#\0011\t\013\035\004A\021\0035\002\027\t,\030\016\0343TiJLgn\032\013\003SB\004\"A[7\017\0051Y\027B\0017\007\003\031\001&/\0323fM&\021an\034\002\007'R\024\030N\\4\013\00514\001\"B9g\001\004\021\030aB2m_N,(/\032\t\005\031M,X&\003\002u\r\tIa)\0368di&|g.\r\t\005\031MLW\006\003\004x\001\021\005!\001_\001\021I\026\024WoZ%oM>\024X.\031;j_:,\022!\037\t\003u~l\021a\037\006\003yv\fA\001\\1oO*\ta0\001\003kCZ\f\027B\0018|\r\031\t\031\001\001\001\002\006\t)A+Y6f]N!\021\021A\006>\021)\tI!!\001\003\002\003\006I\001Y\001\006i\006\\WM\034\005\t\003\033\t\t\001\"\001\002\020\0051A(\0338jiz\"B!!\005\002\026A!\0211CA\001\033\005\001\001bBA\005\003\027\001\r\001\031\005\tI\006\005\001\031!C\001K\"Q\0211DA\001\001\004%\t!!\b\002\033I,W.Y5oS:<w\fJ3r)\ri\023q\004\005\to\005e\021\021!a\001A\"A\0211EA\001A\003&\001-\001\006sK6\f\027N\\5oO\002B\001\"a\n\002\002\021\005\021\021F\001\bQ\006\034h*\032=u+\005\031\006\002CA\027\003\003!\t!a\f\002\t9,\007\020\036\013\002'!11(!\001\005\002qBaaPA\001\t\003\001\005\"CA\034\003\003\001K\021CA\035\003\035!\030m[3TKF,B!a\017\002HQ!\021QHA,)\021\ty$!\024\021\r\005\005\0231IA#\033\005!\021BA&\005!\r!\022q\t\003\t\003\023\n)D1\001\002L\t\021\001+S\t\0031uB\001\"a\024\0026\001\007\021\021K\001\006i\006\\WM\035\t\t\031\005M\023Q\t1\002F%\031\021Q\013\004\003\023\031+hn\031;j_:\024\004\002CA-\003k\001\r!a\027\002\005M\f\b\003\002\"K\003\013B\001\"a\030\001\t\003!\021\021M\001\t]\026<H+Y6f]R!\021\021CA2\021\035\t)'!\030A\002\001\fQ!\0368uS2D\001\"!\033\001\t\003!\0211N\001\021]\026<8\013\\5dK&sG/\032:oC2,B!!\034\002rQ1\021qNA<\003w\0022\001FA9\t!\t\031(a\032C\002\005U$!A+\022\007a\t\t\002\003\005\002z\005\035\004\031AA8\003\tIG\017C\004\002~\005\035\004\031\0011\002\013\031\024x.\\\031\t\017\005\005\005\001\"\021\002\004\006!A/Y6f)\ri\024Q\021\005\b\003\017\013y\b1\001a\003\005q\007bBAF\001\021\005\023QR\001\006g2L7-\032\013\006{\005=\025\021\023\005\b\003{\nI\t1\001a\021\035\t\031*!#A\002\001\fa!\0368uS2\fdABAL\001\001\tIJ\001\004NCB\004X\rZ\013\005\0037\013\tkE\003\002\026.\ti\n\005\003\021\001\005}\005c\001\013\002\"\0221Q,!&C\002]A1\"!*\002\026\n\005\t\025!\003\002(\006\ta\rE\003\rgN\ty\n\003\005\002\016\005UE\021AAV)\021\ti+a,\021\r\005M\021QSAP\021!\t)+!+A\002\005\035\006\002CA\024\003+#\t!!\013\t\021\0055\022Q\023C\001\003k#\"!a(\t\r\021\f)\n\"\001f\021\035Y\024Q\023C\001\003w+\"!!(\t\017}\n)\n\"\001\002@V\021\021\021\031\t\005\005*\013i\nC\004\002F\002!\t%a2\002\0075\f\007/\006\003\002J\006=G\003BAf\003#\004b!a\005\002\026\0065\007c\001\013\002P\0221Q,a1C\002]A\001\"!*\002D\002\007\0211\033\t\006\031M\034\022Q\032\004\007\003/\004\001!!7\003\021\005\003\b/\0328eK\022,b!a7\002b\00658#BAk\027\005u\007\003\002\t\001\003?\0042\001FAq\t!\t\031(!6C\002\005\r\030CA\n\034\021-\t9/!6\003\006\004%\t\"!;\002\tQD\027\r^\013\003\003W\0042\001FAw\t!\tI%!6C\002\005=\030c\001\r\002^\"Y\0211_Ak\005\003\005\013\021BAv\003\025!\b.\031;!\021!\ti!!6\005\002\005]H\003BA}\003w\004\002\"a\005\002V\006}\0271\036\005\t\003O\f)\0201\001\002l\"Q\021q`Ak\001\004%\tB!\001\002\t\r,(O]\013\003\003;D!B!\002\002V\002\007I\021\003B\004\003!\031WO\035:`I\025\fHcA\027\003\n!IqGa\001\002\002\003\007\021Q\034\005\n\005\033\t)\016)Q\005\003;\fQaY;se\002B\001\"a\n\002V\022\005\021\021\006\005\t\003[\t)\016\"\001\003\024Q\021\021q\034\005\007I\006UG\021A3\t\021\te\021Q\033C\t\003S\tQBZ5sgRtuN\\#naRL\bbB\036\002V\022\005!\021\001\005\b\005UG\021\001B\020+\t\021\t\003\005\003C\025\006u\007b\002B\023\001\021\005!qE\001\022CB\004XM\0343QCJLE/\032:bE2,WC\002B\025\005_\021\031\004\006\003\003,\te\002\003CA\n\003+\024iC!\r\021\007Q\021y\003\002\005\002t\t\r\"\031AAr!\r!\"1\007\003\t\003\023\022\031C1\001\0036E\031\001Da\016\021\tA\001!Q\006\005\t\003O\024\031\0031\001\0032\0311!Q\b\001\001\005\021aAW5qa\026$W\003\002B!\005\033\032RAa\017\f\005\007\002B\001\005\001\003FA1ABa\022\024\005\027J1A!\023\007\005\031!V\017\0357feA\031AC!\024\005\ru\023YD1\001\030\021-\t9Oa\017\003\006\004%\tB!\025\026\005\tM\003#\002\t\003V\t-\023b\001B,\005\tY1+Z9Ta2LG\017^3s\021-\t\031Pa\017\003\002\003\006IAa\025\t\021\0055!1\bC\001\005;\"BAa\030\003bA1\0211\003B\036\005\027B\001\"a:\003\\\001\007!1\013\005\t\003O\021Y\004\"\001\002*!A\021Q\006B\036\t\003\0219\007\006\002\003F!1AMa\017\005\002\025Dqa\017B\036\t\003\021i'\006\002\003D!9qHa\017\005\002\tETC\001B:!\021\021%Ja\021\t\017\t]\004\001\"\001\003z\005I!0\0339QCJ\034V-]\013\005\005w\022\t\t\006\003\003~\t\r\005CBA\n\005w\021y\bE\002\025\005\003#a!\030B;\005\0049\002\002CAt\005k\002\rA!\"\021\013A\021)Fa \007\r\t%\005\001\001BF\005%Q\026\016\0359fI\006cG.\006\004\003\016\nU%\021T\n\006\005\017[!q\022\t\005!\001\021\t\nE\004\r\005\017\022\031Ja&\021\007Q\021)\n\002\005\002t\t\035%\031AAr!\r!\"\021\024\003\007;\n\035%\031A\f\t\027\005\035(q\021BC\002\023E!QT\013\003\005?\003R\001\005B+\005/C1\"a=\003\b\n\005\t\025!\003\003 \"Y!Q\025BD\005\013\007I\021\003BT\003!!\b.[:fY\026lWC\001BJ\021-\021YKa\"\003\002\003\006IAa%\002\023QD\027n]3mK6\004\003b\003BX\005\017\023)\031!C\t\005c\013\001\002\0365bi\026dW-\\\013\003\005/C1B!.\003\b\n\005\t\025!\003\003\030\006IA\017[1uK2,W\016\t\005\t\003\033\0219\t\"\001\003:RA!1\030B_\005\023\t\r\005\005\002\024\t\035%1\023BL\021!\t9Oa.A\002\t}\005\002\003BS\005o\003\rAa%\t\021\t=&q\027a\001\005/C\001\"a\n\003\b\022\005\021\021\006\005\t\003[\0219\t\"\001\003HR\021!\021\023\005\007I\n\035E\021A3\t\017m\0229\t\"\001\003NV\021!q\022\005\b\t\035E\021\001Bi+\t\021\031\016\005\003C\025\n=\005b\002Bl\001\021\005!\021\\\001\ru&\004\030\t\0347QCJ\034V-]\013\t\0057\024iO!9\003fRA!Q\034Bx\005g\0249\020\005\005\002\024\t\035%q\034Br!\r!\"\021\035\003\t\003g\022)N1\001\002dB\031AC!:\005\021\t\035(Q\033b\001\005S\024\021AU\t\004\005W\\\002c\001\013\003n\0221QL!6C\002]A\001\"a:\003V\002\007!\021\037\t\006!\tU#1\036\005\t\005k\024)\0161\001\003`\006AA\017[5t\0132,W\016\003\005\003z\nU\007\031\001Br\003!!\b.\031;FY\026l\007")
/*     */ public interface IterableSplitter<T> extends AugmentedIterableIterator<T>, Splitter<T>, Signalling, DelegatedSignalling {
/*     */   Signalling signalDelegate();
/*     */   
/*     */   @TraitSetter
/*     */   void signalDelegate_$eq(Signalling paramSignalling);
/*     */   
/*     */   IterableSplitter<T> dup();
/*     */   
/*     */   Seq<IterableSplitter<T>> split();
/*     */   
/*     */   Seq<IterableSplitter<T>> splitWithSignalling();
/*     */   
/*     */   <S> boolean shouldSplitFurther(ParIterable<S> paramParIterable, int paramInt);
/*     */   
/*     */   int remaining();
/*     */   
/*     */   String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> paramFunction1);
/*     */   
/*     */   String debugInformation();
/*     */   
/*     */   Taken newTaken(int paramInt);
/*     */   
/*     */   <U extends Taken> U newSliceInternal(U paramU, int paramInt);
/*     */   
/*     */   IterableSplitter<T> take(int paramInt);
/*     */   
/*     */   IterableSplitter<T> slice(int paramInt1, int paramInt2);
/*     */   
/*     */   <S> Mapped<S> map(Function1<T, S> paramFunction1);
/*     */   
/*     */   <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI paramPI);
/*     */   
/*     */   <S> Zipped<S> zipParSeq(SeqSplitter<S> paramSeqSplitter);
/*     */   
/*     */   <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> paramSeqSplitter, U paramU, R paramR);
/*     */   
/*     */   public class IterableSplitter$$anonfun$splitWithSignalling$1 extends AbstractFunction1<IterableSplitter<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(IterableSplitter x$1) {
/* 398 */       x$1.signalDelegate_$eq(this.$outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public IterableSplitter$$anonfun$splitWithSignalling$1(IterableSplitter $outer) {}
/*     */   }
/*     */   
/*     */   public class IterableSplitter$$anonfun$buildString$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef output$1;
/*     */     
/*     */     public final void apply(String s) {
/* 427 */       IterableSplitter$class.appendln$1(this.$outer, s, this.output$1);
/*     */     }
/*     */     
/*     */     public IterableSplitter$$anonfun$buildString$1(IterableSplitter $outer, ObjectRef output$1) {}
/*     */   }
/*     */   
/*     */   public class Taken implements IterableSplitter<T> {
/*     */     private final int taken;
/*     */     
/*     */     private int remaining;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/* 438 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 438 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> splitWithSignalling() {
/* 438 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 438 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 438 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 438 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public Taken newTaken(int until) {
/* 438 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends Taken> U newSliceInternal(Taken it, int from1) {
/* 438 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> take(int n) {
/* 438 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> slice(int from1, int until1) {
/* 438 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Mapped<S> map(Function1 f) {
/* 438 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 438 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 438 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 438 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 438 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 438 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 438 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 438 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 438 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 438 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 438 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 438 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 438 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 438 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 438 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 438 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> T min(Ordering ord) {
/* 438 */       return (T)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> T max(Ordering ord) {
/* 438 */       return (T)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 438 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 438 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 438 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 438 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 438 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 438 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 438 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 438 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 438 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<T> seq() {
/* 438 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 438 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 438 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 438 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<T> drop(int n) {
/* 438 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 438 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 438 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<T> filter(Function1 p) {
/* 438 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 438 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> withFilter(Function1 p) {
/* 438 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> filterNot(Function1 p) {
/* 438 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 438 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 438 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 438 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> takeWhile(Function1 p) {
/* 438 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/* 438 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/* 438 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> dropWhile(Function1 p) {
/* 438 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/* 438 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 438 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/* 438 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 438 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 438 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 438 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 438 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 438 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> find(Function1 p) {
/* 438 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 438 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 438 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<T> buffered() {
/* 438 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/* 438 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/* 438 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 438 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/* 438 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 438 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 438 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<T> toTraversable() {
/* 438 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<T> toIterator() {
/* 438 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<T> toStream() {
/* 438 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 438 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 438 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<T> reversed() {
/* 438 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 438 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 438 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 438 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 438 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 438 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 438 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 438 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 438 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 438 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 438 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 438 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 438 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 438 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> T maxBy(Function1 f, Ordering cmp) {
/* 438 */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> T minBy(Function1 f, Ordering cmp) {
/* 438 */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 438 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 438 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 438 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 438 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<T> toList() {
/* 438 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<T> toIterable() {
/* 438 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<T> toSeq() {
/* 438 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<T> toIndexedSeq() {
/* 438 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 438 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 438 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<T> toVector() {
/* 438 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 438 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 438 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 438 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 438 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 438 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 438 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 438 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 438 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 438 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Taken(IterableSplitter $outer, int taken) {
/* 438 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 438 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 438 */       Iterator.class.$init$(this);
/* 438 */       RemainsIterator$class.$init$(this);
/* 438 */       AugmentedIterableIterator$class.$init$(this);
/* 438 */       DelegatedSignalling.class.$init$(this);
/* 438 */       IterableSplitter$class.$init$(this);
/* 439 */       Predef$ predef$ = Predef$.MODULE$;
/* 439 */       this.remaining = RichInt$.MODULE$.min$extension(taken, $outer.remaining());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 439 */       return this.remaining;
/*     */     }
/*     */     
/*     */     public void remaining_$eq(int x$1) {
/* 439 */       this.remaining = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 440 */       return (remaining() > 0);
/*     */     }
/*     */     
/*     */     public T next() {
/* 441 */       remaining_$eq(remaining() - 1);
/* 441 */       return (T)scala$collection$parallel$IterableSplitter$Taken$$$outer().next();
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> dup() {
/* 442 */       return scala$collection$parallel$IterableSplitter$Taken$$$outer().dup().take(this.taken);
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> split() {
/* 443 */       return takeSeq(scala$collection$parallel$IterableSplitter$Taken$$$outer().split(), (Function2<IterableSplitter<T>, Object, IterableSplitter<T>>)new IterableSplitter$Taken$$anonfun$split$1(this));
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Taken$$anonfun$split$1 extends AbstractFunction2<IterableSplitter<T>, Object, IterableSplitter<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IterableSplitter<T> apply(IterableSplitter<T> p, int n) {
/* 443 */         return p.take(n);
/*     */       }
/*     */       
/*     */       public IterableSplitter$Taken$$anonfun$split$1(IterableSplitter.Taken $outer) {}
/*     */     }
/*     */     
/*     */     public <PI extends IterableSplitter<T>> Seq<PI> takeSeq(Seq sq, Function2 taker) {
/* 445 */       Seq sizes = (Seq)sq.scanLeft(BoxesRunTime.boxToInteger(0), (Function2)new IterableSplitter$Taken$$anonfun$2(this), Seq$.MODULE$.canBuildFrom());
/* 446 */       Seq shortened = (Seq)((TraversableLike)sq.zip((GenIterable)((IterableLike)sizes.init()).zip((GenIterable)sizes.tail(), Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter((Function1)new IterableSplitter$Taken$$anonfun$3(this)).map((Function1)new IterableSplitter$Taken$$anonfun$4(this, (Taken)taker), Seq$.MODULE$.canBuildFrom());
/* 448 */       return (Seq<PI>)shortened.filter((Function1)new IterableSplitter$Taken$$anonfun$takeSeq$1(this));
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Taken$$anonfun$2 extends AbstractFunction2<Object, PI, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$2, IterableSplitter x$3) {
/*     */         return x$2 + x$3.remaining();
/*     */       }
/*     */       
/*     */       public IterableSplitter$Taken$$anonfun$2(IterableSplitter.Taken $outer) {}
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Taken$$anonfun$3 extends AbstractFunction1<Tuple2<PI, Tuple2<Object, Object>>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/*     */         if (check$ifrefutable$1 != null && check$ifrefutable$1._2() != null) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public IterableSplitter$Taken$$anonfun$3(IterableSplitter.Taken $outer) {}
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Taken$$anonfun$4 extends AbstractFunction1<Tuple2<PI, Tuple2<Object, Object>>, PI> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function2 taker$1;
/*     */       
/*     */       public final PI apply(Tuple2 x$4) {
/*     */         if (x$4 != null && x$4._2() != null)
/*     */           return (((Tuple2)x$4._2())._2$mcI$sp() < this.$outer.remaining()) ? (PI)x$4._1() : (PI)this.taker$1.apply(x$4._1(), BoxesRunTime.boxToInteger(this.$outer.remaining() - ((Tuple2)x$4._2())._1$mcI$sp())); 
/*     */         throw new MatchError(x$4);
/*     */       }
/*     */       
/*     */       public IterableSplitter$Taken$$anonfun$4(IterableSplitter.Taken $outer, Function2 taker$1) {}
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Taken$$anonfun$takeSeq$1 extends AbstractFunction1<PI, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(IterableSplitter x$5) {
/* 448 */         return (x$5.remaining() > 0);
/*     */       }
/*     */       
/*     */       public IterableSplitter$Taken$$anonfun$takeSeq$1(IterableSplitter.Taken $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Mapped<S> implements IterableSplitter<S> {
/*     */     public final Function1<T, S> scala$collection$parallel$IterableSplitter$Mapped$$f;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/* 466 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 466 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<S>> splitWithSignalling() {
/* 466 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 466 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 466 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 466 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<S>.Taken newTaken(int until) {
/* 466 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<S>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 466 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<S> take(int n) {
/* 466 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<S> slice(int from1, int until1) {
/* 466 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> Mapped<S> map(Function1 f) {
/* 466 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<S>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 466 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<S>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 466 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<S>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 466 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 466 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 466 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 466 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 466 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 466 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 466 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 466 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 466 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 466 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 466 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 466 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 466 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> S min(Ordering ord) {
/* 466 */       return (S)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> S max(Ordering ord) {
/* 466 */       return (S)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 466 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 466 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 466 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 466 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 466 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 466 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 466 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 466 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 466 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<S> seq() {
/* 466 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 466 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 466 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 466 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<S> drop(int n) {
/* 466 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 466 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 466 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<S> filter(Function1 p) {
/* 466 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 466 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<S> withFilter(Function1 p) {
/* 466 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<S> filterNot(Function1 p) {
/* 466 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 466 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 466 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 466 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<S> takeWhile(Function1 p) {
/* 466 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<S>, Iterator<S>> partition(Function1 p) {
/* 466 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<S>, Iterator<S>> span(Function1 p) {
/* 466 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<S> dropWhile(Function1 p) {
/* 466 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<S, B>> zip(Iterator that) {
/* 466 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 466 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<S, Object>> zipWithIndex() {
/* 466 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 466 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 466 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 466 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 466 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 466 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<S> find(Function1 p) {
/* 466 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 466 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 466 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<S> buffered() {
/* 466 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<S>.GroupedIterator<B> grouped(int size) {
/* 466 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<S>.GroupedIterator<B> sliding(int size, int step) {
/* 466 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 466 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<S>, Iterator<S>> duplicate() {
/* 466 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 466 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 466 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<S> toTraversable() {
/* 466 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<S> toIterator() {
/* 466 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<S> toStream() {
/* 466 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 466 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 466 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<S> reversed() {
/* 466 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 466 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 466 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 466 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 466 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 466 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 466 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 466 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 466 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 466 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 466 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 466 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 466 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 466 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> S maxBy(Function1 f, Ordering cmp) {
/* 466 */       return (S)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> S minBy(Function1 f, Ordering cmp) {
/* 466 */       return (S)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 466 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 466 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 466 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 466 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<S> toList() {
/* 466 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<S> toIterable() {
/* 466 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<S> toSeq() {
/* 466 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<S> toIndexedSeq() {
/* 466 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 466 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 466 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<S> toVector() {
/* 466 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 466 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 466 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 466 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 466 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 466 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 466 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 466 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 466 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 466 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Mapped(IterableSplitter $outer, Function1<T, S> f) {
/* 466 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 466 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 466 */       Iterator.class.$init$(this);
/* 466 */       RemainsIterator$class.$init$(this);
/* 466 */       AugmentedIterableIterator$class.$init$(this);
/* 466 */       DelegatedSignalling.class.$init$(this);
/* 466 */       IterableSplitter$class.$init$(this);
/* 467 */       signalDelegate_$eq($outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 468 */       return scala$collection$parallel$IterableSplitter$Mapped$$$outer().hasNext();
/*     */     }
/*     */     
/*     */     public S next() {
/* 469 */       return (S)this.scala$collection$parallel$IterableSplitter$Mapped$$f.apply(scala$collection$parallel$IterableSplitter$Mapped$$$outer().next());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 470 */       return scala$collection$parallel$IterableSplitter$Mapped$$$outer().remaining();
/*     */     }
/*     */     
/*     */     public IterableSplitter<S> dup() {
/* 471 */       return scala$collection$parallel$IterableSplitter$Mapped$$$outer().dup().map(this.scala$collection$parallel$IterableSplitter$Mapped$$f);
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<S>> split() {
/* 472 */       return (Seq<IterableSplitter<S>>)scala$collection$parallel$IterableSplitter$Mapped$$$outer().split().map((Function1)new IterableSplitter$Mapped$$anonfun$split$2(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Mapped$$anonfun$split$2 extends AbstractFunction1<IterableSplitter<T>, Mapped<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IterableSplitter<T>.Mapped<S> apply(IterableSplitter x$6) {
/* 472 */         return x$6.map(this.$outer.scala$collection$parallel$IterableSplitter$Mapped$$f);
/*     */       }
/*     */       
/*     */       public IterableSplitter$Mapped$$anonfun$split$2(IterableSplitter.Mapped $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Appended<U, PI extends IterableSplitter<U>> implements IterableSplitter<U> {
/*     */     private final PI that;
/*     */     
/*     */     private IterableSplitter<U> curr;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/* 477 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 477 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<U>> splitWithSignalling() {
/* 477 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 477 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 477 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 477 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<U>.Taken newTaken(int until) {
/* 477 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<U>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 477 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<U> take(int n) {
/* 477 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<U> slice(int from1, int until1) {
/* 477 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<U>.Mapped<S> map(Function1 f) {
/* 477 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 477 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<U>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 477 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<U>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 477 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 477 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 477 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 477 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 477 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 477 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 477 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 477 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 477 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 477 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 477 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 477 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 477 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> U min(Ordering ord) {
/* 477 */       return (U)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> U max(Ordering ord) {
/* 477 */       return (U)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 477 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 477 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 477 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 477 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 477 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 477 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 477 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 477 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 477 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> seq() {
/* 477 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 477 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 477 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 477 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> drop(int n) {
/* 477 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 477 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 477 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<U> filter(Function1 p) {
/* 477 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 477 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> withFilter(Function1 p) {
/* 477 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> filterNot(Function1 p) {
/* 477 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 477 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 477 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 477 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<U> takeWhile(Function1 p) {
/* 477 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> partition(Function1 p) {
/* 477 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> span(Function1 p) {
/* 477 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<U> dropWhile(Function1 p) {
/* 477 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<U, B>> zip(Iterator that) {
/* 477 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 477 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, Object>> zipWithIndex() {
/* 477 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 477 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 477 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 477 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 477 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 477 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<U> find(Function1 p) {
/* 477 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 477 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 477 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<U> buffered() {
/* 477 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<U>.GroupedIterator<B> grouped(int size) {
/* 477 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<U>.GroupedIterator<B> sliding(int size, int step) {
/* 477 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 477 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<U>, Iterator<U>> duplicate() {
/* 477 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 477 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 477 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<U> toTraversable() {
/* 477 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<U> toIterator() {
/* 477 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<U> toStream() {
/* 477 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 477 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 477 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<U> reversed() {
/* 477 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 477 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 477 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 477 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 477 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 477 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 477 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 477 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 477 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 477 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 477 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 477 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 477 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 477 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> U maxBy(Function1 f, Ordering cmp) {
/* 477 */       return (U)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> U minBy(Function1 f, Ordering cmp) {
/* 477 */       return (U)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 477 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 477 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 477 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 477 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<U> toList() {
/* 477 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<U> toIterable() {
/* 477 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<U> toSeq() {
/* 477 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<U> toIndexedSeq() {
/* 477 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 477 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 477 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<U> toVector() {
/* 477 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 477 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 477 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 477 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 477 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 477 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 477 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 477 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 477 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 477 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public PI that() {
/* 477 */       return this.that;
/*     */     }
/*     */     
/*     */     public Appended(IterableSplitter<U> $outer, IterableSplitter that) {
/* 477 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 477 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 477 */       Iterator.class.$init$(this);
/* 477 */       RemainsIterator$class.$init$(this);
/* 477 */       AugmentedIterableIterator$class.$init$(this);
/* 477 */       DelegatedSignalling.class.$init$(this);
/* 477 */       IterableSplitter$class.$init$(this);
/* 478 */       signalDelegate_$eq($outer.signalDelegate());
/* 479 */       this.curr = $outer;
/*     */     }
/*     */     
/*     */     public IterableSplitter<U> curr() {
/* 479 */       return this.curr;
/*     */     }
/*     */     
/*     */     public void curr_$eq(IterableSplitter<U> x$1) {
/* 479 */       this.curr = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 481 */       curr_$eq((IterableSplitter<U>)that());
/* 482 */       return curr().hasNext() ? true : ((curr() == scala$collection$parallel$IterableSplitter$Appended$$$outer()) ? curr().hasNext() : false);
/*     */     }
/*     */     
/*     */     public U next() {
/* 485 */       hasNext();
/* 486 */       return (curr() == scala$collection$parallel$IterableSplitter$Appended$$$outer()) ? (U)curr().next() : 
/* 487 */         (U)curr().next();
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 488 */       return (curr() == scala$collection$parallel$IterableSplitter$Appended$$$outer()) ? (curr().remaining() + that().remaining()) : curr().remaining();
/*     */     }
/*     */     
/*     */     public boolean firstNonEmpty() {
/* 489 */       return (curr() == scala$collection$parallel$IterableSplitter$Appended$$$outer() && curr().hasNext());
/*     */     }
/*     */     
/*     */     public IterableSplitter<U> dup() {
/* 490 */       return scala$collection$parallel$IterableSplitter$Appended$$$outer().dup().appendParIterable(that());
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<U>> split() {
/* 491 */       (new IterableSplitter[2])[0] = curr();
/* 491 */       (new IterableSplitter[2])[1] = (IterableSplitter)that();
/* 491 */       return firstNonEmpty() ? (Seq<IterableSplitter<U>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[2])) : curr().split();
/*     */     }
/*     */   }
/*     */   
/*     */   public class Zipped<S> implements IterableSplitter<Tuple2<T, S>> {
/*     */     private final SeqSplitter<S> that;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/* 496 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 496 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<T, S>>> splitWithSignalling() {
/* 496 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 496 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 496 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 496 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<T, S>>.Taken newTaken(int until) {
/* 496 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<Tuple2<T, S>>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 496 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<T, S>> take(int n) {
/* 496 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<T, S>> slice(int from1, int until1) {
/* 496 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<Tuple2<T, S>>.Mapped<S> map(Function1 f) {
/* 496 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<Tuple2<T, S>>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 496 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> Zipped<S> zipParSeq(SeqSplitter that) {
/* 496 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<Tuple2<T, S>>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 496 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 496 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 496 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 496 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 496 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 496 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 496 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 496 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 496 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 496 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 496 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 496 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 496 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<T, S> min(Ordering ord) {
/* 496 */       return (Tuple2<T, S>)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<T, S> max(Ordering ord) {
/* 496 */       return (Tuple2<T, S>)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 496 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 496 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 496 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 496 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 496 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 496 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 496 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 496 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 496 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> seq() {
/* 496 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 496 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 496 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 496 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> drop(int n) {
/* 496 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 496 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 496 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> filter(Function1 p) {
/* 496 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 496 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> withFilter(Function1 p) {
/* 496 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> filterNot(Function1 p) {
/* 496 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 496 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 496 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 496 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> takeWhile(Function1 p) {
/* 496 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> partition(Function1 p) {
/* 496 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> span(Function1 p) {
/* 496 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> dropWhile(Function1 p) {
/* 496 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Tuple2<T, S>, B>> zip(Iterator that) {
/* 496 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 496 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Tuple2<T, S>, Object>> zipWithIndex() {
/* 496 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 496 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 496 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 496 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 496 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 496 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<T, S>> find(Function1 p) {
/* 496 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 496 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 496 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Tuple2<T, S>> buffered() {
/* 496 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, S>>.GroupedIterator<B> grouped(int size) {
/* 496 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, S>>.GroupedIterator<B> sliding(int size, int step) {
/* 496 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 496 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> duplicate() {
/* 496 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 496 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 496 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Tuple2<T, S>> toTraversable() {
/* 496 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, S>> toIterator() {
/* 496 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<T, S>> toStream() {
/* 496 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 496 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 496 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<Tuple2<T, S>> reversed() {
/* 496 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 496 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 496 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 496 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 496 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 496 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 496 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 496 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 496 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 496 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 496 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 496 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 496 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 496 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<T, S> maxBy(Function1 f, Ordering cmp) {
/* 496 */       return (Tuple2<T, S>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<T, S> minBy(Function1 f, Ordering cmp) {
/* 496 */       return (Tuple2<T, S>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 496 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 496 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 496 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 496 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Tuple2<T, S>> toList() {
/* 496 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Tuple2<T, S>> toIterable() {
/* 496 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<T, S>> toSeq() {
/* 496 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Tuple2<T, S>> toIndexedSeq() {
/* 496 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 496 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 496 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Tuple2<T, S>> toVector() {
/* 496 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 496 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 496 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 496 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 496 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 496 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 496 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 496 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 496 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 496 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> that() {
/* 496 */       return this.that;
/*     */     }
/*     */     
/*     */     public Zipped(IterableSplitter $outer, SeqSplitter<S> that) {
/* 496 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 496 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 496 */       Iterator.class.$init$(this);
/* 496 */       RemainsIterator$class.$init$(this);
/* 496 */       AugmentedIterableIterator$class.$init$(this);
/* 496 */       DelegatedSignalling.class.$init$(this);
/* 496 */       IterableSplitter$class.$init$(this);
/* 497 */       signalDelegate_$eq($outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 498 */       return (scala$collection$parallel$IterableSplitter$Zipped$$$outer().hasNext() && that().hasNext());
/*     */     }
/*     */     
/*     */     public Tuple2<T, S> next() {
/* 499 */       return new Tuple2(scala$collection$parallel$IterableSplitter$Zipped$$$outer().next(), that().next());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 500 */       int i = scala$collection$parallel$IterableSplitter$Zipped$$$outer().remaining();
/* 500 */       Predef$ predef$ = Predef$.MODULE$;
/* 500 */       return RichInt$.MODULE$.min$extension(i, that().remaining());
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<T, S>> dup() {
/* 501 */       return scala$collection$parallel$IterableSplitter$Zipped$$$outer().dup().zipParSeq(that());
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<T, S>>> split() {
/* 503 */       Seq selfs = scala$collection$parallel$IterableSplitter$Zipped$$$outer().split();
/* 504 */       Seq sizes = (Seq)selfs.map((Function1)new IterableSplitter$Zipped$$anonfun$5(this), Seq$.MODULE$.canBuildFrom());
/* 505 */       Seq<SeqSplitter<S>> thats = that().psplit(sizes);
/* 506 */       return (Seq<IterableSplitter<Tuple2<T, S>>>)((TraversableLike)selfs.zip((GenIterable)thats, Seq$.MODULE$.canBuildFrom())).map((Function1)new IterableSplitter$Zipped$$anonfun$split$3(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Zipped$$anonfun$5 extends AbstractFunction1<IterableSplitter<T>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(IterableSplitter x$7) {
/*     */         return x$7.remaining();
/*     */       }
/*     */       
/*     */       public IterableSplitter$Zipped$$anonfun$5(IterableSplitter.Zipped $outer) {}
/*     */     }
/*     */     
/*     */     public class IterableSplitter$Zipped$$anonfun$split$3 extends AbstractFunction1<Tuple2<IterableSplitter<T>, SeqSplitter<S>>, Zipped<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IterableSplitter<T>.Zipped<S> apply(Tuple2 p) {
/* 506 */         return ((IterableSplitter)p._1()).zipParSeq((SeqSplitter)p._2());
/*     */       }
/*     */       
/*     */       public IterableSplitter$Zipped$$anonfun$split$3(IterableSplitter.Zipped $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class ZippedAll<U, S> implements IterableSplitter<Tuple2<U, S>> {
/*     */     private final SeqSplitter<S> that;
/*     */     
/*     */     private final U thiselem;
/*     */     
/*     */     private final S thatelem;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/* 512 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/* 512 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<U, S>>> splitWithSignalling() {
/* 512 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 512 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/* 512 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 512 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<U, S>>.Taken newTaken(int until) {
/* 512 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<Tuple2<U, S>>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 512 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<U, S>> take(int n) {
/* 512 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<U, S>> slice(int from1, int until1) {
/* 512 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<Tuple2<U, S>>.Mapped<S> map(Function1 f) {
/* 512 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<Tuple2<U, S>>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 512 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<Tuple2<U, S>>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 512 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 512 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/* 512 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/* 512 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/* 512 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/* 512 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/* 512 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/* 512 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/* 512 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 512 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 512 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 512 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 512 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 512 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<U, S> min(Ordering ord) {
/* 512 */       return (Tuple2<U, S>)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<U, S> max(Ordering ord) {
/* 512 */       return (Tuple2<U, S>)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/* 512 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 512 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 512 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 512 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 512 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 512 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 512 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 512 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/* 512 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> seq() {
/* 512 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 512 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 512 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 512 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> drop(int n) {
/* 512 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 512 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 512 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> filter(Function1 p) {
/* 512 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 512 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> withFilter(Function1 p) {
/* 512 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> filterNot(Function1 p) {
/* 512 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 512 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 512 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 512 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> takeWhile(Function1 p) {
/* 512 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> partition(Function1 p) {
/* 512 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> span(Function1 p) {
/* 512 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> dropWhile(Function1 p) {
/* 512 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Tuple2<U, S>, B>> zip(Iterator that) {
/* 512 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 512 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Tuple2<U, S>, Object>> zipWithIndex() {
/* 512 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 512 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 512 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 512 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 512 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 512 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<U, S>> find(Function1 p) {
/* 512 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 512 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 512 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Tuple2<U, S>> buffered() {
/* 512 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<U, S>>.GroupedIterator<B> grouped(int size) {
/* 512 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<U, S>>.GroupedIterator<B> sliding(int size, int step) {
/* 512 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 512 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> duplicate() {
/* 512 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 512 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 512 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Tuple2<U, S>> toTraversable() {
/* 512 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<U, S>> toIterator() {
/* 512 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<U, S>> toStream() {
/* 512 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 512 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 512 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<Tuple2<U, S>> reversed() {
/* 512 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 512 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 512 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 512 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 512 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 512 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 512 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 512 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 512 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 512 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 512 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 512 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 512 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 512 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<U, S> maxBy(Function1 f, Ordering cmp) {
/* 512 */       return (Tuple2<U, S>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<U, S> minBy(Function1 f, Ordering cmp) {
/* 512 */       return (Tuple2<U, S>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 512 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 512 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 512 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 512 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Tuple2<U, S>> toList() {
/* 512 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Tuple2<U, S>> toIterable() {
/* 512 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<U, S>> toSeq() {
/* 512 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Tuple2<U, S>> toIndexedSeq() {
/* 512 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 512 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 512 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Tuple2<U, S>> toVector() {
/* 512 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 512 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 512 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 512 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 512 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 512 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 512 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 512 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 512 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 512 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public SeqSplitter<S> that() {
/* 512 */       return this.that;
/*     */     }
/*     */     
/*     */     public U thiselem() {
/* 512 */       return this.thiselem;
/*     */     }
/*     */     
/*     */     public S thatelem() {
/* 512 */       return this.thatelem;
/*     */     }
/*     */     
/*     */     public ZippedAll(IterableSplitter $outer, SeqSplitter<S> that, Object thiselem, Object thatelem) {
/* 512 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 512 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 512 */       Iterator.class.$init$(this);
/* 512 */       RemainsIterator$class.$init$(this);
/* 512 */       AugmentedIterableIterator$class.$init$(this);
/* 512 */       DelegatedSignalling.class.$init$(this);
/* 512 */       IterableSplitter$class.$init$(this);
/* 514 */       signalDelegate_$eq($outer.signalDelegate());
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 515 */       return (scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext() || that().hasNext());
/*     */     }
/*     */     
/*     */     public Tuple2<U, S> next() {
/* 516 */       return scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext() ? (
/* 517 */         that().hasNext() ? new Tuple2(scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), that().next()) : 
/* 518 */         new Tuple2(scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), thatelem())) : 
/* 519 */         new Tuple2(thiselem(), that().next());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 520 */       int i = scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining();
/* 520 */       Predef$ predef$ = Predef$.MODULE$;
/* 520 */       return RichInt$.MODULE$.max$extension(i, that().remaining());
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<U, S>> dup() {
/* 521 */       return scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().dup().zipAllParSeq(that(), thiselem(), thatelem());
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<U, S>>> split() {
/* 523 */       int selfrem = scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining();
/* 524 */       int thatrem = that().remaining();
/* 525 */       IterableSplitter thisit = (selfrem < thatrem) ? scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().appendParIterable(package$.MODULE$.repetition(thiselem(), thatrem - selfrem).splitter()) : scala$collection$parallel$IterableSplitter$ZippedAll$$$outer();
/* 526 */       SeqSplitter thatit = (selfrem > thatrem) ? that().appendParSeq(package$.MODULE$.repetition(thatelem(), selfrem - thatrem).splitter()) : that();
/* 527 */       IterableSplitter.Zipped<S> zipped = thisit.zipParSeq(thatit);
/* 528 */       return zipped.split();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\IterableSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */