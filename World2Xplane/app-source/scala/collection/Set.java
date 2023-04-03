package scala.collection;

import scala.Function1;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001A4q!\001\002\021\002\007\005qAA\002TKRT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)\"\001\003\n\024\017\001IQB\b\022&YA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t)q\001cG\005\003\037\021\021\021BR;oGRLwN\\\031\021\005E\021B\002\001\003\006'\001\021\r\001\006\002\002\003F\021Q\003\007\t\003\025YI!a\006\003\003\0179{G\017[5oOB\021!\"G\005\0035\021\0211!\0218z!\tQA$\003\002\036\t\t9!i\\8mK\006t\007cA\020!!5\t!!\003\002\"\005\tA\021\n^3sC\ndW\rE\002 GAI!\001\n\002\003\r\035+gnU3u!\0211\023\006E\026\016\003\035R!\001\013\002\002\017\035,g.\032:jG&\021!f\n\002\023\017\026tWM]5d'\026$H+Z7qY\006$X\r\005\002 \001A!q$\f\t0\023\tq#AA\004TKRd\025n[3\021\007}\001\001\003C\0032\001\021\005!'\001\004%S:LG\017\n\013\002gA\021!\002N\005\003k\021\021A!\0268ji\")q\007\001C!q\005I1m\\7qC:LwN\\\013\002sA\031aEO\026\n\005m:#\001E$f]\026\024\030nY\"p[B\fg.[8o\021\025i\004\001\"\021?\003\r\031X-]\013\002_\035)\001I\001E\001\003\006\0311+\032;\021\005}\021e!B\001\003\021\003\0315C\001\"E!\r1SiK\005\003\r\036\022!bU3u\r\006\034Go\034:z\021\025A%\t\"\001J\003\031a\024N\\5u}Q\t\021\tC\003L\005\022\005A*\001\006oK^\024U/\0337eKJ,\"!T+\026\0039\003Ba\024*U-6\t\001K\003\002R\005\0059Q.\036;bE2,\027BA*Q\005\035\021U/\0337eKJ\004\"!E+\005\013MQ%\031\001\013\021\007]SF+D\001Y\025\tI&!A\005j[6,H/\0312mK&\021\021\001\027\005\0069\n#\t%X\001\006K6\004H/_\013\003=\006,\022a\030\t\004?\001\001\007CA\tb\t\025\0312L1\001\025\021\025\031'\tb\001e\0031\031\027M\034\"vS2$gI]8n+\t)g.F\001g!\0251s-[7p\023\tAwE\001\007DC:\024U/\0337e\rJ|W\016\005\002kW6\t!)\003\002mu\t!1i\0347m!\t\tb\016B\003\024E\n\007A\003E\002 \0015\004")
public interface Set<A> extends Function1<A, Object>, Iterable<A>, GenSet<A>, GenericSetTemplate<A, Set>, SetLike<A, Set<A>> {
  GenericCompanion<Set> companion();
  
  Set<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Set.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */