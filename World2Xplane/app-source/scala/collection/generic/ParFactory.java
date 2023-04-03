/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e3Q!\001\002\002\002%\021!\002U1s\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2c\001\001\fYA\031A\"D\b\016\003\tI!A\004\002\003+\035+g\016\026:bm\026\0248/\0312mK\032\0137\r^8ssB\021\001#\005\007\001\t\025\021\002A1\001\024\005\t\0315)\006\002\025GE\021Q#\007\t\003-]i\021AB\005\0031\031\021qAT8uQ&twME\002\0339%2Aa\007\001\0013\taAH]3gS:,W.\0328u}A\031Q\004\t\022\016\003yQ!a\b\003\002\021A\f'/\0317mK2L!!\t\020\003\027A\013'/\023;fe\006\024G.\032\t\003!\r\"Q\001J\tC\002\025\022\021\001W\t\003+\031\002\"AF\024\n\005!2!aA!osB!AB\013\022\020\023\tY#A\001\nHK:,'/[2QCJ$V-\0349mCR,\007c\001\007.\037%\021aF\001\002\024\017\026tWM]5d!\006\0248i\\7qC:LwN\034\005\006a\001!\t!M\001\007y%t\027\016\036 \025\003I\0022\001\004\001\020\r\021!\004\001A\033\003+\035+g.\032:jG\016\013gnQ8nE&tWM\022:p[V\021a\007P\n\004g]r\004c\001\035:w5\t\001!\003\002;\033\t\031r)\0328fe&\0347)\0318Ck&dGM\022:p[B\021\001\003\020\003\006{M\022\r!\n\002\002\003B)AbP!<\r&\021\001I\001\002\017\007\006t7i\\7cS:,gI]8na\t\021E\tE\002\021#\r\003\"\001\005#\005\023\025\033\024\021!A\001\006\003)#aA0%cA\031\001#E\036\t\013A\032D\021\001%\025\003%\0032\001O\032<\021\025Y5\007\"\021M\003\025\t\007\017\0357z)\ti\005\013\005\003\036\035n2\025BA(\037\005!\031u.\0342j]\026\024\b\"B)K\001\004\021\026\001\0024s_6\004\"\001O*\n\005Q+&\001B\"pY2L!A\026\002\003!\035+g.\032:jG\016{W\016]1oS>t\007\"B&4\t\003BF#A'")
/*    */ public abstract class ParFactory<CC extends ParIterable<Object>> extends GenTraversableFactory<CC> implements GenericParCompanion<CC> {
/*    */   public class GenericCanCombineFrom<A> extends GenTraversableFactory<CC>.GenericCanBuildFrom<A> implements CanCombineFrom<CC, A, CC> {
/*    */     public GenericCanCombineFrom(ParFactory<CC> $outer) {
/* 33 */       super($outer);
/*    */     }
/*    */     
/*    */     public Combiner<A, CC> apply(ParIterable from) {
/* 34 */       return from.genericCombiner();
/*    */     }
/*    */     
/*    */     public Combiner<A, CC> apply() {
/* 35 */       return scala$collection$generic$ParFactory$GenericCanCombineFrom$$$outer().newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\ParFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */