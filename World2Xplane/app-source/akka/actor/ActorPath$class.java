/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Util$;
/*     */ import scala.Function2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.JavaConverters$;
/*     */ 
/*     */ public abstract class ActorPath$class {
/*     */   public static void $init$(ActorPath $this) {}
/*     */   
/*     */   public static ActorPath child(ActorPath $this, String child) {
/*  73 */     return $this.$div(child);
/*     */   }
/*     */   
/*     */   public static ActorPath $div(ActorPath $this, Iterable child) {
/*  78 */     ActorPath actorPath = $this;
/*  78 */     return (ActorPath)child.$div$colon(actorPath, (Function2)new ActorPath$$anonfun$$div$1($this));
/*     */   }
/*     */   
/*     */   public static ActorPath descendant(ActorPath $this, Iterable names) {
/*  83 */     return $this.$div((Iterable<String>)Util$.MODULE$.immutableSeq(names));
/*     */   }
/*     */   
/*     */   public static Iterable getElements(ActorPath $this) {
/*  94 */     return (Iterable)JavaConverters$.MODULE$.asJavaIterableConverter((Iterable)$this.elements()).asJava();
/*     */   }
/*     */   
/*     */   public static String toStringWithoutAddress(ActorPath $this) {
/* 106 */     return $this.elements().mkString("/", "/", "");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorPath$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */