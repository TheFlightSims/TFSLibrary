/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ public final class Descriptors {
/*      */   public static final class FileDescriptor {
/*      */     private DescriptorProtos.FileDescriptorProto proto;
/*      */     
/*      */     private final Descriptors.Descriptor[] messageTypes;
/*      */     
/*      */     private final Descriptors.EnumDescriptor[] enumTypes;
/*      */     
/*      */     private final Descriptors.ServiceDescriptor[] services;
/*      */     
/*      */     private final Descriptors.FieldDescriptor[] extensions;
/*      */     
/*      */     private final FileDescriptor[] dependencies;
/*      */     
/*      */     private final Descriptors.DescriptorPool pool;
/*      */     
/*      */     public DescriptorProtos.FileDescriptorProto toProto() {
/*   69 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/*   72 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public String getPackage() {
/*   79 */       return this.proto.getPackage();
/*      */     }
/*      */     
/*      */     public DescriptorProtos.FileOptions getOptions() {
/*   82 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public List<Descriptors.Descriptor> getMessageTypes() {
/*   86 */       return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
/*      */     }
/*      */     
/*      */     public List<Descriptors.EnumDescriptor> getEnumTypes() {
/*   91 */       return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
/*      */     }
/*      */     
/*      */     public List<Descriptors.ServiceDescriptor> getServices() {
/*   96 */       return Collections.unmodifiableList(Arrays.asList(this.services));
/*      */     }
/*      */     
/*      */     public List<Descriptors.FieldDescriptor> getExtensions() {
/*  101 */       return Collections.unmodifiableList(Arrays.asList(this.extensions));
/*      */     }
/*      */     
/*      */     public List<FileDescriptor> getDependencies() {
/*  106 */       return Collections.unmodifiableList(Arrays.asList(this.dependencies));
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor findMessageTypeByName(String name) {
/*  118 */       if (name.indexOf('.') != -1)
/*  119 */         return null; 
/*  121 */       if (getPackage().length() > 0)
/*  122 */         name = getPackage() + '.' + name; 
/*  124 */       Descriptors.GenericDescriptor result = this.pool.findSymbol(name);
/*  125 */       if (result != null && result instanceof Descriptors.Descriptor && result.getFile() == this)
/*  127 */         return (Descriptors.Descriptor)result; 
/*  129 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.EnumDescriptor findEnumTypeByName(String name) {
/*  142 */       if (name.indexOf('.') != -1)
/*  143 */         return null; 
/*  145 */       if (getPackage().length() > 0)
/*  146 */         name = getPackage() + '.' + name; 
/*  148 */       Descriptors.GenericDescriptor result = this.pool.findSymbol(name);
/*  149 */       if (result != null && result instanceof Descriptors.EnumDescriptor && result.getFile() == this)
/*  151 */         return (Descriptors.EnumDescriptor)result; 
/*  153 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.ServiceDescriptor findServiceByName(String name) {
/*  166 */       if (name.indexOf('.') != -1)
/*  167 */         return null; 
/*  169 */       if (getPackage().length() > 0)
/*  170 */         name = getPackage() + '.' + name; 
/*  172 */       Descriptors.GenericDescriptor result = this.pool.findSymbol(name);
/*  173 */       if (result != null && result instanceof Descriptors.ServiceDescriptor && result.getFile() == this)
/*  175 */         return (Descriptors.ServiceDescriptor)result; 
/*  177 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.FieldDescriptor findExtensionByName(String name) {
/*  189 */       if (name.indexOf('.') != -1)
/*  190 */         return null; 
/*  192 */       if (getPackage().length() > 0)
/*  193 */         name = getPackage() + '.' + name; 
/*  195 */       Descriptors.GenericDescriptor result = this.pool.findSymbol(name);
/*  196 */       if (result != null && result instanceof Descriptors.FieldDescriptor && result.getFile() == this)
/*  198 */         return (Descriptors.FieldDescriptor)result; 
/*  200 */       return null;
/*      */     }
/*      */     
/*      */     public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws Descriptors.DescriptorValidationException {
/*  228 */       Descriptors.DescriptorPool pool = new Descriptors.DescriptorPool(dependencies);
/*  229 */       FileDescriptor result = new FileDescriptor(proto, dependencies, pool);
/*  232 */       if (dependencies.length != proto.getDependencyCount())
/*  233 */         throw new Descriptors.DescriptorValidationException(result, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto."); 
/*  237 */       for (int i = 0; i < proto.getDependencyCount(); i++) {
/*  238 */         if (!dependencies[i].getName().equals(proto.getDependency(i)))
/*  239 */           throw new Descriptors.DescriptorValidationException(result, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto."); 
/*      */       } 
/*  245 */       result.crossLink();
/*  246 */       return result;
/*      */     }
/*      */     
/*      */     public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies, InternalDescriptorAssigner descriptorAssigner) {
/*      */       byte[] descriptorBytes;
/*      */       DescriptorProtos.FileDescriptorProto proto;
/*      */       FileDescriptor result;
/*  269 */       StringBuilder descriptorData = new StringBuilder();
/*  270 */       for (String part : descriptorDataParts)
/*  271 */         descriptorData.append(part); 
/*      */       try {
/*  276 */         descriptorBytes = descriptorData.toString().getBytes("ISO-8859-1");
/*  277 */       } catch (UnsupportedEncodingException e) {
/*  278 */         throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", e);
/*      */       } 
/*      */       try {
/*  284 */         proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
/*  285 */       } catch (InvalidProtocolBufferException e) {
/*  286 */         throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */       } 
/*      */       try {
/*  292 */         result = buildFrom(proto, dependencies);
/*  293 */       } catch (DescriptorValidationException e) {
/*  294 */         throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto.getName() + "\".", e);
/*      */       } 
/*  298 */       ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
/*  301 */       if (registry != null) {
/*      */         try {
/*  304 */           proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes, registry);
/*  305 */         } catch (InvalidProtocolBufferException e) {
/*  306 */           throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */         } 
/*  311 */         result.setProto(proto);
/*      */       } 
/*      */     }
/*      */     
/*      */     private FileDescriptor(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, Descriptors.DescriptorPool pool) throws Descriptors.DescriptorValidationException {
/*  343 */       this.pool = pool;
/*  344 */       this.proto = proto;
/*  345 */       this.dependencies = (FileDescriptor[])dependencies.clone();
/*  347 */       pool.addPackage(getPackage(), this);
/*  349 */       this.messageTypes = new Descriptors.Descriptor[proto.getMessageTypeCount()];
/*      */       int i;
/*  350 */       for (i = 0; i < proto.getMessageTypeCount(); i++)
/*  351 */         this.messageTypes[i] = new Descriptors.Descriptor(proto.getMessageType(i), this, null, i); 
/*  355 */       this.enumTypes = new Descriptors.EnumDescriptor[proto.getEnumTypeCount()];
/*  356 */       for (i = 0; i < proto.getEnumTypeCount(); i++)
/*  357 */         this.enumTypes[i] = new Descriptors.EnumDescriptor(proto.getEnumType(i), this, null, i); 
/*  360 */       this.services = new Descriptors.ServiceDescriptor[proto.getServiceCount()];
/*  361 */       for (i = 0; i < proto.getServiceCount(); i++)
/*  362 */         this.services[i] = new Descriptors.ServiceDescriptor(proto.getService(i), this, i); 
/*  365 */       this.extensions = new Descriptors.FieldDescriptor[proto.getExtensionCount()];
/*  366 */       for (i = 0; i < proto.getExtensionCount(); i++)
/*  367 */         this.extensions[i] = new Descriptors.FieldDescriptor(proto.getExtension(i), this, null, i, true); 
/*      */     }
/*      */     
/*      */     private void crossLink() throws Descriptors.DescriptorValidationException {
/*  374 */       for (Descriptors.Descriptor messageType : this.messageTypes)
/*  375 */         messageType.crossLink(); 
/*  378 */       for (Descriptors.ServiceDescriptor service : this.services)
/*  379 */         service.crossLink(); 
/*  382 */       for (Descriptors.FieldDescriptor extension : this.extensions)
/*  383 */         extension.crossLink(); 
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.FileDescriptorProto proto) {
/*  398 */       this.proto = proto;
/*      */       int i;
/*  400 */       for (i = 0; i < this.messageTypes.length; i++)
/*  401 */         this.messageTypes[i].setProto(proto.getMessageType(i)); 
/*  404 */       for (i = 0; i < this.enumTypes.length; i++)
/*  405 */         this.enumTypes[i].setProto(proto.getEnumType(i)); 
/*  408 */       for (i = 0; i < this.services.length; i++)
/*  409 */         this.services[i].setProto(proto.getService(i)); 
/*  412 */       for (i = 0; i < this.extensions.length; i++)
/*  413 */         this.extensions[i].setProto(proto.getExtension(i)); 
/*      */     }
/*      */     
/*      */     public static interface InternalDescriptorAssigner {
/*      */       ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor param2FileDescriptor);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class Descriptor implements GenericDescriptor {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.DescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private final Descriptor containingType;
/*      */     
/*      */     private final Descriptor[] nestedTypes;
/*      */     
/*      */     private final Descriptors.EnumDescriptor[] enumTypes;
/*      */     
/*      */     private final Descriptors.FieldDescriptor[] fields;
/*      */     
/*      */     private final Descriptors.FieldDescriptor[] extensions;
/*      */     
/*      */     public int getIndex() {
/*  435 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.DescriptorProto toProto() {
/*  438 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/*  441 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/*  454 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/*  457 */       return this.file;
/*      */     }
/*      */     
/*      */     public Descriptor getContainingType() {
/*  460 */       return this.containingType;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.MessageOptions getOptions() {
/*  463 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public List<Descriptors.FieldDescriptor> getFields() {
/*  467 */       return Collections.unmodifiableList(Arrays.asList(this.fields));
/*      */     }
/*      */     
/*      */     public List<Descriptors.FieldDescriptor> getExtensions() {
/*  472 */       return Collections.unmodifiableList(Arrays.asList(this.extensions));
/*      */     }
/*      */     
/*      */     public List<Descriptor> getNestedTypes() {
/*  477 */       return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
/*      */     }
/*      */     
/*      */     public List<Descriptors.EnumDescriptor> getEnumTypes() {
/*  482 */       return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
/*      */     }
/*      */     
/*      */     public boolean isExtensionNumber(int number) {
/*  488 */       for (DescriptorProtos.DescriptorProto.ExtensionRange range : this.proto.getExtensionRangeList()) {
/*  489 */         if (range.getStart() <= number && number < range.getEnd())
/*  490 */           return true; 
/*      */       } 
/*  493 */       return false;
/*      */     }
/*      */     
/*      */     public Descriptors.FieldDescriptor findFieldByName(String name) {
/*  502 */       Descriptors.GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  504 */       if (result != null && result instanceof Descriptors.FieldDescriptor)
/*  505 */         return (Descriptors.FieldDescriptor)result; 
/*  507 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.FieldDescriptor findFieldByNumber(int number) {
/*  517 */       return (Descriptors.FieldDescriptor)this.file.pool.fieldsByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, number));
/*      */     }
/*      */     
/*      */     public Descriptor findNestedTypeByName(String name) {
/*  527 */       Descriptors.GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  529 */       if (result != null && result instanceof Descriptor)
/*  530 */         return (Descriptor)result; 
/*  532 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.EnumDescriptor findEnumTypeByName(String name) {
/*  542 */       Descriptors.GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  544 */       if (result != null && result instanceof Descriptors.EnumDescriptor)
/*  545 */         return (Descriptors.EnumDescriptor)result; 
/*  547 */       return null;
/*      */     }
/*      */     
/*      */     private Descriptor(DescriptorProtos.DescriptorProto proto, Descriptors.FileDescriptor file, Descriptor parent, int index) throws Descriptors.DescriptorValidationException {
/*  566 */       this.index = index;
/*  567 */       this.proto = proto;
/*  568 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/*  569 */       this.file = file;
/*  570 */       this.containingType = parent;
/*  572 */       this.nestedTypes = new Descriptor[proto.getNestedTypeCount()];
/*      */       int i;
/*  573 */       for (i = 0; i < proto.getNestedTypeCount(); i++)
/*  574 */         this.nestedTypes[i] = new Descriptor(proto.getNestedType(i), file, this, i); 
/*  578 */       this.enumTypes = new Descriptors.EnumDescriptor[proto.getEnumTypeCount()];
/*  579 */       for (i = 0; i < proto.getEnumTypeCount(); i++)
/*  580 */         this.enumTypes[i] = new Descriptors.EnumDescriptor(proto.getEnumType(i), file, this, i); 
/*  584 */       this.fields = new Descriptors.FieldDescriptor[proto.getFieldCount()];
/*  585 */       for (i = 0; i < proto.getFieldCount(); i++)
/*  586 */         this.fields[i] = new Descriptors.FieldDescriptor(proto.getField(i), file, this, i, false); 
/*  590 */       this.extensions = new Descriptors.FieldDescriptor[proto.getExtensionCount()];
/*  591 */       for (i = 0; i < proto.getExtensionCount(); i++)
/*  592 */         this.extensions[i] = new Descriptors.FieldDescriptor(proto.getExtension(i), file, this, i, true); 
/*  596 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void crossLink() throws Descriptors.DescriptorValidationException {
/*  601 */       for (Descriptor nestedType : this.nestedTypes)
/*  602 */         nestedType.crossLink(); 
/*  605 */       for (Descriptors.FieldDescriptor field : this.fields)
/*  606 */         field.crossLink(); 
/*  609 */       for (Descriptors.FieldDescriptor extension : this.extensions)
/*  610 */         extension.crossLink(); 
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.DescriptorProto proto) {
/*  616 */       this.proto = proto;
/*      */       int i;
/*  618 */       for (i = 0; i < this.nestedTypes.length; i++)
/*  619 */         this.nestedTypes[i].setProto(proto.getNestedType(i)); 
/*  622 */       for (i = 0; i < this.enumTypes.length; i++)
/*  623 */         this.enumTypes[i].setProto(proto.getEnumType(i)); 
/*  626 */       for (i = 0; i < this.fields.length; i++)
/*  627 */         this.fields[i].setProto(proto.getField(i)); 
/*  630 */       for (i = 0; i < this.extensions.length; i++)
/*  631 */         this.extensions[i].setProto(proto.getExtension(i)); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class FieldDescriptor implements GenericDescriptor, Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
/*      */     public int getIndex() {
/*  646 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.FieldDescriptorProto toProto() {
/*  649 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/*  652 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public int getNumber() {
/*  655 */       return this.proto.getNumber();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/*  661 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public JavaType getJavaType() {
/*  667 */       return this.type.getJavaType();
/*      */     }
/*      */     
/*      */     public WireFormat.JavaType getLiteJavaType() {
/*  671 */       return getLiteType().getJavaType();
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/*  675 */       return this.file;
/*      */     }
/*      */     
/*      */     public Type getType() {
/*  678 */       return this.type;
/*      */     }
/*      */     
/*      */     public WireFormat.FieldType getLiteType() {
/*  682 */       return table[this.type.ordinal()];
/*      */     }
/*      */     
/*  687 */     private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
/*      */     
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.FieldDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private final Descriptors.Descriptor extensionScope;
/*      */     
/*      */     private Type type;
/*      */     
/*      */     private Descriptors.Descriptor containingType;
/*      */     
/*      */     private Descriptors.Descriptor messageType;
/*      */     
/*      */     private Descriptors.EnumDescriptor enumType;
/*      */     
/*      */     private Object defaultValue;
/*      */     
/*      */     public boolean isRequired() {
/*  692 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED);
/*      */     }
/*      */     
/*      */     public boolean isOptional() {
/*  697 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL);
/*      */     }
/*      */     
/*      */     public boolean isRepeated() {
/*  702 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED);
/*      */     }
/*      */     
/*      */     public boolean isPacked() {
/*  707 */       return getOptions().getPacked();
/*      */     }
/*      */     
/*      */     public boolean isPackable() {
/*  712 */       return (isRepeated() && getLiteType().isPackable());
/*      */     }
/*      */     
/*      */     public boolean hasDefaultValue() {
/*  716 */       return this.proto.hasDefaultValue();
/*      */     }
/*      */     
/*      */     public Object getDefaultValue() {
/*  724 */       if (getJavaType() == JavaType.MESSAGE)
/*  725 */         throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field."); 
/*  729 */       return this.defaultValue;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.FieldOptions getOptions() {
/*  733 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public boolean isExtension() {
/*  736 */       return this.proto.hasExtendee();
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getContainingType() {
/*  743 */       return this.containingType;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getExtensionScope() {
/*  767 */       if (!isExtension())
/*  768 */         throw new UnsupportedOperationException("This field is not an extension."); 
/*  771 */       return this.extensionScope;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getMessageType() {
/*  776 */       if (getJavaType() != JavaType.MESSAGE)
/*  777 */         throw new UnsupportedOperationException("This field is not of message type."); 
/*  780 */       return this.messageType;
/*      */     }
/*      */     
/*      */     public Descriptors.EnumDescriptor getEnumType() {
/*  785 */       if (getJavaType() != JavaType.ENUM)
/*  786 */         throw new UnsupportedOperationException("This field is not of enum type."); 
/*  789 */       return this.enumType;
/*      */     }
/*      */     
/*      */     public int compareTo(FieldDescriptor other) {
/*  803 */       if (other.containingType != this.containingType)
/*  804 */         throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type."); 
/*  808 */       return getNumber() - other.getNumber();
/*      */     }
/*      */     
/*      */     public enum Type {
/*  826 */       DOUBLE((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
/*  827 */       FLOAT((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
/*  828 */       INT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
/*  829 */       UINT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
/*  830 */       INT32((String)Descriptors.FieldDescriptor.JavaType.INT),
/*  831 */       FIXED64((String)Descriptors.FieldDescriptor.JavaType.LONG),
/*  832 */       FIXED32((String)Descriptors.FieldDescriptor.JavaType.INT),
/*  833 */       BOOL((String)Descriptors.FieldDescriptor.JavaType.BOOLEAN),
/*  834 */       STRING((String)Descriptors.FieldDescriptor.JavaType.STRING),
/*  835 */       GROUP((String)Descriptors.FieldDescriptor.JavaType.MESSAGE),
/*  836 */       MESSAGE((String)Descriptors.FieldDescriptor.JavaType.MESSAGE),
/*  837 */       BYTES((String)Descriptors.FieldDescriptor.JavaType.BYTE_STRING),
/*  838 */       UINT32((String)Descriptors.FieldDescriptor.JavaType.INT),
/*  839 */       ENUM((String)Descriptors.FieldDescriptor.JavaType.ENUM),
/*  840 */       SFIXED32((String)Descriptors.FieldDescriptor.JavaType.INT),
/*  841 */       SFIXED64((String)Descriptors.FieldDescriptor.JavaType.LONG),
/*  842 */       SINT32((String)Descriptors.FieldDescriptor.JavaType.INT),
/*  843 */       SINT64((String)Descriptors.FieldDescriptor.JavaType.LONG);
/*      */       
/*      */       private Descriptors.FieldDescriptor.JavaType javaType;
/*      */       
/*      */       Type(Descriptors.FieldDescriptor.JavaType javaType) {
/*  846 */         this.javaType = javaType;
/*      */       }
/*      */       
/*      */       public DescriptorProtos.FieldDescriptorProto.Type toProto() {
/*  852 */         return DescriptorProtos.FieldDescriptorProto.Type.valueOf(ordinal() + 1);
/*      */       }
/*      */       
/*      */       public Descriptors.FieldDescriptor.JavaType getJavaType() {
/*  854 */         return this.javaType;
/*      */       }
/*      */     }
/*      */     
/*      */     static {
/*  863 */       if ((Type.values()).length != (DescriptorProtos.FieldDescriptorProto.Type.values()).length)
/*  864 */         throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated."); 
/*      */     }
/*      */     
/*      */     public enum JavaType {
/*  871 */       INT((String)Integer.valueOf(0)),
/*  872 */       LONG((String)Long.valueOf(0L)),
/*  873 */       FLOAT((String)Float.valueOf(0.0F)),
/*  874 */       DOUBLE((String)Double.valueOf(0.0D)),
/*  875 */       BOOLEAN((String)Boolean.valueOf(false)),
/*  876 */       STRING(""),
/*  877 */       BYTE_STRING((String)ByteString.EMPTY),
/*  878 */       ENUM(null),
/*  879 */       MESSAGE(null);
/*      */       
/*      */       private final Object defaultDefault;
/*      */       
/*      */       JavaType(Object defaultDefault) {
/*  882 */         this.defaultDefault = defaultDefault;
/*      */       }
/*      */     }
/*      */     
/*      */     private FieldDescriptor(DescriptorProtos.FieldDescriptorProto proto, Descriptors.FileDescriptor file, Descriptors.Descriptor parent, int index, boolean isExtension) throws Descriptors.DescriptorValidationException {
/*  898 */       this.index = index;
/*  899 */       this.proto = proto;
/*  900 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/*  901 */       this.file = file;
/*  903 */       if (proto.hasType())
/*  904 */         this.type = Type.valueOf(proto.getType()); 
/*  907 */       if (getNumber() <= 0)
/*  908 */         throw new Descriptors.DescriptorValidationException(this, "Field numbers must be positive integers."); 
/*  913 */       if (proto.getOptions().getPacked() && !isPackable())
/*  914 */         throw new Descriptors.DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields."); 
/*  919 */       if (isExtension) {
/*  920 */         if (!proto.hasExtendee())
/*  921 */           throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field."); 
/*  924 */         this.containingType = null;
/*  925 */         if (parent != null) {
/*  926 */           this.extensionScope = parent;
/*      */         } else {
/*  928 */           this.extensionScope = null;
/*      */         } 
/*      */       } else {
/*  931 */         if (proto.hasExtendee())
/*  932 */           throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field."); 
/*  935 */         this.containingType = parent;
/*  936 */         this.extensionScope = null;
/*      */       } 
/*  939 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void crossLink() throws Descriptors.DescriptorValidationException {
/*  944 */       if (this.proto.hasExtendee()) {
/*  945 */         Descriptors.GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto.getExtendee(), this);
/*  947 */         if (!(extendee instanceof Descriptors.Descriptor))
/*  948 */           throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getExtendee() + "\" is not a message type."); 
/*  951 */         this.containingType = (Descriptors.Descriptor)extendee;
/*  953 */         if (!getContainingType().isExtensionNumber(getNumber()))
/*  954 */           throw new Descriptors.DescriptorValidationException(this, '"' + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number."); 
/*      */       } 
/*  961 */       if (this.proto.hasTypeName()) {
/*  962 */         Descriptors.GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto.getTypeName(), this);
/*  965 */         if (!this.proto.hasType())
/*  967 */           if (typeDescriptor instanceof Descriptors.Descriptor) {
/*  968 */             this.type = Type.MESSAGE;
/*  969 */           } else if (typeDescriptor instanceof Descriptors.EnumDescriptor) {
/*  970 */             this.type = Type.ENUM;
/*      */           } else {
/*  972 */             throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a type.");
/*      */           }  
/*  977 */         if (getJavaType() == JavaType.MESSAGE) {
/*  978 */           if (!(typeDescriptor instanceof Descriptors.Descriptor))
/*  979 */             throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a message type."); 
/*  982 */           this.messageType = (Descriptors.Descriptor)typeDescriptor;
/*  984 */           if (this.proto.hasDefaultValue())
/*  985 */             throw new Descriptors.DescriptorValidationException(this, "Messages can't have default values."); 
/*  988 */         } else if (getJavaType() == JavaType.ENUM) {
/*  989 */           if (!(typeDescriptor instanceof Descriptors.EnumDescriptor))
/*  990 */             throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not an enum type."); 
/*  993 */           this.enumType = (Descriptors.EnumDescriptor)typeDescriptor;
/*      */         } else {
/*  995 */           throw new Descriptors.DescriptorValidationException(this, "Field with primitive type has type_name.");
/*      */         } 
/*  999 */       } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
/* 1001 */         throw new Descriptors.DescriptorValidationException(this, "Field with message or enum type missing type_name.");
/*      */       } 
/* 1008 */       if (this.proto.hasDefaultValue()) {
/* 1009 */         if (isRepeated())
/* 1010 */           throw new Descriptors.DescriptorValidationException(this, "Repeated fields cannot have default values."); 
/*      */         try {
/* 1015 */           switch (getType()) {
/*      */             case ENUM:
/*      */             case MESSAGE:
/*      */             case null:
/* 1019 */               this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1023 */               this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/*      */             case null:
/* 1028 */               this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1032 */               this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/* 1035 */               if (this.proto.getDefaultValue().equals("inf")) {
/* 1036 */                 this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
/*      */                 break;
/*      */               } 
/* 1037 */               if (this.proto.getDefaultValue().equals("-inf")) {
/* 1038 */                 this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
/*      */                 break;
/*      */               } 
/* 1039 */               if (this.proto.getDefaultValue().equals("nan")) {
/* 1040 */                 this.defaultValue = Float.valueOf(Float.NaN);
/*      */                 break;
/*      */               } 
/* 1042 */               this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             case null:
/* 1046 */               if (this.proto.getDefaultValue().equals("inf")) {
/* 1047 */                 this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
/*      */                 break;
/*      */               } 
/* 1048 */               if (this.proto.getDefaultValue().equals("-inf")) {
/* 1049 */                 this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
/*      */                 break;
/*      */               } 
/* 1050 */               if (this.proto.getDefaultValue().equals("nan")) {
/* 1051 */                 this.defaultValue = Double.valueOf(Double.NaN);
/*      */                 break;
/*      */               } 
/* 1053 */               this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             case null:
/* 1057 */               this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             case null:
/* 1060 */               this.defaultValue = this.proto.getDefaultValue();
/*      */               break;
/*      */             case null:
/*      */               try {
/* 1064 */                 this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
/* 1066 */               } catch (InvalidEscapeSequenceException e) {
/* 1067 */                 throw new Descriptors.DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e);
/*      */               } 
/*      */               break;
/*      */             case null:
/* 1072 */               this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
/* 1073 */               if (this.defaultValue == null)
/* 1074 */                 throw new Descriptors.DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '"'); 
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1081 */               throw new Descriptors.DescriptorValidationException(this, "Message type had default value.");
/*      */           } 
/* 1084 */         } catch (NumberFormatException e) {
/* 1085 */           throw new Descriptors.DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '"', e);
/*      */         } 
/* 1091 */       } else if (isRepeated()) {
/* 1092 */         this.defaultValue = Collections.emptyList();
/*      */       } else {
/* 1094 */         switch (getJavaType()) {
/*      */           case ENUM:
/* 1098 */             this.defaultValue = this.enumType.getValues().get(0);
/*      */             break;
/*      */           case MESSAGE:
/* 1101 */             this.defaultValue = null;
/*      */             break;
/*      */           default:
/* 1104 */             this.defaultValue = (getJavaType()).defaultDefault;
/*      */             break;
/*      */         } 
/*      */       } 
/* 1110 */       if (!isExtension())
/* 1111 */         this.file.pool.addFieldByNumber(this); 
/* 1114 */       if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat())
/* 1116 */         if (isExtension()) {
/* 1117 */           if (!isOptional() || getType() != Type.MESSAGE)
/* 1118 */             throw new Descriptors.DescriptorValidationException(this, "Extensions of MessageSets must be optional messages."); 
/*      */         } else {
/* 1122 */           throw new Descriptors.DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.");
/*      */         }  
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.FieldDescriptorProto proto) {
/* 1130 */       this.proto = proto;
/*      */     }
/*      */     
/*      */     public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
/* 1141 */       return ((Message.Builder)to).mergeFrom((Message)from);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class EnumDescriptor implements GenericDescriptor, Internal.EnumLiteMap<EnumValueDescriptor> {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.EnumDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private final Descriptors.Descriptor containingType;
/*      */     
/*      */     private Descriptors.EnumValueDescriptor[] values;
/*      */     
/*      */     public int getIndex() {
/* 1154 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.EnumDescriptorProto toProto() {
/* 1157 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1160 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/* 1166 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/* 1169 */       return this.file;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getContainingType() {
/* 1172 */       return this.containingType;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.EnumOptions getOptions() {
/* 1175 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public List<Descriptors.EnumValueDescriptor> getValues() {
/* 1179 */       return Collections.unmodifiableList(Arrays.asList(this.values));
/*      */     }
/*      */     
/*      */     public Descriptors.EnumValueDescriptor findValueByName(String name) {
/* 1188 */       Descriptors.GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/* 1190 */       if (result != null && result instanceof Descriptors.EnumValueDescriptor)
/* 1191 */         return (Descriptors.EnumValueDescriptor)result; 
/* 1193 */       return null;
/*      */     }
/*      */     
/*      */     public Descriptors.EnumValueDescriptor findValueByNumber(int number) {
/* 1204 */       return (Descriptors.EnumValueDescriptor)this.file.pool.enumValuesByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, number));
/*      */     }
/*      */     
/*      */     private EnumDescriptor(DescriptorProtos.EnumDescriptorProto proto, Descriptors.FileDescriptor file, Descriptors.Descriptor parent, int index) throws Descriptors.DescriptorValidationException {
/* 1220 */       this.index = index;
/* 1221 */       this.proto = proto;
/* 1222 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/* 1223 */       this.file = file;
/* 1224 */       this.containingType = parent;
/* 1226 */       if (proto.getValueCount() == 0)
/* 1229 */         throw new Descriptors.DescriptorValidationException(this, "Enums must contain at least one value."); 
/* 1233 */       this.values = new Descriptors.EnumValueDescriptor[proto.getValueCount()];
/* 1234 */       for (int i = 0; i < proto.getValueCount(); i++)
/* 1235 */         this.values[i] = new Descriptors.EnumValueDescriptor(proto.getValue(i), file, this, i); 
/* 1239 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.EnumDescriptorProto proto) {
/* 1244 */       this.proto = proto;
/* 1246 */       for (int i = 0; i < this.values.length; i++)
/* 1247 */         this.values[i].setProto(proto.getValue(i)); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class EnumValueDescriptor implements GenericDescriptor, Internal.EnumLite {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.EnumValueDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private final Descriptors.EnumDescriptor type;
/*      */     
/*      */     public int getIndex() {
/* 1266 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.EnumValueDescriptorProto toProto() {
/* 1269 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1272 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public int getNumber() {
/* 1275 */       return this.proto.getNumber();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/* 1281 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/* 1284 */       return this.file;
/*      */     }
/*      */     
/*      */     public Descriptors.EnumDescriptor getType() {
/* 1287 */       return this.type;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.EnumValueOptions getOptions() {
/* 1292 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto proto, Descriptors.FileDescriptor file, Descriptors.EnumDescriptor parent, int index) throws Descriptors.DescriptorValidationException {
/* 1305 */       this.index = index;
/* 1306 */       this.proto = proto;
/* 1307 */       this.file = file;
/* 1308 */       this.type = parent;
/* 1310 */       this.fullName = parent.getFullName() + '.' + proto.getName();
/* 1312 */       file.pool.addSymbol(this);
/* 1313 */       file.pool.addEnumValueByNumber(this);
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.EnumValueDescriptorProto proto) {
/* 1318 */       this.proto = proto;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class ServiceDescriptor implements GenericDescriptor {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.ServiceDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private Descriptors.MethodDescriptor[] methods;
/*      */     
/*      */     public int getIndex() {
/* 1330 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.ServiceDescriptorProto toProto() {
/* 1333 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1336 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/* 1342 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/* 1345 */       return this.file;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.ServiceOptions getOptions() {
/* 1348 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public List<Descriptors.MethodDescriptor> getMethods() {
/* 1352 */       return Collections.unmodifiableList(Arrays.asList(this.methods));
/*      */     }
/*      */     
/*      */     public Descriptors.MethodDescriptor findMethodByName(String name) {
/* 1361 */       Descriptors.GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/* 1363 */       if (result != null && result instanceof Descriptors.MethodDescriptor)
/* 1364 */         return (Descriptors.MethodDescriptor)result; 
/* 1366 */       return null;
/*      */     }
/*      */     
/*      */     private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto proto, Descriptors.FileDescriptor file, int index) throws Descriptors.DescriptorValidationException {
/* 1380 */       this.index = index;
/* 1381 */       this.proto = proto;
/* 1382 */       this.fullName = Descriptors.computeFullName(file, null, proto.getName());
/* 1383 */       this.file = file;
/* 1385 */       this.methods = new Descriptors.MethodDescriptor[proto.getMethodCount()];
/* 1386 */       for (int i = 0; i < proto.getMethodCount(); i++)
/* 1387 */         this.methods[i] = new Descriptors.MethodDescriptor(proto.getMethod(i), file, this, i); 
/* 1391 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void crossLink() throws Descriptors.DescriptorValidationException {
/* 1395 */       for (Descriptors.MethodDescriptor method : this.methods)
/* 1396 */         method.crossLink(); 
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.ServiceDescriptorProto proto) {
/* 1402 */       this.proto = proto;
/* 1404 */       for (int i = 0; i < this.methods.length; i++)
/* 1405 */         this.methods[i].setProto(proto.getMethod(i)); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class MethodDescriptor implements GenericDescriptor {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.MethodDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final Descriptors.FileDescriptor file;
/*      */     
/*      */     private final Descriptors.ServiceDescriptor service;
/*      */     
/*      */     private Descriptors.Descriptor inputType;
/*      */     
/*      */     private Descriptors.Descriptor outputType;
/*      */     
/*      */     public int getIndex() {
/* 1420 */       return this.index;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.MethodDescriptorProto toProto() {
/* 1423 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1426 */       return this.proto.getName();
/*      */     }
/*      */     
/*      */     public String getFullName() {
/* 1432 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptors.FileDescriptor getFile() {
/* 1435 */       return this.file;
/*      */     }
/*      */     
/*      */     public Descriptors.ServiceDescriptor getService() {
/* 1438 */       return this.service;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getInputType() {
/* 1441 */       return this.inputType;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getOutputType() {
/* 1444 */       return this.outputType;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.MethodOptions getOptions() {
/* 1449 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     private MethodDescriptor(DescriptorProtos.MethodDescriptorProto proto, Descriptors.FileDescriptor file, Descriptors.ServiceDescriptor parent, int index) throws Descriptors.DescriptorValidationException {
/* 1466 */       this.index = index;
/* 1467 */       this.proto = proto;
/* 1468 */       this.file = file;
/* 1469 */       this.service = parent;
/* 1471 */       this.fullName = parent.getFullName() + '.' + proto.getName();
/* 1473 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void crossLink() throws Descriptors.DescriptorValidationException {
/* 1477 */       Descriptors.GenericDescriptor input = this.file.pool.lookupSymbol(this.proto.getInputType(), this);
/* 1479 */       if (!(input instanceof Descriptors.Descriptor))
/* 1480 */         throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getInputType() + "\" is not a message type."); 
/* 1483 */       this.inputType = (Descriptors.Descriptor)input;
/* 1485 */       Descriptors.GenericDescriptor output = this.file.pool.lookupSymbol(this.proto.getOutputType(), this);
/* 1487 */       if (!(output instanceof Descriptors.Descriptor))
/* 1488 */         throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getOutputType() + "\" is not a message type."); 
/* 1491 */       this.outputType = (Descriptors.Descriptor)output;
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.MethodDescriptorProto proto) {
/* 1496 */       this.proto = proto;
/*      */     }
/*      */   }
/*      */   
/*      */   private static String computeFullName(FileDescriptor file, Descriptor parent, String name) {
/* 1505 */     if (parent != null)
/* 1506 */       return parent.getFullName() + '.' + name; 
/* 1507 */     if (file.getPackage().length() > 0)
/* 1508 */       return file.getPackage() + '.' + name; 
/* 1510 */     return name;
/*      */   }
/*      */   
/*      */   public static class DescriptorValidationException extends Exception {
/*      */     private static final long serialVersionUID = 5750205775490483148L;
/*      */     
/*      */     private final String name;
/*      */     
/*      */     private final Message proto;
/*      */     
/*      */     private final String description;
/*      */     
/*      */     public String getProblemSymbolName() {
/* 1535 */       return this.name;
/*      */     }
/*      */     
/*      */     public Message getProblemProto() {
/* 1540 */       return this.proto;
/*      */     }
/*      */     
/*      */     public String getDescription() {
/* 1545 */       return this.description;
/*      */     }
/*      */     
/*      */     private DescriptorValidationException(Descriptors.GenericDescriptor problemDescriptor, String description) {
/* 1554 */       super(problemDescriptor.getFullName() + ": " + description);
/* 1559 */       this.name = problemDescriptor.getFullName();
/* 1560 */       this.proto = problemDescriptor.toProto();
/* 1561 */       this.description = description;
/*      */     }
/*      */     
/*      */     private DescriptorValidationException(Descriptors.GenericDescriptor problemDescriptor, String description, Throwable cause) {
/* 1568 */       this(problemDescriptor, description);
/* 1569 */       initCause(cause);
/*      */     }
/*      */     
/*      */     private DescriptorValidationException(Descriptors.FileDescriptor problemDescriptor, String description) {
/* 1575 */       super(problemDescriptor.getName() + ": " + description);
/* 1580 */       this.name = problemDescriptor.getName();
/* 1581 */       this.proto = problemDescriptor.toProto();
/* 1582 */       this.description = description;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class DescriptorPool {
/*      */     private final DescriptorPool[] dependencies;
/*      */     
/*      */     private final Map<String, Descriptors.GenericDescriptor> descriptorsByName;
/*      */     
/*      */     private final Map<DescriptorIntPair, Descriptors.FieldDescriptor> fieldsByNumber;
/*      */     
/*      */     private final Map<DescriptorIntPair, Descriptors.EnumValueDescriptor> enumValuesByNumber;
/*      */     
/*      */     DescriptorPool(Descriptors.FileDescriptor[] dependencies) {
/* 1614 */       this.descriptorsByName = new HashMap<String, Descriptors.GenericDescriptor>();
/* 1616 */       this.fieldsByNumber = new HashMap<DescriptorIntPair, Descriptors.FieldDescriptor>();
/* 1618 */       this.enumValuesByNumber = new HashMap<DescriptorIntPair, Descriptors.EnumValueDescriptor>();
/*      */       this.dependencies = new DescriptorPool[dependencies.length];
/*      */       for (int i = 0; i < dependencies.length; i++)
/*      */         this.dependencies[i] = (dependencies[i]).pool; 
/*      */       for (Descriptors.FileDescriptor dependency : dependencies) {
/*      */         try {
/*      */           addPackage(dependency.getPackage(), dependency);
/*      */         } catch (DescriptorValidationException e) {
/*      */           assert false;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     Descriptors.GenericDescriptor findSymbol(String fullName) {
/* 1623 */       Descriptors.GenericDescriptor result = this.descriptorsByName.get(fullName);
/* 1624 */       if (result != null)
/* 1625 */         return result; 
/* 1628 */       for (DescriptorPool dependency : this.dependencies) {
/* 1629 */         result = dependency.descriptorsByName.get(fullName);
/* 1630 */         if (result != null)
/* 1631 */           return result; 
/*      */       } 
/* 1635 */       return null;
/*      */     }
/*      */     
/*      */     Descriptors.GenericDescriptor lookupSymbol(String name, Descriptors.GenericDescriptor relativeTo) throws Descriptors.DescriptorValidationException {
/*      */       Descriptors.GenericDescriptor result;
/* 1650 */       if (name.startsWith(".")) {
/* 1652 */         result = findSymbol(name.substring(1));
/*      */       } else {
/*      */         String firstPart;
/* 1656 */         int firstPartLength = name.indexOf('.');
/* 1658 */         if (firstPartLength == -1) {
/* 1659 */           firstPart = name;
/*      */         } else {
/* 1661 */           firstPart = name.substring(0, firstPartLength);
/*      */         } 
/* 1666 */         StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());
/*      */         while (true) {
/* 1671 */           int dotpos = scopeToTry.lastIndexOf(".");
/* 1672 */           if (dotpos == -1) {
/* 1673 */             Descriptors.GenericDescriptor genericDescriptor = findSymbol(name);
/*      */             break;
/*      */           } 
/* 1676 */           scopeToTry.setLength(dotpos + 1);
/* 1679 */           scopeToTry.append(firstPart);
/* 1680 */           result = findSymbol(scopeToTry.toString());
/* 1682 */           if (result != null) {
/* 1683 */             if (firstPartLength != -1) {
/* 1687 */               scopeToTry.setLength(dotpos + 1);
/* 1688 */               scopeToTry.append(name);
/* 1689 */               result = findSymbol(scopeToTry.toString());
/*      */             } 
/*      */             break;
/*      */           } 
/* 1695 */           scopeToTry.setLength(dotpos);
/*      */         } 
/*      */       } 
/* 1700 */       if (result == null)
/* 1701 */         throw new Descriptors.DescriptorValidationException(relativeTo, '"' + name + "\" is not defined."); 
/* 1704 */       return result;
/*      */     }
/*      */     
/*      */     void addSymbol(Descriptors.GenericDescriptor descriptor) throws Descriptors.DescriptorValidationException {
/* 1714 */       validateSymbolName(descriptor);
/* 1716 */       String fullName = descriptor.getFullName();
/* 1717 */       int dotpos = fullName.lastIndexOf('.');
/* 1719 */       Descriptors.GenericDescriptor old = this.descriptorsByName.put(fullName, descriptor);
/* 1720 */       if (old != null) {
/* 1721 */         this.descriptorsByName.put(fullName, old);
/* 1723 */         if (descriptor.getFile() == old.getFile()) {
/* 1724 */           if (dotpos == -1)
/* 1725 */             throw new Descriptors.DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined."); 
/* 1728 */           throw new Descriptors.DescriptorValidationException(descriptor, '"' + fullName.substring(dotpos + 1) + "\" is already defined in \"" + fullName.substring(0, dotpos) + "\".");
/*      */         } 
/* 1734 */         throw new Descriptors.DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined in file \"" + old.getFile().getName() + "\".");
/*      */       } 
/*      */     }
/*      */     
/*      */     private static final class PackageDescriptor implements Descriptors.GenericDescriptor {
/*      */       private final String name;
/*      */       
/*      */       private final String fullName;
/*      */       
/*      */       private final Descriptors.FileDescriptor file;
/*      */       
/*      */       public Message toProto() {
/* 1747 */         return this.file.toProto();
/*      */       }
/*      */       
/*      */       public String getName() {
/* 1748 */         return this.name;
/*      */       }
/*      */       
/*      */       public String getFullName() {
/* 1749 */         return this.fullName;
/*      */       }
/*      */       
/*      */       public Descriptors.FileDescriptor getFile() {
/* 1750 */         return this.file;
/*      */       }
/*      */       
/*      */       PackageDescriptor(String name, String fullName, Descriptors.FileDescriptor file) {
/* 1754 */         this.file = file;
/* 1755 */         this.fullName = fullName;
/* 1756 */         this.name = name;
/*      */       }
/*      */     }
/*      */     
/*      */     void addPackage(String fullName, Descriptors.FileDescriptor file) throws Descriptors.DescriptorValidationException {
/*      */       String name;
/* 1772 */       int dotpos = fullName.lastIndexOf('.');
/* 1774 */       if (dotpos == -1) {
/* 1775 */         name = fullName;
/*      */       } else {
/* 1777 */         addPackage(fullName.substring(0, dotpos), file);
/* 1778 */         name = fullName.substring(dotpos + 1);
/*      */       } 
/* 1781 */       Descriptors.GenericDescriptor old = this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
/* 1784 */       if (old != null) {
/* 1785 */         this.descriptorsByName.put(fullName, old);
/* 1786 */         if (!(old instanceof PackageDescriptor))
/* 1787 */           throw new Descriptors.DescriptorValidationException(file, '"' + name + "\" is already defined (as something other than a " + "package) in file \"" + old.getFile().getName() + "\"."); 
/*      */       } 
/*      */     }
/*      */     
/*      */     private static final class DescriptorIntPair {
/*      */       private final Descriptors.GenericDescriptor descriptor;
/*      */       
/*      */       private final int number;
/*      */       
/*      */       DescriptorIntPair(Descriptors.GenericDescriptor descriptor, int number) {
/* 1800 */         this.descriptor = descriptor;
/* 1801 */         this.number = number;
/*      */       }
/*      */       
/*      */       public int hashCode() {
/* 1806 */         return this.descriptor.hashCode() * 65535 + this.number;
/*      */       }
/*      */       
/*      */       public boolean equals(Object obj) {
/* 1810 */         if (!(obj instanceof DescriptorIntPair))
/* 1811 */           return false; 
/* 1813 */         DescriptorIntPair other = (DescriptorIntPair)obj;
/* 1814 */         return (this.descriptor == other.descriptor && this.number == other.number);
/*      */       }
/*      */     }
/*      */     
/*      */     void addFieldByNumber(Descriptors.FieldDescriptor field) throws Descriptors.DescriptorValidationException {
/* 1824 */       DescriptorIntPair key = new DescriptorIntPair(field.getContainingType(), field.getNumber());
/* 1826 */       Descriptors.FieldDescriptor old = this.fieldsByNumber.put(key, field);
/* 1827 */       if (old != null) {
/* 1828 */         this.fieldsByNumber.put(key, old);
/* 1829 */         throw new Descriptors.DescriptorValidationException(field, "Field number " + field.getNumber() + "has already been used in \"" + field.getContainingType().getFullName() + "\" by field \"" + old.getName() + "\".");
/*      */       } 
/*      */     }
/*      */     
/*      */     void addEnumValueByNumber(Descriptors.EnumValueDescriptor value) {
/* 1843 */       DescriptorIntPair key = new DescriptorIntPair(value.getType(), value.getNumber());
/* 1845 */       Descriptors.EnumValueDescriptor old = this.enumValuesByNumber.put(key, value);
/* 1846 */       if (old != null)
/* 1847 */         this.enumValuesByNumber.put(key, old); 
/*      */     }
/*      */     
/*      */     static void validateSymbolName(Descriptors.GenericDescriptor descriptor) throws Descriptors.DescriptorValidationException {
/* 1859 */       String name = descriptor.getName();
/* 1860 */       if (name.length() == 0)
/* 1861 */         throw new Descriptors.DescriptorValidationException(descriptor, "Missing name."); 
/* 1863 */       boolean valid = true;
/* 1864 */       for (int i = 0; i < name.length(); i++) {
/* 1865 */         char c = name.charAt(i);
/* 1868 */         if (c >= '')
/* 1869 */           valid = false; 
/* 1873 */         if (!Character.isLetter(c) && c != '_' && (!Character.isDigit(c) || i <= 0))
/* 1877 */           valid = false; 
/*      */       } 
/* 1880 */       if (!valid)
/* 1881 */         throw new Descriptors.DescriptorValidationException(descriptor, '"' + name + "\" is not a valid identifier."); 
/*      */     }
/*      */   }
/*      */   
/*      */   private static interface GenericDescriptor {
/*      */     Message toProto();
/*      */     
/*      */     String getName();
/*      */     
/*      */     String getFullName();
/*      */     
/*      */     Descriptors.FileDescriptor getFile();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\Descriptors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */