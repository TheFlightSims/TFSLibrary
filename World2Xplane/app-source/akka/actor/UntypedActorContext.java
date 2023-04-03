package akka.actor;

import akka.japi.Procedure;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0253q!\001\002\021\002G\005qAA\nV]RL\b/\0323BGR|'oQ8oi\026DHO\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\004\001!q\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\ta\021i\031;pe\016{g\016^3yi\")1\003\001D\001)\005Yq-\032;DQ&dGM]3o)\005)\002c\001\f\034;5\tqC\003\002\0313\005!A.\0318h\025\005Q\022\001\0026bm\006L!\001H\f\003\021%#XM]1cY\026\004\"a\004\020\n\005}\021!\001C!di>\024(+\0324\t\013\005\002a\021\001\022\002\021\035,Go\0215jY\022$\"!H\022\t\013\021\002\003\031A\023\002\t9\fW.\032\t\003M%r!!C\024\n\005!R\021A\002)sK\022,g-\003\002+W\t11\013\036:j]\036T!\001\013\006\t\0135\002a\021\001\030\002\r\t,7m\\7f)\ty#\007\005\002\na%\021\021G\003\002\005+:LG\017C\0034Y\001\007A'\001\005cK\"\fg/[8s!\r)\004HO\007\002m)\021q\007B\001\005U\006\004\030.\003\002:m\tI\001K]8dK\022,(/\032\t\003\023mJ!\001\020\006\003\007\005s\027\020C\003.\001\031\005a\bF\0020\001CQaM\037A\002QBQ!Q\037A\002\t\013!\002Z5tG\006\024Hm\0247e!\tI1)\003\002E\025\t9!i\\8mK\006t\007")
public interface UntypedActorContext extends ActorContext {
  Iterable<ActorRef> getChildren();
  
  ActorRef getChild(String paramString);
  
  void become(Procedure<Object> paramProcedure);
  
  void become(Procedure<Object> paramProcedure, boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActorContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */