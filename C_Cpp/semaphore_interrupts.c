 * Ahmad Elhamad
 * Program 3
 * CS570 Operating Systems
 * Professor John Carroll
 * SDSU
 * 10/30/2016
 */


  * <YOUR NAME HERE>
  * Program 3
  * CS570
  * <INSTRUCTOR NAME HERE>
  * SDSU
  * <DATE HERE>
  *
  * This is the only file you may change. (In fact, the other files should
  * be symbolic links to:
  *   ~cs570/Three/p3main.c
  *   ~cs570/Three/p3.h
  *   ~cs570/Three/Makefile
  *   ~cs570/Three/CHK.h    )
  *
  */

 static sem_t court;      /*the court segregator, making sure only one group uses court at a time */
 static sem_t mutex;    /* the enforcer, ensures only process manipulates count at a time */
 int shooter_count;     /*shooters can be considered analogy for number-cruncher processes*/
 int jogger_count;    /* and joggers can be considered analogous to I/O-driven programs*/


 /*for efficiency, our program commands the CPU to run similar processes concurrently,
 * increasing the speed and efficiency with which operations are performed*/


 void initstudentstuff()                    /*basic initialization*/
 {                                          /*binary semaphores useful in turning on/off access to court*/
         CHK(sem_init(&court,LOCAL,1));     /*unamed semaphores made using _init() , initially set to 1, unlocked */
         CHK(sem_init(&mutex,LOCAL,1));      /* mutex ensures that access to our critical region is limited to one process at a time */
                                            /* when set to 0, any process that wait()'s on semaphore equal to 0
                                            /*  must wait for a previously queued process to complete and post()
                                            /*  that semaphore before being allowed to continue */

       shooter_count=0; /* initialize counters */
       jogger_count=0; /* this is where degree of concurrency is measured */
 }
       /*local variables are needed to ensure mutex and court semaphores (un)lock at appropriate times. */

 /* SIGNING IN PROCESS */
 void prolog(int athelete)  /* 0=JOGGER 1=SHOOTER */
 {
       if(athelete == 0)/* if a jogger enters 
       {
               if(jogger_count ==0 || shooter_count >0)/* if first jogger entering or shooters on court */
               {       CHK(sem_post(&mutex));          /* unlock the gate*/
                       CHK(sem_wait(&court));          /* and request access to court/queue, process blocks if court already is = 0*/
                       CHK(sem_wait(&mutex));          /* and relock mutex again */
               }

               jogger_count++;  /* account for new jogger */
       }
       if(athelete==1) /* if a shooter enter */
       {
               if(shooter_count ==0  || jogger_count>0)   /*if this is first shooter process called, or jogger processes already on court */
               {       CHK(sem_post(&mutex));             /* unlock segregator */
                       CHK(sem_wait(&court));            /* and request access to court/queue, process blocks if last jogger hasnt posted(unlocked) court*/
                       CHK(sem_wait(&mutex));            /* before relocking it*/
               }

               shooter_count++; /* add the shooter */
       }
       
 /* General documentation for the following functions is in p3.h
  * Here you supply the code:
 void initstudentstuff(void) {

 /* SIGNING OUT PROCESS*/
 void epilog(int athlete)  // signing out!
 {
       if(athlete == 1)// if the *SHOOTER* signs out
       {
               CHK(sem_wait(&mutex));             /*get exclusive access (and lock other processes until posted) */

                       shooter_count--;           /* decrement shooter count*/
               if(shooter_count ==0)             /* if this is the last shooter process about to end*/
               {       CHK(sem_post(&court));   /* unlock the court for*/
               }

               CHK(sem_post(&mutex));
         }
         if(athlete == 0) /* if the *JOGGER* calls to sign out */
         {
               CHK(sem_wait(&mutex)); /* get exclusive access to shared data*/

                       jogger_count--; /* and decrement jogger_count on his way out */
               if(jogger_count == 0) /* if this is the last jogger on his way out */
                       {         CHK(sem_post(&court));/* when last jogger process ends, CPU unblocked for "shooter" processes to begin running */
                       }

                       CHK(sem_post(&mutex)); /* release exclusive access to data */
         }
