import java.util.ArrayList;

public class LineObject
 
{
 
	private ArrayList<Vertex> Vertices;
	private char orientation;

	
 
	public LineObject(int x, int y, int x2, int y2)
	{
		Vertex v1= new Vertex(x, y);
		Vertex v2= new Vertex(x2, y2);
		Vertices= new ArrayList<Vertex>();
		Vertices.add(v1);
		Vertices.add(v2);
		if(x==x2)
		{
			orientation='v';
		}
		else if(y==y2)
			{
				orientation='h';
			}
		else
		{
			orientation='c';
		}
 
	}

	public Vertex getV1()
 
	{
 
		return Vertices.get(0);	

	 }

	public Vertex getV2()
	 
	{
 
		return Vertices.get(1);	

	 }
	
	public void setV1(Vertex v)

	{
		Vertices.set(0, v);
 
	}
 
	

	public void setV2(Vertex v)

	{
		Vertices.set(1, v);
 
	}
 
	

}
