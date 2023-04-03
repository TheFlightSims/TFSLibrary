package akka.routing;

import akka.actor.SupervisorStrategy;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q2\001\"\001\002\021\002\007\005AA\002\002\030!>|Gn\024<feJLG-Z+og\026$8i\0348gS\036T!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\fWCA\004''\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!\001\002)p_2DQa\005\001\005\002U\ta\001J5oSR$3\001\001\013\002-A\021\021bF\005\0031)\021A!\0268ji\")!\004\001C\0037\005\031rN^3se&$W-\0268tKR\034uN\0344jOR\021Ad\b\t\003\037uI!A\b\002\003\031I{W\017^3s\007>tg-[4\t\013\001J\002\031\001\017\002\013=$\b.\032:\t\013\t\002a\021A\022\002-]LG\017[*va\026\024h/[:peN#(/\031;fOf$\"\001\n\027\021\005\0252C\002\001\003\006O\001\021\r\001\013\002\002)F\021\021F\004\t\003\023)J!a\013\006\003\0179{G\017[5oO\")Q&\ta\001]\005A1\017\036:bi\026<\027\020\005\0020e5\t\001G\003\0022\t\005)\021m\031;pe&\0211\007\r\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020C\0036\001\031\005a'A\006xSRD'+Z:ju\026\024HC\001\0238\021\025AD\0071\001:\003\035\021Xm]5{KJ\004\"a\004\036\n\005m\022!a\002*fg&TXM\035")
public interface PoolOverrideUnsetConfig<T extends Pool> extends Pool {
  RouterConfig overrideUnsetConfig(RouterConfig paramRouterConfig);
  
  T withSupervisorStrategy(SupervisorStrategy paramSupervisorStrategy);
  
  T withResizer(Resizer paramResizer);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\PoolOverrideUnsetConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */