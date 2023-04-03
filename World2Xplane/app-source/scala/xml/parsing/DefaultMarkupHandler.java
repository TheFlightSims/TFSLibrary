/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.NodeSeq$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M3Q!\001\002\002\002%\021A\003R3gCVdG/T1sWV\004\b*\0318eY\026\024(BA\002\005\003\035\001\030M]:j]\036T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\031\"\001\001\006\021\005-aQ\"\001\002\n\0055\021!!D'be.,\b\017S1oI2,'\017C\003\020\001\021\005\001#\001\004=S:LGO\020\013\002#A\0211\002\001\005\006'\001!\t\001F\001\005K2,W\016\006\005\0263}A#f\f\033:!\t1r#D\001\005\023\tABAA\004O_\022,7+Z9\t\013i\021\002\031A\016\002\007A|7\017\005\002\035;5\ta!\003\002\037\r\t\031\021J\034;\t\013\001\022\002\031A\021\002\007A\024X\r\005\002#K9\021AdI\005\003I\031\ta\001\025:fI\0264\027B\001\024(\005\031\031FO]5oO*\021AE\002\005\006SI\001\r!I\001\006Y\006\024W\r\034\005\006WI\001\r\001L\001\006CR$(o\035\t\003-5J!A\f\003\003\0215+G/\031#bi\006DQ\001\r\nA\002E\nQa]2pa\026\004\"A\006\032\n\005M\"!\001\005(b[\026\034\b/Y2f\005&tG-\0338h\021\025)$\0031\0017\003\025)W\016\035;z!\tar'\003\0029\r\t9!i\\8mK\006t\007\"\002\036\023\001\004)\022\001B1sONDQ\001\020\001\005\002u\n\021\002\035:pG&s7\017\036:\025\tUqt(\021\005\0065m\002\ra\007\005\006\001n\002\r!I\001\007i\006\024x-\032;\t\013\t[\004\031A\021\002\007QDH\017C\003E\001\021\005Q)A\004d_6lWM\034;\025\007U1u\tC\003\033\007\002\0071\004C\003E\007\002\007\021\005C\003J\001\021\005!*A\005f]RLG/\037*fMR\031Qc\023'\t\013iA\005\031A\016\t\0135C\005\031A\021\002\0039DQa\024\001\005\002A\013A\001^3yiR\031Q#\025*\t\013iq\005\031A\016\t\013\ts\005\031A\021")
/*    */ public abstract class DefaultMarkupHandler extends MarkupHandler {
/*    */   public NodeSeq elem(int pos, String pre, String label, MetaData attrs, NamespaceBinding scope, boolean empty, NodeSeq args) {
/* 19 */     return NodeSeq$.MODULE$.Empty();
/*    */   }
/*    */   
/*    */   public NodeSeq procInstr(int pos, String target, String txt) {
/* 21 */     return NodeSeq$.MODULE$.Empty();
/*    */   }
/*    */   
/*    */   public NodeSeq comment(int pos, String comment) {
/* 23 */     return NodeSeq$.MODULE$.Empty();
/*    */   }
/*    */   
/*    */   public NodeSeq entityRef(int pos, String n) {
/* 25 */     return NodeSeq$.MODULE$.Empty();
/*    */   }
/*    */   
/*    */   public NodeSeq text(int pos, String txt) {
/* 27 */     return NodeSeq$.MODULE$.Empty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\DefaultMarkupHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */