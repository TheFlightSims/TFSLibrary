/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0012a!\001\002\002\002\0211!\001E!cgR\024\030m\031;Ji\026\024\030M\0317f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\013\003\0179\0312\001\001\005\032!\rI!\002D\007\002\005%\0211B\001\002\024\003\n\034HO]1diR\023\030M^3sg\006\024G.\032\t\003\0339a\001\001\002\004\020\001\021\025\r!\005\002\002\003\016\001\021C\001\n\027!\t\031B#D\001\005\023\t)BAA\004O_RD\027N\\4\021\005M9\022B\001\r\005\005\r\te.\037\t\004\023ia\021BA\016\003\005!IE/\032:bE2,\007\"B\017\001\t\003q\022A\002\037j]&$h\bF\001 !\rI\001\001\004")
/*    */ public abstract class AbstractIterable<A> extends AbstractTraversable<A> implements Iterable<A> {
/*    */   public GenericCompanion<Iterable> companion() {
/* 54 */     return Iterable$class.companion(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> seq() {
/* 54 */     return Iterable$class.seq(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> thisCollection() {
/* 54 */     return IterableLike$class.thisCollection(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> toCollection(Object repr) {
/* 54 */     return IterableLike$class.toCollection(this, repr);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 54 */     IterableLike$class.foreach(this, f);
/*    */   }
/*    */   
/*    */   public boolean forall(Function1 p) {
/* 54 */     return IterableLike$class.forall(this, p);
/*    */   }
/*    */   
/*    */   public boolean exists(Function1 p) {
/* 54 */     return IterableLike$class.exists(this, p);
/*    */   }
/*    */   
/*    */   public Option<A> find(Function1 p) {
/* 54 */     return IterableLike$class.find(this, p);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 54 */     return IterableLike$class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public <B> B foldRight(Object z, Function2 op) {
/* 54 */     return (B)IterableLike$class.foldRight(this, z, op);
/*    */   }
/*    */   
/*    */   public <B> B reduceRight(Function2 op) {
/* 54 */     return (B)IterableLike$class.reduceRight(this, op);
/*    */   }
/*    */   
/*    */   public Iterable<A> toIterable() {
/* 54 */     return IterableLike$class.toIterable(this);
/*    */   }
/*    */   
/*    */   public Iterator<A> toIterator() {
/* 54 */     return IterableLike$class.toIterator(this);
/*    */   }
/*    */   
/*    */   public A head() {
/* 54 */     return (A)IterableLike$class.head(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> slice(int from, int until) {
/* 54 */     return (Iterable<A>)IterableLike$class.slice(this, from, until);
/*    */   }
/*    */   
/*    */   public Iterable<A> take(int n) {
/* 54 */     return (Iterable<A>)IterableLike$class.take(this, n);
/*    */   }
/*    */   
/*    */   public Iterable<A> drop(int n) {
/* 54 */     return (Iterable<A>)IterableLike$class.drop(this, n);
/*    */   }
/*    */   
/*    */   public Iterable<A> takeWhile(Function1 p) {
/* 54 */     return (Iterable<A>)IterableLike$class.takeWhile(this, p);
/*    */   }
/*    */   
/*    */   public Iterator<Iterable<A>> grouped(int size) {
/* 54 */     return IterableLike$class.grouped(this, size);
/*    */   }
/*    */   
/*    */   public Iterator<Iterable<A>> sliding(int size) {
/* 54 */     return IterableLike$class.sliding(this, size);
/*    */   }
/*    */   
/*    */   public Iterator<Iterable<A>> sliding(int size, int step) {
/* 54 */     return IterableLike$class.sliding(this, size, step);
/*    */   }
/*    */   
/*    */   public Iterable<A> takeRight(int n) {
/* 54 */     return (Iterable<A>)IterableLike$class.takeRight(this, n);
/*    */   }
/*    */   
/*    */   public Iterable<A> dropRight(int n) {
/* 54 */     return (Iterable<A>)IterableLike$class.dropRight(this, n);
/*    */   }
/*    */   
/*    */   public <B> void copyToArray(Object xs, int start, int len) {
/* 54 */     IterableLike$class.copyToArray(this, xs, start, len);
/*    */   }
/*    */   
/*    */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 54 */     return (That)IterableLike$class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 54 */     return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*    */   }
/*    */   
/*    */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 54 */     return (That)IterableLike$class.zipWithIndex(this, bf);
/*    */   }
/*    */   
/*    */   public <B> boolean sameElements(GenIterable that) {
/* 54 */     return IterableLike$class.sameElements(this, that);
/*    */   }
/*    */   
/*    */   public Stream<A> toStream() {
/* 54 */     return IterableLike$class.toStream(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object that) {
/* 54 */     return IterableLike$class.canEqual(this, that);
/*    */   }
/*    */   
/*    */   public Object view() {
/* 54 */     return IterableLike$class.view(this);
/*    */   }
/*    */   
/*    */   public IterableView<A, Iterable<A>> view(int from, int until) {
/* 54 */     return IterableLike$class.view(this, from, until);
/*    */   }
/*    */   
/*    */   public AbstractIterable() {
/* 54 */     GenIterable$class.$init$(this);
/* 54 */     IterableLike$class.$init$(this);
/* 54 */     Iterable$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */