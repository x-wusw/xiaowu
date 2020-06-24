package Recommend;

import xiaowu.Jdbcs;
import xiaowu.Login;

import java.util.*;
import java.util.Map.Entry;

/**
 * 基于用户的协同过滤推荐算法实现
 */
public class UserCF {
    public static int resultUserId = 0;
    public static void main(String[] args) {
        Jdbcs jdbcs = new Jdbcs();
        List[] list = jdbcs.getData();
        Scanner scanner = new Scanner(System.in);
        //输入用户总量
        int N = list[0].size();
        int[][] sparseMatrix = new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        Map<String, Integer> userItemLength = new HashMap<>();//存储每一个用户对应的不同数据项总数  eg: A 3
        Map<String, Set<String>> itemUserCollection = new HashMap<>();//建立数据项到用户的倒排表 eg: a A B
        Set<String> items = new HashSet<>();//辅助存储物品集合
        Map<String, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射
        Map<Integer, String> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射
        for(int i = 0; i < N ; i++){//依次处理N个用户
            String temp = list[0].get(i)+" "+list[1].get(i)+" "+list[2].get(i)+""+list[3].get(i)+""+list[4].get(i);
            String[] user_item = temp.split(" ");
            int length = user_item.length;
            userItemLength.put(user_item[0], length-1);//eg: A 3
            userID.put(user_item[0], i);//用户ID与稀疏矩阵建立对应关系
            idUser.put(i, user_item[0]);
            //建立物品--用户倒排表
            for(int j = 1; j < length; j++){
                if(items.contains(user_item[j])){//如果已经包含对应的数据项--用户映射，直接添加对应的用户
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }else{//否则创建对应物品--用户集合映射
                    items.add(user_item[j]);
                    itemUserCollection.put(user_item[j], new HashSet<String>());//创建数据项--用户倒排关系
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }
            }
        }
        System.out.println(itemUserCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if(user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v数据项一致的项目总数
                }
            }
        }
        String recommendUser = Login.getNameTe();
        //计算用户之间的相似度【余弦相似性】
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
        //计算指定用户recommendUser的物品推荐度
        for(String item: items){//遍历每一件物品
            Set<String> users = itemUserCollection.get(item);//得到当前信息的所有用户集合
            if(!users.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
                double itemRecommendDegree = 0.0;
                for(String user: users){
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//推荐度计算
                }
                System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
            }
        }
        scanner.close();
    }

}
