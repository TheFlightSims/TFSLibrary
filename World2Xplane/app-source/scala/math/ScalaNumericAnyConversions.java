package scala.math;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0354q!\001\002\021\002\007\005qA\001\016TG\006d\027MT;nKJL7-\0218z\007>tg/\032:tS>t7O\003\002\004\t\005!Q.\031;i\025\005)\021!B:dC2\f7\001A\n\003\001!\001\"!\003\006\016\003\021I!a\003\003\003\007\005s\027\020C\003\016\001\021\005a\"\001\004%S:LG\017\n\013\002\037A\021\021\002E\005\003#\021\021A!\0268ji\")1\003\001D\001)\0059\021n],i_2,G#A\013\021\005%1\022BA\f\005\005\035\021un\0347fC:DQ!\007\001\007\002i\t!\"\0368eKJd\0270\0338h)\005A\001\"\002\017\001\r\003i\022!\0032zi\0264\026\r\\;f)\005q\002CA\005 \023\t\001CA\001\003CsR,\007\"\002\022\001\r\003\031\023AC:i_J$h+\0317vKR\tA\005\005\002\nK%\021a\005\002\002\006'\"|'\017\036\005\006Q\0011\t!K\001\tS:$h+\0317vKR\t!\006\005\002\nW%\021A\006\002\002\004\023:$\b\"\002\030\001\r\003y\023!\0037p]\0364\026\r\\;f)\005\001\004CA\0052\023\t\021DA\001\003M_:<\007\"\002\033\001\r\003)\024A\0034m_\006$h+\0317vKR\ta\007\005\002\no%\021\001\b\002\002\006\r2|\027\r\036\005\006u\0011\taO\001\fI>,(\r\\3WC2,X\rF\001=!\tIQ(\003\002?\t\t1Ai\\;cY\026DQ\001\021\001\005\002\005\013a\001^8DQ\006\024X#\001\"\021\005%\031\025B\001#\005\005\021\031\005.\031:\t\013\031\003A\021A$\002\rQ|')\037;f+\005q\002\"B%\001\t\003Q\025a\002;p'\"|'\017^\013\002I!)A\n\001C\001\033\006)Ao\\%oiV\t!\006C\003P\001\021\005\001+\001\004u_2{gnZ\013\002a!)!\013\001C\001'\0069Ao\034$m_\006$X#\001\034\t\013U\003A\021\001,\002\021Q|Gi\\;cY\026,\022\001\020\005\0061\002!\t!W\001\fSN4\026\r\\5e\005f$X-F\001\026\021\025Y\006\001\"\001Z\0031I7OV1mS\022\034\006n\034:u\021\025i\006\001\"\001Z\003)I7OV1mS\022Le\016\036\005\006?\002!\t!W\001\fSN4\026\r\\5e\007\"\f'\017C\003b\001\021E\021&\001\rv]&4\027.\0323Qe&l\027\016^5wK\"\0137\017[2pI\026DQa\031\001\005\022\021\fa#\0368jM&,G\r\025:j[&$\030N^3FcV\fGn\035\013\003+\025DQA\0322A\002!\t\021\001\037")
public interface ScalaNumericAnyConversions {
  boolean isWhole();
  
  Object underlying();
  
  byte byteValue();
  
  short shortValue();
  
  int intValue();
  
  long longValue();
  
  float floatValue();
  
  double doubleValue();
  
  char toChar();
  
  byte toByte();
  
  short toShort();
  
  int toInt();
  
  long toLong();
  
  float toFloat();
  
  double toDouble();
  
  boolean isValidByte();
  
  boolean isValidShort();
  
  boolean isValidInt();
  
  boolean isValidChar();
  
  int unifiedPrimitiveHashcode();
  
  boolean unifiedPrimitiveEquals(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\ScalaNumericAnyConversions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */