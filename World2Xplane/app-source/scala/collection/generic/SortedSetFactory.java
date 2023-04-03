/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SortedSet;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.SetBuilder;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ma!B\001\003\003\003I!\001E*peR,GmU3u\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)12C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\006!\001!\t!E\001\007y%t\027\016\036 \025\003I\0012a\005\001\025\033\005\021\001CA\013\027\031\001!Qa\006\001C\002a\021!aQ\"\026\005e)\023C\001\016\036!\ta1$\003\002\035\r\t9aj\034;iS:<'c\001\020!W\031!q\004\001\001\036\0051a$/\0324j]\026lWM\034;?!\r\t#\005J\007\002\t%\0211\005\002\002\n'>\024H/\0323TKR\004\"!F\023\005\013\0312\"\031A\024\003\003\005\013\"A\007\025\021\0051I\023B\001\026\007\005\r\te.\037\t\005C1\"c&\003\002.\t\ti1k\034:uK\022\034V\r\036'jW\026\0042!\006\f%\013\021\001\004\001A\031\003\t\r{G\016\034\031\003eQ\0022!\006\f4!\t)B\007B\0056_\005\005\t\021!B\001O\t\031q\fJ\031\t\013]\002a\021\001\035\002\013\025l\007\017^=\026\005ebDC\001\036>!\r)bc\017\t\003+q\"QA\n\034C\002\035BQA\020\034A\004}\n1a\034:e!\r\001\005j\017\b\003\003\032s!AQ#\016\003\rS!\001\022\005\002\rq\022xn\034;?\023\0059\021BA$\007\003\035\001\030mY6bO\026L!!\023&\003\021=\023H-\032:j]\036T!a\022\004\t\0131\003A\021A'\002\013\005\004\b\017\\=\026\0059\023FCA(V)\t\0016\013E\002\026-E\003\"!\006*\005\013\031Z%\031A\024\t\013yZ\0059\001+\021\007\001C\025\013C\003W\027\002\007q+A\003fY\026l7\017E\002\r1FK!!\027\004\003\025q\022X\r]3bi\026$g\bC\003\\\001\021\005A,\001\006oK^\024U/\0337eKJ,\"!X3\025\005y;\007\003B0cI\032l\021\001\031\006\003C\022\tq!\\;uC\ndW-\003\002dA\n9!)^5mI\026\024\bCA\013f\t\0251#L1\001(!\r)b\003\032\005\006}i\003\035\001\033\t\004\001\"#\007\"\0026\001\t\007Y\027a\0048fo\016\013gNQ;jY\0224%o\\7\026\0051\034HCA7v!\025\031b\016\035:u\023\ty'A\001\007DC:\024U/\0337e\rJ|W\016\005\002r_5\t\001\001\005\002\026g\022)a%\033b\001OA\031QC\006:\t\013yJ\0079\001<\021\007\001C%O\002\003y\001\001I(!F*peR,GmU3u\007\006t')^5mI\032\023x.\\\013\003uv\0342a^\006|!\025\031b\016\035?!\t)R\020B\003'o\n\007q\005E\002\026-qD\021BP<\003\002\003\006Y!!\001\021\007\001CE\020\003\004\021o\022\005\021Q\001\013\003\003\017!B!!\003\002\fA\031\021o\036?\t\017y\n\031\001q\001\002\002!1Aj\036C\001\003\037!B!!\005\002\024A!qL\031?\021\035\t)\"!\004A\002A\fAA\032:p[\"1Aj\036C\001\0033!\"!!\005")
/*    */ public abstract class SortedSetFactory<CC extends SortedSet<Object>> {
/*    */   public abstract <A> CC empty(Ordering<A> paramOrdering);
/*    */   
/*    */   public <A> CC apply(Seq elems, Ordering<A> ord) {
/* 26 */     return (CC)((Builder)newBuilder(ord).$plus$plus$eq((TraversableOnce)elems)).result();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, CC> newBuilder(Ordering<?> ord) {
/* 28 */     return (Builder<A, CC>)new SetBuilder(empty(ord));
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<CC, A, CC> newCanBuildFrom(Ordering<?> ord) {
/* 30 */     return new SortedSetCanBuildFrom(this, ord);
/*    */   }
/*    */   
/*    */   public class SortedSetCanBuildFrom<A> implements CanBuildFrom<CC, A, CC> {
/*    */     private final Ordering<A> ord;
/*    */     
/*    */     public SortedSetCanBuildFrom(SortedSetFactory $outer, Ordering<A> ord) {}
/*    */     
/*    */     public Builder<A, CC> apply(SortedSet from) {
/* 33 */       return scala$collection$generic$SortedSetFactory$SortedSetCanBuildFrom$$$outer().newBuilder(this.ord);
/*    */     }
/*    */     
/*    */     public Builder<A, CC> apply() {
/* 34 */       return scala$collection$generic$SortedSetFactory$SortedSetCanBuildFrom$$$outer().newBuilder(this.ord);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SortedSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */