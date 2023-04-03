/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.Timeout;
/*     */ import scala.concurrent.Future;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001=4A!\001\002\003\017\ty\021i]6bE2,\027i\031;peJ+gM\003\002\004\t\0059\001/\031;uKJt'\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f4\026\r\034\005\t\037\001\021)\031!C\001!\005A\021m\031;peJ+g-F\001\022!\t\021R#D\001\024\025\t!B!A\003bGR|'/\003\002\027'\tA\021i\031;peJ+g\r\003\005\031\001\t\005\t\025!\003\022\003%\t7\r^8s%\0264\007\005C\003\033\001\021\0051$\001\004=S:LGO\020\013\0039y\001\"!\b\001\016\003\tAQaD\rA\002EAQ\001\t\001\005\002\005\n1!Y:l)\t\021C\007\006\002$YA\031AeJ\025\016\003\025R!A\n\006\002\025\r|gnY;se\026tG/\003\002)K\t1a)\036;ve\026\004\"!\003\026\n\005-R!aA!os\")Qf\ba\002]\0059A/[7f_V$\bCA\0303\033\005\001$BA\031\005\003\021)H/\0337\n\005M\002$a\002+j[\026|W\017\036\005\006k}\001\r!K\001\b[\026\0348/Y4f\021\0259\004\001\"\0019\003\031!\023/\\1sWR\021\021h\017\013\003GiBQ!\f\034A\0049BQ!\016\034A\002%Bq!\020\001\002\002\023\005c(\001\005iCND7i\0343f)\005y\004CA\005A\023\t\t%BA\002J]RDqa\021\001\002\002\023\005C)\001\004fcV\fGn\035\013\003\013\"\003\"!\003$\n\005\035S!a\002\"p_2,\027M\034\005\b\023\n\013\t\0211\001*\003\rAH%M\004\b\027\n\t\t\021#\001M\003=\t5o[1cY\026\f5\r^8s%\0264\007CA\017N\r\035\t!!!A\t\0029\033\"!T(\021\005%\001\026BA)\013\005\031\te.\037*fM\")!$\024C\001'R\tA\nC\003V\033\022\025a+A\007bg.$S\r\037;f]NLwN\034\013\003/n#\"\001\027.\025\005\rJ\006\"B\027U\001\bq\003\"B\033U\001\004I\003\"\002/U\001\004a\022!\002\023uQ&\034\b\"\0020N\t\013y\026\001\005\023r[\006\0248\016J3yi\026t7/[8o)\t\001G\r\006\002bGR\0211E\031\005\006[u\003\035A\f\005\006ku\003\r!\013\005\0069v\003\r\001\b\005\bM6\013\t\021\"\002h\003IA\027m\0355D_\022,G%\032=uK:\034\030n\0348\025\005yB\007\"\002/f\001\004a\002b\0026N\003\003%)a[\001\021KF,\030\r\\:%Kb$XM\\:j_:$\"\001\0348\025\005\025k\007bB%j\003\003\005\r!\013\005\0069&\004\r\001\b")
/*     */ public final class AskableActorRef {
/*     */   private final ActorRef actorRef;
/*     */   
/*     */   public ActorRef actorRef() {
/* 127 */     return this.actorRef;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 127 */     return AskableActorRef$.MODULE$.hashCode$extension(actorRef());
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 127 */     return AskableActorRef$.MODULE$.equals$extension(actorRef(), x$1);
/*     */   }
/*     */   
/*     */   public AskableActorRef(ActorRef actorRef) {}
/*     */   
/*     */   public Future<Object> ask(Object message, Timeout timeout) {
/* 129 */     return AskableActorRef$.MODULE$.ask$extension(actorRef(), message, timeout);
/*     */   }
/*     */   
/*     */   public Future<Object> $qmark(Object message, Timeout timeout) {
/* 144 */     return AskableActorRef$.MODULE$.$qmark$extension(actorRef(), message, timeout);
/*     */   }
/*     */   
/*     */   public static boolean equals$extension(ActorRef paramActorRef, Object paramObject) {
/*     */     return AskableActorRef$.MODULE$.equals$extension(paramActorRef, paramObject);
/*     */   }
/*     */   
/*     */   public static int hashCode$extension(ActorRef paramActorRef) {
/*     */     return AskableActorRef$.MODULE$.hashCode$extension(paramActorRef);
/*     */   }
/*     */   
/*     */   public static Future<Object> $qmark$extension(ActorRef paramActorRef, Object paramObject, Timeout paramTimeout) {
/*     */     return AskableActorRef$.MODULE$.$qmark$extension(paramActorRef, paramObject, paramTimeout);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask$extension(ActorRef paramActorRef, Object paramObject, Timeout paramTimeout) {
/*     */     return AskableActorRef$.MODULE$.ask$extension(paramActorRef, paramObject, paramTimeout);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskableActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */