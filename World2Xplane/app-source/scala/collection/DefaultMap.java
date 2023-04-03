/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e2q!\001\002\021\002\007\005qA\001\006EK\032\fW\017\034;NCBT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)2\001C\n\036'\r\001\021\"\004\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007\003\002\b\020#qi\021AA\005\003!\t\0211!T1q!\t\0212\003\004\001\005\013Q\001!\031A\013\003\003\005\013\"AF\r\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\003\016\n\005m!!aA!osB\021!#\b\003\007=\001!)\031A\013\003\003\tCQ\001\t\001\005\002\005\na\001J5oSR$C#\001\022\021\005)\031\023B\001\023\005\005\021)f.\033;\t\013\031\002A\021I\024\002\013\021\002H.^:\026\005!ZCCA\025/!\021qq\"\005\026\021\005IYC!\002\027&\005\004i#A\001\"2#\ta\022\004C\0030K\001\007\001'\001\002lmB!!\"M\t+\023\t\021DA\001\004UkBdWM\r\005\006i\001!\t%N\001\007I5Lg.^:\025\00551\004\"B\0344\001\004\t\022aA6fsB!a\002A\t\035\001")
/*    */ public interface DefaultMap<A, B> extends Map<A, B> {
/*    */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*    */   
/*    */   Map<A, B> $minus(A paramA);
/*    */   
/*    */   public class DefaultMap$$anonfun$$minus$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object key$1;
/*    */     
/*    */     public final boolean apply(Tuple2 x$1) {
/* 44 */       Object object2 = x$1._1();
/*    */       Object object1;
/* 44 */       return !(((object1 = this.key$1) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*    */     }
/*    */     
/*    */     public DefaultMap$$anonfun$$minus$1(DefaultMap $outer, Object key$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\DefaultMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */