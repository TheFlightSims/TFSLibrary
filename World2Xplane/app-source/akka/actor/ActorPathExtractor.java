/*     */ package akka.actor;
/*     */ 
/*     */ import java.net.URI;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001U:Q!\001\002\t\002\035\t!#Q2u_J\004\026\r\0365FqR\024\030m\031;pe*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021!#Q2u_J\004\026\r\0365FqR\024\030m\031;peN\031\021\002\004\n\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g!\tA1#\003\002\025\005\tI\001+\031;i+RLGn\035\005\006-%!\taF\001\007y%t\027\016\036 \025\003\035AQ!G\005\005\002i\tq!\0368baBd\027\020\006\002\034gA\031Q\002\b\020\n\005uq!AB(qi&|g\016\005\003\016?\005\"\023B\001\021\017\005\031!V\017\0357feA\021\001BI\005\003G\t\021q!\0213ee\026\0348\017E\002&U1j\021A\n\006\003O!\n\021\"[7nkR\f'\r\\3\013\005%r\021AC2pY2,7\r^5p]&\0211F\n\002\t\023R,'/\0312mKB\021Q\006\r\b\003\0339J!a\f\b\002\rA\023X\rZ3g\023\t\t$G\001\004TiJLgn\032\006\003_9AQ\001\016\rA\0021\nA!\0313ee\002")
/*     */ public final class ActorPathExtractor {
/*     */   public static List<String> split(String paramString1, String paramString2) {
/*     */     return ActorPathExtractor$.MODULE$.split(paramString1, paramString2);
/*     */   }
/*     */   
/*     */   public static Option<Tuple2<Address, Iterable<String>>> unapply(String paramString) {
/*     */     return ActorPathExtractor$.MODULE$.unapply(paramString);
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
/* 157 */       return new Tuple2(x$3, ActorPathExtractor$.MODULE$.split(this.x1$1, this.uri$1.getRawFragment()).drop(1));
/*     */     }
/*     */     
/*     */     public ActorPathExtractor$$anonfun$unapply$1(URI uri$1, String x1$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorPathExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */