package akka.actor;

import java.io.ObjectOutputStream;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Iterable;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes = "\006\001\005eaaB\001\003!\003\r\ta\002\002\r\003\016$xN]\"p]R,\007\020\036\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\024\007\001Aa\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021q\"Q2u_J\024VM\032$bGR|'/\037\005\006'\001!\t\001F\001\007I%t\027\016\036\023\025\003U\001\"!\003\f\n\005]Q!\001B+oSRDQ!\007\001\007\002i\tAa]3mMV\t1\004\005\002\0209%\021QD\001\002\t\003\016$xN\035*fM\")q\004\001D\001A\005)\001O]8qgV\t\021\005\005\002\020E%\0211E\001\002\006!J|\007o\035\005\006K\0011\tAJ\001\017e\026\034W-\033<f)&lWm\\;u+\0059\003C\001\025.\033\005I#B\001\026,\003!!WO]1uS>t'B\001\027\013\003)\031wN\\2veJ,g\016^\005\003]%\022\001\002R;sCRLwN\034\005\006a\0011\t!M\001\022g\026$(+Z2fSZ,G+[7f_V$HCA\0133\021\025\031t\0061\001(\003\035!\030.\\3pkRDQ!\016\001\005\002Y\naAY3d_6,GCA\0138\021\025AD\0071\001:\003!\021W\r[1wS>\024\bC\001\036>\035\ty1(\003\002=\005\005)\021i\031;pe&\021ah\020\002\b%\026\034W-\033<f\025\ta$\001C\0036\001\031\005\021\tF\002\026\005\016CQ\001\017!A\002eBQ\001\022!A\002\025\013!\002Z5tG\006\024Hm\0247e!\tIa)\003\002H\025\t9!i\\8mK\006t\007\"B%\001\r\003!\022\001C;oE\026\034w.\\3\t\013-\003a\021\001'\002\rM,g\016Z3s)\005Y\002\"\002(\001\r\003y\025\001C2iS2$'/\0328\026\003A\0032!\025,\034\033\005\021&BA*U\003%IW.\\;uC\ndWM\003\002V\025\005Q1m\0347mK\016$\030n\0348\n\005]\023&\001C%uKJ\f'\r\\3\t\013e\003a\021\001.\002\013\rD\027\016\0343\025\005ms\006cA\005]7%\021QL\003\002\007\037B$\030n\0348\t\013}C\006\031\0011\002\t9\fW.\032\t\003C\022t!!\0032\n\005\rT\021A\002)sK\022,g-\003\002fM\n11\013\036:j]\036T!a\031\006\t\013!\004a1A5\002\025\021L7\017]1uG\",'/F\001k!\tYG.D\001,\023\ti7F\001\rFq\026\034W\017^5p]\016{g\016^3yi\026CXmY;u_JDQa\034\001\007\004A\faa]=ti\026lW#A9\021\005=\021\030BA:\003\005-\t5\r^8s'f\034H/Z7\t\013U\004a\021\001\016\002\rA\f'/\0328u\021\0259\bA\"\001y\003\0259\030\r^2i)\tY\022\020C\003{m\002\0071$A\004tk\nTWm\031;\t\013q\004a\021A?\002\017Utw/\031;dQR\0211D \005\006un\004\ra\007\005\b\003\003\001AQCA\002\003-9(/\033;f\037\nTWm\031;\025\007U\t)\001C\004\002\b}\004\r!!\003\002\003=\004B!a\003\002\0265\021\021Q\002\006\005\003\037\t\t\"\001\002j_*\021\0211C\001\005U\0064\030-\003\003\002\030\0055!AE(cU\026\034GoT;uaV$8\013\036:fC6\004")
public interface ActorContext extends ActorRefFactory {
  ActorRef self();
  
  Props props();
  
  Duration receiveTimeout();
  
  void setReceiveTimeout(Duration paramDuration);
  
  void become(PartialFunction<Object, BoxedUnit> paramPartialFunction);
  
  void become(PartialFunction<Object, BoxedUnit> paramPartialFunction, boolean paramBoolean);
  
  void unbecome();
  
  ActorRef sender();
  
  Iterable<ActorRef> children();
  
  Option<ActorRef> child(String paramString);
  
  ExecutionContextExecutor dispatcher();
  
  ActorSystem system();
  
  ActorRef parent();
  
  ActorRef watch(ActorRef paramActorRef);
  
  ActorRef unwatch(ActorRef paramActorRef);
  
  void writeObject(ObjectOutputStream paramObjectOutputStream);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */