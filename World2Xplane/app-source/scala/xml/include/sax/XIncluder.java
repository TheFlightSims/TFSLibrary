/*     */ package scala.xml.include.sax;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import scala.collection.mutable.Stack;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.xml.Utility$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005g\001B\001\003\001-\021\021\002W%oG2,H-\032:\013\005\r!\021aA:bq*\021QAB\001\bS:\034G.\0363f\025\t9\001\"A\002y[2T\021!C\001\006g\016\fG.Y\002\001'\021\001A\002\006\017\021\0055\021R\"\001\b\013\005=\001\022\001\0027b]\036T\021!E\001\005U\0064\030-\003\002\024\035\t1qJ\0316fGR\004\"!\006\016\016\003YQ!aA\f\013\005\035A\"\"A\r\002\007=\024x-\003\002\034-\tq1i\0348uK:$\b*\0318eY\026\024\bCA\017!\033\005q\"BA\020\027\003\r)\007\020^\005\003Cy\021a\002T3yS\016\fG\016S1oI2,'\017\003\005$\001\t\005\t\025!\003%\003\021yW\017^:\021\005\025BS\"\001\024\013\005\035\002\022AA5p\023\tIcE\001\007PkR\004X\017^*ue\026\fW\016\003\005,\001\t\005\t\025!\003-\003!)gnY8eS:<\007CA\0272\035\tqs&D\001\t\023\t\001\004\"\001\004Qe\026$WMZ\005\003eM\022aa\025;sS:<'B\001\031\t\021\025)\004\001\"\0017\003\031a\024N\\5u}Q\031q'\017\036\021\005a\002Q\"\001\002\t\013\r\"\004\031\001\023\t\013-\"\004\031\001\027\t\017q\002\001\031!C\001{\005\031q.\036;\026\003y\002\"!J \n\005\0013#AE(viB,Ho\025;sK\006lwK]5uKJDqA\021\001A\002\023\0051)A\004pkR|F%Z9\025\005\021;\005C\001\030F\023\t1\005B\001\003V]&$\bb\002%B\003\003\005\rAP\001\004q\022\n\004B\002&\001A\003&a(\001\003pkR\004\003\"\002'\001\t\003i\025AE:fi\022{7-^7f]RdunY1u_J$\"\001\022(\t\013=[\005\031\001)\002\0171|7-\031;peB\021Q#U\005\003%Z\021q\001T8dCR|'\017C\003U\001\021\005Q+A\007ti\006\024H\017R8dk6,g\016\036\013\002\t\")q\013\001C\001+\006YQM\0343E_\016,X.\0328u\021\025I\006\001\"\001[\003I\031H/\031:u!J,g-\033=NCB\004\030N\\4\025\007\021[V\fC\003]1\002\007A&\001\004qe\0264\027\016\037\005\006=b\003\r\001L\001\004kJL\007\"\0021\001\t\003\t\027\001E3oIB\023XMZ5y\033\006\004\b/\0338h)\t!%\rC\003]?\002\007A\006C\003e\001\021\005Q-\001\007ti\006\024H/\0227f[\026tG\017F\003EM\"TG\016C\003hG\002\007A&\001\007oC6,7\017]1dKV\023\026\nC\003jG\002\007A&A\005m_\016\fGNT1nK\")1n\031a\001Y\005i\021/^1mS\032LW\r\032(b[\026DQ!\\2A\0029\fA!\031;ugB\021Qc\\\005\003aZ\021!\"\021;ue&\024W\017^3t\021\025\021\b\001\"\001t\003))g\016Z#mK6,g\016\036\013\005\tR,h\017C\003hc\002\007A\006C\003jc\002\007A\006C\003lc\002\007A\006C\003y\001\021\005\0210\001\006dQ\006\024\030m\031;feN$b\001\022>\002\006\005=\001\"B>x\001\004a\030AA2i!\rqSp`\005\003}\"\021Q!\021:sCf\0042ALA\001\023\r\t\031\001\003\002\005\007\"\f'\017C\004\002\b]\004\r!!\003\002\013M$\030M\035;\021\0079\nY!C\002\002\016!\0211!\0238u\021\035\t\tb\036a\001\003\023\ta\001\\3oORD\007bBA\013\001\021\005\021qC\001\024S\036twN]1cY\026<\006.\033;fgB\f7-\032\013\b\t\006e\0211DA\017\021\031Y\0301\003a\001y\"A\021qAA\n\001\004\tI\001\003\005\002\022\005M\001\031AA\005\021\035\t\t\003\001C\001\003G\tQ\003\035:pG\026\0348/\0338h\023:\034HO];di&|g\016F\003E\003K\tI\003C\004\002(\005}\001\031\001\027\002\rQ\f'oZ3u\021\035\tY#a\bA\0021\nA\001Z1uC\"9\021q\006\001\005\002\005E\022!D:lSB\004X\rZ#oi&$\030\020F\002E\003gAq!!\016\002.\001\007A&\001\003oC6,\007\"CA\035\001\001\007I\021BA\036\003\025Ig\016\022+E+\t\ti\004E\002/\003I1!!\021\t\005\035\021un\0347fC:D\021\"!\022\001\001\004%I!a\022\002\023%tG\t\026#`I\025\fHc\001#\002J!I\001*a\021\002\002\003\007\021Q\b\005\t\003\033\002\001\025)\003\002>\0051\021N\034#U\t\002B\021\"!\025\001\005\004%I!a\025\002\021\025tG/\033;jKN,\"!!\026\021\013\005]\023\021\r\027\016\005\005e#\002BA.\003;\nq!\\;uC\ndWMC\002\002`!\t!bY8mY\026\034G/[8o\023\021\t\031'!\027\003\013M#\030mY6\t\021\005\035\004\001)A\005\003+\n\021\"\0328uSRLWm\035\021\t\017\005-\004\001\"\001\002n\005A1\017^1si\022#F\tF\004E\003_\n\t(!\036\t\017\005U\022\021\016a\001Y!9\0211OA5\001\004a\023\001\0039vE2L7-\023#\t\017\005]\024\021\016a\001Y\005A1/_:uK6LE\t\003\004\002|\001!\t!V\001\007K:$G\t\026#\t\017\005}\004\001\"\001\002\002\006Y1\017^1si\026sG/\033;z)\r!\0251\021\005\b\003k\ti\b1\001-\021\035\t9\t\001C\001\003\023\013\021\"\0328e\013:$\030\016^=\025\007\021\013Y\tC\004\0026\005\025\005\031\001\027\t\r\005=\005\001\"\001V\003)\031H/\031:u\007\022\013E+\021\005\007\003'\003A\021A+\002\021\025tGm\021#B)\006C\021\"a&\001\001\004%I!!'\002\r\031LG\016^3s+\t\tY\nE\0029\003;K1!a(\003\0059A\026J\\2mk\022,g)\0337uKJD\021\"a)\001\001\004%I!!*\002\025\031LG\016^3s?\022*\027\017F\002E\003OC\021\002SAQ\003\003\005\r!a'\t\021\005-\006\001)Q\005\0037\013qAZ5mi\026\024\b\005C\004\0020\002!\t!!-\002\023M,GOR5mi\026\024Hc\001#\0024\"A\021qSAW\001\004\tY\nC\004\0028\002!\t!!/\002\017\r|W.\\3oiR9A)a/\002>\006}\006BB>\0026\002\007A\020\003\005\002\b\005U\006\031AA\005\021!\t\t\"!.A\002\005%\001")
/*     */ public class XIncluder implements ContentHandler, LexicalHandler {
/*     */   private final String encoding;
/*     */   
/*     */   private OutputStreamWriter out;
/*     */   
/*     */   private boolean inDTD;
/*     */   
/*     */   private final Stack<String> entities;
/*     */   
/*     */   private XIncludeFilter filter;
/*     */   
/*     */   public XIncluder(OutputStream outs, String encoding) {
/*  26 */     this.out = new OutputStreamWriter(outs, encoding);
/* 135 */     this.inDTD = false;
/* 136 */     this.entities = new Stack();
/* 169 */     this.filter = null;
/*     */   }
/*     */   
/*     */   public OutputStreamWriter out() {
/*     */     return this.out;
/*     */   }
/*     */   
/*     */   public void out_$eq(OutputStreamWriter x$1) {
/*     */     this.out = x$1;
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {}
/*     */   
/*     */   public void startDocument() {
/*     */     try {
/*     */       out().write((new StringBuilder()).append("<?xml version='1.0' encoding='").append(this.encoding).append("'?>\r\n").toString());
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endDocument() {
/*     */     try {
/*     */       out().flush();
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Flush failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) {}
/*     */   
/*     */   public void endPrefixMapping(String prefix) {}
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes atts) {
/*     */     try {
/*     */       out().write((new StringBuilder()).append("<").append(qualifiedName).toString());
/*     */       int i;
/*     */       for (i = 0; i < atts.getLength(); ) {
/*     */         out().write(" ");
/*     */         out().write(atts.getQName(i));
/*     */         out().write("='");
/*     */         String value = atts.getValue(i);
/*     */         out().write(Utility$.MODULE$.escape(value));
/*     */         out().write("'");
/*     */         i++;
/*     */       } 
/*     */       out().write(">");
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qualifiedName) {
/*     */     try {
/*     */       out().write((new StringBuilder()).append("</").append(qualifiedName).append(">").toString());
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {
/*     */     try {
/*     */       int i;
/*     */       for (i = 0; i < length; ) {
/*     */         char c = ch[start + i];
/*     */         if (c == '&') {
/*     */           out().write("&amp;");
/*     */         } else if (c == '<') {
/*     */           out().write("&lt;");
/*     */         } else if (c == '>') {
/*     */           out().write("&gt;");
/*     */         } else {
/*     */           out().write(c);
/*     */         } 
/*     */         i++;
/*     */       } 
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) {
/*     */     characters(ch, start, length);
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) {
/*     */     try {
/*     */       out().write((new StringBuilder()).append("<?").append(target).append(" ").append(data).append("?>").toString());
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) {
/*     */     try {
/*     */       out().write((new StringBuilder()).append("&").append(name).append(";").toString());
/*     */       return;
/*     */     } catch (IOException iOException) {
/*     */       throw new SAXException("Write failed", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean inDTD() {
/*     */     return this.inDTD;
/*     */   }
/*     */   
/*     */   private void inDTD_$eq(boolean x$1) {
/*     */     this.inDTD = x$1;
/*     */   }
/*     */   
/*     */   private Stack<String> entities() {
/*     */     return this.entities;
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicID, String systemID) {
/*     */     inDTD_$eq(true);
/*     */     if (entities().isEmpty()) {
/*     */       String id = "";
/*     */       if (publicID == null) {
/*     */         if (systemID != null)
/*     */           id = (new StringBuilder()).append(" SYSTEM \"").append(systemID).append(BoxesRunTime.boxToCharacter('"')).toString(); 
/*     */       } else {
/*     */         id = (new StringBuilder()).append(" PUBLIC \"").append(publicID).append("\" \"").append(systemID).append(BoxesRunTime.boxToCharacter('"')).toString();
/*     */       } 
/*     */       try {
/*     */         out().write((new StringBuilder()).append("<!DOCTYPE ").append(name).append(id).append(">\r\n").toString());
/*     */         return;
/*     */       } catch (IOException iOException) {
/*     */         throw new SAXException("Error while writing DOCTYPE", iOException);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endDTD() {}
/*     */   
/*     */   public void startEntity(String name) {
/*     */     entities().push(name);
/*     */   }
/*     */   
/*     */   public void endEntity(String name) {
/*     */     entities().pop();
/*     */   }
/*     */   
/*     */   public void startCDATA() {}
/*     */   
/*     */   public void endCDATA() {}
/*     */   
/*     */   private XIncludeFilter filter() {
/* 169 */     return this.filter;
/*     */   }
/*     */   
/*     */   private void filter_$eq(XIncludeFilter x$1) {
/* 169 */     this.filter = x$1;
/*     */   }
/*     */   
/*     */   public void setFilter(XIncludeFilter filter) {
/* 172 */     filter_$eq(filter);
/*     */   }
/*     */   
/*     */   public void comment(char[] ch, int start, int length) {
/* 176 */     if (!inDTD() && !filter().insideIncludeElement())
/*     */       try {
/* 178 */         out().write("<!--");
/* 179 */         out().write(ch, start, length);
/* 180 */         out().write("-->");
/*     */         return;
/*     */       } catch (IOException iOException) {
/* 184 */         throw new SAXException("Write failed", iOException);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\XIncluder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */