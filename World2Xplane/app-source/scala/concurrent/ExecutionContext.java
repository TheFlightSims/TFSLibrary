/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.impl.ExecutionContextImpl;
/*    */ import scala.concurrent.impl.ExecutionContextImpl$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u4q!\001\002\021\002\007\005qA\001\tFq\026\034W\017^5p]\016{g\016^3yi*\0211\001B\001\013G>t7-\036:sK:$(\"A\003\002\013M\034\027\r\\1\004\001M\021\001\001\003\t\003\023)i\021\001B\005\003\027\021\021a!\0218z%\0264\007\"B\007\001\t\003q\021A\002\023j]&$H\005F\001\020!\tI\001#\003\002\022\t\t!QK\\5u\021\025\031\002A\"\001\025\003\035)\0070Z2vi\026$\"aD\013\t\013Y\021\002\031A\f\002\021I,hN\\1cY\026\004\"\001G\017\016\003eQ!AG\016\002\t1\fgn\032\006\0029\005!!.\031<b\023\tq\022D\001\005Sk:t\027M\0317f\021\025\001\003A\"\001\"\0035\021X\r]8si\032\013\027\016\\;sKR\021qB\t\005\006G}\001\r\001J\001\002iB\021Q%\f\b\003M-r!a\n\026\016\003!R!!\013\004\002\rq\022xn\034;?\023\005)\021B\001\027\005\003\035\001\030mY6bO\026L!AL\030\003\023QC'o\\<bE2,'B\001\027\005\021\025\t\004\001\"\0013\003\035\001(/\0329be\026$\022a\r\t\003i\001i\021A\001\025\004\001Yb\004CA\034;\033\005A$BA\035\005\003)\tgN\\8uCRLwN\\\005\003wa\022\001#[7qY&\034\027\016\036(pi\032{WO\0343\"\003u\napQ1o]>$\bEZ5oI\002\ng\016I5na2L7-\033;!\013b,7-\036;j_:\034uN\034;fqRd\003%Z5uQ\026\024\b%[7q_J$\be]2bY\006t3m\0348dkJ\024XM\034;/\013b,7-\036;j_:\034uN\034;fqRt\023*\0349mS\016LGo\035\030hY>\024\027\r\034\021pe\002*8/\032\021bA\r,8\017^8nA=tWmB\003@\005!\005\001)\001\tFq\026\034W\017^5p]\016{g\016^3yiB\021A'\021\004\006\003\tA\tAQ\n\003\003\"AQ\001R!\005\002\025\013a\001P5oSRtD#\001!\t\013\035\013E\021\001%\002\r\035dwNY1m+\005I\005C\001\033K\023\tY%A\001\rFq\026\034W\017^5p]\016{g\016^3yi\026CXmY;u_J<Q!T!\t\0029\013\021\"S7qY&\034\027\016^:\021\005=\003V\"A!\007\013E\013\005\022\001*\003\023%k\007\017\\5dSR\0348C\001)\t\021\025!\005\013\"\001U)\005q\005\002C$Q\021\013\007I1\001%\t\021]\003\006\022!Q!\n%\013qa\0327pE\006d\007\005C\003Z\003\022\005!,A\nge>lW\t_3dkR|'oU3sm&\034W\rF\002\\=\036\004\"\001\016/\n\005u\023!aH#yK\016,H/[8o\007>tG/\032=u\013b,7-\036;peN+'O^5dK\")q\f\027a\001A\006\tQ\r\005\002bK6\t!M\003\002\004G*\021AmG\001\005kRLG.\003\002gE\nyQ\t_3dkR|'oU3sm&\034W\rC\003i1\002\007\021.\001\005sKB|'\017^3s!\021I!\016J\b\n\005-$!!\003$v]\016$\030n\03482\021\025I\026\t\"\001n)\tYf\016C\003`Y\002\007\001\rC\003q\003\022\005\021/\001\007ge>lW\t_3dkR|'\017F\002JeZDQaX8A\002M\004\"!\031;\n\005U\024'\001C#yK\016,Ho\034:\t\013!|\007\031A5\t\013A\fE\021\001=\025\005%K\b\"B0x\001\004\031\b\"B>B\t\003a\030a\0043fM\006,H\016\036*fa>\024H/\032:\026\003%\004")
/*    */ public interface ExecutionContext {
/*    */   void execute(Runnable paramRunnable);
/*    */   
/*    */   void reportFailure(Throwable paramThrowable);
/*    */   
/*    */   ExecutionContext prepare();
/*    */   
/*    */   public static class Implicits$ {
/*    */     public static final Implicits$ MODULE$;
/*    */     
/*    */     private ExecutionContextExecutor global;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     public Implicits$() {
/* 58 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public ExecutionContextExecutor global() {
/* 63 */       return this.bitmap$0 ? this.global : global$lzycompute();
/*    */     }
/*    */     
/*    */     private ExecutionContextExecutor global$lzycompute() {
/* 63 */       synchronized (this) {
/* 63 */         if (!this.bitmap$0) {
/* 63 */           ExecutionContextImpl$ executionContextImpl$1 = ExecutionContextImpl$.MODULE$;
/* 63 */           ExecutionContext$ executionContext$ = ExecutionContext$.MODULE$;
/* 63 */           ExecutionContext$$anonfun$defaultReporter$1 executionContext$$anonfun$defaultReporter$1 = new ExecutionContext$$anonfun$defaultReporter$1();
/* 63 */           ExecutionContextImpl$ executionContextImpl$2 = ExecutionContextImpl$.MODULE$;
/* 63 */           this.global = (ExecutionContextExecutor)new ExecutionContextImpl(null, (Function1)executionContext$$anonfun$defaultReporter$1);
/* 63 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/concurrent/ExecutionContext}.Lscala/concurrent/ExecutionContext$Implicits$;}} */
/* 63 */         return this.global;
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ExecutionContext$$anonfun$defaultReporter$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Throwable t) {
/* 86 */       t.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ExecutionContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */