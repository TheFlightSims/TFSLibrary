package scala.runtime;

import scala.Proxy;
import scala.math.Numeric;
import scala.math.ScalaNumericAnyConversions;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q4q!\001\002\021\002\007\005qA\001\tTG\006d\027MT;nE\026\024\bK]8ys*\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\013\003\021q\031R\001A\005\016'\t\002\"AC\006\016\003\021I!\001\004\003\003\007\005s\027\020\005\002\017#5\tqB\003\002\021\t\005!Q.\031;i\023\t\021rB\001\016TG\006d\027MT;nKJL7-\0218z\007>tg/\032:tS>t7\017E\002\025/iq!AC\013\n\005Y!\021!\002)s_bL\030B\001\r\032\005\025!\026\020]3e\025\t1B\001\005\002\03491\001A!B\017\001\005\004q\"!\001+\022\005}I\001C\001\006!\023\t\tCAA\004O_RD\027N\\4\021\007\r\"#$D\001\003\023\t)#A\001\007Pe\022,'/\0323Qe>D\030\020C\003(\001\021\005\001&\001\004%S:LG\017\n\013\002SA\021!BK\005\003W\021\021A!\0268ji\")Q\006\001D\n]\005\031a.^7\026\003=\0022\001\r\035\033\035\t\tdG\004\0023k5\t1G\003\0025\r\0051AH]8pizJ\021!B\005\003o\021\tq\001]1dW\006<W-\003\002:u\t9a*^7fe&\034'BA\034\005\021\025a\004\001\"\001>\003))h\016Z3sYfLgn\032\013\002}A\021!bP\005\003\001\022\021a!\0218z%\0264\007\"\002\"\001\t\003\031\025a\0033pk\ndWMV1mk\026$\022\001\022\t\003\025\025K!A\022\003\003\r\021{WO\0317f\021\025A\005\001\"\001J\003)1Gn\\1u-\006dW/\032\013\002\025B\021!bS\005\003\031\022\021QA\0227pCRDQA\024\001\005\002=\013\021\002\\8oOZ\013G.^3\025\003A\003\"AC)\n\005I#!\001\002'p]\036DQ\001\026\001\005\002U\013\001\"\0338u-\006dW/\032\013\002-B\021!bV\005\0031\022\0211!\0238u\021\025Q\006\001\"\001\\\003%\021\027\020^3WC2,X\rF\001]!\tQQ,\003\002_\t\t!!)\037;f\021\025\001\007\001\"\001b\003)\031\bn\034:u-\006dW/\032\013\002EB\021!bY\005\003I\022\021Qa\0255peRDQA\032\001\005\002\035\f1!\\5o)\tQ\002\016C\003jK\002\007!$\001\003uQ\006$\b\"B6\001\t\003a\027aA7bqR\021!$\034\005\006S*\004\rA\007\005\006_\002!\t\001]\001\004C\n\034X#\001\016\t\013I\004A\021A:\002\rMLwM\\;n+\0051\006")
public interface ScalaNumberProxy<T> extends ScalaNumericAnyConversions, Proxy.Typed<T>, OrderedProxy<T> {
  Numeric<T> num();
  
  Object underlying();
  
  double doubleValue();
  
  float floatValue();
  
  long longValue();
  
  int intValue();
  
  byte byteValue();
  
  short shortValue();
  
  T min(T paramT);
  
  T max(T paramT);
  
  T abs();
  
  int signum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ScalaNumberProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */