/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class AddressTerminated$ extends AbstractFunction1<Address, AddressTerminated> implements Serializable {
/*     */   public static final AddressTerminated$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 110 */     return "AddressTerminated";
/*     */   }
/*     */   
/*     */   public AddressTerminated apply(Address address) {
/* 110 */     return new AddressTerminated(address);
/*     */   }
/*     */   
/*     */   public Option<Address> unapply(AddressTerminated x$0) {
/* 110 */     return (x$0 == null) ? (Option<Address>)scala.None$.MODULE$ : (Option<Address>)new Some(x$0.address());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 110 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private AddressTerminated$() {
/* 110 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AddressTerminated$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */