/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.BitSet;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.BitSetFactory;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ 
/*     */ public final class BitSet$ implements BitSetFactory<BitSet>, Serializable {
/*     */   public static final BitSet$ MODULE$;
/*     */   
/*     */   public BitSet apply(Seq elems) {
/* 119 */     return (BitSet)BitSetFactory.class.apply(this, elems);
/*     */   }
/*     */   
/*     */   public Object bitsetCanBuildFrom() {
/* 119 */     return BitSetFactory.class.bitsetCanBuildFrom(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 119 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BitSet$() {
/* 119 */     MODULE$ = this;
/* 119 */     BitSetFactory.class.$init$(this);
/*     */   }
/*     */   
/*     */   public BitSet empty() {
/* 120 */     return new BitSet();
/*     */   }
/*     */   
/*     */   public Builder<Object, BitSet> newBuilder() {
/* 123 */     return new GrowingBuilder<Object, BitSet>(empty());
/*     */   }
/*     */   
/*     */   public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
/* 126 */     return (CanBuildFrom<BitSet, Object, BitSet>)bitsetCanBuildFrom();
/*     */   }
/*     */   
/*     */   public BitSet fromBitMask(long[] elems) {
/* 130 */     int len = elems.length;
/* 131 */     long[] a = new long[len];
/* 132 */     scala.Array$.MODULE$.copy(elems, 0, a, 0, len);
/* 133 */     return new BitSet(a);
/*     */   }
/*     */   
/*     */   public BitSet fromBitMaskNoCopy(long[] elems) {
/* 139 */     return new BitSet(elems);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BitSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */