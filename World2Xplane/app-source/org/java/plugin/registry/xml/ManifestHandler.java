/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.java.plugin.registry.ExtensionMultiplicity;
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.ParameterMultiplicity;
/*     */ import org.java.plugin.registry.ParameterType;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ final class ManifestHandler extends BaseHandler {
/*  33 */   private ModelPluginManifest manifest = null;
/*     */   
/*  34 */   private ModelDocumentation documentation = null;
/*     */   
/*     */   private ModelPrerequisite prerequisite;
/*     */   
/*     */   private ModelLibrary library;
/*     */   
/*     */   private ModelExtensionPoint extensionPoint;
/*     */   
/*     */   private ModelExtension extension;
/*     */   
/*  39 */   private StringBuilder docText = null;
/*     */   
/*  40 */   private SimpleStack<ModelAttribute> attributeStack = null;
/*     */   
/*     */   private ModelAttribute attribute;
/*     */   
/*  42 */   private SimpleStack<ModelParameterDef> paramDefStack = null;
/*     */   
/*     */   private ModelParameterDef paramDef;
/*     */   
/*  44 */   private SimpleStack<ModelParameter> paramStack = null;
/*     */   
/*     */   private ModelParameter param;
/*     */   
/*  46 */   private StringBuilder paramValue = null;
/*     */   
/*     */   ManifestHandler(EntityResolver anEntityResolver) {
/*  49 */     super(anEntityResolver);
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  60 */     if (this.log.isDebugEnabled())
/*  61 */       this.log.debug("startElement - [" + uri + "]/[" + localName + "]/[" + qName + "]"); 
/*  64 */     String name = qName;
/*  65 */     if ("plugin".equals(name)) {
/*  66 */       if (this.manifest != null)
/*  67 */         throw new SAXException("unexpected [" + name + "] element (manifest already defined)"); 
/*  70 */       this.manifest = new ModelPluginDescriptor();
/*  71 */       this.manifest.setId(attributes.getValue("id"));
/*  72 */       this.manifest.setVersion(attributes.getValue("version"));
/*  73 */       this.manifest.setVendor(attributes.getValue("vendor"));
/*  74 */       this.manifest.setDocsPath(attributes.getValue("docs-path"));
/*  75 */       ((ModelPluginDescriptor)this.manifest).setClassName(attributes.getValue("class"));
/*  77 */     } else if ("plugin-fragment".equals(name)) {
/*  78 */       if (this.manifest != null)
/*  79 */         throw new SAXException("unexpected [" + name + "] element (manifest already defined)"); 
/*  82 */       this.manifest = new ModelPluginFragment();
/*  83 */       this.manifest.setId(attributes.getValue("id"));
/*  84 */       this.manifest.setVersion(attributes.getValue("version"));
/*  85 */       this.manifest.setVendor(attributes.getValue("vendor"));
/*  86 */       this.manifest.setDocsPath(attributes.getValue("docs-path"));
/*  87 */       ((ModelPluginFragment)this.manifest).setPluginId(attributes.getValue("plugin-id"));
/*  89 */       if (attributes.getValue("plugin-version") != null)
/*  90 */         ((ModelPluginFragment)this.manifest).setPluginVersion(attributes.getValue("plugin-version")); 
/*  93 */       if (attributes.getValue("match") != null) {
/*  94 */         ((ModelPluginFragment)this.manifest).setMatchingRule(MatchingRule.fromCode(attributes.getValue("match")));
/*     */       } else {
/*  97 */         ((ModelPluginFragment)this.manifest).setMatchingRule(MatchingRule.COMPATIBLE);
/*     */       } 
/*  99 */     } else if ("doc".equals(name)) {
/* 100 */       this.documentation = new ModelDocumentation();
/* 101 */       this.documentation.setCaption(attributes.getValue("caption"));
/* 102 */     } else if ("doc-ref".equals(name)) {
/* 103 */       if (this.documentation == null) {
/* 104 */         if (this.entityResolver != null)
/* 105 */           throw new SAXException("[doc-ref] element found outside of [doc] element"); 
/* 109 */         this.log.warn("[doc-ref] element found outside of [doc] element");
/*     */         return;
/*     */       } 
/* 112 */       ModelDocumentationReference docRef = new ModelDocumentationReference();
/* 114 */       docRef.setPath(attributes.getValue("path"));
/* 115 */       docRef.setCaption(attributes.getValue("caption"));
/* 116 */       this.documentation.getReferences().add(docRef);
/* 117 */     } else if ("doc-text".equals(name)) {
/* 118 */       if (this.documentation == null) {
/* 119 */         if (this.entityResolver != null)
/* 120 */           throw new SAXException("[doc-text] element found outside of [doc] element"); 
/* 124 */         this.log.warn("[doc-text] element found outside of [doc] element");
/*     */         return;
/*     */       } 
/* 127 */       this.docText = new StringBuilder();
/* 128 */     } else if ("attributes".equals(name)) {
/* 129 */       this.attributeStack = new SimpleStack<ModelAttribute>();
/* 130 */     } else if ("attribute".equals(name)) {
/* 131 */       if (this.attributeStack == null) {
/* 132 */         if (this.entityResolver != null)
/* 133 */           throw new SAXException("[attribute] element found outside of [attributes] element"); 
/* 137 */         this.log.warn("[attribute] element found outside of [attributes] element");
/*     */         return;
/*     */       } 
/* 141 */       if (this.attribute != null)
/* 142 */         this.attributeStack.push(this.attribute); 
/* 144 */       this.attribute = new ModelAttribute();
/* 145 */       this.attribute.setId(attributes.getValue("id"));
/* 146 */       this.attribute.setValue(attributes.getValue("value"));
/* 147 */     } else if (!"requires".equals(name)) {
/* 149 */       if ("import".equals(name)) {
/* 150 */         this.prerequisite = new ModelPrerequisite();
/* 151 */         if (attributes.getValue("id") != null)
/* 152 */           this.prerequisite.setId(attributes.getValue("id")); 
/* 154 */         this.prerequisite.setPluginId(attributes.getValue("plugin-id"));
/* 155 */         if (attributes.getValue("plugin-version") != null)
/* 156 */           this.prerequisite.setPluginVersion(attributes.getValue("plugin-version")); 
/* 159 */         if (attributes.getValue("match") != null) {
/* 160 */           this.prerequisite.setMatchingRule(MatchingRule.fromCode(attributes.getValue("match")));
/*     */         } else {
/* 163 */           this.prerequisite.setMatchingRule(MatchingRule.COMPATIBLE);
/*     */         } 
/* 165 */         this.prerequisite.setExported(attributes.getValue("exported"));
/* 166 */         this.prerequisite.setOptional(attributes.getValue("optional"));
/* 167 */         this.prerequisite.setReverseLookup(attributes.getValue("reverse-lookup"));
/* 169 */       } else if (!"runtime".equals(name)) {
/* 171 */         if ("library".equals(name)) {
/* 172 */           this.library = new ModelLibrary();
/* 173 */           this.library.setId(attributes.getValue("id"));
/* 174 */           this.library.setPath(attributes.getValue("path"));
/* 175 */           this.library.setCodeLibrary(attributes.getValue("type"));
/* 176 */           if (attributes.getValue("version") != null)
/* 177 */             this.library.setVersion(attributes.getValue("version")); 
/* 179 */         } else if ("export".equals(name)) {
/* 180 */           if (this.library == null) {
/* 181 */             if (this.entityResolver != null)
/* 182 */               throw new SAXException("[export] element found outside of [library] element"); 
/* 186 */             this.log.warn("[export] element found outside of [library] element");
/*     */             return;
/*     */           } 
/* 189 */           this.library.getExports().add(attributes.getValue("prefix"));
/* 190 */         } else if ("extension-point".equals(name)) {
/* 191 */           this.extensionPoint = new ModelExtensionPoint();
/* 192 */           this.extensionPoint.setId(attributes.getValue("id"));
/* 193 */           this.extensionPoint.setParentPluginId(attributes.getValue("parent-plugin-id"));
/* 195 */           this.extensionPoint.setParentPointId(attributes.getValue("parent-point-id"));
/* 197 */           if (attributes.getValue("extension-multiplicity") != null) {
/* 198 */             this.extensionPoint.setExtensionMultiplicity(ExtensionMultiplicity.fromCode(attributes.getValue("extension-multiplicity")));
/*     */           } else {
/* 202 */             this.extensionPoint.setExtensionMultiplicity(ExtensionMultiplicity.ANY);
/*     */           } 
/* 205 */           this.paramDefStack = new SimpleStack<ModelParameterDef>();
/* 206 */         } else if ("parameter-def".equals(name)) {
/* 207 */           if (this.extensionPoint == null) {
/* 208 */             if (this.entityResolver != null)
/* 209 */               throw new SAXException("[parameter-def] element found outside of [extension-point] element"); 
/* 213 */             this.log.warn("[parameter-def] element found outside of [extension-point] element");
/*     */             return;
/*     */           } 
/* 217 */           if (this.paramDef != null)
/* 218 */             this.paramDefStack.push(this.paramDef); 
/* 220 */           this.paramDef = new ModelParameterDef();
/* 221 */           this.paramDef.setId(attributes.getValue("id"));
/* 222 */           if (attributes.getValue("multiplicity") != null) {
/* 223 */             this.paramDef.setMultiplicity(ParameterMultiplicity.fromCode(attributes.getValue("multiplicity")));
/*     */           } else {
/* 227 */             this.paramDef.setMultiplicity(ParameterMultiplicity.ONE);
/*     */           } 
/* 229 */           if (attributes.getValue("type") != null) {
/* 230 */             this.paramDef.setType(ParameterType.fromCode(attributes.getValue("type")));
/*     */           } else {
/* 233 */             this.paramDef.setType(ParameterType.STRING);
/*     */           } 
/* 235 */           this.paramDef.setCustomData(attributes.getValue("custom-data"));
/* 236 */           this.paramDef.setDefaultValue(attributes.getValue("default-value"));
/* 237 */         } else if ("extension".equals(name)) {
/* 238 */           this.extension = new ModelExtension();
/* 239 */           this.extension.setId(attributes.getValue("id"));
/* 240 */           this.extension.setPluginId(attributes.getValue("plugin-id"));
/* 241 */           this.extension.setPointId(attributes.getValue("point-id"));
/* 242 */           this.paramStack = new SimpleStack<ModelParameter>();
/* 243 */         } else if ("parameter".equals(name)) {
/* 244 */           if (this.extension == null) {
/* 245 */             if (this.entityResolver != null)
/* 246 */               throw new SAXException("[parameter] element found outside of [extension] element"); 
/* 250 */             this.log.warn("[parameter] element found outside of [extension] element");
/*     */             return;
/*     */           } 
/* 254 */           if (this.param != null)
/* 255 */             this.paramStack.push(this.param); 
/* 257 */           this.param = new ModelParameter();
/* 258 */           this.param.setId(attributes.getValue("id"));
/* 259 */           this.param.setValue(attributes.getValue("value"));
/* 260 */         } else if ("value".equals(name)) {
/* 261 */           if (this.param == null) {
/* 262 */             if (this.entityResolver != null)
/* 263 */               throw new SAXException("[value] element found outside of [parameter] element"); 
/* 267 */             this.log.warn("[value] element found outside of [parameter] element");
/*     */             return;
/*     */           } 
/* 271 */           this.paramValue = new StringBuilder();
/*     */         } else {
/* 273 */           if (this.entityResolver != null)
/* 274 */             throw new SAXException("unexpected manifest element - [" + uri + "]/[" + localName + "]/[" + qName + "]"); 
/* 278 */           this.log.warn("unexpected manifest element - [" + uri + "]/[" + localName + "]/[" + qName + "]");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) {
/* 290 */     if (this.log.isDebugEnabled())
/* 291 */       this.log.debug("endElement - [" + uri + "]/[" + localName + "]/[" + qName + "]"); 
/* 294 */     String name = qName;
/* 295 */     if (!"plugin".equals(name))
/* 297 */       if (!"plugin-fragment".equals(name))
/* 299 */         if ("doc".equals(name)) {
/* 300 */           if (this.param != null) {
/* 301 */             this.param.setDocumentation(this.documentation);
/* 302 */           } else if (this.extension != null) {
/* 303 */             this.extension.setDocumentation(this.documentation);
/* 304 */           } else if (this.paramDef != null) {
/* 305 */             this.paramDef.setDocumentation(this.documentation);
/* 306 */           } else if (this.extensionPoint != null) {
/* 307 */             this.extensionPoint.setDocumentation(this.documentation);
/* 308 */           } else if (this.library != null) {
/* 309 */             this.library.setDocumentation(this.documentation);
/* 310 */           } else if (this.prerequisite != null) {
/* 311 */             this.prerequisite.setDocumentation(this.documentation);
/* 312 */           } else if (this.attribute != null) {
/* 313 */             this.attribute.setDocumentation(this.documentation);
/*     */           } else {
/* 315 */             this.manifest.setDocumentation(this.documentation);
/*     */           } 
/* 317 */           this.documentation = null;
/* 318 */         } else if (!"doc-ref".equals(name)) {
/* 320 */           if ("doc-text".equals(name)) {
/* 321 */             this.documentation.setText(this.docText.toString());
/* 322 */             this.docText = null;
/* 323 */           } else if ("attributes".equals(name)) {
/* 324 */             this.attributeStack = null;
/* 325 */           } else if ("attribute".equals(name)) {
/* 326 */             if (this.attributeStack.size() == 0) {
/* 327 */               this.manifest.getAttributes().add(this.attribute);
/* 328 */               this.attribute = null;
/*     */             } else {
/* 330 */               ModelAttribute temp = this.attribute;
/* 331 */               this.attribute = this.attributeStack.pop();
/* 332 */               this.attribute.getAttributes().add(temp);
/* 333 */               temp = null;
/*     */             } 
/* 335 */           } else if (!"requires".equals(name)) {
/* 337 */             if ("import".equals(name)) {
/* 338 */               this.manifest.getPrerequisites().add(this.prerequisite);
/* 339 */               this.prerequisite = null;
/* 340 */             } else if (!"runtime".equals(name)) {
/* 342 */               if ("library".equals(name)) {
/* 343 */                 this.manifest.getLibraries().add(this.library);
/* 344 */                 this.library = null;
/* 345 */               } else if (!"export".equals(name)) {
/* 347 */                 if ("extension-point".equals(name)) {
/* 348 */                   this.manifest.getExtensionPoints().add(this.extensionPoint);
/* 349 */                   this.extensionPoint = null;
/* 350 */                   this.paramDefStack = null;
/* 351 */                 } else if ("parameter-def".equals(name)) {
/* 352 */                   if (this.paramDefStack.size() == 0) {
/* 353 */                     this.extensionPoint.getParamDefs().add(this.paramDef);
/* 354 */                     this.paramDef = null;
/*     */                   } else {
/* 356 */                     ModelParameterDef temp = this.paramDef;
/* 357 */                     this.paramDef = this.paramDefStack.pop();
/* 358 */                     this.paramDef.getParamDefs().add(temp);
/* 359 */                     temp = null;
/*     */                   } 
/* 361 */                 } else if ("extension".equals(name)) {
/* 362 */                   this.manifest.getExtensions().add(this.extension);
/* 363 */                   this.extension = null;
/* 364 */                   this.paramStack = null;
/* 365 */                 } else if ("parameter".equals(name)) {
/* 366 */                   if (this.paramStack.size() == 0) {
/* 367 */                     this.extension.getParams().add(this.param);
/* 368 */                     this.param = null;
/*     */                   } else {
/* 370 */                     ModelParameter temp = this.param;
/* 371 */                     this.param = this.paramStack.pop();
/* 372 */                     this.param.getParams().add(temp);
/* 373 */                     temp = null;
/*     */                   } 
/* 375 */                 } else if ("value".equals(name)) {
/* 376 */                   this.param.setValue(this.paramValue.toString());
/* 377 */                   this.paramValue = null;
/*     */                 } else {
/* 380 */                   this.log.warn("ignoring manifest element - [" + uri + "]/[" + localName + "]/[" + qName + "]");
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }   
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 391 */     if (this.docText != null) {
/* 392 */       this.docText.append(ch, start, length);
/* 393 */     } else if (this.paramValue != null) {
/* 394 */       this.paramValue.append(ch, start, length);
/*     */     } else {
/* 396 */       if (this.entityResolver != null)
/* 397 */         throw new SAXException("unexpected character data"); 
/* 400 */       this.log.warn("ignoring character data - [" + new String(ch, start, length) + "]");
/*     */     } 
/*     */   }
/*     */   
/*     */   ModelPluginManifest getResult() {
/* 406 */     return this.manifest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ManifestHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */