import Server.Server;
import Server.State;
import Server.Subnet;
import Log.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class WatchLog {
    public static void main(String[] args){
        Path input = Paths.get(args[0]);
        int N = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        int t = Integer.parseInt(args[3]);
        Log l = new Log();
        try {
            List<String> lines = Files.readAllLines(input);
            Map<String,Subnet> subnets = new HashMap<String,Subnet>();
            for(String strlog: lines){
                Log log = new Log(strlog);
                String snkey = l.ipv4_2to10(log.get_subnet());
                if(subnets.containsKey(snkey)){
                    subnets.get(snkey).add_log(log);
                }else{
                    subnets.put(snkey, new Subnet(log.get_subnet(), N, m,t));
                    subnets.get(snkey).add_log(log);
                }
            }
            for(String snkey: subnets.keySet()){
                Subnet subnet = subnets.get(snkey);
                Map<String,Server> servers = subnet.get_servers();
                for(State state: subnet.get_states()){
                    String address = subnet.get_address10();
                    String start = state.get_start();
                    String end = state.get_end() != null?state.get_end():"now";
                    switch(state.get_status()){
                        case 1:
                            System.out.println(address+":"+start+"-"+end+":SubnetFailure");
                            break;
                    }
                }
                for(String svkey: servers.keySet()){
                    Server server = servers.get(svkey);
                    for(State state: server.get_states()){
                        String address = server.get_address10()+"/"+server.get_prefix();
                        String start = state.get_start();
                        String end = state.get_end() != null?state.get_end():"now";
                        switch(state.get_status()){
                            case 1:
                                System.out.println(address+":"+start+"-"+end+":ServerFailure");
                                break;
                            case 3:
                                System.out.println(address+":"+start+"-"+end+":ServerOverload");
                                break;
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
