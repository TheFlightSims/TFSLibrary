/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.BitSet;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.BitSetFactory;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.mutable.BitSet;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class BitSet$ implements BitSetFactory<BitSet>, Serializable {
/*     */   public static final BitSet$ MODULE$;
/*     */   
/*     */   private final BitSet empty;
/*     */   
/*     */   public BitSet apply(Seq elems) {
/*  69 */     return (BitSet)BitSetFactory.class.apply(this, elems);
/*     */   }
/*     */   
/*     */   public Object bitsetCanBuildFrom() {
/*  69 */     return BitSetFactory.class.bitsetCanBuildFrom(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  69 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BitSet$() {
/*  69 */     MODULE$ = this;
/*  69 */     BitSetFactory.class.$init$(this);
/*  71 */     this.empty = new BitSet.BitSet1(0L);
/*     */   }
/*     */   
/*     */   public BitSet empty() {
/*  71 */     return this.empty;
/*     */   }
/*     */   
/*     */   public Builder<Object, BitSet> newBuilder() {
/*  74 */     return new BitSet$$anon$1();
/*     */   }
/*     */   
/*     */   public static class BitSet$$anon$1 implements Builder<Object, BitSet> {
/*     */     private final BitSet b;
/*     */     
/*     */     public void sizeHint(int size) {
/*  74 */       Builder.class.sizeHint(this, size);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/*  74 */       Builder.class.sizeHint(this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/*  74 */       Builder.class.sizeHint(this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  74 */       Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<Object, NewTo> mapResult(Function1 f) {
/*  74 */       return Builder.class.mapResult(this, f);
/*     */     }
/*     */     
/*     */     public Growable<Object> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  74 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<Object> $plus$plus$eq(TraversableOnce xs) {
/*  74 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     }
/*     */     
/*     */     public BitSet$$anon$1() {
/*  74 */       Growable.class.$init$((Growable)this);
/*  74 */       Builder.class.$init$(this);
/*  75 */       this.b = new BitSet();
/*     */     }
/*     */     
/*     */     public BitSet$$anon$1 $plus$eq(int x) {
/*  76 */       this.b.$plus$eq(x);
/*  76 */       return this;
/*     */     }
/*     */     
/*     */     public void clear() {
/*  77 */       this.b.clear();
/*     */     }
/*     */     
/*     */     public BitSet result() {
/*  78 */       return this.b.toImmutable();
/*     */     }
/*     */   }
/*     */   
/*     */   public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
/*  82 */     return (CanBuildFrom<BitSet, Object, BitSet>)bitsetCanBuildFrom();
/*     */   }
/*     */   
/*     */   public BitSet fromArray(long[] elems) {
/*  86 */     return fromBitMaskNoCopy(elems);
/*     */   }
/*     */   
/*     */   public BitSet fromBitMask(long[] elems) {
/*  90 */     int len = elems.length;
/*  95 */     long[] a = new long[len];
/*  96 */     scala.Array$.MODULE$.copy(elems, 0, a, 0, len);
/*  97 */     return (len == 0) ? empty() : ((len == 1) ? new BitSet.BitSet1(elems[0]) : ((len == 2) ? new BitSet.BitSet2(elems[0], elems[1]) : new BitSet.BitSetN(a)));
/*     */   }
/*     */   
/*     */   public BitSet fromBitMaskNoCopy(long[] elems) {
/* 105 */     int len = elems.length;
/* 106 */     return (len == 0) ? empty() : (
/* 107 */       (len == 1) ? new BitSet.BitSet1(elems[0]) : (
/* 108 */       (len == 2) ? new BitSet.BitSet2(elems[0], elems[1]) : 
/* 109 */       new BitSet.BitSetN(elems)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\BitSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */