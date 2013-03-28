package com.stramor.tryto;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;

public class BlueFind {
  public static final Vector devices = new Vector();

  public static void main(String[] args) throws IOException, InterruptedException {
    final Object inquiryCompletedEvent = new Object();
    devices.clear();
    DiscoveryListener listener = new DiscoveryListener() {
      @Override
      public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Device "+ btDevice.getBluetoothAddress() + " found");
        try {
          System.out.println("   name " + btDevice.getFriendlyName(false));
          devices.add(btDevice);
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
        System.out.println("Device Inquiry completed!");
        synchronized (inquiryCompletedEvent) {
          inquiryCompletedEvent.notifyAll();
        }
      }
    };

    synchronized (inquiryCompletedEvent) {
      boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
      if (started) {
        System.out.println("wait for device inquiry to complete...");
        inquiryCompletedEvent.wait();
        System.out.println(devices.size() + " device(s) found");
      }
    }
//    TikTak tikTak = new TikTak();
//   (new Thread(tikTak)).start();
//    System.out.println("OPA-OPA-OPA-PA");
//    Thread.sleep(1000);
//    System.out.println("OPA-OPA-OPA-PA");
//    tikTak.setState(false);


  }

  public static class TikTak implements Runnable{
    public void setState(boolean state) {
      this.state = state;
    }

    boolean state = true;
    @Override
    public void run() {
      int i =0;
      while(state){
        System.out.println(""+i);
        i++;
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
      }
    }

  }
}
