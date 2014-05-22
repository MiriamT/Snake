import java.awt.Color;
import java.util.ArrayList;

//Controls all the game logic .. most important class in this project.

public class ThreadsController extends Thread
{
	static ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
	static boolean run = true;
	Tuple headSnakePos;
	int sizeSnake = 3;
	long speed = 75;
	public int directionSnake;
	ArrayList<Tuple> positions = new ArrayList<Tuple>();
	static Tuple foodPosition = new Tuple(Window.height - 1, Window.width - 1);
	Color color;
	int numFoodEaten;
	String colorName;

	//Constructor of ControllerThread 
	ThreadsController(Tuple positionDepart, Color c)
	{
		numFoodEaten = 0;
		color = c;
		//Color.toString used to print the name, but now it doesnt so i do this :(
		if (color == Color.yellow)
		{
			colorName = "YELLOW";
		}
		else
		{
			colorName = "RED";
		}
		//Get all the threads
		Squares = Window.Grid;

		headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
		directionSnake = 1;

		//!!! Pointer !!!!
		Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
		positions.add(headPos);

	}

	//Important part :
	@Override
	public void run()
	{
		while (run)
		{
			moveInterne(directionSnake);
			checkCollision();
			moveExterne();
			deleteTail();
			pauser();
		}
	}

	//delay between each move of the snake
	private void pauser()
	{
		try
		{
			sleep(speed);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	//Checking if the snake bites itself or is eating
	private void checkCollision()
	{
		Tuple posCritique = positions.get(positions.size() - 1);
		for (int i = 0; i <= positions.size() - 2; i++)
		{
			boolean biteItself = posCritique.getX() == positions.get(i).getX() && posCritique.getY() == positions.get(i).getY();
			if (biteItself)
			{
				System.out.println(colorName + " SNAKE: Ouch! i'll do better next time..");
				stopTheGame();
			}
		}

		boolean eatingFood = posCritique.getX() == foodPosition.y && posCritique.getY() == foodPosition.x;
		if (eatingFood)
		{
			if (numFoodEaten == 9)
			{
				System.out.println(colorName + " SNAKE: mmm, victory tastes delicious!");
				stopTheGame();
			}
			else
			{

				sizeSnake = sizeSnake + 1;
				numFoodEaten++;
				System.out.println(colorName + " SNAKE: Yummy!  " + (10 - numFoodEaten) + " more to go");
				foodPosition = getValAreaNotInSnake();
				spawnFood(foodPosition);
			}
		}
	}

	//Stops The Game
	private void stopTheGame()
	{
		run = false;
	}

	//Put food in a position and displays it
	public static void spawnFood(Tuple foodPositionIn)
	{
		Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(Color.GREEN);
	}

	//return a position not occupied by the snake
	private Tuple getValAreaNotInSnake()
	{
		Tuple p;
		int ranX = 0 + (int) (Math.random() * 19);
		int ranY = 0 + (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		for (int i = 0; i <= positions.size() - 1; i++)
		{
			if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY())
			{
				ranX = 0 + (int) (Math.random() * 19);
				ranY = 0 + (int) (Math.random() * 19);
				p = new Tuple(ranX, ranY);
				i = 0;
			}
		}
		return p;
	}

	//Moves the head of the snake and refreshes the positions in the arraylist
	//1:right 2:left 3:top 4:bottom 0:nothing
	private void moveInterne(int dir)
	{
		switch (dir)
		{
		case 4:
			headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % 20);
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		case 3:
			if (headSnakePos.y - 1 < 0)
			{
				headSnakePos.ChangeData(headSnakePos.x, 19);
			}
			else
			{
				headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y - 1) % 20);
			}
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		case 2:
			if (headSnakePos.x - 1 < 0)
			{
				headSnakePos.ChangeData(19, headSnakePos.y);
			}
			else
			{
				headSnakePos.ChangeData(Math.abs(headSnakePos.x - 1) % 20, headSnakePos.y);
			}
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));

			break;
		case 1:
			headSnakePos.ChangeData(Math.abs(headSnakePos.x + 1) % 20, headSnakePos.y);
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		}
	}

	//Refresh the squares that needs to be 
	private void moveExterne()
	{
		for (Tuple t : positions)
		{
			int y = t.getX();
			int x = t.getY();
			Squares.get(x).get(y).lightMeUp(color);
		}
	}

	//Refreshes the tail of the snake, by removing the superfluous data in positions arraylist
	//and refreshing the display of the things that is removed
	private void deleteTail()
	{
		int cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--)
		{
			if (cmpt == 0)
			{
				Tuple t = positions.get(i);
				Squares.get(t.y).get(t.x).lightMeUp(Color.WHITE);
			}
			else
			{
				cmpt--;
			}
		}
		cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--)
		{
			if (cmpt == 0)
			{
				positions.remove(i);
			}
			else
			{
				cmpt--;
			}
		}
	}
}
