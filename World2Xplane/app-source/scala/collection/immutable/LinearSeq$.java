/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ 
/*    */ public final class LinearSeq$ extends SeqFactory<LinearSeq> {
/*    */   public static final LinearSeq$ MODULE$;
/*    */   
/*    */   private LinearSeq$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>> canBuildFrom() {
/* 35 */     return (CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, LinearSeq<A>> newBuilder() {
/* 36 */     return (Builder<A, LinearSeq<A>>)new ListBuffer();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LinearSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */