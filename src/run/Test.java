package run;

import bussiness.config.IOFile;
import bussiness.entity.Product;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        Food f1 = new Food(1,"Đá Me",100,"ngon");
//        Food f2 = new Food(2,"Pịa",110,"ngon");
//        Food f3 = new Food(3,"Bánh Mì",90,"ngon");
//        Food f4 = new Food(4,"Gà",50,"ngon");
//        Food f5 = new Food(5,"Hàu",150,"ngon");
//        List<Food> f = Arrays.asList(f1,f2,f3,f4,f5);
//        IOFile.writeToFile(IOFile.FOOD_PATH,f);
//        List<Order> list = IOFile.readFromFile(IOFile.ORDER_PATH);
//        for (Order o : list) {
//            System.out.println(o);
//        }

//        Catalog calog1 = new Catalog(1, "Sinh tố", "Trái cây, đường, đá, sữa");
//        Catalog calog2 = new Catalog(2, "Nước ép", "Ép nước từ trái câu tưới");
//        Catalog calog3 = new Catalog(3, "Hồng trà", "Trà, đường, đá, trân châu");
//        Catalog calog4 = new Catalog(4, "Trà sữa", "Trà và sữa, đá, trân châu");
//
//        List<Catalog> listDemoCatalogs = Arrays.asList(calog1, calog2, calog3, calog4);
//        IOFile.writeToFile(IOFile.CATALOGS_PATH, listDemoCatalogs);


        /*------------------------------------------------------------------------------------------*/
        Product prod1 = new Product(1, 1, "Sinh Tố Dâu Chuối", "Dâu, Chuối, Sữa Tươi", 45000, 10);
        Product prod2 = new Product(2, 1, "Sinh Tố Chuối Đặc Biệt", "Chuối, Bơ đậu phộng, Sô cô la", 47000, 10);
        Product prod3 = new Product(3, 1, "Sinh tố Bơ Chuối", "Bơ, Chuối, Sữa tươi", 49000, 10);

        Product prod4 = new Product(4, 2, "Nước Ép Dâu Táo", "Dâu, Chuối, Táo", 42000, 10);
        Product prod5 = new Product(5, 2, "Nước Ép Táo Thơm ", "Dâu, Thơm, Táo", 44000, 10);
        Product prod6 = new Product(6, 2, "Nước ép Táo Ổi", "Táo, Ổi", 46000, 10);

        Product prod7 = new Product(7, 3, "Hồng Trà Lài", "Trà lài, đường, đá, trân châu", 29000, 9);
        Product prod8 = new Product(8, 3, "Hồng Trà Bưởi ", "Trà bưởi, đường, đá, trân châu", 29000, 9);
        Product prod9 = new Product(9, 3, "Hồng Trà Mật Ong", "Trà, mật ong, đường, đá, trân châu", 29000, 9);

        Product prod10 = new Product(10, 4, "Trà Sữa Bạc Hà", "Trà sữa vị bạc hà, đường, đá, trân châu", 30000, 9);
        Product prod11 = new Product(11, 4, "Trà Sữa Mật Ong", "Trà sữa, mật ong, đường, đá, trân châu", 43000, 10);
        Product prod12 = new Product(12, 4, "Trà Sữa Truyền Thống", "Trà sữa, đường, đá, trân châu", 45000, 10);

        /*---*/

        Product prod13 = new Product(13, 1, "Sinh tố Bơ", "Bơ, Sữa đặc, Sữa tươi", 45000, 10);
        Product prod14 = new Product(14, 1, "Sinh tố Bơ Espresso", "Bơ, Espresso, Sữa tươi", 47000, 10);
        Product prod15 = new Product(15, 1, "Sinh tố Bơ Chuối Dâu", "Bơ, Chuối, Dâu, Sữa tươi", 49000, 10);

        Product prod16 = new Product(16, 2, "Nước Ép Táo Chanh Dây", "Chanh Dây, Táo", 42000, 10);
        Product prod17 = new Product(17, 2, "Nước Ép Thơm Chanh Dây ", "Dứa, Chanh Dây", 44000, 10);
        Product prod18 = new Product(18, 2, "Nước Ép Táo Cà Rốt", "Dứa, Táo, Cà Rốt", 46000, 10);

        Product prod19 = new Product(19, 5, "Lục Trà Yakult", "Trà xanh, sữa yakult đường, đá, trân châu", 29000, 9);
        Product prod20 = new Product(20, 5, "Lục Trà Mật Ong ", "Trà xanh, mật ong, đường, đá, trân châu", 29000, 9);
        Product prod21 = new Product(21, 5, "Lục trà Chanh Dây", "Trà xanh vị chanh dây, đường, đá, trân châu", 29000, 9);

        Product prod22 = new Product(22, 6, "Cafe Đen", "Cafe đen đậm vị, đường, đá", 30000, 9);
        Product prod23 = new Product(23, 6, "Cafe Sữa Tươi", "Cafe đen và sữa tươi, đường, đá", 43000, 10);
        Product prod24 = new Product(24, 6, "Bạc Xỉu", "Cafe đen và sữa đặc (nóng/lạnh)", 45000, 10);

        /*---*/

        Product prod25 = new Product(25, 1, "Sinh tố Xoài Dừa", "Xoài, Chanh dây, Dừa", 45000, 10);
        Product prod26 = new Product(26, 1, "Sinh tố Xoài Dâu", "Xoài, Dâu, Sữa chua, Sữa tươi", 47000, 10);
        Product prod27 = new Product(27, 1, "Sinh Tố Xoài Chuối", "Xoài, Chuối, Sữa tươi", 49000, 10);

        Product prod28 = new Product(28, 2, "Nước Ép Cam Đặc Biệt", "Cam, Cà rốt, Dưa leo, Chanh, Bạc hà", 42000, 10);
        Product prod29 = new Product(29, 2, "Nước Ép Thơm Cà Rốt ", "Dứa, Táo, Cà Rốt", 44000, 10);
        Product prod30 = new Product(30, 2, "Nước Ép Táo Cam", "Cam, Chanh dây, Táo", 46000, 10);

        Product prod31 = new Product(31, 3, "Oolong Mật Ong", "Trà Oolong, mật ông, đường, đá, trân châu", 29000, 9);
        Product prod32 = new Product(32, 3, "Hồng Trà Chanh ", "Trà, chanh đường, đá, trân châu", 29000, 9);
        Product prod33 = new Product(33, 3, "Hồng Trà Việt Quất", "Trà, việt quất, đường, đá, trân châu", 29000, 9);

        Product prod34 = new Product(34, 4, "Trà Sữa Thái Xanh", "Trà sữa thái, đường, đá, trân châu", 30000, 9);
        Product prod35 = new Product(35, 4, "Trà Sữa Thái Đỏ", "Trà sữa thái, đường, đá, trân châu", 43000, 10);
        Product prod36 = new Product(36, 4, "Sữa Tươi Đường Đen", "Sữa tươi trân châu đường đen", 45000, 10);

        /*---*/

//        Product prod37 = new Product(37, 1, "Mangonificient", "Xoài, Chanh dây, Dừa", 45000, 10);
//        Product prod38 = new Product(38, 1, "Undercut", "Xoài, Dâu, Sữa chua, Sữa tươi", 47000, 10);
//        Product prod39 = new Product(39, 1, "Earthshaker", "Xoài, Chuối, Sữa tươi", 49000, 10);
//
//        Product prod40 = new Product(40, 2, "Vegie Depot", "Cam, Cà rốt, Dưa leo, Chanh, Bạc hà", 42000, 10);
//        Product prod41 = new Product(41, 2, "Juice Lover ", "Dứa, Táo, Cà Rốt", 44000, 10);
//        Product prod42 = new Product(42, 2, "Balance Control", "Cam, Chanh dây, Táo", 46000, 10);
//
//        Product prod43 = new Product(43, 3, "Hồng Trà Lài", "Trà Lài, đường, đá, trân châu", 29000, 9);
//        Product prod44 = new Product(44, 3, "Hồng Trà Bưởi ", "Trà Bưởi, đường, đá, trân châu", 29000, 9);
//        Product prod45 = new Product(45, 3, "Hồng Trà Mật Ong", "Trà, Mật ong, đường, đá, trân châu", 29000, 9);
//
//        Product prod46 = new Product(46, 4, "Trà Sữa Bạc hà", "Trà, sữa, Bạc hà đường, đá, trân châu", 30000, 9);
//        Product prod47 = new Product(47, 5, "Passionfruit Green Tea", "Lục trà Chanh Dây", 43000, 10);
//        Product prod48 = new Product(48, 5, "Strawberry Orange Green Tea", "Lục Trà Dâu Cam", 45000, 10);


//        List<Product> listFormFile = new LinkedList<>(IOFile.readFromFile(IOFile.PRODUCTS_PATH));
//        List<Product> listDemoProducts = new LinkedList<>(Arrays.asList(
//                prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12,
//
//                prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20, prod21, prod22, prod23, prod24,
//
//                prod25, prod26, prod27, prod28, prod29, prod30, prod31, prod32, prod33, prod34, prod35, prod36
//                ));
//        listFormFile.addAll(listDemoProducts);
//        IOFile.writeToFile(IOFile.PRODUCTS_PATH, listFormFile);

    }
}
