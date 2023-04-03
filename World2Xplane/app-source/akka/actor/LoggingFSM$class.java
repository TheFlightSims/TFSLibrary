/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeq$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.package$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class LoggingFSM$class {
/*     */   public static int logDepth(LoggingFSM $this) {
/* 697 */     return 0;
/*     */   }
/*     */   
/*     */   public static void $init$(LoggingFSM $this) {
/* 699 */     $this.akka$actor$LoggingFSM$_setter_$debugEvent_$eq($this.context().system().settings().FsmDebugEvent());
/* 701 */     $this.akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$events_$eq(new FSM.Event[$this.logDepth()]);
/* 702 */     $this.akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$states_$eq(new Object[$this.logDepth()]);
/* 703 */     $this.akka$actor$LoggingFSM$$pos_$eq(0);
/* 704 */     $this.akka$actor$LoggingFSM$$full_$eq(false);
/*     */   }
/*     */   
/*     */   private static void advance(LoggingFSM $this) {
/* 707 */     int n = $this.akka$actor$LoggingFSM$$pos() + 1;
/* 708 */     if (n == $this.logDepth()) {
/* 709 */       $this.akka$actor$LoggingFSM$$full_$eq(true);
/* 710 */       $this.akka$actor$LoggingFSM$$pos_$eq(0);
/*     */     } else {
/* 712 */       $this.akka$actor$LoggingFSM$$pos_$eq(n);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void processEvent(LoggingFSM $this, FSM.Event<D> event, Object source) {
/* 717 */     if ($this.debugEvent()) {
/*     */       String str1;
/* 718 */       Object object = source;
/* 719 */       if (object instanceof String) {
/* 719 */         String str = (String)object;
/* 720 */       } else if (object instanceof FSM.Timer) {
/* 720 */         FSM.Timer timer = (FSM.Timer)object;
/* 720 */         String name = timer.name();
/* 720 */         str1 = (new StringBuilder()).append("timer ").append(name).toString();
/* 721 */       } else if (object instanceof ActorRef) {
/* 721 */         ActorRef actorRef = (ActorRef)object;
/* 721 */         str1 = actorRef.toString();
/*     */       } else {
/* 722 */         str1 = "unknown";
/*     */       } 
/*     */       String srcstr = str1;
/* 724 */       $this.log().debug((new StringBuilder()).append("processing ").append(event).append(" from ").append(srcstr).toString());
/*     */     } 
/* 727 */     if ($this.logDepth() > 0) {
/* 728 */       $this.akka$actor$LoggingFSM$$states()[$this.akka$actor$LoggingFSM$$pos()] = $this.stateName();
/* 729 */       $this.akka$actor$LoggingFSM$$events()[$this.akka$actor$LoggingFSM$$pos()] = event;
/* 730 */       advance($this);
/*     */     } 
/* 733 */     Object oldState = $this.stateName();
/* 734 */     $this.akka$actor$LoggingFSM$$super$processEvent(event, source);
/* 735 */     Object newState = $this.stateName();
/* 737 */     if ($this.debugEvent() && !BoxesRunTime.equals(oldState, newState))
/* 738 */       $this.log().debug((new StringBuilder()).append("transition ").append(oldState).append(" -> ").append(newState).toString()); 
/*     */   }
/*     */   
/*     */   public static IndexedSeq getLog(LoggingFSM<S, D> $this) {
/* 747 */     FSM.LogEntry[] log = (FSM.LogEntry[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])$this.akka$actor$LoggingFSM$$events()).zip((GenIterable)Predef$.MODULE$.wrapRefArray($this.akka$actor$LoggingFSM$$states()), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(Tuple2.class)))).filter((Function1)new LoggingFSM$$anonfun$2($this))).map((Function1)new LoggingFSM$$anonfun$3($this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(FSM.LogEntry.class)));
/* 748 */     return $this.akka$actor$LoggingFSM$$full() ? 
/* 749 */       (IndexedSeq)((TraversableLike)((TraversableLike)package$.MODULE$.IndexedSeq().apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])log).drop($this.akka$actor$LoggingFSM$$pos())), IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])log).take($this.akka$actor$LoggingFSM$$pos())), IndexedSeq$.MODULE$.canBuildFrom()) : 
/*     */       
/* 751 */       (IndexedSeq)((TraversableLike)package$.MODULE$.IndexedSeq().apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)Predef$.MODULE$.refArrayOps((Object[])log), IndexedSeq$.MODULE$.canBuildFrom());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LoggingFSM$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */