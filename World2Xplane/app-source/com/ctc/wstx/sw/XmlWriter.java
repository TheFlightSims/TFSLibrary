/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.InvalidCharHandler;
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.text.MessageFormat;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.io.EscapingWriterFactory;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class XmlWriter {
/*     */   protected static final int SURR1_FIRST = 55296;
/*     */   
/*     */   protected static final int SURR1_LAST = 56319;
/*     */   
/*     */   protected static final int SURR2_FIRST = 56320;
/*     */   
/*     */   protected static final int SURR2_LAST = 57343;
/*     */   
/*     */   protected static final char DEFAULT_QUOTE_CHAR = '"';
/*     */   
/*     */   protected final WriterConfig mConfig;
/*     */   
/*     */   protected final String mEncoding;
/*     */   
/*     */   protected final boolean mNsAware;
/*     */   
/*     */   protected final boolean mCheckStructure;
/*     */   
/*     */   protected final boolean mCheckContent;
/*     */   
/*     */   protected final boolean mCheckNames;
/*     */   
/*     */   protected final boolean mFixContent;
/*     */   
/*     */   final boolean mEscapeCR;
/*     */   
/*     */   final boolean mAddSpaceAfterEmptyElem;
/*     */   
/*     */   protected final boolean mAutoCloseOutput;
/*     */   
/*     */   protected Writer mTextWriter;
/*     */   
/*     */   protected Writer mAttrValueWriter;
/*     */   
/*     */   protected boolean mXml11 = false;
/*     */   
/* 134 */   protected XmlWriterWrapper mRawWrapper = null;
/*     */   
/* 141 */   protected XmlWriterWrapper mTextWrapper = null;
/*     */   
/* 152 */   protected int mLocPastChars = 0;
/*     */   
/* 154 */   protected int mLocRowNr = 1;
/*     */   
/* 160 */   protected int mLocRowStartOffset = 0;
/*     */   
/*     */   protected XmlWriter(WriterConfig cfg, String encoding, boolean autoclose) throws IOException {
/* 171 */     this.mConfig = cfg;
/* 172 */     this.mEncoding = encoding;
/* 173 */     this.mAutoCloseOutput = autoclose;
/* 174 */     int flags = cfg.getConfigFlags();
/* 175 */     this.mNsAware = ((flags & 0x1) != 0);
/* 176 */     this.mCheckStructure = ((flags & 0x100) != 0);
/* 177 */     this.mCheckContent = ((flags & 0x200) != 0);
/* 178 */     this.mCheckNames = ((flags & 0x400) != 0);
/* 179 */     this.mFixContent = ((flags & 0x1000) != 0);
/* 180 */     this.mEscapeCR = ((flags & 0x20) != 0);
/* 181 */     this.mAddSpaceAfterEmptyElem = ((flags & 0x40) != 0);
/* 185 */     EscapingWriterFactory f = this.mConfig.getTextEscaperFactory();
/* 186 */     if (f == null) {
/* 187 */       this.mTextWriter = null;
/*     */     } else {
/* 189 */       String enc = (this.mEncoding == null || this.mEncoding.length() == 0) ? "UTF-8" : this.mEncoding;
/* 191 */       this.mTextWriter = f.createEscapingWriterFor(wrapAsRawWriter(), enc);
/*     */     } 
/* 194 */     f = this.mConfig.getAttrValueEscaperFactory();
/* 195 */     if (f == null) {
/* 196 */       this.mAttrValueWriter = null;
/*     */     } else {
/* 198 */       String enc = (this.mEncoding == null || this.mEncoding.length() == 0) ? "UTF-8" : this.mEncoding;
/* 200 */       this.mAttrValueWriter = f.createEscapingWriterFor(wrapAsRawWriter(), enc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enableXml11() {
/* 211 */     this.mXml11 = true;
/*     */   }
/*     */   
/*     */   protected abstract OutputStream getOutputStream();
/*     */   
/*     */   protected abstract Writer getWriter();
/*     */   
/*     */   public abstract void close(boolean paramBoolean) throws IOException;
/*     */   
/*     */   public abstract void flush() throws IOException;
/*     */   
/*     */   public abstract void writeRaw(String paramString, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public void writeRaw(String str) throws IOException {
/* 256 */     writeRaw(str, 0, str.length());
/*     */   }
/*     */   
/*     */   public abstract void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void writeRawAscii(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void writeCDataStart() throws IOException;
/*     */   
/*     */   public abstract void writeCDataEnd() throws IOException;
/*     */   
/*     */   public abstract void writeCommentStart() throws IOException;
/*     */   
/*     */   public abstract void writeCommentEnd() throws IOException;
/*     */   
/*     */   public abstract void writePIStart(String paramString, boolean paramBoolean) throws IOException;
/*     */   
/*     */   public abstract void writePIEnd() throws IOException;
/*     */   
/*     */   public abstract int writeCData(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract int writeCData(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeCharacters(String paramString) throws IOException;
/*     */   
/*     */   public abstract void writeCharacters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract int writeComment(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeDTD(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeDTD(String paramString1, String paramString2, String paramString3, String paramString4) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeEntityReference(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract int writePI(String paramString1, String paramString2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeXmlDeclaration(String paramString1, String paramString2, String paramString3) throws IOException;
/*     */   
/*     */   public abstract void writeStartTagStart(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeStartTagStart(String paramString1, String paramString2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeStartTagEnd() throws IOException;
/*     */   
/*     */   public abstract void writeStartTagEmptyEnd() throws IOException;
/*     */   
/*     */   public abstract void writeEndTag(String paramString) throws IOException;
/*     */   
/*     */   public abstract void writeEndTag(String paramString1, String paramString2) throws IOException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeTypedElement(AsciiValueEncoder paramAsciiValueEncoder) throws IOException;
/*     */   
/*     */   public abstract void writeTypedElement(AsciiValueEncoder paramAsciiValueEncoder, XMLValidator paramXMLValidator, char[] paramArrayOfchar) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeTypedAttribute(String paramString, AsciiValueEncoder paramAsciiValueEncoder) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeTypedAttribute(String paramString1, String paramString2, AsciiValueEncoder paramAsciiValueEncoder) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void writeTypedAttribute(String paramString1, String paramString2, String paramString3, AsciiValueEncoder paramAsciiValueEncoder, XMLValidator paramXMLValidator, char[] paramArrayOfchar) throws IOException, XMLStreamException;
/*     */   
/*     */   protected abstract int getOutputPtr();
/*     */   
/*     */   public int getRow() {
/* 496 */     return this.mLocRowNr;
/*     */   }
/*     */   
/*     */   public int getColumn() {
/* 500 */     return getOutputPtr() - this.mLocRowStartOffset + 1;
/*     */   }
/*     */   
/*     */   public int getAbsOffset() {
/* 504 */     return this.mLocPastChars + getOutputPtr();
/*     */   }
/*     */   
/*     */   public final Writer wrapAsRawWriter() {
/* 520 */     if (this.mRawWrapper == null)
/* 521 */       this.mRawWrapper = XmlWriterWrapper.wrapWriteRaw(this); 
/* 523 */     return this.mRawWrapper;
/*     */   }
/*     */   
/*     */   public final Writer wrapAsTextWriter() {
/* 528 */     if (this.mTextWrapper == null)
/* 529 */       this.mTextWrapper = XmlWriterWrapper.wrapWriteCharacters(this); 
/* 531 */     return this.mTextWrapper;
/*     */   }
/*     */   
/*     */   public final void verifyNameValidity(String name, boolean checkNs) throws XMLStreamException {
/* 549 */     if (name == null || name.length() == 0)
/* 550 */       reportNwfName(ErrorConsts.WERR_NAME_EMPTY); 
/* 552 */     int illegalIx = WstxInputData.findIllegalNameChar(name, checkNs, this.mXml11);
/* 553 */     if (illegalIx >= 0) {
/* 554 */       if (illegalIx == 0)
/* 555 */         reportNwfName(ErrorConsts.WERR_NAME_ILLEGAL_FIRST_CHAR, WstxInputData.getCharDesc(name.charAt(0))); 
/* 558 */       reportNwfName(ErrorConsts.WERR_NAME_ILLEGAL_CHAR, WstxInputData.getCharDesc(name.charAt(illegalIx)));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void reportNwfName(String msg) throws XMLStreamException {
/* 572 */     throwOutputError(msg);
/*     */   }
/*     */   
/*     */   protected void reportNwfName(String msg, Object arg) throws XMLStreamException {
/* 578 */     throwOutputError(msg, arg);
/*     */   }
/*     */   
/*     */   protected void reportNwfContent(String msg) throws XMLStreamException {
/* 584 */     throwOutputError(msg);
/*     */   }
/*     */   
/*     */   protected void throwOutputError(String msg) throws XMLStreamException {
/*     */     try {
/* 592 */       flush();
/* 593 */     } catch (IOException ioe) {
/* 594 */       throw new WstxIOException(ioe);
/*     */     } 
/* 597 */     throw new XMLStreamException(msg);
/*     */   }
/*     */   
/*     */   protected void throwOutputError(String format, Object arg) throws XMLStreamException {
/* 603 */     String msg = MessageFormat.format(format, new Object[] { arg });
/* 604 */     throwOutputError(msg);
/*     */   }
/*     */   
/*     */   protected char handleInvalidChar(int c) throws IOException {
/*     */     InvalidCharHandler.FailingHandler failingHandler;
/* 618 */     flush();
/* 619 */     InvalidCharHandler h = this.mConfig.getInvalidCharHandler();
/* 620 */     if (h == null)
/* 621 */       failingHandler = InvalidCharHandler.FailingHandler.getInstance(); 
/* 623 */     return failingHandler.convertInvalidChar(c);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\XmlWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */