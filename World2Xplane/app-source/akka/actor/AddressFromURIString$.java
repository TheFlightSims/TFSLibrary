/*     */ package akka.actor;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class AddressFromURIString$ {
/*     */   public static final AddressFromURIString$ MODULE$;
/*     */   
/*     */   private AddressFromURIString$() {
/* 118 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Option<Address> unapply(String addr) {
/*     */     try {
/*     */     
/* 119 */     } catch (URISyntaxException uRISyntaxException) {}
/* 119 */     return (Option<Address>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Address> unapply(URI uri) {
/* 122 */     return (uri == null) ? (Option<Address>)scala.None$.MODULE$ : (
/* 123 */       (uri.getScheme() == null || (uri.getUserInfo() == null && uri.getHost() == null)) ? (Option<Address>)scala.None$.MODULE$ : (
/* 124 */       (uri.getUserInfo() == null) ? (
/* 125 */       (uri.getPort() != -1) ? (Option<Address>)scala.None$.MODULE$ : 
/* 126 */       (Option<Address>)new Some(Address$.MODULE$.apply(uri.getScheme(), uri.getHost()))) : (
/*     */       
/* 128 */       (uri.getHost() == null || uri.getPort() == -1) ? (Option<Address>)scala.None$.MODULE$ : 
/* 129 */       (Option<Address>)new Some(
/* 130 */         (uri.getUserInfo() == null) ? Address$.MODULE$.apply(uri.getScheme(), uri.getHost()) : 
/* 131 */         Address$.MODULE$.apply(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort())))));
/*     */   }
/*     */   
/*     */   public Address apply(String addr) {
/* 137 */     String str = addr;
/* 138 */     Option<Address> option = unapply(str);
/* 138 */     if (option.isEmpty())
/* 139 */       throw new MalformedURLException(addr); 
/*     */     Address address;
/*     */     return address = (Address)option.get();
/*     */   }
/*     */   
/*     */   public Address parse(String addr) {
/* 145 */     return apply(addr);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AddressFromURIString$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */