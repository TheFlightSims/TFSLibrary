/*    */ package scala.util.control;
/*    */ 
/*    */ import scala.Function0;
/*    */ 
/*    */ public final class TailCalls$ {
/*    */   public static final TailCalls$ MODULE$;
/*    */   
/*    */   private TailCalls$() {
/* 28 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> TailCalls.TailRec<A> tailcall(Function0<TailCalls.TailRec<A>> rest) {
/* 55 */     return new TailCalls.Call<A>(rest);
/*    */   }
/*    */   
/*    */   public <A> TailCalls.TailRec<A> done(Object result) {
/* 62 */     return new TailCalls.Done<A>((A)result);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\TailCalls$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */