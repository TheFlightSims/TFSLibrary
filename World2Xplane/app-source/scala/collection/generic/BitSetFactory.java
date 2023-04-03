/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.BitSet;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0353q!\001\002\021\002\007\005\021BA\007CSR\034V\r\036$bGR|'/\037\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\0135M\021\001a\003\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\"\002\t\001\t\003\t\022A\002\023j]&$H\005F\001\023!\ta1#\003\002\025\r\t!QK\\5u\021\0251\002A\"\001\030\003\025)W\016\035;z+\005A\002CA\r\033\031\001!Qa\007\001C\002q\021AaQ8mYF\021Q\004\t\t\003\031yI!a\b\004\003\0179{G\017[5oOJ\031\021eI\024\007\t\t\002\001\001\t\002\ryI,g-\0338f[\026tGO\020\t\003I\025j\021\001B\005\003M\021\021aAQ5u'\026$\bc\001\023)1%\021\021\006\002\002\013\005&$8+\032;MS.,\007\"B\026\001\r\003a\023A\0038fo\n+\030\016\0343feV\tQ\006\005\003/cMBR\"A\030\013\005A\"\021aB7vi\006\024G.Z\005\003e=\022qAQ;jY\022,'\017\005\002\ri%\021QG\002\002\004\023:$\b\"B\034\001\t\003A\024!B1qa2LHC\001\r:\021\025Qd\0071\001<\003\025)G.Z7t!\raAhM\005\003{\031\021!\002\020:fa\026\fG/\0323?\021\025y\004\001\"\001A\003I\021\027\016^:fi\016\013gNQ;jY\0224%o\\7\026\003\005\0232AQ\006D\r\021\021c\bA!\021\013\021+\005d\r\r\016\003\tI!A\022\002\003\031\r\013gNQ;jY\0224%o\\7")
/*    */ public interface BitSetFactory<Coll extends BitSet & scala.collection.BitSetLike<Coll>> {
/*    */   Coll empty();
/*    */   
/*    */   Builder<Object, Coll> newBuilder();
/*    */   
/*    */   Coll apply(Seq<Object> paramSeq);
/*    */   
/*    */   Object bitsetCanBuildFrom();
/*    */   
/*    */   public class BitSetFactory$$anonfun$apply$1 extends AbstractFunction2<Coll, Object, Coll> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Coll apply(BitSet x$2, int x$3) {
/* 32 */       return (Coll)x$2.$plus(BoxesRunTime.boxToInteger(x$3));
/*    */     }
/*    */     
/*    */     public BitSetFactory$$anonfun$apply$1(BitSetFactory $outer) {}
/*    */   }
/*    */   
/*    */   public class BitSetFactory$$anon$1 implements CanBuildFrom<Coll, Object, Coll> {
/*    */     public BitSetFactory$$anon$1(BitSetFactory $outer) {}
/*    */     
/*    */     public Builder<Object, Coll> apply(BitSet from) {
/* 34 */       return this.$outer.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<Object, Coll> apply() {
/* 35 */       return this.$outer.newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\BitSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */