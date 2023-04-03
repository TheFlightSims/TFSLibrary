/*    */ package akka;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ActorSystem$;
/*    */ import scala.Option;
/*    */ 
/*    */ public final class Main$ {
/*    */   public static final Main$ MODULE$;
/*    */   
/*    */   private Main$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public void main(String[] args) {
/* 27 */     if (args.length != 1) {
/* 28 */       scala.Predef$.MODULE$.println("you need to provide exactly one argument: the class of the application supervisor actor");
/*    */     } else {
/* 30 */       ActorSystem system = ActorSystem$.MODULE$.apply("Main");
/*    */       try {
/*    */         return;
/*    */       } finally {
/* 31 */         Exception exception1 = null, exception2 = exception1;
/* 36 */         Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 36 */         if (option.isEmpty())
/*    */           throw exception1; 
/* 36 */         Throwable e = (Throwable)option.get();
/* 36 */         system.shutdown();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\Main$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */