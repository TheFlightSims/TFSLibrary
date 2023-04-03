/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorRef$;
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.AddressTerminated;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A<a!\001\002\t\002\0211\021AF!eIJ,7o\035+fe6Lg.\031;fIR{\007/[2\013\005\r!\021!B3wK:$(\"A\003\002\t\005\\7.\031\t\003\017!i\021A\001\004\007\023\tA\t\001\002\006\003-\005#GM]3tgR+'/\\5oCR,G\rV8qS\016\034B\001C\006\0221B\021AbD\007\002\033)\ta\"A\003tG\006d\027-\003\002\021\033\t1\021I\\=SK\032\0042AE\013\030\033\005\031\"B\001\013\005\003\025\t7\r^8s\023\t12CA\006FqR,gn]5p]&#\007CA\004\031\r\025I!A\001\003\032'\rA2B\007\t\003%mI!\001H\n\003\023\025CH/\0328tS>t\007\"\002\020\031\t\003\001\023A\002\037j]&$hh\001\001\025\003]AqA\t\rC\002\023%1%A\006tk\n\0348M]5cKJ\034X#\001\023\021\007\025r\003'D\001'\025\t9\003&\001\004bi>l\027n\031\006\003S)\n!bY8oGV\024(/\0328u\025\tYC&\001\003vi&d'\"A\027\002\t)\fg/Y\005\003_\031\022q\"\021;p[&\034'+\0324fe\026t7-\032\t\004cQ:dB\001\0073\023\t\031T\"\001\004Qe\026$WMZ\005\003kY\0221aU3u\025\t\031T\002\005\002\023q%\021\021h\005\002\t\003\016$xN\035*fM\"11\b\007Q\001\n\021\nAb];cg\016\024\030NY3sg\002BQ!\020\r\005\002y\n\021b];cg\016\024\030NY3\025\005}\022\005C\001\007A\023\t\tUB\001\003V]&$\b\"B\"=\001\0049\024AC:vEN\034'/\0332fe\"\022A(\022\t\003\r&k\021a\022\006\003\0216\t!\"\0318o_R\fG/[8o\023\tQuIA\004uC&d'/Z2\t\0131CB\021A'\002\027Ut7/\0362tGJL'-\032\013\0039CQaQ&A\002]B#aS#\t\013ECB\021\001*\002\017A,(\r\\5tQR\021qh\025\005\006)B\003\r!V\001\004[N<\007C\001\nW\023\t96CA\tBI\022\024Xm]:UKJl\027N\\1uK\022\004\"AE-\n\005i\033\"aE#yi\026t7/[8o\023\022\004&o\034<jI\026\024\b\"\002\020\t\t\003aF#\001\004\t\013yCA\021I0\002\007\035,G\017\006\002\030A\")\021-\030a\001E\00611/_:uK6\004\"AE2\n\005\021\034\"aC!di>\0248+_:uK6DQA\032\005\005B\035\fa\001\\8pWV\004H#\0015\017\005\035\001\001\"\0026\t\t\003Z\027aD2sK\006$X-\022=uK:\034\030n\0348\025\005]a\007\"B1j\001\004i\007C\001\no\023\ty7CA\nFqR,g\016Z3e\003\016$xN]*zgR,W\016")
/*    */ public final class AddressTerminatedTopic implements Extension {
/* 38 */   private final AtomicReference<Set<ActorRef>> subscribers = new AtomicReference<Set<ActorRef>>(Predef$.MODULE$.Set().empty());
/*    */   
/*    */   public static Extension apply(ActorSystem paramActorSystem) {
/*    */     return AddressTerminatedTopic$.MODULE$.apply(paramActorSystem);
/*    */   }
/*    */   
/*    */   public static AddressTerminatedTopic createExtension(ExtendedActorSystem paramExtendedActorSystem) {
/*    */     return AddressTerminatedTopic$.MODULE$.createExtension(paramExtendedActorSystem);
/*    */   }
/*    */   
/*    */   public static AddressTerminatedTopic$ lookup() {
/*    */     return AddressTerminatedTopic$.MODULE$.lookup();
/*    */   }
/*    */   
/*    */   public static AddressTerminatedTopic get(ActorSystem paramActorSystem) {
/*    */     return AddressTerminatedTopic$.MODULE$.get(paramActorSystem);
/*    */   }
/*    */   
/*    */   private AtomicReference<Set<ActorRef>> subscribers() {
/* 38 */     return this.subscribers;
/*    */   }
/*    */   
/*    */   public void subscribe(ActorRef subscriber) {
/*    */     while (true) {
/* 41 */       Set current = subscribers().get();
/* 42 */       if (subscribers().compareAndSet(current, current.$plus(subscriber)))
/*    */         return; 
/* 43 */       subscriber = subscriber;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void unsubscribe(ActorRef subscriber) {
/*    */     while (true) {
/* 47 */       Set current = subscribers().get();
/* 48 */       if (subscribers().compareAndSet(current, current.$minus(subscriber)))
/*    */         return; 
/* 49 */       subscriber = subscriber;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void publish(AddressTerminated msg) {
/* 53 */     ((IterableLike)subscribers().get()).foreach((Function1)new AddressTerminatedTopic$$anonfun$publish$1(this, msg));
/*    */   }
/*    */   
/*    */   public class AddressTerminatedTopic$$anonfun$publish$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final AddressTerminated msg$1;
/*    */     
/*    */     public final void apply(ActorRef x$1) {
/* 53 */       x$1.tell(this.msg$1, ActorRef$.MODULE$.noSender());
/*    */     }
/*    */     
/*    */     public AddressTerminatedTopic$$anonfun$publish$1(AddressTerminatedTopic $outer, AddressTerminated msg$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\AddressTerminatedTopic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */