/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0014q!\001\002\021\002\007\005\021B\001\006Rk\026,X\r\025:pqfT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\004\001-Y\002c\001\007\016\0375\t!!\003\002\017\005\t)\021+^3vKB\021\001#\005\007\001\t\025\021\002A1\001\024\005\005\t\025C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\003+qI!!\b\004\003\013A\023x\016_=\t\013}\001A\021\001\021\002\r\021Jg.\033;%)\005\t\003CA\013#\023\t\031cA\001\003V]&$\b\"B\023\001\r\0031\023\001B:fY\032,\022a\003\005\006Q\001!\t%K\001\006CB\004H.\037\013\003\037)BQaK\024A\0021\n\021A\034\t\003+5J!A\f\004\003\007%sG\017C\0031\001\021\005\023'\001\004mK:<G\017[\013\002Y!)1\007\001C!i\0059\021n]#naRLX#A\033\021\005U1\024BA\034\007\005\035\021un\0347fC:DQ!\017\001\005Bi\n\001\002\n9mkN$S-\035\013\003wqj\021\001\001\005\006{a\002\raD\001\005K2,W\016C\003@\001\021\005\003)A\007%a2,8\017\n9mkN$S-\035\013\003w\005CQA\021 A\002\r\013!!\033;\021\007\021+u\"D\001\005\023\t1EAA\bUe\0064XM]:bE2,wJ\\2f\021\025A\005\001\"\021J\003\035)g.];fk\026$\"!\t&\t\013-;\005\031\001'\002\013\025dW-\\:\021\007Uiu\"\003\002O\r\tQAH]3qK\006$X\r\032 \t\013A\003A\021I)\002\017\021,\027/^3vKR\tq\002C\003T\001\021\005C+A\003ge>tG/F\001\020\021\0251\006\001\"\021!\003\025\031G.Z1s\021\025A\006\001\"\021Z\003!IG/\032:bi>\024X#\001.\021\007\021[v\"\003\002]\t\tA\021\n^3sCR|'\017C\003_\001\021\005s,A\003dY>tW\rF\001\f\001")
/*    */ public interface QueueProxy<A> extends Proxy {
/*    */   Queue<A> self();
/*    */   
/*    */   A apply(int paramInt);
/*    */   
/*    */   int length();
/*    */   
/*    */   boolean isEmpty();
/*    */   
/*    */   QueueProxy<A> $plus$eq(A paramA);
/*    */   
/*    */   QueueProxy<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
/*    */   
/*    */   void enqueue(Seq<A> paramSeq);
/*    */   
/*    */   A dequeue();
/*    */   
/*    */   A front();
/*    */   
/*    */   void clear();
/*    */   
/*    */   Iterator<A> iterator();
/*    */   
/*    */   Queue<A> clone();
/*    */   
/*    */   public class QueueProxy$$anon$1 extends Queue<A> implements QueueProxy<A> {
/*    */     public A apply(int n) {
/* 94 */       return (A)QueueProxy$class.apply(this, n);
/*    */     }
/*    */     
/*    */     public int length() {
/* 94 */       return QueueProxy$class.length(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 94 */       return QueueProxy$class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public QueueProxy<A> $plus$eq(Object elem) {
/* 94 */       return QueueProxy$class.$plus$eq(this, elem);
/*    */     }
/*    */     
/*    */     public QueueProxy<A> $plus$plus$eq(TraversableOnce it) {
/* 94 */       return QueueProxy$class.$plus$plus$eq(this, it);
/*    */     }
/*    */     
/*    */     public void enqueue(Seq elems) {
/* 94 */       QueueProxy$class.enqueue(this, elems);
/*    */     }
/*    */     
/*    */     public A dequeue() {
/* 94 */       return (A)QueueProxy$class.dequeue(this);
/*    */     }
/*    */     
/*    */     public A front() {
/* 94 */       return (A)QueueProxy$class.front(this);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 94 */       QueueProxy$class.clear(this);
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 94 */       return QueueProxy$class.iterator(this);
/*    */     }
/*    */     
/*    */     public Queue<A> clone() {
/* 94 */       return QueueProxy$class.clone(this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 94 */       return Proxy.class.hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 94 */       return Proxy.class.equals(this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 94 */       return Proxy.class.toString(this);
/*    */     }
/*    */     
/*    */     public QueueProxy$$anon$1(QueueProxy $outer) {
/* 94 */       Proxy.class.$init$(this);
/* 94 */       QueueProxy$class.$init$(this);
/*    */     }
/*    */     
/*    */     public Queue<A> self() {
/* 95 */       return this.$outer.self().clone();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\QueueProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */