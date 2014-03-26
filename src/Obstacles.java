import java.util.ArrayList;

public class Obstacles
{
	private ArrayList<LineObject> outlines;
	private ArrayList<Vertex> vertices;
	
	/**
	 * Constructor for the Obstacles class
	 * @param o list of LineObjects that are the outlines for the obstacle
	 */
	public Obstacles(ArrayList<LineObject> o)
	{
		outlines=o;
		ArrayList<Vertex> allV= new ArrayList<Vertex>();
		vertices= new ArrayList<Vertex>();
		if(o!=null)
		{
			allV.add(o.get(0).getV1());
			allV.add(o.get(0).getV2());

			vertices.add(o.get(0).getV1());
			vertices.add(o.get(0).getV2());
		}
		for(int i=1; i< o.size(); i++)
		{
			Vertex v1=o.get(i).getV1();
			allV.add(v1);
			boolean addit=true;
			for(int j=0; j<vertices.size(); j++)
			{
				if(v1.getXCoord()==vertices.get(j).getXCoord())
					if(v1.getYCoord()==vertices.get(j).getYCoord())
						addit=false;
			}
			if(addit==true)
				vertices.add(v1);
			Vertex v2=o.get(i).getV2();
			allV.add(v2);
			addit=true;
			for(int j=0; j<vertices.size(); j++)
			{
				if(v2.getXCoord()==vertices.get(j).getXCoord())
				{
					
					if(v2.getYCoord()==vertices.get(j).getYCoord())
					{
						
						addit=false;
					}
				}
			}
			if(addit==true)
				vertices.add(v2);
		}
		
	}
	
	/**
	 * This method returns the lines that make up the obstacle
	 * @return outlines
	 */
	public ArrayList<LineObject> getOutlines()
	{
		return outlines;
	}
	
	/**
	 * This method returns an arraylist of the vertices of the obstacle
	 * @return vertices
	 */
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	
	public void translate(int x, int y)
	{
		for(int i=0; i< outlines.size(); i++)
		{
			outlines.get(i).setV1(new Vertex(outlines.get(i).getV1().getXCoord()+x, outlines.get(i).getV1().getYCoord()+y));
			outlines.get(i).setV2(new Vertex(outlines.get(i).getV2().getXCoord()+x, outlines.get(i).getV2().getYCoord()+y));
		}
	}
}