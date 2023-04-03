/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ public final class OffsetPosition$ extends AbstractFunction2<CharSequence, Object, OffsetPosition> implements Serializable {
/*    */   public static final OffsetPosition$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 21 */     return "OffsetPosition";
/*    */   }
/*    */   
/*    */   public OffsetPosition apply(CharSequence source, int offset) {
/* 21 */     return new OffsetPosition(source, offset);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<CharSequence, Object>> unapply(OffsetPosition x$0) {
/* 21 */     return (x$0 == null) ? (Option<Tuple2<CharSequence, Object>>)scala.None$.MODULE$ : (Option<Tuple2<CharSequence, Object>>)new Some(new Tuple2(x$0.source(), BoxesRunTime.boxToInteger(x$0.offset())));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private OffsetPosition$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class OffsetPosition$$anonfun$index$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ObjectRef lineStarts$1;
/*    */     
/*    */     public OffsetPosition$$anonfun$index$1(OffsetPosition $outer, ObjectRef lineStarts$1) {}
/*    */     
/*    */     public final Object apply(int i) {
/* 28 */       return (this.$outer.source().charAt(i) == '\n') ? ((ArrayBuffer)this.lineStarts$1.elem).$plus$eq(BoxesRunTime.boxToInteger(i + 1)) : BoxedUnit.UNIT;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\OffsetPosition$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */