/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.immutable.LinearSeq$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class LinearSeq$ extends SeqFactory<LinearSeq> {
/*    */   public static final LinearSeq$ MODULE$;
/*    */   
/*    */   private LinearSeq$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>> canBuildFrom() {
/* 32 */     return (CanBuildFrom<LinearSeq<?>, A, LinearSeq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, LinearSeq<A>> newBuilder() {
/* 33 */     return LinearSeq$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\LinearSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */