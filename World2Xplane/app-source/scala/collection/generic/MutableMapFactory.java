/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r3Q!\001\002\002\002%\021\021#T;uC\ndW-T1q\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2C\001\001\f!\raQbD\007\002\005%\021aB\001\002\013\033\006\004h)Y2u_JL\bC\001\t\022\031\001!QA\005\001C\002M\021!aQ\"\026\007Q\031#&\005\002\0263A\021acF\007\002\r%\021\001D\002\002\b\035>$\b.\0338h%\rQB\004\f\004\0057\001\001\021D\001\007=e\0264\027N\\3nK:$h\b\005\003\036A\tJS\"\001\020\013\005}!\021aB7vi\006\024G.Z\005\003Cy\0211!T1q!\t\0012\005B\003%#\t\007QEA\001B#\t)b\005\005\002\027O%\021\001F\002\002\004\003:L\bC\001\t+\t\025Y\023C1\001&\005\005\021\005#B\017.E%z\023B\001\030\037\005\035i\025\r\035'jW\026\004B\001E\t#S!)\021\007\001C\001e\0051A(\0338jiz\"\022a\r\t\004\031\001y\001\"B\033\001\t\0032\024A\0038fo\n+\030\016\0343feV\031qgP!\026\003a\002B!H\035<\005&\021!H\b\002\b\005VLG\016Z3s!\0211BH\020!\n\005u2!A\002+va2,'\007\005\002\021\021)A\005\016b\001KA\021\001#\021\003\006WQ\022\r!\n\t\005!Eq\004\t")
/*    */ public abstract class MutableMapFactory<CC extends Map<Object, Object>> extends MapFactory<CC> {
/*    */   public <A, B> Builder<Tuple2<A, B>, CC> newBuilder() {
/* 29 */     return (Builder<Tuple2<A, B>, CC>)empty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\MutableMapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */