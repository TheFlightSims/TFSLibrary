package scala.collection.mutable;

import scala.Cloneable;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m3q!\001\002\021\002\007\005\021B\001\004Ck\0324WM\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M1\001aC\b\037K%\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\004'\026\f\bC\001\013\026\031\001!QA\006\001C\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\003 EM!S\"\001\021\013\005\005\"\021aB4f]\026\024\030nY\005\003G\001\022!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004\"\001\005\001\021\tA13\003K\005\003O\t\021!BQ;gM\026\024H*[6f!\r\001\002a\005\t\003\031)J!a\013\004\003\023\rcwN\\3bE2,\007\"B\027\001\t\003q\023A\002\023j]&$H\005F\0010!\ta\001'\003\0022\r\t!QK\\5u\021\025\031\004\001\"\0215\003%\031w.\0349b]&|g.F\0016!\ryb\007J\005\003o\001\022\001cR3oKJL7mQ8na\006t\027n\0348\b\013e\022\001\022\001\036\002\r\t+hMZ3s!\t\0012HB\003\002\005!\005Ah\005\002<{A\031qD\020\023\n\005}\002#AC*fc\032\0137\r^8ss\")\021i\017C\001\005\0061A(\0338jiz\"\022A\017\005\006\tn\"\031!R\001\rG\006t')^5mI\032\023x.\\\013\003\r>+\022a\022\t\006?!Se\nU\005\003\023\002\022AbQ1o\005VLG\016\032$s_6\004\"a\023'\016\003mJ!!\024\034\003\t\r{G\016\034\t\003)=#QAF\"C\002]\0012\001\005\001O\021\025\0216\b\"\001T\003)qWm\036\"vS2$WM]\013\003)f+\022!\026\t\005!YC&,\003\002X\005\t9!)^5mI\026\024\bC\001\013Z\t\0251\022K1\001\030!\r\001\002\001\027")
public interface Buffer<A> extends Seq<A>, GenericTraversableTemplate<A, Buffer>, BufferLike<A, Buffer<A>>, Cloneable {
  GenericCompanion<Buffer> companion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Buffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */