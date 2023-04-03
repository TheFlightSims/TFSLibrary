/*    */ package scala.util.logging;
/*    */ 
/*    */ import scala.Console$;
/*    */ 
/*    */ public abstract class ConsoleLogger$class {
/*    */   public static void $init$(ConsoleLogger $this) {}
/*    */   
/*    */   public static void log(ConsoleLogger $this, String msg) {
/* 25 */     Console$.MODULE$.println(msg);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\logging\ConsoleLogger$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */