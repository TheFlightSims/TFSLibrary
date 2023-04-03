/*     */ package org.jdom.transform;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.output.SAXOutputter;
/*     */ import org.jdom.output.XMLOutputter;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLFilter;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ public class JDOMSource extends SAXSource {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JDOMSource.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:02 $ $Name:  $";
/*     */   
/*     */   public static final String JDOM_FEATURE = "http://org.jdom.transform.JDOMSource/feature";
/*     */   
/* 126 */   private XMLReader xmlReader = null;
/*     */   
/* 135 */   private EntityResolver resolver = null;
/*     */   
/*     */   public JDOMSource(Document source) {
/* 147 */     setDocument(source);
/*     */   }
/*     */   
/*     */   public JDOMSource(List source) {
/* 160 */     setNodes(source);
/*     */   }
/*     */   
/*     */   public JDOMSource(Element source) {
/* 173 */     List nodes = new ArrayList();
/* 174 */     nodes.add(source);
/* 176 */     setNodes(nodes);
/*     */   }
/*     */   
/*     */   public JDOMSource(Document source, EntityResolver resolver) {
/* 193 */     setDocument(source);
/* 194 */     this.resolver = resolver;
/*     */   }
/*     */   
/*     */   public void setDocument(Document source) {
/* 209 */     super.setInputSource(new JDOMInputSource(source));
/*     */   }
/*     */   
/*     */   public Document getDocument() {
/* 221 */     Object src = ((JDOMInputSource)getInputSource()).getSource();
/* 222 */     Document doc = null;
/* 224 */     if (src instanceof Document)
/* 225 */       doc = (Document)src; 
/* 227 */     return doc;
/*     */   }
/*     */   
/*     */   public void setNodes(List source) {
/* 242 */     super.setInputSource(new JDOMInputSource(source));
/*     */   }
/*     */   
/*     */   public List getNodes() {
/* 254 */     Object src = ((JDOMInputSource)getInputSource()).getSource();
/* 255 */     List nodes = null;
/* 257 */     if (src instanceof List)
/* 258 */       nodes = (List)src; 
/* 260 */     return nodes;
/*     */   }
/*     */   
/*     */   public void setInputSource(InputSource inputSource) throws UnsupportedOperationException {
/* 282 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setXMLReader(XMLReader reader) throws UnsupportedOperationException {
/* 304 */     if (reader instanceof XMLFilter) {
/* 306 */       XMLFilter filter = (XMLFilter)reader;
/* 307 */       while (filter.getParent() instanceof XMLFilter)
/* 308 */         filter = (XMLFilter)filter.getParent(); 
/* 310 */       filter.setParent(buildDocumentReader());
/* 313 */       this.xmlReader = reader;
/*     */     } else {
/* 316 */       throw new UnsupportedOperationException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLReader getXMLReader() {
/* 331 */     if (this.xmlReader == null)
/* 332 */       this.xmlReader = buildDocumentReader(); 
/* 334 */     return this.xmlReader;
/*     */   }
/*     */   
/*     */   private XMLReader buildDocumentReader() {
/* 346 */     DocumentReader reader = new DocumentReader();
/* 347 */     if (this.resolver != null)
/* 348 */       reader.setEntityResolver(this.resolver); 
/* 349 */     return reader;
/*     */   }
/*     */   
/*     */   private static class JDOMInputSource extends InputSource {
/* 370 */     private Object source = null;
/*     */     
/*     */     public JDOMInputSource(Document document) {
/* 378 */       this.source = document;
/*     */     }
/*     */     
/*     */     public JDOMInputSource(List nodes) {
/* 387 */       this.source = nodes;
/*     */     }
/*     */     
/*     */     public Object getSource() {
/* 396 */       return this.source;
/*     */     }
/*     */     
/*     */     public void setCharacterStream(Reader characterStream) throws UnsupportedOperationException {
/* 418 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Reader getCharacterStream() {
/* 435 */       Object src = getSource();
/* 436 */       Reader reader = null;
/* 438 */       if (src instanceof Document) {
/* 441 */         reader = new StringReader((new XMLOutputter()).outputString((Document)src));
/* 445 */       } else if (src instanceof List) {
/* 446 */         reader = new StringReader((new XMLOutputter()).outputString((List)src));
/*     */       } 
/* 451 */       return reader;
/*     */     }
/*     */     
/*     */     public void setByteStream(InputStream byteStream) throws UnsupportedOperationException {
/* 468 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class DocumentReader extends SAXOutputter implements XMLReader {
/*     */     public void parse(String systemId) throws SAXNotSupportedException {
/* 511 */       throw new SAXNotSupportedException("Only JDOM Documents are supported as input");
/*     */     }
/*     */     
/*     */     public void parse(InputSource input) throws SAXException {
/* 532 */       if (input instanceof JDOMSource.JDOMInputSource) {
/*     */         try {
/* 534 */           Object source = ((JDOMSource.JDOMInputSource)input).getSource();
/* 535 */           if (source instanceof Document) {
/* 536 */             output((Document)source);
/*     */           } else {
/* 539 */             output((List)source);
/*     */           } 
/* 542 */         } catch (JDOMException e) {
/* 543 */           throw new SAXException(e.getMessage(), e);
/*     */         } 
/*     */       } else {
/* 547 */         throw new SAXNotSupportedException("Only JDOM Documents are supported as input");
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\transform\JDOMSource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */