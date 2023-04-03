/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.Actor$;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSelection;
/*    */ import akka.actor.ActorSelection$;
/*    */ import akka.actor.Status;
/*    */ import akka.actor.package$;
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.util.Failure;
/*    */ import scala.util.Success;
/*    */ import scala.util.Try;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y4q!\001\002\021\002\007\005qAA\007QSB,Gk\\*vaB|'\017\036\006\003\007\021\tq\001]1ui\026\024hNC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001A\021\001\t\002\r\021Jg.\033;%)\005\t\002CA\005\023\023\t\031\"B\001\003V]&$h\001B\013\001\005Y\021a\002U5qK\006\024G.\032$viV\024X-\006\002\030GM\021A\003\003\005\t3Q\021)\031!C\0015\0051a-\036;ve\026,\022a\007\t\0049}\tS\"A\017\013\005yQ\021AC2p]\016,(O]3oi&\021\001%\b\002\007\rV$XO]3\021\005\t\032C\002\001\003\006IQ\021\r!\n\002\002)F\021a%\013\t\003\023\035J!\001\013\006\003\0179{G\017[5oOB\021\021BK\005\003W)\0211!\0218z\021!iCC!A!\002\023Y\022a\0024viV\024X\r\t\005\t_Q\021\t\021)A\006a\005\001R\r_3dkRLwN\\\"p]R,\007\020\036\t\0039EJ!AM\017\003!\025CXmY;uS>t7i\0348uKb$\b\"\002\033\025\t\003)\024A\002\037j]&$h\b\006\0027uQ\021q'\017\t\004qQ\tS\"\001\001\t\013=\032\0049\001\031\t\013e\031\004\031A\016\t\013q\"B\021A\037\002\rAL\007/\032+p)\tqt\t\006\002\034!9\001i\017I\001\002\b\t\025AB:f]\022,'\017\005\002C\0136\t1I\003\002E\t\005)\021m\031;pe&\021ai\021\002\t\003\016$xN\035*fM\")\001j\017a\001\003\006I!/Z2ja&,g\016\036\005\006\025R!\taS\001\020a&\004X\rV8TK2,7\r^5p]R\021AJ\024\013\00375Cq\001Q%\021\002\003\017\021\tC\003I\023\002\007q\n\005\002C!&\021\021k\021\002\017\003\016$xN]*fY\026\034G/[8o\021\025\031F\003\"\001U\003\t!x\016\006\0028+\")\001J\025a\001\003\")1\013\006C\001/R\031q\007W-\t\013!3\006\031A!\t\013\0013\006\031A!\t\013M#B\021A.\025\005]b\006\"\002%[\001\004y\005\"B*\025\t\003qFcA\034`A\")\001*\030a\001\037\")\001)\030a\001\003\"9!\rFI\001\n\003\031\027\001\0059ja\026$v\016\n3fM\006,H\016\036\0233)\t!gN\013\002BK.\na\r\005\002hY6\t\001N\003\002jU\006IQO\\2iK\016\\W\r\032\006\003W*\t!\"\0318o_R\fG/[8o\023\ti\007NA\tv]\016DWmY6fIZ\013'/[1oG\026DQ\001S1A\002\005Cq\001\035\013\022\002\023\005\021/A\rqSB,Gk\\*fY\026\034G/[8oI\021,g-Y;mi\022\022DC\0013s\021\025Au\0161\001P\021\025!\b\001b\001v\003\021\001\030\016]3\026\005YTHCA<})\tA8\020E\0029)e\004\"A\t>\005\013\021\032(\031A\023\t\013=\032\b9\001\031\t\013e\031\b\031A?\021\007qy\022\020")
/*    */ public interface PipeToSupport {
/*    */   <T> PipeableFuture<T> pipe(Future<T> paramFuture, ExecutionContext paramExecutionContext);
/*    */   
/*    */   public class PipeableFuture<T> {
/*    */     private final Future<T> future;
/*    */     
/*    */     private final ExecutionContext executionContext;
/*    */     
/*    */     public Future<T> future() {
/* 14 */       return this.future;
/*    */     }
/*    */     
/*    */     public PipeableFuture(PipeToSupport $outer, Future<T> future, ExecutionContext executionContext) {}
/*    */     
/*    */     public ActorRef pipeTo$default$2(ActorRef recipient) {
/* 15 */       return Actor$.MODULE$.noSender();
/*    */     }
/*    */     
/*    */     public Future<T> pipeTo(ActorRef recipient, ActorRef sender) {
/* 16 */       future().onComplete((Function1)new PipeToSupport$PipeableFuture$$anonfun$pipeTo$1(this, recipient, (PipeableFuture<T>)sender), this.executionContext);
/* 20 */       return future();
/*    */     }
/*    */     
/*    */     public class PipeToSupport$PipeableFuture$$anonfun$pipeTo$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorRef recipient$1;
/*    */       
/*    */       private final ActorRef sender$1;
/*    */       
/*    */       public final void apply(Try x0$1) {
/*    */         Try try_ = x0$1;
/*    */         if (try_ instanceof Success) {
/*    */           Success success = (Success)try_;
/*    */           Object r = success.value();
/*    */           package$.MODULE$.actorRef2Scala(this.recipient$1).$bang(r, this.sender$1);
/*    */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */         } else {
/*    */           if (try_ instanceof Failure) {
/*    */             Failure failure = (Failure)try_;
/*    */             Throwable f = failure.exception();
/*    */             package$.MODULE$.actorRef2Scala(this.recipient$1).$bang(new Status.Failure(f), this.sender$1);
/*    */             BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */             return;
/*    */           } 
/*    */           throw new MatchError(try_);
/*    */         } 
/*    */       }
/*    */       
/*    */       public PipeToSupport$PipeableFuture$$anonfun$pipeTo$1(PipeToSupport.PipeableFuture $outer, ActorRef recipient$1, ActorRef sender$1) {}
/*    */     }
/*    */     
/*    */     public ActorRef pipeToSelection$default$2(ActorSelection recipient) {
/* 22 */       return Actor$.MODULE$.noSender();
/*    */     }
/*    */     
/*    */     public Future<T> pipeToSelection(ActorSelection recipient, ActorRef sender) {
/* 23 */       future().onComplete((Function1)new PipeToSupport$PipeableFuture$$anonfun$pipeToSelection$1(this, recipient, (PipeableFuture<T>)sender), this.executionContext);
/* 27 */       return future();
/*    */     }
/*    */     
/*    */     public class PipeToSupport$PipeableFuture$$anonfun$pipeToSelection$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorSelection recipient$2;
/*    */       
/*    */       private final ActorRef sender$2;
/*    */       
/*    */       public final void apply(Try x0$2) {
/*    */         Try try_ = x0$2;
/*    */         if (try_ instanceof Success) {
/*    */           Success success = (Success)try_;
/*    */           Object r = success.value();
/*    */           ActorSelection$.MODULE$.toScala(this.recipient$2).$bang(r, this.sender$2);
/*    */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */         } else {
/*    */           if (try_ instanceof Failure) {
/*    */             Failure failure = (Failure)try_;
/*    */             Throwable f = failure.exception();
/*    */             ActorSelection$.MODULE$.toScala(this.recipient$2).$bang(new Status.Failure(f), this.sender$2);
/*    */             BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */             return;
/*    */           } 
/*    */           throw new MatchError(try_);
/*    */         } 
/*    */       }
/*    */       
/*    */       public PipeToSupport$PipeableFuture$$anonfun$pipeToSelection$1(PipeToSupport.PipeableFuture $outer, ActorSelection recipient$2, ActorRef sender$2) {}
/*    */     }
/*    */     
/*    */     public PipeableFuture<T> to(ActorRef recipient) {
/* 29 */       return to(recipient, Actor$.MODULE$.noSender());
/*    */     }
/*    */     
/*    */     public PipeableFuture<T> to(ActorRef recipient, ActorRef sender) {
/* 31 */       pipeTo(recipient, sender);
/* 32 */       return this;
/*    */     }
/*    */     
/*    */     public PipeableFuture<T> to(ActorSelection recipient) {
/* 34 */       return to(recipient, Actor$.MODULE$.noSender());
/*    */     }
/*    */     
/*    */     public PipeableFuture<T> to(ActorSelection recipient, ActorRef sender) {
/* 36 */       pipeToSelection(recipient, sender);
/* 37 */       return this;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\PipeToSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */