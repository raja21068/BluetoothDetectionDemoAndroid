
import java.util.Collection;
import java.util.Vector;
import se.nicklasgavelin.bluetooth.Bluetooth;
import se.nicklasgavelin.bluetooth.BluetoothDevice;
import se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener;

public class BlueToothManager extends Thread implements BluetoothDiscoveryListener{
    public Bluetooth bt;
    Vector v=new Vector();
    public void run(){
        bt = new Bluetooth( this, Bluetooth.SERIAL_COM );
        bt.discover();
    }
    public Vector getResults(){
        return v;
    }
    @Override
    public void deviceSearchCompleted(Collection<BluetoothDevice> clctn) {
        System.out.println("Search Completed");
        stop();
        Devices.list.setListData(v);
    }

    @Override
    public void deviceDiscovered(BluetoothDevice bd) {
        System.out.println( "Discovered device " + bd.getName());
        v.addElement(bd.getName());
    }

    @Override
    public void deviceSearchFailed(Bluetooth.EVENT event) {
        System.out.println("Failed");
        
    }

    @Override
    public void deviceSearchStarted() {
        System.out.println("Search Started");
        v.removeAllElements();
    }
    
}
