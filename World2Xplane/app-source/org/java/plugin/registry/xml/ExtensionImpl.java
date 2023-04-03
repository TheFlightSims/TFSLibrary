/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.IntegrityCheckReport;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.ParameterMultiplicity;
/*     */ import org.java.plugin.registry.ParameterType;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ 
/*     */ final class ExtensionImpl extends PluginElementImpl<Extension> implements Extension {
/*     */   private final ModelExtension model;
/*     */   
/*     */   private List<Extension.Parameter> parameters;
/*     */   
/*     */   private Boolean isValid;
/*     */   
/*     */   ExtensionImpl(PluginDescriptorImpl descr, PluginFragmentImpl aFragment, ModelExtension aModel) throws ManifestProcessingException {
/*  56 */     super(descr, aFragment, aModel.getId(), aModel.getDocumentation());
/*  57 */     this.model = aModel;
/*  58 */     if (this.model.getPluginId() == null || this.model.getPluginId().trim().length() == 0)
/*  60 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "extensionIdIsBlank", descr.getId()); 
/*  64 */     if (this.model.getPointId() == null || this.model.getPointId().trim().length() == 0)
/*  66 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "extendedPointIdIsBlank", descr.getId()); 
/*  70 */     this.parameters = new ArrayList<Extension.Parameter>(this.model.getParams().size());
/*  71 */     for (ModelParameter parameter : this.model.getParams())
/*  72 */       this.parameters.add(new ParameterImpl(null, parameter)); 
/*  74 */     this.parameters = Collections.unmodifiableList(this.parameters);
/*  75 */     if (this.log.isDebugEnabled())
/*  76 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/*  84 */     return getDeclaringPluginDescriptor().getRegistry().makeUniqueId(getDeclaringPluginDescriptor().getId(), getId());
/*     */   }
/*     */   
/*     */   public Collection<Extension.Parameter> getParameters() {
/*  92 */     return this.parameters;
/*     */   }
/*     */   
/*     */   public Extension.Parameter getParameter(String id) {
/*  99 */     ParameterImpl result = null;
/* 100 */     for (Extension.Parameter parameter : this.parameters) {
/* 101 */       ParameterImpl param = (ParameterImpl)parameter;
/* 102 */       if (param.getId().equals(id)) {
/* 103 */         if (result == null) {
/* 104 */           result = param;
/*     */           continue;
/*     */         } 
/* 106 */         throw new IllegalArgumentException("more than one parameter with ID " + id + " defined in extension " + getUniqueId());
/*     */       } 
/*     */     } 
/* 112 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<Extension.Parameter> getParameters(String id) {
/* 119 */     List<Extension.Parameter> result = new LinkedList<Extension.Parameter>();
/* 120 */     for (Extension.Parameter parameter : this.parameters) {
/* 121 */       if (parameter.getId().equals(id))
/* 122 */         result.add(parameter); 
/*     */     } 
/* 124 */     return Collections.unmodifiableList(result);
/*     */   }
/*     */   
/*     */   public String getExtendedPluginId() {
/* 131 */     return this.model.getPluginId();
/*     */   }
/*     */   
/*     */   public String getExtendedPointId() {
/* 138 */     return this.model.getPointId();
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 145 */     if (this.isValid == null)
/* 146 */       validate(); 
/* 148 */     return this.isValid.booleanValue();
/*     */   }
/*     */   
/*     */   Collection<IntegrityCheckReport.ReportItem> validate() {
/* 152 */     ExtensionPoint point = getExtensionPoint(getExtendedPluginId(), getExtendedPointId());
/* 154 */     if (point == null) {
/* 155 */       this.isValid = Boolean.FALSE;
/* 156 */       return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "extPointNotAvailable", new Object[] { getDeclaringPluginDescriptor().getRegistry().makeUniqueId(getExtendedPluginId(), getExtendedPointId()), getUniqueId() }));
/*     */     } 
/* 165 */     Collection<IntegrityCheckReport.ReportItem> result = validateParameters(point.getParameterDefinitions(), this.parameters);
/* 167 */     this.isValid = result.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
/* 168 */     return result;
/*     */   }
/*     */   
/*     */   ExtensionPoint getExtensionPoint(String uniqueId) {
/* 172 */     PluginRegistry registry = getDeclaringPluginDescriptor().getRegistry();
/* 173 */     return getExtensionPoint(registry.extractPluginId(uniqueId), registry.extractId(uniqueId));
/*     */   }
/*     */   
/*     */   ExtensionPoint getExtensionPoint(String pluginId, String pointId) {
/* 179 */     PluginRegistry registry = getDeclaringPluginDescriptor().getRegistry();
/* 180 */     if (!registry.isPluginDescriptorAvailable(pluginId))
/* 181 */       return null; 
/* 183 */     for (ExtensionPoint point : registry.getPluginDescriptor(pluginId).getExtensionPoints()) {
/* 185 */       if (point.getId().equals(pointId))
/* 186 */         return point; 
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   private Collection<IntegrityCheckReport.ReportItem> validateParameters(Collection<ExtensionPoint.ParameterDefinition> allDefinitions, Collection<Extension.Parameter> allParams) {
/* 194 */     List<IntegrityCheckReport.ReportItem> result = new LinkedList<IntegrityCheckReport.ReportItem>();
/* 195 */     Map<String, Collection<Extension.Parameter>> groups = new HashMap<String, Collection<Extension.Parameter>>();
/* 196 */     for (Extension.Parameter param : allParams) {
/* 197 */       ExtensionPoint.ParameterDefinition def = param.getDefinition();
/* 198 */       if (def == null) {
/* 199 */         result.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "cantDetectParameterDef", new Object[] { param.getId(), getUniqueId() }));
/*     */         continue;
/*     */       } 
/* 206 */       if (groups.containsKey(param.getId())) {
/* 207 */         ((Collection<Extension.Parameter>)groups.get(param.getId())).add(param);
/*     */         continue;
/*     */       } 
/* 209 */       Collection<Extension.Parameter> paramGroup = new LinkedList<Extension.Parameter>();
/* 210 */       paramGroup.add(param);
/* 211 */       groups.put(param.getId(), paramGroup);
/*     */     } 
/* 214 */     if (!result.isEmpty())
/* 215 */       return result; 
/* 218 */     List<Extension.Parameter> empty_paramGroup = Collections.emptyList();
/* 219 */     for (ExtensionPoint.ParameterDefinition def : allDefinitions) {
/* 220 */       Collection<Extension.Parameter> paramGroup = groups.get(def.getId());
/* 221 */       result.addAll(validateParameters(def, (paramGroup != null) ? paramGroup : empty_paramGroup));
/*     */     } 
/* 224 */     return result;
/*     */   }
/*     */   
/*     */   private Collection<IntegrityCheckReport.ReportItem> validateParameters(ExtensionPoint.ParameterDefinition def, Collection<Extension.Parameter> params) {
/* 229 */     if (this.log.isDebugEnabled())
/* 230 */       this.log.debug("validating parameters for definition " + def); 
/* 232 */     switch (def.getMultiplicity()) {
/*     */       case ONE:
/* 234 */         if (params.size() != 1)
/* 235 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "tooManyOrFewParams", new Object[] { def.getId(), getUniqueId() })); 
/*     */         break;
/*     */       case NONE_OR_ONE:
/* 244 */         if (params.size() > 1)
/* 245 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "tooManyParams", new Object[] { def.getId(), getUniqueId() })); 
/*     */         break;
/*     */       case ONE_OR_MORE:
/* 254 */         if (params.isEmpty())
/* 255 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "tooFewParams", new Object[] { def.getId(), getUniqueId() })); 
/*     */         break;
/*     */     } 
/* 267 */     if (params.isEmpty())
/* 268 */       return Collections.emptyList(); 
/* 270 */     List<IntegrityCheckReport.ReportItem> result = new LinkedList<IntegrityCheckReport.ReportItem>();
/* 271 */     int count = 1;
/* 273 */     for (Extension.Parameter parameter : params) {
/* 274 */       ParameterImpl param = (ParameterImpl)parameter;
/* 275 */       if (!param.isValid())
/* 276 */         result.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION, "invalidParameterValue", new Object[] { def.getId(), Integer.valueOf(count), getUniqueId() })); 
/* 282 */       if (ParameterType.ANY != def.getType() && result.isEmpty())
/* 283 */         result.addAll(validateParameters(param.getDefinition().getSubDefinitions(), param.getSubParameters())); 
/* 287 */       count++;
/*     */     } 
/* 289 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 297 */     return "{PluginExtension: uid=" + getUniqueId() + "}";
/*     */   }
/*     */   
/*     */   void registryChanged() {
/* 301 */     this.isValid = null;
/*     */   }
/*     */   
/*     */   private class ParameterImpl extends PluginElementImpl<Extension.Parameter> implements Extension.Parameter {
/*     */     private final ModelParameter modelParam;
/*     */     
/*     */     private ParameterValueParser valueParser;
/*     */     
/*     */     private List<Extension.Parameter> subParameters;
/*     */     
/* 308 */     private ExtensionPoint.ParameterDefinition definition = null;
/*     */     
/*     */     private boolean definitionDetected = false;
/*     */     
/*     */     private final ParameterImpl superParameter;
/*     */     
/*     */     ParameterImpl(ParameterImpl aSuperParameter, ModelParameter aModel) throws ManifestProcessingException {
/* 315 */       super(ExtensionImpl.this.getDeclaringPluginDescriptor(), ExtensionImpl.this.getDeclaringPluginFragment(), aModel.getId(), aModel.getDocumentation());
/* 318 */       this.superParameter = aSuperParameter;
/* 319 */       this.modelParam = aModel;
/* 320 */       this.subParameters = new ArrayList<Extension.Parameter>(this.modelParam.getParams().size());
/* 321 */       for (ModelParameter modelParameter : this.modelParam.getParams())
/* 322 */         this.subParameters.add(new ParameterImpl(this, modelParameter)); 
/* 324 */       this.subParameters = Collections.unmodifiableList(this.subParameters);
/* 325 */       if (this.log.isDebugEnabled())
/* 326 */         this.log.debug("object instantiated: " + this); 
/*     */     }
/*     */     
/*     */     public Extension getDeclaringExtension() {
/* 334 */       return ExtensionImpl.this;
/*     */     }
/*     */     
/*     */     public PluginDescriptor getDeclaringPluginDescriptor() {
/* 342 */       return ExtensionImpl.this.getDeclaringPluginDescriptor();
/*     */     }
/*     */     
/*     */     public PluginFragment getDeclaringPluginFragment() {
/* 350 */       return ExtensionImpl.this.getDeclaringPluginFragment();
/*     */     }
/*     */     
/*     */     public ExtensionPoint.ParameterDefinition getDefinition() {
/*     */       Collection<ExtensionPoint.ParameterDefinition> definitions;
/* 357 */       if (this.definitionDetected)
/* 358 */         return this.definition; 
/* 360 */       this.definitionDetected = true;
/* 361 */       if (this.log.isDebugEnabled())
/* 362 */         this.log.debug("detecting definition for parameter " + this); 
/* 365 */       if (this.superParameter != null) {
/* 366 */         if (this.superParameter.getDefinition() == null)
/* 367 */           return null; 
/* 369 */         if (ParameterType.ANY == this.superParameter.getDefinition().getType()) {
/* 371 */           this.definition = this.superParameter.getDefinition();
/* 372 */           if (this.log.isDebugEnabled())
/* 373 */             this.log.debug("definition detected - " + this.definition); 
/* 375 */           return this.definition;
/*     */         } 
/* 377 */         definitions = this.superParameter.getDefinition().getSubDefinitions();
/*     */       } else {
/* 380 */         definitions = ExtensionImpl.this.getExtensionPoint(getDeclaringExtension().getExtendedPluginId(), getDeclaringExtension().getExtendedPointId()).getParameterDefinitions();
/*     */       } 
/* 385 */       for (ExtensionPoint.ParameterDefinition def : definitions) {
/* 386 */         if (def.getId().equals(getId())) {
/* 387 */           this.definition = def;
/*     */           break;
/*     */         } 
/*     */       } 
/* 391 */       if (this.log.isDebugEnabled())
/* 392 */         this.log.debug("definition detected - " + this.definition); 
/* 394 */       return this.definition;
/*     */     }
/*     */     
/*     */     public Extension.Parameter getSuperParameter() {
/* 401 */       return this.superParameter;
/*     */     }
/*     */     
/*     */     public Collection<Extension.Parameter> getSubParameters() {
/* 408 */       return this.subParameters;
/*     */     }
/*     */     
/*     */     public Extension.Parameter getSubParameter(String id) {
/* 416 */       ParameterImpl result = null;
/* 417 */       for (Extension.Parameter parameter : this.subParameters) {
/* 418 */         ParameterImpl param = (ParameterImpl)parameter;
/* 419 */         if (param.getId().equals(id)) {
/* 420 */           if (result == null) {
/* 421 */             result = param;
/*     */             continue;
/*     */           } 
/* 423 */           throw new IllegalArgumentException("more than one parameter with ID " + id + " defined in extension " + ExtensionImpl.this.getUniqueId());
/*     */         } 
/*     */       } 
/* 429 */       return result;
/*     */     }
/*     */     
/*     */     public Collection<Extension.Parameter> getSubParameters(String id) {
/* 437 */       List<Extension.Parameter> result = new LinkedList<Extension.Parameter>();
/* 438 */       for (Extension.Parameter param : this.subParameters) {
/* 439 */         if (param.getId().equals(id))
/* 440 */           result.add(param); 
/*     */       } 
/* 443 */       return Collections.unmodifiableList(result);
/*     */     }
/*     */     
/*     */     public String rawValue() {
/* 450 */       return (this.modelParam.getValue() != null) ? this.modelParam.getValue() : "";
/*     */     }
/*     */     
/*     */     boolean isValid() {
/* 454 */       if (this.valueParser != null)
/* 455 */         return this.valueParser.isParsingSucceeds(); 
/* 457 */       if (this.log.isDebugEnabled())
/* 458 */         this.log.debug("validating parameter " + this); 
/* 460 */       this.valueParser = new ParameterValueParser(getDeclaringPluginDescriptor().getRegistry(), getDefinition(), this.modelParam.getValue());
/* 463 */       if (!this.valueParser.isParsingSucceeds())
/* 464 */         this.log.warn("parsing value for parameter " + this + " failed, message is: " + this.valueParser.getParsingMessage()); 
/* 468 */       return this.valueParser.isParsingSucceeds();
/*     */     }
/*     */     
/*     */     public Boolean valueAsBoolean() {
/* 475 */       if (!isValid())
/* 476 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 479 */       if (ParameterType.BOOLEAN != this.definition.getType())
/* 480 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.BOOLEAN); 
/* 484 */       if (this.valueParser.getValue() == null)
/* 485 */         return (Boolean)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 488 */       return (Boolean)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public Date valueAsDate() {
/* 495 */       if (!isValid())
/* 496 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 499 */       if (ParameterType.DATE != this.definition.getType() && ParameterType.DATE_TIME != this.definition.getType() && ParameterType.TIME != this.definition.getType())
/* 502 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.DATE + " nor " + ParameterType.DATE_TIME + " nor" + ParameterType.TIME); 
/* 507 */       if (this.valueParser.getValue() == null)
/* 508 */         return (Date)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 511 */       return (Date)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public Number valueAsNumber() {
/* 518 */       if (!isValid())
/* 519 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 522 */       if (ParameterType.NUMBER != this.definition.getType())
/* 523 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.NUMBER); 
/* 527 */       if (this.valueParser.getValue() == null)
/* 528 */         return (Number)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 531 */       return (Number)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public String valueAsString() {
/* 538 */       if (!isValid())
/* 539 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 542 */       if (ParameterType.STRING != this.definition.getType() && ParameterType.FIXED != this.definition.getType())
/* 544 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.STRING); 
/* 548 */       if (this.valueParser.getValue() == null)
/* 549 */         return (String)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 552 */       return (String)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public Extension valueAsExtension() {
/* 559 */       if (!isValid())
/* 560 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 563 */       if (ParameterType.EXTENSION_ID != this.definition.getType())
/* 564 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.EXTENSION_ID); 
/* 568 */       if (this.valueParser.getValue() == null)
/* 569 */         return (Extension)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 572 */       return (Extension)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public ExtensionPoint valueAsExtensionPoint() {
/* 579 */       if (!isValid())
/* 580 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 583 */       if (ParameterType.EXTENSION_POINT_ID != this.definition.getType())
/* 584 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.EXTENSION_POINT_ID); 
/* 588 */       if (this.valueParser.getValue() == null)
/* 589 */         return (ExtensionPoint)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 593 */       return (ExtensionPoint)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public PluginDescriptor valueAsPluginDescriptor() {
/* 600 */       if (!isValid())
/* 601 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 604 */       if (ParameterType.PLUGIN_ID != this.definition.getType())
/* 605 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.PLUGIN_ID); 
/* 608 */       if (this.valueParser.getValue() == null)
/* 609 */         return (PluginDescriptor)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(); 
/* 613 */       return (PluginDescriptor)this.valueParser.getValue();
/*     */     }
/*     */     
/*     */     public URL valueAsUrl() {
/* 620 */       return valueAsUrl((PathResolver)null);
/*     */     }
/*     */     
/*     */     public URL valueAsUrl(PathResolver pathResolver) {
/* 628 */       if (!isValid())
/* 629 */         throw new UnsupportedOperationException("parameter value is invalid"); 
/* 632 */       if (ParameterType.RESOURCE != this.definition.getType())
/* 633 */         throw new UnsupportedOperationException("parameter type is not " + ParameterType.RESOURCE); 
/* 637 */       if (this.valueParser.getValue() == null && rawValue() == null)
/* 638 */         return valueAsUrl(pathResolver, (Identity)getDefinition().getDeclaringExtensionPoint(), (URL)((ExtensionPointImpl.ParameterDefinitionImpl)getDefinition()).getValueParser().getValue(), getDefinition().getDefaultValue()); 
/* 644 */       return valueAsUrl(pathResolver, (Identity)getDeclaringPluginDescriptor(), (URL)this.valueParser.getValue(), rawValue());
/*     */     }
/*     */     
/*     */     private URL valueAsUrl(PathResolver pathResolver, Identity idt, URL absoluteUrl, String relativeUrl) {
/* 651 */       if (pathResolver == null || absoluteUrl != null)
/* 652 */         return absoluteUrl; 
/* 654 */       if (relativeUrl == null)
/* 655 */         return null; 
/* 657 */       return pathResolver.resolvePath(idt, relativeUrl);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 665 */       return "{PluginExtension.Parameter: extUid=" + getDeclaringExtension().getUniqueId() + "; id=" + getId() + "}";
/*     */     }
/*     */     
/*     */     protected boolean isEqualTo(Identity idt) {
/* 676 */       if (!super.isEqualTo(idt))
/* 677 */         return false; 
/* 679 */       ParameterImpl other = (ParameterImpl)idt;
/* 680 */       if (getSuperParameter() == null && other.getSuperParameter() == null)
/* 682 */         return true; 
/* 684 */       if (getSuperParameter() == null || other.getSuperParameter() == null)
/* 686 */         return false; 
/* 688 */       return getSuperParameter().equals(other.getSuperParameter());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ExtensionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */