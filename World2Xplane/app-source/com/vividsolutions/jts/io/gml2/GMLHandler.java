/*     */ package com.vividsolutions.jts.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class GMLHandler extends DefaultHandler {
/*     */   static class Handler {
/*  75 */     protected Attributes attrs = null;
/*     */     
/*     */     protected GeometryStrategies.ParseStrategy strategy;
/*     */     
/*     */     protected StringBuffer text;
/*     */     
/*     */     protected List children;
/*     */     
/*     */     public Handler(GeometryStrategies.ParseStrategy strategy, Attributes attributes) {
/*  89 */       this.text = null;
/* 101 */       this.children = null;
/*     */       if (attributes != null)
/*     */         this.attrs = new AttributesImpl(attributes); 
/*     */       this.strategy = strategy;
/*     */     }
/*     */     
/*     */     public void addText(String str) {
/*     */       if (this.text == null)
/*     */         this.text = new StringBuffer(); 
/*     */       this.text.append(str);
/*     */     }
/*     */     
/*     */     public void keep(Object obj) {
/* 109 */       if (this.children == null)
/* 110 */         this.children = new LinkedList(); 
/* 111 */       this.children.add(obj);
/*     */     }
/*     */     
/*     */     public Object create(GeometryFactory gf) throws SAXException {
/* 121 */       return this.strategy.parse(this, gf);
/*     */     }
/*     */   }
/*     */   
/* 125 */   private Stack stack = new Stack();
/*     */   
/* 127 */   private ErrorHandler delegate = null;
/*     */   
/* 129 */   private GeometryFactory gf = null;
/*     */   
/*     */   private Locator locator;
/*     */   
/*     */   public boolean isGeometryComplete() {
/* 161 */     if (this.stack.size() > 1)
/* 162 */       return false; 
/* 164 */     Handler h = this.stack.peek();
/* 165 */     if (h.children.size() < 1)
/* 166 */       return false; 
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 179 */     if (this.stack.size() == 1) {
/* 180 */       Handler h = this.stack.peek();
/* 181 */       if (h.children.size() == 1)
/* 182 */         return h.children.get(0); 
/* 183 */       return (Geometry)this.gf.createGeometryCollection((Geometry[])h.children.toArray((Object[])new Geometry[this.stack.size()]));
/*     */     } 
/* 186 */     throw new IllegalStateException("Parse did not complete as expected, there are " + this.stack.size() + " elements on the Stack");
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 198 */     if (!this.stack.isEmpty())
/* 199 */       ((Handler)this.stack.peek()).addText(new String(ch, start, length)); 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 207 */     if (!this.stack.isEmpty())
/* 208 */       ((Handler)this.stack.peek()).addText(" "); 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 216 */     Handler thisAction = this.stack.pop();
/* 217 */     ((Handler)this.stack.peek()).keep(thisAction.create(this.gf));
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 226 */     GeometryStrategies.ParseStrategy ps = GeometryStrategies.findStrategy(uri, localName);
/* 227 */     if (ps == null) {
/* 228 */       String qn = qName.substring(qName.indexOf(':') + 1, qName.length());
/* 229 */       ps = GeometryStrategies.findStrategy(null, qn);
/*     */     } 
/* 231 */     Handler h = new Handler(ps, attributes);
/* 233 */     this.stack.push(h);
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/* 243 */     this.locator = locator;
/* 244 */     if (this.delegate != null && this.delegate instanceof ContentHandler)
/* 245 */       ((ContentHandler)this.delegate).setDocumentLocator(locator); 
/*     */   }
/*     */   
/*     */   public GMLHandler(GeometryFactory gf, ErrorHandler delegate) {
/* 249 */     this.locator = null;
/*     */     this.delegate = delegate;
/*     */     this.gf = gf;
/*     */     this.stack.push(new Handler(null, null));
/*     */   }
/*     */   
/*     */   protected Locator getDocumentLocator() {
/* 252 */     return this.locator;
/*     */   }
/*     */   
/*     */   public void fatalError(SAXParseException e) throws SAXException {
/* 262 */     if (this.delegate != null) {
/* 263 */       this.delegate.fatalError(e);
/*     */     } else {
/* 265 */       super.fatalError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void error(SAXParseException e) throws SAXException {
/* 272 */     if (this.delegate != null) {
/* 273 */       this.delegate.error(e);
/*     */     } else {
/* 275 */       super.error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void warning(SAXParseException e) throws SAXException {
/* 282 */     if (this.delegate != null) {
/* 283 */       this.delegate.warning(e);
/*     */     } else {
/* 285 */       super.warning(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\gml2\GMLHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */