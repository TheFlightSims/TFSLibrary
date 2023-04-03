/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Q3A!\001\002\001\017\t1\001k\021#bi\006T!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\031\"\001\001\005\021\007%QA\"D\001\003\023\tY!A\001\003Bi>l\007CA\007\022\035\tqq\"D\001\005\023\t\001B!\001\004Qe\026$WMZ\005\003%M\021aa\025;sS:<'B\001\t\005\021%)\002A!A!\002\023aa#\001\003eCR\f\027BA\013\013\021\025A\002\001\"\001\032\003\031a\024N\\5u}Q\021!d\007\t\003\023\001AQ!F\fA\0021AQ!\b\001\005By\t1BY;jY\022\034FO]5oOR\021qd\013\t\003A!r!!\t\024\017\005\t*S\"A\022\013\005\0212\021A\002\037s_>$h(C\001\006\023\t9C!A\004qC\016\\\027mZ3\n\005%R#!D*ue&twMQ;jY\022,'O\003\002(\t!)A\006\ba\001?\005\0211OY\004\006]\tA\taL\001\007!\016#\025\r^1\021\005%\001d!B\001\003\021\003\t4c\001\0313kA\021abM\005\003i\021\021a!\0218z%\0264\007C\001\b7\023\t9DA\001\007TKJL\027\r\\5{C\ndW\rC\003\031a\021\005\021\bF\0010\021\025Y\004\007\"\001=\003\025\t\007\017\0357z)\tQR\bC\003\026u\001\007A\002C\003@a\021\005\001)A\004v]\006\004\b\017\\=\025\005\005#\005c\001\bC\031%\0211\t\002\002\007\037B$\030n\0348\t\013\025s\004\031\001$\002\013=$\b.\032:\021\00599\025B\001%\005\005\r\te.\037\005\b\025B\n\t\021\"\003L\003-\021X-\0313SKN|GN^3\025\0031\003\"!\024*\016\0039S!a\024)\002\t1\fgn\032\006\002#\006!!.\031<b\023\t\031fJ\001\004PE*,7\r\036")
/*    */ public class PCData extends Atom<String> {
/*    */   public PCData(String data) {
/* 18 */     super(data);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 27 */     Predef$ predef$ = Predef$.MODULE$;
/* 27 */     return sb.append((new StringOps("<![CDATA[%s]]>")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (String)data() })));
/*    */   }
/*    */   
/*    */   public static Option<String> unapply(Object paramObject) {
/*    */     return PCData$.MODULE$.unapply(paramObject);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\PCData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */