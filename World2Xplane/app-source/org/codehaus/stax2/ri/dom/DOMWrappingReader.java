/*      */ package org.codehaus.stax2.ri.dom;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamConstants;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import org.codehaus.stax2.AttributeInfo;
/*      */ import org.codehaus.stax2.DTDInfo;
/*      */ import org.codehaus.stax2.LocationInfo;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.XMLStreamReader2;
/*      */ import org.codehaus.stax2.ri.EmptyIterator;
/*      */ import org.codehaus.stax2.ri.EmptyNamespaceContext;
/*      */ import org.codehaus.stax2.ri.SingletonIterator;
/*      */ import org.codehaus.stax2.ri.Stax2Util;
/*      */ import org.codehaus.stax2.ri.typed.StringBase64Decoder;
/*      */ import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
/*      */ import org.codehaus.stax2.typed.Base64Variant;
/*      */ import org.codehaus.stax2.typed.Base64Variants;
/*      */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*      */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*      */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*      */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*      */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*      */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ public abstract class DOMWrappingReader implements XMLStreamReader2, AttributeInfo, DTDInfo, LocationInfo, NamespaceContext, XMLStreamConstants {
/*      */   protected static final int INT_SPACE = 32;
/*      */   
/*      */   private static final int MASK_GET_TEXT = 6768;
/*      */   
/*      */   private static final int MASK_GET_TEXT_XXX = 4208;
/*      */   
/*      */   private static final int MASK_GET_ELEMENT_TEXT = 4688;
/*      */   
/*      */   protected static final int MASK_TYPED_ACCESS_BINARY = 4178;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_START_ELEM = 1;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_ELEM = 2;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_PI = 3;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_TEXTUAL = 4;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_TEXTUAL_XXX = 5;
/*      */   
/*      */   protected static final int ERR_STATE_NOT_TEXTUAL_OR_ELEM = 6;
/*      */   
/*      */   protected static final int ERR_STATE_NO_LOCALNAME = 7;
/*      */   
/*      */   protected final String _systemId;
/*      */   
/*      */   protected final Node _rootNode;
/*      */   
/*      */   protected final boolean _cfgNsAware;
/*      */   
/*      */   protected final boolean _coalescing;
/*      */   
/*      */   protected boolean _cfgInternNames = false;
/*      */   
/*      */   protected boolean _cfgInternNsURIs = false;
/*      */   
/*  167 */   protected int _currEvent = 7;
/*      */   
/*      */   protected Node _currNode;
/*      */   
/*  175 */   protected int _depth = 0;
/*      */   
/*      */   protected String _coalescedText;
/*      */   
/*  188 */   protected Stax2Util.TextBuffer _textBuffer = new Stax2Util.TextBuffer();
/*      */   
/*  204 */   protected List _attrList = null;
/*      */   
/*  213 */   protected List _nsDeclList = null;
/*      */   
/*      */   protected ValueDecoderFactory _decoderFactory;
/*      */   
/*  224 */   protected StringBase64Decoder _base64Decoder = null;
/*      */   
/*      */   protected DOMWrappingReader(DOMSource src, boolean nsAware, boolean coalescing) throws XMLStreamException {
/*  244 */     Node treeRoot = src.getNode();
/*  245 */     if (treeRoot == null)
/*  246 */       throw new IllegalArgumentException("Can not pass null Node for constructing a DOM-based XMLStreamReader"); 
/*  248 */     this._cfgNsAware = nsAware;
/*  249 */     this._coalescing = coalescing;
/*  250 */     this._systemId = src.getSystemId();
/*  255 */     switch (treeRoot.getNodeType()) {
/*      */       case 1:
/*      */       case 9:
/*      */       case 11:
/*      */         break;
/*      */       default:
/*  269 */         throw new XMLStreamException("Can not create an XMLStreamReader for a DOM node of type " + treeRoot.getClass());
/*      */     } 
/*  271 */     this._rootNode = this._currNode = treeRoot;
/*      */   }
/*      */   
/*      */   protected void setInternNames(boolean state) {
/*  274 */     this._cfgInternNames = state;
/*      */   }
/*      */   
/*      */   protected void setInternNsURIs(boolean state) {
/*  275 */     this._cfgInternNsURIs = state;
/*      */   }
/*      */   
/*      */   protected abstract void throwStreamException(String paramString, Location paramLocation) throws XMLStreamException;
/*      */   
/*      */   public String getCharacterEncodingScheme() {
/*  300 */     return null;
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/*  313 */     return getCharacterEncodingScheme();
/*      */   }
/*      */   
/*      */   public String getVersion() {
/*  321 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isStandalone() {
/*  328 */     return false;
/*      */   }
/*      */   
/*      */   public boolean standaloneSet() {
/*  335 */     return false;
/*      */   }
/*      */   
/*      */   public abstract Object getProperty(String paramString);
/*      */   
/*      */   public abstract boolean isPropertySupported(String paramString);
/*      */   
/*      */   public abstract boolean setProperty(String paramString, Object paramObject);
/*      */   
/*      */   public int getAttributeCount() {
/*  369 */     if (this._currEvent != 1)
/*  370 */       reportWrongState(1); 
/*  372 */     if (this._attrList == null)
/*  373 */       _calcNsAndAttrLists(true); 
/*  375 */     return this._attrList.size();
/*      */   }
/*      */   
/*      */   public String getAttributeLocalName(int index) {
/*  380 */     if (this._currEvent != 1)
/*  381 */       reportWrongState(1); 
/*  383 */     if (this._attrList == null)
/*  384 */       _calcNsAndAttrLists(true); 
/*  386 */     if (index >= this._attrList.size() || index < 0) {
/*  387 */       handleIllegalAttrIndex(index);
/*  388 */       return null;
/*      */     } 
/*  390 */     Attr attr = this._attrList.get(index);
/*  391 */     return _internName(_safeGetLocalName(attr));
/*      */   }
/*      */   
/*      */   public QName getAttributeName(int index) {
/*  396 */     if (this._currEvent != 1)
/*  397 */       reportWrongState(1); 
/*  399 */     if (this._attrList == null)
/*  400 */       _calcNsAndAttrLists(true); 
/*  402 */     if (index >= this._attrList.size() || index < 0) {
/*  403 */       handleIllegalAttrIndex(index);
/*  404 */       return null;
/*      */     } 
/*  406 */     Attr attr = this._attrList.get(index);
/*  407 */     return _constructQName(attr.getNamespaceURI(), _safeGetLocalName(attr), attr.getPrefix());
/*      */   }
/*      */   
/*      */   public String getAttributeNamespace(int index) {
/*  412 */     if (this._currEvent != 1)
/*  413 */       reportWrongState(1); 
/*  415 */     if (this._attrList == null)
/*  416 */       _calcNsAndAttrLists(true); 
/*  418 */     if (index >= this._attrList.size() || index < 0) {
/*  419 */       handleIllegalAttrIndex(index);
/*  420 */       return null;
/*      */     } 
/*  422 */     Attr attr = this._attrList.get(index);
/*  423 */     return _internNsURI(attr.getNamespaceURI());
/*      */   }
/*      */   
/*      */   public String getAttributePrefix(int index) {
/*  428 */     if (this._currEvent != 1)
/*  429 */       reportWrongState(1); 
/*  431 */     if (this._attrList == null)
/*  432 */       _calcNsAndAttrLists(true); 
/*  434 */     if (index >= this._attrList.size() || index < 0) {
/*  435 */       handleIllegalAttrIndex(index);
/*  436 */       return null;
/*      */     } 
/*  438 */     Attr attr = this._attrList.get(index);
/*  439 */     return _internName(attr.getPrefix());
/*      */   }
/*      */   
/*      */   public String getAttributeType(int index) {
/*  444 */     if (this._currEvent != 1)
/*  445 */       reportWrongState(1); 
/*  447 */     if (this._attrList == null)
/*  448 */       _calcNsAndAttrLists(true); 
/*  450 */     if (index >= this._attrList.size() || index < 0) {
/*  451 */       handleIllegalAttrIndex(index);
/*  452 */       return null;
/*      */     } 
/*  468 */     return "CDATA";
/*      */   }
/*      */   
/*      */   public String getAttributeValue(int index) {
/*  473 */     if (this._currEvent != 1)
/*  474 */       reportWrongState(1); 
/*  476 */     if (this._attrList == null)
/*  477 */       _calcNsAndAttrLists(true); 
/*  479 */     if (index >= this._attrList.size() || index < 0) {
/*  480 */       handleIllegalAttrIndex(index);
/*  481 */       return null;
/*      */     } 
/*  483 */     Attr attr = this._attrList.get(index);
/*  484 */     return attr.getValue();
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String nsURI, String localName) {
/*  489 */     if (this._currEvent != 1)
/*  490 */       reportWrongState(1); 
/*  492 */     Element elem = (Element)this._currNode;
/*  493 */     NamedNodeMap attrs = elem.getAttributes();
/*  499 */     if (nsURI != null && nsURI.length() == 0)
/*  500 */       nsURI = null; 
/*  502 */     Attr attr = (Attr)attrs.getNamedItemNS(nsURI, localName);
/*  503 */     return (attr == null) ? null : attr.getValue();
/*      */   }
/*      */   
/*      */   public String getElementText() throws XMLStreamException {
/*  520 */     if (this._currEvent != 1)
/*  525 */       reportParseProblem(1); 
/*  528 */     if (this._coalescing) {
/*  529 */       String text = null;
/*      */       while (true) {
/*  532 */         int type = next();
/*  533 */         if (type == 2)
/*      */           break; 
/*  536 */         if (type == 5 || type == 3)
/*      */           continue; 
/*  539 */         if ((1 << type & 0x1250) == 0)
/*  540 */           reportParseProblem(4); 
/*  542 */         if (text == null) {
/*  543 */           text = getText();
/*      */           continue;
/*      */         } 
/*  545 */         text = text + getText();
/*      */       } 
/*  548 */       return (text == null) ? "" : text;
/*      */     } 
/*  550 */     this._textBuffer.reset();
/*      */     while (true) {
/*  553 */       int type = next();
/*  554 */       if (type == 2)
/*      */         break; 
/*  557 */       if (type == 5 || type == 3)
/*      */         continue; 
/*  560 */       if ((1 << type & 0x1250) == 0)
/*  561 */         reportParseProblem(4); 
/*  563 */       this._textBuffer.append(getText());
/*      */     } 
/*  565 */     return this._textBuffer.get();
/*      */   }
/*      */   
/*      */   public int getEventType() {
/*  574 */     return this._currEvent;
/*      */   }
/*      */   
/*      */   public String getLocalName() {
/*  579 */     if (this._currEvent == 1 || this._currEvent == 2)
/*  580 */       return _internName(_safeGetLocalName(this._currNode)); 
/*  582 */     if (this._currEvent != 9)
/*  583 */       reportWrongState(7); 
/*  585 */     return _internName(this._currNode.getNodeName());
/*      */   }
/*      */   
/*      */   public final Location getLocation() {
/*  589 */     return (Location)getStartLocation();
/*      */   }
/*      */   
/*      */   public QName getName() {
/*  594 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  595 */       reportWrongState(1); 
/*  597 */     return _constructQName(this._currNode.getNamespaceURI(), _safeGetLocalName(this._currNode), this._currNode.getPrefix());
/*      */   }
/*      */   
/*      */   public NamespaceContext getNamespaceContext() {
/*  603 */     return this;
/*      */   }
/*      */   
/*      */   public int getNamespaceCount() {
/*  608 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  609 */       reportWrongState(2); 
/*  611 */     if (this._nsDeclList == null) {
/*  612 */       if (!this._cfgNsAware)
/*  613 */         return 0; 
/*  615 */       _calcNsAndAttrLists((this._currEvent == 1));
/*      */     } 
/*  617 */     return this._nsDeclList.size() / 2;
/*      */   }
/*      */   
/*      */   public String getNamespacePrefix(int index) {
/*  626 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  627 */       reportWrongState(2); 
/*  629 */     if (this._nsDeclList == null) {
/*  630 */       if (!this._cfgNsAware)
/*  631 */         handleIllegalNsIndex(index); 
/*  633 */       _calcNsAndAttrLists((this._currEvent == 1));
/*      */     } 
/*  635 */     if (index < 0 || index + index >= this._nsDeclList.size())
/*  636 */       handleIllegalNsIndex(index); 
/*  639 */     return this._nsDeclList.get(index + index);
/*      */   }
/*      */   
/*      */   public String getNamespaceURI() {
/*  643 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  644 */       reportWrongState(2); 
/*  646 */     return _internNsURI(this._currNode.getNamespaceURI());
/*      */   }
/*      */   
/*      */   public String getNamespaceURI(int index) {
/*  650 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  651 */       reportWrongState(2); 
/*  653 */     if (this._nsDeclList == null) {
/*  654 */       if (!this._cfgNsAware)
/*  655 */         handleIllegalNsIndex(index); 
/*  657 */       _calcNsAndAttrLists((this._currEvent == 1));
/*      */     } 
/*  659 */     if (index < 0 || index + index >= this._nsDeclList.size())
/*  660 */       handleIllegalNsIndex(index); 
/*  663 */     return this._nsDeclList.get(index + index + 1);
/*      */   }
/*      */   
/*      */   public String getPIData() {
/*  670 */     if (this._currEvent != 3)
/*  671 */       reportWrongState(3); 
/*  673 */     return this._currNode.getNodeValue();
/*      */   }
/*      */   
/*      */   public String getPITarget() {
/*  677 */     if (this._currEvent != 3)
/*  678 */       reportWrongState(3); 
/*  680 */     return _internName(this._currNode.getNodeName());
/*      */   }
/*      */   
/*      */   public String getPrefix() {
/*  684 */     if (this._currEvent != 1 && this._currEvent != 2)
/*  685 */       reportWrongState(2); 
/*  687 */     return _internName(this._currNode.getPrefix());
/*      */   }
/*      */   
/*      */   public String getText() {
/*  692 */     if (this._coalescedText != null)
/*  693 */       return this._coalescedText; 
/*  695 */     if ((1 << this._currEvent & 0x1A70) == 0)
/*  696 */       reportWrongState(4); 
/*  698 */     return this._currNode.getNodeValue();
/*      */   }
/*      */   
/*      */   public char[] getTextCharacters() {
/*  703 */     String text = getText();
/*  704 */     return text.toCharArray();
/*      */   }
/*      */   
/*      */   public int getTextCharacters(int sourceStart, char[] target, int targetStart, int len) {
/*  709 */     if ((1 << this._currEvent & 0x1070) == 0)
/*  710 */       reportWrongState(5); 
/*  712 */     String text = getText();
/*  713 */     if (len > text.length())
/*  714 */       len = text.length(); 
/*  716 */     text.getChars(sourceStart, sourceStart + len, target, targetStart);
/*  717 */     return len;
/*      */   }
/*      */   
/*      */   public int getTextLength() {
/*  722 */     if ((1 << this._currEvent & 0x1070) == 0)
/*  723 */       reportWrongState(5); 
/*  725 */     return getText().length();
/*      */   }
/*      */   
/*      */   public int getTextStart() {
/*  730 */     if ((1 << this._currEvent & 0x1070) == 0)
/*  731 */       reportWrongState(5); 
/*  733 */     return 0;
/*      */   }
/*      */   
/*      */   public boolean hasName() {
/*  737 */     return (this._currEvent == 1 || this._currEvent == 2);
/*      */   }
/*      */   
/*      */   public boolean hasNext() {
/*  741 */     return (this._currEvent != 8);
/*      */   }
/*      */   
/*      */   public boolean hasText() {
/*  745 */     return ((1 << this._currEvent & 0x1A70) != 0);
/*      */   }
/*      */   
/*      */   public boolean isAttributeSpecified(int index) {
/*  750 */     if (this._currEvent != 1)
/*  751 */       reportWrongState(1); 
/*  753 */     Element elem = (Element)this._currNode;
/*  754 */     Attr attr = (Attr)elem.getAttributes().item(index);
/*  755 */     if (attr == null) {
/*  756 */       handleIllegalAttrIndex(index);
/*  757 */       return false;
/*      */     } 
/*  759 */     return attr.getSpecified();
/*      */   }
/*      */   
/*      */   public boolean isCharacters() {
/*  764 */     return (this._currEvent == 4);
/*      */   }
/*      */   
/*      */   public boolean isEndElement() {
/*  768 */     return (this._currEvent == 2);
/*      */   }
/*      */   
/*      */   public boolean isStartElement() {
/*  772 */     return (this._currEvent == 1);
/*      */   }
/*      */   
/*      */   public boolean isWhiteSpace() {
/*  777 */     if (this._currEvent == 4 || this._currEvent == 12) {
/*  778 */       String text = getText();
/*  779 */       for (int i = 0, len = text.length(); i < len; i++) {
/*  783 */         if (text.charAt(i) > ' ')
/*  784 */           return false; 
/*      */       } 
/*  787 */       return true;
/*      */     } 
/*  789 */     return (this._currEvent == 6);
/*      */   }
/*      */   
/*      */   public void require(int type, String nsUri, String localName) throws XMLStreamException {
/*  795 */     int curr = this._currEvent;
/*  801 */     if (curr != type)
/*  802 */       if (curr == 12) {
/*  803 */         curr = 4;
/*  804 */       } else if (curr == 6) {
/*  805 */         curr = 4;
/*      */       }  
/*  809 */     if (type != curr)
/*  810 */       throwStreamException("Required type " + Stax2Util.eventTypeDesc(type) + ", current type " + Stax2Util.eventTypeDesc(curr)); 
/*  815 */     if (localName != null) {
/*  816 */       if (curr != 1 && curr != 2 && curr != 9)
/*  818 */         throwStreamException("Required a non-null local name, but current token not a START_ELEMENT, END_ELEMENT or ENTITY_REFERENCE (was " + Stax2Util.eventTypeDesc(this._currEvent) + ")"); 
/*  820 */       String n = getLocalName();
/*  821 */       if (n != localName && !n.equals(localName))
/*  822 */         throwStreamException("Required local name '" + localName + "'; current local name '" + n + "'."); 
/*      */     } 
/*  825 */     if (nsUri != null) {
/*  826 */       if (curr != 1 && curr != 2)
/*  827 */         throwStreamException("Required non-null NS URI, but current token not a START_ELEMENT or END_ELEMENT (was " + Stax2Util.eventTypeDesc(curr) + ")"); 
/*  830 */       String uri = getNamespaceURI();
/*  832 */       if (nsUri.length() == 0) {
/*  833 */         if (uri != null && uri.length() > 0)
/*  834 */           throwStreamException("Required empty namespace, instead have '" + uri + "'."); 
/*  837 */       } else if (nsUri != uri && !nsUri.equals(uri)) {
/*  838 */         throwStreamException("Required namespace '" + nsUri + "'; have '" + uri + "'.");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int next() throws XMLStreamException {
/*      */     Node firstChild, next;
/*      */     int type;
/*  855 */     this._coalescedText = null;
/*  861 */     switch (this._currEvent) {
/*      */       case 7:
/*  867 */         switch (this._currNode.getNodeType()) {
/*      */           case 9:
/*      */           case 11:
/*  871 */             this._currNode = this._currNode.getFirstChild();
/*  873 */             if (this._currNode == null)
/*  874 */               return this._currEvent = 8; 
/*      */             break;
/*      */           case 1:
/*  880 */             return this._currEvent = 1;
/*      */         } 
/*  883 */         throw new XMLStreamException("Internal error: unexpected DOM root node type " + this._currNode.getNodeType() + " for node '" + this._currNode + "'");
/*      */       case 8:
/*  888 */         throw new NoSuchElementException("Can not call next() after receiving END_DOCUMENT");
/*      */       case 1:
/*  891 */         this._depth++;
/*  892 */         this._attrList = null;
/*  894 */         firstChild = this._currNode.getFirstChild();
/*  895 */         if (firstChild == null)
/*  899 */           return this._currEvent = 2; 
/*  901 */         this._nsDeclList = null;
/*  906 */         this._currNode = firstChild;
/*      */         break;
/*      */       case 2:
/*  912 */         this._depth--;
/*  914 */         this._attrList = null;
/*  915 */         this._nsDeclList = null;
/*  922 */         if (this._currNode == this._rootNode)
/*  923 */           return this._currEvent = 8; 
/*      */       default:
/*  932 */         next = this._currNode.getNextSibling();
/*  934 */         if (next != null) {
/*  935 */           this._currNode = next;
/*      */           break;
/*      */         } 
/*  942 */         this._currNode = this._currNode.getParentNode();
/*  943 */         type = this._currNode.getNodeType();
/*  944 */         if (type == 1)
/*  945 */           return this._currEvent = 2; 
/*  948 */         if (this._currNode != this._rootNode || (type != 9 && type != 11))
/*  950 */           throw new XMLStreamException("Internal error: non-element parent node (" + type + ") that is not the initial root node"); 
/*  952 */         return this._currEvent = 8;
/*      */     } 
/*  957 */     switch (this._currNode.getNodeType()) {
/*      */       case 4:
/*  959 */         if (this._coalescing) {
/*  960 */           coalesceText(12);
/*      */         } else {
/*  962 */           this._currEvent = 12;
/*      */         } 
/*  998 */         return this._currEvent;
/*      */       case 8:
/*      */         this._currEvent = 5;
/*  998 */         return this._currEvent;
/*      */       case 10:
/*      */         this._currEvent = 11;
/*  998 */         return this._currEvent;
/*      */       case 1:
/*      */         this._currEvent = 1;
/*  998 */         return this._currEvent;
/*      */       case 5:
/*      */         this._currEvent = 9;
/*  998 */         return this._currEvent;
/*      */       case 7:
/*      */         this._currEvent = 3;
/*  998 */         return this._currEvent;
/*      */       case 3:
/*      */         if (this._coalescing) {
/*      */           coalesceText(4);
/*      */         } else {
/*      */           this._currEvent = 4;
/*      */         } 
/*  998 */         return this._currEvent;
/*      */       case 2:
/*      */       case 6:
/*      */       case 12:
/*      */         throw new XMLStreamException("Internal error: unexpected DOM node type " + this._currNode.getNodeType() + " (attr/entity/notation?), for node '" + this._currNode + "'");
/*      */     } 
/*      */     throw new XMLStreamException("Internal error: unrecognized DOM node type " + this._currNode.getNodeType() + ", for node '" + this._currNode + "'");
/*      */   }
/*      */   
/*      */   public int nextTag() throws XMLStreamException {
/*      */     while (true) {
/* 1005 */       int next = next();
/* 1007 */       switch (next) {
/*      */         case 3:
/*      */         case 5:
/*      */         case 6:
/*      */           continue;
/*      */         case 4:
/*      */         case 12:
/* 1014 */           if (isWhiteSpace())
/*      */             continue; 
/* 1017 */           throwStreamException("Received non-all-whitespace CHARACTERS or CDATA event in nextTag().");
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/* 1021 */           return next;
/*      */       } 
/* 1023 */       throwStreamException("Received event " + Stax2Util.eventTypeDesc(next) + ", instead of START_ELEMENT or END_ELEMENT.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() throws XMLStreamException {}
/*      */   
/*      */   public String getNamespaceURI(String prefix) {
/* 1059 */     return null;
/*      */   }
/*      */   
/*      */   public String getPrefix(String namespaceURI) {
/* 1077 */     return null;
/*      */   }
/*      */   
/*      */   public Iterator getPrefixes(String namespaceURI) {
/* 1082 */     String prefix = getPrefix(namespaceURI);
/* 1083 */     if (prefix == null)
/* 1084 */       return (Iterator)EmptyIterator.getInstance(); 
/* 1086 */     return (Iterator)new SingletonIterator(prefix);
/*      */   }
/*      */   
/*      */   public boolean getElementAsBoolean() throws XMLStreamException {
/* 1097 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 1098 */     getElementAs((TypedValueDecoder)dec);
/* 1099 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public int getElementAsInt() throws XMLStreamException {
/* 1104 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 1105 */     getElementAs((TypedValueDecoder)dec);
/* 1106 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public long getElementAsLong() throws XMLStreamException {
/* 1111 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 1112 */     getElementAs((TypedValueDecoder)dec);
/* 1113 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public float getElementAsFloat() throws XMLStreamException {
/* 1118 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 1119 */     getElementAs((TypedValueDecoder)dec);
/* 1120 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public double getElementAsDouble() throws XMLStreamException {
/* 1125 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 1126 */     getElementAs((TypedValueDecoder)dec);
/* 1127 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigInteger getElementAsInteger() throws XMLStreamException {
/* 1132 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 1133 */     getElementAs((TypedValueDecoder)dec);
/* 1134 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigDecimal getElementAsDecimal() throws XMLStreamException {
/* 1139 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 1140 */     getElementAs((TypedValueDecoder)dec);
/* 1141 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public QName getElementAsQName() throws XMLStreamException {
/* 1146 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 1147 */     getElementAs((TypedValueDecoder)dec);
/* 1148 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public byte[] getElementAsBinary() throws XMLStreamException {
/* 1153 */     return getElementAsBinary(Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public byte[] getElementAsBinary(Base64Variant v) throws XMLStreamException {
/* 1159 */     Stax2Util.ByteAggregator aggr = _base64Decoder().getByteAggregator();
/* 1160 */     byte[] buffer = aggr.startAggregation();
/*      */     while (true) {
/* 1162 */       int offset = 0;
/* 1163 */       int len = buffer.length;
/*      */       while (true) {
/* 1165 */         int readCount = readElementAsBinary(buffer, offset, len, v);
/* 1166 */         if (readCount < 1)
/* 1167 */           return aggr.aggregateAll(buffer, offset); 
/* 1169 */         offset += readCount;
/* 1170 */         len -= readCount;
/* 1171 */         if (len <= 0)
/* 1172 */           buffer = aggr.addFullBlock(buffer); 
/*      */       } 
/*      */       break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
/* 1178 */     String value = getElementText();
/* 1179 */     value = Stax2Util.trimSpaces(value);
/*      */     try {
/* 1181 */       if (value == null) {
/* 1182 */         tvd.handleEmptyValue();
/*      */       } else {
/* 1184 */         tvd.decode(value);
/*      */       } 
/* 1186 */     } catch (IllegalArgumentException iae) {
/* 1187 */       throw _constructTypeException(iae, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int readElementAsIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 1193 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getIntArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 1198 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getLongArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 1203 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getFloatArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 1208 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getDoubleArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsArray(TypedArrayDecoder tad) throws XMLStreamException {
/* 1216 */     if (this._currEvent == 1) {
/* 1218 */       Node fc = this._currNode.getFirstChild();
/* 1219 */       if (fc == null) {
/* 1220 */         this._currEvent = 2;
/* 1221 */         return -1;
/*      */       } 
/* 1223 */       this._coalescedText = coalesceTypedText(fc);
/* 1224 */       this._currEvent = 4;
/* 1225 */       this._currNode = this._currNode.getLastChild();
/*      */     } else {
/* 1227 */       if (this._currEvent != 4 && this._currEvent != 12) {
/* 1229 */         if (this._currEvent == 2)
/* 1230 */           return -1; 
/* 1232 */         reportWrongState(6);
/*      */       } 
/* 1240 */       if (this._coalescedText == null)
/* 1241 */         throw new IllegalStateException("First call to readElementAsArray() must be for a START_ELEMENT, not directly for a textual event"); 
/*      */     } 
/* 1248 */     String input = this._coalescedText;
/* 1249 */     int end = input.length();
/* 1250 */     int ptr = 0;
/* 1251 */     int count = 0;
/* 1252 */     String value = null;
/*      */     try {
/* 1256 */       label48: while (ptr < end) {
/* 1258 */         while (input.charAt(ptr) <= ' ') {
/* 1259 */           if (++ptr >= end)
/*      */             break label48; 
/*      */         } 
/* 1264 */         int start = ptr;
/* 1265 */         ptr++;
/* 1266 */         while (ptr < end && input.charAt(ptr) > ' ')
/* 1267 */           ptr++; 
/* 1269 */         count++;
/* 1271 */         value = input.substring(start, ptr);
/* 1273 */         ptr++;
/* 1274 */         if (tad.decodeValue(value))
/*      */           break; 
/*      */       } 
/* 1278 */     } catch (IllegalArgumentException iae) {
/* 1283 */       Location loc = getLocation();
/* 1284 */       throw new TypedXMLStreamException(value, iae.getMessage(), loc, iae);
/*      */     } finally {
/* 1286 */       int len = end - ptr;
/* 1287 */       this._coalescedText = (len < 1) ? "" : input.substring(ptr);
/*      */     } 
/* 1290 */     if (count < 1) {
/* 1291 */       this._currEvent = 2;
/* 1292 */       this._currNode = this._currNode.getParentNode();
/* 1293 */       return -1;
/*      */     } 
/* 1295 */     return count;
/*      */   }
/*      */   
/*      */   private String coalesceTypedText(Node firstNode) throws XMLStreamException {
/* 1305 */     this._textBuffer.reset();
/* 1306 */     this._attrList = null;
/* 1308 */     for (Node n = firstNode; n != null; n = n.getNextSibling()) {
/* 1309 */       switch (n.getNodeType()) {
/*      */         case 1:
/* 1312 */           throwStreamException("Element content can not contain child START_ELEMENT when using Typed Access methods");
/*      */         case 3:
/*      */         case 4:
/* 1315 */           this._textBuffer.append(n.getNodeValue());
/*      */           break;
/*      */         case 7:
/*      */         case 8:
/*      */           break;
/*      */         default:
/* 1324 */           throwStreamException("Unexpected DOM node type (" + n.getNodeType() + ") when trying to decode Typed content");
/*      */           break;
/*      */       } 
/*      */     } 
/* 1327 */     return this._textBuffer.get();
/*      */   }
/*      */   
/*      */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws XMLStreamException {
/* 1339 */     return readElementAsBinary(resultBuffer, offset, maxLength, Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength, Base64Variant v) throws XMLStreamException {
/* 1346 */     if (resultBuffer == null)
/* 1347 */       throw new IllegalArgumentException("resultBuffer is null"); 
/* 1349 */     if (offset < 0)
/* 1350 */       throw new IllegalArgumentException("Illegal offset (" + offset + "), must be [0, " + resultBuffer.length + "["); 
/* 1352 */     if (maxLength < 1 || offset + maxLength > resultBuffer.length) {
/* 1353 */       if (maxLength == 0)
/* 1354 */         return 0; 
/* 1356 */       throw new IllegalArgumentException("Illegal maxLength (" + maxLength + "), has to be positive number, and offset+maxLength can not exceed" + resultBuffer.length);
/*      */     } 
/* 1359 */     StringBase64Decoder dec = _base64Decoder();
/* 1360 */     int type = this._currEvent;
/* 1362 */     if ((1 << type & 0x1052) == 0)
/* 1363 */       if (type == 2) {
/* 1365 */         if (!dec.hasData())
/* 1366 */           return -1; 
/*      */       } else {
/* 1369 */         reportWrongState(6);
/*      */       }  
/* 1374 */     if (type == 1) {
/*      */       while (true) {
/* 1377 */         type = next();
/* 1378 */         if (type == 2)
/* 1380 */           return -1; 
/* 1382 */         if (type == 5 || type == 3)
/*      */           continue; 
/*      */         break;
/*      */       } 
/* 1385 */       if ((1 << type & 0x1250) == 0)
/* 1386 */         reportParseProblem(4); 
/* 1388 */       dec.init(v, true, getText());
/*      */     } 
/* 1393 */     int totalCount = 0;
/*      */     while (true) {
/*      */       int count;
/*      */       try {
/* 1400 */         count = dec.decode(resultBuffer, offset, maxLength);
/* 1401 */       } catch (IllegalArgumentException iae) {
/* 1402 */         throw _constructTypeException(iae, "");
/*      */       } 
/* 1404 */       offset += count;
/* 1405 */       totalCount += count;
/* 1406 */       maxLength -= count;
/* 1411 */       if (maxLength < 1 || this._currEvent == 2)
/*      */         break; 
/*      */       while (true) {
/* 1416 */         type = next();
/* 1417 */         if (type == 5 || type == 3 || type == 6)
/*      */           continue; 
/*      */         break;
/*      */       } 
/* 1421 */       if (type == 2) {
/* 1428 */         int left = dec.endOfContent();
/* 1429 */         if (left < 0)
/* 1430 */           throw _constructTypeException("Incomplete base64 triplet at the end of decoded content", ""); 
/* 1431 */         if (left > 0)
/*      */           continue; 
/*      */         break;
/*      */       } 
/* 1437 */       if ((1 << type & 0x1250) == 0)
/* 1438 */         reportParseProblem(4); 
/* 1440 */       dec.init(v, false, getText());
/*      */     } 
/* 1446 */     return (totalCount > 0) ? totalCount : -1;
/*      */   }
/*      */   
/*      */   public int getAttributeIndex(String namespaceURI, String localName) {
/* 1457 */     return findAttributeIndex(namespaceURI, localName);
/*      */   }
/*      */   
/*      */   public boolean getAttributeAsBoolean(int index) throws XMLStreamException {
/* 1462 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 1463 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1464 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public int getAttributeAsInt(int index) throws XMLStreamException {
/* 1469 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 1470 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1471 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public long getAttributeAsLong(int index) throws XMLStreamException {
/* 1476 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 1477 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1478 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public float getAttributeAsFloat(int index) throws XMLStreamException {
/* 1483 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 1484 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1485 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public double getAttributeAsDouble(int index) throws XMLStreamException {
/* 1490 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 1491 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1492 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigInteger getAttributeAsInteger(int index) throws XMLStreamException {
/* 1497 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 1498 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1499 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigDecimal getAttributeAsDecimal(int index) throws XMLStreamException {
/* 1504 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 1505 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1506 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public QName getAttributeAsQName(int index) throws XMLStreamException {
/* 1511 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 1512 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 1513 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public final void getAttributeAs(int index, TypedValueDecoder tvd) throws XMLStreamException {
/* 1518 */     String value = getAttributeValue(index);
/* 1519 */     value = Stax2Util.trimSpaces(value);
/*      */     try {
/* 1521 */       if (value == null) {
/* 1522 */         tvd.handleEmptyValue();
/*      */       } else {
/* 1524 */         tvd.decode(value);
/*      */       } 
/* 1526 */     } catch (IllegalArgumentException iae) {
/* 1527 */       throw _constructTypeException(iae, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
/* 1533 */     ValueDecoderFactory.IntArrayDecoder dec = _decoderFactory().getIntArrayDecoder();
/* 1534 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/* 1535 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
/* 1540 */     ValueDecoderFactory.LongArrayDecoder dec = _decoderFactory().getLongArrayDecoder();
/* 1541 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/* 1542 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
/* 1547 */     ValueDecoderFactory.FloatArrayDecoder dec = _decoderFactory().getFloatArrayDecoder();
/* 1548 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/* 1549 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
/* 1554 */     ValueDecoderFactory.DoubleArrayDecoder dec = _decoderFactory().getDoubleArrayDecoder();
/* 1555 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/* 1556 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public int getAttributeAsArray(int index, TypedArrayDecoder tad) throws XMLStreamException {
/* 1561 */     return _getAttributeAsArray(tad, getAttributeValue(index));
/*      */   }
/*      */   
/*      */   protected int _getAttributeAsArray(TypedArrayDecoder tad, String attrValue) throws XMLStreamException {
/* 1566 */     int ptr = 0;
/* 1567 */     int start = 0;
/* 1568 */     int end = attrValue.length();
/* 1569 */     String lexical = null;
/* 1570 */     int count = 0;
/*      */     try {
/* 1574 */       while (ptr < end) {
/* 1576 */         while (attrValue.charAt(ptr) <= ' ') {
/* 1577 */           if (++ptr >= end)
/*      */             // Byte code: goto -> 145 
/*      */         } 
/* 1582 */         start = ptr;
/* 1583 */         ptr++;
/* 1584 */         while (ptr < end && attrValue.charAt(ptr) > ' ')
/* 1585 */           ptr++; 
/* 1587 */         int tokenEnd = ptr;
/* 1588 */         ptr++;
/* 1590 */         lexical = attrValue.substring(start, tokenEnd);
/* 1591 */         count++;
/* 1592 */         if (tad.decodeValue(lexical) && 
/* 1593 */           !checkExpand(tad))
/*      */           break; 
/*      */       } 
/* 1598 */     } catch (IllegalArgumentException iae) {
/* 1600 */       Location loc = getLocation();
/* 1601 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), loc, iae);
/*      */     } 
/* 1603 */     return count;
/*      */   }
/*      */   
/*      */   private final boolean checkExpand(TypedArrayDecoder tad) {
/* 1614 */     if (tad instanceof ValueDecoderFactory.BaseArrayDecoder) {
/* 1615 */       ((ValueDecoderFactory.BaseArrayDecoder)tad).expand();
/* 1616 */       return true;
/*      */     } 
/* 1618 */     return false;
/*      */   }
/*      */   
/*      */   public byte[] getAttributeAsBinary(int index) throws XMLStreamException {
/* 1623 */     return getAttributeAsBinary(index, Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public byte[] getAttributeAsBinary(int index, Base64Variant v) throws XMLStreamException {
/* 1628 */     String lexical = getAttributeValue(index);
/* 1629 */     StringBase64Decoder dec = _base64Decoder();
/* 1630 */     dec.init(v, true, lexical);
/*      */     try {
/* 1632 */       return dec.decodeCompletely();
/* 1633 */     } catch (IllegalArgumentException iae) {
/* 1634 */       throw _constructTypeException(iae, lexical);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getFeature(String name) {
/* 1649 */     throw new IllegalArgumentException("Unrecognized feature \"" + name + "\"");
/*      */   }
/*      */   
/*      */   public void setFeature(String name, Object value) {
/* 1654 */     throw new IllegalArgumentException("Unrecognized feature \"" + name + "\"");
/*      */   }
/*      */   
/*      */   public void skipElement() throws XMLStreamException {
/* 1661 */     if (this._currEvent != 1)
/* 1662 */       reportWrongState(1); 
/* 1664 */     int nesting = 1;
/*      */     while (true) {
/* 1667 */       int type = next();
/* 1668 */       if (type == 1) {
/* 1669 */         nesting++;
/*      */         continue;
/*      */       } 
/* 1670 */       if (type == 2 && 
/* 1671 */         --nesting == 0)
/*      */         break; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public AttributeInfo getAttributeInfo() throws XMLStreamException {
/* 1682 */     if (this._currEvent != 1)
/* 1683 */       reportWrongState(1); 
/* 1685 */     return this;
/*      */   }
/*      */   
/*      */   public int findAttributeIndex(String nsURI, String localName) {
/* 1694 */     if (this._currEvent != 1)
/* 1695 */       reportWrongState(1); 
/* 1697 */     Element elem = (Element)this._currNode;
/* 1698 */     NamedNodeMap attrs = elem.getAttributes();
/* 1699 */     if (nsURI != null && nsURI.length() == 0)
/* 1700 */       nsURI = null; 
/* 1703 */     for (int i = 0, len = attrs.getLength(); i < len; i++) {
/* 1704 */       Node attr = attrs.item(i);
/* 1705 */       String ln = _safeGetLocalName(attr);
/* 1706 */       if (localName.equals(ln)) {
/* 1707 */         String thisUri = attr.getNamespaceURI();
/* 1708 */         boolean isEmpty = (thisUri == null || thisUri.length() == 0);
/* 1709 */         if (nsURI == null) {
/* 1710 */           if (isEmpty)
/* 1711 */             return i; 
/* 1714 */         } else if (!isEmpty && nsURI.equals(thisUri)) {
/* 1715 */           return i;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1720 */     return -1;
/*      */   }
/*      */   
/*      */   public int getIdAttributeIndex() {
/* 1729 */     return -1;
/*      */   }
/*      */   
/*      */   public int getNotationAttributeIndex() {
/* 1738 */     return -1;
/*      */   }
/*      */   
/*      */   public DTDInfo getDTDInfo() throws XMLStreamException {
/* 1752 */     if (this._currEvent != 11)
/* 1753 */       return null; 
/* 1755 */     return this;
/*      */   }
/*      */   
/*      */   public final LocationInfo getLocationInfo() {
/* 1764 */     return this;
/*      */   }
/*      */   
/*      */   public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
/* 1794 */     String text = getText();
/* 1795 */     w.write(text);
/* 1796 */     return text.length();
/*      */   }
/*      */   
/*      */   public int getDepth() {
/* 1806 */     return this._depth;
/*      */   }
/*      */   
/*      */   public boolean isEmptyElement() throws XMLStreamException {
/* 1817 */     return false;
/*      */   }
/*      */   
/*      */   public NamespaceContext getNonTransientNamespaceContext() {
/* 1827 */     return (NamespaceContext)EmptyNamespaceContext.getInstance();
/*      */   }
/*      */   
/*      */   public String getPrefixedName() {
/*      */     String prefix;
/*      */     String ln;
/*      */     StringBuffer sb;
/* 1832 */     switch (this._currEvent) {
/*      */       case 1:
/*      */       case 2:
/* 1836 */         prefix = this._currNode.getPrefix();
/* 1837 */         ln = _safeGetLocalName(this._currNode);
/* 1839 */         if (prefix == null)
/* 1840 */           return _internName(ln); 
/* 1842 */         sb = new StringBuffer(ln.length() + 1 + prefix.length());
/* 1843 */         sb.append(prefix);
/* 1844 */         sb.append(':');
/* 1845 */         sb.append(ln);
/* 1846 */         return _internName(sb.toString());
/*      */       case 9:
/* 1849 */         return getLocalName();
/*      */       case 3:
/* 1851 */         return getPITarget();
/*      */       case 11:
/* 1853 */         return getDTDRootName();
/*      */     } 
/* 1856 */     throw new IllegalStateException("Current state (" + Stax2Util.eventTypeDesc(this._currEvent) + ") not START_ELEMENT, END_ELEMENT, ENTITY_REFERENCE, PROCESSING_INSTRUCTION or DTD");
/*      */   }
/*      */   
/*      */   public void closeCompletely() throws XMLStreamException {}
/*      */   
/*      */   public Object getProcessedDTD() {
/* 1871 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDRootName() {
/* 1875 */     if (this._currEvent == 11)
/* 1876 */       return _internName(((DocumentType)this._currNode).getName()); 
/* 1878 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDPublicId() {
/* 1882 */     if (this._currEvent == 11)
/* 1883 */       return ((DocumentType)this._currNode).getPublicId(); 
/* 1885 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDSystemId() {
/* 1889 */     if (this._currEvent == 11)
/* 1890 */       return ((DocumentType)this._currNode).getSystemId(); 
/* 1892 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDInternalSubset() {
/* 1904 */     return null;
/*      */   }
/*      */   
/*      */   public DTDValidationSchema getProcessedDTDSchema() {
/* 1910 */     return null;
/*      */   }
/*      */   
/*      */   public long getStartingByteOffset() {
/* 1923 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getStartingCharOffset() {
/* 1928 */     return 0L;
/*      */   }
/*      */   
/*      */   public long getEndingByteOffset() throws XMLStreamException {
/* 1934 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getEndingCharOffset() throws XMLStreamException {
/* 1940 */     return -1L;
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getStartLocation() {
/* 1947 */     return XMLStreamLocation2.NOT_AVAILABLE;
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getCurrentLocation() {
/* 1952 */     return XMLStreamLocation2.NOT_AVAILABLE;
/*      */   }
/*      */   
/*      */   public final XMLStreamLocation2 getEndLocation() throws XMLStreamException {
/* 1958 */     return XMLStreamLocation2.NOT_AVAILABLE;
/*      */   }
/*      */   
/*      */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 1971 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 1978 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 1985 */     return null;
/*      */   }
/*      */   
/*      */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 1991 */     return null;
/*      */   }
/*      */   
/*      */   protected void coalesceText(int initialType) {
/* 2002 */     this._textBuffer.reset();
/* 2003 */     this._textBuffer.append(this._currNode.getNodeValue());
/*      */     Node n;
/* 2006 */     while ((n = this._currNode.getNextSibling()) != null) {
/* 2007 */       int type = n.getNodeType();
/* 2008 */       if (type != 3 && type != 4)
/*      */         break; 
/* 2011 */       this._currNode = n;
/* 2012 */       this._textBuffer.append(this._currNode.getNodeValue());
/*      */     } 
/* 2014 */     this._coalescedText = this._textBuffer.get();
/* 2017 */     this._currEvent = 4;
/*      */   }
/*      */   
/*      */   private QName _constructQName(String uri, String ln, String prefix) {
/* 2029 */     return new QName(_internNsURI(uri), _internName(ln), _internName(prefix));
/*      */   }
/*      */   
/*      */   private void _calcNsAndAttrLists(boolean attrsToo) {
/* 2038 */     NamedNodeMap attrsIn = this._currNode.getAttributes();
/* 2041 */     int len = attrsIn.getLength();
/* 2042 */     if (len == 0) {
/* 2043 */       this._attrList = this._nsDeclList = Collections.EMPTY_LIST;
/*      */       return;
/*      */     } 
/* 2047 */     if (!this._cfgNsAware) {
/* 2048 */       this._attrList = new ArrayList(len);
/* 2049 */       for (int j = 0; j < len; j++)
/* 2050 */         this._attrList.add(attrsIn.item(j)); 
/* 2052 */       this._nsDeclList = Collections.EMPTY_LIST;
/*      */       return;
/*      */     } 
/* 2057 */     ArrayList attrsOut = null;
/* 2058 */     ArrayList nsOut = null;
/* 2060 */     int i = 0;
/*      */     while (true) {
/* 2060 */       if (i < len) {
/* 2061 */         Node attr = attrsIn.item(i);
/* 2062 */         String prefix = attr.getPrefix();
/* 2065 */         if (prefix == null || prefix.length() == 0) {
/* 2067 */           if (!"xmlns".equals(attr.getLocalName())) {
/* 2068 */             if (attrsToo) {
/* 2069 */               if (attrsOut == null)
/* 2070 */                 attrsOut = new ArrayList(len - i); 
/* 2072 */               attrsOut.add(attr);
/*      */             } 
/*      */           } else {
/* 2076 */             prefix = null;
/* 2089 */             if (nsOut == null)
/* 2090 */               nsOut = new ArrayList((len - i) * 2); 
/*      */           } 
/*      */         } else if (!"xmlns".equals(prefix)) {
/*      */           if (attrsToo) {
/*      */             if (attrsOut == null)
/*      */               attrsOut = new ArrayList(len - i); 
/*      */             attrsOut.add(attr);
/*      */           } 
/*      */         } else {
/*      */           prefix = attr.getLocalName();
/*      */           if (nsOut == null)
/* 2090 */             nsOut = new ArrayList((len - i) * 2); 
/*      */         } 
/*      */       } else {
/*      */         break;
/*      */       } 
/*      */       i++;
/*      */     } 
/* 2096 */     this._attrList = (attrsOut == null) ? Collections.EMPTY_LIST : attrsOut;
/* 2097 */     this._nsDeclList = (nsOut == null) ? Collections.EMPTY_LIST : nsOut;
/*      */   }
/*      */   
/*      */   private void handleIllegalAttrIndex(int index) {
/* 2102 */     Element elem = (Element)this._currNode;
/* 2103 */     NamedNodeMap attrs = elem.getAttributes();
/* 2104 */     int len = attrs.getLength();
/* 2105 */     String msg = "Illegal attribute index " + index + "; element <" + elem.getNodeName() + "> has " + ((len == 0) ? "no" : String.valueOf(len)) + " attributes";
/* 2106 */     throw new IllegalArgumentException(msg);
/*      */   }
/*      */   
/*      */   private void handleIllegalNsIndex(int index) {
/* 2111 */     String msg = "Illegal namespace declaration index " + index + " (has " + getNamespaceCount() + " ns declarations)";
/* 2112 */     throw new IllegalArgumentException(msg);
/*      */   }
/*      */   
/*      */   private String _safeGetLocalName(Node n) {
/* 2124 */     String ln = n.getLocalName();
/* 2125 */     if (ln == null)
/* 2126 */       ln = n.getNodeName(); 
/* 2128 */     return ln;
/*      */   }
/*      */   
/*      */   protected void reportWrongState(int errorType) {
/* 2139 */     throw new IllegalStateException(findErrorDesc(errorType, this._currEvent));
/*      */   }
/*      */   
/*      */   protected void reportParseProblem(int errorType) throws XMLStreamException {
/* 2145 */     throwStreamException(findErrorDesc(errorType, this._currEvent));
/*      */   }
/*      */   
/*      */   protected void throwStreamException(String msg) throws XMLStreamException {
/* 2151 */     throwStreamException(msg, getErrorLocation());
/*      */   }
/*      */   
/*      */   protected Location getErrorLocation() {
/*      */     Location location;
/* 2156 */     XMLStreamLocation2 xMLStreamLocation2 = getCurrentLocation();
/* 2157 */     if (xMLStreamLocation2 == null)
/* 2158 */       location = getLocation(); 
/* 2160 */     return location;
/*      */   }
/*      */   
/*      */   protected TypedXMLStreamException _constructTypeException(IllegalArgumentException iae, String lexicalValue) {
/* 2173 */     String msg = iae.getMessage();
/* 2174 */     if (msg == null)
/* 2175 */       msg = ""; 
/* 2177 */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/* 2178 */     if (xMLStreamLocation2 == null)
/* 2179 */       return new TypedXMLStreamException(lexicalValue, msg, iae); 
/* 2181 */     return new TypedXMLStreamException(lexicalValue, msg, (Location)xMLStreamLocation2);
/*      */   }
/*      */   
/*      */   protected TypedXMLStreamException _constructTypeException(String msg, String lexicalValue) {
/* 2186 */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/* 2187 */     if (xMLStreamLocation2 == null)
/* 2188 */       return new TypedXMLStreamException(lexicalValue, msg); 
/* 2190 */     return new TypedXMLStreamException(lexicalValue, msg, (Location)xMLStreamLocation2);
/*      */   }
/*      */   
/*      */   protected ValueDecoderFactory _decoderFactory() {
/* 2201 */     if (this._decoderFactory == null)
/* 2202 */       this._decoderFactory = new ValueDecoderFactory(); 
/* 2204 */     return this._decoderFactory;
/*      */   }
/*      */   
/*      */   protected StringBase64Decoder _base64Decoder() {
/* 2209 */     if (this._base64Decoder == null)
/* 2210 */       this._base64Decoder = new StringBase64Decoder(); 
/* 2212 */     return this._base64Decoder;
/*      */   }
/*      */   
/*      */   protected String findErrorDesc(int errorType, int currEvent) {
/* 2222 */     String evtDesc = Stax2Util.eventTypeDesc(currEvent);
/* 2223 */     switch (errorType) {
/*      */       case 1:
/* 2225 */         return "Current event " + evtDesc + ", needs to be START_ELEMENT";
/*      */       case 2:
/* 2227 */         return "Current event " + evtDesc + ", needs to be START_ELEMENT or END_ELEMENT";
/*      */       case 7:
/* 2229 */         return "Current event (" + evtDesc + ") has no local name";
/*      */       case 3:
/* 2231 */         return "Current event (" + evtDesc + ") needs to be PROCESSING_INSTRUCTION";
/*      */       case 4:
/* 2234 */         return "Current event (" + evtDesc + ") not a textual event";
/*      */       case 6:
/* 2236 */         return "Current event (" + evtDesc + " not START_ELEMENT, END_ELEMENT, CHARACTERS or CDATA";
/*      */       case 5:
/* 2238 */         return "Current event " + evtDesc + ", needs to be one of CHARACTERS, CDATA, SPACE or COMMENT";
/*      */     } 
/* 2241 */     return "Internal error (unrecognized error type: " + errorType + ")";
/*      */   }
/*      */   
/*      */   protected String _internName(String name) {
/* 2250 */     if (name == null)
/* 2251 */       return ""; 
/* 2253 */     return this._cfgInternNames ? name.intern() : name;
/*      */   }
/*      */   
/*      */   protected String _internNsURI(String uri) {
/* 2258 */     if (uri == null)
/* 2259 */       return ""; 
/* 2261 */     return this._cfgInternNsURIs ? uri.intern() : uri;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\dom\DOMWrappingReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */