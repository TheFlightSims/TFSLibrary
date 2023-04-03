/*    */ package scala;
/*    */ 
/*    */ public final class Tuple7$ implements Serializable {
/*    */   public static final Tuple7$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 24 */     return "Tuple7";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> apply(Object _1, Object _2, Object _3, Object _4, Object _5, Object _6, Object _7) {
/* 24 */     return new Tuple7<T1, T2, T3, T4, T5, T6, T7>((T1)_1, (T2)_2, (T3)_3, (T4)_4, (T5)_5, (T6)_6, (T7)_7);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4, T5, T6, T7> Option<Tuple7<T1, T2, T3, T4, T5, T6, T7>> unapply(Tuple7 x$0) {
/* 24 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple7<T1, T2, T3, T4, T5, T6, T7>>(new Tuple7<T1, T2, T3, T4, T5, T6, T7>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4(), (T5)x$0._5(), (T6)x$0._6(), (T7)x$0._7()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 24 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple7$() {
/* 24 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple7$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */