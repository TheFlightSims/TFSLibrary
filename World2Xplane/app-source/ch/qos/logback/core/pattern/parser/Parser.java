/*     */ package ch.qos.logback.core.pattern.parser;
/*     */ 
/*     */ import ch.qos.logback.core.pattern.Converter;
/*     */ import ch.qos.logback.core.pattern.FormatInfo;
/*     */ import ch.qos.logback.core.pattern.IdentityCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.ReplacingCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.util.IEscapeUtil;
/*     */ import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.spi.ScanException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class Parser<E> extends ContextAwareBase {
/*     */   public static final String MISSING_RIGHT_PARENTHESIS = "http://logback.qos.ch/codes.html#missingRightParenthesis";
/*     */   
/*  49 */   public static final Map<String, String> DEFAULT_COMPOSITE_CONVERTER_MAP = new HashMap<String, String>();
/*     */   
/*     */   public static final String REPLACE_CONVERTER_WORD = "replace";
/*     */   
/*     */   final List tokenList;
/*     */   
/*     */   static {
/*  52 */     DEFAULT_COMPOSITE_CONVERTER_MAP.put(Token.BARE_COMPOSITE_KEYWORD_TOKEN.getValue().toString(), IdentityCompositeConverter.class.getName());
/*  54 */     DEFAULT_COMPOSITE_CONVERTER_MAP.put("replace", ReplacingCompositeConverter.class.getName());
/*     */   }
/*     */   
/*  59 */   int pointer = 0;
/*     */   
/*     */   Parser(TokenStream ts) throws ScanException {
/*  62 */     this.tokenList = ts.tokenize();
/*     */   }
/*     */   
/*     */   public Parser(String pattern) throws ScanException {
/*  66 */     this(pattern, (IEscapeUtil)new RegularEscapeUtil());
/*     */   }
/*     */   
/*     */   public Parser(String pattern, IEscapeUtil escapeUtil) throws ScanException {
/*     */     try {
/*  71 */       TokenStream ts = new TokenStream(pattern, escapeUtil);
/*  72 */       this.tokenList = ts.tokenize();
/*  73 */     } catch (IllegalArgumentException npe) {
/*  74 */       throw new ScanException("Failed to initialize Parser", npe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Converter<E> compile(Node top, Map converterMap) {
/*  88 */     Compiler<E> compiler = new Compiler<E>(top, converterMap);
/*  89 */     compiler.setContext(this.context);
/*  91 */     return compiler.compile();
/*     */   }
/*     */   
/*     */   public Node parse() throws ScanException {
/*  95 */     return E();
/*     */   }
/*     */   
/*     */   Node E() throws ScanException {
/* 100 */     Node t = T();
/* 101 */     if (t == null)
/* 102 */       return null; 
/* 104 */     Node eOpt = Eopt();
/* 105 */     if (eOpt != null)
/* 106 */       t.setNext(eOpt); 
/* 108 */     return t;
/*     */   }
/*     */   
/*     */   Node Eopt() throws ScanException {
/* 114 */     Token next = getCurentToken();
/* 116 */     if (next == null)
/* 117 */       return null; 
/* 119 */     return E();
/*     */   }
/*     */   
/*     */   Node T() throws ScanException {
/*     */     Token u;
/*     */     FormattingNode c;
/* 125 */     Token t = getCurentToken();
/* 126 */     expectNotNull(t, "a LITERAL or '%'");
/* 128 */     switch (t.getType()) {
/*     */       case 1000:
/* 130 */         advanceTokenPointer();
/* 131 */         return new Node(0, t.getValue());
/*     */       case 37:
/* 133 */         advanceTokenPointer();
/* 136 */         u = getCurentToken();
/* 138 */         expectNotNull(u, "a FORMAT_MODIFIER, SIMPLE_KEYWORD or COMPOUND_KEYWORD");
/* 139 */         if (u.getType() == 1002) {
/* 140 */           FormatInfo fi = FormatInfo.valueOf((String)u.getValue());
/* 141 */           advanceTokenPointer();
/* 142 */           c = C();
/* 143 */           c.setFormatInfo(fi);
/*     */         } else {
/* 145 */           c = C();
/*     */         } 
/* 147 */         return c;
/*     */     } 
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   FormattingNode C() throws ScanException {
/* 157 */     Token t = getCurentToken();
/* 160 */     expectNotNull(t, "a LEFT_PARENTHESIS or KEYWORD");
/* 161 */     int type = t.getType();
/* 162 */     switch (type) {
/*     */       case 1004:
/* 164 */         return SINGLE();
/*     */       case 1005:
/* 166 */         advanceTokenPointer();
/* 167 */         return COMPOSITE(t.getValue().toString());
/*     */     } 
/* 169 */     throw new IllegalStateException("Unexpected token " + t);
/*     */   }
/*     */   
/*     */   FormattingNode SINGLE() throws ScanException {
/* 175 */     Token t = getNextToken();
/* 177 */     SimpleKeywordNode keywordNode = new SimpleKeywordNode(t.getValue());
/* 179 */     Token ot = getCurentToken();
/* 180 */     if (ot != null && ot.getType() == 1006) {
/* 181 */       List<String> optionList = (List<String>)ot.getValue();
/* 182 */       keywordNode.setOptions(optionList);
/* 183 */       advanceTokenPointer();
/*     */     } 
/* 185 */     return keywordNode;
/*     */   }
/*     */   
/*     */   FormattingNode COMPOSITE(String keyword) throws ScanException {
/* 189 */     CompositeNode compositeNode = new CompositeNode(keyword);
/* 191 */     Node childNode = E();
/* 192 */     compositeNode.setChildNode(childNode);
/* 194 */     Token t = getNextToken();
/* 196 */     if (t == null || t.getType() != 41) {
/* 197 */       String msg = "Expecting RIGHT_PARENTHESIS token but got " + t;
/* 198 */       addError(msg);
/* 199 */       addError("See also http://logback.qos.ch/codes.html#missingRightParenthesis");
/* 200 */       throw new ScanException(msg);
/*     */     } 
/* 202 */     Token ot = getCurentToken();
/* 203 */     if (ot != null && ot.getType() == 1006) {
/* 204 */       List<String> optionList = (List<String>)ot.getValue();
/* 205 */       compositeNode.setOptions(optionList);
/* 206 */       advanceTokenPointer();
/*     */     } 
/* 208 */     return compositeNode;
/*     */   }
/*     */   
/*     */   Token getNextToken() {
/* 212 */     if (this.pointer < this.tokenList.size())
/* 213 */       return this.tokenList.get(this.pointer++); 
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   Token getCurentToken() {
/* 219 */     if (this.pointer < this.tokenList.size())
/* 220 */       return this.tokenList.get(this.pointer); 
/* 222 */     return null;
/*     */   }
/*     */   
/*     */   void advanceTokenPointer() {
/* 226 */     this.pointer++;
/*     */   }
/*     */   
/*     */   void expectNotNull(Token t, String expected) {
/* 230 */     if (t == null)
/* 231 */       throw new IllegalStateException("All tokens consumed but was expecting " + expected); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\pattern\parser\Parser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */