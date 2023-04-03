/*    */ package scala.xml.pull;
/*    */ 
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.AbstractIterator;
/*    */ import scala.collection.BufferedIterator;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.io.Source;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.TraitSetter;
/*    */ import scala.xml.Document;
/*    */ import scala.xml.Elem;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.NodeBuffer;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.dtd.DTD;
/*    */ import scala.xml.dtd.ExternalID;
/*    */ import scala.xml.parsing.ExternalSources;
/*    */ import scala.xml.parsing.MarkupHandler;
/*    */ import scala.xml.parsing.MarkupParser;
/*    */ import scala.xml.parsing.MarkupParserCommon;
/*    */ import scala.xml.parsing.TokenTests;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t%a\001B\001\003\001%\021a\002W'M\013Z,g\016\036*fC\022,'O\003\002\004\t\005!\001/\0367m\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\r\001!\002\006\t\004\0279\001R\"\001\007\013\00551\021AC2pY2,7\r^5p]&\021q\002\004\002\021\003\n\034HO]1di&#XM]1u_J\004\"!\005\n\016\003\tI!a\005\002\003\021akE*\022<f]R\0042!E\013\021\023\t1\"A\001\rQe>$WoY3s\007>t7/^7fe&#XM]1u_JD\001\002\007\001\003\002\003\006I!G\001\004gJ\034\007C\001\016\036\033\005Y\"B\001\017\007\003\tIw.\003\002\0377\t11k\\;sG\026DQ\001\t\001\005\002\005\na\001P5oSRtDC\001\022$!\t\t\002\001C\003\031?\001\007\021\004C\004&\001\t\007I\021\001\024\002\025A\024Xm]3sm\026<6+F\001(!\tA\023&D\001\007\023\tQcAA\004C_>dW-\0318\t\r1\002\001\025!\003(\003-\001(/Z:feZ,wk\025\021\t\0179\002!\031!C!_\005aQ*\031=Rk\026,XmU5{KV\t\001\007\005\002)c%\021!G\002\002\004\023:$\bB\002\033\001A\003%\001'A\007NCb\fV/Z;f'&TX\rI\004\006m\001A\tjN\001\007!>K5k\024(\021\005aJT\"\001\001\007\013i\002\001\022S\036\003\rA{\025jU(O'\025ID\bE C!\tAS(\003\002?\r\t1\021I\\=SK\032\004\"\001\013!\n\005\0053!a\002)s_\022,8\r\036\t\003Q\rK!\001\022\004\003\031M+'/[1mSj\f'\r\\3\t\013\001JD\021\001$\025\003]Bq\001S\035\002\002\023\005\023*A\007qe>$Wo\031;Qe\0264\027\016_\013\002\025B\0211\nU\007\002\031*\021QJT\001\005Y\006twMC\001P\003\021Q\027M^1\n\005Ec%AB*ue&tw\rC\004Ts\005\005I\021A\030\002\031A\024x\016Z;di\006\023\030\016^=\t\017UK\024\021!C\001-\006q\001O]8ek\016$X\t\\3nK:$HCA,[!\tA\003,\003\002Z\r\t\031\021I\\=\t\017m#\026\021!a\001a\005\031\001\020J\031\t\017uK\024\021!C!=\006y\001O]8ek\016$\030\n^3sCR|'/F\001`!\rY\001mV\005\003C2\021\001\"\023;fe\006$xN\035\005\bGf\n\t\021\"\001e\003!\031\027M\\#rk\006dGCA\024f\021\035Y&-!AA\002]CqaZ\035\002\002\023\005\003.\001\005iCND7i\0343f)\005\001\004b\0026:\003\003%\te[\001\ti>\034FO]5oOR\t!\nC\004ns\005\005I\021\0028\002\027I,\027\r\032*fg>dg/\032\013\002_B\0211\n]\005\003c2\023aa\0242kK\016$\bbB:\001\005\004%\t\001^\001\f\013:$wJZ*ue\026\fW.F\001v\035\tAT\007\003\004x\001\001\006I!^\001\r\013:$wJZ*ue\026\fW\016\t\005\007s\002\001\013\021\002>\002\rA\f'o]3s!\tA4P\002\003}\001\021i(A\002)beN,'o\005\005|}\006%\021qBA\013!\ry\030QA\007\003\003\003Q1!a\001\005\003\035\001\030M]:j]\036LA!a\002\002\002\tiQ*\031:lkBD\025M\0343mKJ\0042a`A\006\023\021\ti!!\001\003\0315\013'o[;q!\006\0248/\032:\021\007}\f\t\"\003\003\002\024\005\005!aD#yi\026\024h.\0317T_V\0248-Z:\021\007-\0139\"C\002\002\0321\023\001BU;o]\006\024G.\032\005\013\003;Y(Q1A\005\002\005}\021!B5oaV$X#A\r\t\023\005\r2P!A!\002\023I\022AB5oaV$\b\005\003\004!w\022\005\021q\005\013\004u\006%\002bBA\017\003K\001\r!\007\005\bKm\024\r\021\"\001'\021\031a3\020)A\005O!A\021\021G>A\002\023%q&A\003mKZ,G\016C\005\0026m\004\r\021\"\003\0028\005IA.\032<fY~#S-\035\013\005\003s\ty\004E\002)\003wI1!!\020\007\005\021)f.\033;\t\021m\013\031$!AA\002ABq!a\021|A\003&\001'\001\004mKZ,G\016\t\005\b\003\017ZH\021AA%\003!\031X\r^#wK:$H\003BA&\003'\002B!!\024\002P5\tA!C\002\002R\021\021qAT8eKN+\027\017\003\005\002V\005\025\003\031AA,\003\t)7\017\005\003)\0033\002\022bAA.\r\tQAH]3qK\006$X\r\032 \t\017\005}3\020\"\021\002b\005IQ\r\\3n'R\f'\017\036\013\r\003s\t\031'a\032\002x\005m\024Q\021\005\b\003K\ni\0061\0011\003\r\001xn\035\005\t\003S\ni\0061\001\002l\005\031\001O]3\021\t\0055\0241\017\b\004Q\005=\024bAA9\r\0051\001K]3eK\032L1!UA;\025\r\t\tH\002\005\t\003s\ni\0061\001\002l\005)A.\0312fY\"A\021QPA/\001\004\ty(A\003biR\0248\017\005\003\002N\005\005\025bAAB\t\tAQ*\032;b\t\006$\030\r\003\005\002\b\006u\003\031AAE\003\025\0318m\0349f!\021\ti%a#\n\007\0055EA\001\tOC6,7\017]1dK\nKg\016Z5oO\"9\021\021S>\005B\005M\025aB3mK6,e\016\032\013\t\003s\t)*a&\002\032\"9\021QMAH\001\004\001\004\002CA5\003\037\003\r!a\033\t\021\005e\024q\022a\001\003WB\001\"!(|\001\004%IAJ\001\016S\036twN]3Xe&$H/\0328\t\023\005\0056\0201A\005\n\005\r\026!E5h]>\024Xm\026:jiR,gn\030\023fcR!\021\021HAS\021!Y\026qTA\001\002\0049\003bBAUw\002\006KaJ\001\017S\036twN]3Xe&$H/\0328!\021\035\tik\037C\003\003_\013A!\0327f[R\001\0221JAY\003g\013),a.\002:\006u\026\021\031\005\b\003K\nY\0131\0011\021!\tI'a+A\002\005-\004\002CA=\003W\003\r!a\033\t\021\005u\0241\026a\001\003B\001\"a/\002,\002\007\021\021R\001\007aN\034w\016]3\t\017\005}\0261\026a\001O\005)Q-\0349us\"A\0211YAV\001\004\tY%A\003o_\022,7\017C\004\002Hn$\t!!3\002\023A\024xnY%ogR\024H\003CA&\003\027\fi-!5\t\017\005\025\024Q\031a\001a!A\021qZAc\001\004\tY'\001\004uCJ<W\r\036\005\t\003'\f)\r1\001\002l\005\031A\017\037;\t\017\005]7\020\"\001\002Z\00691m\\7nK:$HCBA&\0037\fi\016C\004\002f\005U\007\031\001\031\t\021\005M\027Q\033a\001\003WBq!!9|\t\003\t\031/A\005f]RLG/\037*fMR1\0211JAs\003ODq!!\032\002`\002\007\001\007\003\005\002j\006}\007\031AA6\003\005q\007bBAww\022\005\021q^\001\005i\026DH\017\006\004\002L\005E\0301\037\005\b\003K\nY\0171\0011\021!\t\031.a;A\002\005-\004bBA|w\022\005\023\021`\001\004eVtGCAA\035\021!\ti\020\001Q\001\n\005}\030\001\0049beN,'\017\0265sK\006$\007cA&\003\002%\031!1\001'\003\rQC'/Z1e\021\035\0219\001\001C\001\003s\fAa\035;pa\002")
/*    */ public class XMLEventReader extends AbstractIterator<XMLEvent> implements ProducerConsumerIterator<XMLEvent> {
/*    */   private final boolean preserveWS;
/*    */   
/*    */   private final int MaxQueueSize;
/*    */   
/*    */   private final POISON$ EndOfStream;
/*    */   
/*    */   private final Parser parser;
/*    */   
/*    */   private final Thread parserThread;
/*    */   
/*    */   private volatile POISON$ POISON$module;
/*    */   
/*    */   private final LinkedBlockingQueue<Object> scala$xml$pull$ProducerConsumerIterator$$queue;
/*    */   
/*    */   private Object scala$xml$pull$ProducerConsumerIterator$$buffer;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   private LinkedBlockingQueue scala$xml$pull$ProducerConsumerIterator$$queue$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (!this.bitmap$0) {
/* 26 */         this.scala$xml$pull$ProducerConsumerIterator$$queue = ProducerConsumerIterator$class.scala$xml$pull$ProducerConsumerIterator$$queue(this);
/* 26 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/xml/pull/XMLEventReader}} */
/* 26 */       return this.scala$xml$pull$ProducerConsumerIterator$$queue;
/*    */     } 
/*    */   }
/*    */   
/*    */   public LinkedBlockingQueue<XMLEvent> scala$xml$pull$ProducerConsumerIterator$$queue() {
/* 26 */     return this.bitmap$0 ? (LinkedBlockingQueue)this.scala$xml$pull$ProducerConsumerIterator$$queue : scala$xml$pull$ProducerConsumerIterator$$queue$lzycompute();
/*    */   }
/*    */   
/*    */   public void scala$xml$pull$ProducerConsumerIterator$_setter_$MaxQueueSize_$eq(int x$1) {}
/*    */   
/*    */   public Object scala$xml$pull$ProducerConsumerIterator$$buffer() {
/* 26 */     return this.scala$xml$pull$ProducerConsumerIterator$$buffer;
/*    */   }
/*    */   
/*    */   public void scala$xml$pull$ProducerConsumerIterator$$buffer_$eq(Object x$1) {
/* 26 */     this.scala$xml$pull$ProducerConsumerIterator$$buffer = x$1;
/*    */   }
/*    */   
/*    */   public <T> Option<T> interruptibly(Function0 body) {
/* 26 */     return ProducerConsumerIterator$class.interruptibly(this, body);
/*    */   }
/*    */   
/*    */   public void produce(Object x) {
/* 26 */     ProducerConsumerIterator$class.produce(this, x);
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 26 */     return ProducerConsumerIterator$class.hasNext(this);
/*    */   }
/*    */   
/*    */   public Object next() {
/* 26 */     return ProducerConsumerIterator$class.next(this);
/*    */   }
/*    */   
/*    */   public boolean available() {
/* 26 */     return ProducerConsumerIterator$class.available(this);
/*    */   }
/*    */   
/*    */   public XMLEventReader(Source src) {
/* 26 */     ProducerConsumerIterator$class.$init$(this);
/* 36 */     this.preserveWS = true;
/* 38 */     this.MaxQueueSize = 1000;
/* 40 */     this.EndOfStream = POISON();
/* 43 */     this.parser = new Parser(this, src);
/* 44 */     this.parserThread = new Thread(this.parser, "XMLEventReader");
/* 45 */     this.parserThread.start();
/*    */   }
/*    */   
/*    */   public boolean preserveWS() {
/*    */     return this.preserveWS;
/*    */   }
/*    */   
/*    */   public int MaxQueueSize() {
/*    */     return this.MaxQueueSize;
/*    */   }
/*    */   
/*    */   private POISON$ POISON$lzycompute() {
/*    */     synchronized (this) {
/*    */       if (this.POISON$module == null)
/*    */         this.POISON$module = new POISON$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/xml/pull/XMLEventReader}} */
/*    */       return this.POISON$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public POISON$ POISON() {
/*    */     return (this.POISON$module == null) ? POISON$lzycompute() : this.POISON$module;
/*    */   }
/*    */   
/*    */   public class POISON$ implements XMLEvent, Product, Serializable {
/*    */     public String productPrefix() {
/*    */       return "POISON";
/*    */     }
/*    */     
/*    */     public int productArity() {
/*    */       return 0;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/*    */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/*    */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/*    */       return x$1 instanceof POISON$;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/*    */       return -1929420024;
/*    */     }
/*    */     
/*    */     public String toString() {
/*    */       return "POISON";
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/*    */       return this.$outer.POISON();
/*    */     }
/*    */     
/*    */     public POISON$(XMLEventReader $outer) {
/*    */       Product.class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public POISON$ EndOfStream() {
/*    */     return this.EndOfStream;
/*    */   }
/*    */   
/*    */   public void stop() {
/* 55 */     produce(POISON());
/* 56 */     this.parserThread.interrupt();
/*    */   }
/*    */   
/*    */   public class Parser extends MarkupHandler implements MarkupParser, ExternalSources, Runnable {
/*    */     private final Source input;
/*    */     
/*    */     private final boolean preserveWS;
/*    */     
/*    */     private int level;
/*    */     
/*    */     private boolean ignoreWritten;
/*    */     
/*    */     private Source curInput;
/*    */     
/*    */     private final MarkupHandler scala$xml$parsing$MarkupParser$$handle;
/*    */     
/*    */     private List<Source> inpStack;
/*    */     
/*    */     private int pos;
/*    */     
/*    */     private int extIndex;
/*    */     
/*    */     private int tmppos;
/*    */     
/*    */     private boolean nextChNeeded;
/*    */     
/*    */     private boolean reachedEof;
/*    */     
/*    */     private char lastChRead;
/*    */     
/*    */     private final StringBuilder cbuf;
/*    */     
/*    */     private DTD dtd;
/*    */     
/*    */     private Document doc;
/*    */     
/*    */     public Source externalSource(String systemId) {
/* 59 */       return ExternalSources.class.externalSource(this, systemId);
/*    */     }
/*    */     
/*    */     public Source curInput() {
/* 59 */       return this.curInput;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void curInput_$eq(Source x$1) {
/* 59 */       this.curInput = x$1;
/*    */     }
/*    */     
/*    */     public MarkupHandler scala$xml$parsing$MarkupParser$$handle() {
/* 59 */       return this.scala$xml$parsing$MarkupParser$$handle;
/*    */     }
/*    */     
/*    */     public List<Source> inpStack() {
/* 59 */       return this.inpStack;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void inpStack_$eq(List<Source> x$1) {
/* 59 */       this.inpStack = x$1;
/*    */     }
/*    */     
/*    */     public int pos() {
/* 59 */       return this.pos;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void pos_$eq(int x$1) {
/* 59 */       this.pos = x$1;
/*    */     }
/*    */     
/*    */     public int extIndex() {
/* 59 */       return this.extIndex;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void extIndex_$eq(int x$1) {
/* 59 */       this.extIndex = x$1;
/*    */     }
/*    */     
/*    */     public int tmppos() {
/* 59 */       return this.tmppos;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void tmppos_$eq(int x$1) {
/* 59 */       this.tmppos = x$1;
/*    */     }
/*    */     
/*    */     public boolean nextChNeeded() {
/* 59 */       return this.nextChNeeded;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void nextChNeeded_$eq(boolean x$1) {
/* 59 */       this.nextChNeeded = x$1;
/*    */     }
/*    */     
/*    */     public boolean reachedEof() {
/* 59 */       return this.reachedEof;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void reachedEof_$eq(boolean x$1) {
/* 59 */       this.reachedEof = x$1;
/*    */     }
/*    */     
/*    */     public char lastChRead() {
/* 59 */       return this.lastChRead;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void lastChRead_$eq(char x$1) {
/* 59 */       this.lastChRead = x$1;
/*    */     }
/*    */     
/*    */     public StringBuilder cbuf() {
/* 59 */       return this.cbuf;
/*    */     }
/*    */     
/*    */     public DTD dtd() {
/* 59 */       return this.dtd;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void dtd_$eq(DTD x$1) {
/* 59 */       this.dtd = x$1;
/*    */     }
/*    */     
/*    */     public Document doc() {
/* 59 */       return this.doc;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void doc_$eq(Document x$1) {
/* 59 */       this.doc = x$1;
/*    */     }
/*    */     
/*    */     public void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(MarkupHandler x$1) {
/* 59 */       this.scala$xml$parsing$MarkupParser$$handle = x$1;
/*    */     }
/*    */     
/*    */     public void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(StringBuilder x$1) {
/* 59 */       this.cbuf = x$1;
/*    */     }
/*    */     
/*    */     public scala.runtime.Nothing$ truncatedError(String msg) {
/* 59 */       return MarkupParser.class.truncatedError(this, msg);
/*    */     }
/*    */     
/*    */     public scala.runtime.Nothing$ errorNoEnd(String tag) {
/* 59 */       return MarkupParser.class.errorNoEnd(this, tag);
/*    */     }
/*    */     
/*    */     public void xHandleError(char that, String msg) {
/* 59 */       MarkupParser.class.xHandleError(this, that, msg);
/*    */     }
/*    */     
/*    */     public BufferedIterator<Object> lookahead() {
/* 59 */       return MarkupParser.class.lookahead(this);
/*    */     }
/*    */     
/*    */     public char ch() {
/* 59 */       return MarkupParser.class.ch(this);
/*    */     }
/*    */     
/*    */     public boolean eof() {
/* 59 */       return MarkupParser.class.eof(this);
/*    */     }
/*    */     
/*    */     public MetaData xmlProcInstr() {
/* 59 */       return MarkupParser.class.xmlProcInstr(this);
/*    */     }
/*    */     
/*    */     public Tuple3<Option<String>, Option<String>, Option<Object>> prolog() {
/* 59 */       return MarkupParser.class.prolog(this);
/*    */     }
/*    */     
/*    */     public Tuple2<Option<String>, Option<String>> textDecl() {
/* 59 */       return MarkupParser.class.textDecl(this);
/*    */     }
/*    */     
/*    */     public Document document() {
/* 59 */       return MarkupParser.class.document(this);
/*    */     }
/*    */     
/*    */     public StringBuilder putChar(char c) {
/* 59 */       return MarkupParser.class.putChar(this, c);
/*    */     }
/*    */     
/*    */     public MarkupHandler initialize() {
/* 59 */       return MarkupParser.class.initialize(this);
/*    */     }
/*    */     
/*    */     public char ch_returning_nextch() {
/* 59 */       return MarkupParser.class.ch_returning_nextch(this);
/*    */     }
/*    */     
/*    */     public Tuple2<MetaData, NamespaceBinding> mkAttributes(String name, NamespaceBinding pscope) {
/* 59 */       return MarkupParser.class.mkAttributes(this, name, pscope);
/*    */     }
/*    */     
/*    */     public NodeSeq mkProcInstr(int position, String name, String text) {
/* 59 */       return MarkupParser.class.mkProcInstr(this, position, name, text);
/*    */     }
/*    */     
/*    */     public void nextch() {
/* 59 */       MarkupParser.class.nextch(this);
/*    */     }
/*    */     
/*    */     public Tuple2<MetaData, NamespaceBinding> xAttributes(NamespaceBinding pscope) {
/* 59 */       return MarkupParser.class.xAttributes(this, pscope);
/*    */     }
/*    */     
/*    */     public String xEntityValue() {
/* 59 */       return MarkupParser.class.xEntityValue(this);
/*    */     }
/*    */     
/*    */     public NodeSeq xCharData() {
/* 59 */       return MarkupParser.class.xCharData(this);
/*    */     }
/*    */     
/*    */     public NodeSeq xComment() {
/* 59 */       return MarkupParser.class.xComment(this);
/*    */     }
/*    */     
/*    */     public void appendText(int pos, NodeBuffer ts, String txt) {
/* 59 */       MarkupParser.class.appendText(this, pos, ts, txt);
/*    */     }
/*    */     
/*    */     public void content1(NamespaceBinding pscope, NodeBuffer ts) {
/* 59 */       MarkupParser.class.content1(this, pscope, ts);
/*    */     }
/*    */     
/*    */     public NodeSeq content(NamespaceBinding pscope) {
/* 59 */       return MarkupParser.class.content(this, pscope);
/*    */     }
/*    */     
/*    */     public ExternalID externalID() {
/* 59 */       return MarkupParser.class.externalID(this);
/*    */     }
/*    */     
/*    */     public void parseDTD() {
/* 59 */       MarkupParser.class.parseDTD(this);
/*    */     }
/*    */     
/*    */     public NodeSeq element(NamespaceBinding pscope) {
/* 59 */       return MarkupParser.class.element(this, pscope);
/*    */     }
/*    */     
/*    */     public NodeSeq element1(NamespaceBinding pscope) {
/* 59 */       return MarkupParser.class.element1(this, pscope);
/*    */     }
/*    */     
/*    */     public String systemLiteral() {
/* 59 */       return MarkupParser.class.systemLiteral(this);
/*    */     }
/*    */     
/*    */     public String pubidLiteral() {
/* 59 */       return MarkupParser.class.pubidLiteral(this);
/*    */     }
/*    */     
/*    */     public void extSubset() {
/* 59 */       MarkupParser.class.extSubset(this);
/*    */     }
/*    */     
/*    */     public Object markupDecl1() {
/* 59 */       return MarkupParser.class.markupDecl1(this);
/*    */     }
/*    */     
/*    */     public void markupDecl() {
/* 59 */       MarkupParser.class.markupDecl(this);
/*    */     }
/*    */     
/*    */     public void intSubset() {
/* 59 */       MarkupParser.class.intSubset(this);
/*    */     }
/*    */     
/*    */     public void elementDecl() {
/* 59 */       MarkupParser.class.elementDecl(this);
/*    */     }
/*    */     
/*    */     public void attrDecl() {
/* 59 */       MarkupParser.class.attrDecl(this);
/*    */     }
/*    */     
/*    */     public void entityDecl() {
/* 59 */       MarkupParser.class.entityDecl(this);
/*    */     }
/*    */     
/*    */     public void notationDecl() {
/* 59 */       MarkupParser.class.notationDecl(this);
/*    */     }
/*    */     
/*    */     public void reportSyntaxError(int pos, String str) {
/* 59 */       MarkupParser.class.reportSyntaxError(this, pos, str);
/*    */     }
/*    */     
/*    */     public void reportSyntaxError(String str) {
/* 59 */       MarkupParser.class.reportSyntaxError(this, str);
/*    */     }
/*    */     
/*    */     public void reportValidationError(int pos, String str) {
/* 59 */       MarkupParser.class.reportValidationError(this, pos, str);
/*    */     }
/*    */     
/*    */     public void push(String entityName) {
/* 59 */       MarkupParser.class.push(this, entityName);
/*    */     }
/*    */     
/*    */     public void pushExternal(String systemId) {
/* 59 */       MarkupParser.class.pushExternal(this, systemId);
/*    */     }
/*    */     
/*    */     public void pop() {
/* 59 */       MarkupParser.class.pop(this);
/*    */     }
/*    */     
/*    */     public scala.runtime.Nothing$ unreachable() {
/* 59 */       return MarkupParserCommon.class.unreachable((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public Tuple2<String, Object> xTag(Object pscope) {
/* 59 */       return MarkupParserCommon.class.xTag((MarkupParserCommon)this, pscope);
/*    */     }
/*    */     
/*    */     public Object xProcInstr() {
/* 59 */       return MarkupParserCommon.class.xProcInstr((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public String xAttributeValue(char endCh) {
/* 59 */       return MarkupParserCommon.class.xAttributeValue((MarkupParserCommon)this, endCh);
/*    */     }
/*    */     
/*    */     public String xAttributeValue() {
/* 59 */       return MarkupParserCommon.class.xAttributeValue((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public void xEndTag(String startName) {
/* 59 */       MarkupParserCommon.class.xEndTag((MarkupParserCommon)this, startName);
/*    */     }
/*    */     
/*    */     public String xName() {
/* 59 */       return MarkupParserCommon.class.xName((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public String xCharRef(Function0 ch, Function0 nextch) {
/* 59 */       return MarkupParserCommon.class.xCharRef((MarkupParserCommon)this, ch, nextch);
/*    */     }
/*    */     
/*    */     public String xCharRef(Iterator it) {
/* 59 */       return MarkupParserCommon.class.xCharRef((MarkupParserCommon)this, it);
/*    */     }
/*    */     
/*    */     public String xCharRef() {
/* 59 */       return MarkupParserCommon.class.xCharRef((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public <T> T errorAndResult(String msg, Object x) {
/* 59 */       return (T)MarkupParserCommon.class.errorAndResult((MarkupParserCommon)this, msg, x);
/*    */     }
/*    */     
/*    */     public void xToken(char that) {
/* 59 */       MarkupParserCommon.class.xToken((MarkupParserCommon)this, that);
/*    */     }
/*    */     
/*    */     public void xToken(Seq that) {
/* 59 */       MarkupParserCommon.class.xToken((MarkupParserCommon)this, that);
/*    */     }
/*    */     
/*    */     public void xEQ() {
/* 59 */       MarkupParserCommon.class.xEQ((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public void xSpaceOpt() {
/* 59 */       MarkupParserCommon.class.xSpaceOpt((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public void xSpace() {
/* 59 */       MarkupParserCommon.class.xSpace((MarkupParserCommon)this);
/*    */     }
/*    */     
/*    */     public <T> T returning(Object x, Function1 f) {
/* 59 */       return (T)MarkupParserCommon.class.returning((MarkupParserCommon)this, x, f);
/*    */     }
/*    */     
/*    */     public <A, B> B saving(Object getter, Function1 setter, Function0 body) {
/* 59 */       return (B)MarkupParserCommon.class.saving((MarkupParserCommon)this, getter, setter, body);
/*    */     }
/*    */     
/*    */     public <T> T xTakeUntil(Function2 handler, Function0 positioner, String until) {
/* 59 */       return (T)MarkupParserCommon.class.xTakeUntil((MarkupParserCommon)this, handler, positioner, until);
/*    */     }
/*    */     
/*    */     public final boolean isSpace(char ch) {
/* 59 */       return TokenTests.class.isSpace((TokenTests)this, ch);
/*    */     }
/*    */     
/*    */     public final boolean isSpace(Seq cs) {
/* 59 */       return TokenTests.class.isSpace((TokenTests)this, cs);
/*    */     }
/*    */     
/*    */     public boolean isAlpha(char c) {
/* 59 */       return TokenTests.class.isAlpha((TokenTests)this, c);
/*    */     }
/*    */     
/*    */     public boolean isAlphaDigit(char c) {
/* 59 */       return TokenTests.class.isAlphaDigit((TokenTests)this, c);
/*    */     }
/*    */     
/*    */     public boolean isNameChar(char ch) {
/* 59 */       return TokenTests.class.isNameChar((TokenTests)this, ch);
/*    */     }
/*    */     
/*    */     public boolean isNameStart(char ch) {
/* 59 */       return TokenTests.class.isNameStart((TokenTests)this, ch);
/*    */     }
/*    */     
/*    */     public boolean isName(String s) {
/* 59 */       return TokenTests.class.isName((TokenTests)this, s);
/*    */     }
/*    */     
/*    */     public boolean isPubIDChar(char ch) {
/* 59 */       return TokenTests.class.isPubIDChar((TokenTests)this, ch);
/*    */     }
/*    */     
/*    */     public boolean isValidIANAEncoding(Seq ianaEncoding) {
/* 59 */       return TokenTests.class.isValidIANAEncoding((TokenTests)this, ianaEncoding);
/*    */     }
/*    */     
/*    */     public boolean checkSysID(String s) {
/* 59 */       return TokenTests.class.checkSysID((TokenTests)this, s);
/*    */     }
/*    */     
/*    */     public boolean checkPubID(String s) {
/* 59 */       return TokenTests.class.checkPubID((TokenTests)this, s);
/*    */     }
/*    */     
/*    */     public Source input() {
/* 59 */       return this.input;
/*    */     }
/*    */     
/*    */     public Parser(XMLEventReader $outer, Source input) {
/* 59 */       TokenTests.class.$init$((TokenTests)this);
/* 59 */       MarkupParserCommon.class.$init$((MarkupParserCommon)this);
/* 59 */       MarkupParser.class.$init$(this);
/* 59 */       ExternalSources.class.$init$(this);
/* 60 */       this.preserveWS = $outer.preserveWS();
/* 62 */       this.level = 0;
/* 83 */       this.ignoreWritten = false;
/*    */     }
/*    */     
/*    */     public boolean preserveWS() {
/*    */       return this.preserveWS;
/*    */     }
/*    */     
/*    */     private int level() {
/*    */       return this.level;
/*    */     }
/*    */     
/*    */     private void level_$eq(int x$1) {
/*    */       this.level = x$1;
/*    */     }
/*    */     
/*    */     public NodeSeq setEvent(Seq es) {
/*    */       es.foreach((Function1)new XMLEventReader$Parser$$anonfun$setEvent$1(this));
/*    */       return scala.xml.NodeSeq$.MODULE$.Empty();
/*    */     }
/*    */     
/*    */     public class XMLEventReader$Parser$$anonfun$setEvent$1 extends AbstractFunction1<XMLEvent, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final void apply(XMLEvent x) {
/*    */         this.$outer.scala$xml$pull$XMLEventReader$Parser$$$outer().produce(x);
/*    */       }
/*    */       
/*    */       public XMLEventReader$Parser$$anonfun$setEvent$1(XMLEventReader.Parser $outer) {}
/*    */     }
/*    */     
/*    */     public void elemStart(int pos, String pre, String label, MetaData attrs, NamespaceBinding scope) {
/*    */       level_$eq(level() + 1);
/*    */       (new XMLEvent[1])[0] = new EvElemStart(pre, label, attrs, scope);
/*    */       setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public void elemEnd(int pos, String pre, String label) {
/*    */       (new XMLEvent[1])[0] = new EvElemEnd(pre, label);
/*    */       setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */       level_$eq(level() - 1);
/*    */     }
/*    */     
/*    */     private boolean ignoreWritten() {
/* 83 */       return this.ignoreWritten;
/*    */     }
/*    */     
/*    */     private void ignoreWritten_$eq(boolean x$1) {
/* 83 */       this.ignoreWritten = x$1;
/*    */     }
/*    */     
/*    */     public final NodeSeq elem(int pos, String pre, String label, MetaData attrs, NamespaceBinding pscope, boolean empty, NodeSeq nodes) {
/* 85 */       ignoreWritten_$eq(true);
/* 85 */       return (level() == 1 && !ignoreWritten()) ? (NodeSeq)new Elem(null, "ignore", (MetaData)scala.xml.Null$.MODULE$, (NamespaceBinding)scala.Predef$.MODULE$.$scope(), true, (Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new scala.xml.Node[0])) : scala.xml.NodeSeq$.MODULE$.Empty();
/*    */     }
/*    */     
/*    */     public NodeSeq procInstr(int pos, String target, String txt) {
/* 87 */       (new XMLEvent[1])[0] = new EvProcInstr(target, txt);
/* 87 */       return setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public NodeSeq comment(int pos, String txt) {
/* 88 */       (new XMLEvent[1])[0] = new EvComment(txt);
/* 88 */       return setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public NodeSeq entityRef(int pos, String n) {
/* 89 */       (new XMLEvent[1])[0] = new EvEntityRef(n);
/* 89 */       return setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public NodeSeq text(int pos, String txt) {
/* 90 */       (new XMLEvent[1])[0] = new EvText(txt);
/* 90 */       return setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public void run() {
/* 93 */       curInput_$eq(input());
/* 94 */       scala$xml$pull$XMLEventReader$Parser$$$outer().interruptibly((Function0<?>)new XMLEventReader$Parser$$anonfun$run$1(this));
/* 95 */       (new XMLEvent[1])[0] = scala$xml$pull$XMLEventReader$Parser$$$outer().POISON();
/* 95 */       setEvent((Seq<XMLEvent>)scala.Predef$.MODULE$.wrapRefArray((Object[])new XMLEvent[1]));
/*    */     }
/*    */     
/*    */     public class XMLEventReader$Parser$$anonfun$run$1 extends AbstractFunction0<Document> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Document apply() {
/*    */         return ((MarkupParser)this.$outer.initialize()).document();
/*    */       }
/*    */       
/*    */       public XMLEventReader$Parser$$anonfun$run$1(XMLEventReader.Parser $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\XMLEventReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */