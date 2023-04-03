/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ public abstract class Positional$class {
/*    */   public static void $init$(Positional $this) {
/* 18 */     $this.pos_$eq(NoPosition$.MODULE$);
/*    */   }
/*    */   
/*    */   public static Positional setPos(Positional $this, Position newpos) {
/* 24 */     if ($this.pos() == NoPosition$.MODULE$)
/* 24 */       $this.pos_$eq(newpos); 
/* 25 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\Positional$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */