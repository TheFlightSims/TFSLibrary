/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.BitSetFactory;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.BitSet;
/*    */ import scala.collection.immutable.BitSet$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class BitSet$ implements BitSetFactory<BitSet> {
/*    */   public static final BitSet$ MODULE$;
/*    */   
/*    */   private final BitSet empty;
/*    */   
/*    */   public BitSet apply(Seq elems) {
/* 27 */     return BitSetFactory.class.apply(this, elems);
/*    */   }
/*    */   
/*    */   public Object bitsetCanBuildFrom() {
/* 27 */     return BitSetFactory.class.bitsetCanBuildFrom(this);
/*    */   }
/*    */   
/*    */   private BitSet$() {
/* 27 */     MODULE$ = this;
/* 27 */     BitSetFactory.class.$init$(this);
/* 28 */     this.empty = (BitSet)BitSet$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public BitSet empty() {
/* 28 */     return this.empty;
/*    */   }
/*    */   
/*    */   public Builder<Object, BitSet> newBuilder() {
/* 29 */     return BitSet$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
/* 32 */     return (CanBuildFrom<BitSet, Object, BitSet>)bitsetCanBuildFrom();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\BitSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */