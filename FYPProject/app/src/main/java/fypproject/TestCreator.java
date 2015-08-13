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
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Item> itemList = createTestItems();

        taskList.add(new Task(1,1,1,1, 1.296568, 103.852118, 1.36442 , 103.991531, "Aaron", "78945612", "startlocation", "Singapore Management University\nS123456", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(2,1,1,1, 1.34831, 103.683135, 1.36442, 103.991531, "Benjamin", "45682365", "startlocation", "Nanyang Technological University\nS123456", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(3,1,1,1, 1.340374, 103.963195, 1.36442 , 103.991531,"Charlie", "45688962", "startlocation", "Singapore University of Technology & Design\nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(4,1,1,1, 1.296643, 103.776394, 1.36442 , 103.991531,"David", "45688962", "startlocation", "National University of Singapore\nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(5,1,1,1, 1.329471, 103.776137, 1.36442, 103.991531,"Ethan", "45688962", "startlocation", "Singapore Institute of Management\nS546789", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList));

        return taskList;
    }
    public static ArrayList<Task> createTestTasks2(){
        ArrayList<Task> taskList = new ArrayList<Task>();
        ArrayList<Item> itemList = new ArrayList<Item>();

        taskList.add(new Task(4,1,1,1,1.296568, 103.852118, 1.36442 , 103.991531,"Lilo", "45688962", "startlocation", "Universal Studios Singapore \nS546789", "signature", "OTP", "Completed", "remarks", null, null, null, null, null, itemList));
        taskList.add(new Task(5,1,1,1,1.296568, 103.852118, 1.36442 , 103.991531,"Stitch", "45688962", "startlocation", "Planet from another Universe \nS546789", "signature", "OTP", "Completed", "remarks", null, null, null, null, null, itemList));
        return taskList;
    }

    public static ArrayList<Item> createTestItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(1,1,1,2,"Apples", "dimen", "image", "TAG001", "barcode", "remarks", null));
        itemList.add(new Item(2,1,1,2,"Bananas", "dimen", "image", "TAG002", "barcode", "remarks", null));
        itemList.add(new Item(3,1,1,2,"Cucumber", "dimen", "image", "TAG003", "barcode", "remarks", null));
        itemList.add(new Item(4,1,1,2,"Dragonfruit", "dimen", "image", "TAG004", "barcode", "remarks", null));
        itemList.add(new Item(5,1,1,2,"Eggs", "dimen", "image", "TAG005", "barcode", "remarks", null));

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
