/*    */ package scala;
/*    */ 
/*    */ public final class Tuple4$ implements Serializable {
/*    */   public static final Tuple4$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 21 */     return "Tuple4";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> apply(Object _1, Object _2, Object _3, Object _4) {
/* 21 */     return new Tuple4<T1, T2, T3, T4>((T1)_1, (T2)_2, (T3)_3, (T4)_4);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> Option<Tuple4<T1, T2, T3, T4>> unapply(Tuple4 x$0) {
/* 21 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple4<T1, T2, T3, T4>>(new Tuple4<T1, T2, T3, T4>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3(), (T4)x$0._4()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple4$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple4$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */