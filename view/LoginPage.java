package view;

/**
 * @ClassName LoginPage系统登录页面
 * @Description TODO
 * @Author DengChao
 * @CreatTime 2022/1/21 15:42
 * @Vertion 1.0
 */
public class LoginPage {
    private boolean judge = true;
    private static LoginPage lp = new LoginPage();//当前类的对象，单例设计模式

    private LoginPage() {
        super();
    }

    public static LoginPage getLp() {
        return lp;
    }

    /**
     * @param
     * @return
     * @description TODO系统登录页面
     * @author DengChao
     * @date 2022/1/21 15:31
     */
    public void loginPage() {
        while (judge) {
            System.out.println("* * *欢迎使用购物管理系统* * *\n");
            System.out.println("\t\t1.登陆系统\n");
            System.out.println("\t\t2.退出系统\n");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *\n");
            System.out.print("\t\t请选择（1-2）：");
            //选择是否进入系统
            switch (Tools.loginPage()) {
                //1.系统主菜单
                case '1':
                    PrimaryMenu.getPm().primaryMenu();
                    //用户选择不进入系统，并结束程序
                case '2':
                    judge = false;
                    System.out.println("\n已退出，感谢您的使用！！！\n");
                    break;
            }
        }
    }
}