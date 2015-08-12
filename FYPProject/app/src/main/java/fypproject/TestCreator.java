package fypproject;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import fypproject.Entity.Courier;
import fypproject.Entity.Item;
import fypproject.Entity.Task;

/**
 * Created by Arnold on 8/9/2015.
 */
//TODO: TEST CLASS
public class TestCreator {

    public static Courier createTestCourier(){
        Courier courier = new Courier(1,1,"Arnold Lee", "arnold.lee.2013", "98765446", "Hougang", "no_remarks", "company", "2015-08-18T00:00:00");
        return courier;
    }

    public static ArrayList<Task> createTestTasks1(){
        ArrayList<Task> taskList = new ArrayList<Task>();
        ArrayList<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(1,1,1,2,"Apples", "dimen", "image", "nfctag", "barcode", "remarks", null));
        itemList.add(new Item(2,1,1,2,"Oranges", "dimen", "image", "nfctag", "barcode", "remarks", null));

        taskList.add(new Task(1,1,1,1,"Prof. Lee Yeow Leong", "78945612", "startlocation", "School of Information Systems Level 4 \nS123456", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(2,1,1,1,"Mr. He Wei", "45682365", "startlocation", "SimTech NTU \nS123456", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(3,1,1,1,"Prof. Ben Gan", "45688962", "startlocation", "1 Victoria Street. Singapore Management University \nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(4,1,1,1,"Mr. Lim", "45688962", "startlocation", "1 Victoria Street. Singapore Management University \nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(5,1,1,1,"Mr. Ong", "45688962", "startlocation", "1 Victoria Street. Singapore Management University \nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));

        return taskList;
    }
    public static ArrayList<Task> createTestTasks2(){
        ArrayList<Task> taskList = new ArrayList<Task>();
        ArrayList<Item> itemList = new ArrayList<Item>();

        taskList.add(new Task(4,1,1,1,"Lilo", "45688962", "startlocation", "Universal Studios Singapore \nS546789", "signature", "OTP", "Completed", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(5,1,1,1,"Stitch", "45688962", "startlocation", "Planet from another Universe \nS546789", "signature", "OTP", "Completed", "remarks", null, null, null, null, null, itemList));
        return taskList;
    }

    public static ArrayList<Item> createTestItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(1,1,1,2,"Honda", "dimen", "image", "null", "barcode", "remarks", null));
        itemList.add(new Item(2,1,1,2,"Toyota", "dimen", "image", "TAG002", "barcode", "remarks", null));
        itemList.add(new Item(3,1,1,2,"Mercedes", "dimen", "image", "TAG003", "barcode", "remarks", null));
        itemList.add(new Item(4,1,1,2,"BMW", "dimen", "image", "TAG004", "barcode", "remarks", null));
        itemList.add(new Item(5,1,1,2,"Audi", "dimen", "image", "TAG005", "barcode", "remarks", null));

        return itemList;
    }

    public static ArrayList<LatLng> getTestLocations(){
        ArrayList<LatLng> list = new ArrayList<>();

        list.add(new LatLng(1.296568, 103.852118)); //smu
        list.add(new LatLng(1.34831, 103.683135)); //ntu
        list.add(new LatLng(1.340374, 103.963195)); //sutd
        list.add(new LatLng(1.296643, 103.776394)); //nus
        list.add(new LatLng(1.329471, 103.776137)); //sim

        return list;
    }


}
