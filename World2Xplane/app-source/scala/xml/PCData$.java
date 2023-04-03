/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class PCData$ implements Serializable {
/*    */   public static final PCData$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 36 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private PCData$() {
/* 36 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public PCData apply(String data) {
/* 37 */     return new PCData(data);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(Object other) {
/*    */     scala.None$ none$;
/* 38 */     if (other instanceof PCData) {
/* 38 */       PCData pCData = (PCData)other;
/* 38 */       Some some = new Some(pCData.data());
/*    */     } else {
/* 40 */       none$ = scala.None$.MODULE$;
/*    */     } 
/*    */     return (Option<String>)none$;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\PCData$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */