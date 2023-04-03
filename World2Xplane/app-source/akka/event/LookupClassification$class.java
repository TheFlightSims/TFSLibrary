/*     */ package akka.event;
/*     */ 
/*     */ import akka.util.Index;
/*     */ import scala.collection.Iterator;
/*     */ 
/*     */ public abstract class LookupClassification$class {
/*     */   public static void $init$(LookupClassification $this) {
/*  83 */     $this.akka$event$LookupClassification$_setter_$subscribers_$eq(new Index($this.mapSize(), new LookupClassification.$anon$1($this)));
/*     */   }
/*     */   
/*     */   public static boolean subscribe(LookupClassification $this, Object subscriber, Object to) {
/* 107 */     return $this.subscribers().put(to, subscriber);
/*     */   }
/*     */   
/*     */   public static boolean unsubscribe(LookupClassification $this, Object subscriber, Object from) {
/* 109 */     return $this.subscribers().remove(from, subscriber);
/*     */   }
/*     */   
/*     */   public static void unsubscribe(LookupClassification $this, Object subscriber) {
/* 111 */     $this.subscribers().removeValue(subscriber);
/*     */   }
/*     */   
/*     */   public static void publish(LookupClassification $this, Object event) {
/* 114 */     Iterator i = $this.subscribers().valueIterator($this.classify(event));
/* 115 */     for (; i.hasNext(); $this.publish(event, i.next()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LookupClassification$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */