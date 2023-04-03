/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ public final class UnrolledParArrayCombiner$ {
/*     */   public static final UnrolledParArrayCombiner$ MODULE$;
/*     */   
/*     */   private UnrolledParArrayCombiner$() {
/* 107 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> UnrolledParArrayCombiner<T> apply() {
/* 108 */     return new UnrolledParArrayCombiner$$anon$1();
/*     */   }
/*     */   
/*     */   public static class UnrolledParArrayCombiner$$anon$1 implements UnrolledParArrayCombiner<T> {
/*     */     private final DoublingUnrolledBuffer<Object> buff;
/*     */     
/*     */     private volatile transient TaskSupport _combinerTaskSupport;
/*     */     
/*     */     public DoublingUnrolledBuffer<Object> buff() {
/* 108 */       return this.buff;
/*     */     }
/*     */     
/*     */     public void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(DoublingUnrolledBuffer<Object> x$1) {
/* 108 */       this.buff = x$1;
/*     */     }
/*     */     
/*     */     public UnrolledParArrayCombiner<T> $plus$eq(Object elem) {
/* 108 */       return UnrolledParArrayCombiner$class.$plus$eq(this, elem);
/*     */     }
/*     */     
/*     */     public ParArray<T> result() {
/* 108 */       return UnrolledParArrayCombiner$class.result(this);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 108 */       UnrolledParArrayCombiner$class.clear(this);
/*     */     }
/*     */     
/*     */     public void sizeHint(int sz) {
/* 108 */       UnrolledParArrayCombiner$class.sizeHint(this, sz);
/*     */     }
/*     */     
/*     */     public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner other) {
/* 108 */       return UnrolledParArrayCombiner$class.combine(this, other);
/*     */     }
/*     */     
/*     */     public int size() {
/* 108 */       return UnrolledParArrayCombiner$class.size(this);
/*     */     }
/*     */     
/*     */     public TaskSupport _combinerTaskSupport() {
/* 108 */       return this._combinerTaskSupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 108 */       this._combinerTaskSupport = x$1;
/*     */     }
/*     */     
/*     */     public TaskSupport combinerTaskSupport() {
/* 108 */       return Combiner.class.combinerTaskSupport(this);
/*     */     }
/*     */     
/*     */     public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 108 */       Combiner.class.combinerTaskSupport_$eq(this, cts);
/*     */     }
/*     */     
/*     */     public boolean canBeShared() {
/* 108 */       return Combiner.class.canBeShared(this);
/*     */     }
/*     */     
/*     */     public ParArray<T> resultWithTaskSupport() {
/* 108 */       return (ParArray<T>)Combiner.class.resultWithTaskSupport(this);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/* 108 */       Builder.class.sizeHint((Builder)this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/* 108 */       Builder.class.sizeHint((Builder)this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 108 */       Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 108 */       return Builder.class.mapResult((Builder)this, f);
/*     */     }
/*     */     
/*     */     public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 108 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 108 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     }
/*     */     
/*     */     public UnrolledParArrayCombiner$$anon$1() {
/* 108 */       Growable.class.$init$((Growable)this);
/* 108 */       Builder.class.$init$((Builder)this);
/* 108 */       Combiner.class.$init$(this);
/* 108 */       UnrolledParArrayCombiner$class.$init$(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\UnrolledParArrayCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */