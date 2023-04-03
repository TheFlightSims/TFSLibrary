/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ 
/*    */ public final class LinearSeq$ extends SeqFactory<LinearSeq> {
/*    */   public static final LinearSeq$ MODULE$;
/*    */   
/*    */   private LinearSeq$() {
/* 38 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>> canBuildFrom() {
/* 39 */     return (CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, LinearSeq<A>> newBuilder() {
/* 40 */     return (Builder)new MutableList<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinearSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */