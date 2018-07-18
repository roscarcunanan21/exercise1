package exercise1;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DiceProbability {

	static boolean debug_mode = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int total = 1 ; total <= 13 ; total++) {
			probabilityOfResult(total);
		}
	}
	
	public static float probabilityOfResult(int total) {

		if (debug_mode) {
			System.out.println("--------------");
			System.out.println("total: " + total);			
		}
		
		// initialize the combination count
		int valid_combinations = 0;
		
		// total of 2 dice cannot be less than 2 or more than 12
		if ((total < 2) || (total > 12)) {
			if (debug_mode) {
				System.out.println("Invalid! 2 dice cannot have a total of " + total + " dots.");
			}
		}
		
		// we will prepare an array to store the number of dots for each pair of valid combinations
		// so we do not count the same dots for the next valid combinations we find
		List<Integer> valid_dots = new ArrayList<>();
		
		// we will go through each dot from 1 to 6 from the first dice,
		// make sure we skip the dot number if we already stored it previously
		// and make sure dice1_dots is less than the total
		for (int dice1_dots = 1; (dice1_dots < (total)) && (dice1_dots <= 6) && (!valid_dots.contains(dice1_dots)) ; dice1_dots++){

			// we will get the number of dots for dice2 to get the total
			int dice2_dots = total - dice1_dots;

			if (debug_mode) {
				System.out.println("--------------");
				System.out.println("dice1_dots: " + dice1_dots);
				System.out.println("dice2_dots: " + dice2_dots);
			}
			
			// skip if the calculated dots for dice2 given dice1_dots is greater than 6 or less than 1
			if ((dice2_dots > 6) || (dice2_dots < 1)) {
				if (debug_mode) {
					System.out.println("dice2_dots is invalid! skipping this pair.");
				}
				continue;
			}

			if (debug_mode) {
				System.out.println("valid pair found. Storing the dots to prevent rechecking them.");
			}
			
			// by this point dice1_dots and dice2_dots should be valid so we store those values
			// store dice1_dots if it isnt already stored
			if (!valid_dots.contains(dice1_dots)) {
				valid_dots.add(dice1_dots);
			}
			// store dice2_dots if it isnt already stored
			// we check dice2_dots separately because a valid combination can have 2 same dots
			// because it wont make sense storing the same valid dots more than once
			if (!valid_dots.contains(dice1_dots)) {
				valid_dots.add(dice1_dots);
			}
			
			// by this point, the combination should be valid so we will add it to the count
			valid_combinations++;			
		}
		
		// we will round off to the 3rd decimal place
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);

		float probability = (float)((valid_combinations * 1f) / 36);
		
		if (debug_mode) {
			System.out.println("--------------");
			System.out.println("probability: " + probability);
		}
		
		System.out.format("probabilityOfResult(%d)=%s%n",total,df.format(probability));
		return probability;
	}
}
