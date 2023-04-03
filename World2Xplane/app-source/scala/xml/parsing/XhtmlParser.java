/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.BufferedIterator;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.io.Source;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.TraitSetter;
/*    */ import scala.xml.Document;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.NodeBuffer;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.dtd.DTD;
/*    */ import scala.xml.dtd.ExternalID;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0013A!\001\002\001\023\tY\001\f\033;nYB\013'o]3s\025\t\031A!A\004qCJ\034\030N\\4\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\t\001Qa\"\005\t\003\0271i\021AA\005\003\033\t\0211cQ8ogR\024Xo\031;j]\036D\025M\0343mKJ\004\"aC\b\n\005A\021!\001D'be.,\b\017U1sg\026\024\bCA\006\023\023\t\031\"AA\bFqR,'O\\1m'>,(oY3t\021!)\002A!b\001\n\0031\022!B5oaV$X#A\f\021\005aYR\"A\r\013\005i1\021AA5p\023\ta\022D\001\004T_V\0248-\032\005\t=\001\021\t\021)A\005/\0051\021N\0349vi\002BQ\001\t\001\005\002\005\na\001P5oSRtDC\001\022$!\tY\001\001C\003\026?\001\007q\003C\004&\001\t\007I\021\001\024\002\025A\024Xm]3sm\026<6+F\001(!\tA\023&D\001\007\023\tQcAA\004C_>dW-\0318\t\r1\002\001\025!\003(\003-\001(/Z:feZ,wk\025\021\b\0139\022\001\022A\030\002\027aCG/\0347QCJ\034XM\035\t\003\027A2Q!\001\002\t\002E\032\"\001\r\032\021\005!\032\024B\001\033\007\005\031\te.\037*fM\")\001\005\rC\001mQ\tq\006C\0039a\021\005\021(A\003baBd\027\020\006\002;}A\0211\bP\007\002\t%\021Q\b\002\002\b\035>$WmU3r\021\025yt\0071\001\030\003\031\031x.\036:dK\002")
/*    */ public class XhtmlParser extends ConstructingHandler implements MarkupParser, ExternalSources {
/*    */   private final Source input;
/*    */   
/*    */   private final boolean preserveWS;
/*    */   
/*    */   private Source curInput;
/*    */   
/*    */   private final MarkupHandler scala$xml$parsing$MarkupParser$$handle;
/*    */   
/*    */   private List<Source> inpStack;
/*    */   
/*    */   private int pos;
/*    */   
/*    */   private int extIndex;
/*    */   
/*    */   private int tmppos;
/*    */   
/*    */   private boolean nextChNeeded;
/*    */   
/*    */   private boolean reachedEof;
/*    */   
/*    */   private char lastChRead;
/*    */   
/*    */   private final StringBuilder cbuf;
/*    */   
/*    */   private DTD dtd;
/*    */   
/*    */   private Document doc;
/*    */   
/*    */   public Source externalSource(String systemId) {
/* 19 */     return ExternalSources$class.externalSource(this, systemId);
/*    */   }
/*    */   
/*    */   public Source curInput() {
/* 19 */     return this.curInput;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void curInput_$eq(Source x$1) {
/* 19 */     this.curInput = x$1;
/*    */   }
/*    */   
/*    */   public MarkupHandler scala$xml$parsing$MarkupParser$$handle() {
/* 19 */     return this.scala$xml$parsing$MarkupParser$$handle;
/*    */   }
/*    */   
/*    */   public List<Source> inpStack() {
/* 19 */     return this.inpStack;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void inpStack_$eq(List<Source> x$1) {
/* 19 */     this.inpStack = x$1;
/*    */   }
/*    */   
/*    */   public int pos() {
/* 19 */     return this.pos;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void pos_$eq(int x$1) {
/* 19 */     this.pos = x$1;
/*    */   }
/*    */   
/*    */   public int extIndex() {
/* 19 */     return this.extIndex;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void extIndex_$eq(int x$1) {
/* 19 */     this.extIndex = x$1;
/*    */   }
/*    */   
/*    */   public int tmppos() {
/* 19 */     return this.tmppos;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void tmppos_$eq(int x$1) {
/* 19 */     this.tmppos = x$1;
/*    */   }
/*    */   
/*    */   public boolean nextChNeeded() {
/* 19 */     return this.nextChNeeded;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void nextChNeeded_$eq(boolean x$1) {
/* 19 */     this.nextChNeeded = x$1;
/*    */   }
/*    */   
/*    */   public boolean reachedEof() {
/* 19 */     return this.reachedEof;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void reachedEof_$eq(boolean x$1) {
/* 19 */     this.reachedEof = x$1;
/*    */   }
/*    */   
/*    */   public char lastChRead() {
/* 19 */     return this.lastChRead;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void lastChRead_$eq(char x$1) {
/* 19 */     this.lastChRead = x$1;
/*    */   }
/*    */   
/*    */   public StringBuilder cbuf() {
/* 19 */     return this.cbuf;
/*    */   }
/*    */   
/*    */   public DTD dtd() {
/* 19 */     return this.dtd;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void dtd_$eq(DTD x$1) {
/* 19 */     this.dtd = x$1;
/*    */   }
/*    */   
/*    */   public Document doc() {
/* 19 */     return this.doc;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void doc_$eq(Document x$1) {
/* 19 */     this.doc = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(MarkupHandler x$1) {
/* 19 */     this.scala$xml$parsing$MarkupParser$$handle = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(StringBuilder x$1) {
/* 19 */     this.cbuf = x$1;
/*    */   }
/*    */   
/*    */   public Nothing$ truncatedError(String msg) {
/* 19 */     return MarkupParser$class.truncatedError(this, msg);
/*    */   }
/*    */   
/*    */   public Nothing$ errorNoEnd(String tag) {
/* 19 */     return MarkupParser$class.errorNoEnd(this, tag);
/*    */   }
/*    */   
/*    */   public void xHandleError(char that, String msg) {
/* 19 */     MarkupParser$class.xHandleError(this, that, msg);
/*    */   }
/*    */   
/*    */   public BufferedIterator<Object> lookahead() {
/* 19 */     return MarkupParser$class.lookahead(this);
/*    */   }
/*    */   
/*    */   public char ch() {
/* 19 */     return MarkupParser$class.ch(this);
/*    */   }
/*    */   
/*    */   public boolean eof() {
/* 19 */     return MarkupParser$class.eof(this);
/*    */   }
/*    */   
/*    */   public MetaData xmlProcInstr() {
/* 19 */     return MarkupParser$class.xmlProcInstr(this);
/*    */   }
/*    */   
/*    */   public Tuple3<Option<String>, Option<String>, Option<Object>> prolog() {
/* 19 */     return MarkupParser$class.prolog(this);
/*    */   }
/*    */   
/*    */   public Tuple2<Option<String>, Option<String>> textDecl() {
/* 19 */     return MarkupParser$class.textDecl(this);
/*    */   }
/*    */   
/*    */   public Document document() {
/* 19 */     return MarkupParser$class.document(this);
/*    */   }
/*    */   
/*    */   public StringBuilder putChar(char c) {
/* 19 */     return MarkupParser$class.putChar(this, c);
/*    */   }
/*    */   
/*    */   public MarkupHandler initialize() {
/* 19 */     return MarkupParser$class.initialize(this);
/*    */   }
/*    */   
/*    */   public char ch_returning_nextch() {
/* 19 */     return MarkupParser$class.ch_returning_nextch(this);
/*    */   }
/*    */   
/*    */   public Tuple2<MetaData, NamespaceBinding> mkAttributes(String name, NamespaceBinding pscope) {
/* 19 */     return MarkupParser$class.mkAttributes(this, name, pscope);
/*    */   }
/*    */   
/*    */   public NodeSeq mkProcInstr(int position, String name, String text) {
/* 19 */     return MarkupParser$class.mkProcInstr(this, position, name, text);
/*    */   }
/*    */   
/*    */   public void nextch() {
/* 19 */     MarkupParser$class.nextch(this);
/*    */   }
/*    */   
/*    */   public Tuple2<MetaData, NamespaceBinding> xAttributes(NamespaceBinding pscope) {
/* 19 */     return MarkupParser$class.xAttributes(this, pscope);
/*    */   }
/*    */   
/*    */   public String xEntityValue() {
/* 19 */     return MarkupParser$class.xEntityValue(this);
/*    */   }
/*    */   
/*    */   public NodeSeq xCharData() {
/* 19 */     return MarkupParser$class.xCharData(this);
/*    */   }
/*    */   
/*    */   public NodeSeq xComment() {
/* 19 */     return MarkupParser$class.xComment(this);
/*    */   }
/*    */   
/*    */   public void appendText(int pos, NodeBuffer ts, String txt) {
/* 19 */     MarkupParser$class.appendText(this, pos, ts, txt);
/*    */   }
/*    */   
/*    */   public void content1(NamespaceBinding pscope, NodeBuffer ts) {
/* 19 */     MarkupParser$class.content1(this, pscope, ts);
/*    */   }
/*    */   
/*    */   public NodeSeq content(NamespaceBinding pscope) {
/* 19 */     return MarkupParser$class.content(this, pscope);
/*    */   }
/*    */   
/*    */   public ExternalID externalID() {
/* 19 */     return MarkupParser$class.externalID(this);
/*    */   }
/*    */   
/*    */   public void parseDTD() {
/* 19 */     MarkupParser$class.parseDTD(this);
/*    */   }
/*    */   
/*    */   public NodeSeq element(NamespaceBinding pscope) {
/* 19 */     return MarkupParser$class.element(this, pscope);
/*    */   }
/*    */   
/*    */   public NodeSeq element1(NamespaceBinding pscope) {
/* 19 */     return MarkupParser$class.element1(this, pscope);
/*    */   }
/*    */   
/*    */   public String systemLiteral() {
/* 19 */     return MarkupParser$class.systemLiteral(this);
/*    */   }
/*    */   
/*    */   public String pubidLiteral() {
/* 19 */     return MarkupParser$class.pubidLiteral(this);
/*    */   }
/*    */   
/*    */   public void extSubset() {
/* 19 */     MarkupParser$class.extSubset(this);
/*    */   }
/*    */   
/*    */   public Object markupDecl1() {
/* 19 */     return MarkupParser$class.markupDecl1(this);
/*    */   }
/*    */   
/*    */   public void markupDecl() {
/* 19 */     MarkupParser$class.markupDecl(this);
/*    */   }
/*    */   
/*    */   public void intSubset() {
/* 19 */     MarkupParser$class.intSubset(this);
/*    */   }
/*    */   
/*    */   public void elementDecl() {
/* 19 */     MarkupParser$class.elementDecl(this);
/*    */   }
/*    */   
/*    */   public void attrDecl() {
/* 19 */     MarkupParser$class.attrDecl(this);
/*    */   }
/*    */   
/*    */   public void entityDecl() {
/* 19 */     MarkupParser$class.entityDecl(this);
/*    */   }
/*    */   
/*    */   public void notationDecl() {
/* 19 */     MarkupParser$class.notationDecl(this);
/*    */   }
/*    */   
/*    */   public void reportSyntaxError(int pos, String str) {
/* 19 */     MarkupParser$class.reportSyntaxError(this, pos, str);
/*    */   }
/*    */   
/*    */   public void reportSyntaxError(String str) {
/* 19 */     MarkupParser$class.reportSyntaxError(this, str);
/*    */   }
/*    */   
/*    */   public void reportValidationError(int pos, String str) {
/* 19 */     MarkupParser$class.reportValidationError(this, pos, str);
/*    */   }
/*    */   
/*    */   public void push(String entityName) {
/* 19 */     MarkupParser$class.push(this, entityName);
/*    */   }
/*    */   
/*    */   public void pushExternal(String systemId) {
/* 19 */     MarkupParser$class.pushExternal(this, systemId);
/*    */   }
/*    */   
/*    */   public void pop() {
/* 19 */     MarkupParser$class.pop(this);
/*    */   }
/*    */   
/*    */   public Nothing$ unreachable() {
/* 19 */     return MarkupParserCommon$class.unreachable(this);
/*    */   }
/*    */   
/*    */   public Tuple2<String, Object> xTag(Object pscope) {
/* 19 */     return MarkupParserCommon$class.xTag(this, pscope);
/*    */   }
/*    */   
/*    */   public Object xProcInstr() {
/* 19 */     return MarkupParserCommon$class.xProcInstr(this);
/*    */   }
/*    */   
/*    */   public String xAttributeValue(char endCh) {
/* 19 */     return MarkupParserCommon$class.xAttributeValue(this, endCh);
/*    */   }
/*    */   
/*    */   public String xAttributeValue() {
/* 19 */     return MarkupParserCommon$class.xAttributeValue(this);
/*    */   }
/*    */   
/*    */   public void xEndTag(String startName) {
/* 19 */     MarkupParserCommon$class.xEndTag(this, startName);
/*    */   }
/*    */   
/*    */   public String xName() {
/* 19 */     return MarkupParserCommon$class.xName(this);
/*    */   }
/*    */   
/*    */   public String xCharRef(Function0 ch, Function0 nextch) {
/* 19 */     return MarkupParserCommon$class.xCharRef(this, ch, nextch);
/*    */   }
/*    */   
/*    */   public String xCharRef(Iterator it) {
/* 19 */     return MarkupParserCommon$class.xCharRef(this, it);
/*    */   }
/*    */   
/*    */   public String xCharRef() {
/* 19 */     return MarkupParserCommon$class.xCharRef(this);
/*    */   }
/*    */   
/*    */   public <T> T errorAndResult(String msg, Object x) {
/* 19 */     return (T)MarkupParserCommon$class.errorAndResult(this, msg, x);
/*    */   }
/*    */   
/*    */   public void xToken(char that) {
/* 19 */     MarkupParserCommon$class.xToken(this, that);
/*    */   }
/*    */   
/*    */   public void xToken(Seq that) {
/* 19 */     MarkupParserCommon$class.xToken(this, that);
/*    */   }
/*    */   
/*    */   public void xEQ() {
/* 19 */     MarkupParserCommon$class.xEQ(this);
/*    */   }
/*    */   
/*    */   public void xSpaceOpt() {
/* 19 */     MarkupParserCommon$class.xSpaceOpt(this);
/*    */   }
/*    */   
/*    */   public void xSpace() {
/* 19 */     MarkupParserCommon$class.xSpace(this);
/*    */   }
/*    */   
/*    */   public <T> T returning(Object x, Function1 f) {
/* 19 */     return (T)MarkupParserCommon$class.returning(this, x, f);
/*    */   }
/*    */   
/*    */   public <A, B> B saving(Object getter, Function1 setter, Function0 body) {
/* 19 */     return (B)MarkupParserCommon$class.saving(this, getter, setter, body);
/*    */   }
/*    */   
/*    */   public <T> T xTakeUntil(Function2 handler, Function0 positioner, String until) {
/* 19 */     return (T)MarkupParserCommon$class.xTakeUntil(this, handler, positioner, until);
/*    */   }
/*    */   
/*    */   public final boolean isSpace(char ch) {
/* 19 */     return TokenTests$class.isSpace(this, ch);
/*    */   }
/*    */   
/*    */   public final boolean isSpace(Seq cs) {
/* 19 */     return TokenTests$class.isSpace(this, cs);
/*    */   }
/*    */   
/*    */   public boolean isAlpha(char c) {
/* 19 */     return TokenTests$class.isAlpha(this, c);
/*    */   }
/*    */   
/*    */   public boolean isAlphaDigit(char c) {
/* 19 */     return TokenTests$class.isAlphaDigit(this, c);
/*    */   }
/*    */   
/*    */   public boolean isNameChar(char ch) {
/* 19 */     return TokenTests$class.isNameChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isNameStart(char ch) {
/* 19 */     return TokenTests$class.isNameStart(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isName(String s) {
/* 19 */     return TokenTests$class.isName(this, s);
/*    */   }
/*    */   
/*    */   public boolean isPubIDChar(char ch) {
/* 19 */     return TokenTests$class.isPubIDChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isValidIANAEncoding(Seq ianaEncoding) {
/* 19 */     return TokenTests$class.isValidIANAEncoding(this, ianaEncoding);
/*    */   }
/*    */   
/*    */   public boolean checkSysID(String s) {
/* 19 */     return TokenTests$class.checkSysID(this, s);
/*    */   }
/*    */   
/*    */   public boolean checkPubID(String s) {
/* 19 */     return TokenTests$class.checkPubID(this, s);
/*    */   }
/*    */   
/*    */   public Source input() {
/* 19 */     return this.input;
/*    */   }
/*    */   
/*    */   public XhtmlParser(Source input) {
/* 19 */     TokenTests$class.$init$(this);
/* 19 */     MarkupParserCommon$class.$init$(this);
/* 19 */     MarkupParser$class.$init$(this);
/* 19 */     ExternalSources$class.$init$(this);
/* 20 */     this.preserveWS = true;
/* 21 */     ent().$plus$plus$eq((TraversableOnce)XhtmlEntities$.MODULE$.apply());
/*    */   }
/*    */   
/*    */   public boolean preserveWS() {
/*    */     return this.preserveWS;
/*    */   }
/*    */   
/*    */   public static NodeSeq apply(Source paramSource) {
/*    */     return XhtmlParser$.MODULE$.apply(paramSource);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\XhtmlParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */