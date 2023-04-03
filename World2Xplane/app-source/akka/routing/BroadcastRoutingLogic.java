/*    */ package akka.routing;
/*    */ 
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e:Q!\001\002\t\002\035\tQC\021:pC\022\034\027m\035;S_V$\030N\\4M_\036L7M\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005U\021%o\\1eG\006\034HOU8vi&tw\rT8hS\016\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\003\027\023\021\005q#A\003baBd\027\020F\001\031!\tA\021D\002\003\013\005\tQ2cA\r\r7A\021\001\002H\005\003;\t\021ABU8vi&tw\rT8hS\016DQaE\r\005\002]AQ\001I\r\005B\005\naa]3mK\016$Hc\001\022&UA\021\001bI\005\003I\t\021aAU8vi\026,\007\"\002\024 \001\0049\023aB7fgN\fw-\032\t\003\033!J!!\013\b\003\007\005s\027\020C\003,?\001\007A&A\004s_V$X-Z:\021\0075\022$%D\001/\025\ty\003'A\005j[6,H/\0312mK*\021\021GD\001\013G>dG.Z2uS>t\027BA\032/\005)Ie\016Z3yK\022\034V-\035\025\0043UB\004CA\0077\023\t9dB\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021\001")
/*    */ public final class BroadcastRoutingLogic implements RoutingLogic {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   public static BroadcastRoutingLogic apply() {
/*    */     return BroadcastRoutingLogic$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public Routee select(Object message, IndexedSeq<Routee> routees) {
/* 25 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/* 26 */       new SeveralRoutees(routees);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */