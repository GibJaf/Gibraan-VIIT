import java.util.*;
  class MyAttempt{
          static Stack<String> goalStack = new Stack<>();
  
          //static String start_state="ONTABLE(C) ^ ONTABLE(D) ^ ON(A,C) ^ ON(B,D) ^ ARMEMPTY ^ CLEAR(A) ^ CLEAR(B)";
          //static String goal_state="ONTABLE(C) ^ ONTABLE(D) ^ ON(B,C) ^ ON(A,B) ^ ARMEMPTY ^ CLEAR(A) ^ CLEAR(D)";
  
          static String start_state="ONTABLE(X) ^ ONTABLE(Y) ^ ON(Z,X) ^ ARMEMPTY ^ CLEAR(Z) ^ CLEAR(Y)";
          static String goal_state = "ONTABLE(Z) ^ ON(Y,Z) ^ ON(X,Y) ^ ARMEMPTY ^ CLEAR(X)";
  
          static ArrayList<String> current_state;
 	  static ArrayList<String> action_list=new ArrayList<>();



	  public static ArrayList<String> list_assertion(String compound){
		  ArrayList<String> maker = new ArrayList<>();
		  String assertion[] = compound.split("['^']");
		  for(String as:assertion)
			  maker.add(as.trim());

		  return maker;
	  }


	public static void putdown(char x,boolean alltrue){
                String Pre="HOLDING("+x+")";
                String Add="ONTABLE("+x+")^ARMEMPTY^CLEAR("+x+")";
                String Del="HOLDING("+x+")";
                ArrayList<String> lp=list_assertion(Pre);
                ArrayList<String> la=list_assertion(Add);
                ArrayList<String> ld=list_assertion(Del);
                if(!alltrue){
                        goalStack.push("PUTDOWN("+x+")");
                        goalStack.push(Pre);
                }
               // list_operate(lp,la,ld,alltrue);
        }




	 
 


	  public static void main(String args[]){
		  current_state = list_assertion(start_state);
		  char x='\0';


		  goalStack.push(goal_state);

		  String subgoals[] = goal_state.split("['^']");
              	  for(String sg:subgoals){
                	goalStack.push(sg.trim());
              	  }
		  
		  
		  System.out.println("goalStack => "+goalStack);

		  show_stack();
		  String top = goalStack.pop();
		  System.out.println("top => "+top);

		  if(top.contains("ONTABLE")){
			  x = top.charAt(8);
			  putdown(x,false);
		  }else if(top.contains("ON")){
		  }else if(top.contains("HOLDING")){
		  }else if(top.contains("CLEAR")){
			  y = top.charAt(6);
			  for(String assertion:current_state){
				  if(assertion.matches("ON\\(.,"+y+"\\)"))	temp = assertion;
			  }
			  x = temp.charAt(3);
			  unstack(x,y,false)

		  }else if(top.contains("UNSTACK")){
		  }else if(top.contains("STACK")){
		  }else if(top.contains("PICKUP")){
		  }else if(top.contains("PUTDOWN")){
		  }else	goalStack.push(top);

		  System.out.println("\nSteps => ");
		  for(String action:action_list)	System.out.println(action);





		  
		  System.out.println("goalStack => "+goalStack);
		  
	  }

	 public static void show_stack(){

            Stack<String> tStack = new Stack<>();
            String temp="";
            while(!goalStack.empty()){
             temp=goalStack.pop();
             // System.out.println(temp);
             tStack.push(temp);
            }

            while(!tStack.empty()){
             goalStack.push(tStack.pop());
            }
    	  }


  }
