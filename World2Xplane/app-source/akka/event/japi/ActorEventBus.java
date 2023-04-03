/*     */ package akka.event.japi;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.event.ActorClassification;
/*     */ import akka.event.ActorClassifier;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.TreeSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0054Q!\001\002\002\002%\021Q\"Q2u_J,e/\0328u\005V\034(BA\002\005\003\021Q\027\r]5\013\005\0251\021!B3wK:$(\"A\004\002\t\005\\7.Y\002\001+\tQqcE\002\001\027E\001\"\001D\b\016\0035Q\021AD\001\006g\016\fG.Y\005\003!5\021a!\0218z%\0264\007#\002\n\024+\001\002S\"\001\002\n\005Q\021!\001C#wK:$()^:\021\005Y9B\002\001\003\0061\001\021\r!\007\002\002\013F\021!$\b\t\003\031mI!\001H\007\003\0179{G\017[5oOB\021ABH\005\003?5\0211!\0218z!\t\tC%D\001#\025\t\031c!A\003bGR|'/\003\002&E\tA\021i\031;peJ+g\rC\003(\001\021\005\001&\001\004=S:LGO\020\013\002SA\031!\003A\013\t\017-\002!\031!C\005Y\005\031!-^:\026\0035\022RAL\0063ka2Aa\f\031\001[\taAH]3gS:,W.\0328u}!1\021\007\001Q\001\n5\nAAY;tAA\0211\007N\007\002\t%\021\021\001\002\t\003gYJ!a\016\003\003'\005\033Go\034:DY\006\0348/\0334jG\006$\030n\0348\021\005MJ\024B\001\036\005\005=\t5\r^8s\0072\f7o]5gS\026\024X\001\002\037/\001U\021Q!\022<f]RDQA\020\001\007\022}\nq!\\1q'&TX\rF\001A!\ta\021)\003\002C\033\t\031\021J\034;\t\013\021\003a\021C#\002\021\rd\027m]:jMf$\"\001\t$\t\013\025\031\005\031A\013\t\013!\003A\021I%\002\023M,(m]2sS\n,Gc\001&N\037B\021AbS\005\003\0316\021qAQ8pY\026\fg\016C\003O\017\002\007\001%\001\006tk\n\0348M]5cKJDQ\001U$A\002\001\n!\001^8\t\013I\003A\021I*\002\027Ut7/\0362tGJL'-\032\013\004\025R+\006\"\002(R\001\004\001\003\"\002,R\001\004\001\023\001\0024s_6DQA\025\001\005Ba#\"!\027/\021\0051Q\026BA.\016\005\021)f.\033;\t\0139;\006\031\001\021\t\013y\003A\021I0\002\017A,(\r\\5tQR\021\021\f\031\005\006\013u\003\r!\006")
/*     */ public abstract class ActorEventBus<E> implements EventBus<E, ActorRef, ActorRef> {
/* 194 */   private final akka.event.ActorEventBus bus = new $anon$1(this);
/*     */   
/*     */   private akka.event.ActorEventBus bus() {
/* 194 */     return this.bus;
/*     */   }
/*     */   
/*     */   public class $anon$1 implements akka.event.ActorEventBus, ActorClassification, ActorClassifier {
/*     */     private final TreeSet<ActorRef> akka$event$ActorClassification$$empty;
/*     */     
/*     */     private final ConcurrentHashMap<ActorRef, TreeSet<ActorRef>> akka$event$ActorClassification$$mappings;
/*     */     
/*     */     public TreeSet<ActorRef> akka$event$ActorClassification$$empty() {
/* 194 */       return this.akka$event$ActorClassification$$empty;
/*     */     }
/*     */     
/*     */     public ConcurrentHashMap<ActorRef, TreeSet<ActorRef>> akka$event$ActorClassification$$mappings() {
/* 194 */       return this.akka$event$ActorClassification$$mappings;
/*     */     }
/*     */     
/*     */     public void akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$empty_$eq(TreeSet<ActorRef> x$1) {
/* 194 */       this.akka$event$ActorClassification$$empty = x$1;
/*     */     }
/*     */     
/*     */     public void akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$mappings_$eq(ConcurrentHashMap<ActorRef, TreeSet<ActorRef>> x$1) {
/* 194 */       this.akka$event$ActorClassification$$mappings = x$1;
/*     */     }
/*     */     
/*     */     public final boolean associate(ActorRef monitored, ActorRef monitor) {
/* 194 */       return ActorClassification.class.associate(this, monitored, monitor);
/*     */     }
/*     */     
/*     */     public final Iterable<ActorRef> dissociate(ActorRef monitored) {
/* 194 */       return ActorClassification.class.dissociate(this, monitored);
/*     */     }
/*     */     
/*     */     public final boolean dissociate(ActorRef monitored, ActorRef monitor) {
/* 194 */       return ActorClassification.class.dissociate(this, monitored, monitor);
/*     */     }
/*     */     
/*     */     public void publish(Object event) {
/* 194 */       ActorClassification.class.publish(this, event);
/*     */     }
/*     */     
/*     */     public boolean subscribe(ActorRef subscriber, ActorRef to) {
/* 194 */       return ActorClassification.class.subscribe(this, subscriber, to);
/*     */     }
/*     */     
/*     */     public boolean unsubscribe(ActorRef subscriber, ActorRef from) {
/* 194 */       return ActorClassification.class.unsubscribe(this, subscriber, from);
/*     */     }
/*     */     
/*     */     public void unsubscribe(ActorRef subscriber) {
/* 194 */       ActorClassification.class.unsubscribe(this, subscriber);
/*     */     }
/*     */     
/*     */     public int compareSubscribers(ActorRef a, ActorRef b) {
/* 194 */       return akka.event.ActorEventBus.class.compareSubscribers(this, a, b);
/*     */     }
/*     */     
/*     */     public $anon$1(ActorEventBus $outer) {
/* 194 */       akka.event.ActorEventBus.class.$init$(this);
/* 194 */       ActorClassification.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int mapSize() {
/* 197 */       return this.$outer.mapSize();
/*     */     }
/*     */     
/*     */     public ActorRef classify(Object event) {
/* 200 */       return this.$outer.classify(event);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean subscribe(ActorRef subscriber, ActorRef to) {
/* 213 */     return ((ActorClassification)bus()).subscribe(subscriber, to);
/*     */   }
/*     */   
/*     */   public boolean unsubscribe(ActorRef subscriber, ActorRef from) {
/* 214 */     return ((ActorClassification)bus()).unsubscribe(subscriber, from);
/*     */   }
/*     */   
/*     */   public void unsubscribe(ActorRef subscriber) {
/* 215 */     ((ActorClassification)bus()).unsubscribe(subscriber);
/*     */   }
/*     */   
/*     */   public void publish(Object event) {
/* 216 */     ((ActorClassification)bus()).publish(event);
/*     */   }
/*     */   
/*     */   public abstract int mapSize();
/*     */   
/*     */   public abstract ActorRef classify(E paramE);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\japi\ActorEventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */