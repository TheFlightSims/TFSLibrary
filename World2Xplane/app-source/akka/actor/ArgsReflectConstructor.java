/*     */ package akka.actor;
/*     */ 
/*     */ import akka.util.Reflect$;
/*     */ import java.lang.reflect.Constructor;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001m3Q!\001\002\001\t\031\021a#\021:hgJ+g\r\\3di\016{gn\035;sk\016$xN\035\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027mE\002\001\0175\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007C\001\b\020\033\005\021\021B\001\t\003\005UIe\016Z5sK\016$\030i\031;peB\023x\016Z;dKJD\001B\005\001\003\002\003\006I\001F\001\004G2T8\001\001\031\003+y\0012AF\r\035\035\tAq#\003\002\031\023\0051\001K]3eK\032L!AG\016\003\013\rc\027m]:\013\005aI\001CA\017\037\031\001!\021bH\t\002\002\003\005)\021\001\021\003\t}#\023'N\t\003C\021\002\"\001\003\022\n\005\rJ!a\002(pi\"Lgn\032\t\003\035\025J!A\n\002\003\013\005\033Go\034:\t\021!\002!\021!Q\001\n%\nA!\031:hgB\031!fL\031\016\003-R!\001L\027\002\023%lW.\036;bE2,'B\001\030\n\003)\031w\016\0347fGRLwN\\\005\003a-\0221aU3r!\tA!'\003\0024\023\t\031\021I\\=\t\013U\002A\021\001\034\002\rqJg.\033;?)\r9\004(\020\t\003\035\001AQA\005\033A\002e\002$A\017\037\021\007YI2\b\005\002\036y\021Iq\004OA\001\002\003\025\t\001\t\005\006QQ\002\r!\013\005\007\001\001\013\021\002!\002\027\r|gn\035;sk\016$xN\035\031\003\0032\0032AQ%L\033\005\031%B\001#F\003\035\021XM\0327fGRT!AR$\002\t1\fgn\032\006\002\021\006!!.\031<b\023\tQ5IA\006D_:\034HO];di>\024\bCA\017M\t%ie(!A\001\002\013\005aJ\001\003`IE2\024CA\0212\021\025\001\006\001\"\021R\003)\t7\r^8s\0072\f7o]\013\002%B\0221k\026\t\004)V3V\"A#\n\005i)\005CA\017X\t%yr*!A\001\002\013\005\001\005C\003Z\001\021\005#,A\004qe>$WoY3\025\003\021\002")
/*     */ public class ArgsReflectConstructor implements IndirectActorProducer {
/*     */   private final Class<? extends Actor> clz;
/*     */   
/*     */   private final Seq<Object> args;
/*     */   
/*     */   private final Constructor<?> constructor;
/*     */   
/*     */   public ArgsReflectConstructor(Class<? extends Actor> clz, Seq<Object> args) {
/* 347 */     this.constructor = Reflect$.MODULE$.findConstructor(clz, args);
/*     */   }
/*     */   
/*     */   public Class<? extends Actor> actorClass() {
/* 348 */     return this.clz;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 349 */     return (Actor)Reflect$.MODULE$.instantiate(this.constructor, this.args);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ArgsReflectConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */