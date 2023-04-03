/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function$;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.AbstractTraversable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00153q!\001\002\021\002G\005qA\001\n[SB\004X\r\032+sCZ,'o]1cY\026\024$BA\002\005\003\035\021XO\034;j[\026T\021!B\001\006g\016\fG.Y\002\001+\rA1DI\n\003\001%\001\"AC\006\016\003\021I!\001\004\003\003\007\005s\027\020C\003\017\001\031\005q\"A\004g_J,\027m\0315\026\005A)CCA\t\025!\tQ!#\003\002\024\t\t!QK\\5u\021\025)R\0021\001\027\003\0051\007#\002\006\0303\005\"\023B\001\r\005\005%1UO\\2uS>t'\007\005\002\03371\001AA\002\017\001\t\013\007QDA\002FYF\n\"AH\005\021\005)y\022B\001\021\005\005\035qu\016\0365j]\036\004\"A\007\022\005\r\r\002AQ1\001\036\005\r)EN\r\t\0035\025\"QAJ\007C\002u\021\021!V\004\006Q\tA\t!K\001\0235&\004\b/\0323Ue\0064XM]:bE2,'\007\005\002+W5\t!AB\003\002\005!\005Af\005\002,[A\021!BL\005\003_\021\021a!\0218z%\0264\007\"B\031,\t\003\021\024A\002\037j]&$h\bF\001*\021\025!4\006b\0016\003}Q\030\016\0359fIR\023\030M^3sg\006\024G.\032\032U_R\023\030M^3sg\006\024G.Z\013\004m\035KECA\034K!\rA\004i\021\b\003syr!AO\037\016\003mR!\001\020\004\002\rq\022xn\034;?\023\005)\021BA \005\003\035\001\030mY6bO\026L!!\021\"\003\027Q\023\030M^3sg\006\024G.\032\006\003\021\001BA\003#G\021&\021Q\t\002\002\007)V\004H.\032\032\021\005i9E!\002\0174\005\004i\002C\001\016J\t\025\0313G1\001\036\021\025Y5\0071\001M\003\tQ(\020\005\003+\001\031C\005")
/*    */ public interface ZippedTraversable2<El1, El2> {
/*    */   <U> void foreach(Function2<El1, El2, U> paramFunction2);
/*    */   
/*    */   public static class ZippedTraversable2$$anon$1 extends AbstractTraversable<Tuple2<El1, El2>> {
/*    */     private final ZippedTraversable2 zz$1;
/*    */     
/*    */     public ZippedTraversable2$$anon$1(ZippedTraversable2 zz$1) {}
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 25 */       Function$ function$ = Function$.MODULE$;
/* 25 */       this.zz$1.foreach((Function2)new Object(f));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ZippedTraversable2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */