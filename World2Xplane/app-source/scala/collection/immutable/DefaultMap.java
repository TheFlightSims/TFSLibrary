/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2q!\001\002\021\002\007\005\021B\001\006EK\032\fW\017\034;NCBT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\026?M\031\001aC\b\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021#MqR\"\001\002\n\005I\021!aA'baB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\004\"\001F\020\005\r\001\002AQ1\001\030\005\005\021\005\"\002\022\001\t\003\031\023A\002\023j]&$H\005F\001%!\taQ%\003\002'\r\t!QK\\5u\021\025A\003\001\"\021*\003\025!\003\017\\;t+\tQS\006\006\002,aA!\001#E\n-!\t!R\006B\003/O\t\007qF\001\002CcE\021ad\007\005\006c\035\002\rAM\001\003WZ\004B\001D\032\024Y%\021AG\002\002\007)V\004H.\032\032\t\013Y\002A\021I\034\002\r\021j\027N\\;t)\ty\001\bC\003:k\001\0071#A\002lKf\004B\001\005\001\024=\001")
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
/*    */     public final boolean apply(Tuple2 kv) {
/* 52 */       Object object2 = this.key$1;
/*    */       Object object1;
/* 52 */       return !(((object1 = kv._1()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*    */     }
/*    */     
/*    */     public DefaultMap$$anonfun$$minus$1(DefaultMap $outer, Object key$1) {}
/*    */   }
/*    */   
/*    */   public class DefaultMap$$anonfun$$minus$2 extends AbstractFunction1<Tuple2<A, B>, Builder<Tuple2<A, B>, Map<A, B>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Builder b$1;
/*    */     
/*    */     public final Builder<Tuple2<A, B>, Map<A, B>> apply(Tuple2 kv) {
/* 52 */       return this.b$1.$plus$eq(kv);
/*    */     }
/*    */     
/*    */     public DefaultMap$$anonfun$$minus$2(DefaultMap $outer, Builder b$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\DefaultMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */