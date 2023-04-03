/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Predef$;
/*     */ import scala.StringContext;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E;a!\001\002\t\002\0211\021a\b\"bY\006t7-\0338h\t&\034\b/\031;dQ\026\0248i\0348gS\036,(/\031;pe*\0211\001B\001\tI&\034\b/\031;dQ*\tQ!\001\003bW.\f\007CA\004\t\033\005\021aAB\005\003\021\003!!BA\020CC2\fgnY5oO\022K7\017]1uG\",'oQ8oM&<WO]1u_J\034\"\001C\006\021\0051yQ\"A\007\013\0039\tQa]2bY\006L!\001E\007\003\r\005s\027PU3g\021\025\021\002\002\"\001\025\003\031a\024N\\5u}\r\001A#\001\004\t\017YA!\031!C\005/\005\021B-\0324bk2$(+Z9vSJ,W.\0328u+\005A\002CA\r!\033\005Q\"BA\016\035\003\031\031wN\0344jO*\021QDH\001\tif\004Xm]1gK*\tq$A\002d_6L!!\t\016\003\r\r{gNZ5h\021\031\031\003\002)A\0051\005\031B-\0324bk2$(+Z9vSJ,W.\0328uA!)Q\005\003C\001M\005Y\021-\\3oI\016{gNZ5h)\tAr\005C\003\034I\001\007\001D\002\003\n\005\001I3C\001\025+!\t91&\003\002-\005\tiR*Z:tC\036,G)[:qCR\034\007.\032:D_:4\027nZ;sCR|'\017\003\005/Q\t\005\t\025!\003\031\003\035y6m\0348gS\036D\021\002\r\025\003\002\003\006I!\r\033\002\035}\003(/\032:fcVL7/\033;fgB\021qAM\005\003g\t\021q\003R5ta\006$8\r[3s!J,'/Z9vSNLG/Z:\n\005UZ\023!\0049sKJ,\027/^5tSR,7\017C\003\023Q\021\005q\007F\0029si\002\"a\002\025\t\01392\004\031\001\r\t\013A2\004\031A\031\t\017qB#\031!C\005{\005A\021N\\:uC:\034W-F\001?!\t9q(\003\002A\005\t\031\")\0317b]\016Lgn\032#jgB\fGo\0315fe\"1!\t\013Q\001\ny\n\021\"\0338ti\006t7-\032\021\t\013\021CC\021C#\002\r\r\024X-\031;f)\tqd\tC\003H\007\002\007\001*A\006nC&d'm\034=UsB,\007CA\004J\023\tQ%AA\006NC&d'm\034=UsB,\007\"\002')\t\003j\025A\0033jgB\fGo\0315feR\ta\n\005\002\b\037&\021\001K\001\002\022\033\026\0348/Y4f\t&\034\b/\031;dQ\026\024\b")
/*     */ public class BalancingDispatcherConfigurator extends MessageDispatcherConfigurator {
/*     */   private final BalancingDispatcher instance;
/*     */   
/*     */   public BalancingDispatcherConfigurator(Config _config, DispatcherPrerequisites _prerequisites) {
/* 236 */     super(
/* 237 */         BalancingDispatcherConfigurator$.MODULE$.amendConfig(_config), _prerequisites);
/* 240 */     Mailboxes mailboxes = prerequisites().mailboxes();
/* 241 */     String id = config().getString("id");
/* 242 */     Class<?> requirement = mailboxes.getMailboxRequirement(config());
/* 243 */     if (MultipleConsumerSemantics.class.isAssignableFrom(requirement)) {
/* 248 */       if (config().hasPath("mailbox-type")) {
/* 249 */         MailboxType mt = mailboxes.lookup(id);
/* 250 */         if (requirement.isAssignableFrom(mailboxes.getProducedMessageQueueType(mt))) {
/*     */         
/*     */         } else {
/* 252 */           (new String[4])[0] = "BalancingDispatcher [";
/* 252 */           (new String[4])[1] = "] has 'mailbox-type' [";
/* 252 */           (new String[4])[2] = "] which is incompatible with 'mailbox-requirement' [";
/* 252 */           (new String[4])[3] = "]";
/* 252 */           throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[4]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { id, mt.getClass(), requirement })));
/*     */         } 
/*     */       } else {
/*     */       
/*     */       } 
/* 254 */       MailboxType mailboxType = mailboxes.lookupByQueueType(requirement);
/* 255 */       this.instance = create(mailboxType);
/*     */     } 
/*     */     (new String[3])[0] = "dispatcher [";
/*     */     (new String[3])[1] = "] has [";
/*     */     (new String[3])[2] = "]";
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("BalancingDispatcher must have 'mailbox-requirement' which implements akka.dispatch.MultipleConsumerSemantics; ").append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { id, requirement }))).toString());
/*     */   }
/*     */   
/*     */   private BalancingDispatcher instance() {
/*     */     return this.instance;
/*     */   }
/*     */   
/*     */   public BalancingDispatcher create(MailboxType mailboxType) {
/* 259 */     return new BalancingDispatcher(
/* 260 */         this, 
/* 261 */         config().getString("id"), 
/* 262 */         config().getInt("throughput"), (Duration)Helpers$ConfigOps$.MODULE$
/* 263 */         .getNanosDuration$extension(Helpers$.MODULE$.ConfigOps(config()), "throughput-deadline-time"), 
/* 264 */         mailboxType, 
/* 265 */         configureExecutor(), Helpers$ConfigOps$.MODULE$
/* 266 */         .getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config()), "shutdown-timeout"), 
/* 267 */         config().getBoolean("attempt-teamwork"));
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/* 272 */     return instance();
/*     */   }
/*     */   
/*     */   public static Config amendConfig(Config paramConfig) {
/*     */     return BalancingDispatcherConfigurator$.MODULE$.amendConfig(paramConfig);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BalancingDispatcherConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */