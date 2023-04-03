/*   */ package scala.collection;
/*   */ 
/*   */ import scala.Option;
/*   */ import scala.Some;
/*   */ import scala.Tuple2;
/*   */ 
/*   */ public final class $plus$colon$ {
/*   */   public static final $plus$colon$ MODULE$;
/*   */   
/*   */   private $plus$colon$() {
/* 4 */     MODULE$ = this;
/*   */   }
/*   */   
/*   */   public <T, Coll extends SeqLike<T, Coll>> Option<Tuple2<T, Coll>> unapply(SeqLike t) {
/* 8 */     Object object1 = t.head();
/* 8 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 8 */     Object object2 = t.tail();
/* 8 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$ = scala.Predef$ArrowAssoc$.MODULE$;
/* 8 */     return t.isEmpty() ? (Option<Tuple2<T, Coll>>)scala.None$.MODULE$ : (Option<Tuple2<T, Coll>>)new Some(new Tuple2(object1, object2));
/*   */   }
/*   */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\$plus$colon$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */