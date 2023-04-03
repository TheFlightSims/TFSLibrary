/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Proxy;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y4q!\001\002\021\002\007\005\021B\001\006Ti\006\0347\016\025:pqfT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\004\001-Y\002c\001\007\016\0375\t!!\003\002\017\005\t)1\013^1dWB\021\001#\005\007\001\t\025\021\002A1\001\024\005\005\t\025C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\003+qI!!\b\004\003\013A\023x\016_=\t\013}\001A\021\001\021\002\r\021Jg.\033;%)\005\t\003CA\013#\023\t\031cA\001\003V]&$\b\"B\023\001\r\0031\023\001B:fY\032,\022a\003\005\006Q\001!\t%K\001\006CB\004H.\037\013\003\037)BQaK\024A\0021\n\021A\034\t\003+5J!A\f\004\003\007%sG\017C\0031\001\021\005\023'\001\004mK:<G\017[\013\002Y!)1\007\001C!i\0059\021n]#naRLX#A\033\021\005U1\024BA\034\007\005\035\021un\0347fC:DQ!\017\001\005\002i\n\001\002\n9mkN$S-\035\013\003wqj\021\001\001\005\006{a\002\raD\001\005K2,W\016C\003@\001\021\005\003)A\004qkND\027\t\0347\025\005m\n\005\"\002\"?\001\004\031\025A\001=t!\r!UiD\007\002\t%\021a\t\002\002\020)J\fg/\032:tC\ndWm\0248dK\")\001\n\001C!\023\006!\001/^:i)\021Y$\n\024(\t\013-;\005\031A\b\002\013\025dW-\\\031\t\0135;\005\031A\b\002\013\025dW-\034\032\t\013=;\005\031\001)\002\013\025dW-\\:\021\007U\tv\"\003\002S\r\tQAH]3qK\006$X\r\032 \t\013!\003A\021\t+\025\005m*\006\"B\037T\001\004y\001\"B,\001\t\003B\026a\001;paV\tq\002C\003[\001\021\0053,A\002q_B$\022a\004\005\006;\002!\t\005I\001\006G2,\027M\035\005\006?\002!\t\005Y\001\tSR,'/\031;peV\t\021\rE\002EE>I!a\031\003\003\021%#XM]1u_JDQ!\032\001\005B\031\fa\001^8MSN$X#A4\021\007!\004xB\004\002j]:\021!.\\\007\002W*\021A\016C\001\007yI|w\016\036 \n\003\035I!a\034\004\002\017A\f7m[1hK&\021\021O\035\002\005\031&\034HO\003\002p\r!)A\017\001C!k\006)1\r\\8oKR\t1\002")
/*     */ public interface StackProxy<A> extends Proxy {
/*     */   Stack<A> self();
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   int length();
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   StackProxy<A> $plus$eq(A paramA);
/*     */   
/*     */   StackProxy<A> pushAll(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   StackProxy<A> push(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   StackProxy<A> push(A paramA);
/*     */   
/*     */   A top();
/*     */   
/*     */   A pop();
/*     */   
/*     */   void clear();
/*     */   
/*     */   Iterator<A> iterator();
/*     */   
/*     */   List<A> toList();
/*     */   
/*     */   Stack<A> clone();
/*     */   
/*     */   public class StackProxy$$anon$1 extends Stack<A> implements StackProxy<A> {
/*     */     public A apply(int n) {
/* 100 */       return (A)StackProxy$class.apply(this, n);
/*     */     }
/*     */     
/*     */     public int length() {
/* 100 */       return StackProxy$class.length(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 100 */       return StackProxy$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public StackProxy<A> $plus$eq(Object elem) {
/* 100 */       return StackProxy$class.$plus$eq(this, elem);
/*     */     }
/*     */     
/*     */     public StackProxy<A> pushAll(TraversableOnce xs) {
/* 100 */       return StackProxy$class.pushAll(this, xs);
/*     */     }
/*     */     
/*     */     public StackProxy<A> push(Object elem1, Object elem2, Seq elems) {
/* 100 */       return StackProxy$class.push(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public StackProxy<A> push(Object elem) {
/* 100 */       return StackProxy$class.push(this, elem);
/*     */     }
/*     */     
/*     */     public A top() {
/* 100 */       return (A)StackProxy$class.top(this);
/*     */     }
/*     */     
/*     */     public A pop() {
/* 100 */       return (A)StackProxy$class.pop(this);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 100 */       StackProxy$class.clear(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 100 */       return StackProxy$class.iterator(this);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 100 */       return StackProxy$class.toList(this);
/*     */     }
/*     */     
/*     */     public Stack<A> clone() {
/* 100 */       return StackProxy$class.clone(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 100 */       return Proxy.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 100 */       return Proxy.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 100 */       return Proxy.class.toString(this);
/*     */     }
/*     */     
/*     */     public StackProxy$$anon$1(StackProxy $outer) {
/* 100 */       Proxy.class.$init$(this);
/* 100 */       StackProxy$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Stack<A> self() {
/* 101 */       return this.$outer.self().clone();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\StackProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */