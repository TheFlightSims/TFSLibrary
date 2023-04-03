/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Util$;
/*     */ import scala.Option;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Seq;
/*     */ 
/*     */ public abstract class ActorRefFactory$class {
/*     */   public static void $init$(ActorRefFactory $this) {}
/*     */   
/*     */   public static ActorRef actorFor(ActorRefFactory $this, ActorPath path) {
/* 241 */     return $this.provider().actorFor(path);
/*     */   }
/*     */   
/*     */   public static ActorRef actorFor(ActorRefFactory $this, String path) {
/* 258 */     return $this.provider().actorFor($this.lookupRoot(), path);
/*     */   }
/*     */   
/*     */   public static ActorRef actorFor(ActorRefFactory $this, Iterable<String> path) {
/* 279 */     return $this.provider().actorFor($this.lookupRoot(), path);
/*     */   }
/*     */   
/*     */   public static ActorRef actorFor(ActorRefFactory $this, Iterable path) {
/* 303 */     return $this.provider().actorFor($this.lookupRoot(), (Iterable<String>)Util$.MODULE$.immutableSeq(path));
/*     */   }
/*     */   
/*     */   public static ActorSelection actorSelection(ActorRefFactory $this, String path) {
/*     */     ActorSelection actorSelection;
/* 312 */     String str = path;
/* 313 */     Option<Seq<String>> option = RelativeActorPath$.MODULE$.unapply(str);
/* 313 */     if (option.isEmpty()) {
/* 317 */       Option<Tuple2<Address, Iterable<String>>> option1 = ActorPathExtractor$.MODULE$.unapply(str);
/* 317 */       if (option1.isEmpty()) {
/* 320 */         actorSelection = ActorSelection$.MODULE$.apply($this.provider().deadLetters(), "");
/*     */       } else {
/*     */         Address address = (Address)((Tuple2)option1.get())._1();
/*     */         Iterable elems = (Iterable)((Tuple2)option1.get())._2();
/*     */         actorSelection = ActorSelection$.MODULE$.apply($this.provider().rootGuardianAt(address), (Iterable<String>)elems);
/*     */       } 
/*     */     } else {
/*     */       Seq elems = (Seq)option.get();
/*     */       actorSelection = elems.isEmpty() ? ActorSelection$.MODULE$.apply($this.provider().deadLetters(), "") : (((String)elems.head()).isEmpty() ? ActorSelection$.MODULE$.apply($this.provider().rootGuardian(), (Iterable<String>)elems.tail()) : ActorSelection$.MODULE$.apply($this.lookupRoot(), (Iterable<String>)elems));
/*     */     } 
/*     */     return actorSelection;
/*     */   }
/*     */   
/*     */   public static ActorSelection actorSelection(ActorRefFactory $this, ActorPath path) {
/* 331 */     return ActorSelection$.MODULE$.apply($this.provider().rootGuardianAt(path.address()), (Iterable<String>)path.elements());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRefFactory$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */