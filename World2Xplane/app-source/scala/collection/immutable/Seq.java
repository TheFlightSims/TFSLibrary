package scala.collection.immutable;

import scala.collection.Parallelizable;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q4q!\001\002\021\002\007\005\021BA\002TKFT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\026'\035\0011b\004\020\"Q1\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\t\023R,'/\0312mKB\021A#\006\007\001\t\0311\002\001\"b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bcA\020!'5\tA!\003\002\002\tA!!%J\n(\033\005\031#B\001\023\005\003\0359WM\\3sS\016L!AJ\022\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\021\005A\001\001\003B\020*'-J!A\013\003\003\017M+\027\017T5lKB\031\001\003A\n\021\t}i3cL\005\003]\021\021a\002U1sC2dW\r\\5{C\ndW\rE\0021iMi\021!\r\006\003\007IR!a\r\003\002\021A\f'/\0317mK2L!!N\031\003\rA\013'oU3r\021\0259\004\001\"\0019\003\031!\023N\\5uIQ\t\021\b\005\002\ru%\0211H\002\002\005+:LG\017C\003>\001\021\005c(A\005d_6\004\030M\\5p]V\tq\bE\002#\001\036J!!Q\022\003!\035+g.\032:jG\016{W\016]1oS>t\007\"B\"\001\t\003\"\025!\002;p'\026\fX#A\026\t\013\031\003A\021\t#\002\007M,\027\017\003\004I\001\001&\t&S\001\fa\006\0248i\\7cS:,'/F\001K!\021YEjE\030\016\003IJ!!\024\032\003\021\r{WNY5oKJ<Qa\024\002\t\002A\0131aU3r!\t\001\022KB\003\002\005!\005!k\005\002R'B\031!\005V\024\n\005U\033#AC*fc\032\0137\r^8ss\")q+\025C\0011\0061A(\0338jiz\"\022\001\025\005\0065F#\031aW\001\rG\006t')^5mI\032\023x.\\\013\0039\026,\022!\030\t\006Ey\003GMZ\005\003?\016\022AbQ1o\005VLG\016\032$s_6\004\"!\0312\016\003EK!a\031!\003\t\r{G\016\034\t\003)\025$QAF-C\002]\0012\001\005\001e\021\025A\027\013\"\001j\003)qWm\036\"vS2$WM]\013\003UJ,\022a\033\t\005Y>\f8/D\001n\025\tqG!A\004nkR\f'\r\\3\n\005Al'a\002\"vS2$WM\035\t\003)I$QAF4C\002]\0012\001\005\001r\001")
public interface Seq<A> extends Iterable<A>, Seq<A>, GenericTraversableTemplate<A, Seq>, SeqLike<A, Seq<A>>, Parallelizable<A, ParSeq<A>> {
  GenericCompanion<Seq> companion();
  
  Seq<A> toSeq();
  
  Seq<A> seq();
  
  Combiner<A, ParSeq<A>> parCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Seq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */