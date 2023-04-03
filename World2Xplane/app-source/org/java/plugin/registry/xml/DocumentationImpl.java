/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.registry.Documentation;
/*     */ import org.java.plugin.registry.Identity;
/*     */ 
/*     */ class DocumentationImpl<T extends Identity> implements Documentation<T> {
/*  38 */   protected static Log log = LogFactory.getLog(DocumentationImpl.class);
/*     */   
/*     */   private final T identity;
/*     */   
/*     */   private final ModelDocumentation model;
/*     */   
/*     */   private List<Documentation.Reference<T>> references;
/*     */   
/*     */   DocumentationImpl(T anIdentity, ModelDocumentation aModel) {
/*  45 */     this.identity = anIdentity;
/*  46 */     this.model = aModel;
/*  47 */     if (this.model.getCaption() == null || this.model.getCaption().trim().length() == 0)
/*  49 */       this.model.setCaption(""); 
/*  51 */     this.references = new ArrayList<Documentation.Reference<T>>(this.model.getReferences().size());
/*  52 */     for (ModelDocumentationReference reference : this.model.getReferences())
/*  53 */       this.references.add(new ReferenceImpl(reference)); 
/*  55 */     this.references = Collections.unmodifiableList(this.references);
/*  56 */     if (this.model.getText() == null)
/*  57 */       this.model.setText(""); 
/*  59 */     if (log.isDebugEnabled())
/*  60 */       log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public String getCaption() {
/*  68 */     return this.model.getCaption();
/*     */   }
/*     */   
/*     */   public String getText() {
/*  75 */     return this.model.getText();
/*     */   }
/*     */   
/*     */   public Collection<Documentation.Reference<T>> getReferences() {
/*  82 */     return this.references;
/*     */   }
/*     */   
/*     */   public T getDeclaringIdentity() {
/*  89 */     return this.identity;
/*     */   }
/*     */   
/*     */   private class ReferenceImpl implements Documentation.Reference<T> {
/*     */     private final ModelDocumentationReference modelRef;
/*     */     
/*     */     ReferenceImpl(ModelDocumentationReference aModel) {
/*  96 */       this.modelRef = aModel;
/*  97 */       if (this.modelRef.getCaption() == null || this.modelRef.getCaption().trim().length() == 0)
/*  99 */         this.modelRef.setCaption(""); 
/* 101 */       if (this.modelRef.getPath() == null || this.modelRef.getPath().trim().length() == 0)
/* 103 */         this.modelRef.setPath(""); 
/* 105 */       if (DocumentationImpl.log.isDebugEnabled())
/* 106 */         DocumentationImpl.log.debug("object instantiated: " + this); 
/*     */     }
/*     */     
/*     */     public String getCaption() {
/* 114 */       return this.modelRef.getCaption();
/*     */     }
/*     */     
/*     */     public String getRef() {
/* 121 */       return this.modelRef.getPath();
/*     */     }
/*     */     
/*     */     public T getDeclaringIdentity() {
/* 128 */       return DocumentationImpl.this.getDeclaringIdentity();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\DocumentationImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */