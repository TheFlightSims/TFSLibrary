/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=2Q!\001\002\002\"-\021\001BS*P\035RK\b/\032\006\003\007\021\tAA[:p]*\021QAB\001\ba\006\0248/\0338h\025\t9\001\"\001\003vi&d'\"A\005\002\013M\034\027\r\\1\004\001M\021\001\001\004\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007\"B\t\001\t\003\021\022A\002\037j]&$h\bF\001\024!\t!\002!D\001\003\021\0251\002A\"\001\030\003!!xn\025;sS:<GC\001\r !\tIBD\004\002\0165%\0211\004C\001\007!J,G-\0324\n\005uq\"AB*ue&twM\003\002\034\021!)\001%\006a\001C\005Iam\034:nCR$XM\035\t\003E\025r!\001F\022\n\005\021\022\021A\003&T\037:3uN]7bi&\021ae\n\002\017-\006dW/\032$pe6\fG\017^3s\025\t!#\001C\003\027\001\021\005\023\006F\001\031S\r\0011&L\005\003Y\t\021\021BS*P\035\006\023(/Y=\n\0059\022!A\003&T\037:{%M[3di\002")
/*    */ public abstract class JSONType {
/*    */   public abstract String toString(Function1<Object, String> paramFunction1);
/*    */   
/*    */   public String toString() {
/* 33 */     return toString(JSONFormat$.MODULE$.defaultFormatter());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */