/*    */ package scala;
/*    */ 
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00153q!\001\002\021\002\007\005QAA\002BaBT\021aA\001\006g\016\fG.Y\002\001'\r\001aA\003\t\003\017!i\021AA\005\003\023\t\021a!\0218z%\0264\007CA\004\f\023\ta!AA\006EK2\f\0270\0323J]&$\b\"\002\b\001\t\003y\021A\002\023j]&$H\005F\001\021!\t9\021#\003\002\023\005\t!QK\\5u\021\035!\002A1A\005\002U\ta\"\032=fGV$\030n\0348Ti\006\024H/F\001\027!\t9q#\003\002\031\005\t!Aj\0348h\021\031Q\002\001)A\005-\005yQ\r_3dkRLwN\\*uCJ$\b\005C\003\035\001\021EQ$\001\003be\036\034X#\001\020\021\007\035y\022%\003\002!\005\t)\021I\035:bsB\021!%\n\b\003\017\rJ!\001\n\002\002\rA\023X\rZ3g\023\t1sE\001\004TiJLgn\032\006\003I\tA\021\"\013\001A\002\003\007I\021B\017\002\013}\013'oZ:\t\023-\002\001\031!a\001\n\023a\023!C0be\036\034x\fJ3r)\t\001R\006C\004/U\005\005\t\031\001\020\002\007a$\023\007\003\0041\001\001\006KAH\001\007?\006\024xm\035\021\t\017I\002!\031!C\005g\005A\021N\\5u\007>$W-F\0015!\r)$\bP\007\002m)\021q\007O\001\b[V$\030M\0317f\025\tI$!\001\006d_2dWm\031;j_:L!a\017\034\003\0251K7\017\036\"vM\032,'\017E\002\b{AI!A\020\002\003\023\031+hn\031;j_:\004\004B\002!\001A\003%A'A\005j]&$8i\0343fA!)!\t\001C!\007\006YA-\0327bs\026$\027J\\5u)\t\001B\t\003\004F\003\022\005\rAR\001\005E>$\027\020E\002\b\017BI!\001\023\002\003\021q\022\027P\\1nKzBQA\023\001\005\002-\013A!\\1j]R\021\001\003\024\005\0069%\003\rA\b")
/*    */ public interface App extends DelayedInit {
/*    */   void scala$App$_setter_$executionStart_$eq(long paramLong);
/*    */   
/*    */   void scala$App$_setter_$scala$App$$initCode_$eq(ListBuffer paramListBuffer);
/*    */   
/*    */   long executionStart();
/*    */   
/*    */   String[] args();
/*    */   
/*    */   String[] scala$App$$_args();
/*    */   
/*    */   @TraitSetter
/*    */   void scala$App$$_args_$eq(String[] paramArrayOfString);
/*    */   
/*    */   ListBuffer<Function0<BoxedUnit>> scala$App$$initCode();
/*    */   
/*    */   void delayedInit(Function0<BoxedUnit> paramFunction0);
/*    */   
/*    */   void main(String[] paramArrayOfString);
/*    */   
/*    */   public class App$$anonfun$main$1 extends AbstractFunction1<Function0<BoxedUnit>, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Function0 proc) {
/* 71 */       proc.apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public App$$anonfun$main$1(App $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\App.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */