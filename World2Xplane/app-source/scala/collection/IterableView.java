/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U3q!\001\002\021\002G\005qA\001\007Ji\026\024\030M\0317f-&,wO\003\002\004\t\005Q1m\0347mK\016$\030n\0348\013\003\025\tQa]2bY\006\034\001!F\002\t'u\031B\001A\005\016AA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\0139y\021\003H\020\016\003\tI!\001\005\002\003!%#XM]1cY\0264\026.Z<MS.,\007C\001\n\024\031\001!a\001\006\001\005\006\004)\"!A!\022\005YI\002C\001\006\030\023\tABAA\004O_RD\027N\\4\021\005)Q\022BA\016\005\005\r\te.\037\t\003%u!aA\b\001\005\006\004)\"\001B\"pY2\004BA\004\001\0229A!a\"I\t\035\023\t\021#AA\bHK:LE/\032:bE2,g+[3x\017\025!#\001#\001&\0031IE/\032:bE2,g+[3x!\tqaEB\003\002\005!\005qe\005\002'\023!)\021F\nC\001U\0051A(\0338jiz\"\022!J\003\005=\031\002A\006M\002.iE\002BA\004\0301g%\021qF\001\002\020)J\fg/\032:tC\ndWMV5foB\021!#\r\003\ne-\n\t\021!A\003\002U\0211a\030\0232!\t\021B\007B\0056W\005\005\t\021!B\001m\t\t1)\005\002\027oA\022\001\b\020\t\004\035eZ\024B\001\036\003\005-!&/\031<feN\f'\r\\3\021\005IaD!C\037?\003\003\005\tQ!\001\026\005\ryFE\r\003\nk-\n\t\021!A\003\002YBQ\001\021\024\005\004\005\013AbY1o\005VLG\016\032$s_6,\"A\021'\026\003\r\003R\001R$J\0276k\021!\022\006\003\r\n\tqaZ3oKJL7-\003\002I\013\na1)\0318Ck&dGM\022:p[B\021!jK\007\002MA\021!\003\024\003\006)}\022\r!\006\t\005\035\001Ye\n\r\002P'B\031a\002\025*\n\005E\023!\001C%uKJ\f'\r\\3\021\005I\031F!\003+@\003\003\005\tQ!\001\026\005\ryFe\r")
/*    */ public interface IterableView<A, Coll> extends IterableViewLike<A, Coll, IterableView<A, Coll>>, GenIterableView<A, Coll> {
/*    */   public static class IterableView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, IterableView<A, Iterable<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 28 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 29 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */