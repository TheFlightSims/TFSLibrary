/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BooleanRef;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U3\001\"\001\002\021\002\007\005qA\024\002\r-&,w/T6TiJLgn\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\003\021q\031\"\001A\005\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\rC\003\017\001\021\005q\"\001\004%S:LG\017\n\013\002!A\021!\"E\005\003%\021\021A!\0268ji\"1A\003\001Q\005\022U\tq\001\0365jgN+\027/F\001\027!\r9\002DG\007\002\005%\021\021D\001\002\004'\026\f\bCA\016\035\031\001!a!\b\001\005\006\004q\"!A!\022\005}\021\003C\001\006!\023\t\tCAA\004O_RD\027N\\4\021\005)\031\023B\001\023\005\005\r\te.\037\005\006M\001!\teJ\001\t[.\034FO]5oOV\t\001\006\005\002*Y9\021!BK\005\003W\021\ta\001\025:fI\0264\027BA\027/\005\031\031FO]5oO*\0211\006\002\005\006M\001!\t\005\r\013\003QEBQAM\030A\002!\n1a]3q\021\0251\003\001\"\0215)\021ASg\016\035\t\013Y\032\004\031\001\025\002\013M$\030M\035;\t\013I\032\004\031\001\025\t\013e\032\004\031\001\025\002\007\025tG\rC\003<\001\021\005C(A\005bI\022\034FO]5oOR)Q(S&M\033B\021aH\022\b\003\021s!\001Q\"\016\003\005S!A\021\004\002\rq\022xn\034;?\023\005)\021BA#\005\003\035\001\030mY6bO\026L!a\022%\003\033M#(/\0338h\005VLG\016Z3s\025\t)E\001C\003Ku\001\007Q(A\001c\021\0251$\b1\001)\021\025\021$\b1\001)\021\025I$\b1\001)%\ry\025K\025\004\005!\002\001aJ\001\007=e\0264\027N\\3nK:$h\bE\002\030\001i\0012aF*\033\023\t!&AA\006Ue\0064XM]:bE2,\007")
/*    */ public interface ViewMkString<A> {
/*    */   Seq<A> thisSeq();
/*    */   
/*    */   String mkString();
/*    */   
/*    */   String mkString(String paramString);
/*    */   
/*    */   String mkString(String paramString1, String paramString2, String paramString3);
/*    */   
/*    */   StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
/*    */   
/*    */   public class ViewMkString$$anonfun$addString$1 extends AbstractFunction1<A, StringBuilder> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final BooleanRef first$1;
/*    */     
/*    */     private final StringBuilder b$1;
/*    */     
/*    */     private final String sep$1;
/*    */     
/*    */     public ViewMkString$$anonfun$addString$1(ViewMkString $outer, BooleanRef first$1, StringBuilder b$1, String sep$1) {}
/*    */     
/*    */     public final StringBuilder apply(Object x) {
/* 35 */       this.first$1.elem = false;
/* 35 */       this.first$1.elem ? BoxedUnit.UNIT : this.b$1.append(this.sep$1);
/* 36 */       return this.b$1.append(x);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\ViewMkString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */