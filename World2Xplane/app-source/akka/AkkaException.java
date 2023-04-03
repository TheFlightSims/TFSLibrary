/*    */ package akka;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y2A!\001\002\001\013\ti\021i[6b\013b\034W\r\035;j_:T\021aA\001\005C.\\\027m\001\001\024\007\0011A\003\005\002\b#9\021\001B\004\b\003\0231i\021A\003\006\003\027\021\ta\001\020:p_Rt\024\"A\007\002\013M\034\027\r\\1\n\005=\001\022a\0029bG.\fw-\032\006\002\033%\021!c\005\002\021%VtG/[7f\013b\034W\r\035;j_:T!a\004\t\021\005U1R\"\001\t\n\005]\001\"\001D*fe&\fG.\033>bE2,\007\002C\r\001\005\003\005\013\021\002\016\002\0175,7o]1hKB\0211D\b\b\003+qI!!\b\t\002\rA\023X\rZ3g\023\ty\002E\001\004TiJLgn\032\006\003;AA\001B\t\001\003\002\003\006IaI\001\006G\006,8/\032\t\003\017\021J!!J\n\003\023QC'o\\<bE2,\007\"B\024\001\t\003A\023A\002\037j]&$h\bF\002*W1\002\"A\013\001\016\003\tAQ!\007\024A\002iAQA\t\024A\002\rBQa\n\001\005\0029\"\"!K\030\t\013Aj\003\031\001\016\002\0075\034x\rK\002\001eU\002\"!F\032\n\005Q\002\"\001E*fe&\fGNV3sg&|g.V%E=\005\t\001")
/*    */ public class AkkaException extends RuntimeException implements Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   public AkkaException(String message, Throwable cause) {
/* 15 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public AkkaException(String msg) {
/* 16 */     this(msg, null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\AkkaException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */