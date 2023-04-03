/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Q3A!\001\002\001\017\t!A+\032=u\025\t\031A!A\002y[2T\021!B\001\006g\016\fG.Y\002\001'\t\001\001\002E\002\n\0251i\021AA\005\003\027\t\021A!\021;p[B\021Q\"\005\b\003\035=i\021\001B\005\003!\021\ta\001\025:fI\0264\027B\001\n\024\005\031\031FO]5oO*\021\001\003\002\005\n+\001\021\t\021)A\005\031Y\tA\001Z1uC&\021QC\003\005\0061\001!\t!G\001\007y%t\027\016\036 \025\005iY\002CA\005\001\021\025)r\0031\001\r\021\025i\002\001\"\021\037\003-\021W/\0337e'R\024\030N\\4\025\005}Y\003C\001\021)\035\t\tcE\004\002#K5\t1E\003\002%\r\0051AH]8pizJ\021!B\005\003O\021\tq\001]1dW\006<W-\003\002*U\ti1\013\036:j]\036\024U/\0337eKJT!a\n\003\t\0131b\002\031A\020\002\005M\024w!\002\030\003\021\003y\023\001\002+fqR\004\"!\003\031\007\013\005\021\001\022A\031\024\007A\022T\007\005\002\017g%\021A\007\002\002\007\003:L(+\0324\021\00591\024BA\034\005\0051\031VM]5bY&T\030M\0317f\021\025A\002\007\"\001:)\005y\003\"B\0361\t\003a\024!B1qa2LHC\001\016>\021\025)\"\b1\001\r\021\025y\004\007\"\001A\003\035)h.\0319qYf$\"!\021#\021\0079\021E\"\003\002D\t\t1q\n\035;j_:DQ!\022 A\002\031\013Qa\034;iKJ\004\"AD$\n\005!#!aA!os\"9!\nMA\001\n\023Y\025a\003:fC\022\024Vm]8mm\026$\022\001\024\t\003\033Jk\021A\024\006\003\037B\013A\001\\1oO*\t\021+\001\003kCZ\f\027BA*O\005\031y%M[3di\002")
/*    */ public class Text extends Atom<String> {
/*    */   public Text(String data) {
/* 17 */     super(data);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 23 */     return Utility$.MODULE$.escape((String)data(), sb);
/*    */   }
/*    */   
/*    */   public static Option<String> unapply(Object paramObject) {
/*    */     return Text$.MODULE$.unapply(paramObject);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Text.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */