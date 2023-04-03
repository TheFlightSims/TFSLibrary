/*     */ package akka.actor;
/*     */ 
/*     */ import akka.util.Reflect$;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0013Q!\001\002\001\t\031\021\001DT8Be\036\034(+\0324mK\016$8i\0348tiJ,8\r^8s\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\024\007\0019Q\002\005\002\t\0275\t\021BC\001\013\003\025\0318-\0317b\023\ta\021B\001\004B]f\024VM\032\t\003\035=i\021AA\005\003!\t\021Q#\0238eSJ,7\r^!di>\024\bK]8ek\016,'\017\003\005\023\001\t\005\t\025!\003\025\003\r\031GN_\002\001a\t)b\004E\002\0273qq!\001C\f\n\005aI\021A\002)sK\022,g-\003\002\0337\t)1\t\\1tg*\021\001$\003\t\003;ya\001\001B\005 #\005\005\t\021!B\001A\t!q\fJ\0318#\t\tC\005\005\002\tE%\0211%\003\002\b\035>$\b.\0338h!\tqQ%\003\002'\005\t)\021i\031;pe\")\001\006\001C\001S\0051A(\0338jiz\"\"AK\026\021\0059\001\001\"\002\n(\001\004a\003GA\0270!\r1\022D\f\t\003;=\"\021bH\026\002\002\003\005)\021\001\021\t\013E\002A\021\t\032\002\025\005\034Go\034:DY\006\0348/F\0014a\t!D\bE\0026umj\021A\016\006\003oa\nA\001\\1oO*\t\021(\001\003kCZ\f\027B\001\0167!\tiB\bB\005 a\005\005\t\021!B\001A!)a\b\001C!\0059\001O]8ek\016,G#\001\023")
/*     */ public class NoArgsReflectConstructor implements IndirectActorProducer {
/*     */   private final Class<? extends Actor> clz;
/*     */   
/*     */   public NoArgsReflectConstructor(Class<? extends Actor> clz) {
/* 356 */     Reflect$.MODULE$.findConstructor(clz, (Seq)List$.MODULE$.empty());
/*     */   }
/*     */   
/*     */   public Class<? extends Actor> actorClass() {
/* 357 */     return this.clz;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 358 */     return (Actor)Reflect$.MODULE$.instantiate(this.clz);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\NoArgsReflectConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */