#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>
#include <stdlib.h>
void fork_system_call()
{
	fork();
 	printf("Hello world!\n");
}
void ps_command()
{
	system("ps");
}
void exec_system_call()
{
	char *args[]={"./EXEC",NULL};
        execvp(args[0],args);
	printf("Ending-----");
}
void wait_system_call()
{
	pid_t cpid;
    if (fork()== 0)
        exit(0);           /* terminate child */
    else
        cpid = wait(NULL); /* reaping parent */
    printf("Parent pid = %d\n", getpid());
    printf("Child pid = %d\n", cpid);
}
void join_command()
{
	system("cat file1.txt");
	system("cat file2.txt");
	system("join file1.txt file2.txt");
}
int main() {
 
	int ch=0;
	do
	{
printf("\n---------------------\n1 Fork System call \n2 PS command\n3 Exec System call\n4 wait system call\n5 Join command\n6 Exit\n");
		scanf("%d",&ch);
		switch(ch)
		{
			case 1:
				fork_system_call();
			break;
			case 2:
				ps_command();
			break;
			case 3:
				exec_system_call();
			break;
			case 4:
				wait_system_call();
			break;
			case 5:
				join_command();
			break;
		}
	}while(ch!=6);
 	
    return 0;
}
/*
student@student-OptiPlex-3020:~$ gedit systemcalls.c
student@student-OptiPlex-3020:~$ gcc systemcalls.c
student@student-OptiPlex-3020:~$ ./a.out

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
1
Hello world!

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
Hello world!

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
2
  PID TTY          TIME CMD
 2957 pts/0    00:00:00 bash
 3003 pts/0    00:00:00 a.out
 3005 pts/0    00:00:00 a.out
 3006 pts/0    00:00:00 a.out
 3279 pts/0    00:00:00 a.out
 3280 pts/0    00:00:00 a.out
 3281 pts/0    00:00:00 sh
 3282 pts/0    00:00:00 ps

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
3
Ending-----
---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
4
Parent pid = 3279
Child pid = 3284

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
5
Hello
World

---------------------
1 Fork System call 
2 PS command
3 Exec System call
4 wait system call
5 Join command
6 Exit
6
student@student-OptiPlex-3020:~$ ^C
student@student-OptiPlex-3020:~$ 
*/
