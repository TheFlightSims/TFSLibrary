/*    */ package scala.util;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import java.util.jar.Attributes;
/*    */ import scala.Option;
/*    */ 
/*    */ public final class Properties$ implements PropertiesTrait {
/*    */   public static final Properties$ MODULE$;
/*    */   
/*    */   private final Attributes.Name ScalaCompilerVersion;
/*    */   
/*    */   private final String propFilename;
/*    */   
/*    */   private final Properties scalaProps;
/*    */   
/*    */   private final Option<String> releaseVersion;
/*    */   
/*    */   private final Option<String> developmentVersion;
/*    */   
/*    */   private final String versionString;
/*    */   
/*    */   private final String copyrightString;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   public String propFilename() {
/* 16 */     return this.propFilename;
/*    */   }
/*    */   
/*    */   private Properties scalaProps$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (!this.bitmap$0) {
/* 16 */         this.scalaProps = PropertiesTrait$class.scalaProps(this);
/* 16 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/Properties$}} */
/* 16 */       return this.scalaProps;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Properties scalaProps() {
/* 16 */     return this.bitmap$0 ? this.scalaProps : scalaProps$lzycompute();
/*    */   }
/*    */   
/*    */   public Option<String> releaseVersion() {
/* 16 */     return this.releaseVersion;
/*    */   }
/*    */   
/*    */   public Option<String> developmentVersion() {
/* 16 */     return this.developmentVersion;
/*    */   }
/*    */   
/*    */   public String versionString() {
/* 16 */     return this.versionString;
/*    */   }
/*    */   
/*    */   public String copyrightString() {
/* 16 */     return this.copyrightString;
/*    */   }
/*    */   
/*    */   public void scala$util$PropertiesTrait$_setter_$propFilename_$eq(String x$1) {
/* 16 */     this.propFilename = x$1;
/*    */   }
/*    */   
/*    */   public void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(Option<String> x$1) {
/* 16 */     this.releaseVersion = x$1;
/*    */   }
/*    */   
/*    */   public void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(Option<String> x$1) {
/* 16 */     this.developmentVersion = x$1;
/*    */   }
/*    */   
/*    */   public void scala$util$PropertiesTrait$_setter_$versionString_$eq(String x$1) {
/* 16 */     this.versionString = x$1;
/*    */   }
/*    */   
/*    */   public void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(String x$1) {
/* 16 */     this.copyrightString = x$1;
/*    */   }
/*    */   
/*    */   public boolean propIsSet(String name) {
/* 16 */     return PropertiesTrait$class.propIsSet(this, name);
/*    */   }
/*    */   
/*    */   public boolean propIsSetTo(String name, String value) {
/* 16 */     return PropertiesTrait$class.propIsSetTo(this, name, value);
/*    */   }
/*    */   
/*    */   public String propOrElse(String name, String alt) {
/* 16 */     return PropertiesTrait$class.propOrElse(this, name, alt);
/*    */   }
/*    */   
/*    */   public String propOrEmpty(String name) {
/* 16 */     return PropertiesTrait$class.propOrEmpty(this, name);
/*    */   }
/*    */   
/*    */   public String propOrNull(String name) {
/* 16 */     return PropertiesTrait$class.propOrNull(this, name);
/*    */   }
/*    */   
/*    */   public Option<String> propOrNone(String name) {
/* 16 */     return PropertiesTrait$class.propOrNone(this, name);
/*    */   }
/*    */   
/*    */   public boolean propOrFalse(String name) {
/* 16 */     return PropertiesTrait$class.propOrFalse(this, name);
/*    */   }
/*    */   
/*    */   public String setProp(String name, String value) {
/* 16 */     return PropertiesTrait$class.setProp(this, name, value);
/*    */   }
/*    */   
/*    */   public String clearProp(String name) {
/* 16 */     return PropertiesTrait$class.clearProp(this, name);
/*    */   }
/*    */   
/*    */   public String envOrElse(String name, String alt) {
/* 16 */     return PropertiesTrait$class.envOrElse(this, name, alt);
/*    */   }
/*    */   
/*    */   public Option<String> envOrNone(String name) {
/* 16 */     return PropertiesTrait$class.envOrNone(this, name);
/*    */   }
/*    */   
/*    */   public String scalaPropOrElse(String name, String alt) {
/* 16 */     return PropertiesTrait$class.scalaPropOrElse(this, name, alt);
/*    */   }
/*    */   
/*    */   public String scalaPropOrEmpty(String name) {
/* 16 */     return PropertiesTrait$class.scalaPropOrEmpty(this, name);
/*    */   }
/*    */   
/*    */   public Option<String> scalaPropOrNone(String name) {
/* 16 */     return PropertiesTrait$class.scalaPropOrNone(this, name);
/*    */   }
/*    */   
/*    */   public String versionNumberString() {
/* 16 */     return PropertiesTrait$class.versionNumberString(this);
/*    */   }
/*    */   
/*    */   public String sourceEncoding() {
/* 16 */     return PropertiesTrait$class.sourceEncoding(this);
/*    */   }
/*    */   
/*    */   public String sourceReader() {
/* 16 */     return PropertiesTrait$class.sourceReader(this);
/*    */   }
/*    */   
/*    */   public String encodingString() {
/* 16 */     return PropertiesTrait$class.encodingString(this);
/*    */   }
/*    */   
/*    */   public String lineSeparator() {
/* 16 */     return PropertiesTrait$class.lineSeparator(this);
/*    */   }
/*    */   
/*    */   public String javaClassPath() {
/* 16 */     return PropertiesTrait$class.javaClassPath(this);
/*    */   }
/*    */   
/*    */   public String javaHome() {
/* 16 */     return PropertiesTrait$class.javaHome(this);
/*    */   }
/*    */   
/*    */   public String javaVendor() {
/* 16 */     return PropertiesTrait$class.javaVendor(this);
/*    */   }
/*    */   
/*    */   public String javaVersion() {
/* 16 */     return PropertiesTrait$class.javaVersion(this);
/*    */   }
/*    */   
/*    */   public String javaVmInfo() {
/* 16 */     return PropertiesTrait$class.javaVmInfo(this);
/*    */   }
/*    */   
/*    */   public String javaVmName() {
/* 16 */     return PropertiesTrait$class.javaVmName(this);
/*    */   }
/*    */   
/*    */   public String javaVmVendor() {
/* 16 */     return PropertiesTrait$class.javaVmVendor(this);
/*    */   }
/*    */   
/*    */   public String javaVmVersion() {
/* 16 */     return PropertiesTrait$class.javaVmVersion(this);
/*    */   }
/*    */   
/*    */   public String osName() {
/* 16 */     return PropertiesTrait$class.osName(this);
/*    */   }
/*    */   
/*    */   public String scalaHome() {
/* 16 */     return PropertiesTrait$class.scalaHome(this);
/*    */   }
/*    */   
/*    */   public String tmpDir() {
/* 16 */     return PropertiesTrait$class.tmpDir(this);
/*    */   }
/*    */   
/*    */   public String userDir() {
/* 16 */     return PropertiesTrait$class.userDir(this);
/*    */   }
/*    */   
/*    */   public String userHome() {
/* 16 */     return PropertiesTrait$class.userHome(this);
/*    */   }
/*    */   
/*    */   public String userName() {
/* 16 */     return PropertiesTrait$class.userName(this);
/*    */   }
/*    */   
/*    */   public boolean isWin() {
/* 16 */     return PropertiesTrait$class.isWin(this);
/*    */   }
/*    */   
/*    */   public boolean isMac() {
/* 16 */     return PropertiesTrait$class.isMac(this);
/*    */   }
/*    */   
/*    */   public String jdkHome() {
/* 16 */     return PropertiesTrait$class.jdkHome(this);
/*    */   }
/*    */   
/*    */   public String versionMsg() {
/* 16 */     return PropertiesTrait$class.versionMsg(this);
/*    */   }
/*    */   
/*    */   public String scalaCmd() {
/* 16 */     return PropertiesTrait$class.scalaCmd(this);
/*    */   }
/*    */   
/*    */   public String scalacCmd() {
/* 16 */     return PropertiesTrait$class.scalacCmd(this);
/*    */   }
/*    */   
/*    */   public boolean isJavaAtLeast(String version) {
/* 16 */     return PropertiesTrait$class.isJavaAtLeast(this, version);
/*    */   }
/*    */   
/*    */   public void main(String[] args) {
/* 16 */     PropertiesTrait$class.main(this, args);
/*    */   }
/*    */   
/*    */   private Properties$() {
/* 16 */     MODULE$ = this;
/* 16 */     PropertiesTrait$class.$init$(this);
/* 22 */     this.ScalaCompilerVersion = new Attributes.Name("Scala-Compiler-Version");
/*    */   }
/*    */   
/*    */   public String propCategory() {
/*    */     return "library";
/*    */   }
/*    */   
/*    */   public Class<Option<?>> pickJarBasedOn() {
/*    */     return (Class)Option.class;
/*    */   }
/*    */   
/*    */   public Attributes.Name ScalaCompilerVersion() {
/* 22 */     return this.ScalaCompilerVersion;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Properties$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */