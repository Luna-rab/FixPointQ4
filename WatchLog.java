import Server.Server;
import Server.State;
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
        try {
            List<String> lines = Files.readAllLines(input);
            Map<String,Server> servers = new HashMap<String,Server>();
            for(String strlog: lines){
                Log log = new Log(strlog);
                String key = log.get_address10();
                if(servers.containsKey(key)){
                    servers.get(key).add_log(log);
                }else{
                    servers.put(key, new Server(log.get_address2(), log.get_prefix(), N, m, t));
                    servers.get(key).add_log(log);
                }
            }
            for(String key: servers.keySet()){
                Server server = servers.get(key);
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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
