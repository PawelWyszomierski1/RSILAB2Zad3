import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.LinkedList;

public class MyApp {
    public static void main(String[] args) {
        LinkedList<IWorker> workers = new LinkedList<IWorker>();
        LinkedList<ResultType> result = new LinkedList<>();
        LinkedList<String> serverNames;
        int maxNumberToSearch = 11;
        boolean isWorker = false;
        if (new String("worker").equals(args[0])) isWorker=true;
        Workerr worker;
        Registry reg;

        try
        {
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            reg = LocateRegistry.createRegistry(1099);

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }



        if(isWorker)
        {
            try
            {
                worker= new Workerr();
                Naming.rebind("//127.0.0.1/" + args[1],worker);
                System.out.println("Server is registered now :)");
                System.out.println("Press Crl+C to stop...");
            }
            catch (Exception e )
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                serverNames = new LinkedList<>();
                Collections.addAll(serverNames, Naming.list("//" + "127.0.0.1" + "/"));
            }
            catch (Exception e)
            {
                System.out.println("Nie mozna pobrac referencji");
                e.printStackTrace();;
                return;
            }

            try
            {
                for(String address :serverNames )
                {
                    workers.add((IWorker) java.rmi.Naming.lookup(address));
                    System.out.println(address);
                }

            }
            catch(Exception e) {
                System.out.println("Blad zdalnego wywolania.");
                e.printStackTrace();
                return;
            }
            try
            {
                InputType input = new InputType();
                int size = workers.size();
                int increase = maxNumberToSearch/size;
                int start = 0;


                for (int i =0;i<size;i++)
                {
                    input.x1=start;
                    input.x2=start+increase;
                    start+=increase;
                    if (i== size-1) input.x2 = maxNumberToSearch;

                    result.add(workers.get(i).calculate(input));
                }
            }
            catch (Exception e)
            {
                System.out.println("BLAD");
                e.printStackTrace();
            }
            LinkedList<Integer> finalList = new LinkedList<>();
            for(ResultType r:result)
            {
                finalList.addAll(r.liczby);
                System.out.println(r.liczby.toString());
            }
            System.out.println(finalList.toString());

        }


    }
}
