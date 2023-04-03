/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import org.xml.sax.SAXParseException;
/*    */ import scala.Console$;
/*    */ import scala.Function0;
/*    */ 
/*    */ public abstract class ConsoleErrorHandler$class {
/*    */   public static void $init$(ConsoleErrorHandler $this) {}
/*    */   
/*    */   public static void warning(ConsoleErrorHandler $this, SAXParseException ex) {}
/*    */   
/*    */   public static void error(ConsoleErrorHandler $this, SAXParseException ex) {
/* 21 */     $this.printError("Error", ex);
/*    */   }
/*    */   
/*    */   public static void fatalError(ConsoleErrorHandler $this, SAXParseException ex) {
/* 22 */     $this.printError("Fatal Error", ex);
/*    */   }
/*    */   
/*    */   public static void printError(ConsoleErrorHandler $this, String errtype, SAXParseException ex) {
/* 25 */     Console$.MODULE$.withOut(Console$.MODULE$.err(), (Function0)new ConsoleErrorHandler$$anonfun$printError$1($this, errtype, ex));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ConsoleErrorHandler$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */