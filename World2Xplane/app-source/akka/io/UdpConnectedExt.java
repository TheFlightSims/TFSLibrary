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
/*     */ @ScalaSignature(bytes = "\006\001!3A!\001\002\001\017\tyQ\013\0329D_:tWm\031;fI\026CHO\003\002\004\t\005\021\021n\034\006\002\013\005!\021m[6b\007\001\0312\001\001\005\017!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021qb\005\b\003!Ei\021AA\005\003%\t\t!!S(\n\005Q)\"!C#yi\026t7/[8o\025\t\021\"\001\003\005\030\001\t\005\t\025!\003\031\003\031\031\030p\035;f[B\021\021\004H\007\0025)\0211\004B\001\006C\016$xN]\005\003;i\0211#\022=uK:$W\rZ!di>\0248+_:uK6DQa\b\001\005\002\001\na\001P5oSRtDCA\021#!\t\001\002\001C\003\030=\001\007\001\004C\004%\001\t\007I\021A\023\002\021M,G\017^5oON,\022A\n\t\003OEr!\001K\030\017\005%rcB\001\026.\033\005Y#B\001\027\007\003\031a$o\\8u}%\tQ!\003\002\004\t%\021\001GA\001\004+\022\004\030B\001\0324\005-)F\r]*fiRLgnZ:\013\005A\022\001BB\033\001A\003%a%A\005tKR$\030N\\4tA!9q\007\001b\001\n\003A\024aB7b]\006<WM]\013\002sA\021\021DO\005\003wi\021\001\"Q2u_J\024VM\032\005\007{\001\001\013\021B\035\002\0215\fg.Y4fe\002BQa\020\001\005\002a\n!bZ3u\033\006t\027mZ3s\021\035\t\005A1A\005\002\t\013!BY;gM\026\024\bk\\8m+\005\031\005C\001\tE\023\t)%A\001\006Ck\0324WM\035)p_2Daa\022\001!\002\023\031\025a\0032vM\032,'\017U8pY\002\002")
/*     */ public class UdpConnectedExt implements IO.Extension {
/*     */   private final Udp.UdpSettings settings;
/*     */   
/*     */   private final ActorRef manager;
/*     */   
/*     */   private final BufferPool bufferPool;
/*     */   
/*     */   public UdpConnectedExt(ExtendedActorSystem system) {
/* 149 */     this.settings = new Udp.UdpSettings(system.settings().config().getConfig("akka.io.udp-connected"));
/* 151 */     this.manager = 
/* 152 */       system.systemActorOf(
/* 153 */         Props$.MODULE$.apply(UdpConnectedManager.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this })).withDeploy(Deploy$.MODULE$.local()), 
/* 154 */         "IO-UDP-CONN");
/* 162 */     this.bufferPool = new DirectByteBufferPool(settings().DirectBufferSize(), settings().MaxDirectBufferPoolSize());
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
/* 162 */     return this.bufferPool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnectedExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */