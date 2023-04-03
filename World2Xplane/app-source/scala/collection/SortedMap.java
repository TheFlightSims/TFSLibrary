/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005eaaB\001\003!\003\r\ta\002\002\n'>\024H/\0323NCBT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)2\001C\n\036'\021\001\021\"D\020\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\r\005\003\017\037EaR\"\001\002\n\005A\021!aA'baB\021!c\005\007\001\t\025!\002A1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004\"AE\017\005\ry\001AQ1\001\026\005\005\021\005#\002\b!#q\021\023BA\021\003\0055\031vN\035;fI6\013\007\017T5lKB!a\002A\t\035\021\025!\003\001\"\001&\003\031!\023N\\5uIQ\ta\005\005\002\013O%\021\001\006\002\002\005+:LG\017C\003+\001\021\0053&A\003f[B$\0300F\001#\021\031i\003\001)C)]\005Qa.Z<Ck&dG-\032:\026\003=\002B\001M\0326E5\t\021G\003\0023\005\0059Q.\036;bE2,\027B\001\0332\005\035\021U/\0337eKJ\004BA\003\034\0229%\021q\007\002\002\007)V\004H.\032\032\b\013e\022\001\022\001\036\002\023M{'\017^3e\033\006\004\bC\001\b<\r\025\t!\001#\001='\tYT\bE\002?\003\016k\021a\020\006\003\001\n\tqaZ3oKJL7-\003\002C\t\0012k\034:uK\022l\025\r\035$bGR|'/\037\t\003\035\001AQ!R\036\005\002\031\013a\001P5oSRtD#\001\036\t\013)ZD\021\001%\026\007%ce\n\006\002K\037B!a\002A&N!\t\021B\nB\003\025\017\n\007Q\003\005\002\023\035\022)ad\022b\001+!)\001k\022a\002#\006\031qN\0353\021\007IS6J\004\002T1:\021AkV\007\002+*\021aKB\001\007yI|w\016\036 \n\003\025I!!\027\003\002\017A\f7m[1hK&\0211\f\030\002\t\037J$WM]5oO*\021\021\f\002\005\006=n\"\031aX\001\rG\006t')^5mI\032\023x.\\\013\004A*dGCA1o!\025q$\r\0325n\023\t\031wH\001\007DC:\024U/\0337e\rJ|W\016\005\002fM6\t1(\003\002h\003\n!1i\0347m!\021Qa'[6\021\005IQG!\002\013^\005\004)\002C\001\nm\t\025qRL1\001\026!\021q\001![6\t\013Ak\0069A8\021\007IS\026N\002\005rwA\005\031\021\001\002s\005\035!UMZ1vYR,2a\035<y'\r\001\030\002\036\t\005\035\001)x\017\005\002\023m\022)A\003\035b\001+A\021!\003\037\003\007=A$)\031A\013\t\013\021\002H\021A\023\t\013m\004H\021\t?\002\013\021\002H.^:\026\007u\f\t\001F\002\003\017\001BA\004\001vB\031!#!\001\005\017\005\r!P1\001\002\006\t\021!)M\t\003ofAq!!\003{\001\004\tY!\001\002lmB!!BN;\000\021\035\ty\001\035C!\003#\ta\001J7j]V\034Hc\001;\002\024!9\021QCA\007\001\004)\030aA6fsB!Q\r];x\001")
/*    */ public interface SortedMap<A, B> extends Map<A, B>, SortedMapLike<A, B, SortedMap<A, B>> {
/*    */   SortedMap<A, B> empty();
/*    */   
/*    */   Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder();
/*    */   
/*    */   public static abstract class Default$class {
/*    */     public static void $init$(SortedMap.Default $this) {}
/*    */     
/*    */     public static SortedMap $plus(SortedMap.Default $this, Tuple2 kv) {
/* 40 */       Builder b = SortedMap$.MODULE$.newBuilder($this.ordering());
/* 41 */       b.$plus$plus$eq($this);
/* 42 */       b.$plus$eq(new Tuple2(kv._1(), kv._2()));
/* 43 */       return (SortedMap)b.result();
/*    */     }
/*    */     
/*    */     public static SortedMap $minus(SortedMap.Default<A, B> $this, Object key) {
/* 47 */       Builder b = $this.newBuilder();
/* 48 */       $this.withFilter((Function1<Tuple2<A, B>, Object>)new SortedMap$Default$$anonfun$$minus$1($this, (SortedMap.Default<A, B>)key)).foreach((Function1)new SortedMap$Default$$anonfun$$minus$2($this, (SortedMap.Default<A, B>)b));
/* 49 */       return (SortedMap)b.result();
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface Default<A, B> extends SortedMap<A, B> {
/*    */     <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> param1Tuple2);
/*    */     
/*    */     SortedMap<A, B> $minus(A param1A);
/*    */     
/*    */     public class SortedMap$Default$$anonfun$$minus$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object key$1;
/*    */       
/*    */       public final boolean apply(Tuple2 kv) {
/*    */         Object object2 = this.key$1;
/*    */         Object object1;
/*    */         return !(((object1 = kv._1()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*    */       }
/*    */       
/*    */       public SortedMap$Default$$anonfun$$minus$1(SortedMap.Default $outer, Object key$1) {}
/*    */     }
/*    */     
/*    */     public class SortedMap$Default$$anonfun$$minus$2 extends AbstractFunction1<Tuple2<A, B>, Builder<Tuple2<A, B>, SortedMap<A, B>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Builder b$1;
/*    */       
/*    */       public final Builder<Tuple2<A, B>, SortedMap<A, B>> apply(Tuple2 kv) {
/*    */         return this.b$1.$plus$eq(kv);
/*    */       }
/*    */       
/*    */       public SortedMap$Default$$anonfun$$minus$2(SortedMap.Default $outer, Builder b$1) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */