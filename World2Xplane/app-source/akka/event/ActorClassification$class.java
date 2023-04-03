/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.TreeSet;
/*     */ import scala.collection.immutable.TreeSet$;
/*     */ import scala.math.Ordering$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class ActorClassification$class {
/*     */   public static void $init$(ActorClassification $this) {
/* 251 */     $this.akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$empty_$eq(TreeSet$.MODULE$.empty(Ordering$.MODULE$.ordered((Function1)Predef$.MODULE$.conforms())));
/* 252 */     $this.akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$mappings_$eq(new ConcurrentHashMap<Object, Object>($this.mapSize()));
/*     */   }
/*     */   
/*     */   public static final boolean associate(ActorClassification $this, ActorRef monitored, ActorRef monitor) {
/*     */     while (true) {
/* 256 */       TreeSet<ActorRef> current = $this.akka$event$ActorClassification$$mappings().get(monitored);
/* 257 */       TreeSet<ActorRef> treeSet1 = current;
/* 258 */       if (treeSet1 == null) {
/* 261 */         if ($this.akka$event$ActorClassification$$mappings().putIfAbsent(monitored, $this.akka$event$ActorClassification$$empty().$plus(monitor)) != null) {
/* 261 */           monitor = monitor;
/* 261 */           monitored = monitored;
/* 261 */           $this = $this;
/*     */           continue;
/*     */         } 
/* 262 */         boolean bool = monitored.isTerminated() ? false : (monitored.isTerminated() ? ($this.dissociate(monitored, monitor) ? false : true) : true);
/*     */       } else {
/* 264 */         if (treeSet1 != null) {
/* 264 */           TreeSet<ActorRef> treeSet2 = treeSet1;
/* 265 */           TreeSet<ActorRef> v = treeSet2;
/* 266 */           monitored.isTerminated() ? BoxesRunTime.boxToBoolean(false) : BoxedUnit.UNIT;
/* 269 */           TreeSet<ActorRef> added = v.$plus(monitor);
/* 270 */           if ($this.akka$event$ActorClassification$$mappings().replace(monitored, v, added)) {
/*     */           
/*     */           } else {
/* 270 */             monitor = monitor;
/* 270 */             monitored = monitored;
/* 270 */             $this = $this;
/*     */             continue;
/*     */           } 
/*     */           return v.contains(monitor) ? true : "JD-Core does not support Kotlin";
/*     */         } 
/*     */         throw new MatchError(treeSet1);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_6;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final Iterable dissociateAsMonitored$1(ActorClassification $this, ActorRef monitored) {
/*     */     while (true) {
/* 279 */       TreeSet treeSet2, current = $this.akka$event$ActorClassification$$mappings().get(monitored);
/* 280 */       TreeSet treeSet1 = current;
/* 281 */       if (treeSet1 == null) {
/* 281 */         treeSet2 = $this.akka$event$ActorClassification$$empty();
/*     */       } else {
/* 282 */         if (treeSet1 != null) {
/* 282 */           TreeSet treeSet3 = treeSet1;
/* 283 */           TreeSet v = treeSet3;
/* 284 */           if ($this.akka$event$ActorClassification$$mappings().remove(monitored, v))
/* 285 */             treeSet2 = v; 
/*     */           monitored = monitored;
/*     */           $this = $this;
/*     */           continue;
/*     */         } 
/*     */         throw new MatchError(treeSet1);
/*     */       } 
/*     */       return (Iterable)treeSet2;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final void dissociateAsMonitor$1(ActorClassification $this, ActorRef monitor) {
/* 290 */     Iterator<Map.Entry> i = $this.akka$event$ActorClassification$$mappings().entrySet().iterator();
/* 291 */     while (i.hasNext()) {
/*     */       BoxedUnit boxedUnit;
/* 292 */       Map.Entry entry = i.next();
/* 293 */       TreeSet v = (TreeSet)entry.getValue();
/* 294 */       TreeSet treeSet1 = v;
/* 295 */       if (treeSet1 != null) {
/* 295 */         TreeSet treeSet2 = treeSet1;
/* 296 */         TreeSet monitors = treeSet2;
/* 297 */         boxedUnit = (BoxedUnit)(monitors.contains(monitor) ? 
/* 298 */           BoxesRunTime.boxToBoolean($this.dissociate((ActorRef)entry.getKey(), monitor)) : BoxedUnit.UNIT);
/*     */       } else {
/* 299 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final Iterable dissociate(ActorClassification $this, ActorRef monitored) {
/*     */     try {
/* 304 */       return dissociateAsMonitored$1($this, monitored);
/*     */     } finally {
/* 304 */       dissociateAsMonitor$1($this, monitored);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final boolean dissociate(ActorClassification $this, ActorRef monitored, ActorRef monitor) {
/*     */     while (true) {
/* 309 */       TreeSet<ActorRef> current = $this.akka$event$ActorClassification$$mappings().get(monitored);
/* 310 */       TreeSet<ActorRef> treeSet1 = current;
/* 311 */       if (treeSet1 == null) {
/* 311 */         boolean bool = false;
/*     */       } else {
/* 312 */         if (treeSet1 != null) {
/* 312 */           TreeSet<ActorRef> treeSet2 = treeSet1;
/* 313 */           TreeSet<ActorRef> v = treeSet2;
/* 314 */           TreeSet<ActorRef> removed = v.$minus(monitor);
/* 316 */           if (removed.isEmpty()) {
/* 317 */             if ($this.akka$event$ActorClassification$$mappings().remove(monitored, v)) {
/*     */             
/*     */             } else {
/* 317 */               monitor = monitor;
/* 317 */               monitored = monitored;
/* 317 */               $this = $this;
/*     */               continue;
/*     */             } 
/* 319 */           } else if ($this.akka$event$ActorClassification$$mappings().replace(monitored, v, removed)) {
/*     */           
/*     */           } else {
/* 319 */             monitor = monitor;
/* 319 */             monitored = monitored;
/* 319 */             $this = $this;
/*     */             continue;
/*     */           } 
/*     */           return (removed == treeSet2) ? false : "JD-Core does not support Kotlin";
/*     */         } 
/*     */         throw new MatchError(treeSet1);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_6;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void publish(ActorClassification $this, Object event) {
/* 334 */     TreeSet treeSet = $this.akka$event$ActorClassification$$mappings().get($this.classify(event));
/* 335 */     if (treeSet == null) {
/* 335 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 336 */       treeSet.foreach((Function1)new ActorClassification$$anonfun$publish$2($this, event));
/* 336 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean subscribe(ActorClassification $this, ActorRef subscriber, ActorRef to) {
/* 340 */     if (subscriber == null)
/* 340 */       throw new IllegalArgumentException("Subscriber is null"); 
/* 341 */     if (to == null)
/* 341 */       throw new IllegalArgumentException("Classifier is null"); 
/* 342 */     return $this.associate(to, subscriber);
/*     */   }
/*     */   
/*     */   public static boolean unsubscribe(ActorClassification $this, ActorRef subscriber, ActorRef from) {
/* 345 */     if (subscriber == null)
/* 345 */       throw new IllegalArgumentException("Subscriber is null"); 
/* 346 */     if (from == null)
/* 346 */       throw new IllegalArgumentException("Classifier is null"); 
/* 347 */     return $this.dissociate(from, subscriber);
/*     */   }
/*     */   
/*     */   public static void unsubscribe(ActorClassification $this, ActorRef subscriber) {
/* 350 */     if (subscriber == null)
/* 350 */       throw new IllegalArgumentException("Subscriber is null"); 
/* 351 */     $this.dissociate(subscriber);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ActorClassification$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */