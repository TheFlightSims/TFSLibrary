/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.Comment;
/*     */ import javax.xml.stream.events.DTD;
/*     */ import javax.xml.stream.events.EndDocument;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.EntityDeclaration;
/*     */ import javax.xml.stream.events.EntityReference;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import javax.xml.stream.events.ProcessingInstruction;
/*     */ import javax.xml.stream.events.StartDocument;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.evt.DTD2;
/*     */ import org.codehaus.stax2.evt.XMLEventFactory2;
/*     */ import org.codehaus.stax2.ri.evt.AttributeEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.CharactersEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.CommentEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.DTDEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.EndDocumentEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.EndElementEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.EntityReferenceEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.NamespaceEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.ProcInstrEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.StartDocumentEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.StartElementEventImpl;
/*     */ 
/*     */ public abstract class Stax2EventFactoryImpl extends XMLEventFactory2 {
/*     */   protected Location mLocation;
/*     */   
/*     */   public Attribute createAttribute(QName name, String value) {
/*  40 */     return (Attribute)new AttributeEventImpl(this.mLocation, name, value, true);
/*     */   }
/*     */   
/*     */   public Attribute createAttribute(String localName, String value) {
/*  44 */     return (Attribute)new AttributeEventImpl(this.mLocation, localName, null, null, value, true);
/*     */   }
/*     */   
/*     */   public Attribute createAttribute(String prefix, String nsURI, String localName, String value) {
/*  50 */     return (Attribute)new AttributeEventImpl(this.mLocation, localName, nsURI, prefix, value, true);
/*     */   }
/*     */   
/*     */   public Characters createCData(String content) {
/*  54 */     return (Characters)new CharactersEventImpl(this.mLocation, content, true);
/*     */   }
/*     */   
/*     */   public Characters createCharacters(String content) {
/*  58 */     return (Characters)new CharactersEventImpl(this.mLocation, content, false);
/*     */   }
/*     */   
/*     */   public Comment createComment(String text) {
/*  62 */     return (Comment)new CommentEventImpl(this.mLocation, text);
/*     */   }
/*     */   
/*     */   public DTD createDTD(String dtd) {
/*  71 */     return (DTD)new DTDEventImpl(this.mLocation, dtd);
/*     */   }
/*     */   
/*     */   public EndDocument createEndDocument() {
/*  75 */     return (EndDocument)new EndDocumentEventImpl(this.mLocation);
/*     */   }
/*     */   
/*     */   public EndElement createEndElement(QName name, Iterator namespaces) {
/*  79 */     return (EndElement)new EndElementEventImpl(this.mLocation, name, namespaces);
/*     */   }
/*     */   
/*     */   public EndElement createEndElement(String prefix, String nsURI, String localName) {
/*  85 */     return createEndElement(createQName(nsURI, localName), null);
/*     */   }
/*     */   
/*     */   public EndElement createEndElement(String prefix, String nsURI, String localName, Iterator ns) {
/*  91 */     return createEndElement(createQName(nsURI, localName, prefix), ns);
/*     */   }
/*     */   
/*     */   public EntityReference createEntityReference(String name, EntityDeclaration decl) {
/*  96 */     return (EntityReference)new EntityReferenceEventImpl(this.mLocation, decl);
/*     */   }
/*     */   
/*     */   public Characters createIgnorableSpace(String content) {
/* 100 */     return (Characters)CharactersEventImpl.createIgnorableWS(this.mLocation, content);
/*     */   }
/*     */   
/*     */   public Namespace createNamespace(String nsURI) {
/* 104 */     return (Namespace)NamespaceEventImpl.constructDefaultNamespace(this.mLocation, nsURI);
/*     */   }
/*     */   
/*     */   public Namespace createNamespace(String prefix, String nsURI) {
/* 108 */     return (Namespace)NamespaceEventImpl.constructNamespace(this.mLocation, prefix, nsURI);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data) {
/* 112 */     return (ProcessingInstruction)new ProcInstrEventImpl(this.mLocation, target, data);
/*     */   }
/*     */   
/*     */   public Characters createSpace(String content) {
/* 116 */     return (Characters)CharactersEventImpl.createNonIgnorableWS(this.mLocation, content);
/*     */   }
/*     */   
/*     */   public StartDocument createStartDocument() {
/* 120 */     return (StartDocument)new StartDocumentEventImpl(this.mLocation);
/*     */   }
/*     */   
/*     */   public StartDocument createStartDocument(String encoding) {
/* 124 */     return (StartDocument)new StartDocumentEventImpl(this.mLocation, encoding);
/*     */   }
/*     */   
/*     */   public StartDocument createStartDocument(String encoding, String version) {
/* 128 */     return (StartDocument)new StartDocumentEventImpl(this.mLocation, encoding, version);
/*     */   }
/*     */   
/*     */   public StartDocument createStartDocument(String encoding, String version, boolean standalone) {
/* 133 */     return (StartDocument)new StartDocumentEventImpl(this.mLocation, encoding, version, true, standalone);
/*     */   }
/*     */   
/*     */   public StartElement createStartElement(QName name, Iterator attr, Iterator ns) {
/* 139 */     return createStartElement(name, attr, ns, null);
/*     */   }
/*     */   
/*     */   public StartElement createStartElement(String prefix, String nsURI, String localName) {
/* 144 */     return createStartElement(createQName(nsURI, localName, prefix), null, null, null);
/*     */   }
/*     */   
/*     */   public StartElement createStartElement(String prefix, String nsURI, String localName, Iterator attr, Iterator ns) {
/* 152 */     return createStartElement(createQName(nsURI, localName, prefix), attr, ns, null);
/*     */   }
/*     */   
/*     */   public StartElement createStartElement(String prefix, String nsURI, String localName, Iterator attr, Iterator ns, NamespaceContext nsCtxt) {
/* 160 */     return createStartElement(createQName(nsURI, localName, prefix), attr, ns, nsCtxt);
/*     */   }
/*     */   
/*     */   public void setLocation(Location loc) {
/* 166 */     this.mLocation = loc;
/*     */   }
/*     */   
/*     */   public DTD2 createDTD(String rootName, String sysId, String pubId, String intSubset) {
/* 178 */     return (DTD2)new DTDEventImpl(this.mLocation, rootName, sysId, pubId, intSubset, null);
/*     */   }
/*     */   
/*     */   public DTD2 createDTD(String rootName, String sysId, String pubId, String intSubset, Object processedDTD) {
/* 184 */     return (DTD2)new DTDEventImpl(this.mLocation, rootName, sysId, pubId, intSubset, processedDTD);
/*     */   }
/*     */   
/*     */   protected abstract QName createQName(String paramString1, String paramString2);
/*     */   
/*     */   protected abstract QName createQName(String paramString1, String paramString2, String paramString3);
/*     */   
/*     */   protected StartElement createStartElement(QName name, Iterator attr, Iterator ns, NamespaceContext ctxt) {
/* 201 */     return (StartElement)StartElementEventImpl.construct(this.mLocation, name, attr, ns, ctxt);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2EventFactoryImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */