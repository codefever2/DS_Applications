package priority_queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        allFileWrite();
        PriorityQueue<FileArrangement> pq = new PriorityQueue<FileArrangement>();
        pq.add(new FileArrangement("abc4.txt",numberOfCharactersInAFile("abc4.txt")) );
        pq.add(new FileArrangement("abc2.txt",numberOfCharactersInAFile("abc2.txt")) );
        pq.add(new FileArrangement("abc.txt",numberOfCharactersInAFile("abc.txt")) );
        pq.add(new FileArrangement("abc1.txt",numberOfCharactersInAFile("abc1.txt")) );
        pq.add(new FileArrangement("abc3.txt",numberOfCharactersInAFile("abc3.txt")) );
        System.out.println("The items in the queue are :"+"\n");
        Iterator<FileArrangement> it = pq.iterator();
        {
            while(it.hasNext())
            {
                FileArrangement fa = pq.poll();
                System.out.println("FILENAME : "+fa.getFileName()+" "+" FILE LENGTH: "+fa.getLength());
                System.out.println("****************************");
                fileReadWrite(fa.getFileName());
                System.out.println("****************************");
                System.out.print("\n");
            }
        }

    }
    public static void fileReadWrite(String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while(line != null)
            {
                System.out.println(line);
                line= br.readLine();
            }
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println("exception handled 1");
        }
    }
    public static long numberOfCharactersInAFile(String filename)
    {
        long count =0;
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null)
            {
                count += line.length();
                line= br.readLine();
            }
            br.close();
            fr.close();
        }
        catch(Exception ex)
        {
            System.out.println("exception handled");
        }
        return count;
    }
    public static void allFileWrite() throws IOException
    {
        FileWriter fw = new FileWriter("abc.txt");
        String x = "This is new trial file with lesser words.";
        fw.write(x);
        fw.close();
        fw = new FileWriter("abc1.txt");
        x = "This is a new trial file.\n" +
                "we are going to use priority queue based on the number of words in the file.\n" +
                "\n" +
                "The smallest file will be read and printed first.";
        fw.write(x);
        fw.close();
        fw = new FileWriter("abc2.txt");
        x = "This is new trial3 file with more words.\n" +
                "This is a new trial file.\n" +
                "we are going to use priority queue based on the number of words in the file.\n" +
                "\n" +
                "The smallest file will be read and printed first.";
        fw.write(x);
        fw.close();
        fw = new FileWriter("abc3.txt");
        x = "This is new trial3 file with more words.\n" +
                "This is a new trial file.\n" +
                "we are going to use priority queue based on the number of words in the file.\n" +
                "\n" +
                "The smallest file will be read and printed first.The largest file will printed at last and then output will be displayed";
        fw.write(x);
        fw.close();
        fw = new FileWriter("abc4.txt");
        x = "This is new trial3 file with more words.\n" +
                "This is a new trial file.\n" +
                "we are going to use priority queue based on the number of words in the file.\n" +
                "\n" +
                "The smallest file will be read and printed first.I am a coder";
        fw.write(x);
        fw.close();
    }

    public static class FileArrangement implements Comparable<FileArrangement>
    {
        private String fileName;
        private  long length;

        public String getFileName()
        {
            return fileName;
        }

        public long getLength() {
            return length;
        }

        @Override
        public String toString()
        {
            return "FileName : "+fileName+"Length : "+ length;
        }

        public FileArrangement(String fileName, long length)
        {
            this.fileName = fileName;
            this.length = length;
        }

        @Override
        public int compareTo(FileArrangement fi)
        {
            if(length<fi.length)
            {
                return -1;
            }
            else if (length > fi.length)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}