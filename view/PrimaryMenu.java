package view;

/**
 * @ClassName PrimaryMenu
 * @Description TODO系统主菜单页面
 * @Author DengChao
 * @CreatTime 2022/1/21 15:42
 * @Vertion 1.0
 */
public class PrimaryMenu {
    private boolean judge = true;
    private static PrimaryMenu pm = new PrimaryMenu();//当前类的对象，为了单例设计模式

    private PrimaryMenu() {
        super();
    }

    public static PrimaryMenu getPm() {
        return pm;
    }

    /**
     * @param
     * @return
     * @description TODO系统主菜单页面
     * @author DengChao
     * @date 2022/1/21 15:32
     */
    public void primaryMenu() {
        while (judge) {
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *\n");
            System.out.println("\t\t1.客户信息管理");
            System.out.println("\t\t2.购物结算");
            System.out.println("\t\t3.注销系统");
            System.out.print("\t\t请选择（1-3）：");
            switch (Tools.primaryMenu()) {
                //1.客户信息管理
                case '1':
                    ClientInformationManagePage.getCimp().clientInformationManagePage();
                    break;
                //2.购物结算
                case '2':
                    ProductPage.getPp().productInformationPage();
                    break;
                //3.进入系统后选择结束程序
                case '3':
                    judge = false;
                    break;
            }
        }
    }
}