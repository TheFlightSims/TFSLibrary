/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.Cell;
/*     */ import akka.actor.dungeon.Dispatch;
/*     */ import akka.dispatch.sysmsg.EarliestFirstSystemMessageList$;
/*     */ import akka.dispatch.sysmsg.NoMessage$;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.util.Helpers$;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025d\001B\001\003\001\035\0211CQ1mC:\034\027N\\4ESN\004\030\r^2iKJT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\005\001A\001CA\005\013\033\005\021\021BA\006\003\005)!\025n\0359bi\016DWM\035\005\t\033\001\021\t\021)A\005\035\005iqlY8oM&<WO]1u_J\004\"!C\b\n\005A\021!!H'fgN\fw-\032#jgB\fGo\0315fe\016{gNZ5hkJ\fGo\034:\t\023I\001!\021!Q\001\nMi\022aA0jIB\021AC\007\b\003+ai\021A\006\006\002/\005)1oY1mC&\021\021DF\001\007!J,G-\0324\n\005ma\"AB*ue&twM\003\002\032-%\021aDC\001\003S\022D\021\002\t\001\003\002\003\006I!\t\023\002\025QD'o\\;hQB,H\017\005\002\026E%\0211E\006\002\004\023:$\030B\001\021\013\021%1\003A!A!\002\0239s&\001\fuQJ|Wo\0325qkR$U-\0313mS:,G+[7f!\tAS&D\001*\025\tQ3&\001\005ekJ\fG/[8o\025\tac#\001\006d_:\034WO\035:f]RL!AL\025\003\021\021+(/\031;j_:L!A\n\006\t\021E\002!\021!Q\001\nI\nAbX7bS2\024w\016\037+za\026\004\"!C\032\n\005Q\022!aC'bS2\024w\016\037+za\026D\001B\016\001\003\002\003\006IaN\001 ?\026DXmY;u_J\034VM\035<jG\0264\025m\031;pef\004&o\034<jI\026\024\bCA\0059\023\tI$A\001\020Fq\026\034W\017^8s'\026\024h/[2f\r\006\034Go\034:z!J|g/\0333fe\"I1\b\001B\001B\003%AhP\001\021?NDW\017\0363po:$\026.\\3pkR\004\"\001K\037\n\005yJ#A\004$j]&$X\rR;sCRLwN\\\005\003\001*\tqb\0355vi\022|wO\034+j[\026|W\017\036\005\t\005\002\021\t\021)A\005\007\006y\021\r\036;f[B$H+Z1n/>\0248\016\005\002\026\t&\021QI\006\002\b\005>|G.Z1o\021\0259\005\001\"\001I\003\031a\024N\\5u}QI\021JS&M\033:{\005+\025\t\003\023\001AQ!\004$A\0029AQA\005$A\002MAQ\001\t$A\002\005BQA\n$A\002\035BQ!\r$A\002IBQA\016$A\002]BQa\017$A\002qBQA\021$A\002\rC\001b\025\001C\002\023\005A\001V\001\005i\026\fW.F\001V!\r1FLX\007\002/*\021A\006\027\006\0033j\013A!\036;jY*\t1,\001\003kCZ\f\027BA/X\005U\031uN\\2veJ,g\016^*lSBd\025n\035;TKR\004\"a\0302\016\003\001T!!\031\003\002\013\005\034Go\034:\n\005\r\004'!C!di>\0248)\0327m\021\031)\007\001)A\005+\006)A/Z1nA!Aq\r\001b\001\n\003!\001.\001\007nKN\034\030mZ3Rk\026,X-F\001j!\tI!.\003\002l\005\taQ*Z:tC\036,\027+^3vK\"1Q\016\001Q\001\n%\fQ\"\\3tg\006<W-U;fk\026\004c\001B8\001\tA\024ab\0255be&tw-T1jY\n|\007pE\002ocR\004\"!\003:\n\005M\024!aB'bS2\024w\016\037\t\003\023UL!A\036\002\0033\021+g-Y;miNK8\017^3n\033\026\0348/Y4f#V,W/\032\005\tq:\024)\031!C\001s\00611/_:uK6,\022A\037\t\003?nL!\001 1\003\037\005\033Go\034:TsN$X-\\%na2D\001B 8\003\002\003\006IA_\001\bgf\034H/Z7!\021-\t\tA\034B\001B\003%\021.a\001\002\033}kWm]:bO\026\fV/Z;f\023\t9'\017\003\004H]\022\005\021q\001\013\007\003\023\ti!a\004\021\007\005-a.D\001\001\021\031A\030Q\001a\001u\"9\021\021AA\003\001\004I\007bBA\n]\022\005\023QC\001\bG2,\027M\\+q)\t\t9\002E\002\026\0033I1!a\007\027\005\021)f.\033;\t\021\005}\001\001\"\025\005\003C\tQb\031:fCR,W*Y5mE>DH#B9\002$\005-\002bB1\002\036\001\007\021Q\005\t\004?\006\035\022bAA\025A\n!1)\0327m\021\035\ti#!\bA\002I\n1\"\\1jY\n|\007\020V=qK\"A\021\021\007\001\005R\021\t\031$\001\005sK\036L7\017^3s)\021\t9\"!\016\t\r\005\fy\0031\001_\021!\tI\004\001C)\t\005m\022AC;oe\026<\027n\035;feR!\021qCA\037\021\031\t\027q\007a\001=\"91\001\001C)\t\005\005CCBA\f\003\007\n9\005C\004\002F\005}\002\031\0010\002\021I,7-Z5wKJD\001\"!\023\002@\001\007\0211J\001\013S:4xnY1uS>t\007cA\005\002N%\031\021q\n\002\003\021\025sg/\0327pa\026Dq!a\025\001\t#\t)\"\001\005uK\006lwk\034:lQ\035\001\021qKA/\003C\0022!FA-\023\r\tYF\006\002\013I\026\004(/Z2bi\026$\027EAA0\003A*6/\032\021CC2\fgnY5oOB{w\016\034\021j]N$X-\0313!_\032\004#)\0317b]\016Lgn\032#jgB\fGo\0315fe\006\022\0211M\001\004e9\032\004")
/*     */ public class BalancingDispatcher extends Dispatcher {
/*     */   private final boolean attemptTeamWork;
/*     */   
/*     */   private final ConcurrentSkipListSet<ActorCell> team;
/*     */   
/*     */   private final MessageQueue messageQueue;
/*     */   
/*     */   public BalancingDispatcher(MessageDispatcherConfigurator _configurator, String _id, int throughput, Duration throughputDeadlineTime, MailboxType _mailboxType, ExecutorServiceFactoryProvider _executorServiceFactoryProvider, FiniteDuration _shutdownTimeout, boolean attemptTeamWork) {
/*  32 */     super(
/*     */         
/*  41 */         _configurator, _id, throughput, throughputDeadlineTime, _executorServiceFactoryProvider, _shutdownTimeout);
/*  46 */     this.team = new ConcurrentSkipListSet<ActorCell>(
/*  47 */         Helpers$.MODULE$.identityHashComparator(new $anon$1(this)));
/*  54 */     this.messageQueue = _mailboxType.create((Option<ActorRef>)None$.MODULE$, (Option<ActorSystem>)None$.MODULE$);
/*     */   }
/*     */   
/*     */   public ConcurrentSkipListSet<ActorCell> team() {
/*     */     return this.team;
/*     */   }
/*     */   
/*     */   public class $anon$1 implements Comparator<ActorCell> {
/*     */     public $anon$1(BalancingDispatcher $outer) {}
/*     */     
/*     */     public int compare(ActorCell l, ActorCell r) {
/*     */       return l.self().path().compareTo(r.self().path());
/*     */     }
/*     */   }
/*     */   
/*     */   public MessageQueue messageQueue() {
/*  54 */     return this.messageQueue;
/*     */   }
/*     */   
/*     */   public class SharingMailbox extends Mailbox implements DefaultSystemMessageQueue {
/*     */     private final ActorSystemImpl system;
/*     */     
/*     */     public final void systemEnqueue(ActorRef receiver, SystemMessage message) {
/*  56 */       DefaultSystemMessageQueue$class.systemEnqueue(this, receiver, message);
/*     */     }
/*     */     
/*     */     public final SystemMessage systemDrain(SystemMessage newContents) {
/*  56 */       return DefaultSystemMessageQueue$class.systemDrain(this, newContents);
/*     */     }
/*     */     
/*     */     public boolean hasSystemMessages() {
/*  56 */       return DefaultSystemMessageQueue$class.hasSystemMessages(this);
/*     */     }
/*     */     
/*     */     public ActorSystemImpl system() {
/*  56 */       return this.system;
/*     */     }
/*     */     
/*     */     public SharingMailbox(BalancingDispatcher $outer, ActorSystemImpl system, MessageQueue _messageQueue) {
/*  56 */       super(
/*  57 */           _messageQueue);
/*     */       DefaultSystemMessageQueue$class.$init$(this);
/*     */     }
/*     */     
/*     */     public void cleanUp() {
/*  59 */       Mailbox dlq = akka$dispatch$BalancingDispatcher$SharingMailbox$$$outer().mailboxes().deadLetterMailbox();
/*  61 */       SystemMessage messages = systemDrain((SystemMessage)NoMessage$.MODULE$);
/*  62 */       while (EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(messages)) {
/*  64 */         SystemMessage message = messages;
/*  65 */         messages = EarliestFirstSystemMessageList$.MODULE$.tail$extension(messages);
/*  66 */         message.unlink();
/*  67 */         dlq.systemEnqueue(system().deadLetters(), message);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public Mailbox createMailbox(Cell actor, MailboxType mailboxType) {
/*  73 */     return new SharingMailbox(this, actor.systemImpl(), messageQueue());
/*     */   }
/*     */   
/*     */   public void register(ActorCell actor) {
/*  76 */     super.register(actor);
/*  77 */     team().add(actor);
/*     */   }
/*     */   
/*     */   public void unregister(ActorCell actor) {
/*  81 */     team().remove(actor);
/*  82 */     super.unregister(actor);
/*  83 */     teamWork();
/*     */   }
/*     */   
/*     */   public void dispatch(ActorCell receiver, Envelope invocation) {
/*  87 */     messageQueue().enqueue((ActorRef)receiver.self(), invocation);
/*  88 */     if (!registerForExecution(receiver.mailbox(), false, false))
/*  88 */       teamWork(); 
/*     */   }
/*     */   
/*     */   public void teamWork() {
/*  92 */     if (this.attemptTeamWork)
/* 103 */       scheduleOne$1(scheduleOne$default$1$1()); 
/*     */   }
/*     */   
/*     */   private final Iterator scheduleOne$default$1$1() {
/*     */     return team().iterator();
/*     */   }
/*     */   
/*     */   private final void scheduleOne$1(Iterator<Dispatch> i) {
/*     */     while (messageQueue().hasMessages() && i.hasNext()) {
/*     */       boolean bool;
/*     */       ExecutorService executorService = executorService().executor();
/*     */       if (executorService instanceof LoadMetrics) {
/*     */         ExecutorService executorService1 = executorService;
/*     */         bool = (((LoadMetrics)executorService1).atFullThrottle() == false) ? true : false;
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       if (bool)
/*     */         if (!registerForExecution(((Dispatch)i.next()).mailbox(), false, false))
/*     */           i = i;  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BalancingDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */