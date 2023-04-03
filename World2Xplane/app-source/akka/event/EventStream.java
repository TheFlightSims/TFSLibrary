/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.actor.package$;
/*     */ import akka.util.ReentrantGuard;
/*     */ import akka.util.Subclassification;
/*     */ import akka.util.SubclassifiedIndex;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ut!B\001\003\021\0039\021aC#wK:$8\013\036:fC6T!a\001\003\002\013\0254XM\034;\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!aC#wK:$8\013\036:fC6\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\003\027\023\021\rq#A\bge>l\027i\031;peNK8\017^3n)\rA\022\021\f\t\003\021e1AA\003\002\0015M!\021\004D\016\037!\tAA$\003\002\036\005\tQAj\\4hS:<')^:\021\005!y\022B\001\021\003\005a\031VOY2iC:tW\r\\\"mCN\034\030NZ5dCRLwN\034\005\tEe\021)\031!C\005G\005)A-\0322vOV\tA\005\005\002\016K%\021aE\004\002\b\005>|G.Z1o\021!A\023D!A!\002\023!\023A\0023fEV<\007\005C\003\0243\021\005!\006\006\002\031W!9!%\013I\001\002\004!S\001B\027\032\0011\021Q!\022<f]R,AaL\r\001a\tQ1\t\\1tg&4\027.\032:1\005ER\004c\001\0326q9\021QbM\005\003i9\ta\001\025:fI\0264\027B\001\0348\005\025\031E.Y:t\025\t!d\002\005\002:u1\001A!C\036/\003\003\005\tQ!\001=\005\ryF%M\t\003{\001\003\"!\004 \n\005}r!a\002(pi\"Lgn\032\t\003\033\005K!A\021\b\003\007\005s\027\020C\004E3\t\007I1C#\002#M,(m\0317bgNLg-[2bi&|g.F\001G%\r9Eb\023\004\005\021&\003aI\001\007=e\0264\027N\\3nK:$h\b\003\004K3\001\006IAR\001\023gV\0247\r\\1tg&4\027nY1uS>t\007\005E\002M\037Fk\021!\024\006\003\035\022\tA!\036;jY&\021\001+\024\002\022'V\0247\r\\1tg&4\027nY1uS>t\007G\001*U!\r\021Tg\025\t\003sQ#\021\"\026,\002\002\003\005)\021\001\037\003\007}##G\002\003X\023\nA&!\002\023b]>t7c\001,\r\027\")1C\026C\0015R\t1\f\005\002:-\")QL\026C\001=\0069\021n]#rk\006dGc\001\023`M\")\001\r\030a\001C\006\t\001\020\r\002cIB\031!'N2\021\005e\"G!C3`\003\003\005\tQ!\001=\005\ryFe\r\005\006Or\003\r\001[\001\002sB\022\021n\033\t\004eUR\007CA\035l\t%ag-!A\001\002\013\005AHA\002`IQBQA\034,\005\002=\f!\"[:Tk\n\034G.Y:t)\r!\003O\036\005\006A6\004\r!\035\031\003eR\0042AM\033t!\tID\017B\005va\006\005\t\021!B\001y\t\031q\fJ\033\t\013\035l\007\031A<1\005aT\bc\001\0326sB\021\021H\037\003\nwZ\f\t\021!A\003\002q\0221a\030\0237\021\025i\030\004\"\005\003!\031G.Y:tS\032LHcA@\002\nA\"\021\021AA\003!\021\021T'a\001\021\007e\n)\001\002\006\002\bq\f\t\021!A\003\002q\0221a\030\0238\021\025\031A\0201\001\r\021\035\ti!\007C\t\003\037\tq\001];cY&\034\b\016\006\004\002\022\005]\021\021\004\t\004\033\005M\021bAA\013\035\t!QK\\5u\021\031\031\0211\002a\001\031!A\0211DA\006\001\004\ti\"\001\006tk\n\0348M]5cKJ\004B!a\b\002&5\021\021\021\005\006\004\003G!\021!B1di>\024\030\002BA\024\003C\021\001\"Q2u_J\024VM\032\005\b\003WIB\021IA\027\003%\031XOY:de&\024W\rF\003%\003_\t\t\004\003\005\002\034\005%\002\031AA\017\021!\t\031$!\013A\002\005U\022aB2iC:tW\r\034\031\005\003o\tY\004\005\0033k\005e\002cA\035\002<\021Y\021QHA\031\003\003\005\tQ!\001=\005\ryF\005\017\005\b\003\003JB\021IA\"\003-)hn];cg\016\024\030NY3\025\013\021\n)%a\022\t\021\005m\021q\ba\001\003;A\001\"a\r\002@\001\007\021\021\n\031\005\003\027\ny\005\005\0033k\0055\003cA\035\002P\021Y\021\021KA$\003\003\005\tQ!\001=\005\ryF%\017\005\b\003\003JB\021IA+)\021\t\t\"a\026\t\021\005m\0211\013a\001\003;Aq!a\027\026\001\004\ti&\001\004tsN$X-\034\t\005\003?\ty&\003\003\002b\005\005\"aC!di>\0248+_:uK6D\021\"!\032\n#\003%\t!a\032\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0232+\t\tIGK\002%\003WZ#!!\034\021\t\005=\024\021P\007\003\003cRA!a\035\002v\005IQO\\2iK\016\\W\r\032\006\004\003or\021AC1o]>$\030\r^5p]&!\0211PA9\005E)hn\0315fG.,GMV1sS\006t7-\032")
/*     */ public class EventStream implements LoggingBus, SubchannelClassification {
/*     */   private final boolean debug;
/*     */   
/*     */   private final Object subclassification;
/*     */   
/*     */   private final SubclassifiedIndex<Object, Object> akka$event$SubchannelClassification$$subscriptions;
/*     */   
/*     */   private volatile Map<Object, Set<Object>> akka$event$SubchannelClassification$$cache;
/*     */   
/*     */   private final ReentrantGuard akka$event$LoggingBus$$guard;
/*     */   
/*     */   private Seq<ActorRef> akka$event$LoggingBus$$loggers;
/*     */   
/*     */   private int akka$event$LoggingBus$$_logLevel;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private SubclassifiedIndex akka$event$SubchannelClassification$$subscriptions$lzycompute() {
/*  26 */     synchronized (this) {
/*  26 */       if (!this.bitmap$0) {
/*  26 */         this.akka$event$SubchannelClassification$$subscriptions = SubchannelClassification$class.akka$event$SubchannelClassification$$subscriptions(this);
/*  26 */         this.bitmap$0 = true;
/*     */       } 
/*  26 */       return this.akka$event$SubchannelClassification$$subscriptions;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SubclassifiedIndex<Object, Object> akka$event$SubchannelClassification$$subscriptions() {
/*  26 */     return this.bitmap$0 ? this.akka$event$SubchannelClassification$$subscriptions : akka$event$SubchannelClassification$$subscriptions$lzycompute();
/*     */   }
/*     */   
/*     */   public Map<Object, Set<Object>> akka$event$SubchannelClassification$$cache() {
/*  26 */     return this.akka$event$SubchannelClassification$$cache;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$event$SubchannelClassification$$cache_$eq(Map<Object, Set<Object>> x$1) {
/*  26 */     this.akka$event$SubchannelClassification$$cache = x$1;
/*     */   }
/*     */   
/*     */   public void publish(Object event) {
/*  26 */     SubchannelClassification$class.publish(this, event);
/*     */   }
/*     */   
/*     */   public ReentrantGuard akka$event$LoggingBus$$guard() {
/*  26 */     return this.akka$event$LoggingBus$$guard;
/*     */   }
/*     */   
/*     */   public Seq<ActorRef> akka$event$LoggingBus$$loggers() {
/*  26 */     return this.akka$event$LoggingBus$$loggers;
/*     */   }
/*     */   
/*     */   public void akka$event$LoggingBus$$loggers_$eq(Seq<ActorRef> x$1) {
/*  26 */     this.akka$event$LoggingBus$$loggers = x$1;
/*     */   }
/*     */   
/*     */   public int akka$event$LoggingBus$$_logLevel() {
/*  26 */     return this.akka$event$LoggingBus$$_logLevel;
/*     */   }
/*     */   
/*     */   public void akka$event$LoggingBus$$_logLevel_$eq(int x$1) {
/*  26 */     this.akka$event$LoggingBus$$_logLevel = x$1;
/*     */   }
/*     */   
/*     */   public void akka$event$LoggingBus$_setter_$akka$event$LoggingBus$$guard_$eq(ReentrantGuard x$1) {
/*  26 */     this.akka$event$LoggingBus$$guard = x$1;
/*     */   }
/*     */   
/*     */   public int logLevel() {
/*  26 */     return LoggingBus$class.logLevel(this);
/*     */   }
/*     */   
/*     */   public void setLogLevel(int level) {
/*  26 */     LoggingBus$class.setLogLevel(this, level);
/*     */   }
/*     */   
/*     */   public void startStdoutLogger(ActorSystem.Settings config) {
/*  26 */     LoggingBus$class.startStdoutLogger(this, config);
/*     */   }
/*     */   
/*     */   public void startDefaultLoggers(ActorSystemImpl system) {
/*  26 */     LoggingBus$class.startDefaultLoggers(this, system);
/*     */   }
/*     */   
/*     */   public void stopDefaultLoggers(ActorSystem system) {
/*  26 */     LoggingBus$class.stopDefaultLoggers(this, system);
/*     */   }
/*     */   
/*     */   public int compareSubscribers(ActorRef a, ActorRef b) {
/*  26 */     return ActorEventBus$class.compareSubscribers(this, a, b);
/*     */   }
/*     */   
/*     */   private boolean debug() {
/*  26 */     return this.debug;
/*     */   }
/*     */   
/*     */   public EventStream(boolean debug) {
/*  26 */     ActorEventBus$class.$init$(this);
/*  26 */     LoggingBus$class.$init$(this);
/*  26 */     SubchannelClassification$class.$init$(this);
/*  31 */     this.subclassification = new $anon$1(this);
/*     */   }
/*     */   
/*     */   public Object subclassification() {
/*  31 */     return this.subclassification;
/*     */   }
/*     */   
/*     */   public class $anon$1 implements Subclassification<Class<?>> {
/*     */     public $anon$1(EventStream $outer) {}
/*     */     
/*     */     public boolean isEqual(Class x, Class y) {
/*  32 */       Class clazz = y;
/*  32 */       if (x == null) {
/*  32 */         if (clazz != null);
/*  32 */       } else if (x.equals(clazz)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isSubclass(Class<?> x, Class y) {
/*  33 */       return y.isAssignableFrom(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public Class<?> classify(Object event) {
/*  36 */     return event.getClass();
/*     */   }
/*     */   
/*     */   public void publish(Object event, ActorRef subscriber) {
/*  39 */     if (subscriber.isTerminated()) {
/*  39 */       unsubscribe(subscriber);
/*     */     } else {
/*  40 */       ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(subscriber);
/*  40 */       Object x$1 = event;
/*  40 */       ActorRef x$2 = qual$1.$bang$default$2(x$1);
/*  40 */       qual$1.$bang(x$1, x$2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean subscribe(ActorRef subscriber, Class channel) {
/*  44 */     if (subscriber == null)
/*  44 */       throw new IllegalArgumentException("subscriber is null"); 
/*  45 */     if (debug())
/*  45 */       publish(new Logging.Debug(Logging$.MODULE$.simpleName(this), getClass(), (new StringBuilder()).append("subscribing ").append(subscriber).append(" to channel ").append(channel).toString())); 
/*  46 */     return SubchannelClassification$class.subscribe(this, subscriber, channel);
/*     */   }
/*     */   
/*     */   public boolean unsubscribe(ActorRef subscriber, Class channel) {
/*  50 */     if (subscriber == null)
/*  50 */       throw new IllegalArgumentException("subscriber is null"); 
/*  51 */     boolean ret = SubchannelClassification$class.unsubscribe(this, subscriber, channel);
/*  52 */     if (debug())
/*  52 */       publish(new Logging.Debug(Logging$.MODULE$.simpleName(this), getClass(), (new StringBuilder()).append("unsubscribing ").append(subscriber).append(" from channel ").append(channel).toString())); 
/*  53 */     return ret;
/*     */   }
/*     */   
/*     */   public void unsubscribe(ActorRef subscriber) {
/*  57 */     if (subscriber == null)
/*  57 */       throw new IllegalArgumentException("subscriber is null"); 
/*  58 */     SubchannelClassification$class.unsubscribe(this, subscriber);
/*  59 */     if (debug())
/*  59 */       publish(new Logging.Debug(Logging$.MODULE$.simpleName(this), getClass(), (new StringBuilder()).append("unsubscribing ").append(subscriber).append(" from all channels").toString())); 
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$1() {
/*     */     return EventStream$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static EventStream fromActorSystem(ActorSystem paramActorSystem) {
/*     */     return EventStream$.MODULE$.fromActorSystem(paramActorSystem);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\EventStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */