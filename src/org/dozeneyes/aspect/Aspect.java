package org.dozeneyes.aspect;

import java.util.Random;

public class Aspect {

   static Random random = new Random();

   public static Color newColor() {
     return Color.values()[random.nextInt(3)];
   }

   public static Pattern newPattern() {
     return Pattern.values()[random.nextInt(3)];
   }

   public static Sound newSound() {
     return Sound.values()[random.nextInt(3)];
   }

   public static Animation newAnimation() {
     return Animation.values()[random.nextInt(3)];
   }

}


