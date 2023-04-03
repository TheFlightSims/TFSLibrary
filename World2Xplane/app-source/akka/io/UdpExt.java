/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Deploy$;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.actor.Props$;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0053A!\001\002\001\017\t1Q\013\0329FqRT!a\001\003\002\005%|'\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\031bB\001\t\022\033\005\021\021B\001\n\003\003\tIu*\003\002\025+\tIQ\t\037;f]NLwN\034\006\003%\tA\001b\006\001\003\002\003\006I\001G\001\007gf\034H/Z7\021\005eaR\"\001\016\013\005m!\021!B1di>\024\030BA\017\033\005M)\005\020^3oI\026$\027i\031;peNK8\017^3n\021\025y\002\001\"\001!\003\031a\024N\\5u}Q\021\021E\t\t\003!\001AQa\006\020A\002aAq\001\n\001C\002\023\005Q%\001\005tKR$\030N\\4t+\0051\003CA\024+\035\t\001\002&\003\002*\005\005\031Q\013\0329\n\005-b#aC+eaN+G\017^5oONT!!\013\002\t\r9\002\001\025!\003'\003%\031X\r\036;j]\036\034\b\005C\0041\001\t\007I\021A\031\002\0175\fg.Y4feV\t!\007\005\002\032g%\021AG\007\002\t\003\016$xN\035*fM\"1a\007\001Q\001\nI\n\001\"\\1oC\036,'\017\t\005\006q\001!\t!M\001\013O\026$X*\0318bO\026\024\b\002\003\036\001\005\004%\tAA\036\002\025\t,hMZ3s!>|G.F\001=!\t\001R(\003\002?\005\tQ!)\0364gKJ\004vn\0347\t\r\001\003\001\025!\003=\003-\021WO\0324feB{w\016\034\021")
/*     */ public class UdpExt implements IO.Extension {
/*     */   private final Udp.UdpSettings settings;
/*     */   
/*     */   private final ActorRef manager;
/*     */   
/*     */   private final BufferPool bufferPool;
/*     */   
/*     */   public UdpExt(ExtendedActorSystem system) {
/* 212 */     this.settings = new Udp.UdpSettings(system.settings().config().getConfig("akka.io.udp"));
/* 214 */     this.manager = 
/* 215 */       system.systemActorOf(
/* 216 */         Props$.MODULE$.apply(UdpManager.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this })).withDeploy(Deploy$.MODULE$.local()), 
/* 217 */         "IO-UDP-FF");
/* 228 */     this.bufferPool = new DirectByteBufferPool(settings().DirectBufferSize(), settings().MaxDirectBufferPoolSize());
/*     */   }
/*     */   
/*     */   public Udp.UdpSettings settings() {
/*     */     return this.settings;
/*     */   }
/*     */   
/*     */   public ActorRef manager() {
/*     */     return this.manager;
/*     */   }
/*     */   
/*     */   public ActorRef getManager() {
/*     */     return manager();
/*     */   }
/*     */   
/*     */   public BufferPool bufferPool() {
/* 228 */     return this.bufferPool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */