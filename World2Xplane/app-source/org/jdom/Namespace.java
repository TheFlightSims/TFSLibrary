/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public final class Namespace {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: Namespace.java,v $ $Revision: 1.44 $ $Date: 2008/12/17 23:22:48 $ $Name:  $";
/*     */   
/*     */   private static HashMap namespaces;
/*     */   
/*  90 */   public static final Namespace NO_NAMESPACE = new Namespace("", "");
/*     */   
/*  93 */   public static final Namespace XML_NAMESPACE = new Namespace("xml", "http://www.w3.org/XML/1998/namespace");
/*     */   
/*     */   private String prefix;
/*     */   
/*     */   private String uri;
/*     */   
/*     */   static {
/* 107 */     namespaces = new HashMap(16);
/* 110 */     namespaces.put(new NamespaceKey(NO_NAMESPACE), NO_NAMESPACE);
/* 111 */     namespaces.put(new NamespaceKey(XML_NAMESPACE), XML_NAMESPACE);
/*     */   }
/*     */   
/*     */   public static Namespace getNamespace(String prefix, String uri) {
/*     */     Namespace preexisting;
/* 127 */     if (prefix == null || prefix.trim().equals("")) {
/* 129 */       if (uri == null || uri.trim().equals(""))
/* 130 */         return NO_NAMESPACE; 
/* 132 */       prefix = "";
/* 134 */     } else if (uri == null || uri.trim().equals("")) {
/* 135 */       uri = "";
/*     */     } 
/* 142 */     NamespaceKey lookup = new NamespaceKey(prefix, uri);
/* 144 */     synchronized (namespaces) {
/* 145 */       preexisting = (Namespace)namespaces.get(lookup);
/*     */     } 
/* 147 */     if (preexisting != null)
/* 148 */       return preexisting; 
/*     */     String reason;
/* 153 */     if ((reason = Verifier.checkNamespacePrefix(prefix)) != null)
/* 154 */       throw new IllegalNameException(prefix, "Namespace prefix", reason); 
/* 156 */     if ((reason = Verifier.checkNamespaceURI(uri)) != null)
/* 157 */       throw new IllegalNameException(uri, "Namespace URI", reason); 
/* 161 */     if (!prefix.equals("") && uri.equals(""))
/* 162 */       throw new IllegalNameException("", "namespace", "Namespace URIs must be non-null and non-empty Strings"); 
/* 172 */     if (prefix.equals("xml"))
/* 173 */       throw new IllegalNameException(prefix, "Namespace prefix", "The xml prefix can only be bound to http://www.w3.org/XML/1998/namespace"); 
/* 180 */     if (uri.equals("http://www.w3.org/XML/1998/namespace"))
/* 181 */       throw new IllegalNameException(uri, "Namespace URI", "The http://www.w3.org/XML/1998/namespace must be bound to the xml prefix."); 
/* 187 */     Namespace ns = new Namespace(prefix, uri);
/* 188 */     synchronized (namespaces) {
/* 189 */       namespaces.put(lookup, ns);
/*     */     } 
/* 191 */     return ns;
/*     */   }
/*     */   
/*     */   public static Namespace getNamespace(String uri) {
/* 203 */     return getNamespace("", uri);
/*     */   }
/*     */   
/*     */   private Namespace(String prefix, String uri) {
/* 215 */     this.prefix = prefix;
/* 216 */     this.uri = uri;
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 225 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public String getURI() {
/* 234 */     return this.uri;
/*     */   }
/*     */   
/*     */   public boolean equals(Object ob) {
/* 246 */     if (this == ob)
/* 247 */       return true; 
/* 249 */     if (ob instanceof Namespace)
/* 250 */       return this.uri.equals(((Namespace)ob).uri); 
/* 252 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 262 */     return "[Namespace: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 274 */     return this.uri.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Namespace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */