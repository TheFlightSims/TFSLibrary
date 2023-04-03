/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SortedMap;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.MapBuilder;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055b!B\001\003\003\003I!\001E*peR,G-T1q\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)12C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\006!\001!\t!E\001\007y%t\027\016\036 \025\003I\0012a\005\001\025\033\005\021\001CA\013\027\031\001!Qa\006\001C\002a\021!aQ\"\026\007e)C&\005\002\033;A\021AbG\005\0039\031\021qAT8uQ&twME\002\037A92Aa\b\001\001;\taAH]3gS:,W.\0328u}A!\021E\t\023,\033\005!\021BA\022\005\005%\031vN\035;fI6\013\007\017\005\002\026K\021)aE\006b\001O\t\t\021)\005\002\033QA\021A\"K\005\003U\031\0211!\0218z!\t)B\006B\003.-\t\007qEA\001C!\025\ts\006J\0262\023\t\001DAA\007T_J$X\rZ'ba2K7.\032\t\005+Y!3&\002\0034\001\001!$\001B\"pY2\0044!N\034;!\021)bCN\035\021\005U9D!\003\0353\003\003\005\tQ!\001(\005\ryF%\r\t\003+i\"\021b\017\032\002\002\003\005)\021A\024\003\007}##\007C\003>\001\031\005a(A\003f[B$\0300F\002@\005\022#\"\001Q#\021\tU1\022i\021\t\003+\t#QA\n\037C\002\035\002\"!\006#\005\0135b$\031A\024\t\013\031c\0049A$\002\007=\024H\rE\002I!\006s!!\023(\017\005)kU\"A&\013\0051C\021A\002\037s_>$h(C\001\b\023\tye!A\004qC\016\\\027mZ3\n\005E\023&\001C(sI\026\024\030N\\4\013\005=3\001\"\002+\001\t\003)\026!B1qa2LXc\001,[9R\021qk\030\013\0031v\003B!\006\fZ7B\021QC\027\003\006MM\023\ra\n\t\003+q#Q!L*C\002\035BQAR*A\004y\0032\001\023)Z\021\025\0017\0131\001b\003\025)G.Z7t!\ra!\rZ\005\003G\032\021!\002\020:fa\026\fG/\0323?!\021aQ-W.\n\005\0314!A\002+va2,'\007C\003i\001\021\005\021.\001\006oK^\024U/\0337eKJ,2A[:v)\tYw\017\005\003m_F4X\"A7\013\0059$\021aB7vi\006\024G.Z\005\003a6\024qAQ;jY\022,'\017\005\003\rKJ$\bCA\013t\t\0251sM1\001(!\t)R\017B\003.O\n\007q\005\005\003\026-I$\b\"\002$h\001\bA\bc\001%Qe\032!!\020\001\001|\005U\031vN\035;fI6\013\007oQ1o\005VLG\016\032$s_6,R\001`A\005\003\033\0312!_\006~!!\031b0!\001\002\006\005=\021BA@\003\0051\031\025M\034\"vS2$gI]8n!\r\t\031AM\007\002\001A1A\"ZA\004\003\027\0012!FA\005\t\0251\023P1\001(!\r)\022Q\002\003\006[e\024\ra\n\t\007+Y\t9!a\003\t\023\031K(\021!Q\001\f\005M\001\003\002%Q\003\017Aa\001E=\005\002\005]ACAA\r)\021\tY\"!\b\021\017\005\r\0210a\002\002\f!9a)!\006A\004\005M\001B\002+z\t\003\t\t\003\006\003\002$\005\025\002C\0027p\003\013\ty\001\003\005\002(\005}\001\031AA\001\003\0211'o\\7\t\rQKH\021AA\026)\t\t\031\003")
/*    */ public abstract class SortedMapFactory<CC extends SortedMap<Object, Object>> {
/*    */   public abstract <A, B> CC empty(Ordering<A> paramOrdering);
/*    */   
/*    */   public <A, B> CC apply(Seq elems, Ordering<A> ord) {
/* 27 */     return (CC)((Builder)newBuilder(ord).$plus$plus$eq((TraversableOnce)elems)).result();
/*    */   }
/*    */   
/*    */   public <A, B> Builder<Tuple2<A, B>, CC> newBuilder(Ordering<?> ord) {
/* 30 */     return (Builder<Tuple2<A, B>, CC>)new MapBuilder(empty(ord));
/*    */   }
/*    */   
/*    */   public class SortedMapCanBuildFrom<A, B> implements CanBuildFrom<CC, Tuple2<A, B>, CC> {
/*    */     private final Ordering<A> ord;
/*    */     
/*    */     public SortedMapCanBuildFrom(SortedMapFactory $outer, Ordering<A> ord) {}
/*    */     
/*    */     public Builder<Tuple2<A, B>, CC> apply(SortedMap from) {
/* 33 */       return scala$collection$generic$SortedMapFactory$SortedMapCanBuildFrom$$$outer().newBuilder(this.ord);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, CC> apply() {
/* 34 */       return scala$collection$generic$SortedMapFactory$SortedMapCanBuildFrom$$$outer().newBuilder(this.ord);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SortedMapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */