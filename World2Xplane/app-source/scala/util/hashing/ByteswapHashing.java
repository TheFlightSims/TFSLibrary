/*    */ package scala.util.hashing;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3A!\001\002\003\023\ty!)\037;fg^\f\007\017S1tQ&twM\003\002\004\t\0059\001.Y:iS:<'BA\003\007\003\021)H/\0337\013\003\035\tQa]2bY\006\034\001!\006\002\013+M\031\001aC\b\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\021q\001S1tQ&tw\r\005\002\025+1\001A!\002\f\001\005\0049\"!\001+\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\005\006?\001!\t\001I\001\007y%t\027\016\036 \025\003\005\0022\001\005\001\024\021\025\031\003\001\"\001%\003\021A\027m\0355\025\005\025B\003C\001\007'\023\t9cAA\002J]RDQ!\013\022A\002M\t\021A^\004\006W\tA\t\001L\001\020\005f$Xm]<ba\"\0137\017[5oOB\021\001#\f\004\006\003\tA\tAL\n\004[-y\003C\001\0071\023\t\tdA\001\007TKJL\027\r\\5{C\ndW\rC\003 [\021\0051\007F\001-\r\021)T\006\002\034\003\017\rC\027-\0338fIV\021qGO\n\004i-A\004c\001\t\022sA\021AC\017\003\006-Q\022\ra\006\005\tyQ\022\t\021)A\005q\005\t\001\016C\003 i\021\005a\b\006\002@\003B\031\001\tN\035\016\0035BQ\001P\037A\002aBQa\t\033\005\002\r#\"!\n#\t\013%\022\005\031A\035\t\013\031kC\021A$\002\013\rD\027-\0338\026\005![ECA%M!\r\001\022C\023\t\003)-#QAF#C\002]AQ\001P#A\002%CqAT\027\002\002\023%q*A\006sK\006$'+Z:pYZ,G#\001)\021\005E3V\"\001*\013\005M#\026\001\0027b]\036T\021!V\001\005U\0064\030-\003\002X%\n1qJ\0316fGR\004")
/*    */ public final class ByteswapHashing<T> implements Hashing<T> {
/*    */   public static <T> Hashing<T> chain(Hashing<T> paramHashing) {
/*    */     return ByteswapHashing$.MODULE$.chain(paramHashing);
/*    */   }
/*    */   
/*    */   public int hash(Object v) {
/* 20 */     return package$.MODULE$.byteswap32(ScalaRunTime$.MODULE$.hash(v));
/*    */   }
/*    */   
/*    */   public static class Chained<T> implements Hashing<T> {
/*    */     private final Hashing<T> h;
/*    */     
/*    */     public Chained(Hashing<T> h) {}
/*    */     
/*    */     public int hash(Object v) {
/* 28 */       return package$.MODULE$.byteswap32(this.h.hash((T)v));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\ByteswapHashing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */