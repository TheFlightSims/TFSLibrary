/*     */ package scala.xml;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.nio.channels.Channels;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import org.xml.sax.InputSource;
/*     */ import scala.Enumeration;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.xml.dtd.DocType;
/*     */ import scala.xml.factory.XMLLoader;
/*     */ import scala.xml.parsing.FactoryAdapter;
/*     */ 
/*     */ public final class XML$ implements XMLLoader<Elem> {
/*     */   public static final XML$ MODULE$;
/*     */   
/*     */   private final String xml;
/*     */   
/*     */   private final String xmlns;
/*     */   
/*     */   private final String namespace;
/*     */   
/*     */   private final String preserve;
/*     */   
/*     */   private final String space;
/*     */   
/*     */   private final String lang;
/*     */   
/*     */   private final String encoding;
/*     */   
/*     */   public FactoryAdapter adapter() {
/*  57 */     return XMLLoader.class.adapter(this);
/*     */   }
/*     */   
/*     */   public SAXParser parser() {
/*  57 */     return XMLLoader.class.parser(this);
/*     */   }
/*     */   
/*     */   public Node loadXML(InputSource source, SAXParser parser) {
/*  57 */     return XMLLoader.class.loadXML(this, source, parser);
/*     */   }
/*     */   
/*     */   public Node loadFile(File file) {
/*  57 */     return XMLLoader.class.loadFile(this, file);
/*     */   }
/*     */   
/*     */   public Node loadFile(FileDescriptor fd) {
/*  57 */     return XMLLoader.class.loadFile(this, fd);
/*     */   }
/*     */   
/*     */   public Node loadFile(String name) {
/*  57 */     return XMLLoader.class.loadFile(this, name);
/*     */   }
/*     */   
/*     */   public Node load(InputStream is) {
/*  57 */     return XMLLoader.class.load(this, is);
/*     */   }
/*     */   
/*     */   public Node load(Reader reader) {
/*  57 */     return XMLLoader.class.load(this, reader);
/*     */   }
/*     */   
/*     */   public Node load(String sysID) {
/*  57 */     return XMLLoader.class.load(this, sysID);
/*     */   }
/*     */   
/*     */   public Node load(InputSource source) {
/*  57 */     return XMLLoader.class.load(this, source);
/*     */   }
/*     */   
/*     */   public Node load(URL url) {
/*  57 */     return XMLLoader.class.load(this, url);
/*     */   }
/*     */   
/*     */   public Node loadString(String string) {
/*  57 */     return XMLLoader.class.loadString(this, string);
/*     */   }
/*     */   
/*     */   private XML$() {
/*  57 */     MODULE$ = this;
/*  57 */     XMLLoader.class.$init$(this);
/*  58 */     this.xml = "xml";
/*  59 */     this.xmlns = "xmlns";
/*  60 */     this.namespace = "http://www.w3.org/XML/1998/namespace";
/*  61 */     this.preserve = "preserve";
/*  62 */     this.space = "space";
/*  63 */     this.lang = "lang";
/*  64 */     this.encoding = "ISO-8859-1";
/*     */   }
/*     */   
/*     */   public String xml() {
/*     */     return this.xml;
/*     */   }
/*     */   
/*     */   public String xmlns() {
/*     */     return this.xmlns;
/*     */   }
/*     */   
/*     */   public String namespace() {
/*     */     return this.namespace;
/*     */   }
/*     */   
/*     */   public String preserve() {
/*     */     return this.preserve;
/*     */   }
/*     */   
/*     */   public String space() {
/*     */     return this.space;
/*     */   }
/*     */   
/*     */   public String lang() {
/*     */     return this.lang;
/*     */   }
/*     */   
/*     */   public String encoding() {
/*  64 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public XMLLoader<Elem> withSAXParser(SAXParser p) {
/*  68 */     return new XML$$anon$1(p);
/*     */   }
/*     */   
/*     */   public static class XML$$anon$1 implements XMLLoader<Elem> {
/*     */     private final SAXParser parser;
/*     */     
/*     */     public FactoryAdapter adapter() {
/*  68 */       return XMLLoader.class.adapter(this);
/*     */     }
/*     */     
/*     */     public Node loadXML(InputSource source, SAXParser parser) {
/*  68 */       return XMLLoader.class.loadXML(this, source, parser);
/*     */     }
/*     */     
/*     */     public Node loadFile(File file) {
/*  68 */       return XMLLoader.class.loadFile(this, file);
/*     */     }
/*     */     
/*     */     public Node loadFile(FileDescriptor fd) {
/*  68 */       return XMLLoader.class.loadFile(this, fd);
/*     */     }
/*     */     
/*     */     public Node loadFile(String name) {
/*  68 */       return XMLLoader.class.loadFile(this, name);
/*     */     }
/*     */     
/*     */     public Node load(InputStream is) {
/*  68 */       return XMLLoader.class.load(this, is);
/*     */     }
/*     */     
/*     */     public Node load(Reader reader) {
/*  68 */       return XMLLoader.class.load(this, reader);
/*     */     }
/*     */     
/*     */     public Node load(String sysID) {
/*  68 */       return XMLLoader.class.load(this, sysID);
/*     */     }
/*     */     
/*     */     public Node load(InputSource source) {
/*  68 */       return XMLLoader.class.load(this, source);
/*     */     }
/*     */     
/*     */     public Node load(URL url) {
/*  68 */       return XMLLoader.class.load(this, url);
/*     */     }
/*     */     
/*     */     public Node loadString(String string) {
/*  68 */       return XMLLoader.class.loadString(this, string);
/*     */     }
/*     */     
/*     */     public SAXParser parser() {
/*  68 */       return this.parser;
/*     */     }
/*     */     
/*     */     public XML$$anon$1(SAXParser p$1) {
/*  68 */       XMLLoader.class.$init$(this);
/*  68 */       this.parser = p$1;
/*     */     }
/*     */   }
/*     */   
/*     */   public final String save$default$3() {
/*  82 */     return encoding();
/*     */   }
/*     */   
/*     */   public final boolean save$default$4() {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */   public final void save(String filename, Node node, String enc, boolean xmlDecl, DocType doctype) {
/*  87 */     FileOutputStream fos = new FileOutputStream(filename);
/*  88 */     Writer w = Channels.newWriter(fos.getChannel(), enc);
/*  90 */     XML$$anonfun$save$1 xML$$anonfun$save$1 = new XML$$anonfun$save$1(w);
/*  90 */     scala.util.control.Exception$.MODULE$.noCatch().andFinally((Function0)xML$$anonfun$save$1).apply(
/*  91 */         (Function0)new XML$$anonfun$save$2(node, enc, xmlDecl, doctype, w));
/*     */   }
/*     */   
/*     */   public static class XML$$anonfun$save$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Writer w$1;
/*     */     
/*     */     public final void apply() {
/*     */       this.w$1.close();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.w$1.close();
/*     */     }
/*     */     
/*     */     public XML$$anonfun$save$1(Writer w$1) {}
/*     */   }
/*     */   
/*     */   public static class XML$$anonfun$save$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node node$1;
/*     */     
/*     */     private final String enc$1;
/*     */     
/*     */     private final boolean xmlDecl$1;
/*     */     
/*     */     private final DocType doctype$1;
/*     */     
/*     */     private final Writer w$1;
/*     */     
/*     */     public final void apply() {
/*  91 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*  91 */       XML$.MODULE$.write(this.w$1, this.node$1, this.enc$1, this.xmlDecl$1, this.doctype$1, XML$.MODULE$.write$default$6());
/*     */     }
/*     */     
/*     */     public XML$$anonfun$save$2(Node node$1, String enc$1, boolean xmlDecl$1, DocType doctype$1, Writer w$1) {}
/*     */   }
/*     */   
/*     */   public final Enumeration.Value write$default$6() {
/* 104 */     return MinimizeMode$.MODULE$.Default();
/*     */   }
/*     */   
/*     */   public final void write(Writer w, Node node, String enc, boolean xmlDecl, DocType doctype, Enumeration.Value minimizeTags) {
/* 106 */     if (xmlDecl)
/* 106 */       w.write((new StringBuilder()).append("<?xml version='1.0' encoding='").append(enc).append("'?>\n").toString()); 
/* 107 */     if (doctype != null)
/* 107 */       w.write((new StringBuilder()).append(doctype.toString()).append("\n").toString()); 
/* 108 */     NamespaceBinding x$3 = Utility$.MODULE$.serialize$default$2();
/* 108 */     StringBuilder x$4 = Utility$.MODULE$.serialize$default$3();
/* 108 */     boolean x$5 = Utility$.MODULE$.serialize$default$4(), x$6 = Utility$.MODULE$.serialize$default$5(), x$7 = Utility$.MODULE$.serialize$default$6();
/* 108 */     w.write(Utility$.MODULE$.serialize(node, x$3, x$4, x$5, x$6, x$7, minimizeTags).toString());
/*     */   }
/*     */   
/*     */   public final DocType save$default$5() {
/*     */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\XML$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */