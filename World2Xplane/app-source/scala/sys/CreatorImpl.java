/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e2a!\001\002\002\002\t1!aC\"sK\006$xN]%na2T!a\001\003\002\007ML8OC\001\006\003\025\0318-\0317b+\t9acE\002\001\0211\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PU3g!\ri\021\003\006\b\003\035=i\021AA\005\003!\t\tA\001\025:pa&\021!c\005\002\b\007J,\027\r^8s\025\t\001\"\001\005\002\026-1\001AAB\f\001\t\013\007\021DA\001U\007\001\t\"AG\017\021\005%Y\022B\001\017\005\005\035qu\016\0365j]\036\004\"!\003\020\n\005}!!aA!os\"A\021\005\001B\001B\003%!%A\001g!\021I1%\n\013\n\005\021\"!!\003$v]\016$\030n\03482!\t1\023F\004\002\nO%\021\001\006B\001\007!J,G-\0324\n\005)Z#AB*ue&twM\003\002)\t!)Q\006\001C\001]\0051A(\0338jiz\"\"a\f\031\021\0079\001A\003C\003\"Y\001\007!\005C\0033\001\021\0051'A\003baBd\027\020\006\0025oA\031a\"\016\013\n\005Y\022!\001\002)s_BDQ\001O\031A\002\025\n1a[3z\001")
/*    */ public abstract class CreatorImpl<T> implements Prop.Creator<T> {
/*    */   private final Function1<String, T> f;
/*    */   
/*    */   public CreatorImpl(Function1<String, T> f) {}
/*    */   
/*    */   public Prop<T> apply(String key) {
/* 45 */     return new PropImpl<T>(key, this.f);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\CreatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */