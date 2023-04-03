/*    */ package akka.event;
/*    */ 
/*    */ import akka.util.Index;
/*    */ import java.util.Comparator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u3\001\"\001\002\021\002\007\005qa\026\002\025\031>|7.\0369DY\006\0348/\0334jG\006$\030n\0348\013\005\r!\021!B3wK:$(\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\005\006\037\001!\t\001E\001\007I%t\027\016\036\023\025\003E\001\"!\003\n\n\005MQ!\001B+oSRDq!\006\001C\002\023Ua#A\006tk\n\0348M]5cKJ\034X#A\f\021\taYRdI\007\0023)\021!\004B\001\005kRLG.\003\002\0353\t)\021J\0343fqB\021adH\007\002\001%\021\001%\t\002\013\0072\f7o]5gS\026\024\030B\001\022\003\005!)e/\0328u\005V\034\bC\001\020%\023\t)\023E\001\006Tk\n\0348M]5cKJDaa\n\001!\002\0339\022\001D:vEN\034'/\0332feN\004\003\"B\025\001\r#Q\023aB7baNK'0\032\013\002WA\021\021\002L\005\003[)\0211!\0238u\021\025y\003A\"\0051\003I\031w.\0349be\026\034VOY:de&\024WM]:\025\007-\n4\007C\0033]\001\0071%A\001b\021\025!d\0061\001$\003\005\021\007\"\002\034\001\r#9\024\001C2mCN\034\030NZ=\025\005uA\004\"B\0026\001\004I\004C\001\020;\023\tY\024EA\003Fm\026tG\017C\003>\001\031Ea(A\004qk\nd\027n\0355\025\007Ey\004\tC\003\004y\001\007\021\bC\003By\001\0071%\001\006tk\n\0348M]5cKJDQa\021\001\005\002\021\013\021b];cg\016\024\030NY3\025\007\025C\025\n\005\002\n\r&\021qI\003\002\b\005>|G.Z1o\021\025\t%\t1\001$\021\025Q%\t1\001\036\003\t!x\016C\003M\001\021\005Q*A\006v]N,(m]2sS\n,GcA#O\037\")\021i\023a\001G!)\001k\023a\001;\005!aM]8n\021\025a\005\001\"\001S)\t\t2\013C\003B#\002\0071\005C\003>\001\021\005Q\013\006\002\022-\")1\001\026a\001sI\031\001L\027/\007\te\003\001a\026\002\ryI,g-\0338f[\026tGO\020\t\0037\002i\021A\001\t\0037\006\002")
/*    */ public interface LookupClassification {
/*    */   void akka$event$LookupClassification$_setter_$subscribers_$eq(Index paramIndex);
/*    */   
/*    */   Index<Object, Object> subscribers();
/*    */   
/*    */   int mapSize();
/*    */   
/*    */   int compareSubscribers(Object paramObject1, Object paramObject2);
/*    */   
/*    */   Object classify(Object paramObject);
/*    */   
/*    */   void publish(Object paramObject1, Object paramObject2);
/*    */   
/*    */   boolean subscribe(Object paramObject1, Object paramObject2);
/*    */   
/*    */   boolean unsubscribe(Object paramObject1, Object paramObject2);
/*    */   
/*    */   void unsubscribe(Object paramObject);
/*    */   
/*    */   void publish(Object paramObject);
/*    */   
/*    */   public class $anon$1 implements Comparator<Object> {
/*    */     public $anon$1(LookupClassification $outer) {}
/*    */     
/*    */     public int compare(Object a, Object b) {
/* 84 */       return this.$outer.compareSubscribers(a, b);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LookupClassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */