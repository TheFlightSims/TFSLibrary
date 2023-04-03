/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.LoggingAdapter;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function1;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055a!B\001\003\001\0211!\001\006,jeR,\030\r\034)bi\"\034uN\034;bS:,'O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7c\001\001\b\027A\021\001\"C\007\002\005%\021!B\001\002\021\023:$XM\0358bY\006\033Go\034:SK\032\004\"\001\003\007\n\0055\021!aD'j]&l\027\r\\!di>\024(+\0324\t\021=\001!Q1A\005BE\t\001\002\035:pm&$WM]\002\001+\005\021\002C\001\005\024\023\t!\"A\001\tBGR|'OU3g!J|g/\0333fe\"Aa\003\001B\001B\003%!#A\005qe>4\030\016Z3sA!A\001\004\001BC\002\023\005\023$\001\003qCRDW#\001\016\021\005!Y\022B\001\017\003\005%\t5\r^8s!\006$\b\016\003\005\037\001\t\005\t\025!\003\033\003\025\001\030\r\0365!\021!\001\003A!b\001\n\003\n\023!C4fiB\013'/\0328u+\0059\001\002C\022\001\005\003\005\013\021B\004\002\025\035,G\017U1sK:$\b\005\003\005&\001\t\025\r\021\"\001'\003\rawnZ\013\002OA\021\001fK\007\002S)\021!\006B\001\006KZ,g\016^\005\003Y%\022a\002T8hO&tw-\0213baR,'\017\003\005/\001\t\005\t\025!\003(\003\021awn\032\021\t\013A\002A\021A\031\002\rqJg.\033;?)\025\0214\007N\0337!\tA\001\001C\003\020_\001\007!\003C\003\031_\001\007!\004C\003!_\001\007q\001C\003&_\001\007q\005C\0049\001\t\007I\021B\035\002\021\rD\027\016\0343sK:,\022A\017\t\005w\t#u!D\001=\025\tid(\001\006d_:\034WO\035:f]RT!a\020!\002\tU$\030\016\034\006\002\003\006!!.\031<b\023\t\031EHA\tD_:\034WO\035:f]RD\025m\0355NCB\004\"!R&\017\005\031KU\"A$\013\003!\013Qa]2bY\006L!AS$\002\rA\023X\rZ3g\023\taUJ\001\004TiJLgn\032\006\003\025\036Caa\024\001!\002\023Q\024!C2iS2$'/\0328!\021\025\t\006\001\"\001S\003!\tG\rZ\"iS2$GcA*W1B\021a\tV\005\003+\036\023A!\0268ji\")q\013\025a\001\t\006!a.Y7f\021\025I\006\0131\001\b\003\r\021XM\032\005\0067\002!\t\001X\001\fe\026lwN^3DQ&dG\r\006\002T;\")qK\027a\001\t\")1\f\001C\t?R\0311\013Y1\t\013]s\006\031\001#\t\013es\006\031\0012\021\005!\031\027B\0013\003\005!\t5\r^8s%\0264\007\"\0024\001\t\0039\027\001C4fi\016C\027\016\0343\025\005\035A\007\"B,f\001\004!\005\"\0024\001\t\003RGCA\004l\021\0259\026\0161\001m!\riW\017\022\b\003]Nt!a\034:\016\003AT!!\035\t\002\rq\022xn\034;?\023\005A\025B\001;H\003\035\001\030mY6bO\026L!A^<\003\021%#XM]1u_JT!\001^$\t\013e\004A\021\001>\002\027!\f7o\0215jY\022\024XM\\\013\002wB\021a\t`\005\003{\036\023qAQ8pY\026\fg\016\003\004\000\001\021\005\021\021A\001\rM>\024X-Y2i\007\"LG\016\032\013\004'\006\r\001bBA\003}\002\007\021qA\001\002MB)a)!\003c'&\031\0211B$\003\023\031+hn\031;j_:\f\004")
/*     */ public class VirtualPathContainer extends InternalActorRef implements MinimalActorRef {
/*     */   private final ActorRefProvider provider;
/*     */   
/*     */   private final ActorPath path;
/*     */   
/*     */   private final InternalActorRef getParent;
/*     */   
/*     */   private final LoggingAdapter log;
/*     */   
/*     */   private final ConcurrentHashMap<String, InternalActorRef> children;
/*     */   
/*     */   public void start() {
/* 552 */     MinimalActorRef$class.start(this);
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 552 */     MinimalActorRef$class.suspend(this);
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 552 */     MinimalActorRef$class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 552 */     MinimalActorRef$class.stop(this);
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 552 */     return MinimalActorRef$class.isTerminated(this);
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 552 */     MinimalActorRef$class.$bang(this, message, sender);
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 552 */     MinimalActorRef$class.sendSystemMessage(this, message);
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 552 */     MinimalActorRef$class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 552 */     return MinimalActorRef$class.writeReplace(this);
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 552 */     return MinimalActorRef$class.$bang$default$2(this, message);
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/* 552 */     return LocalRef$class.isLocal(this);
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/* 553 */     return this.provider;
/*     */   }
/*     */   
/*     */   public VirtualPathContainer(ActorRefProvider provider, ActorPath path, InternalActorRef getParent, LoggingAdapter log) {
/*     */     LocalRef$class.$init$(this);
/*     */     MinimalActorRef$class.$init$(this);
/* 558 */     this.children = new ConcurrentHashMap<String, InternalActorRef>();
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/*     */     return this.path;
/*     */   }
/*     */   
/*     */   public InternalActorRef getParent() {
/*     */     return this.getParent;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*     */     return this.log;
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<String, InternalActorRef> children() {
/* 558 */     return this.children;
/*     */   }
/*     */   
/*     */   public void addChild(String name, InternalActorRef ref) {
/* 561 */     InternalActorRef internalActorRef = children().put(name, ref);
/* 562 */     if (internalActorRef == null) {
/* 562 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 566 */       log().debug("{} replacing child {} ({} -> {})", path(), name, internalActorRef, ref);
/* 567 */       internalActorRef.stop();
/* 567 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeChild(String name) {
/* 572 */     if (children().remove(name) == null)
/* 572 */       log().warning("{} trying to remove non-child {}", path(), name); 
/*     */   }
/*     */   
/*     */   public void removeChild(String name, ActorRef ref) {
/* 578 */     InternalActorRef current = getChild(name);
/* 579 */     if (current == null) {
/* 580 */       log().warning("{} trying to remove non-child {}", path(), name);
/*     */     } else {
/* 581 */       ActorRef actorRef = ref;
/* 581 */       if (current == null) {
/* 581 */         if (actorRef != null)
/*     */           return; 
/*     */       } else {
/* 581 */         if (current.equals(actorRef))
/* 582 */           children().remove(name, current); 
/*     */         return;
/*     */       } 
/* 582 */       children().remove(name, current);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(String name) {
/* 586 */     return children().get(name);
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator<String> name) {
/*     */     InternalActorRef internalActorRef2;
/* 591 */     String n = (String)name.next();
/* 593 */     InternalActorRef internalActorRef1 = children().get(n);
/* 594 */     if (internalActorRef1 == null) {
/* 594 */       internalActorRef2 = Nobody$.MODULE$;
/*     */     } else {
/* 596 */       internalActorRef2 = name.isEmpty() ? internalActorRef1 : 
/* 597 */         internalActorRef1.getChild(name);
/*     */     } 
/*     */     return name.isEmpty() ? this : (n.isEmpty() ? this : internalActorRef2);
/*     */   }
/*     */   
/*     */   public boolean hasChildren() {
/* 602 */     return !children().isEmpty();
/*     */   }
/*     */   
/*     */   public void foreachChild(Function1 f) {
/* 605 */     Iterator iter = children().values().iterator();
/* 606 */     for (; iter.hasNext(); f.apply(iter.next()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\VirtualPathContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */