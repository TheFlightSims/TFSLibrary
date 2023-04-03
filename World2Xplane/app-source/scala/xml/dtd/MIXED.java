/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.regexp.Base;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b\001B\001\003\001&\021Q!T%Y\013\022S!a\001\003\002\007\021$HM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001a\005\003\001\0259\021\002CA\006\r\033\005\021\021BA\007\003\005=!e)Q\"p]R,g\016^'pI\026d\007CA\b\021\033\0051\021BA\t\007\005\035\001&o\0343vGR\004\"aD\n\n\005Q1!\001D*fe&\fG.\033>bE2,\007\002\003\f\001\005+\007I\021A\f\002\003I,\022\001\007\t\0033qq!a\003\016\n\005m\021\021\001D\"p]R,g\016^'pI\026d\027BA\017\037\005\031\021VmZ#ya&\021q\004\t\002\005\005\006\034XM\003\002\"E\0051!/Z4fqBT!a\t\004\002\tU$\030\016\034\005\tK\001\021\t\022)A\0051\005\021!\017\t\005\006O\001!\t\001K\001\007y%t\027\016\036 \025\005%R\003CA\006\001\021\0251b\0051\001\031\021\025a\003\001\"\021.\003-\021W/\0337e'R\024\030N\\4\025\0059R\004CA\0308\035\t\001TG\004\0022i5\t!G\003\0024\021\0051AH]8pizJ\021aB\005\003m\031\tq\001]1dW\006<W-\003\0029s\ti1\013\036:j]\036\024U/\0337eKJT!A\016\004\t\013mZ\003\031\001\030\002\005M\024\007bB\037\001\003\003%\tAP\001\005G>\004\030\020\006\002*!9a\003\020I\001\002\004A\002bB!\001#\003%\tAQ\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005\031%F\001\rEW\005)\005C\001$L\033\0059%B\001%J\003%)hn\0315fG.,GM\003\002K\r\005Q\021M\0348pi\006$\030n\0348\n\0051;%!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\nAA\001\n\003z\025!\0049s_\022,8\r\036)sK\032L\0070F\001Q!\t\tf+D\001S\025\t\031F+\001\003mC:<'\"A+\002\t)\fg/Y\005\003/J\023aa\025;sS:<\007bB-\001\003\003%\tAW\001\raJ|G-^2u\003JLG/_\013\0027B\021q\002X\005\003;\032\0211!\0238u\021\035y\006!!A\005\002\001\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002bIB\021qBY\005\003G\032\0211!\0218z\021\035)g,!AA\002m\0131\001\037\0232\021\0359\007!!A\005B!\fq\002\035:pIV\034G/\023;fe\006$xN]\013\002SB\031!.\\1\016\003-T!\001\034\004\002\025\r|G\016\\3di&|g.\003\002oW\nA\021\n^3sCR|'\017C\004q\001\005\005I\021A9\002\021\r\fg.R9vC2$\"A];\021\005=\031\030B\001;\007\005\035\021un\0347fC:Dq!Z8\002\002\003\007\021\rC\004x\001\005\005I\021\t=\002\021!\f7\017[\"pI\026$\022a\027\005\bu\002\t\t\021\"\021|\003\031)\027/^1mgR\021!\017 \005\bKf\f\t\0211\001b\017\035q(!!A\t\002}\fQ!T%Y\013\022\0032aCA\001\r!\t!!!A\t\002\005\r1#BA\001\003\013\021\002CBA\004\003\033A\022&\004\002\002\n)\031\0211\002\004\002\017I,h\016^5nK&!\021qBA\005\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\bO\005\005A\021AA\n)\005y\bBCA\f\003\003\t\t\021\"\022\002\032\005AAo\\*ue&tw\rF\001Q\021)\ti\"!\001\002\002\023\005\025qD\001\006CB\004H.\037\013\004S\005\005\002B\002\f\002\034\001\007\001\004\003\006\002&\005\005\021\021!CA\003O\tq!\0368baBd\027\020\006\003\002*\005=\002\003B\b\002,aI1!!\f\007\005\031y\005\017^5p]\"I\021\021GA\022\003\003\005\r!K\001\004q\022\002\004BCA\033\003\003\t\t\021\"\003\0028\005Y!/Z1e%\026\034x\016\034<f)\t\tI\004E\002R\003wI1!!\020S\005\031y%M[3di\002")
/*     */ public class MIXED extends DFAContentModel implements Product, Serializable {
/*     */   private final Base.RegExp r;
/*     */   
/*     */   public Base.RegExp r() {
/* 105 */     return this.r;
/*     */   }
/*     */   
/*     */   public MIXED copy(Base.RegExp r) {
/* 105 */     return new MIXED(r);
/*     */   }
/*     */   
/*     */   public Base.RegExp copy$default$1() {
/* 105 */     return r();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 105 */     return "MIXED";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 105 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 105 */     switch (x$1) {
/*     */       default:
/* 105 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 105 */     return r();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 105 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 105 */     return x$1 instanceof MIXED;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/MIXED
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/MIXED
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
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
/*     */     //   #105	-> 0
/*     */     //   #236	-> 12
/*     */     //   #105	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/xml/dtd/MIXED;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public MIXED(Base.RegExp r) {
/* 105 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 109 */     Base.RegExp regExp = r();
/* 109 */     if (regExp instanceof Base.Alt) {
/* 109 */       Base.Alt alt = (Base.Alt)regExp;
/* 109 */       Some some = ContentModel$.MODULE$.Alt().unapplySeq(alt);
/* 109 */       if (!some.isEmpty()) {
/* 109 */         Base.Alt alt1 = ContentModel$.MODULE$.Alt().apply((Seq)((IterableLike)some.get()).drop(1));
/* 111 */         sb.append("(#PCDATA|");
/* 112 */         ContentModel$.MODULE$.buildString((Base.RegExp)alt1, sb);
/* 113 */         return sb.append(")*");
/*     */       } 
/*     */     } 
/*     */     throw new MatchError(regExp);
/*     */   }
/*     */   
/*     */   public static <A> Function1<Base.RegExp, A> andThen(Function1<MIXED, A> paramFunction1) {
/*     */     return MIXED$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, MIXED> compose(Function1<A, Base.RegExp> paramFunction1) {
/*     */     return MIXED$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\MIXED.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */