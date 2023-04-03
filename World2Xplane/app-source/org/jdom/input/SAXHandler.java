/*      */ package org.jdom.input;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.jdom.Attribute;
/*      */ import org.jdom.Content;
/*      */ import org.jdom.DefaultJDOMFactory;
/*      */ import org.jdom.Document;
/*      */ import org.jdom.Element;
/*      */ import org.jdom.EntityRef;
/*      */ import org.jdom.JDOMFactory;
/*      */ import org.jdom.Namespace;
/*      */ import org.jdom.Parent;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ public class SAXHandler extends DefaultHandler implements LexicalHandler, DeclHandler, DTDHandler {
/*      */   private static final String CVS_ID = "@(#) $RCSfile: SAXHandler.java,v $ $Revision: 1.73 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*      */   
/*  100 */   private static final Map attrNameToTypeMap = new HashMap(13);
/*      */   
/*      */   private Document document;
/*      */   
/*      */   private Element currentElement;
/*      */   
/*      */   private boolean atRoot;
/*      */   
/*      */   private boolean inDTD = false;
/*      */   
/*      */   private boolean inInternalSubset = false;
/*      */   
/*      */   private boolean previousCDATA = false;
/*      */   
/*      */   private boolean inCDATA = false;
/*      */   
/*      */   private boolean expand = true;
/*      */   
/*      */   private boolean suppress = false;
/*      */   
/*  133 */   private int entityDepth = 0;
/*      */   
/*      */   private List declaredNamespaces;
/*      */   
/*  140 */   private StringBuffer internalSubset = new StringBuffer();
/*      */   
/*  143 */   private TextBuffer textBuffer = new TextBuffer();
/*      */   
/*      */   private Map externalEntities;
/*      */   
/*      */   private JDOMFactory factory;
/*      */   
/*      */   private boolean ignoringWhite = false;
/*      */   
/*      */   private boolean ignoringBoundaryWhite = false;
/*      */   
/*      */   private Locator locator;
/*      */   
/*      */   static {
/*  177 */     attrNameToTypeMap.put("CDATA", new Integer(1));
/*  179 */     attrNameToTypeMap.put("ID", new Integer(2));
/*  181 */     attrNameToTypeMap.put("IDREF", new Integer(3));
/*  183 */     attrNameToTypeMap.put("IDREFS", new Integer(4));
/*  185 */     attrNameToTypeMap.put("ENTITY", new Integer(5));
/*  187 */     attrNameToTypeMap.put("ENTITIES", new Integer(6));
/*  189 */     attrNameToTypeMap.put("NMTOKEN", new Integer(7));
/*  191 */     attrNameToTypeMap.put("NMTOKENS", new Integer(8));
/*  193 */     attrNameToTypeMap.put("NOTATION", new Integer(9));
/*  195 */     attrNameToTypeMap.put("ENUMERATION", new Integer(10));
/*      */   }
/*      */   
/*      */   public SAXHandler() {
/*  205 */     this(null);
/*      */   }
/*      */   
/*      */   public SAXHandler(JDOMFactory factory) {
/*  217 */     if (factory != null) {
/*  218 */       this.factory = factory;
/*      */     } else {
/*  220 */       this.factory = (JDOMFactory)new DefaultJDOMFactory();
/*      */     } 
/*  223 */     this.atRoot = true;
/*  224 */     this.declaredNamespaces = new ArrayList();
/*  225 */     this.externalEntities = new HashMap();
/*  227 */     this.document = this.factory.document(null);
/*      */   }
/*      */   
/*      */   protected void pushElement(Element element) {
/*  238 */     if (this.atRoot) {
/*  239 */       this.document.setRootElement(element);
/*  240 */       this.atRoot = false;
/*      */     } else {
/*  243 */       this.factory.addContent((Parent)this.currentElement, (Content)element);
/*      */     } 
/*  245 */     this.currentElement = element;
/*      */   }
/*      */   
/*      */   public Document getDocument() {
/*  254 */     return this.document;
/*      */   }
/*      */   
/*      */   public JDOMFactory getFactory() {
/*  266 */     return this.factory;
/*      */   }
/*      */   
/*      */   public void setExpandEntities(boolean expand) {
/*  279 */     this.expand = expand;
/*      */   }
/*      */   
/*      */   public boolean getExpandEntities() {
/*  292 */     return this.expand;
/*      */   }
/*      */   
/*      */   public void setIgnoringElementContentWhitespace(boolean ignoringWhite) {
/*  307 */     this.ignoringWhite = ignoringWhite;
/*      */   }
/*      */   
/*      */   public void setIgnoringBoundaryWhitespace(boolean ignoringBoundaryWhite) {
/*  318 */     this.ignoringBoundaryWhite = ignoringBoundaryWhite;
/*      */   }
/*      */   
/*      */   public boolean getIgnoringBoundaryWhitespace() {
/*  331 */     return this.ignoringBoundaryWhite;
/*      */   }
/*      */   
/*      */   public boolean getIgnoringElementContentWhitespace() {
/*  345 */     return this.ignoringWhite;
/*      */   }
/*      */   
/*      */   public void startDocument() {
/*  349 */     if (this.locator != null)
/*  350 */       this.document.setBaseURI(this.locator.getSystemId()); 
/*      */   }
/*      */   
/*      */   public void externalEntityDecl(String name, String publicID, String systemID) throws SAXException {
/*  367 */     this.externalEntities.put(name, new String[] { publicID, systemID });
/*  369 */     if (!this.inInternalSubset)
/*      */       return; 
/*  371 */     this.internalSubset.append("  <!ENTITY ").append(name);
/*  373 */     appendExternalId(publicID, systemID);
/*  374 */     this.internalSubset.append(">\n");
/*      */   }
/*      */   
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
/*  391 */     if (!this.inInternalSubset)
/*      */       return; 
/*  393 */     this.internalSubset.append("  <!ATTLIST ").append(eName).append(' ').append(aName).append(' ').append(type).append(' ');
/*  400 */     if (valueDefault != null) {
/*  401 */       this.internalSubset.append(valueDefault);
/*      */     } else {
/*  403 */       this.internalSubset.append('"').append(value).append('"');
/*      */     } 
/*  407 */     if (valueDefault != null && valueDefault.equals("#FIXED"))
/*  408 */       this.internalSubset.append(" \"").append(value).append('"'); 
/*  412 */     this.internalSubset.append(">\n");
/*      */   }
/*      */   
/*      */   public void elementDecl(String name, String model) throws SAXException {
/*  424 */     if (!this.inInternalSubset)
/*      */       return; 
/*  426 */     this.internalSubset.append("  <!ELEMENT ").append(name).append(' ').append(model).append(">\n");
/*      */   }
/*      */   
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {
/*  444 */     if (!this.inInternalSubset)
/*      */       return; 
/*  446 */     this.internalSubset.append("  <!ENTITY ");
/*  447 */     if (name.startsWith("%")) {
/*  448 */       this.internalSubset.append("% ").append(name.substring(1));
/*      */     } else {
/*  450 */       this.internalSubset.append(name);
/*      */     } 
/*  452 */     this.internalSubset.append(" \"").append(value).append("\">\n");
/*      */   }
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  471 */     if (this.suppress)
/*      */       return; 
/*  473 */     flushCharacters();
/*  475 */     if (this.atRoot) {
/*  476 */       this.factory.addContent((Parent)this.document, (Content)this.factory.processingInstruction(target, data));
/*      */     } else {
/*  478 */       this.factory.addContent((Parent)getCurrentElement(), (Content)this.factory.processingInstruction(target, data));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void skippedEntity(String name) throws SAXException {
/*  495 */     if (name.startsWith("%"))
/*      */       return; 
/*  497 */     flushCharacters();
/*  499 */     this.factory.addContent((Parent)getCurrentElement(), (Content)this.factory.entityRef(name));
/*      */   }
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  512 */     if (this.suppress)
/*      */       return; 
/*  514 */     Namespace ns = Namespace.getNamespace(prefix, uri);
/*  515 */     this.declaredNamespaces.add(ns);
/*      */   }
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/*  538 */     if (this.suppress)
/*      */       return; 
/*  539 */     String prefix = "";
/*  542 */     if (!"".equals(qName)) {
/*  543 */       int colon = qName.indexOf(':');
/*  545 */       if (colon > 0)
/*  546 */         prefix = qName.substring(0, colon); 
/*  550 */       if (localName == null || localName.equals(""))
/*  551 */         localName = qName.substring(colon + 1); 
/*      */     } 
/*  557 */     Namespace namespace = Namespace.getNamespace(prefix, namespaceURI);
/*  558 */     Element element = this.factory.element(localName, namespace);
/*  562 */     if (this.declaredNamespaces.size() > 0)
/*  563 */       transferNamespaces(element); 
/*  566 */     flushCharacters();
/*  568 */     if (this.atRoot) {
/*  569 */       this.document.setRootElement(element);
/*  570 */       this.atRoot = false;
/*      */     } else {
/*  572 */       this.factory.addContent((Parent)getCurrentElement(), (Content)element);
/*      */     } 
/*  574 */     this.currentElement = element;
/*  577 */     for (int i = 0, len = atts.getLength(); i < len; i++) {
/*  579 */       String attPrefix = "";
/*  580 */       String attLocalName = atts.getLocalName(i);
/*  581 */       String attQName = atts.getQName(i);
/*  585 */       if (!attQName.equals("")) {
/*  590 */         if (attQName.startsWith("xmlns:") || attQName.equals("xmlns"))
/*      */           continue; 
/*  594 */         int attColon = attQName.indexOf(':');
/*  596 */         if (attColon > 0)
/*  597 */           attPrefix = attQName.substring(0, attColon); 
/*  601 */         if ("".equals(attLocalName))
/*  602 */           attLocalName = attQName.substring(attColon + 1); 
/*      */       } 
/*  608 */       int attType = getAttributeType(atts.getType(i));
/*  609 */       String attValue = atts.getValue(i);
/*  610 */       String attURI = atts.getURI(i);
/*  612 */       if (!"xmlns".equals(attLocalName) && !"xmlns".equals(attPrefix) && !"http://www.w3.org/2000/xmlns/".equals(attURI)) {
/*  623 */         if (!"".equals(attURI) && "".equals(attPrefix)) {
/*  633 */           Element p = element;
/*  638 */           HashSet overrides = new HashSet();
/*      */           label79: do {
/*  642 */             if (p.getNamespace().getURI().equals(attURI) && !overrides.contains(p.getNamespacePrefix()) && !"".equals(element.getNamespace().getPrefix())) {
/*  647 */               attPrefix = p.getNamespacePrefix();
/*      */               break;
/*      */             } 
/*  650 */             overrides.add(p.getNamespacePrefix());
/*  651 */             Iterator iterator = p.getAdditionalNamespaces().iterator();
/*  652 */             while (iterator.hasNext()) {
/*  653 */               Namespace ns = iterator.next();
/*  654 */               if (!overrides.contains(ns.getPrefix()) && attURI.equals(ns.getURI())) {
/*  656 */                 attPrefix = ns.getPrefix();
/*      */                 break label79;
/*      */               } 
/*  659 */               overrides.add(ns.getPrefix());
/*      */             } 
/*  661 */             Iterator it = p.getAttributes().iterator();
/*  662 */             while (it.hasNext()) {
/*  663 */               Namespace ns = ((Attribute)it.next()).getNamespace();
/*  664 */               if (!overrides.contains(ns.getPrefix()) && attURI.equals(ns.getURI())) {
/*  666 */                 attPrefix = ns.getPrefix();
/*      */                 break label79;
/*      */               } 
/*  669 */               overrides.add(ns.getPrefix());
/*      */             } 
/*  671 */             p = p.getParentElement();
/*  672 */           } while (p != null);
/*  673 */           if ("".equals(attPrefix)) {
/*  685 */             int cnt = 0;
/*  686 */             String base = "attns";
/*  687 */             String pfx = base + cnt;
/*  688 */             while (overrides.contains(pfx)) {
/*  689 */               cnt++;
/*  690 */               pfx = base + cnt;
/*      */             } 
/*  692 */             attPrefix = pfx;
/*      */           } 
/*      */         } 
/*  695 */         Namespace attNs = Namespace.getNamespace(attPrefix, attURI);
/*  697 */         Attribute attribute = this.factory.attribute(attLocalName, attValue, attType, attNs);
/*  699 */         this.factory.setAttribute(element, attribute);
/*      */       } 
/*      */       continue;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void transferNamespaces(Element element) {
/*  710 */     Iterator i = this.declaredNamespaces.iterator();
/*  711 */     while (i.hasNext()) {
/*  712 */       Namespace ns = i.next();
/*  713 */       if (ns != element.getNamespace())
/*  714 */         element.addNamespaceDeclaration(ns); 
/*      */     } 
/*  717 */     this.declaredNamespaces.clear();
/*      */   }
/*      */   
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  731 */     if (this.suppress || length == 0)
/*      */       return; 
/*  734 */     if (this.previousCDATA != this.inCDATA)
/*  735 */       flushCharacters(); 
/*  738 */     this.textBuffer.append(ch, start, length);
/*      */   }
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  753 */     if (!this.ignoringWhite)
/*  754 */       characters(ch, start, length); 
/*      */   }
/*      */   
/*      */   protected void flushCharacters() throws SAXException {
/*  765 */     if (this.ignoringBoundaryWhite) {
/*  766 */       if (!this.textBuffer.isAllWhitespace())
/*  767 */         flushCharacters(this.textBuffer.toString()); 
/*      */     } else {
/*  771 */       flushCharacters(this.textBuffer.toString());
/*      */     } 
/*  773 */     this.textBuffer.clear();
/*      */   }
/*      */   
/*      */   protected void flushCharacters(String data) throws SAXException {
/*  784 */     if (data.length() == 0) {
/*  785 */       this.previousCDATA = this.inCDATA;
/*      */       return;
/*      */     } 
/*  800 */     if (this.previousCDATA) {
/*  801 */       this.factory.addContent((Parent)getCurrentElement(), (Content)this.factory.cdata(data));
/*      */     } else {
/*  804 */       this.factory.addContent((Parent)getCurrentElement(), (Content)this.factory.text(data));
/*      */     } 
/*  807 */     this.previousCDATA = this.inCDATA;
/*      */   }
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/*  825 */     if (this.suppress)
/*      */       return; 
/*  827 */     flushCharacters();
/*  829 */     if (!this.atRoot) {
/*  830 */       Parent p = this.currentElement.getParent();
/*  831 */       if (p instanceof Document) {
/*  832 */         this.atRoot = true;
/*      */       } else {
/*  835 */         this.currentElement = (Element)p;
/*      */       } 
/*      */     } else {
/*  839 */       throw new SAXException("Ill-formed XML document (missing opening tag for " + localName + ")");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void startDTD(String name, String publicID, String systemID) throws SAXException {
/*  858 */     flushCharacters();
/*  860 */     this.factory.addContent((Parent)this.document, (Content)this.factory.docType(name, publicID, systemID));
/*  861 */     this.inDTD = true;
/*  862 */     this.inInternalSubset = true;
/*      */   }
/*      */   
/*      */   public void endDTD() throws SAXException {
/*  872 */     this.document.getDocType().setInternalSubset(this.internalSubset.toString());
/*  873 */     this.inDTD = false;
/*  874 */     this.inInternalSubset = false;
/*      */   }
/*      */   
/*      */   public void startEntity(String name) throws SAXException {
/*  878 */     this.entityDepth++;
/*  880 */     if (this.expand || this.entityDepth > 1)
/*      */       return; 
/*  886 */     if (name.equals("[dtd]")) {
/*  887 */       this.inInternalSubset = false;
/*      */       return;
/*      */     } 
/*  892 */     if (!this.inDTD && !name.equals("amp") && !name.equals("lt") && !name.equals("gt") && !name.equals("apos") && !name.equals("quot"))
/*  899 */       if (!this.expand) {
/*  900 */         String pub = null;
/*  901 */         String sys = null;
/*  902 */         String[] ids = (String[])this.externalEntities.get(name);
/*  903 */         if (ids != null) {
/*  904 */           pub = ids[0];
/*  905 */           sys = ids[1];
/*      */         } 
/*  914 */         if (!this.atRoot) {
/*  915 */           flushCharacters();
/*  916 */           EntityRef entity = this.factory.entityRef(name, pub, sys);
/*  919 */           this.factory.addContent((Parent)getCurrentElement(), (Content)entity);
/*      */         } 
/*  921 */         this.suppress = true;
/*      */       }  
/*      */   }
/*      */   
/*      */   public void endEntity(String name) throws SAXException {
/*  927 */     this.entityDepth--;
/*  928 */     if (this.entityDepth == 0)
/*  931 */       this.suppress = false; 
/*  933 */     if (name.equals("[dtd]"))
/*  934 */       this.inInternalSubset = true; 
/*      */   }
/*      */   
/*      */   public void startCDATA() throws SAXException {
/*  944 */     if (this.suppress)
/*      */       return; 
/*  946 */     this.inCDATA = true;
/*      */   }
/*      */   
/*      */   public void endCDATA() throws SAXException {
/*  953 */     if (this.suppress)
/*      */       return; 
/*  955 */     this.previousCDATA = true;
/*  956 */     this.inCDATA = false;
/*      */   }
/*      */   
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  973 */     if (this.suppress)
/*      */       return; 
/*  975 */     flushCharacters();
/*  977 */     String commentText = new String(ch, start, length);
/*  978 */     if (this.inDTD && this.inInternalSubset && !this.expand) {
/*  979 */       this.internalSubset.append("  <!--").append(commentText).append("-->\n");
/*      */       return;
/*      */     } 
/*  984 */     if (!this.inDTD && !commentText.equals(""))
/*  985 */       if (this.atRoot) {
/*  986 */         this.factory.addContent((Parent)this.document, (Content)this.factory.comment(commentText));
/*      */       } else {
/*  988 */         this.factory.addContent((Parent)getCurrentElement(), (Content)this.factory.comment(commentText));
/*      */       }  
/*      */   }
/*      */   
/*      */   public void notationDecl(String name, String publicID, String systemID) throws SAXException {
/* 1003 */     if (!this.inInternalSubset)
/*      */       return; 
/* 1005 */     this.internalSubset.append("  <!NOTATION ").append(name);
/* 1007 */     appendExternalId(publicID, systemID);
/* 1008 */     this.internalSubset.append(">\n");
/*      */   }
/*      */   
/*      */   public void unparsedEntityDecl(String name, String publicID, String systemID, String notationName) throws SAXException {
/* 1023 */     if (!this.inInternalSubset)
/*      */       return; 
/* 1025 */     this.internalSubset.append("  <!ENTITY ").append(name);
/* 1027 */     appendExternalId(publicID, systemID);
/* 1028 */     this.internalSubset.append(" NDATA ").append(notationName);
/* 1030 */     this.internalSubset.append(">\n");
/*      */   }
/*      */   
/*      */   private void appendExternalId(String publicID, String systemID) {
/* 1041 */     if (publicID != null)
/* 1042 */       this.internalSubset.append(" PUBLIC \"").append(publicID).append('"'); 
/* 1046 */     if (systemID != null) {
/* 1047 */       if (publicID == null) {
/* 1048 */         this.internalSubset.append(" SYSTEM ");
/*      */       } else {
/* 1051 */         this.internalSubset.append(' ');
/*      */       } 
/* 1053 */       this.internalSubset.append('"').append(systemID).append('"');
/*      */     } 
/*      */   }
/*      */   
/*      */   public Element getCurrentElement() throws SAXException {
/* 1066 */     if (this.currentElement == null)
/* 1067 */       throw new SAXException("Ill-formed XML document (multiple root elements detected)"); 
/* 1070 */     return this.currentElement;
/*      */   }
/*      */   
/*      */   private static int getAttributeType(String typeName) {
/* 1086 */     Integer type = (Integer)attrNameToTypeMap.get(typeName);
/* 1087 */     if (type == null) {
/* 1088 */       if (typeName != null && typeName.length() > 0 && typeName.charAt(0) == '(')
/* 1093 */         return 10; 
/* 1096 */       return 0;
/*      */     } 
/* 1099 */     return type.intValue();
/*      */   }
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/* 1116 */     this.locator = locator;
/*      */   }
/*      */   
/*      */   public Locator getDocumentLocator() {
/* 1127 */     return this.locator;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\SAXHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */