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
        try {
            List<String> lines = Files.readAllLines(input);
            Map<String,Server> servers = new HashMap<String,Server>();
            for(String strlog: lines){
                Log log = new Log(strlog);
                String key = log.get_address10();
                if(servers.containsKey(key)){
                    servers.get(key).add_log(log);
                }else{
                    servers.put(key, new Server(log.get_address2(), log.get_prefix()));
                    servers.get(key).add_log(log);
                }
            }
            for(String key: servers.keySet()){
                Server server = servers.get(key);
                for(State state: server.get_states()){
                    switch(state.get_status()){
                        case 1:
                            String address = server.get_address10()+"/"+server.get_prefix();
                            String start = state.get_start();
                            String end = state.get_end() != null?state.get_end():"now";
                            System.out.println(address+":"+start+"-"+end+":ServerFailure");
                            break;
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
