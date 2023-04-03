package scala.util.parsing.combinator;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001u2q!\001\002\021\002\007\0051B\001\tKCZ\fGk\\6f]B\013'o]3sg*\0211\001B\001\013G>l'-\0338bi>\024(BA\003\007\003\035\001\030M]:j]\036T!a\002\005\002\tU$\030\016\034\006\002\023\005)1oY1mC\016\0011c\001\001\r!A\021QBD\007\002\021%\021q\002\003\002\007\003:L(+\0324\021\005E\021R\"\001\002\n\005M\021!\001\004*fO\026D\b+\031:tKJ\034\b\"B\013\001\t\0031\022A\002\023j]&$H\005F\001\030!\ti\001$\003\002\032\021\t!QK\\5u\021\025Y\002\001\"\001\035\003\025IG-\0328u+\005i\002c\001\020 G5\t\001!\003\002!C\t1\001+\031:tKJL!A\t\002\003\017A\013'o]3sgB\021Ae\n\b\003\033\025J!A\n\005\002\rA\023X\rZ3g\023\tA\023F\001\004TiJLgn\032\006\003M!AQa\013\001\005\002q\t1b\0365pY\026tU/\0342fe\")Q\006\001C\0019\005iA-Z2j[\006dg*^7cKJDQa\f\001\005\002q\tQb\035;sS:<G*\033;fe\006d\007\006\002\0302oe\002\"AM\033\016\003MR!\001\016\005\002\025\005tgn\034;bi&|g.\003\0027g\tIQ.[4sCRLwN\\\021\002q\005i\006m\035;sS:<G*\033;fe\006d\007\rI1mY><8\017I3tG\006\004\030N\\4!g&tw\r\\3!C:$\007\005Z8vE2,\007%];pi\026\034H\006\t2vi\002rw\016\036\021g_J<\030M\0353!g2\f7\017[3tA\005t\027\020\t7p]\036,'OL\021\002u\0051!GL\0311]ABQ\001\020\001\005\002q\t1C\0327pCRLgn\032)pS:$h*^7cKJ\004")
public interface JavaTokenParsers extends RegexParsers {
  Parsers.Parser<String> ident();
  
  Parsers.Parser<String> wholeNumber();
  
  Parsers.Parser<String> decimalNumber();
  
  Parsers.Parser<String> stringLiteral();
  
  Parsers.Parser<String> floatingPointNumber();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\JavaTokenParsers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */