package fypproject;

import java.util.ArrayList;

import fypproject.Entity.Courier;
import fypproject.Entity.Item;
import fypproject.Entity.Task;

/**
 * Created by Arnold on 8/9/2015.
 */
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
        ArrayList<Item> itemList2 = new ArrayList<Item>();

        itemList2.add(new Item(3,2,1,2,"chicken", "dimen", "image", "nfctag", "barcode", "remarks", null));
        itemList2.add(new Item(4,2,1,2,"duck", "dimen", "image", "nfctag", "barcode", "remarks", null));

        taskList.add(new Task(2,1,1,1,"receiver1", "receivernum", "startlocation", "endLocation", "signature", "OTP", "Ongoing", "remarks", null, null, null, null, null, itemList2));
        return taskList;
    }

    public static ArrayList<Item> createTestItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(3,2,1,2,"chicken", "dimen", "image", "nfctag", "barcode", "remarks", null));
        itemList.add(new Item(4,2,1,2,"duck", "dimen", "image", "nfctag", "barcode", "remarks", null));

        return itemList;
    }





}
