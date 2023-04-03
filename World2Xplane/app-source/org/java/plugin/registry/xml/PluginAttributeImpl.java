/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.PluginAttribute;
/*     */ 
/*     */ class PluginAttributeImpl extends PluginElementImpl<PluginAttribute> implements PluginAttribute {
/*     */   private final PluginAttributeImpl superAttribute;
/*     */   
/*     */   private final ModelAttribute model;
/*     */   
/*     */   private List<PluginAttribute> subAttributes;
/*     */   
/*     */   PluginAttributeImpl(PluginDescriptorImpl descr, PluginFragmentImpl aFragment, ModelAttribute aModel, PluginAttributeImpl aSuperAttribute) throws ManifestProcessingException {
/*  44 */     super(descr, aFragment, aModel.getId(), aModel.getDocumentation());
/*  45 */     this.model = aModel;
/*  46 */     this.superAttribute = aSuperAttribute;
/*  47 */     if (this.model.getValue() == null)
/*  48 */       this.model.setValue(""); 
/*  50 */     this.subAttributes = new ArrayList<PluginAttribute>(this.model.getAttributes().size());
/*  51 */     for (ModelAttribute modelAttribute : this.model.getAttributes())
/*  52 */       this.subAttributes.add(new PluginAttributeImpl(descr, aFragment, modelAttribute, this)); 
/*  54 */     this.subAttributes = Collections.unmodifiableList(this.subAttributes);
/*  55 */     if (this.log.isDebugEnabled())
/*  56 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public PluginAttribute getSubAttribute(String id) {
/*  64 */     PluginAttributeImpl result = null;
/*  65 */     for (PluginAttribute pluginAttribute : this.subAttributes) {
/*  66 */       PluginAttributeImpl param = (PluginAttributeImpl)pluginAttribute;
/*  67 */       if (param.getId().equals(id)) {
/*  68 */         if (result == null) {
/*  69 */           result = param;
/*     */           continue;
/*     */         } 
/*  71 */         throw new IllegalArgumentException("more than one attribute with ID " + id + " defined in plug-in " + getDeclaringPluginDescriptor().getUniqueId());
/*     */       } 
/*     */     } 
/*  78 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<PluginAttribute> getSubAttributes() {
/*  85 */     return this.subAttributes;
/*     */   }
/*     */   
/*     */   public Collection<PluginAttribute> getSubAttributes(String id) {
/*  92 */     List<PluginAttribute> result = new LinkedList<PluginAttribute>();
/*  93 */     for (PluginAttribute pluginAttribute : this.subAttributes) {
/*  94 */       PluginAttributeImpl param = (PluginAttributeImpl)pluginAttribute;
/*  95 */       if (param.getId().equals(id))
/*  96 */         result.add(param); 
/*     */     } 
/*  99 */     return Collections.unmodifiableList(result);
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 106 */     return this.model.getValue();
/*     */   }
/*     */   
/*     */   protected boolean isEqualTo(Identity idt) {
/* 115 */     if (!super.isEqualTo(idt))
/* 116 */       return false; 
/* 118 */     PluginAttributeImpl other = (PluginAttributeImpl)idt;
/* 119 */     if (getSuperAttribute() == null && other.getSuperAttribute() == null)
/* 121 */       return true; 
/* 123 */     if (getSuperAttribute() == null || other.getSuperAttribute() == null)
/* 125 */       return false; 
/* 127 */     return getSuperAttribute().equals(other.getSuperAttribute());
/*     */   }
/*     */   
/*     */   public PluginAttribute getSuperAttribute() {
/* 134 */     return this.superAttribute;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginAttributeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */