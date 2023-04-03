/*    */ package scala.testing;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.compat.Platform$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M3q!\001\002\021\002\007\005qAA\005CK:\034\007.\\1sW*\0211\001B\001\bi\026\034H/\0338h\025\005)\021!B:dC2\f7\001A\n\003\001!\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PU3g\021\025i\001\001\"\001\017\003\031!\023N\\5uIQ\tq\002\005\002\n!%\021\021\003\002\002\005+:LG\017C\003\024\001\031\005a\"A\002sk:Dq!\006\001A\002\023\005a#\001\006nk2$\030\016\0357jKJ,\022a\006\t\003\023aI!!\007\003\003\007%sG\017C\004\034\001\001\007I\021\001\017\002\0355,H\016^5qY&,'o\030\023fcR\021q\"\b\005\b=i\t\t\0211\001\030\003\rAH%\r\005\007A\001\001\013\025B\f\002\0275,H\016^5qY&,'\017\t\005\006E\001!\taI\001\reVt')\0328dQ6\f'o\033\013\003IM\0022!J\0271\035\t13F\004\002(U5\t\001F\003\002*\r\0051AH]8pizJ\021!B\005\003Y\021\tq\001]1dW\006<W-\003\002/_\t!A*[:u\025\taC\001\005\002\nc%\021!\007\002\002\005\031>tw\rC\0035C\001\007q#A\004o_RKW.Z:\t\013Y\002A\021\001\b\002\013M,G/\0269\t\013a\002A\021\001\b\002\021Q,\027M\035#po:DQA\017\001\005\002m\na\001\035:fM&DX#\001\037\021\005u\002eBA\005?\023\tyD!\001\004Qe\026$WMZ\005\003\003\n\023aa\025;sS:<'BA \005\021\025!\005\001\"\001F\003\021i\027-\0338\025\005=1\005\"B$D\001\004A\025\001B1sON\0042!C%=\023\tQEAA\003BeJ\f\027\020\013\003\001\031>\013\006CA\005N\023\tqEA\001\006eKB\024XmY1uK\022\f\023\001U\001\034)\"L7\017I2mCN\034\be^5mY\002\022W\r\t:f[>4X\r\032\030\"\003I\013aA\r\0302a9\002\004")
/*    */ public interface Benchmark {
/*    */   void run();
/*    */   
/*    */   int multiplier();
/*    */   
/*    */   @TraitSetter
/*    */   void multiplier_$eq(int paramInt);
/*    */   
/*    */   List<Object> runBenchmark(int paramInt);
/*    */   
/*    */   void setUp();
/*    */   
/*    */   void tearDown();
/*    */   
/*    */   String prefix();
/*    */   
/*    */   void main(String[] paramArrayOfString);
/*    */   
/*    */   public class Benchmark$$anonfun$runBenchmark$1 extends AbstractFunction1.mcJI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final long apply(int i) {
/* 55 */       return apply$mcJI$sp(i);
/*    */     }
/*    */     
/*    */     public Benchmark$$anonfun$runBenchmark$1(Benchmark $outer) {}
/*    */     
/*    */     public long apply$mcJI$sp(int i) {
/* 56 */       this.$outer.setUp();
/* 57 */       Platform$ platform$1 = Platform$.MODULE$;
/* 57 */       long startTime = System.currentTimeMillis();
/* 58 */       for (int j = 0; j < this.$outer.multiplier(); ) {
/* 59 */         this.$outer.run();
/* 60 */         j++;
/*    */       } 
/* 62 */       Platform$ platform$2 = Platform$.MODULE$;
/* 62 */       long stopTime = System.currentTimeMillis();
/* 63 */       this.$outer.tearDown();
/* 64 */       Platform$ platform$3 = Platform$.MODULE$;
/* 64 */       System.gc();
/* 66 */       return stopTime - startTime;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\testing\Benchmark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */