/*    */ package scala;
/*    */ 
/*    */ public final class Tuple6$ implements Serializable {
/*    */   public static final Tuple6$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 23 */     return "Tuple6";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6) {
/* 23 */     return new Tuple6<T1, T2, T3, T4, T5, T6>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6> Option<Tuple6<T1, T2, T3, T4, T5, T6>> unapply(Tuple6 x$0) {
/* 23 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple6<T1, T2, T3, T4, T5, T6>>(new Tuple6<T1, T2, T3, T4, T5, T6>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 23 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple6$() {
/* 23 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple6$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */