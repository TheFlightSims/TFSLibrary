/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.dtd.AttListDecl;
/*     */ import scala.xml.dtd.ContentModel;
/*     */ import scala.xml.dtd.ContentModel$;
/*     */ import scala.xml.dtd.DFAContentModel;
/*     */ import scala.xml.dtd.ElemDecl;
/*     */ import scala.xml.dtd.ExternalID;
/*     */ import scala.xml.dtd.NotationDecl;
/*     */ import scala.xml.dtd.PEReference;
/*     */ import scala.xml.dtd.UnparsedEntityDecl;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}d!B\001\003\003\003I!a\006,bY&$\027\r^5oO6\013'o[;q\021\006tG\r\\3s\025\t\031A!A\004qCJ\034\030N\\4\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\007\001Qa\002\005\002\f\0315\t!!\003\002\016\005\tiQ*\031:lkBD\025M\0343mKJ\004\"a\004\013\016\003AQ!!\005\n\002\0171|wmZ5oO*\0211CB\001\005kRLG.\003\002\026!\t1Aj\\4hK\022DQa\006\001\005\002a\ta\001P5oSRtD#A\r\021\005-\001\001\"C\016\001\001\004\005\r\021\"\001\035\003%\021xn\034;MC\n,G.F\001\036!\tq\"E\004\002 A5\ta!\003\002\"\r\0051\001K]3eK\032L!a\t\023\003\rM#(/\0338h\025\t\tc\001C\005'\001\001\007\t\031!C\001O\005i!o\\8u\031\006\024W\r\\0%KF$\"\001K\026\021\005}I\023B\001\026\007\005\021)f.\033;\t\0171*\023\021!a\001;\005\031\001\020J\031\t\r9\002\001\025)\003\036\003)\021xn\034;MC\n,G\016\t\005\ba\001\001\r\021\"\0012\003\031\t8\013^1dWV\t!\007E\0024wyr!\001N\035\017\005UBT\"\001\034\013\005]B\021A\002\037s_>$h(C\001\b\023\tQd!A\004qC\016\\\027mZ3\n\005qj$\001\002'jgRT!A\017\004\021\005}y\024B\001!\007\005\rIe\016\036\005\b\005\002\001\r\021\"\001D\003)\t8\013^1dW~#S-\035\013\003Q\021Cq\001L!\002\002\003\007!\007\003\004G\001\001\006KAM\001\bcN#\030mY6!\021\035A\005\0011A\005\002%\013\001\"]\"veJ,g\016^\013\002}!91\n\001a\001\n\003a\025\001D9DkJ\024XM\034;`I\025\fHC\001\025N\021\035a#*!AA\002yBaa\024\001!B\023q\024!C9DkJ\024XM\034;!\021\035\t\006\0011A\005\002I\013\021\002Z3dYN#\030mY6\026\003M\0032aM\036U!\t)\006,D\001W\025\t9F!A\002ei\022L!!\027,\003\021\025cW-\034#fG2Dqa\027\001A\002\023\005A,A\007eK\016d7\013^1dW~#S-\035\013\003QuCq\001\f.\002\002\003\0071\013\003\004`\001\001\006KaU\001\013I\026\034Gn\025;bG.\004\003bB1\001\001\004%\tAY\001\fI\026\034GnQ;se\026tG/F\001U\021\035!\007\0011A\005\002\025\fq\002Z3dY\016+(O]3oi~#S-\035\013\003Q\031Dq\001L2\002\002\003\007A\013\003\004i\001\001\006K\001V\001\rI\026\034GnQ;se\026tG\017\t\005\bU\002\021\r\021\"\022l\0031I7OV1mS\022\fG/\0338h+\005aw\"A7\032\003\005Aaa\034\001!\002\033a\027!D5t-\006d\027\016Z1uS:<\007\005C\003r\001\021\005#/A\002m_\036$\"\001K:\t\013Q\004\b\031A\017\002\0075\034x\rC\003w\001\021\005s/\001\004f]\022$E\013\022\013\003QaDQ!_;A\002u\t\021A\034\005\006w\002!\t\005`\001\nK2,Wn\025;beR$\022\002K?\000\003\007\t9!a\005\t\013yT\b\031\001 \002\007A|7\017\003\004\002\002i\004\r!H\001\004aJ,\007BBA\003u\002\007Q$A\003mC\n,G\016C\004\002\ni\004\r!a\003\002\013\005$HO]:\021\t\0055\021qB\007\002\t%\031\021\021\003\003\003\0215+G/\031#bi\006Dq!!\006{\001\004\t9\"A\003tG>\004X\r\005\003\002\016\005e\021bAA\016\t\t\001b*Y7fgB\f7-\032\"j]\022Lgn\032\005\b\003?\001A\021IA\021\003\035)G.Z7F]\022$r\001KA\022\003K\t9\003\003\004\003;\001\rA\020\005\b\003\003\ti\0021\001\036\021\035\t)!!\bA\002uAq!a\013\001\t\013\ni#\001\005fY\026lG)Z2m)\025A\023qFA\032\021\035\t\t$!\013A\002u\tAA\\1nK\"9\021QGA\025\001\004i\022!B2ngR\024\bbBA\035\001\021\025\0231H\001\fCR$H*[:u\t\026\034G\016F\003)\003{\ty\004C\004\0022\005]\002\031A\017\t\021\005\005\023q\007a\001\003\007\nq!\031;u\031&\034H\017\005\0034w\005\025\003cA+\002H%\031\021\021\n,\003\021\005#HO\035#fG2Dq!!\024\001\t\013\ny%\001\nv]B\f'o]3e\013:$\030\016^=EK\016dGc\002\025\002R\005M\023Q\f\005\b\003c\tY\0051\001\036\021!\t)&a\023A\002\005]\023!B3yi&#\005cA+\002Z%\031\0211\f,\003\025\025CH/\032:oC2LE\tC\004\002`\005-\003\031A\017\002\0139|G/\031;\t\017\005\r\004\001\"\022\002f\005aan\034;bi&|g\016R3dYR)\001&a\032\002j!9\021qLA1\001\004i\002\002CA+\003C\002\r!a\026\t\017\0055\004\001\"\022\002p\005Y\001/\032*fM\026\024XM\\2f)\rA\023\021\017\005\b\003c\tY\0071\001\036\021\035\t)\b\001D\001\003o\nQC]3q_J$h+\0317jI\006$\030n\0348FeJ|'\017F\003)\003s\nY\b\003\004\003g\002\rA\020\005\b\003{\n\031\b1\001\036\003\r\031HO\035")
/*     */ public abstract class ValidatingMarkupHandler extends MarkupHandler {
/*     */   private String rootLabel;
/*     */   
/*     */   public String rootLabel() {
/*  19 */     return this.rootLabel;
/*     */   }
/*     */   
/*     */   public void rootLabel_$eq(String x$1) {
/*  19 */     this.rootLabel = x$1;
/*     */   }
/*     */   
/*  20 */   private List<Object> qStack = (List<Object>)Nil$.MODULE$;
/*     */   
/*     */   public List<Object> qStack() {
/*  20 */     return this.qStack;
/*     */   }
/*     */   
/*     */   public void qStack_$eq(List<Object> x$1) {
/*  20 */     this.qStack = x$1;
/*     */   }
/*     */   
/*  21 */   private int qCurrent = -1;
/*     */   
/*     */   public int qCurrent() {
/*  21 */     return this.qCurrent;
/*     */   }
/*     */   
/*     */   public void qCurrent_$eq(int x$1) {
/*  21 */     this.qCurrent = x$1;
/*     */   }
/*     */   
/*  23 */   private List<ElemDecl> declStack = (List<ElemDecl>)Nil$.MODULE$;
/*     */   
/*     */   public List<ElemDecl> declStack() {
/*  23 */     return this.declStack;
/*     */   }
/*     */   
/*     */   public void declStack_$eq(List<ElemDecl> x$1) {
/*  23 */     this.declStack = x$1;
/*     */   }
/*     */   
/*  24 */   private ElemDecl declCurrent = null;
/*     */   
/*     */   private final boolean isValidating;
/*     */   
/*     */   public ElemDecl declCurrent() {
/*  24 */     return this.declCurrent;
/*     */   }
/*     */   
/*     */   public void declCurrent_$eq(ElemDecl x$1) {
/*  24 */     this.declCurrent = x$1;
/*     */   }
/*     */   
/*     */   public final boolean isValidating() {
/*  26 */     return true;
/*     */   }
/*     */   
/*     */   public void log(String msg) {}
/*     */   
/*     */   public void endDTD(String n) {
/*  43 */     rootLabel_$eq(n);
/*     */   }
/*     */   
/*     */   private final void advanceDFA$1(DFAContentModel dm, int pos$1, String label$1) {
/*  48 */     Map trans = dm.dfa().delta()[qCurrent()];
/*  49 */     log((new StringBuilder()).append("advanceDFA(dm): ").append(dm).toString());
/*  50 */     log((new StringBuilder()).append("advanceDFA(trans): ").append(trans).toString());
/*  51 */     Option option = trans.get(new ContentModel.ElemName(label$1));
/*  52 */     if (option instanceof Some) {
/*  52 */       Some some = (Some)option;
/*  52 */       qCurrent_$eq(BoxesRunTime.unboxToInt(some.x()));
/*     */     } 
/*  53 */     reportValidationError(pos$1, (new StringBuilder()).append("DTD says, wrong element, expected one of ").append(trans.keys()).toString());
/*     */   }
/*     */   
/*     */   public void elemStart(int pos, String pre, String label, MetaData attrs, NamespaceBinding scope) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: new scala/collection/mutable/StringBuilder
/*     */     //   4: dup
/*     */     //   5: invokespecial <init> : ()V
/*     */     //   8: ldc '[qCurrent = '
/*     */     //   10: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual qCurrent : ()I
/*     */     //   17: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   20: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   23: ldc ' visiting '
/*     */     //   25: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   28: aload_3
/*     */     //   29: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   32: ldc ']'
/*     */     //   34: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   37: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   40: invokevirtual log : (Ljava/lang/String;)V
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual qCurrent : ()I
/*     */     //   47: iconst_m1
/*     */     //   48: if_icmpne -> 115
/*     */     //   51: aload_0
/*     */     //   52: ldc '  checking root'
/*     */     //   54: invokevirtual log : (Ljava/lang/String;)V
/*     */     //   57: aload_3
/*     */     //   58: aload_0
/*     */     //   59: invokevirtual rootLabel : ()Ljava/lang/String;
/*     */     //   62: astore #6
/*     */     //   64: dup
/*     */     //   65: ifnonnull -> 77
/*     */     //   68: pop
/*     */     //   69: aload #6
/*     */     //   71: ifnull -> 271
/*     */     //   74: goto -> 85
/*     */     //   77: aload #6
/*     */     //   79: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   82: ifne -> 271
/*     */     //   85: aload_0
/*     */     //   86: iload_1
/*     */     //   87: new scala/collection/mutable/StringBuilder
/*     */     //   90: dup
/*     */     //   91: invokespecial <init> : ()V
/*     */     //   94: ldc 'this element should be '
/*     */     //   96: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   99: aload_0
/*     */     //   100: invokevirtual rootLabel : ()Ljava/lang/String;
/*     */     //   103: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   106: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   109: invokevirtual reportValidationError : (ILjava/lang/String;)V
/*     */     //   112: goto -> 271
/*     */     //   115: aload_0
/*     */     //   116: ldc '  checking node'
/*     */     //   118: invokevirtual log : (Ljava/lang/String;)V
/*     */     //   121: aload_0
/*     */     //   122: invokevirtual declCurrent : ()Lscala/xml/dtd/ElemDecl;
/*     */     //   125: invokevirtual contentModel : ()Lscala/xml/dtd/ContentModel;
/*     */     //   128: astore #11
/*     */     //   130: getstatic scala/xml/dtd/ANY$.MODULE$ : Lscala/xml/dtd/ANY$;
/*     */     //   133: dup
/*     */     //   134: ifnonnull -> 146
/*     */     //   137: pop
/*     */     //   138: aload #11
/*     */     //   140: ifnull -> 271
/*     */     //   143: goto -> 154
/*     */     //   146: aload #11
/*     */     //   148: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   151: ifne -> 271
/*     */     //   154: getstatic scala/xml/dtd/EMPTY$.MODULE$ : Lscala/xml/dtd/EMPTY$;
/*     */     //   157: dup
/*     */     //   158: ifnonnull -> 170
/*     */     //   161: pop
/*     */     //   162: aload #11
/*     */     //   164: ifnull -> 178
/*     */     //   167: goto -> 188
/*     */     //   170: aload #11
/*     */     //   172: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   175: ifeq -> 188
/*     */     //   178: aload_0
/*     */     //   179: iload_1
/*     */     //   180: ldc 'DTD says, no elems, no text allowed here'
/*     */     //   182: invokevirtual reportValidationError : (ILjava/lang/String;)V
/*     */     //   185: goto -> 271
/*     */     //   188: getstatic scala/xml/dtd/PCDATA$.MODULE$ : Lscala/xml/dtd/PCDATA$;
/*     */     //   191: dup
/*     */     //   192: ifnonnull -> 204
/*     */     //   195: pop
/*     */     //   196: aload #11
/*     */     //   198: ifnull -> 212
/*     */     //   201: goto -> 222
/*     */     //   204: aload #11
/*     */     //   206: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   209: ifeq -> 222
/*     */     //   212: aload_0
/*     */     //   213: iload_1
/*     */     //   214: ldc 'DTD says, no elements allowed here'
/*     */     //   216: invokevirtual reportValidationError : (ILjava/lang/String;)V
/*     */     //   219: goto -> 271
/*     */     //   222: aload #11
/*     */     //   224: instanceof scala/xml/dtd/MIXED
/*     */     //   227: ifeq -> 248
/*     */     //   230: aload #11
/*     */     //   232: checkcast scala/xml/dtd/MIXED
/*     */     //   235: astore #7
/*     */     //   237: aload_0
/*     */     //   238: aload #7
/*     */     //   240: iload_1
/*     */     //   241: aload_3
/*     */     //   242: invokespecial advanceDFA$1 : (Lscala/xml/dtd/DFAContentModel;ILjava/lang/String;)V
/*     */     //   245: goto -> 271
/*     */     //   248: aload #11
/*     */     //   250: instanceof scala/xml/dtd/ELEMENTS
/*     */     //   253: ifeq -> 333
/*     */     //   256: aload #11
/*     */     //   258: checkcast scala/xml/dtd/ELEMENTS
/*     */     //   261: astore #8
/*     */     //   263: aload_0
/*     */     //   264: aload #8
/*     */     //   266: iload_1
/*     */     //   267: aload_3
/*     */     //   268: invokespecial advanceDFA$1 : (Lscala/xml/dtd/DFAContentModel;ILjava/lang/String;)V
/*     */     //   271: aload_0
/*     */     //   272: aload_0
/*     */     //   273: invokevirtual qCurrent : ()I
/*     */     //   276: istore #9
/*     */     //   278: aload_0
/*     */     //   279: invokevirtual qStack : ()Lscala/collection/immutable/List;
/*     */     //   282: iload #9
/*     */     //   284: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   287: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   290: invokevirtual qStack_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   293: aload_0
/*     */     //   294: aload_0
/*     */     //   295: invokevirtual declCurrent : ()Lscala/xml/dtd/ElemDecl;
/*     */     //   298: astore #10
/*     */     //   300: aload_0
/*     */     //   301: invokevirtual declStack : ()Lscala/collection/immutable/List;
/*     */     //   304: aload #10
/*     */     //   306: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   309: invokevirtual declStack_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   312: aload_0
/*     */     //   313: aload_0
/*     */     //   314: aload_3
/*     */     //   315: invokevirtual lookupElemDecl : (Ljava/lang/String;)Lscala/xml/dtd/ElemDecl;
/*     */     //   318: invokevirtual declCurrent_$eq : (Lscala/xml/dtd/ElemDecl;)V
/*     */     //   321: aload_0
/*     */     //   322: iconst_0
/*     */     //   323: invokevirtual qCurrent_$eq : (I)V
/*     */     //   326: aload_0
/*     */     //   327: ldc '  done  now'
/*     */     //   329: invokevirtual log : (Ljava/lang/String;)V
/*     */     //   332: return
/*     */     //   333: new scala/MatchError
/*     */     //   336: dup
/*     */     //   337: aload #11
/*     */     //   339: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   342: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #57	-> 0
/*     */     //   #59	-> 43
/*     */     //   #60	-> 51
/*     */     //   #61	-> 57
/*     */     //   #62	-> 85
/*     */     //   #64	-> 115
/*     */     //   #65	-> 121
/*     */     //   #66	-> 130
/*     */     //   #67	-> 154
/*     */     //   #68	-> 178
/*     */     //   #69	-> 188
/*     */     //   #70	-> 212
/*     */     //   #71	-> 222
/*     */     //   #72	-> 237
/*     */     //   #73	-> 248
/*     */     //   #74	-> 263
/*     */     //   #78	-> 271
/*     */     //   #79	-> 293
/*     */     //   #81	-> 312
/*     */     //   #82	-> 321
/*     */     //   #83	-> 326
/*     */     //   #65	-> 333
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	343	0	this	Lscala/xml/parsing/ValidatingMarkupHandler;
/*     */     //   0	343	1	pos	I
/*     */     //   0	343	2	pre	Ljava/lang/String;
/*     */     //   0	343	3	label	Ljava/lang/String;
/*     */     //   0	343	4	attrs	Lscala/xml/MetaData;
/*     */     //   0	343	5	scope	Lscala/xml/NamespaceBinding;
/*     */   }
/*     */   
/*     */   public void elemEnd(int pos, String pre, String label) {
/*  87 */     log("  elemEnd");
/*  88 */     qCurrent_$eq(BoxesRunTime.unboxToInt(qStack().head()));
/*  89 */     qStack_$eq((List<Object>)qStack().tail());
/*  90 */     declCurrent_$eq((ElemDecl)declStack().head());
/*  91 */     declStack_$eq((List<ElemDecl>)declStack().tail());
/*  92 */     log((new StringBuilder()).append("    qCurrent now").append(BoxesRunTime.boxToInteger(qCurrent())).toString());
/*  93 */     log((new StringBuilder()).append("    declCurrent now").append(declCurrent()).toString());
/*     */   }
/*     */   
/*     */   public final void elemDecl(String name, String cmstr) {
/*  97 */     ElemDecl elemDecl = new ElemDecl(name, ContentModel$.MODULE$.parse(cmstr));
/*  97 */     decls_$eq(decls().$colon$colon(elemDecl));
/*     */   }
/*     */   
/*     */   public final void attListDecl(String name, List attList) {
/* 101 */     AttListDecl attListDecl = new AttListDecl(name, attList);
/* 101 */     decls_$eq(decls().$colon$colon(attListDecl));
/*     */   }
/*     */   
/*     */   public final void unparsedEntityDecl(String name, ExternalID extID, String notat) {
/* 105 */     UnparsedEntityDecl unparsedEntityDecl = new UnparsedEntityDecl(name, extID, notat);
/* 105 */     decls_$eq(decls().$colon$colon(unparsedEntityDecl));
/*     */   }
/*     */   
/*     */   public final void notationDecl(String notat, ExternalID extID) {
/* 109 */     NotationDecl notationDecl = new NotationDecl(notat, extID);
/* 109 */     decls_$eq(decls().$colon$colon(notationDecl));
/*     */   }
/*     */   
/*     */   public final void peReference(String name) {
/* 113 */     PEReference pEReference = new PEReference(name);
/* 113 */     decls_$eq(decls().$colon$colon(pEReference));
/*     */   }
/*     */   
/*     */   public abstract void reportValidationError(int paramInt, String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ValidatingMarkupHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */