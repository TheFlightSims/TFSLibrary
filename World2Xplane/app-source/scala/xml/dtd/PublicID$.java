/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class PublicID$ extends AbstractFunction2<String, String, PublicID> implements Serializable {
/*    */   public static final PublicID$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 60 */     return "PublicID";
/*    */   }
/*    */   
/*    */   public PublicID apply(String publicId, String systemId) {
/* 60 */     return new PublicID(publicId, systemId);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, String>> unapply(PublicID x$0) {
/* 60 */     return (x$0 == null) ? (Option<Tuple2<String, String>>)scala.None$.MODULE$ : (Option<Tuple2<String, String>>)new Some(new Tuple2(x$0.publicId(), x$0.systemId()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 60 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private PublicID$() {
/* 60 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\PublicID$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */