import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter
{
	private final ThreadsController yellowSnake;
	private final ThreadsController redSnake;

	public KeyboardListener(ThreadsController yellowSnake, ThreadsController redSnake)
	{
		this.yellowSnake = yellowSnake;
		this.redSnake = redSnake;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
			//if it's not the opposite direction
			if (yellowSnake.directionSnake != 2)
			{
				yellowSnake.directionSnake = 1;
			}
			break;
		case KeyEvent.VK_D:
			//if it's not the opposite direction
			if (redSnake.directionSnake != 2)
			{
				redSnake.directionSnake = 1;
			}
			break;
		case KeyEvent.VK_UP:
			if (yellowSnake.directionSnake != 4)
			{
				yellowSnake.directionSnake = 3;
			}
			break;
		case KeyEvent.VK_W:
			if (redSnake.directionSnake != 4)
			{
				redSnake.directionSnake = 3;
			}
			break;

		case KeyEvent.VK_LEFT:
			if (yellowSnake.directionSnake != 1)
			{
				yellowSnake.directionSnake = 2;
			}
			break;
		case KeyEvent.VK_A:
			if (redSnake.directionSnake != 1)
			{
				redSnake.directionSnake = 2;
			}
			break;

		case KeyEvent.VK_DOWN:
			if (yellowSnake.directionSnake != 3)
			{
				yellowSnake.directionSnake = 4;
			}
			break;
		case KeyEvent.VK_S:
			if (redSnake.directionSnake != 3)
			{
				redSnake.directionSnake = 4;
			}
			break;

		default:
			break;
		}
	}

}
