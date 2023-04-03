/*     */ package akka.event.japi;
/*     */ 
/*     */ import akka.event.EventBus;
/*     */ import akka.event.SubchannelClassification;
/*     */ import akka.util.Subclassification;
/*     */ import akka.util.SubclassifiedIndex;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001%4Q!\001\002\002\002%\021!cU;cG\"\fgN\\3m\013Z,g\016\036\"vg*\0211\001B\001\005U\006\004\030N\003\002\006\r\005)QM^3oi*\tq!\001\003bW.\f7\001A\013\005\025]\tCeE\002\001\027E\001\"\001D\b\016\0035Q\021AD\001\006g\016\fG.Y\005\003!5\021a!\0218z%\0264\007#\002\n\024+\001\032S\"\001\002\n\005Q\021!\001C#wK:$()^:\021\005Y9B\002\001\003\0061\001\021\r!\007\002\002\013F\021!$\b\t\003\031mI!\001H\007\003\0179{G\017[5oOB\021ABH\005\003?5\0211!\0218z!\t1\022\005B\003#\001\t\007\021DA\001T!\t1B\005B\003&\001\t\007\021DA\001D\021\0259\003\001\"\001)\003\031a\024N\\5u}Q\t\021\006E\003\023\001U\0013\005C\004,\001\t\007I\021\002\027\002\007\t,8/F\001.%\021q3BM\033\007\t=\002\004!\f\002\ryI,g-\0338f[\026tGO\020\005\007c\001\001\013\021B\027\002\t\t,8\017\t\t\003gQj\021\001B\005\003)\021\001\"a\r\034\n\005]\"!\001G*vE\016D\027M\0348fY\016c\027m]:jM&\034\027\r^5p]\026!\021H\f\001\026\005\025)e/\0328u\013\021Yd\006\001\021\003\025M+(m]2sS\n,'/\002\003>]\001\031#AC\"mCN\034\030NZ5fe\")q\b\001D\001\001\006\t2/\0362dY\006\0348/\0334jG\006$\030n\0348\026\003\005\0032AQ#$\033\005\031%B\001#\007\003\021)H/\0337\n\005\031\033%!E*vE\016d\027m]:jM&\034\027\r^5p]\")\001\n\001D\t\023\006A1\r\\1tg&4\027\020\006\002$\025\")Qa\022a\001+!)A\n\001D\t\033\0069\001/\0362mSNDGc\001(R%B\021AbT\005\003!6\021A!\0268ji\")Qa\023a\001+!)1k\023a\001A\005Q1/\0362tGJL'-\032:\t\013U\003A\021\t,\002\023M,(m]2sS\n,GcA,[7B\021A\002W\005\00336\021qAQ8pY\026\fg\016C\003T)\002\007\001\005C\003])\002\0071%\001\002u_\")a\f\001C!?\006YQO\\:vEN\034'/\0332f)\r9\006-\031\005\006'v\003\r\001\t\005\006Ev\003\raI\001\005MJ|W\016C\003_\001\021\005C\r\006\002OK\")1k\031a\001A!)A\n\001C!OR\021a\n\033\005\006\013\031\004\r!\006")
/*     */ public abstract class SubchannelEventBus<E, S, C> implements EventBus<E, S, C> {
/*  99 */   private final EventBus bus = new $anon$3(this);
/*     */   
/*     */   private EventBus bus() {
/*  99 */     return this.bus;
/*     */   }
/*     */   
/*     */   public abstract Subclassification<C> subclassification();
/*     */   
/*     */   public abstract C classify(E paramE);
/*     */   
/*     */   public abstract void publish(E paramE, S paramS);
/*     */   
/*     */   public class $anon$3 implements EventBus, SubchannelClassification {
/*     */     private final SubclassifiedIndex<Object, Object> akka$event$SubchannelClassification$$subscriptions;
/*     */     
/*     */     private volatile Map<Object, Set<Object>> akka$event$SubchannelClassification$$cache;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private SubclassifiedIndex akka$event$SubchannelClassification$$subscriptions$lzycompute() {
/*  99 */       synchronized (this) {
/*  99 */         if (!this.bitmap$0) {
/*  99 */           this.akka$event$SubchannelClassification$$subscriptions = SubchannelClassification.class.akka$event$SubchannelClassification$$subscriptions(this);
/*  99 */           this.bitmap$0 = true;
/*     */         } 
/*  99 */         return this.akka$event$SubchannelClassification$$subscriptions;
/*     */       } 
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex<Object, Object> akka$event$SubchannelClassification$$subscriptions() {
/*  99 */       return this.bitmap$0 ? this.akka$event$SubchannelClassification$$subscriptions : akka$event$SubchannelClassification$$subscriptions$lzycompute();
/*     */     }
/*     */     
/*     */     public Map<Object, Set<Object>> akka$event$SubchannelClassification$$cache() {
/*  99 */       return this.akka$event$SubchannelClassification$$cache;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void akka$event$SubchannelClassification$$cache_$eq(Map<Object, Set<Object>> x$1) {
/*  99 */       this.akka$event$SubchannelClassification$$cache = x$1;
/*     */     }
/*     */     
/*     */     public boolean subscribe(Object subscriber, Object to) {
/*  99 */       return SubchannelClassification.class.subscribe(this, subscriber, to);
/*     */     }
/*     */     
/*     */     public boolean unsubscribe(Object subscriber, Object from) {
/*  99 */       return SubchannelClassification.class.unsubscribe(this, subscriber, from);
/*     */     }
/*     */     
/*     */     public void unsubscribe(Object subscriber) {
/*  99 */       SubchannelClassification.class.unsubscribe(this, subscriber);
/*     */     }
/*     */     
/*     */     public void publish(Object event) {
/*  99 */       SubchannelClassification.class.publish(this, event);
/*     */     }
/*     */     
/*     */     public $anon$3(SubchannelEventBus $outer) {
/*  99 */       SubchannelClassification.class.$init$(this);
/*     */     }
/*     */     
/*     */     public Subclassification<C> subclassification() {
/* 105 */       return this.$outer.subclassification();
/*     */     }
/*     */     
/*     */     public C classify(Object event) {
/* 108 */       return (C)this.$outer.classify(event);
/*     */     }
/*     */     
/*     */     public void publish(Object event, Object subscriber) {
/* 111 */       this.$outer.publish(event, subscriber);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean subscribe(Object subscriber, Object to) {
/* 129 */     return ((SubchannelClassification)bus()).subscribe(subscriber, to);
/*     */   }
/*     */   
/*     */   public boolean unsubscribe(Object subscriber, Object from) {
/* 130 */     return ((SubchannelClassification)bus()).unsubscribe(subscriber, from);
/*     */   }
/*     */   
/*     */   public void unsubscribe(Object subscriber) {
/* 131 */     ((SubchannelClassification)bus()).unsubscribe(subscriber);
/*     */   }
/*     */   
/*     */   public void publish(Object event) {
/* 132 */     ((SubchannelClassification)bus()).publish(event);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\japi\SubchannelEventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */