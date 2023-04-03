/*     */ package akka.actor;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ActorPathExtractor$ implements PathUtils {
/*     */   public static final ActorPathExtractor$ MODULE$;
/*     */   
/*     */   public List<String> split(String s, String fragment) {
/* 151 */     return PathUtils$class.split(this, s, fragment);
/*     */   }
/*     */   
/*     */   private ActorPathExtractor$() {
/* 151 */     MODULE$ = this;
/* 151 */     PathUtils$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Address, Iterable<String>>> unapply(String addr) {
/*     */     try {
/*     */       Option option;
/* 154 */       URI uri = new URI(addr);
/* 155 */       String str = uri.getRawPath();
/* 156 */       if (str == null) {
/* 156 */         scala.None$ none$ = scala.None$.MODULE$;
/*     */       } else {
/* 157 */         option = AddressFromURIString$.MODULE$.unapply(uri).map((Function1)new ActorPathExtractor$$anonfun$unapply$1(uri, str));
/*     */       } 
/*     */     } catch (URISyntaxException uRISyntaxException) {}
/* 160 */     return (Option<Tuple2<Address, Iterable<String>>>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public static class ActorPathExtractor$$anonfun$unapply$1 extends AbstractFunction1<Address, Tuple2<Address, List<String>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final URI uri$1;
/*     */     
/*     */     private final String x1$1;
/*     */     
/*     */     public final Tuple2<Address, List<String>> apply(Address x$3) {
/*     */       return new Tuple2(x$3, ActorPathExtractor$.MODULE$.split(this.x1$1, this.uri$1.getRawFragment()).drop(1));
/*     */     }
/*     */     
/*     */     public ActorPathExtractor$$anonfun$unapply$1(URI uri$1, String x1$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorPathExtractor$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */