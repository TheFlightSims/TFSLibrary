/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import java.nio.channels.SocketChannel;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.Traversable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0314Q!\001\002\001\005\031\021Q\003V2q\023:\034w.\\5oO\016{gN\\3di&|gN\003\002\004\t\005\021\021n\034\006\002\013\005!\021m[6b'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\tiAk\0319D_:tWm\031;j_:D\021\002\004\001\003\002\003\006IAD\t\002\t}#8\r]\002\001!\tAq\"\003\002\021\005\t1Ak\0319FqRL!AE\005\002\007Q\034\007\017C\005\025\001\t\005\t\025!\003\026?\005Aql\0315b]:,G\016\005\002\027;5\tqC\003\002\0313\005A1\r[1o]\026d7O\003\002\0337\005\031a.[8\013\003q\tAA[1wC&\021ad\006\002\016'>\0347.\032;DQ\006tg.\0327\n\005\001J\021aB2iC:tW\r\034\005\tE\001\021\t\021)A\005G\005A!/Z4jgR\024\030\020\005\002\tI%\021QE\001\002\020\007\"\fgN\\3m%\026<\027n\035;ss\"Aq\005\001B\001B\003%\001&A\006cS:$\007*\0318eY\026\024\bCA\025-\033\005Q#BA\026\005\003\025\t7\r^8s\023\ti#F\001\005BGR|'OU3g\021!y\003A!A!\002\023\001\024aB8qi&|gn\035\t\004caRT\"\001\032\013\005M\"\024!C5n[V$\030M\0317f\025\t)d'\001\006d_2dWm\031;j_:T\021aN\001\006g\016\fG.Y\005\003sI\0221\002\026:bm\026\0248/\0312mKB\0211(\022\b\003y\rs!!\020\"\017\005y\nU\"A \013\005\001k\021A\002\037s_>$h(C\001\006\023\t\031A!\003\002E\005\005!\021J\\3u\023\t1uI\001\007T_\016\\W\r^(qi&|gN\003\002E\005!I\021\n\001B\001B\003%!JT\001\017e\026\fG\r\0265s_R$H.\0338h!\tYE*D\0017\023\tieGA\004C_>dW-\0318\n\005=K\021\001\0039vY2lu\016Z3\t\013E\003A\021\001*\002\rqJg.\033;?)\035\031F+\026,X1f\003\"\001\003\001\t\0131\001\006\031\001\b\t\013Q\001\006\031A\013\t\013\t\002\006\031A\022\t\013\035\002\006\031\001\025\t\013=\002\006\031\001\031\t\013%\003\006\031\001&\t\013m\003A\021\001/\002\017I,7-Z5wKV\tQ\f\005\003L=\002\034\027BA07\005=\001\026M\035;jC24UO\\2uS>t\007CA&b\023\t\021gGA\002B]f\004\"a\0233\n\005\0254$\001B+oSR\004")
/*    */ public class TcpIncomingConnection extends TcpConnection {
/*    */   public final ActorRef akka$io$TcpIncomingConnection$$bindHandler;
/*    */   
/*    */   public final Traversable<Inet.SocketOption> akka$io$TcpIncomingConnection$$options;
/*    */   
/*    */   public TcpIncomingConnection(TcpExt _tcp, SocketChannel _channel, ChannelRegistry registry, ActorRef bindHandler, Traversable<Inet.SocketOption> options, boolean readThrottling) {
/* 18 */     super(
/*    */         
/* 24 */         _tcp, _channel, readThrottling);
/* 26 */     context().watch(bindHandler);
/* 28 */     registry.register(channel(), 0, self());
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 30 */     return (PartialFunction<Object, BoxedUnit>)new TcpIncomingConnection$$anonfun$receive$1(this);
/*    */   }
/*    */   
/*    */   public class TcpIncomingConnection$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 30 */       Object object2, object1 = x1;
/* 31 */       if (object1 instanceof ChannelRegistration) {
/* 31 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/* 31 */         this.$outer.completeConnect(channelRegistration, this.$outer.akka$io$TcpIncomingConnection$$bindHandler, this.$outer.akka$io$TcpIncomingConnection$$options);
/* 31 */         object2 = BoxedUnit.UNIT;
/*    */       } else {
/*    */         object2 = default.apply(x1);
/*    */       } 
/*    */       return (B1)object2;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Object x1) {
/*    */       boolean bool;
/*    */       Object object = x1;
/* 31 */       if (object instanceof ChannelRegistration) {
/* 31 */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public TcpIncomingConnection$$anonfun$receive$1(TcpIncomingConnection $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpIncomingConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */