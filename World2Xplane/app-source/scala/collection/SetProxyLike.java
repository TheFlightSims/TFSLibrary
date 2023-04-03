package scala.collection;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001%4q!\001\002\021\002\007\005qA\001\007TKR\004&o\034=z\031&\\WM\003\002\004\t\005Q1m\0347mK\016$\030n\0348\013\003\025\tQa]2bY\006\034\001!F\002\t'u\031B\001A\005\016MA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t9y\021\003H\007\002\005%\021\001C\001\002\b'\026$H*[6f!\t\0212\003\004\001\005\013Q\001!\031A\013\003\003\005\013\"AF\r\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\003\016\n\005m!!aA!osB\021!#\b\003\007=\001!)\031A\020\003\tQC\027n]\t\003-\001\0222!I\007$\r\021\021\003\001\001\021\003\031q\022XMZ5oK6,g\016\036 \021\0079!\023#\003\002&\005\t\0311+\032;\021\t99\023\003H\005\003Q\t\021\021#\023;fe\006\024G.\032)s_bLH*[6f\021\025Q\003\001\"\001,\003\031!\023N\\5uIQ\tA\006\005\002\013[%\021a\006\002\002\005+:LG\017C\0031\001\031\005\021'A\003f[B$\0300F\001\035\021\025\031\004\001\"\0215\003!\031wN\034;bS:\034HCA\0339!\tQa'\003\0028\t\t9!i\\8mK\006t\007\"B\0353\001\004\t\022\001B3mK6DQa\017\001\005Bq\nQ\001\n9mkN$\"\001H\037\t\013eR\004\031A\t\t\013}\002A\021\t!\002\r\021j\027N\\;t)\ta\022\tC\003:}\001\007\021\003C\003D\001\021\005C)A\004jg\026k\007\017^=\026\003UBQA\022\001\005B\035\013Q!\0319qYf$\"!\016%\t\013e*\005\031A\t\t\013)\003A\021I&\002\023%tG/\032:tK\016$HC\001\017M\021\025i\025\n1\001O\003\021!\b.\031;\021\0079y\025#\003\002Q\005\t1q)\0328TKRDQA\025\001\005BM\013A\001J1naR\021A\004\026\005\006\033F\003\rA\024\005\006-\002!\teV\001\006k:LwN\034\013\0039aCQ!T+A\0029CQA\027\001\005Bm\013A\001\n2beR\021A\004\030\005\006\033f\003\rA\024\005\006=\002!\teX\001\005I&4g\r\006\002\035A\")Q*\030a\001\035\")!\r\001C!G\006QA%Y7qIQLG\016Z3\025\005q!\007\"B'b\001\004q\005\"\0024\001\t\003:\027\001C:vEN,Go\0244\025\005UB\007\"B'f\001\004q\005")
public interface SetProxyLike<A, This extends SetLike<A, This> & Set<A>> extends SetLike<A, This>, IterableProxyLike<A, This> {
  This empty();
  
  boolean contains(A paramA);
  
  This $plus(A paramA);
  
  This $minus(A paramA);
  
  boolean isEmpty();
  
  boolean apply(A paramA);
  
  This intersect(GenSet<A> paramGenSet);
  
  This $amp(GenSet<A> paramGenSet);
  
  This union(GenSet<A> paramGenSet);
  
  This $bar(GenSet<A> paramGenSet);
  
  This diff(GenSet<A> paramGenSet);
  
  This $amp$tilde(GenSet<A> paramGenSet);
  
  boolean subsetOf(GenSet<A> paramGenSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SetProxyLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */