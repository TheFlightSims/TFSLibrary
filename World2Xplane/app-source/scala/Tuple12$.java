/*    */ package scala;
/*    */ 
/*    */ public final class Tuple12$ implements Serializable {
/*    */   public static final Tuple12$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 29 */     return "Tuple12";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9, Object _10, Object _11, Object _12) {
/* 29 */     return new Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9, (T10)_10, (T11)_11, (T12)_12);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Option<Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> unapply(Tuple12 x$0) {
/* 29 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>>(new Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9(), (T10)x$0._10(), (T11)x$0._11(), (T12)x$0._12()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 29 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple12$() {
/* 29 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple12$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */