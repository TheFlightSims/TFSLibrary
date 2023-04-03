/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import scala.Console$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.io.Source;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.sys.package$;
/*     */ import scala.xml.Document;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.Node;
/*     */ import scala.xml.NodeBuffer;
/*     */ import scala.xml.NodeSeq;
/*     */ import scala.xml.NodeSeq$;
/*     */ import scala.xml.Null$;
/*     */ import scala.xml.PCData$;
/*     */ import scala.xml.TextBuffer$;
/*     */ import scala.xml.TopScope$;
/*     */ import scala.xml.Utility$;
/*     */ import scala.xml.Utility$Escapes$;
/*     */ import scala.xml.dtd.AttrDecl;
/*     */ import scala.xml.dtd.DEFAULT;
/*     */ import scala.xml.dtd.DefaultDecl;
/*     */ import scala.xml.dtd.EntityDef;
/*     */ import scala.xml.dtd.ExtDef;
/*     */ import scala.xml.dtd.ExternalID;
/*     */ import scala.xml.dtd.IntDef;
/*     */ import scala.xml.dtd.SystemID;
/*     */ 
/*     */ public abstract class MarkupParser$class {
/*     */   public static Nothing$ truncatedError(MarkupHandler $this, String msg) {
/*  39 */     throw new FatalError(msg);
/*     */   }
/*     */   
/*     */   public static Nothing$ errorNoEnd(MarkupHandler $this, String tag) {
/*  40 */     throw new FatalError((new StringBuilder()).append("expected closing tag of ").append(tag).toString());
/*     */   }
/*     */   
/*     */   public static void xHandleError(MarkupHandler $this, char that, String msg) {
/*  42 */     ((MarkupParser)$this).reportSyntaxError(msg);
/*     */   }
/*     */   
/*     */   public static void $init$(MarkupHandler $this) {
/*  55 */     ((MarkupParser)$this).curInput_$eq(((MarkupParser)$this).input());
/*  84 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq($this);
/*  87 */     ((MarkupParser)$this).inpStack_$eq((List<Source>)Nil$.MODULE$);
/*  93 */     ((MarkupParser)$this).extIndex_$eq(-1);
/*  99 */     ((MarkupParser)$this).nextChNeeded_$eq(false);
/* 100 */     ((MarkupParser)$this).reachedEof_$eq(false);
/* 124 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(new StringBuilder());
/* 126 */     ((MarkupParser)$this).dtd_$eq(null);
/* 128 */     ((MarkupParser)$this).doc_$eq(null);
/*     */   }
/*     */   
/*     */   public static BufferedIterator lookahead(MarkupHandler $this) {
/*     */     BufferedIterator<Object> bufferedIterator;
/*     */     Source source = ((MarkupParser)$this).curInput();
/*     */     if (source instanceof MarkupParser.WithLookAhead) {
/*     */       MarkupParser.WithLookAhead withLookAhead = (MarkupParser.WithLookAhead)source;
/*     */       bufferedIterator = withLookAhead.lookahead();
/*     */     } else {
/*     */       MarkupParser.WithLookAhead newInput = new MarkupParser.WithLookAhead($this, ((MarkupParser)$this).curInput());
/*     */       ((MarkupParser)$this).curInput_$eq(newInput);
/*     */       bufferedIterator = newInput.lookahead();
/*     */     } 
/*     */     return bufferedIterator;
/*     */   }
/*     */   
/*     */   public static char ch(MarkupHandler $this) {
/*     */     if (((MarkupParser)$this).nextChNeeded()) {
/*     */       if (((MarkupParser)$this).curInput().hasNext()) {
/*     */         ((MarkupParser)$this).lastChRead_$eq(((MarkupParser)$this).curInput().next());
/*     */         ((MarkupParser)$this).pos_$eq(((MarkupParser)$this).curInput().pos());
/*     */       } else {
/*     */         int ilen = ((MarkupParser)$this).inpStack().length();
/*     */         if (ilen != ((MarkupParser)$this).extIndex() && ilen > 0) {
/*     */           ((MarkupParser)$this).pop();
/*     */         } else {
/*     */           ((MarkupParser)$this).reachedEof_$eq(true);
/*     */           ((MarkupParser)$this).lastChRead_$eq((char)Character.MIN_VALUE);
/*     */         } 
/*     */       } 
/*     */       ((MarkupParser)$this).nextChNeeded_$eq(false);
/*     */     } 
/*     */     return ((MarkupParser)$this).lastChRead();
/*     */   }
/*     */   
/*     */   public static boolean eof(MarkupHandler $this) {
/* 130 */     ((MarkupParser)$this).ch();
/* 130 */     return ((MarkupParser)$this).reachedEof();
/*     */   }
/*     */   
/*     */   public static MetaData xmlProcInstr(MarkupHandler $this) {
/* 140 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("xml"));
/* 141 */     ((MarkupParserCommon)$this).xSpace();
/* 142 */     Tuple2<MetaData, NamespaceBinding> tuple2 = ((MarkupParser)$this).xAttributes((NamespaceBinding)TopScope$.MODULE$);
/* 142 */     if (tuple2 != null) {
/* 142 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 142 */       MetaData md = (MetaData)tuple21._1();
/* 142 */       NamespaceBinding scp = (NamespaceBinding)tuple21._2();
/* 143 */       TopScope$ topScope$ = TopScope$.MODULE$;
/* 143 */       if (scp == null) {
/* 143 */         if (topScope$ != null) {
/* 144 */           ((MarkupParser)$this).reportSyntaxError("no xmlns definitions here, please.");
/* 145 */           ((MarkupParserCommon)$this).xToken('?');
/* 146 */           ((MarkupParserCommon)$this).xToken('>');
/* 147 */           return md;
/*     */         } 
/*     */       } else if (!scp.equals(topScope$)) {
/*     */         ((MarkupParser)$this).reportSyntaxError("no xmlns definitions here, please.");
/*     */         ((MarkupParserCommon)$this).xToken('?');
/*     */         ((MarkupParserCommon)$this).xToken('>');
/* 147 */         return md;
/*     */       } 
/*     */       ((MarkupParserCommon)$this).xToken('?');
/*     */       ((MarkupParserCommon)$this).xToken('>');
/* 147 */       return md;
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   private static Tuple3 prologOrTextDecl(MarkupHandler $this, boolean isProlog) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   3: astore #16
/*     */     //   5: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   8: astore #17
/*     */     //   10: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   13: astore #18
/*     */     //   15: aload_0
/*     */     //   16: checkcast scala/xml/parsing/MarkupParser
/*     */     //   19: invokeinterface xmlProcInstr : ()Lscala/xml/MetaData;
/*     */     //   24: astore #12
/*     */     //   26: iconst_0
/*     */     //   27: istore #13
/*     */     //   29: iload_1
/*     */     //   30: ifeq -> 42
/*     */     //   33: aload_0
/*     */     //   34: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   37: invokeinterface xSpaceOpt : ()V
/*     */     //   42: aload #12
/*     */     //   44: ldc 'version'
/*     */     //   46: invokevirtual apply : (Ljava/lang/String;)Lscala/collection/Seq;
/*     */     //   49: astore_2
/*     */     //   50: aload_2
/*     */     //   51: ifnull -> 128
/*     */     //   54: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   57: aload_2
/*     */     //   58: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   61: astore_3
/*     */     //   62: aload_3
/*     */     //   63: invokevirtual isEmpty : ()Z
/*     */     //   66: ifne -> 117
/*     */     //   69: ldc '1.0'
/*     */     //   71: aload_3
/*     */     //   72: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   75: astore #4
/*     */     //   77: dup
/*     */     //   78: ifnonnull -> 90
/*     */     //   81: pop
/*     */     //   82: aload #4
/*     */     //   84: ifnull -> 98
/*     */     //   87: goto -> 117
/*     */     //   90: aload #4
/*     */     //   92: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   95: ifeq -> 117
/*     */     //   98: new scala/Some
/*     */     //   101: dup
/*     */     //   102: ldc '1.0'
/*     */     //   104: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   107: astore #16
/*     */     //   109: iconst_0
/*     */     //   110: iconst_1
/*     */     //   111: iadd
/*     */     //   112: istore #13
/*     */     //   114: goto -> 128
/*     */     //   117: aload_0
/*     */     //   118: checkcast scala/xml/parsing/MarkupParser
/*     */     //   121: ldc 'cannot deal with versions != 1.0'
/*     */     //   123: invokeinterface reportSyntaxError : (Ljava/lang/String;)V
/*     */     //   128: aload #12
/*     */     //   130: ldc 'encoding'
/*     */     //   132: invokevirtual apply : (Ljava/lang/String;)Lscala/collection/Seq;
/*     */     //   135: astore #5
/*     */     //   137: aload #5
/*     */     //   139: ifnull -> 256
/*     */     //   142: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   145: aload #5
/*     */     //   147: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   150: astore #6
/*     */     //   152: aload #6
/*     */     //   154: invokevirtual isEmpty : ()Z
/*     */     //   157: ifeq -> 170
/*     */     //   160: new scala/MatchError
/*     */     //   163: dup
/*     */     //   164: aload #5
/*     */     //   166: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   169: athrow
/*     */     //   170: aload_0
/*     */     //   171: checkcast scala/xml/parsing/TokenTests
/*     */     //   174: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   177: aload #6
/*     */     //   179: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   182: checkcast java/lang/String
/*     */     //   185: invokevirtual wrapString : (Ljava/lang/String;)Lscala/collection/immutable/WrappedString;
/*     */     //   188: invokeinterface isValidIANAEncoding : (Lscala/collection/Seq;)Z
/*     */     //   193: ifeq -> 219
/*     */     //   196: new scala/Some
/*     */     //   199: dup
/*     */     //   200: aload #6
/*     */     //   202: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   205: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   208: astore #17
/*     */     //   210: iload #13
/*     */     //   212: iconst_1
/*     */     //   213: iadd
/*     */     //   214: istore #13
/*     */     //   216: goto -> 256
/*     */     //   219: aload_0
/*     */     //   220: checkcast scala/xml/parsing/MarkupParser
/*     */     //   223: new scala/collection/mutable/StringBuilder
/*     */     //   226: dup
/*     */     //   227: invokespecial <init> : ()V
/*     */     //   230: ldc '"'
/*     */     //   232: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   235: aload #6
/*     */     //   237: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   240: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   243: ldc '" is not a valid encoding'
/*     */     //   245: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   248: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   251: invokeinterface reportSyntaxError : (Ljava/lang/String;)V
/*     */     //   256: iload_1
/*     */     //   257: ifeq -> 427
/*     */     //   260: aload #12
/*     */     //   262: ldc 'standalone'
/*     */     //   264: invokevirtual apply : (Ljava/lang/String;)Lscala/collection/Seq;
/*     */     //   267: astore #9
/*     */     //   269: aload #9
/*     */     //   271: ifnull -> 427
/*     */     //   274: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   277: aload #9
/*     */     //   279: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   282: astore #7
/*     */     //   284: aload #7
/*     */     //   286: invokevirtual isEmpty : ()Z
/*     */     //   289: ifne -> 344
/*     */     //   292: ldc 'yes'
/*     */     //   294: aload #7
/*     */     //   296: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   299: astore #8
/*     */     //   301: dup
/*     */     //   302: ifnonnull -> 314
/*     */     //   305: pop
/*     */     //   306: aload #8
/*     */     //   308: ifnull -> 322
/*     */     //   311: goto -> 344
/*     */     //   314: aload #8
/*     */     //   316: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   319: ifeq -> 344
/*     */     //   322: new scala/Some
/*     */     //   325: dup
/*     */     //   326: iconst_1
/*     */     //   327: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   330: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   333: astore #18
/*     */     //   335: iload #13
/*     */     //   337: iconst_1
/*     */     //   338: iadd
/*     */     //   339: istore #13
/*     */     //   341: goto -> 427
/*     */     //   344: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   347: aload #9
/*     */     //   349: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   352: astore #10
/*     */     //   354: aload #10
/*     */     //   356: invokevirtual isEmpty : ()Z
/*     */     //   359: ifne -> 415
/*     */     //   362: ldc_w 'no'
/*     */     //   365: aload #10
/*     */     //   367: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   370: astore #11
/*     */     //   372: dup
/*     */     //   373: ifnonnull -> 385
/*     */     //   376: pop
/*     */     //   377: aload #11
/*     */     //   379: ifnull -> 393
/*     */     //   382: goto -> 415
/*     */     //   385: aload #11
/*     */     //   387: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   390: ifeq -> 415
/*     */     //   393: new scala/Some
/*     */     //   396: dup
/*     */     //   397: iconst_0
/*     */     //   398: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   401: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   404: astore #18
/*     */     //   406: iload #13
/*     */     //   408: iconst_1
/*     */     //   409: iadd
/*     */     //   410: istore #13
/*     */     //   412: goto -> 427
/*     */     //   415: aload_0
/*     */     //   416: checkcast scala/xml/parsing/MarkupParser
/*     */     //   419: ldc_w 'either 'yes' or 'no' expected'
/*     */     //   422: invokeinterface reportSyntaxError : (Ljava/lang/String;)V
/*     */     //   427: aload #12
/*     */     //   429: invokevirtual length : ()I
/*     */     //   432: iload #13
/*     */     //   434: isub
/*     */     //   435: iconst_0
/*     */     //   436: if_icmpeq -> 496
/*     */     //   439: iload_1
/*     */     //   440: ifeq -> 449
/*     */     //   443: ldc_w 'SDDecl? '
/*     */     //   446: goto -> 452
/*     */     //   449: ldc_w ''
/*     */     //   452: astore #15
/*     */     //   454: aload_0
/*     */     //   455: checkcast scala/xml/parsing/MarkupParser
/*     */     //   458: new scala/collection/immutable/StringOps
/*     */     //   461: dup
/*     */     //   462: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   465: astore #14
/*     */     //   467: ldc_w 'VersionInfo EncodingDecl? %sor '?>' expected!'
/*     */     //   470: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   473: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   476: iconst_1
/*     */     //   477: anewarray java/lang/Object
/*     */     //   480: dup
/*     */     //   481: iconst_0
/*     */     //   482: aload #15
/*     */     //   484: aastore
/*     */     //   485: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   488: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   491: invokeinterface reportSyntaxError : (Ljava/lang/String;)V
/*     */     //   496: new scala/Tuple3
/*     */     //   499: dup
/*     */     //   500: aload #16
/*     */     //   502: aload #17
/*     */     //   504: aload #18
/*     */     //   506: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   509: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #153	-> 0
/*     */     //   #154	-> 5
/*     */     //   #155	-> 10
/*     */     //   #157	-> 15
/*     */     //   #158	-> 26
/*     */     //   #160	-> 29
/*     */     //   #161	-> 33
/*     */     //   #163	-> 42
/*     */     //   #164	-> 50
/*     */     //   #165	-> 54
/*     */     //   #163	-> 71
/*     */     //   #165	-> 72
/*     */     //   #166	-> 117
/*     */     //   #169	-> 128
/*     */     //   #170	-> 137
/*     */     //   #171	-> 142
/*     */     //   #169	-> 160
/*     */     //   #172	-> 170
/*     */     //   #169	-> 177
/*     */     //   #172	-> 179
/*     */     //   #175	-> 196
/*     */     //   #169	-> 200
/*     */     //   #175	-> 202
/*     */     //   #176	-> 210
/*     */     //   #173	-> 219
/*     */     //   #169	-> 235
/*     */     //   #173	-> 237
/*     */     //   #180	-> 256
/*     */     //   #181	-> 260
/*     */     //   #182	-> 269
/*     */     //   #183	-> 274
/*     */     //   #181	-> 294
/*     */     //   #183	-> 296
/*     */     //   #184	-> 344
/*     */     //   #181	-> 365
/*     */     //   #184	-> 367
/*     */     //   #185	-> 415
/*     */     //   #189	-> 427
/*     */     //   #190	-> 439
/*     */     //   #191	-> 454
/*     */     //   #194	-> 496
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	510	0	$this	Lscala/xml/parsing/MarkupHandler;
/*     */     //   0	510	1	isProlog	Z
/*     */     //   5	504	16	info_ver	Lscala/Option;
/*     */     //   10	499	17	info_enc	Lscala/Option;
/*     */     //   15	494	18	info_stdl	Lscala/Option;
/*     */     //   26	483	12	m	Lscala/xml/MetaData;
/*     */     //   29	480	13	n	I
/*     */     //   454	42	15	s	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public static Tuple3 prolog(MarkupHandler $this) {
/* 202 */     return prologOrTextDecl($this, true);
/*     */   }
/*     */   
/*     */   public static Tuple2 textDecl(MarkupHandler $this) {
/* 206 */     Tuple3 tuple3 = prologOrTextDecl($this, false);
/* 206 */     if (tuple3 != null)
/* 206 */       return new Tuple2(tuple3._1(), tuple3._2()); 
/* 206 */     throw new MatchError(tuple3);
/*     */   }
/*     */   
/*     */   public static Document document(MarkupHandler $this) {
/*     */     NodeSeq nodeSeq;
/* 217 */     ((MarkupParser)$this).doc_$eq(new Document());
/* 219 */     ((MarkupParser)$this).dtd_$eq(null);
/* 220 */     new Tuple3(None$.MODULE$, None$.MODULE$, None$.MODULE$);
/* 221 */     if ('<' != ((MarkupParser)$this).ch()) {
/* 222 */       ((MarkupParser)$this).reportSyntaxError("< expected");
/* 222 */       return null;
/*     */     } 
/* 226 */     ((MarkupParser)$this).nextch();
/* 227 */     if ('?' == ((MarkupParser)$this).ch()) {
/* 229 */       ((MarkupParser)$this).nextch();
/* 230 */       Tuple3<Option<String>, Option<String>, Option<Object>> info_prolog = ((MarkupParser)$this).prolog();
/* 231 */       ((MarkupParser)$this).doc().version_$eq((Option)info_prolog._1());
/* 232 */       ((MarkupParser)$this).doc().encoding_$eq((Option)info_prolog._2());
/* 233 */       ((MarkupParser)$this).doc().standAlone_$eq((Option)info_prolog._3());
/* 235 */       nodeSeq = ((MarkupParser)$this).content((NamespaceBinding)TopScope$.MODULE$);
/*     */     } else {
/* 238 */       NodeBuffer ts = new NodeBuffer();
/* 239 */       ((MarkupParser)$this).content1((NamespaceBinding)TopScope$.MODULE$, ts);
/* 240 */       ts.$amp$plus(((MarkupParser)$this).content((NamespaceBinding)TopScope$.MODULE$));
/* 241 */       nodeSeq = NodeSeq$.MODULE$.fromSeq((Seq)ts);
/*     */     } 
/* 244 */     IntRef elemCount = new IntRef(0);
/* 245 */     ObjectRef theNode = new ObjectRef(null);
/* 246 */     nodeSeq.foreach((Function1)new MarkupParser$$anonfun$document$1($this, elemCount, theNode));
/* 258 */     if (1 != elemCount.elem) {
/* 259 */       ((MarkupParser)$this).reportSyntaxError("document must contain exactly one element");
/* 260 */       Console$.MODULE$.println(nodeSeq.toList());
/*     */     } 
/* 263 */     ((MarkupParser)$this).doc().children_$eq((Seq)nodeSeq);
/* 264 */     ((MarkupParser)$this).doc().docElem_$eq((Node)theNode.elem);
/* 265 */     return ((MarkupParser)$this).doc();
/*     */   }
/*     */   
/*     */   public static StringBuilder putChar(MarkupHandler $this, char c) {
/* 269 */     return ((MarkupParser)$this).cbuf().append(c);
/*     */   }
/*     */   
/*     */   public static MarkupHandler initialize(MarkupHandler $this) {
/* 275 */     ((MarkupParser)$this).nextch();
/* 276 */     return $this;
/*     */   }
/*     */   
/*     */   public static char ch_returning_nextch(MarkupHandler $this) {
/* 279 */     char res = ((MarkupParser)$this).ch();
/* 279 */     ((MarkupParser)$this).nextch();
/* 279 */     return res;
/*     */   }
/*     */   
/*     */   public static Tuple2 mkAttributes(MarkupHandler $this, String name, NamespaceBinding pscope) {
/* 282 */     return ((TokenTests)$this).isNameStart(((MarkupParser)$this).ch()) ? ((MarkupParser)$this).xAttributes(pscope) : 
/* 283 */       new Tuple2(Null$.MODULE$, pscope);
/*     */   }
/*     */   
/*     */   public static NodeSeq mkProcInstr(MarkupHandler $this, int position, String name, String text) {
/* 286 */     return ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().procInstr(position, name, text);
/*     */   }
/*     */   
/*     */   public static void nextch(MarkupHandler $this) {
/* 291 */     ((MarkupParser)$this).ch();
/* 294 */     ((MarkupParser)$this).nextChNeeded_$eq(true);
/*     */   }
/*     */   
/*     */   public static Tuple2 xAttributes(MarkupHandler $this, NamespaceBinding pscope) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore #11
/*     */     //   3: getstatic scala/xml/Null$.MODULE$ : Lscala/xml/Null$;
/*     */     //   6: astore #10
/*     */     //   8: aload_0
/*     */     //   9: checkcast scala/xml/parsing/TokenTests
/*     */     //   12: aload_0
/*     */     //   13: checkcast scala/xml/parsing/MarkupParser
/*     */     //   16: invokeinterface ch : ()C
/*     */     //   21: invokeinterface isNameStart : (C)Z
/*     */     //   26: ifeq -> 340
/*     */     //   29: aload_0
/*     */     //   30: checkcast scala/xml/parsing/MarkupParser
/*     */     //   33: invokeinterface pos : ()I
/*     */     //   38: pop
/*     */     //   39: aload_0
/*     */     //   40: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   43: invokeinterface xName : ()Ljava/lang/String;
/*     */     //   48: astore #8
/*     */     //   50: aload_0
/*     */     //   51: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   54: invokeinterface xEQ : ()V
/*     */     //   59: aload_0
/*     */     //   60: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   63: invokeinterface xAttributeValue : ()Ljava/lang/String;
/*     */     //   68: astore #9
/*     */     //   70: iconst_0
/*     */     //   71: istore #5
/*     */     //   73: aconst_null
/*     */     //   74: astore #6
/*     */     //   76: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*     */     //   79: aload #8
/*     */     //   81: invokevirtual prefix : (Ljava/lang/String;)Lscala/Option;
/*     */     //   84: astore_2
/*     */     //   85: aload_2
/*     */     //   86: instanceof scala/Some
/*     */     //   89: ifeq -> 161
/*     */     //   92: iconst_1
/*     */     //   93: istore #5
/*     */     //   95: aload_2
/*     */     //   96: checkcast scala/Some
/*     */     //   99: astore #6
/*     */     //   101: ldc_w 'xmlns'
/*     */     //   104: aload #6
/*     */     //   106: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   109: astore_3
/*     */     //   110: dup
/*     */     //   111: ifnonnull -> 122
/*     */     //   114: pop
/*     */     //   115: aload_3
/*     */     //   116: ifnull -> 129
/*     */     //   119: goto -> 161
/*     */     //   122: aload_3
/*     */     //   123: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   126: ifeq -> 161
/*     */     //   129: aload #8
/*     */     //   131: bipush #6
/*     */     //   133: aload #8
/*     */     //   135: invokevirtual length : ()I
/*     */     //   138: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   141: astore #4
/*     */     //   143: new scala/xml/NamespaceBinding
/*     */     //   146: dup
/*     */     //   147: aload #4
/*     */     //   149: aload #9
/*     */     //   151: aload #11
/*     */     //   153: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lscala/xml/NamespaceBinding;)V
/*     */     //   156: astore #11
/*     */     //   158: goto -> 286
/*     */     //   161: iload #5
/*     */     //   163: ifeq -> 223
/*     */     //   166: aload #8
/*     */     //   168: aload #6
/*     */     //   170: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   173: checkcast java/lang/String
/*     */     //   176: invokevirtual length : ()I
/*     */     //   179: iconst_1
/*     */     //   180: iadd
/*     */     //   181: aload #8
/*     */     //   183: invokevirtual length : ()I
/*     */     //   186: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   189: astore #7
/*     */     //   191: new scala/xml/PrefixedAttribute
/*     */     //   194: dup
/*     */     //   195: aload #6
/*     */     //   197: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   200: checkcast java/lang/String
/*     */     //   203: aload #7
/*     */     //   205: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   208: aload #9
/*     */     //   210: invokevirtual apply : (Ljava/lang/String;)Lscala/xml/Text;
/*     */     //   213: aload #10
/*     */     //   215: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lscala/collection/Seq;Lscala/xml/MetaData;)V
/*     */     //   218: astore #10
/*     */     //   220: goto -> 286
/*     */     //   223: aload #8
/*     */     //   225: dup
/*     */     //   226: ifnonnull -> 239
/*     */     //   229: pop
/*     */     //   230: ldc_w 'xmlns'
/*     */     //   233: ifnull -> 248
/*     */     //   236: goto -> 265
/*     */     //   239: ldc_w 'xmlns'
/*     */     //   242: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   245: ifeq -> 265
/*     */     //   248: new scala/xml/NamespaceBinding
/*     */     //   251: dup
/*     */     //   252: aconst_null
/*     */     //   253: aload #9
/*     */     //   255: aload #11
/*     */     //   257: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lscala/xml/NamespaceBinding;)V
/*     */     //   260: astore #11
/*     */     //   262: goto -> 286
/*     */     //   265: new scala/xml/UnprefixedAttribute
/*     */     //   268: dup
/*     */     //   269: aload #8
/*     */     //   271: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   274: aload #9
/*     */     //   276: invokevirtual apply : (Ljava/lang/String;)Lscala/xml/Text;
/*     */     //   279: aload #10
/*     */     //   281: invokespecial <init> : (Ljava/lang/String;Lscala/collection/Seq;Lscala/xml/MetaData;)V
/*     */     //   284: astore #10
/*     */     //   286: aload_0
/*     */     //   287: checkcast scala/xml/parsing/MarkupParser
/*     */     //   290: invokeinterface ch : ()C
/*     */     //   295: bipush #47
/*     */     //   297: if_icmpeq -> 8
/*     */     //   300: aload_0
/*     */     //   301: checkcast scala/xml/parsing/MarkupParser
/*     */     //   304: invokeinterface ch : ()C
/*     */     //   309: bipush #62
/*     */     //   311: if_icmpeq -> 8
/*     */     //   314: bipush #63
/*     */     //   316: aload_0
/*     */     //   317: checkcast scala/xml/parsing/MarkupParser
/*     */     //   320: invokeinterface ch : ()C
/*     */     //   325: if_icmpeq -> 8
/*     */     //   328: aload_0
/*     */     //   329: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   332: invokeinterface xSpace : ()V
/*     */     //   337: goto -> 8
/*     */     //   340: aload #10
/*     */     //   342: aload #11
/*     */     //   344: invokevirtual wellformed : (Lscala/xml/NamespaceBinding;)Z
/*     */     //   347: ifne -> 362
/*     */     //   350: aload_0
/*     */     //   351: checkcast scala/xml/parsing/MarkupParser
/*     */     //   354: ldc_w 'double attribute'
/*     */     //   357: invokeinterface reportSyntaxError : (Ljava/lang/String;)V
/*     */     //   362: new scala/Tuple2
/*     */     //   365: dup
/*     */     //   366: aload #10
/*     */     //   368: aload #11
/*     */     //   370: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   373: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #303	-> 0
/*     */     //   #304	-> 3
/*     */     //   #305	-> 8
/*     */     //   #306	-> 29
/*     */     //   #308	-> 39
/*     */     //   #309	-> 50
/*     */     //   #310	-> 59
/*     */     //   #313	-> 70
/*     */     //   #312	-> 76
/*     */     //   #313	-> 85
/*     */     //   #312	-> 104
/*     */     //   #313	-> 106
/*     */     //   #314	-> 129
/*     */     //   #315	-> 143
/*     */     //   #313	-> 158
/*     */     //   #312	-> 161
/*     */     //   #318	-> 166
/*     */     //   #312	-> 168
/*     */     //   #318	-> 170
/*     */     //   #319	-> 191
/*     */     //   #312	-> 195
/*     */     //   #319	-> 197
/*     */     //   #317	-> 220
/*     */     //   #322	-> 223
/*     */     //   #323	-> 248
/*     */     //   #325	-> 265
/*     */     //   #328	-> 286
/*     */     //   #329	-> 328
/*     */     //   #332	-> 340
/*     */     //   #333	-> 350
/*     */     //   #335	-> 362
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	374	0	$this	Lscala/xml/parsing/MarkupHandler;
/*     */     //   0	374	1	pscope	Lscala/xml/NamespaceBinding;
/*     */     //   3	370	11	scope	Lscala/xml/NamespaceBinding;
/*     */     //   8	365	10	aMap	Lscala/xml/MetaData;
/*     */     //   50	324	8	qname	Ljava/lang/String;
/*     */     //   70	304	9	value	Ljava/lang/String;
/*     */     //   143	15	4	prefix	Ljava/lang/String;
/*     */     //   191	29	7	key	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public static String xEntityValue(MarkupHandler $this) {
/* 345 */     char endch = ((MarkupParser)$this).ch();
/* 346 */     ((MarkupParser)$this).nextch();
/* 347 */     while (((MarkupParser)$this).ch() != endch && !((MarkupParser)$this).eof()) {
/* 348 */       ((MarkupParser)$this).putChar(((MarkupParser)$this).ch());
/* 349 */       ((MarkupParser)$this).nextch();
/*     */     } 
/* 351 */     ((MarkupParser)$this).nextch();
/* 352 */     String str = ((MarkupParser)$this).cbuf().toString();
/* 353 */     ((MarkupParser)$this).cbuf().length_$eq(0);
/* 354 */     return str;
/*     */   }
/*     */   
/*     */   public static NodeSeq xCharData(MarkupHandler $this) {
/* 363 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("[CDATA["));
/* 368 */     return ((MarkupParserCommon)$this).<NodeSeq>xTakeUntil((Function2<Object, String, NodeSeq>)new MarkupParser$$anonfun$xCharData$2($this), (Function0)new MarkupParser$$anonfun$xCharData$1($this), "]]>");
/*     */   }
/*     */   
/*     */   public static final NodeSeq mkResult$1(MarkupHandler $this, int pos, String s) {
/*     */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().text(pos, s);
/*     */     return (NodeSeq)PCData$.MODULE$.apply(s);
/*     */   }
/*     */   
/*     */   public static NodeSeq xComment(MarkupHandler $this) {
/* 377 */     StringBuilder sb = new StringBuilder();
/* 378 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("--"));
/*     */     while (true) {
/* 379 */       if (((MarkupParser)$this).ch() == '-') {
/* 379 */         sb.append(((MarkupParser)$this).ch());
/* 379 */         ((MarkupParser)$this).nextch();
/* 379 */         if ((((MarkupParser)$this).ch() == '-')) {
/* 381 */           sb.length_$eq(sb.length() - 1);
/* 382 */           ((MarkupParser)$this).nextch();
/* 383 */           ((MarkupParserCommon)$this).xToken('>');
/* 384 */           return ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().comment(((MarkupParser)$this).pos(), sb.toString());
/*     */         } 
/*     */       } 
/* 385 */       sb.append(((MarkupParser)$this).ch());
/* 386 */       ((MarkupParser)$this).nextch();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void appendText(MarkupHandler $this, int pos, NodeBuffer ts, String txt) {
/* 393 */     if (((MarkupParser)$this).preserveWS()) {
/* 394 */       ts.$amp$plus(((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().text(pos, txt));
/*     */     } else {
/* 396 */       TextBuffer$.MODULE$.fromString(txt).toText().foreach((Function1)new MarkupParser$$anonfun$appendText$1($this, pos, ts));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void content1(MarkupHandler $this, NamespaceBinding pscope, NodeBuffer ts) {
/* 405 */     char c = ((MarkupParser)$this).ch();
/* 405 */     switch (c) {
/*     */       default:
/* 418 */         ts.$amp$plus(((MarkupParser)$this).element1(pscope));
/*     */         return;
/*     */       case '?':
/*     */         ((MarkupParser)$this).nextch();
/*     */         ts.$amp$plus(((MarkupParserCommon)$this).xProcInstr());
/*     */         return;
/*     */       case '!':
/*     */         break;
/*     */     } 
/*     */     ((MarkupParser)$this).nextch();
/*     */     if ('[' == ((MarkupParser)$this).ch()) {
/*     */       ts.$amp$plus(((MarkupParser)$this).xCharData());
/*     */     } else if ('D' == ((MarkupParser)$this).ch()) {
/*     */       ((MarkupParser)$this).parseDTD();
/*     */     } else {
/*     */       ts.$amp$plus(((MarkupParser)$this).xComment());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static NodeSeq content(MarkupHandler $this, NamespaceBinding pscope) {
/* 426 */     ObjectRef ts = new ObjectRef(new NodeBuffer());
/* 427 */     boolean exit = ((MarkupParser)$this).eof();
/*     */     while (true) {
/*     */       char c2, c3;
/*     */       NodeSeq theChar;
/*     */       String n;
/* 431 */       if (exit)
/* 466 */         return done$1($this, ts); 
/*     */       ((MarkupParser)$this).tmppos_$eq(((MarkupParser)$this).pos());
/*     */       exit = ((MarkupParser)$this).eof();
/*     */       if (((MarkupParser)$this).eof())
/*     */         return done$1($this, ts); 
/*     */       char c1 = ((MarkupParser)$this).ch();
/*     */       switch (c1) {
/*     */         default:
/*     */           ((MarkupParser)$this).appendText(((MarkupParser)$this).tmppos(), (NodeBuffer)ts.elem, xText($this));
/*     */         case '&':
/*     */           ((MarkupParser)$this).nextch();
/*     */           c3 = ((MarkupParser)$this).ch();
/*     */           switch (c3) {
/*     */             default:
/*     */               n = ((MarkupParserCommon)$this).xName();
/*     */               ((MarkupParserCommon)$this).xToken(';');
/*     */               if (Utility$Escapes$.MODULE$.pairs().contains(n))
/*     */                 ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().entityRef(((MarkupParser)$this).tmppos(), n); 
/*     */               ((MarkupParser)$this).push(n);
/*     */             case '#':
/*     */               break;
/*     */           } 
/*     */           ((MarkupParser)$this).nextch();
/*     */           theChar = ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().text(((MarkupParser)$this).tmppos(), ((MarkupParserCommon)$this).xCharRef((Function0<Object>)new MarkupParser$$anonfun$1($this), (Function0<BoxedUnit>)new MarkupParser$$anonfun$2($this)));
/*     */           ((MarkupParserCommon)$this).xToken(';');
/*     */         case '<':
/*     */           ((MarkupParser)$this).nextch();
/*     */           c2 = ((MarkupParser)$this).ch();
/*     */           switch (c2) {
/*     */             default:
/*     */               ((MarkupParser)$this).content1(pscope, (NodeBuffer)ts.elem);
/*     */               break;
/*     */             case '/':
/*     */               exit = true;
/*     */               break;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final NodeSeq done$1(MarkupHandler $this, ObjectRef ts$1) {
/*     */     return new MarkupParser$$anon$1($this, ts$1);
/*     */   }
/*     */   
/*     */   public static ExternalID externalID(MarkupHandler $this) {
/*     */     String pubID, sysID;
/* 473 */     char c = ((MarkupParser)$this).ch();
/* 473 */     switch (c) {
/*     */       default:
/* 473 */         throw new MatchError(BoxesRunTime.boxToCharacter(c));
/*     */       case 'P':
/* 481 */         ((MarkupParser)$this).nextch();
/* 481 */         ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("UBLIC"));
/* 482 */         ((MarkupParserCommon)$this).xSpace();
/* 483 */         pubID = ((MarkupParser)$this).pubidLiteral();
/* 484 */         ((MarkupParserCommon)$this).xSpace();
/* 485 */         sysID = ((MarkupParser)$this).systemLiteral();
/*     */       case 'S':
/*     */         break;
/*     */     } 
/*     */     ((MarkupParser)$this).nextch();
/*     */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("YSTEM"));
/*     */     ((MarkupParserCommon)$this).xSpace();
/*     */     String str1 = ((MarkupParser)$this).systemLiteral();
/*     */     return (ExternalID)new SystemID(str1);
/*     */   }
/*     */   
/*     */   public static void parseDTD(MarkupHandler $this) {
/* 496 */     ObjectRef extID = new ObjectRef(null);
/* 497 */     if (((MarkupParser)$this).dtd() != null)
/* 498 */       ((MarkupParser)$this).reportSyntaxError("unexpected character (DOCTYPE already defined"); 
/* 499 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("DOCTYPE"));
/* 500 */     ((MarkupParserCommon)$this).xSpace();
/* 501 */     String n = ((MarkupParserCommon)$this).xName();
/* 502 */     ((MarkupParserCommon)$this).xSpace();
/* 504 */     if ('S' == ((MarkupParser)$this).ch() || 'P' == ((MarkupParser)$this).ch()) {
/* 505 */       extID.elem = ((MarkupParser)$this).externalID();
/* 506 */       ((MarkupParserCommon)$this).xSpaceOpt();
/*     */     } 
/* 512 */     if ((ExternalID)extID.elem != null && $this.isValidating()) {
/* 514 */       ((MarkupParser)$this).pushExternal(((ExternalID)extID.elem).systemId());
/* 515 */       ((MarkupParser)$this).extIndex_$eq(((MarkupParser)$this).inpStack().length());
/* 517 */       ((MarkupParser)$this).extSubset();
/* 518 */       ((MarkupParser)$this).pop();
/* 519 */       ((MarkupParser)$this).extIndex_$eq(-1);
/*     */     } 
/* 522 */     if ('[' == ((MarkupParser)$this).ch()) {
/* 523 */       ((MarkupParser)$this).nextch();
/* 525 */       ((MarkupParser)$this).intSubset();
/* 527 */       ((MarkupParserCommon)$this).xToken(']');
/* 528 */       ((MarkupParserCommon)$this).xSpaceOpt();
/*     */     } 
/* 530 */     ((MarkupParserCommon)$this).xToken('>');
/* 531 */     ((MarkupParser)$this).dtd_$eq(new MarkupParser$$anon$4($this, extID));
/* 536 */     if (((MarkupParser)$this).doc() != null)
/* 537 */       ((MarkupParser)$this).doc().dtd_$eq(((MarkupParser)$this).dtd()); 
/* 539 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().endDTD(n);
/*     */   }
/*     */   
/*     */   public static NodeSeq element(MarkupHandler $this, NamespaceBinding pscope) {
/* 543 */     ((MarkupParserCommon)$this).xToken('<');
/* 544 */     return ((MarkupParser)$this).element1(pscope);
/*     */   }
/*     */   
/*     */   public static NodeSeq element1(MarkupHandler $this, NamespaceBinding pscope) {
/*     */     String qname;
/*     */     MetaData aMap;
/*     */     NamespaceBinding scope;
/*     */     Tuple2 tuple2;
/* 552 */     int pos = ((MarkupParser)$this).pos();
/* 553 */     Tuple2<String, Object> tuple21 = ((MarkupParserCommon)$this).xTag(pscope);
/* 553 */     if (tuple21 != null && tuple21._2() != null) {
/* 553 */       Tuple3 tuple3 = new Tuple3(tuple21._1(), ((Tuple2)tuple21._2())._1(), ((Tuple2)tuple21._2())._2());
/* 553 */       qname = (String)tuple3._1();
/* 553 */       aMap = (MetaData)tuple3._2();
/* 553 */       scope = (NamespaceBinding)tuple3._3();
/* 554 */       Option option = Utility$.MODULE$.prefix(qname);
/* 555 */       if (option instanceof Some) {
/* 555 */         Some some = (Some)option;
/* 555 */         Predef$ predef$ = Predef$.MODULE$;
/*     */       } 
/* 556 */       tuple2 = new Tuple2(null, qname);
/*     */     } else {
/*     */       throw new MatchError(tuple21);
/*     */     } 
/*     */     if (tuple2 != null) {
/*     */       Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
/*     */       String pre = (String)tuple22._1(), local = (String)tuple22._2();
/* 560 */       ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("/>"));
/* 561 */       ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().elemStart(pos, pre, local, aMap, scope);
/* 565 */       ((MarkupParserCommon)$this).xToken('>');
/* 566 */       ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().elemStart(pos, pre, local, aMap, scope);
/* 567 */       NodeSeq tmp = ((MarkupParser)$this).content(scope);
/* 568 */       ((MarkupParserCommon)$this).xEndTag(qname);
/* 569 */       NodeSeq ts = (((MarkupParser)$this).ch() == '/') ? NodeSeq$.MODULE$.Empty() : tmp;
/* 572 */       NodeSeq nodeSeq1 = NodeSeq$.MODULE$.Empty();
/* 572 */       if (ts == null) {
/* 572 */         if (nodeSeq1 != null);
/* 572 */       } else if (ts.equals(nodeSeq1)) {
/*     */       
/*     */       } 
/*     */     } else {
/*     */       throw new MatchError(tuple2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String xText(MarkupHandler $this) {
/* 582 */     boolean exit = false;
/*     */     while (true) {
/* 583 */       if (exit) {
/* 590 */         String str = ((MarkupParser)$this).cbuf().toString();
/* 591 */         ((MarkupParser)$this).cbuf().length_$eq(0);
/* 592 */         return str;
/*     */       } 
/*     */       ((MarkupParser)$this).putChar(((MarkupParser)$this).ch());
/*     */       ((MarkupParser)$this).pos();
/*     */       ((MarkupParser)$this).nextch();
/*     */       exit = (((MarkupParser)$this).eof() || ((MarkupParser)$this).ch() == '<' || ((MarkupParser)$this).ch() == '&');
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String systemLiteral(MarkupHandler $this) {
/* 601 */     char endch = ((MarkupParser)$this).ch();
/* 602 */     if (((MarkupParser)$this).ch() != '\'' && ((MarkupParser)$this).ch() != '"')
/* 603 */       ((MarkupParser)$this).reportSyntaxError("quote ' or \" expected"); 
/* 604 */     ((MarkupParser)$this).nextch();
/* 605 */     while (((MarkupParser)$this).ch() != endch && !((MarkupParser)$this).eof()) {
/* 606 */       ((MarkupParser)$this).putChar(((MarkupParser)$this).ch());
/* 607 */       ((MarkupParser)$this).nextch();
/*     */     } 
/* 609 */     ((MarkupParser)$this).nextch();
/* 610 */     String str = ((MarkupParser)$this).cbuf().toString();
/* 611 */     ((MarkupParser)$this).cbuf().length_$eq(0);
/* 612 */     return str;
/*     */   }
/*     */   
/*     */   public static String pubidLiteral(MarkupHandler $this) {
/* 619 */     char endch = ((MarkupParser)$this).ch();
/* 620 */     if (((MarkupParser)$this).ch() != '\'' && ((MarkupParser)$this).ch() != '"')
/* 621 */       ((MarkupParser)$this).reportSyntaxError("quote ' or \" expected"); 
/* 622 */     ((MarkupParser)$this).nextch();
/* 623 */     while (((MarkupParser)$this).ch() != endch && !((MarkupParser)$this).eof()) {
/* 624 */       ((MarkupParser)$this).putChar(((MarkupParser)$this).ch());
/* 626 */       if (!((TokenTests)$this).isPubIDChar(((MarkupParser)$this).ch()))
/* 627 */         ((MarkupParser)$this).reportSyntaxError((new StringBuilder()).append("char '").append(BoxesRunTime.boxToCharacter(((MarkupParser)$this).ch())).append("' is not allowed in public id").toString()); 
/* 628 */       ((MarkupParser)$this).nextch();
/*     */     } 
/* 630 */     ((MarkupParser)$this).nextch();
/* 631 */     String str = ((MarkupParser)$this).cbuf().toString();
/* 632 */     ((MarkupParser)$this).cbuf().length_$eq(0);
/* 633 */     return str;
/*     */   }
/*     */   
/*     */   public static void extSubset(MarkupHandler $this) {
/* 643 */     ((MarkupParser)$this).nextch();
/* 645 */     ((MarkupParser)$this).nextch();
/* 646 */     ((MarkupParser)$this).textDecl();
/* 648 */     (((MarkupParser)$this).ch() == '<') ? ((((MarkupParser)$this).ch() == '?') ? BoxedUnit.UNIT : ((MarkupParser)$this).markupDecl1()) : BoxedUnit.UNIT;
/*     */     while (true) {
/* 650 */       if (((MarkupParser)$this).eof())
/*     */         return; 
/* 651 */       ((MarkupParser)$this).markupDecl();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final void doInclude$1(MarkupHandler $this) {
/* 656 */     ((MarkupParserCommon)$this).xToken('[');
/* 656 */     for (; ']' != ((MarkupParser)$this).ch(); ((MarkupParser)$this).markupDecl());
/* 656 */     ((MarkupParser)$this).nextch();
/*     */   }
/*     */   
/*     */   private static final void doIgnore$1(MarkupHandler $this) {
/* 659 */     ((MarkupParserCommon)$this).xToken('[');
/* 659 */     for (; ']' != ((MarkupParser)$this).ch(); ((MarkupParser)$this).nextch());
/* 659 */     ((MarkupParser)$this).nextch();
/*     */   }
/*     */   
/*     */   public static Object markupDecl1(MarkupHandler $this) {
/*     */     // Byte code:
/*     */     //   0: bipush #63
/*     */     //   2: aload_0
/*     */     //   3: checkcast scala/xml/parsing/MarkupParser
/*     */     //   6: invokeinterface ch : ()C
/*     */     //   11: if_icmpne -> 35
/*     */     //   14: aload_0
/*     */     //   15: checkcast scala/xml/parsing/MarkupParser
/*     */     //   18: invokeinterface nextch : ()V
/*     */     //   23: aload_0
/*     */     //   24: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   27: invokeinterface xProcInstr : ()Ljava/lang/Object;
/*     */     //   32: goto -> 744
/*     */     //   35: aload_0
/*     */     //   36: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   39: bipush #33
/*     */     //   41: invokeinterface xToken : (C)V
/*     */     //   46: aload_0
/*     */     //   47: checkcast scala/xml/parsing/MarkupParser
/*     */     //   50: invokeinterface ch : ()C
/*     */     //   55: istore_1
/*     */     //   56: iload_1
/*     */     //   57: lookupswitch default -> 514, 45 -> 735, 65 -> 649, 69 -> 673, 78 -> 625, 91 -> 108
/*     */     //   108: aload_0
/*     */     //   109: checkcast scala/xml/parsing/MarkupParser
/*     */     //   112: invokeinterface inpStack : ()Lscala/collection/immutable/List;
/*     */     //   117: invokevirtual length : ()I
/*     */     //   120: aload_0
/*     */     //   121: checkcast scala/xml/parsing/MarkupParser
/*     */     //   124: invokeinterface extIndex : ()I
/*     */     //   129: if_icmplt -> 514
/*     */     //   132: aload_0
/*     */     //   133: checkcast scala/xml/parsing/MarkupParser
/*     */     //   136: invokeinterface nextch : ()V
/*     */     //   141: aload_0
/*     */     //   142: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   145: invokeinterface xSpaceOpt : ()V
/*     */     //   150: aload_0
/*     */     //   151: checkcast scala/xml/parsing/MarkupParser
/*     */     //   154: invokeinterface ch : ()C
/*     */     //   159: istore_2
/*     */     //   160: iload_2
/*     */     //   161: lookupswitch default -> 188, 37 -> 337, 73 -> 200
/*     */     //   188: new scala/MatchError
/*     */     //   191: dup
/*     */     //   192: iload_2
/*     */     //   193: invokestatic boxToCharacter : (C)Ljava/lang/Character;
/*     */     //   196: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   199: athrow
/*     */     //   200: aload_0
/*     */     //   201: checkcast scala/xml/parsing/MarkupParser
/*     */     //   204: invokeinterface nextch : ()V
/*     */     //   209: aload_0
/*     */     //   210: checkcast scala/xml/parsing/MarkupParser
/*     */     //   213: invokeinterface ch : ()C
/*     */     //   218: istore_3
/*     */     //   219: iload_3
/*     */     //   220: lookupswitch default -> 248, 71 -> 294, 78 -> 260
/*     */     //   248: new scala/MatchError
/*     */     //   251: dup
/*     */     //   252: iload_3
/*     */     //   253: invokestatic boxToCharacter : (C)Ljava/lang/Character;
/*     */     //   256: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   259: athrow
/*     */     //   260: aload_0
/*     */     //   261: checkcast scala/xml/parsing/MarkupParser
/*     */     //   264: invokeinterface nextch : ()V
/*     */     //   269: aload_0
/*     */     //   270: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   273: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   276: ldc_w 'NCLUDE'
/*     */     //   279: invokevirtual wrapString : (Ljava/lang/String;)Lscala/collection/immutable/WrappedString;
/*     */     //   282: invokeinterface xToken : (Lscala/collection/Seq;)V
/*     */     //   287: aload_0
/*     */     //   288: invokestatic doInclude$1 : (Lscala/xml/parsing/MarkupHandler;)V
/*     */     //   291: goto -> 476
/*     */     //   294: aload_0
/*     */     //   295: checkcast scala/xml/parsing/MarkupParser
/*     */     //   298: invokeinterface nextch : ()V
/*     */     //   303: aload_0
/*     */     //   304: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   307: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   310: ldc_w 'NORE'
/*     */     //   313: invokevirtual wrapString : (Ljava/lang/String;)Lscala/collection/immutable/WrappedString;
/*     */     //   316: invokeinterface xToken : (Lscala/collection/Seq;)V
/*     */     //   321: aload_0
/*     */     //   322: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   325: invokeinterface xSpaceOpt : ()V
/*     */     //   330: aload_0
/*     */     //   331: invokestatic doIgnore$1 : (Lscala/xml/parsing/MarkupHandler;)V
/*     */     //   334: goto -> 476
/*     */     //   337: aload_0
/*     */     //   338: checkcast scala/xml/parsing/MarkupParser
/*     */     //   341: invokeinterface nextch : ()V
/*     */     //   346: aload_0
/*     */     //   347: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   350: invokeinterface xName : ()Ljava/lang/String;
/*     */     //   355: astore #4
/*     */     //   357: aload_0
/*     */     //   358: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   361: bipush #59
/*     */     //   363: invokeinterface xToken : (C)V
/*     */     //   368: aload_0
/*     */     //   369: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   372: invokeinterface xSpaceOpt : ()V
/*     */     //   377: aload_0
/*     */     //   378: checkcast scala/xml/parsing/MarkupParser
/*     */     //   381: aload #4
/*     */     //   383: invokeinterface push : (Ljava/lang/String;)V
/*     */     //   388: aload_0
/*     */     //   389: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   392: invokeinterface xSpaceOpt : ()V
/*     */     //   397: aload_0
/*     */     //   398: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   401: invokeinterface xName : ()Ljava/lang/String;
/*     */     //   406: astore #5
/*     */     //   408: aload_0
/*     */     //   409: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   412: invokeinterface xSpaceOpt : ()V
/*     */     //   417: ldc_w 'INCLUDE'
/*     */     //   420: dup
/*     */     //   421: ifnonnull -> 433
/*     */     //   424: pop
/*     */     //   425: aload #5
/*     */     //   427: ifnull -> 441
/*     */     //   430: goto -> 448
/*     */     //   433: aload #5
/*     */     //   435: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   438: ifeq -> 448
/*     */     //   441: aload_0
/*     */     //   442: invokestatic doInclude$1 : (Lscala/xml/parsing/MarkupHandler;)V
/*     */     //   445: goto -> 476
/*     */     //   448: ldc_w 'IGNORE'
/*     */     //   451: dup
/*     */     //   452: ifnonnull -> 464
/*     */     //   455: pop
/*     */     //   456: aload #5
/*     */     //   458: ifnull -> 472
/*     */     //   461: goto -> 504
/*     */     //   464: aload #5
/*     */     //   466: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   469: ifeq -> 504
/*     */     //   472: aload_0
/*     */     //   473: invokestatic doIgnore$1 : (Lscala/xml/parsing/MarkupHandler;)V
/*     */     //   476: aload_0
/*     */     //   477: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   480: bipush #93
/*     */     //   482: invokeinterface xToken : (C)V
/*     */     //   487: aload_0
/*     */     //   488: checkcast scala/xml/parsing/MarkupParserCommon
/*     */     //   491: bipush #62
/*     */     //   493: invokeinterface xToken : (C)V
/*     */     //   498: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   501: goto -> 744
/*     */     //   504: new scala/MatchError
/*     */     //   507: dup
/*     */     //   508: aload #5
/*     */     //   510: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   513: athrow
/*     */     //   514: aload_0
/*     */     //   515: checkcast scala/xml/parsing/MarkupParser
/*     */     //   518: invokeinterface curInput : ()Lscala/io/Source;
/*     */     //   523: astore #6
/*     */     //   525: aload_0
/*     */     //   526: checkcast scala/xml/parsing/MarkupParser
/*     */     //   529: invokeinterface pos : ()I
/*     */     //   534: istore #7
/*     */     //   536: new scala/collection/mutable/StringBuilder
/*     */     //   539: dup
/*     */     //   540: invokespecial <init> : ()V
/*     */     //   543: ldc_w 'unexpected character ''
/*     */     //   546: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   549: aload_0
/*     */     //   550: checkcast scala/xml/parsing/MarkupParser
/*     */     //   553: invokeinterface ch : ()C
/*     */     //   558: invokestatic boxToCharacter : (C)Ljava/lang/Character;
/*     */     //   561: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   564: ldc_w '', expected some markupdecl'
/*     */     //   567: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   570: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   573: astore #8
/*     */     //   575: aload #6
/*     */     //   577: invokevirtual reportError$default$3 : ()Ljava/io/PrintStream;
/*     */     //   580: astore #9
/*     */     //   582: aload #6
/*     */     //   584: iload #7
/*     */     //   586: aload #8
/*     */     //   588: aload #9
/*     */     //   590: invokevirtual reportError : (ILjava/lang/String;Ljava/io/PrintStream;)V
/*     */     //   593: aload_0
/*     */     //   594: checkcast scala/xml/parsing/MarkupParser
/*     */     //   597: invokeinterface ch : ()C
/*     */     //   602: bipush #62
/*     */     //   604: if_icmpeq -> 619
/*     */     //   607: aload_0
/*     */     //   608: checkcast scala/xml/parsing/MarkupParser
/*     */     //   611: invokeinterface nextch : ()V
/*     */     //   616: goto -> 593
/*     */     //   619: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   622: goto -> 744
/*     */     //   625: aload_0
/*     */     //   626: checkcast scala/xml/parsing/MarkupParser
/*     */     //   629: invokeinterface nextch : ()V
/*     */     //   634: aload_0
/*     */     //   635: checkcast scala/xml/parsing/MarkupParser
/*     */     //   638: invokeinterface notationDecl : ()V
/*     */     //   643: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   646: goto -> 744
/*     */     //   649: aload_0
/*     */     //   650: checkcast scala/xml/parsing/MarkupParser
/*     */     //   653: invokeinterface nextch : ()V
/*     */     //   658: aload_0
/*     */     //   659: checkcast scala/xml/parsing/MarkupParser
/*     */     //   662: invokeinterface attrDecl : ()V
/*     */     //   667: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   670: goto -> 744
/*     */     //   673: aload_0
/*     */     //   674: checkcast scala/xml/parsing/MarkupParser
/*     */     //   677: invokeinterface nextch : ()V
/*     */     //   682: bipush #76
/*     */     //   684: aload_0
/*     */     //   685: checkcast scala/xml/parsing/MarkupParser
/*     */     //   688: invokeinterface ch : ()C
/*     */     //   693: if_icmpne -> 720
/*     */     //   696: aload_0
/*     */     //   697: checkcast scala/xml/parsing/MarkupParser
/*     */     //   700: invokeinterface nextch : ()V
/*     */     //   705: aload_0
/*     */     //   706: checkcast scala/xml/parsing/MarkupParser
/*     */     //   709: invokeinterface elementDecl : ()V
/*     */     //   714: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   717: goto -> 744
/*     */     //   720: aload_0
/*     */     //   721: checkcast scala/xml/parsing/MarkupParser
/*     */     //   724: invokeinterface entityDecl : ()V
/*     */     //   729: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   732: goto -> 744
/*     */     //   735: aload_0
/*     */     //   736: checkcast scala/xml/parsing/MarkupParser
/*     */     //   739: invokeinterface xComment : ()Lscala/xml/NodeSeq;
/*     */     //   744: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #661	-> 0
/*     */     //   #662	-> 14
/*     */     //   #663	-> 23
/*     */     //   #665	-> 35
/*     */     //   #666	-> 46
/*     */     //   #686	-> 108
/*     */     //   #687	-> 132
/*     */     //   #688	-> 141
/*     */     //   #689	-> 150
/*     */     //   #707	-> 200
/*     */     //   #708	-> 209
/*     */     //   #715	-> 260
/*     */     //   #716	-> 269
/*     */     //   #717	-> 287
/*     */     //   #710	-> 294
/*     */     //   #711	-> 303
/*     */     //   #712	-> 321
/*     */     //   #713	-> 330
/*     */     //   #691	-> 337
/*     */     //   #692	-> 346
/*     */     //   #693	-> 357
/*     */     //   #694	-> 368
/*     */     //   #696	-> 377
/*     */     //   #697	-> 388
/*     */     //   #698	-> 397
/*     */     //   #699	-> 408
/*     */     //   #703	-> 417
/*     */     //   #701	-> 417
/*     */     //   #704	-> 448
/*     */     //   #720	-> 476
/*     */     //   #721	-> 487
/*     */     //   #701	-> 504
/*     */     //   #724	-> 514
/*     */     //   #725	-> 593
/*     */     //   #726	-> 607
/*     */     //   #723	-> 619
/*     */     //   #683	-> 625
/*     */     //   #684	-> 634
/*     */     //   #679	-> 649
/*     */     //   #680	-> 658
/*     */     //   #671	-> 673
/*     */     //   #672	-> 682
/*     */     //   #673	-> 696
/*     */     //   #674	-> 705
/*     */     //   #676	-> 720
/*     */     //   #668	-> 735
/*     */     //   #661	-> 744
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	745	0	$this	Lscala/xml/parsing/MarkupHandler;
/*     */     //   357	388	4	ent	Ljava/lang/String;
/*     */     //   408	337	5	stmt	Ljava/lang/String;
/*     */     //   525	68	6	qual$1	Lscala/io/Source;
/*     */     //   536	57	7	x$6	I
/*     */     //   575	18	8	x$7	Ljava/lang/String;
/*     */     //   582	11	9	x$8	Ljava/io/PrintStream;
/*     */   }
/*     */   
/*     */   public static void markupDecl(MarkupHandler $this) {
/* 731 */     char c = ((MarkupParser)$this).ch();
/* 731 */     switch (c) {
/*     */       default:
/* 745 */         if (((TokenTests)$this).isSpace(((MarkupParser)$this).ch())) {
/* 746 */           ((MarkupParserCommon)$this).xSpace();
/*     */         } else {
/* 748 */           ((MarkupParser)$this).reportSyntaxError((new StringBuilder()).append("markupdecl: unexpected character '").append(BoxesRunTime.boxToCharacter(((MarkupParser)$this).ch())).append("' #").append(BoxesRunTime.boxToInteger(((MarkupParser)$this).ch())).toString());
/* 749 */           ((MarkupParser)$this).nextch();
/*     */         } 
/*     */         return;
/*     */       case '<':
/*     */         ((MarkupParser)$this).nextch();
/*     */         ((MarkupParser)$this).markupDecl1();
/*     */         return;
/*     */       case '%':
/*     */         break;
/*     */     } 
/*     */     ((MarkupParser)$this).nextch();
/*     */     String ent = ((MarkupParserCommon)$this).xName();
/*     */     ((MarkupParserCommon)$this).xToken(';');
/*     */     if ($this.isValidating()) {
/*     */       ((MarkupParser)$this).push(ent);
/*     */     } else {
/*     */       ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().peReference(ent);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void intSubset(MarkupHandler $this) {
/* 756 */     ((MarkupParserCommon)$this).xSpace();
/* 757 */     while (']' != ((MarkupParser)$this).ch())
/* 758 */       ((MarkupParser)$this).markupDecl(); 
/*     */   }
/*     */   
/*     */   public static void elementDecl(MarkupHandler $this) {
/* 764 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("EMENT"));
/* 765 */     ((MarkupParserCommon)$this).xSpace();
/* 766 */     String n = ((MarkupParserCommon)$this).xName();
/* 767 */     ((MarkupParserCommon)$this).xSpace();
/* 768 */     while ('>' != ((MarkupParser)$this).ch()) {
/* 770 */       ((MarkupParser)$this).putChar(((MarkupParser)$this).ch());
/* 771 */       ((MarkupParser)$this).nextch();
/*     */     } 
/* 774 */     ((MarkupParser)$this).nextch();
/* 775 */     String cmstr = ((MarkupParser)$this).cbuf().toString();
/* 776 */     ((MarkupParser)$this).cbuf().length_$eq(0);
/* 777 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().elemDecl(n, cmstr);
/*     */   }
/*     */   
/*     */   public static void attrDecl(MarkupHandler $this) {
/*     */     List list;
/* 784 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("TTLIST"));
/* 785 */     ((MarkupParserCommon)$this).xSpace();
/* 786 */     String n = ((MarkupParserCommon)$this).xName();
/* 787 */     ((MarkupParserCommon)$this).xSpace();
/* 788 */     Nil$ nil$ = Nil$.MODULE$;
/* 791 */     while ('>' != ((MarkupParser)$this).ch()) {
/* 792 */       String str1, aname = ((MarkupParserCommon)$this).xName();
/* 793 */       ((MarkupParserCommon)$this).xSpace();
/* 795 */       while ('"' != ((MarkupParser)$this).ch() && '\'' != ((MarkupParser)$this).ch() && '#' != ((MarkupParser)$this).ch() && '<' != ((MarkupParser)$this).ch()) {
/* 796 */         ((TokenTests)$this).isSpace(((MarkupParser)$this).ch()) ? BoxedUnit.UNIT : (
/* 797 */           (MarkupParser)$this).cbuf().append(((MarkupParser)$this).ch());
/* 798 */         ((MarkupParser)$this).nextch();
/*     */       } 
/* 800 */       String atpe = ((MarkupParser)$this).cbuf().toString();
/* 801 */       ((MarkupParser)$this).cbuf().length_$eq(0);
/* 803 */       char c = ((MarkupParser)$this).ch();
/* 803 */       switch (c) {
/*     */         default:
/*     */         
/*     */         case '#':
/* 808 */           ((MarkupParser)$this).nextch();
/* 809 */           str1 = ((MarkupParserCommon)$this).xName();
/*     */         case '"':
/*     */         case '\'':
/*     */           break;
/*     */       } 
/*     */       DEFAULT dEFAULT = new DEFAULT(false, ((MarkupParserCommon)$this).xAttributeValue());
/* 817 */       ((MarkupParserCommon)$this).xSpaceOpt();
/* 819 */       list = nil$.$colon$colon(new AttrDecl(aname, atpe, (DefaultDecl)dEFAULT));
/* 820 */       ((MarkupParser)$this).cbuf().length_$eq(0);
/*     */       continue;
/*     */       throw new MatchError(SYNTHETIC_LOCAL_VARIABLE_3);
/*     */     } 
/* 822 */     ((MarkupParser)$this).nextch();
/* 823 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().attListDecl(n, list.reverse());
/*     */   }
/*     */   
/*     */   public static void entityDecl(MarkupHandler $this) {
/*     */     String av;
/* 830 */     boolean isParameterEntity = false;
/* 831 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("NTITY"));
/* 833 */     ((MarkupParserCommon)$this).xSpace();
/* 834 */     if ('%' == ((MarkupParser)$this).ch()) {
/* 835 */       ((MarkupParser)$this).nextch();
/* 836 */       isParameterEntity = true;
/* 837 */       ((MarkupParserCommon)$this).xSpace();
/*     */     } 
/* 839 */     String n = ((MarkupParserCommon)$this).xName();
/* 840 */     ((MarkupParserCommon)$this).xSpace();
/* 841 */     char c = ((MarkupParser)$this).ch();
/* 841 */     switch (c) {
/*     */       default:
/* 841 */         throw new MatchError(BoxesRunTime.boxToCharacter(c));
/*     */       case '"':
/*     */       case '\'':
/* 864 */         av = ((MarkupParser)$this).xEntityValue();
/* 865 */         ((MarkupParserCommon)$this).xSpaceOpt();
/* 866 */         ((MarkupParserCommon)$this).xToken('>');
/* 867 */         if (isParameterEntity) {
/* 868 */           ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().parameterEntityDecl(n, (EntityDef)new IntDef(av));
/*     */         } else {
/* 870 */           ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().parsedEntityDecl(n, (EntityDef)new IntDef(av));
/*     */         } 
/*     */         return;
/*     */       case 'P':
/*     */       case 'S':
/*     */         break;
/*     */     } 
/*     */     ExternalID extID = ((MarkupParser)$this).externalID();
/*     */     if (isParameterEntity) {
/*     */       ((MarkupParserCommon)$this).xSpaceOpt();
/*     */       ((MarkupParserCommon)$this).xToken('>');
/*     */       ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().parameterEntityDecl(n, (EntityDef)new ExtDef(extID));
/*     */     } else {
/*     */       ((MarkupParserCommon)$this).xSpace();
/*     */       if ('>' != ((MarkupParser)$this).ch()) {
/*     */         ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("NDATA"));
/*     */         ((MarkupParserCommon)$this).xSpace();
/*     */         String notat = ((MarkupParserCommon)$this).xName();
/*     */         ((MarkupParserCommon)$this).xSpaceOpt();
/*     */         ((MarkupParserCommon)$this).xToken('>');
/*     */         ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().unparsedEntityDecl(n, extID, notat);
/*     */       } else {
/*     */         ((MarkupParser)$this).nextch();
/*     */         ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().parsedEntityDecl(n, (EntityDef)new ExtDef(extID));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void notationDecl(MarkupHandler $this) {
/* 879 */     ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("OTATION"));
/* 880 */     ((MarkupParserCommon)$this).xSpace();
/* 881 */     String notat = ((MarkupParserCommon)$this).xName();
/* 882 */     ((MarkupParserCommon)$this).xSpace();
/* 886 */     if (((MarkupParser)$this).ch() == 'P') {
/* 888 */       ((MarkupParser)$this).nextch();
/* 889 */       ((MarkupParserCommon)$this).xToken((Seq<Object>)Predef$.MODULE$.wrapString("UBLIC"));
/* 890 */       ((MarkupParserCommon)$this).xSpace();
/* 891 */       String pubID = ((MarkupParser)$this).pubidLiteral();
/* 892 */       ((MarkupParserCommon)$this).xSpaceOpt();
/* 893 */       String sysID = (((MarkupParser)$this).ch() != '>') ? (
/* 894 */         (MarkupParser)$this).systemLiteral() : null;
/*     */     } else {
/* 899 */       ((MarkupParser)$this).reportSyntaxError("PUBLIC or SYSTEM expected");
/* 900 */       throw package$.MODULE$.error("died parsing notationdecl");
/*     */     } 
/*     */     ExternalID extID = (((MarkupParser)$this).ch() == 'S') ? ((MarkupParser)$this).externalID() : (ExternalID)"JD-Core does not support Kotlin";
/* 902 */     ((MarkupParserCommon)$this).xSpaceOpt();
/* 903 */     ((MarkupParserCommon)$this).xToken('>');
/* 904 */     ((MarkupParser)$this).scala$xml$parsing$MarkupParser$$handle().notationDecl(notat, extID);
/*     */   }
/*     */   
/*     */   public static void reportSyntaxError(MarkupHandler $this, int pos, String str) {
/* 907 */     Source qual$2 = ((MarkupParser)$this).curInput();
/* 907 */     PrintStream x$11 = qual$2.reportError$default$3();
/* 907 */     qual$2.reportError(pos, str, x$11);
/*     */   }
/*     */   
/*     */   public static void reportSyntaxError(MarkupHandler $this, String str) {
/* 908 */     ((MarkupParser)$this).reportSyntaxError(((MarkupParser)$this).pos(), str);
/*     */   }
/*     */   
/*     */   public static void reportValidationError(MarkupHandler $this, int pos, String str) {
/* 909 */     ((MarkupParser)$this).reportSyntaxError(pos, str);
/*     */   }
/*     */   
/*     */   public static void push(MarkupHandler $this, String entityName) {
/* 912 */     if (!((MarkupParser)$this).eof()) {
/* 913 */       Source source = ((MarkupParser)$this).curInput();
/* 913 */       ((MarkupParser)$this).inpStack_$eq(((MarkupParser)$this).inpStack().$colon$colon(source));
/*     */     } 
/* 916 */     ((MarkupParser)$this).ch();
/* 918 */     ((MarkupParser)$this).curInput_$eq($this.replacementText(entityName));
/* 919 */     ((MarkupParser)$this).nextch();
/*     */   }
/*     */   
/*     */   public static void pushExternal(MarkupHandler $this, String systemId) {
/* 923 */     if (!((MarkupParser)$this).eof()) {
/* 924 */       Source source = ((MarkupParser)$this).curInput();
/* 924 */       ((MarkupParser)$this).inpStack_$eq(((MarkupParser)$this).inpStack().$colon$colon(source));
/*     */     } 
/* 927 */     ((MarkupParser)$this).ch();
/* 929 */     ((MarkupParser)$this).curInput_$eq(((MarkupParser)$this).externalSource(systemId));
/* 930 */     ((MarkupParser)$this).nextch();
/*     */   }
/*     */   
/*     */   public static void pop(MarkupHandler $this) {
/* 934 */     ((MarkupParser)$this).curInput_$eq((Source)((MarkupParser)$this).inpStack().head());
/* 935 */     ((MarkupParser)$this).inpStack_$eq((List<Source>)((MarkupParser)$this).inpStack().tail());
/* 936 */     ((MarkupParser)$this).lastChRead_$eq(((MarkupParser)$this).curInput().ch());
/* 937 */     ((MarkupParser)$this).nextChNeeded_$eq(false);
/* 938 */     ((MarkupParser)$this).pos_$eq(((MarkupParser)$this).curInput().pos());
/* 939 */     ((MarkupParser)$this).reachedEof_$eq(false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\MarkupParser$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */