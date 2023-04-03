/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.GrowingBuilder;
/*    */ import scala.collection.mutable.Set;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q2Q!\001\002\002\002%\021\021#T;uC\ndWmU3u\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2C\001\001\f!\raQbD\007\002\005%\021aB\001\002\013'\026$h)Y2u_JL\bC\001\t\022\031\001!QA\005\001C\002M\021!aQ\"\026\005Q\031\023CA\013\032!\t1r#D\001\007\023\tAbAA\004O_RD\027N\\4\023\007ia\022F\002\003\034\001\001I\"\001\004\037sK\032Lg.Z7f]Rt\004cA\017!E5\taD\003\002 \t\0059Q.\036;bE2,\027BA\021\037\005\r\031V\r\036\t\003!\r\"Q\001J\tC\002\025\022\021\001W\t\003+\031\002\"AF\024\n\005!2!aA!osB!QD\013\022-\023\tYcDA\004TKRd\025n[3\021\007A\t\"\005C\003/\001\021\005q&\001\004=S:LGO\020\013\002aA\031A\002A\b\t\013I\002A\021A\032\002\0259,wOQ;jY\022,'/\006\0025sU\tQ\007\005\003\036maZ\024BA\034\037\005\035\021U/\0337eKJ\004\"\001E\035\005\013i\n$\031A\023\003\003\005\0032\001E\t9\001")
/*    */ public abstract class MutableSetFactory<CC extends Set<Object>> extends SetFactory<CC> {
/*    */   public <A> Builder<A, CC> newBuilder() {
/* 18 */     return (Builder<A, CC>)new GrowingBuilder((Growable)empty());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\MutableSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */