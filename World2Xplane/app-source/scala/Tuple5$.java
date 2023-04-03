/*    */ package scala;
/*    */ 
/*    */ public final class Tuple5$ implements Serializable {
/*    */   public static final Tuple5$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 22 */     return "Tuple5";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> apply(Object _1, Object _2, Object _3, Object _4, Object _5) {
/* 22 */     return new Tuple5<T1, T2, T3, T4, T5>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5> Option<Tuple5<T1, T2, T3, T4, T5>> unapply(Tuple5 x$0) {
/* 22 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple5<T1, T2, T3, T4, T5>>(new Tuple5<T1, T2, T3, T4, T5>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple5$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple5$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */