/*    */ package scala.xml.factory;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileDescriptor;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import org.xml.sax.InputSource;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.Source$;
/*    */ import scala.xml.TopScope$;
/*    */ import scala.xml.parsing.FactoryAdapter;
/*    */ import scala.xml.parsing.NoBindingFactoryAdapter;
/*    */ 
/*    */ public abstract class XMLLoader$class {
/*    */   public static void $init$(XMLLoader $this) {}
/*    */   
/*    */   public static FactoryAdapter adapter(XMLLoader $this) {
/* 24 */     return (FactoryAdapter)new NoBindingFactoryAdapter();
/*    */   }
/*    */   
/*    */   public static SAXParser parser(XMLLoader $this) {
/* 28 */     SAXParserFactory f = SAXParserFactory.newInstance();
/* 29 */     f.setNamespaceAware(false);
/* 30 */     return f.newSAXParser();
/*    */   }
/*    */   
/*    */   public static Node loadXML(XMLLoader $this, InputSource source, SAXParser parser) {
/* 37 */     FactoryAdapter newAdapter = $this.adapter();
/* 39 */     newAdapter.scopeStack().push(TopScope$.MODULE$);
/* 40 */     parser.parse(source, (DefaultHandler)newAdapter);
/* 41 */     newAdapter.scopeStack().pop();
/* 43 */     return newAdapter.rootElem();
/*    */   }
/*    */   
/*    */   public static Node loadFile(XMLLoader<Node> $this, File file) {
/* 47 */     return $this.loadXML(Source$.MODULE$.fromFile(file), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node loadFile(XMLLoader<Node> $this, FileDescriptor fd) {
/* 48 */     return $this.loadXML(Source$.MODULE$.fromFile(fd), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node loadFile(XMLLoader<Node> $this, String name) {
/* 49 */     return $this.loadXML(Source$.MODULE$.fromFile(name), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node load(XMLLoader<Node> $this, InputStream is) {
/* 52 */     return $this.loadXML(Source$.MODULE$.fromInputStream(is), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node load(XMLLoader<Node> $this, Reader reader) {
/* 53 */     return $this.loadXML(Source$.MODULE$.fromReader(reader), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node load(XMLLoader<Node> $this, String sysID) {
/* 54 */     return $this.loadXML(Source$.MODULE$.fromSysId(sysID), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node load(XMLLoader<Node> $this, InputSource source) {
/* 55 */     return $this.loadXML(source, $this.parser());
/*    */   }
/*    */   
/*    */   public static Node load(XMLLoader<Node> $this, URL url) {
/* 56 */     return $this.loadXML(Source$.MODULE$.fromInputStream(url.openStream()), $this.parser());
/*    */   }
/*    */   
/*    */   public static Node loadString(XMLLoader<Node> $this, String string) {
/* 59 */     return $this.loadXML(Source$.MODULE$.fromString(string), $this.parser());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\XMLLoader$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */