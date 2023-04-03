/*    */ package scala.xml;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Null$;
/*    */ import scala.xml.pull.XMLEvent;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}2Q!\001\002\002\002\035\0211b\0259fG&\fGNT8eK*\0211\001B\001\004q6d'\"A\003\002\013M\034\027\r\\1\004\001M\031\001\001\003\007\021\005%QQ\"\001\002\n\005-\021!\001\002(pI\026\004\"!\004\t\016\0039Q!a\004\002\002\tA,H\016\\\005\003#9\021\001\002W'M\013Z,g\016\036\005\006'\001!\t\001F\001\007y%t\027\016\036 \025\003U\001\"!\003\001\t\013]\001AQ\t\r\002\025\005$HO]5ckR,7/F\001\032\035\tI!$\003\002\034\005\005!a*\0367m\021\025i\002\001\"\022\037\003%q\027-\\3ta\006\034W-F\001 !\t\001\023%D\001\005\023\t\021CA\001\003Ok2d\007\"\002\023\001\t\013)\023!B2iS2$W#\001\024\017\005\035bS\"\001\025\013\005%R\023!C5n[V$\030M\0317f\025\tYC!\001\006d_2dWm\031;j_:L!!\f\025\002\0079KG\016C\0030\001\031\005\001'A\006ck&dGm\025;sS:<GCA\031>!\t\021$H\004\0024q9\021AgN\007\002k)\021aGB\001\007yI|w\016\036 \n\003\025I!!\017\003\002\017A\f7m[1hK&\0211\b\020\002\016'R\024\030N\\4Ck&dG-\032:\013\005e\"\001\"\002 /\001\004\t\024AA:c\001")
/*    */ public abstract class SpecialNode extends Node implements XMLEvent {
/*    */   public final Null$ attributes() {
/* 22 */     return Null$.MODULE$;
/*    */   }
/*    */   
/*    */   public final Null$ namespace() {
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public final Nil$ child() {
/* 28 */     return Nil$.MODULE$;
/*    */   }
/*    */   
/*    */   public abstract StringBuilder buildString(StringBuilder paramStringBuilder);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\SpecialNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */