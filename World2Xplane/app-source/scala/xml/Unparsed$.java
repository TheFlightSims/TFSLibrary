/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class Unparsed$ implements Serializable {
/*    */   public static final Unparsed$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Unparsed$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Unparsed apply(String data) {
/* 33 */     return new Unparsed(data);
/*    */   }
/*    */   
/*    */   public Some<String> unapply(Unparsed x) {
/* 34 */     return new Some(x.data());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Unparsed$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */