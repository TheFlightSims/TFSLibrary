/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-eaB\001\003!\003\r\t!\003\002\020)\"\024X-\0313Q_>dG+Y:lg*\0211\001B\001\ta\006\024\030\r\0347fY*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001M\031\001A\003\b\021\005-aQ\"\001\004\n\00551!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\t)A+Y:lg\")1\003\001C\001)\0051A%\0338ji\022\"\022!\006\t\003\027YI!a\006\004\003\tUs\027\016\036\004\b3\001\001\n1!\001\033\005-9&/\0319qK\022$\026m]6\026\007macg\005\003\0319\021:\003CA\017#\033\005q\"BA\020!\003\021a\027M\\4\013\003\005\nAA[1wC&\0211E\b\002\007\037\nTWm\031;\021\005u)\023B\001\024\037\005!\021VO\0348bE2,\007\003\002\025*UUj\021\001A\005\0033A\001\"a\013\027\r\001\021)Q\006\007b\001]\t\t!+\005\0020eA\0211\002M\005\003c\031\021qAT8uQ&tw\r\005\002\fg%\021AG\002\002\004\003:L\bCA\0267\t\0319\004\004\"b\001]\t\021A\013\035\005\006'a!\t\001\006\005\bua\001\r\021\"\001<\003\025ywO\\3e+\005a\004CA\006>\023\tqdAA\004C_>dW-\0318\t\017\001C\002\031!C\001\003\006Iqn\0368fI~#S-\035\013\003+\tCqaQ \002\002\003\007A(A\002yIEBa!\022\r!B\023a\024AB8x]\026$\007\005\013\002E\017B\0211\002S\005\003\023\032\021\001B^8mCRLG.\032\005\b\027b\001\r\021\"\001<\003%\031w.\0349mKR,G\rC\004N1\001\007I\021\001(\002\033\r|W\016\0357fi\026$w\fJ3r)\t)r\nC\004D\031\006\005\t\031\001\037\t\rEC\002\025)\003=\003)\031w.\0349mKR,G\r\t\025\003!\036CQ\001\026\r\005\002Q\tQa\035;beRDQA\026\r\005\002Q\tAa]=oG\")\001\f\007C\0013\006IAO]=DC:\034W\r\034\013\002y!)1\f\007C\001)\005\031!/\0368\t\013uCB\021\t\013\002\017I,G.Z1tK\")q\f\001D\tA\006qa.Z<Xe\006\004\b/\0323UCN\\WcA1eMR\021!m\032\t\005Qa\031W\r\005\002,I\022)QF\030b\001]A\0211F\032\003\006oy\023\rA\f\005\006Qz\003\r![\001\002EB!qB[2f\023\tY'A\001\003UCN\\\007bB7\001\005\0045\tA\\\001\fK:4\030N]8o[\026tG/F\001p!\t\001X/D\001r\025\t\0218/\001\006d_:\034WO\035:f]RT!\001\036\021\002\tU$\030\016\\\005\003mF\024!\003\0265sK\006$\007k\\8m\013b,7-\036;pe\")\001\020\001C\001]\006AQ\r_3dkR|'\017C\003{\001\021\00510A\003rk\026,X-F\001}!\r\001X\020J\005\003}F\0241\003T5oW\026$'\t\\8dW&tw-U;fk\026D\021\"!\001\001\001\004%\t!a\001\002\025Q|G/\0317uCN\\7/\006\002\002\006A\0311\"a\002\n\007\005%aAA\002J]RD\021\"!\004\001\001\004%\t!a\004\002\035Q|G/\0317uCN\\7o\030\023fcR\031Q#!\005\t\023\r\013Y!!AA\002\005\025\001\002CA\013\001\001\006K!!\002\002\027Q|G/\0317uCN\\7\017\t\025\004\003'9\005BBA\016\001\021%A#A\005j]\016\024H+Y:lg\"1\021q\004\001\005\nQ\t\021\002Z3deR\0137o[:\t\017\005\r\002\001\"\001\002&\0059Q\r_3dkR,WCBA\024\003c\tY\004\006\003\002*\005M\002#B\006\002,\005=\022bAA\027\r\tIa)\0368di&|g\016\r\t\004W\005EBAB\027\002\"\t\007a\006\003\005\0026\005\005\002\031AA\034\003\021!\030m]6\021\r=Q\027qFA\035!\rY\0231\b\003\007o\005\005\"\031\001\030\t\017\005}\002\001\"\001\002B\005!R\r_3dkR,\027I\0343XC&$(+Z:vYR,b!a\021\002H\005=C\003BA#\003\023\0022aKA$\t\031i\023Q\bb\001]!A\021QGA\037\001\004\tY\005\005\004\020U\006\025\023Q\n\t\004W\005=CAB\034\002>\t\007a\006C\004\002T\001!\t!a\001\002!A\f'/\0317mK2L7/\034'fm\026dwaBA,\005!\005\021\021L\001\020)\"\024X-\0313Q_>dG+Y:lgB\031q\"a\027\007\r\005\021\001\022AA/'\r\tYF\003\005\t\003C\nY\006\"\001\002d\0051A(\0338jiz\"\"!!\027\t\025\005\035\0241\fb\001\n\003\t\031!\001\005ok6\034uN]3t\021%\tY'a\027!\002\023\t)!A\005ok6\034uN]3tA!Q\021qNA.\005\004%\t!!\035\002\rQ\034w.\0368u+\t\t\031\b\005\003\002v\005mTBAA<\025\r\tI(]\001\007CR|W.[2\n\t\005u\024q\017\002\013\003R|W.[2M_:<\007\"CAA\0037\002\013\021BA:\003\035!8m\\;oi\002B\021\"!\"\002\\\t\007I\021\0018\002#\021,g-Y;miRC'/Z1e!>|G\016\003\005\002\n\006m\003\025!\003p\003I!WMZ1vYR$\006N]3bIB{w\016\034\021")
/*     */ public interface ThreadPoolTasks extends Tasks {
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   ThreadPoolExecutor environment();
/*     */   
/*     */   ThreadPoolExecutor executor();
/*     */   
/*     */   LinkedBlockingQueue<Runnable> queue();
/*     */   
/*     */   int totaltasks();
/*     */   
/*     */   @TraitSetter
/*     */   void totaltasks_$eq(int paramInt);
/*     */   
/*     */   <R, Tp> Function0<R> execute(Task<R, Tp> paramTask);
/*     */   
/*     */   <R, Tp> R executeAndWaitResult(Task<R, Tp> paramTask);
/*     */   
/*     */   int parallelismLevel();
/*     */   
/*     */   public abstract class WrappedTask$class {
/*     */     public static void $init$(ThreadPoolTasks.WrappedTask $this) {
/* 235 */       $this.owned_$eq(false);
/* 236 */       $this.completed_$eq(false);
/*     */     }
/*     */     
/*     */     public static void start(ThreadPoolTasks.WrappedTask $this) {
/* 238 */       synchronized ($this) {
/* 241 */         synchronized ($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor()) {
/* 242 */           ThreadPoolTasks$class.scala$collection$parallel$ThreadPoolTasks$$incrTasks($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer());
/* 243 */           $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().submit($this);
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{scala/collection/parallel/ThreadPoolTasks}.Lscala/collection/parallel/ThreadPoolTasks$WrappedTask;}, name=$this} */
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_1} */
/*     */       throw null;
/*     */     }
/*     */     
/*     */     public static void sync(ThreadPoolTasks.WrappedTask $this) {
/* 246 */       synchronized ($this) {
/* 249 */         synchronized ($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor()) {
/* 250 */           int coresize = $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().getCorePoolSize();
/* 251 */           if (coresize < $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().totaltasks())
/* 252 */             $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().setCorePoolSize(coresize + 1); 
/*     */           while (true) {
/* 256 */             if ($this.completed())
/*     */               /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{scala/collection/parallel/ThreadPoolTasks}.Lscala/collection/parallel/ThreadPoolTasks$WrappedTask;}, name=$this} */ 
/* 256 */             $this.wait();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_1} */
/*     */       throw null;
/*     */     }
/*     */     
/*     */     public static boolean tryCancel(ThreadPoolTasks.WrappedTask $this) {
/* 258 */       synchronized ($this) {
/* 262 */         $this.owned_$eq(true);
/* 263 */         Boolean bool = $this.owned() ? 
/* 264 */           BoxesRunTime.boxToBoolean(false) : BoxesRunTime.boxToBoolean(true);
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{scala/collection/parallel/ThreadPoolTasks}.Lscala/collection/parallel/ThreadPoolTasks$WrappedTask;}, name=$this} */
/*     */         return BoxesRunTime.unboxToBoolean(bool);
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void run(ThreadPoolTasks.WrappedTask $this) {
/* 268 */       boolean isOkToRun = false;
/* 269 */       synchronized ($this) {
/* 270 */         if (!$this.owned()) {
/* 271 */           $this.owned_$eq(true);
/* 272 */           isOkToRun = true;
/*     */         } 
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{scala/collection/parallel/ThreadPoolTasks}.Lscala/collection/parallel/ThreadPoolTasks$WrappedTask;}, name=$this} */
/* 275 */         if (isOkToRun)
/* 277 */           $this.compute(); 
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void release(ThreadPoolTasks.WrappedTask $this) {
/* 283 */       synchronized ($this) {
/* 285 */         $this.completed_$eq(true);
/* 286 */         synchronized ($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor()) {
/* 287 */           ThreadPoolTasks$class.scala$collection$parallel$ThreadPoolTasks$$decrTasks($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer());
/* 289 */           $this.notifyAll();
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{scala/collection/parallel/ThreadPoolTasks}.Lscala/collection/parallel/ThreadPoolTasks$WrappedTask;}, name=$this} */
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_1} */
/*     */       throw null;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ThreadPoolTasks$$anonfun$execute$1 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ThreadPoolTasks.WrappedTask t$1;
/*     */     
/*     */     public ThreadPoolTasks$$anonfun$execute$1(ThreadPoolTasks $outer, ThreadPoolTasks.WrappedTask t$1) {}
/*     */     
/*     */     public final R apply() {
/* 315 */       this.t$1.sync();
/* 316 */       this.t$1.body().forwardThrowable();
/* 317 */       return (R)this.t$1.body().result();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 implements ThreadFactory {
/*     */     public Thread newThread(Runnable r) {
/* 350 */       Thread t = new Thread(r);
/* 351 */       t.setName((new StringBuilder()).append("pc-thread-").append(BoxesRunTime.boxToLong(ThreadPoolTasks$.MODULE$.tcount().incrementAndGet())).toString());
/* 352 */       t.setDaemon(true);
/* 353 */       return t;
/*     */     }
/*     */   }
/*     */   
/*     */   public interface WrappedTask<R, Tp> extends Runnable, Tasks.WrappedTask<R, Tp> {
/*     */     boolean owned();
/*     */     
/*     */     @TraitSetter
/*     */     void owned_$eq(boolean param1Boolean);
/*     */     
/*     */     boolean completed();
/*     */     
/*     */     @TraitSetter
/*     */     void completed_$eq(boolean param1Boolean);
/*     */     
/*     */     void start();
/*     */     
/*     */     void sync();
/*     */     
/*     */     boolean tryCancel();
/*     */     
/*     */     void run();
/*     */     
/*     */     void release();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ThreadPoolTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */