package pages;

import managers.ClientManager;

/**
 * @ClassName ClientInformationManagePage
 * @Description TODO 客户管理的菜单页面
 * @Author DengChao
 * @CreatTime 2022/1/21 15:40
 * @Vertion 1.0
 */
public class ClientManagePage {
    private boolean judge = true;//判断客户信息管理菜单页面的while循环
    private static final ClientManagePage CIMP = new ClientManagePage();//当前类的对象，单例设计模式

    private ClientManagePage() {
        super();
    }

    public static ClientManagePage getCimp() {
        return CIMP;
    }

    /**
     * TODO 客户管理菜单页面
     *
     * @author DengChao
     * @date 2022/1/21 15:31
     */
    public void clientInformationManagePage() {
        while (judge) {
            System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * *\n");
            System.out.println("\t\t1.显示客户所有信息");
            System.out.println("\t\t2.添加客户信息");
            System.out.println("\t\t3.删除客户信息");
            System.out.println("\t\t4.修改客户信息");
            System.out.println("\t\t5.查询客户信息");
            System.out.println("\t\t6.返回到主菜单");
            System.out.print("\t\t请选择（1-6）：");
            switch (Tools.clientInformationPage()) {
                //1.显示客户所有信息
                case '1':
                    ClientManager.getCim().showClientInformationTraverse();
                    break;
                //2.添加客户信息
                case '2':
                    ClientManager.getCim().addClientInformation();
                    break;
                //3.删除客户信息
                case '3':
                    ClientManager.getCim().deleteClientInformation();
                    break;
                //4.修改客户信息
                case '4':
                    ClientManager.getCim().upDateClientInformation();
                    break;
                //5.查询客户信息
                case '5':
                    ClientManager.getCim().queryClientInformation();
                    break;
                /*
                6.返回到主菜单
                    实现思路：
                        把循环条件重新赋值为false结束上面的循环结构，达到返回主菜单的目的
                 * */
                case '6':
                    judge = false;//将客户信息管理菜单页面的判断条件赋值为false，以返回到主菜单
                    System.out.println("\n已返回到主菜单！！！\n");
                    break;
                default:
            }
        }
        judge = true;//将用于判断客户信息管理菜单页面的循环条件重置为true
    }
}
