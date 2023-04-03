package scala.collection.concurrent;

import scala.Option;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0013q!\001\002\021\002G\005\021BA\002NCBT!a\001\003\002\025\r|gnY;se\026tGO\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013-\001\0322\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\005!M!r$D\001\022\025\t\021B!A\004nkR\f'\r\\3\n\005\005\t\002CA\013\027\031\001!Qa\006\001C\002a\021\021!Q\t\0033q\001\"\001\004\016\n\005m1!a\002(pi\"Lgn\032\t\003\031uI!A\b\004\003\007\005s\027\020\005\002\026A\021)\021\005\001b\0011\t\t!\tC\003$\001\031\005A%A\006qkRLe-\0212tK:$HcA\023)UA\031ABJ\020\n\005\0352!AB(qi&|g\016C\003*E\001\007A#A\001l\021\025Y#\0051\001 \003\0051\b\"B\027\001\r\003q\023A\002:f[>4X\rF\0020eM\002\"\001\004\031\n\005E2!a\002\"p_2,\027M\034\005\006S1\002\r\001\006\005\006W1\002\ra\b\005\006k\0011\tAN\001\be\026\004H.Y2f)\021ys\007\017\036\t\013%\"\004\031\001\013\t\013e\"\004\031A\020\002\021=dGM^1mk\026DQa\017\033A\002}\t\001B\\3xm\006dW/\032\005\006k\0011\t!\020\013\004Kyz\004\"B\025=\001\004!\002\"B\026=\001\004y\002")
public interface Map<A, B> extends Map<A, B> {
  Option<B> putIfAbsent(A paramA, B paramB);
  
  boolean remove(A paramA, B paramB);
  
  boolean replace(A paramA, B paramB1, B paramB2);
  
  Option<B> replace(A paramA, B paramB);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */