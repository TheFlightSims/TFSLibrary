/*     */ package scala.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Properties;
/*     */ import scala.Console$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ 
/*     */ public abstract class PropertiesTrait$class {
/*     */   public static void $init$(PropertiesTrait $this) {
/*  30 */     $this.scala$util$PropertiesTrait$_setter_$propFilename_$eq((new StringBuilder()).append("/").append($this.propCategory()).append(".properties").toString());
/*  77 */     PropertiesTrait.$anonfun$1 $anonfun$1 = new PropertiesTrait.$anonfun$1($this);
/*  77 */     Option<String> option1 = $this.scalaPropOrNone("maven.version.number");
/*  77 */     $this.scala$util$PropertiesTrait$_setter_$releaseVersion_$eq((new Option.WithFilter(option1, (Function1)$anonfun$1)).map((Function1)new PropertiesTrait.$anonfun$2($this)));
/*  91 */     PropertiesTrait.$anonfun$3 $anonfun$3 = new PropertiesTrait.$anonfun$3($this);
/*  91 */     Option<String> option2 = $this.scalaPropOrNone("maven.version.number");
/*  91 */     $this.scala$util$PropertiesTrait$_setter_$developmentVersion_$eq((new Option.WithFilter(option2, (Function1)$anonfun$3)).flatMap((Function1)new PropertiesTrait.$anonfun$4($this)));
/* 104 */     $this.scala$util$PropertiesTrait$_setter_$versionString_$eq((new StringBuilder()).append("version ").append($this.scalaPropOrElse("version.number", "(unknown)")).toString());
/* 105 */     $this.scala$util$PropertiesTrait$_setter_$copyrightString_$eq($this.scalaPropOrElse("copyright.string", "Copyright 2002-2013, LAMP/EPFL"));
/*     */   }
/*     */   
/*     */   public static Properties scalaProps(PropertiesTrait $this) {
/*     */     Properties props = new Properties();
/*     */     InputStream stream = $this.pickJarBasedOn().getResourceAsStream($this.propFilename());
/*     */     if (stream != null)
/*     */       quietlyDispose($this, (Function0)new PropertiesTrait$$anonfun$scalaProps$1($this, props, stream), (Function0)new PropertiesTrait$$anonfun$scalaProps$2($this, stream)); 
/*     */     return props;
/*     */   }
/*     */   
/*     */   private static void quietlyDispose(PropertiesTrait $this, Function0 action, Function0 disposal) {
/*     */     try {
/*     */       action.apply$mcV$sp();
/*     */     } finally {
/*     */       try {
/*     */         disposal.apply$mcV$sp();
/*     */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean propIsSet(PropertiesTrait $this, String name) {
/*     */     return !(System.getProperty(name) == null);
/*     */   }
/*     */   
/*     */   public static boolean propIsSetTo(PropertiesTrait $this, String name, String value) {
/*     */     if ($this.propOrNull(name) == null) {
/*     */       $this.propOrNull(name);
/*     */       if (value != null);
/*     */     } else if ($this.propOrNull(name).equals(value)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String propOrElse(PropertiesTrait $this, String name, String alt) {
/*     */     return System.getProperty(name, alt);
/*     */   }
/*     */   
/*     */   public static String propOrEmpty(PropertiesTrait $this, String name) {
/*     */     return $this.propOrElse(name, "");
/*     */   }
/*     */   
/*     */   public static String propOrNull(PropertiesTrait $this, String name) {
/*     */     return $this.propOrElse(name, null);
/*     */   }
/*     */   
/*     */   public static Option propOrNone(PropertiesTrait $this, String name) {
/*     */     return Option$.MODULE$.apply($this.propOrNull(name));
/*     */   }
/*     */   
/*     */   public static boolean propOrFalse(PropertiesTrait $this, String name) {
/*     */     Option<String> option;
/*     */     if (!(option = $this.propOrNone(name)).isEmpty()) {
/*     */       String str = (String)option.get();
/*     */       (new String[3])[0] = "yes";
/*     */       (new String[3])[1] = "on";
/*     */       (new String[3])[2] = "true";
/*     */       WrappedArray wrappedArray = Predef$.MODULE$.wrapRefArray((Object[])new String[3]);
/*     */       List$ list$ = List$.MODULE$;
/*     */       if (wrappedArray.toList().contains(str.toLowerCase()));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static String setProp(PropertiesTrait $this, String name, String value) {
/*     */     return System.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public static String clearProp(PropertiesTrait $this, String name) {
/*     */     return System.clearProperty(name);
/*     */   }
/*     */   
/*     */   public static String envOrElse(PropertiesTrait $this, String name, String alt) {
/*     */     Option option;
/*     */     return (option = Option$.MODULE$.apply(System.getenv(name))).isEmpty() ? alt : (String)option.get();
/*     */   }
/*     */   
/*     */   public static Option envOrNone(PropertiesTrait $this, String name) {
/*     */     return Option$.MODULE$.apply(System.getenv(name));
/*     */   }
/*     */   
/*     */   public static String scalaPropOrElse(PropertiesTrait $this, String name, String alt) {
/*     */     return $this.scalaProps().getProperty(name, alt);
/*     */   }
/*     */   
/*     */   public static String scalaPropOrEmpty(PropertiesTrait $this, String name) {
/*     */     return $this.scalaPropOrElse(name, "");
/*     */   }
/*     */   
/*     */   public static Option scalaPropOrNone(PropertiesTrait $this, String name) {
/*     */     return Option$.MODULE$.apply($this.scalaProps().getProperty(name));
/*     */   }
/*     */   
/*     */   public static String versionNumberString(PropertiesTrait $this) {
/*     */     return $this.scalaPropOrEmpty("version.number");
/*     */   }
/*     */   
/*     */   public static String sourceEncoding(PropertiesTrait $this) {
/* 110 */     return $this.scalaPropOrElse("file.encoding", "UTF-8");
/*     */   }
/*     */   
/*     */   public static String sourceReader(PropertiesTrait $this) {
/* 111 */     return $this.scalaPropOrElse("source.reader", "scala.tools.nsc.io.SourceReader");
/*     */   }
/*     */   
/*     */   public static String encodingString(PropertiesTrait $this) {
/* 116 */     return $this.propOrElse("file.encoding", "UTF-8");
/*     */   }
/*     */   
/*     */   public static String lineSeparator(PropertiesTrait $this) {
/* 120 */     return $this.propOrElse("line.separator", "\n");
/*     */   }
/*     */   
/*     */   public static String javaClassPath(PropertiesTrait $this) {
/* 123 */     return $this.propOrEmpty("java.class.path");
/*     */   }
/*     */   
/*     */   public static String javaHome(PropertiesTrait $this) {
/* 124 */     return $this.propOrEmpty("java.home");
/*     */   }
/*     */   
/*     */   public static String javaVendor(PropertiesTrait $this) {
/* 125 */     return $this.propOrEmpty("java.vendor");
/*     */   }
/*     */   
/*     */   public static String javaVersion(PropertiesTrait $this) {
/* 126 */     return $this.propOrEmpty("java.version");
/*     */   }
/*     */   
/*     */   public static String javaVmInfo(PropertiesTrait $this) {
/* 127 */     return $this.propOrEmpty("java.vm.info");
/*     */   }
/*     */   
/*     */   public static String javaVmName(PropertiesTrait $this) {
/* 128 */     return $this.propOrEmpty("java.vm.name");
/*     */   }
/*     */   
/*     */   public static String javaVmVendor(PropertiesTrait $this) {
/* 129 */     return $this.propOrEmpty("java.vm.vendor");
/*     */   }
/*     */   
/*     */   public static String javaVmVersion(PropertiesTrait $this) {
/* 130 */     return $this.propOrEmpty("java.vm.version");
/*     */   }
/*     */   
/*     */   private static String javaSpecVersion(PropertiesTrait $this) {
/* 132 */     return $this.propOrEmpty("java.specification.version");
/*     */   }
/*     */   
/*     */   public static String osName(PropertiesTrait $this) {
/* 135 */     return $this.propOrEmpty("os.name");
/*     */   }
/*     */   
/*     */   public static String scalaHome(PropertiesTrait $this) {
/* 136 */     return $this.propOrEmpty("scala.home");
/*     */   }
/*     */   
/*     */   public static String tmpDir(PropertiesTrait $this) {
/* 137 */     return $this.propOrEmpty("java.io.tmpdir");
/*     */   }
/*     */   
/*     */   public static String userDir(PropertiesTrait $this) {
/* 138 */     return $this.propOrEmpty("user.dir");
/*     */   }
/*     */   
/*     */   public static String userHome(PropertiesTrait $this) {
/* 139 */     return $this.propOrEmpty("user.home");
/*     */   }
/*     */   
/*     */   public static String userName(PropertiesTrait $this) {
/* 140 */     return $this.propOrEmpty("user.name");
/*     */   }
/*     */   
/*     */   public static boolean isWin(PropertiesTrait $this) {
/* 144 */     return $this.osName().startsWith("Windows");
/*     */   }
/*     */   
/*     */   public static boolean isMac(PropertiesTrait $this) {
/* 148 */     return $this.osName().startsWith("Mac OS X");
/*     */   }
/*     */   
/*     */   public static String jdkHome(PropertiesTrait $this) {
/* 153 */     return $this.envOrElse("JDK_HOME", $this.envOrElse("JAVA_HOME", $this.javaHome()));
/*     */   }
/*     */   
/*     */   public static String versionMsg(PropertiesTrait $this) {
/* 155 */     Predef$ predef$ = Predef$.MODULE$;
/* 155 */     return (new StringOps("Scala %s %s -- %s")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { $this.propCategory(), $this.versionString(), $this.copyrightString() }));
/*     */   }
/*     */   
/*     */   public static String scalaCmd(PropertiesTrait $this) {
/* 156 */     return $this.isWin() ? "scala.bat" : "scala";
/*     */   }
/*     */   
/*     */   public static String scalacCmd(PropertiesTrait $this) {
/* 157 */     return $this.isWin() ? "scalac.bat" : "scalac";
/*     */   }
/*     */   
/*     */   private static final Tuple2 parts$1(PropertiesTrait $this, String x) {
/* 175 */     int i = x.indexOf('.');
/* 176 */     if (i < 0)
/* 176 */       throw new NumberFormatException((new StringBuilder()).append("Not a version: ").append(x).toString()); 
/* 177 */     return new Tuple2(x.substring(0, i), x.substring(i + 1, x.length()));
/*     */   }
/*     */   
/*     */   public static boolean isJavaAtLeast(PropertiesTrait $this, String version) {
/* 179 */     Tuple2 tuple2 = parts$1($this, version);
/* 179 */     if (tuple2 != null) {
/* 179 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 179 */       String v = (String)tuple21._1(), _v = (String)tuple21._2();
/* 180 */       Tuple2 tuple22 = parts$1($this, javaSpecVersion($this));
/* 180 */       if (tuple22 != null) {
/* 180 */         Tuple2 tuple23 = new Tuple2(tuple22._1(), tuple22._2());
/* 180 */         String s = (String)tuple23._1(), _s = (String)tuple23._2();
/* 181 */         Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$, predef$3 = Predef$.MODULE$, predef$4 = Predef$.MODULE$;
/* 181 */         return ((new StringOps(s)).toInt() >= (new StringOps(v)).toInt() && (new StringOps(_s)).toInt() >= (new StringOps(_v)).toInt());
/*     */       } 
/*     */       throw new MatchError(tuple22);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static void main(PropertiesTrait $this, String[] args) {
/* 186 */     PrintWriter writer = new PrintWriter(Console$.MODULE$.err(), true);
/* 187 */     writer.println($this.versionMsg());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\PropertiesTrait$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */