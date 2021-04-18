//Daven Giftian Tejalaksana
//Sunday, 11 April 2021
//CSE 143
//Instructor: Stuart Reges
//TA: Andrew Cheng
//Assignment #2
//This class models a vibrating guitar string of a given frequency. 

import java.util.*;

public class GuitarString {
   private Queue<Double> ringBuffer; //A ring buffer
   private int capacityN; //capacity of the ring buffer
   private Random rand; //a Random object 
   public static final double ENERGY_DECAY_FACTOR = 0.996; //energy decay factor constant
   
   //Pre: frequency must be above 0, ring buffer size must be above 2
      //Throws IllegalArgumentException if not.
   //Constructs a guitar string of the given frequency
   public GuitarString(double frequency) {
      capacityN = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      
      if (frequency <= 0 || capacityN < 2) {
         throw new IllegalArgumentException("Frequency <= 0; Capacity < 2"); //an exception
      }
      
      ringBuffer = new LinkedList<>();
      for (int i = 0; i < capacityN; i++) {
         ringBuffer.add(0.0);
      }
      rand = new Random();
   }
   
   //Pre-condition: array init must have length of at least 2 elements.
      //Throws IllegalArgumentException if not.
   //Constructs a guitar string; initializes the contents of ring buffer to array values.
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException("Capacity < 2"); //an exception
      }
      
      ringBuffer = new LinkedList<>();
      for (Double values: init) {
         ringBuffer.add(values);
      }
   }
   
   //Post: Replaces all elements in ring buffer with random values.
   //Random values are between -0.5 inclusive and 0.5 exclusive.
   public void pluck() {
      for (int i = 0; i < capacityN; i++) {
         ringBuffer.add(rand.nextDouble() - 0.5);
         ringBuffer.remove();
      }
   }
   
   //Post: Applies the Karpus-Strong update once.
   //Deletes the sample at the front of the ring buffer.
   //Adds to the end of the ring buffer the average of the first two samples,
      // multiplied by the energy decay factor (0.996). 
   public void tic() {
      double number1 = ringBuffer.remove();
      double number2 = ringBuffer.peek();
      double newNumber = (number1 + number2) * 0.5 * ENERGY_DECAY_FACTOR;
      ringBuffer.add(newNumber);
   }
   
   //Post: Returns the value in front of the ring buffer. (current sample)
   public double sample() {
      return ringBuffer.peek();
   }
}