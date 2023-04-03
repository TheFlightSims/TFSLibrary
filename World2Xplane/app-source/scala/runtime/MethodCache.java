package scala.runtime;

import java.lang.reflect.Method;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t3Q!\001\002\002\"\035\0211\"T3uQ>$7)Y2iK*\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\n\003\001!\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PU3g\021\025i\001\001\"\001\017\003\031a\024N\\5u}Q\tq\002\005\002\021\0015\t!\001C\003\023\001\031\0051#\001\003gS:$GC\001\013\037!\t)B$D\001\027\025\t9\002$A\004sK\032dWm\031;\013\005eQ\022\001\0027b]\036T\021aG\001\005U\0064\030-\003\002\036-\t1Q*\032;i_\022DQaH\tA\002\001\n1BZ8s%\026\034W-\033<feB\022\021e\n\t\004E\r*S\"\001\r\n\005\021B\"!B\"mCN\034\bC\001\024(\031\001!\021\002\013\020\002\002\003\005)\021A\025\003\007}#\023'\005\002+[A\021\021bK\005\003Y\021\021qAT8uQ&tw\r\005\002\n]%\021q\006\002\002\004\003:L\b\"B\031\001\r\003\021\024aA1eIR\031qbM\035\t\013}\001\004\031\001\0331\005U:\004c\001\022$mA\021ae\016\003\nqM\n\t\021!A\003\002%\0221a\030\0233\021\025Q\004\0071\001\025\003%1wN]'fi\"|G-\013\003\001yy\002\025BA\037\003\005A)U\016\035;z\033\026$\bn\0343DC\016DW-\003\002@\005\tyQ*Z4b\033\026$\bn\0343DC\016DW-\003\002B\005\ty\001k\0347z\033\026$\bn\0343DC\016DW\r")
public abstract class MethodCache {
  public abstract Method find(Class<?> paramClass);
  
  public abstract MethodCache add(Class<?> paramClass, Method paramMethod);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\MethodCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */