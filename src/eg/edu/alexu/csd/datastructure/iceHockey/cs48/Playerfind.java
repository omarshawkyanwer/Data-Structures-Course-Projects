package eg.edu.alexu.csd.datastructure.iceHockey.cs48;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;

public class Playerfind implements IPlayersFinder {
	
	ArrayList<Point> points =new ArrayList();
	int right,left,up,down,cnt;
	//not valid method to check whether this index is valid or not.
	public boolean notvalid(int i,int j,int maxRows,int maxColumns)
	{	if(i>maxRows-1 || j>maxColumns-1 || i<0 || j<0)return true;
		return false;
	}
	
	// to count the reachable squares using floodfill algorithm
	public void cntReachable(int i,int j,int maxRows,int maxColumns,char key,char[][]pitch ,boolean[][]taken)
	{	if(notvalid(i, j, maxRows,maxColumns))return ;
		if(taken[i][j] || pitch[i][j]!=key)return ;	
		cnt++;
		taken[i][j]=true;
		if(j<left)left=j;
		else if(j>right)right=j;
		if(i<up)up=i;
		else if(i>down)down=i;
		cntReachable(i+1, j,maxRows,maxColumns, key,pitch,taken);//go down
		cntReachable(i, j+1,maxRows,maxColumns,key,pitch,taken);//go right
		cntReachable(i-1, j ,maxRows,maxColumns,key,pitch,taken);//go up
		cntReachable(i, j-1,maxRows,maxColumns,key,pitch,taken);//go left
		
	}
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		int size=0;
		if(photo.length==0)
		{ Point[]empty=new Point[0];
		  return empty;
		}
		// to get the dimentions of the new rink
	      int numberOfRows=2*photo.length;
		  int numberOfColumns=2*photo[0].length();
		  char[][]rink=new char[numberOfRows][numberOfColumns];
		// to insert the content of the photo in the new Char array
		char key=(char)(team+48);
		for(int i=0;i<numberOfRows/2;i++){
			char []temp=photo[i].toCharArray();
			for(int j=0;j<numberOfColumns/2;j++){
				rink[2*i+1][2*j+1]=rink[2*i][2*j+1]=rink[2*i+1][2*j]=rink[2*i][2*j]=temp[j];
			}
		}
		// end of insertion
		//start of the algorithm
		boolean[][] vis=new boolean [numberOfRows][numberOfColumns];
		for(int i=0;i<numberOfRows;i++){
			for(int j=0;j<numberOfColumns;j++){
				left=right=j;
				up=down=i;
				cnt=0;	
				cntReachable(i, j,numberOfRows,numberOfColumns, key,rink,vis);
				if(cnt>=threshold)
				{
					int x=(left+right)/2+1,y=(up+down)/2+1 ;
					points.add(new Point(x, y));
				}
			}
		}
	    size=points.size();
		if(size==0)
		{
			Point[]empty=new Point[0];
			return empty;
		}
		Point [] myPoints=new Point[size];
		int i=0;
		for(Point x:points){
			myPoints[i]=x;
			i++;
		}
		points.clear();
		//insertion sort algorithm for sorting according to x-coordinate
		for( i=1;i<size;i++){
			Point temp=myPoints[i];
			for(int j=i-1;j>=0 && temp.x<myPoints[j].x;j--){
				myPoints[j+1]=myPoints[j];
				myPoints[j]=temp;
			}
		}
		// to sort points which are equal in the x coordinate
		for( i=1;i<size;i++){
			Point temp=myPoints[i];
			for(int j=i-1;j>=0 &&( temp.y<myPoints[j].y)&&(temp.x==myPoints[j].x);j--){
				myPoints[j+1]=myPoints[j];
				myPoints[j]=temp;
			}
		}//code end
		return myPoints;
	}
}
