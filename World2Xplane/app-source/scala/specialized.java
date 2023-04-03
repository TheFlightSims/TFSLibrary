/*    */ package scala;
/*    */ 
/*    */ import scala.annotation.Annotation;
/*    */ import scala.annotation.StaticAnnotation;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)2A!\001\002\001\013\tY1\017]3dS\006d\027N_3e\025\005\031\021!B:dC2\f7\001A\n\004\001\031a\001CA\004\013\033\005A!BA\005\003\003)\tgN\\8uCRLwN\\\005\003\027!\021!\"\0218o_R\fG/[8o!\t9Q\"\003\002\017\021\t\0012\013^1uS\016\feN\\8uCRLwN\034\005\t!\001\021\t\021)A\005#\005)qM]8vaB\021!C\006\b\003'Qi\021AA\005\003+\t\tQb\0259fG&\fG.\033>bE2,\027BA\f\031\005A\031\006/Z2jC2L'0\0323He>,\bO\003\002\026\005!)!\004\001C\0017\0051A(\0338jiz\"\"\001H\017\021\005M\001\001\"\002\t\032\001\004\t\002\"\002\016\001\t\003yBC\001\017!\021\025\tc\0041\001#\003\025!\030\020]3t!\r\0312%J\005\003I\t\021!\002\020:fa\026\fG/\0323?!\t\031b%\003\002(\005\ti1\013]3dS\006d\027N_1cY\026DQA\007\001\005\002%\"\022\001\b")
/*    */ public class specialized extends Annotation implements StaticAnnotation {
/*    */   public specialized(Specializable.SpecializedGroup group) {}
/*    */   
/*    */   public specialized(Seq types) {
/* 30 */     this(new Specializable.Group<List>(types.toList()));
/*    */   }
/*    */   
/*    */   public specialized() {
/* 31 */     this(Specializable$.MODULE$.Primitives());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\specialized.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */