/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.VectorBuilder;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0254Q!\001\002\001\005)\021Q\003T1{sB\013'OV3di>\0248i\\7cS:,'O\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t\001\002]1sC2dW\r\034\006\003\017!\t!bY8mY\026\034G/[8o\025\005I\021!B:dC2\fWCA\006\027'\r\001A\002\005\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007\003B\t\023)\001j\021\001B\005\003'\021\021\001bQ8nE&tWM\035\t\003+Ya\001\001B\003\030\001\t\007\021DA\001U\007\001\t\"AG\017\021\0055Y\022B\001\017\t\005\035qu\016\0365j]\036\004\"!\004\020\n\005}A!aA!osB\031\021E\t\013\016\003\tI!a\t\002\003\023A\013'OV3di>\024\b\"B\023\001\t\0031\023A\002\037j]&$h\bF\001(!\r\t\003\001\006\005\bS\001\001\r\021\"\001+\003\t\031(0F\001,!\tiA&\003\002.\021\t\031\021J\034;\t\017=\002\001\031!C\001a\00511O_0%KF$\"!\r\033\021\0055\021\024BA\032\t\005\021)f.\033;\t\017Ur\023\021!a\001W\005\031\001\020J\031\t\r]\002\001\025)\003,\003\r\031(\020\t\005\bs\001\021\r\021\"\001;\003\0351Xm\031;peN,\022a\017\t\004y}\nU\"A\037\013\005y2\021aB7vi\006\024G.Z\005\003\001v\0221\"\021:sCf\024UO\0324feB\031!\t\022\013\016\003\rS!a\001\004\n\005\025\033%!\004,fGR|'OQ;jY\022,'\017\003\004H\001\001\006IaO\001\tm\026\034Go\034:tA!)\021\n\001C\001U\005!1/\033>f\021\025Y\005\001\"\001M\003!!\003\017\\;tI\025\fHCA'O\033\005\001\001\"B(K\001\004!\022\001B3mK6DQ!\025\001\005\002I\013Qa\0317fCJ$\022!\r\005\006)\002!\t!V\001\007e\026\034X\017\034;\025\003\001BQa\026\001\005\002a\013qaY8nE&tW-F\002Z=\n$\"a\n.\t\013m3\006\031\001/\002\013=$\b.\032:\021\tE\021R,\031\t\003+y#Qa\030,C\002\001\024\021!V\t\0035Q\001\"!\0062\005\013\r4&\031\0013\003\0139+w\017V8\022\005\001j\002")
/*     */ public class LazyParVectorCombiner<T> implements Combiner<T, ParVector<T>> {
/*     */   private int sz;
/*     */   
/*     */   private final ArrayBuffer<VectorBuilder<T>> vectors;
/*     */   
/*     */   private volatile transient TaskSupport _combinerTaskSupport;
/*     */   
/*     */   public TaskSupport _combinerTaskSupport() {
/* 105 */     return this._combinerTaskSupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 105 */     this._combinerTaskSupport = x$1;
/*     */   }
/*     */   
/*     */   public TaskSupport combinerTaskSupport() {
/* 105 */     return Combiner.class.combinerTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 105 */     Combiner.class.combinerTaskSupport_$eq(this, cts);
/*     */   }
/*     */   
/*     */   public boolean canBeShared() {
/* 105 */     return Combiner.class.canBeShared(this);
/*     */   }
/*     */   
/*     */   public ParVector<T> resultWithTaskSupport() {
/* 105 */     return (ParVector<T>)Combiner.class.resultWithTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/* 105 */     Builder.class.sizeHint((Builder)this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/* 105 */     Builder.class.sizeHint((Builder)this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/* 105 */     Builder.class.sizeHint((Builder)this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 105 */     Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 105 */     return Builder.class.mapResult((Builder)this, f);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 105 */     return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 105 */     return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */   }
/*     */   
/*     */   public LazyParVectorCombiner() {
/* 105 */     Growable.class.$init$((Growable)this);
/* 105 */     Builder.class.$init$((Builder)this);
/* 105 */     Combiner.class.$init$(this);
/* 107 */     this.sz = 0;
/* 108 */     this.vectors = (new ArrayBuffer()).$plus$eq(new VectorBuilder());
/*     */   }
/*     */   
/*     */   public int sz() {
/*     */     return this.sz;
/*     */   }
/*     */   
/*     */   public void sz_$eq(int x$1) {
/*     */     this.sz = x$1;
/*     */   }
/*     */   
/*     */   public ArrayBuffer<VectorBuilder<T>> vectors() {
/* 108 */     return this.vectors;
/*     */   }
/*     */   
/*     */   public int size() {
/* 110 */     return sz();
/*     */   }
/*     */   
/*     */   public LazyParVectorCombiner<T> $plus$eq(Object elem) {
/* 113 */     ((VectorBuilder)vectors().last()).$plus$eq(elem);
/* 114 */     sz_$eq(sz() + 1);
/* 115 */     return this;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 119 */     vectors().clear();
/* 120 */     vectors().$plus$eq(new VectorBuilder());
/* 121 */     sz_$eq(0);
/*     */   }
/*     */   
/*     */   public ParVector<T> result() {
/* 125 */     VectorBuilder rvb = new VectorBuilder();
/* 126 */     vectors().foreach((Function1)new LazyParVectorCombiner$$anonfun$result$1(this, (LazyParVectorCombiner<T>)rvb));
/* 129 */     return new ParVector<T>(rvb.result());
/*     */   }
/*     */   
/*     */   public class LazyParVectorCombiner$$anonfun$result$1 extends AbstractFunction1<VectorBuilder<T>, VectorBuilder<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final VectorBuilder rvb$1;
/*     */     
/*     */     public LazyParVectorCombiner$$anonfun$result$1(LazyParVectorCombiner $outer, VectorBuilder rvb$1) {}
/*     */     
/*     */     public final VectorBuilder<T> apply(VectorBuilder vb) {
/*     */       return this.rvb$1.$plus$plus$eq((TraversableOnce)vb.result());
/*     */     }
/*     */   }
/*     */   
/*     */   public <U extends T, NewTo> LazyParVectorCombiner<T> combine(Combiner other) {
/* 133 */     LazyParVectorCombiner that = (LazyParVectorCombiner)other;
/* 134 */     sz_$eq(sz() + that.sz());
/* 135 */     vectors().$plus$plus$eq((TraversableOnce)that.vectors());
/* 136 */     return (other == this) ? this : this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\LazyParVectorCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */