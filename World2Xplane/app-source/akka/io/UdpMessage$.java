/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.ByteString;
/*     */ import java.net.InetSocketAddress;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Traversable;
/*     */ 
/*     */ public final class UdpMessage$ {
/*     */   public static final UdpMessage$ MODULE$;
/*     */   
/*     */   private UdpMessage$() {
/* 234 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Udp.NoAck noAck(Object token) {
/* 246 */     return new Udp.NoAck(token);
/*     */   }
/*     */   
/*     */   public Udp.NoAck noAck() {
/* 251 */     return Udp.NoAck$.MODULE$;
/*     */   }
/*     */   
/*     */   public Udp.Command send(ByteString payload, InetSocketAddress target, Udp.Event ack) {
/* 269 */     return new Udp.Send(payload, target, ack);
/*     */   }
/*     */   
/*     */   public Udp.Command send(ByteString payload, InetSocketAddress target) {
/* 273 */     return Udp.Send$.MODULE$.apply(payload, target);
/*     */   }
/*     */   
/*     */   public Udp.Command bind(ActorRef handler, InetSocketAddress endpoint, Iterable options) {
/* 282 */     return new Udp.Bind(handler, endpoint, (Traversable<Inet.SocketOption>)((TraversableLike)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(options).asScala()).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom()));
/*     */   }
/*     */   
/*     */   public Udp.Command bind(ActorRef handler, InetSocketAddress endpoint) {
/* 286 */     return new Udp.Bind(handler, endpoint, (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public Udp.Command unbind() {
/* 293 */     return Udp.Unbind$.MODULE$;
/*     */   }
/*     */   
/*     */   public Udp.Command simpleSender(Iterable options) {
/* 305 */     return new Udp.SimpleSender((Traversable<Inet.SocketOption>)((TraversableLike)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(options).asScala()).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom()));
/*     */   }
/*     */   
/*     */   public Udp.Command simpleSender() {
/* 309 */     return Udp.SimpleSender$.MODULE$;
/*     */   }
/*     */   
/*     */   public Udp.Command suspendReading() {
/* 317 */     return Udp.SuspendReading$.MODULE$;
/*     */   }
/*     */   
/*     */   public Udp.Command resumeReading() {
/* 323 */     return Udp.ResumeReading$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */