/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorCell$;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Address;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Terminated;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.Unwatch;
/*     */ import akka.dispatch.sysmsg.Watch;
/*     */ import akka.event.Logging;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ucAC\001\003!\003\r\tA\002\005\002X\tQA)Z1uQ^\013Go\0315\013\005\r!\021a\0023v]\036,wN\034\006\003\013\031\tQ!Y2u_JT\021aB\001\005C.\\\027m\005\002\001\023A\021!\"D\007\002\027)\tA\"A\003tG\006d\027-\003\002\017\027\t1\021I\\=SK\032DQ\001\005\001\005\002I\ta\001J5oSR$3\001\001\013\002'A\021!\002F\005\003+-\021A!\0268ji\"9q\003\001a\001\n\023A\022\001C<bi\016D\027N\\4\026\003e\0012AG\017!\035\tQ1$\003\002\035\027\0051\001K]3eK\032L!AH\020\003\007M+GO\003\002\035\027A\021\021EI\007\002\t%\0211\005\002\002\t\003\016$xN\035*fM\"9Q\005\001a\001\n\0231\023\001D<bi\016D\027N\\4`I\025\fHCA\n(\021\035AC%!AA\002e\t1\001\037\0232\021\031Q\003\001)Q\0053\005Iq/\031;dQ&tw\r\t\005\bY\001\001\r\021\"\003\031\003%9\030\r^2iK\022\024\025\020C\004/\001\001\007I\021B\030\002\033]\fGo\0315fI\nKx\fJ3r)\t\031\002\007C\004)[\005\005\t\031A\r\t\rI\002\001\025)\003\032\003)9\030\r^2iK\022\024\025\020\t\005\bi\001\001\r\021\"\003\031\003A!XM]7j]\006$X\rZ)vKV,G\rC\0047\001\001\007I\021B\034\002)Q,'/\\5oCR,G-U;fk\026$w\fJ3r)\t\031\002\bC\004)k\005\005\t\031A\r\t\ri\002\001\025)\003\032\003E!XM]7j]\006$X\rZ)vKV,G\r\t\005\006y\001!)%P\001\006o\006$8\r\033\013\003AyBQaP\036A\002\001\nqa];cU\026\034G\017C\003B\001\021\025#)A\004v]^\fGo\0315\025\005\001\032\005\"B A\001\004\001\003\"B#\001\t#1\025A\005:fG\026Lg/\0323UKJl\027N\\1uK\022$\"aE$\t\013!#\005\031A%\002\003Q\004\"!\t&\n\005-#!A\003+fe6Lg.\031;fI\")Q\n\001C\t\035\0061r/\031;dQ\026$\027i\031;peR+'/\\5oCR,G\r\006\003\024\037B+\006\"B\003M\001\004\001\003\"B)M\001\004\021\026AE3ySN$XM\\2f\007>tg-\033:nK\022\004\"AC*\n\005Q[!a\002\"p_2,\027M\034\005\006-2\003\rAU\001\022C\022$'/Z:t)\026\024X.\0338bi\026$\007B\002-\001\t\0031\021,A\nuKJl\027N\\1uK\022\fV/Z;fI\032{'\017\006\002\0245\")qh\026a\001A!)A\f\001C\005;\006\001r/\031;dQ&twmQ8oi\006Lgn\035\013\003%zCQaP.A\002\001BQ\001\031\001\005\n\005\fQB]3n_Z,gI]8n'\026$HcA\rcG\")qh\030a\001A!)Am\030a\0013\005\0311/\032;\t\013\031\004A\021\003\n\002%Q,G\016\\,bi\016DWM]:XK\022KW\r\032\005\006Q\002!\t\"[\001\025k:<\030\r^2i/\006$8\r[3e\003\016$xN]:\025\005MQ\007\"B\003h\001\004Y\007CA\021m\023\tiGAA\003BGR|'\017C\003p\001\021E\001/\001\006bI\022<\026\r^2iKJ$2aE9t\021\025\021h\0161\001!\003\0359\030\r^2iK\026DQ\001\0368A\002\001\nqa^1uG\",'\017C\003w\001\021Eq/\001\006sK6<\026\r^2iKJ$2a\005=z\021\025\021X\0171\001!\021\025!X\0171\001!\021\0251\006\001\"\005|)\t\031B\020C\003~u\002\007a0A\004bI\022\024Xm]:\021\005\005z\030bAA\001\t\t9\021\t\0323sKN\034\bbBA\003\001\021%\021qA\001&[\006Lg\016^1j]\006#GM]3tgR+'/\\5oCR,GmU;cg\016\024\030\016\035;j_:,B!!\003\002\022Q!\0211BA\027)\021\ti!a\t\021\t\005=\021\021\003\007\001\t!\t\031\"a\001C\002\005U!!\001+\022\t\005]\021Q\004\t\004\025\005e\021bAA\016\027\t9aj\034;iS:<\007c\001\006\002 %\031\021\021E\006\003\007\005s\027\020C\005\002&\005\rA\0211\001\002(\005)!\r\\8dWB)!\"!\013\002\016%\031\0211F\006\003\021q\022\027P\\1nKzB\021\"a\f\002\004A\005\t\031\001\021\002\r\rD\027M\\4f\021\031\t\031\004\001C\005%\005aRO\\:vEN\034'/\0332f\003\022$'/Z:t)\026\024X.\0338bi\026$\007BBA\034\001\021%!#\001\016tk\n\0348M]5cK\006#GM]3tgR+'/\\5oCR,G\rC\005\002<\001\t\n\021\"\003\002>\005yS.Y5oi\006Lg.\0213ee\026\0348\017V3s[&t\027\r^3e'V\0247o\031:jaRLwN\034\023eK\032\fW\017\034;%cU!\021qHA++\t\t\tEK\002!\003\007Z#!!\022\021\t\005\035\023\021K\007\003\003\023RA!a\023\002N\005IQO\\2iK\016\\W\r\032\006\004\003\037Z\021AC1o]>$\030\r^5p]&!\0211KA%\005E)hn\0315fG.,GMV1sS\006t7-\032\003\t\003'\tID1\001\002\026A\031\021%!\027\n\007\005mCAA\005BGR|'oQ3mY\002")
/*     */ public interface DeathWatch {
/*     */   Set<ActorRef> akka$actor$dungeon$DeathWatch$$watching();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$DeathWatch$$watching_$eq(Set<ActorRef> paramSet);
/*     */   
/*     */   Set<ActorRef> akka$actor$dungeon$DeathWatch$$watchedBy();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$DeathWatch$$watchedBy_$eq(Set<ActorRef> paramSet);
/*     */   
/*     */   Set<ActorRef> akka$actor$dungeon$DeathWatch$$terminatedQueued();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq(Set<ActorRef> paramSet);
/*     */   
/*     */   ActorRef watch(ActorRef paramActorRef);
/*     */   
/*     */   ActorRef unwatch(ActorRef paramActorRef);
/*     */   
/*     */   void receivedTerminated(Terminated paramTerminated);
/*     */   
/*     */   void watchedActorTerminated(ActorRef paramActorRef, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   void terminatedQueuedFor(ActorRef paramActorRef);
/*     */   
/*     */   void tellWatchersWeDied();
/*     */   
/*     */   void unwatchWatchedActors(Actor paramActor);
/*     */   
/*     */   void addWatcher(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   void remWatcher(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   void addressTerminated(Address paramAddress);
/*     */   
/*     */   public class DeathWatch$$anonfun$watch$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InternalActorRef x2$1;
/*     */     
/*     */     public final void apply() {
/*  23 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$watch$1(ActorCell $outer, InternalActorRef x2$1) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/*  24 */       this.x2$1.sendSystemMessage((SystemMessage)new Watch(this.x2$1, this.$outer.self()));
/*  25 */       this.$outer.akka$actor$dungeon$DeathWatch$$watching_$eq((Set)this.$outer.akka$actor$dungeon$DeathWatch$$watching().$plus(this.x2$1));
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$unwatch$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InternalActorRef x2$2;
/*     */     
/*     */     public final void apply() {
/*  36 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*  36 */       this.$outer.akka$actor$dungeon$DeathWatch$$watching_$eq(DeathWatch$class.akka$actor$dungeon$DeathWatch$$removeFromSet(this.$outer, (ActorRef)this.x2$2, this.$outer.akka$actor$dungeon$DeathWatch$$watching()));
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$unwatch$1(ActorCell $outer, InternalActorRef x2$2) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$watchedActorTerminated$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef actor$1;
/*     */     
/*     */     public final void apply() {
/*  56 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*  56 */       this.$outer.akka$actor$dungeon$DeathWatch$$watching_$eq(DeathWatch$class.akka$actor$dungeon$DeathWatch$$removeFromSet(this.$outer, this.actor$1, this.$outer.akka$actor$dungeon$DeathWatch$$watching()));
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$watchedActorTerminated$1(ActorCell $outer, ActorRef actor$1) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$akka$actor$dungeon$DeathWatch$$removeFromSet$1 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef subject$1;
/*     */     
/*     */     public final boolean apply(ActorRef x$1) {
/*  79 */       ActorPath actorPath = this.subject$1.path();
/*  79 */       if (x$1.path() == null) {
/*  79 */         x$1.path();
/*  79 */         if (actorPath != null);
/*  79 */       } else if (x$1.path().equals(actorPath)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$akka$actor$dungeon$DeathWatch$$removeFromSet$1(ActorCell $outer, ActorRef subject$1) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$tellWatchersWeDied$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef watcher) {
/* 102 */       DeathWatch$class.sendTerminated$1(this.$outer, false, watcher);
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$tellWatchersWeDied$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$tellWatchersWeDied$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef watcher) {
/* 103 */       DeathWatch$class.sendTerminated$1(this.$outer, true, watcher);
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$tellWatchersWeDied$2(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$unwatchWatchedActors$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 110 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$unwatchWatchedActors$1(ActorCell $outer) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       try {
/* 111 */         this.$outer.akka$actor$dungeon$DeathWatch$$watching().foreach((Function1)new DeathWatch$$anonfun$unwatchWatchedActors$1$$anonfun$apply$mcV$sp$1(this));
/*     */         return;
/*     */       } finally {
/* 115 */         this.$outer.akka$actor$dungeon$DeathWatch$$watching_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/* 116 */         this.$outer.akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/*     */       } 
/*     */     }
/*     */     
/*     */     public class DeathWatch$$anonfun$unwatchWatchedActors$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(ActorRef x0$1) {
/*     */         ActorRef actorRef = x0$1;
/*     */         if (actorRef instanceof InternalActorRef) {
/*     */           InternalActorRef internalActorRef = (InternalActorRef)actorRef;
/*     */           internalActorRef.sendSystemMessage((SystemMessage)new Unwatch((ActorRef)internalActorRef, (ActorRef)this.$outer.akka$actor$dungeon$DeathWatch$$anonfun$$$outer().self()));
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */           return;
/*     */         } 
/*     */         throw new MatchError(actorRef);
/*     */       }
/*     */       
/*     */       public DeathWatch$$anonfun$unwatchWatchedActors$1$$anonfun$apply$mcV$sp$1(DeathWatch$$anonfun$unwatchWatchedActors$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$addWatcher$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef watcher$1;
/*     */     
/*     */     public final void apply() {
/* 126 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$addWatcher$1(ActorCell $outer, ActorRef watcher$1) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/* 127 */       this.$outer.akka$actor$dungeon$DeathWatch$$watchedBy_$eq((Set)this.$outer.akka$actor$dungeon$DeathWatch$$watchedBy().$plus(this.watcher$1));
/* 128 */       if (this.$outer.system().settings().DebugLifecycle()) {
/* 128 */         (new String[2])[0] = "now watched by ";
/* 128 */         (new String[2])[1] = "";
/* 128 */         this.$outer.publish((Logging.LogEvent)new Logging.Debug(this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.watcher$1 }))));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$remWatcher$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef watcher$2;
/*     */     
/*     */     public final void apply() {
/* 142 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$remWatcher$1(ActorCell $outer, ActorRef watcher$2) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/* 143 */       this.$outer.akka$actor$dungeon$DeathWatch$$watchedBy_$eq((Set)this.$outer.akka$actor$dungeon$DeathWatch$$watchedBy().$minus(this.watcher$2));
/* 144 */       if (this.$outer.system().settings().DebugLifecycle()) {
/* 144 */         (new String[2])[0] = "no longer watched by ";
/* 144 */         (new String[2])[1] = "";
/* 144 */         this.$outer.publish((Logging.LogEvent)new Logging.Debug(this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.watcher$2 }))));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$addressTerminated$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Address address$1;
/*     */     
/*     */     public final void apply() {
/* 156 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 156 */       this.$outer.akka$actor$dungeon$DeathWatch$$watchedBy().withFilter((Function1)new DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$2(this)).foreach((Function1)new DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$3(this));
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$addressTerminated$1(ActorCell $outer, Address address$1) {}
/*     */     
/*     */     public class DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(ActorRef a) {
/* 156 */         Address address = this.$outer.address$1;
/* 156 */         if (a.path().address() == null) {
/* 156 */           a.path().address();
/* 156 */           if (address != null);
/* 156 */         } else if (a.path().address().equals(address)) {
/*     */         
/*     */         } 
/*     */       }
/*     */       
/*     */       public DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$2(DeathWatch$$anonfun$addressTerminated$1 $outer) {}
/*     */     }
/*     */     
/*     */     public class DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$3 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(ActorRef a) {
/* 156 */         this.$outer.akka$actor$dungeon$DeathWatch$$anonfun$$$outer().akka$actor$dungeon$DeathWatch$$watchedBy_$eq((Set)this.$outer.akka$actor$dungeon$DeathWatch$$anonfun$$$outer().akka$actor$dungeon$DeathWatch$$watchedBy().$minus(a));
/*     */       }
/*     */       
/*     */       public DeathWatch$$anonfun$addressTerminated$1$$anonfun$apply$mcV$sp$3(DeathWatch$$anonfun$addressTerminated$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$addressTerminated$2 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Address address$1;
/*     */     
/*     */     public final boolean apply(ActorRef a) {
/* 165 */       Address address = this.address$1;
/* 165 */       if (a.path().address() == null) {
/* 165 */         a.path().address();
/* 165 */         if (address != null);
/* 165 */       } else if (a.path().address().equals(address)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$addressTerminated$2(ActorCell $outer, Address address$1) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$addressTerminated$3 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public DeathWatch$$anonfun$addressTerminated$3(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(ActorRef a) {
/* 166 */       this.$outer.self().sendSystemMessage((SystemMessage)new DeathWatchNotification(a, this.$outer.childrenRefs().getByRef(a).isDefined(), true));
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$hasNonLocalAddress$1$1 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(ActorRef ref) {
/* 184 */       return DeathWatch$class.isNonLocal$1(this.$outer, ref);
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$hasNonLocalAddress$1$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class DeathWatch$$anonfun$hasNonLocalAddress$1$2 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(ActorRef ref) {
/* 184 */       return DeathWatch$class.isNonLocal$1(this.$outer, ref);
/*     */     }
/*     */     
/*     */     public DeathWatch$$anonfun$hasNonLocalAddress$1$2(ActorCell $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\DeathWatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */