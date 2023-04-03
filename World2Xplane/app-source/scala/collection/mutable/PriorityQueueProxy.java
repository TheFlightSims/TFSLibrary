/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Proxy;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\ra!B\001\003\003\003I!A\005)sS>\024\030\016^=Rk\026,X\r\025:pqfT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\004\001-Y\002c\001\007\016\0375\t!!\003\002\017\005\ti\001K]5pe&$\0300U;fk\026\004\"\001E\t\r\001\021)!\003\001b\001'\t\t\021)\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\005Ua\022BA\017\007\005\025\001&o\034=z\021%y\002A!A!\002\027\001C&A\002pe\022\0042!I\025\020\035\t\021sE\004\002$M5\tAE\003\002&\021\0051AH]8pizJ\021aB\005\003Q\031\tq\001]1dW\006<W-\003\002+W\tAqJ\0353fe&twM\003\002)\r%\021q$\004\005\006]\001!\taL\001\007y%t\027\016\036 \025\003A\"\"!\r\032\021\0071\001q\002C\003 [\001\017\001\005C\0035\001\031\005Q'\001\003tK24W#A\006\t\013]\002A\021\t\035\002\021%$XM]1u_J,\022!\017\t\004umzQ\"\001\003\n\005q\"!\001C%uKJ\fGo\034:\t\013y\002A\021I \002\r1,gn\032;i+\005\001\005CA\013B\023\t\021eAA\002J]RDQ\001\022\001\005B\025\013q![:F[B$\0300F\001G!\t)r)\003\002I\r\t9!i\\8mK\006t\007\"\002&\001\t\003Z\025\001\003\023qYV\034H%Z9\025\0051kU\"\001\001\t\0139K\005\031A\b\002\t\025dW-\034\005\006!\002!\t%U\001\016IAdWo\035\023qYV\034H%Z9\025\0051\023\006\"B*P\001\004!\026AA5u!\rQTkD\005\003-\022\021q\002\026:bm\026\0248/\0312mK>s7-\032\005\0061\002!\t%W\001\bK:\fX/Z;f)\tQV\f\005\002\0267&\021AL\002\002\005+:LG\017C\003_/\002\007q,A\003fY\026l7\017E\002\026A>I!!\031\004\003\025q\022X\r]3bi\026$g\bC\003d\001\021\005C-A\004eKF,X-^3\025\003=AQA\032\001\005B\035\fA\001[3bIV\tq\002C\003j\001\021\005s-A\002nCbDC\001[6oaB\021Q\003\\\005\003[\032\021!\002Z3qe\026\034\027\r^3eC\005y\027aE+tK\002\002\007.Z1eA\002Jgn\035;fC\022t\023%A9\002\013Ir\023H\f\031\t\013M\004A\021\t;\002\013\rdW-\031:\025\003iCQA\036\001\005B]\fq\001^8Rk\026,X-F\001y!\ra\021pD\005\003u\n\021Q!U;fk\026DQ\001 \001\005Bu\fQa\0317p]\026$\022a\003\005\013\002\t\t\021!C\005\003\003a\023!C:va\026\024He\034:e+\005\001\003")
/*     */ public abstract class PriorityQueueProxy<A> extends PriorityQueue<A> implements Proxy {
/*     */   public int hashCode() {
/*  21 */     return Proxy.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  21 */     return Proxy.class.equals(this, that);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  21 */     return Proxy.class.toString(this);
/*     */   }
/*     */   
/*     */   public PriorityQueueProxy(Ordering<A> ord) {
/*  21 */     super(ord);
/*  21 */     Proxy.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  31 */     return self().iterator();
/*     */   }
/*     */   
/*     */   public int length() {
/*  35 */     return self().length();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  41 */     return self().isEmpty();
/*     */   }
/*     */   
/*     */   public PriorityQueueProxy<A> $plus$eq(Object elem) {
/*  47 */     self().$plus$eq((A)elem);
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public PriorityQueueProxy<A> $plus$plus$eq(TraversableOnce<A> it) {
/*  54 */     self().$plus$plus$eq(it);
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public void enqueue(Seq elems) {
/*  62 */     self().$plus$plus$eq((TraversableOnce<A>)elems);
/*     */   }
/*     */   
/*     */   public A dequeue() {
/*  69 */     return self().dequeue();
/*     */   }
/*     */   
/*     */   public A head() {
/*  76 */     return self().head();
/*     */   }
/*     */   
/*     */   public A max() {
/*  84 */     return self().max();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  89 */     self().clear();
/*     */   }
/*     */   
/*     */   public Queue<A> toQueue() {
/*  93 */     return self().toQueue();
/*     */   }
/*     */   
/*     */   public Ordering<A> scala$collection$mutable$PriorityQueueProxy$$super$ord() {
/*  99 */     return ord();
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> clone() {
/*  99 */     return new PriorityQueueProxy$$anon$1(this);
/*     */   }
/*     */   
/*     */   public abstract PriorityQueue<A> self();
/*     */   
/*     */   public class PriorityQueueProxy$$anon$1 extends PriorityQueueProxy<A> {
/*     */     public PriorityQueueProxy$$anon$1(PriorityQueueProxy<A> $outer) {
/*  99 */       super($outer.scala$collection$mutable$PriorityQueueProxy$$super$ord());
/*     */     }
/*     */     
/*     */     public PriorityQueue<A> self() {
/* 100 */       return this.$outer.self().clone();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\PriorityQueueProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */