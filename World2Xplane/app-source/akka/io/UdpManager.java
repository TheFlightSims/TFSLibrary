/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.Props;
/*    */ import akka.actor.Props$;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Traversable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0252Q!\001\002\001\005\031\021!\"\0263q\033\006t\027mZ3s\025\t\031A!\001\002j_*\tQ!\001\003bW.\f7C\001\001\b!\tAAB\004\002\n\0255\t!!\003\002\f\005\005\0012+\0327fGRLwN\034%b]\022dWM]\005\003\0339\021AcU3mK\016$xN\035\"bg\026$W*\0318bO\026\024(BA\006\003\021!\001\002A!A!\002\023\021\022aA;ea\016\001\001CA\005\024\023\t!\"A\001\004VIB,\005\020\036\005\006-\001!\taF\001\007y%t\027\016\036 \025\005aI\002CA\005\001\021\025\001R\0031\001\023\021\025Y\002\001\"\001\035\003\035\021XmY3jm\026,\022!\b\t\003=}i\021\001A\005\003A\005\022qAU3dK&4X-\003\002#G\t)\021i\031;pe*\021A\005B\001\006C\016$xN\035")
/*    */ public class UdpManager extends SelectionHandler.SelectorBasedManager {
/*    */   public final UdpExt akka$io$UdpManager$$udp;
/*    */   
/*    */   public UdpManager(UdpExt udp) {
/* 46 */     super(udp.settings(), udp.settings().NrOfSelectors());
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 48 */     return workerForCommandHandler((PartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>>)new UdpManager$$anonfun$receive$1(this));
/*    */   }
/*    */   
/*    */   public class UdpManager$$anonfun$receive$1 extends AbstractPartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1 extends SelectionHandler.HasFailureMessage, B1> B1 applyOrElse(SelectionHandler.HasFailureMessage x1, Function1 default) {
/*    */       Object object;
/* 48 */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/* 49 */       if (hasFailureMessage instanceof Udp.Bind) {
/* 49 */         Udp.Bind bind = (Udp.Bind)hasFailureMessage;
/* 50 */         ActorRef commander = this.$outer.sender();
/* 51 */         object = new UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$1(this, commander, bind);
/* 53 */       } else if (hasFailureMessage instanceof Udp.SimpleSender) {
/* 53 */         Udp.SimpleSender simpleSender = (Udp.SimpleSender)hasFailureMessage;
/* 53 */         Traversable<Inet.SocketOption> options = simpleSender.options();
/* 54 */         ActorRef commander = this.$outer.sender();
/* 55 */         object = new UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$2(this, options, commander);
/*    */       } else {
/*    */         object = default.apply(x1);
/*    */       } 
/*    */       return (B1)object;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(SelectionHandler.HasFailureMessage x1) {
/*    */       boolean bool;
/*    */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/*    */       if (hasFailureMessage instanceof Udp.Bind) {
/*    */         bool = true;
/*    */       } else if (hasFailureMessage instanceof Udp.SimpleSender) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public UdpManager$$anonfun$receive$1(UdpManager $outer) {}
/*    */     
/*    */     public class UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$1 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorRef commander$1;
/*    */       
/*    */       private final Udp.Bind x3$1;
/*    */       
/*    */       public final Props apply(ChannelRegistry registry) {
/*    */         return Props$.MODULE$.apply(UdpListener.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (this.$outer.akka$io$UdpManager$$anonfun$$$outer()).akka$io$UdpManager$$udp, registry, this.commander$1, this.x3$1 }));
/*    */       }
/*    */       
/*    */       public UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$1(UdpManager$$anonfun$receive$1 $outer, ActorRef commander$1, Udp.Bind x3$1) {}
/*    */     }
/*    */     
/*    */     public class UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$2 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Traversable options$1;
/*    */       
/*    */       private final ActorRef commander$2;
/*    */       
/*    */       public final Props apply(ChannelRegistry registry) {
/* 55 */         return Props$.MODULE$.apply(UdpSender.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (this.$outer.akka$io$UdpManager$$anonfun$$$outer()).akka$io$UdpManager$$udp, registry, this.commander$2, this.options$1 }));
/*    */       }
/*    */       
/*    */       public UdpManager$$anonfun$receive$1$$anonfun$applyOrElse$2(UdpManager$$anonfun$receive$1 $outer, Traversable options$1, ActorRef commander$2) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */