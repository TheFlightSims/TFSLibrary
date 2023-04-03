/*    */ package scala;
/*    */ 
/*    */ import scala.collection.generic.TraversableForwarder;
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.compat.Platform$;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.Properties$;
/*    */ import scala.util.PropertiesTrait;
/*    */ 
/*    */ public abstract class App$class {
/*    */   public static void $init$(App $this) {
/* 42 */     Platform$ platform$ = Platform$.MODULE$;
/* 42 */     $this.scala$App$_setter_$executionStart_$eq(System.currentTimeMillis());
/* 50 */     $this.scala$App$_setter_$scala$App$$initCode_$eq(new ListBuffer());
/*    */   }
/*    */   
/*    */   public static String[] args(App $this) {
/*    */     return $this.scala$App$$_args();
/*    */   }
/*    */   
/*    */   public static void delayedInit(App $this, Function0 body) {
/* 60 */     $this.scala$App$$initCode().$plus$eq(body);
/*    */   }
/*    */   
/*    */   public static void main(App $this, String[] args) {
/* 70 */     $this.scala$App$$_args_$eq(args);
/* 71 */     TraversableForwarder.class.foreach((TraversableForwarder)$this.scala$App$$initCode(), (Function1)new App$$anonfun$main$1($this));
/* 72 */     if (PropertiesTrait.class.propIsSet((PropertiesTrait)Properties$.MODULE$, "scala.time")) {
/* 73 */       Platform$ platform$ = Platform$.MODULE$;
/* 73 */       long total = System.currentTimeMillis() - $this.executionStart();
/* 74 */       String str = (new StringBuilder()).append("[total ").append(BoxesRunTime.boxToLong(total)).append("ms]").toString();
/* 74 */       Console$.MODULE$.out().println(str);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\App$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */