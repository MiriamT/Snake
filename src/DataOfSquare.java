import java.awt.Color;

public class DataOfSquare
{
	Color color;
	SquarePanel square;

	public DataOfSquare(Color col)
	{
		color = col;
		square = new SquarePanel(color);
	}

	public void lightMeUp(Color c)
	{
		square.ChangeColor(c);
	}
}
