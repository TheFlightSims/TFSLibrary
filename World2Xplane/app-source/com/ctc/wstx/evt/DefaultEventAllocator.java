/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.dtd.DTDSubset;
/*     */ import com.ctc.wstx.ent.EntityDecl;
/*     */ import com.ctc.wstx.exc.WstxException;
/*     */ import com.ctc.wstx.sr.ElemAttrs;
/*     */ import com.ctc.wstx.sr.ElemCallback;
/*     */ import com.ctc.wstx.sr.StreamReaderImpl;
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.events.EntityDeclaration;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import javax.xml.stream.util.XMLEventAllocator;
/*     */ import javax.xml.stream.util.XMLEventConsumer;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.ri.evt.AttributeEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.CharactersEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.CommentEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.EndDocumentEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.EndElementEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.NamespaceEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.ProcInstrEventImpl;
/*     */ import org.codehaus.stax2.ri.evt.StartDocumentEventImpl;
/*     */ 
/*     */ public class DefaultEventAllocator extends ElemCallback implements XMLEventAllocator, XMLStreamConstants {
/*  59 */   static final DefaultEventAllocator sStdInstance = new DefaultEventAllocator(true);
/*     */   
/*     */   protected final boolean mAccurateLocation;
/*     */   
/*  79 */   protected Location mLastLocation = null;
/*     */   
/*     */   protected DefaultEventAllocator(boolean accurateLocation) {
/*  88 */     this.mAccurateLocation = accurateLocation;
/*     */   }
/*     */   
/*     */   public static DefaultEventAllocator getDefaultInstance() {
/*  95 */     return sStdInstance;
/*     */   }
/*     */   
/*     */   public static DefaultEventAllocator getFastInstance() {
/* 102 */     return new DefaultEventAllocator(false);
/*     */   }
/*     */   
/*     */   public XMLEvent allocate(XMLStreamReader r) throws XMLStreamException {
/*     */     Location loc;
/*     */     CharactersEventImpl ch;
/*     */     NamespaceContext nsCtxt;
/*     */     Map attrs;
/*     */     int attrCount;
/*     */     List ns;
/*     */     int nsCount;
/* 117 */     if (this.mAccurateLocation) {
/* 118 */       loc = r.getLocation();
/*     */     } else {
/* 120 */       loc = this.mLastLocation;
/* 124 */       if (loc == null)
/* 125 */         loc = this.mLastLocation = r.getLocation(); 
/*     */     } 
/* 129 */     switch (r.getEventType()) {
/*     */       case 12:
/* 131 */         return (XMLEvent)new CharactersEventImpl(loc, r.getText(), true);
/*     */       case 4:
/* 133 */         return (XMLEvent)new CharactersEventImpl(loc, r.getText(), false);
/*     */       case 5:
/* 135 */         return (XMLEvent)new CommentEventImpl(loc, r.getText());
/*     */       case 11:
/* 138 */         if (r instanceof XMLStreamReader2) {
/* 139 */           XMLStreamReader2 sr2 = (XMLStreamReader2)r;
/* 140 */           DTDInfo dtd = sr2.getDTDInfo();
/* 141 */           return (XMLEvent)new WDTD(loc, dtd.getDTDRootName(), dtd.getDTDSystemId(), dtd.getDTDPublicId(), dtd.getDTDInternalSubset(), (DTDSubset)dtd.getProcessedDTD());
/*     */         } 
/* 151 */         return (XMLEvent)new WDTD(loc, null, r.getText());
/*     */       case 8:
/* 154 */         return (XMLEvent)new EndDocumentEventImpl(loc);
/*     */       case 2:
/* 157 */         return (XMLEvent)new EndElementEventImpl(loc, r);
/*     */       case 3:
/* 160 */         return (XMLEvent)new ProcInstrEventImpl(loc, r.getPITarget(), r.getPIData());
/*     */       case 6:
/* 163 */         ch = new CharactersEventImpl(loc, r.getText(), false);
/* 164 */         ch.setWhitespaceStatus(true);
/* 165 */         return (XMLEvent)ch;
/*     */       case 7:
/* 168 */         return (XMLEvent)new StartDocumentEventImpl(loc, r);
/*     */       case 1:
/* 181 */         if (r instanceof StreamReaderImpl) {
/* 182 */           StreamReaderImpl sr = (StreamReaderImpl)r;
/* 183 */           BaseStartElement be = (BaseStartElement)sr.withStartElement(this, loc);
/* 184 */           if (be == null)
/* 185 */             throw new WstxException("Trying to create START_ELEMENT when current event is " + ErrorConsts.tokenTypeDesc(sr.getEventType()), loc); 
/* 189 */           return be;
/*     */         } 
/* 194 */         nsCtxt = null;
/* 195 */         if (r instanceof XMLStreamReader2)
/* 196 */           nsCtxt = ((XMLStreamReader2)r).getNonTransientNamespaceContext(); 
/* 200 */         attrCount = r.getAttributeCount();
/* 201 */         if (attrCount < 1) {
/* 202 */           attrs = null;
/*     */         } else {
/* 204 */           attrs = new LinkedHashMap();
/* 205 */           for (int i = 0; i < attrCount; i++) {
/* 206 */             QName aname = r.getAttributeName(i);
/* 207 */             attrs.put(aname, new AttributeEventImpl(loc, aname, r.getAttributeValue(i), r.isAttributeSpecified(i)));
/*     */           } 
/*     */         } 
/* 213 */         nsCount = r.getNamespaceCount();
/* 214 */         if (nsCount < 1) {
/* 215 */           ns = null;
/*     */         } else {
/* 217 */           ns = new ArrayList(nsCount);
/* 218 */           for (int i = 0; i < nsCount; i++)
/* 219 */             ns.add(NamespaceEventImpl.constructNamespace(loc, r.getNamespacePrefix(i), r.getNamespaceURI(i))); 
/*     */         } 
/* 224 */         return SimpleStartElement.construct(loc, r.getName(), attrs, ns, nsCtxt);
/*     */       case 9:
/* 232 */         if (r instanceof StreamReaderImpl) {
/* 233 */           EntityDecl ed = ((StreamReaderImpl)r).getCurrentEntityDecl();
/* 234 */           if (ed == null)
/* 236 */             return new WEntityReference(loc, r.getLocalName()); 
/* 238 */           return new WEntityReference(loc, (EntityDeclaration)ed);
/*     */         } 
/* 240 */         return new WEntityReference(loc, r.getLocalName());
/*     */       case 10:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/* 256 */         throw new WstxException("Internal error: should not get " + ErrorConsts.tokenTypeDesc(r.getEventType()));
/*     */     } 
/* 259 */     throw new IllegalStateException("Unrecognized event type " + r.getEventType() + ".");
/*     */   }
/*     */   
/*     */   public void allocate(XMLStreamReader r, XMLEventConsumer consumer) throws XMLStreamException {
/* 266 */     consumer.add(allocate(r));
/*     */   }
/*     */   
/*     */   public XMLEventAllocator newInstance() {
/* 270 */     return new DefaultEventAllocator(this.mAccurateLocation);
/*     */   }
/*     */   
/*     */   public Object withStartElement(Location loc, QName name, BaseNsContext nsCtxt, ElemAttrs attrs, boolean wasEmpty) {
/* 283 */     return new CompactStartElement(loc, name, nsCtxt, attrs);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\DefaultEventAllocator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */