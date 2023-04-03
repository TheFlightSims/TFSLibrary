/*    */ package scala.xml;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileDescriptor;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.io.Writer;
/*    */ import java.net.URL;
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import org.xml.sax.InputSource;
/*    */ import scala.Enumeration;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.xml.dtd.DocType;
/*    */ import scala.xml.factory.XMLLoader;
/*    */ import scala.xml.parsing.FactoryAdapter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}r!B\001\003\021\0039\021a\001-N\031*\0211\001B\001\004q6d'\"A\003\002\013M\034\027\r\\1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t\031\001,\024'\024\007%a\001\003\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032\0042!\005\013\027\033\005\021\"BA\n\003\003\0351\027m\031;pefL!!\006\n\003\023akE\nT8bI\026\024\bC\001\005\030\023\tA\"A\001\003FY\026l\007\"\002\016\n\t\003Y\022A\002\037j]&$h\bF\001\b\021\035\031\021B1A\005\002u)\022A\b\t\003?\021j\021\001\t\006\003C\t\nA\001\\1oO*\t1%\001\003kCZ\f\027BA\023!\005\031\031FO]5oO\"1q%\003Q\001\ny\tA\001_7mA!9\021&\003b\001\n\003i\022!\002=nY:\034\bBB\026\nA\003%a$\001\004y[2t7\017\t\005\b[%\021\r\021\"\001\036\003%q\027-\\3ta\006\034W\r\003\0040\023\001\006IAH\001\013]\006lWm\0359bG\026\004\003bB\031\n\005\004%\t!H\001\taJ,7/\032:wK\"11'\003Q\001\ny\t\021\002\035:fg\026\024h/\032\021\t\017UJ!\031!C\001;\005)1\017]1dK\"1q'\003Q\001\ny\taa\0359bG\026\004\003bB\021\n\005\004%\t!\b\005\007u%\001\013\021\002\020\002\0131\fgn\032\021\t\017qJ!\031!C\001;\005AQM\\2pI&tw\r\003\004?\023\001\006IAH\001\nK:\034w\016Z5oO\002BQ\001Q\005\005\002\005\013Qb^5uQN\013\005\fU1sg\026\024HC\001\tC\021\025\031u\b1\001E\003\005\001\bCA#I\035\tAa)\003\002H\005\0059\001/Y2lC\036,\027BA%K\005%\031\026\t\027)beN,'O\003\002H\005!)A*\003C\003\033\006!1/\031<f)\031q\025+\0270aKB\021QbT\005\003!\022\021A!\0268ji\")!k\023a\001'\006Aa-\0337f]\006lW\r\005\002U/:\021Q\"V\005\003-\022\ta\001\025:fI\0264\027BA\023Y\025\t1F\001C\003[\027\002\0071,\001\003o_\022,\007C\001\005]\023\ti&A\001\003O_\022,\007bB0L!\003\005\raU\001\004K:\034\007bB1L!\003\005\rAY\001\bq6dG)Z2m!\ti1-\003\002e\t\t9!i\\8mK\006t\007b\0024L!\003\005\raZ\001\bI>\034G/\0379f!\tA7.D\001j\025\tQ'!A\002ei\022L!\001\\5\003\017\021{7\rV=qK\")a.\003C\003_\006)qO]5uKR9a\n\035=zund\b\"B9n\001\004\021\030!A<\021\005M4X\"\001;\013\005U\024\023AA5p\023\t9HO\001\004Xe&$XM\035\005\00656\004\ra\027\005\006?6\004\ra\025\005\006C6\004\rA\031\005\006M6\004\ra\032\005\b{6\004\n\0211\001\0031i\027N\\5nSj,G+Y4t!\ry\030Q\001\b\004\021\005\005\021bAA\002\005\005aQ*\0338j[&TX-T8eK&!\021qAA\005\005\0251\026\r\\;f\023\r\tY\001\002\002\f\013:,X.\032:bi&|g\016C\005\002\020%\t\n\021\"\002\002\022\005q1/\031<fI\021,g-Y;mi\022\032TCAA\nU\r\031\026QC\026\003\003/\001B!!\007\002$5\021\0211\004\006\005\003;\ty\"A\005v]\016DWmY6fI*\031\021\021\005\003\002\025\005tgn\034;bi&|g.\003\003\002&\005m!!E;oG\",7m[3e-\006\024\030.\0318dK\"I\021\021F\005\022\002\023\025\0211F\001\017g\0064X\r\n3fM\006,H\016\036\0235+\t\tiCK\002c\003+A\021\"!\r\n#\003%)!a\r\002\035M\fg/\032\023eK\032\fW\017\034;%kU\021\021Q\007\026\004O\006U\001\"CA\035\023E\005IQAA\036\003=9(/\033;fI\021,g-Y;mi\0222TCAA\037U\rq\030Q\003")
/*    */ public final class XML {
/*    */   public static Node loadString(String paramString) {
/*    */     return XML$.MODULE$.loadString(paramString);
/*    */   }
/*    */   
/*    */   public static Node load(URL paramURL) {
/*    */     return XML$.MODULE$.load(paramURL);
/*    */   }
/*    */   
/*    */   public static Node load(InputSource paramInputSource) {
/*    */     return XML$.MODULE$.load(paramInputSource);
/*    */   }
/*    */   
/*    */   public static Node load(String paramString) {
/*    */     return XML$.MODULE$.load(paramString);
/*    */   }
/*    */   
/*    */   public static Node load(Reader paramReader) {
/*    */     return XML$.MODULE$.load(paramReader);
/*    */   }
/*    */   
/*    */   public static Node load(InputStream paramInputStream) {
/*    */     return XML$.MODULE$.load(paramInputStream);
/*    */   }
/*    */   
/*    */   public static Node loadFile(String paramString) {
/*    */     return XML$.MODULE$.loadFile(paramString);
/*    */   }
/*    */   
/*    */   public static Node loadFile(FileDescriptor paramFileDescriptor) {
/*    */     return XML$.MODULE$.loadFile(paramFileDescriptor);
/*    */   }
/*    */   
/*    */   public static Node loadFile(File paramFile) {
/*    */     return XML$.MODULE$.loadFile(paramFile);
/*    */   }
/*    */   
/*    */   public static Node loadXML(InputSource paramInputSource, SAXParser paramSAXParser) {
/*    */     return XML$.MODULE$.loadXML(paramInputSource, paramSAXParser);
/*    */   }
/*    */   
/*    */   public static SAXParser parser() {
/*    */     return XML$.MODULE$.parser();
/*    */   }
/*    */   
/*    */   public static FactoryAdapter adapter() {
/*    */     return XML$.MODULE$.adapter();
/*    */   }
/*    */   
/*    */   public static Enumeration.Value write$default$6() {
/*    */     return XML$.MODULE$.write$default$6();
/*    */   }
/*    */   
/*    */   public static DocType save$default$5() {
/*    */     return XML$.MODULE$.save$default$5();
/*    */   }
/*    */   
/*    */   public static boolean save$default$4() {
/*    */     return XML$.MODULE$.save$default$4();
/*    */   }
/*    */   
/*    */   public static String save$default$3() {
/*    */     return XML$.MODULE$.save$default$3();
/*    */   }
/*    */   
/*    */   public static void write(Writer paramWriter, Node paramNode, String paramString, boolean paramBoolean, DocType paramDocType, Enumeration.Value paramValue) {
/*    */     XML$.MODULE$.write(paramWriter, paramNode, paramString, paramBoolean, paramDocType, paramValue);
/*    */   }
/*    */   
/*    */   public static void save(String paramString1, Node paramNode, String paramString2, boolean paramBoolean, DocType paramDocType) {
/*    */     XML$.MODULE$.save(paramString1, paramNode, paramString2, paramBoolean, paramDocType);
/*    */   }
/*    */   
/*    */   public static XMLLoader<Elem> withSAXParser(SAXParser paramSAXParser) {
/*    */     return XML$.MODULE$.withSAXParser(paramSAXParser);
/*    */   }
/*    */   
/*    */   public static String encoding() {
/*    */     return XML$.MODULE$.encoding();
/*    */   }
/*    */   
/*    */   public static String lang() {
/*    */     return XML$.MODULE$.lang();
/*    */   }
/*    */   
/*    */   public static String space() {
/*    */     return XML$.MODULE$.space();
/*    */   }
/*    */   
/*    */   public static String preserve() {
/*    */     return XML$.MODULE$.preserve();
/*    */   }
/*    */   
/*    */   public static String namespace() {
/*    */     return XML$.MODULE$.namespace();
/*    */   }
/*    */   
/*    */   public static String xmlns() {
/*    */     return XML$.MODULE$.xmlns();
/*    */   }
/*    */   
/*    */   public static String xml() {
/*    */     return XML$.MODULE$.xml();
/*    */   }
/*    */   
/*    */   public static class XML$$anon$1 implements XMLLoader<Elem> {
/*    */     private final SAXParser parser;
/*    */     
/*    */     public FactoryAdapter adapter() {
/* 68 */       return XMLLoader.class.adapter(this);
/*    */     }
/*    */     
/*    */     public Node loadXML(InputSource source, SAXParser parser) {
/* 68 */       return XMLLoader.class.loadXML(this, source, parser);
/*    */     }
/*    */     
/*    */     public Node loadFile(File file) {
/* 68 */       return XMLLoader.class.loadFile(this, file);
/*    */     }
/*    */     
/*    */     public Node loadFile(FileDescriptor fd) {
/* 68 */       return XMLLoader.class.loadFile(this, fd);
/*    */     }
/*    */     
/*    */     public Node loadFile(String name) {
/* 68 */       return XMLLoader.class.loadFile(this, name);
/*    */     }
/*    */     
/*    */     public Node load(InputStream is) {
/* 68 */       return XMLLoader.class.load(this, is);
/*    */     }
/*    */     
/*    */     public Node load(Reader reader) {
/* 68 */       return XMLLoader.class.load(this, reader);
/*    */     }
/*    */     
/*    */     public Node load(String sysID) {
/* 68 */       return XMLLoader.class.load(this, sysID);
/*    */     }
/*    */     
/*    */     public Node load(InputSource source) {
/* 68 */       return XMLLoader.class.load(this, source);
/*    */     }
/*    */     
/*    */     public Node load(URL url) {
/* 68 */       return XMLLoader.class.load(this, url);
/*    */     }
/*    */     
/*    */     public Node loadString(String string) {
/* 68 */       return XMLLoader.class.loadString(this, string);
/*    */     }
/*    */     
/*    */     public SAXParser parser() {
/* 68 */       return this.parser;
/*    */     }
/*    */     
/*    */     public XML$$anon$1(SAXParser p$1) {
/* 68 */       XMLLoader.class.$init$(this);
/* 68 */       this.parser = p$1;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class XML$$anonfun$save$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Writer w$1;
/*    */     
/*    */     public final void apply() {
/* 90 */       this.w$1.close();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 90 */       this.w$1.close();
/*    */     }
/*    */     
/*    */     public XML$$anonfun$save$1(Writer w$1) {}
/*    */   }
/*    */   
/*    */   public static class XML$$anonfun$save$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Node node$1;
/*    */     
/*    */     private final String enc$1;
/*    */     
/*    */     private final boolean xmlDecl$1;
/*    */     
/*    */     private final DocType doctype$1;
/*    */     
/*    */     private final Writer w$1;
/*    */     
/*    */     public final void apply() {
/* 91 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 91 */       XML$.MODULE$.write(this.w$1, this.node$1, this.enc$1, this.xmlDecl$1, this.doctype$1, XML$.MODULE$.write$default$6());
/*    */     }
/*    */     
/*    */     public XML$$anonfun$save$2(Node node$1, String enc$1, boolean xmlDecl$1, DocType doctype$1, Writer w$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\XML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */