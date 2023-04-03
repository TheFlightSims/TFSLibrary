/*    */ package akka.routing;
/*    */ 
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.concurrent.forkjoin.ThreadLocalRandom;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e:Q!\001\002\t\002\035\t!CU1oI>l'k\\;uS:<Gj\\4jG*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\nSC:$w.\034*pkRLgn\032'pO&\0347CA\005\r!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fM\")1#\003C\001)\0051A(\0338jiz\"\022a\002\005\006-%!\taF\001\006CB\004H.\037\013\0021A\021\001\"\007\004\005\025\t\021!dE\002\032\031m\001\"\001\003\017\n\005u\021!\001\004*pkRLgn\032'pO&\034\007\"B\n\032\t\0039\002\"\002\021\032\t\003\n\023AB:fY\026\034G\017F\002#K)\002\"\001C\022\n\005\021\022!A\002*pkR,W\rC\003'?\001\007q%A\004nKN\034\030mZ3\021\0055A\023BA\025\017\005\r\te.\037\005\006W}\001\r\001L\001\be>,H/Z3t!\ri#GI\007\002])\021q\006M\001\nS6lW\017^1cY\026T!!\r\b\002\025\r|G\016\\3di&|g.\003\0024]\tQ\021J\0343fq\026$7+Z9)\007e)\004\b\005\002\016m%\021qG\004\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!\001")
/*    */ public final class RandomRoutingLogic implements RoutingLogic {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   public static RandomRoutingLogic apply() {
/*    */     return RandomRoutingLogic$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public Routee select(Object message, IndexedSeq routees) {
/* 26 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/* 27 */       (Routee)routees.apply(ThreadLocalRandom.current().nextInt(routees.size()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */