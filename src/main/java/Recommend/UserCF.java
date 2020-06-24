package Recommend;

import xiaowu.Jdbcs;
import xiaowu.Login;

import java.util.*;
import java.util.Map.Entry;

/**
 * �����û���Эͬ�����Ƽ��㷨ʵ��
 */
public class UserCF {
    public static int resultUserId = 0;
    public static void main(String[] args) {
        Jdbcs jdbcs = new Jdbcs();
        List[] list = jdbcs.getData();
        Scanner scanner = new Scanner(System.in);
        //�����û�����
        int N = list[0].size();
        int[][] sparseMatrix = new int[N][N];//�����û�ϡ����������û����ƶȼ��㡾���ƶȾ���
        Map<String, Integer> userItemLength = new HashMap<>();//�洢ÿһ���û���Ӧ�Ĳ�ͬ����������  eg: A 3
        Map<String, Set<String>> itemUserCollection = new HashMap<>();//����������û��ĵ��ű� eg: a A B
        Set<String> items = new HashSet<>();//�����洢��Ʒ����
        Map<String, Integer> userID = new HashMap<>();//�����洢ÿһ���û����û�IDӳ��
        Map<Integer, String> idUser = new HashMap<>();//�����洢ÿһ��ID��Ӧ���û�ӳ��
        for(int i = 0; i < N ; i++){//���δ���N���û�
            String temp = list[0].get(i)+" "+list[1].get(i)+" "+list[2].get(i)+""+list[3].get(i)+""+list[4].get(i);
            String[] user_item = temp.split(" ");
            int length = user_item.length;
            userItemLength.put(user_item[0], length-1);//eg: A 3
            userID.put(user_item[0], i);//�û�ID��ϡ���������Ӧ��ϵ
            idUser.put(i, user_item[0]);
            //������Ʒ--�û����ű�
            for(int j = 1; j < length; j++){
                if(items.contains(user_item[j])){//����Ѿ�������Ӧ��������--�û�ӳ�䣬ֱ����Ӷ�Ӧ���û�
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }else{//���򴴽���Ӧ��Ʒ--�û�����ӳ��
                    items.add(user_item[j]);
                    itemUserCollection.put(user_item[j], new HashSet<String>());//����������--�û����Ź�ϵ
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }
            }
        }
        System.out.println(itemUserCollection.toString());
        //�������ƶȾ���ϡ�衿
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if(user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//�����û�u���û�v������һ�µ���Ŀ����
                }
            }
        }
        String recommendUser = Login.getNameTe();
        //�����û�֮������ƶȡ����������ԡ�
        int recommendUserId = userID.get(recommendUser);
        for (int j = 0;j < sparseMatrix.length; j++) {
            double temp = 0;
            double t = 0;
            if(j != recommendUserId){
                t = sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j)));
                if(t >= temp){
                    temp = t;
                    resultUserId = userID.get(j);
                } }
        }
        //����ָ���û�recommendUser����Ʒ�Ƽ���
        for(String item: items){//����ÿһ����Ʒ
            Set<String> users = itemUserCollection.get(item);//�õ���ǰ��Ϣ�������û�����
            if(!users.contains(recommendUser)){//������Ƽ��û�û�й���ǰ��Ʒ��������Ƽ��ȼ���
                double itemRecommendDegree = 0.0;
                for(String user: users){
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//�Ƽ��ȼ���
                }
                System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
            }
        }
        scanner.close();
    }

}
