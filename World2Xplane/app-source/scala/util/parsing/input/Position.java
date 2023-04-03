/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}2q!\001\002\021\002\007\0051B\001\005Q_NLG/[8o\025\t\031A!A\003j]B,HO\003\002\006\r\0059\001/\031:tS:<'BA\004\t\003\021)H/\0337\013\003%\tQa]2bY\006\034\001a\005\002\001\031A\021QBD\007\002\021%\021q\002\003\002\007\003:L(+\0324\t\013E\001A\021\001\n\002\r\021Jg.\033;%)\005\031\002CA\007\025\023\t)\002B\001\003V]&$\b\"B\f\001\r\003A\022\001\0027j]\026,\022!\007\t\003\033iI!a\007\005\003\007%sG\017C\003\036\001\031\005\001$\001\004d_2,XN\034\005\006?\0011\t\002I\001\rY&tWmQ8oi\026tGo]\013\002CA\021!%\n\b\003\033\rJ!\001\n\005\002\rA\023X\rZ3g\023\t1sE\001\004TiJLgn\032\006\003I!AQ!\013\001\005B)\n\001\002^8TiJLgn\032\013\002WA\021A&M\007\002[)\021afL\001\005Y\006twMC\0011\003\021Q\027M^1\n\005\031j\003\"B\032\001\t\003!\024A\0037p]\036\034FO]5oOV\t1\006C\0037\001\021\005q'A\003%Y\026\0348\017\006\0029wA\021Q\"O\005\003u!\021qAQ8pY\026\fg\016C\003=k\001\007Q(\001\003uQ\006$\bC\001 \001\033\005\021\001")
/*    */ public interface Position {
/*    */   int line();
/*    */   
/*    */   int column();
/*    */   
/*    */   String lineContents();
/*    */   
/*    */   String toString();
/*    */   
/*    */   String longString();
/*    */   
/*    */   boolean $less(Position paramPosition);
/*    */   
/*    */   public class Position$$anonfun$longString$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final char apply(char x) {
/* 48 */       return (x == '\t') ? x : ' ';
/*    */     }
/*    */     
/*    */     public Position$$anonfun$longString$1(Position $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\Position.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */