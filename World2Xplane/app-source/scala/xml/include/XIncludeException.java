/*    */ package scala.xml.include;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0013A!\001\002\001\023\t\t\002,\0238dYV$W-\022=dKB$\030n\0348\013\005\r!\021aB5oG2,H-\032\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001A\n\003\001)\001\"aC\n\017\0051\tbBA\007\021\033\005q!BA\b\t\003\031a$o\\8u}%\tq!\003\002\023\r\0059\001/Y2lC\036,\027B\001\013\026\005%)\005pY3qi&|gN\003\002\023\r!Aq\003\001B\001B\003%\001$A\004nKN\034\030mZ3\021\005eibB\001\016\034\033\0051\021B\001\017\007\003\031\001&/\0323fM&\021ad\b\002\007'R\024\030N\\4\013\005q1\001\"B\021\001\t\003\021\023A\002\037j]&$h\b\006\002$KA\021A\005A\007\002\005!)q\003\ta\0011!)\021\005\001C\001OQ\t1\005C\004*\001\001\007I\021\002\026\002\023I|w\016^\"bkN,W#A\026\021\005-a\023BA\027\026\005%!\006N]8xC\ndW\rC\0040\001\001\007I\021\002\031\002\033I|w\016^\"bkN,w\fJ3r)\t\tD\007\005\002\033e%\0211G\002\002\005+:LG\017C\0046]\005\005\t\031A\026\002\007a$\023\007\003\0048\001\001\006KaK\001\013e>|GoQ1vg\026\004\003\"B\035\001\t\003Q\024\001D:fiJ{w\016^\"bkN,GCA\031<\021\025a\004\b1\001,\003=qWm\035;fI\026C8-\0329uS>t\007\"\002 \001\t\003y\024\001D4fiJ{w\016^\"bkN,G#A\026")
/*    */ public class XIncludeException extends Exception {
/*    */   private Throwable rootCause;
/*    */   
/*    */   public XIncludeException(String message) {
/* 23 */     super(message);
/* 30 */     this.rootCause = null;
/*    */   }
/*    */   
/*    */   public XIncludeException() {
/*    */     this(null);
/*    */   }
/*    */   
/*    */   private Throwable rootCause() {
/* 30 */     return this.rootCause;
/*    */   }
/*    */   
/*    */   private void rootCause_$eq(Throwable x$1) {
/* 30 */     this.rootCause = x$1;
/*    */   }
/*    */   
/*    */   public void setRootCause(Throwable nestedException) {
/* 42 */     rootCause_$eq(nestedException);
/*    */   }
/*    */   
/*    */   public Throwable getRootCause() {
/* 55 */     return rootCause();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\XIncludeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */