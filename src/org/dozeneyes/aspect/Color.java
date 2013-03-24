package org.dozeneyes.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum Color {
   PINK, RED, BLUE;

   static Random random = new Random();
   static List<Color> colorSet = new ArrayList<Color>();

   public static Color next() {
      if (colorSet.isEmpty()) return reset();
      return colorSet.remove(0);
   }

   public static Color reset() {
      colorSet.clear();
      for (int i=0; i<3; i++) {
         colorSet.add(Color.values()[i]);
      }
      Collections.shuffle(colorSet, random);
      return colorSet.remove(0);
   }


}


