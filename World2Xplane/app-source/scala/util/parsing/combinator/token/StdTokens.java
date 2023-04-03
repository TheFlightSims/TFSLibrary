/*    */ package scala.util.parsing.combinator.token;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t}baB\001\003!\003\r\t!\004\002\n'R$Gk\\6f]NT!a\001\003\002\013Q|7.\0328\013\005\0251\021AC2p[\nLg.\031;pe*\021q\001C\001\ba\006\0248/\0338h\025\tI!\"\001\003vi&d'\"A\006\002\013M\034\027\r\\1\004\001M\031\001A\004\n\021\005=\001R\"\001\006\n\005EQ!AB!osJ+g\r\005\002\024)5\t!!\003\002\026\005\t1Ak\\6f]NDQa\006\001\005\002a\ta\001J5oSR$C#A\r\021\005=Q\022BA\016\013\005\021)f.\033;\007\tu\001\001I\b\002\b\027\026Lxo\034:e'\021ard\t\024\021\005\001\nS\"\001\001\n\005\t\"\"!\002+pW\026t\007CA\b%\023\t)#BA\004Qe>$Wo\031;\021\005=9\023B\001\025\013\0051\031VM]5bY&T\030M\0317f\021!QCD!f\001\n\003Y\023!B2iCJ\034X#\001\027\021\0055\002dBA\b/\023\ty#\"\001\004Qe\026$WMZ\005\003cI\022aa\025;sS:<'BA\030\013\021!!DD!E!\002\023a\023AB2iCJ\034\b\005C\00379\021\005q'\001\004=S:LGO\020\013\003qe\002\"\001\t\017\t\013)*\004\031\001\027\t\013mbB\021\t\037\002\021Q|7\013\036:j]\036$\022!\020\t\003}\rk\021a\020\006\003\001\006\013A\001\\1oO*\t!)\001\003kCZ\f\027BA\031@\021\035)E$!A\005\002\031\013AaY8qsR\021\001h\022\005\bU\021\003\n\0211\001-\021\035IE$%A\005\002)\013abY8qs\022\"WMZ1vYR$\023'F\001LU\taCjK\001N!\tq5+D\001P\025\t\001\026+A\005v]\016DWmY6fI*\021!KC\001\013C:tw\016^1uS>t\027B\001+P\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b-r\t\t\021\"\021X\0035\001(o\0343vGR\004&/\0324jqV\tQ\bC\004Z9\005\005I\021\001.\002\031A\024x\016Z;di\006\023\030\016^=\026\003m\003\"a\004/\n\005uS!aA%oi\"9q\fHA\001\n\003\001\027A\0049s_\022,8\r^#mK6,g\016\036\013\003C\022\004\"a\0042\n\005\rT!aA!os\"9QMXA\001\002\004Y\026a\001=%c!9q\rHA\001\n\003B\027a\0049s_\022,8\r^%uKJ\fGo\034:\026\003%\0042A[7b\033\005Y'B\0017\013\003)\031w\016\0347fGRLwN\\\005\003].\024\001\"\023;fe\006$xN\035\005\bar\t\t\021\"\001r\003!\031\027M\\#rk\006dGC\001:v!\ty1/\003\002u\025\t9!i\\8mK\006t\007bB3p\003\003\005\r!\031\005\bor\t\t\021\"\021y\003!A\027m\0355D_\022,G#A.\t\017id\022\021!C!w\0061Q-];bYN$\"A\035?\t\017\025L\030\021!a\001C\0369a\020AA\001\022\003y\030aB&fs^|'\017\032\t\004A\005\005a\001C\017\001\003\003E\t!a\001\024\013\005\005\021Q\001\024\021\r\005\035\021Q\002\0279\033\t\tIAC\002\002\f)\tqA];oi&lW-\003\003\002\020\005%!!E!cgR\024\030m\031;Gk:\034G/[8oc!9a'!\001\005\002\005MA#A@\t\021m\n\t!!A\005FqB!\"!\007\002\002\005\005I\021QA\016\003\025\t\007\017\0357z)\rA\024Q\004\005\007U\005]\001\031\001\027\t\025\005\005\022\021AA\001\n\003\013\031#A\004v]\006\004\b\017\\=\025\t\005\025\0221\006\t\005\037\005\035B&C\002\002*)\021aa\0249uS>t\007\"CA\027\003?\t\t\0211\0019\003\rAH\005\r\005\013\003c\t\t!!A\005\n\005M\022a\003:fC\022\024Vm]8mm\026$\"!!\016\021\007y\n9$C\002\002:}\022aa\0242kK\016$hABA\037\001\001\013yD\001\006Ok6,'/[2MSR\034R!a\017 G\031B\021BKA\036\005+\007I\021A\026\t\023Q\nYD!E!\002\023a\003b\002\034\002<\021\005\021q\t\013\005\003\023\nY\005E\002!\003wAaAKA#\001\004a\003bB\036\002<\021\005\023q\n\013\002Y!IQ)a\017\002\002\023\005\0211\013\013\005\003\023\n)\006\003\005+\003#\002\n\0211\001-\021!I\0251HI\001\n\003Q\005\002\003,\002<\005\005I\021I,\t\021e\013Y$!A\005\002iC\021bXA\036\003\003%\t!a\030\025\007\005\f\t\007\003\005f\003;\n\t\0211\001\\\021!9\0271HA\001\n\003B\007\"\0039\002<\005\005I\021AA4)\r\021\030\021\016\005\tK\006\025\024\021!a\001C\"Aq/a\017\002\002\023\005\003\020C\005{\003w\t\t\021\"\021\002pQ\031!/!\035\t\021\025\fi'!AA\002\005<\021\"!\036\001\003\003E\t!a\036\002\0259+X.\032:jG2KG\017E\002!\003s2\021\"!\020\001\003\003E\t!a\037\024\013\005e\024Q\020\024\021\017\005\035\021Q\002\027\002J!9a'!\037\005\002\005\005ECAA<\021!Y\024\021PA\001\n\013b\004BCA\r\003s\n\t\021\"!\002\bR!\021\021JAE\021\031Q\023Q\021a\001Y!Q\021\021EA=\003\003%\t)!$\025\t\005\025\022q\022\005\013\003[\tY)!AA\002\005%\003BCA\031\003s\n\t\021\"\003\0024\0311\021Q\023\001A\003/\023\021b\025;sS:<G*\033;\024\013\005Mud\t\024\t\023)\n\031J!f\001\n\003Y\003\"\003\033\002\024\nE\t\025!\003-\021\0351\0241\023C\001\003?#B!!)\002$B\031\001%a%\t\r)\ni\n1\001-\021\031Y\0241\023C!y!IQ)a%\002\002\023\005\021\021\026\013\005\003C\013Y\013\003\005+\003O\003\n\0211\001-\021!I\0251SI\001\n\003Q\005\002\003,\002\024\006\005I\021I,\t\021e\013\031*!A\005\002iC\021bXAJ\003\003%\t!!.\025\007\005\f9\f\003\005f\003g\013\t\0211\001\\\021!9\0271SA\001\n\003B\007\"\0039\002\024\006\005I\021AA_)\r\021\030q\030\005\tK\006m\026\021!a\001C\"Aq/a%\002\002\023\005\003\020C\005{\003'\013\t\021\"\021\002FR\031!/a2\t\021\025\f\031-!AA\002\005<\021\"a3\001\003\003E\t!!4\002\023M#(/\0338h\031&$\bc\001\021\002P\032I\021Q\023\001\002\002#\005\021\021[\n\006\003\037\f\031N\n\t\b\003\017\ti\001LAQ\021\0351\024q\032C\001\003/$\"!!4\t\021m\ny-!A\005FqB!\"!\007\002P\006\005I\021QAo)\021\t\t+a8\t\r)\nY\0161\001-\021)\t\t#a4\002\002\023\005\0251\035\013\005\003K\t)\017\003\006\002.\005\005\030\021!a\001\003CC!\"!\r\002P\006\005I\021BA\032\r\031\tY\017\001!\002n\nQ\021\nZ3oi&4\027.\032:\024\013\005%xd\t\024\t\023)\nIO!f\001\n\003Y\003\"\003\033\002j\nE\t\025!\003-\021\0351\024\021\036C\001\003k$B!a>\002zB\031\001%!;\t\r)\n\031\0201\001-\021\031Y\024\021\036C!y!IQ)!;\002\002\023\005\021q \013\005\003o\024\t\001\003\005+\003{\004\n\0211\001-\021!I\025\021^I\001\n\003Q\005\002\003,\002j\006\005I\021I,\t\021e\013I/!A\005\002iC\021bXAu\003\003%\tAa\003\025\007\005\024i\001\003\005f\005\023\t\t\0211\001\\\021!9\027\021^A\001\n\003B\007\"\0039\002j\006\005I\021\001B\n)\r\021(Q\003\005\tK\nE\021\021!a\001C\"Aq/!;\002\002\023\005\003\020C\005{\003S\f\t\021\"\021\003\034Q\031!O!\b\t\021\025\024I\"!AA\002\005<\021B!\t\001\003\003E\tAa\t\002\025%#WM\034;jM&,'\017E\002!\005K1\021\"a;\001\003\003E\tAa\n\024\013\t\025\"\021\006\024\021\017\005\035\021Q\002\027\002x\"9aG!\n\005\002\t5BC\001B\022\021!Y$QEA\001\n\013b\004BCA\r\005K\t\t\021\"!\0034Q!\021q\037B\033\021\031Q#\021\007a\001Y!Q\021\021\005B\023\003\003%\tI!\017\025\t\005\025\"1\b\005\013\003[\0219$!AA\002\005]\bBCA\031\005K\t\t\021\"\003\0024\001")
/*    */ public interface StdTokens extends Tokens {
/*    */   Keyword$ Keyword();
/*    */   
/*    */   NumericLit$ NumericLit();
/*    */   
/*    */   StringLit$ StringLit();
/*    */   
/*    */   Identifier$ Identifier();
/*    */   
/*    */   public class Keyword$ extends AbstractFunction1<String, Keyword> implements Serializable {
/*    */     public final String toString() {
/* 20 */       return "Keyword";
/*    */     }
/*    */     
/*    */     public StdTokens.Keyword apply(String chars) {
/* 20 */       return new StdTokens.Keyword(this.$outer, chars);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(StdTokens.Keyword x$0) {
/* 20 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.chars());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 20 */       return this.$outer.Keyword();
/*    */     }
/*    */     
/*    */     public Keyword$(StdTokens $outer) {}
/*    */   }
/*    */   
/*    */   public class Keyword extends Tokens.Token implements Product, Serializable {
/*    */     private final String chars;
/*    */     
/*    */     public String chars() {
/* 20 */       return this.chars;
/*    */     }
/*    */     
/*    */     public Keyword copy(String chars) {
/* 20 */       return new Keyword(scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer(), chars);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 20 */       return chars();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 20 */       return "Keyword";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 20 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 20 */       switch (x$1) {
/*    */         default:
/* 20 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 20 */       return chars();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 20 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 20 */       return x$1 instanceof Keyword;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 20 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/parsing/combinator/token/StdTokens$Keyword
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/parsing/combinator/token/StdTokens$Keyword
/*    */       //   16: invokevirtual scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/parsing/combinator/token/StdTokens$Keyword
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #20	-> 0
/*    */       //   #236	-> 26
/*    */       //   #20	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/parsing/combinator/token/StdTokens$Keyword;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public Keyword(StdTokens $outer, String chars) {
/* 20 */       super($outer);
/* 20 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 21 */       return (new StringBuilder()).append("`").append(chars()).append("'").toString();
/*    */     }
/*    */   }
/*    */   
/*    */   public class NumericLit$ extends AbstractFunction1<String, NumericLit> implements Serializable {
/*    */     public final String toString() {
/* 25 */       return "NumericLit";
/*    */     }
/*    */     
/*    */     public StdTokens.NumericLit apply(String chars) {
/* 25 */       return new StdTokens.NumericLit(this.$outer, chars);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(StdTokens.NumericLit x$0) {
/* 25 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.chars());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 25 */       return this.$outer.NumericLit();
/*    */     }
/*    */     
/*    */     public NumericLit$(StdTokens $outer) {}
/*    */   }
/*    */   
/*    */   public class NumericLit extends Tokens.Token implements Product, Serializable {
/*    */     private final String chars;
/*    */     
/*    */     public String chars() {
/* 25 */       return this.chars;
/*    */     }
/*    */     
/*    */     public NumericLit copy(String chars) {
/* 25 */       return new NumericLit(scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer(), chars);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 25 */       return chars();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 25 */       return "NumericLit";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 25 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 25 */       switch (x$1) {
/*    */         default:
/* 25 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 25 */       return chars();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 25 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 25 */       return x$1 instanceof NumericLit;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 25 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/parsing/combinator/token/StdTokens$NumericLit
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/parsing/combinator/token/StdTokens$NumericLit
/*    */       //   16: invokevirtual scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/parsing/combinator/token/StdTokens$NumericLit
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #25	-> 0
/*    */       //   #236	-> 26
/*    */       //   #25	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/parsing/combinator/token/StdTokens$NumericLit;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public NumericLit(StdTokens $outer, String chars) {
/* 25 */       super($outer);
/* 25 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 26 */       return chars();
/*    */     }
/*    */   }
/*    */   
/*    */   public class StringLit$ extends AbstractFunction1<String, StringLit> implements Serializable {
/*    */     public final String toString() {
/* 30 */       return "StringLit";
/*    */     }
/*    */     
/*    */     public StdTokens.StringLit apply(String chars) {
/* 30 */       return new StdTokens.StringLit(this.$outer, chars);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(StdTokens.StringLit x$0) {
/* 30 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.chars());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 30 */       return this.$outer.StringLit();
/*    */     }
/*    */     
/*    */     public StringLit$(StdTokens $outer) {}
/*    */   }
/*    */   
/*    */   public class StringLit extends Tokens.Token implements Product, Serializable {
/*    */     private final String chars;
/*    */     
/*    */     public String chars() {
/* 30 */       return this.chars;
/*    */     }
/*    */     
/*    */     public StringLit copy(String chars) {
/* 30 */       return new StringLit(scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer(), chars);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 30 */       return chars();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 30 */       return "StringLit";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 30 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 30 */       switch (x$1) {
/*    */         default:
/* 30 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 30 */       return chars();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 30 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 30 */       return x$1 instanceof StringLit;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 30 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/parsing/combinator/token/StdTokens$StringLit
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/parsing/combinator/token/StdTokens$StringLit
/*    */       //   16: invokevirtual scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/parsing/combinator/token/StdTokens$StringLit
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #30	-> 0
/*    */       //   #236	-> 26
/*    */       //   #30	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/parsing/combinator/token/StdTokens$StringLit;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public StringLit(StdTokens $outer, String chars) {
/* 30 */       super($outer);
/* 30 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 31 */       return (new StringBuilder()).append("\"").append(chars()).append("\"").toString();
/*    */     }
/*    */   }
/*    */   
/*    */   public class Identifier$ extends AbstractFunction1<String, Identifier> implements Serializable {
/*    */     public final String toString() {
/* 35 */       return "Identifier";
/*    */     }
/*    */     
/*    */     public StdTokens.Identifier apply(String chars) {
/* 35 */       return new StdTokens.Identifier(this.$outer, chars);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(StdTokens.Identifier x$0) {
/* 35 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.chars());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 35 */       return this.$outer.Identifier();
/*    */     }
/*    */     
/*    */     public Identifier$(StdTokens $outer) {}
/*    */   }
/*    */   
/*    */   public class Identifier extends Tokens.Token implements Product, Serializable {
/*    */     private final String chars;
/*    */     
/*    */     public String chars() {
/* 35 */       return this.chars;
/*    */     }
/*    */     
/*    */     public Identifier copy(String chars) {
/* 35 */       return new Identifier(scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer(), chars);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 35 */       return chars();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 35 */       return "Identifier";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 35 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 35 */       switch (x$1) {
/*    */         default:
/* 35 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 35 */       return chars();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 35 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 35 */       return x$1 instanceof Identifier;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 35 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/parsing/combinator/token/StdTokens$Identifier
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/parsing/combinator/token/StdTokens$Identifier
/*    */       //   16: invokevirtual scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer : ()Lscala/util/parsing/combinator/token/StdTokens;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/parsing/combinator/token/StdTokens$Identifier
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual chars : ()Ljava/lang/String;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #35	-> 0
/*    */       //   #236	-> 26
/*    */       //   #35	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/parsing/combinator/token/StdTokens$Identifier;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public Identifier(StdTokens $outer, String chars) {
/* 35 */       super($outer);
/* 35 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 36 */       return (new StringBuilder()).append("identifier ").append(chars()).toString();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\token\StdTokens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */