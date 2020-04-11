
import java.util.*;

class Astar
 {
 int str[][]= new int[3][3];//start state
int str1[][]= new int[3][3];// dummy state
 int goal[][]= new int[3][3]; //goal state
 int numCorrect=0; 
  int a=0, b=0, c=0,n=1, x,bi,bj;
 int origNum, leftNum,rightNum,upNum,downNum;

/* n  => number of current iteration
   bi => row number of row containing 0
   bj => column number of column containing 0

*/

//----------------------------------------------------------
public void acceptStates()
{
  
  //Scanner sc = new Scanner(System.in);
  //System.out.println("Enter the Start State: ");
  int arr[] = {1,2,3,4,0,5,6,7,8};
  for(int i=0;i<3;i++)
  {
    for(int j=0;j<3;j++)
    {
        //str[i][j]=sc.nextInt();
	//str1[i][j]=str[i][j];
	str[i][j] = arr[i*3+j];
	str1[i][j] = arr[i*3+j];
    }
  }

  int arr2[] = {4,1,3,0,2,5,6,7,8};
  //System.out.println("Enter the Goal State: ");
  for(int i=0;i<3;i++)
  {
    for(int j=0;j<3;j++)
    {
      //goal[i][j]=sc.nextInt();
      goal[i][j] = arr2[i*3+j];
    }
  }
}

//---------------------------------------------------------------------------
public  void displayStates()
{
	
	  System.out.println(" State after iteration "+n);
	  for(int i=0;i<3;i++)
	  {
	    for(int j=0;j<3;j++)
	    {
	      System.out.print(str[i][j]+" ");
	    }
	     System.out.println(" ");
	  }

	/*  System.out.println(" Goal State of iteration "+n);
	  for(int i=0;i<3;i++)
	  {
	    for(int j=0;j<3;j++)
	    {
	      System.out.print(goal[i][j]+" ");
	    }
	     System.out.println(" ");
	  }*/
 n++;
}
//-------------------------------------------------------------------------------------------
public void puzzSolver()
      {
            int a;
	 //   displayStates();
           for (a=0; a<5; ++a)
                 {
                         countTiles();
                         branch();
			
                        //System.out.println(numCorrect);
                  }
      }

//-----------------------------------------------------------------------------------------
public void countTiles()
      {
          
            numCorrect =0;
            for (int i=0; i<3; ++i)
		{
			for(int j=0;j<3;j++)
                  {
                        if (str[i][j]==goal[i][j])
                        {
                              numCorrect = numCorrect + 1;
                        }
                  }
		}
	    System.out.println(" numCorrect => "+numCorrect);
         
      }
//----------------------------------------------------------------------------------------
public void branch()
      {
            if (numCorrect<9)
                  {
                        //countTiles();
                        locateSpace();
                        //System.out.println(numCorrect);
                        checkNum();
                        getLarge();
                        //System.out.println(x);

                        if(x==leftNum)
                              changeTableLeft();
                                    else if(x==rightNum)
                                          changeTableRight();
                                                else if(x==upNum)
                                                      changeTableUp();
                                                            else
                                                                  changeTableDown();
                        displayStates();
                  }
      }
//----------------------------------------------------------------------------------------------------------
 public void locateSpace()
      {
            
            for (int  t=0;t<3;++t){
		for(int s=0;s<3;s++){
                	if (str1[t][s]==0){
                                    bi=t;
				    bj=s;
				    //System.out.println(" bi => "+bi+"	bj => "+bj);
                                    return;
                              }
                  }
            }
}
//--------------------------------------------------------------------------------------------------------
 public void checkNum()
{
            makeMoveLeft();
            locateSpace();                  //Locates the position of the blank space
            countTiles();
            leftNum = numCorrect;
            resetTable();

            makeMoveUp();
            locateSpace();                  //Locates the position of the blank space
            countTiles();
            upNum = numCorrect;
            resetTable();

            makeMoveRight();
            locateSpace();                  //Locates the position of the blank space
            countTiles();
            rightNum = numCorrect;
            resetTable();

            makeMoveDown();
            locateSpace();                  //Locates the position of the blank space
            countTiles();
            downNum = numCorrect;
            resetTable();
      }

//--------------------------------------------------------------------------------------------------------------------

public void makeMoveLeft()
      {
            if(bj!=0)
            {
                
                              int temp;
                              temp = str1[bi][bj-1];
                                    if (temp != goal[bi][bj-1])
                                          {
                                                str[bi][bj-1]=str1[bi][bj];
                                                str[bi][bj] = temp;
                                               // countTiles();
                                          }
               
            }
            
      }

//------------------------------------------------------------------------------------------------------------
//Makes moves of blank tiles to right
      public void makeMoveRight()
      {
            if(bj!=2)
                  {
                   		  int temp;
                                      temp = str1[bi][bj+1];
                                       if (temp != goal[bi][bj+1])
                                         {
                                             str[bi][bj+1]=str1[bi][bj];
                                             str[bi][bj]= temp;
                                              return;
                                           }     
                                                       
                                        
                  }

      }
//--------------------------------------------------------------------------------------------------------
//Makes moves of blank tiles up

      public void makeMoveUp()
      {
            if(bi!=0)
                  {
                       
                                                int temp;
                                                temp = str1[bi-1][bj];
                                                if (temp != goal[bi-1][bj])
                                                      {
                                                            str[bi-1][bj]=str1[bi][bj];
                                                            str[bi][bj] = temp;
                                                      }
                                   
                  }
      }
//--------------------------------------------------------------------------------------------------------
//Makes moves of blank tiles down
      public void makeMoveDown()
      {
            if(bi!=2)
                  {
                      
                                                int temp;
                                                temp = str1[bi+1][bj];
                                                if (temp != goal[bi+1][bj])
                                                      {
                                                            str[bi+1][bj]=str1[bi][bj];
                                                            str[bi][bj] = temp;
                                                      }
                                          
                  }
      }

//-----------------------------------------------------------------------------------------------------
//End of method

 public void resetTable()
      {
            int i;
            for (i=0; i<3; ++i)
		{
		for(int j=0;j<3;j++)
		    {
			  str[i][j]=str1[i][j];
		    }
		}
      }

//--------------------------------------------------------------------------------

 public void getLarge()
      {
            x=leftNum;

            if (x<rightNum)
                  {
                        x=rightNum;
                  }

            if (x<upNum)
                  {
                        x=upNum;
                  }

            if (x<downNum)
                  {
                        x=downNum;
                  }
      }

//----------------------------------------------------------------------------------------------------------------------

      public void changeTableLeft()
      {
            makeMoveLeft();
            int i;
            for (i=0; i<3; ++i)
		{
		for(int j=0;j<3;j++)
		    {
                  str1[i][j] = str[i][j];
            }
      }
}
//-----------------------------------------------------------------------------------------------------------------
      public void changeTableRight()
      {
            makeMoveRight();
            int i;
            for (i=0; i<3; ++i)
		{
		for(int j=0;j<3;j++)
		    {
                        str1[i][j] = str[i][j];
                    }
                 }
      }
//---------------------------------------------------------------------------------------------------

      public void changeTableDown()
      {
            makeMoveDown();
            int i;
            for (i=0; i<3; ++i)
		{
			for(int j=0;j<3;j++)
			   {
		                str1[i][j] = str[i][j];
		          }
                 }
      }
//---------------------------------------------------------------------------------------------------

      public void changeTableUp()
      {
            makeMoveUp();
           int i;
            for (i=0; i<3; ++i)
		{
		for(int j=0;j<3;j++)
		    {
                        str1[i][j] = str[i][j];
                   }
		}
    }

//---------------------------------------------------------------------------------------------------
   public static void main(String args[])
   {
	Astar a = new Astar();	
     	a.acceptStates();
	a.puzzSolver();
	//a.displayStates();
   }

}
