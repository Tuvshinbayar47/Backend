package mn.tetris;

public class Block {

	public static void main(String[] args) {
		 int[][][] tetrominoes = {
		            

					//T-shape			
				   { {0, 1, 0, 0},
					 {1, 1, 1, 0},
					 {0, 0, 0, 0},
					 {0, 0, 0, 0}},
	        
				  //I-shape
				  { {0, 0, 0, 0},
	        	    {1, 1, 1, 1},
	        	    {0, 0, 0, 0},
	        	    {0, 0, 0, 0}},
	        						  
	        	 
				  //O-shape
	        {
	        	    {0, 1, 1, 0},
	        	    {0, 1, 1, 0},
	        	    {0, 0, 0, 0},
	        	    {0, 0, 0, 0}
	        	},

					//S=shape

	        {
	        	    {0, 1, 1, 0},
	        	    {1, 1, 0, 0},
	        	    {0, 0, 0, 0},
	        	    {0, 0, 0, 0}
	        	},


	        		//Z-shape 
	        {
	        	    {1, 1, 0, 0},
	        	    {0, 1, 1, 0},
	        	    {0, 0, 0, 0},
	        	    {0, 0, 0, 0}
	        	},

	        		//J-shape
	        		{
	        	    {1, 0, 0, 0},
	        	    {1, 1, 1, 0},
	        	    {0, 0, 0, 0},
	        	    {0, 0, 0, 0}
	        		},
	        		
	        		//H-shape minii buteesen shape
	        		{
	        		{1, 0, 1, 0},
	        		{1, 1, 1, 0},
	        		{1, 0, 1, 0},
	        		{0 ,0, 0, 0}
	        		}
		 };




	        String[] names = {"T", "I", "O", "S", "Z", "J", "H"};

	        for (int i = 0; i < tetrominoes.length; i++) {
	            System.out.println("Shape " + names[i] + ":");
	            printShape(tetrominoes[i]);
	            System.out.println();
	        }
	    }

	public static void printShape(int[][] shape) {
		for (int row = 0; row < shape.length; row++) {
			for (int col = 0; col < shape[row].length; col++) {
				System.out.print(shape[row][col] == 1 ? "■ " : "  ");
			}
			System.out.println();
		}
	}
}
