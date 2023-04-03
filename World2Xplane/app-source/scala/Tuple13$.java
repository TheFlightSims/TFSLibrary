/*    */ package scala;
/*    */ 
/*    */ public final class Tuple13$ implements Serializable {
/*    */   public static final Tuple13$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 30 */     return "Tuple13";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9, Object _10, Object _11, Object _12, Object _13) {
/* 30 */     return new Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9, (T10)_10, (T11)_11, (T12)_12, (T13)_13);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Option<Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> unapply(Tuple13 x$0) {
/* 30 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>>(new Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9(), (T10)x$0._10(), (T11)x$0._11(), (T12)x$0._12(), (T13)x$0._13()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 30 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple13$() {
/* 30 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple13$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */