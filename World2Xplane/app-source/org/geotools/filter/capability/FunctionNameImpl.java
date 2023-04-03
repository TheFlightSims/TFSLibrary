/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.data.Parameter;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.capability.Operator;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FunctionNameImpl extends OperatorImpl implements FunctionName {
/*     */   Name functionName;
/*     */   
/*     */   List<Parameter<?>> args;
/*     */   
/*     */   Parameter<?> ret;
/*     */   
/*     */   public FunctionNameImpl(String name, int argumentCount) {
/*  50 */     this((Name)new NameImpl(name), argumentCount);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, int argumentCount) {
/*  54 */     this(name, generateReturn(), generateArguments(argumentCount));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, String... argumentsNames) {
/*  58 */     this((Name)new NameImpl(name), argumentsNames);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, String... argumentsNames) {
/*  62 */     this(name, argumentsNames.length, Arrays.asList(argumentsNames));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, List<String> argumentsNames) {
/*  66 */     this((Name)new NameImpl(name), argumentsNames);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, List<String> argumentsNames) {
/*  70 */     this(name, argumentsNames.size(), argumentsNames);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, int argumentCount, List<String> argumentsNames) {
/*  74 */     this((Name)new NameImpl(name), argumentCount, argumentsNames);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, int argumentCount, List<String> argumentsNames) {
/*  78 */     this(name, generateReturn(), generateArguments(argumentsNames));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, int argumentCount, String... argumentsNames) {
/*  82 */     this((Name)new NameImpl(name), argumentCount, argumentsNames);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, int argumentCount, String... argumentsNames) {
/*  86 */     this(name, argumentCount, Arrays.asList(argumentsNames));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(FunctionName copy) {
/*  90 */     super((Operator)copy);
/*  91 */     this.functionName = copy.getFunctionName();
/*  92 */     this.ret = copy.getReturn();
/*  93 */     this.args = copy.getArguments();
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, Class returnType, Parameter<?>... arguments) {
/*  97 */     this((Name)new NameImpl(name), returnType, arguments);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, Class returnType, Parameter<?>... arguments) {
/* 101 */     this(name, parameter(name.getLocalPart(), returnType), Arrays.asList(arguments));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, Parameter<?> retern, Parameter<?>... arguments) {
/* 105 */     this((Name)new NameImpl(name), retern, arguments);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, Parameter<?> retern, Parameter<?>... arguments) {
/* 109 */     this(name, retern, Arrays.asList(arguments));
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(String name, Parameter<?> retern, List<Parameter<?>> arguments) {
/* 113 */     this((Name)new NameImpl(name), retern, arguments);
/*     */   }
/*     */   
/*     */   public FunctionNameImpl(Name name, Parameter<?> retern, List<Parameter<?>> arguments) {
/* 117 */     super(name.getLocalPart());
/* 118 */     this.functionName = name;
/* 119 */     this.ret = retern;
/* 120 */     this.args = arguments;
/*     */   }
/*     */   
/*     */   public Name getFunctionName() {
/* 125 */     return this.functionName;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 131 */     return getFunctionName().getLocalPart();
/*     */   }
/*     */   
/*     */   public int getArgumentCount() {
/* 135 */     return this.args.size();
/*     */   }
/*     */   
/*     */   public List<Parameter<?>> getArguments() {
/* 139 */     return this.args;
/*     */   }
/*     */   
/*     */   public Parameter<?> getReturn() {
/* 143 */     return this.ret;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 148 */     int prime = 31;
/* 149 */     int result = super.hashCode();
/* 150 */     if (this.args != null)
/* 151 */       result = 31 * result + this.args.hashCode(); 
/* 153 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 158 */     if (this == obj)
/* 159 */       return true; 
/* 160 */     if (!super.equals(obj))
/* 161 */       return false; 
/* 162 */     if (getClass() != obj.getClass())
/* 163 */       return false; 
/* 164 */     FunctionNameImpl other = (FunctionNameImpl)obj;
/* 165 */     if (this.args == null)
/* 166 */       return (other.args == null); 
/* 168 */     return this.args.equals(other.args);
/*     */   }
/*     */   
/*     */   public List<String> getArgumentNames() {
/* 177 */     List<String> names = new ArrayList<String>();
/* 178 */     for (Parameter<?> arg : this.args)
/* 179 */       names.add(arg.getName()); 
/* 181 */     return names;
/*     */   }
/*     */   
/*     */   private static Parameter<?> generateReturn() {
/* 186 */     return parameter("return", Object.class);
/*     */   }
/*     */   
/*     */   private static List<Parameter<?>> generateArguments(int count) {
/* 200 */     List<Parameter<?>> args = new ArrayList<Parameter<?>>();
/* 201 */     if (count < 0) {
/* 204 */       args.add(parameter("arg", Object.class, Math.abs(count), 2147483647));
/*     */     } else {
/* 207 */       for (int i = 0; i < count; i++)
/* 208 */         args.add(parameter("arg" + i, Object.class, 1, 1)); 
/*     */     } 
/* 212 */     return args;
/*     */   }
/*     */   
/*     */   private static List<Parameter<?>> generateArguments(List<String> names) {
/* 216 */     List<Parameter<?>> args = new ArrayList<Parameter<?>>();
/* 218 */     for (String name : names)
/* 219 */       args.add(parameter(name, Object.class, 1, 1)); 
/* 222 */     return args;
/*     */   }
/*     */   
/*     */   public static Parameter<?> parameter(String name, Class type) {
/* 226 */     return parameter(name, type, 1, 1);
/*     */   }
/*     */   
/*     */   public static Parameter<?> parameter(String name, Class type, int min, int max) {
/* 230 */     return (Parameter<?>)new Parameter(name, type, min, max);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\FunctionNameImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */