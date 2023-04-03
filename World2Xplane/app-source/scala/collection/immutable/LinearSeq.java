package scala.collection.immutable;

import scala.collection.LinearSeq;
import scala.collection.LinearSeqLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0054q!\001\002\021\002\007\005\021BA\005MS:,\027M]*fc*\0211\001B\001\nS6lW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\013\024\r\001YqBH\021)!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\002\n\005I\021!aA*fcB\021A#\006\007\001\t\0311\002\001\"b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bcA\020!'5\tA!\003\002\002\tA!!%J\n(\033\005\031#B\001\023\005\003\0359WM\\3sS\016L!AJ\022\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\021\005A\001\001\003B\020*'-J!A\013\003\003\0331Kg.Z1s'\026\fH*[6f!\r\001\002a\005\005\006[\001!\tAL\001\007I%t\027\016\036\023\025\003=\002\"\001\004\031\n\005E2!\001B+oSRDQa\r\001\005BQ\n\021bY8na\006t\027n\0348\026\003U\0022A\t\034(\023\t94E\001\tHK:,'/[2D_6\004\030M\\5p]\")\021\b\001C!u\005\0311/Z9\026\003-:Q\001\020\002\t\002u\n\021\002T5oK\006\0248+Z9\021\005Aqd!B\001\003\021\003y4C\001 A!\r\021\023iJ\005\003\005\016\022!bU3r\r\006\034Go\034:z\021\025!e\b\"\001F\003\031a\024N\\5u}Q\tQ\bC\003H}\021\r\001*\001\007dC:\024U/\0337e\rJ|W.\006\002J%V\t!\nE\003#\0276\0136+\003\002MG\ta1)\0318Ck&dGM\022:p[B\021ajT\007\002}%\021\001K\016\002\005\007>dG\016\005\002\025%\022)aC\022b\001/A\031\001\003A)\t\013UsD\021\001,\002\0259,wOQ;jY\022,'/\006\002X?V\t\001\f\005\003Z9z\003W\"\001.\013\005m#\021aB7vi\006\024G.Z\005\003;j\023qAQ;jY\022,'\017\005\002\025?\022)a\003\026b\001/A\031\001\003\0010")
public interface LinearSeq<A> extends Seq<A>, LinearSeq<A>, GenericTraversableTemplate<A, LinearSeq>, LinearSeqLike<A, LinearSeq<A>> {
  GenericCompanion<LinearSeq> companion();
  
  LinearSeq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LinearSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */