package org.dozeneyes.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.dozeneyes.Logger;

public class Aspect {

   protected static Logger log = new Logger(Aspect.class);

   static Random random = new Random();
   static List<Color> colorSet = new ArrayList<Color>();
   static List<Pattern> patternSet = new ArrayList<Pattern>();
   static List<Sound> soundSet = new ArrayList<Sound>();
   static List<Animation> animationSet = new ArrayList<Animation>();


   public static Color newColor() {
      if (colorSet.isEmpty()) resetColor();
      return colorSet.remove(0);
   }

   public static Color resetColor() {
      colorSet.clear();
      for (int i=0; i<3; i++) {
         colorSet.add(Color.values()[i]);
      }
      Collections.shuffle(colorSet, random);
      return colorSet.remove(0);
   }

   public static Pattern newPattern() {
      if (patternSet.isEmpty()) resetPattern();
      return patternSet.remove(0);
   }

   public static Pattern resetPattern() {
      patternSet.clear();
      for (int i=0; i<3; i++) {
         patternSet.add(Pattern.values()[i]);
      }
      Collections.shuffle(patternSet, random);
      return patternSet.remove(0);
   }

   public static Sound newSound() {
      if (soundSet.isEmpty()) resetSound();
      return soundSet.remove(0);
   }

   public static Sound resetSound() {
      soundSet.clear();
      for (int i=0; i<3; i++) {
         soundSet.add(Sound.values()[i]);
      }
      Collections.shuffle(soundSet, random);
      return soundSet.remove(0);
   }

   public static Animation newAnimation() {
      if (animationSet.isEmpty()) resetAnimation();
      return animationSet.remove(0);
   }

   public static Animation resetAnimation() {
      animationSet.clear();
      for (int i=0; i<3; i++) {
         animationSet.add(Animation.values()[i]);
      }
      Collections.shuffle(animationSet, random);
      return animationSet.remove(0);
   }

}


