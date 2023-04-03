/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U2q!\001\002\021\002\007\005qA\001\bQCJ\fG\016\\3mSj\f'\r\\3\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\007!abe\005\002\001\023A\021!bC\007\002\t%\021A\002\002\002\004\003:L\b\"\002\b\001\t\003y\021A\002\023j]&$H\005F\001\021!\tQ\021#\003\002\023\t\t!QK\\5u\021\025!\002A\"\001\026\003\r\031X-]\013\002-A\031q\003\007\016\016\003\tI!!\007\002\003\037Q\023\030M^3sg\006\024G.Z(oG\026\004\"a\007\017\r\001\0211Q\004\001CC\002y\021\021!Q\t\003?%\001\"A\003\021\n\005\005\"!a\002(pi\"Lgn\032\005\006G\001!\t\001J\001\004a\006\024X#A\023\021\005m1CAB\024\001\t\013\007\001FA\004QCJ\024V\r\035:\022\005}I\003CA\f+\023\tY#A\001\005QCJ\fG\016\\3m\021\031i\003\001)D\t]\005Y\001/\031:D_6\024\027N\\3s+\005y\003\003\002\03145\025j\021!\r\006\003e\t\t\001\002]1sC2dW\r\\\005\003iE\022\001bQ8nE&tWM\035")
/*    */ public interface Parallelizable<A, ParRepr extends Parallel> {
/*    */   TraversableOnce<A> seq();
/*    */   
/*    */   ParRepr par();
/*    */   
/*    */   Combiner<A, ParRepr> parCombiner();
/*    */   
/*    */   public class Parallelizable$$anonfun$par$1 extends AbstractFunction1<A, Combiner<A, ParRepr>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Combiner cb$1;
/*    */     
/*    */     public final Combiner<A, ParRepr> apply(Object x) {
/* 41 */       return (Combiner<A, ParRepr>)this.cb$1.$plus$eq(x);
/*    */     }
/*    */     
/*    */     public Parallelizable$$anonfun$par$1(Parallelizable $outer, Combiner cb$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Parallelizable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */