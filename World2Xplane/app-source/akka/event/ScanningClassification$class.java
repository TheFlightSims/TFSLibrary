/*     */ package akka.event;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import scala.MatchError;
/*     */ import scala.Tuple2;
/*     */ 
/*     */ public abstract class ScanningClassification$class {
/*     */   public static void $init$(ScanningClassification $this) {
/* 196 */     $this.akka$event$ScanningClassification$_setter_$subscribers_$eq(new ConcurrentSkipListSet(new ScanningClassification.$anon$2($this)));
/*     */   }
/*     */   
/*     */   public static boolean subscribe(ScanningClassification $this, Object subscriber, Object to) {
/* 223 */     return $this.subscribers().add(new Tuple2(to, subscriber));
/*     */   }
/*     */   
/*     */   public static boolean unsubscribe(ScanningClassification $this, Object subscriber, Object from) {
/* 225 */     return $this.subscribers().remove(new Tuple2(from, subscriber));
/*     */   }
/*     */   
/*     */   public static void unsubscribe(ScanningClassification $this, Object subscriber) {
/* 228 */     Iterator<Tuple2<Object, Object>> i = $this.subscribers().iterator();
/* 229 */     while (i.hasNext()) {
/* 230 */       Tuple2 e = i.next();
/* 231 */       if ($this.compareSubscribers(subscriber, e._2()) == 0)
/* 231 */         i.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void publish(ScanningClassification $this, Object event) {
/* 236 */     Iterator<Tuple2<Object, Object>> currentSubscribers = $this.subscribers().iterator();
/* 237 */     while (currentSubscribers.hasNext()) {
/* 238 */       Tuple2 tuple2 = currentSubscribers.next();
/* 238 */       if (tuple2 != null) {
/* 238 */         Object classifier = tuple2._1(), subscriber = tuple2._2();
/* 238 */         Tuple2 tuple22 = new Tuple2(classifier, subscriber), tuple21 = tuple22;
/* 238 */         Object object1 = tuple21._1(), object2 = tuple21._2();
/* 239 */         if ($this.matches(object1, event))
/* 240 */           $this.publish(event, object2); 
/*     */         continue;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ScanningClassification$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */