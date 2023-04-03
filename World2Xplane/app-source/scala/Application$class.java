/*    */ package scala;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.compat.Platform$;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.Properties$;
/*    */ 
/*    */ public abstract class Application$class {
/*    */   public static void $init$(Application $this) {
/* 67 */     Platform$ platform$ = Platform$.MODULE$;
/* 67 */     $this.scala$Application$_setter_$executionStart_$eq(System.currentTimeMillis());
/*    */   }
/*    */   
/*    */   public static void main(Application $this, String[] args) {
/* 74 */     if (Properties$.MODULE$.propIsSet("scala.time")) {
/* 75 */       Platform$ platform$ = Platform$.MODULE$;
/* 75 */       long total = System.currentTimeMillis() - $this.executionStart();
/* 76 */       Console$.MODULE$.println((new StringBuilder()).append("[total ").append(BoxesRunTime.boxToLong(total)).append("ms]").toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Application$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */