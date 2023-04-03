/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.actor.ActorCell;
/*    */ import java.util.concurrent.BlockingQueue;
/*    */ import java.util.concurrent.RejectedExecutionHandler;
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.concurrent.duration.Duration$;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}3A!\001\002\001\017\t\001\002+\0338oK\022$\025n\0359bi\016DWM\035\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%QQ\"\001\002\n\005-\021!A\003#jgB\fGo\0315fe\"IQ\002\001B\001B\003%a\"E\001\016?\016|gNZ5hkJ\fGo\034:\021\005%y\021B\001\t\003\005uiUm]:bO\026$\025n\0359bi\016DWM]\"p]\032Lw-\036:bi>\024\030B\001\n\024\0031\031wN\0344jOV\024\030\r^8s\023\t!\"AA\tNKN\034\030mZ3ESN\004\030\r^2iKJD\001B\006\001\003\002\003\006IaF\001\007?\006\034Go\034:\021\005aYR\"A\r\013\005i!\021!B1di>\024\030B\001\017\032\005%\t5\r^8s\007\026dG\016C\005\037\001\t\005\t\025!\003 S\005\031q,\0333\021\005\0012cBA\021%\033\005\021#\"A\022\002\013M\034\027\r\\1\n\005\025\022\023A\002)sK\022,g-\003\002(Q\t11\013\036:j]\036T!!\n\022\n\005)R\021AA5e\021%a\003A!A!\002\023iS'\001\t`g\",H\017Z8x]RKW.Z8viB\021afM\007\002_)\021\001'M\001\tIV\024\030\r^5p]*\021!GI\001\013G>t7-\036:sK:$\030B\001\0330\00591\025N\\5uK\022+(/\031;j_:L!A\016\006\002\037MDW\017\0363po:$\026.\\3pkRD\001\002\017\001\003\002\003\006I!O\001\022?RD'/Z1e!>|GnQ8oM&<\007CA\005;\023\tY$A\001\tUQJ,\027\r\032)p_2\034uN\0344jO\")Q\b\001C\001}\0051A(\0338jiz\"ba\020!B\005\016#\005CA\005\001\021\025iA\b1\001\017\021\0251B\b1\001\030\021\025qB\b1\001 \021\025aC\b1\001.\021\025AD\b1\001:\021\0351\005\0011A\005\n\035\013Qa\\<oKJ,\022a\006\005\b\023\002\001\r\021\"\003K\003%ywO\\3s?\022*\027\017\006\002L\035B\021\021\005T\005\003\033\n\022A!\0268ji\"9q\nSA\001\002\0049\022a\001=%c!1\021\013\001Q!\n]\taa\\<oKJ\004\003F\001)T!\t\tC+\003\002VE\tAao\0347bi&dW\r\003\004X\001\021EC\001W\001\te\026<\027n\035;feR\0211*\027\005\0065Z\003\raF\001\nC\016$xN]\"fY2Da\001\030\001\005R\021i\026AC;oe\026<\027n\035;feR\0211J\030\005\0065m\003\ra\006")
/*    */ public class PinnedDispatcher extends Dispatcher {
/*    */   private volatile ActorCell owner;
/*    */   
/*    */   public PinnedDispatcher(MessageDispatcherConfigurator _configurator, ActorCell _actor, String _id, FiniteDuration _shutdownTimeout, ThreadPoolConfig _threadPoolConfig) {
/* 17 */     super(
/*    */         
/* 23 */         _configurator, 
/* 24 */         _id, 
/* 25 */         2147483647, 
/* 26 */         (Duration)Duration$.MODULE$.Zero(), 
/* 27 */         _threadPoolConfig.copy(x$3, x$1, x$2, x$4, x$5, x$6), 
/* 28 */         _shutdownTimeout);
/* 31 */     this.owner = _actor;
/*    */   }
/*    */   
/*    */   private ActorCell owner() {
/* 31 */     return this.owner;
/*    */   }
/*    */   
/*    */   private void owner_$eq(ActorCell x$1) {
/* 31 */     this.owner = x$1;
/*    */   }
/*    */   
/*    */   public void register(ActorCell actorCell) {
/* 35 */     ActorCell actor = owner();
/* 36 */     if (actor != null) {
/* 36 */       ActorCell actorCell1 = actor;
/* 36 */       if (actorCell == null) {
/* 36 */         if (actorCell1 != null)
/* 36 */           throw new IllegalArgumentException((new StringBuilder()).append("Cannot register to anyone but ").append(actor).toString()); 
/* 36 */       } else if (!actorCell.equals(actorCell1)) {
/* 36 */         throw new IllegalArgumentException((new StringBuilder()).append("Cannot register to anyone but ").append(actor).toString());
/*    */       } 
/*    */     } 
/* 37 */     owner_$eq(actorCell);
/* 38 */     super.register(actorCell);
/*    */   }
/*    */   
/*    */   public void unregister(ActorCell actor) {
/* 42 */     super.unregister(actor);
/* 43 */     null;
/* 43 */     owner_$eq(null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\PinnedDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */