/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorPath;
/*    */ import akka.actor.ActorSystemImpl;
/*    */ import akka.actor.Cell;
/*    */ import akka.actor.InternalActorRef;
/*    */ import akka.actor.Props;
/*    */ import akka.actor.RepointableActorRef;
/*    */ import akka.actor.UnstartedCell;
/*    */ import akka.dispatch.MailboxType;
/*    */ import akka.dispatch.MessageDispatcher;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Q3Q!\001\002\001\t\031\021aBU8vi\026$\027i\031;peJ+gM\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\n\003\001\035\001\"\001C\006\016\003%Q!A\003\003\002\013\005\034Go\034:\n\0051I!a\005*fa>Lg\016^1cY\026\f5\r^8s%\0264\007\"\003\b\001\005\003\005\013\021\002\t\024\003\035y6/_:uK6\034\001\001\005\002\t#%\021!#\003\002\020\003\016$xN]*zgR,W.S7qY&\021AcC\001\007gf\034H/Z7\t\023Y\001!\021!Q\001\n]Q\022\001D0s_V$XM\035)s_B\034\bC\001\005\031\023\tI\022BA\003Qe>\0048/\003\002\034\027\005)\001O]8qg\"IQ\004\001B\001B\003%a\004J\001\022?J|W\017^3s\t&\034\b/\031;dQ\026\024\bCA\020#\033\005\001#BA\021\005\003!!\027n\0359bi\016D\027BA\022!\005EiUm]:bO\026$\025n\0359bi\016DWM]\005\003K-\t!\002Z5ta\006$8\r[3s\021%9\003A!A!\002\023A3&\001\b`e>,H/\032:NC&d'm\034=\021\005}I\023B\001\026!\005-i\025-\0337c_b$\026\020]3\n\0051Z\021aC7bS2\024w\016\037+za\026D\001B\f\001\003\002\003\006IaF\001\r?J|W\017^3f!J|\007o\035\005\na\001\021\t\021)A\005cQ\n1bX:va\026\024h/[:peB\021\001BM\005\003g%\021\001#\0238uKJt\027\r\\!di>\024(+\0324\n\005UZ\021AC:va\026\024h/[:pe\"Iq\007\001B\001B\003%\001hO\001\006?B\fG\017\033\t\003\021eJ!AO\005\003\023\005\033Go\034:QCRD\027B\001\037\f\003\021\001\030\r\0365\t\013y\002A\021A \002\rqJg.\033;?)!\001%i\021#F\r\036C\005CA!\001\033\005\021\001\"\002\b>\001\004\001\002\"\002\f>\001\0049\002\"B\017>\001\004q\002\"B\024>\001\004A\003\"\002\030>\001\0049\002\"\002\031>\001\004\t\004\"B\034>\001\004A\004\"\002&\001\t\003Z\025a\0028fo\016+G\016\034\013\003\031>\003\"\001C'\n\0059K!\001B\"fY2DQ\001U%A\002E\0131a\0347e!\tA!+\003\002T\023\tiQK\\:uCJ$X\rZ\"fY2\004")
/*    */ public class RoutedActorRef extends RepointableActorRef {
/*    */   private final Props _routeeProps;
/*    */   
/*    */   public RoutedActorRef(ActorSystemImpl _system, Props _routerProps, MessageDispatcher _routerDispatcher, MailboxType _routerMailbox, Props _routeeProps, InternalActorRef _supervisor, ActorPath _path) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload #5
/*    */     //   3: putfield _routeeProps : Lakka/actor/Props;
/*    */     //   6: aload_0
/*    */     //   7: aload_1
/*    */     //   8: aload_2
/*    */     //   9: aload_3
/*    */     //   10: aload #4
/*    */     //   12: aload #6
/*    */     //   14: aload #7
/*    */     //   16: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Lakka/actor/Props;Lakka/dispatch/MessageDispatcher;Lakka/dispatch/MailboxType;Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;)V
/*    */     //   19: aload_0
/*    */     //   20: invokespecial props : ()Lakka/actor/Props;
/*    */     //   23: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*    */     //   26: getstatic akka/routing/NoRouter$.MODULE$ : Lakka/routing/NoRouter$;
/*    */     //   29: astore #8
/*    */     //   31: dup
/*    */     //   32: ifnonnull -> 44
/*    */     //   35: pop
/*    */     //   36: aload #8
/*    */     //   38: ifnull -> 94
/*    */     //   41: goto -> 52
/*    */     //   44: aload #8
/*    */     //   46: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   49: ifne -> 94
/*    */     //   52: aload_0
/*    */     //   53: invokespecial dispatcher : ()Lakka/dispatch/MessageDispatcher;
/*    */     //   56: instanceof akka/dispatch/BalancingDispatcher
/*    */     //   59: ifeq -> 94
/*    */     //   62: new akka/ConfigurationException
/*    */     //   65: dup
/*    */     //   66: new scala/collection/mutable/StringBuilder
/*    */     //   69: dup
/*    */     //   70: invokespecial <init> : ()V
/*    */     //   73: ldc 'Configuration for '
/*    */     //   75: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   78: aload_0
/*    */     //   79: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   82: ldc ' is invalid - you can not use a 'BalancingDispatcher' as a Router's dispatcher, you can however use it for the routees.'
/*    */     //   84: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   87: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   90: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   93: athrow
/*    */     //   94: aload_0
/*    */     //   95: invokespecial props : ()Lakka/actor/Props;
/*    */     //   98: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*    */     //   101: aload_0
/*    */     //   102: invokespecial path : ()Lakka/actor/ActorPath;
/*    */     //   105: invokeinterface verifyConfig : (Lakka/actor/ActorPath;)V
/*    */     //   110: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #30	-> 0
/*    */     //   #25	-> 6
/*    */     //   #33	-> 7
/*    */     //   #25	-> 16
/*    */     //   #36	-> 19
/*    */     //   #37	-> 62
/*    */     //   #38	-> 66
/*    */     //   #39	-> 82
/*    */     //   #38	-> 87
/*    */     //   #37	-> 90
/*    */     //   #40	-> 94
/*    */     //   #25	-> 110
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	111	0	this	Lakka/routing/RoutedActorRef;
/*    */     //   0	111	1	_system	Lakka/actor/ActorSystemImpl;
/*    */     //   0	111	2	_routerProps	Lakka/actor/Props;
/*    */     //   0	111	3	_routerDispatcher	Lakka/dispatch/MessageDispatcher;
/*    */     //   0	111	4	_routerMailbox	Lakka/dispatch/MailboxType;
/*    */     //   0	111	5	_routeeProps	Lakka/actor/Props;
/*    */     //   0	111	6	_supervisor	Lakka/actor/InternalActorRef;
/*    */     //   0	111	7	_path	Lakka/actor/ActorPath;
/*    */   }
/*    */   
/*    */   public Cell newCell(UnstartedCell old) {
/* 43 */     RouterConfig routerConfig = props().routerConfig();
/* 44 */     if (routerConfig instanceof Pool) {
/* 44 */       Pool pool = (Pool)routerConfig;
/* 44 */       if (pool.resizer().isDefined()) {
/* 45 */         ResizablePoolCell resizablePoolCell = new ResizablePoolCell(system(), (InternalActorRef)this, props(), dispatcher(), this._routeeProps, supervisor(), pool);
/*    */         RoutedActorCell cell = resizablePoolCell;
/* 49 */         return (Cell)cell.init(false, mailboxType());
/*    */       } 
/*    */     } 
/*    */     RoutedActorCell routedActorCell2 = new RoutedActorCell(system(), (InternalActorRef)this, props(), dispatcher(), this._routeeProps, supervisor());
/*    */     RoutedActorCell routedActorCell1 = routedActorCell2;
/* 49 */     return (Cell)routedActorCell1.init(false, mailboxType());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoutedActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */