package Log;

import java.util.regex.Pattern;

public class Log {
    private String date;
    private long address;
    private int prefix;
    private int ping;

    public Log(){}
    public Log(String log){
        String[] divlog = log.split(",");
        this.date = divlog[0];
        this.address = ipv4_10to2(divlog[1].split("/")[0]);
        this.prefix = Integer.parseInt(divlog[1].split("/")[1]);
        if(divlog[2].equals("-")){
            this.ping = -1;
        }else{
            this.ping = Integer.parseInt(divlog[2]);
        }
    }

    public String ipv4_2to10(long add2){
        long[] add10_int = new long[4];
        for(int i=0;i<4;i++){
            add10_int[3-i] = add2 % (long)256;
            add2 /= (long)256;
        }
        return add10_int[0] + "." + add10_int[1] + "." + add10_int[2] + "." + add10_int[3];
    }

    public long ipv4_10to2(String add10){
        long add2 = 0;
        String[] add10_str = add10.split(Pattern.quote("."));
        for(int i=0;i<4;i++){
            add2 += Long.parseLong(add10_str[3-i]) * (long)Math.pow(2, 8*i);
        }
        return add2;
    }

    public String get_date(){
        return this.date;
    }

    public long get_address2(){
        return this.address;
    }

    public String get_address10(){
        return ipv4_2to10(this.address);
    }

    public int get_ping(){
        return this.ping;
    }

    public int get_prefix(){
        return this.prefix;
    }
}
