/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0012a!\001\002\002\002\0211!aC!cgR\024\030m\031;TKFT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b+\t9abE\002\001\021e\0012!\003\006\r\033\005\021\021BA\006\003\005A\t%m\035;sC\016$\030\n^3sC\ndW\r\005\002\016\0351\001AAB\b\001\t\013\007\021CA\001B\007\001\t\"A\005\f\021\005M!R\"\001\003\n\005U!!a\002(pi\"Lgn\032\t\003']I!\001\007\003\003\007\005s\027\020E\002\n51I!a\007\002\003\007M+\027\017C\003\036\001\021\005a$\001\004=S:LGO\020\013\002?A\031\021\002\001\007")
/*    */ public abstract class AbstractSeq<A> extends AbstractIterable<A> implements Seq<A> {
/*    */   public GenericCompanion<Seq> companion() {
/* 40 */     return Seq$class.companion(this);
/*    */   }
/*    */   
/*    */   public Seq<A> seq() {
/* 40 */     return Seq$class.seq(this);
/*    */   }
/*    */   
/*    */   public Seq<A> thisCollection() {
/* 40 */     return SeqLike$class.thisCollection(this);
/*    */   }
/*    */   
/*    */   public Seq<A> toCollection(Object repr) {
/* 40 */     return SeqLike$class.toCollection(this, repr);
/*    */   }
/*    */   
/*    */   public Combiner<A, ParSeq<A>> parCombiner() {
/* 40 */     return SeqLike$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public int lengthCompare(int len) {
/* 40 */     return SeqLike$class.lengthCompare(this, len);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 40 */     return SeqLike$class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 40 */     return SeqLike$class.size(this);
/*    */   }
/*    */   
/*    */   public int segmentLength(Function1 p, int from) {
/* 40 */     return SeqLike$class.segmentLength(this, p, from);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p, int from) {
/* 40 */     return SeqLike$class.indexWhere(this, p, from);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p, int end) {
/* 40 */     return SeqLike$class.lastIndexWhere(this, p, end);
/*    */   }
/*    */   
/*    */   public Iterator<Seq<A>> permutations() {
/* 40 */     return SeqLike$class.permutations(this);
/*    */   }
/*    */   
/*    */   public Iterator<Seq<A>> combinations(int n) {
/* 40 */     return SeqLike$class.combinations(this, n);
/*    */   }
/*    */   
/*    */   public Seq<A> reverse() {
/* 40 */     return (Seq<A>)SeqLike$class.reverse(this);
/*    */   }
/*    */   
/*    */   public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.reverseMap(this, f, bf);
/*    */   }
/*    */   
/*    */   public Iterator<A> reverseIterator() {
/* 40 */     return SeqLike$class.reverseIterator(this);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that, int offset) {
/* 40 */     return SeqLike$class.startsWith(this, that, offset);
/*    */   }
/*    */   
/*    */   public <B> boolean endsWith(GenSeq that) {
/* 40 */     return SeqLike$class.endsWith(this, that);
/*    */   }
/*    */   
/*    */   public <B> int indexOfSlice(GenSeq that) {
/* 40 */     return SeqLike$class.indexOfSlice(this, that);
/*    */   }
/*    */   
/*    */   public <B> int indexOfSlice(GenSeq that, int from) {
/* 40 */     return SeqLike$class.indexOfSlice(this, that, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOfSlice(GenSeq that) {
/* 40 */     return SeqLike$class.lastIndexOfSlice(this, that);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 40 */     return SeqLike$class.lastIndexOfSlice(this, that, end);
/*    */   }
/*    */   
/*    */   public <B> boolean containsSlice(GenSeq that) {
/* 40 */     return SeqLike$class.containsSlice(this, that);
/*    */   }
/*    */   
/*    */   public boolean contains(Object elem) {
/* 40 */     return SeqLike$class.contains(this, elem);
/*    */   }
/*    */   
/*    */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.union(this, that, bf);
/*    */   }
/*    */   
/*    */   public <B> Seq<A> diff(GenSeq that) {
/* 40 */     return (Seq<A>)SeqLike$class.diff(this, that);
/*    */   }
/*    */   
/*    */   public <B> Seq<A> intersect(GenSeq that) {
/* 40 */     return (Seq<A>)SeqLike$class.intersect(this, that);
/*    */   }
/*    */   
/*    */   public Seq<A> distinct() {
/* 40 */     return (Seq<A>)SeqLike$class.distinct(this);
/*    */   }
/*    */   
/*    */   public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.patch(this, from, patch, replaced, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.updated(this, index, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.$plus$colon(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.$colon$plus(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 40 */     return (That)SeqLike$class.padTo(this, len, elem, bf);
/*    */   }
/*    */   
/*    */   public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 40 */     return SeqLike$class.corresponds(this, that, p);
/*    */   }
/*    */   
/*    */   public Seq<A> sortWith(Function2 lt) {
/* 40 */     return (Seq<A>)SeqLike$class.sortWith(this, lt);
/*    */   }
/*    */   
/*    */   public <B> Seq<A> sortBy(Function1 f, Ordering ord) {
/* 40 */     return (Seq<A>)SeqLike$class.sortBy(this, f, ord);
/*    */   }
/*    */   
/*    */   public <B> Seq<A> sorted(Ordering ord) {
/* 40 */     return (Seq<A>)SeqLike$class.sorted(this, ord);
/*    */   }
/*    */   
/*    */   public Seq<A> toSeq() {
/* 40 */     return SeqLike$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public Range indices() {
/* 40 */     return SeqLike$class.indices(this);
/*    */   }
/*    */   
/*    */   public Object view() {
/* 40 */     return SeqLike$class.view(this);
/*    */   }
/*    */   
/*    */   public SeqView<A, Seq<A>> view(int from, int until) {
/* 40 */     return SeqLike$class.view(this, from, until);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 40 */     return SeqLike$class.toString(this);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(int idx) {
/* 40 */     return GenSeqLike$class.isDefinedAt(this, idx);
/*    */   }
/*    */   
/*    */   public int prefixLength(Function1 p) {
/* 40 */     return GenSeqLike$class.prefixLength(this, p);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p) {
/* 40 */     return GenSeqLike$class.indexWhere(this, p);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem) {
/* 40 */     return GenSeqLike$class.indexOf(this, elem);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem, int from) {
/* 40 */     return GenSeqLike$class.indexOf(this, elem, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem) {
/* 40 */     return GenSeqLike$class.lastIndexOf(this, elem);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem, int end) {
/* 40 */     return GenSeqLike$class.lastIndexOf(this, elem, end);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p) {
/* 40 */     return GenSeqLike$class.lastIndexWhere(this, p);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that) {
/* 40 */     return GenSeqLike$class.startsWith(this, that);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 40 */     return GenSeqLike$class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 40 */     return GenSeqLike$class.equals(this, that);
/*    */   }
/*    */   
/*    */   public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 40 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 40 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<Object, Option<A>> lift() {
/* 40 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 40 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<Object, Object> runWith(Function1 action) {
/* 40 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 40 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 40 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 40 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 40 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 40 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 40 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 40 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 40 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 40 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 40 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 40 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 40 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 40 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 40 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 40 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 40 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 40 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 40 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 40 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 40 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 40 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 40 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 40 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 40 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, A> compose(Function1 g) {
/* 40 */     return Function1.class.compose((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 40 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 40 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public AbstractSeq() {
/* 40 */     Function1.class.$init$((Function1)this);
/* 40 */     PartialFunction.class.$init$(this);
/* 40 */     GenSeqLike$class.$init$(this);
/* 40 */     GenSeq$class.$init$(this);
/* 40 */     SeqLike$class.$init$(this);
/* 40 */     Seq$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */