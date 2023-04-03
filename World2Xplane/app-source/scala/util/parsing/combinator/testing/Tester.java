/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import scala.Console$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.lexical.Scanners;
/*    */ import scala.util.parsing.combinator.syntactical.TokenParsers;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=3Q!\001\002\002\0025\021a\001V3ti\026\024(BA\002\005\003\035!Xm\035;j]\036T!!\002\004\002\025\r|WNY5oCR|'O\003\002\b\021\0059\001/\031:tS:<'BA\005\013\003\021)H/\0337\013\003-\tQa]2bY\006\034\001a\005\002\001\035A\021q\002E\007\002\025%\021\021C\003\002\007\003:L(+\0324\t\013M\001A\021\001\013\002\rqJg.\033;?)\005)\002C\001\f\001\033\005\021\001b\002\r\001\005\0045\t!G\001\ngftG/Y2uS\016,\022A\007\n\0037u1A\001\b\001\0015\taAH]3gS:,W.\0328u}A\021a$I\007\002?)\021\001\005B\001\fgftG/Y2uS\016\fG.\003\002#?\taAk\\6f]B\013'o]3sg\"9Ae\007b\001\016\003*\023a\0027fq&\034\027\r\\\013\002MA\021q%K\007\002Q)\021A\005B\005\003U!\022q\001T3yS\016\fG\016C\004-\001\t\007i\021A\027\002\rA\f'o]3s+\005q\003cA\0302k9\021\001gF\007\002\001%\021!g\r\002\007!\006\0248/\032:\n\005Q\"!a\002)beN,'o\035\t\003\037YJ!a\016\006\003\007\005s\027\020C\003:\001\021\005!(\001\003uKN$HCA\036?!\tyA(\003\002>\025\t!QK\\5u\021\025y\004\b1\001A\003\tIg\016\005\002B\t:\021qBQ\005\003\007*\ta\001\025:fI\0264\027BA#G\005\031\031FO]5oO*\0211I\003\025\005\001![U\n\005\002\020\023&\021!J\003\002\013I\026\004(/Z2bi\026$\027%\001'\0025QC\027n\035\021dY\006\0348\017I<jY2\004#-\032\021sK6|g/\0323\"\0039\013aA\r\0302a9\002\004")
/*    */ public abstract class Tester {
/*    */   public abstract TokenParsers syntactic();
/*    */   
/*    */   public abstract Parsers.Parser<Object> parser();
/*    */   
/*    */   public void test(String in) {
/* 42 */     Console$.MODULE$.println((new StringBuilder()).append("\nin : ").append(in).toString());
/* 43 */     Console$.MODULE$.println(syntactic().phrase(parser()).apply((Reader)new Scanners.Scanner((Scanners)syntactic().lexical(), in)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Tester.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */