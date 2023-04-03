/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableLike;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001m3q!\001\002\021\002G\005\021BA\tJgR\023\030M^3sg\006\024G.\032'jW\026T!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\002I\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\t\025\001\002A!\001\022\005\005\t\025C\001\n\026!\ta1#\003\002\025\r\t9aj\034;iS:<\007C\001\007\027\023\t9bAA\002B]fDq!\007\001C\002\033\005!$\001\006d_:4XM]:j_:,\022a\007\t\005\031qq\"%\003\002\036\r\tIa)\0368di&|g.\r\t\003?\001b\001\001B\003\"\001\t\007\021C\001\003SKB\024\b\003B\022%Myi\021\001B\005\003K\021\021!cR3o)J\fg/\032:tC\ndW\rT5lKB\021qeD\007\002\001\035)\021F\001E\001U\005\t\022j\035+sCZ,'o]1cY\026d\025n[3\021\005-bS\"\001\002\007\013\005\021\001\022A\027\024\0051Z\001\"B\030-\t\003\001\024A\002\037j]&$h\bF\001+\021\035\021DF1A\005\004M\n!b\035;sS:<'+\0329s+\005!$CA\0338\r\0211D\006\001\033\003\031q\022XMZ5oK6,g\016\036 \021\007-\002\001\b\005\002:y9\021ABO\005\003w\031\ta\001\025:fI\0264\027BA\037?\005\031\031FO]5oO*\0211HB\003\005!U\002\003\t\005\002\r\003&\021!I\002\002\005\007\"\f'\017\003\004EY\001\006I\001N\001\fgR\024\030N\\4SKB\024\b\005C\003GY\021\rq)\001\fhK:$&/\031<feN\f'\r\\3MS.,'+\0329s+\rAU\n\026\013\003\023^\023\"AS&\007\tYb\003!\023\t\004W\001a\005cA\020N'\022)a*\022b\001\037\n\t1)\006\002\022!\022)\021K\025b\001#\t\tq\fB\003O\013\n\007q\n\005\002 )\022)Q+\022b\001#\t\021\021\tM\003\005!)\0031\013C\003Y\013\002\017\021,\001\003d_:4\b\003\002\007\035\031j\003Ba\t\023T\031\002")
/*     */ public interface IsTraversableLike<Repr> {
/*     */   Function1<Repr, GenTraversableLike<Object, Repr>> conversion();
/*     */   
/*     */   public static class $anon$1 implements IsTraversableLike<String> {
/*     */     private final Function1<String, GenTraversableLike<Object, String>> conversion;
/*     */     
/*     */     public $anon$1() {
/* 121 */       $anonfun$1 $anonfun$1 = new $anonfun$1(this);
/* 121 */       Predef$ predef$ = Predef$.MODULE$;
/* 121 */       this.conversion = (Function1<String, GenTraversableLike<Object, String>>)$anonfun$1;
/*     */     }
/*     */     
/*     */     public Function1<String, GenTraversableLike<Object, String>> conversion() {
/* 121 */       return this.conversion;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply(String x) {
/* 121 */         Predef$ predef$ = Predef$.MODULE$;
/* 121 */         return x;
/*     */       }
/*     */       
/*     */       public $anonfun$1(IsTraversableLike.$anon$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IsTraversableLike$$anon$2 implements IsTraversableLike<C> {
/*     */     private final Function1<C, GenTraversableLike<A0, C>> conversion;
/*     */     
/*     */     public IsTraversableLike$$anon$2(Function1<C, GenTraversableLike<A0, C>> conv$1) {
/* 127 */       this.conversion = conv$1;
/*     */     }
/*     */     
/*     */     public Function1<C, GenTraversableLike<A0, C>> conversion() {
/* 127 */       return this.conversion;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IsTraversableLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */