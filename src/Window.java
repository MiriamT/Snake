import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

class Window extends JFrame
{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 20;
	public static int height = 20;
	ThreadsController yellowSnake;
	ThreadsController redSnake;

	public Window()
	{

		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;

		// Creates Threads and its data and adds it to the arrayList
		for (int i = 0; i < width; i++)
		{
			data = new ArrayList<DataOfSquare>();
			for (int j = 0; j < height; j++)
			{
				DataOfSquare c = new DataOfSquare(Color.WHITE);
				data.add(c);
			}
			Grid.add(data);
		}

		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

		// Start & pauses all threads, then adds every square of each thread to the panel
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}

		// initial position of the snake
		Tuple position = new Tuple(10, 10);
		// passing this value to the controller
		yellowSnake = new ThreadsController(position, Color.yellow);

		Tuple position2 = new Tuple(13, 13);
		redSnake = new ThreadsController(position2, Color.red);

		this.addKeyListener(new KeyboardListener(yellowSnake, redSnake));

		//Let's start the game now..
		yellowSnake.start();
		redSnake.start();
		ThreadsController.spawnFood(ThreadsController.foodPosition);

	}

}
