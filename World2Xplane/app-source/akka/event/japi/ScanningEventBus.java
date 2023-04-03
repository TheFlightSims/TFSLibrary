/*     */ package akka.event.japi;
/*     */ 
/*     */ import akka.event.EventBus;
/*     */ import akka.event.ScanningClassification;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import scala.Tuple2;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E4Q!\001\002\002\002%\021\001cU2b]:LgnZ#wK:$()^:\013\005\r!\021\001\0026ba&T!!\002\004\002\013\0254XM\034;\013\003\035\tA!Y6lC\016\001Q\003\002\006\030C\021\0322\001A\006\022!\taq\"D\001\016\025\005q\021!B:dC2\f\027B\001\t\016\005\031\te.\037*fMB)!cE\013!G5\t!!\003\002\025\005\tAQI^3oi\n+8\017\005\002\027/1\001A!\002\r\001\005\004I\"!A#\022\005ii\002C\001\007\034\023\taRBA\004O_RD\027N\\4\021\0051q\022BA\020\016\005\r\te.\037\t\003-\005\"QA\t\001C\002e\021\021a\025\t\003-\021\"Q!\n\001C\002e\021\021a\021\005\006O\001!\t\001K\001\007y%t\027\016\036 \025\003%\002RA\005\001\026A\rBqa\013\001C\002\023%A&A\002ckN,\022!\f\n\005]-\021TG\002\0030a\001i#\001\004\037sK\032Lg.Z7f]Rt\004BB\031\001A\003%Q&\001\003ckN\004\003CA\0325\033\005!\021B\001\013\005!\t\031d'\003\0028\t\t12kY1o]&twm\0217bgNLg-[2bi&|g.\002\003:]\001)\"!B#wK:$X\001B\036/\001\001\022!bU;cg\016\024\030NY3s\013\021id\006A\022\003\025\rc\027m]:jM&,'\017C\003@\001\031E\001)\001\nd_6\004\030M]3DY\006\0348/\0334jKJ\034HcA!E\rB\021ABQ\005\003\0076\0211!\0238u\021\025)e\b1\001$\003\005\t\007\"B$?\001\004\031\023!\0012\t\013%\003a\021\003&\002%\r|W\016]1sKN+(m]2sS\n,'o\035\013\004\003.c\005\"B#I\001\004\001\003\"B$I\001\004\001\003\"\002(\001\r#y\025aB7bi\016DWm\035\013\004!N+\006C\001\007R\023\t\021VBA\004C_>dW-\0318\t\013Qk\005\031A\022\002\025\rd\027m]:jM&,'\017C\003\006\033\002\007Q\003C\003X\001\031E\001,A\004qk\nd\027n\0355\025\007ecV\f\005\002\r5&\0211,\004\002\005+:LG\017C\003\006-\002\007Q\003C\003_-\002\007\001%\001\006tk\n\0348M]5cKJDQ\001\031\001\005B\005\f\021b];cg\016\024\030NY3\025\007A\0237\rC\003_?\002\007\001\005C\003e?\002\0071%\001\002u_\")a\r\001C!O\006YQO\\:vEN\034'/\0332f)\r\001\006.\033\005\006=\026\004\r\001\t\005\006U\026\004\raI\001\005MJ|W\016C\003g\001\021\005C\016\006\002Z[\")al\033a\001A!)q\013\001C!_R\021\021\f\035\005\006\0139\004\r!\006")
/*     */ public abstract class ScanningEventBus<E, S, C> implements EventBus<E, S, C> {
/* 143 */   private final EventBus bus = new $anon$4(this);
/*     */   
/*     */   private EventBus bus() {
/* 143 */     return this.bus;
/*     */   }
/*     */   
/*     */   public abstract int compareClassifiers(C paramC1, C paramC2);
/*     */   
/*     */   public abstract int compareSubscribers(S paramS1, S paramS2);
/*     */   
/*     */   public abstract boolean matches(C paramC, E paramE);
/*     */   
/*     */   public abstract void publish(E paramE, S paramS);
/*     */   
/*     */   public class $anon$4 implements EventBus, ScanningClassification {
/*     */     private final ConcurrentSkipListSet<Tuple2<Object, Object>> subscribers;
/*     */     
/*     */     public final ConcurrentSkipListSet<Tuple2<Object, Object>> subscribers() {
/* 143 */       return this.subscribers;
/*     */     }
/*     */     
/*     */     public final void akka$event$ScanningClassification$_setter_$subscribers_$eq(ConcurrentSkipListSet<Tuple2<Object, Object>> x$1) {
/* 143 */       this.subscribers = x$1;
/*     */     }
/*     */     
/*     */     public boolean subscribe(Object subscriber, Object to) {
/* 143 */       return ScanningClassification.class.subscribe(this, subscriber, to);
/*     */     }
/*     */     
/*     */     public boolean unsubscribe(Object subscriber, Object from) {
/* 143 */       return ScanningClassification.class.unsubscribe(this, subscriber, from);
/*     */     }
/*     */     
/*     */     public void unsubscribe(Object subscriber) {
/* 143 */       ScanningClassification.class.unsubscribe(this, subscriber);
/*     */     }
/*     */     
/*     */     public void publish(Object event) {
/* 143 */       ScanningClassification.class.publish(this, event);
/*     */     }
/*     */     
/*     */     public $anon$4(ScanningEventBus $outer) {
/* 143 */       ScanningClassification.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compareClassifiers(Object a, Object b) {
/* 149 */       return this.$outer.compareClassifiers(a, b);
/*     */     }
/*     */     
/*     */     public int compareSubscribers(Object a, Object b) {
/* 152 */       return this.$outer.compareSubscribers(a, b);
/*     */     }
/*     */     
/*     */     public boolean matches(Object classifier, Object event) {
/* 155 */       return this.$outer.matches(classifier, event);
/*     */     }
/*     */     
/*     */     public void publish(Object event, Object subscriber) {
/* 158 */       this.$outer.publish(event, subscriber);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean subscribe(Object subscriber, Object to) {
/* 181 */     return ((ScanningClassification)bus()).subscribe(subscriber, to);
/*     */   }
/*     */   
/*     */   public boolean unsubscribe(Object subscriber, Object from) {
/* 182 */     return ((ScanningClassification)bus()).unsubscribe(subscriber, from);
/*     */   }
/*     */   
/*     */   public void unsubscribe(Object subscriber) {
/* 183 */     ((ScanningClassification)bus()).unsubscribe(subscriber);
/*     */   }
/*     */   
/*     */   public void publish(Object event) {
/* 184 */     ((ScanningClassification)bus()).publish(event);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\japi\ScanningEventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */