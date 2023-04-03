/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001a4A!\001\002\001\023\tI2+\0378dQJ|g.\033>fIB\023\030n\034:jif\fV/Z;f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2C\001\001\f!\raQbD\007\002\005%\021aB\001\002\016!JLwN]5usF+X-^3\021\005A\tB\002\001\003\006%\001\021\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\b\"\003\017\001\005\003\005\0131B\017*\003\ry'\017\032\t\004=\031zaBA\020%\035\t\0013%D\001\"\025\t\021\003\"\001\004=e>|GOP\005\002\017%\021QEB\001\ba\006\0347.Y4f\023\t9\003F\001\005Pe\022,'/\0338h\025\t)c!\003\002\035\033!)1\006\001C\001Y\0051A(\0338jiz\"\022!\f\013\003]=\0022\001\004\001\020\021\025a\"\006q\001\036\021\025\t\004\001\"\0213\003\035I7/R7qif,\022a\r\t\003+QJ!!\016\004\003\017\t{w\016\\3b]\")q\007\001C!q\005AA\005\0357vg\022*\027\017\006\002:u5\t\001\001C\003<m\001\007q\"\001\003fY\026l\007\"B\037\001\t\003r\024!\004\023qYV\034H\005\0357vg\022*\027\017\006\002:!)\001\t\020a\001\003\006\021\001p\035\t\004\005\016{Q\"\001\003\n\005\021#!a\004+sCZ,'o]1cY\026|enY3\t\013\031\003A\021I$\002\017\025t\027/^3vKR\021\001j\023\t\003+%K!A\023\004\003\tUs\027\016\036\005\006\031\026\003\r!T\001\006K2,Wn\035\t\004+9{\021BA(\007\005)a$/\0329fCR,GM\020\005\006#\002!\tEU\001\bI\026\fX/Z;f)\005y\001\"\002+\001\t\003*\026\001\0025fC\022,\022a\004\005\006/\002!\t%V\001\004[\006D\b\006\002,Z9z\003\"!\006.\n\005m3!A\0033faJ,7-\031;fI\006\nQ,A\nVg\026\004\003\r[3bI\002\004\023N\\:uK\006$g&I\001`\003\025\021d&\017\0301\021\025\t\007\001\"\021c\003\025\031G.Z1s)\005A\005\"\0023\001\t\003*\027\001C5uKJ\fGo\034:\026\003\031\0042AQ4\020\023\tAGA\001\005Ji\026\024\030\r^8s\021\025Q\007\001\"\021l\003\031)\027/^1mgR\0211\007\034\005\006[&\004\r\001G\001\005i\"\fG\017C\003p\001\021\005\003/\001\005u_N#(/\0338h)\005\t\bC\001:v\035\t)2/\003\002u\r\0051\001K]3eK\032L!A^<\003\rM#(/\0338h\025\t!h\001")
/*     */ public class SynchronizedPriorityQueue<A> extends PriorityQueue<A> {
/*     */   public SynchronizedPriorityQueue(Ordering<A> ord) {
/*  26 */     super(ord);
/*     */   }
/*     */   
/*     */   public synchronized boolean isEmpty() {
/*  32 */     return super.isEmpty();
/*     */   }
/*     */   
/*     */   public SynchronizedPriorityQueue<A> $plus$eq(Object elem) {
/*  39 */     synchronized (this) {
/*  40 */       super.$plus$eq((A)elem);
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/SynchronizedPriorityQueue}} */
/*  42 */       return this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SynchronizedPriorityQueue<A> $plus$plus$eq(TraversableOnce xs) {
/*  50 */     synchronized (this) {
/*  51 */       Growable.class.$plus$plus$eq(this, xs);
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/SynchronizedPriorityQueue}} */
/*  53 */       return this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enqueue(Seq elems) {
/*  60 */     synchronized (this) {
/*  60 */       Growable.class.$plus$plus$eq(this, (TraversableOnce)elems);
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/SynchronizedPriorityQueue}} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized A dequeue() {
/*  67 */     return super.dequeue();
/*     */   }
/*     */   
/*     */   public synchronized A head() {
/*  74 */     return super.head();
/*     */   }
/*     */   
/*     */   public synchronized A max() {
/*  82 */     return super.max();
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*  87 */     super.clear();
/*     */   }
/*     */   
/*     */   public synchronized Iterator<A> iterator() {
/*  94 */     return super.iterator();
/*     */   }
/*     */   
/*     */   public synchronized boolean equals(Object that) {
/* 100 */     return super.equals(that);
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 106 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedPriorityQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */