/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.CaselessStringArrayTable;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.ListResourceBundle;
/*      */ import java.util.Locale;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.media.jai.util.Range;
/*      */ 
/*      */ public abstract class OperationDescriptorImpl implements OperationDescriptor, Serializable {
/*      */   private boolean deprecated = false;
/*      */   
/*      */   protected final String[][] resources;
/*      */   
/*      */   protected final String[] supportedModes;
/*      */   
/*      */   private CaselessStringArrayTable modeIndices;
/*      */   
/*      */   protected final String[] sourceNames;
/*      */   
/*      */   private Class[][] sourceClasses;
/*      */   
/*      */   private CaselessStringArrayTable sourceIndices;
/*      */   
/*      */   private ParameterListDescriptor[] paramListDescriptors;
/*      */   
/*      */   String[] paramNames;
/*      */   
/*  101 */   private String name = null;
/*      */   
/*      */   private String[] checkSources(String[][] resources, String[] supportedModes, String[] sourceNames, Class[][] sourceClasses) {
/*  113 */     if (resources == null || resources.length == 0)
/*  114 */       throw new IllegalArgumentException("resources: " + JaiI18N.getString("Generic2")); 
/*  117 */     if (supportedModes == null || supportedModes.length == 0)
/*  118 */       throw new IllegalArgumentException("supportedModes: " + JaiI18N.getString("Generic2")); 
/*  123 */     int numModes = supportedModes.length;
/*  125 */     if (sourceClasses != null) {
/*  127 */       if (sourceClasses.length != numModes)
/*  128 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "sourceClasses", new Integer(numModes) })); 
/*  132 */       int numSources = (sourceClasses[0] == null) ? 0 : (sourceClasses[0]).length;
/*  135 */       if (sourceNames == null) {
/*  136 */         sourceNames = getDefaultSourceNames(numSources);
/*  138 */       } else if (sourceNames.length != numSources) {
/*  140 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl1", new Object[] { new Integer(sourceNames.length), new Integer(numSources) }));
/*      */       } 
/*  148 */       for (int i = 0; i < sourceClasses.length; i++) {
/*  149 */         int ns = (sourceClasses[i] == null) ? 0 : (sourceClasses[i]).length;
/*  152 */         if (numSources != ns)
/*  153 */           throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl2", new Object[] { new Integer(ns), new Integer(numSources), supportedModes[i] })); 
/*      */       } 
/*  163 */     } else if (sourceNames != null && sourceNames.length != 0) {
/*  164 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl1", new Object[] { new Integer(sourceNames.length), new Integer(0) }));
/*      */     } 
/*  172 */     return sourceNames;
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, String[] supportedModes, String[] sourceNames, Class[][] sourceClasses, String[] paramNames, Class[][] paramClasses, Object[][] paramDefaults, Object[][] validParamValues) {
/*  243 */     sourceNames = checkSources(resources, supportedModes, sourceNames, sourceClasses);
/*  246 */     this.resources = resources;
/*  247 */     this.supportedModes = supportedModes;
/*  248 */     this.sourceNames = sourceNames;
/*  249 */     this.sourceClasses = sourceClasses;
/*  250 */     this.paramNames = paramNames;
/*  252 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/*  253 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/*  257 */     int numParams = (paramNames == null) ? 0 : paramNames.length;
/*  258 */     int numModes = supportedModes.length;
/*  260 */     if (numParams == 0) {
/*  261 */       if (paramClasses != null && paramClasses.length != numModes)
/*  262 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "paramClasses", new Integer(numModes) })); 
/*  268 */     } else if (paramClasses == null || paramClasses.length != numModes) {
/*  269 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "paramClasses", new Integer(numModes) }));
/*      */     } 
/*  274 */     if (paramDefaults != null && paramDefaults.length != numModes)
/*  275 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "paramDefaults", new Integer(numModes) })); 
/*  279 */     if (validParamValues != null && validParamValues.length != numModes)
/*  280 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "validParamValues", new Integer(numModes) })); 
/*  286 */     this.paramListDescriptors = new ParameterListDescriptor[numModes];
/*  289 */     for (int i = 0; i < numModes; i++)
/*  290 */       this.paramListDescriptors[i] = new ParameterListDescriptorImpl(this, paramNames, paramClasses[i], (paramDefaults == null) ? null : paramDefaults[i], (validParamValues == null) ? null : validParamValues[i]); 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, String[] supportedModes, String[] sourceNames, Class[][] sourceClasses, String[] paramNames, Class[] paramClasses, Object[] paramDefaults, Object[] validParamValues) {
/*  358 */     sourceNames = checkSources(resources, supportedModes, sourceNames, sourceClasses);
/*  361 */     this.resources = resources;
/*  362 */     this.supportedModes = supportedModes;
/*  363 */     this.sourceNames = sourceNames;
/*  364 */     this.sourceClasses = sourceClasses;
/*  365 */     this.paramNames = paramNames;
/*  367 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/*  368 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/*  372 */     ParameterListDescriptor pld = new ParameterListDescriptorImpl(this, paramNames, paramClasses, paramDefaults, validParamValues);
/*  375 */     this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/*  378 */     for (int i = 0; i < supportedModes.length; i++)
/*  379 */       this.paramListDescriptors[i] = pld; 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, String[] supportedModes, int numSources, String[] paramNames, Class[] paramClasses, Object[] paramDefaults, Object[] validParamValues) {
/*  432 */     Class[][] sourceClasses = makeDefaultSourceClassList(supportedModes, numSources);
/*  435 */     String[] sourceNames = checkSources(resources, supportedModes, null, sourceClasses);
/*  438 */     this.resources = resources;
/*  439 */     this.supportedModes = supportedModes;
/*  440 */     this.sourceNames = sourceNames;
/*  441 */     this.sourceClasses = sourceClasses;
/*  442 */     this.paramNames = paramNames;
/*  444 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/*  445 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/*  449 */     ParameterListDescriptor pld = new ParameterListDescriptorImpl(this, paramNames, paramClasses, paramDefaults, validParamValues);
/*  452 */     this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/*  455 */     for (int i = 0; i < supportedModes.length; i++)
/*  456 */       this.paramListDescriptors[i] = pld; 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, String[] supportedModes, String[] sourceNames, Class[][] sourceClasses, ParameterListDescriptor[] pld) {
/*  499 */     sourceNames = checkSources(resources, supportedModes, sourceNames, sourceClasses);
/*  502 */     this.resources = resources;
/*  503 */     this.supportedModes = supportedModes;
/*  504 */     this.sourceNames = sourceNames;
/*  505 */     this.sourceClasses = sourceClasses;
/*  507 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/*  508 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/*  510 */     if (pld != null && pld.length != supportedModes.length)
/*  511 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl0", new Object[] { "ParameterListDescriptor's", new Integer(supportedModes.length) })); 
/*  519 */     if (pld == null) {
/*  521 */       ParameterListDescriptor tpld = new ParameterListDescriptorImpl();
/*  523 */       this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/*  526 */       for (int i = 0; i < supportedModes.length; i++)
/*  527 */         this.paramListDescriptors[i] = tpld; 
/*  529 */       this.paramNames = null;
/*      */     } else {
/*  531 */       this.paramListDescriptors = pld;
/*  532 */       this.paramNames = this.paramListDescriptors[0].getParamNames();
/*      */     } 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, String[] supportedModes, String[] sourceNames, Class[][] sourceClasses, ParameterListDescriptor pld) {
/*  575 */     sourceNames = checkSources(resources, supportedModes, sourceNames, sourceClasses);
/*  578 */     this.resources = resources;
/*  579 */     this.supportedModes = supportedModes;
/*  580 */     this.sourceNames = sourceNames;
/*  581 */     this.sourceClasses = sourceClasses;
/*  583 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/*  584 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/*  586 */     if (pld == null)
/*  587 */       pld = new ParameterListDescriptorImpl(); 
/*  589 */     this.paramNames = pld.getParamNames();
/*  591 */     this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/*  594 */     for (int i = 0; i < supportedModes.length; i++)
/*  595 */       this.paramListDescriptors[i] = pld; 
/*      */   }
/*      */   
/*      */   private String[] getDefaultSourceNames(int numSources) {
/*  603 */     String[] defaultSourceNames = new String[numSources];
/*  604 */     for (int i = 0; i < numSources; i++)
/*  605 */       defaultSourceNames[i] = "source" + i; 
/*  607 */     return defaultSourceNames;
/*      */   }
/*      */   
/*      */   public String getName() {
/*  625 */     if (this.name == null)
/*  626 */       this.name = (String)getResourceBundle(Locale.getDefault()).getObject("GlobalName"); 
/*  629 */     return this.name;
/*      */   }
/*      */   
/*      */   public String[] getSupportedModes() {
/*  644 */     return this.supportedModes;
/*      */   }
/*      */   
/*      */   public boolean isModeSupported(String modeName) {
/*  665 */     if (modeName == null)
/*  666 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  668 */     return this.modeIndices.contains(modeName);
/*      */   }
/*      */   
/*      */   public boolean arePropertiesSupported() {
/*  685 */     return true;
/*      */   }
/*      */   
/*      */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/*  719 */     if (modeName == null)
/*  720 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  722 */     if (this.deprecated && (
/*  723 */       modeName.equalsIgnoreCase("rendered") || modeName.equalsIgnoreCase("renderable")))
/*  725 */       return getPropertyGenerators(); 
/*  728 */     if (!arePropertiesSupported())
/*  729 */       throw new UnsupportedOperationException(JaiI18N.formatMsg("OperationDescriptorImpl3", new Object[] { modeName })); 
/*  734 */     return null;
/*      */   }
/*      */   
/*      */   public ParameterListDescriptor getParameterListDescriptor(String modeName) {
/*  756 */     return this.paramListDescriptors[this.modeIndices.indexOf(modeName)];
/*      */   }
/*      */   
/*      */   public String[][] getResources(Locale locale) {
/*  783 */     return this.resources;
/*      */   }
/*      */   
/*      */   public ResourceBundle getResourceBundle(Locale locale) {
/*  802 */     Locale l = locale;
/*  803 */     return new ListResourceBundle(this, l) {
/*      */         private final Locale val$l;
/*      */         
/*      */         private final OperationDescriptorImpl this$0;
/*      */         
/*      */         public Object[][] getContents() {
/*  805 */           return (Object[][])this.this$0.getResources(this.val$l);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public int getNumSources() {
/*  815 */     return this.sourceNames.length;
/*      */   }
/*      */   
/*      */   public Class[] getSourceClasses(String modeName) {
/*  831 */     checkModeName(modeName);
/*  833 */     Class[] sc = this.sourceClasses[this.modeIndices.indexOf(modeName)];
/*  835 */     if (sc != null && sc.length <= 0)
/*  836 */       return null; 
/*  838 */     return sc;
/*      */   }
/*      */   
/*      */   public String[] getSourceNames() {
/*  853 */     if (this.sourceNames == null || this.sourceNames.length <= 0)
/*  854 */       return null; 
/*  856 */     return this.sourceNames;
/*      */   }
/*      */   
/*      */   public Class getDestClass(String modeName) {
/*  878 */     checkModeName(modeName);
/*  880 */     if (this.deprecated) {
/*  882 */       if (modeName.equalsIgnoreCase("rendered"))
/*  883 */         return getDestClass(); 
/*  885 */       if (modeName.equalsIgnoreCase("renderable"))
/*  886 */         return getRenderableDestClass(); 
/*      */     } 
/*  889 */     return RegistryMode.getMode(modeName).getProductClass();
/*      */   }
/*      */   
/*      */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/*  923 */     if (modeName == null)
/*  924 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  926 */     if (this.deprecated) {
/*  928 */       if (modeName.equalsIgnoreCase("rendered"))
/*  929 */         return validateSources(args, msg); 
/*  931 */       if (modeName.equalsIgnoreCase("renderable"))
/*  932 */         return validateRenderableSources(args, msg); 
/*      */     } 
/*  935 */     return validateSources(getSourceClasses(modeName), args, msg);
/*      */   }
/*      */   
/*      */   protected boolean validateParameters(String modeName, ParameterBlock args, StringBuffer msg) {
/*  978 */     if (modeName == null)
/*  979 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  981 */     if (this.deprecated && (
/*  982 */       modeName.equalsIgnoreCase("rendered") || modeName.equalsIgnoreCase("renderable")))
/*  984 */       return validateParameters(args, msg); 
/*  987 */     return validateParameters(getParameterListDescriptor(modeName), args, msg);
/*      */   }
/*      */   
/*      */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 1029 */     return (isModeSupported(modeName) && validateSources(modeName, args, msg) && validateParameters(modeName, args, msg));
/*      */   }
/*      */   
/*      */   public boolean isImmediate() {
/* 1059 */     return false;
/*      */   }
/*      */   
/*      */   public Object getInvalidRegion(String modeName, ParameterBlock oldParamBlock, RenderingHints oldHints, ParameterBlock newParamBlock, RenderingHints newHints, OperationNode node) {
/* 1097 */     if (modeName == null)
/* 1098 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1100 */     return null;
/*      */   }
/*      */   
/*      */   protected static Class getDefaultSourceClass(String modeName) {
/* 1115 */     if ("rendered".equalsIgnoreCase(modeName))
/* 1116 */       return RenderedImage.class; 
/* 1118 */     if ("renderable".equalsIgnoreCase(modeName))
/* 1119 */       return RenderableImage.class; 
/* 1121 */     if ("collection".equalsIgnoreCase(modeName))
/* 1122 */       return Collection.class; 
/* 1124 */     if ("renderableCollection".equalsIgnoreCase(modeName))
/* 1125 */       return Collection.class; 
/* 1127 */     return null;
/*      */   }
/*      */   
/*      */   protected static Class[][] makeDefaultSourceClassList(String[] supportedModes, int numSources) {
/* 1141 */     if (supportedModes == null || supportedModes.length == 0)
/* 1142 */       return (Class[][])null; 
/* 1144 */     int count = supportedModes.length;
/* 1146 */     Class[][] classes = new Class[count][numSources];
/* 1148 */     for (int i = 0; i < count; i++) {
/* 1150 */       Class sourceClass = getDefaultSourceClass(supportedModes[i]);
/* 1152 */       for (int j = 0; j < numSources; j++)
/* 1153 */         classes[i][j] = sourceClass; 
/*      */     } 
/* 1156 */     return classes;
/*      */   }
/*      */   
/*      */   private String[] makeSupportedModeList() {
/* 1166 */     int count = 0;
/* 1168 */     if (isRenderedSupported())
/* 1168 */       count++; 
/* 1169 */     if (isRenderableSupported())
/* 1169 */       count++; 
/* 1171 */     String[] modes = new String[count];
/* 1173 */     count = 0;
/* 1175 */     if (isRenderedSupported())
/* 1175 */       modes[count++] = "rendered"; 
/* 1176 */     if (isRenderableSupported())
/* 1176 */       modes[count++] = "renderable"; 
/* 1178 */     return modes;
/*      */   }
/*      */   
/*      */   private Class[][] makeSourceClassList(Class[] sourceClasses, Class[] renderableSourceClasses) {
/* 1187 */     int count = 0;
/* 1189 */     if (isRenderedSupported())
/* 1189 */       count++; 
/* 1190 */     if (isRenderableSupported())
/* 1190 */       count++; 
/* 1192 */     Class[][] classes = new Class[count][];
/* 1194 */     count = 0;
/* 1196 */     if (isRenderedSupported())
/* 1196 */       classes[count++] = sourceClasses; 
/* 1197 */     if (isRenderableSupported())
/* 1197 */       classes[count++] = renderableSourceClasses; 
/* 1199 */     return classes;
/*      */   }
/*      */   
/*      */   private Object[] makeValidParamValueList(Class[] paramClasses) {
/* 1207 */     if (paramClasses == null)
/* 1208 */       return null; 
/* 1210 */     int numParams = paramClasses.length;
/* 1212 */     Object[] validValues = null;
/* 1214 */     for (int i = 0; i < numParams; i++) {
/* 1215 */       Number min = getParamMinValue(i);
/* 1216 */       Number max = getParamMaxValue(i);
/* 1218 */       if (min != null || max != null) {
/* 1221 */         if (validValues == null)
/* 1222 */           validValues = new Object[numParams]; 
/* 1224 */         validValues[i] = new Range(min.getClass(), (Comparable)min, (Comparable)max);
/*      */       } 
/*      */     } 
/* 1228 */     return validValues;
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, Class[] sourceClasses, Class[] renderableSourceClasses, Class[] paramClasses, String[] paramNames, Object[] paramDefaults) {
/* 1285 */     this.deprecated = true;
/* 1287 */     String[] supportedModes = makeSupportedModeList();
/* 1288 */     Class[][] sourceClassList = makeSourceClassList(sourceClasses, renderableSourceClasses);
/* 1291 */     String[] sourceNames = checkSources(resources, supportedModes, null, sourceClassList);
/* 1294 */     Object[] validParamValues = makeValidParamValueList(paramClasses);
/* 1296 */     this.resources = resources;
/* 1297 */     this.supportedModes = supportedModes;
/* 1298 */     this.sourceNames = sourceNames;
/* 1299 */     this.sourceClasses = sourceClassList;
/* 1300 */     this.paramNames = paramNames;
/* 1302 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/* 1303 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/* 1307 */     ParameterListDescriptor pld = new ParameterListDescriptorImpl(this, paramNames, paramClasses, paramDefaults, validParamValues);
/* 1310 */     this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/* 1313 */     for (int i = 0; i < supportedModes.length; i++)
/* 1314 */       this.paramListDescriptors[i] = pld; 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, int numSources, Class[] paramClasses, String[] paramNames, Object[] paramDefaults) {
/* 1363 */     this.deprecated = true;
/* 1365 */     String[] supportedModes = makeSupportedModeList();
/* 1366 */     Class[][] sourceClassList = makeDefaultSourceClassList(supportedModes, numSources);
/* 1369 */     String[] sourceNames = checkSources(resources, supportedModes, null, sourceClassList);
/* 1372 */     Object[] validParamValues = makeValidParamValueList(paramClasses);
/* 1374 */     this.resources = resources;
/* 1375 */     this.supportedModes = supportedModes;
/* 1376 */     this.sourceNames = sourceNames;
/* 1377 */     this.sourceClasses = sourceClassList;
/* 1378 */     this.paramNames = paramNames;
/* 1380 */     this.modeIndices = new CaselessStringArrayTable(supportedModes);
/* 1381 */     this.sourceIndices = new CaselessStringArrayTable(sourceNames);
/* 1385 */     ParameterListDescriptor pld = new ParameterListDescriptorImpl(this, paramNames, paramClasses, paramDefaults, validParamValues);
/* 1388 */     this.paramListDescriptors = new ParameterListDescriptor[supportedModes.length];
/* 1391 */     for (int i = 0; i < supportedModes.length; i++)
/* 1392 */       this.paramListDescriptors[i] = pld; 
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, Class[] sourceClasses) {
/* 1416 */     this(resources, sourceClasses, null, null, null, null);
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, Class[] sourceClasses, Class[] renderableSourceClasses) {
/* 1454 */     this(resources, sourceClasses, renderableSourceClasses, null, null, null);
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, Class[] paramClasses, String[] paramNames, Object[] paramDefaults) {
/* 1482 */     this(resources, null, null, paramClasses, paramNames, paramDefaults);
/*      */   }
/*      */   
/*      */   public OperationDescriptorImpl(String[][] resources, int numSources) {
/* 1511 */     this(resources, numSources, (Class[])null, (String[])null, (Object[])null);
/*      */   }
/*      */   
/*      */   public PropertyGenerator[] getPropertyGenerators() {
/* 1529 */     return this.deprecated ? null : getPropertyGenerators("rendered");
/*      */   }
/*      */   
/*      */   public boolean isRenderedSupported() {
/* 1542 */     return this.deprecated ? true : isModeSupported("rendered");
/*      */   }
/*      */   
/*      */   public Class[] getSourceClasses() {
/* 1557 */     return getSourceClasses("rendered");
/*      */   }
/*      */   
/*      */   public Class getDestClass() {
/* 1571 */     if (this.deprecated)
/* 1572 */       return isRenderedSupported() ? RenderedImage.class : null; 
/* 1575 */     return getDestClass("rendered");
/*      */   }
/*      */   
/*      */   public boolean validateArguments(ParameterBlock args, StringBuffer msg) {
/* 1602 */     if (this.deprecated)
/* 1603 */       return (validateSources(args, msg) && validateParameters(args, msg)); 
/* 1606 */     return validateArguments("rendered", args, msg);
/*      */   }
/*      */   
/*      */   public boolean isRenderableSupported() {
/* 1623 */     return this.deprecated ? false : isModeSupported("renderable");
/*      */   }
/*      */   
/*      */   public Class[] getRenderableSourceClasses() {
/* 1639 */     return getSourceClasses("renderable");
/*      */   }
/*      */   
/*      */   public Class getRenderableDestClass() {
/* 1654 */     if (this.deprecated)
/* 1655 */       return isRenderableSupported() ? RenderableImage.class : null; 
/* 1658 */     return getDestClass("renderable");
/*      */   }
/*      */   
/*      */   public boolean validateRenderableArguments(ParameterBlock args, StringBuffer msg) {
/* 1689 */     if (this.deprecated)
/* 1690 */       return (validateRenderableSources(args, msg) && validateParameters(args, msg)); 
/* 1693 */     return validateArguments("renderable", args, msg);
/*      */   }
/*      */   
/*      */   private ParameterListDescriptor getDefaultPLD() {
/* 1704 */     return getParameterListDescriptor(getSupportedModes()[0]);
/*      */   }
/*      */   
/*      */   public int getNumParameters() {
/* 1719 */     return getDefaultPLD().getNumParameters();
/*      */   }
/*      */   
/*      */   public Class[] getParamClasses() {
/* 1735 */     return getDefaultPLD().getParamClasses();
/*      */   }
/*      */   
/*      */   public String[] getParamNames() {
/* 1751 */     return getDefaultPLD().getParamNames();
/*      */   }
/*      */   
/*      */   public Object[] getParamDefaults() {
/* 1774 */     return getDefaultPLD().getParamDefaults();
/*      */   }
/*      */   
/*      */   public Object getParamDefaultValue(int index) {
/* 1798 */     return getDefaultPLD().getParamDefaultValue(this.paramNames[index]);
/*      */   }
/*      */   
/*      */   public Number getParamMinValue(int index) {
/* 1845 */     return null;
/*      */   }
/*      */   
/*      */   public Number getParamMaxValue(int index) {
/* 1891 */     return null;
/*      */   }
/*      */   
/*      */   protected boolean validateSources(ParameterBlock args, StringBuffer msg) {
/* 1913 */     if (this.deprecated)
/* 1914 */       return (isRenderedSupported() && validateSources(getSourceClasses(), args, msg)); 
/* 1917 */     return validateSources("rendered", args, msg);
/*      */   }
/*      */   
/*      */   protected boolean validateRenderableSources(ParameterBlock args, StringBuffer msg) {
/* 1941 */     if (this.deprecated)
/* 1942 */       return (isRenderableSupported() && validateSources(getRenderableSourceClasses(), args, msg)); 
/* 1945 */     return validateSources("renderable", args, msg);
/*      */   }
/*      */   
/*      */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 1981 */     return validateParameters(getDefaultPLD(), args, msg);
/*      */   }
/*      */   
/*      */   private int getMinNumParameters(ParameterListDescriptor pld) {
/* 1990 */     int numParams = pld.getNumParameters();
/* 1992 */     Object[] paramDefaults = pld.getParamDefaults();
/* 1994 */     for (int i = numParams - 1; i >= 0 && 
/* 1995 */       paramDefaults[i] != ParameterListDescriptor.NO_PARAMETER_DEFAULT; i--)
/* 1998 */       numParams--; 
/* 2002 */     return numParams;
/*      */   }
/*      */   
/*      */   private boolean validateSources(Class[] sources, ParameterBlock args, StringBuffer msg) {
/* 2009 */     if (args == null || msg == null)
/* 2010 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2013 */     int numSources = getNumSources();
/* 2016 */     if (args.getNumSources() < numSources) {
/* 2017 */       msg.append(JaiI18N.formatMsg("OperationDescriptorImpl6", new Object[] { getName(), new Integer(numSources) }));
/* 2019 */       return false;
/*      */     } 
/* 2022 */     for (int i = 0; i < numSources; i++) {
/* 2023 */       Object s = args.getSource(i);
/* 2026 */       if (s == null) {
/* 2027 */         msg.append(JaiI18N.formatMsg("OperationDescriptorImpl7", new Object[] { getName() }));
/* 2029 */         return false;
/*      */       } 
/* 2033 */       Class c = sources[i];
/* 2034 */       if (!c.isInstance(s)) {
/* 2035 */         msg.append(JaiI18N.formatMsg("OperationDescriptorImpl8", new Object[] { getName(), new Integer(i), new String(c.toString()), new String(s.getClass().toString()) }));
/* 2041 */         return false;
/*      */       } 
/*      */     } 
/* 2045 */     return true;
/*      */   }
/*      */   
/*      */   private boolean validateParameters(ParameterListDescriptor pld, ParameterBlock args, StringBuffer msg) {
/* 2052 */     if (args == null || msg == null)
/* 2053 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2056 */     int numParams = pld.getNumParameters();
/* 2059 */     int argNumParams = args.getNumParameters();
/* 2061 */     Object[] paramDefaults = pld.getParamDefaults();
/* 2064 */     if (argNumParams < numParams) {
/* 2066 */       if (argNumParams < getMinNumParameters(pld)) {
/* 2067 */         msg.append(JaiI18N.formatMsg("OperationDescriptorImpl9", new Object[] { getName(), new Integer(numParams) }));
/* 2070 */         return false;
/*      */       } 
/* 2073 */       for (int j = argNumParams; j < numParams; j++)
/* 2074 */         args.add(paramDefaults[j]); 
/*      */     } 
/* 2079 */     for (int i = 0; i < numParams; i++) {
/* 2080 */       Object p = args.getObjectParameter(i);
/* 2083 */       if (p == null) {
/* 2084 */         p = paramDefaults[i];
/* 2086 */         if (p == OperationDescriptor.NO_PARAMETER_DEFAULT) {
/* 2087 */           msg.append(JaiI18N.formatMsg("OperationDescriptorImpl11", new Object[] { getName(), new Integer(i) }));
/* 2090 */           return false;
/*      */         } 
/* 2093 */         args.set(p, i);
/*      */       } 
/*      */       try {
/* 2099 */         if (!pld.isParameterValueValid(this.paramNames[i], p)) {
/* 2100 */           msg.append(JaiI18N.formatMsg("OperationDescriptorImpl10", new Object[] { getName(), pld.getParamNames()[i] }));
/* 2103 */           return false;
/*      */         } 
/* 2105 */       } catch (IllegalArgumentException e) {
/* 2106 */         msg.append(getName() + " - " + e.getLocalizedMessage());
/* 2107 */         return false;
/*      */       } 
/*      */     } 
/* 2111 */     return true;
/*      */   }
/*      */   
/*      */   private void checkModeName(String modeName) {
/* 2120 */     if (modeName == null)
/* 2121 */       throw new IllegalArgumentException(JaiI18N.getString("OperationDescriptorImpl12")); 
/* 2124 */     if (!this.modeIndices.contains(modeName))
/* 2125 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationDescriptorImpl13", new Object[] { getName(), modeName })); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationDescriptorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */