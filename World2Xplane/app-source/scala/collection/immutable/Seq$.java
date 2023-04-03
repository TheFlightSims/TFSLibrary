/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ 
/*    */ public final class Seq$ extends SeqFactory<Seq> {
/*    */   public static final Seq$ MODULE$;
/*    */   
/*    */   private Seq$() {
/* 42 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Seq<?>, A, Seq<A>> canBuildFrom() {
/* 44 */     return (CanBuildFrom<Seq<?>, A, Seq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Seq<A>> newBuilder() {
/* 45 */     return (Builder<A, Seq<A>>)new ListBuffer();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Seq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */