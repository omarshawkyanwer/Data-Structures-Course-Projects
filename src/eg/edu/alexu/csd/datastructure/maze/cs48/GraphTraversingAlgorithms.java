package eg.edu.alexu.csd.datastructure.maze.cs48;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs48.LinkedBased;
import eg.edu.alexu.csd.datastructure.stack.cs48.MyStack;

public class GraphTraversingAlgorithms implements IMazeSolver{	
	Scanner scan=null;
	
	boolean isValid(int i,int j,int row,int col){
		return (i<row && j<col && i>=0 && j>=0);
	}
	@Override
	public int[][] solveBFS(File maze) {
		// TODO Auto-generated method stub
			
			int row=0,col=0;
			char [][]grid;
			try{
				BufferedReader reader = new BufferedReader(new FileReader(maze));				
				String read = reader.readLine();
				String[] split = read.split(" ");
				row =  Integer.valueOf(split[0]);
                col = Integer.valueOf(split[1]);
                if(col<=0 || row<=0)throw new RuntimeException();
                String readline;
                int num = 0;
                grid = new char[row][col];
                while((readline = reader.readLine()) != null){
                    char[] ch = readline.toCharArray();
                    if(ch.length!=col)throw new RuntimeException();
                    for(int i = 0;i < ch.length;i++){
                        grid[num][i] = ch[i];
                    }
                    num++;
                }
                if(num!=row)throw new RuntimeException();
			}
			catch(Exception e){
			      throw new RuntimeException("Wrong Input.");
			}
				
			
			 //BFS
			 Point S= new Point(-1,-1);
			 Point E=new Point(-1,-1);
			 for(int i=0;i<row;i++)
			 {
				 for(int j=0;j<col;j++)
				 {
					 if(grid[i][j]=='S')
					 {
						 S.x=i;
						 S.y=j;
					 }
					 else if(grid[i][j]=='E')
					 {
						 E.x=i;
					     E.y=j;
					 }
				 }
			 }
			 if(S.x==-1 || E.x==-1) throw new RuntimeException();
			 boolean [][]visited = new boolean[row][col];
			 LinkedBased q=new LinkedBased();
			 Point [][] parent=new Point[row+2][col+2];
			 parent[S.x][S.y]=new Point(-1,-1);
			 Point temp =new Point();
			 q.enqueue(S);
			 visited[S.x][S.y]=true;
			 boolean flag=false;
			 while(!q.isEmpty()){
				 temp=(Point)q.dequeue();
				 if(grid[temp.x][temp.y]=='E'){
					 flag=true;
					 break;
				 }
				 else if(grid[temp.x][temp.y]=='#')continue;
				 visited[temp.x][temp.y]=true;
				 if(isValid((temp.x)-1,temp.y,row,col) && !visited[(temp.x)-1][temp.y]  )//up
				 {	
					 parent[(temp.x)-1][temp.y]=temp;
					 q.enqueue(new Point((temp.x)-1,temp.y)); 
				 }
				 if(isValid(temp.x,(temp.y)+1,row,col) && !visited[temp.x][(temp.y)+1] )//right
				 {	
					 parent[temp.x][temp.y+1]=temp;
					 q.enqueue(new Point(temp.x,(temp.y)+1)); 
				 }
				 if(isValid((temp.x),temp.y-1,row,col) && !visited[(temp.x)][temp.y-1]   )//left
				 {
					 parent[temp.x][temp.y-1]=temp;
					 q.enqueue(new Point((temp.x),temp.y-1));
				 } 
				 if(isValid(temp.x+1,temp.y,row,col) && !visited[temp.x+1][temp.y]  )//down
				 {	
					 parent[temp.x+1][temp.y]=temp;
					 q.enqueue(new Point(temp.x+1,temp.y)); 
				 }
			 }
			 ArrayList<Point> path=new ArrayList();
			 path.add(E);
			 if(!flag)return null;
			 temp=E;
			 while(temp.x!=-1 && temp.y!=-1)
			 {
				 temp=parent[temp.x][temp.y];
			     path.add(temp);
			 }
			 path.remove(path.size() - 1);
			 Collections.reverse(path);
			 int [][]result=new int[path.size()][2];
			 int i=0;
			 for(Point x:path){
				result[i][0]=x.x;
				result[i][1]=x.y;
				i++;
			}
			return result; 
	}
	@Override
	public int[][] solveDFS(File maze) {
		// TODO Auto-generated method stub
		int row,col;
		char [][]grid;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(maze));				
			String read = reader.readLine();
			String[] split = read.split(" ");
			row =  Integer.valueOf(split[0]);
            col = Integer.valueOf(split[1]);
            if(col<=0 || row<=0)throw new RuntimeException();
            String readline;
            int num = 0;
            grid = new char[row][col];
            while((readline = reader.readLine()) != null){
                char[] ch = readline.toCharArray();
                if(ch.length!=col)throw new RuntimeException();
                for(int i = 0;i < ch.length;i++){
                    grid[num][i] = ch[i];
                }
                num++;
            }
            if(num!=row)throw new RuntimeException();
		}
		catch(Exception e){
		      throw new RuntimeException("Wrong Input.");
		}
		 Point S= new Point(-1,-1);
		 Point E=new Point(-1,-1);
		 for(int i=0;i<row;i++)
		 {
			 for(int j=0;j<col;j++)
			 {
				 if(grid[i][j]=='S')
				 {
					 S.x=i;
					 S.y=j;
				 }
				 else if(grid[i][j]=='E')
				 {
					 E.x=i;
				     E.y=j;
				 }
			 }
		 }
		 if(S.x==-1 || E.x==-1) throw new RuntimeException();
		 MyStack API=new MyStack();
		 Point temp=new Point();
		 boolean flag=false;
		 boolean [][]visited = new boolean[row][col];
		 Point [][] parent=new Point[row][col];
		 parent[S.x][S.y]=new Point(-10000,-10000);
		 API.push(S);
		 while (!API.isEmpty())
		 {	temp=(Point)API.pop();
		     if(temp.x==E.x && temp.y==E.y)
		     {	flag=true;		
		    	 break;
		     }
		     
			 if(!visited[temp.x][temp.y])
			 {
				 visited[temp.x][temp.y]=true;
				 if(isValid((temp.x),temp.y-1,row,col) && !visited[(temp.x)][temp.y-1] && grid[temp.x][temp.y-1]!='#'  )//left
				 {
					 parent[temp.x][temp.y-1]=temp;
					 API.push(new Point((temp.x),temp.y-1)); 
				 } 
				 if(isValid((temp.x)-1,temp.y,row,col) && !visited[(temp.x)-1][temp.y] && grid[temp.x-1][temp.y]!='#' )//up
				 {	
					 parent[(temp.x)-1][temp.y]=temp;
					 API.push(new Point((temp.x)-1,temp.y)); 
				 }
				 if(isValid(temp.x,(temp.y)+1,row,col) && !visited[temp.x][(temp.y)+1]  && grid[temp.x][temp.y+1]!='#'  )//right
				 {
					 parent[temp.x][temp.y+1]=temp;
					 API.push(new Point(temp.x,(temp.y)+1));
				 }
				
				 if(isValid(temp.x+1,temp.y,row,col) && !visited[temp.x+1][temp.y] &&  grid[temp.x+1][temp.y]!='#' )//down
				 {
					 parent[temp.x+1][temp.y]=temp;
					 API.push(new Point(temp.x+1,temp.y)); 
				 }
				 //to check all valid neighbours
			 }	
		 }
		
		 if(!flag)return null;
		 ArrayList<Point> path=new ArrayList();
		 path.add(E);
		 temp=E;
		 while(temp.x!=-10000 && temp.y!=-10000)
		 {
			 temp=parent[temp.x][temp.y];
		     path.add(temp);
		 }
		 path.remove(path.size() - 1);
		 Collections.reverse(path);
		 int [][]result=new int[path.size()][2];
		 int i=0;
		 for(Point x:path){
			result[i][0]=x.x;
			result[i][1]=x.y;
			i++;
		}
		return result; 
	}
}
