package scala.concurrent;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001!:Q!\001\002\t\002\035\t1\002V1tWJ+hN\\3sg*\0211\001B\001\013G>t7-\036:sK:$(\"A\003\002\013M\034\027\r\\1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\tYA+Y:l%Vtg.\032:t'\tIA\002\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032DQ!E\005\005\002I\ta\001P5oSRtD#A\004\t\017QI!\031!C\002+\005aA\017\033:fC\022\024VO\0348feV\ta\003\005\002\t/%\021\001D\001\002\021\rV$XO]3UCN\\'+\0368oKJDaAG\005!\002\0231\022!\004;ie\026\fGMU;o]\026\024\b\005C\004\035\023\t\007I1A\013\002!QD'/Z1e!>|GNU;o]\026\024\bB\002\020\nA\003%a#A\tuQJ,\027\r\032)p_2\024VO\0348fe\002BC!\003\021$KA\021Q\"I\005\003E\021\021!\002Z3qe\026\034\027\r^3eC\005!\023aH+tK\002\002W\t_3dkRLwN\\\"p]R,\007\020\0361!S:\034H/Z1e]\005\na%\001\0043]E\002d\006\r\025\005\001\001\032S\005")
public final class TaskRunners {
  public static FutureTaskRunner threadPoolRunner() {
    return TaskRunners$.MODULE$.threadPoolRunner();
  }
  
  public static FutureTaskRunner threadRunner() {
    return TaskRunners$.MODULE$.threadRunner();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\TaskRunners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */