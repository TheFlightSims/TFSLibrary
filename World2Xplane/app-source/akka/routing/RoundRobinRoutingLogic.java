/*    */ package akka.routing;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001);Q!\001\002\t\002\035\taCU8v]\022\024vNY5o%>,H/\0338h\031><\027n\031\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t1\"k\\;oIJ{'-\0338S_V$\030N\\4M_\036L7m\005\002\n\031A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032DQaE\005\005\002Q\ta\001P5oSRtD#A\004\t\013YIA\021A\f\002\013\005\004\b\017\\=\025\003a\001\"\001C\r\007\t)\021!AG\n\00431Y\002C\001\005\035\023\ti\"A\001\007S_V$\030N\\4M_\036L7\rC\003\0243\021\005q\003C\004!3\t\007I\021A\021\002\t9,\007\020^\013\002EA\0211\005L\007\002I)\021QEJ\001\007CR|W.[2\013\005\035B\023AC2p]\016,(O]3oi*\021\021FK\001\005kRLGNC\001,\003\021Q\027M^1\n\0055\"#AC!u_6L7\rT8oO\"1q&\007Q\001\n\t\nQA\\3yi\002BQ!M\r\005BI\naa]3mK\016$HcA\0327wA\021\001\002N\005\003k\t\021aAU8vi\026,\007\"B\0341\001\004A\024aB7fgN\fw-\032\t\003\033eJ!A\017\b\003\007\005s\027\020C\003=a\001\007Q(A\004s_V$X-Z:\021\007y\0325'D\001@\025\t\001\025)A\005j[6,H/\0312mK*\021!ID\001\013G>dG.Z2uS>t\027B\001#@\005)Ie\016Z3yK\022\034V-\035\025\0043\031K\005CA\007H\023\tAeB\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021\001")
/*    */ public final class RoundRobinRoutingLogic implements RoutingLogic {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/* 26 */   private final AtomicLong next = new AtomicLong(0L);
/*    */   
/*    */   public static RoundRobinRoutingLogic apply() {
/*    */     return RoundRobinRoutingLogic$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public AtomicLong next() {
/* 26 */     return this.next;
/*    */   }
/*    */   
/*    */   public Routee select(Object message, IndexedSeq routees) {
/* 29 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/* 30 */       (Routee)routees.apply((int)(next().getAndIncrement() % routees.size()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */