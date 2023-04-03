package scala.util.parsing.ast;

import scala.reflect.ScalaSignature;
import scala.util.parsing.input.Positional;

@ScalaSignature(bytes = "\006\001\t3q!\001\002\021\002\007\0051B\001\bBEN$(/Y2u'ftG/\031=\013\005\r!\021aA1ti*\021QAB\001\ba\006\0248/\0338h\025\t9\001\"\001\003vi&d'\"A\005\002\013M\034\027\r\\1\004\001M\021\001\001\004\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007\"B\t\001\t\003\021\022A\002\023j]&$H\005F\001\024!\tiA#\003\002\026\021\t!QK\\5u\r\0359\002\001%A\022\002a\021q!\0227f[\026tGoE\002\027\031e\001\"AG\017\016\003mQ!\001\b\003\002\013%t\007/\036;\n\005yY\"A\003)pg&$\030n\0348bY\0329\001\005\001I\001\004\003\t#a\003(b[\026,E.Z7f]R\0342a\b\007#!\t\031c#D\001\001\021\025\tr\004\"\001\023\021\0251sD\"\001(\003\021q\027-\\3\026\003!\002\"!\013\027\017\0055Q\023BA\026\t\003\031\001&/\0323fM&\021QF\f\002\007'R\024\030N\\4\013\005-B\001\"\002\031 \t\003\n\024AB3rk\006d7\017\006\0023kA\021QbM\005\003i!\021qAQ8pY\026\fg\016C\0037_\001\007q'\001\003uQ\006$\bCA\0079\023\tI\004BA\002B]fDC\001A\036?\001B\021Q\002P\005\003{!\021!\002Z3qe\026\034\027\r^3eC\005y\024A\007+iSN\0043\r\\1tg\002:\030\016\0347!E\026\004#/Z7pm\026$\027%A!\002\rIr\023\007\r\0301\001")
public interface AbstractSyntax {
  public interface Element extends Positional {}
  
  public interface NameElement extends Element {
    String name();
    
    boolean equals(Object param1Object);
  }
  
  public abstract class NameElement$class {
    public static void $init$(AbstractSyntax.NameElement $this) {}
    
    public static boolean equals(AbstractSyntax.NameElement $this, Object that) {
      // Byte code:
      //   0: aload_1
      //   1: instanceof scala/util/parsing/ast/AbstractSyntax$NameElement
      //   4: ifeq -> 72
      //   7: aload_1
      //   8: checkcast scala/util/parsing/ast/AbstractSyntax$NameElement
      //   11: invokeinterface scala$util$parsing$ast$AbstractSyntax$NameElement$$$outer : ()Lscala/util/parsing/ast/AbstractSyntax;
      //   16: aload_0
      //   17: invokeinterface scala$util$parsing$ast$AbstractSyntax$NameElement$$$outer : ()Lscala/util/parsing/ast/AbstractSyntax;
      //   22: if_acmpne -> 72
      //   25: aload_1
      //   26: checkcast scala/util/parsing/ast/AbstractSyntax$NameElement
      //   29: astore_2
      //   30: aload_2
      //   31: invokeinterface name : ()Ljava/lang/String;
      //   36: aload_0
      //   37: invokeinterface name : ()Ljava/lang/String;
      //   42: astore_3
      //   43: dup
      //   44: ifnonnull -> 55
      //   47: pop
      //   48: aload_3
      //   49: ifnull -> 62
      //   52: goto -> 66
      //   55: aload_3
      //   56: invokevirtual equals : (Ljava/lang/Object;)Z
      //   59: ifeq -> 66
      //   62: iconst_1
      //   63: goto -> 67
      //   66: iconst_0
      //   67: istore #4
      //   69: goto -> 75
      //   72: iconst_0
      //   73: istore #4
      //   75: iload #4
      //   77: ireturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #28	-> 0
      //   #27	-> 0
      //   #29	-> 72
      //   #27	-> 75
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   0	78	0	$this	Lscala/util/parsing/ast/AbstractSyntax$NameElement;
      //   0	78	1	that	Ljava/lang/Object;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\ast\AbstractSyntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */