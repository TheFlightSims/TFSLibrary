/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)4A!\001\002\001\023\t\t2+\0378dQJ|g.\033>fIN#\030mY6\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031\"\001A\006\021\0071iq\"D\001\003\023\tq!AA\003Ti\006\0347\016\005\002\021#1\001A!\002\n\001\005\004\031\"!A!\022\005QA\002CA\013\027\033\0051\021BA\f\007\005\035qu\016\0365j]\036\004\"!F\r\n\005i1!aA!os\")A\004\001C\001;\0051A(\0338jiz\"\022A\b\t\004\031\001y\001\"\002\021\001\t\003\n\023aB5t\0136\004H/_\013\002EA\021QcI\005\003I\031\021qAQ8pY\026\fg\016C\003'\001\021\005s%\001\003qkNDGC\001\025*\033\005\001\001\"\002\026&\001\004y\021\001B3mK6DQA\n\001\005B1\"B\001K\0270c!)af\013a\001\037\005)Q\r\\3nc!)\001g\013a\001\037\005)Q\r\\3ne!)!g\013a\001g\005)Q\r\\3ngB\031Q\003N\b\n\005U2!A\003\037sKB,\027\r^3e}!)q\007\001C!q\0059\001/^:i\0032dGC\001\025:\021\025Qd\0071\001<\003\tA8\017E\002={=i\021\001B\005\003}\021\021q\002\026:bm\026\0248/\0312mK>s7-\032\005\006\001\002!\t%Q\001\004i>\004X#A\b\t\013\r\003A\021\t#\002\007A|\007\017F\001\020\021\0251\005\001\"\021H\003\025\031G.Z1s)\005A\005CA\013J\023\tQeA\001\003V]&$\b\"\002'\001\t\003j\025\001C5uKJ\fGo\034:\026\0039\0032\001P(\020\023\t\001FA\001\005Ji\026\024\030\r^8s\021\025\021\006\001\"\021T\003\031!x\016T5tiV\tA\013E\002V;>q!AV.\017\005]SV\"\001-\013\005eC\021A\002\037s_>$h(C\001\b\023\taf!A\004qC\016\\\027mZ3\n\005y{&\001\002'jgRT!\001\030\004\t\013\005\004A\021\t2\002\021Q|7\013\036:j]\036$\022a\031\t\003I\036t!!F3\n\005\0314\021A\002)sK\022,g-\003\002iS\n11\013\036:j]\036T!A\032\004")
/*    */ public class SynchronizedStack<A> extends Stack<A> {
/*    */   public synchronized boolean isEmpty() {
/* 34 */     return super.isEmpty();
/*    */   }
/*    */   
/*    */   public synchronized SynchronizedStack<A> push(Object elem) {
/* 40 */     return (SynchronizedStack<A>)super.push((A)elem);
/*    */   }
/*    */   
/*    */   public synchronized SynchronizedStack<A> push(Object elem1, Object elem2, Seq<A> elems) {
/* 50 */     return (SynchronizedStack<A>)super.push((A)elem1, (A)elem2, elems);
/*    */   }
/*    */   
/*    */   public synchronized SynchronizedStack<A> pushAll(TraversableOnce xs) {
/* 58 */     return (SynchronizedStack<A>)super.pushAll((TraversableOnce<A>)elems());
/*    */   }
/*    */   
/*    */   public synchronized A top() {
/* 66 */     return super.top();
/*    */   }
/*    */   
/*    */   public synchronized A pop() {
/* 70 */     return super.pop();
/*    */   }
/*    */   
/*    */   public synchronized void clear() {
/* 76 */     super.clear();
/*    */   }
/*    */   
/*    */   public synchronized Iterator<A> iterator() {
/* 86 */     return super.iterator();
/*    */   }
/*    */   
/*    */   public synchronized List<A> toList() {
/* 92 */     return super.toList();
/*    */   }
/*    */   
/*    */   public synchronized String toString() {
/* 98 */     return SeqLike.class.toString(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */