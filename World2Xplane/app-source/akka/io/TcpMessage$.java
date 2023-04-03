/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.ByteString;
/*     */ import java.net.InetSocketAddress;
/*     */ import scala.Option;
/*     */ import scala.collection.immutable.Traversable;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ 
/*     */ public final class TcpMessage$ {
/*     */   public static final TcpMessage$ MODULE$;
/*     */   
/*     */   private TcpMessage$() {
/* 606 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Tcp.Command connect(InetSocketAddress remoteAddress, InetSocketAddress localAddress, Iterable<Inet.SocketOption> options, FiniteDuration timeout, boolean pullMode) {
/* 626 */     return new Tcp.Connect(remoteAddress, scala.Option$.MODULE$.apply(localAddress), fromJava(options), scala.Option$.MODULE$.apply(timeout), pullMode);
/*     */   }
/*     */   
/*     */   public Tcp.Command connect(InetSocketAddress remoteAddress) {
/* 632 */     return new Tcp.Connect(remoteAddress, (Option<InetSocketAddress>)scala.None$.MODULE$, (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$, (Option<FiniteDuration>)scala.None$.MODULE$, false);
/*     */   }
/*     */   
/*     */   public Tcp.Command bind(ActorRef handler, InetSocketAddress endpoint, int backlog, Iterable<Inet.SocketOption> options, boolean pullMode) {
/* 660 */     return new Tcp.Bind(handler, endpoint, backlog, fromJava(options), pullMode);
/*     */   }
/*     */   
/*     */   public Tcp.Command bind(ActorRef handler, InetSocketAddress endpoint, int backlog) {
/* 666 */     return new Tcp.Bind(handler, endpoint, backlog, (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$, Tcp.Bind$.MODULE$.apply$default$5());
/*     */   }
/*     */   
/*     */   public Tcp.Command register(ActorRef handler, boolean keepOpenOnPeerClosed, boolean useResumeWriting) {
/* 687 */     return new Tcp.Register(handler, keepOpenOnPeerClosed, useResumeWriting);
/*     */   }
/*     */   
/*     */   public Tcp.Command register(ActorRef handler) {
/* 691 */     return new Tcp.Register(handler, Tcp.Register$.MODULE$.apply$default$2(), Tcp.Register$.MODULE$.apply$default$3());
/*     */   }
/*     */   
/*     */   public Tcp.Command unbind() {
/* 698 */     return Tcp.Unbind$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command close() {
/* 706 */     return Tcp.Close$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command confirmedClose() {
/* 714 */     return Tcp.ConfirmedClose$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command abort() {
/* 723 */     return Tcp.Abort$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.NoAck noAck(Object token) {
/* 731 */     return new Tcp.NoAck(token);
/*     */   }
/*     */   
/*     */   public Tcp.NoAck noAck() {
/* 736 */     return Tcp.NoAck$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command write(ByteString data, Tcp.Event ack) {
/* 748 */     return new Tcp.Write(data, ack);
/*     */   }
/*     */   
/*     */   public Tcp.Command write(ByteString data) {
/* 752 */     return Tcp.Write$.MODULE$.apply(data);
/*     */   }
/*     */   
/*     */   public Tcp.Command writeFile(String filePath, long position, long count, Tcp.Event ack) {
/* 765 */     return new Tcp.WriteFile(filePath, position, count, ack);
/*     */   }
/*     */   
/*     */   public Tcp.Command resumeWriting() {
/* 774 */     return Tcp.ResumeWriting$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command suspendReading() {
/* 781 */     return Tcp.SuspendReading$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command resumeReading() {
/* 787 */     return Tcp.ResumeReading$.MODULE$;
/*     */   }
/*     */   
/*     */   public Tcp.Command resumeAccepting(int batchSize) {
/* 794 */     return new Tcp.ResumeAccepting(batchSize);
/*     */   }
/*     */   
/*     */   private <T> Traversable<T> fromJava(Iterable coll) {
/* 797 */     return (Traversable<T>)akka.japi.Util$.MODULE$.immutableSeq(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */