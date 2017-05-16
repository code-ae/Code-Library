/*  
Copyright © 2016 Ahmad Elhamad
*/

#define STORAGE 99990

char c[STORAGE]; /* holds arguments , c[0] is command name */

char *argv_copy[STORAGE]; // holds arguments
char *pipe_one[STORAGE]; // holds arguments of first half of pipe
char *pipe_two[STORAGE]; // holds arguments of second half of pipe

int in_file = NULL;			// file descriptors
int out_file = NULL;		//points to the in/out file in argv

char *infile = '\0';		//file names
char *outfile = '\0';		

int infl_index= 0;      // stores index of in_file in argv_copy
int outfl_index= 0;     //stores index of out_file in argv_copy

int ampFLAG = 0;	//marks background processing
int leftFLAG = 0;
int rightFLAG = 0; 
int pipeFLAG = 0; 
int flags_out = O_CREAT | O_EXCL | O_RDWR;
int flags_in = O_RDONLY;

int pipeLocation= 0;
int pipe_indx[20];
int ampersand_seen=0;
void sighandler()
{

}



	
void pipe_it(){	//based off pipe.c in course reader


    int p[2]; // pipe used for redirection
    int pid; // process id number

    int x=0;

    char *firstcommand = argv_copy;
    char *piped_cmd = argv_copy[pipe_indx[x]]; //first piped command (if applicable)
    x++;	//for check if single pipe or multiple pipe
    pipe(p);	//create the pipe

    pid = fork(); // and a new child process
    if (pid > 0) {
            // Parent: Output is to child via pipe[1]

            // Change stdout to pipe[1]
            dup2(p[1], 1);
            close(p[0]);
 	    int result;
            if( result = execvp(*argv_copy, argv_copy) < 0 ){
	    	printf(" %s: Command failed/n");
	    }
    } 
    else if( pid < 0 ){
    	    fprintf(stderr,"Fork failed)";
	    //Error Handling
    else {
            // Child: Input is from pipe[0] and output is via stdout.
            dup2(p[0], 0);
            close(p[1]);
     	    
	    if( result = execvp(*argv_copy, argv_copy) < 0 ){
	    	printf(" %s: Command failed/n");
	     }
             close(p[0]);
    }
    if(pipe_indx[x] != NULL) {
    	  	int w[2]; // pipe used for redirection
     		int pid2; // process id number

    		int x=0;

    		char *firstcommand = argv_copy;
   		char *piped_cmd = argv_copy[pipe_indx[x]]; //first piped command (if applicable)
    		x++;					//for check if single pipe or multiple pipe
    
    		pipe(w);	//create the pipe

    		pid2 = fork(); // and a new child process
    		if (pid2 > 0) {
    	       			 // Parent: Output is to child via pipe[1]
        	         	 // Change stdout to pipe[1]
         	   	dup2(w[1], 1);
         	   	close(w[0]);
 	  	  	int result;
          	  	if( result = execvp(*argv_copy, argv_copy) < 0 ){
	   	 		printf(" %s: Command failed/n");
	 	   	}
	  	  } 
    		else if( pid2 < 0 ){
    		    	fprintf(stderr,"Fork failed)";
		    	//Error Handling
		}
    		else {
        		    // Child: Input is from pipe[0] and output is via stdout.
        		    dup2(w[0], 0);
        		    close(w[1]);
     	    
			    if( result = execvp(*argv_copy, argv_copy) < 0 ){
			    	printf(" %s: Command failed/n");
			     }
        		     close(w[0]);
   		 	}

		for(;;)  // wait code........*/
	 	{	pid_t pid;
			CHK( pid = wait(NULL) );
			if (pid == file_two) {
				break;
	  		 }
		}
	}

	for(;;)  // wait code........*/
 	{	pid_t pid;
		CHK( pid = wait(NULL) );
		if (pid == file_two) {
			break;
	  	 }
	}

exit(EXIT_SUCCESS);
}


int parse() { // parses a command line, each argument in seperate slot
	
	int current; //current position in storage array
	int offset = 0; // calculates offset
	int num_words = 0; // counts words, increments every getword() return
	int num_args=0;
	int pipecount = 0; // counts pipes; error if >2
	int count;		//return value of getword() indicates EOF or \n
	
	while( (count = getword(c + offset)) > 0 )
		{
			if(count == -2){
				break;
			}

			if ( *(c + offset) == '|' )  //if we reach a pipe
			{		
				pipeFLAG=1;
				argv_copy[num_args++] = (char *) NULL; //we dont put pipe as argument, Null Terminates that part of array,
				pipeLocation = num_args; // stores index of piped command..... divides between 1st args and 2nd args
				pipe_indx[pipe_count++] = pipeLocation; // store index of piped command (after '|')
														// for later reference
			}
			
			if ( *(c + offset) == '&' )
			{	ampFLAG=1;
				argv_copy[num_args++] = (char *) NULL;
				continue;
				
			}		
			if( *(c + offset) == '<' && count == 1)
			{	
				if(leftFLAG == 1){
					fprintf(stderr," Only one input redirect allowed.");
				}
				leftFLAG++; 
				infl_index = num_args; //save index
				continue;
				
			}
			else if( *(c + offset) == '>' && count == 1)
			{	if(rightFLAG == 1){
					fprintf(stderr,"Only one output redirect allowed");
				}			
				rightFLAG++;
				outfl_index = num_args;
				continue;
			}
		
			else {
			
				argv_copy[num_args++] = c + offset; /*removed offset*/
			}
			
			c[offset+count] = '\0';
			offset = offset+count+1; //offset goes up one for every letter seen in word
			num_words++;
		}
		
		if(count == -1)
		{	return count;
		}
				
	argv_copy[num_args] = NULL;
	return num_words;
}

	
int main()
{
	int in_desc;        // file descriptor for input
	int out_desc;       //file descriptor for output
	char *argument[STORAGE];	//holds value for argument check *//
	
	signal(SIGTERM,sighandler); // interrupt service routine
				// for handling SIGTERM signal
	
	pid_t pid;		 // holds process's ID val in special format
	pid_t ch_pid;		// holds processes pchild's ID val
 
	int argcount=0; 	// counts how many arguments given to line
				//always at least 1 argument, otherwise special
	for(;;) {
		
		fflush(stderr); //clean out buffers for children
		fflush(stdout); // to inherit after
		argv_copy[0] = '\0';
		argument[0] = '\0';
		ampFLAG = leftFLAG = rightFLAG = pipeFLAG = 0;
		int mark=0;
		
		printf("p2: ");     //issue prompt
		argcount = parse(); //parse command, store arg count value

		if(pipeFLAG >0) {
			int loc = pipe_indx[mark++]; //save piped cmd index to loc
			piped_cmd = argv_copy[loc] //transfer contents of piped command to pipedcmd
		}		
		if(leftFLAG > 0) {	//extract input file name from args and store in infile
			infile = argv_copy[infl_index];
		}
		if(rightFLAG > 0) { //extract output file name from argv_copy and store in outfile
			outfile = argv_copy[outfl_index];
		}

		int g=0;
		while(argv_copy[g] != NULL) {
			pipe_one[g] = argv_copy[g]  ;
			g++;
		}
		g++;
		while(argv_copy[g] != NULL){
			pipe_two[g] = argv_copy[g];
			g++;
		}
	
		if(argcount == amper_index){
			ampFLAG=1;
		}
		int i;
		if(ampersand_seen == 1){
			for(i = 0; i < argcount; i++)
			{	argument[i] = argv_copy[i];
				if( (argument[i] == '&') && argv_copy[i+1] == NULL){
					ampFLAG=1;
				
				}
			}
		}	
		if(argcount == -1) { //if first word == EOF , break
			break;
		}				
		if(argv_copy[0] == NULL && argcount == 0){
			continue; // if line is empty, 
		}
		
		//handle builtin commands and continue, OR
		// if there is a command on line, begin processing them
		if( (strcmp(argv_copy[0] , "cd") == 0))
		{ 
			if( argcount == 1 ) // if line is just cd , means change to homedir
			{	int result;
			
				if( (result=chdir(getenv("HOME"))) != 0){ // chdir() changes the directory to the specified argument
					fprintf(stderr, "cd: couldn't get to home directory. \n"); // and only returns -1 when
				}							// an error occurred
					
			} else if ( argv_copy[2] != NULL ) // if argc > 2, to many args, invalid use
			{		fprintf(stderr, "cd: too many arguments. \n");
		
			} else {   int result;		// otherwise change to specified directory	 
				   if( (result=chdir(argv_copy[1])) != 0) // if chdir returns anything besides 0, report error
					{	fprintf( stderr,"Could not change to that directory: ");
					} // only prints message error occurs, otherwise action is done
								
				}	
		}
		//setup redirection (w/ background children having 
		//their stdin redirected to /dev/null
		
		
		if(leftFLAG ==1)
		{
			if(infile == '\0'){
				fprintf(stderr, "No input file designated.\n");
				continue;
			}
			else
			{
				if( in_file = open(infile, flags_in , 777) < 0)
				{	fprintf(stderr, "Error with input file. \n");
					continue;
				}								//store location of filename
			}
		}
		if(rightFLAG == 1)
		{
			if(outfile == '\0')
			{	fprintf(stderr,"No output file designated.\n");
			}
			else
			{
				if( out_file = open(outfile, flags_out, 777 ) < 0 )
				{	fprintf(stderr,"Error with output file. \n");	
					continue;
				}
			}			
		}

		fflush(stdout);
		fflush(stderr);
	/*   new process to be created using fork()
	    returning pid's to respective processes    */
//////////////////////////////////////////////////////////////////////////////////////////////////////
/*////////*/ch_pid = fork();/////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if(ch_pid < 0) // if the process could not be created
		{	printf("Cannot Fork!");
			exit(-1);
		}
		else if(ch_pid == 0) // fork returns 0 to the child process, so being in this loop means we are currently the child
		{
			if(ampFLAG == 1  && leftFLAG == 0  )//if process is backgrounded
			{				   // it should not be able to read from terminal
			    int filepath = "/dev/null";		  //  to do this, make childs input come from /dev/null
			    				 //   save dev/null descriptor for dup2 call			  					//file descriptor created in system//
 			    if(filepath = open(filepath, flags,S_IRUSR | S_IWUSR | S_IXUSR ) < 0) 		//    open() only retuns -1 if error occurs
			    {	fprintf(stderr,"Failed to open file.\n");
					exit(9);
			    }
			    dup2(filepath, STDIN_FILENO); // dup2 used for redirection, 
			    close(filepath);	    	 // ^^redirects STDIN TO /dev/null
				
			}	
		
			if(leftFLAG == 1)
			{			 // if input flag has been spotted
			       if(( in_desc = open(infile,flags, S_IRUSR | S_IWUSR | S_IXUSR) < 0 )){
			       		fprintf(stderr, "Error opening input file. \n");
				}					 //create file descriptor for input_file
				dup2(in_desc,STDIN_FILENO);  // dup2 redirects STDIN to the input file
				leftFLAG=0;
				close(in_desc);		//deallocates filedescriptor in system for later use */
			}
			
			if(rightFLAG == 1)
			{ 		//if output flag has been spotted
			        if(( out_desc = open(outfile, flags, S_IRUSR | S_IWUSR | S_IXUSR) < 0 )){
					fprintf(stderr, "Error opening output files.\n"); // create file descriptor for output file
				}
				dup2(out_desc,STDOUT_FILENO); //and use it to redirect programs output into the outfile
				rightFLAG=0;
				close(out_desc); 		
			}
			
			if(pipeFLAG==1)
			{	pipe_it();
			}
					
			//////EXECVP////
			int exec_result= execvp(*argv_copy, argv_copy); // first argument specifes file (first element in list) to execute
			if(exec_result == -1)				//second provides entire list of arguments
			{					// execvp returns -1 only if an error occurs
				printf("%s: Command not found.\n",argv_copy[0]); //now that proper redirection has been set up
				exit(9);				// execute the process
			}	
		}
		  else {
				if(ampFLAG >0) {
				printf("%s [%d] \n",argv_copy[0],ch_pid); //print the childs pid(in this case, child should redirect stdin to /dev/null
				ampFLAG=0;
				continue;
			}
			else{	//if not background then wait till its done
				for(;;){
					pid = wait(NULL); //wait(NULL) waits for a state change in the calling process
					if(pid == ch_pid) // when fork returns same process number
						break;    // time to terminate program
					}
				} 
		}	
	}
	
	killpg( getpid(), SIGTERM ); // Terminate any children still running 
								//BAD ARGS TO KILLPG CAN CRASH AUTOGRADER
	printf("p2 terminated. \n");
	exit(0);
}
