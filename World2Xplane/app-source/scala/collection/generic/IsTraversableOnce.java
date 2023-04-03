/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m3q!\001\002\021\002G\005\021BA\tJgR\023\030M^3sg\006\024G.Z(oG\026T!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\002I\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\t\025\001\002A!\001\022\005\005\t\025C\001\n\026!\ta1#\003\002\025\r\t9aj\034;iS:<\007C\001\007\027\023\t9bAA\002B]fDq!\007\001C\002\033\005!$\001\006d_:4XM]:j_:,\022a\007\t\005\031qq\"%\003\002\036\r\tIa)\0368di&|g.\r\t\003?\001b\001\001B\003\"\001\t\007\021C\001\003SKB\024\bcA\022%M5\tA!\003\002&\t\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\t9s\"D\001\001\017\025I#\001#\001+\003EI5\017\026:bm\026\0248/\0312mK>s7-\032\t\003W1j\021A\001\004\006\003\tA\t!L\n\003Y-AQa\f\027\005\002A\na\001P5oSRtD#\001\026\t\017Ib#\031!C\002g\005Q1\017\036:j]\036\024V\r\035:\026\003Q\022\"!N\034\007\tYb\003\001\016\002\ryI,g-\0338f[\026tGO\020\t\004W\001A\004CA\035=\035\ta!(\003\002<\r\0051\001K]3eK\032L!!\020 \003\rM#(/\0338h\025\tYd!\002\003\021k\001\002\005C\001\007B\023\t\021eA\001\003DQ\006\024\bB\002#-A\003%A'A\006tiJLgn\032*faJ\004\003\"\002$-\t\0079\025AF4f]R\023\030M^3sg\006\024G.\032'jW\026\024V\r\035:\026\007!kE\013\006\002J/J\021!j\023\004\005m1\002\021\nE\002,\0011\0032aH'T\t\025qUI1\001P\005\005\031UCA\tQ\t\025\t&K1\001\022\005\005yF!\002(F\005\004y\005CA\020U\t\025)VI1\001\022\005\t\t\005'\002\003\021\025\002\031\006\"\002-F\001\bI\026\001B2p]Z\004B\001\004\017M5B\0311\005J*")
/*    */ public interface IsTraversableOnce<Repr> {
/*    */   Function1<Repr, GenTraversableOnce<Object>> conversion();
/*    */   
/*    */   public static class $anon$1 implements IsTraversableOnce<String> {
/*    */     private final Function1<String, GenTraversableOnce<Object>> conversion;
/*    */     
/*    */     public $anon$1() {
/* 53 */       $anonfun$1 $anonfun$1 = new $anonfun$1(this);
/* 53 */       Predef$ predef$ = Predef$.MODULE$;
/* 53 */       this.conversion = (Function1<String, GenTraversableOnce<Object>>)$anonfun$1;
/*    */     }
/*    */     
/*    */     public Function1<String, GenTraversableOnce<Object>> conversion() {
/* 53 */       return this.conversion;
/*    */     }
/*    */     
/*    */     public class $anonfun$1 extends AbstractFunction1<String, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(String x) {
/* 53 */         Predef$ predef$ = Predef$.MODULE$;
/* 53 */         return x;
/*    */       }
/*    */       
/*    */       public $anonfun$1(IsTraversableOnce.$anon$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static class IsTraversableOnce$$anon$2 implements IsTraversableOnce<C> {
/*    */     private final Function1<C, GenTraversableOnce<A0>> conversion;
/*    */     
/*    */     public IsTraversableOnce$$anon$2(Function1<C, GenTraversableOnce<A0>> conv$1) {
/* 59 */       this.conversion = conv$1;
/*    */     }
/*    */     
/*    */     public Function1<C, GenTraversableOnce<A0>> conversion() {
/* 59 */       return this.conversion;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IsTraversableOnce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */