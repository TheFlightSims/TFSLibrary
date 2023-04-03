/*    */ package scala.collection.generic;
/*    */ 
/*    */ public final class SliceInterval$ {
/*    */   public static final SliceInterval$ MODULE$;
/*    */   
/*    */   private SliceInterval$() {
/* 45 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public SliceInterval apply(int from, int until) {
/* 47 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 47 */     int lo = scala.runtime.RichInt$.MODULE$.max$extension(from, 0);
/* 48 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 48 */     int hi = scala.runtime.RichInt$.MODULE$.max$extension(until, 0);
/* 50 */     return (hi <= lo) ? new SliceInterval(lo, lo) : 
/* 51 */       new SliceInterval(lo, hi);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SliceInterval$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */