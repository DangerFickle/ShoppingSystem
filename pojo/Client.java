package pojo;

/**
 * @ClassName ClientInformation
 * @Description TODO 储存客户的信息
 * @Author DengChao
 * @CreatTime 2022/1/21 15:35
 * @Vertion 1.0
 */
public class Client implements Comparable{
    //储存客户编号
    private int clientNum;
    //储存客户姓名
    private String clientName;
    //储存客户电话号码
    private String clientPhoneNum;
    //储存客户积分
    private int clientScore;

    public Client(int clientNum, String clientName, String clientPhoneNum, int clientScore) {
        this.clientNum = clientNum;
        this.clientName = clientName;
        this.clientPhoneNum = clientPhoneNum;
        this.clientScore = clientScore;
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNum() {
        return clientPhoneNum;
    }

    public void setClientPhoneNum(String clientPhoneNum) {
        this.clientPhoneNum = clientPhoneNum;
    }

    public int getClientScore() {
        return clientScore;
    }

    public void setClientScore(int clientScore) {
        this.clientScore = clientScore;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Client) {
            Client client = (Client) o;
            if (this.clientNum > client.clientNum) {
                return 1;
            } else if (this.clientNum < client.clientNum) {
                return -1;
            } else {
                return 0;
            }
        }
        throw new RuntimeException("传入的参数类型不一致！");
    }

    @Override
    public String toString() {
        return clientNum + "\t\t\t" + clientName + "\t\t\t" + clientPhoneNum + "\t\t" + clientScore;
    }


}
