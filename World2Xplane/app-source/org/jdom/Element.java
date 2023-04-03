/*      */ package org.jdom;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jdom.filter.ElementFilter;
/*      */ import org.jdom.filter.Filter;
/*      */ 
/*      */ public class Element extends Content implements Parent {
/*      */   private static final String CVS_ID = "@(#) $RCSfile: Element.java,v $ $Revision: 1.159 $ $Date: 2007/11/14 05:02:08 $ $Name:  $";
/*      */   
/*      */   private static final int INITIAL_ARRAY_SIZE = 5;
/*      */   
/*      */   protected String name;
/*      */   
/*      */   protected transient Namespace namespace;
/*      */   
/*      */   protected transient List additionalNamespaces;
/*      */   
/*  106 */   AttributeList attributes = new AttributeList(this);
/*      */   
/*  112 */   ContentList content = new ContentList(this);
/*      */   
/*      */   protected Element() {}
/*      */   
/*      */   public Element(String name, Namespace namespace) {
/*  141 */     setName(name);
/*  142 */     setNamespace(namespace);
/*      */   }
/*      */   
/*      */   public Element(String name) {
/*  153 */     this(name, (Namespace)null);
/*      */   }
/*      */   
/*      */   public Element(String name, String uri) {
/*  168 */     this(name, Namespace.getNamespace("", uri));
/*      */   }
/*      */   
/*      */   public Element(String name, String prefix, String uri) {
/*  184 */     this(name, Namespace.getNamespace(prefix, uri));
/*      */   }
/*      */   
/*      */   public String getName() {
/*  193 */     return this.name;
/*      */   }
/*      */   
/*      */   public Element setName(String name) {
/*  205 */     String reason = Verifier.checkElementName(name);
/*  206 */     if (reason != null)
/*  207 */       throw new IllegalNameException(name, "element", reason); 
/*  209 */     this.name = name;
/*  210 */     return this;
/*      */   }
/*      */   
/*      */   public Namespace getNamespace() {
/*  219 */     return this.namespace;
/*      */   }
/*      */   
/*      */   public Element setNamespace(Namespace namespace) {
/*  230 */     if (namespace == null)
/*  231 */       namespace = Namespace.NO_NAMESPACE; 
/*  233 */     String reason = Verifier.checkNamespaceCollision(namespace, getAdditionalNamespaces());
/*  235 */     if (reason != null)
/*  236 */       throw new IllegalAddException(this, namespace, reason); 
/*  238 */     for (Iterator it = getAttributes().iterator(); it.hasNext(); ) {
/*  239 */       Attribute a = it.next();
/*  240 */       reason = Verifier.checkNamespaceCollision(namespace, a);
/*  241 */       if (reason != null)
/*  242 */         throw new IllegalAddException(this, namespace, reason); 
/*      */     } 
/*  246 */     this.namespace = namespace;
/*  247 */     return this;
/*      */   }
/*      */   
/*      */   public String getNamespacePrefix() {
/*  257 */     return this.namespace.getPrefix();
/*      */   }
/*      */   
/*      */   public String getNamespaceURI() {
/*  268 */     return this.namespace.getURI();
/*      */   }
/*      */   
/*      */   public Namespace getNamespace(String prefix) {
/*  283 */     if (prefix == null)
/*  284 */       return null; 
/*  287 */     if ("xml".equals(prefix))
/*  289 */       return Namespace.XML_NAMESPACE; 
/*  293 */     if (prefix.equals(getNamespacePrefix()))
/*  294 */       return getNamespace(); 
/*  298 */     if (this.additionalNamespaces != null)
/*  299 */       for (int i = 0; i < this.additionalNamespaces.size(); i++) {
/*  300 */         Namespace ns = this.additionalNamespaces.get(i);
/*  301 */         if (prefix.equals(ns.getPrefix()))
/*  302 */           return ns; 
/*      */       }  
/*  307 */     if (this.attributes != null)
/*  308 */       for (Iterator it = this.attributes.iterator(); it.hasNext(); ) {
/*  309 */         Attribute a = (Attribute)it.next();
/*  310 */         if (prefix.equals(a.getNamespacePrefix()))
/*  311 */           return a.getNamespace(); 
/*      */       }  
/*  317 */     if (this.parent instanceof Element)
/*  318 */       return ((Element)this.parent).getNamespace(prefix); 
/*  321 */     return null;
/*      */   }
/*      */   
/*      */   public String getQualifiedName() {
/*  335 */     if ("".equals(this.namespace.getPrefix()))
/*  336 */       return getName(); 
/*  339 */     return this.namespace.getPrefix() + ':' + this.name;
/*      */   }
/*      */   
/*      */   public void addNamespaceDeclaration(Namespace additionalNamespace) {
/*  361 */     String reason = Verifier.checkNamespaceCollision(additionalNamespace, this);
/*  362 */     if (reason != null)
/*  363 */       throw new IllegalAddException(this, additionalNamespace, reason); 
/*  366 */     if (this.additionalNamespaces == null)
/*  367 */       this.additionalNamespaces = new ArrayList(5); 
/*  370 */     this.additionalNamespaces.add(additionalNamespace);
/*      */   }
/*      */   
/*      */   public void removeNamespaceDeclaration(Namespace additionalNamespace) {
/*  384 */     if (this.additionalNamespaces == null)
/*      */       return; 
/*  387 */     this.additionalNamespaces.remove(additionalNamespace);
/*      */   }
/*      */   
/*      */   public List getAdditionalNamespaces() {
/*  404 */     if (this.additionalNamespaces == null)
/*  405 */       return Collections.EMPTY_LIST; 
/*  407 */     return Collections.unmodifiableList(this.additionalNamespaces);
/*      */   }
/*      */   
/*      */   public String getValue() {
/*  419 */     StringBuffer buffer = new StringBuffer();
/*  421 */     Iterator iter = getContent().iterator();
/*  422 */     while (iter.hasNext()) {
/*  423 */       Content child = iter.next();
/*  424 */       if (child instanceof Element || child instanceof Text)
/*  425 */         buffer.append(child.getValue()); 
/*      */     } 
/*  428 */     return buffer.toString();
/*      */   }
/*      */   
/*      */   public boolean isRootElement() {
/*  439 */     return this.parent instanceof Document;
/*      */   }
/*      */   
/*      */   public int getContentSize() {
/*  443 */     return this.content.size();
/*      */   }
/*      */   
/*      */   public int indexOf(Content child) {
/*  447 */     return this.content.indexOf(child);
/*      */   }
/*      */   
/*      */   public String getText() {
/*  473 */     if (this.content.size() == 0)
/*  474 */       return ""; 
/*  478 */     if (this.content.size() == 1) {
/*  479 */       Object obj = this.content.get(0);
/*  480 */       if (obj instanceof Text)
/*  481 */         return ((Text)obj).getText(); 
/*  484 */       return "";
/*      */     } 
/*  489 */     StringBuffer textContent = new StringBuffer();
/*  490 */     boolean hasText = false;
/*  492 */     for (int i = 0; i < this.content.size(); i++) {
/*  493 */       Object obj = this.content.get(i);
/*  494 */       if (obj instanceof Text) {
/*  495 */         textContent.append(((Text)obj).getText());
/*  496 */         hasText = true;
/*      */       } 
/*      */     } 
/*  500 */     if (!hasText)
/*  501 */       return ""; 
/*  504 */     return textContent.toString();
/*      */   }
/*      */   
/*      */   public String getTextTrim() {
/*  517 */     return getText().trim();
/*      */   }
/*      */   
/*      */   public String getTextNormalize() {
/*  530 */     return Text.normalizeString(getText());
/*      */   }
/*      */   
/*      */   public String getChildText(String name) {
/*  543 */     Element child = getChild(name);
/*  544 */     if (child == null)
/*  545 */       return null; 
/*  547 */     return child.getText();
/*      */   }
/*      */   
/*      */   public String getChildTextTrim(String name) {
/*  560 */     Element child = getChild(name);
/*  561 */     if (child == null)
/*  562 */       return null; 
/*  564 */     return child.getTextTrim();
/*      */   }
/*      */   
/*      */   public String getChildTextNormalize(String name) {
/*  577 */     Element child = getChild(name);
/*  578 */     if (child == null)
/*  579 */       return null; 
/*  581 */     return child.getTextNormalize();
/*      */   }
/*      */   
/*      */   public String getChildText(String name, Namespace ns) {
/*  594 */     Element child = getChild(name, ns);
/*  595 */     if (child == null)
/*  596 */       return null; 
/*  598 */     return child.getText();
/*      */   }
/*      */   
/*      */   public String getChildTextTrim(String name, Namespace ns) {
/*  611 */     Element child = getChild(name, ns);
/*  612 */     if (child == null)
/*  613 */       return null; 
/*  615 */     return child.getTextTrim();
/*      */   }
/*      */   
/*      */   public String getChildTextNormalize(String name, Namespace ns) {
/*  628 */     Element child = getChild(name, ns);
/*  629 */     if (child == null)
/*  630 */       return null; 
/*  632 */     return child.getTextNormalize();
/*      */   }
/*      */   
/*      */   public Element setText(String text) {
/*  650 */     this.content.clear();
/*  652 */     if (text != null)
/*  653 */       addContent(new Text(text)); 
/*  656 */     return this;
/*      */   }
/*      */   
/*      */   public List getContent() {
/*  682 */     return this.content;
/*      */   }
/*      */   
/*      */   public List getContent(Filter filter) {
/*  698 */     return this.content.getView(filter);
/*      */   }
/*      */   
/*      */   public List removeContent() {
/*  707 */     List old = new ArrayList(this.content);
/*  708 */     this.content.clear();
/*  709 */     return old;
/*      */   }
/*      */   
/*      */   public List removeContent(Filter filter) {
/*  719 */     List old = new ArrayList();
/*  720 */     Iterator iter = this.content.getView(filter).iterator();
/*  721 */     while (iter.hasNext()) {
/*  722 */       Content child = iter.next();
/*  723 */       old.add(child);
/*  724 */       iter.remove();
/*      */     } 
/*  726 */     return old;
/*      */   }
/*      */   
/*      */   public Element setContent(Collection newContent) {
/*  765 */     this.content.clearAndSet(newContent);
/*  766 */     return this;
/*      */   }
/*      */   
/*      */   public Element setContent(int index, Content child) {
/*  785 */     this.content.set(index, child);
/*  786 */     return this;
/*      */   }
/*      */   
/*      */   public Parent setContent(int index, Collection newContent) {
/*  806 */     this.content.remove(index);
/*  807 */     this.content.addAll(index, newContent);
/*  808 */     return this;
/*      */   }
/*      */   
/*      */   public Element addContent(String str) {
/*  822 */     return addContent(new Text(str));
/*      */   }
/*      */   
/*      */   public Element addContent(Content child) {
/*  832 */     this.content.add((E)child);
/*  833 */     return this;
/*      */   }
/*      */   
/*      */   public Element addContent(Collection newContent) {
/*  848 */     this.content.addAll(newContent);
/*  849 */     return this;
/*      */   }
/*      */   
/*      */   public Element addContent(int index, Content child) {
/*  863 */     this.content.add(index, child);
/*  864 */     return this;
/*      */   }
/*      */   
/*      */   public Element addContent(int index, Collection newContent) {
/*  882 */     this.content.addAll(index, newContent);
/*  883 */     return this;
/*      */   }
/*      */   
/*      */   public List cloneContent() {
/*  887 */     int size = getContentSize();
/*  888 */     List list = new ArrayList(size);
/*  889 */     for (int i = 0; i < size; i++) {
/*  890 */       Content child = getContent(i);
/*  891 */       list.add(child.clone());
/*      */     } 
/*  893 */     return list;
/*      */   }
/*      */   
/*      */   public Content getContent(int index) {
/*  897 */     return (Content)this.content.get(index);
/*      */   }
/*      */   
/*      */   public boolean removeContent(Content child) {
/*  906 */     return this.content.remove(child);
/*      */   }
/*      */   
/*      */   public Content removeContent(int index) {
/*  910 */     return (Content)this.content.remove(index);
/*      */   }
/*      */   
/*      */   public Element setContent(Content child) {
/*  941 */     this.content.clear();
/*  942 */     this.content.add((E)child);
/*  943 */     return this;
/*      */   }
/*      */   
/*      */   public boolean isAncestor(Element element) {
/*  955 */     Parent p = element.getParent();
/*  956 */     while (p instanceof Element) {
/*  957 */       if (p == this)
/*  958 */         return true; 
/*  960 */       p = p.getParent();
/*      */     } 
/*  962 */     return false;
/*      */   }
/*      */   
/*      */   public List getAttributes() {
/*  977 */     return this.attributes;
/*      */   }
/*      */   
/*      */   public Attribute getAttribute(String name) {
/*  990 */     return getAttribute(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public Attribute getAttribute(String name, Namespace ns) {
/* 1004 */     return (Attribute)this.attributes.get(name, ns);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String name) {
/* 1018 */     return getAttributeValue(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String name, String def) {
/* 1033 */     return getAttributeValue(name, Namespace.NO_NAMESPACE, def);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String name, Namespace ns) {
/* 1048 */     return getAttributeValue(name, ns, (String)null);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String name, Namespace ns, String def) {
/* 1064 */     Attribute attribute = (Attribute)this.attributes.get(name, ns);
/* 1065 */     if (attribute == null)
/* 1066 */       return def; 
/* 1069 */     return attribute.getValue();
/*      */   }
/*      */   
/*      */   public Element setAttributes(Collection newAttributes) {
/* 1116 */     this.attributes.clearAndSet(newAttributes);
/* 1117 */     return this;
/*      */   }
/*      */   
/*      */   public Element setAttributes(List newAttributes) {
/* 1128 */     return setAttributes(newAttributes);
/*      */   }
/*      */   
/*      */   public Element setAttribute(String name, String value) {
/* 1147 */     Attribute attribute = getAttribute(name);
/* 1148 */     if (attribute == null) {
/* 1149 */       Attribute newAttribute = new Attribute(name, value);
/* 1150 */       setAttribute(newAttribute);
/*      */     } else {
/* 1152 */       attribute.setValue(value);
/*      */     } 
/* 1155 */     return this;
/*      */   }
/*      */   
/*      */   public Element setAttribute(String name, String value, Namespace ns) {
/* 1178 */     Attribute attribute = getAttribute(name, ns);
/* 1179 */     if (attribute == null) {
/* 1180 */       Attribute newAttribute = new Attribute(name, value, ns);
/* 1181 */       setAttribute(newAttribute);
/*      */     } else {
/* 1183 */       attribute.setValue(value);
/*      */     } 
/* 1186 */     return this;
/*      */   }
/*      */   
/*      */   public Element setAttribute(Attribute attribute) {
/* 1202 */     this.attributes.add(attribute);
/* 1203 */     return this;
/*      */   }
/*      */   
/*      */   public boolean removeAttribute(String name) {
/* 1216 */     return removeAttribute(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public boolean removeAttribute(String name, Namespace ns) {
/* 1231 */     return this.attributes.remove(name, ns);
/*      */   }
/*      */   
/*      */   public boolean removeAttribute(Attribute attribute) {
/* 1243 */     return this.attributes.remove(attribute);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1259 */     StringBuffer stringForm = (new StringBuffer(64)).append("[Element: <").append(getQualifiedName());
/* 1263 */     String nsuri = getNamespaceURI();
/* 1264 */     if (!"".equals(nsuri))
/* 1265 */       stringForm.append(" [Namespace: ").append(nsuri).append("]"); 
/* 1270 */     stringForm.append("/>]");
/* 1272 */     return stringForm.toString();
/*      */   }
/*      */   
/*      */   public Object clone() {
/* 1288 */     Element element = (Element)super.clone();
/* 1301 */     element.content = new ContentList(element);
/* 1302 */     element.attributes = new AttributeList(element);
/* 1305 */     if (this.attributes != null)
/* 1306 */       for (int i = 0; i < this.attributes.size(); i++) {
/* 1307 */         Attribute attribute = (Attribute)this.attributes.get(i);
/* 1308 */         element.attributes.add(attribute.clone());
/*      */       }  
/* 1313 */     if (this.additionalNamespaces != null)
/* 1314 */       element.additionalNamespaces = new ArrayList(this.additionalNamespaces); 
/* 1318 */     if (this.content != null)
/* 1319 */       for (int i = 0; i < this.content.size(); i++) {
/* 1320 */         Content c = (Content)this.content.get(i);
/* 1321 */         element.content.add((E)c.clone());
/*      */       }  
/* 1325 */     return element;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1333 */     out.defaultWriteObject();
/* 1337 */     out.writeObject(this.namespace.getPrefix());
/* 1338 */     out.writeObject(this.namespace.getURI());
/* 1340 */     if (this.additionalNamespaces == null) {
/* 1341 */       out.write(0);
/*      */     } else {
/* 1344 */       int size = this.additionalNamespaces.size();
/* 1345 */       out.write(size);
/* 1346 */       for (int i = 0; i < size; i++) {
/* 1347 */         Namespace additional = this.additionalNamespaces.get(i);
/* 1348 */         out.writeObject(additional.getPrefix());
/* 1349 */         out.writeObject(additional.getURI());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1357 */     in.defaultReadObject();
/* 1359 */     this.namespace = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
/* 1362 */     int size = in.read();
/* 1364 */     if (size != 0) {
/* 1365 */       this.additionalNamespaces = new ArrayList(size);
/* 1366 */       for (int i = 0; i < size; i++) {
/* 1367 */         Namespace additional = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
/* 1369 */         this.additionalNamespaces.add(additional);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator getDescendants() {
/* 1380 */     return new DescendantIterator(this);
/*      */   }
/*      */   
/*      */   public Iterator getDescendants(Filter filter) {
/* 1393 */     Iterator iterator = new DescendantIterator(this);
/* 1394 */     return new FilterIterator(iterator, filter);
/*      */   }
/*      */   
/*      */   public List getChildren() {
/* 1431 */     return this.content.getView((Filter)new ElementFilter());
/*      */   }
/*      */   
/*      */   public List getChildren(String name) {
/* 1451 */     return getChildren(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public List getChildren(String name, Namespace ns) {
/* 1472 */     return this.content.getView((Filter)new ElementFilter(name, ns));
/*      */   }
/*      */   
/*      */   public Element getChild(String name, Namespace ns) {
/* 1486 */     List elements = this.content.getView((Filter)new ElementFilter(name, ns));
/* 1487 */     Iterator iter = elements.iterator();
/* 1488 */     if (iter.hasNext())
/* 1489 */       return iter.next(); 
/* 1491 */     return null;
/*      */   }
/*      */   
/*      */   public Element getChild(String name) {
/* 1504 */     return getChild(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public boolean removeChild(String name) {
/* 1518 */     return removeChild(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public boolean removeChild(String name, Namespace ns) {
/* 1533 */     ElementFilter elementFilter = new ElementFilter(name, ns);
/* 1534 */     List old = this.content.getView((Filter)elementFilter);
/* 1535 */     Iterator iter = old.iterator();
/* 1536 */     if (iter.hasNext()) {
/* 1537 */       iter.next();
/* 1538 */       iter.remove();
/* 1539 */       return true;
/*      */     } 
/* 1542 */     return false;
/*      */   }
/*      */   
/*      */   public boolean removeChildren(String name) {
/* 1556 */     return removeChildren(name, Namespace.NO_NAMESPACE);
/*      */   }
/*      */   
/*      */   public boolean removeChildren(String name, Namespace ns) {
/* 1571 */     boolean deletedSome = false;
/* 1573 */     ElementFilter elementFilter = new ElementFilter(name, ns);
/* 1574 */     List old = this.content.getView((Filter)elementFilter);
/* 1575 */     Iterator iter = old.iterator();
/* 1576 */     while (iter.hasNext()) {
/* 1577 */       iter.next();
/* 1578 */       iter.remove();
/* 1579 */       deletedSome = true;
/*      */     } 
/* 1582 */     return deletedSome;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Element.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */