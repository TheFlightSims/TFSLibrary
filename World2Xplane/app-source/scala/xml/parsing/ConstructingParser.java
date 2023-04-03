/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import java.io.File;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.BufferedIterator;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.io.Source;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.xml.Document;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.NodeBuffer;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.dtd.DTD;
/*    */ import scala.xml.dtd.ExternalID;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e;Q!\001\002\t\002%\t!cQ8ogR\024Xo\031;j]\036\004\026M]:fe*\0211\001B\001\ba\006\0248/\0338h\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001#\001\016\005I\031uN\\:ueV\034G/\0338h!\006\0248/\032:\024\005-q\001CA\b\021\033\0051\021BA\t\007\005\031\te.\037*fM\")1c\003C\001)\0051A(\0338jiz\"\022!\003\005\006--!\taF\001\tMJ|WNR5mKR\031\001DS*\021\005)Ib\001\002\007\003\001i\031B!G\016\037CA\021!\002H\005\003;\t\0211cQ8ogR\024Xo\031;j]\036D\025M\0343mKJ\004\"AC\020\n\005\001\022!aD#yi\026\024h.\0317T_V\0248-Z:\021\005)\021\023BA\022\003\0051i\025M]6vaB\013'o]3s\021!)\023D!b\001\n\0031\023!B5oaV$X#A\024\021\005!ZS\"A\025\013\005)2\021AA5p\023\ta\023F\001\004T_V\0248-\032\005\t]e\021\t\021)A\005O\0051\021N\0349vi\002B\001\002M\r\003\006\004%\t!M\001\013aJ,7/\032:wK^\033V#\001\032\021\005=\031\024B\001\033\007\005\035\021un\0347fC:D\001BN\r\003\002\003\006IAM\001\faJ,7/\032:wK^\033\006\005C\003\0243\021\005\001\bF\002\031siBQ!J\034A\002\035BQ\001M\034A\002IBQ\001P\r\005Bu\n1\001\\8h)\tq\024\t\005\002\020%\021\001I\002\002\005+:LG\017C\003Cw\001\0071)A\002ng\036\004\"\001R$\017\005=)\025B\001$\007\003\031\001&/\0323fM&\021\001*\023\002\007'R\024\030N\\4\013\005\0313\001\"B&\026\001\004a\025aA5oaB\021Q*U\007\002\035*\021!f\024\006\002!\006!!.\031<b\023\t\021fJ\001\003GS2,\007\"\002\031\026\001\004\021\004\"B+\f\t\0031\026A\0034s_6\034v.\036:dKR\031\001d\026-\t\013-#\006\031A\024\t\013A\"\006\031\001\032")
/*    */ public class ConstructingParser extends ConstructingHandler implements ExternalSources, MarkupParser {
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
/*    */   public Source curInput() {
/* 47 */     return this.curInput;
/*    */   }
/*    */   
/*    */   public void curInput_$eq(Source x$1) {
/* 47 */     this.curInput = x$1;
/*    */   }
/*    */   
/*    */   public MarkupHandler scala$xml$parsing$MarkupParser$$handle() {
/* 47 */     return this.scala$xml$parsing$MarkupParser$$handle;
/*    */   }
/*    */   
/*    */   public List<Source> inpStack() {
/* 47 */     return this.inpStack;
/*    */   }
/*    */   
/*    */   public void inpStack_$eq(List<Source> x$1) {
/* 47 */     this.inpStack = x$1;
/*    */   }
/*    */   
/*    */   public int pos() {
/* 47 */     return this.pos;
/*    */   }
/*    */   
/*    */   public void pos_$eq(int x$1) {
/* 47 */     this.pos = x$1;
/*    */   }
/*    */   
/*    */   public int extIndex() {
/* 47 */     return this.extIndex;
/*    */   }
/*    */   
/*    */   public void extIndex_$eq(int x$1) {
/* 47 */     this.extIndex = x$1;
/*    */   }
/*    */   
/*    */   public int tmppos() {
/* 47 */     return this.tmppos;
/*    */   }
/*    */   
/*    */   public void tmppos_$eq(int x$1) {
/* 47 */     this.tmppos = x$1;
/*    */   }
/*    */   
/*    */   public boolean nextChNeeded() {
/* 47 */     return this.nextChNeeded;
/*    */   }
/*    */   
/*    */   public void nextChNeeded_$eq(boolean x$1) {
/* 47 */     this.nextChNeeded = x$1;
/*    */   }
/*    */   
/*    */   public boolean reachedEof() {
/* 47 */     return this.reachedEof;
/*    */   }
/*    */   
/*    */   public void reachedEof_$eq(boolean x$1) {
/* 47 */     this.reachedEof = x$1;
/*    */   }
/*    */   
/*    */   public char lastChRead() {
/* 47 */     return this.lastChRead;
/*    */   }
/*    */   
/*    */   public void lastChRead_$eq(char x$1) {
/* 47 */     this.lastChRead = x$1;
/*    */   }
/*    */   
/*    */   public StringBuilder cbuf() {
/* 47 */     return this.cbuf;
/*    */   }
/*    */   
/*    */   public DTD dtd() {
/* 47 */     return this.dtd;
/*    */   }
/*    */   
/*    */   public void dtd_$eq(DTD x$1) {
/* 47 */     this.dtd = x$1;
/*    */   }
/*    */   
/*    */   public Document doc() {
/* 47 */     return this.doc;
/*    */   }
/*    */   
/*    */   public void doc_$eq(Document x$1) {
/* 47 */     this.doc = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(MarkupHandler x$1) {
/* 47 */     this.scala$xml$parsing$MarkupParser$$handle = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(StringBuilder x$1) {
/* 47 */     this.cbuf = x$1;
/*    */   }
/*    */   
/*    */   public Nothing$ truncatedError(String msg) {
/* 47 */     return MarkupParser$class.truncatedError(this, msg);
/*    */   }
/*    */   
/*    */   public Nothing$ errorNoEnd(String tag) {
/* 47 */     return MarkupParser$class.errorNoEnd(this, tag);
/*    */   }
/*    */   
/*    */   public void xHandleError(char that, String msg) {
/* 47 */     MarkupParser$class.xHandleError(this, that, msg);
/*    */   }
/*    */   
/*    */   public BufferedIterator<Object> lookahead() {
/* 47 */     return MarkupParser$class.lookahead(this);
/*    */   }
/*    */   
/*    */   public char ch() {
/* 47 */     return MarkupParser$class.ch(this);
/*    */   }
/*    */   
/*    */   public boolean eof() {
/* 47 */     return MarkupParser$class.eof(this);
/*    */   }
/*    */   
/*    */   public MetaData xmlProcInstr() {
/* 47 */     return MarkupParser$class.xmlProcInstr(this);
/*    */   }
/*    */   
/*    */   public Tuple3<Option<String>, Option<String>, Option<Object>> prolog() {
/* 47 */     return MarkupParser$class.prolog(this);
/*    */   }
/*    */   
/*    */   public Tuple2<Option<String>, Option<String>> textDecl() {
/* 47 */     return MarkupParser$class.textDecl(this);
/*    */   }
/*    */   
/*    */   public Document document() {
/* 47 */     return MarkupParser$class.document(this);
/*    */   }
/*    */   
/*    */   public StringBuilder putChar(char c) {
/* 47 */     return MarkupParser$class.putChar(this, c);
/*    */   }
/*    */   
/*    */   public MarkupHandler initialize() {
/* 47 */     return MarkupParser$class.initialize(this);
/*    */   }
/*    */   
/*    */   public char ch_returning_nextch() {
/* 47 */     return MarkupParser$class.ch_returning_nextch(this);
/*    */   }
/*    */   
/*    */   public Tuple2<MetaData, NamespaceBinding> mkAttributes(String name, NamespaceBinding pscope) {
/* 47 */     return MarkupParser$class.mkAttributes(this, name, pscope);
/*    */   }
/*    */   
/*    */   public NodeSeq mkProcInstr(int position, String name, String text) {
/* 47 */     return MarkupParser$class.mkProcInstr(this, position, name, text);
/*    */   }
/*    */   
/*    */   public void nextch() {
/* 47 */     MarkupParser$class.nextch(this);
/*    */   }
/*    */   
/*    */   public Tuple2<MetaData, NamespaceBinding> xAttributes(NamespaceBinding pscope) {
/* 47 */     return MarkupParser$class.xAttributes(this, pscope);
/*    */   }
/*    */   
/*    */   public String xEntityValue() {
/* 47 */     return MarkupParser$class.xEntityValue(this);
/*    */   }
/*    */   
/*    */   public NodeSeq xCharData() {
/* 47 */     return MarkupParser$class.xCharData(this);
/*    */   }
/*    */   
/*    */   public NodeSeq xComment() {
/* 47 */     return MarkupParser$class.xComment(this);
/*    */   }
/*    */   
/*    */   public void appendText(int pos, NodeBuffer ts, String txt) {
/* 47 */     MarkupParser$class.appendText(this, pos, ts, txt);
/*    */   }
/*    */   
/*    */   public void content1(NamespaceBinding pscope, NodeBuffer ts) {
/* 47 */     MarkupParser$class.content1(this, pscope, ts);
/*    */   }
/*    */   
/*    */   public NodeSeq content(NamespaceBinding pscope) {
/* 47 */     return MarkupParser$class.content(this, pscope);
/*    */   }
/*    */   
/*    */   public ExternalID externalID() {
/* 47 */     return MarkupParser$class.externalID(this);
/*    */   }
/*    */   
/*    */   public void parseDTD() {
/* 47 */     MarkupParser$class.parseDTD(this);
/*    */   }
/*    */   
/*    */   public NodeSeq element(NamespaceBinding pscope) {
/* 47 */     return MarkupParser$class.element(this, pscope);
/*    */   }
/*    */   
/*    */   public NodeSeq element1(NamespaceBinding pscope) {
/* 47 */     return MarkupParser$class.element1(this, pscope);
/*    */   }
/*    */   
/*    */   public String systemLiteral() {
/* 47 */     return MarkupParser$class.systemLiteral(this);
/*    */   }
/*    */   
/*    */   public String pubidLiteral() {
/* 47 */     return MarkupParser$class.pubidLiteral(this);
/*    */   }
/*    */   
/*    */   public void extSubset() {
/* 47 */     MarkupParser$class.extSubset(this);
/*    */   }
/*    */   
/*    */   public Object markupDecl1() {
/* 47 */     return MarkupParser$class.markupDecl1(this);
/*    */   }
/*    */   
/*    */   public void markupDecl() {
/* 47 */     MarkupParser$class.markupDecl(this);
/*    */   }
/*    */   
/*    */   public void intSubset() {
/* 47 */     MarkupParser$class.intSubset(this);
/*    */   }
/*    */   
/*    */   public void elementDecl() {
/* 47 */     MarkupParser$class.elementDecl(this);
/*    */   }
/*    */   
/*    */   public void attrDecl() {
/* 47 */     MarkupParser$class.attrDecl(this);
/*    */   }
/*    */   
/*    */   public void entityDecl() {
/* 47 */     MarkupParser$class.entityDecl(this);
/*    */   }
/*    */   
/*    */   public void notationDecl() {
/* 47 */     MarkupParser$class.notationDecl(this);
/*    */   }
/*    */   
/*    */   public void reportSyntaxError(int pos, String str) {
/* 47 */     MarkupParser$class.reportSyntaxError(this, pos, str);
/*    */   }
/*    */   
/*    */   public void reportSyntaxError(String str) {
/* 47 */     MarkupParser$class.reportSyntaxError(this, str);
/*    */   }
/*    */   
/*    */   public void reportValidationError(int pos, String str) {
/* 47 */     MarkupParser$class.reportValidationError(this, pos, str);
/*    */   }
/*    */   
/*    */   public void push(String entityName) {
/* 47 */     MarkupParser$class.push(this, entityName);
/*    */   }
/*    */   
/*    */   public void pushExternal(String systemId) {
/* 47 */     MarkupParser$class.pushExternal(this, systemId);
/*    */   }
/*    */   
/*    */   public void pop() {
/* 47 */     MarkupParser$class.pop(this);
/*    */   }
/*    */   
/*    */   public Nothing$ unreachable() {
/* 47 */     return MarkupParserCommon$class.unreachable(this);
/*    */   }
/*    */   
/*    */   public Tuple2<String, Object> xTag(Object pscope) {
/* 47 */     return MarkupParserCommon$class.xTag(this, pscope);
/*    */   }
/*    */   
/*    */   public Object xProcInstr() {
/* 47 */     return MarkupParserCommon$class.xProcInstr(this);
/*    */   }
/*    */   
/*    */   public String xAttributeValue(char endCh) {
/* 47 */     return MarkupParserCommon$class.xAttributeValue(this, endCh);
/*    */   }
/*    */   
/*    */   public String xAttributeValue() {
/* 47 */     return MarkupParserCommon$class.xAttributeValue(this);
/*    */   }
/*    */   
/*    */   public void xEndTag(String startName) {
/* 47 */     MarkupParserCommon$class.xEndTag(this, startName);
/*    */   }
/*    */   
/*    */   public String xName() {
/* 47 */     return MarkupParserCommon$class.xName(this);
/*    */   }
/*    */   
/*    */   public String xCharRef(Function0 ch, Function0 nextch) {
/* 47 */     return MarkupParserCommon$class.xCharRef(this, ch, nextch);
/*    */   }
/*    */   
/*    */   public String xCharRef(Iterator it) {
/* 47 */     return MarkupParserCommon$class.xCharRef(this, it);
/*    */   }
/*    */   
/*    */   public String xCharRef() {
/* 47 */     return MarkupParserCommon$class.xCharRef(this);
/*    */   }
/*    */   
/*    */   public <T> T errorAndResult(String msg, Object x) {
/* 47 */     return (T)MarkupParserCommon$class.errorAndResult(this, msg, x);
/*    */   }
/*    */   
/*    */   public void xToken(char that) {
/* 47 */     MarkupParserCommon$class.xToken(this, that);
/*    */   }
/*    */   
/*    */   public void xToken(Seq that) {
/* 47 */     MarkupParserCommon$class.xToken(this, that);
/*    */   }
/*    */   
/*    */   public void xEQ() {
/* 47 */     MarkupParserCommon$class.xEQ(this);
/*    */   }
/*    */   
/*    */   public void xSpaceOpt() {
/* 47 */     MarkupParserCommon$class.xSpaceOpt(this);
/*    */   }
/*    */   
/*    */   public void xSpace() {
/* 47 */     MarkupParserCommon$class.xSpace(this);
/*    */   }
/*    */   
/*    */   public <T> T returning(Object x, Function1 f) {
/* 47 */     return (T)MarkupParserCommon$class.returning(this, x, f);
/*    */   }
/*    */   
/*    */   public <A, B> B saving(Object getter, Function1 setter, Function0 body) {
/* 47 */     return (B)MarkupParserCommon$class.saving(this, getter, setter, body);
/*    */   }
/*    */   
/*    */   public <T> T xTakeUntil(Function2 handler, Function0 positioner, String until) {
/* 47 */     return (T)MarkupParserCommon$class.xTakeUntil(this, handler, positioner, until);
/*    */   }
/*    */   
/*    */   public final boolean isSpace(char ch) {
/* 47 */     return TokenTests$class.isSpace(this, ch);
/*    */   }
/*    */   
/*    */   public final boolean isSpace(Seq cs) {
/* 47 */     return TokenTests$class.isSpace(this, cs);
/*    */   }
/*    */   
/*    */   public boolean isAlpha(char c) {
/* 47 */     return TokenTests$class.isAlpha(this, c);
/*    */   }
/*    */   
/*    */   public boolean isAlphaDigit(char c) {
/* 47 */     return TokenTests$class.isAlphaDigit(this, c);
/*    */   }
/*    */   
/*    */   public boolean isNameChar(char ch) {
/* 47 */     return TokenTests$class.isNameChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isNameStart(char ch) {
/* 47 */     return TokenTests$class.isNameStart(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isName(String s) {
/* 47 */     return TokenTests$class.isName(this, s);
/*    */   }
/*    */   
/*    */   public boolean isPubIDChar(char ch) {
/* 47 */     return TokenTests$class.isPubIDChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isValidIANAEncoding(Seq ianaEncoding) {
/* 47 */     return TokenTests$class.isValidIANAEncoding(this, ianaEncoding);
/*    */   }
/*    */   
/*    */   public boolean checkSysID(String s) {
/* 47 */     return TokenTests$class.checkSysID(this, s);
/*    */   }
/*    */   
/*    */   public boolean checkPubID(String s) {
/* 47 */     return TokenTests$class.checkPubID(this, s);
/*    */   }
/*    */   
/*    */   public Source externalSource(String systemId) {
/* 47 */     return ExternalSources$class.externalSource(this, systemId);
/*    */   }
/*    */   
/*    */   public Source input() {
/* 47 */     return this.input;
/*    */   }
/*    */   
/*    */   public boolean preserveWS() {
/* 47 */     return this.preserveWS;
/*    */   }
/*    */   
/*    */   public ConstructingParser(Source input, boolean preserveWS) {
/* 47 */     ExternalSources$class.$init$(this);
/* 47 */     TokenTests$class.$init$(this);
/* 47 */     MarkupParserCommon$class.$init$(this);
/* 47 */     MarkupParser$class.$init$(this);
/*    */   }
/*    */   
/*    */   public void log(String msg) {}
/*    */   
/*    */   public static ConstructingParser fromSource(Source paramSource, boolean paramBoolean) {
/*    */     return ConstructingParser$.MODULE$.fromSource(paramSource, paramBoolean);
/*    */   }
/*    */   
/*    */   public static ConstructingParser fromFile(File paramFile, boolean paramBoolean) {
/*    */     return ConstructingParser$.MODULE$.fromFile(paramFile, paramBoolean);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ConstructingParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */