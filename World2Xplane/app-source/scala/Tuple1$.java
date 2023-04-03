/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Tuple1$ implements Serializable {
/*    */   public static final Tuple1$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 18 */     return "Tuple1";
/*    */   }
/*    */   
/*    */   public <T1> Tuple1<T1> apply(Object _1) {
/* 18 */     return new Tuple1<T1>((T1)_1);
/*    */   }
/*    */   
/*    */   public <T1> Option<T1> unapply(Tuple1<T1> x$0) {
/* 18 */     return (x$0 == null) ? None$.MODULE$ : new Some<T1>(x$0._1());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 18 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public Tuple1<Object> apply$mDc$sp(double _1) {
/* 18 */     return new Tuple1$mcD$sp(_1);
/*    */   }
/*    */   
/*    */   public Tuple1<Object> apply$mIc$sp(int _1) {
/* 18 */     return new Tuple1$mcI$sp(_1);
/*    */   }
/*    */   
/*    */   public Tuple1<Object> apply$mJc$sp(long _1) {
/* 18 */     return new Tuple1$mcJ$sp(_1);
/*    */   }
/*    */   
/*    */   public Option<Object> unapply$mDc$sp(Tuple1 x$0) {
/* 18 */     return (x$0 == null) ? None$.MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0._1$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Object> unapply$mIc$sp(Tuple1 x$0) {
/* 18 */     return (x$0 == null) ? None$.MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0._1$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Object> unapply$mJc$sp(Tuple1 x$0) {
/* 18 */     return (x$0 == null) ? None$.MODULE$ : new Some(BoxesRunTime.boxToLong(x$0._1$mcJ$sp()));
/*    */   }
/*    */   
/*    */   private Tuple1$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple1$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */