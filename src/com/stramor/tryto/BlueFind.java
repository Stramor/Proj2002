package com.stramor.tryto;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;
import com.intel.bluetooth.*;

public class BlueFind {
  public static final Vector devicesDiscovered = new Vector();

  public static void main(String[] args) throws IOException, InterruptedException {
    final Object inquiryCompletedEvent = new Object();
    devicesDiscovered.clear();
    DiscoveryListener listener = new DiscoveryListener() {
      @Override
      public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Device "+ btDevice.getBluetoothAddress() + " found");
        try {
          System.out.println("   name " + btDevice.getFriendlyName(false));
        } catch (IOException cantGetDeviceName){}
      }

      @Override
      public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void serviceSearchCompleted(int transID, int respCode) {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void inquiryCompleted(int discType) {
        //To change body of implemented methods use File | Settings | File Templates.
      }
    };

    synchronized (inquiryCompletedEvent) {
      boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
      if (started) {
        System.out.println("wait for device inquiry to complete...");
        inquiryCompletedEvent.wait();
        System.out.println(devicesDiscovered.size() + " device(s) found");
      }
    }

  }

}
