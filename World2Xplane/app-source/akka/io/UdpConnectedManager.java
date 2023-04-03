/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.Props;
/*    */ import akka.actor.Props$;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0252Q!\001\002\001\005\031\0211#\0263q\007>tg.Z2uK\022l\025M\\1hKJT!a\001\003\002\005%|'\"A\003\002\t\005\\7.Y\n\003\001\035\001\"\001\003\007\017\005%QQ\"\001\002\n\005-\021\021\001E*fY\026\034G/[8o\021\006tG\r\\3s\023\tiaB\001\013TK2,7\r^8s\005\006\034X\rZ'b]\006<WM\035\006\003\027\tA\001\002\005\001\003\002\003\006IAE\001\bk\022\0048i\0348o\007\001\001\"!C\n\n\005Q\021!aD+ea\016{gN\\3di\026$W\t\037;\t\013Y\001A\021A\f\002\rqJg.\033;?)\tA\022\004\005\002\n\001!)\001#\006a\001%!)1\004\001C\0019\0059!/Z2fSZ,W#A\017\021\005yyR\"\001\001\n\005\001\n#a\002*fG\026Lg/Z\005\003E\r\022Q!Q2u_JT!\001\n\003\002\013\005\034Go\034:")
/*    */ public class UdpConnectedManager extends SelectionHandler.SelectorBasedManager {
/*    */   public final UdpConnectedExt akka$io$UdpConnectedManager$$udpConn;
/*    */   
/*    */   public UdpConnectedManager(UdpConnectedExt udpConn) {
/* 12 */     super(
/* 13 */         udpConn.settings(), udpConn.settings().NrOfSelectors());
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 15 */     return workerForCommandHandler((PartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>>)new UdpConnectedManager$$anonfun$receive$1(this));
/*    */   }
/*    */   
/*    */   public class UdpConnectedManager$$anonfun$receive$1 extends AbstractPartialFunction<SelectionHandler.HasFailureMessage, Function1<ChannelRegistry, Props>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1 extends SelectionHandler.HasFailureMessage, B1> B1 applyOrElse(SelectionHandler.HasFailureMessage x1, Function1 default) {
/*    */       Object object;
/* 15 */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/* 16 */       if (hasFailureMessage instanceof UdpConnected.Connect) {
/* 16 */         UdpConnected.Connect connect = (UdpConnected.Connect)hasFailureMessage;
/* 17 */         ActorRef commander = this.$outer.sender();
/* 18 */         object = new UdpConnectedManager$$anonfun$receive$1$$anonfun$applyOrElse$1(this, commander, connect);
/*    */       } else {
/*    */         object = default.apply(x1);
/*    */       } 
/*    */       return (B1)object;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(SelectionHandler.HasFailureMessage x1) {
/*    */       boolean bool;
/*    */       SelectionHandler.HasFailureMessage hasFailureMessage = x1;
/*    */       if (hasFailureMessage instanceof UdpConnected.Connect) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public UdpConnectedManager$$anonfun$receive$1(UdpConnectedManager $outer) {}
/*    */     
/*    */     public class UdpConnectedManager$$anonfun$receive$1$$anonfun$applyOrElse$1 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ActorRef commander$1;
/*    */       
/*    */       private final UdpConnected.Connect x2$1;
/*    */       
/*    */       public final Props apply(ChannelRegistry registry) {
/* 18 */         return Props$.MODULE$.apply(UdpConnection.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (this.$outer.akka$io$UdpConnectedManager$$anonfun$$$outer()).akka$io$UdpConnectedManager$$udpConn, registry, this.commander$1, this.x2$1 }));
/*    */       }
/*    */       
/*    */       public UdpConnectedManager$$anonfun$receive$1$$anonfun$applyOrElse$1(UdpConnectedManager$$anonfun$receive$1 $outer, ActorRef commander$1, UdpConnected.Connect x2$1) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnectedManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */