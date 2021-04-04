package Server;

public class State {
    private String start;
    private String end;
    private int status; //0:normal 1:Failure 2:Failure? 3:Overload
    private int no_reply;
    private int reply;

    public State(String start, int status){
        this.start = start;
        this.status = status;
        this.no_reply = 0;
        this.reply = 0;
    }

    public State(String start, int status, int reply){
        this.start = start;
        this.status = status;
        this.reply = reply;
    }

    public String get_start(){
        return this.start;
    }

    public String get_end(){
        return this.end;
    }

    public int get_status(){
        return this.status;
    }

    public int get_noreply(){
        return this.no_reply;
    }

    public int get_reply(){
        return this.reply;
    }

    public void set_end(String end){
        this.end = end;
    }

    public void set_status(int status){
        this.status = status;
    }

    public void inc_noreply(){
        this.no_reply++;
    }

    public void inc_reply(){
        this.reply++;
    }

    public void reset_noreply(){
        this.no_reply = 0;
    }

    public void reset_reply(){
        this.reply = 0;
    }
    
}
