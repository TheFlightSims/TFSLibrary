/*    */ package scala;
/*    */ 
/*    */ public final class Tuple8$ implements Serializable {
/*    */   public static final Tuple8$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 25 */     return "Tuple8";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8) {
/* 25 */     return new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8> Option<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> unapply(Tuple8 x$0) {
/* 25 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>(new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 25 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple8$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple8$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */