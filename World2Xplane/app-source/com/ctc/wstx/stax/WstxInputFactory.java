/*     */ package com.ctc.wstx.stax;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.InputConfigFlags;
/*     */ import com.ctc.wstx.dom.WstxDOMWrappingReader;
/*     */ import com.ctc.wstx.dtd.DTDId;
/*     */ import com.ctc.wstx.dtd.DTDSubset;
/*     */ import com.ctc.wstx.evt.DefaultEventAllocator;
/*     */ import com.ctc.wstx.evt.WstxEventReader;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.io.BranchingReaderSource;
/*     */ import com.ctc.wstx.io.DefaultInputResolver;
/*     */ import com.ctc.wstx.io.InputBootstrapper;
/*     */ import com.ctc.wstx.io.InputSourceFactory;
/*     */ import com.ctc.wstx.io.ReaderBootstrapper;
/*     */ import com.ctc.wstx.io.StreamBootstrapper;
/*     */ import com.ctc.wstx.sr.ReaderCreator;
/*     */ import com.ctc.wstx.sr.ValidatingStreamReader;
/*     */ import com.ctc.wstx.util.DefaultXmlSymbolTable;
/*     */ import com.ctc.wstx.util.SimpleCache;
/*     */ import com.ctc.wstx.util.SymbolTable;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.EventFilter;
/*     */ import javax.xml.stream.StreamFilter;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.XMLReporter;
/*     */ import javax.xml.stream.XMLResolver;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.util.XMLEventAllocator;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.codehaus.stax2.XMLEventReader2;
/*     */ import org.codehaus.stax2.XMLInputFactory2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.io.Stax2ByteArraySource;
/*     */ import org.codehaus.stax2.io.Stax2Source;
/*     */ import org.codehaus.stax2.ri.Stax2FilteredStreamReader;
/*     */ import org.codehaus.stax2.ri.Stax2ReaderAdapter;
/*     */ import org.codehaus.stax2.ri.evt.Stax2EventReaderAdapter;
/*     */ import org.codehaus.stax2.ri.evt.Stax2FilteredEventReader;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class WstxInputFactory extends XMLInputFactory2 implements ReaderCreator, InputConfigFlags {
/*     */   static final int MAX_SYMBOL_TABLE_SIZE = 12000;
/*     */   
/*     */   static final int MAX_SYMBOL_TABLE_GENERATIONS = 500;
/*     */   
/*     */   protected final ReaderConfig mConfig;
/*     */   
/* 105 */   protected XMLEventAllocator mAllocator = null;
/*     */   
/* 109 */   protected SimpleCache mDTDCache = null;
/*     */   
/* 121 */   static final SymbolTable mRootSymbols = DefaultXmlSymbolTable.getInstance();
/*     */   
/*     */   static {
/* 132 */     mRootSymbols.setInternStrings(true);
/*     */   }
/*     */   
/* 139 */   SymbolTable mSymbols = mRootSymbols;
/*     */   
/*     */   public WstxInputFactory() {
/* 148 */     this.mConfig = ReaderConfig.createFullDefaults();
/*     */   }
/*     */   
/*     */   public synchronized DTDSubset findCachedDTD(DTDId id) {
/* 166 */     return (this.mDTDCache == null) ? null : (DTDSubset)this.mDTDCache.find(id);
/*     */   }
/*     */   
/*     */   public synchronized void updateSymbolTable(SymbolTable t) {
/* 183 */     SymbolTable curr = this.mSymbols;
/* 188 */     if (t.isDirectChildOf(curr))
/* 196 */       if (t.size() > 12000 || t.version() > 500) {
/* 199 */         this.mSymbols = mRootSymbols;
/*     */       } else {
/* 202 */         this.mSymbols.mergeChild(t);
/*     */       }  
/*     */   }
/*     */   
/*     */   public synchronized void addCachedDTD(DTDId id, DTDSubset extSubset) {
/* 211 */     if (this.mDTDCache == null)
/* 212 */       this.mDTDCache = new SimpleCache(this.mConfig.getDtdCacheSize()); 
/* 214 */     this.mDTDCache.add(id, extSubset);
/*     */   }
/*     */   
/*     */   public XMLEventReader createFilteredReader(XMLEventReader reader, EventFilter filter) {
/* 227 */     return (XMLEventReader)new Stax2FilteredEventReader(Stax2EventReaderAdapter.wrapIfNecessary(reader), filter);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createFilteredReader(XMLStreamReader reader, StreamFilter filter) throws XMLStreamException {
/* 233 */     Stax2FilteredStreamReader fr = new Stax2FilteredStreamReader(reader, filter);
/* 241 */     if (!filter.accept((XMLStreamReader)fr))
/* 243 */       fr.next(); 
/* 245 */     return (XMLStreamReader)fr;
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(InputStream in) throws XMLStreamException {
/* 254 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR((String)null, in, (String)null, true, false));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(InputStream in, String enc) throws XMLStreamException {
/* 262 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR((String)null, in, enc, true, false));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(Reader r) throws XMLStreamException {
/* 270 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR((String)null, r, true, false));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(Source source) throws XMLStreamException {
/* 277 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR(source, true));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(String systemId, InputStream in) throws XMLStreamException {
/* 285 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR(systemId, in, (String)null, true, false));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(String systemId, Reader r) throws XMLStreamException {
/* 293 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), createSR(systemId, r, true, false));
/*     */   }
/*     */   
/*     */   public XMLEventReader createXMLEventReader(XMLStreamReader sr) throws XMLStreamException {
/* 300 */     return (XMLEventReader)new WstxEventReader(createEventAllocator(), Stax2ReaderAdapter.wrapIfNecessary(sr));
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(InputStream in) throws XMLStreamException {
/* 309 */     return (XMLStreamReader)createSR((String)null, in, (String)null, false, false);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(InputStream in, String enc) throws XMLStreamException {
/* 316 */     return (XMLStreamReader)createSR((String)null, in, enc, false, false);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(Reader r) throws XMLStreamException {
/* 323 */     return (XMLStreamReader)createSR((String)null, r, false, false);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(Source src) throws XMLStreamException {
/* 330 */     return (XMLStreamReader)createSR(src, false);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(String systemId, InputStream in) throws XMLStreamException {
/* 337 */     return (XMLStreamReader)createSR(systemId, in, (String)null, false, false);
/*     */   }
/*     */   
/*     */   public XMLStreamReader createXMLStreamReader(String systemId, Reader r) throws XMLStreamException {
/* 344 */     return (XMLStreamReader)createSR(systemId, r, false, false);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 355 */     Object ob = this.mConfig.getProperty(name);
/* 357 */     if (ob == null && 
/* 358 */       name.equals("javax.xml.stream.allocator"))
/* 360 */       return getEventAllocator(); 
/* 363 */     return ob;
/*     */   }
/*     */   
/*     */   public void setProperty(String propName, Object value) {
/* 368 */     if (!this.mConfig.setProperty(propName, value) && 
/* 369 */       "javax.xml.stream.allocator".equals(propName))
/* 370 */       setEventAllocator((XMLEventAllocator)value); 
/*     */   }
/*     */   
/*     */   public XMLEventAllocator getEventAllocator() {
/* 376 */     return this.mAllocator;
/*     */   }
/*     */   
/*     */   public XMLReporter getXMLReporter() {
/* 380 */     return this.mConfig.getXMLReporter();
/*     */   }
/*     */   
/*     */   public XMLResolver getXMLResolver() {
/* 384 */     return this.mConfig.getXMLResolver();
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 388 */     return this.mConfig.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public void setEventAllocator(XMLEventAllocator allocator) {
/* 392 */     this.mAllocator = allocator;
/*     */   }
/*     */   
/*     */   public void setXMLReporter(XMLReporter r) {
/* 396 */     this.mConfig.setXMLReporter(r);
/*     */   }
/*     */   
/*     */   public void setXMLResolver(XMLResolver r) {
/* 406 */     this.mConfig.setXMLResolver(r);
/*     */   }
/*     */   
/*     */   public XMLEventReader2 createXMLEventReader(URL src) throws XMLStreamException {
/* 423 */     return (XMLEventReader2)new WstxEventReader(createEventAllocator(), createSR(createPrivateConfig(), src, true, true));
/*     */   }
/*     */   
/*     */   public XMLEventReader2 createXMLEventReader(File f) throws XMLStreamException {
/* 433 */     return (XMLEventReader2)new WstxEventReader(createEventAllocator(), createSR(f, true, true));
/*     */   }
/*     */   
/*     */   public XMLStreamReader2 createXMLStreamReader(URL src) throws XMLStreamException {
/* 443 */     return createSR(createPrivateConfig(), src, false, true);
/*     */   }
/*     */   
/*     */   public XMLStreamReader2 createXMLStreamReader(File f) throws XMLStreamException {
/* 456 */     return createSR(f, false, true);
/*     */   }
/*     */   
/*     */   public void configureForXmlConformance() {
/* 463 */     this.mConfig.configureForXmlConformance();
/*     */   }
/*     */   
/*     */   public void configureForConvenience() {
/* 468 */     this.mConfig.configureForConvenience();
/*     */   }
/*     */   
/*     */   public void configureForSpeed() {
/* 473 */     this.mConfig.configureForSpeed();
/*     */   }
/*     */   
/*     */   public void configureForLowMemUsage() {
/* 478 */     this.mConfig.configureForLowMemUsage();
/*     */   }
/*     */   
/*     */   public void configureForRoundTripping() {
/* 483 */     this.mConfig.configureForRoundTripping();
/*     */   }
/*     */   
/*     */   public ReaderConfig getConfig() {
/* 493 */     return this.mConfig;
/*     */   }
/*     */   
/*     */   private XMLStreamReader2 doCreateSR(ReaderConfig cfg, String systemId, InputBootstrapper bs, URL src, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/*     */     Reader r;
/* 525 */     if (!autoCloseInput)
/* 526 */       autoCloseInput = cfg.willAutoCloseInput(); 
/*     */     try {
/* 531 */       r = bs.bootstrapInput(cfg, true, 0);
/* 532 */       if (bs.declaredXml11())
/* 533 */         cfg.enableXml11(true); 
/* 535 */     } catch (IOException ie) {
/* 536 */       throw new WstxIOException(ie);
/*     */     } 
/* 542 */     BranchingReaderSource input = InputSourceFactory.constructDocumentSource(cfg, bs, null, systemId, src, r, autoCloseInput);
/* 545 */     return (XMLStreamReader2)ValidatingStreamReader.createValidatingStreamReader(input, this, cfg, bs, forER);
/*     */   }
/*     */   
/*     */   public XMLStreamReader2 createSR(ReaderConfig cfg, String systemId, InputBootstrapper bs, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/* 575 */     URL src = cfg.getBaseURL();
/* 578 */     if (src == null && systemId != null && systemId.length() > 0)
/*     */       try {
/* 580 */         src = URLUtil.urlFromSystemId(systemId);
/* 581 */       } catch (IOException ie) {
/* 582 */         throw new WstxIOException(ie);
/*     */       }  
/* 585 */     return doCreateSR(cfg, systemId, bs, src, forER, autoCloseInput);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader2 createSR(String systemId, InputStream in, String enc, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/* 594 */     if (in == null)
/* 595 */       throw new IllegalArgumentException("Null InputStream is not a valid argument"); 
/* 598 */     ReaderConfig cfg = createPrivateConfig();
/* 599 */     if (enc == null || enc.length() == 0)
/* 600 */       return createSR(cfg, systemId, (InputBootstrapper)StreamBootstrapper.getInstance(null, systemId, in), forER, autoCloseInput); 
/* 609 */     Reader r = DefaultInputResolver.constructOptimizedReader(cfg, in, false, enc);
/* 610 */     return createSR(cfg, systemId, (InputBootstrapper)ReaderBootstrapper.getInstance(null, systemId, r, enc), forER, autoCloseInput);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader2 createSR(ReaderConfig cfg, URL src, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/*     */     try {
/* 618 */       return createSR(cfg, src, URLUtil.inputStreamFromURL(src), forER, autoCloseInput);
/* 620 */     } catch (IOException ioe) {
/* 621 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private XMLStreamReader2 createSR(ReaderConfig cfg, URL src, InputStream in, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/* 630 */     String systemId = src.toExternalForm();
/* 631 */     return doCreateSR(cfg, systemId, (InputBootstrapper)StreamBootstrapper.getInstance(null, systemId, in), src, forER, autoCloseInput);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader2 createSR(String systemId, Reader r, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/* 641 */     return createSR(createPrivateConfig(), systemId, (InputBootstrapper)ReaderBootstrapper.getInstance(null, systemId, r, null), forER, autoCloseInput);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader2 createSR(File f, boolean forER, boolean autoCloseInput) throws XMLStreamException {
/* 649 */     ReaderConfig cfg = createPrivateConfig();
/*     */     try {
/* 654 */       if (!f.isAbsolute()) {
/* 655 */         URL base = cfg.getBaseURL();
/* 656 */         if (base != null) {
/* 657 */           URL src = new URL(base, f.getPath());
/* 658 */           return createSR(cfg, src, URLUtil.inputStreamFromURL(src), forER, autoCloseInput);
/*     */         } 
/*     */       } 
/* 661 */       return createSR(cfg, f.toURL(), new FileInputStream(f), forER, autoCloseInput);
/* 662 */     } catch (IOException ie) {
/* 663 */       throw new WstxIOException(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected XMLStreamReader2 createSR(Source src, boolean forER) throws XMLStreamException {
/*     */     boolean autoCloseInput;
/*     */     StreamBootstrapper streamBootstrapper;
/* 680 */     ReaderConfig cfg = createPrivateConfig();
/* 681 */     Reader r = null;
/* 682 */     InputStream in = null;
/* 683 */     String pubId = null;
/* 684 */     String sysId = null;
/* 685 */     String encoding = null;
/* 688 */     InputBootstrapper bs = null;
/* 690 */     if (src instanceof Stax2Source) {
/* 691 */       Stax2Source ss = (Stax2Source)src;
/* 692 */       sysId = ss.getSystemId();
/* 693 */       pubId = ss.getPublicId();
/* 694 */       encoding = ss.getEncoding();
/*     */       try {
/* 700 */         if (src instanceof Stax2ByteArraySource) {
/* 701 */           Stax2ByteArraySource bas = (Stax2ByteArraySource)src;
/* 702 */           streamBootstrapper = StreamBootstrapper.getInstance(pubId, sysId, bas.getBuffer(), bas.getBufferStart(), bas.getBufferEnd());
/*     */         } else {
/* 704 */           in = ss.constructInputStream();
/* 705 */           if (in == null)
/* 706 */             r = ss.constructReader(); 
/*     */         } 
/* 709 */       } catch (IOException ioe) {
/* 710 */         throw new WstxIOException(ioe);
/*     */       } 
/* 715 */       autoCloseInput = true;
/* 716 */     } else if (src instanceof StreamSource) {
/* 717 */       StreamSource ss = (StreamSource)src;
/* 718 */       sysId = ss.getSystemId();
/* 719 */       pubId = ss.getPublicId();
/* 720 */       in = ss.getInputStream();
/* 721 */       if (in == null)
/* 722 */         r = ss.getReader(); 
/* 727 */       autoCloseInput = cfg.willAutoCloseInput();
/* 728 */     } else if (src instanceof SAXSource) {
/* 729 */       SAXSource ss = (SAXSource)src;
/* 733 */       sysId = ss.getSystemId();
/* 734 */       InputSource isrc = ss.getInputSource();
/* 735 */       if (isrc != null) {
/* 736 */         encoding = isrc.getEncoding();
/* 737 */         in = isrc.getByteStream();
/* 738 */         if (in == null)
/* 739 */           r = isrc.getCharacterStream(); 
/*     */       } 
/* 745 */       autoCloseInput = cfg.willAutoCloseInput();
/*     */     } else {
/* 746 */       if (src instanceof DOMSource) {
/* 747 */         DOMSource domSrc = (DOMSource)src;
/* 749 */         return (XMLStreamReader2)WstxDOMWrappingReader.createFrom(domSrc, cfg);
/*     */       } 
/* 751 */       throw new IllegalArgumentException("Can not instantiate Stax reader for XML source type " + src.getClass() + " (unrecognized type)");
/*     */     } 
/* 753 */     if (streamBootstrapper == null)
/* 754 */       if (r != null) {
/* 755 */         ReaderBootstrapper readerBootstrapper = ReaderBootstrapper.getInstance(pubId, sysId, r, encoding);
/* 756 */       } else if (in != null) {
/* 757 */         streamBootstrapper = StreamBootstrapper.getInstance(pubId, sysId, in);
/*     */       } else {
/* 758 */         if (sysId != null && sysId.length() > 0) {
/* 763 */           autoCloseInput = true;
/*     */           try {
/* 765 */             return createSR(cfg, URLUtil.urlFromSystemId(sysId), forER, autoCloseInput);
/* 767 */           } catch (IOException ioe) {
/* 768 */             throw new WstxIOException(ioe);
/*     */           } 
/*     */         } 
/* 771 */         throw new XMLStreamException("Can not create Stax reader for the Source passed -- neither reader, input stream nor system id was accessible; can not use other types of sources (like embedded SAX streams)");
/*     */       }  
/* 774 */     return createSR(cfg, sysId, (InputBootstrapper)streamBootstrapper, forER, autoCloseInput);
/*     */   }
/*     */   
/*     */   protected XMLEventAllocator createEventAllocator() {
/* 780 */     if (this.mAllocator != null)
/* 781 */       return this.mAllocator.newInstance(); 
/* 788 */     return this.mConfig.willPreserveLocation() ? (XMLEventAllocator)DefaultEventAllocator.getDefaultInstance() : (XMLEventAllocator)DefaultEventAllocator.getFastInstance();
/*     */   }
/*     */   
/*     */   public ReaderConfig createPrivateConfig() {
/* 803 */     return this.mConfig.createNonShared(this.mSymbols.makeChild());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\stax\WstxInputFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */