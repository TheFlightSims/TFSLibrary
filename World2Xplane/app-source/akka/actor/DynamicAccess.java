package akka.actor;

import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@ScalaSignature(bytes = "\006\001\005\005b!B\001\003\003\0039!!\004#z]\006l\027nY!dG\026\0348O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\037j]&$h\bF\001\022!\t\021\002!D\001\003\021\025!\002A\"\001\026\003E\031'/Z1uK&s7\017^1oG\0264uN]\013\003-\001\"2aF\031?)\tA\022\006E\002\0329yi\021A\007\006\0037)\tA!\036;jY&\021QD\007\002\004)JL\bCA\020!\031\001!Q!I\nC\002\t\022\021\001V\t\003G\031\002\"!\003\023\n\005\025R!a\002(pi\"Lgn\032\t\003\023\035J!\001\013\006\003\007\005s\027\020C\004+'\005\005\t9A\026\002\025\0254\030\016Z3oG\026$\023\007E\002-_yi\021!\f\006\003])\tqA]3gY\026\034G/\003\0021[\tA1\t\\1tgR\013w\rC\0033'\001\0071'A\003dY\006T(\020\r\0025yA\031Q\007O\036\017\005%1\024BA\034\013\003\031\001&/\0323fM&\021\021H\017\002\006\0072\f7o\035\006\003o)\001\"a\b\037\005\023u\n\024\021!A\001\006\003\021#aA0%c!)qh\005a\001\001\006!\021M]4t!\r\te\tS\007\002\005*\0211\tR\001\nS6lW\017^1cY\026T!!\022\006\002\025\r|G\016\\3di&|g.\003\002H\005\n\0311+Z9\021\t%I5\nC\005\003\025*\021a\001V;qY\026\024\004G\001'O!\r)\004(\024\t\003?9#\021b\024 \002\002\003\005)\021\001\022\003\007}##\007C\003R\001\031\005!+A\006hKR\034E.Y:t\r>\024XCA*^)\t!\026\r\006\002V=B\031\021\004\b,1\005]K\006cA\03391B\021q$\027\003\n5B\013\t\021!A\003\002m\0231a\030\0234#\t\031C\f\005\002 ;\022)\021\005\025b\001E!9q\fUA\001\002\b\001\027AC3wS\022,gnY3%eA\031Af\f/\t\013\t\004\006\031A2\002\t\031\f8M\034\t\003k\021L!!\032\036\003\rM#(/\0338h\021\025!\002A\"\001h+\tAG\016F\002jaF$\"A[7\021\007ea2\016\005\002 Y\022)\021E\032b\001E!9aNZA\001\002\by\027AC3wS\022,gnY3%gA\031AfL6\t\013\t4\007\031A2\t\013}2\007\031\001:\021\007\00535\017\005\003\n\023RD\001GA;x!\r)\004H\036\t\003?]$\021\002_9\002\002\003\005)\021\001\022\003\007}#C\007C\003{\001\031\00510\001\007hKR|%M[3di\032{'/F\002}\003\003!2!`A\005)\rq\0301\001\t\0043qy\bcA\020\002\002\021)\021%\037b\001E!I\021QA=\002\002\003\017\021qA\001\013KZLG-\0328dK\022\"\004c\001\0270\")!-\037a\001G\"9\021Q\002\001\007\002\005=\021aC2mCN\034Hj\\1eKJ,\"!!\005\021\t\005M\021QD\007\003\003+QA!a\006\002\032\005!A.\0318h\025\t\tY\"\001\003kCZ\f\027\002BA\020\003+\0211b\0217bgNdu.\0313fe\002")
public abstract class DynamicAccess {
  public abstract <T> Try<T> createInstanceFor(Class<?> paramClass, Seq<Tuple2<Class<?>, Object>> paramSeq, ClassTag<T> paramClassTag);
  
  public abstract <T> Try<Class<? extends T>> getClassFor(String paramString, ClassTag<T> paramClassTag);
  
  public abstract <T> Try<T> createInstanceFor(String paramString, Seq<Tuple2<Class<?>, Object>> paramSeq, ClassTag<T> paramClassTag);
  
  public abstract <T> Try<T> getObjectFor(String paramString, ClassTag<T> paramClassTag);
  
  public abstract ClassLoader classLoader();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DynamicAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */