/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class SystemID$ extends AbstractFunction1<String, SystemID> implements Serializable {
/*    */   public static final SystemID$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 46 */     return "SystemID";
/*    */   }
/*    */   
/*    */   public SystemID apply(String systemId) {
/* 46 */     return new SystemID(systemId);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(SystemID x$0) {
/* 46 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.systemId());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 46 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private SystemID$() {
/* 46 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\SystemID$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */