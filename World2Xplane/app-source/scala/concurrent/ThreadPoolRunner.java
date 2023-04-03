/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Future;
/*    */ import scala.Function0;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055baB\001\003!\003\r\ta\002\002\021)\"\024X-\0313Q_>d'+\0368oKJT!a\001\003\002\025\r|gnY;se\026tGOC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f\024VM\032\t\003\0339i\021AA\005\003\037\t\021\001CR;ukJ,G+Y:l%Vtg.\032:\t\013E\001A\021\001\n\002\r\021Jg.\033;%)\005\031\002CA\005\025\023\t)BA\001\003V]&$X\001B\f\001\001a\021A\001V1tWV\021\021d\n\n\0045q\001d\001B\016\001\001e\021A\002\020:fM&tW-\\3oiz\0022!H\022&\033\005q\"BA\002 \025\t\001\023%\001\003vi&d'\"\001\022\002\t)\fg/Y\005\003Iy\021\001bQ1mY\006\024G.\032\t\003M\035b\001\001B\003)-\t\007\021FA\001U#\tQS\006\005\002\nW%\021A\006\002\002\b\035>$\b.\0338h!\tIa&\003\0020\t\t\031\021I\\=\021\005E\"T\"\001\032\013\005M\n\023\001\0027b]\036L!!\016\032\003\021I+hN\\1cY\026,Aa\016\001\001q\t1a)\036;ve\026,\"!\017\037\021\007uQ4(\003\0028=A\021a\005\020\003\006QY\022\r!\013\004\005}\001!qHA\006Sk:\034\025\r\0347bE2,WC\001!G'\021i\024\t\r#\021\005E\022\025BA\"3\005\031y%M[3diB\031QdI#\021\005\0312E!B$>\005\004I#!A*\t\021%k$\021!Q\001\n)\0131AZ;o!\rI1*R\005\003\031\022\021\021BR;oGRLwN\034\031\t\0139kD\021A(\002\rqJg.\033;?)\t\001&\013E\002R{\025k\021\001\001\005\006\0236\003\rA\023\005\006)v\"\tAE\001\004eVt\007\"\002,>\t\0039\026\001B2bY2$\022!\022\005\0063\002!\031AW\001\017MVt7\r^5p]\006\033H+Y:l+\tYf\f\006\002]?B\031\021KF/\021\005\031rF!B$Y\005\004I\003\"B%Y\001\004\001\007cA\005L;\")!\r\001C\002G\006\001b-\036;ve\026\f5OR;oGRLwN\\\013\003I\036$\"!\0325\021\007%Ye\r\005\002'O\022)q)\031b\001S!)\021.\031a\001U\006\t\001\020E\002Rm\031DQ\001\034\001\007\0225\f\001\"\032=fGV$xN]\013\002]B\021Qd\\\005\003az\021q\"\022=fGV$xN]*feZL7-\032\005\006e\002!\ta]\001\007gV\024W.\033;\026\005Q<HCA;y!\r\tfG\036\t\003M]$QaR9C\002%BQ!_9A\002i\fA\001^1tWB\031\021K\006<\t\013q\004A\021A?\002\017\025DXmY;uKV\031a0!\002\025\005My\bBB=|\001\004\t\t\001\005\003R-\005\r\001c\001\024\002\006\021)qi\037b\001S!9\021\021\002\001\005\002\005-\021\001D7b]\006<W\r\032\"m_\016\\GcA\n\002\016!A\021qBA\004\001\004\t\t\"A\004cY>\0347.\032:\021\0075\t\031\"C\002\002\026\t\021a\"T1oC\036,GM\0217pG.,'\017\013\005\002\b\005e\021qDA\022!\rI\0211D\005\004\003;!!A\0033faJ,7-\031;fI\006\022\021\021E\001\030+N,\007\005\0312m_\016\\\027N\\4aA%t7\017^3bI:\n#!!\n\002\rIr\023\007\r\0301Q\035\001\021\021DA\025\003G\t#!a\013\002?U\033X\r\t1Fq\026\034W\017^5p]\016{g\016^3yi\002\004\023N\\:uK\006$g\006")
/*    */ public interface ThreadPoolRunner extends FutureTaskRunner {
/*    */   <S> Callable<S> functionAsTask(Function0<S> paramFunction0);
/*    */   
/*    */   <S> Function0<S> futureAsFunction(Future<S> paramFuture);
/*    */   
/*    */   ExecutorService executor();
/*    */   
/*    */   <S> Future<S> submit(Callable<S> paramCallable);
/*    */   
/*    */   <S> void execute(Callable<S> paramCallable);
/*    */   
/*    */   void managedBlock(ManagedBlocker paramManagedBlocker);
/*    */   
/*    */   public class RunCallable<S> implements Runnable, Callable<S> {
/*    */     private final Function0<S> fun;
/*    */     
/*    */     public RunCallable(ThreadPoolRunner $outer, Function0<S> fun) {}
/*    */     
/*    */     public void run() {
/* 26 */       this.fun.apply();
/*    */     }
/*    */     
/*    */     public S call() {
/* 27 */       return (S)this.fun.apply();
/*    */     }
/*    */   }
/*    */   
/*    */   public class ThreadPoolRunner$$anonfun$futureAsFunction$1 extends AbstractFunction0<S> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Future x$1;
/*    */     
/*    */     public final S apply() {
/* 34 */       return this.x$1.get();
/*    */     }
/*    */     
/*    */     public ThreadPoolRunner$$anonfun$futureAsFunction$1(ThreadPoolRunner $outer, Future x$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ThreadPoolRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */