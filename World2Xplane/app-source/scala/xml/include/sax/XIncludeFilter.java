/*     */ package scala.xml.include.sax;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Stack;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.xml.include.CircularIncludeException;
/*     */ import scala.xml.include.UnavailableResourceException;
/*     */ import scala.xml.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055e\001B\001\003\001-\021a\002W%oG2,H-\032$jYR,'O\003\002\004\t\005\0311/\031=\013\005\0251\021aB5oG2,H-\032\006\003\017!\t1\001_7m\025\005I\021!B:dC2\f7\001A\n\003\0011\001\"!\004\013\016\0039Q!a\004\t\002\017!,G\016]3sg*\0211!\005\006\003\017IQ\021aE\001\004_J<\027BA\013\017\0055AV\n\024$jYR,'/S7qY\")q\003\001C\0011\0051A(\0338jiz\"\022!\007\t\0035\001i\021A\001\005\b9\001\021\r\021\"\002\036\003IA\026JT\"M+\022+uLT!N\013N\003\026iQ#\026\003yy\021aH\021\002A\005y\002\016\036;qu=zso^</oNrsN]40eA\002\024g\f-J]\016dW\017Z3\t\r\t\002\001\025!\004\037\003MA\026JT\"M+\022+uLT!N\013N\003\026iQ#!\021\035!\003A1A\005\n\025\nQAY1tKN,\022A\n\t\004O1rS\"\001\025\013\005%R\023\001B;uS2T\021aK\001\005U\0064\030-\003\002.Q\t)1\013^1dWB\021qFM\007\002a)\021\021GK\001\004]\026$\030BA\0321\005\r)&\013\024\005\007k\001\001\013\021\002\024\002\r\t\f7/Z:!\021\0359\004A1A\005\na\n\001\002\\8dCR|'o]\013\002sA\031q\005\f\036\021\005mbT\"\001\t\n\005u\002\"a\002'pG\006$xN\035\005\007\001\001\013\021B\035\002\0231|7-\031;peN\004\003\"B!\001\t\003\022\025AE:fi\022{7-^7f]RdunY1u_J$\"aQ$\021\005\021+U\"\001\005\n\005\031C!\001B+oSRDQ\001\023!A\002i\nq\001\\8dCR|'\017C\004K\001\001\007I\021B&\002\0131,g/\0327\026\0031\003\"\001R'\n\0059C!aA%oi\"9\001\013\001a\001\n\023\t\026!\0037fm\026dw\fJ3r)\t\031%\013C\004T\037\006\005\t\031\001'\002\007a$\023\007\003\004V\001\001\006K\001T\001\007Y\0264X\r\034\021\t\013]\003A\021\001-\002)%t7/\0333f\023:\034G.\0363f\0132,W.\0328u)\005I\006C\001#[\023\tY\006BA\004C_>dW-\0318\t\013u\003A\021\t0\002\031M$\030M\035;FY\026lWM\034;\025\013\r{\006N\0337\t\013\001d\006\031A1\002\007U\024\030\016\005\002cK:\021AiY\005\003I\"\ta\001\025:fI\0264\027B\0014h\005\031\031FO]5oO*\021A\r\003\005\006Sr\003\r!Y\001\nY>\034\027\r\034(b[\026DQa\033/A\002\005\fQ!\035(b[\026DQ!\034/A\0029\fQ!\031;ugF\002\"aO8\n\005A\004\"AC!uiJL'-\036;fg\")!\017\001C!g\006QQM\0343FY\026lWM\034;\025\t\r#XO\036\005\006AF\004\r!\031\005\006SF\004\r!\031\005\006WF\004\r!\031\005\bq\002\001\r\021\"\003L\003\025!W\r\035;i\021\035Q\b\0011A\005\nm\f\021\002Z3qi\"|F%Z9\025\005\rc\bbB*z\003\003\005\r\001\024\005\007}\002\001\013\025\002'\002\r\021,\007\017\0365!\021\035\t\t\001\001C!\003\007\tQb\035;beR$unY;nK:$H#A\"\t\017\005\035\001\001\"\021\002\004\005YQM\0343E_\016,X.\0328u\021\035\tY\001\001C!\003\033\t!c\035;beR\004&/\0324jq6\013\007\017]5oOR)1)a\004\002\024!9\021\021CA\005\001\004\t\027A\0029sK\032L\007\020\003\004a\003\023\001\r!\031\005\b\003/\001A\021IA\r\003A)g\016\032)sK\032L\0070T1qa&tw\rF\002D\0037Aq!!\005\002\026\001\007\021\rC\004\002 \001!\t%!\t\002\025\rD\027M]1di\026\0248\017F\004D\003G\t\031$a\016\t\021\005\025\022Q\004a\001\003O\t!a\0315\021\013\021\013I#!\f\n\007\005-\002BA\003BeJ\f\027\020E\002E\003_I1!!\r\t\005\021\031\005.\031:\t\017\005U\022Q\004a\001\031\006)1\017^1si\"9\021\021HA\017\001\004a\025A\0027f]\036$\b\016C\004\002>\001!\t%a\020\002'%<gn\034:bE2,w\013[5uKN\004\030mY3\025\017\r\013\t%a\021\002F!A\021QEA\036\001\004\t9\003C\004\0026\005m\002\031\001'\t\017\005e\0221\ba\001\031\"9\021\021\n\001\005B\005-\023!\0069s_\016,7o]5oO&s7\017\036:vGRLwN\034\013\006\007\0065\023\021\013\005\b\003\037\n9\0051\001b\003\031!\030M]4fi\"9\0211KA$\001\004\t\027\001\0023bi\006Dq!a\026\001\t\003\nI&A\007tW&\004\b/\0323F]RLG/\037\013\004\007\006m\003bBA/\003+\002\r!Y\001\005]\006lW\rC\004\002b\001!I!a\031\002\027\035,G\017T8dCRLwN\034\013\002C\"9\021q\r\001\005\n\005%\024aE5oG2,H-\032+fqR$unY;nK:$H#B\"\002l\005=\004bBA7\003K\002\r!Y\001\004kJd\007bBA9\003K\002\r!Y\001\nK:\034w\016Z5oOFB\021\"!\036\001\001\004%I!a\036\002\r\005$(k\\8u+\005I\006\"CA>\001\001\007I\021BA?\003)\tGOU8pi~#S-\035\013\004\007\006}\004\002C*\002z\005\005\t\031A-\t\017\005\r\005\001)Q\0053\0069\021\r\036*p_R\004\003bBAD\001\021%\021\021R\001\023S:\034G.\0363f16cEi\\2v[\026tG\017F\002D\003\027Cq!!\034\002\006\002\007\021\r")
/*     */ public class XIncludeFilter extends XMLFilterImpl {
/*     */   private final String XINCLUDE_NAMESPACE;
/*     */   
/*     */   public final String XINCLUDE_NAMESPACE() {
/*  69 */     return "http://www.w3.org/2001/XInclude";
/*     */   }
/*     */   
/*  71 */   private final Stack<URL> bases = new Stack<URL>();
/*     */   
/*     */   private Stack<URL> bases() {
/*  71 */     return this.bases;
/*     */   }
/*     */   
/*  72 */   private final Stack<Locator> locators = new Stack<Locator>();
/*     */   
/*     */   private Stack<Locator> locators() {
/*  72 */     return this.locators;
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/*  89 */     locators().push(locator);
/*  90 */     String base = locator.getSystemId();
/*     */     try {
/*  92 */       bases().push(new URL(base));
/*  98 */       super.setDocumentLocator(locator);
/*     */       return;
/*     */     } catch (MalformedURLException malformedURLException) {
/*     */       throw new UnsupportedOperationException((new StringBuilder()).append("Unrecognized SYSTEM ID: ").append(base).toString());
/*     */     } 
/*     */   }
/*     */   
/* 103 */   private int level = 0;
/*     */   
/*     */   private int level() {
/* 103 */     return this.level;
/*     */   }
/*     */   
/*     */   private void level_$eq(int x$1) {
/* 103 */     this.level = x$1;
/*     */   }
/*     */   
/*     */   public boolean insideIncludeElement() {
/* 114 */     return (level() != 0);
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes atts1) {
/*     */     // Byte code:
/*     */     //   0: aload #4
/*     */     //   2: astore #5
/*     */     //   4: aload_0
/*     */     //   5: invokespecial level : ()I
/*     */     //   8: iconst_0
/*     */     //   9: if_icmpne -> 276
/*     */     //   12: aload #4
/*     */     //   14: ldc 'http://www.w3.org/XML/1998/namespace'
/*     */     //   16: ldc 'base'
/*     */     //   18: invokeinterface getValue : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   23: astore #6
/*     */     //   25: aload_0
/*     */     //   26: invokespecial bases : ()Ljava/util/Stack;
/*     */     //   29: invokevirtual peek : ()Ljava/lang/Object;
/*     */     //   32: checkcast java/net/URL
/*     */     //   35: astore #7
/*     */     //   37: aload #7
/*     */     //   39: astore #8
/*     */     //   41: aload #6
/*     */     //   43: ifnull -> 59
/*     */     //   46: new java/net/URL
/*     */     //   49: dup
/*     */     //   50: aload #7
/*     */     //   52: aload #6
/*     */     //   54: invokespecial <init> : (Ljava/net/URL;Ljava/lang/String;)V
/*     */     //   57: astore #8
/*     */     //   59: aload_0
/*     */     //   60: invokespecial bases : ()Ljava/util/Stack;
/*     */     //   63: aload #8
/*     */     //   65: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   68: pop
/*     */     //   69: aload_1
/*     */     //   70: ldc 'http://www.w3.org/2001/XInclude'
/*     */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   75: ifeq -> 222
/*     */     //   78: aload_2
/*     */     //   79: ldc 'include'
/*     */     //   81: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   84: ifeq -> 222
/*     */     //   87: aload #4
/*     */     //   89: ldc 'href'
/*     */     //   91: invokeinterface getValue : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   96: astore #10
/*     */     //   98: aload #10
/*     */     //   100: ifnonnull -> 113
/*     */     //   103: new org/xml/sax/SAXException
/*     */     //   106: dup
/*     */     //   107: ldc 'Missing href attribute'
/*     */     //   109: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   112: athrow
/*     */     //   113: aload #4
/*     */     //   115: ldc 'parse'
/*     */     //   117: invokeinterface getValue : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   122: astore #11
/*     */     //   124: aload #11
/*     */     //   126: ifnonnull -> 133
/*     */     //   129: ldc 'xml'
/*     */     //   131: astore #11
/*     */     //   133: aload #11
/*     */     //   135: ldc 'text'
/*     */     //   137: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   140: ifeq -> 165
/*     */     //   143: aload #4
/*     */     //   145: ldc 'encoding'
/*     */     //   147: invokeinterface getValue : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   152: astore #12
/*     */     //   154: aload_0
/*     */     //   155: aload #10
/*     */     //   157: aload #12
/*     */     //   159: invokespecial includeTextDocument : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   162: goto -> 181
/*     */     //   165: aload #11
/*     */     //   167: ldc 'xml'
/*     */     //   169: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   172: ifeq -> 194
/*     */     //   175: aload_0
/*     */     //   176: aload #10
/*     */     //   178: invokespecial includeXMLDocument : (Ljava/lang/String;)V
/*     */     //   181: aload_0
/*     */     //   182: aload_0
/*     */     //   183: invokespecial level : ()I
/*     */     //   186: iconst_1
/*     */     //   187: iadd
/*     */     //   188: invokespecial level_$eq : (I)V
/*     */     //   191: goto -> 276
/*     */     //   194: new org/xml/sax/SAXException
/*     */     //   197: dup
/*     */     //   198: new scala/collection/mutable/StringBuilder
/*     */     //   201: dup
/*     */     //   202: invokespecial <init> : ()V
/*     */     //   205: ldc 'Illegal value for parse attribute: '
/*     */     //   207: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   210: aload #11
/*     */     //   212: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   215: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   218: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   221: athrow
/*     */     //   222: aload_0
/*     */     //   223: invokespecial atRoot : ()Z
/*     */     //   226: ifeq -> 267
/*     */     //   229: new org/xml/sax/helpers/AttributesImpl
/*     */     //   232: dup
/*     */     //   233: aload #4
/*     */     //   235: invokespecial <init> : (Lorg/xml/sax/Attributes;)V
/*     */     //   238: astore #13
/*     */     //   240: aload #13
/*     */     //   242: ldc 'http://www.w3.org/XML/1998/namespace'
/*     */     //   244: ldc 'base'
/*     */     //   246: ldc 'xml:base'
/*     */     //   248: ldc 'CDATA'
/*     */     //   250: aload #8
/*     */     //   252: invokevirtual toExternalForm : ()Ljava/lang/String;
/*     */     //   255: invokevirtual addAttribute : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   258: aload #13
/*     */     //   260: astore #5
/*     */     //   262: aload_0
/*     */     //   263: iconst_0
/*     */     //   264: invokespecial atRoot_$eq : (Z)V
/*     */     //   267: aload_0
/*     */     //   268: aload_1
/*     */     //   269: aload_2
/*     */     //   270: aload_3
/*     */     //   271: aload #5
/*     */     //   273: invokespecial startElement : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/xml/sax/Attributes;)V
/*     */     //   276: return
/*     */     //   277: astore #9
/*     */     //   279: new org/xml/sax/SAXException
/*     */     //   282: dup
/*     */     //   283: new scala/collection/mutable/StringBuilder
/*     */     //   286: dup
/*     */     //   287: invokespecial <init> : ()V
/*     */     //   290: ldc 'Malformed base URL: '
/*     */     //   292: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   295: aload #8
/*     */     //   297: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   300: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   303: aload #9
/*     */     //   305: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Exception;)V
/*     */     //   308: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     //   #118	-> 4
/*     */     //   #122	-> 12
/*     */     //   #123	-> 25
/*     */     //   #124	-> 37
/*     */     //   #125	-> 41
/*     */     //   #127	-> 46
/*     */     //   #135	-> 59
/*     */     //   #137	-> 69
/*     */     //   #139	-> 87
/*     */     //   #141	-> 98
/*     */     //   #142	-> 103
/*     */     //   #145	-> 113
/*     */     //   #146	-> 124
/*     */     //   #148	-> 133
/*     */     //   #149	-> 143
/*     */     //   #150	-> 154
/*     */     //   #152	-> 165
/*     */     //   #153	-> 175
/*     */     //   #160	-> 181
/*     */     //   #157	-> 194
/*     */     //   #158	-> 198
/*     */     //   #157	-> 218
/*     */     //   #163	-> 222
/*     */     //   #165	-> 229
/*     */     //   #166	-> 240
/*     */     //   #167	-> 246
/*     */     //   #166	-> 255
/*     */     //   #168	-> 258
/*     */     //   #169	-> 262
/*     */     //   #171	-> 267
/*     */     //   #116	-> 276
/*     */     //   #130	-> 277
/*     */     //   #126	-> 277
/*     */     //   #131	-> 279
/*     */     //   #132	-> 283
/*     */     //   #131	-> 290
/*     */     //   #132	-> 295
/*     */     //   #131	-> 305
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	309	0	this	Lscala/xml/include/sax/XIncludeFilter;
/*     */     //   0	309	1	uri	Ljava/lang/String;
/*     */     //   0	309	2	localName	Ljava/lang/String;
/*     */     //   0	309	3	qName	Ljava/lang/String;
/*     */     //   0	309	4	atts1	Lorg/xml/sax/Attributes;
/*     */     //   4	305	5	atts	Lorg/xml/sax/Attributes;
/*     */     //   25	284	6	base	Ljava/lang/String;
/*     */     //   37	272	7	parentBase	Ljava/net/URL;
/*     */     //   41	268	8	currentBase	Ljava/net/URL;
/*     */     //   98	93	10	href	Ljava/lang/String;
/*     */     //   124	67	11	parse	Ljava/lang/String;
/*     */     //   154	8	12	encoding	Ljava/lang/String;
/*     */     //   240	27	13	attsImpl	Lorg/xml/sax/helpers/AttributesImpl;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   46	59	277	java/net/MalformedURLException
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) {
/* 177 */     if (uri.equals("http://www.w3.org/2001/XInclude") && 
/* 178 */       localName.equals("include")) {
/* 179 */       level_$eq(level() - 1);
/* 181 */     } else if (level() == 0) {
/* 182 */       bases().pop();
/* 183 */       super.endElement(uri, localName, qName);
/*     */     } 
/*     */   }
/*     */   
/* 187 */   private int depth = 0;
/*     */   
/*     */   private int depth() {
/* 187 */     return this.depth;
/*     */   }
/*     */   
/*     */   private void depth_$eq(int x$1) {
/* 187 */     this.depth = x$1;
/*     */   }
/*     */   
/*     */   public void startDocument() {
/* 190 */     level_$eq(0);
/* 191 */     if (depth() == 0)
/* 191 */       super.startDocument(); 
/* 192 */     depth_$eq(depth() + 1);
/*     */   }
/*     */   
/*     */   public void endDocument() {
/* 196 */     locators().pop();
/* 197 */     bases().pop();
/* 198 */     depth_$eq(depth() - 1);
/* 199 */     if (depth() == 0)
/* 199 */       super.endDocument(); 
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) {
/* 204 */     if (level() == 0)
/* 204 */       super.startPrefixMapping(prefix, uri); 
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) {
/* 208 */     if (level() == 0)
/* 208 */       super.endPrefixMapping(prefix); 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {
/* 212 */     if (level() == 0)
/* 212 */       super.characters(ch, start, length); 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) {
/* 216 */     if (level() == 0)
/* 216 */       super.ignorableWhitespace(ch, start, length); 
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) {
/* 220 */     if (level() == 0)
/* 220 */       super.processingInstruction(target, data); 
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) {
/* 224 */     if (level() == 0)
/* 224 */       super.skippedEntity(name); 
/*     */   }
/*     */   
/*     */   private String getLocation() {
/* 229 */     Locator locator = locators().peek();
/* 231 */     String publicID = "";
/* 232 */     String systemID = "";
/* 233 */     int column = -1;
/* 234 */     int line = -1;
/* 235 */     if (locator != null) {
/* 236 */       publicID = locator.getPublicId();
/* 237 */       systemID = locator.getSystemId();
/* 238 */       line = locator.getLineNumber();
/* 239 */       column = locator.getColumnNumber();
/*     */     } 
/* 243 */     return (new StringBuilder()).append(" in document included from ").append(publicID).append(" at ").append(systemID).append(" at line ").append(BoxesRunTime.boxToInteger(line)).append(", column ").append(BoxesRunTime.boxToInteger(column)).toString();
/*     */   }
/*     */   
/*     */   private void includeTextDocument(String url, String encoding1) {
/*     */     URL source;
/* 260 */     String encoding = encoding1;
/* 261 */     if (encoding1 == null || encoding1.trim().equals(""))
/* 261 */       encoding = "UTF-8"; 
/*     */     try {
/* 262 */       URL base = bases().peek();
/* 265 */       source = new URL(base, url);
/*     */       try {
/*     */         int charsRead;
/* 276 */         URLConnection uc = source.openConnection();
/* 277 */         BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
/* 278 */         String encodingFromHeader = uc.getContentEncoding();
/* 279 */         String contentType = uc.getContentType();
/* 280 */         if (encodingFromHeader == null) {
/* 286 */           if (contentType != null && ((
/* 287 */             contentType = contentType.toLowerCase())
/* 288 */             .equals("text/xml") || 
/* 289 */             contentType.equals("application/xml") || (
/* 290 */             contentType.startsWith("text/") && contentType.endsWith("+xml")) || (
/* 291 */             contentType.startsWith("application/") && contentType.endsWith("+xml"))))
/* 292 */             encoding = EncodingHeuristics$.MODULE$.readEncodingFromStream(in); 
/*     */         } else {
/*     */           encoding = encodingFromHeader;
/*     */         } 
/* 296 */         InputStreamReader reader = new InputStreamReader(in, encoding);
/* 297 */         char[] c = new char[1024];
/*     */         do {
/* 298 */           if ((charsRead = reader.read(c, 0, 1024)) <= 
/*     */             
/* 301 */             0)
/*     */             continue; 
/* 301 */           characters(c, 0, charsRead);
/* 302 */         } while (charsRead != -1);
/*     */         return;
/*     */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 306 */         throw new SAXException((
/* 307 */             new StringBuilder()).append("Unsupported encoding: ").append(encoding).append(getLocation()).toString(), unsupportedEncodingException);
/*     */       } catch (IOException iOException) {}
/*     */     } catch (MalformedURLException malformedURLException) {
/*     */       UnavailableResourceException ex = new UnavailableResourceException((new StringBuilder()).append("Unresolvable URL ").append(url).append(getLocation()).toString());
/*     */       ex.setRootCause(malformedURLException);
/*     */       throw new SAXException((new StringBuilder()).append("Unresolvable URL ").append(url).append(getLocation()).toString(), ex);
/*     */     } 
/* 309 */     throw new SAXException((
/* 310 */         new StringBuilder()).append("Document not found: ").append(source.toExternalForm()).append(getLocation()).toString(), iOException);
/*     */   }
/*     */   
/*     */   private boolean atRoot = false;
/*     */   
/*     */   private boolean atRoot() {
/* 315 */     return this.atRoot;
/*     */   }
/*     */   
/*     */   private void atRoot_$eq(boolean x$1) {
/* 315 */     this.atRoot = x$1;
/*     */   }
/*     */   
/*     */   private void includeXMLDocument(String url) {
/*     */     try {
/* 327 */       URL source = 
/* 328 */         new URL(bases().peek(), url);
/*     */       try {
/*     */       
/* 338 */       } catch (SAXException sAXException) {
/*     */         try {
/* 340 */           XMLReader parser = XMLReaderFactory.createXMLReader(package$.MODULE$.XercesClassName());
/* 345 */           parser.setContentHandler(this);
/* 346 */           EntityResolver resolver = getEntityResolver();
/* 347 */           if (resolver != null)
/* 348 */             parser.setEntityResolver(resolver); 
/* 351 */           int previousLevel = level();
/* 352 */           level_$eq(0);
/* 353 */           if (bases().contains(source))
/* 354 */             throw new SAXException(
/* 355 */                 "Circular XInclude Reference", 
/* 356 */                 new CircularIncludeException((new StringBuilder()).append("Circular XInclude Reference to ").append(source).append(getLocation()).toString())); 
/* 359 */           bases().push(source);
/* 360 */           atRoot_$eq(true);
/* 361 */           parser.parse(source.toExternalForm());
/* 364 */           level_$eq(previousLevel);
/* 365 */           bases().pop();
/*     */           return;
/*     */         } catch (SAXException sAXException1) {
/*     */           System.err.println("Could not find an XML parser");
/*     */           return;
/*     */         } 
/*     */       } catch (IOException iOException) {
/* 369 */         throw new SAXException((new StringBuilder()).append("Document not found: ").append(source.toExternalForm()).append(getLocation()).toString(), iOException);
/*     */       } 
/*     */     } catch (MalformedURLException malformedURLException) {
/*     */       UnavailableResourceException ex = new UnavailableResourceException((new StringBuilder()).append("Unresolvable URL ").append(url).append(getLocation()).toString());
/*     */       ex.setRootCause(malformedURLException);
/*     */       throw new SAXException((new StringBuilder()).append("Unresolvable URL ").append(url).append(getLocation()).toString(), ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\XIncludeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */