
/*		CSC 244
 * 	Homework Assignment 1
 *  Name: Bhargi Patel
 *  Sac State ID- 217314540  
 */

/*   Assuming Relation R and S are in hard disc
     * S has few tuples which can be less than the size of memory (S<M-1)
     * 
     * Relation S and R is assumed to be two-dimensional array, and each block
     * contains 3 tuples.
     * 
     * Each tuple of R and S is assumed to have one attribute value
     *  
*/
package csc;

public class BagDiff 
{
	
	// Declarating of Relation S
	
	int [][] S = { {2,3,4},{2,2,7},{2,4,5} };
	
	// Declarating of Relation R
	
	int [][] R = { {2,3,4},{2,4,5},{2,2,7},{5,4,7},{2,3,4},{5,6,7},{8,9,10} };
	
	// Creating Memory Mem for storage of tuples of relation S
	int Mem[] = new int[50];
	
	// Count will store the number of occurrences of each tuple of relation S
	int count[] = new int[50];
	
	// index for loading each tuple into memory
	int index = 0;
	
	// output is the output buffer for the disk storage
	int output[]= new int[100];
	
	
	public void algo()
	{
		// To add distinct tuple values of relation S
		// Loop to move the Relation S into the memory;
		for(int i =0; i<3; i++)
		{
				/* Load each tuple of one block into memory
				 * all tuple of S will be stored in Mem[]
				 * and the count of each occurrence will be stored in count[]
				 */
			
			for(int j =0; j<3;j++)
			{
				int flag=0;
				
				 /* 
		          flag is used to track the number of identical tuple in the relation S
		        */	
				
				for(int k=0;k<index;k++)
				{
					// if same tuple found which is already present in memory
					// increase count by 1
					if(S[i][j] == Mem[k])
					{
						count[k]+=1;
						flag = 1;
					}
				}
					// tuples are added into memory storage
					if(flag == 0)
					{
						Mem[index]= S[i][j];
						count[index]=1;
						index+=1;
					}
			}
		}
		
	// Store all elements into memory and display them
		
	System.out.println("\n---- Elements:\n");
	for(int i=0; i<index; i++)
	{
		System.out.print("   "+Mem[i]);
	}
	
	/* Display count of each element stored into memory
	 * This count would be used to store tuple into 
	 * output buffer
	 */
	
	System.out.println("\n\n---- # Count of each element:\n\n");
	for(int i=0; i<index; i++)
	{
		System.out.print("   "+count[i]);
	}
	System.out.println("\n\n---- Bag Difference:\n");
	
	
	/* get each block from R and compare it with all the elements of S( which is in memory)
     * if matching found then move R to the buffer and
     * If the current count=0, copy R to the output
     * If count>0, do not copy R to the output, but count=count-1 
     * If R does not occur in S, then we copy R to the output
     */
	
	int output_index=0;
	
	// Loop to check each block of relation R
	for(int i=0;i<R.length;i++)
	{
		for(int j=0;j<S.length;j++)
		{
			for(int k=0;k<index;k++)
			{
				if(Mem[k] == R[i][j])
				{	
					if (count[k] > 0)
					{
						count[k] -=1;
						break;
					}
					else if( count[k] == 0)
					{			
						output[output_index] = R[i][j];
						output_index += 1;
						break;
					}
				}
				if(k==index-1)
				{
					output[output_index] = R[i][j];
					output_index += 1;
				}
			}			
		}
	}
	
	/* Display the bag intersection which will be stored
	 *  in the output buffer
	 */
	
	for(int i=0; i<output_index; i++)
	{
		System.out.print("  "+output[i]);
	}
	System.out.println("\n---- ");
	}		
}


