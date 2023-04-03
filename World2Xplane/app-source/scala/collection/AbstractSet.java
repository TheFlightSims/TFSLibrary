/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericSetTemplate;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParSet;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0012a!\001\002\002\002\0211!aC!cgR\024\030m\031;TKRT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b+\t9abE\002\001\021e\0012!\003\006\r\033\005\021\021BA\006\003\005A\t%m\035;sC\016$\030\n^3sC\ndW\r\005\002\016\0351\001A!B\b\001\005\004\t\"!A!\004\001E\021!C\006\t\003'Qi\021\001B\005\003+\021\021qAT8uQ&tw\r\005\002\024/%\021\001\004\002\002\004\003:L\bcA\005\033\031%\0211D\001\002\004'\026$\b\"B\017\001\t\003q\022A\002\037j]&$h\bF\001 !\rI\001\001\004")
/*    */ public abstract class AbstractSet<A> extends AbstractIterable<A> implements Set<A> {
/*    */   public GenericCompanion<Set> companion() {
/* 47 */     return Set$class.companion(this);
/*    */   }
/*    */   
/*    */   public Set<A> seq() {
/* 47 */     return Set$class.seq(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
/* 47 */     return TraversableLike$class.map(this, f, bf);
/*    */   }
/*    */   
/*    */   public Builder<A, Set<A>> newBuilder() {
/* 47 */     return SetLike$class.newBuilder(this);
/*    */   }
/*    */   
/*    */   public Combiner<A, ParSet<A>> parCombiner() {
/* 47 */     return SetLike$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public Seq<A> toSeq() {
/* 47 */     return SetLike$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public <A1> Buffer<A1> toBuffer() {
/* 47 */     return SetLike$class.toBuffer(this);
/*    */   }
/*    */   
/*    */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 47 */     return (That)SetLike$class.map(this, f, bf);
/*    */   }
/*    */   
/*    */   public Set<A> $plus(Object elem1, Object elem2, Seq elems) {
/* 47 */     return SetLike$class.$plus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Set<A> $plus$plus(GenTraversableOnce elems) {
/* 47 */     return SetLike$class.$plus$plus(this, elems);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 47 */     return SetLike$class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public Set<A> union(GenSet that) {
/* 47 */     return SetLike$class.union(this, that);
/*    */   }
/*    */   
/*    */   public Set<A> diff(GenSet that) {
/* 47 */     return SetLike$class.diff(this, that);
/*    */   }
/*    */   
/*    */   public Iterator<Set<A>> subsets(int len) {
/* 47 */     return SetLike$class.subsets(this, len);
/*    */   }
/*    */   
/*    */   public Iterator<Set<A>> subsets() {
/* 47 */     return SetLike$class.subsets(this);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 47 */     return SetLike$class.stringPrefix(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return SetLike$class.toString(this);
/*    */   }
/*    */   
/*    */   public Set<A> $minus(Object elem1, Object elem2, Seq elems) {
/* 47 */     return (Set<A>)Subtractable.class.$minus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Set<A> $minus$minus(GenTraversableOnce xs) {
/* 47 */     return (Set<A>)Subtractable.class.$minus$minus(this, xs);
/*    */   }
/*    */   
/*    */   public Set<A> empty() {
/* 47 */     return (Set<A>)GenericSetTemplate.class.empty(this);
/*    */   }
/*    */   
/*    */   public boolean apply(Object elem) {
/* 47 */     return GenSetLike$class.apply(this, elem);
/*    */   }
/*    */   
/*    */   public Set<A> intersect(GenSet that) {
/* 47 */     return (Set<A>)GenSetLike$class.intersect(this, that);
/*    */   }
/*    */   
/*    */   public Set<A> $amp(GenSet that) {
/* 47 */     return (Set<A>)GenSetLike$class.$amp(this, that);
/*    */   }
/*    */   
/*    */   public Set<A> $bar(GenSet that) {
/* 47 */     return (Set<A>)GenSetLike$class.$bar(this, that);
/*    */   }
/*    */   
/*    */   public Set<A> $amp$tilde(GenSet that) {
/* 47 */     return (Set<A>)GenSetLike$class.$amp$tilde(this, that);
/*    */   }
/*    */   
/*    */   public boolean subsetOf(GenSet that) {
/* 47 */     return GenSetLike$class.subsetOf(this, that);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 47 */     return GenSetLike$class.equals(this, that);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 47 */     return GenSetLike$class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 47 */     return Function1.class.apply$mcZD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 47 */     return Function1.class.apply$mcDD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 47 */     return Function1.class.apply$mcFD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 47 */     return Function1.class.apply$mcID$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 47 */     return Function1.class.apply$mcJD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 47 */     Function1.class.apply$mcVD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 47 */     return Function1.class.apply$mcZF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 47 */     return Function1.class.apply$mcDF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 47 */     return Function1.class.apply$mcFF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 47 */     return Function1.class.apply$mcIF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 47 */     return Function1.class.apply$mcJF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 47 */     Function1.class.apply$mcVF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 47 */     return Function1.class.apply$mcZI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 47 */     return Function1.class.apply$mcDI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 47 */     return Function1.class.apply$mcFI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 47 */     return Function1.class.apply$mcII$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 47 */     return Function1.class.apply$mcJI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 47 */     Function1.class.apply$mcVI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 47 */     return Function1.class.apply$mcZJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 47 */     return Function1.class.apply$mcDJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 47 */     return Function1.class.apply$mcFJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 47 */     return Function1.class.apply$mcIJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 47 */     return Function1.class.apply$mcJJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 47 */     Function1.class.apply$mcVJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose(Function1 g) {
/* 47 */     return Function1.class.compose(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 47 */     return Function1.class.compose$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, A> andThen(Function1 g) {
/* 47 */     return Function1.class.andThen(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 47 */     return Function1.class.andThen$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public AbstractSet() {
/* 47 */     Function1.class.$init$(this);
/* 47 */     GenSetLike$class.$init$(this);
/* 47 */     GenericSetTemplate.class.$init$(this);
/* 47 */     GenSet$class.$init$(this);
/* 47 */     Subtractable.class.$init$(this);
/* 47 */     SetLike$class.$init$(this);
/* 47 */     Set$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */