package akka.pattern;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)3q!\001\002\021\002\007\005qA\001\006Bg.\034V\017\0359peRT!a\001\003\002\017A\fG\017^3s]*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\023j]&$H\005F\001\022!\tI!#\003\002\024\025\t!QK\\5u\021\025)\002\001b\001\027\003\r\t7o\033\013\003/m\001\"\001G\r\016\003\tI!A\007\002\003\037\005\0338.\0312mK\006\033Go\034:SK\032DQ\001\b\013A\002u\t\001\"Y2u_J\024VM\032\t\003=\005j\021a\b\006\003A\021\tQ!Y2u_JL!AI\020\003\021\005\033Go\034:SK\032DQ!\006\001\005\002\021\"2!J\0349)\t1s\006E\002(U1j\021\001\013\006\003S)\t!bY8oGV\024(/\0328u\023\tY\003F\001\004GkR,(/\032\t\003\0235J!A\f\006\003\007\005s\027\020C\0031G\001\017\021'A\004uS6,w.\036;\021\005I*T\"A\032\013\005Q\"\021\001B;uS2L!AN\032\003\017QKW.Z8vi\")Ad\ta\001;!)\021h\ta\001Y\0059Q.Z:tC\036,\007\"B\013\001\t\007YDC\001\037@!\tAR(\003\002?\005\t)\022i]6bE2,\027i\031;peN+G.Z2uS>t\007\"\002!;\001\004\t\025AD1di>\0248+\0327fGRLwN\034\t\003=\tK!aQ\020\003\035\005\033Go\034:TK2,7\r^5p]\")Q\003\001C\001\013R\031a\tS%\025\005\031:\005\"\002\031E\001\b\t\004\"\002!E\001\004\t\005\"B\035E\001\004a\003")
public interface AskSupport {
  ActorRef ask(ActorRef paramActorRef);
  
  Future<Object> ask(ActorRef paramActorRef, Object paramObject, Timeout paramTimeout);
  
  ActorSelection ask(ActorSelection paramActorSelection);
  
  Future<Object> ask(ActorSelection paramActorSelection, Object paramObject, Timeout paramTimeout);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */