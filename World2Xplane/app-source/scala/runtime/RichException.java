/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.compat.Platform$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)2A!\001\002\003\017\ti!+[2i\013b\034W\r\035;j_:T!a\001\003\002\017I,h\016^5nK*\tQ!A\003tG\006d\027m\001\001\024\005\001A\001CA\005\013\033\005!\021BA\006\005\005\031\te.\037*fM\"AQ\002\001B\001B\003%a\"A\002fq\016\004\"aD\f\017\005A)bBA\t\025\033\005\021\"BA\n\007\003\031a$o\\8u}%\tQ!\003\002\027\t\0059\001/Y2lC\036,\027B\001\r\032\005%!\006N]8xC\ndWM\003\002\027\t!)1\004\001C\0019\0051A(\0338jiz\"\"!H\020\021\005y\001Q\"\001\002\t\0135Q\002\031\001\b\t\013\005\002A\021\001\022\002'\035,Go\025;bG.$&/Y2f'R\024\030N\\4\026\003\r\002\"\001J\024\017\005%)\023B\001\024\005\003\031\001&/\0323fM&\021\001&\013\002\007'R\024\030N\\4\013\005\031\"\001")
/*    */ public final class RichException {
/*    */   private final Throwable exc;
/*    */   
/*    */   public RichException(Throwable exc) {}
/*    */   
/*    */   public String getStackTraceString() {
/* 14 */     return Predef$.MODULE$.refArrayOps((Object[])this.exc.getStackTrace()).mkString("", Platform$.MODULE$.EOL(), Platform$.MODULE$.EOL());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */