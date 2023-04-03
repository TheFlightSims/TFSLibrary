/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefWithCell;
/*     */ import akka.actor.Cell;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025q!B\001\003\021\0039\021aG*nC2dWm\035;NC&d'm\034=S_V$\030N\\4M_\036L7M\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005m\031V.\0317mKN$X*Y5mE>D(k\\;uS:<Gj\\4jGN\021\021\002\004\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\t\013MIA\021\001\013\002\rqJg.\033;?)\0059\001\"\002\f\n\t\0039\022!B1qa2LH#\001\r\021\005!Ib\001\002\006\003\001i\0312!\007\007\034!\tAA$\003\002\036\005\ta!k\\;uS:<Gj\\4jG\")1#\007C\001/!)\001%\007C!C\00511/\0327fGR$2AI\023+!\tA1%\003\002%\005\t1!k\\;uK\026DQAJ\020A\002\035\nq!\\3tg\006<W\r\005\002\016Q%\021\021F\004\002\004\003:L\b\"B\026 \001\004a\023a\002:pkR,Wm\035\t\004[I\022S\"\001\030\013\005=\002\024!C5n[V$\030M\0317f\025\t\td\"\001\006d_2dWm\031;j_:L!a\r\030\003\025%sG-\032=fIN+\027\017C\00363\021%a'\001\006tK2,7\r\036(fqR$bAI\034:w\001+\005\"\002\0355\001\004a\023a\002;be\036,Go\035\005\buQ\002\n\0211\001#\0039\001(o\0349pg\026$G+\031:hKRDq\001\020\033\021\002\003\007Q(\001\007dkJ\024XM\034;TG>\024X\r\005\002\016}%\021qH\004\002\005\031>tw\rC\004BiA\005\t\031\001\"\002\005\005$\bCA\007D\023\t!eBA\002J]RDqA\022\033\021\002\003\007q)\001\003eK\026\004\bCA\007I\023\tIeBA\004C_>dW-\0318)\005QZ\005C\001'P\033\005i%B\001(\017\003)\tgN\\8uCRLwN\\\005\003!6\023q\001^1jYJ,7\rC\003S3\021E1+\001\007jgR+'/\\5oCR,G\r\006\002H)\")Q+\025a\001E\005\t\021\rC\003X3\021E\001,A\njgB\023xnY3tg&tw-T3tg\006<W\r\006\002H3\")QK\026a\001E!)1,\007C\t9\006Y\001.Y:NKN\034\030mZ3t)\t9U\fC\003V5\002\007!\005C\003`3\021E\001-A\006jgN+8\017]3oI\026$GCA$b\021\025)f\f1\001#\021\025\031\027\004\"\005e\003AqW/\0342fe>3W*Z:tC\036,7\017\006\002CK\")QK\031a\001E!9q-GI\001\n\023A\027\001F:fY\026\034GOT3yi\022\"WMZ1vYR$#'F\001jU\t\021#nK\001l!\taw.D\001n\025\tqW*A\005v]\016DWmY6fI&\021\001/\034\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002:\032#\003%Ia]\001\025g\026dWm\031;OKb$H\005Z3gCVdG\017J\032\026\003QT#!\0206\t\017YL\022\023!C\005o\006!2/\0327fGRtU\r\037;%I\0264\027-\0367uIQ*\022\001\037\026\003\005*DqA_\r\022\002\023%10\001\013tK2,7\r\036(fqR$C-\0324bk2$H%N\013\002y*\022qI\033\025\0053y\f\031\001\005\002\016&\031\021\021\001\b\003!M+'/[1m-\026\0248/[8o+&#e$A\001")
/*     */ public class SmallestMailboxRoutingLogic implements RoutingLogic {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   public Routee select(Object message, IndexedSeq<Routee> routees) {
/*  36 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/*  37 */       selectNext(routees, selectNext$default$2(), selectNext$default$3(), selectNext$default$4(), selectNext$default$5());
/*     */   }
/*     */   
/*     */   private Routee selectNext$default$2() {
/*  51 */     return NoRoutee$.MODULE$;
/*     */   }
/*     */   
/*     */   private long selectNext$default$3() {
/*  52 */     return Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private int selectNext$default$4() {
/*  53 */     return 0;
/*     */   }
/*     */   
/*     */   private boolean selectNext$default$5() {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   private Routee selectNext(IndexedSeq targets, Routee proposedTarget, long currentScore, int at, boolean deep) {
/*     */     while (true) {
/*  57 */       if (at >= targets.size()) {
/*  58 */         if (deep) {
/*     */         
/*     */         } else {
/*  60 */           deep = true;
/*  60 */           at = 0;
/*  60 */           currentScore = currentScore;
/*  60 */           proposedTarget = proposedTarget;
/*  60 */           targets = targets;
/*     */           continue;
/*     */         } 
/*     */       } else {
/*  62 */         Routee target = (Routee)targets.apply(at);
/*  67 */         long noOfMsgs = deep ? numberOfMessages(target) : 0L;
/*  68 */         long newScore = isSuspended(target) ? 9223372036854775806L : ((isProcessingMessage(target) ? 1L : 0L) + (hasMessages(target) ? ((noOfMsgs > 0L) ? noOfMsgs : 9223372036854775804L) : 0L));
/*  72 */         if (newScore == 0L)
/*  72 */           return target; 
/*  73 */         if (newScore < 0L || newScore >= currentScore) {
/*  73 */           deep = deep;
/*  73 */           at++;
/*  73 */           currentScore = currentScore;
/*  73 */           proposedTarget = proposedTarget;
/*  73 */           targets = targets;
/*     */           continue;
/*     */         } 
/*  74 */         deep = deep;
/*  74 */         at++;
/*  74 */         currentScore = newScore;
/*  74 */         proposedTarget = target;
/*  74 */         targets = targets;
/*     */         continue;
/*     */       } 
/*     */       return targets.isEmpty() ? NoRoutee$.MODULE$ : (Routee)"JD-Core does not support Kotlin";
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isTerminated(Routee a) {
/*     */     boolean bool;
/*  78 */     Routee routee = a;
/*  79 */     if (routee instanceof ActorRefRoutee) {
/*  79 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/*  79 */       ActorRef ref = actorRefRoutee.ref();
/*  79 */       bool = ref.isTerminated();
/*     */     } else {
/*  80 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public boolean isProcessingMessage(Routee a) {
/*  89 */     Routee routee = a;
/*  90 */     if (routee instanceof ActorRefRoutee) {
/*  90 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/*  90 */       ActorRef x = actorRefRoutee.ref();
/*  90 */       if (x instanceof ActorRefWithCell) {
/*     */         boolean bool;
/*  90 */         ActorRefWithCell actorRefWithCell = (ActorRefWithCell)x;
/*  91 */         Cell cell = actorRefWithCell.underlying();
/*  92 */         if (cell instanceof ActorCell) {
/*  92 */           ActorCell actorCell = (ActorCell)cell;
/*  92 */           bool = (actorCell.mailbox().isScheduled() && actorCell.currentMessage() != null) ? true : false;
/*     */         } else {
/*  93 */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       } 
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasMessages(Routee a) {
/* 105 */     Routee routee = a;
/* 106 */     if (routee instanceof ActorRefRoutee) {
/* 106 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 106 */       ActorRef x = actorRefRoutee.ref();
/* 106 */       if (x instanceof ActorRefWithCell) {
/* 106 */         ActorRefWithCell actorRefWithCell = (ActorRefWithCell)x;
/* 106 */         return actorRefWithCell.underlying().hasMessages();
/*     */       } 
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSuspended(Routee a) {
/* 116 */     Routee routee = a;
/* 117 */     if (routee instanceof ActorRefRoutee) {
/* 117 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 117 */       ActorRef x = actorRefRoutee.ref();
/* 117 */       if (x instanceof ActorRefWithCell) {
/*     */         boolean bool;
/* 117 */         ActorRefWithCell actorRefWithCell = (ActorRefWithCell)x;
/* 118 */         Cell cell = actorRefWithCell.underlying();
/* 119 */         if (cell instanceof ActorCell) {
/* 119 */           ActorCell actorCell = (ActorCell)cell;
/* 119 */           bool = actorCell.mailbox().isSuspended();
/*     */         } else {
/* 120 */           bool = true;
/*     */         } 
/*     */         return bool;
/*     */       } 
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public int numberOfMessages(Routee a) {
/* 131 */     Routee routee = a;
/* 132 */     if (routee instanceof ActorRefRoutee) {
/* 132 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 132 */       ActorRef x = actorRefRoutee.ref();
/* 132 */       if (x instanceof ActorRefWithCell) {
/* 132 */         ActorRefWithCell actorRefWithCell = (ActorRefWithCell)x;
/* 132 */         return actorRefWithCell.underlying().numberOfMessages();
/*     */       } 
/*     */     } 
/* 133 */     return 0;
/*     */   }
/*     */   
/*     */   public static SmallestMailboxRoutingLogic apply() {
/*     */     return SmallestMailboxRoutingLogic$.MODULE$.apply();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SmallestMailboxRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */