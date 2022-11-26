package managers;


import pojo.Product;
import pages.Tools;

/**
 * @ClassName ProductManage
 * @Description TODO 购物结算的具体逻辑
 * @Author DengChao
 * @CreatTime 2022/1/21 15:43
 * @Vertion 1.0
 */
public class ProductManager {
    private int addCount = -1;//记录已添加的商品个数（从-1开始），已添加的商品个数为：addCount + 1
    private double realityMoney;//记录实际支付价格
    private double totalMoney;//记录购物总金额
    private double chance;//记录结算时的找零
    private double paymentAmount;//记录输入的支付金额
    private boolean firstShow = true;//记录是否添加过商品
    private StringBuilder buyInformation = new StringBuilder();//记录购买的商品信息
    private int showCount = 1;//记录下一个要添加的商品位数
    private static final ProductManager PM = new ProductManager();//当前类的对象，为了单例设计模式
    private final Product[] PI = new Product[3];//客户类的对象数组，为了单例设计模式

    private ProductManager() {
        super();
    }

    public static ProductManager getPm() {
        return PM;
    }

    public Product[] getPi() {
        return PI;
    }

    public int getAddCount() {
        return addCount;
    }

    public double getRealityMoney() {
        return realityMoney;
    }


    /**
     * TODO 购买商品
     *
     * @author DengChao
     * @date 2022/1/21 15:29
     */
    public void productBuy() {
        boolean judge = true;
        boolean judgeString = true;

        if (!firstShow) {
            while (judge) {
                productContent();
                System.out.print("\n请输入商品编号：");
                int productNum = Tools.readProductNumBuy();
                System.out.print("请输入购买数量：");
                int buyCount = Tools.buyCount();
                for (int i = 0; i <= addCount; i++) {
                    if (PI[i].getProductNum() == productNum) {
                        System.out.println("商品名称：" + PI[i].getProductName() + "\n单价：￥" + PI[i].getPrice() +
                                "\n数量：" + buyCount + "\n合计：￥" + Tools.setMoney(PI[i].getPrice() * buyCount));
                        totalMoney += PI[i].getPrice() * buyCount;
                        if (judgeString) {
                            buyInformation.append("\t" + buyCount + "个" + PI[i].getProductName() + "\t￥" + Tools.setMoney(PI[i].getPrice() * buyCount));
                        } else {
                            buyInformation.append("\n\t\t\t" + buyCount + "个" + PI[i].getProductName() + "\t￥" + Tools.setMoney(PI[i].getPrice() * buyCount));
                        }
                        judgeString = false;
                        System.out.println("当前已购买：" + buyInformation);
                        totalMoney = Tools.setMoney(totalMoney);
                        System.out.println("总金额：￥" + totalMoney);
                        System.out.println("是否要继续购买？（Y / N）");
                        //判断是否继续添加客户信息，"Y"为继续添加，"N"为进入结算系统
                        switch (Tools.readConfirmSelection()) {
                            case 'N':
                                checkOut();
                                buyInformation.delete(0,buyInformation.length());//对记录购买信息的变量重新赋值，以便下一次购买，否则还会保留上一次购买的信息
                                return;
                            case 'Y':
                            default:
                        }
                    }
                }
            }
        } else {
            System.out.println("\n当前还没有商品呢！");
        }
    }

    /**
     * TODO 添加商品
     *
     * @author DengChao
     * @date 2022/1/21 15:29
     */
    public void addProduct() {
        if (addCount < PI.length - 1) {
            table:
            for (int i = ++addCount; addCount < PI.length; i++, addCount = i) {
                System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * *\n");
                System.out.println("当前剩余可添加商品：" + (PI.length - addCount) + "个");
                System.out.println("开始添加第" + showCount + "个商品：\n");
                System.out.print("请输入商品编号：");
                int productNum = Tools.readProductNumber();
                System.out.print("请输入商品名称：");
                String productName = Tools.readProductName();
                System.out.print("请输入商品价格：");
                double productPrice = Tools.readProductPrice();
                PI[i] = new Product(productName, productPrice, productNum);
                firstShow = false;//开始添加商品后，对象数组就开始逐一被赋值，有赋值后被遍历调用才不会导致空指针异常
                Tools.setProductFirstShow(false);
                showCount++;
                System.out.println("\n* * * * * * * * * *登记完成* * * * * * * * * *\n");
                System.out.println("请问是否要继续添加？(Y / N)\n");
                //判断是否继续添加客户信息，"y"为继续添加，"n"为返回到上级菜单
                switch (Tools.readConfirmSelection()) {
                    case 'N':
                        System.out.println("\n已返回上级菜单\n");
                        break table;
                    case 'Y':
                        if (addCount == PI.length - 1) {
                            System.out.println("\n商品位置已满！无法继续添加！");
                            break table;
                        }
                    default:
                }
            }
        } else {
            System.out.println("\n商品位置已满！无法继续添加！");
        }
        if (addCount < PI.length - 1) {
            showCount = addCount + 2;
        } else {
            showCount = addCount + 2;
        }
    }

    /**
     * TODO 删除商品信息
     *
     * @author DengChao
     * @date 2022/1/22 19:36
     */
    public void deleteProductInformation() {
        if (!firstShow) {
            productContent();
            System.out.print("\n请输入要删除的商品编号：");
            int productInformationIndex = Tools.productInformationNumExist();
            if (productInformationIndex < 0) {
                return;
            }
            System.out.print("请问是否确认删除？此操作不可逆！（Y / N）");
            if (Tools.readConfirmSelection() == 'Y') {
                for (int i = productInformationIndex; i < addCount; i++) {
                    PI[i] = PI[i + 1];
                }
                System.out.print("商品信息删除成功！");
                PI[addCount--] = null;
                showCount--;
                //判断商品是否被删完如果删完了就将firstShow赋值为没有添加商品时的状态
                if (showCount == 1) {
                    firstShow = true;
                }
            }
            System.out.println("\n\n已返回上级菜单");
            return;
        }
        System.out.println("\n当前还没有商品呢！");

    }


    /**
     * TODO 修改商品信息
     *
     * @author DengChao
     * @date 2022/1/21 15:30
     */
    public void upDataProductInformation() {
        if (!firstShow) {
            productContent();
            System.out.println("\n请输入商品编号：");
            int findProductIndex = Tools.productInformationNumExist();
            if (findProductIndex < 0) {
                return;
            }
            System.out.println("商品原信息为：");
            System.out.println("商品编号：" + PI[findProductIndex].getProductNum());
            System.out.println("商品名称：" + PI[findProductIndex].getProductName());
            System.out.println("商品价格：" + "￥ " + PI[findProductIndex].getPrice());
            System.out.println("确认是否要修改？（Y / N）");
            char judge = Tools.readConfirmSelection();
            switch (judge) {
                case 'N':
                    return;
                case 'Y':
                default:
            }
            System.out.println("开始修改商品信息！");
            System.out.print("请输入修改后的商品编号：");
            PI[findProductIndex].setProductNum(Tools.readProductNumber());
            System.out.print("请输入修改后的商品名称：");
            PI[findProductIndex].setProductName(Tools.readProductName());
            System.out.print("请输入修改后的商品价格：");
            PI[findProductIndex].setPrice(Tools.readProductPrice());
            System.out.print("修改完成！已返回上级菜单！");
            return;
        }
        System.out.println("\n当前还没有商品呢！");
    }

    /**
     * TODO 查询商品信息
     *
     * @author DengChao
     * @date 2022/1/21 15:30
     */
    public void queryProductInformation() {
        if (!firstShow) {
            System.out.print("请输入商品编号：");
            int findProduct = Tools.productInformationNumExist();
            if (findProduct < 0) {
                return;
            }
            System.out.println("\n* * * * * * * * * *商品信息* * * * * * * * * *\n");
            System.out.println("商品编号：" + PI[findProduct].getProductNum());
            System.out.println("商品名称：" + PI[findProduct].getProductName());
            System.out.println("商品价格：" + "￥ " + PI[findProduct].getPrice());
            return;
        }
        System.out.println("\n当前还没有商品呢！");
    }


    /**
     * TODO 查看商品目录
     *
     * @author DengChao
     * @date 2022/1/21 15:29
     */
    public void productContent() {
        System.out.println("\n* * * * * * * * * * 商品目录 * * * * * * * * * *");
        if (!firstShow) {
            //每当查看商品信息时，都会以商品编号由小到大的顺序对对象数组中的每一个对象进行排序
            for (int i = addCount; i > 0; i--) {
                if (PI[i].getProductNum() < PI[i - 1].getProductNum()) {
                    Product tamp = PI[i];
                    PI[i] = PI[i - 1];
                    PI[i - 1] = tamp;
                }
            }
            //遍历对象数组，输出各自的属性
            for (int i = 0; i <= addCount; i++) {
                System.out.print(PI[i]);
                if ((i + 1) % 2 == 0) {
                    System.out.println();
                }
            }
        } else {
            System.out.println("\n当前还没有商品呢！");
        }
    }


    /**
     * TODO 结算方法
     *
     * @author DengChao
     * @date 2022/1/21 15:30
     */
    private void checkOut() {
        boolean judge = true;
        System.out.println("\n正在进入结算系统 · · · · ·\n");
        System.out.println("开始结算（注：结算时的所有金额为四舍五入之后的）：");
        while (judge) {
            System.out.println("是否为客户？（Y / N）");
            table:
            switch (Tools.readConfirmSelection()) {
                case 'Y':
                    double discount = 0;//记录根据客户积分打折的倍率
                    String discountChinese = null;//记录根据客户积分打折的倍率的中文显示
                    if (ClientManager.getCim().isFirstShow()) {
                        System.out.println("当前并未添加任何客户！正在进入非客户结算 · · · · ·");
                        unClientPay();
                        return;
                    }
                    System.out.print("请输入客户编号：");
                    int clientNumIndex = Tools.checkOutClientNumExist();
                    if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 2000) {
                        discount = 0.9;
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() >= 2000 && ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 4000) {
                        discount = 0.8;
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() >= 4000 && ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 8000) {
                        discount = 0.7;
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() > 8000) {
                        discount = 0.6;
                    }
                    if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 2000) {
                        discountChinese = "九折";
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() >= 2000 && ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 4000) {
                        discountChinese = "八折";
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() >= 4000 && ClientManager.getCim().getCi()[clientNumIndex].getClientScore() < 8000) {
                        discountChinese = "七折";
                    } else if (ClientManager.getCim().getCi()[clientNumIndex].getClientScore() > 8000) {
                        discountChinese = "六折";
                    }
                    System.out.println("亲爱的" + ClientManager.getCim().getCi()[clientNumIndex].getClientName());
                    System.out.println("您的客户积分为：" + ClientManager.getCim().getCi()[clientNumIndex].getClientScore());
                    System.out.println("根据您的客户积分，享受" + discountChinese + "优惠！");
                    System.out.println("当前已购买：" + buyInformation);
                    System.out.println("总金额：￥" + totalMoney);
                    realityMoney = Tools.setMoney(totalMoney * discount);
                    System.out.println("打折后金额为：￥" + realityMoney);
                    System.out.print("请输入支付金额：");
                    paymentAmount = Tools.readCheckOutMoney(realityMoney);
                    System.out.println("您支付了：￥：" + paymentAmount);
                    chance = Tools.setMoney(paymentAmount - realityMoney);
                    System.out.println("找钱：￥" + chance);
                    System.out.println("结算完毕！");
                    totalMoney = 0.0;//对总金额变量重新赋值，便于下一次购买
                    realityMoney = 0.0;//对实际支付金额重新赋值，便于下一次购买
                    return;
                case 'N':
                    unClientPay();
                    return;
                default:
            }
        }
    }

    /**
     * TODO 非客户结算方法
     *
     * @author DengChao
     * @date 2022/1/21 15:30
     */
    private void unClientPay() {
        System.out.println("总金额：" + totalMoney);
        if (totalMoney >= 100) {
            realityMoney = Tools.setMoney(totalMoney * 0.9);
            System.out.println("非客户购物满一百打九折");
        } else {
            realityMoney = totalMoney;
            System.out.println("非客户购物未满一百不打折");
        }
        System.out.println("实付金额：￥" + realityMoney);
        System.out.print("请输入支付金额：");
        paymentAmount = Tools.readCheckOutMoney(realityMoney);
        System.out.println("您支付了：￥：" + paymentAmount);
        System.out.println("找钱：￥" + Tools.setMoney(paymentAmount - realityMoney));
        System.out.println("结算完毕！已返回上级菜单！");
        totalMoney = 0.0;//对总金额变量重新赋值，便于下一次购买
        realityMoney = 0.0;//对实际支付金额重新赋值，便于下一次购买
        return;
    }
}