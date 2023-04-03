/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.io.Source;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.util.logging.Logged;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.NodeSeq;
/*     */ import scala.xml.dtd.Decl;
/*     */ import scala.xml.dtd.ElemDecl;
/*     */ import scala.xml.dtd.EntityDecl;
/*     */ import scala.xml.dtd.EntityDef;
/*     */ import scala.xml.dtd.ExternalID;
/*     */ import scala.xml.dtd.PEReference;
/*     */ import scala.xml.dtd.ParameterEntityDecl;
/*     */ import scala.xml.dtd.ParsedEntityDecl;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]h!B\001\003\003\003I!!D'be.,\b\017S1oI2,'O\003\002\004\t\0059\001/\031:tS:<'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005=!R\"\001\t\013\005E\021\022a\0027pO\036Lgn\032\006\003'\031\tA!\036;jY&\021Q\003\005\002\007\031><w-\0323\t\013]\001A\021\001\r\002\rqJg.\033;?)\005I\002C\001\016\001\033\005\021\001b\002\017\001\005\004%\t!H\001\rSN4\026\r\\5eCRLgnZ\013\002=A\0211bH\005\003A\031\021qAQ8pY\026\fg\016\003\004#\001\001\006IAH\001\016SN4\026\r\\5eCRLgn\032\021\t\017\021\002\001\031!C\001K\005)A-Z2mgV\ta\005E\002(_Ir!\001K\027\017\005%bS\"\001\026\013\005-B\021A\002\037s_>$h(C\001\b\023\tqc!A\004qC\016\\\027mZ3\n\005A\n$\001\002'jgRT!A\f\004\021\005M2T\"\001\033\013\005U\"\021a\0013uI&\021q\007\016\002\005\t\026\034G\016C\004:\001\001\007I\021\001\036\002\023\021,7\r\\:`I\025\fHCA\036?!\tYA(\003\002>\r\t!QK\\5u\021\035y\004(!AA\002\031\n1\001\037\0232\021\031\t\005\001)Q\005M\0051A-Z2mg\002Bqa\021\001A\002\023\005A)A\002f]R,\022!\022\t\005\r.kE+D\001H\025\tA\025*A\004nkR\f'\r\\3\013\005)3\021AC2pY2,7\r^5p]&\021Aj\022\002\004\033\006\004\bC\001(R\035\tYq*\003\002Q\r\0051\001K]3eK\032L!AU*\003\rM#(/\0338h\025\t\001f\001\005\0024+&\021a\013\016\002\013\013:$\030\016^=EK\016d\007b\002-\001\001\004%\t!W\001\bK:$x\fJ3r)\tY$\fC\004@/\006\005\t\031A#\t\rq\003\001\025)\003F\003\021)g\016\036\021\t\013y\003A\021A0\002\0351|wn[;q\0132,W\016R3dYR\021\001m\031\t\003g\005L!A\031\033\003\021\025cW-\034#fG2DQ\001Z/A\0025\013Q\001T1cK2DQA\032\001\005\002\035\fqB]3qY\006\034W-\\3oiR+\007\020\036\013\003Q:\004\"!\0337\016\003)T!a\033\004\002\005%|\027BA7k\005\031\031v.\036:dK\")q.\032a\001\033\006QQM\034;jift\025-\\3\t\013E\004A\021\001:\002\r\025tG\r\022+E)\tY4\017C\003ua\002\007Q*A\001o\021\0251\b\001\"\001x\003%)G.Z7Ti\006\024H\017\006\005<qv|\0301AA\b\021\025IX\0171\001{\003\r\001xn\035\t\003\027mL!\001 \004\003\007%sG\017C\003k\002\007Q*A\002qe\026Da!!\001v\001\004i\025!\0027bE\026d\007bBA\003k\002\007\021qA\001\006CR$(o\035\t\005\003\023\tY!D\001\005\023\r\ti\001\002\002\t\033\026$\030\rR1uC\"9\021\021C;A\002\005M\021!B:d_B,\007\003BA\005\003+I1!a\006\005\005Aq\025-\\3ta\006\034WMQ5oI&tw\rC\004\002\034\001!\t!!\b\002\017\025dW-\\#oIR91(a\b\002\"\005\r\002BB=\002\032\001\007!\020\003\004\0033\001\r!\024\005\b\003\003\tI\0021\001N\021\035\t9\003\001D\001\003S\tA!\0327f[R\001\0221FA\031\003g\t)$a\016\002:\005m\022q\b\t\005\003\023\ti#C\002\0020\021\021qAT8eKN+\027\017\003\004z\003K\001\rA\037\005\007}\006\025\002\031A'\t\017\005\005\021Q\005a\001\033\"A\021QAA\023\001\004\t9\001\003\005\002\022\005\025\002\031AA\n\021\035\ti$!\nA\002y\tQ!Z7qifD\001\"!\021\002&\001\007\0211F\001\005CJ<7\017C\004\002F\0011\t!a\022\002\023A\024xnY%ogR\024H\003CA\026\003\023\nY%a\024\t\re\f\031\0051\001{\021\035\ti%a\021A\0025\013a\001^1sO\026$\bbBA)\003\007\002\r!T\001\004ib$\bbBA+\001\031\005\021qK\001\bG>lW.\0328u)\031\tY#!\027\002\\!1\0210a\025A\002iDq!!\026\002T\001\007Q\nC\004\002`\0011\t!!\031\002\023\025tG/\033;z%\0264GCBA\026\003G\n)\007\003\004z\003;\002\rA\037\005\007i\006u\003\031A'\t\017\005%\004A\"\001\002l\005!A/\032=u)\031\tY#!\034\002p!1\0210a\032A\002iDq!!\025\002h\001\007Q\nC\004\002t\001!\t!!\036\002\021\025dW-\034#fG2$RaOA<\003sBa\001^A9\001\004i\005bBA>\003c\002\r!T\001\006G6\034HO\035\005\b\003\002A\021AAA\003-\tG\017\036'jgR$Um\0317\025\013m\n\031)a\"\t\017\005\025\025Q\020a\001\033\006!a.Y7f\021!\tI)! A\002\005-\025aB1ui2K7\017\036\t\005O=\ni\tE\0024\003\037K1!!%5\005!\tE\017\036:EK\016d\007bBAK\001\021%\021qS\001\017g>lW-\0228uSRLH)Z2m)\035Y\024\021TAN\003KCq!!\"\002\024\002\007Q\n\003\005\002\036\006M\005\031AAP\003\021)G-\0324\021\007M\n\t+C\002\002$R\022\021\"\0228uSRLH)\0324\t\021\005\035\0261\023a\001\003S\013\021A\032\t\b\027\005-V*a(U\023\r\tiK\002\002\n\rVt7\r^5p]JBq!!-\001\t\003\t\031,A\nqCJ\fW.\032;fe\026sG/\033;z\t\026\034G\016F\003<\003k\0139\fC\004\002\006\006=\006\031A'\t\021\005u\025q\026a\001\003?Cq!a/\001\t\003\ti,\001\tqCJ\034X\rZ#oi&$\030\020R3dYR)1(a0\002B\"9\021QQA]\001\004i\005\002CAO\003s\003\r!a(\t\017\005\025\007\001\"\001\002H\006Y\001/\032*fM\026\024XM\\2f)\rY\024\021\032\005\b\003\013\013\031\r1\001N\021\035\ti\r\001C\001\003\037\f!#\0368qCJ\034X\rZ#oi&$\030\020R3dYR91(!5\002T\006u\007bBAC\003\027\004\r!\024\005\t\003+\fY\r1\001\002X\006)Q\r\037;J\tB\0311'!7\n\007\005mGG\001\006FqR,'O\\1m\023\022Cq!a8\002L\002\007Q*A\003o_R\fG\017C\004\002d\002!\t!!:\002\0319|G/\031;j_:$Um\0317\025\013m\n9/!;\t\017\005}\027\021\035a\001\033\"A\021Q[Aq\001\004\t9\016C\004\002n\0021\t!a<\002#I,\007o\034:u'ftG/\031=FeJ|'\017F\003<\003c\f\031\020\003\004z\003W\004\rA\037\005\b\003k\fY\0171\001N\003\r\031HO\035")
/*     */ public abstract class MarkupHandler implements Logged {
/*     */   private final boolean isValidating;
/*     */   
/*     */   private List<Decl> decls;
/*     */   
/*     */   private Map<String, EntityDecl> ent;
/*     */   
/*     */   public void log(String msg) {
/*  28 */     Logged.class.log(this, msg);
/*     */   }
/*     */   
/*     */   public MarkupHandler() {
/*  28 */     Logged.class.$init$(this);
/*  31 */     this.isValidating = false;
/*  33 */     this.decls = (List<Decl>)Nil$.MODULE$;
/*  34 */     this.ent = (Map<String, EntityDecl>)new HashMap();
/*     */   }
/*     */   
/*     */   public boolean isValidating() {
/*     */     return this.isValidating;
/*     */   }
/*     */   
/*     */   public List<Decl> decls() {
/*     */     return this.decls;
/*     */   }
/*     */   
/*     */   public void decls_$eq(List<Decl> x$1) {
/*     */     this.decls = x$1;
/*     */   }
/*     */   
/*     */   public Map<String, EntityDecl> ent() {
/*  34 */     return this.ent;
/*     */   }
/*     */   
/*     */   public void ent_$eq(Map<String, EntityDecl> x$1) {
/*  34 */     this.ent = x$1;
/*     */   }
/*     */   
/*     */   public ElemDecl lookupElemDecl(String Label) {
/*  36 */     Object object = new Object();
/*     */     try {
/*  37 */       decls().withFilter((Function1)new MarkupHandler$$anonfun$lookupElemDecl$1(this, Label)).foreach((Function1)new MarkupHandler$$anonfun$lookupElemDecl$2(this, Label, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */       NonLocalReturnControl nonLocalReturnControl1;
/*     */       if ((nonLocalReturnControl1 = null).key() == object)
/*     */         return (ElemDecl)nonLocalReturnControl1.value(); 
/*     */     } 
/*  37 */     return null;
/*     */   }
/*     */   
/*     */   public class MarkupHandler$$anonfun$lookupElemDecl$1 extends AbstractFunction1<Decl, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String Label$1;
/*     */     
/*     */     public final boolean apply(Decl check$ifrefutable$1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/xml/dtd/ElemDecl
/*     */       //   4: ifeq -> 46
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/xml/dtd/ElemDecl
/*     */       //   11: astore_2
/*     */       //   12: aload_0
/*     */       //   13: getfield Label$1 : Ljava/lang/String;
/*     */       //   16: aload_2
/*     */       //   17: invokevirtual name : ()Ljava/lang/String;
/*     */       //   20: astore_3
/*     */       //   21: dup
/*     */       //   22: ifnonnull -> 33
/*     */       //   25: pop
/*     */       //   26: aload_3
/*     */       //   27: ifnull -> 40
/*     */       //   30: goto -> 46
/*     */       //   33: aload_3
/*     */       //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   37: ifeq -> 46
/*     */       //   40: iconst_1
/*     */       //   41: istore #4
/*     */       //   43: goto -> 49
/*     */       //   46: iconst_0
/*     */       //   47: istore #4
/*     */       //   49: iload #4
/*     */       //   51: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #37	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	52	0	this	Lscala/xml/parsing/MarkupHandler$$anonfun$lookupElemDecl$1;
/*     */       //   0	52	1	check$ifrefutable$1	Lscala/xml/dtd/Decl;
/*     */     }
/*     */     
/*     */     public MarkupHandler$$anonfun$lookupElemDecl$1(MarkupHandler $outer, String Label$1) {}
/*     */   }
/*     */   
/*     */   public class MarkupHandler$$anonfun$lookupElemDecl$2 extends AbstractFunction1<Decl, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String Label$1;
/*     */     
/*     */     private final Object nonLocalReturnKey1$1;
/*     */     
/*     */     public final Nothing$ apply(Decl x$1) {
/*  37 */       if (x$1 instanceof ElemDecl) {
/*  37 */         ElemDecl elemDecl = (ElemDecl)x$1;
/*  37 */         String str = elemDecl.name();
/*  37 */         if (this.Label$1 == null) {
/*  37 */           if (str != null)
/*  37 */             throw new MatchError(x$1); 
/*     */         } else {
/*  37 */           if (this.Label$1.equals(str))
/*  38 */             throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, elemDecl); 
/*     */           throw new MatchError(x$1);
/*     */         } 
/*  38 */         throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, elemDecl);
/*     */       } 
/*     */       throw new MatchError(x$1);
/*     */     }
/*     */     
/*     */     public MarkupHandler$$anonfun$lookupElemDecl$2(MarkupHandler $outer, String Label$1, Object nonLocalReturnKey1$1) {}
/*     */   }
/*     */   
/*     */   public Source replacementText(String entityName) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/io/Source$.MODULE$ : Lscala/io/Source$;
/*     */     //   3: iconst_0
/*     */     //   4: istore #8
/*     */     //   6: aconst_null
/*     */     //   7: astore #4
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual ent : ()Lscala/collection/mutable/Map;
/*     */     //   13: aload_1
/*     */     //   14: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   19: astore #12
/*     */     //   21: aload #12
/*     */     //   23: instanceof scala/Some
/*     */     //   26: ifeq -> 85
/*     */     //   29: iconst_1
/*     */     //   30: istore #8
/*     */     //   32: aload #12
/*     */     //   34: checkcast scala/Some
/*     */     //   37: dup
/*     */     //   38: astore #4
/*     */     //   40: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   43: instanceof scala/xml/dtd/ParsedEntityDecl
/*     */     //   46: ifeq -> 85
/*     */     //   49: aload #4
/*     */     //   51: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   54: checkcast scala/xml/dtd/ParsedEntityDecl
/*     */     //   57: astore_2
/*     */     //   58: aload_2
/*     */     //   59: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
/*     */     //   62: instanceof scala/xml/dtd/IntDef
/*     */     //   65: ifeq -> 85
/*     */     //   68: aload_2
/*     */     //   69: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
/*     */     //   72: checkcast scala/xml/dtd/IntDef
/*     */     //   75: astore_3
/*     */     //   76: aload_3
/*     */     //   77: invokevirtual value : ()Ljava/lang/String;
/*     */     //   80: astore #11
/*     */     //   82: goto -> 270
/*     */     //   85: iload #8
/*     */     //   87: ifeq -> 172
/*     */     //   90: aload #4
/*     */     //   92: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   95: instanceof scala/xml/dtd/ParameterEntityDecl
/*     */     //   98: ifeq -> 172
/*     */     //   101: aload #4
/*     */     //   103: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   106: checkcast scala/xml/dtd/ParameterEntityDecl
/*     */     //   109: astore #5
/*     */     //   111: aload #5
/*     */     //   113: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
/*     */     //   116: instanceof scala/xml/dtd/IntDef
/*     */     //   119: ifeq -> 172
/*     */     //   122: aload #5
/*     */     //   124: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
/*     */     //   127: checkcast scala/xml/dtd/IntDef
/*     */     //   130: astore #7
/*     */     //   132: new scala/collection/immutable/StringOps
/*     */     //   135: dup
/*     */     //   136: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   139: astore #6
/*     */     //   141: ldc ' %s '
/*     */     //   143: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   146: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   149: iconst_1
/*     */     //   150: anewarray java/lang/Object
/*     */     //   153: dup
/*     */     //   154: iconst_0
/*     */     //   155: aload #7
/*     */     //   157: invokevirtual value : ()Ljava/lang/String;
/*     */     //   160: aastore
/*     */     //   161: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   164: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   167: astore #11
/*     */     //   169: goto -> 270
/*     */     //   172: iload #8
/*     */     //   174: ifeq -> 213
/*     */     //   177: new scala/collection/immutable/StringOps
/*     */     //   180: dup
/*     */     //   181: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   184: astore #9
/*     */     //   186: ldc '<!-- %s; -->'
/*     */     //   188: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   191: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   194: iconst_1
/*     */     //   195: anewarray java/lang/Object
/*     */     //   198: dup
/*     */     //   199: iconst_0
/*     */     //   200: aload_1
/*     */     //   201: aastore
/*     */     //   202: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   205: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   208: astore #11
/*     */     //   210: goto -> 270
/*     */     //   213: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   216: dup
/*     */     //   217: ifnonnull -> 229
/*     */     //   220: pop
/*     */     //   221: aload #12
/*     */     //   223: ifnull -> 237
/*     */     //   226: goto -> 276
/*     */     //   229: aload #12
/*     */     //   231: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   234: ifeq -> 276
/*     */     //   237: new scala/collection/immutable/StringOps
/*     */     //   240: dup
/*     */     //   241: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   244: astore #10
/*     */     //   246: ldc '<!-- unknown entity %s; -->'
/*     */     //   248: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   251: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   254: iconst_1
/*     */     //   255: anewarray java/lang/Object
/*     */     //   258: dup
/*     */     //   259: iconst_0
/*     */     //   260: aload_1
/*     */     //   261: aastore
/*     */     //   262: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   265: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   268: astore #11
/*     */     //   270: aload #11
/*     */     //   272: invokevirtual fromString : (Ljava/lang/String;)Lscala/io/Source;
/*     */     //   275: areturn
/*     */     //   276: new scala/MatchError
/*     */     //   279: dup
/*     */     //   280: aload #12
/*     */     //   282: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   285: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #44	-> 0
/*     */     //   #45	-> 3
/*     */     //   #44	-> 9
/*     */     //   #45	-> 21
/*     */     //   #44	-> 49
/*     */     //   #45	-> 51
/*     */     //   #44	-> 58
/*     */     //   #45	-> 59
/*     */     //   #44	-> 68
/*     */     //   #45	-> 69
/*     */     //   #44	-> 76
/*     */     //   #45	-> 77
/*     */     //   #44	-> 85
/*     */     //   #46	-> 92
/*     */     //   #44	-> 101
/*     */     //   #46	-> 103
/*     */     //   #44	-> 111
/*     */     //   #46	-> 113
/*     */     //   #44	-> 122
/*     */     //   #46	-> 124
/*     */     //   #44	-> 155
/*     */     //   #46	-> 157
/*     */     //   #44	-> 172
/*     */     //   #47	-> 177
/*     */     //   #48	-> 213
/*     */     //   #44	-> 270
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	286	0	this	Lscala/xml/parsing/MarkupHandler;
/*     */     //   0	286	1	entityName	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public void endDTD(String n) {}
/*     */   
/*     */   public void elemStart(int pos, String pre, String label, MetaData attrs, NamespaceBinding scope) {}
/*     */   
/*     */   public void elemEnd(int pos, String pre, String label) {}
/*     */   
/*     */   public void elemDecl(String n, String cmstr) {}
/*     */   
/*     */   public void attListDecl(String name, List attList) {}
/*     */   
/*     */   private void someEntityDecl(String name, EntityDef edef, Function2 f) {
/* 106 */     if (!(edef instanceof scala.xml.dtd.ExtDef) || isValidating()) {
/* 109 */       EntityDecl y = (EntityDecl)f.apply(name, edef);
/* 110 */       decls_$eq(decls().$colon$colon(y));
/* 111 */       ent().update(name, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void parameterEntityDecl(String name, EntityDef edef) {
/* 115 */     someEntityDecl(name, edef, (Function2<String, EntityDef, EntityDecl>)new MarkupHandler$$anonfun$parameterEntityDecl$1(this));
/*     */   }
/*     */   
/*     */   public class MarkupHandler$$anonfun$parameterEntityDecl$1 extends AbstractFunction2<String, EntityDef, ParameterEntityDecl> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ParameterEntityDecl apply(String name, EntityDef entdef) {
/* 115 */       return new ParameterEntityDecl(name, entdef);
/*     */     }
/*     */     
/*     */     public MarkupHandler$$anonfun$parameterEntityDecl$1(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public void parsedEntityDecl(String name, EntityDef edef) {
/* 118 */     someEntityDecl(name, edef, (Function2<String, EntityDef, EntityDecl>)new MarkupHandler$$anonfun$parsedEntityDecl$1(this));
/*     */   }
/*     */   
/*     */   public class MarkupHandler$$anonfun$parsedEntityDecl$1 extends AbstractFunction2<String, EntityDef, ParsedEntityDecl> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ParsedEntityDecl apply(String name, EntityDef entdef) {
/* 118 */       return new ParsedEntityDecl(name, entdef);
/*     */     }
/*     */     
/*     */     public MarkupHandler$$anonfun$parsedEntityDecl$1(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public void peReference(String name) {
/* 120 */     decls_$eq(decls().$colon$colon(new PEReference(name)));
/*     */   }
/*     */   
/*     */   public void unparsedEntityDecl(String name, ExternalID extID, String notat) {}
/*     */   
/*     */   public void notationDecl(String notat, ExternalID extID) {}
/*     */   
/*     */   public abstract NodeSeq elem(int paramInt, String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, boolean paramBoolean, NodeSeq paramNodeSeq);
/*     */   
/*     */   public abstract NodeSeq procInstr(int paramInt, String paramString1, String paramString2);
/*     */   
/*     */   public abstract NodeSeq comment(int paramInt, String paramString);
/*     */   
/*     */   public abstract NodeSeq entityRef(int paramInt, String paramString);
/*     */   
/*     */   public abstract NodeSeq text(int paramInt, String paramString);
/*     */   
/*     */   public abstract void reportSyntaxError(int paramInt, String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\MarkupHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */