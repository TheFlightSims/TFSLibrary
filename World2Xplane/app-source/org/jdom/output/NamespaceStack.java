/*     */ package org.jdom.output;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.jdom.Namespace;
/*     */ 
/*     */ class NamespaceStack {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: NamespaceStack.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:29:01 $ $Name:  $";
/*     */   
/*  87 */   private Stack prefixes = new Stack();
/*     */   
/*  88 */   private Stack uris = new Stack();
/*     */   
/*     */   public void push(Namespace ns) {
/*  98 */     this.prefixes.push(ns.getPrefix());
/*  99 */     this.uris.push(ns.getURI());
/*     */   }
/*     */   
/*     */   public String pop() {
/* 109 */     String prefix = this.prefixes.pop();
/* 110 */     this.uris.pop();
/* 112 */     return prefix;
/*     */   }
/*     */   
/*     */   public int size() {
/* 121 */     return this.prefixes.size();
/*     */   }
/*     */   
/*     */   public String getURI(String prefix) {
/* 132 */     int index = this.prefixes.lastIndexOf(prefix);
/* 133 */     if (index == -1)
/* 134 */       return null; 
/* 136 */     String uri = this.uris.elementAt(index);
/* 137 */     return uri;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     StringBuffer buf = new StringBuffer();
/* 147 */     String sep = System.getProperty("line.separator");
/* 148 */     buf.append("Stack: " + this.prefixes.size() + sep);
/* 149 */     for (int i = 0; i < this.prefixes.size(); i++)
/* 150 */       buf.append((new StringBuffer()).append(this.prefixes.elementAt(i)).append("&").append(this.uris.elementAt(i)).append(sep).toString()); 
/* 152 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\output\NamespaceStack.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */