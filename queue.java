package Queue_implementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class queue
{
    static int mainHallCount=0;
    static int queueCapacity=0;
    static int currentUserInQueue=0;
    int maxHallCount=0;
    Queue<Integer> ll = new LinkedList<Integer>();
    int startingCount =0;
    public static void main(String[] args)
    {
        queue c = new queue();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of candidates who appeared for the interview: ");
        mainHallCount = sc.nextInt();
        c.maxHallCount=mainHallCount;
        System.out.println("Enter the capacity a queue can handle at a time : ");
        queueCapacity= sc.nextInt();
        while(mainHallCount > 0)
        {
            c.allowInterviewersWhenQueueIsAvailable();

            System.out.println("INTERVIEWER ID : "+ c.ll.poll() +" has completed the interview");
            if(mainHallCount==0)
            {
                while(c.ll.size() >0)
                {
                    System.out.println("INTERVIEWER ID : "+ c.ll.poll() +" has completed the interview");
                    if(c.ll.size() != 0)
                    {
                        System.out.println("CANDIDATES WAITING IN THE QUEUE :");
                        System.out.println(c.ll + "\n");
                    }
                }
                System.out.println("Interview process completed successfully..NO MORE PERSON TO INTERVIEW");
            }
        }
    }
    public void allowInterviewersWhenQueueIsAvailable()
    {
        if(ll.size()<queue.queueCapacity)
        {
            int j = queue.currentUserInQueue;
            int loopCount= queue.queueCapacity-ll.size();
            for(int i=0;i<loopCount;i++)
            {
                if(j+1 <= maxHallCount)
                {
                    ll.add(++j);
                }
                else
                {
                    break;
                }
            }
            System.out.println("CANDIDATES  WAITING IN THE QUEUE :");
            System.out.println(ll+"\n");
            queue.currentUserInQueue=j;
            mainHallCount -= loopCount;
        }
    }

}
