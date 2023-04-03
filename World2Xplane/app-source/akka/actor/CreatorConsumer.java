/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Creator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001%3Q!\001\002\001\t\031\021qb\021:fCR|'oQ8ogVlWM\035\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027mE\002\001\0175\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007C\001\b\020\033\005\021\021B\001\t\003\005UIe\016Z5sK\016$\030i\031;peB\023x\016Z;dKJD\001B\005\001\003\002\003\006I\001F\001\006G2\f'P_\002\001a\t)b\004E\002\0273qq!\001C\f\n\005aI\021A\002)sK\022,g-\003\002\0337\t)1\t\\1tg*\021\001$\003\t\003;ya\001\001B\005 #\005\005\t\021!B\001A\t!q\fJ\0314#\t\tC\005\005\002\tE%\0211%\003\002\b\035>$\b.\0338h!\tqQ%\003\002'\005\t)\021i\031;pe\"A\001\006\001B\001B\003%\021&A\004de\026\fGo\034:\021\007)jC%D\001,\025\taC!\001\003kCBL\027B\001\030,\005\035\031%/Z1u_JDQ\001\r\001\005\002E\na\001P5oSRtDc\001\0324qA\021a\002\001\005\006%=\002\r\001\016\031\003k]\0022AF\r7!\tir\007B\005 g\005\005\t\021!B\001A!)\001f\fa\001S!)!\b\001C!w\005Q\021m\031;pe\016c\027m]:\026\003q\002$!P#\021\007y\032E)D\001@\025\t\001\025)\001\003mC:<'\"\001\"\002\t)\fg/Y\005\0035}\002\"!H#\005\023}I\024\021!A\001\006\003\001\003\"B$\001\t\003B\025a\0029s_\022,8-\032\013\002I\001")
/*     */ public class CreatorConsumer implements IndirectActorProducer {
/*     */   private final Class<? extends Actor> clazz;
/*     */   
/*     */   private final Creator<Actor> creator;
/*     */   
/*     */   public CreatorConsumer(Class<? extends Actor> clazz, Creator<Actor> creator) {}
/*     */   
/*     */   public Class<? extends Actor> actorClass() {
/* 331 */     return this.clazz;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 332 */     return (Actor)this.creator.create();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\CreatorConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */