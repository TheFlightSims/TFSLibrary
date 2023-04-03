/*    */ package scala;
/*    */ 
/*    */ public final class Tuple3$ implements Serializable {
/*    */   public static final Tuple3$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 20 */     return "Tuple3";
/*    */   }
/*    */   
/*    */   public <T1, T2, T3> Tuple3<T1, T2, T3> apply(Object _1, Object _2, Object _3) {
/* 20 */     return new Tuple3<T1, T2, T3>((T1)_1, (T2)_2, (T3)_3);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3> Option<Tuple3<T1, T2, T3>> unapply(Tuple3 x$0) {
/* 20 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple3<T1, T2, T3>>(new Tuple3<T1, T2, T3>((T1)x$0._1(), (T2)x$0._2(), (T3)x$0._3()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 20 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Tuple3$() {
/* 20 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple3$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */