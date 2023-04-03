package akka.actor;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0052q!\001\002\021\002G\005qAA\nFqR,gn]5p]&#\007K]8wS\022,'O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\r\003\001\022A\0027p_.,\b\017F\001\022a\t\021\002\004E\002\024)Yi\021AA\005\003+\t\0211\"\022=uK:\034\030n\0348JIB\021q\003\007\007\001\t%Ib\"!A\001\002\013\005!DA\002`IE\n\"a\007\020\021\005%a\022BA\017\013\005\035qu\016\0365j]\036\004\"aE\020\n\005\001\022!!C#yi\026t7/[8o\001")
public interface ExtensionIdProvider {
  ExtensionId<? extends Extension> lookup();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ExtensionIdProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */