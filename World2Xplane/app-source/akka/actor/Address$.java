/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple4;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Address$ implements Serializable {
/*    */   public static final Address$ MODULE$;
/*    */   
/*    */   public Address apply(String protocol, String system, Option<String> host, Option<Object> port) {
/* 21 */     return new Address(protocol, system, host, port);
/*    */   }
/*    */   
/*    */   public Option<Tuple4<String, String, Option<String>, Option<Object>>> unapply(Address x$0) {
/* 21 */     return (x$0 == null) ? (Option<Tuple4<String, String, Option<String>, Option<Object>>>)scala.None$.MODULE$ : (Option<Tuple4<String, String, Option<String>, Option<Object>>>)new Some(new Tuple4(x$0.protocol(), x$0.system(), x$0.host(), x$0.port()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 69 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Address$() {
/* 69 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Address apply(String protocol, String system) {
/* 73 */     return new Address(protocol, system);
/*    */   }
/*    */   
/*    */   public Address apply(String protocol, String system, String host, int port) {
/* 78 */     return new Address(protocol, system, (Option<String>)new Some(host), (Option<Object>)new Some(BoxesRunTime.boxToInteger(port)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Address$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */