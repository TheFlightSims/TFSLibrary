/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*     */ import org.apache.commons.configuration.reloading.Reloadable;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ 
/*     */ public class SubnodeConfiguration extends HierarchicalReloadableConfiguration {
/*     */   private static final long serialVersionUID = 3105734147019386480L;
/*     */   
/*     */   private HierarchicalConfiguration parent;
/*     */   
/*     */   private String subnodeKey;
/*     */   
/*     */   public SubnodeConfiguration(HierarchicalConfiguration parent, ConfigurationNode root) {
/* 140 */     super((parent instanceof Reloadable) ? ((Reloadable)parent).getReloadLock() : null);
/* 141 */     if (parent == null)
/* 143 */       throw new IllegalArgumentException("Parent configuration must not be null!"); 
/* 146 */     if (root == null)
/* 148 */       throw new IllegalArgumentException("Root node must not be null!"); 
/* 151 */     setRootNode(root);
/* 152 */     this.parent = parent;
/* 153 */     initFromParent(parent);
/*     */   }
/*     */   
/*     */   public HierarchicalConfiguration getParent() {
/* 163 */     return this.parent;
/*     */   }
/*     */   
/*     */   public String getSubnodeKey() {
/* 177 */     return this.subnodeKey;
/*     */   }
/*     */   
/*     */   public void setSubnodeKey(String subnodeKey) {
/* 190 */     this.subnodeKey = subnodeKey;
/*     */   }
/*     */   
/*     */   public ConfigurationNode getRootNode() {
/* 205 */     if (getSubnodeKey() != null)
/*     */       try {
/* 209 */         List nodes = getParent().fetchNodeList(getSubnodeKey());
/* 210 */         if (nodes.size() != 1) {
/* 213 */           setSubnodeKey((String)null);
/*     */         } else {
/* 217 */           ConfigurationNode currentRoot = nodes.get(0);
/* 219 */           if (currentRoot != super.getRootNode()) {
/* 223 */             fireEvent(12, null, null, true);
/* 224 */             setRootNode(currentRoot);
/* 225 */             fireEvent(12, null, null, false);
/*     */           } 
/* 227 */           return currentRoot;
/*     */         } 
/* 230 */       } catch (Exception ex) {
/* 235 */         setSubnodeKey((String)null);
/*     */       }  
/* 239 */     return super.getRootNode();
/*     */   }
/*     */   
/*     */   protected SubnodeConfiguration createSubnodeConfiguration(ConfigurationNode node) {
/* 253 */     SubnodeConfiguration result = new SubnodeConfiguration(getParent(), node);
/* 254 */     getParent().registerSubnodeConfiguration(result);
/* 255 */     return result;
/*     */   }
/*     */   
/*     */   protected SubnodeConfiguration createSubnodeConfiguration(ConfigurationNode node, String subnodeKey) {
/* 276 */     SubnodeConfiguration result = createSubnodeConfiguration(node);
/* 278 */     if (getSubnodeKey() != null) {
/* 282 */       List lstPathToRoot = new ArrayList();
/* 283 */       ConfigurationNode top = super.getRootNode();
/* 284 */       ConfigurationNode nd = node;
/* 285 */       while (nd != top) {
/* 287 */         lstPathToRoot.add(nd);
/* 288 */         nd = nd.getParentNode();
/*     */       } 
/* 292 */       Collections.reverse(lstPathToRoot);
/* 293 */       String key = getSubnodeKey();
/* 294 */       for (Iterator it = lstPathToRoot.iterator(); it.hasNext();)
/* 296 */         key = getParent().getExpressionEngine().nodeKey(it.next(), key); 
/* 299 */       result.setSubnodeKey(key);
/*     */     } 
/* 302 */     return result;
/*     */   }
/*     */   
/*     */   protected HierarchicalConfiguration.Node createNode(String name) {
/* 313 */     return getParent().createNode(name);
/*     */   }
/*     */   
/*     */   protected void initFromParent(HierarchicalConfiguration parentConfig) {
/* 325 */     setExpressionEngine(parentConfig.getExpressionEngine());
/* 326 */     setListDelimiter(parentConfig.getListDelimiter());
/* 327 */     setDelimiterParsingDisabled(parentConfig.isDelimiterParsingDisabled());
/* 328 */     setThrowExceptionOnMissing(parentConfig.isThrowExceptionOnMissing());
/*     */   }
/*     */   
/*     */   protected ConfigurationInterpolator createInterpolator() {
/* 339 */     ConfigurationInterpolator interpolator = super.createInterpolator();
/* 340 */     interpolator.setParentInterpolator(getParent().getInterpolator());
/* 341 */     return interpolator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\SubnodeConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */