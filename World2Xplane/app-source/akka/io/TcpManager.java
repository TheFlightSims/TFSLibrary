/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorLogging;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.Props;
/*    */ import akka.actor.Props$;
/*    */ import akka.event.LoggingAdapter;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001%2Q!\001\002\001\005\031\021!\002V2q\033\006t\027mZ3s\025\t\031A!\001\002j_*\tQ!\001\003bW.\f7c\001\001\b\037A\021\001\002\004\b\003\023)i\021AA\005\003\027\t\t\001cU3mK\016$\030n\0348IC:$G.\032:\n\0055q!\001F*fY\026\034Go\034:CCN,G-T1oC\036,'O\003\002\f\005A\021\001cE\007\002#)\021!\003B\001\006C\016$xN]\005\003)E\021A\"Q2u_JdunZ4j]\036D\001B\006\001\003\002\003\006I\001G\001\004i\016\0048\001\001\t\003\023eI!A\007\002\003\rQ\033\007/\022=u\021\025a\002\001\"\001\036\003\031a\024N\\5u}Q\021ad\b\t\003\023\001AQAF\016A\002aAQ!\t\001\005\002\t\nqA]3dK&4X-F\001$!\t!S%D\001\001\023\t1sEA\004SK\016,\027N^3\n\005!\n\"!B!di>\024\b")
/*    */ public class TcpManager extends SelectionHandler.SelectorBasedManager implements ActorLogging {
/*    */   public final TcpExt akka$io$TcpManager$$tcp;
/*    */   
/*    */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*    */   
/*    */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/* 47 */     return this.akka$actor$ActorLogging$$_log;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/* 47 */     this.akka$actor$ActorLogging$$_log = x$1;
/*    */   }
/*    */   
/*    */   public LoggingAdapter log() {
/* 47 */     return ActorLogging.class.log(this);
/*    */   }
/*    */   
/*    */   public TcpManager(TcpExt tcp) {
/* 47 */     super(
/* 48 */         tcp.Settings(), tcp.Settings().NrOfSelectors());
/*    */     ActorLogging.class.$init$(this);
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 50 */     return workerForCommandHandler((PartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>>)new TcpManager$$anonfun$receive$1(this));
/*    */   }
/*    */   
/*    */   public class TcpManager$$anonfun$receive$1 extends AbstractPartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1 extends SelectionHandler.HasFailureMessage, B1> B1 applyOrElse(SelectionHandler.HasFailureMessage x1, Function1 default) {
/*    */       Object object;
/* 50 */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/* 51 */       if (hasFailureMessage instanceof Tcp.Connect) {
/* 51 */         Tcp.Connect connect = (Tcp.Connect)hasFailureMessage;
/* 52 */         ActorRef commander = this.$outer.sender();
/* 53 */         object = new TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$1(this, commander, connect);
/* 55 */       } else if (hasFailureMessage instanceof Tcp.Bind) {
/* 55 */         Tcp.Bind bind = (Tcp.Bind)hasFailureMessage;
/* 56 */         ActorRef commander = this.$outer.sender();
/* 57 */         object = new TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$2(this, commander, bind);
/*    */       } else {
/*    */         object = default.apply(x1);
/*    */       } 
/*    */       return (B1)object;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(SelectionHandler.HasFailureMessage x1) {
/*    */       boolean bool;
/*    */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/*    */       if (hasFailureMessage instanceof Tcp.Connect) {
/*    */         bool = true;
/*    */       } else if (hasFailureMessage instanceof Tcp.Bind) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public TcpManager$$anonfun$receive$1(TcpManager $outer) {}
/*    */     
/*    */     public class TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$1 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorRef commander$1;
/*    */       
/*    */       private final Tcp.Connect x2$1;
/*    */       
/*    */       public final Props apply(ChannelRegistry registry) {
/*    */         return Props$.MODULE$.apply(TcpOutgoingConnection.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (this.$outer.akka$io$TcpManager$$anonfun$$$outer()).akka$io$TcpManager$$tcp, registry, this.commander$1, this.x2$1 }));
/*    */       }
/*    */       
/*    */       public TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$1(TcpManager$$anonfun$receive$1 $outer, ActorRef commander$1, Tcp.Connect x2$1) {}
/*    */     }
/*    */     
/*    */     public class TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$2 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorRef commander$2;
/*    */       
/*    */       private final Tcp.Bind x3$1;
/*    */       
/*    */       public final Props apply(ChannelRegistry registry) {
/* 57 */         return Props$.MODULE$.apply(TcpListener.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.$outer.akka$io$TcpManager$$anonfun$$$outer().selectorPool(), (this.$outer.akka$io$TcpManager$$anonfun$$$outer()).akka$io$TcpManager$$tcp, registry, this.commander$2, this.x3$1 }));
/*    */       }
/*    */       
/*    */       public TcpManager$$anonfun$receive$1$$anonfun$applyOrElse$2(TcpManager$$anonfun$receive$1 $outer, ActorRef commander$2, Tcp.Bind x3$1) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */