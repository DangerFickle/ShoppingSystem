package service;

import domain.ClientInformation;
import view.Tools;

/**
 * @ClassName ClientInformationManage
 * @Description TODO客户信息的具体管理逻辑
 * @Author DengChao
 * @CreatTime 2022/1/21 15:36
 * @Vertion 1.0
 */
public class ClientInformationManage {
    private int addCount = -1;//记录已添加的客户个数（从-1开始），已添加的客户个数为：addCount + 1
    private boolean firstShow = true;//判断显示客户所有信息，如果在还没有录入任何客户信息的形况下，就不遍历对象数组，否则会导致空指针异常
    private int showCount = 1;//记录下一个要添加的客户位数
    private static ClientInformationManage cim = new ClientInformationManage();//当前类的对象，为了单例设计模式
    private ClientInformation[] ci = new ClientInformation[3];//客户类的对象数组

    //当前类的空参构造器
    private ClientInformationManage() {
        super();
    }

    //当前类对象的get方法
    public static ClientInformationManage getCim() {
        return cim;
    }

    //客户对象数组的引用值的get方法
    public ClientInformation[] getCi() {
        return ci;
    }

    //变量firstShow的get方法
    public boolean isFirstShow() {
        return firstShow;
    }

    //变量addCount的get方法
    public int getAddCount() {
        return addCount;
    }

    /**
     * @param
     * @return
     * @description TODO打印指定客户对象的信息
     * @author DengChao
     * @date 2022/1/21 15:27
     */
    public void showClientInformation(ClientInformation client) {
        System.out.println(client.getClientNum() + "\t\t\t" +
                client.getClientName() + "\t\t\t" + client.getClientPhoneNum() + "\t\t" + client.getClientScore());
    }

    /**
     * @param
     * @return
     * @description TODO调用客户对象的属性，并对对象数组内的对象根据客户编号由小到大的顺序进行排序
     * 然后遍历数组
     * @author DengChao
     * @date 2022/1/21 15:27
     */
    public void showClientInformationTraverse() {
        System.out.println("\n* * * * * * * * 当前客户信息记录 * * * * * * * *");
        System.out.println("客户编号" + "\t\t\t" + "客户姓名" + "\t\t\t" + "客户电话" + "\t\t\t" + "客户积分");
        if (!firstShow) {
            //每当查看客户信息时，都会以客户编号由小到大的顺序对对象数组中的每一个对象进行排序
            for (int i = addCount; i > 0; i--) {
                if (ci[i].getClientNum() < ci[i - 1].getClientNum()) {
                    ClientInformation tamp = ci[i];
                    ci[i] = ci[i - 1];
                    ci[i - 1] = tamp;
                }
            }
            //遍历对象数组，输出各自的属性
            for (int i = 0; i <= addCount; i++) {
                System.out.println(ci[i].getClientNum() + "\t\t\t" +
                        ci[i].getClientName() + "\t\t\t" + ci[i].getClientPhoneNum() + "\t\t" + ci[i].getClientScore());
            }
        } else {
            System.out.println("\n当前还没有客户呢！");
        }
    }

    /**
     * @param
     * @return
     * @description TODO对客户对象的属性进行赋值操作
     * @author DengChao
     * @date 2022/1/21 15:28
     */
    public void addClientInformation() {
        if (addCount < ci.length - 1) {
            table:
            for (int i = ++addCount; addCount < ci.length; i++, addCount = i) {
                System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * *\n");
                System.out.println("当前剩余可添加客户：" + (ci.length - addCount) + "位");
                System.out.println("开始登记第" + showCount + "位客户的信息：\n");
                System.out.print("请输入客户编号：");
                int clientNum = Tools.readClientNumber();
                System.out.print("请输入客户姓名：");
                String clientName = Tools.readClientName();
                System.out.print("请输入客户电话：");
                String clientPhoneNum = Tools.readClientPhoneNumber();
                System.out.print("请输入客户积分：");
                int clientScore = Tools.readClientScore();
                ci[i] = new ClientInformation(clientNum , clientName , clientPhoneNum , clientScore);
                firstShow = false;//添加完客户后，对象数组就已经被逐一赋值，有赋值后遍历调用才不会导致空指针异常
                Tools.setClientFirstShow(false);
                showCount++;
                System.out.println("\n* * * * * * * * * *登记完成* * * * * * * * * *\n");
                System.out.print("请问是否要继续添加？（Y / N）");
                //判断是否继续添加客户信息，"Y"为继续添加，"N"为返回到上级菜单
                switch (Tools.readConfirmSelection()) {
                    case 'N':
                        System.out.println("\n已返回上级菜单\n");
                        break table;
                    case 'Y':
                        if (addCount == ci.length - 1) {
                            System.out.println("\n客户位置已满！无法继续添加！");
                            break table;
                        }
                }
            }
        } else {
            System.out.println("\n客户位置已满！无法继续添加！");
        }
        showCount = addCount + 2;
    }

    /**
     * @param
     * @return
     * @description TODO对客户信息进行删除，实现思路：将后一个对象赋值给前一个
     * @author DengChao
     * @date 2022/1/22 15:20
     */
    public void deleteClientInformation() {
        if (!firstShow) {
            showClientInformationTraverse();
            System.out.print("请输入要删除的客户编号：");
            int clientInformationIndex = Tools.clientInformationNumExist();
            if (clientInformationIndex < 0) {
                return;
            }
            System.out.print("请问是否确认删除？此操作不可逆！（Y / N）");
            if (Tools.readConfirmSelection() == 'Y') {
                for (int i = clientInformationIndex; i < addCount; i++) {
                    ci[i] = ci[i + 1];
                }
                System.out.print("客户信息删除成功！");
                ci[addCount--] = null;
                showCount--;
                //判断客户是否被删完如果删完了就将firstShow赋值为没有添加客户时的状态
                if (showCount == 1) {
                    firstShow = true;
                }
            }
            System.out.println("\n\n已返回上级菜单");
            return;
        }
        System.out.println("\n当前还没有客户呢！");
    }

    /**
     * @param
     * @return
     * @description TODO修改客户信息
     * @author DengChao
     * @date 2022/1/21 15:28
     */
    public void upDateClientInformation() {
        if (!firstShow) {
            showClientInformationTraverse();
            System.out.print("请输入需要修改的客户信息的客户编号：");
            int findClientNumIndex = Tools.clientInformationNumExist();//获取需要修改的客户信息的对象数组索引
            //找到该客户编号对应的数组索引后显示一次该索引对应的数组元素，即客户的所有信息
            if (findClientNumIndex < 0) {
                return;
            }
            System.out.println("该客户的原信息为：");
            System.out.println("客户编号" + "\t\t\t" + "客户姓名" + "\t\t\t" + "客户电话" + "\t\t\t" + "客户积分");
            showClientInformation(ci[findClientNumIndex]);
            System.out.println("请问是否确定要修改？（Y / N）");
            switch (Tools.readConfirmSelection()) {
                case 'N':
                    //客户选择不修改该客户的信息，结束该for循环，返回到上级菜单
                    break;
                case 'Y':
                    //客户选择确定修改该客户的信息后，开始对该客户编号对应的数组索引的元素进行重新赋值，即修改客户信息
                    System.out.println("开始修改第" + (findClientNumIndex + 1) + "位的客户信息：");
                    System.out.print("请输入修改后的客户编号：");
                    ci[findClientNumIndex].setClientNum(Tools.readClientNumber());
                    System.out.print("请输入修改后的客户姓名：");
                    ci[findClientNumIndex].setClientName(Tools.readClientName());
                    System.out.print("请输入修改后的客户电话：");
                    ci[findClientNumIndex].setClientPhoneNum(Tools.readClientPhoneNumber());
                    System.out.print("请输入修改后的客户积分：");
                    ci[findClientNumIndex].setClientScore(Tools.readClientScore());
                    System.out.println("客户信息修改成功！");
                    return;//客户信息修改完成后，结束该方法，返回到上级菜单
            }
        }
        System.out.println("\n当前还没有客户呢！");
    }

    /**
     * @param
     * @return
     * @description TODO查询客户信息
     * @author DengChao
     * @date 2022/1/21 15:28
     */
    public void queryClientInformation() {
        if (!firstShow) {
            System.out.print("请输入需要查找的客户信息的客户编号：");
            int findClientNumIndex = Tools.clientInformationNumExist();//获取需要查找的客户信息的客户编号
            if (findClientNumIndex < 0) {
                return;
            }
            System.out.println("客户编号" + "\t\t\t" + "客户姓名" + "\t\t\t" + "客户电话" + "\t\t\t" + "客户积分");
            //找到该客户编号对应的对象数组后，将该对象数组的地址值传进此方法，然后调用本类内的showClientInformation方法显示客户信息
            showClientInformation(ci[findClientNumIndex]);
            return;
        }
        System.out.println("\n当前还没有客户呢！");
    }
}