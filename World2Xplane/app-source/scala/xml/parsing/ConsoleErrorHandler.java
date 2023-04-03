/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import org.xml.sax.SAXParseException;
/*    */ import scala.Console$;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y2q!\001\002\021\002\007\005\021BA\nD_:\034x\016\\3FeJ|'\017S1oI2,'O\003\002\004\t\0059\001/\031:tS:<'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011C\001\001\013!\tY1#D\001\r\025\tia\"A\004iK2\004XM]:\013\005=\001\022aA:bq*\021Q!\005\006\002%\005\031qN]4\n\005Qa!A\004#fM\006,H\016\036%b]\022dWM\035\005\006-\001!\taF\001\007I%t\027\016\036\023\025\003a\001\"!\007\016\016\003\031I!a\007\004\003\tUs\027\016\036\005\006;\001!\tEH\001\bo\006\024h.\0338h)\tAr\004C\003!9\001\007\021%\001\002fqB\021!E\n\b\003G\021j\021\001B\005\003K\021\tq\001]1dW\006<W-\003\002(Q\t\t2+\021-QCJ\034X-\022=dKB$\030n\0348\013\005\025\"\001\"\002\026\001\t\003Z\023!B3se>\024HC\001\r-\021\025\001\023\0061\001\"\021\025q\003\001\"\0210\003)1\027\r^1m\013J\024xN\035\013\0031ABQ\001I\027A\002\005BQA\r\001\005\022M\n!\002\035:j]R,%O]8s)\rAB'\020\005\006kE\002\rAN\001\bKJ\024H/\0379f!\t9$H\004\002\032q%\021\021HB\001\007!J,G-\0324\n\005mb$AB*ue&twM\003\002:\r!)\001%\ra\001C\001")
/*    */ public interface ConsoleErrorHandler {
/*    */   void warning(SAXParseException paramSAXParseException);
/*    */   
/*    */   void error(SAXParseException paramSAXParseException);
/*    */   
/*    */   void fatalError(SAXParseException paramSAXParseException);
/*    */   
/*    */   void printError(String paramString, SAXParseException paramSAXParseException);
/*    */   
/*    */   public class ConsoleErrorHandler$$anonfun$printError$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String errtype$1;
/*    */     
/*    */     private final SAXParseException ex$1;
/*    */     
/*    */     public final void apply() {
/* 25 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public ConsoleErrorHandler$$anonfun$printError$1(ConsoleErrorHandler $outer, String errtype$1, SAXParseException ex$1) {}
/*    */     
/*    */     public void apply$mcV$sp() {
/* 26 */       Predef$ predef$ = Predef$.MODULE$;
/* 26 */       String s = (new StringOps("[%s]:%d:%d: %s")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.errtype$1, BoxesRunTime.boxToInteger(this.ex$1.getLineNumber()), BoxesRunTime.boxToInteger(this.ex$1.getColumnNumber()), this.ex$1.getMessage() }));
/* 28 */       Console$.MODULE$.println(s);
/* 29 */       Console$.MODULE$.flush();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ConsoleErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */