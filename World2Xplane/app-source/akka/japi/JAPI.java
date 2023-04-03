/*   */ package akka.japi;
/*   */ 
/*   */ import scala.collection.Seq;
/*   */ 
/*   */ public class JAPI {
/*   */   public static <T> Seq<T> seq(T... paramVarArgs) {
/* 8 */     return (Seq)Util.immutableSeq(paramVarArgs);
/*   */   }
/*   */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\JAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */