/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e2A!\001\002\001\023\tYR\t_3dkRLwN\\\"p]R,\007\020\036+bg.\034V\017\0359peRT!a\001\003\002\021A\f'/\0317mK2T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\031B\001\001\006\017%A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!a\003+bg.\034V\017\0359peR\004\"aD\n\n\005Q\021!!F#yK\016,H/[8o\007>tG/\032=u)\006\0348n\035\005\t-\001\021)\031!C\001/\005YQM\034<je>tW.\0328u+\005A\002CA\r\035\033\005Q\"BA\016\007\003)\031wN\\2veJ,g\016^\005\003;i\021\001#\022=fGV$\030n\0348D_:$X\r\037;\t\021}\001!\021!Q\001\na\tA\"\0328wSJ|g.\\3oi\002BQ!\t\001\005\002\t\na\001P5oSRtDCA\022%!\ty\001\001C\004\027AA\005\t\031\001\r\b\017\031\022\021\021!E\001O\005YR\t_3dkRLwN\\\"p]R,\007\020\036+bg.\034V\017\0359peR\004\"a\004\025\007\017\005\021\021\021!E\001SM\021\001F\003\005\006C!\"\ta\013\013\002O!9Q\006KI\001\n\003q\023a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$\023'F\0010U\tA\002gK\0012!\t\021t'D\0014\025\t!T'A\005v]\016DWmY6fI*\021aGB\001\013C:tw\016^1uS>t\027B\001\0354\005E)hn\0315fG.,GMV1sS\006t7-\032")
/*    */ public class ExecutionContextTaskSupport implements TaskSupport, ExecutionContextTasks {
/*    */   private final ExecutionContext environment;
/*    */   
/*    */   private final Tasks driver;
/*    */   
/*    */   private final ArrayBuffer<String> debugMessages;
/*    */   
/*    */   public static ExecutionContext $lessinit$greater$default$1() {
/*    */     return ExecutionContextTaskSupport$.MODULE$.$lessinit$greater$default$1();
/*    */   }
/*    */   
/*    */   public Tasks driver() {
/* 88 */     return this.driver;
/*    */   }
/*    */   
/*    */   public void scala$collection$parallel$ExecutionContextTasks$_setter_$driver_$eq(Tasks x$1) {
/* 88 */     this.driver = x$1;
/*    */   }
/*    */   
/*    */   public ExecutionContext executionContext() {
/* 88 */     return ExecutionContextTasks$class.executionContext(this);
/*    */   }
/*    */   
/*    */   public <R, Tp> Function0<R> execute(Task task) {
/* 88 */     return ExecutionContextTasks$class.execute(this, task);
/*    */   }
/*    */   
/*    */   public <R, Tp> R executeAndWaitResult(Task task) {
/* 88 */     return (R)ExecutionContextTasks$class.executeAndWaitResult(this, task);
/*    */   }
/*    */   
/*    */   public int parallelismLevel() {
/* 88 */     return ExecutionContextTasks$class.parallelismLevel(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debugMessages() {
/* 88 */     return this.debugMessages;
/*    */   }
/*    */   
/*    */   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(ArrayBuffer<String> x$1) {
/* 88 */     this.debugMessages = x$1;
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debuglog(String s) {
/* 88 */     return Tasks$class.debuglog(this, s);
/*    */   }
/*    */   
/*    */   public ExecutionContext environment() {
/* 88 */     return this.environment;
/*    */   }
/*    */   
/*    */   public ExecutionContextTaskSupport(ExecutionContext environment) {
/* 88 */     Tasks$class.$init$(this);
/* 88 */     ExecutionContextTasks$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ExecutionContextTaskSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */