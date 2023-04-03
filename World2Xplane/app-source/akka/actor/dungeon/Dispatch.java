/*    */ package akka.actor.dungeon;
/*    */ 
/*    */ import akka.actor.ActorCell;
/*    */ import akka.dispatch.Envelope;
/*    */ import akka.dispatch.Mailbox;
/*    */ import akka.dispatch.MailboxType;
/*    */ import akka.dispatch.sysmsg.SystemMessage;
/*    */ import akka.event.Logging;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]bAC\001\003!\003\r\tA\002\005\0020\tAA)[:qCR\034\007N\003\002\004\t\0059A-\0368hK>t'BA\003\007\003\025\t7\r^8s\025\0059\021\001B1lW\006\034\"\001A\005\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g\021\025\001\002\001\"\001\023\003\031!\023N\\5uI\r\001A#A\n\021\005)!\022BA\013\f\005\021)f.\033;\t\023]\001\001\031!a\001\n\023A\022aG0nC&d'm\034=E_:{GoQ1mY6+G)\033:fGRd\0270F\001\032!\tQR$D\001\034\025\tab!\001\005eSN\004\030\r^2i\023\tq2DA\004NC&d'm\034=\t\023\001\002\001\031!a\001\n\023\t\023aH0nC&d'm\034=E_:{GoQ1mY6+G)\033:fGRd\027p\030\023fcR\0211C\t\005\bG}\t\t\0211\001\032\003\rAH%\r\005\007K\001\001\013\025B\r\0029}k\027-\0337c_b$uNT8u\007\006dG.T3ESJ,7\r\0367zA!\022Ae\n\t\003\025!J!!K\006\003\021Y|G.\031;jY\026DQa\013\001\005\006a\tq!\\1jY\n|\007\020\013\002+[A\021!BL\005\003_-\021a!\0338mS:,\007\"B\031\001\t\013\021\024aC:xCBl\025-\0337c_b$\"!G\032\t\013Q\002\004\031A\r\002\0259,w/T1jY\n|\007\020\013\0021mA\021qGO\007\002q)\021\021hC\001\013C:tw\016^1uS>t\027BA\0369\005\035!\030-\0337sK\016DQ!\020\001\005\006y\n1\002[1t\033\026\0348/Y4fgV\tq\b\005\002\013\001&\021\021i\003\002\b\005>|G.Z1o\021\025\031\005\001\"\002E\003AqW/\0342fe>3W*Z:tC\036,7/F\001F!\tQa)\003\002H\027\t\031\021J\034;\t\013%\003AQ\001 \002\031%\034H+\032:nS:\fG/\0323\t\013-\003AQ\001'\002\t%t\027\016\036\013\004\033:\003V\"\001\001\t\013=S\005\031A \002\033M,g\016Z*va\026\024h/[:f\021\025\t&\n1\001S\003-i\027-\0337c_b$\026\020]3\021\005i\031\026B\001+\034\005-i\025-\0337c_b$\026\020]3\t\013Y\003A\021A,\002\013M$\030M\035;\025\0035CQ!\027\001\005\ni\013q\002[1oI2,W\t_2faRLwN\\\013\0027B\031A\f\\\n\017\005uKgB\0010g\035\tyFM\004\002aG6\t\021M\003\002c#\0051AH]8pizJ\021\001D\005\003K.\tA!\036;jY&\021q\r[\001\bG>tGO]8m\025\t)7\"\003\002kW\006IQ\t_2faRLwN\034\006\003O\"L!!\0348\003\017\r\013Go\0315fe*\021!n\033\005\006a\002!)AE\001\bgV\034\b/\0328e\021\025\021\b\001\"\002t\003\031\021Xm];nKR\0211\003\036\005\006kF\004\rA^\001\020G\006,8/\0323Cs\032\013\027\016\\;sKB\021qO\037\b\003?bL!!_\006\002\017A\f7m[1hK&\0211\020 \002\n)\"\024xn^1cY\026T!!_\006\t\013y\004AQA@\002\017I,7\017^1siR\0311#!\001\t\r\005\rQ\0201\001w\003\025\031\027-^:f\021\031\t9\001\001C\003%\005!1\017^8q\021\035\tY\001\001C\001\003\033\t1b]3oI6+7o]1hKR\0311#a\004\t\021\005E\021\021\002a\001\003'\t1!\\:h!\rQ\022QC\005\004\003/Y\"\001C#om\026dw\016]3\t\017\005m\001\001\"\021\002\036\005\t2/\0328e'f\034H/Z7NKN\034\030mZ3\025\007M\ty\002\003\005\002\"\005e\001\031AA\022\003\035iWm]:bO\026\004B!!\n\002,5\021\021q\005\006\004\003SY\022AB:zg6\034x-\003\003\002.\005\035\"!D*zgR,W.T3tg\006<W\r\005\003\0022\005MR\"\001\003\n\007\005UBAA\005BGR|'oQ3mY\002")
/*    */ public interface Dispatch {
/*    */   Mailbox akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly();
/*    */   
/*    */   @TraitSetter
/*    */   void akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly_$eq(Mailbox paramMailbox);
/*    */   
/*    */   Mailbox mailbox();
/*    */   
/*    */   Mailbox swapMailbox(Mailbox paramMailbox);
/*    */   
/*    */   boolean hasMessages();
/*    */   
/*    */   int numberOfMessages();
/*    */   
/*    */   boolean isTerminated();
/*    */   
/*    */   ActorCell init(boolean paramBoolean, MailboxType paramMailboxType);
/*    */   
/*    */   ActorCell start();
/*    */   
/*    */   void suspend();
/*    */   
/*    */   void resume(Throwable paramThrowable);
/*    */   
/*    */   void restart(Throwable paramThrowable);
/*    */   
/*    */   void stop();
/*    */   
/*    */   void sendMessage(Envelope paramEnvelope);
/*    */   
/*    */   void sendSystemMessage(SystemMessage paramSystemMessage);
/*    */   
/*    */   public class Dispatch$$anonfun$handleException$1 extends AbstractPartialFunction.mcVL.sp<Throwable> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x7, Function1 default) {
/*    */       Object object;
/* 91 */       Throwable throwable = x7;
/* 92 */       if (throwable instanceof InterruptedException) {
/* 92 */         InterruptedException interruptedException = (InterruptedException)throwable;
/* 93 */         this.$outer.system().eventStream().publish(new Logging.Error(interruptedException, this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), "interrupted during message send"));
/* 94 */         Thread.currentThread().interrupt();
/* 94 */         object = BoxedUnit.UNIT;
/*    */       } else {
/* 95 */         Option option = NonFatal$.MODULE$.unapply(throwable);
/* 95 */         if (option.isEmpty()) {
/*    */           object = default.apply(x7);
/*    */         } else {
/* 95 */           Throwable e = (Throwable)option.get();
/* 96 */           this.$outer.system().eventStream().publish(new Logging.Error(e, this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), "swallowing exception during message send"));
/* 96 */           object = BoxedUnit.UNIT;
/*    */         } 
/*    */       } 
/*    */       return (B1)object;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Throwable x7) {
/*    */       boolean bool;
/*    */       Throwable throwable = x7;
/*    */       if (throwable instanceof InterruptedException) {
/*    */         bool = true;
/*    */       } else {
/*    */         Option option = NonFatal$.MODULE$.unapply(throwable);
/*    */         if (option.isEmpty()) {
/*    */           bool = false;
/*    */         } else {
/* 96 */           bool = true;
/*    */         } 
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public Dispatch$$anonfun$handleException$1(ActorCell $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\Dispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */