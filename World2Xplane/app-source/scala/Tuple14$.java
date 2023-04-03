/*    */ package scala;
/*    */ 
/*    */ public final class Tuple14$ implements Serializable {
/*    */   public static final Tuple14$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 31 */     return "Tuple14";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9, Object _10, Object _11, Object _12, Object _13, Object _14) {
/* 31 */     return new Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9, (T10)_10, (T11)_11, (T12)_12, (T13)_13, (T14)_14);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Option<Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> unapply(Tuple14 x$0) {
/* 31 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>>(new Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9(), (T10)x$0._10(), (T11)x$0._11(), (T12)x$0._12(), (T13)x$0._13(), (T14)x$0._14()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 31 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple14$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple14$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */