/*    */ package scala.util.hashing;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private package$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public int byteswap32(int v) {
/* 21 */     int hc = v * -1640532531;
/* 23 */     return Integer.reverseBytes(hc) * -1640532531;
/*    */   }
/*    */   
/*    */   public long byteswap64(long v) {
/* 30 */     long hc = v * -7046033566014671411L;
/* 32 */     return Long.reverseBytes(hc) * -7046033566014671411L;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */