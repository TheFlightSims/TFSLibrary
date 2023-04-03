/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ 
/*    */ public final class IndexedSeq$ extends SeqFactory<IndexedSeq> {
/*    */   public static final IndexedSeq$ MODULE$;
/*    */   
/*    */   private IndexedSeq$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
/* 35 */     return (CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, IndexedSeq<A>> newBuilder() {
/* 36 */     return (Builder)new ArrayBuffer<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */