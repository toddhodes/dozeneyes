package org.dozeneyes.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Sound {
   HIGH, MID, LOW;

   static Random random = new Random();
   static List<Sound> soundSet = new ArrayList<Sound>();

   public static Sound next() {
      if (soundSet.isEmpty()) return reset();
      return soundSet.remove(0);
   }

   public static Sound reset() {
      soundSet.clear();
      for (int i=0; i<3; i++) {
         soundSet.add(Sound.values()[i]);
      }
      Collections.shuffle(soundSet, random);
      return soundSet.remove(0);
   }

}


