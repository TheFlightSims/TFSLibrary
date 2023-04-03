/*     */ package scala.util.parsing.json;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]b\001B\001\003\001.\021\021BS*P\035\006\023(/Y=\013\005\r!\021\001\0026t_:T!!\002\004\002\017A\f'o]5oO*\021q\001C\001\005kRLGNC\001\n\003\025\0318-\0317b\007\001\031B\001\001\007\021)A\021QBD\007\002\005%\021qB\001\002\t\025N{e\nV=qKB\021\021CE\007\002\021%\0211\003\003\002\b!J|G-^2u!\t\tR#\003\002\027\021\ta1+\032:jC2L'0\0312mK\"A\001\004\001BK\002\023\005\021$\001\003mSN$X#\001\016\021\007m\031cE\004\002\035C9\021Q\004I\007\002=)\021qDC\001\007yI|w\016\036 \n\003%I!A\t\005\002\017A\f7m[1hK&\021A%\n\002\005\031&\034HO\003\002#\021A\021\021cJ\005\003Q!\0211!\0218z\021!Q\003A!E!\002\023Q\022!\0027jgR\004\003\"\002\027\001\t\003i\023A\002\037j]&$h\b\006\002/_A\021Q\002\001\005\0061-\002\rA\007\005\006c\001!\tAM\001\ti>\034FO]5oOR\0211g\017\t\003iej\021!\016\006\003m]\nA\001\\1oO*\t\001(\001\003kCZ\f\027B\001\0366\005\031\031FO]5oO\")A\b\ra\001{\005Iam\034:nCR$XM\035\t\003}\005s!!D \n\005\001\023\021A\003&T\037:3uN]7bi&\021!i\021\002\017-\006dW/\032$pe6\fG\017^3s\025\t\001%\001C\004F\001\005\005I\021\001$\002\t\r|\007/\037\013\003]\035Cq\001\007#\021\002\003\007!\004C\004J\001E\005I\021\001&\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t1J\013\002\033\031.\nQ\n\005\002O'6\tqJ\003\002Q#\006IQO\\2iK\016\\W\r\032\006\003%\"\t!\"\0318o_R\fG/[8o\023\t!vJA\tv]\016DWmY6fIZ\013'/[1oG\026DqA\026\001\002\002\023\005s+A\007qe>$Wo\031;Qe\0264\027\016_\013\002g!9\021\fAA\001\n\003Q\026\001\0049s_\022,8\r^!sSRLX#A.\021\005Ea\026BA/\t\005\rIe\016\036\005\b?\002\t\t\021\"\001a\0039\001(o\0343vGR,E.Z7f]R$\"AJ1\t\017\tt\026\021!a\0017\006\031\001\020J\031\t\017\021\004\021\021!C!K\006y\001O]8ek\016$\030\n^3sCR|'/F\001g!\r9'NJ\007\002Q*\021\021\016C\001\013G>dG.Z2uS>t\027BA6i\005!IE/\032:bi>\024\bbB7\001\003\003%\tA\\\001\tG\006tW)];bYR\021qN\035\t\003#AL!!\035\005\003\017\t{w\016\\3b]\"9!\r\\A\001\002\0041\003b\002;\001\003\003%\t%^\001\tQ\006\034\bnQ8eKR\t1\fC\004x\001\005\005I\021\t=\002\r\025\fX/\0317t)\ty\027\020C\004cm\006\005\t\031\001\024\b\017m\024\021\021!E\001y\006I!jU(O\003J\024\030-\037\t\003\033u4q!\001\002\002\002#\005apE\002~R\001b!!\001\002\biqSBAA\002\025\r\t)\001C\001\beVtG/[7f\023\021\tI!a\001\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007\003\004-{\022\005\021Q\002\013\002y\"A\021'`A\001\n\013\n\t\002F\0014\021%\t)\"`A\001\n\003\0139\"A\003baBd\027\020F\002/\0033Aa\001GA\n\001\004Q\002\"CA\017{\006\005I\021QA\020\003\035)h.\0319qYf$B!!\t\002(A!\021#a\t\033\023\r\t)\003\003\002\007\037B$\030n\0348\t\023\005%\0221DA\001\002\004q\023a\001=%a!I\021QF?\002\002\023%\021qF\001\fe\026\fGMU3t_24X\r\006\002\0022A\031A'a\r\n\007\005URG\001\004PE*,7\r\036")
/*     */ public class JSONArray extends JSONType implements Product, Serializable {
/*     */   private final List<Object> list;
/*     */   
/*     */   public List<Object> list() {
/* 103 */     return this.list;
/*     */   }
/*     */   
/*     */   public JSONArray copy(List<Object> list) {
/* 103 */     return new JSONArray(list);
/*     */   }
/*     */   
/*     */   public List<Object> copy$default$1() {
/* 103 */     return list();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 103 */     return "JSONArray";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 103 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 103 */     switch (x$1) {
/*     */       default:
/* 103 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 103 */     return list();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 103 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 103 */     return x$1 instanceof JSONArray;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 103 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/util/parsing/json/JSONArray
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/util/parsing/json/JSONArray
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual list : ()Lscala/collection/immutable/List;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual list : ()Lscala/collection/immutable/List;
/*     */     //   38: astore_3
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 51
/*     */     //   43: pop
/*     */     //   44: aload_3
/*     */     //   45: ifnull -> 58
/*     */     //   48: goto -> 71
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 71
/*     */     //   58: aload #4
/*     */     //   60: aload_0
/*     */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   64: ifeq -> 71
/*     */     //   67: iconst_1
/*     */     //   68: goto -> 72
/*     */     //   71: iconst_0
/*     */     //   72: ifeq -> 79
/*     */     //   75: iconst_1
/*     */     //   76: goto -> 80
/*     */     //   79: iconst_0
/*     */     //   80: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #103	-> 0
/*     */     //   #236	-> 12
/*     */     //   #103	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/util/parsing/json/JSONArray;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public JSONArray(List<Object> list) {
/* 103 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString(Function1 formatter) {
/* 105 */     return (new StringBuilder()).append("[").append(((TraversableOnce)list().map(formatter, List$.MODULE$.canBuildFrom())).mkString(", ")).append("]").toString();
/*     */   }
/*     */   
/*     */   public static <A> Function1<List<Object>, A> andThen(Function1<JSONArray, A> paramFunction1) {
/*     */     return JSONArray$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, JSONArray> compose(Function1<A, List<Object>> paramFunction1) {
/*     */     return JSONArray$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */