/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.Either;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=a\001B\001\003\001\035\021A\002\0265sK\006$'+\0368oKJT!a\001\003\002\025\r|gnY;se\026tGOC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f\024VM\032\t\003\0339i\021AA\005\003\037\t\021\001CR;ukJ,G+Y:l%Vtg.\032:\t\013E\001A\021\001\n\002\rqJg.\033;?)\005\031\002CA\007\001\013\021)\002\001\001\f\003\tQ\0137o[\013\003/q\0012!\003\r\033\023\tIBAA\005Gk:\034G/[8oaA\0211\004\b\007\001\t\025iBC1\001\037\005\005!\026CA\020#!\tI\001%\003\002\"\t\t9aj\034;iS:<\007CA\005$\023\t!CAA\002B]f,AA\n\001\001O\t1a)\036;ve\026,\"\001\013\026\021\007%A\022\006\005\002\034U\021)Q$\nb\001=!)A\006\001C\002[\005qa-\0368di&|g.Q:UCN\\WC\001\0303)\tyC\007E\0021)Ej\021\001\001\t\0037I\"QaM\026C\002y\021\021a\025\005\006k-\002\rAN\001\004MVt\007cA\005\031c!)\001\b\001C\002s\005\001b-\036;ve\026\f5OR;oGRLwN\\\013\003uu\"\"a\017 \021\007%AB\b\005\002\034{\021)1g\016b\001=!)qh\016a\001\001\006\t\001\020E\0021KqBQA\021\001\005\n\r\013\001\002\036:z\007\006$8\r[\013\003\tV#\"!R,\021\t\031s\025\013\026\b\003\0172s!\001S&\016\003%S!A\023\004\002\rq\022xn\034;?\023\005)\021BA'\005\003\035\001\030mY6bO\026L!a\024)\003\r\025KG\017[3s\025\tiE\001\005\002G%&\0211\013\025\002\n\013b\034W\r\035;j_:\004\"aG+\005\013Y\013%\031\001\020\003\003\005Ca\001W!\005\002\004I\026\001\0022pIf\0042!\003.U\023\tYFA\001\005=Eft\027-\\3?\021\025i\006\001\"\001_\003\035)\0070Z2vi\026,\"aX4\025\005\001\034\007CA\005b\023\t\021GA\001\003V]&$\b\"\0023]\001\004)\027\001\002;bg.\0042\001\r\013g!\tYr\rB\00349\n\007a\004C\003j\001\021\005!.\001\004tk\nl\027\016^\013\003W:$\"\001\\8\021\007A*S\016\005\002\034]\022)1\007\033b\001=!)A\r\033a\001aB\031\001\007F7\t\013I\004A\021A:\002\0315\fg.Y4fI\ncwnY6\025\005\001$\b\"B;r\001\0041\030a\0022m_\016\\WM\035\t\003\033]L!\001\037\002\003\0355\013g.Y4fI\ncwnY6fe\"\"\021O_?\000!\tI10\003\002}\t\tQA-\0329sK\016\fG/\0323\"\003y\fq#V:fA\001\024Gn\\2lS:<\007\rI5ogR,\027\r\032\030\"\005\005\005\021A\002\032/cAr\003\007C\004\002\006\001!\t!a\002\002\021MDW\017\0363po:$\022\001\031\025\006\001i\fYa`\021\003\003\033\tq$V:fA\001,\0050Z2vi&|gnQ8oi\026DH\017\031\021j]N$X-\0313/\001")
/*    */ public class ThreadRunner implements FutureTaskRunner {
/*    */   public <S> Function0<S> functionAsTask(Function0<S> fun) {
/* 24 */     return fun;
/*    */   }
/*    */   
/*    */   public <S> Function0<S> futureAsFunction(Function0<S> x) {
/* 25 */     return x;
/*    */   }
/*    */   
/*    */   public <A> Either<Exception, A> scala$concurrent$ThreadRunner$$tryCatch(Function0 body) {
/*    */     try {
/*    */     
/* 32 */     } catch (Exception exception) {}
/* 32 */     return (Either<Exception, A>)package$.MODULE$.Left().apply(exception);
/*    */   }
/*    */   
/*    */   public <S> void execute(Function0 task) {
/* 36 */     Runnable runnable = new ThreadRunner$$anon$1(this, task);
/* 39 */     (new Thread(runnable)).start();
/*    */   }
/*    */   
/*    */   public class ThreadRunner$$anon$1 implements Runnable {
/*    */     private final Function0 task$1;
/*    */     
/*    */     public ThreadRunner$$anon$1(ThreadRunner $outer, Function0 task$1) {}
/*    */     
/*    */     public void run() {
/*    */       this.$outer.scala$concurrent$ThreadRunner$$tryCatch(this.task$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public <S> Function0<S> submit(Function0 task) {
/* 43 */     SyncVar result = new SyncVar();
/* 44 */     Runnable runnable = new ThreadRunner$$anon$2(this, task, result);
/* 47 */     (new Thread(runnable)).start();
/* 48 */     return (Function0<S>)new ThreadRunner$$anonfun$submit$1(this, result);
/*    */   }
/*    */   
/*    */   public class ThreadRunner$$anon$2 implements Runnable {
/*    */     private final Function0 task$2;
/*    */     
/*    */     private final SyncVar result$1;
/*    */     
/*    */     public ThreadRunner$$anon$2(ThreadRunner $outer, Function0 task$2, SyncVar result$1) {}
/*    */     
/*    */     public void run() {
/*    */       this.result$1.set(this.$outer.scala$concurrent$ThreadRunner$$tryCatch(this.task$2));
/*    */     }
/*    */   }
/*    */   
/*    */   public class ThreadRunner$$anonfun$submit$1 extends AbstractFunction0<S> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final SyncVar result$1;
/*    */     
/*    */     public final S apply() {
/* 48 */       return (S)((Either)this.result$1.get()).fold((Function1)new ThreadRunner$$anonfun$submit$1$$anonfun$apply$1(this), (Function1)new ThreadRunner$$anonfun$submit$1$$anonfun$apply$2(this));
/*    */     }
/*    */     
/*    */     public ThreadRunner$$anonfun$submit$1(ThreadRunner $outer, SyncVar result$1) {}
/*    */     
/*    */     public class ThreadRunner$$anonfun$submit$1$$anonfun$apply$1 extends AbstractFunction1<Exception, Nothing$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Nothing$ apply(Exception x$1) {
/* 48 */         throw x$1;
/*    */       }
/*    */       
/*    */       public ThreadRunner$$anonfun$submit$1$$anonfun$apply$1(ThreadRunner$$anonfun$submit$1 $outer) {}
/*    */     }
/*    */     
/*    */     public class ThreadRunner$$anonfun$submit$1$$anonfun$apply$2 extends AbstractFunction1<S, S> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final S apply(Object x) {
/* 48 */         return (S)Predef$.MODULE$.identity(x);
/*    */       }
/*    */       
/*    */       public ThreadRunner$$anonfun$submit$1$$anonfun$apply$2(ThreadRunner$$anonfun$submit$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public void managedBlock(ManagedBlocker blocker) {
/* 53 */     blocker.block();
/*    */   }
/*    */   
/*    */   public void shutdown() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ThreadRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */