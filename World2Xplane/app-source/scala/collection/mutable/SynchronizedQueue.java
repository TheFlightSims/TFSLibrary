/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001%4A!\001\002\001\023\t\t2+\0378dQJ|g.\033>fIF+X-^3\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031\"\001A\006\021\0071iq\"D\001\003\023\tq!AA\003Rk\026,X\r\005\002\021#1\001A!\002\n\001\005\004\031\"!A!\022\005QA\002CA\013\027\033\0051\021BA\f\007\005\035qu\016\0365j]\036\004\"!F\r\n\005i1!aA!os\")A\004\001C\001;\0051A(\0338jiz\"\022A\b\t\004\031\001y\001\"\002\021\001\t\003\n\023aB5t\0136\004H/_\013\002EA\021QcI\005\003I\031\021qAQ8pY\026\fg\016C\003'\001\021\005s%\001\005%a2,8\017J3r)\tA\023&D\001\001\021\025QS\0051\001\020\003\021)G.Z7\t\0131\002A\021I\027\002\033\021\002H.^:%a2,8\017J3r)\tAc\006C\0030W\001\007\001'\001\002ygB\031\021GM\b\016\003\021I!a\r\003\003\037Q\023\030M^3sg\006\024G.Z(oG\026DQ!\016\001\005BY\nq!\0328rk\026,X\r\006\0028uA\021Q\003O\005\003s\031\021A!\0268ji\")1\b\016a\001y\005)Q\r\\3ngB\031Q#P\b\n\005y2!A\003\037sKB,\027\r^3e}!)\001\t\001C!\003\0069A-Z9vKV,G#A\b\t\013\r\003A\021\t#\002\031\021,\027/^3vK\032K'o\035;\025\005\025C\005cA\013G\037%\021qI\002\002\007\037B$\030n\0348\t\013%\023\005\031\001&\002\003A\004B!F&\020E%\021AJ\002\002\n\rVt7\r^5p]FBQA\024\001\005B=\013!\002Z3rk\026,X-\0217m)\t\0016\013E\002\r#>I!A\025\002\003\007M+\027\017C\003J\033\002\007!\nC\003V\001\021\005c+A\003ge>tG/F\001\020\021\025A\006\001\"\021Z\003\025\031G.Z1s)\0059\004\"B.\001\t\003b\026AB3rk\006d7\017\006\002#;\")aL\027a\0011\005!A\017[1u\021\025\001\007\001\"\021b\003!!xn\025;sS:<G#\0012\021\005\r4gBA\013e\023\t)g!\001\004Qe\026$WMZ\005\003O\"\024aa\025;sS:<'BA3\007\001")
/*     */ public class SynchronizedQueue<A> extends Queue<A> {
/*     */   public synchronized boolean isEmpty() {
/*  34 */     return super.isEmpty();
/*     */   }
/*     */   
/*     */   public synchronized SynchronizedQueue<A> $plus$eq(Object elem) {
/*  40 */     return (SynchronizedQueue<A>)super.$plus$eq((A)elem);
/*     */   }
/*     */   
/*     */   public synchronized SynchronizedQueue<A> $plus$plus$eq(TraversableOnce xs) {
/*  48 */     return (SynchronizedQueue<A>)Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public void enqueue(Seq elems) {
/*  54 */     synchronized (this) {
/*  54 */       Growable.class.$plus$plus$eq(this, (TraversableOnce)elems);
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/SynchronizedQueue}} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized A dequeue() {
/*  61 */     return super.dequeue();
/*     */   }
/*     */   
/*     */   public synchronized Option<A> dequeueFirst(Function1<A, Object> p) {
/*  69 */     return super.dequeueFirst(p);
/*     */   }
/*     */   
/*     */   public synchronized Seq<A> dequeueAll(Function1<A, Object> p) {
/*  78 */     return super.dequeueAll(p);
/*     */   }
/*     */   
/*     */   public synchronized A front() {
/*  85 */     return super.front();
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*  90 */     super.clear();
/*     */   }
/*     */   
/*     */   public synchronized boolean equals(Object that) {
/*  96 */     return GenSeqLike.class.equals((GenSeqLike)this, that);
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 102 */     return SeqLike.class.toString(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */