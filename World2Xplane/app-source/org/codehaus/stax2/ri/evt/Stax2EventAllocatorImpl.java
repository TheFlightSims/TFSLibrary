/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.events.DTD;
/*     */ import javax.xml.stream.events.EntityReference;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import javax.xml.stream.util.XMLEventAllocator;
/*     */ import javax.xml.stream.util.XMLEventConsumer;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ 
/*     */ public class Stax2EventAllocatorImpl implements XMLEventAllocator, XMLStreamConstants {
/*     */   public XMLEvent allocate(XMLStreamReader r) throws XMLStreamException {
/*     */     CharactersEventImpl ch;
/*  37 */     Location loc = getLocation(r);
/*  39 */     switch (r.getEventType()) {
/*     */       case 12:
/*  41 */         return new CharactersEventImpl(loc, r.getText(), true);
/*     */       case 4:
/*  43 */         return new CharactersEventImpl(loc, r.getText(), false);
/*     */       case 5:
/*  45 */         return new CommentEventImpl(loc, r.getText());
/*     */       case 11:
/*  47 */         return createDTD(r, loc);
/*     */       case 8:
/*  50 */         return new EndDocumentEventImpl(loc);
/*     */       case 2:
/*  53 */         return new EndElementEventImpl(loc, r);
/*     */       case 3:
/*  56 */         return new ProcInstrEventImpl(loc, r.getPITarget(), r.getPIData());
/*     */       case 6:
/*  59 */         ch = new CharactersEventImpl(loc, r.getText(), false);
/*  60 */         ch.setWhitespaceStatus(true);
/*  61 */         return ch;
/*     */       case 7:
/*  64 */         return new StartDocumentEventImpl(loc, r);
/*     */       case 1:
/*  67 */         return createStartElement(r, loc);
/*     */       case 9:
/*  70 */         return createEntityReference(r, loc);
/*     */     } 
/*  85 */     throw new XMLStreamException("Unrecognized event type " + r.getEventType() + ".");
/*     */   }
/*     */   
/*     */   public void allocate(XMLStreamReader r, XMLEventConsumer consumer) throws XMLStreamException {
/*  92 */     consumer.add(allocate(r));
/*     */   }
/*     */   
/*     */   public XMLEventAllocator newInstance() {
/*  96 */     return new Stax2EventAllocatorImpl();
/*     */   }
/*     */   
/*     */   protected Location getLocation(XMLStreamReader r) {
/* 113 */     return r.getLocation();
/*     */   }
/*     */   
/*     */   protected EntityReference createEntityReference(XMLStreamReader r, Location loc) throws XMLStreamException {
/* 125 */     return new EntityReferenceEventImpl(loc, r.getLocalName());
/*     */   }
/*     */   
/*     */   protected DTD createDTD(XMLStreamReader r, Location loc) throws XMLStreamException {
/* 132 */     if (r instanceof XMLStreamReader2) {
/* 133 */       XMLStreamReader2 sr2 = (XMLStreamReader2)r;
/* 134 */       DTDInfo dtd = sr2.getDTDInfo();
/* 135 */       return (DTD)new DTDEventImpl(loc, dtd.getDTDRootName(), dtd.getDTDSystemId(), dtd.getDTDPublicId(), dtd.getDTDInternalSubset(), dtd.getProcessedDTD());
/*     */     } 
/* 146 */     return (DTD)new DTDEventImpl(loc, null, r.getText());
/*     */   }
/*     */   
/*     */   protected StartElement createStartElement(XMLStreamReader r, Location loc) throws XMLStreamException {
/*     */     List attrs;
/*     */     List ns;
/* 152 */     NamespaceContext nsCtxt = null;
/* 157 */     if (r instanceof XMLStreamReader2)
/* 158 */       nsCtxt = ((XMLStreamReader2)r).getNonTransientNamespaceContext(); 
/* 163 */     int attrCount = r.getAttributeCount();
/* 164 */     if (attrCount < 1) {
/* 165 */       attrs = null;
/*     */     } else {
/* 167 */       attrs = new ArrayList(attrCount);
/* 168 */       for (int i = 0; i < attrCount; i++) {
/* 169 */         QName aname = r.getAttributeName(i);
/* 170 */         attrs.add(new AttributeEventImpl(loc, aname, r.getAttributeValue(i), r.isAttributeSpecified(i)));
/*     */       } 
/*     */     } 
/* 176 */     int nsCount = r.getNamespaceCount();
/* 177 */     if (nsCount < 1) {
/* 178 */       ns = null;
/*     */     } else {
/* 180 */       ns = new ArrayList(nsCount);
/* 181 */       for (int i = 0; i < nsCount; i++)
/* 182 */         ns.add(NamespaceEventImpl.constructNamespace(loc, r.getNamespacePrefix(i), r.getNamespaceURI(i))); 
/*     */     } 
/* 187 */     return StartElementEventImpl.construct(loc, r.getName(), (attrs == null) ? (Iterator)EmptyIterator.getInstance() : attrs.iterator(), (ns == null) ? (Iterator)EmptyIterator.getInstance() : ns.iterator(), nsCtxt);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\Stax2EventAllocatorImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */