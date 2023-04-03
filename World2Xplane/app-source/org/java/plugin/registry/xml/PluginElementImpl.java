/*    */ package org.java.plugin.registry.xml;
/*    */ 
/*    */ import org.java.plugin.registry.Documentation;
/*    */ import org.java.plugin.registry.Identity;
/*    */ import org.java.plugin.registry.ManifestProcessingException;
/*    */ import org.java.plugin.registry.PluginDescriptor;
/*    */ import org.java.plugin.registry.PluginElement;
/*    */ import org.java.plugin.registry.PluginFragment;
/*    */ 
/*    */ abstract class PluginElementImpl<T extends PluginElement<T>> extends IdentityImpl implements PluginElement<T> {
/*    */   private final PluginDescriptor descriptor;
/*    */   
/*    */   private final PluginFragment fragment;
/*    */   
/*    */   private DocumentationImpl<T> doc;
/*    */   
/*    */   protected PluginElementImpl(PluginDescriptor descr, PluginFragment aFragment, String id, ModelDocumentation modelDoc) throws ManifestProcessingException {
/* 41 */     super(id);
/* 42 */     this.descriptor = descr;
/* 43 */     this.fragment = aFragment;
/* 44 */     if (modelDoc != null)
/* 45 */       this.doc = (DocumentationImpl)new DocumentationImpl<PluginElementImpl<T>>(this, modelDoc); 
/*    */   }
/*    */   
/*    */   protected boolean isEqualTo(Identity idt) {
/* 55 */     if (!getClass().getName().equals(idt.getClass().getName()))
/* 56 */       return false; 
/* 58 */     return (getDeclaringPluginDescriptor().equals(((PluginElementImpl)idt).getDeclaringPluginDescriptor()) && getId().equals(idt.getId()));
/*    */   }
/*    */   
/*    */   public PluginDescriptor getDeclaringPluginDescriptor() {
/* 67 */     return this.descriptor;
/*    */   }
/*    */   
/*    */   public PluginFragment getDeclaringPluginFragment() {
/* 74 */     return this.fragment;
/*    */   }
/*    */   
/*    */   public Documentation<T> getDocumentation() {
/* 81 */     return this.doc;
/*    */   }
/*    */   
/*    */   public String getDocsPath() {
/* 88 */     return (this.fragment != null) ? this.fragment.getDocsPath() : this.descriptor.getDocsPath();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */