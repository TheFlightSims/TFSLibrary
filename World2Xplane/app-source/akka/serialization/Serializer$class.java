/*    */ package akka.serialization;
/*    */ 
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Option$;
/*    */ 
/*    */ public abstract class Serializer$class {
/*    */   public static void $init$(Serializer $this) {}
/*    */   
/*    */   public static final Object fromBinary(Serializer $this, byte[] bytes) {
/* 60 */     return $this.fromBinary(bytes, (Option<Class<?>>)None$.MODULE$);
/*    */   }
/*    */   
/*    */   public static final Object fromBinary(Serializer $this, byte[] bytes, Class clazz) {
/* 65 */     return $this.fromBinary(bytes, Option$.MODULE$.apply(clazz));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\Serializer$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */