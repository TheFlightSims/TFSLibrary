package scala.compat;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005\rq!B\001\003\021\0039\021\001\003)mCR4wN]7\013\005\r!\021AB2p[B\fGOC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021\001\002\0257bi\032|'/\\\n\003\0231\001\"!\004\b\016\003\021I!a\004\003\003\r\005s\027PU3g\021\025\t\022\002\"\001\023\003\031a\024N\\5u}Q\tq!\002\003\025\023\001)\"AE*uC\016\\wJ^3sM2|w/\022:s_J\004\"AF\016\016\003]Q!\001G\r\002\t1\fgn\032\006\0025\005!!.\031<b\023\t!r#\002\003\036\023\001q\"aH\"p]\016,(O]3oi6{G-\0334jG\006$\030n\0348Fq\016,\007\017^5p]B\021qDI\007\002A)\021\021%G\001\005kRLG.\003\002\036A!)A%\003C\001K\005I\021M\035:bs\016|\007/\037\013\007M%Z\003G\r\033\021\00559\023B\001\025\005\005\021)f.\033;\t\013)\032\003\031\001\007\002\007M\0248\rC\003-G\001\007Q&\001\004te\016\004vn\035\t\003\0339J!a\f\003\003\007%sG\017C\0032G\001\007A\"\001\003eKN$\b\"B\032$\001\004i\023a\0023fgR\004vn\035\005\006k\r\002\r!L\001\007Y\026tw\r\0365)\005\r:\004CA\0079\023\tIDA\001\004j]2Lg.\032\005\006w%!\t\001P\001\fGJ,\027\r^3BeJ\f\027\020F\002\r{ICQA\020\036A\002}\n\021\"\0327f[\016c\027m]:1\005\001K\005cA!E\017:\021QBQ\005\003\007\022\ta\001\025:fI\0264\027BA#G\005\025\031E.Y:t\025\t\031E\001\005\002I\0232\001A!\003&>\003\003\005\tQ!\001L\005\ryF%M\t\003\031>\003\"!D'\n\0059#!a\002(pi\"Lgn\032\t\003\033AK!!\025\003\003\007\005s\027\020C\0036u\001\007Q\006\013\002;o!)Q+\003C\001-\006Q\021M\035:bs\016dW-\031:\025\005\031:\006\"\002-U\001\004I\026aA1seB\031QBW\027\n\005m#!!B!se\006L\bF\001+8\021\025q\026\002\"\001`\003=9W\r^\"mCN\034hi\034:OC6,GC\0011fa\t\t7\rE\002B\t\n\004\"\001S2\005\023\021l\026\021!A\001\006\003Y%aA0%e!)a-\030a\001O\006!a.Y7f!\t\t\005.\003\002j\r\n11\013\036:j]\036D#!X\034\t\0171L!\031!C\001[\006\031Qi\024'\026\0039\004\"AF8\n\005%<\002BB9\nA\003%a.\001\003F\0372\003\003\"B:\n\t\003!\030aC2veJ,g\016\036+j[\026,\022!\036\t\003\033YL!a\036\003\003\t1{gn\032\025\003e^BQA_\005\005\002m\fabY8mY\026\034GoR1sE\006<W\rF\001'Q\tIx\007C\003\023\021\005q0\001\neK\032\fW\017\034;DQ\006\0248/\032;OC6,W#A4)\005u<\004")
public final class Platform {
  public static String defaultCharsetName() {
    return Platform$.MODULE$.defaultCharsetName();
  }
  
  public static void collectGarbage() {
    Platform$.MODULE$.collectGarbage();
  }
  
  public static long currentTime() {
    return Platform$.MODULE$.currentTime();
  }
  
  public static String EOL() {
    return Platform$.MODULE$.EOL();
  }
  
  public static Class<?> getClassForName(String paramString) {
    return Platform$.MODULE$.getClassForName(paramString);
  }
  
  public static void arrayclear(int[] paramArrayOfint) {
    Platform$.MODULE$.arrayclear(paramArrayOfint);
  }
  
  public static Object createArray(Class<?> paramClass, int paramInt) {
    return Platform$.MODULE$.createArray(paramClass, paramInt);
  }
  
  public static void arraycopy(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2, int paramInt3) {
    Platform$.MODULE$.arraycopy(paramObject1, paramInt1, paramObject2, paramInt2, paramInt3);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\compat\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */