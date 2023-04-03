/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.Stack;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichChar$;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.Node;
/*     */ import scala.xml.Null$;
/*     */ import scala.xml.ProcInstr;
/*     */ import scala.xml.Text;
/*     */ import scala.xml.TopScope$;
/*     */ import scala.xml.factory.XMLLoader;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mg!B\001\003\003\003I!A\004$bGR|'/_!eCB$XM\035\006\003\007\021\tq\001]1sg&twM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001aE\002\001\025U\001\"aC\n\016\0031Q!!\004\b\002\017!,G\016]3sg*\021q\002E\001\004g\006D(BA\003\022\025\005\021\022aA8sO&\021A\003\004\002\017\t\0264\027-\0367u\021\006tG\r\\3s!\r1\022dG\007\002/)\021\001\004B\001\bM\006\034Go\034:z\023\tQrCA\005Y\0332cu.\0313feB\021A$H\007\002\t%\021a\004\002\002\005\035>$W\rC\003!\001\021\005\021%\001\004=S:LGO\020\013\002EA\0211\005A\007\002\005!9Q\005\001a\001\n\0031\023\001\003:p_R,E.Z7\026\003mAq\001\013\001A\002\023\005\021&\001\007s_>$X\t\\3n?\022*\027\017\006\002+]A\0211\006L\007\002\r%\021QF\002\002\005+:LG\017C\0040O\005\005\t\031A\016\002\007a$\023\007\003\0042\001\001\006KaG\001\ne>|G/\0227f[\002Bqa\r\001C\002\023\005A'\001\004ck\0324WM]\013\002kA\021agO\007\002o)\021\001(O\001\b[V$\030M\0317f\025\tQd!\001\006d_2dWm\031;j_:L!\001P\034\003\033M#(/\0338h\005VLG\016Z3s\021\031q\004\001)A\005k\0059!-\0364gKJ\004\003b\002!\001\005\004%\t!Q\001\fCR$(/\0332Ti\006\0347.F\001C!\r14)R\005\003\t^\022Qa\025;bG.\004\"\001\b$\n\005\035#!\001C'fi\006$\025\r^1\t\r%\003\001\025!\003C\0031\tG\017\036:jEN#\030mY6!\021\035Y\005A1A\005\0021\013a\001[*uC\016\\W#A'\021\007Y\0325\004\003\004P\001\001\006I!T\001\bQN#\030mY6!\021\035\t\006A1A\005\002I\013\001\002^1h'R\f7m[\013\002'B\031ag\021+\021\005UCfBA\026W\023\t9f!\001\004Qe\026$WMZ\005\0033j\023aa\025;sS:<'BA,\007\021\031a\006\001)A\005'\006IA/Y4Ti\006\0347\016\t\005\b=\002\001\r\021\"\001`\003)\0318m\0349f'R\f7m[\013\002AB\031agQ1\021\005q\021\027BA2\005\005Aq\025-\\3ta\006\034WMQ5oI&tw\rC\004f\001\001\007I\021\0014\002\035M\034w\016]3Ti\006\0347n\030\023fcR\021!f\032\005\b_\021\f\t\0211\001a\021\031I\007\001)Q\005A\006Y1oY8qKN#\030mY6!\021\035Y\007\0011A\005\0021\faaY;s)\006<W#\001+\t\0179\004\001\031!C\001_\006Q1-\036:UC\036|F%Z9\025\005)\002\bbB\030n\003\003\005\r\001\026\005\007e\002\001\013\025\002+\002\017\r,(\017V1hA!9A\017\001a\001\n\003)\030aB2baR,(/Z\013\002mB\0211f^\005\003q\032\021qAQ8pY\026\fg\016C\004{\001\001\007I\021A>\002\027\r\f\007\017^;sK~#S-\035\013\003UqDqaL=\002\002\003\007a\017\003\004\001\001\006KA^\001\tG\006\004H/\036:fA!9\021\021\001\001\007\002\005\r\021\001\0058pI\026\034uN\034;bS:\034H+\032=u)\r1\030Q\001\005\007\003\017y\b\031\001+\002\0231|7-\0317OC6,\007bBA\006\001\031\005\021QB\001\013GJ,\027\r^3O_\022,GcC\016\002\020\005M\021qCA\016\003?Aq!!\005\002\n\001\007A+A\002qe\026Dq!!\006\002\n\001\007A+\001\005fY\026lg*Y7f\021\035\tI\"!\003A\002\025\013q!\031;ue&\0247\017C\004\002\036\005%\001\031A1\002\013M\034w\016]3\t\021\005\005\022\021\002a\001\003G\taa\0315Ji\026\024\b#BA\023\003kYb\002BA\024\003cqA!!\013\00205\021\0211\006\006\004\003[A\021A\002\037s_>$h(C\001\b\023\r\t\031DB\001\ba\006\0347.Y4f\023\021\t9$!\017\003\t1K7\017\036\006\004\003g1\001bBA\037\001\031\005\021qH\001\013GJ,\027\r^3UKb$H\003BA!\003\017\0022\001HA\"\023\r\t)\005\002\002\005)\026DH\017C\004\002J\005m\002\031\001+\002\tQ,\007\020\036\005\b\003\033\002a\021AA(\003=\031'/Z1uKB\023xnY%ogR\024HCBA)\003;\n\t\007\005\004\002&\005M\023qK\005\005\003+\nIDA\002TKF\0042\001HA-\023\r\tY\006\002\002\n!J|7-\0238tiJDq!a\030\002L\001\007A+\001\004uCJ<W\r\036\005\b\003G\nY\0051\001U\003\021!\027\r^1\t\021\005\035\004A1A\005\002U\f1C\\8s[\006d\027N_3XQ&$Xm\0359bG\026Dq!a\033\001A\003%a/\001\013o_Jl\027\r\\5{K^C\027\016^3ta\006\034W\r\t\005\b\003_\002A\021IA9\003)\031\007.\031:bGR,'o\035\013\bU\005M\0241QAG\021!\t)(!\034A\002\005]\024AA2i!\025Y\023\021PA?\023\r\tYH\002\002\006\003J\024\030-\037\t\004W\005}\024bAAA\r\t!1\t[1s\021!\t))!\034A\002\005\035\025AB8gMN,G\017E\002,\003\023K1!a#\007\005\rIe\016\036\005\t\003\037\013i\0071\001\002\b\0061A.\0328hi\"Dq!a%\001\t\023\t)*A\005ta2LGOT1nKR!\021qSAO!\025Y\023\021\024+U\023\r\tYJ\002\002\007)V\004H.\032\032\t\017\005}\025\021\023a\001)\006\t1\017C\004\002$\002!\t%!*\002\031M$\030M\035;FY\026lWM\034;\025\023)\n9+a+\0020\006M\006bBAU\003C\003\r\001V\001\004kJL\007bBAW\003C\003\r\001V\001\013?2|7-\0317OC6,\007bBAY\003C\003\r\001V\001\006c:\fW.\032\005\t\003k\013\t\0131\001\0028\006Q\021\r\036;sS\n,H/Z:\021\t\005e\0261X\007\002\035%\031\021Q\030\b\003\025\005#HO]5ckR,7\017C\004\002B\002!\t!a1\002\027\r\f\007\017^;sKR+\007\020\036\013\002U!9\021q\031\001\005B\005%\027AC3oI\026cW-\\3oiR9!&a3\002N\006=\007bBAU\003\013\004\r\001\026\005\b\003[\013)\r1\001U\021\035\t\t,!2A\002QCq!a5\001\t\003\n).A\013qe>\034Wm]:j]\036Len\035;sk\016$\030n\0348\025\013)\n9.!7\t\017\005}\023\021\033a\001)\"9\0211MAi\001\004!\006")
/*     */ public abstract class FactoryAdapter extends DefaultHandler implements XMLLoader<Node> {
/*     */   private Node rootElem;
/*     */   
/*     */   private final StringBuilder buffer;
/*     */   
/*     */   private final Stack<MetaData> attribStack;
/*     */   
/*     */   private final Stack<Node> hStack;
/*     */   
/*     */   private final Stack<String> tagStack;
/*     */   
/*     */   private Stack<NamespaceBinding> scopeStack;
/*     */   
/*     */   private String curTag;
/*     */   
/*     */   private boolean capture;
/*     */   
/*     */   private final boolean normalizeWhitespace;
/*     */   
/*     */   public FactoryAdapter adapter() {
/*  37 */     return XMLLoader.class.adapter(this);
/*     */   }
/*     */   
/*     */   public SAXParser parser() {
/*  37 */     return XMLLoader.class.parser(this);
/*     */   }
/*     */   
/*     */   public Node loadXML(InputSource source, SAXParser parser) {
/*  37 */     return XMLLoader.class.loadXML(this, source, parser);
/*     */   }
/*     */   
/*     */   public Node loadFile(File file) {
/*  37 */     return XMLLoader.class.loadFile(this, file);
/*     */   }
/*     */   
/*     */   public Node loadFile(FileDescriptor fd) {
/*  37 */     return XMLLoader.class.loadFile(this, fd);
/*     */   }
/*     */   
/*     */   public Node loadFile(String name) {
/*  37 */     return XMLLoader.class.loadFile(this, name);
/*     */   }
/*     */   
/*     */   public Node load(InputStream is) {
/*  37 */     return XMLLoader.class.load(this, is);
/*     */   }
/*     */   
/*     */   public Node load(Reader reader) {
/*  37 */     return XMLLoader.class.load(this, reader);
/*     */   }
/*     */   
/*     */   public Node load(String sysID) {
/*  37 */     return XMLLoader.class.load(this, sysID);
/*     */   }
/*     */   
/*     */   public Node load(InputSource source) {
/*  37 */     return XMLLoader.class.load(this, source);
/*     */   }
/*     */   
/*     */   public Node load(URL url) {
/*  37 */     return XMLLoader.class.load(this, url);
/*     */   }
/*     */   
/*     */   public Node loadString(String string) {
/*  37 */     return XMLLoader.class.loadString(this, string);
/*     */   }
/*     */   
/*     */   public FactoryAdapter() {
/*  37 */     XMLLoader.class.$init$(this);
/*  38 */     this.rootElem = null;
/*  40 */     this.buffer = new StringBuilder();
/*  41 */     this.attribStack = new Stack();
/*  42 */     this.hStack = new Stack();
/*  43 */     this.tagStack = new Stack();
/*  44 */     this.scopeStack = new Stack();
/*  46 */     this.curTag = null;
/*  47 */     this.capture = false;
/*  79 */     this.normalizeWhitespace = false;
/*     */   }
/*     */   
/*     */   public Node rootElem() {
/*     */     return this.rootElem;
/*     */   }
/*     */   
/*     */   public void rootElem_$eq(Node x$1) {
/*     */     this.rootElem = x$1;
/*     */   }
/*     */   
/*     */   public StringBuilder buffer() {
/*     */     return this.buffer;
/*     */   }
/*     */   
/*     */   public Stack<MetaData> attribStack() {
/*     */     return this.attribStack;
/*     */   }
/*     */   
/*     */   public Stack<Node> hStack() {
/*     */     return this.hStack;
/*     */   }
/*     */   
/*     */   public Stack<String> tagStack() {
/*     */     return this.tagStack;
/*     */   }
/*     */   
/*     */   public Stack<NamespaceBinding> scopeStack() {
/*     */     return this.scopeStack;
/*     */   }
/*     */   
/*     */   public void scopeStack_$eq(Stack<NamespaceBinding> x$1) {
/*     */     this.scopeStack = x$1;
/*     */   }
/*     */   
/*     */   public String curTag() {
/*     */     return this.curTag;
/*     */   }
/*     */   
/*     */   public void curTag_$eq(String x$1) {
/*     */     this.curTag = x$1;
/*     */   }
/*     */   
/*     */   public boolean capture() {
/*     */     return this.capture;
/*     */   }
/*     */   
/*     */   public void capture_$eq(boolean x$1) {
/*     */     this.capture = x$1;
/*     */   }
/*     */   
/*     */   public boolean normalizeWhitespace() {
/*  79 */     return this.normalizeWhitespace;
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int offset, int length) {
/*  87 */     if (capture()) {
/*  89 */       if (normalizeWhitespace()) {
/*  92 */         Iterator it = Predef$.MODULE$.charArrayOps((char[])Predef$.MODULE$.charArrayOps(ch).slice(offset, offset + length)).iterator();
/*  93 */         while (it.hasNext()) {
/*  94 */           char c = BoxesRunTime.unboxToChar(it.next());
/*  95 */           Predef$ predef$ = Predef$.MODULE$;
/*  95 */           boolean isSpace = RichChar$.MODULE$.isWhitespace$extension(c);
/*  96 */           buffer().append(isSpace ? 32 : c);
/*  97 */           if (isSpace)
/*  98 */             it = it.dropWhile((Function1)new FactoryAdapter$$anonfun$characters$1(this)); 
/*     */         } 
/*     */       } else {
/*     */         buffer().appendAll(ch, offset, length);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public class FactoryAdapter$$anonfun$characters$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char x$1) {
/*  98 */       Predef$ predef$ = Predef$.MODULE$;
/*  98 */       return RichChar$.MODULE$.isWhitespace$extension(x$1);
/*     */     }
/*     */     
/*     */     public FactoryAdapter$$anonfun$characters$1(FactoryAdapter $outer) {}
/*     */   }
/*     */   
/*     */   public Tuple2<String, String> scala$xml$parsing$FactoryAdapter$$splitName(String s) {
/* 104 */     int idx = s.indexOf(':');
/* 106 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 106 */     return (idx < 0) ? new Tuple2(null, s) : new Tuple2((new StringOps(s)).take(idx), (new StringOps(s)).drop(idx + 1));
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String _localName, String qname, Attributes attributes) {
/* 118 */     captureText();
/* 119 */     tagStack().push(curTag());
/* 120 */     curTag_$eq(qname);
/* 122 */     String localName = (String)scala$xml$parsing$FactoryAdapter$$splitName(qname)._2();
/* 123 */     capture_$eq(nodeContainsText(localName));
/* 125 */     hStack().push(null);
/* 126 */     ObjectRef m = new ObjectRef(Null$.MODULE$);
/* 127 */     ObjectRef scpe = 
/* 128 */       scopeStack().isEmpty() ? new ObjectRef(TopScope$.MODULE$) : 
/* 129 */       new ObjectRef(scopeStack().top());
/* 131 */     Predef$ predef$ = Predef$.MODULE$;
/* 131 */     int i = attributes.getLength();
/* 131 */     Range$ range$ = Range$.MODULE$;
/* 131 */     FactoryAdapter$$anonfun$startElement$1 factoryAdapter$$anonfun$startElement$1 = new FactoryAdapter$$anonfun$startElement$1(this, attributes, m, scpe);
/*     */     Range range;
/* 131 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)factoryAdapter$$anonfun$startElement$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 131 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 131 */         factoryAdapter$$anonfun$startElement$1.apply$mcVI$sp(i1);
/* 131 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 145 */     scopeStack().push(scpe.elem);
/* 146 */     attribStack().push(m.elem);
/*     */   }
/*     */   
/*     */   public class FactoryAdapter$$anonfun$startElement$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Attributes attributes$1;
/*     */     
/*     */     private final ObjectRef m$1;
/*     */     
/*     */     private final ObjectRef scpe$1;
/*     */     
/*     */     public final void apply(int i) {
/*     */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public FactoryAdapter$$anonfun$startElement$1(FactoryAdapter $outer, Attributes attributes$1, ObjectRef m$1, ObjectRef scpe$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield attributes$1 : Lorg/xml/sax/Attributes;
/*     */       //   4: iload_1
/*     */       //   5: invokeinterface getQName : (I)Ljava/lang/String;
/*     */       //   10: astore_3
/*     */       //   11: aload_0
/*     */       //   12: getfield attributes$1 : Lorg/xml/sax/Attributes;
/*     */       //   15: iload_1
/*     */       //   16: invokeinterface getValue : (I)Ljava/lang/String;
/*     */       //   21: astore #7
/*     */       //   23: aload_0
/*     */       //   24: getfield $outer : Lscala/xml/parsing/FactoryAdapter;
/*     */       //   27: aload_3
/*     */       //   28: invokevirtual scala$xml$parsing$FactoryAdapter$$splitName : (Ljava/lang/String;)Lscala/Tuple2;
/*     */       //   31: astore #8
/*     */       //   33: aload #8
/*     */       //   35: ifnull -> 217
/*     */       //   38: new scala/Tuple2
/*     */       //   41: dup
/*     */       //   42: aload #8
/*     */       //   44: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   47: aload #8
/*     */       //   49: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   52: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   55: astore_2
/*     */       //   56: aload_2
/*     */       //   57: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   60: checkcast java/lang/String
/*     */       //   63: astore #5
/*     */       //   65: aload_2
/*     */       //   66: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   69: checkcast java/lang/String
/*     */       //   72: astore #6
/*     */       //   74: aload #5
/*     */       //   76: dup
/*     */       //   77: ifnonnull -> 89
/*     */       //   80: pop
/*     */       //   81: ldc 'xmlns'
/*     */       //   83: ifnull -> 124
/*     */       //   86: goto -> 97
/*     */       //   89: ldc 'xmlns'
/*     */       //   91: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   94: ifne -> 124
/*     */       //   97: aload #5
/*     */       //   99: ifnonnull -> 172
/*     */       //   102: aload_3
/*     */       //   103: dup
/*     */       //   104: ifnonnull -> 116
/*     */       //   107: pop
/*     */       //   108: ldc 'xmlns'
/*     */       //   110: ifnull -> 124
/*     */       //   113: goto -> 172
/*     */       //   116: ldc 'xmlns'
/*     */       //   118: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   121: ifeq -> 172
/*     */       //   124: aload #5
/*     */       //   126: ifnonnull -> 133
/*     */       //   129: aconst_null
/*     */       //   130: goto -> 135
/*     */       //   133: aload #6
/*     */       //   135: astore #4
/*     */       //   137: aload_0
/*     */       //   138: getfield scpe$1 : Lscala/runtime/ObjectRef;
/*     */       //   141: new scala/xml/NamespaceBinding
/*     */       //   144: dup
/*     */       //   145: aload #4
/*     */       //   147: aload_0
/*     */       //   148: aload #7
/*     */       //   150: invokespecial nullIfEmpty$1 : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   153: aload_0
/*     */       //   154: getfield scpe$1 : Lscala/runtime/ObjectRef;
/*     */       //   157: getfield elem : Ljava/lang/Object;
/*     */       //   160: checkcast scala/xml/NamespaceBinding
/*     */       //   163: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lscala/xml/NamespaceBinding;)V
/*     */       //   166: putfield elem : Ljava/lang/Object;
/*     */       //   169: goto -> 216
/*     */       //   172: aload_0
/*     */       //   173: getfield m$1 : Lscala/runtime/ObjectRef;
/*     */       //   176: getstatic scala/xml/Attribute$.MODULE$ : Lscala/xml/Attribute$;
/*     */       //   179: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */       //   182: aload #5
/*     */       //   184: invokevirtual apply : (Ljava/lang/Object;)Lscala/Option;
/*     */       //   187: aload #6
/*     */       //   189: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */       //   192: aload #7
/*     */       //   194: invokevirtual apply : (Ljava/lang/String;)Lscala/xml/Text;
/*     */       //   197: aload_0
/*     */       //   198: getfield m$1 : Lscala/runtime/ObjectRef;
/*     */       //   201: getfield elem : Ljava/lang/Object;
/*     */       //   204: checkcast scala/xml/MetaData
/*     */       //   207: invokevirtual apply : (Lscala/Option;Ljava/lang/String;Lscala/collection/Seq;Lscala/xml/MetaData;)Lscala/xml/Attribute;
/*     */       //   210: checkcast scala/xml/MetaData
/*     */       //   213: putfield elem : Ljava/lang/Object;
/*     */       //   216: return
/*     */       //   217: new scala/MatchError
/*     */       //   220: dup
/*     */       //   221: aload #8
/*     */       //   223: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   226: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #132	-> 0
/*     */       //   #133	-> 11
/*     */       //   #134	-> 23
/*     */       //   #137	-> 74
/*     */       //   #138	-> 124
/*     */       //   #139	-> 137
/*     */       //   #142	-> 172
/*     */       //   #131	-> 216
/*     */       //   #134	-> 217
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	227	0	this	Lscala/xml/parsing/FactoryAdapter$$anonfun$startElement$1;
/*     */       //   0	227	1	i	I
/*     */       //   11	216	3	qname	Ljava/lang/String;
/*     */       //   23	204	7	value	Ljava/lang/String;
/*     */       //   65	162	5	pre	Ljava/lang/String;
/*     */       //   74	153	6	key	Ljava/lang/String;
/*     */       //   137	32	4	arg	Ljava/lang/String;
/*     */     }
/*     */     
/*     */     private final String nullIfEmpty$1(String s) {
/*     */       if (s == null) {
/*     */         if ("" != null);
/*     */       } else if (s.equals("")) {
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void captureText() {
/* 153 */     (capture() && buffer().length() > 0) ? 
/* 154 */       hStack().push(createText(buffer().toString())) : BoxedUnit.UNIT;
/* 156 */     buffer().clear();
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String _localName, String qname) {
/* 166 */     captureText();
/* 167 */     MetaData metaData = (MetaData)attribStack().pop();
/* 170 */     FactoryAdapter$$anonfun$1 factoryAdapter$$anonfun$1 = new FactoryAdapter$$anonfun$1(this);
/* 170 */     Iterator$ iterator$ = Iterator$.MODULE$;
/* 170 */     List<Node> v = (new Object((Function0)factoryAdapter$$anonfun$1)).takeWhile((Function1)new FactoryAdapter$$anonfun$2(this)).toList().reverse();
/* 171 */     Tuple2<String, String> tuple2 = scala$xml$parsing$FactoryAdapter$$splitName(qname);
/* 171 */     if (tuple2 != null) {
/* 171 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 171 */       String pre = (String)tuple21._1(), localName = (String)tuple21._2();
/* 172 */       NamespaceBinding scp = (NamespaceBinding)scopeStack().pop();
/* 175 */       rootElem_$eq(createNode(pre, localName, metaData, scp, v));
/* 176 */       hStack().push(rootElem());
/* 177 */       curTag_$eq((String)tagStack().pop());
/* 178 */       capture_$eq((curTag() != null && nodeContainsText(curTag())));
/*     */       return;
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public class FactoryAdapter$$anonfun$1 extends AbstractFunction0<Node> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Node apply() {
/*     */       return (Node)this.$outer.hStack().pop();
/*     */     }
/*     */     
/*     */     public FactoryAdapter$$anonfun$1(FactoryAdapter $outer) {}
/*     */   }
/*     */   
/*     */   public class FactoryAdapter$$anonfun$2 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$3) {
/*     */       return !(x$3 == null);
/*     */     }
/*     */     
/*     */     public FactoryAdapter$$anonfun$2(FactoryAdapter $outer) {}
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) {
/* 184 */     hStack().pushAll((TraversableOnce)createProcInstr(target, data));
/*     */   }
/*     */   
/*     */   public abstract boolean nodeContainsText(String paramString);
/*     */   
/*     */   public abstract Node createNode(String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, List<Node> paramList);
/*     */   
/*     */   public abstract Text createText(String paramString);
/*     */   
/*     */   public abstract Seq<ProcInstr> createProcInstr(String paramString1, String paramString2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\FactoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */