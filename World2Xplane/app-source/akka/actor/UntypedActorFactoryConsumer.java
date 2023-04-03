/*     */ package akka.actor;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00152Q!\001\002\001\t\031\0211$\0268usB,G-Q2u_J4\025m\031;pef\034uN\\:v[\026\024(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\0342\001A\004\016!\tA1\"D\001\n\025\005Q\021!B:dC2\f\027B\001\007\n\005\031\te.\037*fMB\021abD\007\002\005%\021\001C\001\002\026\023:$\027N]3di\006\033Go\034:Qe>$WoY3s\021!\021\002A!A!\002\023!\022a\0024bGR|'/_\002\001!\tqQ#\003\002\027\005\t\031RK\034;za\026$\027i\031;pe\032\0137\r^8ss\")\001\004\001C\0013\0051A(\0338jiz\"\"AG\016\021\0059\001\001\"\002\n\030\001\004!\002\"B\017\001\t\003r\022AC1di>\0248\t\\1tgV\tq\004E\002!K\035j\021!\t\006\003E\r\nA\001\\1oO*\tA%\001\003kCZ\f\027B\001\024\"\005\025\031E.Y:t!\tq\001&\003\002*\005\t)\021i\031;pe\")1\006\001C!Y\0059\001O]8ek\016,G#A\024")
/*     */ public class UntypedActorFactoryConsumer implements IndirectActorProducer {
/*     */   private final UntypedActorFactory factory;
/*     */   
/*     */   public UntypedActorFactoryConsumer(UntypedActorFactory factory) {}
/*     */   
/*     */   public Class<Actor> actorClass() {
/* 315 */     return Actor.class;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 316 */     return (Actor)this.factory.create();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActorFactoryConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */