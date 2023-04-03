/*     */ package akka.actor;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import scala.Option;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Seq;
/*     */ 
/*     */ public final class RelativeActorPath$ implements PathUtils {
/*     */   public static final RelativeActorPath$ MODULE$;
/*     */   
/*     */   public List<String> split(String s, String fragment) {
/* 103 */     return PathUtils$class.split(this, s, fragment);
/*     */   }
/*     */   
/*     */   private RelativeActorPath$() {
/* 103 */     MODULE$ = this;
/* 103 */     PathUtils$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Option<Seq<String>> unapply(String addr) {
/*     */     try {
/* 106 */       URI uri = new URI(addr);
/*     */     } catch (URISyntaxException uRISyntaxException) {}
/* 110 */     return (Option<Seq<String>>)scala.None$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\RelativeActorPath$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */