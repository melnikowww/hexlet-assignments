package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;
    public Disconnected(TcpConnection tcp) {
        this.tcpConnection = tcp;
    }
    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        this.tcpConnection.setConnection(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Already disconnected!");
    }

    @Override
    public void write(String data) {
        System.out.println("Error: need to connect!");
    }
}
// END
