/*     */ package org.jdom.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.Namespace;
/*     */ 
/*     */ public class ElementFilter extends AbstractFilter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: ElementFilter.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   private String name;
/*     */   
/*     */   private transient Namespace namespace;
/*     */   
/*     */   public ElementFilter() {}
/*     */   
/*     */   public ElementFilter(String name) {
/*  91 */     this.name = name;
/*     */   }
/*     */   
/*     */   public ElementFilter(Namespace namespace) {
/* 100 */     this.namespace = namespace;
/*     */   }
/*     */   
/*     */   public ElementFilter(String name, Namespace namespace) {
/* 110 */     this.name = name;
/* 111 */     this.namespace = namespace;
/*     */   }
/*     */   
/*     */   public boolean matches(Object obj) {
/* 122 */     if (obj instanceof Element) {
/* 123 */       Element el = (Element)obj;
/* 124 */       return ((this.name == null || this.name.equals(el.getName())) && (this.namespace == null || this.namespace.equals(el.getNamespace())));
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 140 */     if (this == obj)
/* 140 */       return true; 
/* 141 */     if (!(obj instanceof ElementFilter))
/* 141 */       return false; 
/* 143 */     ElementFilter filter = (ElementFilter)obj;
/* 145 */     if ((this.name != null) ? !this.name.equals(filter.name) : (filter.name != null))
/* 145 */       return false; 
/* 146 */     if ((this.namespace != null) ? !this.namespace.equals(filter.namespace) : (filter.namespace != null))
/* 146 */       return false; 
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 154 */     int result = (this.name != null) ? this.name.hashCode() : 0;
/* 155 */     result = 29 * result + ((this.namespace != null) ? this.namespace.hashCode() : 0);
/* 156 */     return result;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 163 */     out.defaultWriteObject();
/* 167 */     if (this.namespace != null) {
/* 168 */       out.writeObject(this.namespace.getPrefix());
/* 169 */       out.writeObject(this.namespace.getURI());
/*     */     } else {
/* 172 */       out.writeObject(null);
/* 173 */       out.writeObject(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 180 */     in.defaultReadObject();
/* 182 */     Object prefix = in.readObject();
/* 183 */     Object uri = in.readObject();
/* 185 */     if (prefix != null)
/* 186 */       this.namespace = Namespace.getNamespace((String)prefix, (String)uri); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\ElementFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */