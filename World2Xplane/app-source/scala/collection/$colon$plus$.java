/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class $colon$plus$ {
/*    */   public static final $colon$plus$ MODULE$;
/*    */   
/*    */   private $colon$plus$() {
/* 12 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T, Coll extends SeqLike<T, Coll>> Option<Tuple2<Coll, T>> unapply(SeqLike t) {
/* 19 */     Object object1 = t.init();
/* 19 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 19 */     Object object2 = t.last();
/* 19 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$ = scala.Predef$ArrowAssoc$.MODULE$;
/* 19 */     return t.isEmpty() ? (Option<Tuple2<Coll, T>>)scala.None$.MODULE$ : (Option<Tuple2<Coll, T>>)new Some(new Tuple2(object1, object2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\$colon$plus$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */