package view;

import service.ProductManage;

/**
 * @ClassName ProductPage
 * @Description TODO购物结算菜单页面
 * @Author DengChao
 * @CreatTime 2022/1/21 15:44
 * @Vertion 1.0
 */
public class ProductPage {
    private boolean judge = true;
    private static ProductPage pp = new ProductPage();//当前类的对象，为了单例设计模式

    private ProductPage() {
    }

    public static ProductPage getPp() {
        return pp;
    }

    /**
     * @param
     * @return
     * @description TODO购物结算菜单页面
     * @author DengChao
     * @date 2022/1/21 15:32
     */
    public void productInformationPage() {
        while (judge) {
            System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * *\n");
            System.out.println("\t\t1.购买商品");
            System.out.println("\t\t2.添加商品信息");
            System.out.println("\t\t3.删除商品信息");
            System.out.println("\t\t4.修改商品信息");
            System.out.println("\t\t5.查询商品信息");
            System.out.println("\t\t6.查看商品目录");
            System.out.println("\t\t7.返回到主菜单");
            System.out.print("\t\t请选择（1-7）：");
            switch (Tools.productPage()) {
                //1.购买商品
                case '1':
                    ProductManage.getPm().productBuy();
                    break;
                //2.添加商品信息
                case '2':
                    ProductManage.getPm().addProduct();
                    break;
                //3.删除商品信息
                case '3':
                    ProductManage.getPm().deleteProductInformation();
                    break;
                //4.修改商品信息
                case '4':
                    ProductManage.getPm().upDataProductInformation();
                    break;
                //5.查询商品信息
                case '5':
                    ProductManage.getPm().queryProductInformation();
                    break;
                //6.查看商品目录
                case '6':
                    ProductManage.getPm().productContent();
                    break;
                //7.返回到主菜单
                case '7':
                    judge = false;//将购物结算菜单页面的判断条件赋值为false，以已返回主菜单
                    System.out.println("\n已返回到主菜单！！！\n");
                    break;
            }
        }
        judge = true;////将购物结算菜单页面的判断条件重置为true，否则一旦赋值过为false后就再也进不到此页面了
    }
}
