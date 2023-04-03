/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import scala.Function1;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\025;Q!\001\002\t\002\035\t\021\003\025:j_JLG/_$f]\026\024\030\r^8s\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\tQe&|'/\033;z\017\026tWM]1u_J\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\003\027\023\021\005q#A\003baBd\027\020\006\002\031\001B\021\001\"\007\004\006\025\t\t\tAG\n\0043m\031\003C\001\017\"\033\005i\"B\001\020 \003\021a\027M\\4\013\003\001\nAA[1wC&\021!%\b\002\007\037\nTWm\031;\021\007\021:\023&D\001&\025\t1s$\001\003vi&d\027B\001\025&\005)\031u.\0349be\006$xN\035\t\003\021)J!a\013\002\003\021\025sg/\0327pa\026DQaE\r\005\0025\"\022\001\007\005\006_e1\t\001M\001\004O\026tGCA\0315!\ti!'\003\0024\035\t\031\021J\034;\t\013Ur\003\031\001\034\002\0175,7o]1hKB\021QbN\005\003q9\0211!\0218z\021\025Q\024\004\"\002<\003\035\031w.\0349be\026$2!\r\037?\021\025i\024\b1\001*\003-!\b.[:NKN\034\030mZ3\t\013}J\004\031A\025\002\027QD\027\r^'fgN\fw-\032\005\006\003V\001\rAQ\001\021aJLwN]5us\032+hn\031;j_:\004B!D\"7c%\021AI\004\002\n\rVt7\r^5p]F\002")
/*     */ public abstract class PriorityGenerator implements Comparator<Envelope> {
/*     */   public static class PriorityGenerator$$anon$2 extends PriorityGenerator {
/*     */     private final Function1 priorityFunction$1;
/*     */     
/*     */     public PriorityGenerator$$anon$2(Function1 priorityFunction$1) {}
/*     */     
/*     */     public int gen(Object message) {
/* 144 */       return BoxesRunTime.unboxToInt(this.priorityFunction$1.apply(message));
/*     */     }
/*     */   }
/*     */   
/*     */   public final int compare(Envelope thisMessage, Envelope thatMessage) {
/* 156 */     return gen(thisMessage.message()) - gen(thatMessage.message());
/*     */   }
/*     */   
/*     */   public abstract int gen(Object paramObject);
/*     */   
/*     */   public static PriorityGenerator apply(Function1<Object, Object> paramFunction1) {
/*     */     return PriorityGenerator$.MODULE$.apply(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\PriorityGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */