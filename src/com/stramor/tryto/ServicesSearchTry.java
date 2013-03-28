package com.stramor.tryto;

import javax.bluetooth.*;
import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;
import java.util.logging.Logger;


public class ServicesSearchTry {
  public static final Vector serviceFound = new Vector();

  public static void main(String[] args) throws IOException, InterruptedException {
    BlueFind.main(null);
    serviceFound.clear();

    final Object serviceSearchCompletedEvent = new Object();
    DiscoveryListener listener = new DiscoveryListener() {
      @Override
      public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void inquiryCompleted(int discType) {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        //To change body of implemented methods use File | Settings | File Templates.
        for (int i = 0; i < servRecord.length; i++) {
          String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
          if (url == null) {continue;}
          serviceFound.add(url);
          DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
          Logger.getAnonymousLogger().info("" + servRecord[i]);
          if (serviceName != null) {
            System.out.println("service " + serviceName.getValue() + " found " + url);
          } else {
            System.out.println("service found " + url);
          }
        }
      }

      @Override
      public void serviceSearchCompleted(int transID, int respCode) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("service search completed!");
        synchronized (serviceSearchCompletedEvent) {
          serviceSearchCompletedEvent.notifyAll();
        }
      }
    };

    UUID[] searchUuidSet = new UUID[] { new UUID(0x003), new UUID(0x1101), new UUID(0x1002)};
    int[] attrIDs =null;

    for (Enumeration en = BlueFind.devices.elements(); en.hasMoreElements();){
      RemoteDevice btDevice = (RemoteDevice)en.nextElement();

      synchronized (serviceSearchCompletedEvent) {
        System.out.println("Search services on " + btDevice.getBluetoothAddress() + " " + btDevice.getFriendlyName(false));
        LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
      }
    }
  }
}
