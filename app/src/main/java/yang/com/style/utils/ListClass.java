package yang.com.style.utils;

import java.util.ArrayList;

import yang.com.style.R;
import yang.com.style.main.ImageName;

/**
 * Created by Admin on 31-07-2017.
 */
public class ListClass {

    public static ArrayList<Integer> HairList ;
    public static ArrayList<Integer> Beard;
    public static ArrayList<ImageName> CategoryList =new ArrayList<>() ;
    public static ArrayList<Integer> hatList ;
    private static ArrayList<Integer> glassList;
    private static ArrayList<Integer> FunList;


//    public static ArrayList<ImageName>

    public static ArrayList<Integer> hairList(){
        if (HairList!=null){
            HairList=null;
        }
        HairList=new ArrayList<>();

         HairList.add(R.drawable.hair01);
         HairList.add(R.drawable.hair02);
         HairList.add(R.drawable.hair03);
         HairList.add(R.drawable.hair04);
         HairList.add(R.drawable.hair05);
         HairList.add(R.drawable.hair06);
         HairList.add(R.drawable.hair07);
         HairList.add(R.drawable.hair08);
         HairList.add(R.drawable.hair09);
         HairList.add(R.drawable.hair10);
         HairList.add(R.drawable.hair11);
         HairList.add(R.drawable.hair12);
         HairList.add(R.drawable.hair13);
         HairList.add(R.drawable.hair14);
         HairList.add(R.drawable.hair15);
         HairList.add(R.drawable.hair16);
         HairList.add(R.drawable.hair17);
         HairList.add(R.drawable.hair18);
         HairList.add(R.drawable.hair19);
         HairList.add(R.drawable.hair20);
         HairList.add(R.drawable.hair21);
         HairList.add(R.drawable.hair22);
         HairList.add(R.drawable.hair23);
         HairList.add(R.drawable.hair24);
         HairList.add(R.drawable.hair25);
         HairList.add(R.drawable.hair26);
         HairList.add(R.drawable.hair27);
         HairList.add(R.drawable.hair28);
         HairList.add(R.drawable.hair29);
         HairList.add(R.drawable.hair30);
         HairList.add(R.drawable.hair31);
         HairList.add(R.drawable.hair32);
         HairList.add(R.drawable.hair33);
         HairList.add(R.drawable.hair34);
         HairList.add(R.drawable.hair35);
         HairList.add(R.drawable.hair36);
         HairList.add(R.drawable.hair37);
         HairList.add(R.drawable.hair38);
//         HairList.add(R.drawable.hair39);
         HairList.add(R.drawable.hair40);

         return HairList;
     }

    public static  ArrayList<Integer>  HatLists(){
        hatList = new ArrayList<Integer>();
        
        hatList.add(R.drawable.hat1);
        hatList.add(R.drawable.hat2);
        hatList.add(R.drawable.hat3);
        hatList.add(R.drawable.hat4);
        hatList.add(R.drawable.hat5);
        hatList.add(R.drawable.hat6);
        hatList.add(R.drawable.hat7);
        hatList.add(R.drawable.hat8);
        hatList.add(R.drawable.hat9);
        hatList.add(R.drawable.hat10);
        hatList.add(R.drawable.hat11);
        hatList.add(R.drawable.hat12);
        hatList.add(R.drawable.hat13);
        hatList.add(R.drawable.hat14);
        hatList.add(R.drawable.hat15);
        hatList.add(R.drawable.hat16);
        hatList.add(R.drawable.hat17);
        hatList.add(R.drawable.hat18);
        hatList.add(R.drawable.hat19);
        hatList.add(R.drawable.hat20);

        return hatList;
    }
    public static  ArrayList<Integer>  FunLists(){
        FunList = new ArrayList<Integer>();

        FunList.add(R.drawable.fun01);
        FunList.add(R.drawable.thug_life_cigarette_smoke);
        FunList.add(R.drawable._logothug_life_sticker);
        FunList.add(R.drawable.fun02);
        FunList.add(R.drawable.fun03);
        FunList.add(R.drawable.fun04);
        FunList.add(R.drawable.fun05);
        FunList.add(R.drawable.fun06);
        FunList.add(R.drawable.fun07);
        FunList.add(R.drawable.fun08);
        FunList.add(R.drawable.fun09);
        FunList.add(R.drawable.fun10);
        FunList.add(R.drawable.fun11);
        FunList.add(R.drawable.fun12);
        FunList.add(R.drawable.fun13);
        FunList.add(R.drawable.fun16);
        FunList.add(R.drawable.fun17);
        FunList.add(R.drawable.fun18);
        FunList.add(R.drawable.fun19);
        FunList.add(R.drawable.fun20);
        FunList.add(R.drawable.fun21);
        FunList.add(R.drawable.fun22);
        FunList.add(R.drawable.fun23);
        FunList.add(R.drawable.fun24);
        FunList.add(R.drawable.fun25);
        FunList.add(R.drawable.fun26);
        FunList.add(R.drawable.fun27);
        FunList.add(R.drawable.fun28);

        return FunList;
    }

    public static  ArrayList<Integer> GlassesLists(){
        glassList = new ArrayList<Integer>();

        glassList.add(R.drawable.thug_life_sunglasses1);
        glassList.add(R.drawable.glasses20);
        glassList.add(R.drawable.glasses2);
        glassList.add(R.drawable.glasses3);
        glassList.add(R.drawable.glasses4);
        glassList.add(R.drawable.glasses5);
        glassList.add(R.drawable.glasses6);
        glassList.add(R.drawable.glasses7);
        glassList.add(R.drawable.glasses8);
        glassList.add(R.drawable.glasses9);
        glassList.add(R.drawable.glasses10);
        glassList.add(R.drawable.glasses11);
        glassList.add(R.drawable.glasses12);
        glassList.add(R.drawable.glasses13);
        glassList.add(R.drawable.glasses14);
        glassList.add(R.drawable.glasses15);
        glassList.add(R.drawable.glasses16);
        glassList.add(R.drawable.glasses17);
        glassList.add(R.drawable.glasses18);
        glassList.add(R.drawable.glasses19);
//        glassList.add(R.drawable.glasses1);

        return glassList;
    }
    public static ArrayList<Integer> BeardList() {
        Beard = new ArrayList<Integer>();

        Beard.add(R.drawable.beard10);
        Beard.add(R.drawable.beard2);
        Beard.add(R.drawable.beard3);
        Beard.add(R.drawable.beard4);
        Beard.add(R.drawable.beard5);
        Beard.add(R.drawable.beard6);
        Beard.add(R.drawable.beard7);
        Beard.add(R.drawable.beard8);
        Beard.add(R.drawable.beard9);
        Beard.add(R.drawable.beard1);
        Beard.add(R.drawable.beard11);
        Beard.add(R.drawable.beard12);
        Beard.add(R.drawable.beard13);
        Beard.add(R.drawable.beard14);
        Beard.add(R.drawable.beard15);
        Beard.add(R.drawable.beard16);
        Beard.add(R.drawable.beard17);
        Beard.add(R.drawable.beard18);
        Beard.add(R.drawable.beard19);
        Beard.add(R.drawable.beard20);
        Beard.add(R.drawable.beard21);
        Beard.add(R.drawable.beard22);
        Beard.add(R.drawable.beard23);
        Beard.add(R.drawable.beard24);
        Beard.add(R.drawable.beard25);
        Beard.add(R.drawable.beard26);
        Beard.add(R.drawable.beard27);
        Beard.add(R.drawable.beard28);
        Beard.add(R.drawable.beard29);
        Beard.add(R.drawable.beard30);
        Beard.add(R.drawable.beard31);
        Beard.add(R.drawable.beard32);
        Beard.add(R.drawable.beard33);
        Beard.add(R.drawable.beard34);
        Beard.add(R.drawable.beard35);
        Beard.add(R.drawable.beard36);
        Beard.add(R.drawable.beard37);
        Beard.add(R.drawable.beard38);
        Beard.add(R.drawable.beard39);
        Beard.add(R.drawable.beard40);
        Beard.add(R.drawable.beard41);
        Beard.add(R.drawable.beard42);
        Beard.add(R.drawable.beard43);
        Beard.add(R.drawable.beard44);
        Beard.add(R.drawable.beard45);
        return Beard;
    }

    /**
     * @author Admin
     * Hat
     */

    public static ArrayList<ImageName> CategoryList() {

        if (CategoryList!=null){
            CategoryList = new ArrayList<>();
        }

        ImageName glasses = new ImageName();
        glasses.setName("Glasses");
        glasses.setImagePath(R.drawable.glasses6);

        CategoryList.add(glasses);


        ImageName fun = new ImageName();
        fun.setName("Fun");
        fun.setImagePath(R.drawable.fun01);

        CategoryList.add(fun);


        ImageName hair = new ImageName();
        hair.setName("Hairs");
        hair.setImagePath(R.drawable.hair01);

        CategoryList.add(hair);

        ImageName beard = new ImageName();
        beard.setName("Beards");
        beard.setImagePath(R.drawable.beard10);

        CategoryList.add(beard);

        ImageName hat = new ImageName();
        hat.setName("Hats");
        hat.setImagePath(R.drawable.hat1);

        CategoryList.add(hat);


        return CategoryList;
    }

}