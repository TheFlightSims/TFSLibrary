/*    */ package scala;
/*    */ 
/*    */ public final class Tuple9$ implements Serializable {
/*    */   public static final Tuple9$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 26 */     return "Tuple9";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9) {
/* 26 */     return new Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Option<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> unapply(Tuple9 x$0) {
/* 26 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>(new Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple9$() {
/* 26 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple9$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */