/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.ByteString;
/*     */ import java.net.InetSocketAddress;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Traversable;
/*     */ 
/*     */ public final class UdpConnectedMessage$ {
/*     */   public static final UdpConnectedMessage$ MODULE$;
/*     */   
/*     */   private UdpConnectedMessage$() {
/* 169 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public UdpConnected.Command connect(ActorRef handler, InetSocketAddress remoteAddress, InetSocketAddress localAddress, Iterable<Inet.SocketOption> options) {
/* 182 */     return new UdpConnected.Connect(handler, remoteAddress, (Option<InetSocketAddress>)new Some(localAddress), fromJava(options));
/*     */   }
/*     */   
/*     */   public UdpConnected.Command connect(ActorRef handler, InetSocketAddress remoteAddress, Iterable<Inet.SocketOption> options) {
/* 188 */     return new UdpConnected.Connect(handler, remoteAddress, (Option<InetSocketAddress>)scala.None$.MODULE$, fromJava(options));
/*     */   }
/*     */   
/*     */   public UdpConnected.Command connect(ActorRef handler, InetSocketAddress remoteAddress) {
/* 193 */     return new UdpConnected.Connect(handler, remoteAddress, (Option<InetSocketAddress>)scala.None$.MODULE$, (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public UdpConnected.Command send(ByteString data, Object ack) {
/* 203 */     return new UdpConnected.Send(data, ack);
/*     */   }
/*     */   
/*     */   public UdpConnected.Command send(ByteString data) {
/* 207 */     return UdpConnected.Send$.MODULE$.apply(data);
/*     */   }
/*     */   
/*     */   public UdpConnected.Command disconnect() {
/* 214 */     return UdpConnected.Disconnect$.MODULE$;
/*     */   }
/*     */   
/*     */   public UdpConnected.NoAck noAck(Object token) {
/* 222 */     return new UdpConnected.NoAck(token);
/*     */   }
/*     */   
/*     */   public UdpConnected.NoAck noAck() {
/* 228 */     return UdpConnected.NoAck$.MODULE$;
/*     */   }
/*     */   
/*     */   public UdpConnected.Command suspendReading() {
/* 236 */     return UdpConnected.SuspendReading$.MODULE$;
/*     */   }
/*     */   
/*     */   public UdpConnected.Command resumeReading() {
/* 242 */     return UdpConnected.ResumeReading$.MODULE$;
/*     */   }
/*     */   
/*     */   private <T> Traversable<T> fromJava(Iterable coll) {
/* 246 */     return (Traversable<T>)((TraversableLike)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(coll).asScala()).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnectedMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */