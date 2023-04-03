/*    */ package scala.annotation;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M2Q!\001\002\003\t\031\021\021\"\\5he\006$\030n\0348\013\005\r!\021AC1o]>$\030\r^5p]*\tQ!A\003tG\006d\027mE\002\001\017-\001\"\001C\005\016\003\tI!A\003\002\003\025\005sgn\034;bi&|g\016\005\002\t\031%\021QB\001\002\021'R\fG/[2B]:|G/\031;j_:D\001b\004\001\003\002\003\006I!E\001\b[\026\0348/Y4f\007\001\001\"A\005\f\017\005M!R\"\001\003\n\005U!\021A\002)sK\022,g-\003\002\0301\t11\013\036:j]\036T!!\006\003\t\021i\001!\021!Q\001\nE\t\021b\0315b]\036,G-\0238\t\013q\001A\021A\017\002\rqJg.\033;?)\rqr\004\t\t\003\021\001AQaD\016A\002EAQAG\016A\002EAQ\001\b\001\005\002\t\"BAH\022)U!)A%\ta\001K\005aQ.\0316peZ+'o]5p]B\0211CJ\005\003O\021\0211!\0238u\021\025I\023\0051\001&\0031i\027N\\8s-\026\0248/[8o\021\025y\021\0051\001\022Q\021\tCfL\031\021\005Mi\023B\001\030\005\005)!W\r\035:fG\006$X\rZ\021\002a\005ySk]3!i\",\007eY8ogR\024Xo\031;pe\002\"\030m[5oO\002\"xo\034\021TiJLgnZ:!S:\034H/Z1e]\005\n!'\001\0043]E\002d\006\r")
/*    */ public final class migration extends Annotation implements StaticAnnotation {
/*    */   public migration(String message, String changedIn) {}
/*    */   
/*    */   public migration(int majorVersion, int minorVersion, String message) {
/* 30 */     this(message, (new StringBuilder()).append(majorVersion).append(".").append(BoxesRunTime.boxToInteger(minorVersion)).toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\annotation\migration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */