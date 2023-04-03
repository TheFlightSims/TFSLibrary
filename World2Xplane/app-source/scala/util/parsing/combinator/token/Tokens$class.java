/*    */ package scala.util.parsing.combinator.token;
/*    */ 
/*    */ public abstract class Tokens$class {
/*    */   public static void $init$(Tokens $this) {}
/*    */   
/*    */   public static Tokens.Token errorToken(Tokens $this, String msg) {
/* 41 */     return new Tokens.ErrorToken($this, msg);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\token\Tokens$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */