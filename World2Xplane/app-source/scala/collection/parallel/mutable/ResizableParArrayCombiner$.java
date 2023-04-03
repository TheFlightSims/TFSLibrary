/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.TaskSupport;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ public final class ResizableParArrayCombiner$ {
/*    */   public static final ResizableParArrayCombiner$ MODULE$;
/*    */   
/*    */   private ResizableParArrayCombiner$() {
/* 88 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> ResizableParArrayCombiner<T> apply(ArrayBuffer c) {
/* 90 */     return new ResizableParArrayCombiner$$anon$1(c);
/*    */   }
/*    */   
/*    */   public static class ResizableParArrayCombiner$$anon$1 implements ResizableParArrayCombiner<T> {
/*    */     private final ArrayBuffer<ExposedArrayBuffer<T>> chain;
/*    */     
/*    */     private final Growable lastbuff;
/*    */     
/*    */     private volatile transient TaskSupport _combinerTaskSupport;
/*    */     
/*    */     public void sizeHint(int sz) {
/* 90 */       ResizableParArrayCombiner$class.sizeHint(this, sz);
/*    */     }
/*    */     
/*    */     public ResizableParArrayCombiner<T> newLazyCombiner(ArrayBuffer c) {
/* 90 */       return ResizableParArrayCombiner$class.newLazyCombiner(this, c);
/*    */     }
/*    */     
/*    */     public ParArray<T> allocateAndCopy() {
/* 90 */       return ResizableParArrayCombiner$class.allocateAndCopy(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 90 */       return ResizableParArrayCombiner$class.toString(this);
/*    */     }
/*    */     
/*    */     public ExposedArrayBuffer<T> lastbuff() {
/* 90 */       return (ExposedArrayBuffer<T>)this.lastbuff;
/*    */     }
/*    */     
/*    */     public void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(Growable x$1) {
/* 90 */       this.lastbuff = x$1;
/*    */     }
/*    */     
/*    */     public LazyCombiner<T, ParArray<T>, ExposedArrayBuffer<T>> $plus$eq(Object elem) {
/* 90 */       return LazyCombiner$class.$plus$eq(this, elem);
/*    */     }
/*    */     
/*    */     public ParArray<T> result() {
/* 90 */       return (ParArray<T>)LazyCombiner$class.result(this);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 90 */       LazyCombiner$class.clear(this);
/*    */     }
/*    */     
/*    */     public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner other) {
/* 90 */       return LazyCombiner$class.combine(this, other);
/*    */     }
/*    */     
/*    */     public int size() {
/* 90 */       return LazyCombiner$class.size(this);
/*    */     }
/*    */     
/*    */     public TaskSupport _combinerTaskSupport() {
/* 90 */       return this._combinerTaskSupport;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 90 */       this._combinerTaskSupport = x$1;
/*    */     }
/*    */     
/*    */     public TaskSupport combinerTaskSupport() {
/* 90 */       return Combiner.class.combinerTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 90 */       Combiner.class.combinerTaskSupport_$eq(this, cts);
/*    */     }
/*    */     
/*    */     public boolean canBeShared() {
/* 90 */       return Combiner.class.canBeShared(this);
/*    */     }
/*    */     
/*    */     public ParArray<T> resultWithTaskSupport() {
/* 90 */       return (ParArray<T>)Combiner.class.resultWithTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 90 */       Builder.class.sizeHint((Builder)this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 90 */       Builder.class.sizeHint((Builder)this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 90 */       Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 90 */       return Builder.class.mapResult((Builder)this, f);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 90 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 90 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*    */     }
/*    */     
/*    */     public ArrayBuffer<ExposedArrayBuffer<T>> chain() {
/* 90 */       return this.chain;
/*    */     }
/*    */     
/*    */     public ResizableParArrayCombiner$$anon$1(ArrayBuffer<ExposedArrayBuffer<T>> c$1) {
/* 90 */       Growable.class.$init$((Growable)this);
/* 90 */       Builder.class.$init$((Builder)this);
/* 90 */       Combiner.class.$init$(this);
/* 90 */       LazyCombiner$class.$init$(this);
/* 90 */       ResizableParArrayCombiner$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> ResizableParArrayCombiner<T> apply() {
/* 92 */     return apply((new ArrayBuffer()).$plus$eq(new ExposedArrayBuffer()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ResizableParArrayCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */