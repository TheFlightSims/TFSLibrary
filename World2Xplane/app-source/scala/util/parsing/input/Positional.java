package scala.util.parsing.input;

import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002\007\0051B\001\006Q_NLG/[8oC2T!a\001\003\002\013%t\007/\036;\013\005\0251\021a\0029beNLgn\032\006\003\017!\tA!\036;jY*\t\021\"A\003tG\006d\027m\001\001\024\005\001a\001CA\007\017\033\005A\021BA\b\t\005\031\te.\037*fM\")\021\003\001C\001%\0051A%\0338ji\022\"\022a\005\t\003\033QI!!\006\005\003\tUs\027\016\036\005\b/\001\001\r\021\"\001\031\003\r\001xn]\013\0023A\021!dG\007\002\005%\021AD\001\002\t!>\034\030\016^5p]\"9a\004\001a\001\n\003y\022a\0029pg~#S-\035\013\003'\001Bq!I\017\002\002\003\007\021$A\002yIEBaa\t\001!B\023I\022\001\0029pg\002BQ!\n\001\005\002\031\naa]3u!>\034HCA\024)\033\005\001\001\"B\025%\001\004I\022A\0028foB|7\017")
public interface Positional {
  Position pos();
  
  @TraitSetter
  void pos_$eq(Position paramPosition);
  
  Positional setPos(Position paramPosition);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\Positional.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */