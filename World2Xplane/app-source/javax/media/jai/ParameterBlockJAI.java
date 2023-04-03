/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.CaselessStringArrayTable;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class ParameterBlockJAI extends ParameterBlock implements ParameterList {
/*      */   private transient OperationDescriptor odesc;
/*      */   
/*      */   private String modeName;
/*      */   
/*      */   private ParameterListDescriptor pld;
/*      */   
/*      */   private CaselessStringArrayTable paramIndices;
/*      */   
/*      */   private CaselessStringArrayTable sourceIndices;
/*      */   
/*      */   private int numParameters;
/*      */   
/*      */   private String[] paramNames;
/*      */   
/*      */   private Class[] paramClasses;
/*      */   
/*      */   private Class[] sourceClasses;
/*      */   
/*      */   private static String getDefaultMode(OperationDescriptor odesc) {
/*  135 */     if (odesc == null)
/*  136 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  138 */     return odesc.getSupportedModes()[0];
/*      */   }
/*      */   
/*      */   public ParameterBlockJAI(OperationDescriptor odesc) {
/*  157 */     this(odesc, getDefaultMode(odesc));
/*      */   }
/*      */   
/*      */   public ParameterBlockJAI(String operationName) {
/*  178 */     this((OperationDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor(OperationDescriptor.class, operationName));
/*      */   }
/*      */   
/*      */   public ParameterBlockJAI(OperationDescriptor odesc, String modeName) {
/*  199 */     if (odesc == null || modeName == null)
/*  200 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  202 */     this.odesc = odesc;
/*  203 */     this.modeName = modeName;
/*  205 */     this.pld = odesc.getParameterListDescriptor(modeName);
/*  207 */     this.numParameters = this.pld.getNumParameters();
/*  208 */     this.paramNames = this.pld.getParamNames();
/*  210 */     this.paramIndices = new CaselessStringArrayTable(this.pld.getParamNames());
/*  211 */     this.sourceIndices = new CaselessStringArrayTable(odesc.getSourceNames());
/*  213 */     this.paramClasses = this.pld.getParamClasses();
/*  214 */     this.sourceClasses = odesc.getSourceClasses(modeName);
/*  216 */     Object[] defaults = this.pld.getParamDefaults();
/*  218 */     this.parameters = new Vector(this.numParameters);
/*  220 */     for (int i = 0; i < this.numParameters; i++)
/*  221 */       this.parameters.addElement(defaults[i]); 
/*      */   }
/*      */   
/*      */   public ParameterBlockJAI(String operationName, String modeName) {
/*  242 */     this((OperationDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor(modeName, operationName), modeName);
/*      */   }
/*      */   
/*      */   public int indexOfSource(String sourceName) {
/*  258 */     return this.sourceIndices.indexOf(sourceName);
/*      */   }
/*      */   
/*      */   public int indexOfParam(String paramName) {
/*  273 */     return this.paramIndices.indexOf(paramName);
/*      */   }
/*      */   
/*      */   public OperationDescriptor getOperationDescriptor() {
/*  281 */     return this.odesc;
/*      */   }
/*      */   
/*      */   public ParameterListDescriptor getParameterListDescriptor() {
/*  292 */     return this.pld;
/*      */   }
/*      */   
/*      */   public String getMode() {
/*  302 */     return this.modeName;
/*      */   }
/*      */   
/*      */   public ParameterBlockJAI setSource(String sourceName, Object source) {
/*  322 */     if (source == null || sourceName == null)
/*  323 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  327 */     int index = indexOfSource(sourceName);
/*  329 */     if (!this.sourceClasses[index].isInstance(source))
/*  330 */       throw new IllegalArgumentException(JaiI18N.getString("ParameterBlockJAI4")); 
/*  334 */     if (index >= this.odesc.getNumSources()) {
/*  335 */       addSource(source);
/*      */     } else {
/*  337 */       setSource(source, index);
/*      */     } 
/*  340 */     return this;
/*      */   }
/*      */   
/*      */   public Class[] getParamClasses() {
/*  352 */     return this.paramClasses;
/*      */   }
/*      */   
/*      */   private Object getObjectParameter0(String paramName) {
/*  363 */     Object obj = getObjectParameter(indexOfParam(paramName));
/*  365 */     if (obj == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/*  366 */       throw new IllegalStateException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI6")); 
/*  369 */     return obj;
/*      */   }
/*      */   
/*      */   public Object getObjectParameter(String paramName) {
/*  385 */     return getObjectParameter0(paramName);
/*      */   }
/*      */   
/*      */   public byte getByteParameter(String paramName) {
/*  401 */     return ((Byte)getObjectParameter0(paramName)).byteValue();
/*      */   }
/*      */   
/*      */   public boolean getBooleanParameter(String paramName) {
/*  419 */     return ((Boolean)getObjectParameter0(paramName)).booleanValue();
/*      */   }
/*      */   
/*      */   public char getCharParameter(String paramName) {
/*  435 */     return ((Character)getObjectParameter0(paramName)).charValue();
/*      */   }
/*      */   
/*      */   public short getShortParameter(String paramName) {
/*  453 */     return ((Short)getObjectParameter0(paramName)).shortValue();
/*      */   }
/*      */   
/*      */   public int getIntParameter(String paramName) {
/*  469 */     return ((Integer)getObjectParameter0(paramName)).intValue();
/*      */   }
/*      */   
/*      */   public long getLongParameter(String paramName) {
/*  485 */     return ((Long)getObjectParameter0(paramName)).longValue();
/*      */   }
/*      */   
/*      */   public float getFloatParameter(String paramName) {
/*  501 */     return ((Float)getObjectParameter0(paramName)).floatValue();
/*      */   }
/*      */   
/*      */   public double getDoubleParameter(String paramName) {
/*  517 */     return ((Double)getObjectParameter0(paramName)).doubleValue();
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, byte b) {
/*  540 */     return setParameter0(paramName, new Byte(b));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, boolean b) {
/*  561 */     return setParameter0(paramName, new Boolean(b));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, char c) {
/*  582 */     return setParameter0(paramName, new Character(c));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, short s) {
/*  603 */     return setParameter0(paramName, new Short(s));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, int i) {
/*  624 */     return setParameter0(paramName, new Integer(i));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, long l) {
/*  645 */     return setParameter0(paramName, new Long(l));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, float f) {
/*  666 */     return setParameter0(paramName, new Float(f));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, double d) {
/*  687 */     return setParameter0(paramName, new Double(d));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, Object obj) {
/*  712 */     return setParameter0(paramName, obj);
/*      */   }
/*      */   
/*      */   private int checkParameter(String paramName, Object obj) {
/*  732 */     int index = indexOfParam(paramName);
/*  734 */     if (obj != null) {
/*  736 */       if (obj == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/*  737 */         throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI8")); 
/*  741 */       if (obj instanceof DeferredData) {
/*  742 */         DeferredData dd = (DeferredData)obj;
/*  743 */         if (!this.paramClasses[index].isAssignableFrom(dd.getDataClass()))
/*  744 */           throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI0")); 
/*  748 */         if (dd.isValid() && !this.pld.isParameterValueValid(paramName, dd.getData()))
/*  750 */           throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI2")); 
/*  753 */       } else if (!this.paramClasses[index].isInstance(obj)) {
/*  754 */         throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI0"));
/*      */       } 
/*      */     } 
/*  759 */     if ((obj == null || !(obj instanceof DeferredData)) && 
/*  760 */       !this.pld.isParameterValueValid(paramName, obj))
/*  761 */       throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterBlockJAI2")); 
/*  766 */     return index;
/*      */   }
/*      */   
/*      */   private ParameterList setParameter0(String paramName, Object obj) {
/*  793 */     int index = checkParameter(paramName, obj);
/*  795 */     this.parameters.setElementAt(obj, index);
/*  796 */     return this;
/*      */   }
/*      */   
/*      */   public ParameterBlock add(Object obj) {
/*  815 */     throw new IllegalStateException(JaiI18N.getString("ParameterBlockJAI5"));
/*      */   }
/*      */   
/*      */   public ParameterBlock set(Object obj, int index) {
/*  835 */     if (index < 0 || index >= this.pld.getNumParameters())
/*  836 */       throw new ArrayIndexOutOfBoundsException(); 
/*  840 */     setParameter0(this.paramNames[index], obj);
/*  842 */     return this;
/*      */   }
/*      */   
/*      */   public void setParameters(Vector parameters) {
/*  866 */     if (parameters == null || parameters.size() != this.numParameters)
/*  867 */       throw new IllegalArgumentException(JaiI18N.getString("ParameterBlockJAI7")); 
/*  870 */     for (int i = 0; i < this.numParameters; i++)
/*  871 */       checkParameter(this.paramNames[i], parameters.get(i)); 
/*  874 */     this.parameters = parameters;
/*      */   }
/*      */   
/*      */   public int indexOf(String paramName) {
/*  893 */     return indexOfParam(paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(byte b, String paramName) {
/*  911 */     return set(new Byte(b), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(char c, String paramName) {
/*  929 */     return set(new Character(c), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(short s, String paramName) {
/*  947 */     return set(new Short(s), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(int i, String paramName) {
/*  965 */     return set(new Integer(i), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(long l, String paramName) {
/*  983 */     return set(new Long(l), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(float f, String paramName) {
/* 1001 */     return set(new Float(f), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(double d, String paramName) {
/* 1019 */     return set(new Double(d), paramName);
/*      */   }
/*      */   
/*      */   public ParameterBlock set(Object obj, String paramName) {
/* 1040 */     setParameter0(paramName, obj);
/* 1041 */     return this;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1052 */     out.defaultWriteObject();
/* 1055 */     out.writeObject(this.odesc.getName());
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1070 */     in.defaultReadObject();
/* 1073 */     String operationName = (String)in.readObject();
/* 1076 */     this.odesc = (OperationDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor(this.modeName, operationName);
/* 1080 */     if (this.odesc == null)
/* 1081 */       throw new NotSerializableException(operationName + " " + JaiI18N.getString("ParameterBlockJAI1")); 
/*      */   }
/*      */   
/*      */   public Object clone() {
/* 1100 */     ParameterBlockJAI theClone = (ParameterBlockJAI)shallowClone();
/* 1102 */     if (this.sources != null)
/* 1103 */       theClone.setSources((Vector)this.sources.clone()); 
/* 1106 */     if (this.parameters != null)
/* 1109 */       theClone.parameters = (Vector)this.parameters.clone(); 
/* 1111 */     return theClone;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ParameterBlockJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */