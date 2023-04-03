/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private package$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ error(String message) {
/* 27 */     throw new RuntimeException(message);
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ exit() {
/* 33 */     return exit(0);
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ exit(int status) {
/* 40 */     System.exit(status);
/* 41 */     throw new Throwable();
/*    */   }
/*    */   
/*    */   public Runtime runtime() {
/* 48 */     return Runtime.getRuntime();
/*    */   }
/*    */   
/*    */   public SystemProperties props() {
/* 55 */     return new SystemProperties();
/*    */   }
/*    */   
/*    */   public Map<String, String> env() {
/* 61 */     return (Map<String, String>)scala.collection.immutable.Map$.MODULE$.apply(((MapLike)scala.collection.JavaConverters$.MODULE$.mapAsScalaMapConverter(System.getenv()).asScala()).toSeq());
/*    */   }
/*    */   
/*    */   public ShutdownHookThread addShutdownHook(Function0<BoxedUnit> body) {
/* 75 */     return ShutdownHookThread$.MODULE$.apply(body);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Thread> allThreads() {
/* 82 */     int num = Thread.activeCount();
/* 83 */     Thread[] tarray = new Thread[num];
/* 84 */     int got = Thread.enumerate(tarray);
/* 86 */     return (IndexedSeq<Thread>)scala.Predef$.MODULE$.wrapRefArray((Object[])scala.Predef$.MODULE$.refArrayOps((Object[])tarray).take(got));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */