/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ public final class NoPosition$ implements Position {
/*    */   public static final NoPosition$ MODULE$;
/*    */   
/*    */   public boolean $less(Position that) {
/* 18 */     return Position$class.$less(this, that);
/*    */   }
/*    */   
/*    */   private NoPosition$() {
/* 18 */     MODULE$ = this;
/* 18 */     Position$class.$init$(this);
/*    */   }
/*    */   
/*    */   public int line() {
/* 19 */     return 0;
/*    */   }
/*    */   
/*    */   public int column() {
/* 20 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 21 */     return "<undefined position>";
/*    */   }
/*    */   
/*    */   public String longString() {
/* 22 */     return toString();
/*    */   }
/*    */   
/*    */   public String lineContents() {
/* 23 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\NoPosition$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */