package scala.collection.parallel;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001E2q!\001\002\021\002G\005\021BA\bQe\026\034\027n]3Ta2LG\017^3s\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQcE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\t'Bd\027\016\036;feB\021A#\006\007\001\t\0311\002\001\"b\001/\t\tA+\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\b\"B\020\001\r\003\001\023A\0029ta2LG\017\006\002\"MA\031!eI\023\016\003\021I!\001\n\003\003\007M+\027\017E\002\021\001MAQa\n\020A\002!\nQa]5{KN\0042\001D\025,\023\tQcA\001\006=e\026\004X-\031;fIz\002\"\001\004\027\n\00552!aA%oi\")q\006\001D\001a\005)1\017\0357jiV\t\021\005")
public interface PreciseSplitter<T> extends Splitter<T> {
  Seq<PreciseSplitter<T>> psplit(Seq<Object> paramSeq);
  
  Seq<PreciseSplitter<T>> split();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\PreciseSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */