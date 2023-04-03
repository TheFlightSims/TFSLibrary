/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import javax.xml.stream.util.XMLEventAllocator;
/*     */ import org.codehaus.stax2.XMLEventReader2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ 
/*     */ public abstract class Stax2EventReaderImpl implements XMLEventReader2, XMLStreamConstants {
/*     */   protected static final int STATE_INITIAL = 1;
/*     */   
/*     */   protected static final int STATE_END_OF_INPUT = 2;
/*     */   
/*     */   protected static final int STATE_CONTENT = 3;
/*     */   
/*     */   protected static final int ERR_GETELEMTEXT_NOT_START_ELEM = 1;
/*     */   
/*     */   protected static final int ERR_GETELEMTEXT_NON_TEXT_EVENT = 2;
/*     */   
/*     */   protected static final int ERR_NEXTTAG_NON_WS_TEXT = 3;
/*     */   
/*     */   protected static final int ERR_NEXTTAG_WRONG_TYPE = 4;
/*     */   
/*     */   protected final XMLEventAllocator mAllocator;
/*     */   
/*     */   protected final XMLStreamReader2 mReader;
/*     */   
/* 106 */   private XMLEvent mPeekedEvent = null;
/*     */   
/* 116 */   protected int mState = 1;
/*     */   
/* 124 */   protected int mPrePeekEvent = 7;
/*     */   
/*     */   protected Stax2EventReaderImpl(XMLEventAllocator a, XMLStreamReader2 r) {
/* 134 */     this.mAllocator = a;
/* 135 */     this.mReader = r;
/*     */   }
/*     */   
/*     */   public abstract boolean isPropertySupported(String paramString);
/*     */   
/*     */   public abstract boolean setProperty(String paramString, Object paramObject);
/*     */   
/*     */   protected abstract String getErrorDesc(int paramInt1, int paramInt2);
/*     */   
/*     */   public void close() throws XMLStreamException {
/* 170 */     this.mReader.close();
/*     */   }
/*     */   
/*     */   public String getElementText() throws XMLStreamException {
/* 179 */     if (this.mPeekedEvent == null)
/* 180 */       return this.mReader.getElementText(); 
/* 183 */     XMLEvent evt = this.mPeekedEvent;
/* 184 */     this.mPeekedEvent = null;
/* 189 */     if (this.mPrePeekEvent != 1)
/* 190 */       reportProblem(findErrorDesc(1, this.mPrePeekEvent)); 
/* 194 */     String str = null;
/* 195 */     StringBuffer sb = null;
/* 201 */     for (; !evt.isEndElement(); evt = nextEvent()) {
/* 204 */       int type = evt.getEventType();
/* 205 */       if (type != 5 && type != 3) {
/* 209 */         if (!evt.isCharacters())
/* 210 */           reportProblem(findErrorDesc(2, type)); 
/* 212 */         String curr = evt.asCharacters().getData();
/* 213 */         if (str == null) {
/* 214 */           str = curr;
/*     */         } else {
/* 216 */           if (sb == null) {
/* 217 */             sb = new StringBuffer(str.length() + curr.length());
/* 218 */             sb.append(str);
/*     */           } 
/* 220 */           sb.append(curr);
/*     */         } 
/*     */       } 
/*     */     } 
/* 224 */     if (sb != null)
/* 225 */       return sb.toString(); 
/* 227 */     return (str == null) ? "" : str;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 231 */     return this.mReader.getProperty(name);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 235 */     return (this.mState != 2);
/*     */   }
/*     */   
/*     */   public XMLEvent nextEvent() throws XMLStreamException {
/* 241 */     if (this.mState == 2) {
/* 242 */       throwEndOfInput();
/* 243 */     } else if (this.mState == 1) {
/* 244 */       this.mState = 3;
/* 245 */       return createStartDocumentEvent();
/*     */     } 
/* 247 */     if (this.mPeekedEvent != null) {
/* 248 */       XMLEvent evt = this.mPeekedEvent;
/* 249 */       this.mPeekedEvent = null;
/* 250 */       if (evt.isEndDocument())
/* 251 */         this.mState = 2; 
/* 253 */       return evt;
/*     */     } 
/* 255 */     return createNextEvent(true, this.mReader.next());
/*     */   }
/*     */   
/*     */   public Object next() {
/*     */     try {
/* 260 */       return nextEvent();
/* 261 */     } catch (XMLStreamException sex) {
/* 262 */       throwUnchecked(sex);
/* 263 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLEvent nextTag() throws XMLStreamException {
/* 271 */     if (this.mPeekedEvent != null) {
/* 272 */       XMLEvent evt = this.mPeekedEvent;
/* 273 */       this.mPeekedEvent = null;
/* 274 */       int type = evt.getEventType();
/* 275 */       switch (type) {
/*     */         case 8:
/* 277 */           return null;
/*     */         case 7:
/*     */         case 6:
/*     */         case 3:
/*     */         case 5:
/*     */           break;
/*     */         case 4:
/*     */         case 12:
/* 294 */           if (((Characters)evt).isWhiteSpace())
/*     */             break; 
/* 297 */           reportProblem(findErrorDesc(3, type));
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/* 301 */           return evt;
/*     */         default:
/* 304 */           reportProblem(findErrorDesc(4, type));
/*     */           break;
/*     */       } 
/* 314 */     } else if (this.mState == 1) {
/* 315 */       this.mState = 3;
/*     */     } 
/*     */     while (true) {
/* 320 */       int next = this.mReader.next();
/* 322 */       switch (next) {
/*     */         case 8:
/* 324 */           return null;
/*     */         case 3:
/*     */         case 5:
/*     */         case 6:
/*     */           continue;
/*     */         case 4:
/*     */         case 12:
/* 335 */           if (this.mReader.isWhiteSpace())
/*     */             continue; 
/* 338 */           reportProblem(findErrorDesc(3, next));
/*     */           continue;
/*     */         case 1:
/*     */         case 2:
/* 343 */           return createNextEvent(false, next);
/*     */       } 
/* 346 */       reportProblem(findErrorDesc(4, next));
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLEvent peek() throws XMLStreamException {
/* 354 */     if (this.mPeekedEvent == null) {
/* 355 */       if (this.mState == 2)
/* 358 */         return null; 
/* 360 */       if (this.mState == 1) {
/* 362 */         this.mPrePeekEvent = 7;
/* 363 */         this.mPeekedEvent = createStartDocumentEvent();
/* 364 */         this.mState = 3;
/*     */       } else {
/* 366 */         this.mPrePeekEvent = this.mReader.getEventType();
/* 367 */         this.mPeekedEvent = createNextEvent(false, this.mReader.next());
/*     */       } 
/*     */     } 
/* 370 */     return this.mPeekedEvent;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 378 */     throw new UnsupportedOperationException("Can not remove events from XMLEventReader.");
/*     */   }
/*     */   
/*     */   public boolean hasNextEvent() throws XMLStreamException {
/* 398 */     return (this.mState != 2);
/*     */   }
/*     */   
/*     */   protected XMLEvent createNextEvent(boolean checkEOD, int type) throws XMLStreamException {
/*     */     try {
/* 411 */       XMLEvent evt = this.mAllocator.allocate((XMLStreamReader)this.mReader);
/* 412 */       if (checkEOD && type == 8)
/* 413 */         this.mState = 2; 
/* 415 */       return evt;
/* 416 */     } catch (RuntimeException rex) {
/* 423 */       Throwable t = rex.getCause();
/* 424 */       while (t != null) {
/* 425 */         if (t instanceof XMLStreamException)
/* 426 */           throw (XMLStreamException)t; 
/* 428 */         t = t.getCause();
/*     */       } 
/* 431 */       throw rex;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected XMLEvent createStartDocumentEvent() throws XMLStreamException {
/* 441 */     XMLEvent start = this.mAllocator.allocate((XMLStreamReader)this.mReader);
/* 442 */     return start;
/*     */   }
/*     */   
/*     */   private void throwEndOfInput() {
/* 453 */     throw new NoSuchElementException();
/*     */   }
/*     */   
/*     */   protected void throwUnchecked(XMLStreamException sex) {
/* 461 */     Throwable t = (sex.getNestedException() == null) ? sex : sex.getNestedException();
/* 463 */     if (t instanceof RuntimeException)
/* 464 */       throw (RuntimeException)t; 
/* 466 */     if (t instanceof Error)
/* 467 */       throw (Error)t; 
/* 470 */     throw new RuntimeException("[was " + t.getClass() + "] " + t.getMessage(), t);
/*     */   }
/*     */   
/*     */   protected void reportProblem(String msg) throws XMLStreamException {
/* 476 */     reportProblem(msg, this.mReader.getLocation());
/*     */   }
/*     */   
/*     */   protected void reportProblem(String msg, Location loc) throws XMLStreamException {
/* 482 */     if (loc == null)
/* 483 */       throw new XMLStreamException(msg); 
/* 485 */     throw new XMLStreamException(msg, loc);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader getStreamReader() {
/* 496 */     return (XMLStreamReader)this.mReader;
/*     */   }
/*     */   
/*     */   private final String findErrorDesc(int errorType, int currEvent) {
/* 512 */     String msg = getErrorDesc(errorType, currEvent);
/* 513 */     if (msg != null)
/* 514 */       return msg; 
/* 516 */     switch (errorType) {
/*     */       case 1:
/* 518 */         return "Current state not START_ELEMENT when calling getElementText()";
/*     */       case 2:
/* 520 */         return "Expected a text token";
/*     */       case 3:
/* 522 */         return "Only all-whitespace CHARACTERS/CDATA (or SPACE) allowed for nextTag()";
/*     */       case 4:
/* 524 */         return "Should only encounter START_ELEMENT/END_ELEMENT, SPACE, or all-white-space CHARACTERS";
/*     */     } 
/* 528 */     return "Internal error (unrecognized error type: " + errorType + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2EventReaderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */