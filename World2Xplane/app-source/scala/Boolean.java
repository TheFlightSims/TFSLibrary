package scala;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001u3Q!\001\002\002\006\025\021qAQ8pY\026\fgNC\001\004\003\025\0318-\0317b\007\001\031\"\001\001\004\021\005\035AQ\"\001\002\n\005%\021!AB!osZ\013G\016C\003\f\001\021\005A\"\001\004=S:LGO\020\013\002\033A\021q\001\001\005\006\037\0011\t\001E\001\fk:\f'/_0%E\006tw-F\001\016\021\025\021\002A\"\001\024\003\031!S-\035\023fcR\021Q\002\006\005\006+E\001\r!D\001\002q\")q\003\001D\0011\005AAEY1oO\022*\027\017\006\002\0163!)QC\006a\001\033!)1\004\001D\0019\005AAEY1sI\t\f'\017\006\002\016;!)QC\007a\001\033!)q\004\001D\001A\005AA%Y7qI\005l\007\017\006\002\016C!)QC\ba\001\033!)1\005\001D\001I\005!AEY1s)\tiQ\005C\003\026E\001\007Q\002C\003(\001\031\005\001&\001\003%C6\004HCA\007*\021\025)b\0051\001\016\021\025Y\003A\"\001-\003\r!S\017\035\013\003\0335BQ!\006\026A\0025AQa\f\001\005BA\n\001bZ3u\0072\f7o\035\013\002cA\031!'N\007\017\005\035\031\024B\001\033\003\003\031\001&/\0323fM&\021ag\016\002\006\0072\f7o\035\006\003i\t9Q!\017\002\t\002i\nqAQ8pY\026\fg\016\005\002\bw\031)\021A\001E\001yM\0311(\020!\021\005\035q\024BA \003\005\031\te.\037*fMB\021q!Q\005\003\005\n\021q\"\0218z-\006d7i\\7qC:LwN\034\005\006\027m\"\t\001\022\013\002u!)ai\017C\001\017\006\031!m\034=\025\005!{\005CA%O\033\005Q%BA&M\003\021a\027M\\4\013\0035\013AA[1wC&\021\021A\023\005\006+\025\003\r!\004\005\006#n\"\tAU\001\006k:\024w\016\037\013\003\033MCQ!\006)A\002Q\003\"!S+\n\005YS%AB(cU\026\034G\017C\003Yw\021\005\023,\001\005u_N#(/\0338h)\005Q\006CA%\\\023\ta&J\001\004TiJLgn\032")
public abstract class Boolean {
  public static String toString() {
    return Boolean$.MODULE$.toString();
  }
  
  public static boolean unbox(Object paramObject) {
    return Boolean$.MODULE$.unbox(paramObject);
  }
  
  public static java.lang.Boolean box(boolean paramBoolean) {
    return Boolean$.MODULE$.box(paramBoolean);
  }
  
  public abstract boolean unary_$bang();
  
  public abstract boolean $eq$eq(boolean paramBoolean);
  
  public abstract boolean $bang$eq(boolean paramBoolean);
  
  public abstract boolean $bar$bar(boolean paramBoolean);
  
  public abstract boolean $amp$amp(boolean paramBoolean);
  
  public abstract boolean $bar(boolean paramBoolean);
  
  public abstract boolean $amp(boolean paramBoolean);
  
  public abstract boolean $up(boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Boolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */