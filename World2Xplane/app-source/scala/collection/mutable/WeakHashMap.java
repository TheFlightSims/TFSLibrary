/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.convert.Wrappers;
/*    */ import scala.collection.convert.Wrappers$;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001-4A!\001\002\001\023\tYq+Z1l\021\006\034\b.T1q\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007)QReE\002\001\027\035\002B\001\004\013\031I9\021Q\"\005\b\003\035=i\021\001B\005\003!\021\tqaY8om\026\024H/\003\002\023'\005AqK]1qa\026\0248O\003\002\021\t%\021QC\006\002\f\0256\013\007o\026:baB,'/\003\002\030'\tAqK]1qa\026\0248\017\005\002\03251\001A!B\016\001\005\004a\"!A!\022\005u\t\003C\001\020 \033\0051\021B\001\021\007\005\035qu\016\0365j]\036\004\"A\b\022\n\005\r2!aA!osB\021\021$\n\003\006M\001\021\r\001\b\002\002\005B)A\002\013\r%U%\021\021F\006\002\020\0256\013\007o\026:baB,'\017T5lKB!1\006\001\r%\033\005\021\001\"B\027\001\t\003q\023A\002\037j]&$h\bF\001+\021\025\001\004\001\"\0212\003\025)W\016\035;z+\005Qs!B\032\003\021\003!\024aC,fC.D\025m\0355NCB\004\"aK\033\007\013\005\021\001\022\001\034\024\007U:d\bE\0029wuj\021!\017\006\003u\021\tqaZ3oKJL7-\003\002=s\t\tR*\036;bE2,W*\0319GC\016$xN]=\021\005-\002\001C\001\020@\023\t\001eA\001\007TKJL\027\r\\5{C\ndW\rC\003.k\021\005!\tF\0015\021\025!U\007b\001F\0031\031\027M\034\"vS2$gI]8n+\r1EKV\013\002\017B)\001\b\023&Q/&\021\021*\017\002\r\007\006t')^5mI\032\023x.\034\t\003\0272k\021!N\005\003\033:\023AaQ8mY&\021q*\017\002\016\017\026tW*\0319GC\016$xN]=\021\ty\t6+V\005\003%\032\021a\001V;qY\026\024\004CA\rU\t\025Y2I1\001\035!\tIb\013B\003'\007\n\007A\004\005\003,\001M+\006\"\002\0316\t\003IVc\001.^?V\t1\f\005\003,\001qs\006CA\r^\t\025Y\002L1\001\035!\tIr\fB\003'1\n\007A\004C\004bk\005\005I\021\0022\002\027I,\027\r\032*fg>dg/\032\013\002GB\021A-[\007\002K*\021amZ\001\005Y\006twMC\001i\003\021Q\027M^1\n\005),'AB(cU\026\034G\017")
/*    */ public class WeakHashMap<A, B> extends Wrappers.JMapWrapper<A, B> implements Wrappers.JMapWrapperLike<A, B, WeakHashMap<A, B>> {
/*    */   public WeakHashMap() {
/* 40 */     super((Wrappers)Wrappers$.MODULE$, new java.util.WeakHashMap<Object, Object>());
/*    */   }
/*    */   
/*    */   public WeakHashMap<A, B> empty() {
/* 42 */     return new WeakHashMap();
/*    */   }
/*    */   
/*    */   public static <A, B> CanBuildFrom<WeakHashMap<?, ?>, Tuple2<A, B>, WeakHashMap<A, B>> canBuildFrom() {
/*    */     return WeakHashMap$.MODULE$.canBuildFrom();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\WeakHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */