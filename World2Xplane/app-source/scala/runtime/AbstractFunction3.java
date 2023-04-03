/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function3;
/*    */ import scala.Tuple3;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001%2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03484\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!F\003\t%qy\"eE\002\001\0235\001\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\031Qa\002E\016\037C%\021q\002\002\002\n\rVt7\r^5p]N\002\"!\005\n\r\001\02111\003\001EC\002Q\021!\001V\031\022\005UA\002C\001\006\027\023\t9BAA\004O_RD\027N\\4\021\005)I\022B\001\016\005\005\r\te.\037\t\003#q!a!\b\001\t\006\004!\"A\001+3!\t\tr\004\002\004!\001!\025\r\001\006\002\003)N\002\"!\005\022\005\r\r\002AQ1\001\025\005\005\021\006\"B\023\001\t\0031\023A\002\037j]&$h\bF\001(!\031A\003\001E\016\037C5\t!\001")
/*    */ public abstract class AbstractFunction3<T1, T2, T3, R> implements Function3<T1, T2, T3, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, R>>> curried() {
/* 12 */     return Function3.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple3<T1, T2, T3>, R> tupled() {
/* 12 */     return Function3.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function3.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction3() {
/* 12 */     Function3.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */