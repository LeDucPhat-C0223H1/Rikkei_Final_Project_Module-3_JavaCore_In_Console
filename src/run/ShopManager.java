package run;

import bussiness.entity.*;
import bussiness.service.*;
import bussiness.service.impl.*;
import bussiness.util.InputMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShopManager {
    public static final ICustomerService customerService = new CustomerService();
    public static final ICatalogService catalogService = new CatalogService();
    public static final IProductService productService = new ProductService();
    public static ICartService cartService;

//    public static final IOrderService orderService = new OrderService();

    public static final Scanner input = new Scanner(System.in);
    public static Customer currentLogin;
    public static String inputSearch;
    public static long inputId;
    public static Catalog catalogFindById;
    public static Product productFindById;


    public static void main(String[] args) {
        while (true) {
            //System.out.println("\u001B[97m");
            System.out.println();
            System.out.println("\u001B[36m================ SHOP =================\u001B[34m");
            System.out.println("|| 1. Login                          ||");
            System.out.println("|| 2. Register                       ||");
            System.out.println("|| 3. Thoát                          ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 3");
                    break;
            }
        }
    }


    /*====================================Start Login Register===================================*/
    public static void login() {
        System.out.println();
        System.out.println("\u001B[35m==================================================================== LOGIN =====================================================================\u001B[97m");
        System.out.println("Nhập Username: ");
        String userName = input.nextLine();
        System.out.println("Nhập Password: ");
        String password = input.nextLine();

        if (Objects.equals(userName, "admin") && Objects.equals(password, "admin")) {
            // Addmin
            System.out.println("\u001B[35m================================================================================================================================================\u001B[97m");
            admin();

        } else {
            // Người dùng
            Customer customerLogin = customerService.login(userName, password);
            if (customerLogin != null) {

                if (customerLogin.isStatus()) {
                    System.out.println("\u001B[35m                                                              Đăng nhập thành công                                                               ");
                    currentLogin = customerLogin;
                    cartService = new CartService(currentLogin);
                    System.out.println("________________________________________________________ Chào mừng " + currentLogin.getUsername() + " đến với shop ^^ _______________________________________________________\u001B[97m");
                    // Chuyển qua Menu cửa hàng
                    shopping();
                } else {
                    System.err.println("=================================================== Tài khoản này đã bị khóa, mời đăng ký mới! =================================================");
                }
            } else {
                System.err.println("Tên đăng nhập hoặc mật khẩu không đúng, mời nhập lại!");
            }
        }
    }

    public static void register() {
        System.out.println();
        System.out.println("\u001B[35m=================================================================== REGISTER ===================================================================\u001B[97m");

        //--
        System.out.println("1. Nhập Username: ");
        String userName;
        while (true) {
            userName = InputMethods.getString().trim();
            if (customerService.existByUserName(userName)) {
                System.err.println("Tên người dùng đã được sử dụng, mời nhập lại tên khác!");
            } else {
                break;
            }
        }

        //--
        String password, confirmPassword;
        while (true) {
            System.out.println("2. Nhập Password: ");
            password = InputMethods.getString().trim();
            if (password.length() < 6) {
                System.err.println("Password phải từ 6 kí tự trở lên, mời nhập lại!");
            } else {
                System.out.println("3. Xác nhận lại Password: ");
                confirmPassword = InputMethods.getString().trim();
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("\u001B[31mXác nhận lại password không trùng khớp, mời nhập lại!\u001B[97m");
                }
            }
        }

        //--
        System.out.println("4. Nhập Email:  ");
        String email;
        while (true) {
            email = InputMethods.getString().trim();

            if (email.matches(".+@gmail\\.com$")) {
                break;
            } else if (customerService.existByPhone(email)) {
                System.err.println("Email đã được đãng kí, mời nhập lại email khác!");
            } else {
                System.err.println("Nhập sai định dạng Email, mời nhập lại! (Vd: hihi678@gmail.com)");
            }
        }

        //--
        System.out.println("4. Nhập Phone:  ");
        String phone;
        while (true) {
            phone = InputMethods.getString().trim();
            if (phone.matches("^0\\d{9,10}$")) {
                break;
            } else if (customerService.existByEmail(email)) {
                System.err.println("SĐT đã được đãng kí, mời nhập lại số khác!");
            } else {
                System.err.println("Nhập sai định dạng SĐT, mời nhập lại! (Vd: 0912674678)");
            }
        }

        //--
        System.out.println("Nhập Fullname: ");
        String fullName = InputMethods.getString().trim();

        //--
        Customer newCustomer = new Customer(customerService.getNewId(), userName, password, email, fullName, phone);
        customerService.save(newCustomer);
        System.out.println("\u001B[35m______________________________________________________________ Đăng kí thành công ______________________________________________________________\u001B[97m");
        System.out.println();
        login();
    }
    /*=====================================End Login Register====================================*/


    /*========================================Start Admin========================================*/
    public static void admin() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[36m================ Admin ================\u001B[34m");
            System.out.println("|| 1. Quản lý người dùng             ||");
            System.out.println("|| 2. Quản lý danh mục               ||");
            System.out.println("|| 3. Quản lý sản phẩm               ||");
            System.out.println("|| 4. Đăng xuất                      ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    managerUser();
                    break;

                case 2:
                    managerCatalog();
                    break;

                case 3:
                    managerProduct();
                    break;

                case 4:
                    return;

                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 4");
                    break;
            }
        }
    }

    /*------User-----*/
    public static void managerUser() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[36m============ Manager User =============\u001B[34m");
            System.out.println("|| 1. Hiển thị danh sách người dùng  ||");
            System.out.println("|| 2. Tìm kiếm người dùng theo tên   ||");
            System.out.println("|| 3. Block/ Unblock người dùng      ||");
            System.out.println("|| 4. Quay lại                       ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    showListCustomer(customerService.findAll(), " DANH SÁCH NGƯỜI DÙNG ");
                    break;
                case 2:
                    findUserByName();
                    break;
                case 3:
                    toggleUserStatusById();
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 4");
                    break;
            }
        }
    }

    private static void showListCustomer(List<Customer> list, String title) {
        System.out.println("\u001B[33m=================" + title + "====================");
        System.out.printf("\u001B[36m* %-4s | %-14s | %-12s | %-16s %-1s\n",
                " ID",
                " HỌ VÀ TÊN",
                "    SĐT",
                "   TRẠNG THÁI",
                "*");

        for (Customer c : list
        ) {
            System.out.printf("\u001B[97m* %-4s | %-14s | %-12s | %-16s %-1s\n",
                    " " + c.getId(),
                    " " + c.getUsername(),
                    " " + c.getPhone(),
                    " " + (c.isStatus() ? "Đang hoạt động" : "Bị khóa"),
                    "*");
        }
        System.out.println("\u001B[33m===========================================================\u001B[97m");
        /*======================================End Method Reuse========================================*/
    }

    private static void findUserByName() {
        System.out.println("Nhập tên người dùng muốn tìm kiếm: ");
        inputSearch = InputMethods.getString();
        List<Customer> listResultSeacrh = customerService.findByName(inputSearch);
        if (listResultSeacrh.isEmpty()) {
            System.out.println("\u001B[33m================================================================ KHÔNG TÌM THẤY ================================================================\u001B[97m");
        } else {
            showListCustomer(listResultSeacrh, "== KẾT QUẢ TÌM KIẾM ==");
        }

    }

    private static void toggleUserStatusById() {
        System.out.print("Nhập Id người dùng cần thay đổi trạng thái: ");
        inputId = InputMethods.getLong();
        Customer customerFindById = customerService.findById(inputId);
        if (customerFindById != null) {
            //cập nhật lại trạng thái
            customerFindById.setStatus(!customerFindById.isStatus());
            // lưu database lại
            customerService.save(customerFindById);
            System.out.println("\n\u001B[33m  __________ Cập nhật trạng thái người dùng thành công _________ \u001B[95m");
            System.out.println(customerFindById);
        } else {
            System.out.println("\u001B[33m========================================================= KHÔNG TÌM THẤY ID NGƯỜI DÙNG =========================================================\u001B[97m");
        }
    }


    /*-----Catalog----*/
    public static void managerCatalog() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[36m=========== Manager Catalog ===========\u001B[34m");
            System.out.println("|| 1. Hiển thị danh sách danh mục    ||");
            System.out.println("|| 2. Tạo danh mục mới               ||");
            System.out.println("|| 3. Tìm kiếm danh mục theo tên     ||");
            System.out.println("|| 4. Chỉnh sửa thông tin danh mục   ||");
            System.out.println("|| 5. Thay đổi trạng thái danh mục   ||");
            System.out.println("|| 6. Ẩn nhiều danh mục cùng lúc     ||");
            System.out.println("|| 7. Quay lại                       ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    showListCatalog(catalogService.findAll(), " DANH SÁCH DANH MỤC ");
                    break;

                case 2:
                    addNewCatalog();
                    break;

                case 3:
                    findCatalogByName();
                    break;

                case 4:
                    editCatalogById();
                    break;

                case 5:
                    toggleCatalogStatusById();
                    break;

                case 6:
                    hideManyCatalogsById();
                    break;

                case 7:
                    return;

                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 7");
                    break;
            }
        }
    }

    private static void showListCatalog(List<Catalog> list, String title) {
        System.out.println("\u001B[33m===========================" + title + "===========================");
        System.out.printf("\u001B[36m* %-4s | %-14s | %-11s | %-32s %-1s\n",
                " ID",
                " TÊN DANH MỤC ",
                " TRẠNG THÁI",
                "              MÔ TẢ",
                "*");

        for (Catalog c : list
        ) {
            System.out.printf("\u001B[97m* %-4s | %-14s | %-11s | %-32s %-1s\n",
                    " " + c.getCatalogtId(),
                    " " + c.getCatalogName(),
                    " " + (c.isStatus() ? "Hiển thị" : "Bị ẩn"),
                    " " + c.getDescription(),
                    "*");
        }
        System.out.println("\u001B[33m==========================================================================\u001B[97m");
        /*======================================End Method Reuse========================================*/
    }

    private static void addNewCatalog() {
        System.out.println("Nhập tên danh mục mới: ");
        String catologName = InputMethods.getString();
        //
        System.out.println("Nhập mô tả danh mục: ");
        String catologDesc = InputMethods.getString();
        // //
        Catalog newCatalog = new Catalog(catalogService.getNewId(), catologName, catologDesc);
        catalogService.save(newCatalog);
        System.out.println("\nThêm mới danh mục thành công.");
    }

    private static void findCatalogByName() {
        System.out.println("Nhập tên danh mục muốn tìm kiếm: ");
        inputSearch = InputMethods.getString();
        List<Catalog> listResultSeacrh = catalogService.findByName(inputSearch);
        if (listResultSeacrh.isEmpty()) {
            System.out.println("\u001B[33m================================================================ KHÔNG TÌM THẤY ================================================================\u001B[97m");
        } else {
            showListCatalog(listResultSeacrh, "= KẾT QUẢ TÌM KIẾM =");
        }
    }

    private static void editCatalogById() {
        System.out.print("Nhập Id danh mục cần sửa thông tin: ");
        inputId = InputMethods.getLong();
        Catalog catalogEdit = catalogService.findById(inputId);

        if (catalogEdit == null) {
            System.out.println("Danh mục này không tồn tại!");
        } else {
            System.out.println(catalogEdit);
            System.out.println("\nNhập tên mới cho danh mục: ");
            catalogEdit.setCatalogName(InputMethods.getString());
            //
            System.out.println("Nhập mô tả mới cho danh mục: ");
            catalogEdit.setDescription(InputMethods.getString());
            // //
            catalogService.save(catalogEdit);
            System.out.println("\nCập nhật danh mục thành công.");

        }
    }

    private static void toggleCatalogStatusById() {
        System.out.print("Nhập Id danh mục cần thay đổi trạng thái: ");
        inputId = InputMethods.getLong();
        catalogFindById = catalogService.findById(inputId);
        if (catalogFindById != null) {
            //cập nhật lại trạng thái
            catalogFindById.setStatus(!catalogFindById.isStatus());
            // lưu database lại
            catalogService.save(catalogFindById);
            System.out.println("\n\u001B[33m  ___ Cập nhật trạng thái danh mục thành công ___ \u001B[95m");
            System.out.println(catalogFindById);
        } else {
            System.out.println("\u001B[33m========================================================= KHÔNG TÌM THẤY ID DANH MỤC =========================================================\u001B[97m");
        }
    }

    private static void hideManyCatalogsById() {
        System.out.println("Nhập các Id của các danh mục muốn ẩn ");
        System.out.println("hiển thị (ngăn cách nhau bởi dấu phẩy): ");
        String inputStr = InputMethods.getString();

        String[] arrayWords = inputStr.split(",");

        boolean checked = InputMethods.checkInteger(arrayWords);

        if (!checked) {
            System.out.println("\nNhập sai cú pháp yêu cầu, mời nhập lại!");
        } else {
            System.out.println("Đã ẩn hiển thị các danh mục thành công.\n");
            for (String word : arrayWords
            ) {
                long id = Long.parseLong(word);
                //cập nhật lại trạng thái
                catalogFindById = catalogService.findById(id);
                catalogFindById.setStatus(false);
                // lưu database lại
                catalogService.save(catalogFindById);
                System.out.println(catalogFindById);
            }
        }
    }

    /*-----Product----*/
    public static void managerProduct() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[36m=========== Manager Product ===========\u001B[34m");
            System.out.println("|| 1. Hiển thị danh sách sản phẩm    ||");
            System.out.println("|| 2. Thêm mới sản phẩm              ||");
            System.out.println("|| 3. Tìm kiếm sản phẩm theo tên     ||");
            System.out.println("|| 4. Chỉnh sửa thông tin sản phẩm   ||");
            System.out.println("|| 5. Thay đổi trạng thái sản phẩm   ||");
            System.out.println("|| 6. Quay lại                       ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    printListProductForAdmin(productService.findAll(), " DANH SÁCH SẢN PHẨM");
                    break;

                case 2:
                    addNewProduct();
                    break;

                case 3:
                    findProductByName();
                    break;

                case 4:
                    editProductById();
                    break;

                case 5:
                    toggleProducStatustById();
                    break;

                case 6:
                    return;

                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 6");
                    break;
            }
        }
    }

    private static void addNewProduct() {
        System.out.print("Nhập số lượng sản phẩm cần nhập: ");
        int quantityAddProd = InputMethods.getPositiveInteger();

        for (int i = 0; i < quantityAddProd; i++) {
            System.out.printf("\nThêm sản phẩm thứ %d \n", (i + 1));
            System.out.print("1. Nhập tên sản phẩm mới: ");
            String productName = InputMethods.getString();
            //
            long categoryId;
            System.out.println("\n2. Phân loại sản phẩm theo danh mục");
            if (catalogService.findAll().isEmpty()) {
                System.out.println("\nHiện tại danh sách danh mục trống\nBắt đầu tạo một danh mục");
                addNewCatalog();
                categoryId = catalogService.findAll().get(0).getCatalogtId();
            } else {
                System.out.println("______________________________________");
                for (Catalog calog : catalogService.findAll()
                ) {
                    System.out.printf("|  id=%-2s __ Tên danh mục: %-11s|\n", calog.getCatalogtId(), calog.getCatalogName());
                }
                System.out.println("______________________________________");
                System.out.println("Chọn theo id các danh mục có sẵn");
                System.out.print("Nhập id danh mục: ");
                while (true) {
                    categoryId = InputMethods.getLong();
                    System.out.println();
                    if (catalogService.findById(categoryId) == null) {
                        System.out.println("Mã danh mục không tồn tại! Mời nhập lại.");
                    } else {
                        break;
                    }
                }
            }
            //
            System.out.println("3. Nhập mô tả sản phẩm: ");
            String catologDesc = InputMethods.getString();
            //
            System.out.println("4. Nhập giá sản phẩm: ");
            double unitPrice = InputMethods.getDouble();
            //
            System.out.println("5. Nhập số lượng lưu kho: ");
            int stock = InputMethods.getInteger();
            // //
            Product newProduct = new Product(productService.getNewId(), categoryId, productName, catologDesc, unitPrice, stock);
            productService.save(newProduct);
            System.out.println("\n---------------------------\n");
        }
        System.out.println("Thêm mới sản phẩm thành công.");
    }

    private static void findProductByName() {
        System.out.println("Nhập tên sản phẩm muốn tìm kiếm: ");
        inputSearch = InputMethods.getString();
        List<Product> listResultSeacrh = productService.findByName(inputSearch);

        if (listResultSeacrh.isEmpty()) {
            System.out.println("\u001B[33m================================================================ KHÔNG TÌM THẤY ================================================================\u001B[97m");
        } else {
            printListProductForAdmin(listResultSeacrh, "== KẾT QUẢ TÌM KIẾM");
        }

    }

    private static void editProductById() {
        System.out.print("Nhập Id sản phẩm cần sửa thông tin: ");
        inputId = InputMethods.getLong();
        Product productEdit = productService.findById(inputId);

        if (productEdit == null) {
            System.out.println("Sản phẩm này không tồn tại!");
        } else {
            System.out.println(productEdit);

            long categoryId;
            System.out.println("\n   Cập nhật danh mục cho sản phẩm");
            System.out.println("______________________________________");
            for (Catalog calog : catalogService.findAll()
            ) {
                System.out.printf("|  id=%-2s __ Tên danh mục: %-11s|\n", calog.getCatalogtId(), calog.getCatalogName());
            }
            System.out.println("______________________________________");
            System.out.println("Chọn theo id các danh mục có sẵn");
            System.out.print("Nhập id danh mục: ");
            while (true) {
                categoryId = InputMethods.getLong();
                System.out.println();
                if (catalogService.findById(categoryId) == null) {
                    System.out.println("Mã danh mục không tồn tại! Mời nhập lại.");
                } else {
                    productEdit.setCategoryId(categoryId);
                    break;
                }
            }
        }

        //
        System.out.println("\nNhập tên mới cho sản phẩm: ");
        productEdit.setProductName(InputMethods.getString());
        //
        System.out.println("Nhập mô tả mới cho sản phẩm: ");
        productEdit.setDescription(InputMethods.getString());
        //
        System.out.println("Nhập giá mới cho sản phẩm: ");
        productEdit.setUnitPrice(InputMethods.getDouble());
        //
        System.out.println("Cập nhật số lượng lưu kho: ");
        productEdit.setStock(InputMethods.getInteger());
        // //
        productService.save(productEdit);
        System.out.println("\nCập nhật sản phẩm thành công.");

    }

    private static void toggleProducStatustById() {
        System.out.print("Nhập Id sản phẩm cần thay đổi trạng thái: ");
        inputId = InputMethods.getLong();
        productFindById = productService.findById(inputId);
        if (productFindById != null) {
            //cập nhật lại trạng thái
            productFindById.setStatus(!productFindById.isStatus());
            // lưu database lại
            productService.save(productFindById);
            System.out.println("\n\u001B[33m  ______________________________ Cập nhật trạng thái sản phẩm thành công ___________________________ \u001B[95m");
            System.out.println(productFindById);
        } else {
            System.out.println("\u001B[33m========================================================= KHÔNG TÌM THẤY ID SẢN PHẨM =========================================================\u001B[97m");
        }
    }

    /*========================================End Admin========================================*/


    /*=======================================Start Shop========================================*/
    public static void shopping() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[36m============== Menu Shop ==============\u001B[34m");
            System.out.println("|| 1. Tìm kiếm sản phẩm              ||");
            System.out.println("|| 2. Hiển thị 10 sản phẩm mới nhất  ||");
            System.out.println("|| 3. Danh sách sản phẩm             ||");
            System.out.println("|| 4. Mua sắm                        ||");
            System.out.println("|| 5. Xem giỏ hàng                   ||");
            System.out.println("|| 6. Đăng xuất                      ||");
            System.out.println("\u001B[36m_______________________________________\u001B[93m");
            System.out.print("** Mời nhập lựa chọn: \u001B[97m");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Nhập truy vấn để tìm kiếm: ");
                    inputSearch = InputMethods.getString();
                    List<Product> listResultSearch = productService.findProductByNameOrDescForCustomer(inputSearch);
                    if (listResultSearch.isEmpty()) {
                        System.out.println("\u001B[33m================================================================ KHÔNG TÌM THẤY ================================================================\u001B[97m");
                    } else {
                        printListProductForCustommer(listResultSearch, "== KẾT QUẢ TÌM KIẾM");
                    }
                    break;

                case 2:
                    List<Product> listProductTop10 = productService.findTop10NewProductByDate();
                    printListProductForCustommer(listProductTop10, "=== TOP 10 SẢN PHẨM");
                    break;

                case 3:
                    List<Product> listProductForUser = productService.findProductStatusIsActive();
                    printListProductForCustommer(listProductForUser, " DANH SÁCH SẢN PHẨM");
                    break;

                case 4:
                    addToCart();
                    break;

                case 5:
                    showCart();
                    break;

                case 6:
                    currentLogin = null;
                    return;

                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 6");
                    break;
            }
        }
    }

    private static void addToCart() {
        System.out.print("Nhập Id sản phẩm cần mua: ");
        inputId = InputMethods.getLong();
        Product product = productService.findProductIsActiveById(inputId);

        if (product != null) {
            System.out.println("\u001B[33m=============================================================== THÔNG TIN SẢN PHẨM ================================================================");
            System.out.printf("\u001B[36m*  %-11s | %-12s | %-25s | %-12s | %-11s | %-8s | %-45s %-1s\n",
                    "ID SẢN PHẨM",
                    "TÊN DANH MỤC",
                    "       TÊN SẢN PHẨM",
                    "  NGÀY TẠO",
                    "  GIÁ BÁN",
                    "SỐ LƯỢNG",
                    "                   MÔ TẢ",
                    "*");
            System.out.printf("\u001B[97m*  id=%-8d | %-12s | %-25s | %-12s | %-11s | %-8s | %-45s %-1s\n",
                    product.getProductId(),
                    catalogService.findById(product.getCategoryId()).getCatalogName(),
                    " " + product.getProductName(),
                    " " + product.getCreatedAt(),
                    " " + product.getUnitPrice() + " đ",
                    "   " + product.getStock(),
                    product.getDescription(),
                    "*");
            System.out.println("\u001B[33m===================================================================================================================================================\u001B[97m");

            System.out.print("Mời nhập số lượng muốn mua: ");
            int quantity = InputMethods.getPositiveInteger();
            if (quantity <= product.getStock()) {
                // tạo mới cartItem
                CartItem cartItemCurrent = new CartItem(product, quantity);
                cartService.save(cartItemCurrent);
            } else {
            System.out.println("\nRất tiếc, cửa hàng chỉ còn " + product.getStock() + " cho sản phẩm này!\n");
        }
            System.out.println("______________________________________________________________ Đã thêm vào giỏ hàng ______________________________________________________________");
        } else {
            System.out.println("___________________________________________________________ Không tìm thấy sản phẩm __________________________________________________________");
        }
    }

    private static void showCart() {
        if (cartService.findAll().isEmpty()) {
            System.out.println("Giỏ hàng hiện tại không có sản phẩm!");
        } else {
            printProductInCart();
        }
        while (true) {
            System.out.println("1. Thay đổi số lượng");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Quay lại");
            System.out.print("** Mời nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            System.out.println();
            switch (choice) {
                case 1:
                    toggleQuantityProductInCartById();
                    printProductInCart();
                    break;
                case 2:
                    deleteProductInCartById();
                    printProductInCart();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Nhập sai, mời nhập lại 1 -> 3");
                    break;
            }
        }
    }

    private static void deleteProductInCartById() {
        System.out.print("Nhập Id sản phẩm trong giỏ hàng cần xóa: ");
        long deleteId = InputMethods.getPositiveInteger();
        if (cartService.findById(deleteId) == null) {
            System.out.println("Không tìm thấy sản phẩm trùng Id trong giỏ hàng\n");
        } else {
            cartService.deleteById(deleteId);
            System.out.println("\nXóa sản phẩm thành công");
        }
    }

    private static void toggleQuantityProductInCartById() {
        System.out.print("Nhập Id sản phẩm trong giỏ hàng cần thay đổi lượng: ");
        long editQuantityId = InputMethods.getPositiveInteger();
        CartItem cartItemEdit = cartService.findById(editQuantityId);
        if (cartItemEdit == null) {
            System.out.println("Không tìm thấy sản phẩm trùng Id trong giỏ hàng\n");
        } else {
            System.out.print("Cập nhật số lượng mới: ");
            int quantityEdit = InputMethods.getPositiveInteger();
            if (quantityEdit <= cartItemEdit.getProduct().getStock()) {
                cartItemEdit.setQuantity(quantityEdit);
                cartService.saveEditQuantity(cartItemEdit);
                System.out.println("\nThay đổi số lượng thành công");
            } else {
                System.out.println("\nRất tiếc, cửa hàng chỉ còn " + cartItemEdit.getProduct().getStock() + " cho sản phẩm này!\n");
            }

        }
    }
    /*========================================End Shop=========================================*/


    /*=======================================Method Reuse========================================*/
    private static <T> void showList(List<T> list, String title, boolean flag)
    // Phương thức chung hiển thị danh sách và cả hiển thị kết quả tìm kết
    {
        if (list.isEmpty()) {
            String text = flag ? "Hiện tại chưa có " + title + " được tạo!" : "Không tìm thấy " + title + " nào cùng tên!";
            System.out.println(text);
        } else {
            String text = flag ? "-------- Danh sách " + title + " ---------" : "-------- Kết quả tìm kiếm ---------";
            System.out.println(text);
            for (T t : list
            ) {
                System.out.println(t);
            }
        }
    }

    private static void printListProductForCustommer(List<Product> list, String title)
    // In danh sách sản phẩm cho người dùng
    {
        System.out.println("\u001B[33m===============================================================" + title + " ================================================================");
        System.out.printf("\u001B[36m*  %-11s | %-12s | %-25s | %-12s | %-11s | %-8s | %-45s %-1s\n",
                "ID SẢN PHẨM",
                "TÊN DANH MỤC",
                "       TÊN SẢN PHẨM",
                "  NGÀY TẠO",
                "  GIÁ BÁN",
                "SỐ LƯỢNG",
                "                   MÔ TẢ",
                "*");

        for (Product prod : list
        ) {
            System.out.printf("\u001B[97m*  id=%-8d | %-12s | %-25s | %-12s | %-11s | %-8s | %-45s %-1s\n",
                    prod.getProductId(),
                    catalogService.findById(prod.getCategoryId()).getCatalogName(),
                    " " + prod.getProductName(),
                    " " + prod.getCreatedAt(),
                    " " + prod.getUnitPrice() + " đ",
                    "   " + prod.getStock(),
                    prod.getDescription(),
                    "*");
        }

        System.out.println("\u001B[33m===================================================================================================================================================\u001B[97m");
    }

    private static void printListProductForAdmin(List<Product> list, String title)
    // In danh sách sản phẩm cho admin
    {
        System.out.println("\u001B[33m===============================================================" + title + " =============================================================================");
        System.out.printf("\u001B[36m*  %-11s | %-12s | %-25s | %-12s | %-11s | %-8s | %-10s | %-45s %-1s\n",
                "ID SẢN PHẨM",
                "TÊN DANH MỤC",
                "      TÊN SẢN PHẨM",
                "  NGÀY TẠO",
                "  GIÁ BÁN",
                "SỐ LƯỢNG",
                "TRẠNG THÁI",
                "                   MÔ TẢ",
                "*");

        for (Product prod : list
        ) {
            System.out.printf("\u001B[97m*  id=%-8d | %-10s%-2s | %-25s | %-12s | %-11s | %-8s | %-10s | %-45s %-1s\n",
                    prod.getProductId(),
                    catalogService.findById(prod.getCategoryId()).getCatalogName(),
                    (catalogService.findById(prod.getCategoryId()).isStatus() ? "" : "\u001B[31m *\u001B[97m"),
                    " " + prod.getProductName(),
                    " " + prod.getCreatedAt(),
                    " " + prod.getUnitPrice() + " đ",
                    "   " + prod.getStock(),
                    " " + (prod.isStatus() ? "Hiển thị" : "Bị ẩn"),
                    prod.getDescription(),
                    "*");
        }

        System.out.println("\u001B[33m================================================================================================================================================================\u001B[97m");
    }

    private static void printProductInCart()
    // In danh sách sản phẩm cho trong giỏ hàng
    {
        System.out.println("\u001B[33m================================== GIỎ HÀNG CỦA BẠN ====================================");
        System.out.printf("\u001B[36m*  %-3s | %-11s | %-25s | %-10s | %-8s | %-11s %-1s\n",
                "STT",
                "ID SẢN PHẨM",
                "       TÊN SẢN PHẨM",
                " GIÁ BÁN",
                "SỐ LƯỢNG",
                "THÀNH TIỀN",
                "*");

        int count = 0;
        double total = 0;
        for (CartItem c : cartService.findAll()
        ) {
            System.out.printf("\u001B[97m*  %-3s | id=%-8d | %-25s | %-10s | %-8s | %-11s %-1s\n",
                    " " + (++count),
                    c.getProduct().getProductId(),
                    c.getProduct().getProductName(),
                    c.getProduct().getUnitPrice() + " đ",
                    "   " + c.getQuantity(),
                    " " + c.getProduct().getUnitPrice() * c.getQuantity() + " đ",
                    "*");
            total += c.getProduct().getUnitPrice() * c.getQuantity();
        }

        System.out.println("\u001B[33m----------------------------------------------------------------------------------------\u001B[97m");
        System.out.printf("| %-60s Tổng tiền: %9s đ  |\n", "", total);
        System.out.println("\u001B[33m========================================================================================\u001B[97m");
    }


}
