/*     */ package org.jdom;
/*     */ 
/*     */ final class NamespaceKey {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: NamespaceKey.java,v $ $Revision: 1.2 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   private String prefix;
/*     */   
/*     */   private String uri;
/*     */   
/*     */   private int hash;
/*     */   
/*     */   public NamespaceKey(String prefix, String uri) {
/*  78 */     this.prefix = prefix;
/*  79 */     this.uri = uri;
/*  80 */     this.hash = prefix.hashCode();
/*     */   }
/*     */   
/*     */   public NamespaceKey(Namespace namespace) {
/*  84 */     this(namespace.getPrefix(), namespace.getURI());
/*     */   }
/*     */   
/*     */   public boolean equals(Object ob) {
/*  88 */     if (this == ob)
/*  89 */       return true; 
/*  91 */     if (ob instanceof NamespaceKey) {
/*  92 */       NamespaceKey other = (NamespaceKey)ob;
/*  93 */       return (this.prefix.equals(other.prefix) && this.uri.equals(other.uri));
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 101 */     return this.hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 105 */     return "[NamespaceKey: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\NamespaceKey.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */