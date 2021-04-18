//Daven Giftian Tejalaksana
//Sunday, 11 April 2021
//CSE 143
//Instructor: Stuart Reges
//TA: Andrew Cheng
//Assignment #2
//This class models a guitar with 37 different strings. 

import java.util.*;

public class Guitar37 implements Guitar {
   public static final int TOTAL_STRINGS = 37; //total number of guitar strings
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private GuitarString[] strings; //array containing all of Guitar37's various strings.
   private int time; //amount of times tic has been called
   
   //Constructs an array of 37 guitar strings with differing frequency.
   public Guitar37() {
      strings = new GuitarString[TOTAL_STRINGS];
      for (int i = 0; i < strings.length; i++) { 
         strings[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0)); 
      }
   }
  
   //If pitch that is passed is not between -24 and 12 (inclusive), nothing happens.
   //Runs the pluck method on the specified pitch
   //Passes on a pitch to specify exactly which note to play
   public void playNote(int pitch) { 
      if (pitch >= -24 && pitch <= 12) {
         strings[pitch + 24].pluck();
      }
   }
   
   //Checks whether the particular key has a corresponding string to the guitar. 
   public boolean hasString(char key) {
      return (KEYBOARD.indexOf(key) != -1);
   }
   
   //Pre-condition: key parameter should be one of the 37 keys it is designed to play. (legal)
      //Throws IllegalArgumentException if not.
   //Retrieves and "plucks" the selected guitar string based on the key it corresponds to.
   public void pluck(char key) {
      if (!this.hasString(key)) {
         throw new IllegalArgumentException(key + "is not a legal key."); //an exception
      }
      int index = KEYBOARD.indexOf(key);
      strings[index].pluck();
   }
   
   //Returns the current sound sample (sum of all samples from the strings of the guitar).
   public double sample() {
      double sum = 0.0;
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         sum += strings[i].sample();
      }
      return sum;
   }
   
   //Calls the tic method on every string once
   //Advances the time forward one "tic".
   public void tic() {
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         strings[i].tic();
      }
      time++;
   }
   
   //Returns the current time (the number of times tic has been called)
   public int time() {
      return time;
   }
}