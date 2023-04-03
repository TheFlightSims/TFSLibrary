/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001%3A!\001\002\001\017\t\001bj\0343f\033\026\0348/Y4f#V,W/\032\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031B\001\001\005\020%A\031\021B\003\007\016\003\tI!a\003\002\003#\005\0237\017\036:bGRtu\016Z3Rk\026,X\r\005\002\n\033%\021aB\001\002\t\013:4X\r\\8qKB\021\021\002E\005\003#\t\021A\"T3tg\006<W-U;fk\026\004\"!C\n\n\005Q\021!AH+oE>,h\016Z3e\033\026\0348/Y4f#V,W/Z*f[\006tG/[2t\021\0251\002\001\"\001\030\003\031a\024N\\5u}Q\t\001\004\005\002\n\001!)!\004\001C\0037\0059QM\\9vKV,Gc\001\017#UA\021Q\004I\007\002=)\tq$A\003tG\006d\027-\003\002\"=\t!QK\\5u\021\025\031\023\0041\001%\003!\021XmY3jm\026\024\bCA\023)\033\0051#BA\024\005\003\025\t7\r^8s\023\tIcE\001\005BGR|'OU3g\021\025Y\023\0041\001\r\003\031A\027M\0343mK\")Q\006\001C\003]\0059A-Z9vKV,G#\001\007\t\013A\002AQA\031\002!9,XNY3s\037\032lUm]:bO\026\034X#\001\032\021\005u\031\024B\001\033\037\005\rIe\016\036\005\006m\001!)aN\001\fQ\006\034X*Z:tC\036,7/F\0019!\ti\022(\003\002;=\t9!i\\8mK\006t\007\"\002\037\001\t\013i\024aB2mK\006tW\013\035\013\0049y\002\005\"B <\001\004!\023!B8x]\026\024\b\"B!<\001\004y\021a\0033fC\022dU\r\036;feND#aO\"\021\005\021;U\"A#\013\005\031s\022AC1o]>$\030\r^5p]&\021\001*\022\002\bi\006LGN]3d\001")
/*     */ public class NodeMessageQueue extends AbstractNodeQueue<Envelope> implements MessageQueue, UnboundedMessageQueueSemantics {
/*     */   public final void enqueue(ActorRef receiver, Envelope handle) {
/* 353 */     add(handle);
/*     */   }
/*     */   
/*     */   public final Envelope dequeue() {
/* 355 */     return poll();
/*     */   }
/*     */   
/*     */   public final int numberOfMessages() {
/* 357 */     return count();
/*     */   }
/*     */   
/*     */   public final boolean hasMessages() {
/* 359 */     return !isEmpty();
/*     */   }
/*     */   
/*     */   public final void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/*     */     while (true) {
/* 362 */       Envelope envelope = dequeue();
/* 363 */       if (envelope != null) {
/* 364 */         deadLetters.enqueue(owner, envelope);
/* 365 */         deadLetters = deadLetters;
/* 365 */         owner = owner;
/*     */         continue;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\NodeMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */