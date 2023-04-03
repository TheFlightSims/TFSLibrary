/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class Builder$class {
/*     */   public static void $init$(Builder $this) {}
/*     */   
/*     */   public static void sizeHint(Builder $this, int size) {}
/*     */   
/*     */   public static void sizeHint(Builder $this, TraversableLike coll) {
/*  68 */     if (coll instanceof scala.collection.IndexedSeqLike)
/*  69 */       $this.sizeHint(coll.size()); 
/*     */   }
/*     */   
/*     */   public static void sizeHint(Builder $this, TraversableLike coll, int delta) {
/*  87 */     if (coll instanceof scala.collection.IndexedSeqLike)
/*  88 */       $this.sizeHint(coll.size() + delta); 
/*     */   }
/*     */   
/*     */   public static void sizeHintBounded(Builder $this, int size, TraversableLike boundingColl) {
/* 105 */     if (boundingColl instanceof scala.collection.IndexedSeqLike) {
/* 106 */       Predef$ predef$ = Predef$.MODULE$;
/* 106 */       $this.sizeHint(RichInt$.MODULE$.min$extension(size, boundingColl.size()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Builder mapResult(Builder $this, Function1 f) {
/* 117 */     return new Builder$$anon$1($this, (Builder<Elem, To>)f);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Builder$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */