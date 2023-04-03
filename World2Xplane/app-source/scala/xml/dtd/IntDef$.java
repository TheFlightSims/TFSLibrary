/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class IntDef$ extends AbstractFunction1<String, IntDef> implements Serializable {
/*    */   public static final IntDef$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 91 */     return "IntDef";
/*    */   }
/*    */   
/*    */   public IntDef apply(String value) {
/* 91 */     return new IntDef(value);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(IntDef x$0) {
/* 91 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.value());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 91 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private IntDef$() {
/* 91 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\IntDef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */