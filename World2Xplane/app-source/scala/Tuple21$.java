/*    */ package scala;
/*    */ 
/*    */ public final class Tuple21$ implements Serializable {
/*    */   public static final Tuple21$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 38 */     return "Tuple21";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9, Object _10, Object _11, Object _12, Object _13, Object _14, Object _15, Object _16, Object _17, Object _18, Object _19, Object _20, Object _21) {
/* 38 */     return new Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9, (T10)_10, (T11)_11, (T12)_12, (T13)_13, (T14)_14, (T15)_15, (T16)_16, (T17)_17, (T18)_18, (T19)_19, (T20)_20, (T21)_21);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Option<Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> unapply(Tuple21 x$0) {
/* 38 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>>(new Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9(), (T10)x$0._10(), (T11)x$0._11(), (T12)x$0._12(), (T13)x$0._13(), (T14)x$0._14(), (T15)x$0._15(), (T16)x$0._16(), (T17)x$0._17(), (T18)x$0._18(), (T19)x$0._19(), (T20)x$0._20(), (T21)x$0._21()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 38 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple21$() {
/* 38 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple21$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */