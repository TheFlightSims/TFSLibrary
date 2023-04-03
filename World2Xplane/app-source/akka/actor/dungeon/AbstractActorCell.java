/*    */ package akka.actor.dungeon;
/*    */ 
/*    */ import akka.actor.ActorCell;
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ final class AbstractActorCell {
/*    */   static final long mailboxOffset;
/*    */   
/*    */   static final long childrenOffset;
/*    */   
/*    */   static final long nextNameOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 17 */       mailboxOffset = Unsafe.instance.objectFieldOffset(ActorCell.class.getDeclaredField("akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly"));
/* 18 */       childrenOffset = Unsafe.instance.objectFieldOffset(ActorCell.class.getDeclaredField("akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly"));
/* 19 */       nextNameOffset = Unsafe.instance.objectFieldOffset(ActorCell.class.getDeclaredField("akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly"));
/* 20 */     } catch (Throwable throwable) {
/* 21 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\AbstractActorCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */