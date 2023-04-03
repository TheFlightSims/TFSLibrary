package akka.japi;

import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e<Q!\001\002\t\002\035\tA!\026;jY*\0211\001B\001\005U\006\004\030NC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t!Q\013^5m'\tIA\002\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\005\006'%!\t\001F\001\007y%t\027\016\036 \025\003\035AQAF\005\005\002]\t\001b\0317bgN$\026mZ\013\0031\005\"\"!\007\026\021\007iir$D\001\034\025\tab\"A\004sK\032dWm\031;\n\005yY\"\001C\"mCN\034H+Y4\021\005\001\nC\002\001\003\006EU\021\ra\t\002\002)F\021Ae\n\t\003\033\025J!A\n\b\003\0179{G\017[5oOB\021Q\002K\005\003S9\0211!\0218z\021\025YS\0031\001-\003\025\031G.\031>{!\ri\003g\b\b\003\0339J!a\f\b\002\rA\023X\rZ3g\023\t\t$GA\003DY\006\0348O\003\0020\035!)A'\003C\001k\005a\021.\\7vi\006\024G.Z*fcR\021ag\021\t\004oqrT\"\001\035\013\005eR\024!C5n[V$\030M\0317f\025\tYd\"\001\006d_2dWm\031;j_:L!!\020\035\003\007M+\027\017\r\002@\003B\031Q\006\r!\021\005\001\nE!\003\"4\003\003\005\tQ!\001$\005\ryFE\r\005\006\tN\002\r!R\001\004CJ\024\bcA\007G\021&\021qI\004\002\006\003J\024\030-\037\031\003\023.\0032!\f\031K!\t\0013\nB\005M\007\006\005\t\021!B\001G\t\031q\fJ\031\t\013QJA\021\001(\026\005=\023FC\001)T!\r9D(\025\t\003AI#QAI'C\002\rBQ\001R'A\002Q\0032!\004$R\021\025!\024\002\"\001W+\t9&\f\006\002Y7B\031q\007P-\021\005\001RF!\002\022V\005\004\031\003\"\002/V\001\004i\026\001C5uKJ\f'\r\\3\021\007y\033\027,D\001`\025\t\001\027-\001\003mC:<'\"\0012\002\t)\fg/Y\005\003I~\023\001\"\023;fe\006\024G.\032\005\006M&!\taZ\001\026S6lW\017^1cY\026\034\026N\\4mKR|gnU3r+\tA7\016\006\002jYB\031q\007\0206\021\005\001ZG!\002\022f\005\004\031\003\"B7f\001\004Q\027!\002<bYV,\007\"B8\n\t\003\001\030aE5n[V$\030M\0317f\023:$W\r_3e'\026\fXCA9w)\t\021x\017E\0028gVL!\001\036\035\003\025%sG-\032=fIN+\027\017\005\002!m\022)!E\034b\001G!)AL\034a\001qB\031alY;")
public final class Util {
  public static <T> IndexedSeq<T> immutableIndexedSeq(Iterable<T> paramIterable) {
    return Util$.MODULE$.immutableIndexedSeq(paramIterable);
  }
  
  public static <T> Seq<T> immutableSingletonSeq(T paramT) {
    return Util$.MODULE$.immutableSingletonSeq(paramT);
  }
  
  public static <T> Seq<T> immutableSeq(Iterable<T> paramIterable) {
    return Util$.MODULE$.immutableSeq(paramIterable);
  }
  
  public static <T> Seq<T> immutableSeq(Object paramObject) {
    return Util$.MODULE$.immutableSeq(paramObject);
  }
  
  public static Seq<Class<?>> immutableSeq(Class<?>[] paramArrayOfClass) {
    return Util$.MODULE$.immutableSeq(paramArrayOfClass);
  }
  
  public static <T> ClassTag<T> classTag(Class<T> paramClass) {
    return Util$.MODULE$.classTag(paramClass);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */