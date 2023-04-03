/*     */ package org.jdom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class Content implements Cloneable, Serializable {
/*  79 */   protected Parent parent = null;
/*     */   
/*     */   public Content detach() {
/*  90 */     if (this.parent != null)
/*  91 */       this.parent.removeContent(this); 
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public Parent getParent() {
/* 104 */     return this.parent;
/*     */   }
/*     */   
/*     */   public Element getParentElement() {
/* 117 */     Parent parent = getParent();
/* 118 */     return (parent instanceof Element) ? (Element)parent : null;
/*     */   }
/*     */   
/*     */   protected Content setParent(Parent parent) {
/* 129 */     this.parent = parent;
/* 130 */     return this;
/*     */   }
/*     */   
/*     */   public Document getDocument() {
/* 140 */     if (this.parent == null)
/* 140 */       return null; 
/* 141 */     return this.parent.getDocument();
/*     */   }
/*     */   
/*     */   public abstract String getValue();
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 160 */       Content c = (Content)super.clone();
/* 161 */       c.parent = null;
/* 162 */       return c;
/* 163 */     } catch (CloneNotSupportedException e) {
/* 166 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final boolean equals(Object ob) {
/* 181 */     return (ob == this);
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 190 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Content.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */