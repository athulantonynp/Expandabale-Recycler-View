package antony.athul.com.expandingrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvExpandable;
    ArrayList<Phone> phones;
    PhonesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvExpandable=(RecyclerView)findViewById(R.id.rv_expandable);
        rvExpandable.setHasFixedSize(true);
        phones=createObject();
        rvExpandable.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter=new PhonesAdapter(phones,this);
        rvExpandable.setAdapter(adapter);
    }

    private ArrayList<Phone> createObject() {

        ArrayList<Phone>phones=new ArrayList<>();
        Phone Android=new Phone();
        Android.setOsName("Android");

        Models anOne=new Models();
        anOne.setPreselect(true);
        anOne.setName("Lenovo A6000");
        Models anTwo=new Models();
        anTwo.setName("Lenovo A7000");
        Models anThree=new Models();
        anThree.setName("Samsung galaxy s8");
        anThree.setExclusive(true);
        ArrayList<Models>AndroidModels=new ArrayList<>();
        AndroidModels.add(0,anOne);
        AndroidModels.add(1,anTwo);
        AndroidModels.add(2,anThree);

        Android.setModels(AndroidModels);

        Phone iOS=new Phone();
        iOS.setOsName("iOS");
        Models iOne=new Models();
        iOne.setName("iPhone 6");
        Models iTwo=new Models();
        iTwo.setName("iPhone 7");
        iTwo.setPreselect(true);
        Models iThree=new Models();
        iThree.setName("iPhone 8");
        ArrayList<Models>iPhoneModels=new ArrayList<>();
        iPhoneModels.add(0,iOne);
        iPhoneModels.add(1,iTwo);
        iPhoneModels.add(2,iThree);

        iOS.setModels(iPhoneModels);

        Phone Windows=new Phone();
        Windows.setOsName("Windows Phone");
        Models wOne=new Models();
        wOne.setName("Lumia 720");
        Models wTwo=new Models();
        wTwo.setName("Microsoft Lumia");
        Models wThree=new Models();
        wThree.setPreselect(true);
        wThree.setName("Xolo Windows Phone");
        ArrayList<Models>windowsModels=new ArrayList<>();
        windowsModels.add(0,wOne);
        windowsModels.add(1,wTwo);
        windowsModels.add(2,wThree);
        Windows.setModels(windowsModels);

        phones.add(0,Android);
        phones.add(1,iOS);
        phones.add(2,Windows);
        return phones;


    }
}
