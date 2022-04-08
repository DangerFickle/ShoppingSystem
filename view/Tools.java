package view;

import service.ClientInformationManage;
import service.ProductManage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @ClassName Utility
 * @Description TODO存放各种可重复使用的方法
 * @Author DengChao
 * @CreatTime 2022/1/21 15:44
 * @Vertion 1.0
 */
public class Tools {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean clientFirstShow = true;
    private static boolean productFirstShow = true;

    private Tools() {
        super();
    }

    public static void setClientFirstShow(boolean clientFirstShow) {
        Tools.clientFirstShow = clientFirstShow;
    }

    public static void setProductFirstShow(boolean productFirstShow) {
        Tools.productFirstShow = productFirstShow;
    }

    /**
     * @param
     * @return {char}
     * @description TODO用于登录系统界面菜单的选择。该方法读取键盘，
     * 如果用户键入’1’-’2’中的任意字符，则方法返回。返回值为用户所键入字符。
     * @author DengChao
     * @date 2022/1/21 15:04
     */
    public static char loginPage() {
        char choice;
        for (; ; ) {
            String str = scanner.next();
            if (str.length() == 1) {
                choice = str.charAt(0);
                if (choice != '1' && choice != '2') {
                    System.out.print("没有此选项！请重新输入(1-2)：");
                } else break;
            } else {
                System.out.print("没有此选项！请重新输入(1-2)：");
            }
        }
        return choice;
    }

    /**
     * @param
     * @return {@link char}
     * @description TODO用于主菜单界面菜单的选择。该方法读取键盘，如果用户键入’1’-’3’中的任意字符，
     * 则方法返回。返回值为用户所键入字符。
     * @author DengChao
     * @date 2022/1/21 15:23
     */
    public static char primaryMenu() {
        char choice;
        for (; ; ) {
            String str = scanner.next();
            if (str.length() == 1) {
                choice = str.charAt(0);
                if (choice != '1' && choice != '2' && choice != '3') {
                    System.out.print("没有此选项！请重新输入(1-3)：");
                } else break;
            } else {
                System.out.print("没有此选项！请重新输入(1-3)：");
            }
        }
        return choice;
    }

    /**
     * @param
     * @return {@link char}
     * @description TODO用于客户信息管理的界面菜单的选择。该方法读取键盘，如果用户键入’1’-’6’中的任意字符，
     * 则方法返回。返回值为用户所键入字符。
     * @author DengChao
     * @date 2022/1/21 15:23
     */

    public static char clientInformationPage() {
        char choice;
        for (; ; ) {
            String str = scanner.next();
            if (str.length() == 1) {
                choice = str.charAt(0);
                if (choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5' && choice != '6') {
                    System.out.print("没有此选项！请重新输入(1-6)：");
                } else break;
            } else {
                System.out.print("没有此选项！请重新输入(1-6)：");
            }
        }
        return choice;
    }

    /**
     * @param
     * @return {@link char}
     * @description TODO用于商品信息管理界面菜单的选择。该方法读取键盘，如果用户键入’1’-’7’中的任意字符，
     * 则方法返回。返回值为用户所键入字符。
     * @author DengChao
     * @date 2022/1/21 15:23
     */
    public static char productPage() {
        char choice;
        for (; ; ) {
            String str = scanner.next();
            if (str.length() == 1) {
                choice = str.charAt(0);
                if (choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5' && choice !=
                        '6' && choice != '7') {
                    System.out.print("没有此选项！请重新输入(1-7)：");
                } else break;
            } else {
                System.out.print("没有此选项！请重新输入(1-7)：");
            }
        }
        return choice;
    }


    /**
     * @param
     * @return {@link int}
     * @description TODO用于客户编号的输入判断。该方法从键盘读取一个不超过4位长度的整数，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:25
     */
    public static int readClientNumber() {
        int clientNumber;
        char firstString;
        table:
        for (; ; ) {
            String str = scanner.next();
            firstString = str.charAt(0);
            if (str.length() == 4 && firstString != '0') {
                try {
                    clientNumber = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.print("输入错误！只能输入纯数字！请重新输入：");
                    continue;
                }
                if (!clientFirstShow) {
                    for (int i = 0; i < ClientInformationManage.getCim().getAddCount(); i++) {
                        if (ClientInformationManage.getCim().getCi()[i].getClientNum() == clientNumber) {
                            System.out.print("该客户编号已存在，请重新输入：");
                            continue table;
                        }
                    }
                    return clientNumber;
                } else if (clientFirstShow) {
                    return clientNumber;
                }
            } else {
                System.out.print("输入错误！请输入一个四位整数且首位不可为0：");
            }
        }
    }

    /**
     * @param
     * @return {@link String}
     * @description TODO用于客户姓名的输入判断。该方法判断输入的姓名位数，并自动添加制表符保证查看客户信息的排版美观。
     * @author DengChao
     * @date 2022/1/21 15:25
     */
    public static String readClientName() {
        for (; ; ) {
            String name = scanner.next();
            if (name.length() == 2) {
                name += "\t";
                return name;
            } else if (name.length() == 3) {
                return name;
            } else if (name.length() == 4) {
                return name;
            } else {
                System.out.print("输入错误！请输入至少两位且至多四位的姓名！请重新输入：");
            }
        }
    }

    /**
     * @param
     * @return {@link String}
     * @description TODO用于手机号码的输入判断。该方法从键盘读取一个不超过11位长度的字符串，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:25
     */
    public static String readClientPhoneNumber() {
        table:
        for (; ; ) {
            String str = scanner.next();
            if (str.length() == 11 && str.charAt(0) == '1') {
                if (!clientFirstShow) {
                    for (int i = 0; i < ClientInformationManage.getCim().getAddCount(); i++) {
                        if (ClientInformationManage.getCim().getCi()[i].getClientPhoneNum().equals(str)) {
                            System.out.print("该手机号码已存在，请重新输入：");
                            continue table;
                        }
                    }
                    return str;
                } else if (clientFirstShow) {
                    return str;
                }
            } else {
                System.out.print("输入错误！请输入11位电话号码且必须是以'1'开头：");
            }
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO用于客户积分的输入判断。该方法从键盘读取一个整数，该整数不能为小于或等于0，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:25
     */
    public static int readClientScore() {
        int score = 0;
        for (; ; ) {
            try {
                score = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("输入错误！只能输入纯数字！请重新输入：");
                scanner.nextLine();
                continue;
            }
            if (score <= 0) {
                System.out.print("客户积分不可小于0！请重新输入：");
                continue;
            }
            return score;
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO删除、修改、查询客户信息时，查找输入的客户编号是否存在，存在则返回其数组中的索引位置，反之则提示重新输入
     * @author DengChao
     * @date 2022/1/22 15:28
     */
    public static int clientInformationNumExist() {
        int clientNum;
        table:
        for (; ; ) {
            clientNum = scanner.nextInt();
            for (int i = 0; i <= ClientInformationManage.getCim().getAddCount(); i++) {
                if (ClientInformationManage.getCim().getCi()[i].getClientNum() == clientNum) {
                    return i;
                }
            }
            if (clientNum < 0) {
                return -1;
            }
            System.out.print("客户编号不存在！（输入-1返回到上级菜单）请重新输入：");
            continue table;
        }
    }

    public static int checkOutClientNumExist() {
        int clientNum;
        table:
        for (; ; ) {
            try {
                clientNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("输入错误！只能输入纯数字！请重新输入：");
                scanner.nextLine();
                continue;
            }
            for (int i = 0; i <= ClientInformationManage.getCim().getAddCount(); i++) {
                if (ClientInformationManage.getCim().getCi()[i].getClientNum() == clientNum) {
                    return i;
                }
            }
            System.out.print("客户编号不存在！请重新输入：");
            continue table;
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO删除、修改、查询商品信息时，查找输入的商品编号是否存在，存在则返回其数组中的索引位置，反之则提示重新输入
     * @author DengChao
     * @date 2022/1/22 19:42
     */
    public static int productInformationNumExist() {
        int productNum;
        table:
        for (; ; ) {
            productNum = scanner.nextInt();
            for (int i = 0; i <= ProductManage.getPm().getAddCount(); i++) {
                if (ProductManage.getPm().getPi()[i].
                        getProductNum() == productNum) {
                    return i;
                }
            }
            if (productNum < 0) {
                return -1;
            }
            System.out.print("商品编号不存在！（输入-1返回到上级菜单）请重新输入：");
            continue table;
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO用于在添加商品输入商品编号时判断是否添加过该商品编号，从键盘读入一个整数，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:25
     */
    public static int readProductNumber() {
        int productNum = 0;
        table:
        for (; ; ) {
            try {
                productNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("输入错误！只能输入纯数字！请重新输入：");
                scanner.nextLine();
                continue;
            }
            if (!productFirstShow) {
                for (int i = 0; i < ProductManage.getPm().getAddCount(); i++) {
                    if (ProductManage.getPm().getPi()[i].getProductNum() == productNum) {
                        System.out.print("该商品编号已存在！请重新输入：");
                        continue table;
                    }
                }
                return productNum;
            }
            return productNum;
        }
    }

    /**
     * @param
     * @return {@link String}
     * @description TODO用于在添加商品输入商品名称时判断是否添加过该商品名称，从键盘读入一个字符串，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static String readProductName() {
        table:
        for (; ; ) {
            String name = scanner.next();
            if (!productFirstShow) {
                for (int i = 0; i < ProductManage.getPm().getAddCount(); i++) {
                    if (ProductManage.getPm().getPi()[i].getProductName().equals(name)) {
                        System.out.print("该商品名称已存在！请重新输入：");
                        continue table;
                    }
                }
                return name;
            }
            return name;
        }
    }

    /**
     * @param
     * @return {@link double}
     * @description TODO用于在添加商品输入商品价格时，判断输入的价格是否小于零，如果是就提示重新输入，反之则返回变量price。
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static double readProductPrice() {
        double price = 0;
        for (; ; ) {
            try {
                price = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("输入错误！只能输入纯数字！请重新输入：");
                scanner.nextLine();
                continue;
            }
            if (price <= 0) {
                System.out.print("商品价格不能小于0！请重新输入：");
                continue;
            }
            return price;
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO输入商品编号时，判断输入的商品编号是否存在，存在则返回该值，反之则提示重新输入。
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static int readProductNumBuy() {
        for (; ; ) {
            int productNum = scanner.nextInt();
            for (int i = 0; i <= ProductManage.getPm().getAddCount(); i++) {
                if (ProductManage.getPm().getPi()[i].getProductNum() == productNum) {
                    return productNum;
                }
            }
            System.out.print("商品编号输入错误或该商品不存在！请重新输入：");
        }
    }

    /**
     * @param
     * @return {@link double}
     * @description TODO判断结算时输入的金额是否大于购物金额，如果大于就继续结算，反之则让客户重新输入。
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static double readCheckOutMoney(double realityMoney) {
        double checkOutMoney;
        for (; ; ) {
            try {
                checkOutMoney = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("输入错误！只能输入纯数字！请重新输入：");
                scanner.nextLine();
                continue;
            }
            if (checkOutMoney < realityMoney) {
                System.out.print("支付金额低于实际应付金额！请重新输入：");
                continue;
            }
            return checkOutMoney;
        }
    }

    /**
     * @param
     * @return {@link int}
     * @description TODO购物结算时判断输入的购买数量是否小于零
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static int buyCount() {
        int buyCount;
        for (; ; ) {
            buyCount = scanner.nextInt();
            if (buyCount <= 0) {
                System.out.print("购买数量不能小于1！请重新输入:");
                continue;
            }
            return buyCount;
        }
    }

    /**
     * @param money 对金额进行四舍五入
     * @return
     */
    public static double setMoney(double money) {
        String setMoneyString = String.format("%.2f", money);
        double setMoney = Double.parseDouble(setMoneyString);
        return setMoney;
    }

    /**
     * @param
     * @return {@link char}
     * @description TODO用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     * @author DengChao
     * @date 2022/1/21 15:26
     */
    public static char readConfirmSelection() {
        char choice;
        for (; ; ) {
            String str = scanner.next().toUpperCase();
            choice = str.charAt(0);
            if (choice == 'Y' || choice == 'N') {
                break;
            } else {
                System.out.print("选择错误！请重新输入（Y / N）：");
            }
        }
        return choice;
    }
}