/*    */ package scala.ref;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class WeakReference$ {
/*    */   public static final WeakReference$ MODULE$;
/*    */   
/*    */   private WeakReference$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> WeakReference<T> apply(Object value) {
/* 28 */     return new WeakReference<T>((T)value);
/*    */   }
/*    */   
/*    */   public <T> Option<T> unapply(WeakReference wr) {
/* 32 */     Object x = wr.underlying().get();
/* 33 */     return (x == null) ? (Option<T>)scala.None$.MODULE$ : (Option<T>)new Some(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\WeakReference$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */