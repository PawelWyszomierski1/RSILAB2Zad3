import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class Workerr extends UnicastRemoteObject implements IWorker {

    Random r;
    public Workerr() throws RemoteException {
        super();
    }

    @Override
    public ResultType calculate(InputType inputParam) throws RemoteException {
        LinkedList<Integer> lista = new LinkedList<>();
        System.out.println("Otrzymano przedial od "+inputParam.x1 +" do "+inputParam.x2+ " i randomValue "+ inputParam.randomValue) ;
        for (int i = inputParam.x1; i < inputParam.x2; i++) {

            int podzielnik = 2;

            boolean liczbaPierwsza = true;

            if (i > 3) {

                while (podzielnik < i) {
                    if (i % podzielnik == 0) {

                        liczbaPierwsza = false;break;

                    }
                    podzielnik++;}

            }
            if (liczbaPierwsza) {

                lista.add(i);

            }

        }
        r = new Random(inputParam.randomValue);
        ResultType wynik = new ResultType(lista);
        wynik.randomValue = r.nextInt(100);
        return wynik;

    }
}
