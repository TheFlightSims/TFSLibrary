/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.util.control.ControlThrowable;
/*    */ import scala.util.control.NoStackTrace;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0213A!\001\002\001\017\t)bj\0348M_\016\fGNU3ukJt7i\0348ue>d'BA\002\005\003\035\021XO\034;j[\026T\021!B\001\006g\016\fG.Y\002\001+\tA1fE\002\001\023U\001\"A\003\n\017\005-\001bB\001\007\020\033\005i!B\001\b\007\003\031a$o\\8u}%\tQ!\003\002\022\t\0059\001/Y2lC\036,\027BA\n\025\005%!\006N]8xC\ndWM\003\002\022\tA\021acG\007\002/)\021\001$G\001\bG>tGO]8m\025\tQB!\001\003vi&d\027B\001\017\030\005A\031uN\034;s_2$\006N]8xC\ndW\r\003\005\037\001\t\025\r\021\"\001 \003\rYW-_\013\002AA\021\021EI\007\002\t%\0211\005\002\002\007\003:L(+\0324\t\021\025\002!\021!Q\001\n\001\nAa[3zA!Aq\005\001BC\002\023\005\001&A\003wC2,X-F\001*!\tQ3\006\004\001\005\0231\002\001\025!A\001\006\004i#!\001+\022\0059\n\004CA\0210\023\t\001DAA\004O_RD\027N\\4\021\005\005\022\024BA\032\005\005\r\te.\037\025\003WU\002\"!\t\034\n\005]\"!aC:qK\016L\027\r\\5{K\022D\001\"\017\001\003\002\003\006I!K\001\007m\006dW/\032\021\t\013m\002A\021\001\037\002\rqJg.\033;?)\rit\b\021\t\004}\001IS\"\001\002\t\013yQ\004\031\001\021\t\013\035R\004\031A\025\t\013\t\003AQI\"\002!\031LG\016\\%o'R\f7m\033+sC\016,G#A\005")
/*    */ public class NonLocalReturnControl<T> extends Throwable implements ControlThrowable {
/*    */   private final Object key;
/*    */   
/*    */   public final T value;
/*    */   
/*    */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 13 */     return super.fillInStackTrace();
/*    */   }
/*    */   
/*    */   public Object key() {
/* 13 */     return this.key;
/*    */   }
/*    */   
/*    */   public T value() {
/* 13 */     return this.value;
/*    */   }
/*    */   
/*    */   public boolean value$mcZ$sp() {
/* 13 */     return BoxesRunTime.unboxToBoolean(value());
/*    */   }
/*    */   
/*    */   public byte value$mcB$sp() {
/* 13 */     return BoxesRunTime.unboxToByte(value());
/*    */   }
/*    */   
/*    */   public char value$mcC$sp() {
/* 13 */     return BoxesRunTime.unboxToChar(value());
/*    */   }
/*    */   
/*    */   public double value$mcD$sp() {
/* 13 */     return BoxesRunTime.unboxToDouble(value());
/*    */   }
/*    */   
/*    */   public float value$mcF$sp() {
/* 13 */     return BoxesRunTime.unboxToFloat(value());
/*    */   }
/*    */   
/*    */   public int value$mcI$sp() {
/* 13 */     return BoxesRunTime.unboxToInt(value());
/*    */   }
/*    */   
/*    */   public long value$mcJ$sp() {
/* 13 */     return BoxesRunTime.unboxToLong(value());
/*    */   }
/*    */   
/*    */   public short value$mcS$sp() {
/* 13 */     return BoxesRunTime.unboxToShort(value());
/*    */   }
/*    */   
/*    */   public void value$mcV$sp() {
/* 13 */     value();
/*    */   }
/*    */   
/*    */   public boolean specInstance$() {
/* 13 */     return false;
/*    */   }
/*    */   
/*    */   public NonLocalReturnControl(Object key, Object value) {
/* 13 */     NoStackTrace.class.$init$((NoStackTrace)this);
/*    */   }
/*    */   
/*    */   public final Throwable fillInStackTrace() {
/* 14 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\NonLocalReturnControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */