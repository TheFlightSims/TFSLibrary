/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class Text$ implements Serializable {
/*    */   public static final Text$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Text$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Text apply(String data) {
/* 33 */     return new Text(data);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(Object other) {
/*    */     scala.None$ none$;
/* 34 */     if (other instanceof Text) {
/* 34 */       Text text = (Text)other;
/* 34 */       Some some = new Some(text.data());
/*    */     } else {
/* 36 */       none$ = scala.None$.MODULE$;
/*    */     } 
/*    */     return (Option<String>)none$;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Text$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */