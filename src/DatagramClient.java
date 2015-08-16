import java.net.*;
import java.io.*;
public class DatagramClient
{public static void main(String[] args) {
    String hostname;
    int port = 2018;
    int len = 1024;
    DatagramPacket sPacket, rPacket;
    
    //CLIENT: takes input from the user 
    //	reserve <name> 
    //	bookSeat <name> <seatNum>  
    //	search <name> 
    //	delete <seatNum>
    System.out.println("UDP Datagram client Start");
    //System.out.println("enter the reservation name: ");
    // Retrieve the input 
    //  
    //array list of c total seats from 1 to c (100) 
    //the array has 
    //	1. an binary bit 1/0 signaling if a seat is available or already reserved 
    //	2. integer seat number 
    //  3. string = name of the person who holds the seat 

    //initialize the seat array list c with values 1 to c 
    // int    list.add(c1);    list.add(c2);    list.add(c. ...n);
    //list.get(index) 
    //assign it to c 
    //INTIALIZE the c ARRAY 
    
    // following is the UDP example from the book Fig 6.2 p.p. 95
    if (args.length > 0)
        hostname = args[0];
    else
        hostname = "localhost";
    try {
        InetAddress ia = InetAddress.getByName(hostname);
        DatagramSocket datasocket = new DatagramSocket();
        BufferedReader stdinp = new BufferedReader(
                                    new InputStreamReader(System.in));
        while (true) {
            try {
                String echoline = stdinp.readLine();
                if (echoline.equals("done")) break;
                byte[] buffer = new byte[echoline.length()];
                buffer = echoline.getBytes();
                sPacket = new DatagramPacket(buffer, buffer.length, ia, port);
                datasocket.send(sPacket);
                byte[] rbuffer = new byte[len];
                rPacket = new DatagramPacket(rbuffer, rbuffer.length);
                datasocket.receive(rPacket);
                String retstring = new String(rPacket.getData());
                System.out.println(retstring);
            } catch (IOException e) {
                System.err.println(e);
            }
        } // while
    } catch (UnknownHostException e) {
        System.err.println(e);
    } catch (SocketException se) {
        System.err.println(se);
    }
}  // end main
}
