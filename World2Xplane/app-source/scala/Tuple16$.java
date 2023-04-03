/*    */ package scala;
/*    */ 
/*    */ public final class Tuple16$ implements Serializable {
/*    */   public static final Tuple16$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 33 */     return "Tuple16";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7, Object _8, Object _9, Object _10, Object _11, Object _12, Object _13, Object _14, Object _15, Object _16) {
/* 33 */     return new Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7, (T8)_8, (T9)_9, (T10)_10, (T11)_11, (T12)_12, (T13)_13, (T14)_14, (T15)_15, (T16)_16);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Option<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> unapply(Tuple16 x$0) {
/* 33 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>>(new Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7(), (T8)x$0._8(), (T9)x$0._9(), (T10)x$0._10(), (T11)x$0._11(), (T12)x$0._12(), (T13)x$0._13(), (T14)x$0._14(), (T15)x$0._15(), (T16)x$0._16()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 33 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple16$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple16$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */