package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q3q!\001\002\021\002\007\005qAA\005MS:,\027M]*fc*\0211\001B\001\013G>dG.Z2uS>t'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bE\n\006\001%iAd\t\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\020#5\t!!\003\002\021\005\t\0311+Z9\021\005I\031B\002\001\003\007)\001!)\031A\013\003\003\005\013\"AF\r\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\003\016\n\005m!!aA!osB!Q\004I\t#\033\005q\"BA\020\003\003\0359WM\\3sS\016L!!\t\020\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\021\0059\001\001\003\002\b%#\031J!!\n\002\003\0331Kg.Z1s'\026\fH*[6f!\rq\001!\005\005\006Q\001!\t!K\001\007I%t\027\016\036\023\025\003)\002\"AC\026\n\0051\"!\001B+oSRDQA\f\001\005B=\n\021bY8na\006t\027n\0348\026\003A\0022!H\031#\023\t\021dD\001\tHK:,'/[2D_6\004\030M\\5p]\")A\007\001C!k\005\0311/Z9\026\003\031:Qa\016\002\t\002a\n\021\002T5oK\006\0248+Z9\021\0059Id!B\001\003\021\003Q4CA\035<!\riBHI\005\003{y\021!bU3r\r\006\034Go\034:z\021\025y\024\b\"\001A\003\031a\024N\\5u}Q\t\001\bC\003Cs\021\r1)\001\007dC:\024U/\0337e\rJ|W.\006\002E\033V\tQ\tE\003\036\r\"ce*\003\002H=\ta1)\0318Ck&dGM\022:p[B\021\021JS\007\002s%\0211*\r\002\005\007>dG\016\005\002\023\033\022)A#\021b\001+A\031a\002\001'\t\013AKD\021A)\002\0259,wOQ;jY\022,'/\006\002S5V\t1\013\005\003U/f[V\"A+\013\005Y\023\021aB7vi\006\024G.Z\005\0031V\023qAQ;jY\022,'\017\005\002\0235\022)Ac\024b\001+A\031a\002A-")
public interface LinearSeq<A> extends Seq<A>, GenericTraversableTemplate<A, LinearSeq>, LinearSeqLike<A, LinearSeq<A>> {
  GenericCompanion<LinearSeq> companion();
  
  LinearSeq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\LinearSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */