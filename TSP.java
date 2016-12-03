/**
  * Author    : Christopher Bell
  * Professor : Dr Femiani
  * Date      : 12-1-2016
  * Info      : Project - Part 4
  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class TSP
{
    public static int brute(String filename)
    {
        Environment env = read(filename);

        return 0;
    }

    public static int greedy(String filename)
    {
        Random rand = new Random();

        Environment env = read(filename);

        int node = rand.nextInt(env.dimension);
        env.visited[node] = 1;

        int i;
        double distance = 0;
        for(i=1;i<env.dimension;i++)
        {
            int j;
            double min = Integer.MAX_VALUE;
            int next = -1;
            for(j=0;j<env.dimension;j++)
            {
                if(j == node || env.visited[j] == 1)
                {
                    continue;
                }

                int xd = env.x[node] - env.x[j];
                int yd = env.y[node] - env.y[j];
                double dij =  Math.sqrt(xd * xd + yd * yd);

                if(dij < min)
                {
                    min = dij;
                    next = j;
                }
            }

            distance += min;

            node = next;
            env.visited[node] = 1;
        }

        return (int) distance;
    }

    public static Environment read(String filename)
    {
        try
        {
            Scanner reader = new Scanner(new File(filename));
       
            int dim = 0; 
            String tmp = ""; 
            while(!tmp.equals("NODE_COORD_SECTION"))
            {
                tmp = reader.nextLine();

                if(tmp.startsWith("DIMENSION"))
                {
                    String num = tmp.substring(11, tmp.length()).trim();
                    dim = Integer.parseInt(num); 
                }
            }

            Environment env = new Environment(dim);

            while(reader.hasNextLine())
            {
                String line = reader.nextLine().trim();

                if(line.equals("EOF"))
                {
                    break;
                }

                String reg = "\\s*[^0-9]";
                line = line.replaceAll(reg, "-");
                String nums[] = line.split("-");

                int node = Integer.parseInt(nums[0]) - 1;
                env.x[node] = Integer.parseInt(nums[1]);
                env.y[node] = Integer.parseInt(nums[2]);
            }

            return env;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("FILE NOT FOUND: " + filename);
        }

        return null;
    }

    public static void main(String [] args) throws FileNotFoundException
    {
        if(args.length != 1)
        {
            System.out.println("PROPER USAGE: java TSP <filename>");
            System.exit(0);
        }

        String name = args[0].substring(0, args[0].length() - 4);

        System.out.println(name + " Solution: " + greedy("assets/" + args[0]));
    }
}

class Environment
{
    public int[] x;
    public int[] y;
    public int[] visited;
    public int dimension;

    public Environment()
    {
        this(0);
    }

    public Environment(int dimension)
    {
        this.dimension = dimension;
        this.x = new int[dimension];
        this.y = new int[dimension];
        this.visited = new int[dimension];

        init();
    }

    private void init()
    {
        int i;
        for(i=0;i<this.dimension;i++)
        {
            visited[i] = 0;
        }
    }
}
