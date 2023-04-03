/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.util.Timeout;
/*     */ import scala.concurrent.Future;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001=4A!\001\002\003\017\t)\022i]6bE2,\027i\031;peN+G.Z2uS>t'BA\002\005\003\035\001\030\r\036;fe:T\021!B\001\005C.\\\027m\001\001\024\005\001A\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osZ\013G\016\003\005\020\001\t\025\r\021\"\001\021\003!\t7\r^8s'\026dW#A\t\021\005I)R\"A\n\013\005Q!\021!B1di>\024\030B\001\f\024\0059\t5\r^8s'\026dWm\031;j_:D\001\002\007\001\003\002\003\006I!E\001\nC\016$xN]*fY\002BQA\007\001\005\002m\ta\001P5oSRtDC\001\017\037!\ti\002!D\001\003\021\025y\021\0041\001\022\021\025\001\003\001\"\001\"\003\r\t7o\033\013\003EQ\"\"a\t\027\021\007\021:\023&D\001&\025\t1#\"\001\006d_:\034WO\035:f]RL!\001K\023\003\r\031+H/\036:f!\tI!&\003\002,\025\t\031\021I\\=\t\0135z\0029\001\030\002\017QLW.Z8viB\021qFM\007\002a)\021\021\007B\001\005kRLG.\003\0024a\t9A+[7f_V$\b\"B\033 \001\004I\023aB7fgN\fw-\032\005\006o\001!\t\001O\001\007IEl\027M]6\025\005eZDCA\022;\021\025ic\007q\001/\021\025)d\0071\001*\021\035i\004!!A\005By\n\001\002[1tQ\016{G-\032\013\002A\021\021\002Q\005\003\003*\0211!\0238u\021\035\031\005!!A\005B\021\013a!Z9vC2\034HCA#I!\tIa)\003\002H\025\t9!i\\8mK\006t\007bB%C\003\003\005\r!K\001\004q\022\ntaB&\003\003\003E\t\001T\001\026\003N\\\027M\0317f\003\016$xN]*fY\026\034G/[8o!\tiRJB\004\002\005\005\005\t\022\001(\024\0055{\005CA\005Q\023\t\t&B\001\004B]f\024VM\032\005\00655#\ta\025\013\002\031\")Q+\024C\003-\006i\021m]6%Kb$XM\\:j_:$\"aV.\025\005aSFCA\022Z\021\025iC\013q\001/\021\025)D\0131\001*\021\025aF\0131\001\035\003\025!C\017[5t\021\025qV\n\"\002`\003A!\023/\\1sW\022*\007\020^3og&|g\016\006\002aIR\021\021m\031\013\003G\tDQ!L/A\0049BQ!N/A\002%BQ\001X/A\002qAqAZ'\002\002\023\025q-\001\niCND7i\0343fI\025DH/\0328tS>tGC\001 i\021\025aV\r1\001\035\021\035QW*!A\005\006-\f\001#Z9vC2\034H%\032=uK:\034\030n\0348\025\0051tGCA#n\021\035I\025.!AA\002%BQ\001X5A\002q\001")
/*     */ public final class AskableActorSelection {
/*     */   private final ActorSelection actorSel;
/*     */   
/*     */   public ActorSelection actorSel() {
/* 150 */     return this.actorSel;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 150 */     return AskableActorSelection$.MODULE$.hashCode$extension(actorSel());
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 150 */     return AskableActorSelection$.MODULE$.equals$extension(actorSel(), x$1);
/*     */   }
/*     */   
/*     */   public AskableActorSelection(ActorSelection actorSel) {}
/*     */   
/*     */   public Future<Object> ask(Object message, Timeout timeout) {
/* 152 */     return AskableActorSelection$.MODULE$.ask$extension(actorSel(), message, timeout);
/*     */   }
/*     */   
/*     */   public Future<Object> $qmark(Object message, Timeout timeout) {
/* 165 */     return AskableActorSelection$.MODULE$.$qmark$extension(actorSel(), message, timeout);
/*     */   }
/*     */   
/*     */   public static boolean equals$extension(ActorSelection paramActorSelection, Object paramObject) {
/*     */     return AskableActorSelection$.MODULE$.equals$extension(paramActorSelection, paramObject);
/*     */   }
/*     */   
/*     */   public static int hashCode$extension(ActorSelection paramActorSelection) {
/*     */     return AskableActorSelection$.MODULE$.hashCode$extension(paramActorSelection);
/*     */   }
/*     */   
/*     */   public static Future<Object> $qmark$extension(ActorSelection paramActorSelection, Object paramObject, Timeout paramTimeout) {
/*     */     return AskableActorSelection$.MODULE$.$qmark$extension(paramActorSelection, paramObject, paramTimeout);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask$extension(ActorSelection paramActorSelection, Object paramObject, Timeout paramTimeout) {
/*     */     return AskableActorSelection$.MODULE$.ask$extension(paramActorSelection, paramObject, paramTimeout);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskableActorSelection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */