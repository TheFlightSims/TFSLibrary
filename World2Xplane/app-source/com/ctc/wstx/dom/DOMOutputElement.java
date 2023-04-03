/*     */ package com.ctc.wstx.dom;
/*     */ 
/*     */ import com.ctc.wstx.sw.OutputElementBase;
/*     */ import com.ctc.wstx.util.BijectiveNsMap;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public final class DOMOutputElement extends OutputElementBase {
/*     */   private DOMOutputElement mParent;
/*     */   
/*     */   private final Node mRootNode;
/*     */   
/*     */   private Element mElement;
/*     */   
/*     */   private boolean mDefaultNsSet;
/*     */   
/*     */   private DOMOutputElement(Node rootNode) {
/*  48 */     this.mRootNode = rootNode;
/*  49 */     this.mParent = null;
/*  50 */     this.mElement = null;
/*  51 */     this.mNsMapping = null;
/*  52 */     this.mNsMapShared = false;
/*  53 */     this.mDefaultNsURI = "";
/*  54 */     this.mRootNsContext = null;
/*  55 */     this.mDefaultNsSet = false;
/*     */   }
/*     */   
/*     */   private DOMOutputElement(DOMOutputElement parent, Element element, BijectiveNsMap ns) {
/*  60 */     super(parent, ns);
/*  61 */     this.mRootNode = null;
/*  62 */     this.mParent = parent;
/*  63 */     this.mElement = element;
/*  64 */     this.mNsMapping = ns;
/*  65 */     this.mNsMapShared = (ns != null);
/*  66 */     this.mDefaultNsURI = parent.mDefaultNsURI;
/*  67 */     this.mRootNsContext = parent.mRootNsContext;
/*  68 */     this.mDefaultNsSet = false;
/*     */   }
/*     */   
/*     */   private void relink(DOMOutputElement parent, Element element) {
/*  79 */     relink(parent);
/*  80 */     this.mParent = parent;
/*  81 */     this.mElement = element;
/*  82 */     parent.appendNode(element);
/*  83 */     this.mDefaultNsSet = false;
/*     */   }
/*     */   
/*     */   public static DOMOutputElement createRoot(Node rootNode) {
/*  88 */     return new DOMOutputElement(rootNode);
/*     */   }
/*     */   
/*     */   protected DOMOutputElement createAndAttachChild(Element element) {
/* 100 */     if (this.mRootNode != null) {
/* 101 */       this.mRootNode.appendChild(element);
/*     */     } else {
/* 103 */       this.mElement.appendChild(element);
/*     */     } 
/* 105 */     return createChild(element);
/*     */   }
/*     */   
/*     */   protected DOMOutputElement createChild(Element element) {
/* 110 */     return new DOMOutputElement(this, element, this.mNsMapping);
/*     */   }
/*     */   
/*     */   protected DOMOutputElement reuseAsChild(DOMOutputElement parent, Element element) {
/* 118 */     DOMOutputElement poolHead = this.mParent;
/* 119 */     relink(parent, element);
/* 120 */     return poolHead;
/*     */   }
/*     */   
/*     */   protected void addToPool(DOMOutputElement poolHead) {
/* 129 */     this.mParent = poolHead;
/*     */   }
/*     */   
/*     */   public DOMOutputElement getParent() {
/* 139 */     return this.mParent;
/*     */   }
/*     */   
/*     */   public boolean isRoot() {
/* 144 */     return (this.mParent == null);
/*     */   }
/*     */   
/*     */   public String getNameDesc() {
/* 153 */     if (this.mElement != null)
/* 154 */       return this.mElement.getLocalName(); 
/* 156 */     return "#error";
/*     */   }
/*     */   
/*     */   public void setDefaultNsUri(String uri) {
/* 166 */     this.mDefaultNsURI = uri;
/* 167 */     this.mDefaultNsSet = true;
/*     */   }
/*     */   
/*     */   protected void setRootNsContext(NamespaceContext ctxt) {
/* 172 */     this.mRootNsContext = ctxt;
/* 176 */     if (!this.mDefaultNsSet) {
/* 177 */       String defURI = ctxt.getNamespaceURI("");
/* 178 */       if (defURI != null && defURI.length() > 0)
/* 179 */         this.mDefaultNsURI = defURI; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void appendNode(Node n) {
/* 192 */     if (this.mRootNode != null) {
/* 193 */       this.mRootNode.appendChild(n);
/*     */     } else {
/* 195 */       this.mElement.appendChild(n);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addAttribute(String pname, String value) {
/* 201 */     this.mElement.setAttribute(pname, value);
/*     */   }
/*     */   
/*     */   protected void addAttribute(String uri, String qname, String value) {
/* 206 */     this.mElement.setAttributeNS(uri, qname, value);
/*     */   }
/*     */   
/*     */   public void appendChild(Node n) {
/* 210 */     this.mElement.appendChild(n);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dom\DOMOutputElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */