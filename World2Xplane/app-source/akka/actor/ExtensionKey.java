/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0253Q!\001\002\002\002\035\021A\"\022=uK:\034\030n\0348LKfT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\001QC\001\005\026'\021\001\021b\004\020\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\f\013b$XM\\:j_:LE\r\005\002\025+1\001A!\002\f\001\005\0049\"!\001+\022\005aY\002C\001\006\032\023\tQ2BA\004O_RD\027N\\4\021\005Aa\022BA\017\003\005%)\005\020^3og&|g\016\005\002\021?%\021\001E\001\002\024\013b$XM\\:j_:LE\r\025:pm&$WM\035\005\tE\001\021\t\021)A\006G\005\tQ\016E\002%OMi\021!\n\006\003M-\tqA]3gY\026\034G/\003\002)K\tA1\t\\1tgR\013w\rC\003+\001\021\0051&\001\004=S:LGO\020\013\002YQ\021QF\f\t\004!\001\031\002\"\002\022*\001\b\031\003\"\002\026\001\t\003\001DCA\0272\021\025\021t\0061\0014\003\025\031G.\031>{!\r!tg\005\b\003\025UJ!AN\006\002\rA\023X\rZ3g\023\tA\024HA\003DY\006\0348O\003\0027\027!)1\b\001C!y\0051An\\8lkB$\022a\004\005\006}\001!\taP\001\020GJ,\027\r^3FqR,gn]5p]R\0211\003\021\005\006\003v\002\rAQ\001\007gf\034H/Z7\021\005A\031\025B\001#\003\005M)\005\020^3oI\026$\027i\031;peNK8\017^3n\001")
/*     */ public abstract class ExtensionKey<T extends Extension> implements ExtensionId<T>, ExtensionIdProvider {
/*     */   private final ClassTag<T> m;
/*     */   
/*     */   public T apply(ActorSystem system) {
/* 149 */     return (T)ExtensionId$class.apply(this, system);
/*     */   }
/*     */   
/*     */   public T get(ActorSystem system) {
/* 149 */     return (T)ExtensionId$class.get(this, system);
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 149 */     return ExtensionId$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 149 */     return ExtensionId$class.equals(this, other);
/*     */   }
/*     */   
/*     */   public ExtensionKey(ClassTag<T> m) {
/* 149 */     ExtensionId$class.$init$(this);
/*     */   }
/*     */   
/*     */   public ExtensionKey(Class clazz) {
/* 150 */     this(ClassTag$.MODULE$.apply(clazz));
/*     */   }
/*     */   
/*     */   public ExtensionId<T> lookup() {
/* 152 */     return this;
/*     */   }
/*     */   
/*     */   public T createExtension(ExtendedActorSystem system) {
/* 153 */     (new Tuple2[1])[0] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(ExtendedActorSystem.class), system);
/* 153 */     return (T)system.dynamicAccess().<T>createInstanceFor(this.m.runtimeClass(), (Seq<Tuple2<Class<?>, Object>>)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])), this.m).get();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ExtensionKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */