package scala.util;

import java.util.jar.Attributes;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\0019;Q!\001\002\t\002\035\t!\002\025:pa\026\024H/[3t\025\t\031A!\001\003vi&d'\"A\003\002\013M\034\027\r\\1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\tQ\001K]8qKJ$\030.Z:\024\007%a\001\003\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032\004\"\001C\t\n\005I\021!a\004)s_B,'\017^5fgR\023\030-\033;\t\013QIA\021A\013\002\rqJg.\033;?)\0059\001\"B\f\n\t#A\022\001\0049s_B\034\025\r^3h_JLX#A\r\021\005iyR\"A\016\013\005qi\022\001\0027b]\036T\021AH\001\005U\0064\030-\003\002!7\t11\013\036:j]\036DQAI\005\005\022\r\na\002]5dW*\013'OQ1tK\022|e.F\001%!\rQReJ\005\003Mm\021Qa\0217bgN\004$\001K\027\021\0075I3&\003\002+\t\t1q\n\035;j_:\004\"\001L\027\r\001\021Ia&IA\001\002\003\025\ta\f\002\004?\022\n\024C\001\0314!\ti\021'\003\0023\t\t9aj\034;iS:<\007CA\0075\023\t)DAA\002B]fDqaN\005C\002\023\005\001(\001\013TG\006d\027mQ8na&dWM\035,feNLwN\\\013\002sA\021!(\023\b\003w\031s!\001P\"\017\005u\022eB\001 B\033\005y$B\001!\007\003\031a$o\\8u}%\ta$\003\002\004;%\021A)R\001\004U\006\024(BA\002\036\023\t9\005*\001\006BiR\024\030NY;uKNT!\001R#\n\005)[%\001\002(b[\026T!a\022%\t\r5K\001\025!\003:\003U\0316-\0317b\007>l\007/\0337feZ+'o]5p]\002\002")
public final class Properties {
  public static void main(String[] paramArrayOfString) {
    Properties$.MODULE$.main(paramArrayOfString);
  }
  
  public static boolean isJavaAtLeast(String paramString) {
    return Properties$.MODULE$.isJavaAtLeast(paramString);
  }
  
  public static String scalacCmd() {
    return Properties$.MODULE$.scalacCmd();
  }
  
  public static String scalaCmd() {
    return Properties$.MODULE$.scalaCmd();
  }
  
  public static String versionMsg() {
    return Properties$.MODULE$.versionMsg();
  }
  
  public static String jdkHome() {
    return Properties$.MODULE$.jdkHome();
  }
  
  public static boolean isMac() {
    return Properties$.MODULE$.isMac();
  }
  
  public static boolean isWin() {
    return Properties$.MODULE$.isWin();
  }
  
  public static String userName() {
    return Properties$.MODULE$.userName();
  }
  
  public static String userHome() {
    return Properties$.MODULE$.userHome();
  }
  
  public static String userDir() {
    return Properties$.MODULE$.userDir();
  }
  
  public static String tmpDir() {
    return Properties$.MODULE$.tmpDir();
  }
  
  public static String scalaHome() {
    return Properties$.MODULE$.scalaHome();
  }
  
  public static String osName() {
    return Properties$.MODULE$.osName();
  }
  
  public static String javaVmVersion() {
    return Properties$.MODULE$.javaVmVersion();
  }
  
  public static String javaVmVendor() {
    return Properties$.MODULE$.javaVmVendor();
  }
  
  public static String javaVmName() {
    return Properties$.MODULE$.javaVmName();
  }
  
  public static String javaVmInfo() {
    return Properties$.MODULE$.javaVmInfo();
  }
  
  public static String javaVersion() {
    return Properties$.MODULE$.javaVersion();
  }
  
  public static String javaVendor() {
    return Properties$.MODULE$.javaVendor();
  }
  
  public static String javaHome() {
    return Properties$.MODULE$.javaHome();
  }
  
  public static String javaClassPath() {
    return Properties$.MODULE$.javaClassPath();
  }
  
  public static String lineSeparator() {
    return Properties$.MODULE$.lineSeparator();
  }
  
  public static String encodingString() {
    return Properties$.MODULE$.encodingString();
  }
  
  public static String sourceReader() {
    return Properties$.MODULE$.sourceReader();
  }
  
  public static String sourceEncoding() {
    return Properties$.MODULE$.sourceEncoding();
  }
  
  public static String versionNumberString() {
    return Properties$.MODULE$.versionNumberString();
  }
  
  public static Option<String> scalaPropOrNone(String paramString) {
    return Properties$.MODULE$.scalaPropOrNone(paramString);
  }
  
  public static String scalaPropOrEmpty(String paramString) {
    return Properties$.MODULE$.scalaPropOrEmpty(paramString);
  }
  
  public static String scalaPropOrElse(String paramString1, String paramString2) {
    return Properties$.MODULE$.scalaPropOrElse(paramString1, paramString2);
  }
  
  public static Option<String> envOrNone(String paramString) {
    return Properties$.MODULE$.envOrNone(paramString);
  }
  
  public static String envOrElse(String paramString1, String paramString2) {
    return Properties$.MODULE$.envOrElse(paramString1, paramString2);
  }
  
  public static String clearProp(String paramString) {
    return Properties$.MODULE$.clearProp(paramString);
  }
  
  public static String setProp(String paramString1, String paramString2) {
    return Properties$.MODULE$.setProp(paramString1, paramString2);
  }
  
  public static boolean propOrFalse(String paramString) {
    return Properties$.MODULE$.propOrFalse(paramString);
  }
  
  public static Option<String> propOrNone(String paramString) {
    return Properties$.MODULE$.propOrNone(paramString);
  }
  
  public static String propOrNull(String paramString) {
    return Properties$.MODULE$.propOrNull(paramString);
  }
  
  public static String propOrEmpty(String paramString) {
    return Properties$.MODULE$.propOrEmpty(paramString);
  }
  
  public static String propOrElse(String paramString1, String paramString2) {
    return Properties$.MODULE$.propOrElse(paramString1, paramString2);
  }
  
  public static boolean propIsSetTo(String paramString1, String paramString2) {
    return Properties$.MODULE$.propIsSetTo(paramString1, paramString2);
  }
  
  public static boolean propIsSet(String paramString) {
    return Properties$.MODULE$.propIsSet(paramString);
  }
  
  public static void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(String paramString) {
    Properties$.MODULE$.scala$util$PropertiesTrait$_setter_$copyrightString_$eq(paramString);
  }
  
  public static void scala$util$PropertiesTrait$_setter_$versionString_$eq(String paramString) {
    Properties$.MODULE$.scala$util$PropertiesTrait$_setter_$versionString_$eq(paramString);
  }
  
  public static void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(Option paramOption) {
    Properties$.MODULE$.scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(paramOption);
  }
  
  public static void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(Option paramOption) {
    Properties$.MODULE$.scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(paramOption);
  }
  
  public static void scala$util$PropertiesTrait$_setter_$propFilename_$eq(String paramString) {
    Properties$.MODULE$.scala$util$PropertiesTrait$_setter_$propFilename_$eq(paramString);
  }
  
  public static String copyrightString() {
    return Properties$.MODULE$.copyrightString();
  }
  
  public static String versionString() {
    return Properties$.MODULE$.versionString();
  }
  
  public static Option<String> developmentVersion() {
    return Properties$.MODULE$.developmentVersion();
  }
  
  public static Option<String> releaseVersion() {
    return Properties$.MODULE$.releaseVersion();
  }
  
  public static java.util.Properties scalaProps() {
    return Properties$.MODULE$.scalaProps();
  }
  
  public static String propFilename() {
    return Properties$.MODULE$.propFilename();
  }
  
  public static Attributes.Name ScalaCompilerVersion() {
    return Properties$.MODULE$.ScalaCompilerVersion();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Properties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */