
import java.util.*;
class Gs{
	static Stack<String> goalStack = new Stack<>();
	
	//static String start_state="ONTABLE(C) ^ ONTABLE(D) ^ ON(A,C) ^ ON(B,D) ^ ARMEMPTY ^ CLEAR(A) ^ CLEAR(B)";
	//static String goal_state="ONTABLE(C) ^ ONTABLE(D) ^ ON(B,C) ^ ON(A,B) ^ ARMEMPTY ^ CLEAR(A) ^ CLEAR(D)";

	static String start_state="ONTABLE(X) ^ ONTABLE(Y) ^ ON(Z,X) ^ ARMEMPTY ^ CLEAR(Z) ^ CLEAR(Y)";
	static String goal_state = "ONTABLE(Z) ^ ON(Y,Z) ^ ON(X,Y) ^ ARMEMPTY ^ CLEAR(X)";

	static ArrayList<String> current_state;
  	static ArrayList<String> action_list=new ArrayList<>();


	public static void list_operate(ArrayList<String> lp,ArrayList<String> la,ArrayList<String> ld,boolean alltrue){
        	if(!alltrue){
            		for(String as:lp){
           			//Adding preconditions to goalstack
            			goalStack.push(as);
	        	}
	    	}
	    	else{
        		for(String as:la){
          			//all preconditons are true,add items to state
	        		if(!current_state.contains(as.trim()))
	           			current_state.add(as.trim());
        		}
        		for(String as:ld){
	         		//all preconditons are true, delete items from current state
	         		if(current_state.contains(as.trim()))
	            			current_state.remove(as.trim());
         		}
       		}
     	}

	public static void stack(char x,char y,boolean alltrue){
	    String Pre="HOLDING("+x+")^CLEAR("+y+")";
      String Add="ARMEMPTY^ON("+x+","+y+")^CLEAR("+x+")";
      String Del="CLEAR("+y+")^HOLDING("+x+")";
      ArrayList<String> lp=list_assertion(Pre);
      ArrayList<String> la=list_assertion(Add);
      ArrayList<String> ld=list_assertion(Del);
      if(!alltrue){
         goalStack.push("STACK("+x+","+y+")");
         goalStack.push(Pre);
      }
      list_operate(lp,la,ld,alltrue);
    }
	public static void unstack(char x,char y,boolean alltrue){
    String Pre="ON("+x+","+y+")^CLEAR("+x+")^ARMEMPTY";
    String Add="HOLDING("+x+")^CLEAR("+y+")";
    String Del="ON("+x+","+y+")^CLEAR("+x+")^ARMEMPTY";
    ArrayList<String> lp=list_assertion(Pre);
    ArrayList<String> la=list_assertion(Add);
    ArrayList<String> ld=list_assertion(Del);
    if(!alltrue){
      goalStack.push("UNSTACK("+x+","+y+")");
      goalStack.push(Pre);
    }
    list_operate(lp,la,ld,alltrue);
	}
	public static void pickup(char x,boolean alltrue){
	    String Pre="ONTABLE("+x+")^CLEAR("+x+")^ARMEMPTY";
      String Add="HOLDING("+x+")";
      String Del="ONTABLE("+x+")^CLEAR("+x+")^ARMEMPTY";
      ArrayList<String> lp=list_assertion(Pre);
      ArrayList<String> la=list_assertion(Add);
      ArrayList<String> ld=list_assertion(Del);
      if(!alltrue){
         goalStack.push("PICKUP("+x+")");
         goalStack.push(Pre);
      }
      list_operate(lp,la,ld,alltrue);
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
      		list_operate(lp,la,ld,alltrue);
	}

	public static ArrayList<String> list_assertion(String compound){
	    ArrayList<String> maker =new ArrayList<>();
	    String assertion[] = compound.split("['^']");
	    for(String as:assertion){
	      maker.add(as.trim());
	    }
	    return maker;
	}


	public static void main(String args[]){
	    //List of Assertions that are True which make the state description
        current_state = list_assertion(start_state);
	    //Push Final State and SubGoals onto Stack
	    goalStack.push(goal_state);
	    String subgoals[] = goal_state.split("['^']");
	    for(String sg:subgoals){
	      goalStack.push(sg.trim());
	    }
	    boolean b=true;
	    String s="ON(a,b)";

	    char t='\0';
	    char x='\0';
	    char y='\0';
	    String temp="";
	    ArrayList<String> simple;
	     while(!goalStack.empty()){
	         show_stack();
	         String top=goalStack.pop();

		 // since no action is being performed here , this can be eliminated
	         if(current_state.contains(top)){
	            // System.out.println(top+" True pushed off Stack");
	         }

		 //top cant contain ^ cuz goalStack wont contain it , thus can be skipped
	         else if(top.contains("^")){
	            //SPLIT AND RESTACK
	            // System.out.println(top+" Compound Split Before Re-entry");
	            simple=list_assertion(top);
	            for(String sp:simple){
	                goalStack.push(sp);
	            }
	         }


	         else{
	            if(top.contains("ONTABLE")){
	                x=top.charAt(8);
	                putdown(x,false);
	                // System.out.println(top+" False Replaced with action");
	            }
	            else if(top.contains("ON")){
	                x=top.charAt(3);
	                y=top.charAt(5);
	                stack(x,y,false);
	                // System.out.println(top+" False Replaced with action");
	            }
	            else if(top.contains("ARMEMPTY")){
	                //check for holding and put down OR STACK
	                for(String assertion:current_state){
	                    if(assertion.contains("HOLDING")){
	                        temp=assertion;
	                    }
	                }
	                x=temp.charAt(8);
	                putdown(x,false);
	                // System.out.println(top+" False Replaced with action");

	            }
	            else if(top.contains("HOLDING")){
               x=top.charAt(8);
	                if(current_state.contains("ONTABLE("+x+")")){
	                    pickup(x,false);
	                }
	                else{
	                    for(String assertion:current_state){
	                        if(assertion.contains("ON("+x+",")){
	                            temp=assertion;
	                        }
	                    }
	                   y=temp.charAt(5);
	                   unstack(x,y,false);
	                }

	                // System.out.println(top+" False Replaced with action");
	            }
	            else if(top.contains("CLEAR")){
	                y=top.charAt(6);
	                for(String assertion:current_state){
	                   if(assertion.matches("ON\\(.,"+y+"\\)")){
	                      temp=assertion;
	                   }
	                }
	                x=temp.charAt(3);
	                unstack(x,y,false);

	                // System.out.println(top+" False Replaced with action");
	            }
	            else if(top.contains("UNSTACK")){
	                x=top.charAt(8);
	                y=top.charAt(10);
	                unstack(x,y,true);
	                action_list.add("UNSTACK("+x+","+y+")");
	                // System.out.println("Action Performed: UNSTACK("+x+","+y+")");
	            }
	            else if(top.contains("STACK")){
	                x=top.charAt(6);
	                y=top.charAt(8);
	                stack(x,y,true);
	                action_list.add("STACK("+x+","+y+")");
	                // System.out.println("Action Performed: STACK("+x+","+y+")");
	            }
	            else if(top.contains("PICKUP")){
	                x=top.charAt(7);
	                pickup(x,true);
	                action_list.add("PICKUP("+x+")");
	                // System.out.println("Action Performed: PICKUP("+x+")");
	            }
	            else if(top.contains("PUTDOWN")){
	                x=top.charAt(8);
	                putdown(x,true);
	                action_list.add("PUTDOWN("+x+")");
	                // System.out.println("Action Performed: PUTDOWN("+x+")");
	            }
	           else{goalStack.push(top);}
	         }
	     }
	     System.out.println("Steps:");
	     for(String action:action_list){
	        System.out.println(action);
	     }
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

