package Server;

public class State {
    private String start;
    private String end;
    private int status; //0:normal 1:Failure 2:Failure?
    private int no_reply;

    public State(String start, int status){
        this.start = start;
        this.status = status;
        this.no_reply = 0;
    }

    public State(String start, int status, int noreply){
        this.start = start;
        this.status = status;
        this.no_reply = noreply;
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

    public void set_end(String end){
        this.end = end;
    }

    public void set_status(int status){
        this.status = status;
    }

    public void inc_noreply(){
        this.no_reply++;
    }

    public void reset_noreply(){
        this.no_reply = 0;
    }
    
}
